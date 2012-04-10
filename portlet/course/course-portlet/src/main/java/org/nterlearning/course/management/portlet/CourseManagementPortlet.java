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


package org.nterlearning.course.management.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.utils.ExpandoConstants;
import org.nterlearning.utils.ReviewUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.List;

/**
 * The portlet class containing the ProcessAction handlers for the course
 * management portlet.
 *
 * @author Robert Bailey
 */
public class CourseManagementPortlet extends MVCPortlet {

    private static double accessCountWeight = 0.0;
    private static double completedCountWeight = 0.0;
    private static double averageScoreWeight = 0.0;


    public void init()
            throws PortletException {

        accessCountWeight = Double.valueOf(getInitParameter("access-count-weight"));
        completedCountWeight = Double.valueOf(getInitParameter("completed-count-weight"));
        averageScoreWeight = Double.valueOf(getInitParameter("average-score-weight"));

        CourseManagementTask.getInstance().initiateScheduledTask();

        super.init();
    }


    @Override
    public void destroy() {
        CourseManagementTask.getInstance().shutdownTask();
        super.destroy();
    }


    /**
     * @return the initialization parameters for popularity equation weight
     * values.
     */
    public static double getAccessCountWeight() {
        return accessCountWeight;
    }


    public static double getCompletedCountWeight() {
        return completedCountWeight;
    }


    public static double getAverageScoreWeight() {
        return averageScoreWeight;
    }


    public static void updateUserHelpfulness() throws SystemException {
        List<User> users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (User user : users) {
            List<CourseReview> reviews =
                    CourseReviewLocalServiceUtil.findByUserId(user.getUserId());
            int positive = 0;
            int total = 0;
            for (CourseReview review : reviews) {
                List<RatingsEntry> entries =
                        RatingsEntryLocalServiceUtil.getEntries(CourseReview.class.getName(),
                                                                review.getCourseReviewId());
                total += entries.size();

                for (RatingsEntry entry : entries) {
                    if (entry.getScore() > 0) {
                        positive++;
                    }
                }
            }

            try {
                ExpandoValueLocalServiceUtil.addValue(user.getCompanyId(),
                    User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
                    ExpandoConstants.REPUTATION_SCORE, user.getUserId(),
                    ReviewUtil.wilsonScore(positive, total, 0.05));
            }
            catch (Exception e) {
                _log.warn("Could not update " + ExpandoConstants.REPUTATION_SCORE +
                        "for user: " + user.getUserId());
            }
        }
    }


    public void markCourseAsFeatured(ActionRequest request, ActionResponse response)
            throws Exception {

        // TODO validate that the calling user has permission to edit the course
        setCourseFeaturedStatus(request, response, 1.0);
    }


    public void markCourseAsNotFeatured(ActionRequest request, ActionResponse response)
            throws Exception {

        // TODO validate that the calling user has permission to edit the course
        setCourseFeaturedStatus(request, response, 0.0);
    }


    protected void setCourseFeaturedStatus(ActionRequest request, ActionResponse response, double featured)
            throws Exception {

        // TODO validate that the calling user has permission to edit the course
        long courseId = ParamUtil.getLong(request, "resourcePrimKey");
        if (Validator.isNotNull(courseId)) {
            _log.debug("Setting course id " + courseId + " featured status value " + featured);
            CourseLocalServiceUtil.setCourseFeaturedStatus(courseId, featured);
        }
        else {
            SessionErrors.add(request, "error-marking-featured");
        }
    }


    private static Log _log = LogFactoryUtil.getLog(CourseManagementPortlet.class);
}