package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.FlagReportStats;

/**
 * The persistence interface for the flag report stats service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagReportStatsPersistenceImpl
 * @see FlagReportStatsUtil
 * @generated
 */
public interface FlagReportStatsPersistence extends BasePersistence<FlagReportStats> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FlagReportStatsUtil} to access the flag report stats persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the flag report stats in the entity cache if it is enabled.
    *
    * @param flagReportStats the flag report stats
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats);

    /**
    * Caches the flag report statses in the entity cache if it is enabled.
    *
    * @param flagReportStatses the flag report statses
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> flagReportStatses);

    /**
    * Creates a new flag report stats with the primary key. Does not add the flag report stats to the database.
    *
    * @param flagReportStatsId the primary key for the new flag report stats
    * @return the new flag report stats
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats create(
        long flagReportStatsId);

    /**
    * Removes the flag report stats with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats remove(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;

    public org.nterlearning.datamodel.catalog.model.FlagReportStats updateImpl(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the flag report stats with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats findByPrimaryKey(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;

    /**
    * Returns the flag report stats with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats, or <code>null</code> if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByPrimaryKey(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the flag report stats where flagReportStatsId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
    *
    * @param flagReportStatsId the flag report stats ID
    * @return the matching flag report stats
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats findByflagReportStatsId(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;

    /**
    * Returns the flag report stats where flagReportStatsId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param flagReportStatsId the flag report stats ID
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByflagReportStatsId(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the flag report stats where flagReportStatsId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param flagReportStatsId the flag report stats ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByflagReportStatsId(
        long flagReportStatsId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching flag report stats
    * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats findByClassNameIdWithClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;

    /**
    * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByClassNameIdWithClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats fetchByClassNameIdWithClassPK(
        long classNameId, long classPK, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the flag report statses.
    *
    * @return the flag report statses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the flag report stats where flagReportStatsId = &#63; from the database.
    *
    * @param flagReportStatsId the flag report stats ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByflagReportStatsId(long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;

    /**
    * Removes the flag report stats where classNameId = &#63; and classPK = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdWithClassPK(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;

    /**
    * Removes all the flag report statses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of flag report statses where flagReportStatsId = &#63;.
    *
    * @param flagReportStatsId the flag report stats ID
    * @return the number of matching flag report statses
    * @throws SystemException if a system exception occurred
    */
    public int countByflagReportStatsId(long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of flag report statses where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the number of matching flag report statses
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdWithClassPK(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of flag report statses.
    *
    * @return the number of flag report statses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
