package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.FlagReportStats;

import java.util.List;

/**
 * The persistence utility for the flag report stats service. This utility wraps {@link FlagReportStatsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagReportStatsPersistence
 * @see FlagReportStatsPersistenceImpl
 * @generated
 */
public class FlagReportStatsUtil {
    private static FlagReportStatsPersistence _persistence;

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
    public static void clearCache(FlagReportStats flagReportStats) {
        getPersistence().clearCache(flagReportStats);
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
    public static List<FlagReportStats> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FlagReportStats> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FlagReportStats> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static FlagReportStats update(FlagReportStats flagReportStats,
        boolean merge) throws SystemException {
        return getPersistence().update(flagReportStats, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static FlagReportStats update(FlagReportStats flagReportStats,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(flagReportStats, merge, serviceContext);
    }

    /**
    * Caches the flag report stats in the entity cache if it is enabled.
    *
    * @param flagReportStats the flag report stats
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats) {
        getPersistence().cacheResult(flagReportStats);
    }

    /**
    * Caches the flag report statses in the entity cache if it is enabled.
    *
    * @param flagReportStatses the flag report statses
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> flagReportStatses) {
        getPersistence().cacheResult(flagReportStatses);
    }

    /**
    * Creates a new flag report stats with the primary key. Does not add the flag report stats to the database.
    *
    * @param flagReportStatsId the primary key for the new flag report stats
    * @return the new flag report stats
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats create(
        long flagReportStatsId) {
        return getPersistence().create(flagReportStatsId);
    }

    /**
    * Removes the flag report stats with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats remove(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException {
        return getPersistence().remove(flagReportStatsId);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReportStats updateImpl(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(flagReportStats, merge);
    }

    /**
    * Returns the flag report stats with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats findByPrimaryKey(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException {
        return getPersistence().findByPrimaryKey(flagReportStatsId);
    }

    /**
    * Returns the flag report stats with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats, or <code>null</code> if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByPrimaryKey(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(flagReportStatsId);
    }

    /**
    * Returns the flag report stats where flagReportStatsId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
    *
    * @param flagReportStatsId the flag report stats ID
    * @return the matching flag report stats
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats findByflagReportStatsId(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException {
        return getPersistence().findByflagReportStatsId(flagReportStatsId);
    }

    /**
    * Returns the flag report stats where flagReportStatsId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param flagReportStatsId the flag report stats ID
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByflagReportStatsId(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByflagReportStatsId(flagReportStatsId);
    }

    /**
    * Returns the flag report stats where flagReportStatsId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param flagReportStatsId the flag report stats ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByflagReportStatsId(
        long flagReportStatsId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByflagReportStatsId(flagReportStatsId,
            retrieveFromCache);
    }

    /**
    * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching flag report stats
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats findByClassNameIdWithClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException {
        return getPersistence()
                   .findByClassNameIdWithClassPK(classNameId, classPK);
    }

    /**
    * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByClassNameIdWithClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByClassNameIdWithClassPK(classNameId, classPK);
    }

    /**
    * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByClassNameIdWithClassPK(
        long classNameId, long classPK, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByClassNameIdWithClassPK(classNameId, classPK,
            retrieveFromCache);
    }

    /**
    * Returns all the flag report statses.
    *
    * @return the flag report statses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the flag report statses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of flag report statses
    * @param end the upper bound of the range of flag report statses (not inclusive)
    * @return the range of flag report statses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the flag report statses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of flag report statses
    * @param end the upper bound of the range of flag report statses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of flag report statses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the flag report stats where flagReportStatsId = &#63; from the database.
    *
    * @param flagReportStatsId the flag report stats ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByflagReportStatsId(long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException {
        getPersistence().removeByflagReportStatsId(flagReportStatsId);
    }

    /**
    * Removes the flag report stats where classNameId = &#63; and classPK = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @throws SystemException if a system exception occurred
    */
    public static void removeByClassNameIdWithClassPK(long classNameId,
        long classPK)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException {
        getPersistence().removeByClassNameIdWithClassPK(classNameId, classPK);
    }

    /**
    * Removes all the flag report statses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of flag report statses where flagReportStatsId = &#63;.
    *
    * @param flagReportStatsId the flag report stats ID
    * @return the number of matching flag report statses
    * @throws SystemException if a system exception occurred
    */
    public static int countByflagReportStatsId(long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByflagReportStatsId(flagReportStatsId);
    }

    /**
    * Returns the number of flag report statses where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the number of matching flag report statses
    * @throws SystemException if a system exception occurred
    */
    public static int countByClassNameIdWithClassPK(long classNameId,
        long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByClassNameIdWithClassPK(classNameId, classPK);
    }

    /**
    * Returns the number of flag report statses.
    *
    * @return the number of flag report statses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FlagReportStatsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FlagReportStatsPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    FlagReportStatsPersistence.class.getName());

            ReferenceRegistry.registerReference(FlagReportStatsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(FlagReportStatsPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(FlagReportStatsUtil.class,
            "_persistence");
    }
}
