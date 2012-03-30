/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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

package org.nterlearning.atom.parser.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import org.nterlearning.atom.parser.FeedParser;
import org.nterlearning.atom.parser.push.PubSubHubbubSubscriber;
import org.nterlearning.course.enumerations.FeedRemovalReasonType;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalServiceUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletResponse;


/**
 * This class provides methods for the Course-Feeds control panel page.
 *
 * @author gjiva
 *
 */
public class AtomParserControlPanelPortlet extends MVCPortlet {

    static Log mLog = LogFactoryUtil.getLog(AtomParserControlPanelPortlet.class);

	public static final String PARAM_URL_TO_PARSE = "feeds-url-to-parse";
    public static final String FEED_TIMER_PROPERTY = "feedTimer";

    private FeedParser mFeedParser = FeedParser.getInstance();
    private PubSubHubbubSubscriber mPushSubscriber = PubSubHubbubSubscriber.getInstance();

    @Override
    public void init() throws PortletException {

    	// configure the feed timer interval
        String feedTimer = getInitParameter(FEED_TIMER_PROPERTY);
        if (feedTimer != null) {
        	mLog.info("Setting feed timer interval from init-param '" + 
        			FEED_TIMER_PROPERTY + "' to [" + feedTimer + "]");
            mFeedParser.setFeedTimerInterval(Long.valueOf(feedTimer));
        }
        else {
        	mLog.warn("The init-param '" + FEED_TIMER_PROPERTY + 
        			"' is not set. The default feed timer interval of [" +
        			mFeedParser.getFeedTimerInterval() + "] will be used.");
        }

        // initialize the feed parser timer task
        mFeedParser.initiateFeedParserSchedule();

        super.init();
    }

    @Override
    public void destroy() {
        // destroy the feed parser timer task
        mFeedParser.shutdownTask();
        super.destroy();
    }


	/**
	 * Parses an Atom feed located at the URL given in the PARAM_URL_TO_PARSE
     * parameter
	 *
	 * @param request HTTP Request handler
	 * @param response HTTP response handler
     *
	 * @throws Exception Standard Liferay Exception
	 */
	public void parseUrl(ActionRequest request, ActionResponse response)
        throws Exception{

		String urlToParse = request.getParameter(PARAM_URL_TO_PARSE);
        if ((urlToParse != null) && !urlToParse.equals("")) {
		    mFeedParser.runFeedParser(urlToParse);
        }
	}

	/**
	 * Parses all Atom feeds located in the Service Registry
	 *
	 * @param request HTTP Request handler
	 * @param response HTTP response handler
	 */
	public void processCourseRegistryFeeds(ActionRequest request, ActionResponse response) {
        mFeedParser.runFeedParser();
	}
    

    /**
     * Parses a single Course Feed object that is defined in the request
     * parameter <code>feedRefId</code>.
     *
     * @param request HTTP Request handler
     * @param response HTTP response handler
     *
     * @throws Exception Standard Liferay Exception
     */
    public void processCourseFeed(ActionRequest request, ActionResponse response)
            throws Exception {

        // TODO : This currently doesn't work, but we may not need/want to
        // handle individual feeds.
        try {
            Long feedId = ParamUtil.getLong(request, "feedRefId");
            String feedIri = FeedReferenceLocalServiceUtil.getFeedReference(feedId).getFeedIri();
            mFeedParser.runFeedParser(feedIri);
        }
        catch (Exception e) {
            SessionErrors.add(request, e.getMessage());
            mLog.error(e.getMessage());
        }
    }

    /**
     * Manually disables the selected course-feed, defined via the request
     * parameter <code>feedRefId</code>.  This routine is called from
     * /course-feeds/html/cp/editActions.jsp.
     *
     * @param request HTTP Request handler
     * @param response HTTP response handler
     *
     * @throws Exception Standard Liferay Exception
     */
    @Deprecated
    public void disableCourseFeed(ActionRequest request, ActionResponse response)
            throws Exception {

        Long feedId = ParamUtil.getLong(request, "feedRefId");
        FeedReference feedRef = FeedReferenceLocalServiceUtil.getFeedReference(feedId);

        feedRef.setRemoved(true);
        feedRef.setRemovedDate(new Date());
        feedRef.setRemovedReason(FeedRemovalReasonType.REMOVED.getCode());

        // updateFeedReference also updates the enabled/disabled state of courses
        FeedReferenceLocalServiceUtil.updateFeedReference(feedRef, true);

        // unsubscribe from any hubs
        if (feedRef.getPshbSubscribed()) {
            String hubs[] = feedRef.getPshb().split(",");
            for (String hub : hubs) {
                mPushSubscriber.unsubscribe(hub, feedRef.getHref());
            }
        }

        response.setRenderParameter("feedCur", request.getParameter("feedCur"));
        response.setRenderParameter("feedDelta", request.getParameter("feedDelta"));
        response.setRenderParameter("feedRedirect", request.getParameter("feedRedirect"));
        response.setRenderParameter("feedRefId", feedId.toString());
        response.setRenderParameter("feedTabs", request.getParameter("feedTabs"));
    }

    /**
     * Manually enables the selected course-feed.  This routine is called from
     * /course-feeds/html/cp/editActions.jsp.
     *
     * @param request HTTP Request handler
     * @param response HTTP response handler
     *
     * @throws Exception Standard Liferay Exception
     */
    @Deprecated
    public void enableCourseFeed(ActionRequest request, ActionResponse response)
            throws Exception {

        Long feedId = ParamUtil.getLong(request, "feedRefId");
        FeedReference feedRef = FeedReferenceLocalServiceUtil.getFeedReference(feedId);

        feedRef.setRemoved(false);
        feedRef.setRemovedDate(null);
        feedRef.setRemovedReason(null);

        // updateFeedReference also updates the enabled/disabled state of courses
        FeedReferenceLocalServiceUtil.updateFeedReference(feedRef, true);

        // subscribe to any hubs
        if (!feedRef.getPshb().equals("")) {
            String hubs[] = feedRef.getPshb().split(",");
            for (String hub : hubs) {
                mPushSubscriber.subscribe(hub, feedRef.getHref());
            }
        }

        response.setRenderParameter("feedCur", request.getParameter("feedCur"));
        response.setRenderParameter("feedDelta", request.getParameter("feedDelta"));
        response.setRenderParameter("feedRedirect", request.getParameter("feedRedirect"));
        response.setRenderParameter("feedRefId", feedId.toString());
        response.setRenderParameter("feedTabs", request.getParameter("feedTabs"));
    }

    /**
     * Deletes all entries for a particular feed from the FeedSyncHistory table.
     * This routine is called from /course-feeds/html/cp/feedDetails.jsp.
     *
     * @param request HTTP Request handler
     * @param response HTTP response handler
     *
     * @throws Exception Standard Liferay Exception
     */
    public void clearFeedSyncHistory(ActionRequest request, ActionResponse response)
                throws Exception {

        Long feedRefId = ParamUtil.getLong(request, "feedRefId");
        FeedSyncHistoryLocalServiceUtil.purgeFeedSyncHistory(feedRefId, 0);
    }

    /**
     * Updates the owners of the FeedReference objects.  This routine is called
     * from /course-feeds/html/cp/view.jsp
     *
     * @param request HTTP Request handler
     * @param response HTTP Response handler
     *
     * @throws Exception Standard Liferay Exception
     */
    public void updateFeedOwner(ActionRequest request, ActionResponse response)
            throws Exception {

        long feedId[] = ParamUtil.getLongValues(request, "feedRefId");
        long newGroupId[] = ParamUtil.getLongValues(request, "ownerSelect");

        for (int i = 0; i < newGroupId.length; i++) {
            updateFeedOwner(feedId[i], newGroupId[i]);
        }
    }

    /**
     * Updates the owner of the FeedReference.
     *
     * @param feedRefId The feedRefId of the FeedReference to change.
     * @param newGroupId The new groupId to change the FeedReference to.
     *
     * @throws Exception Standard Liferay Exception
     */
    public void updateFeedOwner(long feedRefId, long newGroupId)
        throws Exception {

        FeedReference feedRef = FeedReferenceLocalServiceUtil.getFeedReference(feedRefId);

        if (feedRef.getGroupId() != newGroupId) {
            feedRef.setGroupId(newGroupId);

            // updateFeedReference also updates the course owners
            FeedReferenceLocalServiceUtil.updateFeedReference(feedRef, true);
        }
    }


    /**
     * Unsubscribes a feed from its respective PubSubHubbub hubs.  This routine
     * is called from /course-feeds/html/cp/view.jsp
     *
     * @param request HTTP Request handler
     * @param response HTTP Response handler
     *
     * @throws Exception Standard Liferay Exception
      */
    public void pushUnsubscribe(ActionRequest request, ActionResponse response)
            throws Exception {

        Long feedRefId = ParamUtil.getLong(request, "feedRefId");
        FeedReference feedRef = FeedReferenceLocalServiceUtil.getFeedReference(feedRefId);
        int responseCode;

        List<String> hubs = Arrays.asList(feedRef.getPshb().split(","));
        for (String hub : hubs) {
            // the unsubscribe routine updates the feedref object if successful
            responseCode = mPushSubscriber.unsubscribe(hub, feedRef.getHref());

            if (responseCode == HttpServletResponse.SC_BAD_REQUEST) {
                SessionErrors.add(request, "course-feed-details-push-unsubscribe-failed", hub);
            }
        }

        if (request.getParameter("jspPage") != null) {
        response.setRenderParameter("jspPage", request.getParameter("jspPage"));
        }
        
        response.setRenderParameter("feedCur", request.getParameter("feedCur"));
        response.setRenderParameter("feedDelta", request.getParameter("feedDelta"));
        response.setRenderParameter("feedRedirect", request.getParameter("feedRedirect"));
        response.setRenderParameter("feedRefId", feedRefId.toString());
        response.setRenderParameter("feedTabs", request.getParameter("feedTabs"));
    }


    /**
     * Resubscribes a feed from its respective PubSubHubbub hubs.  This routine
     * is called from /course-feeds/html/cp/view.jsp
     *
     * @param request HTTP Request handler
     * @param response HTTP Response handler
     *
     * @throws Exception Standard Liferay Exception
      */
    public void pushResubscribe(ActionRequest request, ActionResponse response)
            throws Exception {

        Long feedRefId = ParamUtil.getLong(request, "feedRefId");
        FeedReference feedRef = FeedReferenceLocalServiceUtil.getFeedReference(feedRefId);
        int responseCode;

        List<String> hubs = Arrays.asList(feedRef.getPshb().split(","));
        if (feedRef.getPshbSubscribed()) {
            for (String hub : hubs) {
                // the unsubscribe routine updates the feedref object if successful
                responseCode = mPushSubscriber.unsubscribe(hub, feedRef.getHref());

                if (responseCode == HttpServletResponse.SC_BAD_REQUEST) {
                    SessionErrors.add(request, "course-feed-details-push-unsubscribe-failed", hub);
                }
            }

            // pause for a few seconds to ensure the unsubscribe messages went through
            Thread.sleep(2000);
        }
        
        for (String hub : hubs) {
            // the subscribe routine updates the feedref object if successful
            responseCode = mPushSubscriber.subscribe(hub, feedRef.getHref());

            if (!((responseCode == HttpServletResponse.SC_ACCEPTED) ||
                  (responseCode == HttpServletResponse.SC_NO_CONTENT))) {
                SessionErrors.add(request, "course-feed-details-push-resubscribe-failed", hub);
            }
        }

        if (request.getParameter("jspPage") != null) {
        response.setRenderParameter("jspPage", request.getParameter("jspPage"));
        }
        
        response.setRenderParameter("feedCur", request.getParameter("feedCur"));
        response.setRenderParameter("feedDelta", request.getParameter("feedDelta"));
        response.setRenderParameter("feedRedirect", request.getParameter("feedRedirect"));
        response.setRenderParameter("feedRefId", feedRefId.toString());
        response.setRenderParameter("feedTabs", request.getParameter("feedTabs"));
    }
}