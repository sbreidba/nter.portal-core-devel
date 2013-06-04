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

package org.nterlearning.course.hook.upgrade;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserIdMapperLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.nterlearning.utils.ExpandoConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This upgrade process is designed to move the contents of the SingleSignOn
 * table to the Liferay supported UserIdMapper table and then drop the
 * CATALOG_SingleSignOn table.
 */
public class UpgradeProcess_0_0_9 extends UpgradeProcess {

    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_9.class);


    @Override
    public int getThreshold() {
        return 9;
    }


    @Override
    protected void doUpgrade() throws Exception {
        mLog.info("Running upgrade script for 009");

        // ensure both permission checker and all pre-requisites are in place
        UpgradeProcessUtils.initPermissionChecker();
        UpgradeProcessUtils.createExpandoTables();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs= null;

        try {
            conn = DataAccess.getConnection();
            ps = conn.prepareStatement("select * from information_schema.TABLES " +
                                       "where TABLE_NAME='CATALOG_SingleSignOn'");
            rs = ps.executeQuery();

            if (rs.next()) {
                updateUserIdMapperTable();

                runSQL("update CATALOG_CourseRecord " +
                       "set singleSignOnValue = " +
                       " (select CATALOG_SingleSignOn.singleSignOnValue " +
                       " from CATALOG_SingleSignOn, CATALOG_tempCourseRecord " +
                       " where CATALOG_SingleSignOn.singleSignOnId = CATALOG_tempCourseRecord.singleSignOnId " +
                       " and CATALOG_tempCourseRecord.courseRecordId = CATALOG_CourseRecord.courseRecordId)");
                runSQL("drop table CATALOG_SingleSignOn");
            }

            runSQL("drop table CATALOG_tempCourseRecord");
        }
        catch (SQLException sql) {
            mLog.error("Error updating CATALOG tables: " + sql.getMessage());
        }
		finally {
			DataAccess.cleanUp(conn, ps, rs);
		}
    }


    /**
     * Takes the users listed in the CATALOG_SingleSignOn table and creates a
     * UserIdMapper entry for each user.  A new UserIdMapper is only created if
     * the userId value in the SSO table is greater than 0, meaning that the
     * user has logged in and has been creted locally.
     */
    private void updateUserIdMapperTable() {

        long companyId = PortalUtil.getDefaultCompanyId();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs= null;

        try {
            conn = DataAccess.getConnection();
            ps = conn.prepareStatement("select userId, singleSignOnValue, reputationScore " +
                                       "from CATALOG_SingleSignOn where userId > 0");
            rs = ps.executeQuery();

            while (rs.next()) {
                long userId = rs.getLong("userId");
                String ssoId = rs.getString("singleSignOnValue");
                double reputationScore = rs.getDouble("reputationScore");

                // create a new userIdMapper for each user in the SSO table
                try {
                    UserIdMapperLocalServiceUtil.updateUserIdMapper(
                            userId, "Shibboleth", null, ssoId);
                }
                catch (Exception e) {
                    mLog.warn("Could not migrate user " + userId +
                              " to UserIdMapper table: " + e.getMessage());
                }

                // store the user's reputation score in an expando table
                try {
                    User user = UserLocalServiceUtil.getUser(userId);
                    ExpandoValueLocalServiceUtil.addValue(companyId,
                            User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
                            ExpandoConstants.REPUTATION_SCORE, user.getUserId(), reputationScore);
                }
                catch (Exception e){
                    mLog.warn("Could not update user " + userId + ": " + e.getMessage());
                }
            }
        }
        catch (SQLException sql) {
            mLog.error("Error updating UserIdMapper table: " + sql.getMessage());
        }
		finally {
			DataAccess.cleanUp(conn, ps, rs);
		}
    }
}
