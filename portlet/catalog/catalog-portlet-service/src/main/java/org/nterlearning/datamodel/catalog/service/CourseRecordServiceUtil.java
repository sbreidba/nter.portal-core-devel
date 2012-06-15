package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course record remote service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseRecordServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecordService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRecordServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseRecordServiceImpl
 * @generated
 */
public class CourseRecordServiceUtil {
    private static CourseRecordService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseRecordServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getService().findByPrimaryKey(courseRecordId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getService().findByCourseRecordIri(courseRecordIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getService().findByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findBySingleSignOnValue(singleSignOnValue);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUserId(userId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByFeedReferenceId(feedRefId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponentRecords(courseRecord);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long courseRecordPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponentRecords(courseRecordPrimaryKey);
    }

    public static void clearService() {
        _service = null;
    }

    public static CourseRecordService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseRecordService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseRecordService.class.getName(), portletClassLoader);

            _service = new CourseRecordServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseRecordServiceUtil.class,
                "_service");
            MethodCache.remove(CourseRecordService.class);
        }

        return _service;
    }

    public void setService(CourseRecordService service) {
        MethodCache.remove(CourseRecordService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseRecordServiceUtil.class,
            "_service");
        MethodCache.remove(CourseRecordService.class);
    }
}
