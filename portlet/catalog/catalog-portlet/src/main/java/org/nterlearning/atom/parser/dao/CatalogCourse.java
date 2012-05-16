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

import org.nterlearning.datamodel.catalog.model.*;

import java.util.List;


public class CatalogCourse {

    private Course course;
    private List<Contributor> contributors;
    private List<CourseImage> images;
    private List<Courses_Components> componentRefs;
    private List<CourseRelated> relateds;
    private List<CourseRequirement> requirements;


    public CatalogCourse(Course course, List<Contributor> contributors,
            List<CourseImage> images, List<Courses_Components> componentRefs,
            List<CourseRelated> relateds, List<CourseRequirement> requirements) {

        this.course = course;
        this.contributors = contributors;
        this.images = images;
        this.componentRefs = componentRefs;
        this.relateds = relateds;
        this.requirements = requirements;
    }


    public Course getCourse() {
        return course;
    }


    public void setCourse(Course course) {
        this.course = course;
    }


    public List<Contributor> getContributors() {
        return contributors;
    }


    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }


    public List<CourseImage> getImages() {
        return images;
    }


    public void setImages(List<CourseImage> images) {
        this.images = images;
    }


    public List<Courses_Components> getComponentRefs() {
        return componentRefs;
    }


    public void setComponentRefs(List<Courses_Components> componentRefs) {
        this.componentRefs = componentRefs;
    }


    public List<CourseRelated> getRelateds() {
        return relateds;
    }


    public void setRelateds(List<CourseRelated> relateds) {
        this.relateds = relateds;
    }


    public List<CourseRequirement> getRequirements() {
        return requirements;
    }


    public void setRequirements(List<CourseRequirement> requirements) {
        this.requirements = requirements;
    }
}