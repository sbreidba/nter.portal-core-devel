package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course image remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseImageServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImageService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseImageServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseImageServiceImpl
 * @generated
 */
public class CourseImageServiceUtil {
    private static CourseImageService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseImageServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static CourseImageService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseImageService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseImageService.class.getName(), portletClassLoader);

            _service = new CourseImageServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseImageServiceUtil.class,
                "_service");
            MethodCache.remove(CourseImageService.class);
        }

        return _service;
    }

    public void setService(CourseImageService service) {
        MethodCache.remove(CourseImageService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseImageServiceUtil.class,
            "_service");
        MethodCache.remove(CourseImageService.class);
    }
}
