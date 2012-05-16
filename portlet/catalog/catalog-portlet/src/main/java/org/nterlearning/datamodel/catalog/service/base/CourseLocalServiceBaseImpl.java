package org.nterlearning.datamodel.catalog.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.OrganizationService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.asset.service.AssetEntryLocalService;
import com.liferay.portlet.asset.service.AssetEntryService;
import com.liferay.portlet.asset.service.AssetTagLocalService;
import com.liferay.portlet.asset.service.AssetTagService;
import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;
import com.liferay.portlet.ratings.service.RatingsStatsLocalService;
import com.liferay.portlet.ratings.service.persistence.RatingsStatsPersistence;

import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.ComponentLocalService;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalService;
import org.nterlearning.datamodel.catalog.service.ComponentRecordService;
import org.nterlearning.datamodel.catalog.service.ComponentService;
import org.nterlearning.datamodel.catalog.service.ContributorLocalService;
import org.nterlearning.datamodel.catalog.service.CourseImageLocalService;
import org.nterlearning.datamodel.catalog.service.CourseLocalService;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalService;
import org.nterlearning.datamodel.catalog.service.CourseRecordService;
import org.nterlearning.datamodel.catalog.service.CourseRelatedLocalService;
import org.nterlearning.datamodel.catalog.service.CourseRequirementLocalService;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalService;
import org.nterlearning.datamodel.catalog.service.CourseReviewService;
import org.nterlearning.datamodel.catalog.service.CourseService;
import org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalService;
import org.nterlearning.datamodel.catalog.service.ExternalLinkLocalService;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalService;
import org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalService;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalService;
import org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalService;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalService;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewService;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentFinder;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentRecordFinder;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentRecordPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.ContributorPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseFinder;
import org.nterlearning.datamodel.catalog.service.persistence.CourseImagePersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CoursePersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRecordFinder;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRecordPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRelatedPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRequirementPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseReviewFinder;
import org.nterlearning.datamodel.catalog.service.persistence.CourseReviewPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.Courses_ComponentsPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.ExternalLinkPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FeedReferencePersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FeedSyncHistoryPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FlagReportFinder;
import org.nterlearning.datamodel.catalog.service.persistence.FlagReportPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FlagReportStatsPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewFinder;
import org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the course local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseLocalServiceImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil
 * @generated
 */
public abstract class CourseLocalServiceBaseImpl implements CourseLocalService,
    IdentifiableBean {
    private static Log _log = LogFactoryUtil.getLog(CourseLocalServiceBaseImpl.class);
    @BeanReference(type = ComponentLocalService.class)
    protected ComponentLocalService componentLocalService;
    @BeanReference(type = ComponentService.class)
    protected ComponentService componentService;
    @BeanReference(type = ComponentPersistence.class)
    protected ComponentPersistence componentPersistence;
    @BeanReference(type = ComponentFinder.class)
    protected ComponentFinder componentFinder;
    @BeanReference(type = ComponentRecordLocalService.class)
    protected ComponentRecordLocalService componentRecordLocalService;
    @BeanReference(type = ComponentRecordService.class)
    protected ComponentRecordService componentRecordService;
    @BeanReference(type = ComponentRecordPersistence.class)
    protected ComponentRecordPersistence componentRecordPersistence;
    @BeanReference(type = ComponentRecordFinder.class)
    protected ComponentRecordFinder componentRecordFinder;
    @BeanReference(type = ContributorLocalService.class)
    protected ContributorLocalService contributorLocalService;
    @BeanReference(type = ContributorPersistence.class)
    protected ContributorPersistence contributorPersistence;
    @BeanReference(type = CourseLocalService.class)
    protected CourseLocalService courseLocalService;
    @BeanReference(type = CourseService.class)
    protected CourseService courseService;
    @BeanReference(type = CoursePersistence.class)
    protected CoursePersistence coursePersistence;
    @BeanReference(type = CourseFinder.class)
    protected CourseFinder courseFinder;
    @BeanReference(type = CourseImageLocalService.class)
    protected CourseImageLocalService courseImageLocalService;
    @BeanReference(type = CourseImagePersistence.class)
    protected CourseImagePersistence courseImagePersistence;
    @BeanReference(type = CourseRecordLocalService.class)
    protected CourseRecordLocalService courseRecordLocalService;
    @BeanReference(type = CourseRecordService.class)
    protected CourseRecordService courseRecordService;
    @BeanReference(type = CourseRecordPersistence.class)
    protected CourseRecordPersistence courseRecordPersistence;
    @BeanReference(type = CourseRecordFinder.class)
    protected CourseRecordFinder courseRecordFinder;
    @BeanReference(type = CourseRelatedLocalService.class)
    protected CourseRelatedLocalService courseRelatedLocalService;
    @BeanReference(type = CourseRelatedPersistence.class)
    protected CourseRelatedPersistence courseRelatedPersistence;
    @BeanReference(type = CourseRequirementLocalService.class)
    protected CourseRequirementLocalService courseRequirementLocalService;
    @BeanReference(type = CourseRequirementPersistence.class)
    protected CourseRequirementPersistence courseRequirementPersistence;
    @BeanReference(type = CourseReviewLocalService.class)
    protected CourseReviewLocalService courseReviewLocalService;
    @BeanReference(type = CourseReviewService.class)
    protected CourseReviewService courseReviewService;
    @BeanReference(type = CourseReviewPersistence.class)
    protected CourseReviewPersistence courseReviewPersistence;
    @BeanReference(type = CourseReviewFinder.class)
    protected CourseReviewFinder courseReviewFinder;
    @BeanReference(type = Courses_ComponentsLocalService.class)
    protected Courses_ComponentsLocalService courses_ComponentsLocalService;
    @BeanReference(type = Courses_ComponentsPersistence.class)
    protected Courses_ComponentsPersistence courses_ComponentsPersistence;
    @BeanReference(type = ExternalLinkLocalService.class)
    protected ExternalLinkLocalService externalLinkLocalService;
    @BeanReference(type = ExternalLinkPersistence.class)
    protected ExternalLinkPersistence externalLinkPersistence;
    @BeanReference(type = FeedReferenceLocalService.class)
    protected FeedReferenceLocalService feedReferenceLocalService;
    @BeanReference(type = FeedReferencePersistence.class)
    protected FeedReferencePersistence feedReferencePersistence;
    @BeanReference(type = FeedSyncHistoryLocalService.class)
    protected FeedSyncHistoryLocalService feedSyncHistoryLocalService;
    @BeanReference(type = FeedSyncHistoryPersistence.class)
    protected FeedSyncHistoryPersistence feedSyncHistoryPersistence;
    @BeanReference(type = FlagReportLocalService.class)
    protected FlagReportLocalService flagReportLocalService;
    @BeanReference(type = FlagReportPersistence.class)
    protected FlagReportPersistence flagReportPersistence;
    @BeanReference(type = FlagReportFinder.class)
    protected FlagReportFinder flagReportFinder;
    @BeanReference(type = FlagReportStatsLocalService.class)
    protected FlagReportStatsLocalService flagReportStatsLocalService;
    @BeanReference(type = FlagReportStatsPersistence.class)
    protected FlagReportStatsPersistence flagReportStatsPersistence;
    @BeanReference(type = GlobalCourseReviewLocalService.class)
    protected GlobalCourseReviewLocalService globalCourseReviewLocalService;
    @BeanReference(type = GlobalCourseReviewService.class)
    protected GlobalCourseReviewService globalCourseReviewService;
    @BeanReference(type = GlobalCourseReviewPersistence.class)
    protected GlobalCourseReviewPersistence globalCourseReviewPersistence;
    @BeanReference(type = GlobalCourseReviewFinder.class)
    protected GlobalCourseReviewFinder globalCourseReviewFinder;
    @BeanReference(type = CounterLocalService.class)
    protected CounterLocalService counterLocalService;
    @BeanReference(type = CompanyLocalService.class)
    protected CompanyLocalService companyLocalService;
    @BeanReference(type = CompanyService.class)
    protected CompanyService companyService;
    @BeanReference(type = CompanyPersistence.class)
    protected CompanyPersistence companyPersistence;
    @BeanReference(type = GroupLocalService.class)
    protected GroupLocalService groupLocalService;
    @BeanReference(type = GroupService.class)
    protected GroupService groupService;
    @BeanReference(type = GroupPersistence.class)
    protected GroupPersistence groupPersistence;
    @BeanReference(type = OrganizationLocalService.class)
    protected OrganizationLocalService organizationLocalService;
    @BeanReference(type = OrganizationService.class)
    protected OrganizationService organizationService;
    @BeanReference(type = OrganizationPersistence.class)
    protected OrganizationPersistence organizationPersistence;
    @BeanReference(type = ResourceLocalService.class)
    protected ResourceLocalService resourceLocalService;
    @BeanReference(type = ResourceService.class)
    protected ResourceService resourceService;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserLocalService.class)
    protected UserLocalService userLocalService;
    @BeanReference(type = UserService.class)
    protected UserService userService;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    @BeanReference(type = AssetEntryLocalService.class)
    protected AssetEntryLocalService assetEntryLocalService;
    @BeanReference(type = AssetEntryService.class)
    protected AssetEntryService assetEntryService;
    @BeanReference(type = AssetEntryPersistence.class)
    protected AssetEntryPersistence assetEntryPersistence;
    @BeanReference(type = AssetTagLocalService.class)
    protected AssetTagLocalService assetTagLocalService;
    @BeanReference(type = AssetTagService.class)
    protected AssetTagService assetTagService;
    @BeanReference(type = AssetTagPersistence.class)
    protected AssetTagPersistence assetTagPersistence;
    @BeanReference(type = RatingsStatsLocalService.class)
    protected RatingsStatsLocalService ratingsStatsLocalService;
    @BeanReference(type = RatingsStatsPersistence.class)
    protected RatingsStatsPersistence ratingsStatsPersistence;
    private String _beanIdentifier;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil} to access the course local service.
     */

    /**
     * Adds the course to the database. Also notifies the appropriate model listeners.
     *
     * @param course the course
     * @return the course that was added
     * @throws SystemException if a system exception occurred
     */
    public Course addCourse(Course course) throws SystemException {
        course.setNew(true);

        course = coursePersistence.update(course, false);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.reindex(course);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }

        return course;
    }

    /**
     * Creates a new course with the primary key. Does not add the course to the database.
     *
     * @param courseId the primary key for the new course
     * @return the new course
     */
    public Course createCourse(long courseId) {
        return coursePersistence.create(courseId);
    }

    /**
     * Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseId the primary key of the course
     * @throws PortalException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public void deleteCourse(long courseId)
        throws PortalException, SystemException {
        Course course = coursePersistence.remove(courseId);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.delete(course);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }
    }

    /**
     * Deletes the course from the database. Also notifies the appropriate model listeners.
     *
     * @param course the course
     * @throws PortalException
     * @throws SystemException if a system exception occurred
     */
    public void deleteCourse(Course course)
        throws PortalException, SystemException {
        coursePersistence.remove(course);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.delete(course);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     * @throws SystemException if a system exception occurred
     */
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return coursePersistence.findWithDynamicQuery(dynamicQuery);
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
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return coursePersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return coursePersistence.findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */
    public long dynamicQueryCount(DynamicQuery dynamicQuery)
        throws SystemException {
        return coursePersistence.countWithDynamicQuery(dynamicQuery);
    }

    public Course fetchCourse(long courseId) throws SystemException {
        return coursePersistence.fetchByPrimaryKey(courseId);
    }

    /**
     * Returns the course with the primary key.
     *
     * @param courseId the primary key of the course
     * @return the course
     * @throws PortalException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course getCourse(long courseId)
        throws PortalException, SystemException {
        return coursePersistence.findByPrimaryKey(courseId);
    }

    public PersistedModel getPersistedModel(Serializable primaryKeyObj)
        throws PortalException, SystemException {
        return coursePersistence.findByPrimaryKey(primaryKeyObj);
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
    public List<Course> getCourses(int start, int end)
        throws SystemException {
        return coursePersistence.findAll(start, end);
    }

    /**
     * Returns the number of courses.
     *
     * @return the number of courses
     * @throws SystemException if a system exception occurred
     */
    public int getCoursesCount() throws SystemException {
        return coursePersistence.countAll();
    }

    /**
     * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param course the course
     * @return the course that was updated
     * @throws SystemException if a system exception occurred
     */
    public Course updateCourse(Course course) throws SystemException {
        return updateCourse(course, true);
    }

    /**
     * Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param course the course
     * @param merge whether to merge the course with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the course that was updated
     * @throws SystemException if a system exception occurred
     */
    public Course updateCourse(Course course, boolean merge)
        throws SystemException {
        course.setNew(false);

        course = coursePersistence.update(course, merge);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.reindex(course);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }

        return course;
    }

    /**
     * Returns the component local service.
     *
     * @return the component local service
     */
    public ComponentLocalService getComponentLocalService() {
        return componentLocalService;
    }

    /**
     * Sets the component local service.
     *
     * @param componentLocalService the component local service
     */
    public void setComponentLocalService(
        ComponentLocalService componentLocalService) {
        this.componentLocalService = componentLocalService;
    }

    /**
     * Returns the component remote service.
     *
     * @return the component remote service
     */
    public ComponentService getComponentService() {
        return componentService;
    }

    /**
     * Sets the component remote service.
     *
     * @param componentService the component remote service
     */
    public void setComponentService(ComponentService componentService) {
        this.componentService = componentService;
    }

    /**
     * Returns the component persistence.
     *
     * @return the component persistence
     */
    public ComponentPersistence getComponentPersistence() {
        return componentPersistence;
    }

    /**
     * Sets the component persistence.
     *
     * @param componentPersistence the component persistence
     */
    public void setComponentPersistence(
        ComponentPersistence componentPersistence) {
        this.componentPersistence = componentPersistence;
    }

    /**
     * Returns the component finder.
     *
     * @return the component finder
     */
    public ComponentFinder getComponentFinder() {
        return componentFinder;
    }

    /**
     * Sets the component finder.
     *
     * @param componentFinder the component finder
     */
    public void setComponentFinder(ComponentFinder componentFinder) {
        this.componentFinder = componentFinder;
    }

    /**
     * Returns the component record local service.
     *
     * @return the component record local service
     */
    public ComponentRecordLocalService getComponentRecordLocalService() {
        return componentRecordLocalService;
    }

    /**
     * Sets the component record local service.
     *
     * @param componentRecordLocalService the component record local service
     */
    public void setComponentRecordLocalService(
        ComponentRecordLocalService componentRecordLocalService) {
        this.componentRecordLocalService = componentRecordLocalService;
    }

    /**
     * Returns the component record remote service.
     *
     * @return the component record remote service
     */
    public ComponentRecordService getComponentRecordService() {
        return componentRecordService;
    }

    /**
     * Sets the component record remote service.
     *
     * @param componentRecordService the component record remote service
     */
    public void setComponentRecordService(
        ComponentRecordService componentRecordService) {
        this.componentRecordService = componentRecordService;
    }

    /**
     * Returns the component record persistence.
     *
     * @return the component record persistence
     */
    public ComponentRecordPersistence getComponentRecordPersistence() {
        return componentRecordPersistence;
    }

    /**
     * Sets the component record persistence.
     *
     * @param componentRecordPersistence the component record persistence
     */
    public void setComponentRecordPersistence(
        ComponentRecordPersistence componentRecordPersistence) {
        this.componentRecordPersistence = componentRecordPersistence;
    }

    /**
     * Returns the component record finder.
     *
     * @return the component record finder
     */
    public ComponentRecordFinder getComponentRecordFinder() {
        return componentRecordFinder;
    }

    /**
     * Sets the component record finder.
     *
     * @param componentRecordFinder the component record finder
     */
    public void setComponentRecordFinder(
        ComponentRecordFinder componentRecordFinder) {
        this.componentRecordFinder = componentRecordFinder;
    }

    /**
     * Returns the contributor local service.
     *
     * @return the contributor local service
     */
    public ContributorLocalService getContributorLocalService() {
        return contributorLocalService;
    }

    /**
     * Sets the contributor local service.
     *
     * @param contributorLocalService the contributor local service
     */
    public void setContributorLocalService(
        ContributorLocalService contributorLocalService) {
        this.contributorLocalService = contributorLocalService;
    }

    /**
     * Returns the contributor persistence.
     *
     * @return the contributor persistence
     */
    public ContributorPersistence getContributorPersistence() {
        return contributorPersistence;
    }

    /**
     * Sets the contributor persistence.
     *
     * @param contributorPersistence the contributor persistence
     */
    public void setContributorPersistence(
        ContributorPersistence contributorPersistence) {
        this.contributorPersistence = contributorPersistence;
    }

    /**
     * Returns the course local service.
     *
     * @return the course local service
     */
    public CourseLocalService getCourseLocalService() {
        return courseLocalService;
    }

    /**
     * Sets the course local service.
     *
     * @param courseLocalService the course local service
     */
    public void setCourseLocalService(CourseLocalService courseLocalService) {
        this.courseLocalService = courseLocalService;
    }

    /**
     * Returns the course remote service.
     *
     * @return the course remote service
     */
    public CourseService getCourseService() {
        return courseService;
    }

    /**
     * Sets the course remote service.
     *
     * @param courseService the course remote service
     */
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Returns the course persistence.
     *
     * @return the course persistence
     */
    public CoursePersistence getCoursePersistence() {
        return coursePersistence;
    }

    /**
     * Sets the course persistence.
     *
     * @param coursePersistence the course persistence
     */
    public void setCoursePersistence(CoursePersistence coursePersistence) {
        this.coursePersistence = coursePersistence;
    }

    /**
     * Returns the course finder.
     *
     * @return the course finder
     */
    public CourseFinder getCourseFinder() {
        return courseFinder;
    }

    /**
     * Sets the course finder.
     *
     * @param courseFinder the course finder
     */
    public void setCourseFinder(CourseFinder courseFinder) {
        this.courseFinder = courseFinder;
    }

    /**
     * Returns the course image local service.
     *
     * @return the course image local service
     */
    public CourseImageLocalService getCourseImageLocalService() {
        return courseImageLocalService;
    }

    /**
     * Sets the course image local service.
     *
     * @param courseImageLocalService the course image local service
     */
    public void setCourseImageLocalService(
        CourseImageLocalService courseImageLocalService) {
        this.courseImageLocalService = courseImageLocalService;
    }

    /**
     * Returns the course image persistence.
     *
     * @return the course image persistence
     */
    public CourseImagePersistence getCourseImagePersistence() {
        return courseImagePersistence;
    }

    /**
     * Sets the course image persistence.
     *
     * @param courseImagePersistence the course image persistence
     */
    public void setCourseImagePersistence(
        CourseImagePersistence courseImagePersistence) {
        this.courseImagePersistence = courseImagePersistence;
    }

    /**
     * Returns the course record local service.
     *
     * @return the course record local service
     */
    public CourseRecordLocalService getCourseRecordLocalService() {
        return courseRecordLocalService;
    }

    /**
     * Sets the course record local service.
     *
     * @param courseRecordLocalService the course record local service
     */
    public void setCourseRecordLocalService(
        CourseRecordLocalService courseRecordLocalService) {
        this.courseRecordLocalService = courseRecordLocalService;
    }

    /**
     * Returns the course record remote service.
     *
     * @return the course record remote service
     */
    public CourseRecordService getCourseRecordService() {
        return courseRecordService;
    }

    /**
     * Sets the course record remote service.
     *
     * @param courseRecordService the course record remote service
     */
    public void setCourseRecordService(CourseRecordService courseRecordService) {
        this.courseRecordService = courseRecordService;
    }

    /**
     * Returns the course record persistence.
     *
     * @return the course record persistence
     */
    public CourseRecordPersistence getCourseRecordPersistence() {
        return courseRecordPersistence;
    }

    /**
     * Sets the course record persistence.
     *
     * @param courseRecordPersistence the course record persistence
     */
    public void setCourseRecordPersistence(
        CourseRecordPersistence courseRecordPersistence) {
        this.courseRecordPersistence = courseRecordPersistence;
    }

    /**
     * Returns the course record finder.
     *
     * @return the course record finder
     */
    public CourseRecordFinder getCourseRecordFinder() {
        return courseRecordFinder;
    }

    /**
     * Sets the course record finder.
     *
     * @param courseRecordFinder the course record finder
     */
    public void setCourseRecordFinder(CourseRecordFinder courseRecordFinder) {
        this.courseRecordFinder = courseRecordFinder;
    }

    /**
     * Returns the course related local service.
     *
     * @return the course related local service
     */
    public CourseRelatedLocalService getCourseRelatedLocalService() {
        return courseRelatedLocalService;
    }

    /**
     * Sets the course related local service.
     *
     * @param courseRelatedLocalService the course related local service
     */
    public void setCourseRelatedLocalService(
        CourseRelatedLocalService courseRelatedLocalService) {
        this.courseRelatedLocalService = courseRelatedLocalService;
    }

    /**
     * Returns the course related persistence.
     *
     * @return the course related persistence
     */
    public CourseRelatedPersistence getCourseRelatedPersistence() {
        return courseRelatedPersistence;
    }

    /**
     * Sets the course related persistence.
     *
     * @param courseRelatedPersistence the course related persistence
     */
    public void setCourseRelatedPersistence(
        CourseRelatedPersistence courseRelatedPersistence) {
        this.courseRelatedPersistence = courseRelatedPersistence;
    }

    /**
     * Returns the course requirement local service.
     *
     * @return the course requirement local service
     */
    public CourseRequirementLocalService getCourseRequirementLocalService() {
        return courseRequirementLocalService;
    }

    /**
     * Sets the course requirement local service.
     *
     * @param courseRequirementLocalService the course requirement local service
     */
    public void setCourseRequirementLocalService(
        CourseRequirementLocalService courseRequirementLocalService) {
        this.courseRequirementLocalService = courseRequirementLocalService;
    }

    /**
     * Returns the course requirement persistence.
     *
     * @return the course requirement persistence
     */
    public CourseRequirementPersistence getCourseRequirementPersistence() {
        return courseRequirementPersistence;
    }

    /**
     * Sets the course requirement persistence.
     *
     * @param courseRequirementPersistence the course requirement persistence
     */
    public void setCourseRequirementPersistence(
        CourseRequirementPersistence courseRequirementPersistence) {
        this.courseRequirementPersistence = courseRequirementPersistence;
    }

    /**
     * Returns the course review local service.
     *
     * @return the course review local service
     */
    public CourseReviewLocalService getCourseReviewLocalService() {
        return courseReviewLocalService;
    }

    /**
     * Sets the course review local service.
     *
     * @param courseReviewLocalService the course review local service
     */
    public void setCourseReviewLocalService(
        CourseReviewLocalService courseReviewLocalService) {
        this.courseReviewLocalService = courseReviewLocalService;
    }

    /**
     * Returns the course review remote service.
     *
     * @return the course review remote service
     */
    public CourseReviewService getCourseReviewService() {
        return courseReviewService;
    }

    /**
     * Sets the course review remote service.
     *
     * @param courseReviewService the course review remote service
     */
    public void setCourseReviewService(CourseReviewService courseReviewService) {
        this.courseReviewService = courseReviewService;
    }

    /**
     * Returns the course review persistence.
     *
     * @return the course review persistence
     */
    public CourseReviewPersistence getCourseReviewPersistence() {
        return courseReviewPersistence;
    }

    /**
     * Sets the course review persistence.
     *
     * @param courseReviewPersistence the course review persistence
     */
    public void setCourseReviewPersistence(
        CourseReviewPersistence courseReviewPersistence) {
        this.courseReviewPersistence = courseReviewPersistence;
    }

    /**
     * Returns the course review finder.
     *
     * @return the course review finder
     */
    public CourseReviewFinder getCourseReviewFinder() {
        return courseReviewFinder;
    }

    /**
     * Sets the course review finder.
     *
     * @param courseReviewFinder the course review finder
     */
    public void setCourseReviewFinder(CourseReviewFinder courseReviewFinder) {
        this.courseReviewFinder = courseReviewFinder;
    }

    /**
     * Returns the courses_ components local service.
     *
     * @return the courses_ components local service
     */
    public Courses_ComponentsLocalService getCourses_ComponentsLocalService() {
        return courses_ComponentsLocalService;
    }

    /**
     * Sets the courses_ components local service.
     *
     * @param courses_ComponentsLocalService the courses_ components local service
     */
    public void setCourses_ComponentsLocalService(
        Courses_ComponentsLocalService courses_ComponentsLocalService) {
        this.courses_ComponentsLocalService = courses_ComponentsLocalService;
    }

    /**
     * Returns the courses_ components persistence.
     *
     * @return the courses_ components persistence
     */
    public Courses_ComponentsPersistence getCourses_ComponentsPersistence() {
        return courses_ComponentsPersistence;
    }

    /**
     * Sets the courses_ components persistence.
     *
     * @param courses_ComponentsPersistence the courses_ components persistence
     */
    public void setCourses_ComponentsPersistence(
        Courses_ComponentsPersistence courses_ComponentsPersistence) {
        this.courses_ComponentsPersistence = courses_ComponentsPersistence;
    }

    /**
     * Returns the external link local service.
     *
     * @return the external link local service
     */
    public ExternalLinkLocalService getExternalLinkLocalService() {
        return externalLinkLocalService;
    }

    /**
     * Sets the external link local service.
     *
     * @param externalLinkLocalService the external link local service
     */
    public void setExternalLinkLocalService(
        ExternalLinkLocalService externalLinkLocalService) {
        this.externalLinkLocalService = externalLinkLocalService;
    }

    /**
     * Returns the external link persistence.
     *
     * @return the external link persistence
     */
    public ExternalLinkPersistence getExternalLinkPersistence() {
        return externalLinkPersistence;
    }

    /**
     * Sets the external link persistence.
     *
     * @param externalLinkPersistence the external link persistence
     */
    public void setExternalLinkPersistence(
        ExternalLinkPersistence externalLinkPersistence) {
        this.externalLinkPersistence = externalLinkPersistence;
    }

    /**
     * Returns the feed reference local service.
     *
     * @return the feed reference local service
     */
    public FeedReferenceLocalService getFeedReferenceLocalService() {
        return feedReferenceLocalService;
    }

    /**
     * Sets the feed reference local service.
     *
     * @param feedReferenceLocalService the feed reference local service
     */
    public void setFeedReferenceLocalService(
        FeedReferenceLocalService feedReferenceLocalService) {
        this.feedReferenceLocalService = feedReferenceLocalService;
    }

    /**
     * Returns the feed reference persistence.
     *
     * @return the feed reference persistence
     */
    public FeedReferencePersistence getFeedReferencePersistence() {
        return feedReferencePersistence;
    }

    /**
     * Sets the feed reference persistence.
     *
     * @param feedReferencePersistence the feed reference persistence
     */
    public void setFeedReferencePersistence(
        FeedReferencePersistence feedReferencePersistence) {
        this.feedReferencePersistence = feedReferencePersistence;
    }

    /**
     * Returns the feed sync history local service.
     *
     * @return the feed sync history local service
     */
    public FeedSyncHistoryLocalService getFeedSyncHistoryLocalService() {
        return feedSyncHistoryLocalService;
    }

    /**
     * Sets the feed sync history local service.
     *
     * @param feedSyncHistoryLocalService the feed sync history local service
     */
    public void setFeedSyncHistoryLocalService(
        FeedSyncHistoryLocalService feedSyncHistoryLocalService) {
        this.feedSyncHistoryLocalService = feedSyncHistoryLocalService;
    }

    /**
     * Returns the feed sync history persistence.
     *
     * @return the feed sync history persistence
     */
    public FeedSyncHistoryPersistence getFeedSyncHistoryPersistence() {
        return feedSyncHistoryPersistence;
    }

    /**
     * Sets the feed sync history persistence.
     *
     * @param feedSyncHistoryPersistence the feed sync history persistence
     */
    public void setFeedSyncHistoryPersistence(
        FeedSyncHistoryPersistence feedSyncHistoryPersistence) {
        this.feedSyncHistoryPersistence = feedSyncHistoryPersistence;
    }

    /**
     * Returns the flag report local service.
     *
     * @return the flag report local service
     */
    public FlagReportLocalService getFlagReportLocalService() {
        return flagReportLocalService;
    }

    /**
     * Sets the flag report local service.
     *
     * @param flagReportLocalService the flag report local service
     */
    public void setFlagReportLocalService(
        FlagReportLocalService flagReportLocalService) {
        this.flagReportLocalService = flagReportLocalService;
    }

    /**
     * Returns the flag report persistence.
     *
     * @return the flag report persistence
     */
    public FlagReportPersistence getFlagReportPersistence() {
        return flagReportPersistence;
    }

    /**
     * Sets the flag report persistence.
     *
     * @param flagReportPersistence the flag report persistence
     */
    public void setFlagReportPersistence(
        FlagReportPersistence flagReportPersistence) {
        this.flagReportPersistence = flagReportPersistence;
    }

    /**
     * Returns the flag report finder.
     *
     * @return the flag report finder
     */
    public FlagReportFinder getFlagReportFinder() {
        return flagReportFinder;
    }

    /**
     * Sets the flag report finder.
     *
     * @param flagReportFinder the flag report finder
     */
    public void setFlagReportFinder(FlagReportFinder flagReportFinder) {
        this.flagReportFinder = flagReportFinder;
    }

    /**
     * Returns the flag report stats local service.
     *
     * @return the flag report stats local service
     */
    public FlagReportStatsLocalService getFlagReportStatsLocalService() {
        return flagReportStatsLocalService;
    }

    /**
     * Sets the flag report stats local service.
     *
     * @param flagReportStatsLocalService the flag report stats local service
     */
    public void setFlagReportStatsLocalService(
        FlagReportStatsLocalService flagReportStatsLocalService) {
        this.flagReportStatsLocalService = flagReportStatsLocalService;
    }

    /**
     * Returns the flag report stats persistence.
     *
     * @return the flag report stats persistence
     */
    public FlagReportStatsPersistence getFlagReportStatsPersistence() {
        return flagReportStatsPersistence;
    }

    /**
     * Sets the flag report stats persistence.
     *
     * @param flagReportStatsPersistence the flag report stats persistence
     */
    public void setFlagReportStatsPersistence(
        FlagReportStatsPersistence flagReportStatsPersistence) {
        this.flagReportStatsPersistence = flagReportStatsPersistence;
    }

    /**
     * Returns the global course review local service.
     *
     * @return the global course review local service
     */
    public GlobalCourseReviewLocalService getGlobalCourseReviewLocalService() {
        return globalCourseReviewLocalService;
    }

    /**
     * Sets the global course review local service.
     *
     * @param globalCourseReviewLocalService the global course review local service
     */
    public void setGlobalCourseReviewLocalService(
        GlobalCourseReviewLocalService globalCourseReviewLocalService) {
        this.globalCourseReviewLocalService = globalCourseReviewLocalService;
    }

    /**
     * Returns the global course review remote service.
     *
     * @return the global course review remote service
     */
    public GlobalCourseReviewService getGlobalCourseReviewService() {
        return globalCourseReviewService;
    }

    /**
     * Sets the global course review remote service.
     *
     * @param globalCourseReviewService the global course review remote service
     */
    public void setGlobalCourseReviewService(
        GlobalCourseReviewService globalCourseReviewService) {
        this.globalCourseReviewService = globalCourseReviewService;
    }

    /**
     * Returns the global course review persistence.
     *
     * @return the global course review persistence
     */
    public GlobalCourseReviewPersistence getGlobalCourseReviewPersistence() {
        return globalCourseReviewPersistence;
    }

    /**
     * Sets the global course review persistence.
     *
     * @param globalCourseReviewPersistence the global course review persistence
     */
    public void setGlobalCourseReviewPersistence(
        GlobalCourseReviewPersistence globalCourseReviewPersistence) {
        this.globalCourseReviewPersistence = globalCourseReviewPersistence;
    }

    /**
     * Returns the global course review finder.
     *
     * @return the global course review finder
     */
    public GlobalCourseReviewFinder getGlobalCourseReviewFinder() {
        return globalCourseReviewFinder;
    }

    /**
     * Sets the global course review finder.
     *
     * @param globalCourseReviewFinder the global course review finder
     */
    public void setGlobalCourseReviewFinder(
        GlobalCourseReviewFinder globalCourseReviewFinder) {
        this.globalCourseReviewFinder = globalCourseReviewFinder;
    }

    /**
     * Returns the counter local service.
     *
     * @return the counter local service
     */
    public CounterLocalService getCounterLocalService() {
        return counterLocalService;
    }

    /**
     * Sets the counter local service.
     *
     * @param counterLocalService the counter local service
     */
    public void setCounterLocalService(CounterLocalService counterLocalService) {
        this.counterLocalService = counterLocalService;
    }

    /**
     * Returns the company local service.
     *
     * @return the company local service
     */
    public CompanyLocalService getCompanyLocalService() {
        return companyLocalService;
    }

    /**
     * Sets the company local service.
     *
     * @param companyLocalService the company local service
     */
    public void setCompanyLocalService(CompanyLocalService companyLocalService) {
        this.companyLocalService = companyLocalService;
    }

    /**
     * Returns the company remote service.
     *
     * @return the company remote service
     */
    public CompanyService getCompanyService() {
        return companyService;
    }

    /**
     * Sets the company remote service.
     *
     * @param companyService the company remote service
     */
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Returns the company persistence.
     *
     * @return the company persistence
     */
    public CompanyPersistence getCompanyPersistence() {
        return companyPersistence;
    }

    /**
     * Sets the company persistence.
     *
     * @param companyPersistence the company persistence
     */
    public void setCompanyPersistence(CompanyPersistence companyPersistence) {
        this.companyPersistence = companyPersistence;
    }

    /**
     * Returns the group local service.
     *
     * @return the group local service
     */
    public GroupLocalService getGroupLocalService() {
        return groupLocalService;
    }

    /**
     * Sets the group local service.
     *
     * @param groupLocalService the group local service
     */
    public void setGroupLocalService(GroupLocalService groupLocalService) {
        this.groupLocalService = groupLocalService;
    }

    /**
     * Returns the group remote service.
     *
     * @return the group remote service
     */
    public GroupService getGroupService() {
        return groupService;
    }

    /**
     * Sets the group remote service.
     *
     * @param groupService the group remote service
     */
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Returns the group persistence.
     *
     * @return the group persistence
     */
    public GroupPersistence getGroupPersistence() {
        return groupPersistence;
    }

    /**
     * Sets the group persistence.
     *
     * @param groupPersistence the group persistence
     */
    public void setGroupPersistence(GroupPersistence groupPersistence) {
        this.groupPersistence = groupPersistence;
    }

    /**
     * Returns the organization local service.
     *
     * @return the organization local service
     */
    public OrganizationLocalService getOrganizationLocalService() {
        return organizationLocalService;
    }

    /**
     * Sets the organization local service.
     *
     * @param organizationLocalService the organization local service
     */
    public void setOrganizationLocalService(
        OrganizationLocalService organizationLocalService) {
        this.organizationLocalService = organizationLocalService;
    }

    /**
     * Returns the organization remote service.
     *
     * @return the organization remote service
     */
    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    /**
     * Sets the organization remote service.
     *
     * @param organizationService the organization remote service
     */
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Returns the organization persistence.
     *
     * @return the organization persistence
     */
    public OrganizationPersistence getOrganizationPersistence() {
        return organizationPersistence;
    }

    /**
     * Sets the organization persistence.
     *
     * @param organizationPersistence the organization persistence
     */
    public void setOrganizationPersistence(
        OrganizationPersistence organizationPersistence) {
        this.organizationPersistence = organizationPersistence;
    }

    /**
     * Returns the resource local service.
     *
     * @return the resource local service
     */
    public ResourceLocalService getResourceLocalService() {
        return resourceLocalService;
    }

    /**
     * Sets the resource local service.
     *
     * @param resourceLocalService the resource local service
     */
    public void setResourceLocalService(
        ResourceLocalService resourceLocalService) {
        this.resourceLocalService = resourceLocalService;
    }

    /**
     * Returns the resource remote service.
     *
     * @return the resource remote service
     */
    public ResourceService getResourceService() {
        return resourceService;
    }

    /**
     * Sets the resource remote service.
     *
     * @param resourceService the resource remote service
     */
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Returns the resource persistence.
     *
     * @return the resource persistence
     */
    public ResourcePersistence getResourcePersistence() {
        return resourcePersistence;
    }

    /**
     * Sets the resource persistence.
     *
     * @param resourcePersistence the resource persistence
     */
    public void setResourcePersistence(ResourcePersistence resourcePersistence) {
        this.resourcePersistence = resourcePersistence;
    }

    /**
     * Returns the user local service.
     *
     * @return the user local service
     */
    public UserLocalService getUserLocalService() {
        return userLocalService;
    }

    /**
     * Sets the user local service.
     *
     * @param userLocalService the user local service
     */
    public void setUserLocalService(UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    /**
     * Returns the user remote service.
     *
     * @return the user remote service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Sets the user remote service.
     *
     * @param userService the user remote service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the user persistence.
     *
     * @return the user persistence
     */
    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    /**
     * Sets the user persistence.
     *
     * @param userPersistence the user persistence
     */
    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    /**
     * Returns the asset entry local service.
     *
     * @return the asset entry local service
     */
    public AssetEntryLocalService getAssetEntryLocalService() {
        return assetEntryLocalService;
    }

    /**
     * Sets the asset entry local service.
     *
     * @param assetEntryLocalService the asset entry local service
     */
    public void setAssetEntryLocalService(
        AssetEntryLocalService assetEntryLocalService) {
        this.assetEntryLocalService = assetEntryLocalService;
    }

    /**
     * Returns the asset entry remote service.
     *
     * @return the asset entry remote service
     */
    public AssetEntryService getAssetEntryService() {
        return assetEntryService;
    }

    /**
     * Sets the asset entry remote service.
     *
     * @param assetEntryService the asset entry remote service
     */
    public void setAssetEntryService(AssetEntryService assetEntryService) {
        this.assetEntryService = assetEntryService;
    }

    /**
     * Returns the asset entry persistence.
     *
     * @return the asset entry persistence
     */
    public AssetEntryPersistence getAssetEntryPersistence() {
        return assetEntryPersistence;
    }

    /**
     * Sets the asset entry persistence.
     *
     * @param assetEntryPersistence the asset entry persistence
     */
    public void setAssetEntryPersistence(
        AssetEntryPersistence assetEntryPersistence) {
        this.assetEntryPersistence = assetEntryPersistence;
    }

    /**
     * Returns the asset tag local service.
     *
     * @return the asset tag local service
     */
    public AssetTagLocalService getAssetTagLocalService() {
        return assetTagLocalService;
    }

    /**
     * Sets the asset tag local service.
     *
     * @param assetTagLocalService the asset tag local service
     */
    public void setAssetTagLocalService(
        AssetTagLocalService assetTagLocalService) {
        this.assetTagLocalService = assetTagLocalService;
    }

    /**
     * Returns the asset tag remote service.
     *
     * @return the asset tag remote service
     */
    public AssetTagService getAssetTagService() {
        return assetTagService;
    }

    /**
     * Sets the asset tag remote service.
     *
     * @param assetTagService the asset tag remote service
     */
    public void setAssetTagService(AssetTagService assetTagService) {
        this.assetTagService = assetTagService;
    }

    /**
     * Returns the asset tag persistence.
     *
     * @return the asset tag persistence
     */
    public AssetTagPersistence getAssetTagPersistence() {
        return assetTagPersistence;
    }

    /**
     * Sets the asset tag persistence.
     *
     * @param assetTagPersistence the asset tag persistence
     */
    public void setAssetTagPersistence(AssetTagPersistence assetTagPersistence) {
        this.assetTagPersistence = assetTagPersistence;
    }

    /**
     * Returns the ratings stats local service.
     *
     * @return the ratings stats local service
     */
    public RatingsStatsLocalService getRatingsStatsLocalService() {
        return ratingsStatsLocalService;
    }

    /**
     * Sets the ratings stats local service.
     *
     * @param ratingsStatsLocalService the ratings stats local service
     */
    public void setRatingsStatsLocalService(
        RatingsStatsLocalService ratingsStatsLocalService) {
        this.ratingsStatsLocalService = ratingsStatsLocalService;
    }

    /**
     * Returns the ratings stats persistence.
     *
     * @return the ratings stats persistence
     */
    public RatingsStatsPersistence getRatingsStatsPersistence() {
        return ratingsStatsPersistence;
    }

    /**
     * Sets the ratings stats persistence.
     *
     * @param ratingsStatsPersistence the ratings stats persistence
     */
    public void setRatingsStatsPersistence(
        RatingsStatsPersistence ratingsStatsPersistence) {
        this.ratingsStatsPersistence = ratingsStatsPersistence;
    }

    public void afterPropertiesSet() {
        PersistedModelLocalServiceRegistryUtil.register("org.nterlearning.datamodel.catalog.model.Course",
            courseLocalService);
    }

    public void destroy() {
        PersistedModelLocalServiceRegistryUtil.unregister(
            "org.nterlearning.datamodel.catalog.model.Course");
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    public String getBeanIdentifier() {
        return _beanIdentifier;
    }

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
    public void setBeanIdentifier(String beanIdentifier) {
        _beanIdentifier = beanIdentifier;
    }

    protected Class<?> getModelClass() {
        return Course.class;
    }

    protected String getModelClassName() {
        return Course.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = coursePersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
