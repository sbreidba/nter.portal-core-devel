/*
 National Training and Education Resource (NTER)
 Copyright (C) 2012  SRI International

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or (at
 your option) any later version.

 This program is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.
 */

package org.nterlearning.atom.parser.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import com.sri.nter.registry.proxy.ServiceBean;

import org.nterlearning.xml.nter_registry.blacklist_objects_0_1_0.ActiveStatusEnum;
import org.nterlearning.xml.nter_registry.domain_objects_0_1_0.Binding;

import java.util.*;


/**
 * This class is used to connect to the Service Registry and request current
 * list of course, student record, and activity feeds. 
 *
 * @author gjiva
 */
public class CourseRegistryClient {

	private static Log log = LogFactoryUtil.getLog(CourseRegistryClient.class);

	
	/**
	 * Connects to the Service Registries and returns a list of PubSubHubbub
     * endpoints.  Note, this list may contain duplicates if a PuSH hub is
     * stored in both the local and global registry.
     *
	 * @return List of String PuSH Hub URLs
	 */
	public static List<String> getPushHubUrls(){		
		try {
			ServiceRegistryClient serviceRegistryClient = new ServiceRegistryClient();
			return serviceRegistryClient.getPushHubEndpoints();
		}
		catch (Exception e) {
			throw new RuntimeException(
					"Exception while retrieving PuSH Hub URLs from the Course Registry", e);
		}
	}

    
	/**
	 * Retrieves the list of Atom feed URLs form the Course Registry
	 *
	 * @return List of String Feed URLs
	 */
	public static List<String> getFeedUrls() {
		try {
			ServiceRegistryClient serviceRegistryClient = new ServiceRegistryClient();

			List<String> feedUrls = serviceRegistryClient.getCourseFeeds();
			feedUrls.addAll(serviceRegistryClient.getStudentFeeds());
            feedUrls.addAll(serviceRegistryClient.getCourseReviewFeeds());

			return feedUrls;
		}
		catch (Exception e) {
			throw new RuntimeException(
					"Exception while retrieving Atom feed URLs from the Course Registry", e);
		}
	}


    /**
     * Retrieves a listing of all Atom feed URLs and their corresponding
     * ContentProvider ids.
     *
     * @return Hashmap\<endpoint,contentProviderId\> containing all feed urls.
     */
    public static HashMap<String, String> getContentProviderFeedUrls() {
        try {
            ServiceRegistryClient serviceRegistryClient = new ServiceRegistryClient();

            HashMap<String, String> feeds = serviceRegistryClient.getContentProviderCourseFeeds();
            feeds.putAll(serviceRegistryClient.getContentProviderStudentFeeds());

            return removeSubscribedFeeds(feeds);
        }
        catch (Exception e) {
			throw new RuntimeException(
					"Exception while retrieving Atom feed URLs from the Course Registry", e);
        }
    }


    /**
     * Returns a list of Course Review endpoints from the service registry.
     *
     * @return a list of Course Review endpoints
     */
    public static List<String> getCourseReviewFeedUrls() {
        ServiceRegistryClient srClient = new ServiceRegistryClient();
        return removeSubscribedFeeds(srClient.getCourseReviewFeeds());
    }


    /**
     * Returns a list of blacklisted course and student record feeds from the
     * service registries.
     *
     * @return List of blacklisted feed endpoints
     */
    public static List<String> getBlacklistedFeedUrls() {
        ServiceRegistryClient srClient = new ServiceRegistryClient();

        List<String> feeds = srClient.getCourseFeeds(ActiveStatusEnum.BLACKLIST);
        feeds.addAll(srClient.getStudentFeeds(ActiveStatusEnum.BLACKLIST));

        return feeds;
    }


    /**
     * Returns a list of inactive (unspecified) course and student record feeds
     * from the service registries.
     *
     * @return List of inactive feed endpoints.
     */
    public static List<String> getInactiveFeedUrls() {
        ServiceRegistryClient srClient = new ServiceRegistryClient();

        List<String> feeds = srClient.getCourseFeeds(ActiveStatusEnum.INACTIVE);
        feeds.addAll(srClient.getStudentFeeds(ActiveStatusEnum.INACTIVE));

        return feeds;
    }


    /**
     * Returns a HashMap containing a Hashmap of content repository endpoints
     * and their institution name.
     * 
     * @return hashmap of all endpoints
     */
    public static HashMap<ActiveStatusEnum, HashMap<String, String>> getContentRepositories() {
        ServiceRegistryClient srClient = new ServiceRegistryClient();

        HashMap<String, String> activeFeeds = new HashMap<String, String>();
        HashMap<String, String> inactiveFeeds = new HashMap<String, String>();
        HashMap<String, String> blacklistedFeeds = new HashMap<String, String>();

        HashMap<ActiveStatusEnum, HashMap<String, String>> contentRepos =
                new HashMap<ActiveStatusEnum, HashMap<String, String>>();

        List<ServiceBean> contentProviders =
                srClient.getContentProviders(ActiveStatusEnum.UNSPECIFIED);
        for (ServiceBean contentProvider : contentProviders) {
            if (contentProvider.getActiveStatus() == ActiveStatusEnum.ACTIVE) {
                activeFeeds.putAll(addContentReposFeed(contentProvider));
            }
            else if (contentProvider.getActiveStatus() == ActiveStatusEnum.INACTIVE) {
                inactiveFeeds.putAll(addContentReposFeed(contentProvider));
            }
            else if (contentProvider.getActiveStatus() == ActiveStatusEnum.BLACKLIST) {
                blacklistedFeeds.putAll(addContentReposFeed(contentProvider));
            }
        }

        activeFeeds = removeSubscribedFeeds(activeFeeds);
        contentRepos.put(ActiveStatusEnum.ACTIVE, activeFeeds);
        contentRepos.put(ActiveStatusEnum.BLACKLIST, blacklistedFeeds);
        contentRepos.put(ActiveStatusEnum.INACTIVE, inactiveFeeds);

        return contentRepos;
    }


    /**
     * Returns a HashMap of endpoints and Institution name for any provider that
     * contains content related bindings.
     *
     * @param provider ContentProvider to process
     *
     * @return Hashmap of endpoint and institution name
     */
    private static HashMap<String, String> addContentReposFeed(ServiceBean provider) {
        HashMap<String, String> feeds = new HashMap<String, String>();
        for (Binding binding : provider.getBinding()) {
            if (ServiceRegistryClient.CONTENT_BINDING_TYPES.contains(binding.getBindingType())) {
                feeds.put(binding.getAccessPoint(), provider.getInstitutionName());
            }
        }

        return feeds;
    }


    /**
     * Accepts a list of feeds (either student record, course, or activity
     * stream) and removes any feeds that are to be handled by pubsubhubbub.
     * This is determined by first searching for a FeedReference object with
     * the feed's endpoint, and then checking the pubSubscribed field.  If a
     * feed reference object is not found, or it is not currently subscribed,
     * then it is added to the returned list to be processed.
     *
     * @param feeds A complete list of feeds to validate
     *
     * @return A list of only feeds that should be processed
     */
    private static HashMap<String, String> removeSubscribedFeeds(HashMap<String, String> feeds) {
        HashMap<String, String> validFeeds = new HashMap<String, String>();

        Set<String> endpoints = feeds.keySet();
        for (String endpoint : endpoints) {
            try {
                FeedReference feedRef = FeedReferenceLocalServiceUtil.findByFeedHref(endpoint);

                if (!feedRef.getPshbSubscribed()) {
                    validFeeds.put(endpoint, feeds.get(endpoint));
                }
            }
            catch (NoSuchFeedReferenceException ne) {
                validFeeds.put(endpoint, feeds.get(endpoint));
            }
            catch (Exception e) {
                log.error("Error attempting to find feed with endpoint: " + endpoint);    
            }
        }

        return validFeeds;
    }


    private static List<String> removeSubscribedFeeds(List<String> feeds) {
        List<String> endpoints = new ArrayList<String>();

        for (String feed : feeds) {
            try {
                FeedReference feedRef = FeedReferenceLocalServiceUtil.findByFeedHref(feed);
                if (!feedRef.getPshbSubscribed()) {
                    endpoints.add(feed);
                }
            }
            catch (NoSuchFeedReferenceException nse) {
                endpoints.add(feed);
            }
            catch (Exception e) {
                log.error("Error attempting to find feed with endpoing: " + feed);
            }
        }

        return endpoints;
    }
}