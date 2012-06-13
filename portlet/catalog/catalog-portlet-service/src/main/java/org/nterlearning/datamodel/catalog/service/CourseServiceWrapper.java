package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseService
 * @generated
 */
public class CourseServiceWrapper implements CourseService,
    ServiceWrapper<CourseService> {
    private CourseService _courseService;

    public CourseServiceWrapper(CourseService courseService) {
        _courseService = courseService;
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findAllValidCourses();
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findAllValidCourses(start, end);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return _courseService.findByCourseId(courseId);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return _courseService.findByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findByGroupId(groupId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findByGroupId(groupId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findByCompanyId(companyId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findByCompanyId(companyId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findByFeedReferenceId(feedReferenceId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseService.findAllCourses(start, end);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseService getWrappedCourseService() {
        return _courseService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseService(CourseService courseService) {
        _courseService = courseService;
    }

    public CourseService getWrappedService() {
        return _courseService;
    }

    public void setWrappedService(CourseService courseService) {
        _courseService = courseService;
    }
}
