package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the feed reference remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.FeedReferenceServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferenceService
 * @see org.nterlearning.datamodel.catalog.service.base.FeedReferenceServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.FeedReferenceServiceImpl
 * @generated
 */
public class FeedReferenceServiceUtil {
    private static FeedReferenceService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.FeedReferenceServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return getService().findByFeedIri(feedIri);
    }

    public static org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return getService().findByFeedHref(href);
    }

    public static void clearService() {
        _service = null;
    }

    public static FeedReferenceService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FeedReferenceService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FeedReferenceService.class.getName(), portletClassLoader);

            _service = new FeedReferenceServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FeedReferenceServiceUtil.class,
                "_service");
            MethodCache.remove(FeedReferenceService.class);
        }

        return _service;
    }

    public void setService(FeedReferenceService service) {
        MethodCache.remove(FeedReferenceService.class);

        _service = service;

        ReferenceRegistry.registerReference(FeedReferenceServiceUtil.class,
            "_service");
        MethodCache.remove(FeedReferenceService.class);
    }
}
