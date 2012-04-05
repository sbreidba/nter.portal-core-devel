package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the course review local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseReviewLocalServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.CourseReviewLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseReviewLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface CourseReviewLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseReviewLocalServiceUtil} to access the course review local service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseReviewLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the course review to the database. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @return the course review that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseReview addCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new course review with the primary key. Does not add the course review to the database.
    *
    * @param courseReviewId the primary key for the new course review
    * @return the new course review
    */
    public org.nterlearning.datamodel.catalog.model.CourseReview createCourseReview(
        long courseReviewId);

    /**
    * Deletes the course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseReviewId the primary key of the course review
    * @throws PortalException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseReview(long courseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the course review from the database. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @throws PortalException
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseReview fetchCourseReview(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course review with the primary key.
    *
    * @param courseReviewId the primary key of the course review
    * @return the course review
    * @throws PortalException if a course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.CourseReview getCourseReview(
        long courseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course reviews
    * @param end the upper bound of the range of course reviews (not inclusive)
    * @return the range of course reviews
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course reviews.
    *
    * @return the number of course reviews
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCourseReviewsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @return the course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseReview updateCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseReview the course review
    * @param merge whether to merge the course review with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseReview updateCourseReview(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
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

    /**
    * Update an existing course review or add it if it does not exists.
    * Modifies the database.
    *
    * @param userId the user's ID
    * @param courseId the ID of the reviewed course
    * @param summary the review summary/title
    * @param content the review text
    * @param serviceContext the service context
    */
    public org.nterlearning.datamodel.catalog.model.CourseReview appendCourseReview(
        long userId, long courseReviewId, long courseId,
        java.lang.String summary, java.lang.String content, double rating,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.CourseReview addCourseReview(
        long userId, long courseId, java.lang.String summary,
        java.lang.String content, double rating,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseReviewResources(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        boolean addGroupPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseReviewResources(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public double findScoreByReviewId(long reviewId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<java.lang.Double> findScoreByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void addCourseReviewResources(long courseReviewId,
        boolean addGroupPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseReviewResources(long courseReviewId,
        java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void deleteCourseReviews(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.CourseReview updateCourseReview(
        long userId, long courseReviewId, long courseId,
        java.lang.String summary, java.lang.String content, double rating,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateCourseReviewRating(long courseReviewId,
        double weightedScore)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateCourseReviewResources(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setRemoved(long reviewId, boolean removed)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void purgeAllRemovedOlderThan(java.util.Date date)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countByCourseIdWithRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException;
}
