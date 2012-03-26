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


package org.nterlearning.hook.setup;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.DuplicateVocabularyException;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class is designed to be a listener for when new communities are created.
 * Whenever a new community is created, a corresponding vocabulary is also
 * created.
 */
public class OrganizationListener implements ModelListener<Organization> {

    protected long mCompanyId;
    protected long mUserId;
    protected long mDefaultGroupId;
    protected ServiceContext mServiceContext;

    static Log mLog = LogFactoryUtil.getLog(OrganizationListener.class);


    public void onBeforeAddAssociation(Object o, String s, Object o1) throws ModelListenerException {}
    public void onAfterAddAssociation(Object o, String s, Object o1) throws ModelListenerException {}

    public void onBeforeRemoveAssociation(Object o, String s, Object o1) throws ModelListenerException {}
    public void onAfterRemoveAssociation(Object o, String s, Object o1) throws ModelListenerException {}


    /**
     * Verify that the organization has a parent org, and if not, assign the
     * NTER organization as its parent.
     *
     * @param org The newly created organization
     *
     * @throws com.liferay.portal.ModelListenerException
     */
    public void onBeforeCreate(Organization org) throws ModelListenerException {

        initHook();

        // if this is the creation of the parent org, do nothing
        // or if the organization already has a parent configured, do nothing
        if (org.getName().equals(NterConstants.DEFAULT_NTER_ORGANIZATION) ||
           (org.getParentOrganizationId() != OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID)) {
            return;
        }

        try {
            Organization nterOrg =
                    OrganizationLocalServiceUtil.getOrganization(
                            mCompanyId, NterConstants.DEFAULT_NTER_ORGANIZATION);
            org.setParentOrganizationId(nterOrg.getOrganizationId());
        }
        catch (Exception e) {
            mLog.error("Could not update " + org.getName() + " due to: " + e.getMessage());
        }
    }


    /**
     * Whenever a new organization is created, also create a corresponding
     * asset vocabulary.
     *
     * @param org The newly created organization
     *
     * @throws com.liferay.portal.ModelListenerException
     */
    public void onAfterCreate(Organization org) throws ModelListenerException {

        initHook();

        Map<Locale, String> titleMap = new HashMap<Locale, String>();
        Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

        String name = SetupTools.generateVocabularyName(org);
        titleMap.put(Locale.US, name);
        descriptionMap.put(Locale.US, org.getComments());

        // create the vocabulary
        try {
            AssetVocabularyLocalServiceUtil.addVocabulary(mUserId, titleMap,
                                descriptionMap, null, mServiceContext);
        }
        catch (DuplicateVocabularyException e) {
            // vocabulary already exists for this organization
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }
    

    public void onBeforeRemove(Organization org) throws ModelListenerException {}
    public void onAfterRemove(Organization org) throws ModelListenerException {}


    /**
     * Prevent the user from removing the NTER organization as the parent org.
     * If the user does remove a parent organization, reset that parent org to
     * be the NTER organization.
     *
     * @param org The organization that was updated
     * @throws com.liferay.portal.ModelListenerException
     */
    public void onBeforeUpdate(Organization org) throws ModelListenerException {
        initHook();

        // ensure that the NTER org is never given a parent organization
        if (org.getName().equals(NterConstants.DEFAULT_NTER_ORGANIZATION)) {
            if (org.getParentOrganizationId() != OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {
                org.setParentOrganizationId(OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);
                return;
            }
            else {
                return;
            }
        }

        // if an organization has their parent org removed, force them to
        // be under NTER
        if (org.getParentOrganizationId() == OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {
            try {
                Organization nterOrg =
                        OrganizationLocalServiceUtil.getOrganization(
                                mCompanyId, NterConstants.DEFAULT_NTER_ORGANIZATION);
                org.setParentOrganizationId(nterOrg.getOrganizationId());
            }
            catch (Exception e) {
                mLog.error("Could not update " + org.getName() + " due to: " + e.getMessage());
            }
        }
    }


    public void onAfterUpdate(Organization org) throws ModelListenerException {}


    /**
     * Initializes the hook by setting up common properties needed by all
     * routines.
     */
    protected void initHook() {
        try {
            mCompanyId = CompanyThreadLocal.getCompanyId();
            mUserId = UserLocalServiceUtil.getDefaultUserId(mCompanyId);
            mDefaultGroupId = GroupLocalServiceUtil.getCompanyGroup(mCompanyId).getGroupId();
        }
        catch (Exception e) {
            mUserId = 0;
            mDefaultGroupId = 0;
        }

        mServiceContext = new ServiceContext();
		mServiceContext.setAddGroupPermissions(true);
		mServiceContext.setAddGuestPermissions(true);
        mServiceContext.setScopeGroupId(mDefaultGroupId);
    }
}
