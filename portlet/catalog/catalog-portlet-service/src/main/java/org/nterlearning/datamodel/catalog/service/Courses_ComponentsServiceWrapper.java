package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Courses_ComponentsService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Courses_ComponentsService
 * @generated
 */
public class Courses_ComponentsServiceWrapper
    implements Courses_ComponentsService,
        ServiceWrapper<Courses_ComponentsService> {
    private Courses_ComponentsService _courses_ComponentsService;

    public Courses_ComponentsServiceWrapper(
        Courses_ComponentsService courses_ComponentsService) {
        _courses_ComponentsService = courses_ComponentsService;
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseId(
        java.lang.Long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsService.findByCourseId(courseId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsService.findByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentId(
        java.lang.Long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsService.findByComponentId(componentId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsService.findByComponentIri(componentIri);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public Courses_ComponentsService getWrappedCourses_ComponentsService() {
        return _courses_ComponentsService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourses_ComponentsService(
        Courses_ComponentsService courses_ComponentsService) {
        _courses_ComponentsService = courses_ComponentsService;
    }

    public Courses_ComponentsService getWrappedService() {
        return _courses_ComponentsService;
    }

    public void setWrappedService(
        Courses_ComponentsService courses_ComponentsService) {
        _courses_ComponentsService = courses_ComponentsService;
    }
}
