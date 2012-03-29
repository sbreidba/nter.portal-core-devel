package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FlagReportStatsLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FlagReportStatsLocalService
 * @generated
 */
public class FlagReportStatsLocalServiceWrapper
    implements FlagReportStatsLocalService,
        ServiceWrapper<FlagReportStatsLocalService> {
    private FlagReportStatsLocalService _flagReportStatsLocalService;

    public FlagReportStatsLocalServiceWrapper(
        FlagReportStatsLocalService flagReportStatsLocalService) {
        _flagReportStatsLocalService = flagReportStatsLocalService;
    }

    /**
    * Adds the flag report stats to the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportStats the flag report stats
    * @return the flag report stats that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats addFlagReportStats(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.addFlagReportStats(flagReportStats);
    }

    /**
    * Creates a new flag report stats with the primary key. Does not add the flag report stats to the database.
    *
    * @param flagReportStatsId the primary key for the new flag report stats
    * @return the new flag report stats
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats createFlagReportStats(
        long flagReportStatsId) {
        return _flagReportStatsLocalService.createFlagReportStats(flagReportStatsId);
    }

    /**
    * Deletes the flag report stats with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @throws PortalException if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFlagReportStats(long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _flagReportStatsLocalService.deleteFlagReportStats(flagReportStatsId);
    }

    /**
    * Deletes the flag report stats from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportStats the flag report stats
    * @throws SystemException if a system exception occurred
    */
    public void deleteFlagReportStats(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats)
        throws com.liferay.portal.kernel.exception.SystemException {
        _flagReportStatsLocalService.deleteFlagReportStats(flagReportStats);
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
        return _flagReportStatsLocalService.dynamicQuery(dynamicQuery);
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
        return _flagReportStatsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _flagReportStatsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _flagReportStatsLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReportStats fetchFlagReportStats(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.fetchFlagReportStats(flagReportStatsId);
    }

    /**
    * Returns the flag report stats with the primary key.
    *
    * @param flagReportStatsId the primary key of the flag report stats
    * @return the flag report stats
    * @throws PortalException if a flag report stats with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats getFlagReportStats(
        long flagReportStatsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.getFlagReportStats(flagReportStatsId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the flag report statses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of flag report statses
    * @param end the upper bound of the range of flag report statses (not inclusive)
    * @return the range of flag report statses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReportStats> getFlagReportStatses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.getFlagReportStatses(start, end);
    }

    /**
    * Returns the number of flag report statses.
    *
    * @return the number of flag report statses
    * @throws SystemException if a system exception occurred
    */
    public int getFlagReportStatsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.getFlagReportStatsesCount();
    }

    /**
    * Updates the flag report stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param flagReportStats the flag report stats
    * @return the flag report stats that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats updateFlagReportStats(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.updateFlagReportStats(flagReportStats);
    }

    /**
    * Updates the flag report stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param flagReportStats the flag report stats
    * @param merge whether to merge the flag report stats with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the flag report stats that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FlagReportStats updateFlagReportStats(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReportStatsLocalService.updateFlagReportStats(flagReportStats,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _flagReportStatsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _flagReportStatsLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FlagReportStatsLocalService getWrappedFlagReportStatsLocalService() {
        return _flagReportStatsLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFlagReportStatsLocalService(
        FlagReportStatsLocalService flagReportStatsLocalService) {
        _flagReportStatsLocalService = flagReportStatsLocalService;
    }

    public FlagReportStatsLocalService getWrappedService() {
        return _flagReportStatsLocalService;
    }

    public void setWrappedService(
        FlagReportStatsLocalService flagReportStatsLocalService) {
        _flagReportStatsLocalService = flagReportStatsLocalService;
    }
}
