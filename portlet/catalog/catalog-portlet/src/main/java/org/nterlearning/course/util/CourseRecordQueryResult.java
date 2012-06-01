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

import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;

public final class CourseRecordQueryResult {

    private final Course course;
    private final CourseRecord courseRecord;
    private final Double userRating;

    CourseRecordQueryResult(Course course, CourseRecord courseRecord, Double userRating) {
        if (course == null) {
            throw new IllegalArgumentException("course cannot be null");
        }

        this.course = course;
        this.courseRecord = courseRecord;
        this.userRating = userRating;
    }

    public Course getCourse() {
        return course;
    }

    public CourseRecord getCourseRecord() {
        return courseRecord;
    }

    public Double getUserRating() {
        return userRating;
    }
}