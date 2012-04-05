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

package org.nterlearning.datamodel.catalog.model.impl;

/**
 * The extended model implementation for the FeedReference service. Represents a row in the &quot;CATALOG_FeedReference&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.model.FeedReference} interface.
 * </p>
 *
 */


import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

import org.nterlearning.datamodel.catalog.model.FeedReference;

/**
 * The model implementation for the FeedReference service. Represents a row in the &quot;CATALOG_FeedReference&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.model.FeedReference} interface.
 * </p>
 *
 * <p>
 * Never reference this class directly. All methods that expect a feed reference model instance should use the {@link FeedReference} interface instead.
 * </p>
 */
public class FeedReferenceImpl extends FeedReferenceModelImpl
	implements FeedReference {
//public class FeedReferenceImpl extends FeedReferenceBaseImpl {
	public FeedReferenceImpl() {
	}

    /**
     * Determines the correct name of the owner, regardless of whether or not
     * the owning group is a group, community, organization, or company.
     *
     * Typically, returning the name of the group will only return the primary
     * key in the corresponding owner table.
     *
     * @return The name of the owning entity.
     */
    public String getOwnerName() {
        String ownerName = null;

        try {
            Group ownerGroup = GroupLocalServiceUtil.getGroup(getGroupId());
            Long ownerClassId = ownerGroup.getClassNameId();

            if (ownerClassId == ClassNameLocalServiceUtil.getClassNameId(Company.class)) {
                ownerName = CompanyLocalServiceUtil.getCompany(ownerGroup.getClassPK()).getName();
            }
            else if (ownerClassId == ClassNameLocalServiceUtil.getClassNameId(Organization.class)) {
                ownerName = OrganizationLocalServiceUtil.getOrganization(ownerGroup.getClassPK()).getName();
            }
            else if (ownerClassId == ClassNameLocalServiceUtil.getClassNameId(Group.class)) {
                ownerName = ownerGroup.getName();
            }
        }
        catch (Exception e) {
            // this should never occur
        }

        return ownerName;
    }

    /**
     * Parses out the 'tag:...' portion of the Feed IRI.
     *
     * @return The tag substring of the Feed IRI
     */
    public String getTagSubstring() {
        String feedIri = getFeedIri();

        String tagSubString = feedIri.substring(feedIri.indexOf(":") + 1, feedIri.lastIndexOf(":"));

        if (tagSubString.length() == 0) {
            return feedIri;
        }
        else {
            return tagSubString;
        }
    }
}