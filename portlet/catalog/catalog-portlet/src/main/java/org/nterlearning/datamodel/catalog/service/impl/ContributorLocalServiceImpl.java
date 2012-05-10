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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.ContributorLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the contributor local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.ContributorLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.ContributorLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil
 */
public class ContributorLocalServiceImpl extends ContributorLocalServiceBaseImpl {


    private static Log mLog = LogFactoryUtil.getLog(ContributorLocalServiceImpl.class);

    @Override
    public Contributor addContributor(Contributor contributor)
            throws SystemException {
        long id = counterLocalService.increment(Contributor.class.getName());
        contributor.setPrimaryKey(id);

        updateIndex(contributor);

        return super.addContributor(contributor);
    }


    @Override
    public Contributor updateContributor(Contributor contributor) throws SystemException {
        updateIndex(contributor);
        return super.updateContributor(contributor);
    }


    @Override
    public Contributor updateContributor(Contributor contributor, boolean merge) throws
            SystemException {
        updateIndex(contributor);
        return super.updateContributor(contributor, merge);
    }


    @Override
    public void deleteContributor(long contributorId) throws PortalException, SystemException {
        updateIndex(ContributorLocalServiceUtil.getContributor(contributorId));
        super.deleteContributor(contributorId);
    }


    @Override
    public void deleteContributor(Contributor contributor) throws SystemException {
        updateIndex(contributor);
        super.deleteContributor(contributor);
    }


    public List<Contributor> findByCourseIdWithRole(Long courseId, String role)
            throws SystemException {
        return contributorPersistence.findByCourseIdWithRole(courseId, role);
    }


    public List<Contributor> findByComponentIdWithRole(Long componentId, String role)
            throws SystemException {
        return contributorPersistence.findByComponentIdWithRole(componentId,role);
    }


    private void updateIndex(Contributor contributor) {
        try {
            if (contributor.getComponentId() > 0) {
                Component component = ComponentLocalServiceUtil.getComponent(contributor.getComponentId());
                component.updateIndex();
            }
            else if (contributor.getCourseId() > 0) {
                Course course = CourseLocalServiceUtil.getCourse(contributor.getCourseId());
                course.updateIndex();
            }
        }
        catch (Exception e) {
            mLog.error("Could not update index related to contributor: " + contributor.getContributorId());
        }
    }
}