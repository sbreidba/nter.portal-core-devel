package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.CourseReview;

import java.util.List;

/**
 * The persistence utility for the course review service. This utility wraps {@link CourseReviewPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseReviewPersistence
 * @see CourseReviewPersistenceImpl
 * @generated
 */
public class CourseReviewUtil {
    private static CourseReviewPersistence _persistence;

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
    public static void clearCache(CourseReview courseReview) {
        getPersistence().clearCache(courseReview);
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
    public static List<CourseReview> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CourseReview> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CourseReview> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static CourseReview update(CourseReview courseReview, boolean merge)
        throws SystemException {
        return getPersistence().update(courseReview, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static CourseReview update(CourseReview courseReview, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(courseReview, merge, serviceContext);
    }

    /**
    * Caches the course review in the entity cache if it is enabled.
    *
    * @param courseReview the course review
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview) {
        getPersistence().cacheResult(courseReview);
    }

    /**
    * Caches the course reviews in the entity cache if it is enabled.
    *
    * @param courseReviews the course reviews
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> courseReviews) {
        getPersistence().cacheResult(courseReviews);
    }

    /**
    * Creates a new course review with the primary key. Does not add the course review to the database.
    *
    * @param courseReviewId the primary key for the new course review
    * @return the new course review
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview create(
        long courseReviewId) {
        return getPersistence().create(courseReviewId);
    }

    /**
    * Removes the course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseReviewId the primary key of the course review
    * @return the course review that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview remove(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().remove(courseReviewId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReview updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(courseReview, merge);
    }

    /**
    * Returns the course review with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseReviewException} if it could not be found.
    *
    * @param courseReviewId the primary key of the course review
    * @return the course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByPrimaryKey(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByPrimaryKey(courseReviewId);
    }

    /**
    * Returns the course review with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseReviewId the primary key of the course review
    * @return the course review, or <code>null</code> if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview fetchByPrimaryKey(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(courseReviewId);
    }

    /**
    * Returns all the course reviews where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId);
    }

    /**
    * Returns a range of all the course reviews where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseId(courseId, start, end, orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByCourseId_First(courseId, orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByCourseId_Last(courseId, orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByCourseId_PrevAndNext(
        long courseReviewId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseId_PrevAndNext(courseReviewId, courseId,
            orderByComparator);
    }

    /**
    * Returns all the course reviews where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the course reviews where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByUserId_PrevAndNext(
        long courseReviewId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByUserId_PrevAndNext(courseReviewId, userId,
            orderByComparator);
    }

    /**
    * Returns all the course reviews where courseId = &#63; and userId = &#63;.
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long courseId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIdWithUserId(courseId, userId);
    }

    /**
    * Returns a range of all the course reviews where courseId = &#63; and userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long courseId, long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithUserId(courseId, userId, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where courseId = &#63; and userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long courseId, long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithUserId(courseId, userId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where courseId = &#63; and userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCourseIdWithUserId_First(
        long courseId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseIdWithUserId_First(courseId, userId,
            orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where courseId = &#63; and userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCourseIdWithUserId_Last(
        long courseId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseIdWithUserId_Last(courseId, userId,
            orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where courseId = &#63; and userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param courseId the course ID
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByCourseIdWithUserId_PrevAndNext(
        long courseReviewId, long courseId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseIdWithUserId_PrevAndNext(courseReviewId,
            courseId, userId, orderByComparator);
    }

    /**
    * Returns all the course reviews where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId);
    }

    /**
    * Returns a range of all the course reviews where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGroupId(groupId, start, end, orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByGroupId_First(groupId, orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByGroupId_Last(groupId, orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByGroupId_PrevAndNext(
        long courseReviewId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByGroupId_PrevAndNext(courseReviewId, groupId,
            orderByComparator);
    }

    /**
    * Returns all the course reviews where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

    /**
    * Returns a range of all the course reviews where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCompanyId(
        long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCompanyId_First(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCompanyId_Last(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByCompanyId_PrevAndNext(
        long courseReviewId, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(courseReviewId, companyId,
            orderByComparator);
    }

    /**
    * Returns all the course reviews where removed = &#63;.
    *
    * @param removed the removed
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByRemoved(
        boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByRemoved(removed);
    }

    /**
    * Returns a range of all the course reviews where removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param removed the removed
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByRemoved(
        boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByRemoved(removed, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param removed the removed
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByRemoved(
        boolean removed, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRemoved(removed, start, end, orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByRemoved_First(
        boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByRemoved_First(removed, orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByRemoved_Last(
        boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence().findByRemoved_Last(removed, orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByRemoved_PrevAndNext(
        long courseReviewId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByRemoved_PrevAndNext(courseReviewId, removed,
            orderByComparator);
    }

    /**
    * Returns all the course reviews where courseId = &#63; and removed = &#63;.
    *
    * @param courseId the course ID
    * @param removed the removed
    * @return the matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIdWithRemoved(courseId, removed);
    }

    /**
    * Returns a range of all the course reviews where courseId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param removed the removed
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRemoved(courseId, removed, start, end);
    }

    /**
    * Returns an ordered range of all the course reviews where courseId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param removed the removed
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRemoved(courseId, removed, start, end,
            orderByComparator);
    }

    /**
    * Returns the first course review in the ordered set where courseId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCourseIdWithRemoved_First(
        long courseId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseIdWithRemoved_First(courseId, removed,
            orderByComparator);
    }

    /**
    * Returns the last course review in the ordered set where courseId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview findByCourseIdWithRemoved_Last(
        long courseId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseIdWithRemoved_Last(courseId, removed,
            orderByComparator);
    }

    /**
    * Returns the course reviews before and after the current course review in the ordered set where courseId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseReviewId the primary key of the current course review
    * @param courseId the course ID
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview[] findByCourseIdWithRemoved_PrevAndNext(
        long courseReviewId, long courseId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseReviewException {
        return getPersistence()
                   .findByCourseIdWithRemoved_PrevAndNext(courseReviewId,
            courseId, removed, orderByComparator);
    }

    /**
    * Returns all the course reviews.
    *
    * @return the course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the course reviews where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseId(courseId);
    }

    /**
    * Removes all the course reviews where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Removes all the course reviews where courseId = &#63; and userId = &#63; from the database.
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIdWithUserId(long courseId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIdWithUserId(courseId, userId);
    }

    /**
    * Removes all the course reviews where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByGroupId(groupId);
    }

    /**
    * Removes all the course reviews where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Removes all the course reviews where removed = &#63; from the database.
    *
    * @param removed the removed
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRemoved(boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByRemoved(removed);
    }

    /**
    * Removes all the course reviews where courseId = &#63; and removed = &#63; from the database.
    *
    * @param courseId the course ID
    * @param removed the removed
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIdWithRemoved(long courseId,
        boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIdWithRemoved(courseId, removed);
    }

    /**
    * Removes all the course reviews from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of course reviews where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseId(courseId);
    }

    /**
    * Returns the number of course reviews where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Returns the number of course reviews where courseId = &#63; and userId = &#63;.
    *
    * @param courseId the course ID
    * @param userId the user ID
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIdWithUserId(long courseId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIdWithUserId(courseId, userId);
    }

    /**
    * Returns the number of course reviews where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByGroupId(groupId);
    }

    /**
    * Returns the number of course reviews where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns the number of course reviews where removed = &#63;.
    *
    * @param removed the removed
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByRemoved(boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByRemoved(removed);
    }

    /**
    * Returns the number of course reviews where courseId = &#63; and removed = &#63;.
    *
    * @param courseId the course ID
    * @param removed the removed
    * @return the number of matching course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIdWithRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIdWithRemoved(courseId, removed);
    }

    /**
    * Returns the number of course reviews.
    *
    * @return the number of course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CourseReviewPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CourseReviewPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseReviewPersistence.class.getName());

            ReferenceRegistry.registerReference(CourseReviewUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(CourseReviewPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(CourseReviewUtil.class,
            "_persistence");
    }
}
