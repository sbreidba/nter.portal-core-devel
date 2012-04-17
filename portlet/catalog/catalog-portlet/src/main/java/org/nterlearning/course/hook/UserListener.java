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

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.User;

import com.liferay.portal.model.UserIdMapper;
import com.liferay.portal.service.UserIdMapperLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.utils.ExpandoConstants;
import org.nterlearning.utils.PortalPropertiesUtil;

import java.util.List;

/**
 * This class is designed to detect when changes have occurred for the User
 * object and update the various course portlets accordingly.
 */
public class UserListener implements ModelListener<User> {

    static Log mLog = LogFactoryUtil.getLog(UserListener.class);

    /**
     * After a user has been deleted, remove the userId from any course records
     * that may have been processed.  This prevents the records from being re-
     * created during the next feed processing event.  Also, remove any course
     * reviews that the user may have submitted.
     *
     * @param user The freshly deleted user
     *
     * @throws com.liferay.portal.ModelListenerException Standard Liferay exception
     */
    public void onAfterRemove(User user) throws ModelListenerException {

        try {
            // remove the user's reputation score
            ExpandoValueLocalServiceUtil.deleteValue(user.getCompanyId(),
                    User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
                    ExpandoConstants.REPUTATION_SCORE, user.getUserId());
        }
        catch (Exception e) {
            mLog.warn("Could not remove the reputation score for user: " + user.getUserId());
        }
        
        try {
            // un-associate all student records for this user
            List<CourseRecord> records = CourseRecordLocalServiceUtil.findByUserId(user.getUserId());
            for (CourseRecord record : records) {
                record.setUserId(0);
                CourseRecordLocalServiceUtil.updateCourseRecord(record);
            }

            // remove all course reviews made by this user
            List<CourseReview> reviews = CourseReviewLocalServiceUtil.findByUserId(user.getUserId());
            for (CourseReview review : reviews) {
                CourseReviewLocalServiceUtil.deleteCourseReview(review);
            }
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    /**
     * When a new user is created, add a default reputation score and attempt to
     * assign all previously unassigned student records to the student.
     *
     * @param user The user account that was just created.
     * 
     * @throws com.liferay.portal.ModelListenerException
     */
    public void onAfterCreate(User user) throws ModelListenerException {
        try {
            // add a default reputation score
            ExpandoValueLocalServiceUtil.addValue(user.getCompanyId(),
                User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
                ExpandoConstants.REPUTATION_SCORE, user.getUserId(), 0.0);

            // find all student records for this user and update the userId
            String ssoImplementation = PortalPropertiesUtil.getSsoImplementation();
            UserIdMapper userMapper =
                    UserIdMapperLocalServiceUtil.getUserIdMapper(user.getUserId(), ssoImplementation);
            List<CourseRecord> records =
                    CourseRecordLocalServiceUtil.findBySingleSignOnValue(userMapper.getExternalUserId());

            for (CourseRecord record : records) {
                record.setUserId(user.getUserId());
                CourseRecordLocalServiceUtil.updateCourseRecord(record);
            }
        }
        catch (NoSuchUserIdMapperException e) {
            mLog.warn("Could not find the user " + user.getUserId() + " in the UserIdMapper table");
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    public void onBeforeAddAssociation(Object o, String s, Object o1) throws ModelListenerException {}
    public void onAfterAddAssociation(Object o, String s, Object o1) throws ModelListenerException {}

    public void onBeforeRemoveAssociation(Object o, String s, Object o1) throws ModelListenerException {}
    public void onAfterRemoveAssociation(Object o, String s, Object o1) throws ModelListenerException {}

    public void onBeforeCreate(User user) throws ModelListenerException {}

    public void onBeforeRemove(User user) throws ModelListenerException {}

    public void onBeforeUpdate(User user) throws ModelListenerException {}
    public void onAfterUpdate(User user) throws ModelListenerException {}

}