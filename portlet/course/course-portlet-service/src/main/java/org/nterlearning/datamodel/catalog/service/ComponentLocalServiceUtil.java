package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the component local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.ComponentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.ComponentLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.ComponentLocalServiceImpl
 * @generated
 */
public class ComponentLocalServiceUtil {
    private static ComponentLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.ComponentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the component to the database. Also notifies the appropriate model listeners.
    *
    * @param component the component
    * @return the component that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component addComponent(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addComponent(component);
    }

    /**
    * Creates a new component with the primary key. Does not add the component to the database.
    *
    * @param componentId the primary key for the new component
    * @return the new component
    */
    public static org.nterlearning.datamodel.catalog.model.Component createComponent(
        long componentId) {
        return getService().createComponent(componentId);
    }

    /**
    * Deletes the component with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentId the primary key of the component
    * @throws PortalException if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteComponent(long componentId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteComponent(componentId);
    }

    /**
    * Deletes the component from the database. Also notifies the appropriate model listeners.
    *
    * @param component the component
    * @throws PortalException
    * @throws SystemException if a system exception occurred
    */
    public static void deleteComponent(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteComponent(component);
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

    public static org.nterlearning.datamodel.catalog.model.Component fetchComponent(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchComponent(componentId);
    }

    /**
    * Returns the component with the primary key.
    *
    * @param componentId the primary key of the component
    * @return the component
    * @throws PortalException if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component getComponent(
        long componentId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponent(componentId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the components.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponents(start, end);
    }

    /**
    * Returns the number of components.
    *
    * @return the number of components
    * @throws SystemException if a system exception occurred
    */
    public static int getComponentsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponentsCount();
    }

    /**
    * Updates the component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param component the component
    * @return the component that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component updateComponent(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateComponent(component);
    }

    /**
    * Updates the component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param component the component
    * @param merge whether to merge the component with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the component that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component updateComponent(
        org.nterlearning.datamodel.catalog.model.Component component,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateComponent(component, merge);
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

    public static org.nterlearning.datamodel.catalog.model.Component addComponent(
        long companyId, long feedReferenceId, java.lang.String componentIRI,
        java.lang.String description, java.lang.String title,
        java.lang.String href, java.lang.String lang,
        java.util.Date updateDate, int displayHeight, int displayWidth,
        java.lang.String version, java.util.Date versionDate, double price,
        java.lang.String priceUnit, java.lang.String priceTerms,
        java.lang.String priceExpiration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addComponent(companyId, feedReferenceId, componentIRI,
            description, title, href, lang, updateDate, displayHeight,
            displayWidth, version, versionDate, price, priceUnit, priceTerms,
            priceExpiration);
    }

    public static org.nterlearning.datamodel.catalog.model.Component addComponent(
        long companyId, long groupId, long feedReferenceId,
        java.lang.String componentIRI, java.lang.String description,
        java.lang.String title, java.lang.String href,
        java.lang.String fullTextHref, java.lang.String lang,
        java.util.Date updateDate, int displayHeight, int displayWidth,
        java.lang.String version, java.util.Date versionDate, double price,
        java.lang.String priceUnit, java.lang.String priceTerms,
        java.lang.String priceExpiration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addComponent(companyId, groupId, feedReferenceId,
            componentIRI, description, title, href, fullTextHref, lang,
            updateDate, displayHeight, displayWidth, version, versionDate,
            price, priceUnit, priceTerms, priceExpiration);
    }

    public static void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteAllChildren(component);
    }

    /**
    * Clears the cache for all components stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    */
    public static void clearCache() {
        getService().clearCache();
    }

    /**
    * Clears the cache for all components stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    *
    * @param component Component to remove from the cache
    */
    public static void clearCache(
        org.nterlearning.datamodel.catalog.model.Component component) {
        getService().clearCache(component);
    }

    /**
    * Clears the cache for all components stored in this session.  This
    * should only be needed in a multi-threaded environment, where a thread is
    * not notified of persistence updates done in a different thread.
    *
    * @param componentId Id of component to remove from cache
    */
    public static void clearCache(java.lang.Long componentId) {
        getService().clearCache(componentId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getService().findByComponentId(componentId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component fetchByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByComponentId(componentId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getService().findByComponentIri(componentIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        java.lang.Long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByFeedReferenceId(feedRefId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component fetchByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByComponentIri(componentIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getAuthors(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAuthors(component);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributors(component);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long componentPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributors(componentPrimaryKey);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponentRecords(component);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourses_Componentses(component);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLinks(component);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLinks(componentId);
    }

    public static java.util.List<java.lang.Object[]> findByCourseIdLanguageSorted(
        long courseId, java.util.Locale locale, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findByCourseIdLanguageSorted(courseId, locale, start, end);
    }

    public static void clearService() {
        _service = null;
    }

    public static ComponentLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ComponentLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ComponentLocalService.class.getName(), portletClassLoader);

            _service = new ComponentLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ComponentLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ComponentLocalService.class);
        }

        return _service;
    }

    public void setService(ComponentLocalService service) {
        MethodCache.remove(ComponentLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ComponentLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ComponentLocalService.class);
    }
}
