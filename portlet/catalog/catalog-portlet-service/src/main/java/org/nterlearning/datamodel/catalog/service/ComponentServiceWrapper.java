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
