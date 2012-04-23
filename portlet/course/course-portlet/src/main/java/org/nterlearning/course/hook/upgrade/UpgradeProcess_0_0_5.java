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

import org.nterlearning.course.util.FeedReferenceUtil;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

import java.util.List;

/**
 * href adjustment to Course
 */
public class UpgradeProcess_0_0_5 extends UpgradeProcess {

    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_5.class);

    public int getThreshold() {
        mLog.info("Version Threshold is 005");
        return 5;
    }

    protected void doUpgrade() throws Exception {
        mLog.info("Running SQL upgrade script 005");

        // copy the current href column into the fullTextHref column
        runSQL("update CATALOG_Course set fullTextHref = href where fullTextHref is null and href is not null");

        // before making any system changes, initialize indexers
        UpgradeProcessUtils.initializeIndexers();

        // create the sha-1 hash for existing feedReference objects
        List<FeedReference> feedRefs =
                FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for(FeedReference feedRef : feedRefs) {
            try {
                if ((feedRef.getHrefHash() == null) || feedRef.getHrefHash().equals("")) {
                    feedRef.setHrefHash(FeedReferenceUtil.generateHash(feedRef.getHref()));
                    FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
                }
            }
            catch (Exception e) {
                mLog.error(e.getMessage());
            }
        }
    }
}
