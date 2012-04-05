/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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
import org.nterlearning.datamodel.catalog.model.CourseRecord;

import java.util.List;
import java.util.Vector;

public class CatalogFeed {

    private NterFeedType feedType;
    private List<CatalogCourse> courseList;
    private List<CatalogComponent> courseComponentsList;
    private List<CourseRecord> courseRecordsList;


    public CatalogFeed(List<CatalogCourse> courseList,
                       List<CatalogComponent> courseComponentsList) {

        this.feedType = NterFeedType.COURSES;
        this.courseList = courseList;
        this.courseComponentsList = courseComponentsList;
        this.courseRecordsList = new Vector<CourseRecord>();
    }


    public CatalogFeed(List<CourseRecord> courseRecords) {
        this.feedType = NterFeedType.RECORDS;
        this.courseList = new Vector<CatalogCourse>();
        this.courseComponentsList = new Vector<CatalogComponent>();
        this.courseRecordsList = courseRecords;
    }


    public NterFeedType getFeedType() {
        return feedType;
    }


    public List<CatalogCourse> getCourseList() {
        return courseList;
    }


    public List<CatalogComponent> getCourseComponentsList() {
        return courseComponentsList;
    }


    public List<CourseRecord> getCourseRecordsList() {
        return courseRecordsList;
    }
}