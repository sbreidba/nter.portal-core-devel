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
