package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;

import java.util.List;

/**
 * The persistence utility for the global course review service. This utility wraps {@link GlobalCourseReviewPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReviewPersistence
 * @see GlobalCourseReviewPersistenceImpl
 * @generated
 */
public class GlobalCourseReviewUtil {
    private static GlobalCourseReviewPersistence _persistence;

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
    public static void clearCache(GlobalCourseReview globalCourseReview) {
        getPersistence().clearCache(globalCourseReview);
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
    public static List<GlobalCourseReview> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<GlobalCourseReview> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<GlobalCourseReview> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static GlobalCourseReview update(
        GlobalCourseReview globalCourseReview, boolean merge)
        throws SystemException {
        return getPersistence().update(globalCourseReview, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static GlobalCourseReview update(
        GlobalCourseReview globalCourseReview, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(globalCourseReview, merge, serviceContext);
    }

    /**
    * Caches the global course review in the entity cache if it is enabled.
    *
    * @param globalCourseReview the global course review
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview) {
        getPersistence().cacheResult(globalCourseReview);
    }

    /**
    * Caches the global course reviews in the entity cache if it is enabled.
    *
    * @param globalCourseReviews the global course reviews
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> globalCourseReviews) {
        getPersistence().cacheResult(globalCourseReviews);
    }

    /**
    * Creates a new global course review with the primary key. Does not add the global course review to the database.
    *
    * @param globalCourseReviewId the primary key for the new global course review
    * @return the new global course review
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview create(
        long globalCourseReviewId) {
        return getPersistence().create(globalCourseReviewId);
    }

    /**
    * Removes the global course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @return the global course review that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview remove(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().remove(globalCourseReviewId);
    }

    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateImpl(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(globalCourseReview, merge);
    }

    /**
    * Returns the global course review with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException} if it could not be found.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @return the global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByPrimaryKey(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().findByPrimaryKey(globalCourseReviewId);
    }

    /**
    * Returns the global course review with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @return the global course review, or <code>null</code> if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchByPrimaryKey(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(globalCourseReviewId);
    }

    /**
    * Returns the global course review where courseReviewIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException} if it could not be found.
    *
    * @param courseReviewIri the course review iri
    * @return the matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().findByCourseReviewIri(courseReviewIri);
    }

    /**
    * Returns the global course review where courseReviewIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param courseReviewIri the course review iri
    * @return the matching global course review, or <code>null</code> if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCourseReviewIri(courseReviewIri);
    }

    /**
    * Returns the global course review where courseReviewIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param courseReviewIri the course review iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching global course review, or <code>null</code> if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchByCourseReviewIri(
        java.lang.String courseReviewIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCourseReviewIri(courseReviewIri, retrieveFromCache);
    }

    /**
    * Returns all the global course reviews where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId);
    }

    /**
    * Returns a range of all the global course reviews where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId, start, end);
    }

    /**
    * Returns an ordered range of all the global course reviews where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseId(courseId, start, end, orderByComparator);
    }

    /**
    * Returns the first global course review in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().findByCourseId_First(courseId, orderByComparator);
    }

    /**
    * Returns the last global course review in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().findByCourseId_Last(courseId, orderByComparator);
    }

    /**
    * Returns the global course reviews before and after the current global course review in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param globalCourseReviewId the primary key of the current global course review
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview[] findByCourseId_PrevAndNext(
        long globalCourseReviewId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCourseId_PrevAndNext(globalCourseReviewId, courseId,
            orderByComparator);
    }

    /**
    * Returns all the global course reviews where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIri(courseIri);
    }

    /**
    * Returns a range of all the global course reviews where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIri(courseIri, start, end);
    }

    /**
    * Returns an ordered range of all the global course reviews where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIri(courseIri, start, end, orderByComparator);
    }

    /**
    * Returns the first global course review in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseIri_First(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCourseIri_First(courseIri, orderByComparator);
    }

    /**
    * Returns the last global course review in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseIri_Last(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCourseIri_Last(courseIri, orderByComparator);
    }

    /**
    * Returns the global course reviews before and after the current global course review in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param globalCourseReviewId the primary key of the current global course review
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview[] findByCourseIri_PrevAndNext(
        long globalCourseReviewId, java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCourseIri_PrevAndNext(globalCourseReviewId,
            courseIri, orderByComparator);
    }

    /**
    * Returns all the global course reviews where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId);
    }

    /**
    * Returns a range of all the global course reviews where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId, start, end);
    }

    /**
    * Returns an ordered range of all the global course reviews where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGroupId(groupId, start, end, orderByComparator);
    }

    /**
    * Returns the first global course review in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().findByGroupId_First(groupId, orderByComparator);
    }

    /**
    * Returns the last global course review in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence().findByGroupId_Last(groupId, orderByComparator);
    }

    /**
    * Returns the global course reviews before and after the current global course review in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param globalCourseReviewId the primary key of the current global course review
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview[] findByGroupId_PrevAndNext(
        long globalCourseReviewId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByGroupId_PrevAndNext(globalCourseReviewId, groupId,
            orderByComparator);
    }

    /**
    * Returns all the global course reviews where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

    /**
    * Returns a range of all the global course reviews where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

    /**
    * Returns an ordered range of all the global course reviews where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCompanyId(
        long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first global course review in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCompanyId_First(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the last global course review in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCompanyId_Last(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the global course reviews before and after the current global course review in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param globalCourseReviewId the primary key of the current global course review
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next global course review
    * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview[] findByCompanyId_PrevAndNext(
        long globalCourseReviewId, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(globalCourseReviewId,
            companyId, orderByComparator);
    }

    /**
    * Returns all the global course reviews.
    *
    * @return the global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the global course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the global course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the global course review where courseReviewIri = &#63; from the database.
    *
    * @param courseReviewIri the course review iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseReviewIri(java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        getPersistence().removeByCourseReviewIri(courseReviewIri);
    }

    /**
    * Removes all the global course reviews where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseId(courseId);
    }

    /**
    * Removes all the global course reviews where courseIri = &#63; from the database.
    *
    * @param courseIri the course iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIri(courseIri);
    }

    /**
    * Removes all the global course reviews where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByGroupId(groupId);
    }

    /**
    * Removes all the global course reviews where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Removes all the global course reviews from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of global course reviews where courseReviewIri = &#63;.
    *
    * @param courseReviewIri the course review iri
    * @return the number of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseReviewIri(java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseReviewIri(courseReviewIri);
    }

    /**
    * Returns the number of global course reviews where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseId(courseId);
    }

    /**
    * Returns the number of global course reviews where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the number of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIri(courseIri);
    }

    /**
    * Returns the number of global course reviews where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByGroupId(groupId);
    }

    /**
    * Returns the number of global course reviews where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns the number of global course reviews.
    *
    * @return the number of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static GlobalCourseReviewPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (GlobalCourseReviewPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    GlobalCourseReviewPersistence.class.getName());

            ReferenceRegistry.registerReference(GlobalCourseReviewUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(GlobalCourseReviewPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(GlobalCourseReviewUtil.class,
            "_persistence");
    }
}
