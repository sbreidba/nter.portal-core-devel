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
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

import java.util.List;

/**
 * During the deployment of upgrades 005 and 006, there were a handful of
 * indexer errors which raised a number of exceptions.  These prevented the
 * upgrade scripts from working correctly.  This upgrade class is designed to
 * re-do the upgrades from 005 and 006.
 */
public class UpgradeProcess_0_0_7 extends UpgradeProcess {

    static Log mLog = LogFactoryUtil.getLog(UpgradeProcess_0_0_7.class);

    public int getThreshold() {
        mLog.info("Version Threshold is 007");
        return 7;
    }

    protected void doUpgrade() throws Exception {
        mLog.info("Running upgrade script for 007");

        UpgradeProcessUtils.initializeIndexers();

        // create the sha-1 hash for existing feedReference objects
        List<FeedReference> feedRefs =
                FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for(FeedReference feedRef : feedRefs) {
            try {
                feedRef.setHrefHash(FeedReferenceUtil.generateHash(feedRef.getHref()));
                FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
            }
            catch (Exception e) {
                mLog.error(e.getMessage());
            }

            List<Component> components =
                    ComponentLocalServiceUtil.findByFeedReferenceId(feedRef.getFeedReferenceId());
            for (Component component : components) {
                try {
                    if (component.getGroupId() != feedRef.getGroupId()) {
                        component.setGroupId(feedRef.getGroupId());
                        ComponentLocalServiceUtil.updateComponent(component);
                    }
                }
                catch (Exception e) {
                    mLog.error(e.getMessage());
                }
            }
        }

        List<Course> courses = CourseLocalServiceUtil.getCourses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Course course : courses) {
            if ((course.getFullTextHref() == null) || course.getFullTextHref().equals("")) {
                course.setFullTextHref(course.getHref());
                CourseLocalServiceUtil.updateCourse(course, true);
            }
        }
    }
}
