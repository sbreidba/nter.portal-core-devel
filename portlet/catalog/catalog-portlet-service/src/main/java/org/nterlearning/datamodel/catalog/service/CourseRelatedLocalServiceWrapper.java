package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRelatedLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRelatedLocalService
 * @generated
 */
public class CourseRelatedLocalServiceWrapper
    implements CourseRelatedLocalService,
        ServiceWrapper<CourseRelatedLocalService> {
    private CourseRelatedLocalService _courseRelatedLocalService;

    public CourseRelatedLocalServiceWrapper(
        CourseRelatedLocalService courseRelatedLocalService) {
        _courseRelatedLocalService = courseRelatedLocalService;
    }

    /**
    * Adds the course related to the database. Also notifies the appropriate model listeners.
    *
    * @param courseRelated the course related
    * @return the course related that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRelated addCourseRelated(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.addCourseRelated(courseRelated);
    }

    /**
    * Creates a new course related with the primary key. Does not add the course related to the database.
    *
    * @param courseRelatedId the primary key for the new course related
    * @return the new course related
    */
    public org.nterlearning.datamodel.catalog.model.CourseRelated createCourseRelated(
        long courseRelatedId) {
        return _courseRelatedLocalService.createCourseRelated(courseRelatedId);
    }

    /**
    * Deletes the course related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRelatedId the primary key of the course related
    * @throws PortalException if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseRelated(long courseRelatedId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseRelatedLocalService.deleteCourseRelated(courseRelatedId);
    }

    /**
    * Deletes the course related from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRelated the course related
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseRelated(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseRelatedLocalService.deleteCourseRelated(courseRelated);
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
        return _courseRelatedLocalService.dynamicQuery(dynamicQuery);
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
        return _courseRelatedLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _courseRelatedLocalService.dynamicQuery(dynamicQuery, start,
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
        return _courseRelatedLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRelated fetchCourseRelated(
        long courseRelatedId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.fetchCourseRelated(courseRelatedId);
    }

    /**
    * Returns the course related with the primary key.
    *
    * @param courseRelatedId the primary key of the course related
    * @return the course related
    * @throws PortalException if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRelated getCourseRelated(
        long courseRelatedId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.getCourseRelated(courseRelatedId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the course relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @return the range of course relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.getCourseRelateds(start, end);
    }

    /**
    * Returns the number of course relateds.
    *
    * @return the number of course relateds
    * @throws SystemException if a system exception occurred
    */
    public int getCourseRelatedsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.getCourseRelatedsCount();
    }

    /**
    * Updates the course related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRelated the course related
    * @return the course related that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRelated updateCourseRelated(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.updateCourseRelated(courseRelated);
    }

    /**
    * Updates the course related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRelated the course related
    * @param merge whether to merge the course related with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course related that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRelated updateCourseRelated(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRelatedLocalService.updateCourseRelated(courseRelated,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _courseRelatedLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _courseRelatedLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseRelatedLocalService getWrappedCourseRelatedLocalService() {
        return _courseRelatedLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseRelatedLocalService(
        CourseRelatedLocalService courseRelatedLocalService) {
        _courseRelatedLocalService = courseRelatedLocalService;
    }

    public CourseRelatedLocalService getWrappedService() {
        return _courseRelatedLocalService;
    }

    public void setWrappedService(
        CourseRelatedLocalService courseRelatedLocalService) {
        _courseRelatedLocalService = courseRelatedLocalService;
    }
}
