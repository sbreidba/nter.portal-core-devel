package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseReviewService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseReviewService
 * @generated
 */
public class CourseReviewServiceWrapper implements CourseReviewService,
    ServiceWrapper<CourseReviewService> {
    private CourseReviewService _courseReviewService;

    public CourseReviewServiceWrapper(CourseReviewService courseReviewService) {
        _courseReviewService = courseReviewService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseReviewService getWrappedCourseReviewService() {
        return _courseReviewService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseReviewService(
        CourseReviewService courseReviewService) {
        _courseReviewService = courseReviewService;
    }

    public CourseReviewService getWrappedService() {
        return _courseReviewService;
    }

    public void setWrappedService(CourseReviewService courseReviewService) {
        _courseReviewService = courseReviewService;
    }
}
