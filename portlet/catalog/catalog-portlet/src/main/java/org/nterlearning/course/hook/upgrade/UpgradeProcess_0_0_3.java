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

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;


/**
 *
 * Course review modifications.
 */
public class UpgradeProcess_0_0_3 extends UpgradeProcess {
    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_3.class);


    public int getThreshold() {
        mLog.info("Version Threshold is 003");
        return 3;
    }

    protected void doUpgrade() throws Exception {
        mLog.info("Running SQL upgrade script 003");
        try {
            // verify an upgrade needed by checking to see if course reviews exist.
            int courseReviewCount = CourseReviewLocalServiceUtil.getCourseReviewsCount();

            if (courseReviewCount > 0) {
                DB database = DBFactoryUtil.getDB();
                mLog.info("Database is " + database.getType());

                runSQL("update CATALOG_CourseReview set modifiedDate = " +
                        "(select createDate from CATALOG_CourseReviewSave " +
                        "where CATALOG_CourseReview.courseReviewId = CATALOG_CourseReviewSave.courseReviewId)");
                mLog.info("Update of modifiedDate complete.");

                runSQL("update CATALOG_CourseReview set weightedScore = " +
                        "(select score from CATALOG_CourseReviewSave " +
                        "where CATALOG_CourseReview.courseReviewId = CATALOG_CourseReviewSave.courseReviewId)");
                mLog.info("Update of weightedScore complete.");

                //sqlserver and mysql require different syntax for update
                //sqlserver requires len as function, mysql requires length as function

                if (database.getType().equalsIgnoreCase("mysql")) {
                    runSQL("update CATALOG_CourseReview set summary = " +
                            "(select replace(substring(summary,123,LENGTH(summary)-123),'</Summary></root','') " +
                            "from CATALOG_CourseReviewSave " +
                            "where CATALOG_CourseReview.courseReviewId = CATALOG_CourseReviewSave.courseReviewId)");
                    runSQL("update CATALOG_CourseReview set content = " +
                            "(select replace(substring(description,127,LENGTH(description)-127),'</Description></root','') " +
                            "from CATALOG_CourseReviewSave " +
                            "where CATALOG_CourseReview.courseReviewId = CATALOG_CourseReviewSave.courseReviewId)");
                    mLog.info("Update of summary and content complete.");

                } else {
                    runSQL("update CATALOG_CourseReview set summary = " +
                            "(select replace(substring(summary,123,LEN(summary)-123),'</Summary></root','') " +
                            "from CATALOG_CourseReviewSave " +
                            "where CATALOG_CourseReview.courseReviewId = CATALOG_CourseReviewSave.courseReviewId)");

                    runSQL("update CATALOG_CourseReview set content = " +
                            "(select replace(substring(description,127,LEN(description)-127),'</Description></root','') " +
                            "from CATALOG_CourseReviewSave " +
                            "where CATALOG_CourseReview.courseReviewId = CATALOG_CourseReviewSave.courseReviewId)");
                    mLog.info("Update of summary and content complete.");
                }
            }
        } catch (Exception e) {
            mLog.info("Error -  Existing data in CATALOG_CourseReview not upgraded. ");
        }
    }
}

