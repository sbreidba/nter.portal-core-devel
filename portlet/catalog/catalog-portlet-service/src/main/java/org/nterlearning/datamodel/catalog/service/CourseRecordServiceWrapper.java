package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRecordService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRecordService
 * @generated
 */
public class CourseRecordServiceWrapper implements CourseRecordService,
    ServiceWrapper<CourseRecordService> {
    private CourseRecordService _courseRecordService;

    public CourseRecordServiceWrapper(CourseRecordService courseRecordService) {
        _courseRecordService = courseRecordService;
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return _courseRecordService.findByPrimaryKey(courseRecordId);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return _courseRecordService.findByCourseRecordIri(courseRecordIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return _courseRecordService.findByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordService.findBySingleSignOnValue(singleSignOnValue);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordService.findByUserId(userId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordService.findByFeedReferenceId(feedRefId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordService.getComponentRecords(courseRecord);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long courseRecordPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordService.getComponentRecords(courseRecordPrimaryKey);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseRecordService getWrappedCourseRecordService() {
        return _courseRecordService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseRecordService(
        CourseRecordService courseRecordService) {
        _courseRecordService = courseRecordService;
    }

    public CourseRecordService getWrappedService() {
        return _courseRecordService;
    }

    public void setWrappedService(CourseRecordService courseRecordService) {
        _courseRecordService = courseRecordService;
    }
}
