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

package org.nterlearning.atom.parser;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import javax.portlet.ActionRequest;
import java.util.*;

/**
 * The <code>FeedContext</code> class is used to store Feed specific information
 * that is needed for proper processing, but that is not readily available in
 * the Feed itself.  This information is passed through the processing flow
 * along with the Feed.
 */
public class FeedContext {

    private long feedReferenceId;
    private long feedReferenceGroupId;
    private long companyId;
    private long scopeGroupId;
    private long userId;
    private ServiceContext sc;
    private String contentProviderId;
    private String feedUrl;
    private String syncMessage;

    private static Log mLog = LogFactoryUtil.getLog(FeedContext.class);


    /**
     * Public constructor.
     * <p/>
     * Creates a ServiceContext using default values.  Only use when a
     * ServiceContext cannot be obtained from an ActionRequest.
     *
     * @param feedUrl URL to convert into a FeedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException Standard Liferay Exception
     */
    public FeedContext(String feedUrl)
            throws SystemException, PortalException {
        this(ServiceContextUtil.createDefaultServiceContext(), feedUrl, null);
    }


    public FeedContext(String feedUrl, String contentProviderId)
            throws SystemException, PortalException {
        this(ServiceContextUtil.createDefaultServiceContext(), feedUrl, contentProviderId);
    }


    public FeedContext(ActionRequest request, String url) throws Exception {
        this(ServiceContextUtil.getServiceContext(request, url), url);
    }


    public FeedContext(ServiceContext sc, String feedUrl) {
        this(sc, feedUrl, null);
    }


    /**
     * Public constructor.
     *
     * @param sc The ServiceContext is required in order to add courses and get
     * company, group and user IDs.
     * @param feedUrl URL to convert into a FeedContext object
     * @param contentProviderId the
     */
    public FeedContext(ServiceContext sc, String feedUrl, String contentProviderId) {
        this.sc = sc;
        this.feedUrl = feedUrl;
        this.contentProviderId = contentProviderId;
        this.companyId = sc.getCompanyId();

        try {
            Group guestGroup = GroupLocalServiceUtil.getGroup(sc.getCompanyId(), GroupConstants.GUEST);
            this.scopeGroupId = guestGroup.getGroupId();
        }
        catch (Exception e) {
            this.scopeGroupId = sc.getScopeGroupId();
        }

        this.userId = sc.getUserId();
        this.syncMessage = new String();
    }


    public long getFeedReferenceId() {
        return feedReferenceId;
    }


    public void setFeedReferenceId(long feedReferenceId) {
        this.feedReferenceId = feedReferenceId;
    }


    public long getFeedReferenceGroupId() {
        return feedReferenceGroupId;
    }


    public void setFeedReferenceGroupId(long feedReferenceGroupId) {
        this.feedReferenceGroupId = feedReferenceGroupId;
    }


    public long getCompanyId() {
        return companyId;
    }


    public long getScopeGroupId() {
        return scopeGroupId;
    }


    public long getUserId() {
        return userId;
    }


    public ServiceContext getSc() {
        return sc;
    }


    public String getFeedUrl() {
        return feedUrl;
    }


    public String getContentProviderId() {
        return contentProviderId;
    }


    public String getSyncMessage() {
        return syncMessage;
    }


    public void setSyncMessage(String syncMessage) {
        if (syncMessage.length() > 2000) {
            this.syncMessage = syncMessage.substring(0, 2000);
        }
        else {
            this.syncMessage = syncMessage;
        }
    }


    public void addSyncMessage(String syncMessageText) {
        String syncMessage = this.syncMessage;
        if (syncMessage.equals("")) {
            syncMessage = syncMessageText;
        }
        else {
            syncMessage = syncMessage.concat("; \n" + syncMessageText);
        }
        
        if (syncMessage.length() > 2000) {
            this.syncMessage = syncMessage.substring(0, 2000);
        }
        else {
            this.syncMessage = syncMessage;
        }
    }


    /**
     * Creates a list of FeedContext objects from the default ServiceContext and
     * a list of URLs
     *
     * @param feedUrls List of URLs to convert to FeedContext objects
     *
     * @return A list of FeedContext objects generated from the URLs.
     */
    public static List<FeedContext> createList(List<String> feedUrls) {

        List<FeedContext> contexts = new Vector<FeedContext>();

        for (String url : feedUrls) {
            try {
                contexts.add(new FeedContext(url));
            }
            catch (Exception e) {
                mLog.error("Could not process URL: " + url + " due to: " + e.getMessage());
            }
        }

        return contexts;
    }


    /**
     * Creates a list of FeedContext objects from an ActionRequest and a list of
     * feed URLs
     *
     * @param feedUrls List of URLs to convert to FeedContext objects
     * @param request Associated ActionRequest
     *
     * @return List of FeedContext objects based on the URLs and request
     */
    public static List<FeedContext> createList(List<String> feedUrls, ActionRequest request) {

        List<FeedContext> contexts = new Vector<FeedContext>();

        for (String url : feedUrls) {
            try {
                contexts.add(new FeedContext(request, url));
            }
            catch (Exception e) {
                mLog.error("Could not process URL: " + url + " due to: " + e.getMessage());
            }
        }

        return contexts;
    }


    /**
     * Creates a list of FeedContext objects from the default ServiceContext and
     * a HashMap of feed URLs and their corresponding content provider ids.
     *
     * @param feedUrls Hashmap of feedURLs and content provider ids
     *
     * @return The generated FeedContext objects
     */
    public static List<FeedContext> createList(HashMap<String, String> feedUrls) {
        List<FeedContext> contexts = new ArrayList<FeedContext>();
        ServiceContext defaultContext = ServiceContextUtil.createDefaultServiceContext();

        Set<String> endpoints = feedUrls.keySet();
        for (String endpoint : endpoints) {
            contexts.add(new FeedContext(defaultContext, endpoint, feedUrls.get(endpoint)));
        }

        return contexts;
    }
}