package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the global course review remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReviewService
 * @see org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewServiceImpl
 * @generated
 */
public class GlobalCourseReviewServiceUtil {
    private static GlobalCourseReviewService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getService().findByCourseReviewIri(courseReviewIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseId(courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findValidByCourseId(courseId, start, end);
    }

    public static long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countValidByCourseId(courseId);
    }

    public static void clearService() {
        _service = null;
    }

    public static GlobalCourseReviewService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    GlobalCourseReviewService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    GlobalCourseReviewService.class.getName(),
                    portletClassLoader);

            _service = new GlobalCourseReviewServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(GlobalCourseReviewServiceUtil.class,
                "_service");
            MethodCache.remove(GlobalCourseReviewService.class);
        }

        return _service;
    }

    public void setService(GlobalCourseReviewService service) {
        MethodCache.remove(GlobalCourseReviewService.class);

        _service = service;

        ReferenceRegistry.registerReference(GlobalCourseReviewServiceUtil.class,
            "_service");
        MethodCache.remove(GlobalCourseReviewService.class);
    }
}
