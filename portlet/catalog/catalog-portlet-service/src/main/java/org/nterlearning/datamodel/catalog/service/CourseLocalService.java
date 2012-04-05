package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the course local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.CourseLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface CourseLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseLocalServiceUtil} to access the course local service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the course to the database. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @return the course that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new course with the primary key. Does not add the course to the database.
    *
    * @param courseId the primary key for the new course
    * @return the new course
    */
    public org.nterlearning.datamodel.catalog.model.Course createCourse(
        long courseId);

    /**
    * Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseId the primary key of the course
    * @throws PortalException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourse(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the course from the database. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @throws PortalException
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
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
    public org.nterlearning.datamodel.catalog.model.Course fetchCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course with the primary key.
    *
    * @param courseId the primary key of the course
    * @return the course
    * @throws PortalException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.Course getCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the courses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of courses
    * @param end the upper bound of the range of courses (not inclusive)
    * @return the range of courses
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> getCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of courses.
    *
    * @return the number of courses
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCoursesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @return the course that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @param merge whether to merge the course with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course, boolean merge)
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

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        java.lang.String filterSQL, java.lang.String sortSQL, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        long vocabularyId, long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countAllValidCourses(java.lang.String filterSQL, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countAllValidCourses(long vocabularyId, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void updateReviewHistogram(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        long userId, long feedReferenceId, java.lang.String href,
        java.lang.String courseIri, java.util.Date updatedDate,
        java.lang.String title, java.lang.String transcriptAbstract,
        java.lang.String description, java.lang.String keywords,
        java.lang.String copyright, java.lang.String ratingLevel,
        java.lang.String ratingReason, java.lang.String duration,
        java.lang.String durationStandard, double featuredStatus,
        java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        long userId, long feedReferenceId, java.lang.String href,
        java.lang.String fullTextHref, java.lang.String courseIri,
        java.util.Date updatedDate, java.lang.String title,
        java.lang.String transcriptAbstract, java.lang.String description,
        java.lang.String keywords, java.lang.String copyright,
        java.lang.String ratingLevel, java.lang.String ratingReason,
        java.lang.String duration, java.lang.String durationStandard,
        double featuredStatus, java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        boolean addGroupPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseResources(long courseId, boolean addGroupPermissions,
        boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addCourseResources(long courseId,
        java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void markCourseRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    public void setCourseFeaturedStatus(long courseId, double featured)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    public void deleteCourses(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames,
        boolean reIndex)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course updateCourseHistogram(
        long courseId, long oneStarsCount, long twoStarsCount,
        long threeStarsCount, long fourStarsCount, long fiveStarsCount)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        long userId, long courseId, long feedReferenceId,
        java.lang.String href, java.lang.String courseIri,
        java.util.Date updatedDate, java.lang.String title,
        java.lang.String transcriptAbstract, java.lang.String description,
        java.lang.String keywords, java.lang.String copyright,
        java.lang.String ratingLevel, java.lang.String ratingReason,
        java.lang.String duration, java.lang.String durationStandard,
        double featuredStatus, double popularWeight, long accessCount,
        long completedCount, boolean removed, java.util.Date removedDate,
        java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        long oneStarRateCount, long twoStarRateCount, long threeStarRateCount,
        long fourStarRateCount, long fiveStarRateCount,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        long userId, long courseId, long feedReferenceId,
        java.lang.String href, java.lang.String fullTextHref,
        java.lang.String courseIri, java.util.Date updatedDate,
        java.lang.String title, java.lang.String transcriptAbstract,
        java.lang.String description, java.lang.String keywords,
        java.lang.String copyright, java.lang.String ratingLevel,
        java.lang.String ratingReason, java.lang.String duration,
        java.lang.String durationStandard, double featuredStatus,
        double popularWeight, long accessCount, long completedCount,
        boolean removed, java.util.Date removedDate,
        java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        long oneStarRateCount, long twoStarRateCount, long threeStarRateCount,
        long fourStarRateCount, long fiveStarRateCount,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course updateStatus(
        long userId, long courseId, int status,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public int countAllCourses()
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portlet.asset.model.AssetCategory findAssetCategoryByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portlet.asset.NoSuchCategoryException;

    public java.util.List<com.liferay.portlet.asset.model.AssetEntry> findAllAssetEntries()
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getCategoryAssetEntries(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Boolean containsCategoryAssetEntries(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> findAllAssetVocabularies()
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portlet.asset.model.AssetVocabulary fetchVocabularyByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.search.Hits search(long companyId,
        java.lang.String keywords, boolean andSearch, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.search.Hits search(long companyId,
        long groupId, java.lang.String keywords, boolean andSearch, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    public void assignAllAccessCounts()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void assignAllCompletedCounts()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Clears the cache for all courses stored in this session.  This should
    * only be needed in a multi-threaded environment, where a thread is not
    * notified of persistence updates done in a different thread.
    */
    public void clearCache();

    /**
    * Clears the cache of a given course in this session.  This should only be
    * needed in a multi-threaded environment, where a thread is not notified of
    * persistence updates done in a different thread.
    *
    * @param course Course to remove from cache
    */
    public void clearCache(
        org.nterlearning.datamodel.catalog.model.Course course);

    /**
    * Clears the cache of a given course in this session.  This should only be
    * needed in a multi-threaded environment, where a thread is not notified of
    * persistence updates done in a different thread.
    *
    * @param courseId Id of the course to remove from the cache
    */
    public void clearCache(java.lang.Long courseId);
}
