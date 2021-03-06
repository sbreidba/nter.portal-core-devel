package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the feed reference local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.FeedReferenceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferenceLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.FeedReferenceLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.FeedReferenceLocalServiceImpl
 * @generated
 */
public class FeedReferenceLocalServiceUtil {
    private static FeedReferenceLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.FeedReferenceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the feed reference to the database. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @return the feed reference that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedReference addFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addFeedReference(feedReference);
    }

    /**
    * Creates a new feed reference with the primary key. Does not add the feed reference to the database.
    *
    * @param feedReferenceId the primary key for the new feed reference
    * @return the new feed reference
    */
    public static org.nterlearning.datamodel.catalog.model.FeedReference createFeedReference(
        long feedReferenceId) {
        return getService().createFeedReference(feedReferenceId);
    }

    /**
    * Deletes the feed reference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @throws PortalException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFeedReference(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFeedReference(feedReferenceId);
    }

    /**
    * Deletes the feed reference from the database. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFeedReference(feedReference);
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

    public static org.nterlearning.datamodel.catalog.model.FeedReference fetchFeedReference(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchFeedReference(feedReferenceId);
    }

    /**
    * Returns the feed reference with the primary key.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @return the feed reference
    * @throws PortalException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedReference getFeedReference(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getFeedReference(feedReferenceId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the feed references.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of feed references
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> getFeedReferences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFeedReferences(start, end);
    }

    /**
    * Returns the number of feed references.
    *
    * @return the number of feed references
    * @throws SystemException if a system exception occurred
    */
    public static int getFeedReferencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFeedReferencesCount();
    }

    /**
    * Updates the feed reference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @return the feed reference that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedReference updateFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFeedReference(feedReference);
    }

    /**
    * Updates the feed reference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @param merge whether to merge the feed reference with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the feed reference that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedReference updateFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFeedReference(feedReference, merge);
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

    public static org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return getService().findByFeedIri(feedIri);
    }

    public static org.nterlearning.datamodel.catalog.model.FeedReference fetchByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByFeedIri(feedIri);
    }

    public static org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return getService().findByFeedHref(href);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        long groupId, boolean removed) {
        return getService().generateDynamicQuery(groupId, removed);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        boolean removed) {
        return getService().generateDynamicQuery(removed);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        boolean removed, java.lang.String reasonCode) {
        return getService().generateDynamicQuery(removed, reasonCode);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        java.lang.String feedType, long groupId, boolean removed) {
        return getService().generateDynamicQuery(feedType, groupId, removed);
    }

    public static void removeAssociatedVocabularies(long feedReferenceId) {
        getService().removeAssociatedVocabularies(feedReferenceId);
    }

    /**
    * Clears the cache for all feedReferences stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    */
    public static void clearCache() {
        getService().clearCache();
    }

    /**
    * Clears the cache for all feedReferences stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    *
    * @param feedRef the FeedReference object to remove from the cache
    */
    public static void clearCache(
        org.nterlearning.datamodel.catalog.model.FeedReference feedRef) {
        getService().clearCache(feedRef);
    }

    /**
    * Clears the cache for all feedReferences stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    *
    * @param feedRefId The Id of the FeedReference to remove from the cache
    */
    public static void clearCache(java.lang.Long feedRefId) {
        getService().clearCache(feedRefId);
    }

    public static void clearService() {
        _service = null;
    }

    public static FeedReferenceLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FeedReferenceLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FeedReferenceLocalService.class.getName(),
                    portletClassLoader);

            _service = new FeedReferenceLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FeedReferenceLocalServiceUtil.class,
                "_service");
            MethodCache.remove(FeedReferenceLocalService.class);
        }

        return _service;
    }

    public void setService(FeedReferenceLocalService service) {
        MethodCache.remove(FeedReferenceLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(FeedReferenceLocalServiceUtil.class,
            "_service");
        MethodCache.remove(FeedReferenceLocalService.class);
    }
}
