package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ComponentRecordLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ComponentRecordLocalService
 * @generated
 */
public class ComponentRecordLocalServiceWrapper
    implements ComponentRecordLocalService,
        ServiceWrapper<ComponentRecordLocalService> {
    private ComponentRecordLocalService _componentRecordLocalService;

    public ComponentRecordLocalServiceWrapper(
        ComponentRecordLocalService componentRecordLocalService) {
        _componentRecordLocalService = componentRecordLocalService;
    }

    /**
    * Adds the component record to the database. Also notifies the appropriate model listeners.
    *
    * @param componentRecord the component record
    * @return the component record that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord addComponentRecord(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.addComponentRecord(componentRecord);
    }

    /**
    * Creates a new component record with the primary key. Does not add the component record to the database.
    *
    * @param componentRecordId the primary key for the new component record
    * @return the new component record
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord createComponentRecord(
        long componentRecordId) {
        return _componentRecordLocalService.createComponentRecord(componentRecordId);
    }

    /**
    * Deletes the component record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentRecordId the primary key of the component record
    * @throws PortalException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteComponentRecord(long componentRecordId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _componentRecordLocalService.deleteComponentRecord(componentRecordId);
    }

    /**
    * Deletes the component record from the database. Also notifies the appropriate model listeners.
    *
    * @param componentRecord the component record
    * @throws SystemException if a system exception occurred
    */
    public void deleteComponentRecord(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        _componentRecordLocalService.deleteComponentRecord(componentRecord);
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
        return _componentRecordLocalService.dynamicQuery(dynamicQuery);
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
        return _componentRecordLocalService.dynamicQuery(dynamicQuery, start,
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
        return _componentRecordLocalService.dynamicQuery(dynamicQuery, start,
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
        return _componentRecordLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.ComponentRecord fetchComponentRecord(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.fetchComponentRecord(componentRecordId);
    }

    /**
    * Returns the component record with the primary key.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record
    * @throws PortalException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord getComponentRecord(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.getComponentRecord(componentRecordId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the component records.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @return the range of component records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.getComponentRecords(start, end);
    }

    /**
    * Returns the number of component records.
    *
    * @return the number of component records
    * @throws SystemException if a system exception occurred
    */
    public int getComponentRecordsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.getComponentRecordsCount();
    }

    /**
    * Updates the component record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param componentRecord the component record
    * @return the component record that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord updateComponentRecord(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.updateComponentRecord(componentRecord);
    }

    /**
    * Updates the component record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param componentRecord the component record
    * @param merge whether to merge the component record with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the component record that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord updateComponentRecord(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.updateComponentRecord(componentRecord,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _componentRecordLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _componentRecordLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return _componentRecordLocalService.findByComponentIri(componentIri);
    }

    public java.util.List<java.lang.Object[]> findByCourseRecordIdUserIdLanguageFilterSorted(
        long courseRecordId, long userId, java.util.Locale locale,
        java.lang.String filterType, java.lang.String sortType, boolean asc,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.findByCourseRecordIdUserIdLanguageFilterSorted(courseRecordId,
            userId, locale, filterType, sortType, asc, start, end);
    }

    public long countByCourseRecordIdUserIdLanguageFilter(long courseRecordId,
        long userId, java.util.Locale locale, java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _componentRecordLocalService.countByCourseRecordIdUserIdLanguageFilter(courseRecordId,
            userId, locale, filterType);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ComponentRecordLocalService getWrappedComponentRecordLocalService() {
        return _componentRecordLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedComponentRecordLocalService(
        ComponentRecordLocalService componentRecordLocalService) {
        _componentRecordLocalService = componentRecordLocalService;
    }

    public ComponentRecordLocalService getWrappedService() {
        return _componentRecordLocalService;
    }

    public void setWrappedService(
        ComponentRecordLocalService componentRecordLocalService) {
        _componentRecordLocalService = componentRecordLocalService;
    }
}
