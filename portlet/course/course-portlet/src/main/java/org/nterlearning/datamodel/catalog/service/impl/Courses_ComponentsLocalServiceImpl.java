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

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.service.base.Courses_ComponentsLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the courses_ components local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.Courses_ComponentsLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalServiceUtil
 */
public class Courses_ComponentsLocalServiceImpl
    extends Courses_ComponentsLocalServiceBaseImpl {

    @Override
    public Courses_Components addCourses_Components(Courses_Components courses_components) throws SystemException {
        long id = counterLocalService.increment(Courses_Components.class.getName());
        courses_components.setPrimaryKey(id);
        return super.addCourses_Components(courses_components);
    }

    public List<Courses_Components> findByCourseId(Long courseId) throws SystemException {
        return courses_ComponentsPersistence.findByCourseId(courseId);
    }

    public List<Courses_Components> findByCourseIri(String courseIri) throws SystemException {
        return courses_ComponentsPersistence.findByCourseIri(courseIri);
    }

    public List<Courses_Components> findByComponentId(Long componentId) throws SystemException {
        return courses_ComponentsPersistence.findByComponentId(componentId);
    }

    public List<Courses_Components> findByComponentIri(String componentIri) throws SystemException {
        return courses_ComponentsPersistence.findByComponentIri(componentIri);
    }
}