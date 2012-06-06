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
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalServiceUtil;

import java.util.List;
import java.util.Vector;


public class CatalogComponentDao extends AbstractDao<Courses_Components> {

    public CatalogComponentDao() {
        super();
    }


    @Override
    public void persistAdd(Courses_Components course_component)
            throws SystemException {
        addTargetCourse_Component(course_component);
    }


    @Override
    public void persistUpdate(Courses_Components course_component)
            throws SystemException {
        updateTargetCourse_Component(course_component);
    }


    @Override
    public void persistDelete(Courses_Components course_component)
            throws SystemException {
        deleteTargetCourse_Component(course_component);
    }


    @Override
    public long getPrimaryKey(Courses_Components course_component) {
        //This primaryKey is needed for update persist
        return course_component.getPrimaryKey();
    }


    @Override
    public void setPrimaryKey(Courses_Components course_component, long primaryKey) {
        //This primaryKey is used during update persist
        course_component.setPrimaryKey(primaryKey);
    }


    @Override
    public List<Courses_Components> getRevertList(long parentPrimaryKey) {
        //This primaryKey of parent is used to retrieve entries to revert
        List<Courses_Components> courses_components = new Vector<Courses_Components>();
        try {
            return CourseLocalServiceUtil.getCourses_Componentses(parentPrimaryKey);
        }
        catch (SystemException e) {
            return courses_components;
        }
    }


    @Override
    public String getContents(Courses_Components course_component) {
        //combination of IRI columns make each entry unique
        return (course_component.getCourseIri() + "+" + course_component.getComponentIri());
    }


    @Override
    public String getId(Courses_Components course_component) {
        //This identifier is returned if issue found during persist
        return course_component.getComponentIri();
    }


    @Override
    public String getLabel(Courses_Components course_component) {
        return "Course_Component";
    }


    /**
     * @param targetCourse_Component
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void addTargetCourse_Component(Courses_Components targetCourse_Component)
            throws SystemException {
        Courses_ComponentsLocalServiceUtil.addCourses_Components(targetCourse_Component);
    }


    /**
     * @param targetCourse_Component
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void updateTargetCourse_Component(Courses_Components targetCourse_Component)
            throws SystemException {
        Courses_ComponentsLocalServiceUtil.updateCourses_Components(targetCourse_Component);
    }


    /**
     * @param targetCourses_Components
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void deleteTargetCourses_Components(List<Courses_Components> targetCourses_Components)
            throws SystemException {

        for (Courses_Components targetCourse_Component : targetCourses_Components) {
            deleteTargetCourse_Component(targetCourse_Component);
        }
    }


    /**
     * @param targetCourse_Component
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static void deleteTargetCourse_Component(Courses_Components targetCourse_Component)
            throws SystemException {
        Courses_ComponentsLocalServiceUtil.deleteCourses_Components(targetCourse_Component);
    }


    /**
     * @param targetComponent
     */
    public static void updateCourses_ComponentsWithIriAndRollback(Component targetComponent)
            throws SystemException {
        // special update when component loaded after course
        String componentIri = targetComponent.getComponentIri();
        long componentId = targetComponent.getComponentId();

        List<Courses_Components> existingEntries = Courses_ComponentsLocalServiceUtil.findByComponentIri(componentIri);
        List<Courses_Components> targetEntries = Courses_ComponentsLocalServiceUtil.findByComponentIri(componentIri);

        try {
            for (Courses_Components targetEntry : targetEntries) {
                if (targetEntry.getComponentId() != componentId) {
                    targetEntry.setComponentId(componentId);
                    Courses_ComponentsLocalServiceUtil.updateCourses_Components(targetEntry);
                }
            }
        }
        catch (Exception e) {
            // if we can't persist one of the items,
            // go through the list again and revert the updates to original list
            for (Courses_Components existingEntry : existingEntries) {
                Courses_ComponentsLocalServiceUtil.updateCourses_Components(existingEntry);
            }
            throw new SystemException("Problem persisting Courses_Component update for " + componentIri + ": ", e);
        }
    }
}