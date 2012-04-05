package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Courses_ComponentsLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Courses_ComponentsLocalService
 * @generated
 */
public class Courses_ComponentsLocalServiceWrapper
    implements Courses_ComponentsLocalService,
        ServiceWrapper<Courses_ComponentsLocalService> {
    private Courses_ComponentsLocalService _courses_ComponentsLocalService;

    public Courses_ComponentsLocalServiceWrapper(
        Courses_ComponentsLocalService courses_ComponentsLocalService) {
        _courses_ComponentsLocalService = courses_ComponentsLocalService;
    }

    /**
    * Adds the courses_ components to the database. Also notifies the appropriate model listeners.
    *
    * @param courses_Components the courses_ components
    * @return the courses_ components that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Courses_Components addCourses_Components(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.addCourses_Components(courses_Components);
    }

    /**
    * Creates a new courses_ components with the primary key. Does not add the courses_ components to the database.
    *
    * @param coursesComponentsId the primary key for the new courses_ components
    * @return the new courses_ components
    */
    public org.nterlearning.datamodel.catalog.model.Courses_Components createCourses_Components(
        long coursesComponentsId) {
        return _courses_ComponentsLocalService.createCourses_Components(coursesComponentsId);
    }

    /**
    * Deletes the courses_ components with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param coursesComponentsId the primary key of the courses_ components
    * @throws PortalException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourses_Components(long coursesComponentsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courses_ComponentsLocalService.deleteCourses_Components(coursesComponentsId);
    }

    /**
    * Deletes the courses_ components from the database. Also notifies the appropriate model listeners.
    *
    * @param courses_Components the courses_ components
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourses_Components(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components)
        throws com.liferay.portal.kernel.exception.SystemException {
        _courses_ComponentsLocalService.deleteCourses_Components(courses_Components);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.Courses_Components fetchCourses_Components(
        long coursesComponentsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.fetchCourses_Components(coursesComponentsId);
    }

    /**
    * Returns the courses_ components with the primary key.
    *
    * @param coursesComponentsId the primary key of the courses_ components
    * @return the courses_ components
    * @throws PortalException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Courses_Components getCourses_Components(
        long coursesComponentsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.getCourses_Components(coursesComponentsId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the courses_ componentses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.getCourses_Componentses(start,
            end);
    }

    /**
    * Returns the number of courses_ componentses.
    *
    * @return the number of courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public int getCourses_ComponentsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.getCourses_ComponentsesCount();
    }

    /**
    * Updates the courses_ components in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courses_Components the courses_ components
    * @return the courses_ components that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Courses_Components updateCourses_Components(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.updateCourses_Components(courses_Components);
    }

    /**
    * Updates the courses_ components in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courses_Components the courses_ components
    * @param merge whether to merge the courses_ components with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the courses_ components that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Courses_Components updateCourses_Components(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.updateCourses_Components(courses_Components,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _courses_ComponentsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _courses_ComponentsLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseId(
        java.lang.Long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.findByCourseId(courseId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.findByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentId(
        java.lang.Long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.findByComponentId(componentId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courses_ComponentsLocalService.findByComponentIri(componentIri);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public Courses_ComponentsLocalService getWrappedCourses_ComponentsLocalService() {
        return _courses_ComponentsLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourses_ComponentsLocalService(
        Courses_ComponentsLocalService courses_ComponentsLocalService) {
        _courses_ComponentsLocalService = courses_ComponentsLocalService;
    }

    public Courses_ComponentsLocalService getWrappedService() {
        return _courses_ComponentsLocalService;
    }

    public void setWrappedService(
        Courses_ComponentsLocalService courses_ComponentsLocalService) {
        _courses_ComponentsLocalService = courses_ComponentsLocalService;
    }
}
