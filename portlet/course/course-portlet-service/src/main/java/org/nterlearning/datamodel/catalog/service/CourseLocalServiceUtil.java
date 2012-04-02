package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl
 * @generated
 */
public class CourseLocalServiceUtil {
    private static CourseLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the course to the database. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @return the course that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course addCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addCourse(course);
    }

    /**
    * Creates a new course with the primary key. Does not add the course to the database.
    *
    * @param courseId the primary key for the new course
    * @return the new course
    */
    public static org.nterlearning.datamodel.catalog.model.Course createCourse(
        long courseId) {
        return getService().createCourse(courseId);
    }

    /**
    * Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseId the primary key of the course
    * @throws PortalException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourse(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourse(courseId);
    }

    /**
    * Deletes the course from the database. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @throws PortalException
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourse(course);
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

    public static org.nterlearning.datamodel.catalog.model.Course fetchCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCourse(courseId);
    }

    /**
    * Returns the course with the primary key.
    *
    * @param courseId the primary key of the course
    * @return the course
    * @throws PortalException if a course with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course getCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourse(courseId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> getCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourses(start, end);
    }

    /**
    * Returns the number of courses.
    *
    * @return the number of courses
    * @throws SystemException if a system exception occurred
    */
    public static int getCoursesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCoursesCount();
    }

    /**
    * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @return the course that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourse(course);
    }

    /**
    * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param course the course
    * @param merge whether to merge the course with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourse(course, merge);
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

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllValidCourses();
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllValidCourses(start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        java.lang.String filterSQL, java.lang.String sortSQL, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findAllValidCourses(filterSQL, sortSQL, groupId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllValidCourses(category, groupId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        long vocabularyId, long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findAllValidCourses(vocabularyId, groupId, start, end);
    }

    public static long countAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countAllValidCourses();
    }

    public static long countAllValidCourses(java.lang.String filterSQL,
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countAllValidCourses(filterSQL, groupId);
    }

    public static long countAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countAllValidCourses(category, groupId);
    }

    public static long countAllValidCourses(long vocabularyId, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countAllValidCourses(vocabularyId, groupId);
    }

    public static void updateReviewHistogram(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().updateReviewHistogram(courseId);
    }

    public static org.nterlearning.datamodel.catalog.model.Course addCourse(
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
        return getService()
                   .addCourse(userId, feedReferenceId, href, courseIri,
            updatedDate, title, transcriptAbstract, description, keywords,
            copyright, ratingLevel, ratingReason, duration, durationStandard,
            featuredStatus, supersedesCourseIri, supersededByCourseIri,
            releaseOnDate, acceptUntilDate, version, versionDate, price,
            priceUnit, priceTerms, priceExpiration, serviceContext);
    }

    public static org.nterlearning.datamodel.catalog.model.Course addCourse(
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
        return getService()
                   .addCourse(userId, feedReferenceId, href, fullTextHref,
            courseIri, updatedDate, title, transcriptAbstract, description,
            keywords, copyright, ratingLevel, ratingReason, duration,
            durationStandard, featuredStatus, supersedesCourseIri,
            supersededByCourseIri, releaseOnDate, acceptUntilDate, version,
            versionDate, price, priceUnit, priceTerms, priceExpiration,
            serviceContext);
    }

    public static void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addCourseResources(course, addCommunityPermissions,
            addGuestPermissions);
    }

    public static void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addCourseResources(course, communityPermissions, guestPermissions);
    }

    public static void addCourseResources(long courseId,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addCourseResources(courseId, addCommunityPermissions,
            addGuestPermissions);
    }

    public static void addCourseResources(long courseId,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addCourseResources(courseId, communityPermissions, guestPermissions);
    }

    public static void markCourseRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        getService().markCourseRemoved(courseId, removed);
    }

    public static void setCourseFeaturedStatus(long courseId, double featured)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        getService().setCourseFeaturedStatus(courseId, featured);
    }

    public static void deleteCourses(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourses(groupId);
    }

    public static void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteAllChildren(course);
    }

    public static void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().updateAsset(userId, course, assetCategoryIds, assetTagNames);
    }

    public static void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames,
        boolean reIndex)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .updateAsset(userId, course, assetCategoryIds, assetTagNames,
            reIndex);
    }

    public static org.nterlearning.datamodel.catalog.model.Course updateCourseHistogram(
        long courseId, long oneStarsCount, long twoStarsCount,
        long threeStarsCount, long fourStarsCount, long fiveStarsCount)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateCourseHistogram(courseId, oneStarsCount,
            twoStarsCount, threeStarsCount, fourStarsCount, fiveStarsCount);
    }

    public static org.nterlearning.datamodel.catalog.model.Course updateCourse(
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
        return getService()
                   .updateCourse(userId, courseId, feedReferenceId, href,
            courseIri, updatedDate, title, transcriptAbstract, description,
            keywords, copyright, ratingLevel, ratingReason, duration,
            durationStandard, featuredStatus, popularWeight, accessCount,
            completedCount, removed, removedDate, supersedesCourseIri,
            supersededByCourseIri, releaseOnDate, acceptUntilDate, version,
            versionDate, price, priceUnit, priceTerms, priceExpiration,
            oneStarRateCount, twoStarRateCount, threeStarRateCount,
            fourStarRateCount, fiveStarRateCount, serviceContext);
    }

    public static org.nterlearning.datamodel.catalog.model.Course updateCourse(
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
        return getService()
                   .updateCourse(userId, courseId, feedReferenceId, href,
            fullTextHref, courseIri, updatedDate, title, transcriptAbstract,
            description, keywords, copyright, ratingLevel, ratingReason,
            duration, durationStandard, featuredStatus, popularWeight,
            accessCount, completedCount, removed, removedDate,
            supersedesCourseIri, supersededByCourseIri, releaseOnDate,
            acceptUntilDate, version, versionDate, price, priceUnit,
            priceTerms, priceExpiration, oneStarRateCount, twoStarRateCount,
            threeStarRateCount, fourStarRateCount, fiveStarRateCount,
            serviceContext);
    }

    public static void updateCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .updateCourseResources(course, communityPermissions,
            guestPermissions);
    }

    public static org.nterlearning.datamodel.catalog.model.Course updateStatus(
        long userId, long courseId, int status,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateStatus(userId, courseId, status, serviceContext);
    }

    public static int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countByGroupId(groupId);
    }

    public static org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getService().findByCourseId(courseId);
    }

    public static org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByCourseId(courseId);
    }

    public static org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        return getService().findByCourseIri(courseIri);
    }

    public static org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByGroupId(groupId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByGroupId(groupId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCompanyId(companyId, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByFeedReferenceId(feedReferenceId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findBySupersededByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findBySupersededByCourseIri(courseIri, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllCourses(start, end);
    }

    public static int countAllCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countAllCourses();
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributors(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContributors(coursePrimaryKey);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseImages(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseImages(coursePrimaryKey);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRelateds(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRelateds(coursePrimaryKey);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRequirements(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRequirements(coursePrimaryKey);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseReviews(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getGlobalCourseReviews(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourses_Componentses(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourses_Componentses(coursePrimaryKey);
    }

    public static com.liferay.portlet.asset.model.AssetCategory findAssetCategoryByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portlet.asset.NoSuchCategoryException {
        return getService().findAssetCategoryByG_N(groupId, name);
    }

    public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> findAllAssetEntries()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllAssetEntries();
    }

    public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> getCategoryAssetEntries(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCategoryAssetEntries(categoryId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLinks(course);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExternalLinks(courseId);
    }

    public static java.lang.Boolean containsCategoryAssetEntries(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().containsCategoryAssetEntries(categoryId);
    }

    public static java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> findAllAssetVocabularies()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findAllAssetVocabularies();
    }

    public static com.liferay.portlet.asset.model.AssetVocabulary fetchVocabularyByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchVocabularyByG_N(groupId, name);
    }

    public static com.liferay.portal.kernel.search.Hits search(long companyId,
        java.lang.String keywords, boolean andSearch, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().search(companyId, keywords, andSearch, start, end);
    }

    public static com.liferay.portal.kernel.search.Hits search(long companyId,
        long groupId, java.lang.String keywords, boolean andSearch, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .search(companyId, groupId, keywords, andSearch, start, end);
    }

    public static void assignAllAccessCounts()
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().assignAllAccessCounts();
    }

    public static void assignAllCompletedCounts()
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().assignAllCompletedCounts();
    }

    /**
    * Assigns the ranking score tall all courses based on a "popularity" weighting
    * system. This system takes into account the access count, completed count,
    * and user rating according to a Bayesian Average rating system.
    */
    public static void assignAllPopularWeights(double accessCountWeight,
        double completedCountWeight, double scoreWeight)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService()
            .assignAllPopularWeights(accessCountWeight, completedCountWeight,
            scoreWeight);
    }

    /**
    * Clears the cache for all courses stored in this session.  This should
    * only be needed in a multi-threaded environment, where a thread is not
    * notified of persistence updates done in a different thread.
    */
    public static void clearCache() {
        getService().clearCache();
    }

    /**
    * Clears the cache of a given course in this session.  This should only be
    * needed in a multi-threaded environment, where a thread is not notified of
    * persistence updates done in a different thread.
    *
    * @param course Course to remove from cache
    */
    public static void clearCache(
        org.nterlearning.datamodel.catalog.model.Course course) {
        getService().clearCache(course);
    }

    /**
    * Clears the cache of a given course in this session.  This should only be
    * needed in a multi-threaded environment, where a thread is not notified of
    * persistence updates done in a different thread.
    *
    * @param courseId Id of the course to remove from the cache
    */
    public static void clearCache(java.lang.Long courseId) {
        getService().clearCache(courseId);
    }

    public static void clearService() {
        _service = null;
    }

    public static CourseLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseLocalService.class.getName(), portletClassLoader);

            _service = new CourseLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseLocalServiceUtil.class,
                "_service");
            MethodCache.remove(CourseLocalService.class);
        }

        return _service;
    }

    public void setService(CourseLocalService service) {
        MethodCache.remove(CourseLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseLocalServiceUtil.class,
            "_service");
        MethodCache.remove(CourseLocalService.class);
    }
}
