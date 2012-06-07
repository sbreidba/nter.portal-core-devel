package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseServiceImpl
 * @generated
 */
public class CourseServiceUtil {
    private static CourseService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllValidCourses();
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllValidCourses(start, end);
    }

    public static org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getService().findByCourseId(courseId);
    }

    public static org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getService().findByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByGroupId(groupId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByGroupId(groupId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByFeedReferenceId(feedReferenceId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllCourses(start, end);
    }

    public static void clearService() {
        _service = null;
    }

    public static CourseService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseService.class.getName(), portletClassLoader);

            _service = new CourseServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseServiceUtil.class,
                "_service");
            MethodCache.remove(CourseService.class);
        }

        return _service;
    }

    public void setService(CourseService service) {
        MethodCache.remove(CourseService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseServiceUtil.class, "_service");
        MethodCache.remove(CourseService.class);
    }
}
