package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course review remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseReviewServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseReviewService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseReviewServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseReviewServiceImpl
 * @generated
 */
public class CourseReviewServiceUtil {
    private static CourseReviewService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseReviewServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static double findScoreByReviewId(long reviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findScoreByReviewId(reviewId);
    }

    public static java.util.List<java.lang.Double> findScoreByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findScoreByCourseId(courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseId(courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseId(courseId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseIdWithUserId(userId, courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findByCourseIdWithUserId(userId, courseId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUserId(userId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUserId(userId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findByCourseIdWithRemoved(courseId, removed, start, end);
    }

    public static long countByCourseIdWithRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countByCourseIdWithRemoved(courseId, removed);
    }

    public static void clearService() {
        _service = null;
    }

    public static CourseReviewService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseReviewService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseReviewService.class.getName(), portletClassLoader);

            _service = new CourseReviewServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseReviewServiceUtil.class,
                "_service");
            MethodCache.remove(CourseReviewService.class);
        }

        return _service;
    }

    public void setService(CourseReviewService service) {
        MethodCache.remove(CourseReviewService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseReviewServiceUtil.class,
            "_service");
        MethodCache.remove(CourseReviewService.class);
    }
}
