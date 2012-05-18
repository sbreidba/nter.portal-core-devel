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

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return _componentRecordService.findByComponentIri(componentIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordService.findByCourseRecordId(courseRecordId);
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
