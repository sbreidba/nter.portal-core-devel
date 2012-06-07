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

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.model.CourseRequirement;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRequirementLocalServiceUtil;

import java.util.List;
import java.util.Vector;


public class CatalogRequirementDao extends AbstractDao<CourseRequirement> {

    public CatalogRequirementDao() {
        super();
    }


    @Override
    public void persistAdd(CourseRequirement courseRequirement)
            throws SystemException {
        addTargetCourseRequirement(courseRequirement);
    }


    @Override
    public void persistUpdate(CourseRequirement courseRequirement)
            throws SystemException {
        updateTargetCourseRequirement(courseRequirement);
    }


    @Override
    public void persistDelete(CourseRequirement courseRequirement)
            throws SystemException {
        deleteTargetCourseRequirement(courseRequirement);
    }


    @Override
    //This primaryKey is needed for update persist
    public long getPrimaryKey(CourseRequirement courseRequirement) {
        return courseRequirement.getPrimaryKey();
    }


    @Override
    //This primaryKey is used during update persist
    public void setPrimaryKey(CourseRequirement courseRequirement, long primaryKey) {
        courseRequirement.setPrimaryKey(primaryKey);
    }


    @Override
    //This primaryKey of parent is used to retrieve entries to revert
    public List<CourseRequirement> getRevertList(long parentPrimaryKey) {
        List<CourseRequirement> courseRequirements = new Vector<CourseRequirement>();
        try {
            return CourseLocalServiceUtil.getCourseRequirements(parentPrimaryKey);
        }
        catch (SystemException e) {
            return courseRequirements;
        }
    }


    @Override
    //combination of requirement and requirementType columns make each entry unique
    public String getContents(CourseRequirement courseRequirement) {
        return (courseRequirement.getRequirementType() + "+" + courseRequirement.getRequirementValue());
    }


    @Override
    //This identifier is returned if issue found during persist
    public String getId(CourseRequirement courseRequirement) {
        return courseRequirement.getRequirementValue();
    }


    @Override
    public String getLabel(CourseRequirement courseRequirement) {
        return "Course Requirement";
    }


    public static void addTargetCourseRequirement(
            CourseRequirement targetCourseRequirement)
            throws SystemException {
        CourseRequirementLocalServiceUtil.addCourseRequirement(targetCourseRequirement);
    }


    public static void updateTargetCourseRequirement(
            CourseRequirement targetCourseRequirement)
            throws SystemException {
        CourseRequirementLocalServiceUtil.updateCourseRequirement(targetCourseRequirement);
    }


    public static void deleteTargetCourseRequirements(
            List<CourseRequirement> targetCourseRequirements)
            throws SystemException {

        for (CourseRequirement targetCourseRequirement : targetCourseRequirements) {
            deleteTargetCourseRequirement(targetCourseRequirement);
        }
    }


    public static void deleteTargetCourseRequirement(CourseRequirement targetCourseRequirement)
            throws SystemException {
        CourseRequirementLocalServiceUtil.deleteCourseRequirement(targetCourseRequirement);
	}
}