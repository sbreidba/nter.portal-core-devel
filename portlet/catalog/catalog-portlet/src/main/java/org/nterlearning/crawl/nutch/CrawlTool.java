/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */


package org.nterlearning.crawl.nutch;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;

import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalServiceUtil;
import org.nterlearning.utils.PortalProperties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.nutch.crawl.*;
import org.apache.nutch.fetcher.Fetcher;
import org.apache.nutch.parse.ParseSegment;

import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.utils.ExecutorUtil;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * This class implements the Nutch CrawlTool.  It is designed as a singleton to
 * prevent multiple crawlers from concurrently affecting the same index files.
 *
 * This class also has checks enabled to ensure that it is only run on the
 * NTER instance designated as the master Nutch indexer.  To configure the NTER
 * instance, update the portal-ext.properties file to include:
 * <pre>
 *      nter.nutch.role=master
 *      nter.nutch.home.dir={nutch home dir}
 *      nter.nutch.indexer.type={solr}
 *      nter.nutch.solr.url={solr url}
 *      nter.nutch.solr.user={username}
 *      nter.nutch.solr.password={password}
 * </pre>
 *
 * The primary usage of this class is to add course objects via the
 * <code>addCourse</code> methods.  This allows additional metadata to be added
 * to the index from the course object.  Once a collection of courses are added,
 * run <code>crawl()</code> to initiate the crawling and indexing.
 *
 * Additionally, it can be used to remove course information from the index.
 * To do this, utilize the <code>removeFromIndex(Course)</code> or
 * <code>removeFeedReferenceFromIndex</code> methods.
 *
 * Implementation Notes:
 * A typical Nutch deployment maintains a current crawldb, linkdb, and segment
 * directories, each representing everything that has been crawled and parsed.
 * Typically, this is used so Nutch knows when it should recrawl a particular
 * page.
 *
 * However, our implementation of Nutch is rather unique.  Because courses
 * are constantly being added and removed, having a list of every course that
 * has ever been parsed creates problems.  Primarily is the fact the Nutch
 * reprocesses segment directories for each crawl.  If a course is in segment1,
 * and later deleted, during a subsequent crawl, segment1 is readded to the
 * index and the course is re-indexed.  The solution to this is to simply
 * delete the crawldb, linkdb, and segments prior to a crawl.  This forces any
 * course information to be recrawled and prevents any previous information
 * from being readded to the index.
 *
 */
public class CrawlTool {

    private static CrawlTool mCrawlTool = new CrawlTool();

    // global file system paths
    private FileSystem mFS = null;
    private Path mCrawlDbDir = null;
    private Path mLinkDbDir = null;
    private Path mSegmentDir = null;
    private Path mURLDir = null;

    // global configuration
    private Configuration mConfig = null;
    private int mMaxDepth = 10;
    private int mMaxThreads = 10;
    private long mCrawlIntervalMin = 60;

    // indexer properties
    private String mIndexerName = PortalProperties.SOLR_INDEXER_NAME;
    private String mSolrUrl = null;

    private HashSet<Course> mCourseList = new HashSet<Course>();
    private HashSet<Component> mComponentList = new HashSet<Component>();

    // status
    private boolean mIsMaster = false;
    private String mStatus = null;

    // executor for thread pooling
    private volatile ScheduledExecutorService mExecutor;

    private static final Log mLog = LogFactoryUtil.getLog(CrawlTool.class);


    /**
     * Private constructor to prevent multiple instances from being created.
     */
    private CrawlTool() {

        mExecutor = Executors.newSingleThreadScheduledExecutor();

        String role = PropsUtil.get(PortalProperties.NUTCH_ROLE_PROPERTY);
        mIsMaster = !(role == null) && (role.equals(PortalProperties.NUTCH_MASTER_ROLE));

        // if this is not the master node, don't configure anything
        if (!mIsMaster) {
            shutdownTasks();
        }
        else {
            try {
                mConfig = Config.createCrawlConfiguration();

                String indexerType = PropsUtil.get(PortalProperties.NUTCH_INDEXER_TYPE_PROPERTY);
                if (indexerType != null) {
                    mIndexerName = indexerType;
                    mConfig.set(PortalProperties.NUTCH_INDEXER_TYPE_PROPERTY, mIndexerName);
                }

                String solrUrl = PropsUtil.get(PortalProperties.SOLR_URL_PROPERTY);
                if (solrUrl != null) {
                    mSolrUrl = solrUrl;
                    mConfig.set(PortalProperties.SOLR_URL_PROPERTY, mSolrUrl);
                }

                IndexTool.getInstance().setIndexer(mIndexerName, mConfig);

                mFS = FileSystem.get(mConfig);
                createDataDirectories();

                mStatus = "Initialized";
            }
            catch (Exception e) {
                shutdownTasks();
                mCrawlTool = null;
                mStatus = "Error occurred during initialization. " +
                        "Please check log files for details. " + e.getMessage();
                e.printStackTrace();
            }
        }
    }


    /**
     * Returns the Nutch CrawlTool instance.   This is used to prevent multiple
     * crawlers from being created simultaneously.
     *
     * @return Nutch crawler
     */
    public static CrawlTool getInstance() {
        return mCrawlTool;
    }


    /**
     * Shuts down the crawl task and prevents any other tasks from being
     * starting.  Ensure that this is called before garbage collection, or a
     * thread will leak.
     */
    public void shutdownTasks() {
        mExecutor.shutdown();

        try {
            // Wait for existing tasks to terminate before attempting
            // to forcefully shut them down
            if (!mExecutor.awaitTermination(60, TimeUnit.SECONDS))  {
                mExecutor.shutdownNow();
                if (!mExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    mLog.error("Error stopping the CrawlTool execution pool");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch (InterruptedException e) {
            mExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Adds a crawl task to the task queue to be run immediately.
     */
    public void runCrawl() {
        ExecutorUtil.safeSubmit(mExecutor, new CrawlProcess());
    }


    /**
     * Initializes the scheduled crawl task, and adds it to the ThreadPool to
     * ensure only a single, thread-safe operation executes at a time.  The
     * scheduling of the task is handled by the portlet.xml file
     */
    public void initiateCrawlSchedule() {

        mLog.info("CrawlTool : Using Nutch Home: " + Config.getNutchHomeDir());
        mLog.info("CrawlTool : Using Nutch Data: " + Config.getNutchDataDir());

        ExecutorUtil.safeSubmitFixedDelay(mExecutor, new CrawlProcess(),
                mCrawlIntervalMin, mCrawlIntervalMin, TimeUnit.MINUTES);
    }


    /**
     * Removes an object from the current index.  The object may represent a
     * course, course-component, or feed.  This task task is added to the
     * ThreadPool to ensure only a single, thread-safe operation executes at a
     * time.
     *
     * @param removeObject the object to remove from the index.
     */
    public void removeFromIndex(Object removeObject) {
        ExecutorUtil.safeSubmit(mExecutor, new RemoveFromIndexProcess(removeObject));
    }


    /**
     * Purges all items from the index.  This task is added to the ThreadPool
     * to ensure only a single, thread-safe operation executes at a time.
     */
    public void purgeIndex() {
        ExecutorUtil.safeSubmit(mExecutor, new PurgeIndexProcess());
    }


    /**
     * Configures and creates the appropriate directories for storing the
     * indexes.
     *
     * @throws java.io.IOException if unable to create directories
     */
    private void createDataDirectories() throws IOException {

        // set the database and segment directories
        String nutchHomeDir = mConfig.get(PortalProperties.NUTCH_HOME_DIR_PROPERTY);
        String nutchDataDir = mConfig.get(PortalProperties.NUTCH_DATA_DIR_PROPERTY);

        mCrawlDbDir = new Path(nutchDataDir, NutchConstants.NUTCH_CRAWLDB);
        mLinkDbDir = new Path(nutchDataDir, NutchConstants.NUTCH_LINKDB);
        mSegmentDir = new Path(nutchDataDir, NutchConstants.NUTCH_SEGMENTS);
        mURLDir = new Path(nutchHomeDir, NutchConstants.NUTCH_URL);

        // ensure that the necessary directories exist
        mFS.mkdirs(mCrawlDbDir);
        mFS.mkdirs(mLinkDbDir);
        mFS.mkdirs(mSegmentDir);
        mFS.mkdirs(mURLDir);
    }


    /**
     * Deletes data directories that were created during a previous index run.
     */
    private void deleteDataDirectories() {
        CrawlUtil.rmDir(mFS, CrawlUtil.getSubDirectories(mCrawlDbDir));
        CrawlUtil.rmDir(mFS, CrawlUtil.getSubDirectories(mLinkDbDir));
        CrawlUtil.rmDir(mFS, CrawlUtil.getSubDirectories(mSegmentDir));
    }


    /**
     * Deletes any URL files that may have been created during a previous run
     * and were not correctly removed.  This may happen if an exception was
     * encountered during the run, or more likely, a developer stops debugging
     * part way through a test.
     */
    private void deleteURLTempFiles() {
        File urlFiles[] = new File(mURLDir.toString()).listFiles();
        if (urlFiles != null) {
            for (File urlFile : urlFiles) {
                urlFile.delete();
            }
        }
    }


    /**
     * Creates a temporary file containing the URLs currently stored in the
     * course and component lists.  This file is used by the crawl task to
     * bootstrap the system.
     *
     * Once objects have been added to the temporary file, they are removed from
     * the listing.
     *
     * @return A handle to the temporary file that contains the URLs
     */
    private File createUrlSeedFile() {

        File tmpUrlFile = null;

        String guestUrl = CrawlUtil.getGuestUrl();
        try {
            tmpUrlFile = File.createTempFile("url", ".tmp", new File(mURLDir.toString()));
            BufferedWriter urlOut = new BufferedWriter(new FileWriter(tmpUrlFile));
            String fullTextHref;

            for (Component component : mComponentList) {
                try {
                    FeedReference feedRef =
                            FeedReferenceLocalServiceUtil.getFeedReference(component.getFeedReferenceId());
                    fullTextHref = URLDecoder.decode(component.getFullTextHref(), StringPool.UTF8);

                    List<Courses_Components> courses_components =
                            Courses_ComponentsLocalServiceUtil.findByComponentId(component.getComponentId());
                    StringBuffer coursesTitle = new StringBuffer();
                    for (Courses_Components courses_component : courses_components) {
                        Course course = CourseLocalServiceUtil.findByCourseId(courses_component.getCourseId());
                        coursesTitle.append(course.getTitle("en_US"));
                        coursesTitle.append(" ");
                    }

                    if (!(fullTextHref == null) && !(fullTextHref.equals(""))) {
                        // Note: Each meta tag must also be added to the urlmeta
                        // and db.parsemeta.to.crawldb properties in nutch-site.xml
                        urlOut.write(
                                fullTextHref
                                        + "\t" + NutchConstants.FEED_IRI_INDEX_TAG + "=" + feedRef.getFeedIri()
                                        + "\t" + NutchConstants.CLASS_INDEX_TAG + "=" + Component.class.getName()
                                        + "\t" + NutchConstants.IRI_INDEX_TAG + "=" + component.getComponentIri()
                                        + "\t" + NutchConstants.TITLE_INDEX_TAG + "=" + component.getTitle()
                                        + "\t" + NutchConstants.COURSE_TITLE_INDEX_TAG + "=" + coursesTitle.toString()
                                        + System.getProperty("line.separator"));
                    }
                }
                catch (Exception e) {
                    mLog.error("Could not find FeedReference for component IRI: "
                            + component.getComponentIri());
                }
            }

            for (Course course : mCourseList) {
                try {
                    FeedReference feedRef =
                            FeedReferenceLocalServiceUtil.getFeedReference(course.getFeedReferenceId());
                    fullTextHref = URLDecoder.decode(course.getFullTextHref(), StringPool.UTF8);

                    if (!(fullTextHref == null) && !(fullTextHref.equals(""))) {
                        // Note: Each meta tag must also be added to the urlmeta
                        // and db.parsemeta.to.crawldb properties in nutch-site.xml
                        urlOut.write(
                                fullTextHref
                                + "\t" + NutchConstants.FEED_IRI_INDEX_TAG + "=" + feedRef.getFeedIri()
                                + "\t" + NutchConstants.CLASS_INDEX_TAG + "=" + Course.class.getName()
                                + "\t" + NutchConstants.IRI_INDEX_TAG + "=" + course.getCourseIri()
                                + "\t" + NutchConstants.TITLE_INDEX_TAG + "=" + course.getTitle()
                                + "\t" + NutchConstants.COURSE_DETAILS_INDEX_TAG + "=http://" + guestUrl + "/course-details?cid=" + course.getCourseId()
                                + System.getProperty("line.separator"));
                    }
                }
                catch (Exception e) {
                    mLog.error("Could not find FeedReference for course IRI: "
                            + course.getCourseIri());
                }
            }

            urlOut.close();
            mCourseList.clear();
            mComponentList.clear();
        }
        catch (IOException e) {
            mLog.error("Could not create temp. URL file at " + mURLDir.toString());
        }

        return tmpUrlFile;
    }


    /**
     * Injects the URLs found in the seed file into the crawlDb.
     *
     * @throws Exception - Thrown due to error opening seed file
     */
    private void injectCrawlDB() throws Exception {

        mStatus = "Injecting courses into the crawlDb.";

        // create temporary seed file and inject the urls into the crawldb
        File seedFile = createUrlSeedFile();
        try {
            Injector injector = new Injector(mConfig);
            injector.inject(mCrawlDbDir, mURLDir);
        }
        catch (IOException e) {
            mLog.error(e.getMessage());
            seedFile.delete();

            throw new Exception(e);
        }

        seedFile.delete();
    }


    /**
     * Generates a fetch list for all of the pages to be fetched and parsed. The
     * fetch list is placed in a newly created segment directory. The segment
     * directory is named based on the time it's created.
     *
     * @return Path[] of the newly created fetch list, null if no segments were
     * added.
     */
    private Path[] generateFetchList() {
        try {
            Generator generator = new Generator(mConfig);
            return generator.generate(mCrawlDbDir, mSegmentDir, -1,
                    Long.MAX_VALUE, System.currentTimeMillis());
        }
        catch (Exception e) {
            mLog.error("Generator: " + e.getMessage());
            return null;
        }
    }


    /**
     * Initiates a Nutch crawl against any URLs or courses stored in the
     * crawltool.
     */
    private void crawl() {

        if (mCrawlTool == null) {
            mLog.error("Crawl tool not correctly initialized.  View logs for details.");
            return;
        }

        if ((mCourseList.size() == 0) && (mComponentList.size() == 0)) {
            mLog.info("No courses or components to crawl.");
            return;
        }

        // clean out any previously created data
        deleteDataDirectories();
        deleteURLTempFiles();

        Fetcher fetcher = new Fetcher(mConfig);
        ParseSegment parseSegment = new ParseSegment(mConfig);
        CrawlDb crawlDb = new CrawlDb(mConfig);

        try {
            injectCrawlDB();

            int i;
            for (i = 0; i < mMaxDepth; i++) {

                // generate the fetch list of URLs to process
                mStatus = "Generating files to fetch for depth " + i + " of " + mMaxDepth;
                Path segments[] = generateFetchList();
                if (segments == null) {
                    mLog.info("Stopping at depth: " + i + " - no more URLs to fetch");
                    break;
                }

                mStatus = "Downloading files to index for depth " + i + " of " + mMaxDepth;
                fetcher.fetch(segments[0], mMaxThreads);

                mStatus = "Parsing files for depth " + i + " of " + mMaxDepth;
                parseSegment.parse(segments[0]);

                // update the existing crawldb
                mStatus = "Updating crawldb for files fetched at depth " + i + " of " + mMaxDepth;
                crawlDb.update(mCrawlDbDir, segments, true, true, true, true);
            }

            if (i > 0) {

                // if pages were found and parsed, index them
                mStatus = "Inverting LinkDB to improve indexing efficiency.";
                LinkDb linkDb = new LinkDb(mConfig);
                linkDb.invert(mLinkDbDir, mSegmentDir, true, true, true);

                mStatus = "Indexing data using " + mIndexerName;
                IndexTool.getInstance().index();
            }
            else {
                mLog.info("No URLs to fetch.");
            }
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
        finally {
            mStatus = "Waiting for next crawl.";
        }
    }


    /**
     * Returns whether or not this is the master nutch crawl node.
     *
     * @return Returns true if this is the master crawl node, false otherwise.
     */
    public boolean isMaster() {
        return mIsMaster;
    }


    /**
     * Adds a course to the list of courses to process.
     *
     * @param course Course to crawl.
     */
    public void addCourse(Course course) {
        if (course != null) {
            mCourseList.add(course);
        }
    }


    public void addCourseComponent(Component component) {
        if (component != null) {
            mComponentList.add(component);
        }
    }


    /**
     * Get the number of courses currently stored for processing.
     *
     * @return Number of courses to process.
     */
    public int getCourseListCount() {
        return mCourseList.size();
    }


    public int getComponentListCount() {
        return mComponentList.size();
    }


    public int getCompleteListCount() {
        return mCourseList.size() + mComponentList.size();
    }


    public String getStatus() {
        return mStatus;
    }


    public void setMaxDepth(int depth) {
        // don't allow depth to be less than 1
        mMaxDepth = Math.max(1, depth);
    }

    public int getMaxDepth() {
        return mMaxDepth;
    }

    public void setMaxThreads(int threads) {
        // don't allow threads to be less than 1
        mMaxThreads = Math.max(1, threads);
    }

    public int getMaxThreads() {
        return mMaxThreads;
    }

    public String getIndexerName() {
        return mIndexerName;
    }

    public String getSolrUrl() {
        return mSolrUrl;
    }

    public void setCrawlIntervalTimer(long minutes) {
        // prevent the interval from being less than 1 minute
        mCrawlIntervalMin = Math.max(1, minutes);
    }

    public long getCrawlIntervalMinutes() {
        return mCrawlIntervalMin;
    }


    /**
     * This class is used to add the crawl() method to the current task queue.
     */
    private class CrawlProcess implements Runnable {
        public void run() {

            Thread.currentThread().setName("NutchCrawlProcess");

            try {
                crawl();
            }
            catch (RuntimeException e) {
                mLog.fatal(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
            catch (Exception e) {
                mLog.fatal(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * This class is used to purge all index data on the server.
     */
    private static class PurgeIndexProcess implements Runnable {
        public void run() {

            Thread.currentThread().setName("NutchPurgeIndexProcess");

            try {
                IndexTool.getInstance().purgeIndex();
            }
            catch (RuntimeException e) {
                mLog.fatal(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * This class is used to add the appropriate remove_FromIndex method to the
     * current task queue.
     */
    private class RemoveFromIndexProcess implements Runnable {
        Object removedObject = null;

        public RemoveFromIndexProcess(Object remove) {
            removedObject = remove;
        }

        public void run() {

            Thread.currentThread().setName("NutchRemoveFromIndexProcess");

            try {
                if (removedObject instanceof Course) {
                    mStatus = "Removing course " +
                            ((Course)removedObject).getCourseIri() + " from the index";
                    mCourseList.remove((Course)removedObject);
                    IndexTool.getInstance().removeCourseFromIndex((Course)removedObject);
                }
                else if (removedObject instanceof Component) {
                    mStatus = "Removing component " +
                            ((Component)removedObject).getComponentIri() + " from the index";
                    mComponentList.remove((Component)removedObject);
                    IndexTool.getInstance().removeCourseComponentFromIndex((Component)removedObject);
                }
                else if (removedObject instanceof FeedReference) {
                    mStatus = "Removing FeedReference " +
                            ((FeedReference)removedObject).getFeedIri() + " from the index";
                    IndexTool.getInstance().removeFeedReferenceFromIndex(((FeedReference)removedObject).getFeedIri());
                }
                else {
                    mLog.error("Unknown object type to remove.");
                }
            }
            catch (RuntimeException e) {
                mLog.fatal(e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (Exception e) {
                mLog.warn(e.getMessage());
                Thread.currentThread().interrupt();
            }
            finally {
                mStatus = "Waiting for next crawl";
            }
        }
    }
}
