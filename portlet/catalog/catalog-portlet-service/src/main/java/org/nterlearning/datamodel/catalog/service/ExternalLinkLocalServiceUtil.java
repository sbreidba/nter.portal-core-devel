package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the external link local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.ExternalLinkLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.ExternalLinkLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.ExternalLinkLocalServiceImpl
 * @generated
 */
public class ExternalLinkLocalServiceUtil {
    private static ExternalLinkLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.ExternalLinkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the external link to the database. Also notifies the appropriate model listeners.
    *
    * @param externalLink the external link
    * @return the external link that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink addExternalLink(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addExternalLink(externalLink);
    }

    /**
    * Creates a new external link with the primary key. Does not add the external link to the database.
    *
    * @param linkId the primary key for the new external link
    * @return the new external link
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink createExternalLink(
        long linkId) {
        return getService().createExternalLink(linkId);
    }

    /**
    * Deletes the external link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param linkId the primary key of the external link
    * @throws PortalException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteExternalLink(long linkId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteExternalLink(linkId);
    }

    /**
    * Deletes the external link from the database. Also notifies the appropriate model listeners.
    *
    * @param externalLink the external link
    * @throws SystemException if a system exception occurred
    */
    public static void deleteExternalLink(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteExternalLink(externalLink);
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

    public static org.nterlearning.datamodel.catalog.model.ExternalLink fetchExternalLink(
        long linkId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchExternalLink(linkId);
    }

    /**
    * Returns the external link with the primary key.
    *
    * @param linkId the primary key of the external link
    * @return the external link
    * @throws PortalException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink getExternalLink(
        long linkId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLink(linkId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the external links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @return the range of external links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLinks(start, end);
    }

    /**
    * Returns the number of external links.
    *
    * @return the number of external links
    * @throws SystemException if a system exception occurred
    */
    public static int getExternalLinksCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLinksCount();
    }

    /**
    * Updates the external link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param externalLink the external link
    * @return the external link that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink updateExternalLink(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateExternalLink(externalLink);
    }

    /**
    * Updates the external link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param externalLink the external link
    * @param merge whether to merge the external link with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the external link that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink updateExternalLink(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateExternalLink(externalLink, merge);
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

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        java.lang.Long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseId(courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        java.lang.Long courseId, java.lang.String type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseIdWithType(courseId, type);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        java.lang.Long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByComponentId(componentId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        java.lang.Long componentId, java.lang.String type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByComponentIdWithType(componentId, type);
    }

    public static void clearService() {
        _service = null;
    }

    public static ExternalLinkLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ExternalLinkLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ExternalLinkLocalService.class.getName(), portletClassLoader);

            _service = new ExternalLinkLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ExternalLinkLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ExternalLinkLocalService.class);
        }

        return _service;
    }

    public void setService(ExternalLinkLocalService service) {
        MethodCache.remove(ExternalLinkLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ExternalLinkLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ExternalLinkLocalService.class);
    }
}
