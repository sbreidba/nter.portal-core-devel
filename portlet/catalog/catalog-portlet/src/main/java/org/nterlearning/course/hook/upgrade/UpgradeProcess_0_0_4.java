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
import com.liferay.portal.kernel.upgrade.UpgradeProcess;


/**
 * CreateDate added to CourseReview and GlobalCourseReview
 */
public class UpgradeProcess_0_0_4 extends UpgradeProcess {
    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_4.class);


    public int getThreshold() {
        mLog.info("Version Threshold is 004");
        return 4;
    }

    protected void doUpgrade() throws Exception {
        mLog.info("Running SQL upgrade script 004");
        runSQL("update CATALOG_CourseReview  set createDate = modifiedDate where createDate is null");
        runSQL("update CATALOG_GlobalCourseReview set createDate = modifiedDate where createDate is null");
    }
}

