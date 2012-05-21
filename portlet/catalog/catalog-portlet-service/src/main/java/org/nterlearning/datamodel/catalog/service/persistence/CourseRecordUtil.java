package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.CourseRecord;

import java.util.List;

/**
 * The persistence utility for the course record service. This utility wraps {@link CourseRecordPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecordPersistence
 * @see CourseRecordPersistenceImpl
 * @generated
 */
public class CourseRecordUtil {
    private static CourseRecordPersistence _persistence;

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
    public static void clearCache(CourseRecord courseRecord) {
        getPersistence().clearCache(courseRecord);
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
    public static List<CourseRecord> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CourseRecord> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CourseRecord> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static CourseRecord update(CourseRecord courseRecord, boolean merge)
        throws SystemException {
        return getPersistence().update(courseRecord, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static CourseRecord update(CourseRecord courseRecord, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(courseRecord, merge, serviceContext);
    }

    /**
    * Caches the course record in the entity cache if it is enabled.
    *
    * @param courseRecord the course record
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord) {
        getPersistence().cacheResult(courseRecord);
    }

    /**
    * Caches the course records in the entity cache if it is enabled.
    *
    * @param courseRecords the course records
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> courseRecords) {
        getPersistence().cacheResult(courseRecords);
    }

    /**
    * Creates a new course record with the primary key. Does not add the course record to the database.
    *
    * @param courseRecordId the primary key for the new course record
    * @return the new course record
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord create(
        long courseRecordId) {
        return getPersistence().create(courseRecordId);
    }

    /**
    * Removes the course record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord remove(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence().remove(courseRecordId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecord updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(courseRecord, merge);
    }

    /**
    * Returns the course record with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRecordException} if it could not be found.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence().findByPrimaryKey(courseRecordId);
    }

    /**
    * Returns the course record with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record, or <code>null</code> if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord fetchByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(courseRecordId);
    }

    /**
    * Returns the course record where courseRecordIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRecordException} if it could not be found.
    *
    * @param courseRecordIri the course record iri
    * @return the matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence().findByCourseRecordIri(courseRecordIri);
    }

    /**
    * Returns the course record where courseRecordIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseRecordIri the course record iri
    * @return the matching course record, or <code>null</code> if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord fetchByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCourseRecordIri(courseRecordIri);
    }

    /**
    * Returns the course record where courseRecordIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseRecordIri the course record iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching course record, or <code>null</code> if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord fetchByCourseRecordIri(
        java.lang.String courseRecordIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCourseRecordIri(courseRecordIri, retrieveFromCache);
    }

    /**
    * Returns all the course records where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIri(courseIri);
    }

    /**
    * Returns a range of all the course records where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIri(courseIri, start, end);
    }

    /**
    * Returns an ordered range of all the course records where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIri(courseIri, start, end, orderByComparator);
    }

    /**
    * Returns the first course record in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseIri_First(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByCourseIri_First(courseIri, orderByComparator);
    }

    /**
    * Returns the last course record in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseIri_Last(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByCourseIri_Last(courseIri, orderByComparator);
    }

    /**
    * Returns the course records before and after the current course record in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the primary key of the current course record
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord[] findByCourseIri_PrevAndNext(
        long courseRecordId, java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByCourseIri_PrevAndNext(courseRecordId, courseIri,
            orderByComparator);
    }

    /**
    * Returns all the course records where singleSignOnValue = &#63;.
    *
    * @param singleSignOnValue the single sign on value
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySingleSignOnValue(singleSignOnValue);
    }

    /**
    * Returns a range of all the course records where singleSignOnValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param singleSignOnValue the single sign on value
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySingleSignOnValue(singleSignOnValue, start, end);
    }

    /**
    * Returns an ordered range of all the course records where singleSignOnValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param singleSignOnValue the single sign on value
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySingleSignOnValue(singleSignOnValue, start, end,
            orderByComparator);
    }

    /**
    * Returns the first course record in the ordered set where singleSignOnValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param singleSignOnValue the single sign on value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findBySingleSignOnValue_First(
        java.lang.String singleSignOnValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findBySingleSignOnValue_First(singleSignOnValue,
            orderByComparator);
    }

    /**
    * Returns the last course record in the ordered set where singleSignOnValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param singleSignOnValue the single sign on value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findBySingleSignOnValue_Last(
        java.lang.String singleSignOnValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findBySingleSignOnValue_Last(singleSignOnValue,
            orderByComparator);
    }

    /**
    * Returns the course records before and after the current course record in the ordered set where singleSignOnValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the primary key of the current course record
    * @param singleSignOnValue the single sign on value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord[] findBySingleSignOnValue_PrevAndNext(
        long courseRecordId, java.lang.String singleSignOnValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findBySingleSignOnValue_PrevAndNext(courseRecordId,
            singleSignOnValue, orderByComparator);
    }

    /**
    * Returns all the course records where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the course records where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the course records where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first course record in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last course record in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the course records before and after the current course record in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the primary key of the current course record
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord[] findByUserId_PrevAndNext(
        long courseRecordId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByUserId_PrevAndNext(courseRecordId, userId,
            orderByComparator);
    }

    /**
    * Returns all the course records where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns a range of all the course records where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFeedReferenceId(feedReferenceId, start, end);
    }

    /**
    * Returns an ordered range of all the course records where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFeedReferenceId(feedReferenceId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first course record in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByFeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByFeedReferenceId_First(feedReferenceId,
            orderByComparator);
    }

    /**
    * Returns the last course record in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByFeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByFeedReferenceId_Last(feedReferenceId,
            orderByComparator);
    }

    /**
    * Returns the course records before and after the current course record in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the primary key of the current course record
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord[] findByFeedReferenceId_PrevAndNext(
        long courseRecordId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getPersistence()
                   .findByFeedReferenceId_PrevAndNext(courseRecordId,
            feedReferenceId, orderByComparator);
    }

    /**
    * Returns all the course records.
    *
    * @return the course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the course records.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the course records.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the course record where courseRecordIri = &#63; from the database.
    *
    * @param courseRecordIri the course record iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseRecordIri(java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        getPersistence().removeByCourseRecordIri(courseRecordIri);
    }

    /**
    * Removes all the course records where courseIri = &#63; from the database.
    *
    * @param courseIri the course iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIri(courseIri);
    }

    /**
    * Removes all the course records where singleSignOnValue = &#63; from the database.
    *
    * @param singleSignOnValue the single sign on value
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySingleSignOnValue(singleSignOnValue);
    }

    /**
    * Removes all the course records where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Removes all the course records where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByFeedReferenceId(feedReferenceId);
    }

    /**
    * Removes all the course records from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of course records where courseRecordIri = &#63;.
    *
    * @param courseRecordIri the course record iri
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseRecordIri(java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseRecordIri(courseRecordIri);
    }

    /**
    * Returns the number of course records where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIri(courseIri);
    }

    /**
    * Returns the number of course records where singleSignOnValue = &#63;.
    *
    * @param singleSignOnValue the single sign on value
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static int countBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySingleSignOnValue(singleSignOnValue);
    }

    /**
    * Returns the number of course records where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Returns the number of course records where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public static int countByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the number of course records.
    *
    * @return the number of course records
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the component records associated with the course record.
    *
    * @param pk the primary key of the course record
    * @return the component records associated with the course record
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentRecords(pk);
    }

    /**
    * Returns a range of all the component records associated with the course record.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course record
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of component records associated with the course record
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentRecords(pk, start, end);
    }

    /**
    * Returns an ordered range of all the component records associated with the course record.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course record
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of component records associated with the course record
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getComponentRecords(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of component records associated with the course record.
    *
    * @param pk the primary key of the course record
    * @return the number of component records associated with the course record
    * @throws SystemException if a system exception occurred
    */
    public static int getComponentRecordsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentRecordsSize(pk);
    }

    /**
    * Returns <code>true</code> if the component record is associated with the course record.
    *
    * @param pk the primary key of the course record
    * @param componentRecordPK the primary key of the component record
    * @return <code>true</code> if the component record is associated with the course record; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsComponentRecord(long pk,
        long componentRecordPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsComponentRecord(pk, componentRecordPK);
    }

    /**
    * Returns <code>true</code> if the course record has any component records associated with it.
    *
    * @param pk the primary key of the course record to check for associations with component records
    * @return <code>true</code> if the course record has any component records associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsComponentRecords(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsComponentRecords(pk);
    }

    public static CourseRecordPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CourseRecordPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseRecordPersistence.class.getName());

            ReferenceRegistry.registerReference(CourseRecordUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(CourseRecordPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(CourseRecordUtil.class,
            "_persistence");
    }
}
