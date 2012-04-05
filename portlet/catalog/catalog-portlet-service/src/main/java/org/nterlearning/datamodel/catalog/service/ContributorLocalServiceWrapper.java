package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContributorLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContributorLocalService
 * @generated
 */
public class ContributorLocalServiceWrapper implements ContributorLocalService,
    ServiceWrapper<ContributorLocalService> {
    private ContributorLocalService _contributorLocalService;

    public ContributorLocalServiceWrapper(
        ContributorLocalService contributorLocalService) {
        _contributorLocalService = contributorLocalService;
    }

    /**
    * Adds the contributor to the database. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @return the contributor that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor addContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.addContributor(contributor);
    }

    /**
    * Creates a new contributor with the primary key. Does not add the contributor to the database.
    *
    * @param contributorId the primary key for the new contributor
    * @return the new contributor
    */
    public org.nterlearning.datamodel.catalog.model.Contributor createContributor(
        long contributorId) {
        return _contributorLocalService.createContributor(contributorId);
    }

    /**
    * Deletes the contributor with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contributorId the primary key of the contributor
    * @throws PortalException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteContributor(long contributorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contributorLocalService.deleteContributor(contributorId);
    }

    /**
    * Deletes the contributor from the database. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @throws SystemException if a system exception occurred
    */
    public void deleteContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contributorLocalService.deleteContributor(contributor);
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
        return _contributorLocalService.dynamicQuery(dynamicQuery);
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
        return _contributorLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _contributorLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _contributorLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.Contributor fetchContributor(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.fetchContributor(contributorId);
    }

    /**
    * Returns the contributor with the primary key.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor
    * @throws PortalException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor getContributor(
        long contributorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.getContributor(contributorId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.getPersistedModel(primaryKeyObj);
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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.getContributors(start, end);
    }

    /**
    * Returns the number of contributors.
    *
    * @return the number of contributors
    * @throws SystemException if a system exception occurred
    */
    public int getContributorsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.getContributorsCount();
    }

    /**
    * Updates the contributor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @return the contributor that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor updateContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.updateContributor(contributor);
    }

    /**
    * Updates the contributor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contributor the contributor
    * @param merge whether to merge the contributor with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contributor that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor updateContributor(
        org.nterlearning.datamodel.catalog.model.Contributor contributor,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contributorLocalService.updateContributor(contributor, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _contributorLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contributorLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContributorLocalService getWrappedContributorLocalService() {
        return _contributorLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContributorLocalService(
        ContributorLocalService contributorLocalService) {
        _contributorLocalService = contributorLocalService;
    }

    public ContributorLocalService getWrappedService() {
        return _contributorLocalService;
    }

    public void setWrappedService(
        ContributorLocalService contributorLocalService) {
        _contributorLocalService = contributorLocalService;
    }
}
