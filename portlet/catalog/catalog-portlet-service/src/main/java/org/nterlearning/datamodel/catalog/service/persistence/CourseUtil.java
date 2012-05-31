package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.Course;

import java.util.List;

/**
 * The persistence utility for the course service. This utility wraps {@link CoursePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CoursePersistence
 * @see CoursePersistenceImpl
 * @generated
 */
public class CourseUtil {
    private static CoursePersistence _persistence;

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
    public static void clearCache(Course course) {
        getPersistence().clearCache(course);
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
    public static List<Course> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Course> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Course> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static Course update(Course course, boolean merge)
        throws SystemException {
        return getPersistence().update(course, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static Course update(Course course, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(course, merge, serviceContext);
    }

    /**
    * Caches the course in the entity cache if it is enabled.
    *
    * @param course the course
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.Course course) {
        getPersistence().cacheResult(course);
    }

    /**
    * Caches the courses in the entity cache if it is enabled.
    *
    * @param courses the courses
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.Course> courses) {
        getPersistence().cacheResult(courses);
    }

    /**
    * Creates a new course with the primary key. Does not add the course to the database.
    *
    * @param courseId the primary key for the new course
    * @return the new course
    */
    public static org.nterlearning.datamodel.catalog.model.Course create(
        long courseId) {
        return getPersistence().create(courseId);
    }

    /**
    * Removes the course with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseId the primary key of the course
    * @return the course that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course remove(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence().remove(courseId);
    }

    public static org.nterlearning.datamodel.catalog.model.Course updateImpl(
        org.nterlearning.datamodel.catalog.model.Course course, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(course, merge);
    }

    /**
    * Returns the course with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
    *
    * @param courseId the primary key of the course
    * @return the course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course findByPrimaryKey(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence().findByPrimaryKey(courseId);
    }

    /**
    * Returns the course with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseId the primary key of the course
    * @return the course, or <code>null</code> if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course fetchByPrimaryKey(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(courseId);
    }

    /**
    * Returns the course where courseId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
    *
    * @param courseId the course ID
    * @return the matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence().findByCourseId(courseId);
    }

    /**
    * Returns the course where courseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseId the course ID
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCourseId(courseId);
    }

    /**
    * Returns the course where courseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseId the course ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCourseId(courseId, retrieveFromCache);
    }

    /**
    * Returns all the courses where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGroupId(groupId, start, end, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence().findByGroupId_First(groupId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence().findByGroupId_Last(groupId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course[] findByGroupId_PrevAndNext(
        long courseId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByGroupId_PrevAndNext(courseId, groupId,
            orderByComparator);
    }

    /**
    * Returns all the courses where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findByCompanyId_First(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findByCompanyId_Last(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course[] findByCompanyId_PrevAndNext(
        long courseId, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(courseId, companyId,
            orderByComparator);
    }

    /**
    * Returns the course where courseIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
    *
    * @param courseIri the course iri
    * @return the matching course
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence().findByCourseIri(courseIri);
    }

    /**
    * Returns the course where courseIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseIri the course iri
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCourseIri(courseIri);
    }

    /**
    * Returns the course where courseIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseIri the course iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching course, or <code>null</code> if a matching course could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCourseIri(courseIri, retrieveFromCache);
    }

    /**
    * Returns all the courses where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFeedReferenceId(feedReferenceId);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFeedReferenceId(feedReferenceId, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFeedReferenceId(feedReferenceId, start, end,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findByFeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByFeedReferenceId_First(feedReferenceId,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findByFeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByFeedReferenceId_Last(feedReferenceId,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course[] findByFeedReferenceId_PrevAndNext(
        long courseId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findByFeedReferenceId_PrevAndNext(courseId,
            feedReferenceId, orderByComparator);
    }

    /**
    * Returns all the courses where supersededByCourseIri = &#63;.
    *
    * @param supersededByCourseIri the superseded by course iri
    * @return the matching courses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String supersededByCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySupersededByCourseIri(supersededByCourseIri);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String supersededByCourseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySupersededByCourseIri(supersededByCourseIri, start,
            end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String supersededByCourseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySupersededByCourseIri(supersededByCourseIri, start,
            end, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findBySupersededByCourseIri_First(
        java.lang.String supersededByCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findBySupersededByCourseIri_First(supersededByCourseIri,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course findBySupersededByCourseIri_Last(
        java.lang.String supersededByCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findBySupersededByCourseIri_Last(supersededByCourseIri,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.Course[] findBySupersededByCourseIri_PrevAndNext(
        long courseId, java.lang.String supersededByCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getPersistence()
                   .findBySupersededByCourseIri_PrevAndNext(courseId,
            supersededByCourseIri, orderByComparator);
    }

    /**
    * Returns all the courses.
    *
    * @return the courses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the course where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        getPersistence().removeByCourseId(courseId);
    }

    /**
    * Removes all the courses where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByGroupId(groupId);
    }

    /**
    * Removes all the courses where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Removes the course where courseIri = &#63; from the database.
    *
    * @param courseIri the course iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        getPersistence().removeByCourseIri(courseIri);
    }

    /**
    * Removes all the courses where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByFeedReferenceId(feedReferenceId);
    }

    /**
    * Removes all the courses where supersededByCourseIri = &#63; from the database.
    *
    * @param supersededByCourseIri the superseded by course iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySupersededByCourseIri(
        java.lang.String supersededByCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySupersededByCourseIri(supersededByCourseIri);
    }

    /**
    * Removes all the courses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of courses where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseId(courseId);
    }

    /**
    * Returns the number of courses where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public static int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByGroupId(groupId);
    }

    /**
    * Returns the number of courses where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns the number of courses where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIri(courseIri);
    }

    /**
    * Returns the number of courses where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public static int countByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the number of courses where supersededByCourseIri = &#63;.
    *
    * @param supersededByCourseIri the superseded by course iri
    * @return the number of matching courses
    * @throws SystemException if a system exception occurred
    */
    public static int countBySupersededByCourseIri(
        java.lang.String supersededByCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countBySupersededByCourseIri(supersededByCourseIri);
    }

    /**
    * Returns the number of courses.
    *
    * @return the number of courses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the contributors associated with the course.
    *
    * @param pk the primary key of the course
    * @return the contributors associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getContributors(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getContributors(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getContributors(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of contributors associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of contributors associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getContributorsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getContributorsSize(pk);
    }

    /**
    * Returns <code>true</code> if the contributor is associated with the course.
    *
    * @param pk the primary key of the course
    * @param contributorPK the primary key of the contributor
    * @return <code>true</code> if the contributor is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsContributor(long pk, long contributorPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsContributor(pk, contributorPK);
    }

    /**
    * Returns <code>true</code> if the course has any contributors associated with it.
    *
    * @param pk the primary key of the course to check for associations with contributors
    * @return <code>true</code> if the course has any contributors associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsContributors(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsContributors(pk);
    }

    /**
    * Returns all the course images associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course images associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseImages(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseImages(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getCourseImages(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of course images associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course images associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseImagesSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseImagesSize(pk);
    }

    /**
    * Returns <code>true</code> if the course image is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseImagePK the primary key of the course image
    * @return <code>true</code> if the course image is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseImage(long pk, long courseImagePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseImage(pk, courseImagePK);
    }

    /**
    * Returns <code>true</code> if the course has any course images associated with it.
    *
    * @param pk the primary key of the course to check for associations with course images
    * @return <code>true</code> if the course has any course images associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseImages(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseImages(pk);
    }

    /**
    * Returns all the course relateds associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course relateds associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseRelateds(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseRelateds(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getCourseRelateds(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of course relateds associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course relateds associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseRelatedsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseRelatedsSize(pk);
    }

    /**
    * Returns <code>true</code> if the course related is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseRelatedPK the primary key of the course related
    * @return <code>true</code> if the course related is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseRelated(long pk, long courseRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseRelated(pk, courseRelatedPK);
    }

    /**
    * Returns <code>true</code> if the course has any course relateds associated with it.
    *
    * @param pk the primary key of the course to check for associations with course relateds
    * @return <code>true</code> if the course has any course relateds associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseRelateds(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseRelateds(pk);
    }

    /**
    * Returns all the course requirements associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course requirements associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseRequirements(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseRequirements(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getCourseRequirements(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of course requirements associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course requirements associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseRequirementsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseRequirementsSize(pk);
    }

    /**
    * Returns <code>true</code> if the course requirement is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseRequirementPK the primary key of the course requirement
    * @return <code>true</code> if the course requirement is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseRequirement(long pk,
        long courseRequirementPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .containsCourseRequirement(pk, courseRequirementPK);
    }

    /**
    * Returns <code>true</code> if the course has any course requirements associated with it.
    *
    * @param pk the primary key of the course to check for associations with course requirements
    * @return <code>true</code> if the course has any course requirements associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseRequirements(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseRequirements(pk);
    }

    /**
    * Returns all the course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseReviews(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseReviews(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getCourseReviews(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseReviewsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourseReviewsSize(pk);
    }

    /**
    * Returns <code>true</code> if the course review is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courseReviewPK the primary key of the course review
    * @return <code>true</code> if the course review is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseReview(long pk, long courseReviewPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseReview(pk, courseReviewPK);
    }

    /**
    * Returns <code>true</code> if the course has any course reviews associated with it.
    *
    * @param pk the primary key of the course to check for associations with course reviews
    * @return <code>true</code> if the course has any course reviews associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourseReviews(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourseReviews(pk);
    }

    /**
    * Returns all the courses_ componentses associated with the course.
    *
    * @param pk the primary key of the course
    * @return the courses_ componentses associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourses_Componentses(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourses_Componentses(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getCourses_Componentses(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of courses_ componentses associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of courses_ componentses associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getCourses_ComponentsesSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourses_ComponentsesSize(pk);
    }

    /**
    * Returns <code>true</code> if the courses_ components is associated with the course.
    *
    * @param pk the primary key of the course
    * @param courses_ComponentsPK the primary key of the courses_ components
    * @return <code>true</code> if the courses_ components is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourses_Components(long pk,
        long courses_ComponentsPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .containsCourses_Components(pk, courses_ComponentsPK);
    }

    /**
    * Returns <code>true</code> if the course has any courses_ componentses associated with it.
    *
    * @param pk the primary key of the course to check for associations with courses_ componentses
    * @return <code>true</code> if the course has any courses_ componentses associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourses_Componentses(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourses_Componentses(pk);
    }

    /**
    * Returns all the global course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the global course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getGlobalCourseReviews(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getGlobalCourseReviews(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getGlobalCourseReviews(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of global course reviews associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of global course reviews associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getGlobalCourseReviewsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getGlobalCourseReviewsSize(pk);
    }

    /**
    * Returns <code>true</code> if the global course review is associated with the course.
    *
    * @param pk the primary key of the course
    * @param globalCourseReviewPK the primary key of the global course review
    * @return <code>true</code> if the global course review is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsGlobalCourseReview(long pk,
        long globalCourseReviewPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .containsGlobalCourseReview(pk, globalCourseReviewPK);
    }

    /**
    * Returns <code>true</code> if the course has any global course reviews associated with it.
    *
    * @param pk the primary key of the course to check for associations with global course reviews
    * @return <code>true</code> if the course has any global course reviews associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsGlobalCourseReviews(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsGlobalCourseReviews(pk);
    }

    /**
    * Returns all the external links associated with the course.
    *
    * @param pk the primary key of the course
    * @return the external links associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getExternalLinks(pk);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getExternalLinks(pk, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getExternalLinks(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of external links associated with the course.
    *
    * @param pk the primary key of the course
    * @return the number of external links associated with the course
    * @throws SystemException if a system exception occurred
    */
    public static int getExternalLinksSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getExternalLinksSize(pk);
    }

    /**
    * Returns <code>true</code> if the external link is associated with the course.
    *
    * @param pk the primary key of the course
    * @param externalLinkPK the primary key of the external link
    * @return <code>true</code> if the external link is associated with the course; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsExternalLink(long pk, long externalLinkPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsExternalLink(pk, externalLinkPK);
    }

    /**
    * Returns <code>true</code> if the course has any external links associated with it.
    *
    * @param pk the primary key of the course to check for associations with external links
    * @return <code>true</code> if the course has any external links associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsExternalLinks(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsExternalLinks(pk);
    }

    public static CoursePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CoursePersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CoursePersistence.class.getName());

            ReferenceRegistry.registerReference(CourseUtil.class, "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(CoursePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(CourseUtil.class, "_persistence");
    }
}
