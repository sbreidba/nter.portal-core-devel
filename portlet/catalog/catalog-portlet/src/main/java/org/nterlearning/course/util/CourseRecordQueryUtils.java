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


package org.nterlearning.course.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.course.enumerations.CompletionStatusType;
import org.nterlearning.course.enumerations.CourseRecordSortType;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;

import java.util.*;

public final class CourseRecordQueryUtils {

    private CourseRecordQueryUtils() {
    }

    public static List<CourseRecordQueryResult> getCompoundQueryResults(long userId, Locale locale, long classNameId,
                                                                        String filterType, String sortType, boolean asc, int start, int end, boolean dynamicSortEnabled) {
        List<CourseRecordQueryResult> result = new ArrayList<CourseRecordQueryResult>();  //new studentStatusResult
        try {
            if (dynamicSortEnabled) {
                // Dynamic sorting is enabled.
                // Consistently retrieve 500 courseRecords ordered by recent updatedDate.
                // After retrieval, post-sort result set depending on sortType and asc.
                String staticSortType = CourseRecordSortType.UPDATED_DATE.toString();
                List<Object[]> rows = CourseRecordLocalServiceUtil.findByUserIdFilterSorted(
                        userId, classNameId, filterType, staticSortType, false, 0, 500);

                for (Object[] row : rows) {
                    CourseRecord courseRecord = null;
                    Course course = null;
                    Double userRating = null;

                    if (row.length != 3) {
                        _log.error("Row result with an incorrect number of objects: " +
                                "expected 3 but got " + row.length);
                        continue;
                    }

                    // the instanceof operator will return false if the argument is null
                    if (row[0] instanceof CourseRecord) {
                        courseRecord = (CourseRecord) row[0];
                    }
                    if (row[1] instanceof Course) {
                        course = (Course) row[1];
                    }
                    if (row[2] instanceof Double) {
                        userRating = (Double) row[2];
                    }

                    _log.debug("----------  ROW  ----------");
                    _log.debug("  record = " + courseRecord);
                    _log.debug("  course = " + course);
                    _log.debug("  rating = " + userRating);
                    _log.debug("---------------------------");

                    result.add(new CourseRecordQueryResult(course, courseRecord, userRating));

                }
                //Special sorting required if sortType is localized title or completion status
                // Sort using title with XML removed
                if (sortType.equals(CourseRecordSortType.COURSE_TITLE.toString())) {
                    Collections.sort(result, new LocalizedTitleComparator(locale, asc));
                }

                // Sort using comparator by completion status and updatedDate
                if (sortType.equals(CourseRecordSortType.COMPLETION_STATUS.toString())) {

                    if (asc) {
                        Collections.sort(result, new CompletionStatusComparator());
                    } else {
                        Collections.sort(result, Collections.reverseOrder(new CompletionStatusComparator()));
                    }
                }

                // Sort using UpdatedDate
                if (sortType.equals(CourseRecordSortType.UPDATED_DATE.toString())) {
                    Collections.sort(result, new UpdatedDateComparator(asc));
                }

                // Sort using UserRating
                if (sortType.equals(CourseRecordSortType.USER_RATING.toString())) {
                    Collections.sort(result, new UserRatingComparator(asc));
                }

                // return container size
                if (result.size() < end) {
                    int newEnd = start + (result.size() - start);
                    return result.subList(start, newEnd);
                } else {
                    return result.subList(start, end);
                }

            } else {
                // Return all courseRecords in a container list based on its start and end value.
                // Dynamic sorting is no possible.
                List<Object[]> rows = CourseRecordLocalServiceUtil.findByUserIdFilterSorted(
                        userId, classNameId, filterType, sortType, asc, start, end);
                for (Object[] row : rows) {
                    CourseRecord courseRecord = null;
                    Course course = null;
                    Double userRating = null;

                    if (row.length != 3) {
                        _log.error("Row result with an incorrect number of objects: " +
                                "expected 3 but got " + row.length);
                        continue;
                    }

                    // the instanceof operator will return false if the argument is null
                    if (row[0] instanceof CourseRecord) {
                        courseRecord = (CourseRecord) row[0];
                    }
                    if (row[1] instanceof Course) {
                        course = (Course) row[1];
                    }
                    if (row[2] instanceof Double) {
                        userRating = (Double) row[2];
                    }

                    _log.debug("----------  ROW  ----------");
                    _log.debug("  record = " + courseRecord);
                    _log.debug("  course = " + course);
                    _log.debug("  rating = " + userRating);
                    _log.debug("---------------------------");

                    result.add(new CourseRecordQueryResult(course, courseRecord, userRating));

                }
                return result;
            }

        }
        catch (SystemException e) {
            _log.error(e);
            return new ArrayList<CourseRecordQueryResult>();
        }
        catch (IllegalArgumentException e) {
            _log.error(e);
            return new ArrayList<CourseRecordQueryResult>();
        }
    }

    //Return total number of student courseRecord with completed or failed status
    public static int getCourseFinishedCount(List<CourseRecordQueryResult> resultList){
        int finishedCourseCount = 0;
        for (CourseRecordQueryResult result : resultList) {
            if (result.getCourseRecord() != null) {
                if (CompletionStatusType.valueOfDbValue(result.getCourseRecord().getCompletionStatus()).isFinished()) {
                    finishedCourseCount++;
                }
            }
        }
        return finishedCourseCount;
    }

    //Return most recent student courseRecord
    public static CourseRecordQueryResult getMostRecent(List<CourseRecordQueryResult> resultList){
        Date mostRecent = resultList.get(0).getCourseRecord().getUpdatedDate();
        CourseRecordQueryResult mostRecentCourseRecord = resultList.get(0);
        for (CourseRecordQueryResult result : resultList) {
            if (result.getCourseRecord().getUpdatedDate() .after(mostRecent)) {
                mostRecent = result.getCourseRecord().getUpdatedDate();
                mostRecentCourseRecord = result;
            }
        }
        return mostRecentCourseRecord;
    }

	private static Log _log = LogFactoryUtil.getLog(CourseRecordQueryUtils.class);
}