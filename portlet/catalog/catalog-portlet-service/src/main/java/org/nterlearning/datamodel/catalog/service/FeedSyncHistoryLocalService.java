package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the feed sync history local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistoryLocalServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.FeedSyncHistoryLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.FeedSyncHistoryLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface FeedSyncHistoryLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FeedSyncHistoryLocalServiceUtil} to access the feed sync history local service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.FeedSyncHistoryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the feed sync history to the database. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @return the feed sync history that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory addFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new feed sync history with the primary key. Does not add the feed sync history to the database.
    *
    * @param syncId the primary key for the new feed sync history
    * @return the new feed sync history
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory createFeedSyncHistory(
        long syncId);

    /**
    * Deletes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param syncId the primary key of the feed sync history
    * @throws PortalException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFeedSyncHistory(long syncId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the feed sync history from the database. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @throws SystemException if a system exception occurred
    */
    public void deleteFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory fetchFeedSyncHistory(
        long syncId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the feed sync history with the primary key.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history
    * @throws PortalException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory getFeedSyncHistory(
        long syncId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> getFeedSyncHistories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed sync histories.
    *
    * @return the number of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getFeedSyncHistoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the feed sync history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param feedSyncHistory the feed sync history
    * @return the feed sync history that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateFeedSyncHistory(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);
}
