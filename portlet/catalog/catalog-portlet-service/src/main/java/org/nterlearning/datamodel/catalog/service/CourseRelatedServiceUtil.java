package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course related remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseRelatedServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRelatedService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRelatedServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseRelatedServiceImpl
 * @generated
 */
public class CourseRelatedServiceUtil {
    private static CourseRelatedService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseRelatedServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static CourseRelatedService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseRelatedService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseRelatedService.class.getName(), portletClassLoader);

            _service = new CourseRelatedServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseRelatedServiceUtil.class,
                "_service");
            MethodCache.remove(CourseRelatedService.class);
        }

        return _service;
    }

    public void setService(CourseRelatedService service) {
        MethodCache.remove(CourseRelatedService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseRelatedServiceUtil.class,
            "_service");
        MethodCache.remove(CourseRelatedService.class);
    }
}
