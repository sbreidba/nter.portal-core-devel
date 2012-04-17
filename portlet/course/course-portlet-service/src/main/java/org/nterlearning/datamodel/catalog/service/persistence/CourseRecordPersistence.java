package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.CourseRecord;

/**
 * The persistence interface for the course record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecordPersistenceImpl
 * @see CourseRecordUtil
 * @generated
 */
public interface CourseRecordPersistence extends BasePersistence<CourseRecord> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseRecordUtil} to access the course record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the course record in the entity cache if it is enabled.
    *
    * @param courseRecord the course record
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord);

    /**
    * Caches the course records in the entity cache if it is enabled.
    *
    * @param courseRecords the course records
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> courseRecords);

    /**
    * Creates a new course record with the primary key. Does not add the course record to the database.
    *
    * @param courseRecordId the primary key for the new course record
    * @return the new course record
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord create(
        long courseRecordId);

    /**
    * Removes the course record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord remove(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    public org.nterlearning.datamodel.catalog.model.CourseRecord updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course record with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRecordException} if it could not be found.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Returns the course record with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record, or <code>null</code> if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord fetchByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course record where courseRecordIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRecordException} if it could not be found.
    *
    * @param courseRecordIri the course record iri
    * @return the matching course record
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Returns the course record where courseRecordIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseRecordIri the course record iri
    * @return the matching course record, or <code>null</code> if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord fetchByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course record where courseRecordIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseRecordIri the course record iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching course record, or <code>null</code> if a matching course record could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord fetchByCourseRecordIri(
        java.lang.String courseRecordIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course records where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseIri_First(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseIri_Last(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord[] findByCourseIri_PrevAndNext(
        long courseRecordId, java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Returns all the course records where singleSignOnValue = &#63;.
    *
    * @param singleSignOnValue the single sign on value
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findBySingleSignOnValue_First(
        java.lang.String singleSignOnValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findBySingleSignOnValue_Last(
        java.lang.String singleSignOnValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord[] findBySingleSignOnValue_PrevAndNext(
        long courseRecordId, java.lang.String singleSignOnValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Returns all the course records where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord[] findByUserId_PrevAndNext(
        long courseRecordId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Returns all the course records where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching course records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByFeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord findByFeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRecord[] findByFeedReferenceId_PrevAndNext(
        long courseRecordId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Returns all the course records.
    *
    * @return the course records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the course record where courseRecordIri = &#63; from the database.
    *
    * @param courseRecordIri the course record iri
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseRecordIri(java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;

    /**
    * Removes all the course records where courseIri = &#63; from the database.
    *
    * @param courseIri the course iri
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course records where singleSignOnValue = &#63; from the database.
    *
    * @param singleSignOnValue the single sign on value
    * @throws SystemException if a system exception occurred
    */
    public void removeBySingleSignOnValue(java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course records where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course records where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course records from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course records where courseRecordIri = &#63;.
    *
    * @param courseRecordIri the course record iri
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseRecordIri(java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course records where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course records where singleSignOnValue = &#63;.
    *
    * @param singleSignOnValue the single sign on value
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public int countBySingleSignOnValue(java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course records where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course records where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching course records
    * @throws SystemException if a system exception occurred
    */
    public int countByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course records.
    *
    * @return the number of course records
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the component records associated with the course record.
    *
    * @param pk the primary key of the course record
    * @return the component records associated with the course record
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of component records associated with the course record.
    *
    * @param pk the primary key of the course record
    * @return the number of component records associated with the course record
    * @throws SystemException if a system exception occurred
    */
    public int getComponentRecordsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the component record is associated with the course record.
    *
    * @param pk the primary key of the course record
    * @param componentRecordPK the primary key of the component record
    * @return <code>true</code> if the component record is associated with the course record; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsComponentRecord(long pk, long componentRecordPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course record has any component records associated with it.
    *
    * @param pk the primary key of the course record to check for associations with component records
    * @return <code>true</code> if the course record has any component records associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsComponentRecords(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;
}
