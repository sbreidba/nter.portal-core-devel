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

package org.nterlearning.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sri.nter.registry.proxy.InstitutionBean;
import com.sri.nter.registry.proxy.Registry;
import com.sri.nter.registry.proxy.RegistryImpl;
import com.sri.nter.registry.proxy.ServiceBean;

import org.nterlearning.xml.nter_registry.blacklist_objects_0_1_0.ActiveStatusEnum;
import org.nterlearning.xml.nter_registry.domain_objects_0_1_0.Binding;
import org.nterlearning.xml.nter_registry.domain_objects_0_1_0.BindingTypeEnum;
import org.nterlearning.xml.nter_registry.domain_objects_0_1_0.ServiceTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;


public class ServiceRegistryClient {

    private volatile ExecutorService mExecutor;
    private int mTimeoutSecs = 15;

    private static Registry registry = null;

	private static final Log _log = LogFactoryUtil.getLog(ServiceRegistryClient.class);


    public ServiceRegistryClient() {
        mExecutor = Executors.newCachedThreadPool();
    }


    public List<String> getStudentFeeds() {
        return getStudentFeeds(ActiveStatusEnum.ACTIVE);
    }


    /**
     * Queries the ServiceRegistry for a list of StudentFeed endpoints that
     * match the provided Active Status.
     *
     * @param status Status to search for in the ServiceRegistry
     *
     * @return List of matching endpoints (may contain duplicates)
     */
    public List<String> getStudentFeeds(ActiveStatusEnum status) {
        return getServiceEndpoints(ServiceTypeEnum.CONTENT_REPOSITORY,
                    BindingTypeEnum.STUDENT_PROGRESS_ATOM, status);
    }


    /**
     * Queries the ServiceRegistry for a list of StudentRecord Endpoints and
     * their corresponding Content Provider Id's
     *
     * @return A HashMap of named-value pairs containing the endpoint and
     * content provider id.
     */
    public HashMap<String, String> getContentProviderStudentFeeds() {
        return getContentProviderStudentFeeds(ActiveStatusEnum.ACTIVE);
    }


    /**
     * Queries the ServiceRegistry for a list of StudentRecord Endpoints and
     * their corresponding Content Provider Id's
     *
     * @param status ActiveStatus to search for
     *
     * @return A HashMap of named-value pairs containing the endpoint and
     * content provider id.
     */
    public HashMap<String, String> getContentProviderStudentFeeds(ActiveStatusEnum status) {
        HashMap<String, String> feeds = new HashMap<String, String>();

        List<String> endpoints =
                getServiceEndpoints(ServiceTypeEnum.CONTENT_REPOSITORY,
                    BindingTypeEnum.STUDENT_PROGRESS_ATOM, status);

        for (final String endpoint : endpoints) {
            feeds.put(endpoint, getContentProviderId(endpoint));
        }

        return feeds;
    }


    /**
     * Queries the ServiceRegistry for list of all Active Course Feed endpoints.
     *
     * @return List of active Course Feeds (may contain dulicates).
     */
    public List<String> getCourseFeeds() {
        return getCourseFeeds(ActiveStatusEnum.ACTIVE);
    }


    /**
     * Queries the Service Registry for a list of Course Feed endpoints
     * matching the provided Active Status.
     *
     * @param status Active Status to search for
     *
     * @return List of Course Feed endpoints (may contain duplicates)
     */
    public List<String> getCourseFeeds(ActiveStatusEnum status) {
        return getServiceEndpoints(ServiceTypeEnum.CONTENT_REPOSITORY,
                        BindingTypeEnum.COURSE_CATALOG_ATOM, status);
    }


    /**
     * Queries the ServiceRegistry for a list of CourseFeed Endpoints and their
     * corresponding Content Provider Ids.
     *
     * @return A list of named-value pairs containing the endpoint and content
     * provider id.
     */
    public HashMap<String, String> getContentProviderCourseFeeds() {
        return getContentProviderCourseFeeds(ActiveStatusEnum.ACTIVE);
    }


    /**
     * Queries the ServiceRegistry for a list of CourseFeed Endpoints and their
     * corresponding Content Provider Ids.
     *
     * @param status ActiveStatus to search for
     *
     * @return A list of named-value pairs containing the endpoint and content
     * provider id.
     */
    public HashMap<String, String> getContentProviderCourseFeeds(ActiveStatusEnum status) {

        HashMap<String, String> feeds = new HashMap<String, String>();

        List<String> endpoints =
                getServiceEndpoints(ServiceTypeEnum.CONTENT_REPOSITORY,
                        BindingTypeEnum.COURSE_CATALOG_ATOM, status);

        for (final String endpoint : endpoints) {
            feeds.put(endpoint, getContentProviderId(endpoint));
        }

        return feeds;
    }


    public List<String> getCourseReviewFeeds() {
        return getServiceEndpoints(ServiceTypeEnum.COURSE_REVIEWS,
                       BindingTypeEnum.ACTIVITY_STREAM_ATOM, ActiveStatusEnum.ACTIVE);
    }


    /**
     *
     * @return a list of the PuSH Hub endpoints available in the registry
     */
    public List<String> getPushHubEndpoints(){
    	return getServiceEndpoints(ServiceTypeEnum.PUB_SUB,
                    BindingTypeEnum.XML_HTTP, ActiveStatusEnum.ACTIVE);
    }


    public List<ServiceBean> getContentProviders() {
        return getContentProviders(ActiveStatusEnum.ACTIVE);
    }


    public List<ServiceBean> getContentProviders(ActiveStatusEnum status) {
        return getServices(ServiceTypeEnum.CONTENT_REPOSITORY, status);
    }


    public String getSearchService() {
        String endPoint = null;
        List<String> endpoints =
                getServiceEndpoints(ServiceTypeEnum.SEARCH_INDEXER,
                     BindingTypeEnum.XML_HTTP, ActiveStatusEnum.ACTIVE);

        if ((endpoints != null) && !endpoints.isEmpty()) {
            endPoint = endpoints.get(0);
        }

        return endPoint;
    }


    private List<String> getServiceEndpoints(ServiceTypeEnum serviceType,
            BindingTypeEnum bindingType, ActiveStatusEnum status) {

        ServiceRegistryRequest srRequest = new ServiceRegistryRequest(
                serviceType, bindingType, status);
        final Future<?> srRequestProcess = ExecutorUtil.safeSubmit(mExecutor, srRequest);

        if (srRequestProcess != null) {
            try {
                srRequestProcess.get(mTimeoutSecs, TimeUnit.SECONDS);
            }
            catch (TimeoutException te) {
                _log.error("Timeout occurred attempting to connect to the ServiceRegistry");
                srRequestProcess.cancel(true);
            }
            catch (Exception e) {
                _log.error("Unknown error occurred connecting to the ServiceRegistry");
                srRequestProcess.cancel(true);
            }
        }

        return srRequest.getEndpoints();
    }


    private List<ServiceBean> getServices(ServiceTypeEnum serviceType,
                                          ActiveStatusEnum status) {

        ServiceRegistryRequest srRequest = new ServiceRegistryRequest(
                serviceType, null, status);
        final Future<?> srRequestProcess = ExecutorUtil.safeSubmit(mExecutor, srRequest);

        if (srRequestProcess != null) {
            try {
                srRequestProcess.get(mTimeoutSecs, TimeUnit.SECONDS);
            }
            catch (TimeoutException te) {
                _log.error("Timeout occurred attempting to connect to the ServiceRegistry");
                srRequestProcess.cancel(true);
            }
            catch (Exception e) {
                _log.error("Unknown error occurred connecting to the ServiceRegistry");
                srRequestProcess.cancel(true);
            }
        }

        return srRequest.getServices();
    }


    /**
     * Searches the ServiceRegistry for a ContentProvider's id, based on the
     * Service Endpoint.
     *
     * @param endpoint Service Endpoint to search for.
     *
     * @return Either the ContentProvider's Id, or null, if one was not found.
     */
    public String getContentProviderId(String endpoint) {
        String institutionName = "NWTP";
        if (endpoint != null) {
            InstitutionBean institution =
                getRegistryClient().getInstitutionFromAccessPoint(endpoint);

            if (institution != null) {
                institutionName = institution.getName();
            }
            else {
                _log.error("Unable to find institution by endpoint [" + endpoint + "]");
            }
        }
        else {
            _log.error("Unable to find institution by endpoint [" + endpoint + "]");
        }

        return institutionName;
    }


    private Registry getRegistryClient() {
        if (registry == null) {
            try {
                registry = new RegistryImpl();
            }
            catch (Exception e) {
                _log.error(e.getMessage());
            }
        }

        return registry;
    }


    /**
     * This class is used to connect to the service registry and request
     * certain information.  It is designed as a Runnable to allow it to be
     * handled by an Executor to allow for a Timeout check.
     */
    private class ServiceRegistryRequest implements Runnable {

        private ServiceTypeEnum mServiceType;
        private BindingTypeEnum mBindingTypeEnum;
        private ActiveStatusEnum mActiveStatus;

        private List<ServiceBean> mServices = new ArrayList<ServiceBean>();
        private List<String> mEndPoints = new ArrayList<String>();


        public ServiceRegistryRequest(ServiceTypeEnum serviceType,
                    BindingTypeEnum bindingType, ActiveStatusEnum activeStatus) {
            mServiceType = serviceType;
            mBindingTypeEnum = bindingType;
            mActiveStatus = activeStatus;
        }


        public List<String> getEndpoints() {
            return mEndPoints;
        }


        public List<ServiceBean> getServices() {
            return mServices;
        }


        public void run() {
            try {
                if (mBindingTypeEnum != null) {
                    mServices = getRegistryClient().getServicesByServiceAndBindingType(
                                    mServiceType, mBindingTypeEnum, mActiveStatus, mActiveStatus);

                    for (ServiceBean service : mServices) {
                        for (Binding binding : service.getBinding()) {
                            mEndPoints.add(binding.getAccessPoint());
                        }
                    }
                }
                else {
                    mServices = getRegistryClient().getServicesByServiceType(
                                    mServiceType, mActiveStatus, mActiveStatus);
                }
            }
            catch (Exception e) {
                _log.error(e.getMessage());
            }
        }
    }
}