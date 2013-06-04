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
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import java.util.List;
import java.util.Vector;


public class CatalogContributorDao extends AbstractDao<Contributor> {

    public CatalogContributorDao() {
        super();
    }


    @Override
    public void persistAdd(Contributor contributor)
            throws SystemException {
        addTargetContributor(contributor);
    }


    @Override
    public void persistUpdate(Contributor contributor)
            throws SystemException {
        updateTargetContributor(contributor);
    }


    @Override
    public void persistDelete(Contributor contributor)
            throws SystemException {
        deleteTargetContributor(contributor);
    }


    @Override
    //This primaryKey is needed for update persist
    public long getPrimaryKey(Contributor contributor) {
        return contributor.getPrimaryKey();
    }


    @Override
    //This primaryKey is used during update persist
    public void setPrimaryKey(Contributor contributor, long primaryKey) {
        contributor.setPrimaryKey(primaryKey);
    }


    @Override
    //This primaryKey of parent is used to retrieve entries to revert
    public List<Contributor> getRevertList(long parentPrimaryKey) {
        List<Contributor> contributors = new Vector<Contributor>();
        try {
            return CourseLocalServiceUtil.getContributors(parentPrimaryKey);
        }
        catch (SystemException e) {
            return contributors;
        }
    }


    @Override
    //combination of role and name columns make each entry unique
    public String getContents(Contributor contributor) {
        return (contributor.getRole() + "+" + contributor.getContributorName());
    }


    @Override
    //This identifier is returned if issue found during persist
    public String getId(Contributor contributor) {
        return contributor.getContributorName();
    }


    @Override
    public String getLabel(Contributor contributor) {
        return "Course Contributor";
    }


    public static void addTargetContributor(Contributor targetContributor)
            throws SystemException {
        ContributorLocalServiceUtil.addContributor(targetContributor);
    }


    public static void updateTargetContributor(Contributor targetContributor)
            throws SystemException {
        ContributorLocalServiceUtil.updateContributor(targetContributor);
    }


    public static void deleteTargetContributors(List<Contributor> targetContributors)
            throws SystemException {
        for (Contributor targetContributor : targetContributors) {
            deleteTargetContributor(targetContributor);
        }
    }


    public static void deleteTargetContributor(Contributor targetContributor)
            throws SystemException {
        ContributorLocalServiceUtil.deleteContributor(targetContributor);
	}
}