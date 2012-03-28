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


package org.nterlearning.hook.setup;

import com.liferay.portal.DuplicateOrganizationException;
import com.liferay.portal.DuplicateUserScreenNameException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.monitoring.statistics.SummaryStatistics;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.*;
import com.liferay.portal.service.*;
import com.liferay.portlet.asset.DuplicateCategoryException;
import com.liferay.portlet.asset.DuplicateVocabularyException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.rmi.registry.LocateRegistry;
import java.util.*;

/**
 * This class is designed to ensure that certain organizations and groups are
 * created for all NTER instances.  It runs when NTER starts. 
 * 
 * @author lmoulder
 */
public class SetupAction extends SimpleAction {
    
    protected long mCompanyId;
    protected long mDefaultUserId;
    protected long mDefaultGroupId;
    protected ServiceContext mServiceContext;

    static Log mLog = LogFactoryUtil.getLog(SetupAction.class);
    

    @Override
	public void run(String[] args) throws ActionException {
		try {
			doRun(GetterUtil.getLong(args[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}		
	}
	
	
	protected void doRun(long companyId)
            throws PortalException, SystemException {
        
        initHook(companyId);

        try {
            updateGuestCommunity();
		    createDefaultOrganizations();

            createDefaultWebContent();
            synchronizeTaxonomy();
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
        
		createDefaultUsers(companyId);
        createDefaultAdministrators();

        disableUnusedLiferayPortlets(companyId);
	}


    /**
     * Initializes the hook by setting some common properties needed by all
     * routines.
     *
     * @param companyId The default company
     */
    protected void initHook(long companyId) {

        mCompanyId = companyId;
        
        try {
            mDefaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
            mDefaultGroupId =
                    GroupLocalServiceUtil.getCompanyGroup(mCompanyId).getGroupId();
        }
        catch (Exception e) {
            mDefaultUserId = 0;
            mDefaultGroupId = 0;
        }       
        
        mServiceContext = new ServiceContext();
		mServiceContext.setAddGroupPermissions(true);
		mServiceContext.setAddGuestPermissions(true);
        mServiceContext.setCompanyId(mCompanyId);
        mServiceContext.setScopeGroupId(mDefaultGroupId);
    }

	
	/**
	 * Creates the collection of default organizations needed for every NTER 
	 * instance.
     *
     * @throws Exception Generic Java exception (should never be thrown)
	 */
	protected void createDefaultOrganizations()
        throws Exception {
        
		long parentOrgId = OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;
		String orgType = OrganizationConstants.TYPE_REGULAR_ORGANIZATION;

        // NTER Organization
        Organization orgNTER =
                createOrganization(mDefaultUserId, parentOrgId, mCompanyId,
                       "NTER", orgType, true, 0, 0, null);
    }


    /**
     * Applies the appropriate layout template to the guest group and configures
     * the virtual host. 
     */
    protected void updateGuestCommunity() {
        Group guestGroup = null;
        try {
            guestGroup = GroupLocalServiceUtil.getGroup(mCompanyId, "Guest");
             List<Layout> guestLayouts =
                    LayoutLocalServiceUtil.getLayouts(guestGroup.getGroupId(), false);
            // if size == 1, then the lar file hasn't been applied yet, so apply it
	        //liferay 61 requires deleteLayouts method signature to include a serviceContext
			ServiceContext serviceContext = new ServiceContext();

            //TODO create new nter.lar for 61, then activate following if statement
            if (guestLayouts.size() == 1) {
                LayoutLocalServiceUtil.deleteLayouts(guestGroup.getGroupId(), false, serviceContext);
                SetupTools.addDefaultLayoutsByLar(mDefaultUserId,
                                guestGroup.getGroupId(), false,
                               NterConstants.DEFAULT_LAR_FILENAME);
            }
        }
        catch (NoSuchGroupException e) {
            // guest is a liferay group, this should never happen
            mLog.error("Could not find guest group because: " + e.getMessage());
        }
        catch (Exception se) {
            mLog.error("Error updating guest group: " + se.getMessage());
        }
    }
	
	
	/**
	 * Creates a collection of default users needed for every NTER instance.
     *
	 * @param companyId Company creating the users
     *
	 * @throws com.liferay.portal.kernel.exception.SystemException Liferay's SystemException
	 * @throws com.liferay.portal.kernel.exception.PortalException Liferay's PortalException
	 */
	protected void createDefaultUsers(long companyId)
			throws PortalException, SystemException {
		
		// Default roles
		Role adminRole = RoleLocalServiceUtil.getRole(
                                    companyId, RoleConstants.ADMINISTRATOR);
		Role powerUserRole = RoleLocalServiceUtil.getRole(
                                    companyId, RoleConstants.POWER_USER);
		long[] roleIds =
                new long[] {adminRole.getRoleId(), powerUserRole.getRoleId()};
		
		try {
			addUser(companyId, "NTERAdmin", "NTER", "Admin", "admin@NTER",
                    true, "Administrator", roleIds);
		}
		catch (Exception e) {
            // user already exists
		}
	}


    protected void createDefaultAdministrators() {
        try {
		    Role adminRole = RoleLocalServiceUtil.getRole(
                                    mCompanyId, RoleConstants.ADMINISTRATOR);
		    Role powerUserRole = RoleLocalServiceUtil.getRole(
                                    mCompanyId, RoleConstants.POWER_USER);
		    long[] roleIds =
                new long[] {adminRole.getRoleId(), powerUserRole.getRoleId()};

            String admins[] = PrefsPropsUtil.getStringArray(mCompanyId, "nter.default.admin.accounts", ",");
            for (String admin : admins) {
                admin = admin.trim();
                String screenName = admin.substring(0, admin.indexOf("@"));

                try {
                    addUser(mCompanyId, screenName, screenName, screenName, admin,
                            admin, true, "Administrator", roleIds);
                }
                catch (Exception e) {
                    mLog.error("Error creating user: " + admin);
                }
            }
        }
        catch (Exception e) {
            mLog.error("Could not read 'nter.default.admin.accounts' from portal properties");
        }
    }


    protected void createDefaultWebContent() throws Exception {

        Group guestGroup =
                GroupLocalServiceUtil.getGroup(mCompanyId, GroupConstants.GUEST);
        Calendar now = Calendar.getInstance();

        String title;
        String content = "<?xml version='1.0' encoding='UTF-8'?>" +
                "<root><static-content><![CDATA[<p></p>]]></static-content></root>";

        title = "VERSION";
        Locale defaultLocale = LocaleUtil.getDefault();
        Map<Locale, String> titleMap = new HashMap<Locale, String>();
        titleMap.put(defaultLocale,title);
        try {
           JournalArticleLocalServiceUtil.getArticle(guestGroup.getGroupId(), title);
        }
        catch (NoSuchArticleException e) {
            // could not find web content, create it
            //use title to fill in articleId
            //autoArticleId is false, version is 1.0
            //Liferay 61 added classNameId, classPk, layoutUuid to signature
            //Liferay 61 changed title and description to locale attributes
            JournalArticleLocalServiceUtil.addArticle(mDefaultUserId,
                        guestGroup.getGroupId(),  0, 0,
                        title, false, 1.0, titleMap, null,
                        content, "general", null, null, null, now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.YEAR),
                        now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),
                        0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true, false, false,
                        null, null, null, title, mServiceContext);
        }
    }


    /**
     * Synchronizes the organizations and vocabulary taxonomy by ensuring that
     * every org has a corresponding vocabulary.
     */
    protected void synchronizeTaxonomy() {

        List<Organization> orgs;
        String orgName;
        Map<Locale, String> titleMap = new HashMap<Locale, String>();
        Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
 
        try {
            orgs = OrganizationLocalServiceUtil.getOrganizations(
                                    QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        }
        catch (SystemException se) {
            orgs = null;
        }

        for (Organization org : orgs) {
            orgName = SetupTools.generateVocabularyName(org);
            titleMap.put(Locale.US, orgName);
            descriptionMap.put(Locale.US, org.getComments());

            try {
                AssetVocabularyLocalServiceUtil.addVocabulary(mDefaultUserId,
                            titleMap, descriptionMap, null, mServiceContext);
            }
            catch (DuplicateVocabularyException e) {
                // vocabulary already exists for this organization
            }
            catch (Exception e) {
                mLog.error(e.getMessage());
            }
            
            titleMap.clear();
            descriptionMap.clear();
        }
    }

	
	/**
	 * Creates and returns an organization in Liferay.  If the organization 
	 * already exists, it is return.
	 * 
	 * @param userId User id of the User creating the organization
	 * @param parentOrgId Parent organization.  If this is the highest org, use 0.
	 * @param mCompanyId Company ID that is creating the organization
	 * @param name Organization's name
	 * @param type Type of organization to create, default is
     * OrganizationConstants.TYPE_REGULAR_ORGANIZATION
	 * @param recursable Is the organization recurable? Default is true
	 * @param regionId The Region Id of the organization. Use 0 for default.
	 * @param countryId The country ID of the organization Use 0 for default.
	 * @param comments Organization's comments (may be null).
	 * 
	 * @return The newly created organization.
	 * 
	 * @throws Exception Rare chance of throwing a generic exception
	 */
	private Organization createOrganization(long userId, long parentOrgId,
            long mCompanyId, String name, String type, boolean recursable,
            long regionId, long countryId, String comments)
            throws Exception {
		
		int statusId = GetterUtil.getInteger(
                PropsUtil.get("sql.data.com.liferay.portal.model.ListType.organization.status"));

        Organization org = null;
		try {
			org = OrganizationLocalServiceUtil.addOrganization(userId,
                    parentOrgId, name, type, recursable, regionId, countryId,
                    statusId, comments, true, mServiceContext);
		}
		catch (DuplicateOrganizationException e) {
			org = OrganizationLocalServiceUtil.getOrganization(mCompanyId, name);
		}
        catch (Exception e) {
            mLog.error("Error processing " + name +"\n" + e.getMessage());            
        }

        return org;
	}
	
	
	/**
	 * Creates a new user.  
	 * 
	 * @param companyId Use's associated company ID.
	 * @param screenName User's screen name
	 * @param firstName User's first name
	 * @param lastName User's last name
     * @param password User's password
	 * @param male true if male, false if female
	 * @param jobTitle User's job title (may be null)
	 * @param roleIds Array of roles this user will have access to.
	 * 
	 * @return The new user.
     *
     * @throws Exception generic exception
	 */
	protected User addUser(long companyId, String screenName, String firstName,
						   String lastName, String password, boolean male, 
						   String jobTitle, long[] roleIds) throws Exception {

        String webId = PrefsPropsUtil.getString(companyId, "company.default.web.id");
		String emailAddress = screenName + "@" + webId;

        return addUser(companyId, screenName, firstName, lastName, emailAddress,
                       password, male, jobTitle, roleIds);
	}


	protected User addUser(long companyId, String screenName, String firstName,
						   String lastName, String emailAddress, String password,
                           boolean male, String jobTitle, long[] roleIds) throws Exception {

		Group guestGroup = GroupLocalServiceUtil.getGroup(mCompanyId, GroupConstants.GUEST);

		long[] groupIds = new long[] {guestGroup.getGroupId()};
        long[] organizationIds = new long[] {
                OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID
        };

		User user;
		try {
			user = UserLocalServiceUtil.addUser(0, companyId, false, password,
                    password, false, screenName, emailAddress, 0,
                    StringPool.BLANK, Locale.US, firstName, StringPool.BLANK,
                    lastName, 0, 0, male, Calendar.JANUARY, 1, 1970, jobTitle,
                    groupIds, organizationIds, roleIds, null, false,
                    mServiceContext);
            UserIdMapperLocalServiceUtil.updateUserIdMapper(user.getUserId(), "",
                                                            null, emailAddress);
		}
		catch (DuplicateUserScreenNameException e) {
			user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
		}

		return user;
    }


    protected AssetVocabulary createAssetVocabulary(long userId,
            Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
            String settings, ServiceContext serviceContext)
            throws PortalException, SystemException{

        try {
            return AssetVocabularyLocalServiceUtil.addVocabulary(userId,
                        titleMap, descriptionMap, settings, serviceContext);
        }
        catch (DuplicateVocabularyException e) {
            return AssetVocabularyLocalServiceUtil.getGroupVocabulary(
                        mDefaultGroupId, titleMap.get(Locale.US));
        }
        catch (Exception e) {
            return null;
        }
    }


    protected AssetCategory createAssetCategory(long userId,
        long parentCategoryId, Map<Locale,String> titleMap,
        Map<Locale,String> descriptionMap, long vocabularyId,
        String[] categoryProperties, ServiceContext serviceContext)
        throws PortalException, SystemException {

        try {
            return AssetCategoryLocalServiceUtil.addCategory( userId,
                    parentCategoryId, titleMap, descriptionMap,vocabularyId, categoryProperties,
                    serviceContext);     
        }
        catch (DuplicateCategoryException dce) {
            return SetupTools.findCategoryByNameAndVocabulary(
                            titleMap.get(Locale.US), vocabularyId);
        }
        catch (Exception e) {
            return null;
        }
    }


    private void disableUnusedLiferayPortlets(long companyId) {
        try {
            Portlet passwordPolicy =
                    PortletLocalServiceUtil.updatePortlet(companyId, "129", "", false);
        }
        catch (Exception e) {
            mLog.error("Could not update Password Policy portlet");
        }
    }
}