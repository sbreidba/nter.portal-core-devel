package org.nterlearning.datamodel.catalog.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.base.PrincipalBean;
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

import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.ComponentLocalService;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalService;
import org.nterlearning.datamodel.catalog.service.ComponentRecordService;
import org.nterlearning.datamodel.catalog.service.ContributorLocalService;
import org.nterlearning.datamodel.catalog.service.CourseImageLocalService;
import org.nterlearning.datamodel.catalog.service.CourseLocalService;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalService;
import org.nterlearning.datamodel.catalog.service.CourseRecordService;
import org.nterlearning.datamodel.catalog.service.CourseRelatedLocalService;
import org.nterlearning.datamodel.catalog.service.CourseRequirementLocalService;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalService;
import org.nterlearning.datamodel.catalog.service.CourseReviewService;
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

import javax.sql.DataSource;

/**
 * The base implementation of the course review remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.nterlearning.datamodel.catalog.service.impl.CourseReviewServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseReviewServiceImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil
 * @generated
 */
public abstract class CourseReviewServiceBaseImpl extends PrincipalBean
    implements CourseReviewService, IdentifiableBean {
    @BeanReference(type = ComponentLocalService.class)
    protected ComponentLocalService componentLocalService;
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
     * Never modify or reference this class directly. Always use {@link org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil} to access the course review remote service.
     */

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
    }

    public void destroy() {
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
        return CourseReview.class;
    }

    protected String getModelClassName() {
        return CourseReview.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = courseReviewPersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
