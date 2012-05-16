package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the component remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.ComponentServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentService
 * @see org.nterlearning.datamodel.catalog.service.base.ComponentServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.ComponentServiceImpl
 * @generated
 */
public class ComponentServiceUtil {
    private static ComponentService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.ComponentServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getService().findByComponentId(componentId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getService().findByComponentIri(componentIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        java.lang.Long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByFeedReferenceId(feedRefId);
    }

    public static void clearService() {
        _service = null;
    }

    public static ComponentService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ComponentService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ComponentService.class.getName(), portletClassLoader);

            _service = new ComponentServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ComponentServiceUtil.class,
                "_service");
            MethodCache.remove(ComponentService.class);
        }

        return _service;
    }

    public void setService(ComponentService service) {
        MethodCache.remove(ComponentService.class);

        _service = service;

        ReferenceRegistry.registerReference(ComponentServiceUtil.class,
            "_service");
        MethodCache.remove(ComponentService.class);
    }
}
