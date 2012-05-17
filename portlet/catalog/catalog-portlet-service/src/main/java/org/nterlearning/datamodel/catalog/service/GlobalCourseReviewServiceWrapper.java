package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link GlobalCourseReviewService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       GlobalCourseReviewService
 * @generated
 */
public class GlobalCourseReviewServiceWrapper
    implements GlobalCourseReviewService,
        ServiceWrapper<GlobalCourseReviewService> {
    private GlobalCourseReviewService _globalCourseReviewService;

    public GlobalCourseReviewServiceWrapper(
        GlobalCourseReviewService globalCourseReviewService) {
        _globalCourseReviewService = globalCourseReviewService;
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return _globalCourseReviewService.findByCourseReviewIri(courseReviewIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewService.findByCourseId(courseId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewService.findByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewService.findValidByCourseId(courseId, start,
            end);
    }

    public long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewService.countValidByCourseId(courseId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public GlobalCourseReviewService getWrappedGlobalCourseReviewService() {
        return _globalCourseReviewService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedGlobalCourseReviewService(
        GlobalCourseReviewService globalCourseReviewService) {
        _globalCourseReviewService = globalCourseReviewService;
    }

    public GlobalCourseReviewService getWrappedService() {
        return _globalCourseReviewService;
    }

    public void setWrappedService(
        GlobalCourseReviewService globalCourseReviewService) {
        _globalCourseReviewService = globalCourseReviewService;
    }
}
