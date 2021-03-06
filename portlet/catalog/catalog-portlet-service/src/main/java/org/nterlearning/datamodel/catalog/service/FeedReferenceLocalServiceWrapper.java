package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedReferenceLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedReferenceLocalService
 * @generated
 */
public class FeedReferenceLocalServiceWrapper
    implements FeedReferenceLocalService,
        ServiceWrapper<FeedReferenceLocalService> {
    private FeedReferenceLocalService _feedReferenceLocalService;

    public FeedReferenceLocalServiceWrapper(
        FeedReferenceLocalService feedReferenceLocalService) {
        _feedReferenceLocalService = feedReferenceLocalService;
    }

    /**
    * Adds the feed reference to the database. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @return the feed reference that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference addFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.addFeedReference(feedReference);
    }

    /**
    * Creates a new feed reference with the primary key. Does not add the feed reference to the database.
    *
    * @param feedReferenceId the primary key for the new feed reference
    * @return the new feed reference
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference createFeedReference(
        long feedReferenceId) {
        return _feedReferenceLocalService.createFeedReference(feedReferenceId);
    }

    /**
    * Deletes the feed reference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @throws PortalException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFeedReference(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _feedReferenceLocalService.deleteFeedReference(feedReferenceId);
    }

    /**
    * Deletes the feed reference from the database. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @throws SystemException if a system exception occurred
    */
    public void deleteFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        _feedReferenceLocalService.deleteFeedReference(feedReference);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.dynamicQuery(dynamicQuery);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.dynamicQuery(dynamicQuery, start, end);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference fetchFeedReference(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.fetchFeedReference(feedReferenceId);
    }

    /**
    * Returns the feed reference with the primary key.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @return the feed reference
    * @throws PortalException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference getFeedReference(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.getFeedReference(feedReferenceId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.getPersistedModel(primaryKeyObj);
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
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> getFeedReferences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.getFeedReferences(start, end);
    }

    /**
    * Returns the number of feed references.
    *
    * @return the number of feed references
    * @throws SystemException if a system exception occurred
    */
    public int getFeedReferencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.getFeedReferencesCount();
    }

    /**
    * Updates the feed reference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @return the feed reference that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference updateFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.updateFeedReference(feedReference);
    }

    /**
    * Updates the feed reference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedReference the feed reference
    * @param merge whether to merge the feed reference with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the feed reference that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference updateFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.updateFeedReference(feedReference,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _feedReferenceLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _feedReferenceLocalService.setBeanIdentifier(beanIdentifier);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return _feedReferenceLocalService.findByFeedIri(feedIri);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference fetchByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedReferenceLocalService.fetchByFeedIri(feedIri);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return _feedReferenceLocalService.findByFeedHref(href);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        long groupId, boolean removed) {
        return _feedReferenceLocalService.generateDynamicQuery(groupId, removed);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        boolean removed) {
        return _feedReferenceLocalService.generateDynamicQuery(removed);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        boolean removed, java.lang.String reasonCode) {
        return _feedReferenceLocalService.generateDynamicQuery(removed,
            reasonCode);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        java.lang.String feedType, long groupId, boolean removed) {
        return _feedReferenceLocalService.generateDynamicQuery(feedType,
            groupId, removed);
    }

    public void removeAssociatedVocabularies(long feedReferenceId) {
        _feedReferenceLocalService.removeAssociatedVocabularies(feedReferenceId);
    }

    /**
    * Clears the cache for all feedReferences stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    */
    public void clearCache() {
        _feedReferenceLocalService.clearCache();
    }

    /**
    * Clears the cache for all feedReferences stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    *
    * @param feedRef the FeedReference object to remove from the cache
    */
    public void clearCache(
        org.nterlearning.datamodel.catalog.model.FeedReference feedRef) {
        _feedReferenceLocalService.clearCache(feedRef);
    }

    /**
    * Clears the cache for all feedReferences stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    *
    * @param feedRefId The Id of the FeedReference to remove from the cache
    */
    public void clearCache(java.lang.Long feedRefId) {
        _feedReferenceLocalService.clearCache(feedRefId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FeedReferenceLocalService getWrappedFeedReferenceLocalService() {
        return _feedReferenceLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFeedReferenceLocalService(
        FeedReferenceLocalService feedReferenceLocalService) {
        _feedReferenceLocalService = feedReferenceLocalService;
    }

    public FeedReferenceLocalService getWrappedService() {
        return _feedReferenceLocalService;
    }

    public void setWrappedService(
        FeedReferenceLocalService feedReferenceLocalService) {
        _feedReferenceLocalService = feedReferenceLocalService;
    }
}
