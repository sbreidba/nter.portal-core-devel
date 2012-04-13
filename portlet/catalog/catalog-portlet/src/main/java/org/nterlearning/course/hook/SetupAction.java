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

package org.nterlearning.course.hook;

import com.liferay.portal.DuplicateUserScreenNameException;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.*;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.*;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;
import org.nterlearning.exporter.ReportReaper;
import org.nterlearning.utils.ExpandoConstants;
import org.nterlearning.utils.PortalProperties;
import org.nterlearning.utils.PortalPropertiesUtil;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class SetupAction extends SimpleAction {

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

    protected void doRun(long companyId) {
        createExpandoTables(companyId);
        createTestUsers(companyId);
        configureSearchPermission(companyId);
        
        try {
        	ReportReaper.startReaping();
        }
        catch(Exception e) {
        	mLog.error(ExceptionUtils.getFullStackTrace(e));
        }
    }


    public void createExpandoTables(long companyId) {
        try {
            createAssetVocabExpandoTable(companyId);
            createUserExpandoTable(companyId);
        }
        catch (Exception e) {
            mLog.error("Could not create expando tables: " + e.getMessage());
        }
    }


    /**
     * Creates an expando table for the AssetVocabulary objects and adds an
     * updatedDate and vocabularyType attribute.
     *
     * @param companyId Default companyId
     *
     * @throws com.liferay.portal.kernel.exception.PortalException Standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.SystemException Standard Liferay Exception
     */
    protected void createAssetVocabExpandoTable(long companyId)
            throws PortalException, SystemException {

        String className = AssetVocabulary.class.getName();

        ExpandoTable expandoTable;

        try {
             expandoTable = ExpandoTableLocalServiceUtil.getTable(
                             companyId, className,
                             ExpandoConstants.ASSET_VOCABULARY_TABLENAME);
        }
        catch (NoSuchTableException e) {
            mLog.info("Creating entry in Expando tables for AssetVocabulary");
            expandoTable = ExpandoTableLocalServiceUtil.addTable(
                            companyId, className,
                            ExpandoConstants.ASSET_VOCABULARY_TABLENAME);
        }

        addExpandoColumn(expandoTable, ExpandoConstants.UPDATE_DATE, ExpandoColumnConstants.DATE);
        addExpandoColumn(expandoTable, ExpandoConstants.VOCABULARY_TYPE, ExpandoColumnConstants.STRING);
        addExpandoColumn(expandoTable, ExpandoConstants.FEED_REFERENCE_ID, ExpandoColumnConstants.LONG);
    }


    protected void createUserExpandoTable(long companyId)
            throws PortalException, SystemException {
        
        String className = User.class.getName();

        ExpandoTable expandoTable;
        try {
            expandoTable = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, className);
        }
        catch (NoSuchTableException e) {
            mLog.info("Creating entry in Expando tables for Users");
            expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, className);
        }

        addExpandoColumn(expandoTable, ExpandoConstants.REPUTATION_SCORE,
                         ExpandoColumnConstants.DOUBLE);
    }



    protected void configureSearchPermission(long companyId) {
        try {
            Role authorRole = RoleLocalServiceUtil.getRole(companyId, "Author");
            String companyIdStr = String.valueOf(companyId);
            Boolean hasPermission =
                    ResourcePermissionLocalServiceUtil.hasResourcePermission(
                            companyId, NterKeys.COMPONENT_SEARCH_PORTLET,
                            RoleConstants.TYPE_REGULAR, companyIdStr,
                            authorRole.getRoleId(), ActionKeys.VIEW);
            
            if (!hasPermission) {
                ResourcePermissionLocalServiceUtil.addResourcePermission(
                        companyId, NterKeys.COMPONENT_SEARCH_PORTLET,
                        RoleConstants.TYPE_REGULAR, companyIdStr,
                        authorRole.getRoleId(), ActionKeys.VIEW);
            }
        }
        catch (NoSuchRoleException e) {
            mLog.error("Could not find Author Role.  Ensure it has been created");
        }
        catch (Exception e) {
            mLog.error("Could not configure Author permissions: " + e.getMessage());
        }
    }


    /**
     * Adds an expando column for a particular expandoTable
     *
     * @param expandoTable Expando table to add the column to
     * @param columnName The column to add
     * @param type The corresponding ExpandoColumnConstants type
     */
    protected void addExpandoColumn(ExpandoTable expandoTable, String columnName, int type) {
        try {
            ExpandoColumnLocalServiceUtil.addColumn(expandoTable.getTableId(), columnName, type);
        }
        catch (DuplicateColumnNameException dcn) {
            mLog.info("Expando column already exists: " + expandoTable.getName() + "." + columnName);
        }
        catch (Exception e) {
            mLog.info(ExceptionUtils.getFullStackTrace(e)); //in case the exception is something else
        }
    }


    private User addUser(long companyId, String screenName, String firstName,
            String lastName, String password, String email, boolean male, String jobTitle,
            long[] roleIds) throws Exception {
        String emailAddress = screenName + email;

        Group guestGroup = GroupLocalServiceUtil.getGroup(
                companyId, GroupConstants.GUEST);

        long[] groupIds = new long[] {guestGroup.getGroupId()};
        long[] organizationIds = new long[] {
                OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID
        };

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);
        serviceContext.setCompanyId(companyId);
        long groupId = GroupLocalServiceUtil.getCompanyGroup(companyId).getGroupId();
        serviceContext.setScopeGroupId(groupId);

        User user;
        try {
            user = UserLocalServiceUtil.addUser(0, companyId, false, password,
                    password, false, screenName, emailAddress, 0,
                    StringPool.BLANK, Locale.US, firstName, StringPool.BLANK,
                    lastName, 0, 0, male, Calendar.JANUARY, 1, 1970, jobTitle,
                    groupIds, organizationIds, roleIds, null, false,
                    serviceContext);
        }
        catch (DuplicateUserScreenNameException e) {
            user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
        }

        return user;
    }


    private void addSsoInformation(User user) {
        try {
            UserIdMapper userMapper;
            try {
                userMapper =
                        UserIdMapperLocalServiceUtil.getUserIdMapper(
                                user.getUserId(), PortalPropertiesUtil.getSsoImplementation());
            }
            catch (NoSuchUserIdMapperException ne) {
                userMapper =
                        UserIdMapperLocalServiceUtil.updateUserIdMapper(
                                user.getUserId(), PortalPropertiesUtil.getSsoImplementation(),
                                null, user.getEmailAddress());
            }

            List<CourseRecord> records =
                    CourseRecordLocalServiceUtil.findBySingleSignOnValue(user.getEmailAddress());
            for (CourseRecord record : records) {
                record.setUserId(userMapper.getUserId());
                CourseRecordLocalServiceUtil.updateCourseRecord(record);
            }

        }
        catch (Exception e) {
        	mLog.info(ExceptionUtils.getFullStackTrace(e));
        }
    }
    

    /**
     * Creates a collection of test users.
     *
     * Note: These users should only be created for testing/development
     * purposes.  To ensure this, the property
     * <code>nter.test.user.create</code> must be set in the
     * <code>portal-ext.properties</code> file.
     *
     * @param companyId Company id used to create the test users against
     */
    private void createTestUsers(long companyId) {
        String createUser = PropsUtil.get(PortalProperties.NTER_CREATE_TEST_USERS);
        Boolean createTestUser = ((createUser != null) && createUser.equals("true"));

        Role userRole;
        long roleIds[];
        try {
            userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
            roleIds = new long[] {userRole.getRoleId()};
        }
        catch (Exception e) {
            roleIds = new long[] {0};
        }

        // note, these accounts are referenced in @org.nterlearning.course.hook.SetupAction
        // any changes to the usernames should be duplicated in that package
        String accounts[] = {"test", "nterdemo1", "nterdemo2"};
        String password = "nter@DEMO!";
        String email = "@nter.com";

        if (createTestUser) {
            for (String account : accounts) {
                try {
                    User user = addUser(companyId, account, account, "TestAccount",
                                        password, email, true, "Test User", roleIds);
                    addSsoInformation(user);
                }
                catch (Exception e) {
                    // probably account already exists, but log just in case
                	mLog.info(ExceptionUtils.getFullStackTrace(e));
                }
            }
        }
    }
}