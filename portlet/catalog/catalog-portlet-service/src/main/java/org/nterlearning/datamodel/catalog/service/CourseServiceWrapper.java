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
