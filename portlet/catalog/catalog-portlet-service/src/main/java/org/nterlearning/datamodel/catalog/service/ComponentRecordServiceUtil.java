package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the component record remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.ComponentRecordServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecordService
 * @see org.nterlearning.datamodel.catalog.service.base.ComponentRecordServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.ComponentRecordServiceImpl
 * @generated
 */
public class ComponentRecordServiceUtil {
    private static ComponentRecordService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.ComponentRecordServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getService().findByComponentIri(componentIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseRecordId(courseRecordId);
    }

    public static void clearService() {
        _service = null;
    }

    public static ComponentRecordService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ComponentRecordService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ComponentRecordService.class.getName(), portletClassLoader);

            _service = new ComponentRecordServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ComponentRecordServiceUtil.class,
                "_service");
            MethodCache.remove(ComponentRecordService.class);
        }

        return _service;
    }

    public void setService(ComponentRecordService service) {
        MethodCache.remove(ComponentRecordService.class);

        _service = service;

        ReferenceRegistry.registerReference(ComponentRecordServiceUtil.class,
            "_service");
        MethodCache.remove(ComponentRecordService.class);
    }
}
