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
