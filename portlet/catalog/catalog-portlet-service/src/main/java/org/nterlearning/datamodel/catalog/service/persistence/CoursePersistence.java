package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.Course;

/**
 * The persistence interface for the course service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CoursePersistenceImpl
 * @see CourseUtil
 * @generated
 */
public interface CoursePersistence extends BasePersistence<Course> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseUtil} to access the course persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the course in the entity cache if it is enabled.
    *
    * @param course the course
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.Course course);

    /**
    * Caches the courses in the entity cache if it is enabled.
    *
    * @param courses the courses
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.Course> courses);

    /**
    * Creates a new course with the primary key. Does not add the course to the database.
    *
    * @param courseId the primary key for the new course
    * @return the new course
    */
    public org.nterlearning.datamodel.catalog.model.Course create(long courseId);

    /**
    * Removes the course with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseId the primary key of the course
    * @return the course that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course remove(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    public org.nterlearning.datamodel.catalog.model.Course updateImpl(
        org.nterlearning.datamodel.catalog.model.Course course, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
    *
    * @param courseId the primary key of the course
    * @return the course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByPrimaryKey(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the course with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseId the primary key of the course
    * @return the course, or <code>null</code> if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course fetchByPrimaryKey(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course where courseId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
    *
    * @param courseId the course ID
    * @return the matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the course where courseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseId the course ID
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course where courseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseId the course ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the courses where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the courses where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first course in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the last course in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the courses before and after the current course in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the primary key of the current course
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course[] findByGroupId_PrevAndNext(
        long courseId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns all the courses where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the courses where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first course in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByCompanyId_First(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the last course in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByCompanyId_Last(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the courses before and after the current course in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the primary key of the current course
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course[] findByCompanyId_PrevAndNext(
        long courseId, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the course where courseIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
    *
    * @param courseIri the course iri
    * @return the matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the course where courseIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseIri the course iri
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course where courseIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseIri the course iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the courses where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the courses where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first course in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByFeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the last course in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findByFeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the courses before and after the current course in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the primary key of the current course
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course[] findByFeedReferenceId_PrevAndNext(
        long courseId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns all the courses where supersededByCourseIri = &#63;.
    *
    * @param supersededByCourseIri the superseded by course iri
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String supersededByCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses where supersededByCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param supersededByCourseIri the superseded by course iri
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String supersededByCourseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the courses where supersededByCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param supersededByCourseIri the superseded by course iri
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String supersededByCourseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first course in the ordered set where supersededByCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param supersededByCourseIri the superseded by course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findBySupersededByCourseIri_First(
        java.lang.String supersededByCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the last course in the ordered set where supersededByCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param supersededByCourseIri the superseded by course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course findBySupersededByCourseIri_Last(
        java.lang.String supersededByCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns the courses before and after the current course in the ordered set where supersededByCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the primary key of the current course
    * @param supersededByCourseIri the superseded by course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course[] findBySupersededByCourseIri_PrevAndNext(
        long courseId, java.lang.String supersededByCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Returns all the courses.
    *
    * @return the courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the courses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of courses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the course where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Removes all the courses where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the courses where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the course where courseIri = &#63; from the database.
    *
    * @param courseIri the course iri
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    /**
    * Removes all the courses where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the courses where supersededByCourseIri = &#63; from the database.
    *
    * @param supersededByCourseIri the superseded by course iri
    * @throws SystemException if a system exception occurred
    */
    public void removeBySupersededByCourseIri(
        java.lang.String supersededByCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the courses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public int countByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses where supersededByCourseIri = &#63;.
    *
    * @param supersededByCourseIri the superseded by course iri
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public int countBySupersededByCourseIri(
        java.lang.String supersededByCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses.
    *
    * @return the number of courses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contributors associated with the course.
    *
    * @param pk the primary key of the course
    * @return the contributors associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contributors associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of contributors associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contributors associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contributors associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contributors associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of contributors associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getContributorsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the contributor is associated with the course.
    *
    * @param pk the primary key of the course
    * @param contributorPK the primary key of the contributor
    * @return <code>true</code> if the contributor is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsContributor(long pk, long contributorPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any contributors associated with it.
    *
    * @param pk the primary key of the course to check for associations with contributors
    * @return <code>true</code> if the course has any contributors associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsContributors(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course images associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course images associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the course images associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of course images associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the course images associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course images associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course images associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course images associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getCourseImagesSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course image is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseImagePK the primary key of the course image
    * @return <code>true</code> if the course image is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseImage(long pk, long courseImagePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any course images associated with it.
    *
    * @param pk the primary key of the course to check for associations with course images
    * @return <code>true</code> if the course has any course images associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseImages(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course relateds associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course relateds associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the course relateds associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of course relateds associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the course relateds associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course relateds associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course relateds associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course relateds associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getCourseRelatedsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course related is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseRelatedPK the primary key of the course related
    * @return <code>true</code> if the course related is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseRelated(long pk, long courseRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any course relateds associated with it.
    *
    * @param pk the primary key of the course to check for associations with course relateds
    * @return <code>true</code> if the course has any course relateds associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseRelateds(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course requirements associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course requirements associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the course requirements associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of course requirements associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the course requirements associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course requirements associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course requirements associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course requirements associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getCourseRequirementsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course requirement is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseRequirementPK the primary key of the course requirement
    * @return <code>true</code> if the course requirement is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseRequirement(long pk, long courseRequirementPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any course requirements associated with it.
    *
    * @param pk the primary key of the course to check for associations with course requirements
    * @return <code>true</code> if the course has any course requirements associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseRequirements(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the course reviews associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the course reviews associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getCourseReviewsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course review is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseReviewPK the primary key of the course review
    * @return <code>true</code> if the course review is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseReview(long pk, long courseReviewPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any course reviews associated with it.
    *
    * @param pk the primary key of the course to check for associations with course reviews
    * @return <code>true</code> if the course has any course reviews associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourseReviews(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the courses_ componentses associated with the course.
    *
    * @param pk the primary key of the course
    * @return the courses_ componentses associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses_ componentses associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of courses_ componentses associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the courses_ componentses associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of courses_ componentses associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses_ componentses associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of courses_ componentses associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getCourses_ComponentsesSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the courses_ components is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courses_ComponentsPK the primary key of the courses_ components
    * @return <code>true</code> if the courses_ components is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourses_Components(long pk, long courses_ComponentsPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any courses_ componentses associated with it.
    *
    * @param pk the primary key of the course to check for associations with courses_ componentses
    * @return <code>true</code> if the course has any courses_ componentses associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsCourses_Componentses(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the global course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the global course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the global course reviews associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of global course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the global course reviews associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of global course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of global course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of global course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getGlobalCourseReviewsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the global course review is associated with the course.
    *
    * @param pk the primary key of the course
    * @param globalCourseReviewPK the primary key of the global course review
    * @return <code>true</code> if the global course review is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsGlobalCourseReview(long pk, long globalCourseReviewPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any global course reviews associated with it.
    *
    * @param pk the primary key of the course to check for associations with global course reviews
    * @return <code>true</code> if the course has any global course reviews associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsGlobalCourseReviews(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the external links associated with the course.
    *
    * @param pk the primary key of the course
    * @return the external links associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the external links associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of external links associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the external links associated with the course.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the course
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of external links associated with the course
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of external links associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of external links associated with the course
    * @throws SystemException if a system exception occurred
    */
    public int getExternalLinksSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the external link is associated with the course.
    *
    * @param pk the primary key of the course
    * @param externalLinkPK the primary key of the external link
    * @return <code>true</code> if the external link is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsExternalLink(long pk, long externalLinkPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the course has any external links associated with it.
    *
    * @param pk the primary key of the course to check for associations with external links
    * @return <code>true</code> if the course has any external links associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsExternalLinks(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;
}
