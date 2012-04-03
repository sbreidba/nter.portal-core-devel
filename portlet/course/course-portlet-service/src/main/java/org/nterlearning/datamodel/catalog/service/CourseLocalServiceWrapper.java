package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseLocalService
 * @generated
 */
public class CourseLocalServiceWrapper implements CourseLocalService,
    ServiceWrapper<CourseLocalService> {
    private CourseLocalService _courseLocalService;

    public CourseLocalServiceWrapper(CourseLocalService courseLocalService) {
        _courseLocalService = courseLocalService;
    }

    /**
    * Adds the course to the database. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @return the course that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.addCourse(course);
    }

    /**
    * Creates a new course with the primary key. Does not add the course to the database.
    *
    * @param courseId the primary key for the new course
    * @return the new course
    */
    public org.nterlearning.datamodel.catalog.model.Course createCourse(
        long courseId) {
        return _courseLocalService.createCourse(courseId);
    }

    /**
    * Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseId the primary key of the course
    * @throws PortalException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourse(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.deleteCourse(courseId);
    }

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
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.deleteCourse(course);
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
        return _courseLocalService.dynamicQuery(dynamicQuery);
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
        return _courseLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _courseLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _courseLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.nterlearning.datamodel.catalog.model.Course fetchCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.fetchCourse(courseId);
    }

    /**
    * Returns the course with the primary key.
    *
    * @param courseId the primary key of the course
    * @return the course
    * @throws PortalException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course getCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourse(courseId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getPersistedModel(primaryKeyObj);
    }

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> getCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourses(start, end);
    }

    /**
    * Returns the number of courses.
    *
    * @return the number of courses
    * @throws SystemException if a system exception occurred
    */
    public int getCoursesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCoursesCount();
    }

    /**
    * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @return the course that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.updateCourse(course);
    }

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
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.updateCourse(course, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _courseLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _courseLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllValidCourses();
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllValidCourses(start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        java.lang.String filterSQL, java.lang.String sortSQL, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllValidCourses(filterSQL, sortSQL,
            groupId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllValidCourses(category, groupId,
            start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        long vocabularyId, long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllValidCourses(vocabularyId, groupId,
            start, end);
    }

    public long countAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.countAllValidCourses();
    }

    public long countAllValidCourses(java.lang.String filterSQL, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.countAllValidCourses(filterSQL, groupId);
    }

    public long countAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.countAllValidCourses(category, groupId);
    }

    public long countAllValidCourses(long vocabularyId, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.countAllValidCourses(vocabularyId, groupId);
    }

    public void updateReviewHistogram(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.updateReviewHistogram(courseId);
    }

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
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.addCourse(userId, feedReferenceId, href,
            courseIri, updatedDate, title, transcriptAbstract, description,
            keywords, copyright, ratingLevel, ratingReason, duration,
            durationStandard, featuredStatus, supersedesCourseIri,
            supersededByCourseIri, releaseOnDate, acceptUntilDate, version,
            versionDate, price, priceUnit, priceTerms, priceExpiration,
            serviceContext);
    }

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
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.addCourse(userId, feedReferenceId, href,
            fullTextHref, courseIri, updatedDate, title, transcriptAbstract,
            description, keywords, copyright, ratingLevel, ratingReason,
            duration, durationStandard, featuredStatus, supersedesCourseIri,
            supersededByCourseIri, releaseOnDate, acceptUntilDate, version,
            versionDate, price, priceUnit, priceTerms, priceExpiration,
            serviceContext);
    }

    public void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.addCourseResources(course, addCommunityPermissions,
            addGuestPermissions);
    }

    public void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.addCourseResources(course, communityPermissions,
            guestPermissions);
    }

    public void addCourseResources(long courseId,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.addCourseResources(courseId,
            addCommunityPermissions, addGuestPermissions);
    }

    public void addCourseResources(long courseId,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.addCourseResources(courseId, communityPermissions,
            guestPermissions);
    }

    public void markCourseRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        _courseLocalService.markCourseRemoved(courseId, removed);
    }

    public void setCourseFeaturedStatus(long courseId, double featured)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        _courseLocalService.setCourseFeaturedStatus(courseId, featured);
    }

    public void deleteCourses(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.deleteCourses(groupId);
    }

    public void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.deleteAllChildren(course);
    }

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.updateAsset(userId, course, assetCategoryIds,
            assetTagNames);
    }

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames,
        boolean reIndex)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.updateAsset(userId, course, assetCategoryIds,
            assetTagNames, reIndex);
    }

    public org.nterlearning.datamodel.catalog.model.Course updateCourseHistogram(
        long courseId, long oneStarsCount, long twoStarsCount,
        long threeStarsCount, long fourStarsCount, long fiveStarsCount)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.updateCourseHistogram(courseId,
            oneStarsCount, twoStarsCount, threeStarsCount, fourStarsCount,
            fiveStarsCount);
    }

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
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.updateCourse(userId, courseId,
            feedReferenceId, href, courseIri, updatedDate, title,
            transcriptAbstract, description, keywords, copyright, ratingLevel,
            ratingReason, duration, durationStandard, featuredStatus,
            popularWeight, accessCount, completedCount, removed, removedDate,
            supersedesCourseIri, supersededByCourseIri, releaseOnDate,
            acceptUntilDate, version, versionDate, price, priceUnit,
            priceTerms, priceExpiration, oneStarRateCount, twoStarRateCount,
            threeStarRateCount, fourStarRateCount, fiveStarRateCount,
            serviceContext);
    }

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
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.updateCourse(userId, courseId,
            feedReferenceId, href, fullTextHref, courseIri, updatedDate, title,
            transcriptAbstract, description, keywords, copyright, ratingLevel,
            ratingReason, duration, durationStandard, featuredStatus,
            popularWeight, accessCount, completedCount, removed, removedDate,
            supersedesCourseIri, supersededByCourseIri, releaseOnDate,
            acceptUntilDate, version, versionDate, price, priceUnit,
            priceTerms, priceExpiration, oneStarRateCount, twoStarRateCount,
            threeStarRateCount, fourStarRateCount, fiveStarRateCount,
            serviceContext);
    }

    public void updateCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.updateCourseResources(course, communityPermissions,
            guestPermissions);
    }

    public org.nterlearning.datamodel.catalog.model.Course updateStatus(
        long userId, long courseId, int status,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.updateStatus(userId, courseId, status,
            serviceContext);
    }

    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.countByGroupId(groupId);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return _courseLocalService.findByCourseId(courseId);
    }

    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.fetchByCourseId(courseId);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return _courseLocalService.findByCourseIri(courseIri);
    }

    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.fetchByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findByGroupId(groupId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findByGroupId(groupId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findByCompanyId(companyId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findByCompanyId(companyId, start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findByFeedReferenceId(feedReferenceId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findBySupersededByCourseIri(courseIri);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findBySupersededByCourseIri(courseIri,
            start, end);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllCourses(start, end);
    }

    public int countAllCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.countAllCourses();
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getContributors(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getContributors(coursePrimaryKey);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseImages(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseImages(coursePrimaryKey);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseRelateds(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseRelateds(coursePrimaryKey);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseRequirements(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseRequirements(coursePrimaryKey);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourseReviews(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getGlobalCourseReviews(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourses_Componentses(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCourses_Componentses(coursePrimaryKey);
    }

    public com.liferay.portlet.asset.model.AssetCategory findAssetCategoryByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portlet.asset.NoSuchCategoryException {
        return _courseLocalService.findAssetCategoryByG_N(groupId, name);
    }

    public java.util.List<com.liferay.portlet.asset.model.AssetEntry> findAllAssetEntries()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllAssetEntries();
    }

    public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getCategoryAssetEntries(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getCategoryAssetEntries(categoryId);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getExternalLinks(course);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.getExternalLinks(courseId);
    }

    public java.lang.Boolean containsCategoryAssetEntries(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.containsCategoryAssetEntries(categoryId);
    }

    public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> findAllAssetVocabularies()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.findAllAssetVocabularies();
    }

    public com.liferay.portlet.asset.model.AssetVocabulary fetchVocabularyByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.fetchVocabularyByG_N(groupId, name);
    }

    public com.liferay.portal.kernel.search.Hits search(long companyId,
        java.lang.String keywords, boolean andSearch, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.search(companyId, keywords, andSearch,
            start, end);
    }

    public com.liferay.portal.kernel.search.Hits search(long companyId,
        long groupId, java.lang.String keywords, boolean andSearch, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _courseLocalService.search(companyId, groupId, keywords,
            andSearch, start, end);
    }

    public void assignAllAccessCounts()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.assignAllAccessCounts();
    }

    public void assignAllCompletedCounts()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.assignAllCompletedCounts();
    }

    /**
    * Assigns the ranking score tall all courses based on a "popularity" weighting
    * system. This system takes into account the access count, completed count,
    * and user rating according to a Bayesian Average rating system.
    */
    public void assignAllPopularWeights(double accessCountWeight,
        double completedCountWeight, double scoreWeight)
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseLocalService.assignAllPopularWeights(accessCountWeight,
            completedCountWeight, scoreWeight);
    }

    /**
    * Clears the cache for all courses stored in this session.  This should
    * only be needed in a multi-threaded environment, where a thread is not
    * notified of persistence updates done in a different thread.
    */
    public void clearCache() {
        _courseLocalService.clearCache();
    }

    /**
    * Clears the cache of a given course in this session.  This should only be
    * needed in a multi-threaded environment, where a thread is not notified of
    * persistence updates done in a different thread.
    *
    * @param course Course to remove from cache
    */
    public void clearCache(
        org.nterlearning.datamodel.catalog.model.Course course) {
        _courseLocalService.clearCache(course);
    }

    /**
    * Clears the cache of a given course in this session.  This should only be
    * needed in a multi-threaded environment, where a thread is not notified of
    * persistence updates done in a different thread.
    *
    * @param courseId Id of the course to remove from the cache
    */
    public void clearCache(java.lang.Long courseId) {
        _courseLocalService.clearCache(courseId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public CourseLocalService getWrappedCourseLocalService() {
        return _courseLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedCourseLocalService(
        CourseLocalService courseLocalService) {
        _courseLocalService = courseLocalService;
    }

    public CourseLocalService getWrappedService() {
        return _courseLocalService;
    }

    public void setWrappedService(CourseLocalService courseLocalService) {
        _courseLocalService = courseLocalService;
    }
}
