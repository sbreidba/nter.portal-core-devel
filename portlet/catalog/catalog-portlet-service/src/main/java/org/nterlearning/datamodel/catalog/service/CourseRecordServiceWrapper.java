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
