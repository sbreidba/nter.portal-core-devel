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
import org.nterlearning.datamodel.catalog.model.CourseImage;
import org.nterlearning.datamodel.catalog.service.CourseImageLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import java.util.List;
import java.util.Vector;


public class CatalogImageDao extends AbstractDao<CourseImage> {

    public CatalogImageDao() {
        super();
    }


    @Override
    public void persistAdd(CourseImage courseImage)
            throws SystemException {
        addTargetCourseImage(courseImage);
    }


    @Override
    public void persistUpdate(CourseImage courseImage)
            throws SystemException {
        updateTargetCourseImage(courseImage);
    }


    @Override
    public void persistDelete(CourseImage courseImage)
            throws SystemException {
        deleteTargetCourseImage(courseImage);
    }


    @Override
    //This primaryKey is needed for update persist
    public long getPrimaryKey(CourseImage courseImage) {
        return courseImage.getPrimaryKey();
    }


    @Override
    //This primaryKey is used during update persist
    public void setPrimaryKey(CourseImage courseImage, long primaryKey) {
        courseImage.setPrimaryKey(primaryKey);
    }


    @Override
    //This primaryKey of parent is used to retrieve entries to revert
    public List<CourseImage> getRevertList(long parentPrimaryKey) {
        List<CourseImage> courseImages = new Vector<CourseImage>();
        try {
            return CourseLocalServiceUtil.getCourseImages(parentPrimaryKey);
        }
        catch (SystemException e) {
            return courseImages;
        }
    }


    @Override
    //combination of language and sourceImageUrl columns make each entry unique
    public String getContents(CourseImage courseImage) {
        return (courseImage.getLanguage() + "+" + courseImage.getSourceImageUrl());
    }


    @Override
    //This identifier is returned if issue found during persist
    public String getId(CourseImage courseImage) {
        return courseImage.getSourceImageUrl();
    }


    @Override
    public String getLabel(CourseImage courseImage) {
        return "Course Image";
    }


    public static void addTargetCourseImage(CourseImage targetCourseImage)
            throws SystemException {
        CourseImageLocalServiceUtil.addCourseImage(targetCourseImage);
    }


    public static void updateTargetCourseImage(CourseImage targetCourseImage)
            throws SystemException {
        CourseImageLocalServiceUtil.updateCourseImage(targetCourseImage);
    }


    public static void deleteTargetCourseImages(List<CourseImage> targetCourseImages)
            throws SystemException {

        for (CourseImage targetCourseImage : targetCourseImages) {
            deleteTargetCourseImage(targetCourseImage);
        }
    }


    public static void deleteTargetCourseImage(CourseImage targetCourseImage)
            throws SystemException {
        CourseImageLocalServiceUtil.deleteCourseImage(targetCourseImage);
	}
}