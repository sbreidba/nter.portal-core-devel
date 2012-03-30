package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link GlobalCourseReviewLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       GlobalCourseReviewLocalService
 * @generated
 */
public class GlobalCourseReviewLocalServiceWrapper
    implements GlobalCourseReviewLocalService,
        ServiceWrapper<GlobalCourseReviewLocalService> {
    private GlobalCourseReviewLocalService _globalCourseReviewLocalService;

    public GlobalCourseReviewLocalServiceWrapper(
        GlobalCourseReviewLocalService globalCourseReviewLocalService) {
        _globalCourseReviewLocalService = globalCourseReviewLocalService;
    }

    /**
    * Adds the global course review to the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @return the global course review that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview addGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.addGlobalCourseReview(globalCourseReview);
    }

    /**
    * Creates a new global course review with the primary key. Does not add the global course review to the database.
    *
    * @param globalCourseReviewId the primary key for the new global course review
    * @return the new global course review
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview createGlobalCourseReview(
        long globalCourseReviewId) {
        return _globalCourseReviewLocalService.createGlobalCourseReview(globalCourseReviewId);
    }

    /**
    * Deletes the global course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @throws PortalException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteGlobalCourseReview(long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _globalCourseReviewLocalService.deleteGlobalCourseReview(globalCourseReviewId);
    }

    /**
    * Deletes the global course review from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @throws SystemException if a system exception occurred
    */
    public void deleteGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        _globalCourseReviewLocalService.deleteGlobalCourseReview(globalCourseReview);
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
        return _globalCourseReviewLocalService.dynamicQuery(dynamicQuery);
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
        return _globalCourseReviewLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
        return _globalCourseReviewLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _globalCourseReviewLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.fetchGlobalCourseReview(globalCourseReviewId);
    }

    /**
    * Returns the global course review with the primary key.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @return the global course review
    * @throws PortalException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview getGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.getGlobalCourseReview(globalCourseReviewId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the global course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.getGlobalCourseReviews(start, end);
    }

    /**
    * Returns the number of global course reviews.
    *
    * @return the number of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public int getGlobalCourseReviewsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.getGlobalCourseReviewsCount();
    }

    /**
    * Updates the global course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @return the global course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.updateGlobalCourseReview(globalCourseReview);
    }

    /**
    * Updates the global course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @param merge whether to merge the global course review with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the global course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _globalCourseReviewLocalService.updateGlobalCourseReview(globalCourseReview,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _globalCourseReviewLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _globalCourseReviewLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public GlobalCourseReviewLocalService getWrappedGlobalCourseReviewLocalService() {
        return _globalCourseReviewLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedGlobalCourseReviewLocalService(
        GlobalCourseReviewLocalService globalCourseReviewLocalService) {
        _globalCourseReviewLocalService = globalCourseReviewLocalService;
    }

    public GlobalCourseReviewLocalService getWrappedService() {
        return _globalCourseReviewLocalService;
    }

    public void setWrappedService(
        GlobalCourseReviewLocalService globalCourseReviewLocalService) {
        _globalCourseReviewLocalService = globalCourseReviewLocalService;
    }
}
