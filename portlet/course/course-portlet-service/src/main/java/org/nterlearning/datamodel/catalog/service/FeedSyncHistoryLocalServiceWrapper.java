package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedSyncHistoryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedSyncHistoryLocalService
 * @generated
 */
public class FeedSyncHistoryLocalServiceWrapper
    implements FeedSyncHistoryLocalService,
        ServiceWrapper<FeedSyncHistoryLocalService> {
    private FeedSyncHistoryLocalService _feedSyncHistoryLocalService;

    public FeedSyncHistoryLocalServiceWrapper(
        FeedSyncHistoryLocalService feedSyncHistoryLocalService) {
        _feedSyncHistoryLocalService = feedSyncHistoryLocalService;
    }

    /**
    * Adds the feed sync history to the database. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @return the feed sync history that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory addFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.addFeedSyncHistory(feedSyncHistory);
    }

    /**
    * Creates a new feed sync history with the primary key. Does not add the feed sync history to the database.
    *
    * @param syncId the primary key for the new feed sync history
    * @return the new feed sync history
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory createFeedSyncHistory(
        long syncId) {
        return _feedSyncHistoryLocalService.createFeedSyncHistory(syncId);
    }

    /**
    * Deletes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param syncId the primary key of the feed sync history
    * @throws PortalException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFeedSyncHistory(long syncId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _feedSyncHistoryLocalService.deleteFeedSyncHistory(syncId);
    }

    /**
    * Deletes the feed sync history from the database. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @throws SystemException if a system exception occurred
    */
    public void deleteFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        _feedSyncHistoryLocalService.deleteFeedSyncHistory(feedSyncHistory);
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
        return _feedSyncHistoryLocalService.dynamicQuery(dynamicQuery);
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
        return _feedSyncHistoryLocalService.dynamicQuery(dynamicQuery, start,
            end);
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
        return _feedSyncHistoryLocalService.dynamicQuery(dynamicQuery, start,
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
        return _feedSyncHistoryLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory fetchFeedSyncHistory(
        long syncId) throws com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.fetchFeedSyncHistory(syncId);
    }

    /**
    * Returns the feed sync history with the primary key.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history
    * @throws PortalException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory getFeedSyncHistory(
        long syncId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.getFeedSyncHistory(syncId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.getPersistedModel(primaryKeyObj);
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
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> getFeedSyncHistories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.getFeedSyncHistories(start, end);
    }

    /**
    * Returns the number of feed sync histories.
    *
    * @return the number of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public int getFeedSyncHistoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.getFeedSyncHistoriesCount();
    }

    /**
    * Updates the feed sync history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @return the feed sync history that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.updateFeedSyncHistory(feedSyncHistory);
    }

    /**
    * Updates the feed sync history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @param merge whether to merge the feed sync history with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the feed sync history that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _feedSyncHistoryLocalService.updateFeedSyncHistory(feedSyncHistory,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _feedSyncHistoryLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _feedSyncHistoryLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FeedSyncHistoryLocalService getWrappedFeedSyncHistoryLocalService() {
        return _feedSyncHistoryLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFeedSyncHistoryLocalService(
        FeedSyncHistoryLocalService feedSyncHistoryLocalService) {
        _feedSyncHistoryLocalService = feedSyncHistoryLocalService;
    }

    public FeedSyncHistoryLocalService getWrappedService() {
        return _feedSyncHistoryLocalService;
    }

    public void setWrappedService(
        FeedSyncHistoryLocalService feedSyncHistoryLocalService) {
        _feedSyncHistoryLocalService = feedSyncHistoryLocalService;
    }
}
