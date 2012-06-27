package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.FeedSyncHistory;

/**
 * The persistence interface for the feed sync history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistoryPersistenceImpl
 * @see FeedSyncHistoryUtil
 * @generated
 */
public interface FeedSyncHistoryPersistence extends BasePersistence<FeedSyncHistory> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FeedSyncHistoryUtil} to access the feed sync history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the feed sync history in the entity cache if it is enabled.
    *
    * @param feedSyncHistory the feed sync history
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory);

    /**
    * Caches the feed sync histories in the entity cache if it is enabled.
    *
    * @param feedSyncHistories the feed sync histories
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> feedSyncHistories);

    /**
    * Creates a new feed sync history with the primary key. Does not add the feed sync history to the database.
    *
    * @param syncId the primary key for the new feed sync history
    * @return the new feed sync history
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory create(
        long syncId);

    /**
    * Removes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory remove(
        long syncId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;

    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateImpl(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the feed sync history with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException} if it could not be found.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory findByPrimaryKey(
        long syncId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;

    /**
    * Returns the feed sync history with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history, or <code>null</code> if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory fetchByPrimaryKey(
        long syncId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the feed sync histories where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByfeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed sync histories where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of feed sync histories
    * @param end the upper bound of the range of feed sync histories (not inclusive)
    * @return the range of matching feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByfeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed sync histories where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of feed sync histories
    * @param end the upper bound of the range of feed sync histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByfeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed sync history in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed sync history
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a matching feed sync history could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory findByfeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;

    /**
    * Returns the last feed sync history in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed sync history
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a matching feed sync history could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory findByfeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;

    /**
    * Returns the feed sync histories before and after the current feed sync history in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param syncId the primary key of the current feed sync history
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed sync history
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory[] findByfeedReferenceId_PrevAndNext(
        long syncId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;

    /**
    * Returns all the feed sync histories.
    *
    * @return the feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed sync histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of feed sync histories
    * @param end the upper bound of the range of feed sync histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed sync histories where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByfeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed sync histories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed sync histories where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public int countByfeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed sync histories.
    *
    * @return the number of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
