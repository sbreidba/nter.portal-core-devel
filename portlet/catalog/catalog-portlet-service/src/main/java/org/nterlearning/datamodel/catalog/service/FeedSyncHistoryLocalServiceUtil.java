package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the feed sync history local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.FeedSyncHistoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistoryLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.FeedSyncHistoryLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.FeedSyncHistoryLocalServiceImpl
 * @generated
 */
public class FeedSyncHistoryLocalServiceUtil {
    private static FeedSyncHistoryLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.FeedSyncHistoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the feed sync history to the database. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @return the feed sync history that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory addFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addFeedSyncHistory(feedSyncHistory);
    }

    /**
    * Creates a new feed sync history with the primary key. Does not add the feed sync history to the database.
    *
    * @param syncId the primary key for the new feed sync history
    * @return the new feed sync history
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory createFeedSyncHistory(
        long syncId) {
        return getService().createFeedSyncHistory(syncId);
    }

    /**
    * Deletes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param syncId the primary key of the feed sync history
    * @throws PortalException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFeedSyncHistory(long syncId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFeedSyncHistory(syncId);
    }

    /**
    * Deletes the feed sync history from the database. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFeedSyncHistory(feedSyncHistory);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory fetchFeedSyncHistory(
        long syncId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchFeedSyncHistory(syncId);
    }

    /**
    * Returns the feed sync history with the primary key.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history
    * @throws PortalException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory getFeedSyncHistory(
        long syncId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getFeedSyncHistory(syncId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the feed sync histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of feed sync histories
    * @param end the upper bound of the range of feed sync histories (not inclusive)
    * @return the range of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> getFeedSyncHistories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFeedSyncHistories(start, end);
    }

    /**
    * Returns the number of feed sync histories.
    *
    * @return the number of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public static int getFeedSyncHistoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFeedSyncHistoriesCount();
    }

    /**
    * Updates the feed sync history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @return the feed sync history that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFeedSyncHistory(feedSyncHistory);
    }

    /**
    * Updates the feed sync history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @param merge whether to merge the feed sync history with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the feed sync history that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFeedSyncHistory(feedSyncHistory, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    /**
    * Purges older feedSyncHistory log entries from the database.
    *
    * @param feedRefId The feedReferenceId to purge entries for.
    * @param retainCount The number of entries to retain
    * @throws PortalException - Liferay's SystemException
    * @throws SystemException - Liferay's SystemException
    */
    public static void purgeFeedSyncHistory(long feedRefId, long retainCount)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().purgeFeedSyncHistory(feedRefId, retainCount);
    }

    /**
    * Prunes the FeedSyncHistory table for a given FeedReference object.  It
    * retains the latest number of entries based on the configuration parameter
    * in the portlet.xml file.
    *
    * @param feedRefId The id of the FeedReference object to prune entries for.
    * @throws PortalException - Standard Liferay exception
    * @throws SystemException - Standard Liferay exception
    */
    public static void pruneFeedSyncHistory(long feedRefId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().pruneFeedSyncHistory(feedRefId);
    }

    /**
    * Returns a list of FeedSyncHistory objects corresponding to a particular
    * feedReference object.
    *
    * @param feedRefId The feedReferenceId to search for.
    * @return A Collection of FeedSyncHistory objects
    * @throws NoSuchFeedSyncHistoryException - Returned if no objects are found
    * @throws SystemException - Liferay's SystemException
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByFeedReference(
        long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException {
        return getService().findByFeedReference(feedRefId);
    }

    /**
    * Generates a dynamicQuery object that searches for FeedSyncHistory objects
    * based on the associated feedReferenceIds.
    *
    * @param feedRefId The feedReferenceId to search for
    * @return The associated dynamicQuery
    */
    public static com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        long feedRefId) {
        return getService().generateDynamicQuery(feedRefId);
    }

    public static void clearService() {
        _service = null;
    }

    public static FeedSyncHistoryLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FeedSyncHistoryLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FeedSyncHistoryLocalService.class.getName(),
                    portletClassLoader);

            _service = new FeedSyncHistoryLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FeedSyncHistoryLocalServiceUtil.class,
                "_service");
            MethodCache.remove(FeedSyncHistoryLocalService.class);
        }

        return _service;
    }

    public void setService(FeedSyncHistoryLocalService service) {
        MethodCache.remove(FeedSyncHistoryLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(FeedSyncHistoryLocalServiceUtil.class,
            "_service");
        MethodCache.remove(FeedSyncHistoryLocalService.class);
    }
}
