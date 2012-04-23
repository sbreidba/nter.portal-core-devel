package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the global course review local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReviewLocalServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface GlobalCourseReviewLocalService
    extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link GlobalCourseReviewLocalServiceUtil} to access the global course review local service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the global course review to the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @return the global course review that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview addGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new global course review with the primary key. Does not add the global course review to the database.
    *
    * @param globalCourseReviewId the primary key for the new global course review
    * @return the new global course review
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview createGlobalCourseReview(
        long globalCourseReviewId);

    /**
    * Deletes the global course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @throws PortalException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteGlobalCourseReview(long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the global course review from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @throws SystemException if a system exception occurred
    */
    public void deleteGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the global course review with the primary key.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @return the global course review
    * @throws PortalException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview getGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of global course reviews.
    *
    * @return the number of global course reviews
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getGlobalCourseReviewsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the global course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @return the global course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    public void setReviewIsHidden(long globalCourseReviewId,
        java.lang.Boolean hidden)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException;

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;
}
