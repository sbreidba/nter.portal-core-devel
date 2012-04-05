package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FlagReportLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FlagReportLocalService
 * @generated
 */
public class FlagReportLocalServiceWrapper implements FlagReportLocalService,
    ServiceWrapper<FlagReportLocalService> {
    private FlagReportLocalService _flagReportLocalService;

    public FlagReportLocalServiceWrapper(
        FlagReportLocalService flagReportLocalService) {
        _flagReportLocalService = flagReportLocalService;
    }

    /**
    * Adds the flag report to the database. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @return the flag report that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReport addFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.addFlagReport(flagReport);
    }

    /**
    * Creates a new flag report with the primary key. Does not add the flag report to the database.
    *
    * @param flagReportId the primary key for the new flag report
    * @return the new flag report
    */
    public org.nterlearning.datamodel.catalog.model.FlagReport createFlagReport(
        long flagReportId) {
        return _flagReportLocalService.createFlagReport(flagReportId);
    }

    /**
    * Deletes the flag report with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportId the primary key of the flag report
    * @throws PortalException if a flag report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFlagReport(long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _flagReportLocalService.deleteFlagReport(flagReportId);
    }

    /**
    * Deletes the flag report from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @throws SystemException if a system exception occurred
    */
    public void deleteFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        _flagReportLocalService.deleteFlagReport(flagReport);
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
        return _flagReportLocalService.dynamicQuery(dynamicQuery);
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
        return _flagReportLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _flagReportLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _flagReportLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport fetchFlagReport(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.fetchFlagReport(flagReportId);
    }

    /**
    * Returns the flag report with the primary key.
    *
    * @param flagReportId the primary key of the flag report
    * @return the flag report
    * @throws PortalException if a flag report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReport getFlagReport(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.getFlagReport(flagReportId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns the flag report with the UUID in the group.
    *
    * @param uuid the UUID of flag report
    * @param groupId the group id of the flag report
    * @return the flag report
    * @throws PortalException if a flag report with the UUID in the group could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReport getFlagReportByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.getFlagReportByUuidAndGroupId(uuid,
            groupId);
    }

    /**
    * Returns a range of all the flag reports.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of flag reports
    * @param end the upper bound of the range of flag reports (not inclusive)
    * @return the range of flag reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> getFlagReports(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.getFlagReports(start, end);
    }

    /**
    * Returns the number of flag reports.
    *
    * @return the number of flag reports
    * @throws SystemException if a system exception occurred
    */
    public int getFlagReportsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.getFlagReportsCount();
    }

    /**
    * Updates the flag report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @return the flag report that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.updateFlagReport(flagReport);
    }

    /**
    * Updates the flag report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @param merge whether to merge the flag report with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the flag report that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportLocalService.updateFlagReport(flagReport, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _flagReportLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _flagReportLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FlagReportLocalService getWrappedFlagReportLocalService() {
        return _flagReportLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFlagReportLocalService(
        FlagReportLocalService flagReportLocalService) {
        _flagReportLocalService = flagReportLocalService;
    }

    public FlagReportLocalService getWrappedService() {
        return _flagReportLocalService;
    }

    public void setWrappedService(FlagReportLocalService flagReportLocalService) {
        _flagReportLocalService = flagReportLocalService;
    }
}
