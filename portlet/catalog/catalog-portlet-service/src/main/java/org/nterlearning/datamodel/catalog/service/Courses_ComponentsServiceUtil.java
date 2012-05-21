package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the courses_ components remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.Courses_ComponentsServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Courses_ComponentsService
 * @see org.nterlearning.datamodel.catalog.service.base.Courses_ComponentsServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.Courses_ComponentsServiceImpl
 * @generated
 */
public class Courses_ComponentsServiceUtil {
    private static Courses_ComponentsService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.Courses_ComponentsServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseId(
        java.lang.Long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseId(courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentId(
        java.lang.Long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByComponentId(componentId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByComponentIri(componentIri);
    }

    public static void clearService() {
        _service = null;
    }

    public static Courses_ComponentsService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Courses_ComponentsService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    Courses_ComponentsService.class.getName(),
                    portletClassLoader);

            _service = new Courses_ComponentsServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(Courses_ComponentsServiceUtil.class,
                "_service");
            MethodCache.remove(Courses_ComponentsService.class);
        }

        return _service;
    }

    public void setService(Courses_ComponentsService service) {
        MethodCache.remove(Courses_ComponentsService.class);

        _service = service;

        ReferenceRegistry.registerReference(Courses_ComponentsServiceUtil.class,
            "_service");
        MethodCache.remove(Courses_ComponentsService.class);
    }
}
