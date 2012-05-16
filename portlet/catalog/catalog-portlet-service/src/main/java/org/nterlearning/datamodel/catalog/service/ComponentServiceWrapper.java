package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ComponentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ComponentService
 * @generated
 */
public class ComponentServiceWrapper implements ComponentService,
    ServiceWrapper<ComponentService> {
    private ComponentService _componentService;

    public ComponentServiceWrapper(ComponentService componentService) {
        _componentService = componentService;
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentService.findByCompanyId(companyId);
    }

    public org.nterlearning.datamodel.catalog.model.Component findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return _componentService.findByComponentId(componentId);
    }

    public org.nterlearning.datamodel.catalog.model.Component findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return _componentService.findByComponentIri(componentIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        java.lang.Long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentService.findByFeedReferenceId(feedRefId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ComponentService getWrappedComponentService() {
        return _componentService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedComponentService(ComponentService componentService) {
        _componentService = componentService;
    }

    public ComponentService getWrappedService() {
        return _componentService;
    }

    public void setWrappedService(ComponentService componentService) {
        _componentService = componentService;
    }
}
