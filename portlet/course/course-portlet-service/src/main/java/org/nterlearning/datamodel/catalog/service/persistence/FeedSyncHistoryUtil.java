package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.FeedSyncHistory;

import java.util.List;

/**
 * The persistence utility for the feed sync history service. This utility wraps {@link FeedSyncHistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistoryPersistence
 * @see FeedSyncHistoryPersistenceImpl
 * @generated
 */
public class FeedSyncHistoryUtil {
    private static FeedSyncHistoryPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(FeedSyncHistory feedSyncHistory) {
        getPersistence().clearCache(feedSyncHistory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<FeedSyncHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FeedSyncHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FeedSyncHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static FeedSyncHistory update(FeedSyncHistory feedSyncHistory,
        boolean merge) throws SystemException {
        return getPersistence().update(feedSyncHistory, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static FeedSyncHistory update(FeedSyncHistory feedSyncHistory,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(feedSyncHistory, merge, serviceContext);
    }

    /**
    * Caches the feed sync history in the entity cache if it is enabled.
    *
    * @param feedSyncHistory the feed sync history
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory) {
        getPersistence().cacheResult(feedSyncHistory);
    }

    /**
    * Caches the feed sync histories in the entity cache if it is enabled.
    *
    * @param feedSyncHistories the feed sync histories
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> feedSyncHistories) {
        getPersistence().cacheResult(feedSyncHistories);
    }

    /**
    * Creates a new feed sync history with the primary key. Does not add the feed sync history to the database.
    *
    * @param syncId the primary key for the new feed sync history
    * @return the new feed sync history
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory create(
        long syncId) {
        return getPersistence().create(syncId);
    }

    /**
    * Removes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory remove(
        long syncId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException {
        return getPersistence().remove(syncId);
    }

    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory updateImpl(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(feedSyncHistory, merge);
    }

    /**
    * Returns the feed sync history with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException} if it could not be found.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory findByPrimaryKey(
        long syncId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException {
        return getPersistence().findByPrimaryKey(syncId);
    }

    /**
    * Returns the feed sync history with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param syncId the primary key of the feed sync history
    * @return the feed sync history, or <code>null</code> if a feed sync history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory fetchByPrimaryKey(
        long syncId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(syncId);
    }

    /**
    * Returns all the feed sync histories where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByfeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfeedReferenceId(feedReferenceId);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByfeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfeedReferenceId(feedReferenceId, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findByfeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfeedReferenceId(feedReferenceId, start, end,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory findByfeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException {
        return getPersistence()
                   .findByfeedReferenceId_First(feedReferenceId,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory findByfeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException {
        return getPersistence()
                   .findByfeedReferenceId_Last(feedReferenceId,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.FeedSyncHistory[] findByfeedReferenceId_PrevAndNext(
        long syncId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException {
        return getPersistence()
                   .findByfeedReferenceId_PrevAndNext(syncId, feedReferenceId,
            orderByComparator);
    }

    /**
    * Returns all the feed sync histories.
    *
    * @return the feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the feed sync histories where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByfeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByfeedReferenceId(feedReferenceId);
    }

    /**
    * Removes all the feed sync histories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of feed sync histories where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByfeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the number of feed sync histories.
    *
    * @return the number of feed sync histories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FeedSyncHistoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FeedSyncHistoryPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    FeedSyncHistoryPersistence.class.getName());

            ReferenceRegistry.registerReference(FeedSyncHistoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(FeedSyncHistoryPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(FeedSyncHistoryUtil.class,
            "_persistence");
    }
}
