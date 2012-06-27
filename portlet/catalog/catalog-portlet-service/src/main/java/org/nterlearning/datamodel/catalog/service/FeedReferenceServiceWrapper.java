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

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return _feedReferenceService.findByFeedIri(feedIri);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        return _feedReferenceService.findByFeedHref(href);
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
