package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedReferenceService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedReferenceService
 * @generated
 */
public class FeedReferenceServiceWrapper implements FeedReferenceService,
    ServiceWrapper<FeedReferenceService> {
    private FeedReferenceService _feedReferenceService;

    public FeedReferenceServiceWrapper(
        FeedReferenceService feedReferenceService) {
        _feedReferenceService = feedReferenceService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FeedReferenceService getWrappedFeedReferenceService() {
        return _feedReferenceService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFeedReferenceService(
        FeedReferenceService feedReferenceService) {
        _feedReferenceService = feedReferenceService;
    }

    public FeedReferenceService getWrappedService() {
        return _feedReferenceService;
    }

    public void setWrappedService(FeedReferenceService feedReferenceService) {
        _feedReferenceService = feedReferenceService;
    }
}
