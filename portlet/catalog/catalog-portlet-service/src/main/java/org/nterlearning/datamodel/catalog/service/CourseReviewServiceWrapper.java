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

    public double findScoreByReviewId(long reviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findScoreByReviewId(reviewId);
    }

    public java.util.List<java.lang.Double> findScoreByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findScoreByCourseId(courseId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByCourseId(courseId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByCourseId(courseId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByCourseIdWithUserId(userId, courseId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByCourseIdWithUserId(userId, courseId,
            start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByUserId(userId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByUserId(userId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.findByCourseIdWithRemoved(courseId,
            removed, start, end);
    }

    public long countByCourseIdWithRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReviewService.countByCourseIdWithRemoved(courseId, removed);
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
