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

package org.nterlearning.atom.parser;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.parser.portlet.CourseRegistryClient;
import org.nterlearning.atom.parser.portlet.ServiceRegistryClient;
import org.nterlearning.course.enumerations.FeedRemovalReasonType;
import org.nterlearning.course.management.portlet.CourseManagementPortlet;
import org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.utils.ExecutorUtil;
import org.apache.abdera.model.Feed;
import org.joda.time.DateTime;

import org.nterlearning.registry.client.ActiveStatusEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is designed to be the starting point for all feed parsing tasks.
 * The class is created as a singleton to prevent multiple threads from
 * attempting to process feeds concurrently.
 */
public class FeedParser {

    private static FeedParser mFeedParser = new FeedParser();
    private ServiceRegistryClient mServiceRegistryClient;

    private static final Log mLog = LogFactoryUtil.getLog(FeedParser.class);

    // timer information
    private long mIntervalMin = 10;

    // executor for thread pooling
    private volatile ScheduledExecutorService mExecutor;


    private FeedParser() {
        mExecutor = Executors.newSingleThreadScheduledExecutor();
        mServiceRegistryClient = new ServiceRegistryClient();
    }


    public static FeedParser getInstance() {
        return mFeedParser;
    }


    /**
     * Starts the Scheduled Feed Parser task.  This task runs every x minutes,
     * with the default being 10 minutes.  This time can be changed by updating
     * the <code>feedTimer</code> property in the portlet.xml file.
     */
    public void initiateFeedParserSchedule() {
        mLog.info("Starting the Feed Parser scheduler to run every "
                  + mIntervalMin + " minutes.");

        ExecutorUtil.safeSubmitFixedDelay(mExecutor, new FeedProcess(),
                mIntervalMin, mIntervalMin, TimeUnit.MINUTES);
    }


    /**
     * Shuts down the feed parser tasks and prevents any other tasks from being
     * started.  Ensure that this is called before garbage collection, or a
     * thread will leak.
     */
    public void shutdownTask() {
        mLog.info("Stopping Feed Parser task");

        mExecutor.shutdown();

        try {
            // Wait for existing tasks to terminate before attempting
            // to forcefully shut them down
            if (!mExecutor.awaitTermination(60, TimeUnit.SECONDS))  {
                mExecutor.shutdownNow();
                if (!mExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    mLog.error("Error stopping the FeedParser execution pool");
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
     * Restarts the Feed Parser task by first stopping any running tasks and
     * processes and restarting the executor.  This needs to be run if the
     * Feed Timer interval has changed.
     */
    public void restartFeedParseSchedule() {
        shutdownTask();
        initiateFeedParserSchedule();
    }


    /**
     * Restarts the Feed Parser task by first stopping any running tasks and
     * processes and restarting the executor.  Once restarted, the Feed Timer
     * uses the new interval.
     *
     * @param intervalMinutes New interval to use for the feedTimer.
     */
    public void restartFeedParseSchedule(long intervalMinutes) {
        setFeedTimerInterval(intervalMinutes);
        restartFeedParseSchedule();
    }


    /**
     * Adds an immediate feed processing task to the task queue.
     */
    public void runFeedParser() {
        ExecutorUtil.safeSubmit(mExecutor, new FeedProcess());
    }


    /**
     * Adds an immediate feed processing task to the task queue.  This task is
     * used to process a particular Feed Reference object based on the feed
     * reference id.  This is used from the course-feeds portlet to initiate
     * manual processing.
     *
     * @param feedRefId The selected Feed Reference object to process
     */
    public void runFeedParser(long feedRefId) {
        try {
            String feedIri =
                    FeedReferenceLocalServiceUtil.getFeedReference(
                            feedRefId).getFeedIri();
            ExecutorUtil.safeSubmit(mExecutor, new FeedRequestProcess(feedIri));
        }
        catch (Exception e) {
            mLog.error("Could not find FeedReference with id: " + feedRefId);
        }
    }


    /**
     * Adds an immediate feed processing task to the task queue.  This task is
     * used to process a feed that has been received through the PubSubHubbub
     * interface.
     *
     * @param feed Feed to process
     */
    public void runFeedParser(Feed feed) {
        ExecutorUtil.safeSubmit(mExecutor, new FeedUpdateProcess(feed));
    }


    /**
     * Adds an immediate feed processing task to the task queue.  This task is
     * used to process a particular Feed Reference object based on either the
     * feed reference's URL or the IRI.
     *
     * @param feedRef The selected Feed reference URL
     */
    public void runFeedParser(String feedRef) {
        ExecutorUtil.safeSubmit(mExecutor, new FeedRequestProcess(feedRef));
    }


    public void setFeedTimerInterval(long intervalMinutes) {
        // prevent the interval from being less than 1 minute
        mIntervalMin = Math.max(1, intervalMinutes);
    }


    public long getFeedTimerInterval() {
        return mIntervalMin;
    }


    /**
     * This class is used by the automatic feed processing timer task.  It
     * collects a list of feeds to process from the Service Registry.
     */
    private class FeedProcess implements Runnable {

        public void run() {

            Thread.currentThread().setName("AtomFeedProcessor");

            try {
                mLog.info("Fetching feeds from service registry");

                HashMap<ActiveStatusEnum, HashMap<String, String>> repos =
                        CourseRegistryClient.getContentRepositories();

                if (repos.containsKey(ActiveStatusEnum.ACTIVE)) {
                    List<FeedContext> fcs = FeedContext.createList(repos.get(ActiveStatusEnum.ACTIVE));
                    fcs.addAll(FeedContext.createList(CourseRegistryClient.getCourseReviewFeedUrls()));

                    if (fcs.size() > 0) {
                        updateFeedStatus(fcs);

                        mLog.info("Processing " + fcs.size() + " active feeds from service registry");
                        AtomFeedProcessor.processFeeds(fcs);
                    }
                }

                if (repos.containsKey(ActiveStatusEnum.INACTIVE)) {
                    removeFeeds(repos.get(ActiveStatusEnum.INACTIVE), FeedRemovalReasonType.INACTIVE);
                }

                if (repos.containsKey(ActiveStatusEnum.BLACKLIST)) {
                    removeFeeds(repos.get(ActiveStatusEnum.BLACKLIST), FeedRemovalReasonType.BLACKLISTED);
                }

				mLog.info("Feed processing from service registry complete");
            }
            catch (Throwable e) {
                mLog.error("FeedProcess threw exception: " + e,e);
                Thread.currentThread().interrupt();
            }

			try {
				CourseManagementPortlet.updateUserHelpfulness();
			}
            catch (Exception e) {
				mLog.error("Error updating User Helpfulness" + e,e);
			}

			try {
				DateTime time = new DateTime().minusMinutes(2);
				CourseReviewLocalServiceUtil.purgeAllRemovedOlderThan(time.toDate());
			}
            catch (Exception e) {
				mLog.error("Error purging removed courses", e);
			}
		}

        /**
         * Check each of the 'available' feeds against what we have in the
         * database and update accordingly.  We currently don't have a better
         * way of knowing what feeds have been un-blacklisted or reactivated.
         *
         * @param activeFcs List of active FeeContext objects
         */
        private void updateFeedStatus(List<FeedContext> activeFcs) {
            for (FeedContext fc : activeFcs) {
                try {
                    FeedReference feedRef =
                            FeedReferenceLocalServiceUtil.findByFeedHref(fc.getFeedUrl());
                    if (feedRef.isRemoved() &&
                            (feedRef.getRemovedReason().equals(FeedRemovalReasonType.BLACKLISTED.getCode())) ||
                            (feedRef.getRemovedReason().equals(FeedRemovalReasonType.INACTIVE.getCode()))) {
                        feedRef.setRemoved(false);
                        feedRef.setRemovedDate(null);
                        feedRef.setRemovedReason(null);
                        FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
                    }
                }
                catch (NoSuchFeedReferenceException e) {
                    mLog.debug("Could not find a feedReference with HREF: " + fc.getFeedUrl());
                }
                catch (Exception e) {
                    mLog.warn("Could not update feedReference with HREF: " + fc.getFeedUrl());
                }
            }
        }


        private void removeFeeds(HashMap<String, String> feedEndPoints,
                                 FeedRemovalReasonType reason) {
            for (String endpoint : feedEndPoints.keySet()) {
                try {
                    FeedReference feedRef =
                            FeedReferenceLocalServiceUtil.findByFeedHref(endpoint);
                    if (!feedRef.isRemoved()) {
                        feedRef.setRemoved(true);
                        feedRef.setRemovedDate(new Date());
                        feedRef.setRemovedReason(reason.getCode());

                        FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
                    }
                }
                catch (NoSuchFeedReferenceException fre) {
                    mLog.debug("Could not find feedReference with endpoint: " + endpoint);
                }
                catch (Exception e) {
                    mLog.warn("Error encountered updating feedReference: " + endpoint);
                }
            }
        }
    }


    /**
     * This class is used to process feed updates that are provided through the
     * PubSubHubbub interface.
     */
    private class FeedUpdateProcess implements Runnable {
        private Feed mFeed;

        public FeedUpdateProcess(Feed feed) {
            mFeed = feed;
        }

        public void run() {

            Thread.currentThread().setName("PubSubHubbubFeedProcessor");

            try {
                String feedUrl = mFeed.getSelfLinkResolvedHref().toString();
                String contentProviderId = mServiceRegistryClient.getContentProviderId(feedUrl);

                FeedContext fc = new FeedContext(feedUrl, contentProviderId);

                AtomFeedProcessor.processFeed(fc, mFeed);
            }
            catch (Throwable e) {
                mLog.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * This class is used to manually process a particular feed, based on the
     * either the feed IRI or feed URL information.
     */
    private class FeedRequestProcess implements Runnable {

        private String mFeedRef;

        public FeedRequestProcess(String feedRef) {
            mFeedRef = feedRef.trim();
        }

        public void run() {

            Thread.currentThread().setName("ManualFeedProcessor");

            try {
                mLog.info("Starting a manual Feed Processing Task for Feed: " + mFeedRef);
                AtomFeedProcessor.processFeed(mFeedRef);
            }
            catch (Throwable e) {
                mLog.error(e);
                Thread.currentThread().interrupt();
            }
        }
    }
}