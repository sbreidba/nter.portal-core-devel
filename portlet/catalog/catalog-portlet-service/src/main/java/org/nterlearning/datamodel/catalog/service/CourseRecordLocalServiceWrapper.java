package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRecordLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRecordLocalService
 * @generated
 */
public class CourseRecordLocalServiceWrapper implements CourseRecordLocalService,
    ServiceWrapper<CourseRecordLocalService> {
    private CourseRecordLocalService _courseRecordLocalService;

    public CourseRecordLocalServiceWrapper(
        CourseRecordLocalService courseRecordLocalService) {
        _courseRecordLocalService = courseRecordLocalService;
    }

    /**
    * Adds the course record to the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @return the course record that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord addCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.addCourseRecord(courseRecord);
    }

    /**
    * Creates a new course record with the primary key. Does not add the course record to the database.
    *
    * @param courseRecordId the primary key for the new course record
    * @return the new course record
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord createCourseRecord(
        long courseRecordId) {
        return _courseRecordLocalService.createCourseRecord(courseRecordId);
    }

    /**
    * Deletes the course record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecordId the primary key of the course record
    * @throws PortalException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseRecord(long courseRecordId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseRecordLocalService.deleteCourseRecord(courseRecordId);
    }

    /**
    * Deletes the course record from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @throws PortalException
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseRecordLocalService.deleteCourseRecord(courseRecord);
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
        return _courseRecordLocalService.dynamicQuery(dynamicQuery);
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
        return _courseRecordLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _courseRecordLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _courseRecordLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord fetchCourseRecord(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.fetchCourseRecord(courseRecordId);
    }

    /**
    * Returns the course record with the primary key.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record
    * @throws PortalException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord getCourseRecord(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.getCourseRecord(courseRecordId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the course records.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course records
    * @param end the upper bound of the range of course records (not inclusive)
    * @return the range of course records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> getCourseRecords(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.getCourseRecords(start, end);
    }

    /**
    * Returns the number of course records.
    *
    * @return the number of course records
    * @throws SystemException if a system exception occurred
    */
    public int getCourseRecordsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.getCourseRecordsCount();
    }

    /**
    * Updates the course record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @return the course record that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord updateCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.updateCourseRecord(courseRecord);
    }

    /**
    * Updates the course record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @param merge whether to merge the course record with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course record that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRecord updateCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.updateCourseRecord(courseRecord, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _courseRecordLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _courseRecordLocalService.setBeanIdentifier(beanIdentifier);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord addCourseRecord(
        java.lang.Long feedRefId, java.lang.String courseRecordIRI,
        long userId, java.lang.String singleSignOnValue,
        java.lang.String courseIRI, java.util.Date updatedDate,
        java.lang.String completionStatus, boolean userHidden, boolean assigned)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.addCourseRecord(feedRefId,
            courseRecordIRI, userId, singleSignOnValue, courseIRI, updatedDate,
            completionStatus, userHidden, assigned);
    }

    public void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseRecordLocalService.deleteAllChildren(courseRecord);
    }

    public void addCourseRecordResource(
        org.nterlearning.datamodel.catalog.model.CourseRecord record)
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseRecordLocalService.addCourseRecordResource(record);
    }

    public void deleteCourseRecordResource(
        org.nterlearning.datamodel.catalog.model.CourseRecord record) {
        _courseRecordLocalService.deleteCourseRecordResource(record);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return _courseRecordLocalService.findByPrimaryKey(courseRecordId);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord fetchByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.fetchByPrimaryKey(courseRecordId);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return _courseRecordLocalService.findByCourseRecordIri(courseRecordIri);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord fetchByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.fetchByCourseRecordIri(courseRecordIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return _courseRecordLocalService.findByCourseIri(courseIri);
    }

    public java.util.List<java.lang.Object[]> findByUserIdFilterSorted(
        long userId, long classNameId, java.lang.String filterType,
        java.lang.String sortType, boolean asc, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.findByUserIdFilterSorted(userId,
            classNameId, filterType, sortType, asc, start, end);
    }

    public long countByUserIdFilter(long userId, long classNameId,
        java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.countByUserIdFilter(userId,
            classNameId, filterType);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.findBySingleSignOnValue(singleSignOnValue);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.findBySingleSignOnValue(singleSignOnValue,
            start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.findByUserId(userId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.findByUserId(userId, start, end);
    }

    public long countAccessedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.countAccessedByCourseIri(courseIri);
    }

    public long countCompletedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.countCompletedByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.findByFeedReferenceId(feedRefId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.getComponentRecords(courseRecord);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long courseRecordPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecordLocalService.getComponentRecords(courseRecordPrimaryKey);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseRecordLocalService getWrappedCourseRecordLocalService() {
        return _courseRecordLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseRecordLocalService(
        CourseRecordLocalService courseRecordLocalService) {
        _courseRecordLocalService = courseRecordLocalService;
    }

    public CourseRecordLocalService getWrappedService() {
        return _courseRecordLocalService;
    }

    public void setWrappedService(
        CourseRecordLocalService courseRecordLocalService) {
        _courseRecordLocalService = courseRecordLocalService;
    }
}
