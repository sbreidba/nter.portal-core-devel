package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ComponentRecordService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ComponentRecordService
 * @generated
 */
public class ComponentRecordServiceWrapper implements ComponentRecordService,
    ServiceWrapper<ComponentRecordService> {
    private ComponentRecordService _componentRecordService;

    public ComponentRecordServiceWrapper(
        ComponentRecordService componentRecordService) {
        _componentRecordService = componentRecordService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ComponentRecordService getWrappedComponentRecordService() {
        return _componentRecordService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedComponentRecordService(
        ComponentRecordService componentRecordService) {
        _componentRecordService = componentRecordService;
    }

    public ComponentRecordService getWrappedService() {
        return _componentRecordService;
    }

    public void setWrappedService(ComponentRecordService componentRecordService) {
        _componentRecordService = componentRecordService;
    }
}
