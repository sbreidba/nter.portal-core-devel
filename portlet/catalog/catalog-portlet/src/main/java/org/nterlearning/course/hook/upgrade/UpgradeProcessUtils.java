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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.nterlearning.course.hook.SetupAction;
import org.nterlearning.course.search.ComponentIndexer;
import org.nterlearning.course.search.CourseIndexer;

/**
 * This class is designed to provide common tools and utilities to the various
 * upgrade scripts.
 */
public class UpgradeProcessUtils {

    private static Log mLog = LogFactoryUtil.getLog(UpgradeProcessUtils.class);


    /**
     * This method initializes the indexers.  For some reason, the upgrade
     * scripts are called and processed prior to the indexers being initialized.
     * This method forces that initializations.
     */
    public static void initializeIndexers() {
        IndexerRegistryUtil.register(new CourseIndexer());
        IndexerRegistryUtil.register(new ComponentIndexer());
    }


    /**
     * This method creates a workaround to force the PermissionChecker to
     * initialize. During startup scripts, it may not have been initialized yet.
     */
    public static void initPermissionChecker() {
        try {
            if (PermissionThreadLocal.getPermissionChecker() == null) {
                long companyId = PortalUtil.getDefaultCompanyId();
                String defaultAdminScreenName =
                        PrefsPropsUtil.getString(PropsKeys.DEFAULT_ADMIN_SCREEN_NAME);
                User defaultAdmin =
                        UserLocalServiceUtil.getUserByScreenName(companyId, defaultAdminScreenName);

                PermissionThreadLocal.setPermissionChecker(
                    PermissionCheckerFactoryUtil.create(defaultAdmin, true));
            }
            else {
                mLog.info("Permission checker already initialized");
            }
        }
        catch (Exception e) {
            mLog.error("Could not initialize permissionChecker: " + e.getMessage());
        }
    }


    public static void runPortletSetupAction() {
        long companyId = PortalUtil.getDefaultCompanyId();

        try {
            SetupAction setupAction = new SetupAction();
            setupAction.run(new String[]{String.valueOf(companyId)});
        }
        catch (Exception e) {
            mLog.error("Could not run the SetupAction: " + e.getMessage());
        }
    }


    public static void createExpandoTables() {
        long companyId = PortalUtil.getDefaultCompanyId();
        SetupAction setupAction = new SetupAction();
        setupAction.createExpandoTables(companyId);
    }
}
