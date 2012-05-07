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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

import java.util.List;

/**
 * This class handles the upgrade process from migrating to database version 5
 * to database version 6.  This involves adding a groupId to all course
 * component objects.
 */
public class UpgradeProcess_0_0_6  extends UpgradeProcess {

    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_6.class);

    public int getThreshold() {
        mLog.info("Version Threshold is 006");
        return 6;
    }


    public void doUpgrade() throws Exception {
        mLog.info("Running SQL upgrade script 006");

        // before making any system changes, initialize indexers
        UpgradeProcessUtils.initializeIndexers();

        List<FeedReference> feedRefs =
                FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (FeedReference feedRef : feedRefs) {

            List<Component> components =
                    ComponentLocalServiceUtil.findByFeedReferenceId(feedRef.getFeedReferenceId());

            for (Component component : components) {
                try {
                    component.setGroupId(feedRef.getGroupId());
                    ComponentLocalServiceUtil.updateComponent(component);
                }
                catch (Exception e) {
                    mLog.error(e.getMessage());
                }
            }
        }        
    }
}
