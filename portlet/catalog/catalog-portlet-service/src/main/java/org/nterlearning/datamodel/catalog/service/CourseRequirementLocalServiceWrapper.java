package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRequirementLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRequirementLocalService
 * @generated
 */
public class CourseRequirementLocalServiceWrapper
    implements CourseRequirementLocalService,
        ServiceWrapper<CourseRequirementLocalService> {
    private CourseRequirementLocalService _courseRequirementLocalService;

    public CourseRequirementLocalServiceWrapper(
        CourseRequirementLocalService courseRequirementLocalService) {
        _courseRequirementLocalService = courseRequirementLocalService;
    }

    /**
    * Adds the course requirement to the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @return the course requirement that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement addCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.addCourseRequirement(courseRequirement);
    }

    /**
    * Creates a new course requirement with the primary key. Does not add the course requirement to the database.
    *
    * @param courseRequirementId the primary key for the new course requirement
    * @return the new course requirement
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement createCourseRequirement(
        long courseRequirementId) {
        return _courseRequirementLocalService.createCourseRequirement(courseRequirementId);
    }

    /**
    * Deletes the course requirement with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @throws PortalException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseRequirement(long courseRequirementId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseRequirementLocalService.deleteCourseRequirement(courseRequirementId);
    }

    /**
    * Deletes the course requirement from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement)
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseRequirementLocalService.deleteCourseRequirement(courseRequirement);
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
        return _courseRequirementLocalService.dynamicQuery(dynamicQuery);
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
        return _courseRequirementLocalService.dynamicQuery(dynamicQuery, start,
            end);
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
        return _courseRequirementLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _courseRequirementLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRequirement fetchCourseRequirement(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.fetchCourseRequirement(courseRequirementId);
    }

    /**
    * Returns the course requirement with the primary key.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement
    * @throws PortalException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement getCourseRequirement(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.getCourseRequirement(courseRequirementId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the course requirements.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course requirements
    * @param end the upper bound of the range of course requirements (not inclusive)
    * @return the range of course requirements
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.getCourseRequirements(start, end);
    }

    /**
    * Returns the number of course requirements.
    *
    * @return the number of course requirements
    * @throws SystemException if a system exception occurred
    */
    public int getCourseRequirementsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.getCourseRequirementsCount();
    }

    /**
    * Updates the course requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @return the course requirement that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement updateCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.updateCourseRequirement(courseRequirement);
    }

    /**
    * Updates the course requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @param merge whether to merge the course requirement with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course requirement that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement updateCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.updateCourseRequirement(courseRequirement,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _courseRequirementLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _courseRequirementLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        java.lang.Long courseId, java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRequirementLocalService.findByCourseIdWithRequirementType(courseId,
            requirementType);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseRequirementLocalService getWrappedCourseRequirementLocalService() {
        return _courseRequirementLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseRequirementLocalService(
        CourseRequirementLocalService courseRequirementLocalService) {
        _courseRequirementLocalService = courseRequirementLocalService;
    }

    public CourseRequirementLocalService getWrappedService() {
        return _courseRequirementLocalService;
    }

    public void setWrappedService(
        CourseRequirementLocalService courseRequirementLocalService) {
        _courseRequirementLocalService = courseRequirementLocalService;
    }
}
