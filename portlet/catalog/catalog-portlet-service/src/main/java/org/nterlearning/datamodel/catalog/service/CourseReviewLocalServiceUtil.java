package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course review local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseReviewLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseReviewLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseReviewLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseReviewLocalServiceImpl
 * @generated
 */
public class CourseReviewLocalServiceUtil {
    private static CourseReviewLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseReviewLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the course review to the database. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @return the course review that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview addCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addCourseReview(courseReview);
    }

    /**
    * Creates a new course review with the primary key. Does not add the course review to the database.
    *
    * @param courseReviewId the primary key for the new course review
    * @return the new course review
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview createCourseReview(
        long courseReviewId) {
        return getService().createCourseReview(courseReviewId);
    }

    /**
    * Deletes the course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseReviewId the primary key of the course review
    * @throws PortalException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourseReview(long courseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourseReview(courseReviewId);
    }

    /**
    * Deletes the course review from the database. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourseReview(courseReview);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReview fetchCourseReview(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCourseReview(courseReviewId);
    }

    /**
    * Returns the course review with the primary key.
    *
    * @param courseReviewId the primary key of the course review
    * @return the course review
    * @throws PortalException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview getCourseReview(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseReview(courseReviewId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseReviews(start, end);
    }

    /**
    * Returns the number of course reviews.
    *
    * @return the number of course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseReviewsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseReviewsCount();
    }

    /**
    * Updates the course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @return the course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview updateCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourseReview(courseReview);
    }

    /**
    * Updates the course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @param merge whether to merge the course review with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseReview updateCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourseReview(courseReview, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static void clearService() {
        _service = null;
    }

    public static CourseReviewLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseReviewLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseReviewLocalService.class.getName(), portletClassLoader);

            _service = new CourseReviewLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseReviewLocalServiceUtil.class,
                "_service");
            MethodCache.remove(CourseReviewLocalService.class);
        }

        return _service;
    }

    public void setService(CourseReviewLocalService service) {
        MethodCache.remove(CourseReviewLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseReviewLocalServiceUtil.class,
            "_service");
        MethodCache.remove(CourseReviewLocalService.class);
    }
}
