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


/**
 *
 */

package org.nterlearning.atom.parser.dao;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.course.enumerations.RelationshipType;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRelated;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRelatedLocalServiceUtil;
import org.apache.commons.lang.Validate;

import java.util.List;
import java.util.Vector;


public class CatalogRelatedDao extends AbstractDao<CourseRelated> {

    private static Log log = LogFactoryUtil.getLog(CatalogRelatedDao.class);

    public CatalogRelatedDao(FeedContext feedContext) {
        super();
        super.setFeedContext(feedContext);
    }


    @Override
    public void persistAdd(CourseRelated courseRelated)
            throws SystemException {
        addTargetCourseRelated(courseRelated, super.getFeedContext());
    }


    @Override
    public void persistUpdate(CourseRelated courseRelated)
            throws SystemException {
        updateTargetCourseRelated(courseRelated, super.getFeedContext());
    }


    @Override
    public void persistDelete(CourseRelated courseRelated)
            throws SystemException {
        deleteTargetCourseRelated(courseRelated);
    }


    @Override
    //This primaryKey is needed for update persist
    public long getPrimaryKey(CourseRelated courseRelated) {
        return courseRelated.getPrimaryKey();
    }


    @Override
    //This primaryKey is used during update persist
    public void setPrimaryKey(CourseRelated courseRelated, long primaryKey) {
        courseRelated.setPrimaryKey(primaryKey);
    }


    @Override
    //This primaryKey of parent is used to retrieve entries to revert
    public List<CourseRelated> getRevertList(long parentPrimaryKey) {
        List<CourseRelated> courseRelateds = new Vector<CourseRelated>();
        try {
            return CourseLocalServiceUtil.getCourseRelateds(parentPrimaryKey);
        }
        catch (SystemException e) {
            return courseRelateds;
        }
    }


    @Override
    //combination of relationshipType and relatedCourseIri columns make each entry unique
    public String getContents(CourseRelated courseRelated) {
        return (courseRelated.getRelationshipType() + "+" + courseRelated.getRelatedCourseIri());
    }


    @Override
    //This identifier is returned if issue found during persist
    public String getId(CourseRelated courseRelated) {
        return String.valueOf(courseRelated.getRelatedCourseId());
    }


    @Override
    public String getLabel(CourseRelated courseRelated) {
        return "Course Related";
    }


    public static void addTargetCourseRelated(CourseRelated targetCourseRelated,
            FeedContext fc)
            throws SystemException {

        CourseRelatedLocalServiceUtil.addCourseRelated(targetCourseRelated);

        // if the relationship is a superseding one
        if (targetCourseRelated.getRelationshipType().equals(
                RelationshipType.SUPERSEDES.toString())) {

            long courseId = targetCourseRelated.getCourseId();
            long relatedCourseId = targetCourseRelated.getRelatedCourseId();
            String relatedCourseIri = targetCourseRelated.getRelatedCourseIri();

            log.info("Found superseding relationship in CourseRelated " + relatedCourseId);

            Validate.notNull(fc, "Cannot process superseding relationship because the FeedContext is null");

            // get the superseded course and our parent course
            Course supersededCourse;
            Course supersedingCourse;
            try {
                supersededCourse = CourseLocalServiceUtil.fetchByCourseId(relatedCourseId);
                supersedingCourse = CourseLocalServiceUtil.fetchByCourseId(courseId);

                // if we can't find the course referenced
                if (supersededCourse == null) {
                    String errorMsg = "Could not find superseded course id [" + relatedCourseId +
                            "] with IRI [" + relatedCourseIri + "]";
                    log.error(errorMsg);
                    fc.addSyncMessage(errorMsg);

                    // if we can't find our parent course
                }
                else if (supersedingCourse == null) {
                    String errorMsg = "Could not find superseding course id [" + courseId + "]";
                    log.error(errorMsg);
                    fc.addSyncMessage(errorMsg);

                    // everything in place to update courses
                }
                else {
                    updateSupersededBy(supersededCourse, supersedingCourse.getCourseIri(), fc);
                }
            }
            catch (PortalException e) {
                throw new SystemException("Error retrieving course: " + e, e);
            }
        }
    }


    public static void updateTargetCourseRelated(CourseRelated targetCourseRelated,
            FeedContext fc)
            throws SystemException {

        CourseRelatedLocalServiceUtil.updateCourseRelated(targetCourseRelated);

        // if the relationship is a superseding one
        if (targetCourseRelated.getRelationshipType().equals(
                RelationshipType.SUPERSEDES.toString())) {

            long courseId = targetCourseRelated.getCourseId();
            long relatedCourseId = targetCourseRelated.getRelatedCourseId();
            String relatedCourseIri = targetCourseRelated.getRelatedCourseIri();

            log.info("Found superseding relationship in CourseRelated " + relatedCourseId);

            Validate.notNull(fc, "Cannot process superseding relationship because the FeedContext is null");

            // get the superseded course and our parent course
            Course supersededCourse;
            Course supersedingCourse;
            try {
                supersededCourse = CourseLocalServiceUtil.fetchByCourseId(relatedCourseId);
                supersedingCourse = CourseLocalServiceUtil.fetchByCourseId(courseId);

                // if we can't find the course referenced
                if (supersededCourse == null) {
                    String errorMsg = "Could not find superseded course id [" + relatedCourseId +
                            "] with IRI [" + relatedCourseIri + "]";
                    log.error(errorMsg);
                    fc.addSyncMessage(errorMsg);

                    // if we can't find our parent course
                }
                else if (supersedingCourse == null) {
                    String errorMsg = "Could not find superseding course id [" + courseId + "]";
                    log.error(errorMsg);
                    fc.addSyncMessage(errorMsg);

                    // everything in place to update courses
                }
                else {
                    updateSupersededBy(supersededCourse,
                            supersedingCourse.getCourseIri(), fc);
                }
            }
            catch (PortalException e) {
                throw new SystemException("Error retrieving course: " + e, e);
            }
        }
    }


    public static void updateSupersededBy(Course supersededCourse,
            String supersedingCourseIri, FeedContext fc)
            throws SystemException, PortalException {

        // see if the course that's being superseded also supersedes another course
        String supersededSupersedesCourseIri = supersededCourse.getSupersedesCourseIri();
        if ((supersededSupersedesCourseIri != null) && (!supersededSupersedesCourseIri.equals(""))) {

            // if it does superseded another course, fetch that course
            Course parentCourse =
                    CourseLocalServiceUtil.fetchByCourseIri(supersededSupersedesCourseIri);
            if (parentCourse != null) {
                // recursively set the parent course's supersededBy
                updateSupersededBy(parentCourse, supersedingCourseIri, fc);
            }
            else {
                log.warn("Course with id [" + supersededCourse.getCourseIri() +
                        "] purports to supersede the course with id [" +
                        supersededSupersedesCourseIri + "], which does not " +
                        "exist in persistence");
            }
        }

        // tell the old course that it's been replaced by a younger,
        // better version that costs less, but that it'll get a nice retirement watch
        supersededCourse.setSupersededByCourseIri(supersedingCourseIri);
        CourseLocalServiceUtil.updateCourse(fc.getUserId(), supersededCourse.getCourseId(),
                fc.getFeedReferenceId(), supersededCourse.getHref(),
                supersededCourse.getFullTextHref(), supersededCourse.getCourseIri(),
                supersededCourse.getUpdatedDate(), supersededCourse.getTitle(),
                supersededCourse.getTranscriptAbstract(), supersededCourse.getDescription(),
                supersededCourse.getKeywords(), supersededCourse.getCopyright(),
                supersededCourse.getRatingLevel(), supersededCourse.getRatingReason(),
                supersededCourse.getDuration(), supersededCourse.getDurationStandard(),
                supersededCourse.getFeaturedStatus(),
                supersededCourse.getPopularWeight(),
                supersededCourse.getAccessCount(), supersededCourse.getCompletedCount(),
                supersededCourse.getRemoved(), supersededCourse.getRemovedDate(),
                supersededCourse.getSupersedesCourseIri(),
                supersededCourse.getSupersededByCourseIri(),
                supersededCourse.getReleaseOnDate(), supersededCourse.getAcceptUntilDate(),
                supersededCourse.getVersion(), supersededCourse.getVersionDate(),
                supersededCourse.getPrice(), supersededCourse.getPriceUnit(),
                supersededCourse.getPriceTerms(), supersededCourse.getPriceExpiration(),
                supersededCourse.getOneStarRateCount(), supersededCourse.getTwoStarRateCount(),
                supersededCourse.getThreeStarRateCount(), supersededCourse.getFourStarRateCount(),
                supersededCourse.getFiveStarRateCount(), fc.getSc());
    }


    public static void updateSupersededByReverse(Course supersededCourse,
            String supersedingCourseIri, FeedContext fc)
            throws SystemException, PortalException {

        // tell the old course that it's been replaced by a younger,
        // better version that costs less, but that it'll get a nice retirement watch
        supersededCourse.setSupersededByCourseIri(supersedingCourseIri);
        CourseLocalServiceUtil.updateCourse(fc.getUserId(), supersededCourse.getCourseId(),
                fc.getFeedReferenceId(), supersededCourse.getHref(),
                supersededCourse.getFullTextHref(), supersededCourse.getCourseIri(),
                supersededCourse.getUpdatedDate(), supersededCourse.getTitle(),
                supersededCourse.getTranscriptAbstract(), supersededCourse.getDescription(),
                supersededCourse.getKeywords(), supersededCourse.getCopyright(),
                supersededCourse.getRatingLevel(), supersededCourse.getRatingReason(),
                supersededCourse.getDuration(), supersededCourse.getDurationStandard(),
                supersededCourse.getFeaturedStatus(),
                supersededCourse.getPopularWeight(),
                supersededCourse.getAccessCount(), supersededCourse.getCompletedCount(),
                supersededCourse.getRemoved(), supersededCourse.getRemovedDate(),
                supersededCourse.getSupersedesCourseIri(),
                supersededCourse.getSupersededByCourseIri(),
                supersededCourse.getReleaseOnDate(), supersededCourse.getAcceptUntilDate(),
                supersededCourse.getVersion(), supersededCourse.getVersionDate(),
                supersededCourse.getPrice(), supersededCourse.getPriceUnit(),
                supersededCourse.getPriceTerms(), supersededCourse.getPriceExpiration(),
                supersededCourse.getOneStarRateCount(), supersededCourse.getTwoStarRateCount(),
                supersededCourse.getThreeStarRateCount(), supersededCourse.getFourStarRateCount(),
                supersededCourse.getFiveStarRateCount(), fc.getSc());

        // see if the course that's being superseded also is superseded by newer course
        Course parentCourse = CourseLocalServiceUtil.fetchByCourseIri(supersedingCourseIri);
        if (parentCourse != null) {
            String newerSupersededByCourseIri = parentCourse.getSupersededByCourseIri();

            if ((newerSupersededByCourseIri != null) && (!newerSupersededByCourseIri.equals(""))) {
                // recursively set the superseded course's supersededBy with most current courseIri
                updateSupersededByReverse(supersededCourse, newerSupersededByCourseIri, fc);
            }
        }
    }


    public static void deleteTargetCourseRelateds(List<CourseRelated> targetCourseRelateds)
            throws SystemException {

        for (CourseRelated targetCourseRelated : targetCourseRelateds) {
            deleteTargetCourseRelated(targetCourseRelated);
        }
    }


    public static void deleteTargetCourseRelated(CourseRelated targetCourseRelated)
            throws SystemException {
        CourseRelatedLocalServiceUtil.deleteCourseRelated(targetCourseRelated);
    }


    public static void updateCourseRelatedWithIriAndRollback(Course targetCourse,
            FeedContext fc)
            throws PortalException, SystemException {
        // special update when related courses loaded after course
        String courseIri = targetCourse.getCourseIri();
        long courseId = targetCourse.getCourseId();

        List<CourseRelated> existingEntries =
                CourseRelatedLocalServiceUtil.findByRelatedCourseIri(courseIri);
        List<CourseRelated> targetEntries =
                CourseRelatedLocalServiceUtil.findByRelatedCourseIri(courseIri);

        try {
            for (CourseRelated targetEntry : targetEntries) {
                if (targetEntry.getRelatedCourseId() != courseId) {
                    targetEntry.setRelatedCourseId(courseId);
                    CourseRelatedLocalServiceUtil.updateCourseRelated(targetEntry);
                    // if the relationship is a superseding one
                    if (targetEntry.getRelationshipType().equals(
                            RelationshipType.SUPERSEDES.toString())) {
                        Course supersededByCourse =
                                CourseLocalServiceUtil.fetchByCourseId(targetEntry.getCourseId());

                        if (supersededByCourse == null) {
                            String errorMsg = "Could not find supersededByCourse id [" +
                                    targetEntry.getCourseId() + "]";
                            log.error(errorMsg);
                            fc.addSyncMessage(errorMsg);

                        }
                        else {
                            String supersededByIri = supersededByCourse.getCourseIri();
                            updateSupersededBy(targetCourse, supersededByIri, fc);
                            updateSupersededByReverse(targetCourse, supersededByIri, fc);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            // if we can't persist one of the items,
            // go through the list again and revert the updates to original list

            for (CourseRelated existingEntry : existingEntries) {
                CourseRelatedLocalServiceUtil.updateCourseRelated(existingEntry);
            }
            //
            throw new SystemException("Problem persisting CourseRelated update for "
                    + courseIri + ": ", e);
        }
    }
}