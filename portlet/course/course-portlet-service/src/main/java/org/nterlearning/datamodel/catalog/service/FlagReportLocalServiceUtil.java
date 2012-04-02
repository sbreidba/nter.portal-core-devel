package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the flag report local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.FlagReportLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagReportLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.FlagReportLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.FlagReportLocalServiceImpl
 * @generated
 */
public class FlagReportLocalServiceUtil {
    private static FlagReportLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.FlagReportLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the flag report to the database. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @return the flag report that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReport addFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addFlagReport(flagReport);
    }

    /**
    * Creates a new flag report with the primary key. Does not add the flag report to the database.
    *
    * @param flagReportId the primary key for the new flag report
    * @return the new flag report
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReport createFlagReport(
        long flagReportId) {
        return getService().createFlagReport(flagReportId);
    }

    /**
    * Deletes the flag report with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReportId the primary key of the flag report
    * @throws PortalException if a flag report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFlagReport(long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFlagReport(flagReportId);
    }

    /**
    * Deletes the flag report from the database. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFlagReport(flagReport);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport fetchFlagReport(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchFlagReport(flagReportId);
    }

    /**
    * Returns the flag report with the primary key.
    *
    * @param flagReportId the primary key of the flag report
    * @return the flag report
    * @throws PortalException if a flag report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReport getFlagReport(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getFlagReport(flagReportId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static org.nterlearning.datamodel.catalog.model.FlagReport getFlagReportByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getFlagReportByUuidAndGroupId(uuid, groupId);
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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> getFlagReports(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFlagReports(start, end);
    }

    /**
    * Returns the number of flag reports.
    *
    * @return the number of flag reports
    * @throws SystemException if a system exception occurred
    */
    public static int getFlagReportsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFlagReportsCount();
    }

    /**
    * Updates the flag report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @return the flag report that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFlagReport(flagReport);
    }

    /**
    * Updates the flag report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param flagReport the flag report
    * @param merge whether to merge the flag report with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the flag report that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFlagReport(flagReport, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport addFlagReport(
        long userId, long classNameId, long classPK, java.lang.String title,
        java.lang.String content, java.lang.String flagReason,
        java.lang.String flagComment, java.lang.String moderateAction,
        java.lang.String moderatorComment,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addFlagReport(userId, classNameId, classPK, title, content,
            flagReason, flagComment, moderateAction, moderatorComment,
            serviceContext);
    }

    public static void addFlagReportResources(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addFlagReportResources(flagReport, addCommunityPermissions,
            addGuestPermissions);
    }

    public static void addFlagReportResources(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addFlagReportResources(flagReport, communityPermissions,
            guestPermissions);
    }

    public static void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .updateAsset(userId, flagReport, assetCategoryIds, assetTagNames);
    }

    public static void deleteReports(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteReports(groupId);
    }

    public static void deleteReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteReport(flagReport);
    }

    public static void deleteReport(long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteReport(flagReportId);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport updateReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateReport(flagReport);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        long userId, long flagReportId, long classNameId, long classPK,
        java.lang.String title, java.lang.String content,
        java.lang.String flagReason, java.lang.String flagComment,
        java.lang.String moderateAction, java.lang.String moderatorComment,
        java.lang.Integer Status, long statusByUserId,
        java.lang.String statusByUserName, java.util.Date StatusDate,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateFlagReport(userId, flagReportId, classNameId,
            classPK, title, content, flagReason, flagComment, moderateAction,
            moderatorComment, Status, statusByUserId, statusByUserName,
            StatusDate, serviceContext);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport moderateFlagReport(
        long userId, long flagReportId, java.lang.Integer moderateStatus,
        java.lang.String moderateAction, java.lang.String moderatorComment,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .moderateFlagReport(userId, flagReportId, moderateStatus,
            moderateAction, moderatorComment, serviceContext);
    }

    public static void updateFlagReportResources(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .updateFlagReportResources(flagReport, communityPermissions,
            guestPermissions);
    }

    public static void updateFlagReportStats(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().updateFlagReportStats(classNameId, classPK);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport updateStatus(
        long userId, long resourcePrimKey, int status,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateStatus(userId, resourcePrimKey, status, serviceContext);
    }

    public static void removeWorkflowInstance(long groupId, long companyId,
        java.lang.String className, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .removeWorkflowInstance(groupId, companyId, className, classPK);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport findByFlagReportId(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportException {
        return getService().findByFlagReportId(flagReportId);
    }

    public static org.nterlearning.datamodel.catalog.model.FlagReport fetchByFlagReportId(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByFlagReportId(flagReportId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByGroupId(groupId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> findByClassNameIdClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByClassNameIdClassPK(classNameId, classPK);
    }

    public static java.util.List<java.lang.Object[]> findByClassNameIdAndFilter(
        long classNameId, java.lang.String filterType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findByClassNameIdAndFilter(classNameId, filterType, start,
            end);
    }

    public static void clearService() {
        _service = null;
    }

    public static FlagReportLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FlagReportLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FlagReportLocalService.class.getName(), portletClassLoader);

            _service = new FlagReportLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FlagReportLocalServiceUtil.class,
                "_service");
            MethodCache.remove(FlagReportLocalService.class);
        }

        return _service;
    }

    public void setService(FlagReportLocalService service) {
        MethodCache.remove(FlagReportLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(FlagReportLocalServiceUtil.class,
            "_service");
        MethodCache.remove(FlagReportLocalService.class);
    }
}
