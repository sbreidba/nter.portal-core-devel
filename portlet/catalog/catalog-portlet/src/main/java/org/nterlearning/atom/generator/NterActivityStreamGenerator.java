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

package org.nterlearning.atom.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.nterlearning.atom.parser.model.AsVerb;
import org.nterlearning.utils.PortalPropertiesUtil;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.parser.AbderaAtomParser;
import org.nterlearning.atom.parser.ServiceContextUtil;
import org.nterlearning.atom.parser.portlet.CourseRegistryClient;
import org.nterlearning.atom.parser.push.PubSubHubbubPublisher;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;

public class NterActivityStreamGenerator {

    public static final String DEFAULT_STREAM_URL = "stream-url-has-not-been-set";
    public static final String ACTIVITY_STREAM_SERVLET = "/nter-catalog-portlet/activity-stream";

    private static Log log = LogFactoryUtil.getLog(NterActivityStreamGenerator.class);
    private static Feed singletonFeed = null;
    private static String feedTitle = "NTER Activity Stream Feed";
    private static String streamUrl = DEFAULT_STREAM_URL;


    // fields dealing with a static activity stream feed file,
    // in case we ever go that route again
    @Deprecated
    private static String feedFileName = "reviews.xml";    
    @Deprecated
    private static String[] feedFilePathElements = {"nter-catalog-portlet", "course-feeds", feedFileName};
    @Deprecated
    private static String feedFileRootPath = System.getenv("CATALINA_BASE") + File.separatorChar + "webapps";


    /**
     * Creates an activity stream of the reviews in the database, up to the cutoff point
     *
     * @param streamUrl
     * @param cutoffTimestamp
     * @param global - true if the reviews are global; false otherwise
     * @return
     * @throws RuntimeException
     */
    private static Feed createActivityStream(String streamUrl, long cutoffTimestamp, boolean global)
        throws RuntimeException {

        try {
            if (cutoffTimestamp < 0) {
                cutoffTimestamp = getDefaultExpirationTimestamp();
            }

            // get all the local course reviews
            List<CourseReview> dbRevs = CourseReviewLocalServiceUtil.getCourseReviews(
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            // make a new feed
            log.info("Creating activity stream at URL [" + streamUrl +
            		"] using cutoff of " + new Date(cutoffTimestamp));
            Feed feed =
                    NterActivityStreamUtils.createNewStream(streamUrl,
                        getFeedTitle(), getFeedId(), getPushHubUrls());
            NterActivityStreamUtils.addReviewsToStream(dbRevs, feed, new Date(cutoffTimestamp),global);

            return feed;

        }
        catch (Exception e) {
            throw new RuntimeException("Error creating activity stream: " + e);
        }
    }

    /**
     *
     * @param streamUrl
     * @param cutoffTimestamp
     */
    private static void createSingletonActivityStream(String streamUrl, long cutoffTimestamp){

    	// make a new feed
        log.info("Initializing singleton activity stream");
    	singletonFeed = createActivityStream(streamUrl, cutoffTimestamp,true);

    	// since we don't know what happened since the last time we had a
        // stream, publish it just for safety
        publishStream(streamUrl);
    }


    /**
     * @param streamUrl - the URL where the stream will be published 
     * @param cutoffTimestamp the timestamp prior to which entries in the stream
     * are excluded
     *
     * @return the singleton Activity Stream populated with all the course reviews
     *         in persistence that are after the cutoff
     */
    public static Feed getActivityStream(String streamUrl, long cutoffTimestamp) {

        if (singletonFeed == null) {
            createSingletonActivityStream(streamUrl, getDefaultExpirationTimestamp());
        }

        // prune the feed to get rid of expired entries
        NterActivityStreamUtils.removeOlderEntries(singletonFeed, getDefaultExpirationTimestamp());

        if (cutoffTimestamp > getDefaultExpirationTimestamp()) {
            return NterActivityStreamUtils.copyFeed(singletonFeed, cutoffTimestamp);
        }
        else {
            return singletonFeed;
        }
    }


    /**
     * Gets the singleton activity stream
     *
     * @return
     */
    public static Feed getActivityStream() {
        return getActivityStream(getStreamUrl(), getDefaultExpirationTimestamp());
    }

    /**
     *
     * @param localReviewsStreamUrl
     * @return
     */
    public static Feed getLocalReviews(String localReviewsStreamUrl){
    	return createActivityStream(localReviewsStreamUrl, 0,false);
    }


    /**
     * @return
     */
    public static List<String> getPushHubUrls() {

        List<String> pushHubUrls = CourseRegistryClient.getPushHubUrls();
        if (pushHubUrls.isEmpty()) {
            log.warn("No PuSH hub URLs exist in the service registry");
        }
        return pushHubUrls;
    }


    /**
     * @param streamUrl
     */
    private static void publishStream(String streamUrl) {

        List<String> pushHubUrls = CourseRegistryClient.getPushHubUrls();
        if (!pushHubUrls.isEmpty()) {
            for (String hubUrl : pushHubUrls) {
                try {
                    PubSubHubbubPublisher.publish(streamUrl, hubUrl);
                }
                catch (Exception e) {
                    log.error("Error publishing stream at [" + streamUrl +
                            "] to hub at [" + hubUrl + "]: " + e, e);
                }
            }
        }
        else {
            log.info("Not publishing activity stream to any PuSH hubs " +
                    "because no PuSH hub URLs exist in the service registry");
        }
    }


    /**
     * @param courseReviewId
     * @param verb
     */
    private static void courseReviewChanged(long courseReviewId, AsVerb.VerbType verb) {

        try {
            Feed as = getActivityStream();
            String entryId = NterActivityStreamUtils.addReviewActivity(as, courseReviewId, verb, true);

            // publish the feed to PuSH hubs
            log.info("Added entry with id '" + entryId + "', containing a course review " +
                    verb + ", to activity stream. Publishing changes to PuSH hubs.");
            publishStream(as.getLink("self").getHref().toString());
        }
        catch (PortalException e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
        }
        catch (SystemException e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
        }
    }


    /**
     * @param courseReviewId
     */
    public static void courseReviewCreated(long courseReviewId) {
        courseReviewChanged(courseReviewId, AsVerb.VerbType.ADD);
    }


    /**
     * @param courseReviewId
     */
    public static void courseReviewDeleted(long courseReviewId) {
        courseReviewChanged(courseReviewId, AsVerb.VerbType.DELETE);
    }


    /**
     * @param courseReviewId
     */
    public static void courseReviewUpdated(long courseReviewId) {
        courseReviewChanged(courseReviewId, AsVerb.VerbType.UPDATE);
    }

    public static String getFeedId() {
        return AbderaAtomGenerator.generateAtomId("activity-stream");
    }


    public static String getFeedTitle() {
        return feedTitle;
    }


    public static String getFeedUrl() {
        String protocol = PortalPropertiesUtil.getWebServerProtocol();
        String port = PortalPropertiesUtil.getWebServerPort();
        String virtualHost = ServiceContextUtil.getDefaultVirtualHost();

        if (!port.equals("80") && !port.equals("443") && !port.equals("-1")) {
            virtualHost += ":" + port;
        }
        
        return protocol + "://" + virtualHost + ACTIVITY_STREAM_SERVLET;
    }


    public static void setFeedTitle(String feedTitle) {
        NterActivityStreamGenerator.feedTitle = feedTitle;
    }


    /**
     * Returns the default expiration timestamp, which is calculated as 1 month
     * prior to the current date/time.
     *
     * @return Long value of the default expiration timestamp
     */
    public static long getDefaultExpirationTimestamp() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, -1);
        return now.getTimeInMillis();
    }


    /**
     * @return the absolute URL to the file
     */
    public static String getStreamUrl() {
        if (streamUrl.equals(DEFAULT_STREAM_URL)) {
            streamUrl = getFeedUrl();
        }

        return streamUrl;
    }

    public static void setStreamUrl(String url){
    	streamUrl = url;
    }


    /*
     The below are methods dealing with a static activity stream feed file,
     in case we ever go that route again
    */


    /**
     * Retrieves an activity stream from a static file
     *
     * @return
     */
    @Deprecated
    public static Feed getActivityStreamFromFile(String streamUrl) {

        String feedFilePath = getFeedFilePath();
        File asFeedFile = new File(feedFilePath);
        Feed feed = null;

        log.info("Activity stream file path is set to: " + asFeedFile.getAbsolutePath());

        // if the static feed file doesn't exist, or they wanted a new stream, create a new feed
        if (!asFeedFile.exists()) {

            log.info("Activity stream feed file not found at " + asFeedFile.getAbsolutePath() +
                    ". Creating new feed.");
            feed = NterActivityStreamUtils.createNewStream(streamUrl, getFeedTitle(), getFeedId(), getPushHubUrls());
        }
        else {
            // parse it
            AbderaAtomParser parser = new AbderaAtomParser(asFeedFile);
            feed = parser.getFeed();

            // refresh all the hub links
            // remove the existing ones
            Iterator<Link> it = feed.getLinks("hub").iterator();
            while (it.hasNext()) {
                it.next().discard();
            }

            NterActivityStreamUtils.addPushHubLinks(feed, getPushHubUrls());
        }

        return feed;
    }


    /**
     * @param as
     *
     * @throws java.io.IOException
     */
    @Deprecated
    private static void updateActivityStreamFile(String streamUrl, Feed as)
            throws IOException {

        // update the stream
        String feedFilePath = getFeedFilePath();
        as.writeTo(AbderaAtomGenerator.getRegularWriter(), new FileWriter(feedFilePath));
        log.info("Generated activity stream feed at " + feedFilePath);

        publishStream(streamUrl);
    }


    /**
     * @return the relative (to the host) URL to the feed
     */
    @Deprecated
    public static String getFeedFileRelativeUrl() {
        String feedRelativeUrl = "";
        for (String pathElement : feedFilePathElements) {
            feedRelativeUrl += "/" + pathElement;
        }
        return feedRelativeUrl;
    }


    /**
     * @return the absolute path to the feed file on the file system
     */
    @Deprecated
    public static String getFeedFilePath() {
        return getFeedFileRootPath() + getFeedFileRelativePath();
    }


    /**
     * @return the relative (to Tomcat's webapps directory) path to the feed file
     */
    @Deprecated
    public static String getFeedFileRelativePath() {
        String feedFileRelativePath = "";
        for (String pathElement : feedFilePathElements) {
            feedFileRelativePath += File.separatorChar + pathElement;
        }

        return feedFileRelativePath;
    }


    /**
     * @return the absolute path to Tomcat's webapps directory
     */
    @Deprecated
    public static String getFeedFileRootPath() {
        return feedFileRootPath;
    }


    @Deprecated
    public static void setFeedFileRootPath(String feedFileRootPath) {
		NterActivityStreamGenerator.feedFileRootPath = feedFileRootPath;
	}
}
