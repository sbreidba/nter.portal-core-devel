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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;

import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.CourseReviewLocalServiceBaseImpl;
import org.nterlearning.datamodel.catalog.service.persistence.CourseReviewFinderUtil;

import java.util.*;

/**
 * The implementation of the course review local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.CourseReviewLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.CourseReviewLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil
 */
public class CourseReviewLocalServiceImpl
    extends CourseReviewLocalServiceBaseImpl {

    @Override
    public CourseReview addCourseReview(CourseReview courseReview) throws SystemException {
        throw new IllegalArgumentException("Must pass userId, CourseReview columns, serviceContext when adding a new review.");
    }


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
    public CourseReview appendCourseReview(long userId, long courseReviewId,
            long courseId, String summary, String content, double rating,
            ServiceContext serviceContext)
            throws SystemException, PortalException {

        List<CourseReview> existingCourseReviews =
                courseReviewPersistence.findByCourseIdWithUserId(courseId, userId);

        CourseReview courseReview;

        if (existingCourseReviews == null || existingCourseReviews.isEmpty()) {
            //Create new CourseReview
            courseReview =
                    addCourseReview(userId, courseId, summary, content, rating, serviceContext);
        }
        else if (existingCourseReviews.get(0).getRemoved()) {
            // The existing course review is marked for removal
            deleteCourseReview(existingCourseReviews.get(0));
            courseReview =
                    addCourseReview(userId, courseId, summary, content, rating, serviceContext);
        }
        else {
            //Update CourseReview
            courseReview =
                    updateCourseReview(userId, courseReviewId, courseId,
                                       summary, content, rating, serviceContext);
        }

        return courseReview;
    }


    public CourseReview addCourseReview(long userId, long courseId, String summary,
            String content, double rating, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // CourseReview

        User user = userPersistence.findByPrimaryKey(userId);
        long groupId = serviceContext.getScopeGroupId();

        //Star score for a course's ratings must be inserted prior to review
        // insert so that listener onAfterUpdate of review will have value to
        // generate global review activity stream
        if (rating >= 1 || rating <= 5) {
            RatingsEntryLocalServiceUtil
                    .updateEntry(user.getUserId(), Course.class.getName(),
                                 courseId, rating, serviceContext);
        }

        Date now = new Date();

        long courseReviewId = counterLocalService.increment(CourseReview.class.getName());

        CourseReview courseReview = courseReviewPersistence.create(courseReviewId);

        courseReview.setGroupId(groupId);
        courseReview.setCompanyId(user.getCompanyId());
        courseReview.setCourseId(courseId);
        courseReview.setUserId(user.getUserId());
        courseReview.setSummary(summary);
        courseReview.setContent(content);
        courseReview.setCreateDate(serviceContext.getCreateDate(now));
        courseReview.setModifiedDate(serviceContext.getCreateDate(now));

        courseReviewPersistence.update(courseReview, true);

        // Resources
        if (serviceContext.isAddGroupPermissions() ||
                serviceContext.isAddGuestPermissions()) {
            addCourseReviewResources(courseReview, serviceContext.isAddGroupPermissions(),
                                     serviceContext.isAddGuestPermissions());
        }
        else {
            addCourseReviewResources(courseReview, serviceContext.isAddGroupPermissions(),
                                     serviceContext.isAddGuestPermissions());
        }

        // Asset
        updateAsset(userId, courseReview, serviceContext.getAssetCategoryIds(),
                    serviceContext.getAssetTagNames());

        // Histogram starRateCounts in Course
        CourseLocalServiceUtil.updateReviewHistogram(courseReview.getCourseId());

        return courseReview;
    }

        public CourseReview migrateCourseReview(long userId, long courseId, String summary,
            String content, double rating, Date createDate, Date modifiedDate,
            boolean removed, Date removedDate, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // CourseReview - these should always be an add.  if already exists, skip it.
        // During migration, we want to keep the original creation, modification date.
        // During migration, its possible we could extract using SQL when a review is marked for
        //      marked for removal but hasn't yet been deleted.

        User user = userPersistence.findByPrimaryKey(userId);
        long groupId = serviceContext.getScopeGroupId();

        //Star score for a course's ratings must be inserted prior to review
        // insert so that listener onAfterUpdate of review will have value to
        // generate global review activity stream
        if (rating >= 1 || rating <= 5) {
            RatingsEntryLocalServiceUtil
                    .updateEntry(user.getUserId(), Course.class.getName(),
                                 courseId, rating, serviceContext);
        }

        long courseReviewId = counterLocalService.increment(CourseReview.class.getName());

        CourseReview courseReview = courseReviewPersistence.create(courseReviewId);

        courseReview.setGroupId(groupId);
        courseReview.setCompanyId(user.getCompanyId());
        courseReview.setCourseId(courseId);
        courseReview.setUserId(user.getUserId());
        courseReview.setSummary(summary);
        courseReview.setContent(content);
        courseReview.setCreateDate(createDate);
        courseReview.setModifiedDate(modifiedDate);
        courseReview.setRemoved(removed);
        courseReview.setRemovedDate(removedDate);

        courseReviewPersistence.update(courseReview, true);

        // Resources
        if (serviceContext.isAddGroupPermissions() ||
                serviceContext.isAddGuestPermissions()) {
            addCourseReviewResources(courseReview, serviceContext.isAddGroupPermissions(),
                                     serviceContext.isAddGuestPermissions());
        }
        else {
            addCourseReviewResources(courseReview, serviceContext.isAddGroupPermissions(),
                                     serviceContext.isAddGuestPermissions());
        }

        // Asset
        updateAsset(userId, courseReview, serviceContext.getAssetCategoryIds(),
                    serviceContext.getAssetTagNames());

        // Histogram starRateCounts in Course
        CourseLocalServiceUtil.updateReviewHistogram(courseReview.getCourseId());

        return courseReview;
    }

    public void addCourseReviewResources(CourseReview courseReview,
            boolean addGroupPermissions, boolean addGuestPermissions)
            throws PortalException, SystemException {
        resourceLocalService.addResources(
                courseReview.getCompanyId(), courseReview.getGroupId(), courseReview.getUserId(),
                CourseReview.class.getName(), courseReview.getCourseReviewId(), false,
                addGroupPermissions, addGuestPermissions);
    }


    public void addCourseReviewResources(CourseReview courseReview,
            String[] groupPermissions, String[] guestPermissions)
            throws PortalException, SystemException {
        try {
            resourceLocalService.addModelResources(
                    courseReview.getCompanyId(), courseReview.getGroupId(), courseReview.getUserId(),
                    CourseReview.class.getName(), courseReview.getCourseReviewId(),
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


    public double findScoreByReviewId(long reviewId) throws SystemException {
        return CourseReviewFinderUtil.findScoreByReviewId(reviewId);
    }


    public List<Double> findScoreByCourseId(long courseId) throws SystemException {
        return CourseReviewFinderUtil.findScoresByCourseId(courseId);
    }


    public void addCourseReviewResources(long courseReviewId, boolean addGroupPermissions,
                                         boolean addGuestPermissions) throws PortalException, SystemException {
        CourseReview courseReview = courseReviewPersistence.findByPrimaryKey(courseReviewId);
        addCourseReviewResources(courseReview, addGroupPermissions, addGuestPermissions);
    }


    public void addCourseReviewResources(long courseReviewId, String[] groupPermissions,
                                         String[] guestPermissions) throws PortalException, SystemException {
        CourseReview courseReview = courseReviewPersistence.findByPrimaryKey(courseReviewId);
        addCourseReviewResources(courseReview, groupPermissions, guestPermissions);
    }


    public void deleteCourseReviews(long groupId) throws PortalException, SystemException {
        for (CourseReview courseReview : courseReviewPersistence.findByGroupId(groupId)) {
            deleteCourseReview(courseReview);
        }
    }


    @Override
    public void deleteCourseReview(CourseReview courseReview)
            throws PortalException, SystemException {

        // Resources
        resourceLocalService.deleteResource(
                courseReview.getCompanyId(), CourseReview.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, courseReview.getCourseReviewId());

        // Asset
        assetEntryLocalService.deleteEntry(CourseReview.class.getName(), courseReview.getCourseReviewId());

        // Ratings
        ratingsStatsLocalService.deleteStats(CourseReview.class.getName(), courseReview.getCourseReviewId());
        RatingsEntryLocalServiceUtil.deleteEntry(courseReview.getUserId(), Course.class.getName(), courseReview.getCourseId());

        // CourseReview
        courseReviewPersistence.remove(courseReview);
    }


    @Override
    public void deleteCourseReview(long courseReviewId)
            throws PortalException, SystemException {
        CourseReview courseReview = courseReviewPersistence.findByPrimaryKey(courseReviewId);
        deleteCourseReview(courseReview);
    }


    public void updateAsset(long userId, CourseReview courseReview, long[] assetCategoryIds,
                            String[] assetTagNames) throws PortalException, SystemException {

        assetEntryLocalService.updateEntry(
                userId, courseReview.getGroupId(), CourseReview.class.getName(), courseReview.getCourseReviewId(),
                null, 0, assetCategoryIds, assetTagNames, true, null, null, null, null, ContentTypes.TEXT_HTML,
                null, null, null, null, null, 0, 0, null, false);
    }


    @Override
    public CourseReview updateCourseReview(CourseReview courseReview) throws SystemException {
        throw new IllegalArgumentException("Must pass userId, CourseReview columns, and serviceContext when updating a course review.");
    }


    public CourseReview updateCourseReview(long userId, long courseReviewId, long courseId, String summary,
                                           String content, double rating, ServiceContext serviceContext)
            throws PortalException, SystemException {

        // CourseReview

        User user = userPersistence.findByPrimaryKey(userId);

        //Star score for a course's ratings must be updated prior to review update so that
        //listener onAfterUpdate of review will have value to generate global review activity stream
        if (rating >= 1 || rating <= 5) {
            RatingsEntryLocalServiceUtil
                    .updateEntry(user.getUserId(), Course.class.getName(), courseId, rating, serviceContext);
        }

        CourseReview courseReview = courseReviewPersistence.findByPrimaryKey(courseReviewId);
        Date now = new Date();
        courseReview.setCourseId(courseId);
        courseReview.setSummary(summary);
        courseReview.setContent(content);
        courseReview.setModifiedDate(serviceContext.getCreateDate(now));
        courseReviewPersistence.update(courseReview, true);

        // Resources
        if ((serviceContext.getGroupPermissions() != null) ||
                (serviceContext.getGuestPermissions() != null)) {
            updateCourseReviewResources(courseReview, serviceContext.getGroupPermissions(),
                                        serviceContext.getGuestPermissions());
        }

        // Asset
        updateAsset(user.getUserId(), courseReview, serviceContext.getAssetCategoryIds(),
                    serviceContext.getAssetTagNames());

        // Histogram starRateCounts in Course
        CourseLocalServiceUtil.updateReviewHistogram(courseReview.getCourseId());
        return courseReview;
    }


    public void updateCourseReviewRating(long courseReviewId, double weightedScore)
            throws PortalException, SystemException {
        CourseReview courseReview = courseReviewPersistence.findByPrimaryKey(courseReviewId);
        courseReview.setWeightedScore(weightedScore);
        courseReviewPersistence.update(courseReview, true);
    }


    public void updateCourseReviewResources(CourseReview courseReview,
            String[] groupPermissions, String[] guestPermissions)
            throws PortalException, SystemException {
        resourceLocalService.updateResources(
                courseReview.getCompanyId(), courseReview.getGroupId(),
                CourseReview.class.getName(), courseReview.getCourseReviewId(),
                groupPermissions, guestPermissions);
    }


    public void setRemoved(long reviewId, boolean removed)
            throws SystemException, PortalException {

        CourseReview review = courseReviewPersistence.fetchByPrimaryKey(reviewId);
        if (review == null) {
            throw new SystemException("Review " + reviewId + " not found");
        }

        review.setRemoved(removed);
        if (removed) {
            review.setRemovedDate(new Date());
        }
        else {
            review.setRemovedDate(null);
        }

        courseReviewPersistence.update(review, true);
        CourseLocalServiceUtil.updateReviewHistogram(review.getCourseId());
    }


    public void purgeAllRemovedOlderThan(Date date)
            throws SystemException, PortalException {
        List<CourseReview> reviews = courseReviewPersistence.findByRemoved(true);
        for (CourseReview review : reviews) {
            if (review.getRemovedDate() == null || review.getRemovedDate().before(date)) {
                deleteCourseReview(review);
            }
        }
    }


    public List<CourseReview> findByCourseId(long courseId) throws SystemException {
        return courseReviewPersistence.findByCourseId(courseId);
    }


    public long countByCourseId(long courseId) throws SystemException {
        return courseReviewPersistence.countByCourseId(courseId);
    }


    public List<CourseReview> findByCourseId(long courseId, int start, int end) throws SystemException {
        return courseReviewPersistence.findByCourseId(courseId, start, end);
    }


    public List<CourseReview> findByCourseIdWithUserId(long userId, long courseId) throws SystemException {
        return courseReviewPersistence.findByCourseIdWithUserId(courseId, userId);
    }


    public List<CourseReview> findByCourseIdWithUserId(long userId, long courseId, int start, int end) throws SystemException {
        return courseReviewPersistence.findByCourseIdWithUserId(courseId, userId, start, end);
    }


    public List<CourseReview> findByUserId(long userId) throws SystemException {
        return courseReviewPersistence.findByUserId(userId);
    }


    public List<CourseReview> findByUserId(long userId, int start, int end) throws SystemException {
        return courseReviewPersistence.findByUserId(userId, start, end);
    }


    public List<CourseReview> findByCourseIdWithRemoved(long courseId, boolean removed, int start, int end)
            throws SystemException {
        return courseReviewPersistence.findByCourseIdWithRemoved(courseId, removed, start, end);
    }


    public long countByCourseIdWithRemoved(long courseId, boolean removed)
            throws SystemException {
        return courseReviewPersistence.countByCourseIdWithRemoved(courseId, removed);
    }


    private static Log _log = LogFactoryUtil.getLog(CourseReviewLocalServiceImpl.class);
}