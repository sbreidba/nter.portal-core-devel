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

import org.nterlearning.course.enumerations.FeedRemovalReasonType;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

import java.util.List;

/**
 * A column has been added to the FeedReference table to annotate why a feed
 * has been marked as removed.  This class is designed to set a default value
 * of <code>REMOVED</code> for any feeds that have already been removed.
 */
public class UpgradeProcess_0_0_8 extends UpgradeProcess {

    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_8.class);

    public int getThreshold() {
        return 8;
    }


    protected void doUpgrade() throws Exception {
        mLog.info("Running upgrade script for 008");

        List<FeedReference> feedRefs =
                FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        String removedCode = FeedRemovalReasonType.REMOVED.getCode();

        for (FeedReference feedRef : feedRefs) {
            if (feedRef.isRemoved()) {
                feedRef.setRemovedReason(removedCode);
                FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
            }
        }
    }
}
