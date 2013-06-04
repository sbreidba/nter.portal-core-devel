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


package org.nterlearning.atom.parser.dao;

import org.nterlearning.atom.enumerations.NterFeedType;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;

import java.util.List;
import java.util.Vector;


public class PersistedCatalogFeed {

    private NterFeedType feedType;
    private List<Course> courseList;
    private List<Component> courseComponentsList;
    private List<CourseRecord> courseRecordsList;


    public PersistedCatalogFeed(List<Course> courseList,
                                List<Component> courseComponentsList) {
        this.feedType = NterFeedType.COURSES;
        this.courseList = courseList;
        this.courseComponentsList = courseComponentsList;
        this.courseRecordsList = new Vector<CourseRecord>();
    }


    public PersistedCatalogFeed(List<CourseRecord> courseRecords) {
        this.feedType = NterFeedType.RECORDS;
        this.courseList = new Vector<Course>();
        this.courseComponentsList = new Vector<Component>();
        this.courseRecordsList = courseRecords;
    }


    public NterFeedType getFeedType() {
        return feedType;
    }


    public List<Course> getCourseList() {
        return courseList;
    }


    public List<Component> getCourseComponentsList() {
        return courseComponentsList;
    }


    public List<CourseRecord> getCourseRecordsList() {
        return courseRecordsList;
    }
}