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
