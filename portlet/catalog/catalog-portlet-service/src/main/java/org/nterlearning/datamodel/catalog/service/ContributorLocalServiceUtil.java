package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contributor local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.ContributorLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContributorLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.ContributorLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.ContributorLocalServiceImpl
 * @generated
 */
public class ContributorLocalServiceUtil {
    private static ContributorLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.ContributorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contributor to the database. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @return the contributor that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor addContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContributor(contributor);
    }

    /**
    * Creates a new contributor with the primary key. Does not add the contributor to the database.
    *
    * @param contributorId the primary key for the new contributor
    * @return the new contributor
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor createContributor(
        long contributorId) {
        return getService().createContributor(contributorId);
    }

    /**
    * Deletes the contributor with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contributorId the primary key of the contributor
    * @throws PortalException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContributor(long contributorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContributor(contributorId);
    }

    /**
    * Deletes the contributor from the database. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContributor(contributor);
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

    public static org.nterlearning.datamodel.catalog.model.Contributor fetchContributor(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContributor(contributorId);
    }

    /**
    * Returns the contributor with the primary key.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor
    * @throws PortalException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor getContributor(
        long contributorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributor(contributorId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contributors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @return the range of contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributors(start, end);
    }

    /**
    * Returns the number of contributors.
    *
    * @return the number of contributors
    * @throws SystemException if a system exception occurred
    */
    public static int getContributorsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributorsCount();
    }

    /**
    * Updates the contributor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @return the contributor that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor updateContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContributor(contributor);
    }

    /**
    * Updates the contributor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @param merge whether to merge the contributor with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contributor that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor updateContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContributor(contributor, merge);
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

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        java.lang.Long courseId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseIdWithRole(courseId, role);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        java.lang.Long componentId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByComponentIdWithRole(componentId, role);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContributorLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContributorLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContributorLocalService.class.getName(), portletClassLoader);

            _service = new ContributorLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContributorLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ContributorLocalService.class);
        }

        return _service;
    }

    public void setService(ContributorLocalService service) {
        MethodCache.remove(ContributorLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContributorLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ContributorLocalService.class);
    }
}
