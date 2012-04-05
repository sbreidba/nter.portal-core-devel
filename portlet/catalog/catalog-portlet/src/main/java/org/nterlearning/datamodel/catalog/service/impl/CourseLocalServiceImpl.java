/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.NoSuchCategoryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.persistence.AssetCategoryFinderUtil;
import com.liferay.portlet.asset.service.persistence.AssetCategoryUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryUtil;
import com.liferay.portlet.asset.service.persistence.AssetVocabularyUtil;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;
import org.nterlearning.course.enumerations.CourseSortType;
//import org.nterlearning.crawl.nutch.CrawlTool;
import org.nterlearning.datamodel.catalog.NoSuchCourseException;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.service.*;
import org.nterlearning.datamodel.catalog.service.base.CourseLocalServiceBaseImpl;
import org.nterlearning.datamodel.catalog.service.persistence.CourseFinderUtil;
import org.nterlearning.datamodel.catalog.service.persistence.CourseUtil;
//import org.nterlearning.utils.ReviewUtil;

import java.util.*;

/**
 * The implementation of the course local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.CourseLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.CourseLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil
 */
public class CourseLocalServiceImpl extends CourseLocalServiceBaseImpl {

    private static final String TAG_SEPARATOR_REGEX = "[\\s,;]+";
    private static final String defaultSortSQl = CourseSortType.NEW_POPULAR.getSortSql();


    public List<Course> findAllValidCourses()
            throws SystemException {
        return CourseFinderUtil.findAllValidCourses(null, defaultSortSQl, null,
                0, 0, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }


    public List<Course> findAllValidCourses(int start, int end)
            throws SystemException {
        return CourseFinderUtil.findAllValidCourses(null, defaultSortSQl, null,
                0, 0, start, end);
    }


    public List<Course> findAllValidCourses(String filterSQL, String sortSQL,
            long groupId, int start, int end)
            throws SystemException {
        return CourseFinderUtil.findAllValidCourses(filterSQL, sortSQL, null, 0,
                groupId, start, end);
    }


    public List<Course> findAllValidCourses(AssetCategory category, long groupId,
            int start, int end)
            throws SystemException {
        return CourseFinderUtil.findAllValidCourses(null, defaultSortSQl, category,
                0, groupId, start, end);
    }


    public List<Course> findAllValidCourses(long vocabularyId, long groupId,
            int start, int end)
            throws SystemException {
        return CourseFinderUtil.findAllValidCourses(null, defaultSortSQl, null,
                vocabularyId, groupId, start, end);
    }


    public long countAllValidCourses() throws SystemException {
        return CourseFinderUtil.countAllValidCourses(null, null, 0, 0);
    }


    public long countAllValidCourses(String filterSQL, long groupId)
            throws SystemException {
        return CourseFinderUtil.countAllValidCourses(filterSQL, null, 0, groupId);
    }


    public long countAllValidCourses(AssetCategory category, long groupId)
            throws SystemException {
        return CourseFinderUtil.countAllValidCourses(null, category, 0, groupId);
    }


    public long countAllValidCourses(long vocabularyId, long groupId)
            throws SystemException {
        return CourseFinderUtil.countAllValidCourses(null, null, vocabularyId, groupId);
    }


    @Override
    public void updateReviewHistogram(long courseId)
            throws SystemException, PortalException {

        if (_log.isDebugEnabled()) {
            _log.debug("Update histogram for course " + courseId);
        }
        int one = 0, two = 0, three = 0, four = 0, five = 0;
        List<Double> entries = CourseReviewLocalServiceUtil.findScoreByCourseId(courseId);
        if (_log.isDebugEnabled()) {
            _log.debug("  Found " + entries.size() + "review(s)");
        }
        for (double entry : entries) {
            switch ((int) entry) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                default:
                    break;
            }
        }
        if (_log.isDebugEnabled()) {
            _log.debug("\tHistogram is " + one + " " + two + " " + three + " "
                       + four + "" + " " + five);
        }
        updateCourseHistogram(courseId, one, two, three, four, five);
    }


    @Override
    public Course addCourse(Course course)
            throws SystemException {

        throw new IllegalArgumentException(
                "Use method signature with userId, course columns, and " +
                        "serviceContext when adding a new course.");
    }


    public Course addCourse(long userId, long feedReferenceId, String href,
            String courseIri, Date updatedDate, String title, String transcriptAbstract,
            String description, String keywords, String copyright, String ratingLevel,
            String ratingReason, String duration, String durationStandard,
            double featuredStatus, String supersedesCourseIri,
            String supersededByCourseIri, Date releaseOnDate, Date acceptUntilDate,
            String version, Date versionDate, double price, String priceUnit,
            String priceTerms, String priceExpiration, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // prior to feed 0.6.3, the href represented the full text href. to
        // ensure backwards compatibility, use the href for both the href and
        // the full text href.
        return addCourse(userId, feedReferenceId, href, href, courseIri,
                updatedDate, title, transcriptAbstract, description, keywords,
                copyright, ratingLevel, ratingReason, duration, durationStandard,
                featuredStatus, supersedesCourseIri, supersededByCourseIri,
                releaseOnDate, acceptUntilDate, version, versionDate, price,
                priceUnit, priceTerms, priceExpiration, serviceContext);
    }


    public Course addCourse(long userId, long feedReferenceId, String href,
            String fullTextHref, String courseIri, Date updatedDate, String title,
            String transcriptAbstract, String description, String keywords,
            String copyright, String ratingLevel, String ratingReason, String duration,
            String durationStandard, double featuredStatus, String supersedesCourseIri,
            String supersededByCourseIri, Date releaseOnDate, Date acceptUntilDate,
            String version, Date versionDate, double price, String priceUnit,
            String priceTerms, String priceExpiration, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // Course
        User user = userPersistence.findByPrimaryKey(userId);
        long groupId = feedReferencePersistence.fetchByPrimaryKey(feedReferenceId).getGroupId();
        long courseId = counterLocalService.increment(Course.class.getName());

        Course course = coursePersistence.create(courseId);
        course.setGroupId(groupId);
        course.setCompanyId(user.getCompanyId());
        course.setUserId(user.getUserId());
        course.setFeedReferenceId(feedReferenceId);
        course.setHref(href);
        course.setFullTextHref(fullTextHref);
        course.setCourseIri(courseIri);
        course.setUpdatedDate(updatedDate);
        course.setTitle(title);
        course.setTranscriptAbstract(transcriptAbstract);
        course.setDescription(description);
        course.setKeywords(keywords);
        course.setCopyright(copyright);
        course.setRatingLevel(ratingLevel);
        course.setRatingReason(ratingReason);
        course.setDuration(duration);
        course.setDurationStandard(durationStandard);
        course.setFeaturedStatus(featuredStatus);
        course.setPopularWeight(0);
        course.setAccessCount(0);
        course.setCompletedCount(0);
        course.setCreateDate(serviceContext.getCreateDate(new Date()));
        course.setRemoved(false);
        // do not set removedDate
        course.setSupersedesCourseIri(supersedesCourseIri);
        course.setSupersededByCourseIri(supersededByCourseIri);
        course.setReleaseOnDate(releaseOnDate);
        course.setAcceptUntilDate(acceptUntilDate);
        course.setVersion(version);
        course.setVersionDate(versionDate);
        course.setPrice(price);
        course.setPriceUnit(priceUnit);
        course.setPriceTerms(priceTerms);
        course.setPriceExpiration(priceExpiration);
        course.setOneStarRateCount(0);
        course.setTwoStarRateCount(0);
        course.setThreeStarRateCount(0);
        course.setFourStarRateCount(0);
        course.setFiveStarRateCount(0);

        coursePersistence.update(course, true);

        // Resources
        if (serviceContext.isAddGroupPermissions() ||
                serviceContext.isAddGuestPermissions()) {
            addCourseResources(course, serviceContext.isAddGroupPermissions(),
                    serviceContext.isAddGuestPermissions());
        }
        else {
            addCourseResources(course, serviceContext.getGroupPermissions(),
                    serviceContext.getGuestPermissions());
        }

        // Asset
        updateAsset(userId, serviceContext, course);

        // TODO Workflow - indexer should be merged into workflow in the future
        // WorkflowHandlerRegistryUtil.startWorkflowInstance(
        // user.getCompanyId(), groupId, userId, Course.class.getName(),
        // course.getCourseId(), course, serviceContext);
        // Indexer
//        try {
//            course.updateIndex();
//        }
//        catch (Exception e) {
//            _log.error("Could not index course: " + course.getCourseIri() +
//                    " due to: " + e.getMessage());
//        }

        return course;
    }


    private void updateAsset(long userId, ServiceContext serviceContext, Course course)
            throws PortalException, SystemException {

        // Get all language tag strings.
        String[] assetTags = course.getKeywordsMap().values().toArray(
                new String[course.getKeywordsMap().values().size()]);

        Set<String> set = new HashSet<String>();

        for (String assetTag : assetTags) {
            set.addAll(Arrays.asList(assetTag.split(TAG_SEPARATOR_REGEX)));
        }

        set.remove("");

        updateAsset(userId, course, serviceContext.getAssetCategoryIds(),
                set.toArray(new String[set.size()]), false);
    }


    public void addCourseResources(
            Course course, boolean addGroupPermissions,
            boolean addGuestPermissions)
            throws PortalException, SystemException {

        resourceLocalService.addResources(
                course.getCompanyId(), course.getGroupId(), course.getUserId(),
                Course.class.getName(), course.getCourseId(), false,
                addGroupPermissions, addGuestPermissions);
    }


    public void addCourseResources(
            Course course, String[] groupPermissions, String[] guestPermissions)
            throws PortalException, SystemException {

        try {
            resourceLocalService.addModelResources(
                    course.getCompanyId(), course.getGroupId(), course.getUserId(),
                    Course.class.getName(), course.getCourseId(),
                    groupPermissions, guestPermissions);
        }
        catch (PortalException e) {
            e.printStackTrace();
            throw e;
        }
        catch (SystemException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void addCourseResources(
            long courseId, boolean addGroupPermissions,
            boolean addGuestPermissions)
            throws PortalException, SystemException {

        Course course = coursePersistence.findByPrimaryKey(courseId);
        addCourseResources(course, addGroupPermissions, addGuestPermissions);
    }


    public void addCourseResources(
            long courseId, String[] groupPermissions, String[] guestPermissions)
            throws PortalException, SystemException {

        Course course = coursePersistence.findByPrimaryKey(courseId);
        addCourseResources(course, groupPermissions, guestPermissions);
    }


    public void markCourseRemoved(long courseId, boolean removed)
            throws SystemException, NoSuchCourseException, SearchException {

        Course course = coursePersistence.findByPrimaryKey(courseId);

        course.setRemoved(removed);
        if (removed) {
            course.setRemovedDate(new Date());
        }
        else {
            course.setRemovedDate(null);
        }

        coursePersistence.update(course, true);

//        course.updateIndex();
//        removeCourseFromIndex(course);
    }


    public void setCourseFeaturedStatus(long courseId, double featured)
            throws SystemException, NoSuchCourseException, SearchException {

        Course course = coursePersistence.findByPrimaryKey(courseId);

        course.setFeaturedStatus(featured);

        coursePersistence.update(course, true);

//        course.updateIndex();
    }


    public void deleteCourses(long groupId)
            throws PortalException, SystemException {

        for (Course course : coursePersistence.findByGroupId(groupId)) {
            deleteCourse(course);
        }
    }


    public void deleteCourse(Course course)
            throws PortalException, SystemException {

        deleteAllChildren(course);

        // TODO - once workflow is setup, the indexer delete may be moved
        // similar to blogs
        // Indexer - the course must be removed from indexer prior to removal
//        Indexer indexer = IndexerRegistryUtil.getIndexer(Course.class);
//        indexer.delete(course);
//        removeCourseFromIndex(course);

        // Course
        coursePersistence.remove(course);

        // Resources
        resourceLocalService.deleteResource(
                course.getCompanyId(), Course.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, course.getCourseId());

        // Asset
        assetEntryLocalService.deleteEntry(
                Course.class.getName(), course.getCourseId());

        // Ratings
        ratingsStatsLocalService.deleteStats(
                Course.class.getName(), course.getCourseId());

        // external links
        externalLinkPersistence.removeByCourseId(course.getCourseId());
    }


    public void deleteCourse(long courseId)
            throws PortalException, SystemException {

        Course course = coursePersistence.findByPrimaryKey(courseId);
        deleteCourse(course);
    }


    public void deleteAllChildren(Course course)
            throws PortalException, SystemException {

        for (CourseImage image : CourseLocalServiceUtil.getCourseImages(course)) {
            CourseImageLocalServiceUtil.deleteCourseImage(image);
        }

        for (CourseRelated related : CourseLocalServiceUtil.getCourseRelateds(course)) {
            CourseRelatedLocalServiceUtil.deleteCourseRelated(related);
        }

        for (CourseRequirement requirement : CourseLocalServiceUtil.getCourseRequirements(course)) {
            CourseRequirementLocalServiceUtil.deleteCourseRequirement(requirement);
        }

        for (Contributor contributor : CourseLocalServiceUtil.getContributors(course)) {
            ContributorLocalServiceUtil.deleteContributor(contributor);
        }

        for (Courses_Components courses_components :
                CourseLocalServiceUtil.getCourses_Componentses(course)) {
            Courses_ComponentsLocalServiceUtil.deleteCourses_Components(courses_components);
        }
    }


    public void updateAsset(long userId, Course course, long[] assetCategoryIds,
            String[] assetTagNames)
            throws PortalException, SystemException {

        updateAsset(userId, course, assetCategoryIds, assetTagNames, true);
    }


    public void updateAsset(long userId, Course course, long[] assetCategoryIds,
            String[] assetTagNames, boolean reIndex)
            throws PortalException, SystemException {

        assetEntryLocalService.updateEntry(
                userId, course.getGroupId(), Course.class.getName(),
                course.getCourseId(), null, 0, assetCategoryIds, assetTagNames, true,
                null, null, null, null, ContentTypes.TEXT_HTML, null, null, null, null,
                null, 0, 0, null, false);

        // Indexer
//        if (reIndex) {
//            course.updateIndex();
//        }
    }


    @Override
    public Course updateCourse(Course course)
            throws SystemException {

        throw new IllegalArgumentException(
                "Must pass userId, course columns, and serviceContext when updating an course.");
    }


    @Override
    public Course updateCourse(Course course, boolean merge)
            throws SystemException {

        // Indexer
//        course.updateIndex();
//
//        if (course.isRemoved()) {
//            removeCourseFromIndex(course);
//        }

        return super.updateCourse(course, merge);
    }


    public Course updateCourseHistogram(long courseId, long oneStarsCount,
            long twoStarsCount, long threeStarsCount, long fourStarsCount,
            long fiveStarsCount)
            throws PortalException, SystemException {

        Course course = coursePersistence.findByPrimaryKey(courseId);
        course.setOneStarRateCount(oneStarsCount);
        course.setTwoStarRateCount(twoStarsCount);
        course.setThreeStarRateCount(threeStarsCount);
        course.setFourStarRateCount(fourStarsCount);
        course.setFiveStarRateCount(fiveStarsCount);

        coursePersistence.update(course, true);

        return course;
    }


    public Course updateCourse(long userId, long courseId, long feedReferenceId,
            String href, String courseIri, Date updatedDate, String title,
            String transcriptAbstract, String description, String keywords,
            String copyright, String ratingLevel, String ratingReason,
            String duration, String durationStandard, double featuredStatus,
            double popularWeight, long accessCount, long completedCount,
            boolean removed, Date removedDate, String supersedesCourseIri,
            String supersededByCourseIri, Date releaseOnDate, Date acceptUntilDate,
            String version, Date versionDate, double price, String priceUnit,
            String priceTerms, String priceExpiration, long oneStarRateCount,
            long twoStarRateCount, long threeStarRateCount,long fourStarRateCount,
            long fiveStarRateCount, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // prior to feed 0.6.3, the href represented the full text href. to
        // ensure backwards compatibility, use the href for both the href and
        // the full text href.
        return updateCourse(userId, courseId, feedReferenceId, href, href,
                courseIri, updatedDate, title, transcriptAbstract, description,
                keywords, copyright, ratingLevel, ratingReason, duration,
                durationStandard, featuredStatus, popularWeight, accessCount,
                completedCount, removed, removedDate, supersedesCourseIri,
                supersededByCourseIri, releaseOnDate, acceptUntilDate, version,
                versionDate, price, priceUnit, priceTerms, priceExpiration,
                oneStarRateCount, twoStarRateCount, threeStarRateCount,
                fourStarRateCount, fiveStarRateCount, serviceContext);
    }


    public Course updateCourse(long userId, long courseId, long feedReferenceId,
            String href, String fullTextHref, String courseIri, Date updatedDate,
            String title, String transcriptAbstract, String description, String keywords,
            String copyright, String ratingLevel, String ratingReason, String duration,
            String durationStandard, double featuredStatus, double popularWeight,
            long accessCount, long completedCount, boolean removed,
            Date removedDate, String supersedesCourseIri,
            String supersededByCourseIri, Date releaseOnDate, Date acceptUntilDate,
            String version, Date versionDate, double price, String priceUnit,
            String priceTerms, String priceExpiration, long oneStarRateCount,
            long twoStarRateCount, long threeStarRateCount, long fourStarRateCount,
            long fiveStarRateCount, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // Course
        Course course = coursePersistence.findByPrimaryKey(courseId);

        course.setFeedReferenceId(feedReferenceId);
        course.setHref(href);
        course.setFullTextHref(fullTextHref);
        course.setCourseIri(courseIri);
        course.setUpdatedDate(updatedDate);
        course.setTitle(title);
        course.setTranscriptAbstract(transcriptAbstract);
        course.setDescription(description);
        course.setKeywords(keywords);
        course.setCopyright(copyright);
        course.setRatingLevel(ratingLevel);
        course.setRatingReason(ratingReason);
        course.setDuration(duration);
        course.setDurationStandard(durationStandard);
        course.setFeaturedStatus(featuredStatus);
        course.setPopularWeight(popularWeight);
        course.setAccessCount(accessCount);
        course.setCompletedCount(completedCount);
        course.setRemoved(removed);
        course.setRemovedDate(removedDate);
        course.setSupersedesCourseIri(supersedesCourseIri);
        course.setSupersededByCourseIri(supersededByCourseIri);
        course.setReleaseOnDate(releaseOnDate);
        course.setAcceptUntilDate(acceptUntilDate);
        course.setVersion(version);
        course.setVersionDate(versionDate);
        course.setPrice(price);
        course.setPriceUnit(priceUnit);
        course.setPriceTerms(priceTerms);
        course.setPriceExpiration(priceExpiration);
        course.setOneStarRateCount(oneStarRateCount);
        course.setTwoStarRateCount(twoStarRateCount);
        course.setThreeStarRateCount(threeStarRateCount);
        course.setFourStarRateCount(fourStarRateCount);
        course.setFiveStarRateCount(fiveStarRateCount);

        coursePersistence.update(course, true, serviceContext);

        // Resources
        if ((serviceContext.getGroupPermissions() != null) ||
                (serviceContext.getGuestPermissions() != null)) {
            updateCourseResources(
                    course, serviceContext.getGroupPermissions(),
                    serviceContext.getGuestPermissions());
        }

        updateAsset(userId, serviceContext, course);

        // Indexer
//        try {
//            course.updateIndex();
//
//            if (course.isRemoved()) {
//                removeCourseFromIndex(course);
//            }
//        }
//        catch (Exception e) {
//            _log.error("Could not reindex course: " + course.getCourseIri() +
//                    " due to: " + e.getMessage());
//        }

        return course;
    }


    public void updateCourseResources(
            Course course, String[] groupPermissions, String[] guestPermissions)
            throws PortalException, SystemException {

        resourceLocalService.updateResources(
                course.getCompanyId(), course.getGroupId(), Course.class.getName(),
                course.getCourseId(), groupPermissions, guestPermissions);
    }


    public Course updateStatus(
            long userId, long courseId, int status, ServiceContext serviceContext)
            throws PortalException, SystemException {

        Course course = coursePersistence.findByPrimaryKey(courseId);

        // Indexer
//        course.updateIndex();

        return course;
    }


    public int countByGroupId(long groupId)  throws SystemException {
        return coursePersistence.countByGroupId(groupId);
    }


    public Course findByCourseId(long courseId)
            throws NoSuchCourseException, SystemException {
        return coursePersistence.findByCourseId(courseId);
    }


    public Course fetchByCourseId(long courseId) throws SystemException {
        return coursePersistence.fetchByCourseId(courseId);
    }


    public Course findByCourseIri(String courseIri)
            throws SystemException, NoSuchCourseException {
        return coursePersistence.findByCourseIri(courseIri);
    }


    public Course fetchByCourseIri(String courseIri)
            throws SystemException {
        return coursePersistence.fetchByCourseIri(courseIri);
    }


    public List<Course> findByGroupId(long groupId)
            throws SystemException {
        return coursePersistence.findByGroupId(groupId);
    }


    public List<Course> findByGroupId(long groupId, int start, int end)
            throws SystemException {
        return coursePersistence.findByGroupId(groupId, start, end);
    }


    public List<Course> findByCompanyId(long companyId)
            throws SystemException {
        return coursePersistence.findByCompanyId(companyId);
    }


    public List<Course> findByCompanyId(long companyId, int start, int end)
            throws SystemException {
        return coursePersistence.findByCompanyId(companyId, start, end);
    }


    public List<Course> findByFeedReferenceId(long feedReferenceId)
            throws SystemException {
        return coursePersistence.findByFeedReferenceId(feedReferenceId);
    }


    public List<Course> findBySupersededByCourseIri(String courseIri)
            throws SystemException {
        return coursePersistence.findBySupersededByCourseIri(courseIri);
    }


    public List<Course> findBySupersededByCourseIri(String courseIri, int start, int end)
            throws SystemException {
        return coursePersistence.findBySupersededByCourseIri(courseIri, start, end);
    }


    public List<Course> findAllCourses(int start, int end)
            throws SystemException {
        return coursePersistence.findAll(start, end);
    }


    public int countAllCourses()  throws SystemException {
        return coursePersistence.countAll();
    }


    public List<Contributor> getContributors(Course course)
            throws SystemException {
        return coursePersistence.getContributors(course.getCourseId());
    }


    public List<Contributor> getContributors(long coursePrimaryKey)
            throws SystemException {
        return coursePersistence.getContributors(coursePrimaryKey);
    }


    public List<CourseImage> getCourseImages(Course course)
            throws SystemException {
        return coursePersistence.getCourseImages(course.getCourseId());
    }


    public List<CourseImage> getCourseImages(long coursePrimaryKey)
            throws SystemException {
        return coursePersistence.getCourseImages(coursePrimaryKey);
    }


    public List<CourseRelated> getCourseRelateds(Course course)
            throws SystemException {
        return coursePersistence.getCourseRelateds(course.getCourseId());
    }


    public List<CourseRelated> getCourseRelateds(long coursePrimaryKey)
            throws SystemException {
        return coursePersistence.getCourseRelateds(coursePrimaryKey);
    }


    public List<CourseRequirement> getCourseRequirements(Course course)
            throws SystemException {
        return coursePersistence.getCourseRequirements(course.getCourseId());
    }


    public List<CourseRequirement> getCourseRequirements(long coursePrimaryKey)
            throws SystemException {
        return coursePersistence.getCourseRequirements(coursePrimaryKey);
    }


    public List<CourseReview> getCourseReviews(Course course)
            throws SystemException {
        return coursePersistence.getCourseReviews(course.getCourseId());
    }


    public List<GlobalCourseReview> getGlobalCourseReviews(Course course)
            throws SystemException {
        return coursePersistence.getGlobalCourseReviews(course.getCourseId());
    }


    public List<Courses_Components> getCourses_Componentses(Course course)
            throws SystemException {
        return coursePersistence.getCourses_Componentses(course.getCourseId());
    }


    public List<Courses_Components> getCourses_Componentses(long coursePrimaryKey)
            throws SystemException {
        return coursePersistence.getCourses_Componentses(coursePrimaryKey);
    }


    public AssetCategory findAssetCategoryByG_N(long groupId, String name)
            throws SystemException, NoSuchCategoryException {
        return AssetCategoryFinderUtil.findByG_N(groupId, name);
    }


    public List<AssetEntry> findAllAssetEntries()
            throws SystemException {
        return AssetEntryUtil.findAll();
    }


    public List<AssetEntry> getCategoryAssetEntries(long categoryId)
            throws SystemException {
        return AssetCategoryUtil.getAssetEntries(categoryId);
    }


    public List<ExternalLink> getExternalLinks(Course course) throws SystemException {
        return coursePersistence.getExternalLinks(course.getCourseId());
    }


    public List<ExternalLink> getExternalLinks(long courseId) throws SystemException {
        return coursePersistence.getExternalLinks(courseId);
    }


    public Boolean containsCategoryAssetEntries(long categoryId)
            throws SystemException {
        return AssetCategoryUtil.containsAssetEntries(categoryId);
    }


    public List<AssetVocabulary> findAllAssetVocabularies()
            throws SystemException {
        return AssetVocabularyUtil.findAll();
    }


    public AssetVocabulary fetchVocabularyByG_N(long groupId, String name)
            throws SystemException {
        return AssetVocabularyUtil.fetchByG_N(groupId, name);
    }


    public Hits search(
            long companyId, String keywords, boolean andSearch, int start, int end)
            throws SystemException {

        SearchContext searchContext = new SearchContext();
        searchContext.setKeywords(keywords);
        searchContext.setAndSearch(andSearch);
        searchContext.setCompanyId(companyId);
        searchContext.setStart(start);
        searchContext.setEnd(end);
        return search(searchContext);
    }


    public Hits search(
            long companyId, long groupId, String keywords, boolean andSearch,
            int start, int end)
            throws SystemException {

        SearchContext searchContext = new SearchContext();
        searchContext.setKeywords(keywords);
        searchContext.setAndSearch(andSearch);
        searchContext.setCompanyId(companyId);
        searchContext.setStart(start);
        searchContext.setEnd(end);
        searchContext.setGroupIds(new long[]{groupId});
        return search(searchContext);
    }


    private Hits search(SearchContext searchContext)
            throws SystemException {

        Indexer indexer = IndexerRegistryUtil.getIndexer(Course.class);
        try {
            return indexer.search(searchContext);
        }
        catch (SearchException e) {
            throw new SystemException(e);
        }
    }


    public void assignAllAccessCounts()
            throws SystemException {

        long accessCount = 0;
        List<Course> courses = CourseLocalServiceUtil.getCourses(
                        QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Course course : courses) {
            if (Validator.isNotNull(course.getCourseIri())) {
                accessCount =
                        CourseRecordLocalServiceUtil.countAccessedByCourseIri(course.getCourseIri());
            }
            else {
                accessCount = 0;
            }

            _log.debug("Setting course id " + course.getCourseId() +
                    " accessCount value " + accessCount);
            course.setAccessCount(accessCount);
            coursePersistence.update(course, true);
        }
    }


    public void assignAllCompletedCounts()
            throws SystemException {

        long completedCount = 0;
        List<Course> courses = CourseLocalServiceUtil.getCourses(
                        QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Course course : courses) {
            if (Validator.isNotNull(course.getCourseIri())) {
                completedCount =
                        CourseRecordLocalServiceUtil.countCompletedByCourseIri(course.getCourseIri());
            }
            else {
                completedCount = 0;
            }

            if (_log.isDebugEnabled()) {
                _log.debug("Setting course id " + course.getCourseId() +
                        " completedCount value " + completedCount);
            }
            course.setCompletedCount(completedCount);
            coursePersistence.update(course, true);
        }
    }


    /**
     * Assigns the ranking score tall all courses based on a "popularity" weighting
     * system. This system takes into account the access count, completed count,
     * and user rating according to a Bayesian Average rating system.
     */
//    public void assignAllPopularWeights(double accessCountWeight,
//            double completedCountWeight, double scoreWeight)
//            throws SystemException {
//
//        double popularWeight = 0.0;
//        List<Course> courses =
//                CourseLocalServiceUtil.getCourses(
//                        QueryUtil.ALL_POS, QueryUtil.ALL_POS);
//        for (Course course : courses) {
//
//            RatingsStats stats =
//                    RatingsStatsLocalServiceUtil.getStats(
//                            Course.class.getName(), course.getCourseId());
//
//            popularWeight =
//                    (accessCountWeight * course.getAccessCount()) +
//                            (completedCountWeight * course.getCompletedCount()) +
//                            (ReviewUtil.bayesianAverage(
//                                    stats.getAverageScore(), stats.getTotalEntries(),
//                                    Course.class.getName()) * scoreWeight);
//
//            if (_log.isDebugEnabled()) {
//                _log.debug("Setting course id " + course.getCourseId() +
//                        " popularWeight value " + course.getPopularWeight());
//            }
//            course.setPopularWeight(popularWeight);
//            coursePersistence.update(course, true);
//            try {
//                IndexerRegistryUtil.getIndexer(Course.class.getName()).reindex(course);
//            }
//            catch (SearchException e) {
//                _log.error("Cannot reindex course", e);
//            }
//        }
//    }


//    private void removeCourseFromIndex(Course course) {
//        if (CrawlTool.getInstance().isMaster()) {
//            CrawlTool.getInstance().removeFromIndex(course);
//        }
//    }


    /**
     * Clears the cache for all courses stored in this session.  This should
     * only be needed in a multi-threaded environment, where a thread is not
     * notified of persistence updates done in a different thread.
     */
    public void clearCache() {
        coursePersistence.clearCache();
    }


    /**
     * Clears the cache of a given course in this session.  This should only be
     * needed in a multi-threaded environment, where a thread is not notified of
     * persistence updates done in a different thread.
     *
     * @param course Course to remove from cache
     */
    public void clearCache(Course course) {
        coursePersistence.clearCache(course);
    }


    /**
     * Clears the cache of a given course in this session.  This should only be
     * needed in a multi-threaded environment, where a thread is not notified of
     * persistence updates done in a different thread.
     *
     * @param courseId Id of the course to remove from the cache
     */
    public void clearCache(Long courseId) {
        try {
            Course course = coursePersistence.findByCourseId(courseId);
            clearCache(course);
        }
        catch (Exception e) {
            _log.error("Course not find course with id: " + courseId);
        }
    }


    private static Log _log = LogFactoryUtil.getLog(
            CourseLocalServiceImpl.class);

}