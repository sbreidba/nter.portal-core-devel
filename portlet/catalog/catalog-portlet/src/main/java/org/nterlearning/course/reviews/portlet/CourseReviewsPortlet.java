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


package org.nterlearning.course.reviews.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.flags.service.FlagsEntryServiceUtil;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil;
import org.nterlearning.utils.ReviewUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.WindowStateException;
import java.io.IOException;

@SuppressWarnings("unused")
public class CourseReviewsPortlet extends MVCPortlet {

	private static final int CONTENT_MAX_LENGTH = 3999;
	private static final int SUMMARY_MAX_LENGTH = 250;

	/**
	 * Adds or updates a course in the database. If "review-reviewId" is set to, an existing reviewId, that review will be
	 * updated. Otherwise a review will be created for the current user. <br> Parameters: <ul> <li>review-reviewId</li>
	 * <li>review-summary</li> <li>review-description</li> <li>review-rating</li> <li>review-classPK</li> </ul>
	 *
	 * @param request
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 */
	public void updateCourseReview(ActionRequest request, ActionResponse response)
			throws SystemException, PortalException {

		CourseReviewUpdateParameters params = new CourseReviewUpdateParameters(request);

		checkCourseReviewErrors(request, params);

		if (SessionErrors.isEmpty(request)) { // no errors
			updateCourseReview(request, params);
		}
		setCourseReviewUpdateResponse(request, response, params);
	}

	private void setCourseReviewUpdateResponse(ActionRequest request, ActionResponse response,
			CourseReviewUpdateParameters params)
			throws SystemException, PortalException {
		// Set response type
		if (!ParamUtil.getBoolean(request, "ajax") && SessionErrors.isEmpty(request)) { // Is not AJAX response
			redirect(request, response);
		} else if (ParamUtil.getBoolean(request, "ajax")) {
			response.setRenderParameter(NterKeys.REVIEW_CONTENT, params.content);
			response.setRenderParameter(NterKeys.REVIEW_SUMMARY, "");
			response.setRenderParameter(NterKeys.REVIEW_RATING, String.valueOf(params.rating));
			Course course = CourseLocalServiceUtil.getCourse(params.classPK);
			response.setRenderParameter(NterKeys.AVERAGE_RATING,
					String.valueOf(ReviewUtil.getCourseAverageRating(course)));
			String[] histogram = new String[]{
				String.valueOf(course.getOneStarRateCount()),
				String.valueOf(course.getTwoStarRateCount()),
				String.valueOf(course.getThreeStarRateCount()),
				String.valueOf(course.getFourStarRateCount()),
				String.valueOf(course.getFiveStarRateCount())
			};
			response.setRenderParameter(NterKeys.REVIEW_HISTOGRAM, histogram);
			response.setRenderParameter(NterKeys.REVIEW_COUNT, 
					String.valueOf(ReviewUtil.getCourseRatingCount(course)));
			response.setRenderParameter("jspPage", "/course-reviews/jsp/ajax-update-review.jsp");
			try {
				response.setWindowState(LiferayWindowState.EXCLUSIVE);
			} catch (WindowStateException e) {
				_log.error(e);
			}
		} else { // has errors
			response.setRenderParameter(NterKeys.REVIEW_CONTENT, params.content);
			response.setRenderParameter(NterKeys.REVIEW_RATING, String.valueOf(params.rating));
			response.setRenderParameter(NterKeys.REVIEW_CLASSPK, String.valueOf(params.classPK));
			if (params.reviewId != 0) {
				response.setRenderParameter(NterKeys.REVIEW_ID, String.valueOf(params.reviewId));
			}
			if (Validator.isNotNull(request.getParameter("jspPage"))) {
				response.setRenderParameter("jspPage", request.getParameter("jspPage"));
			}
		}
	}

	private void checkCourseReviewErrors(ActionRequest request, CourseReviewUpdateParameters params)
			throws PortalException, SystemException {
		// Set session errors
		if (!request.getMethod().equalsIgnoreCase("post")) SessionErrors.add(request, "error-method-get");
		if (Validator.isNull(params.content)) SessionErrors.add(request, "error-review-no-content");
		//if (Validator.isNull(params.summary)) SessionErrors.add(request, "error-review-no-summary");
		if (params.rating == 0) SessionErrors.add(request, "error-review-no-rating");
		if (params.content.length() > CONTENT_MAX_LENGTH) SessionErrors.add(request, "error-review-content-length");
		//remove summary; may reactivate based on user feedback
        // if (params.summary.length() > SUMMARY_MAX_LENGTH) SessionErrors.add(request, "error-review-summary-length");
		if (CourseLocalServiceUtil.getCourse(params.classPK).isRemoved() && !params.themeDisplay.getPermissionChecker()
				.isOmniadmin()) SessionErrors.add(request, "error-course-removed");
	}

	private void updateCourseReview(ActionRequest request, CourseReviewUpdateParameters params)
			throws PortalException, SystemException {
		// Update database
		String content = params.content;
		//remove summary; may reactivate based on user feedback
        // String summary = params.summary;
        String summary = "";
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(RatingsEntryLocalServiceUtil.class.getName(), request);
		// if new review, add it to the database and update the course ratings
		// otherwise, update the existing review and update the course ratings
		if (params.reviewId == 0) {            
			CourseReviewLocalServiceUtil
					.appendCourseReview(params.userId, params.reviewId, params.classPK, summary, content,params.rating,
							ServiceContextFactory.getInstance(CourseReview.class.toString(), request));
		} else {
			CourseReview review = CourseReviewLocalServiceUtil.getCourseReview(params.reviewId);
			params.userId = review.getUserId();
			CourseReviewLocalServiceUtil
					.appendCourseReview(params.userId, params.reviewId, params.classPK, summary, content,params.rating,
							ServiceContextFactory.getInstance(CourseReview.class.toString(), request));
		}
	}

	/**
	 * Updates the rating of a Course Review. Must be a post. Users cannot rate their own reviews. Expected paramaters are:
	 * <ul> <li>score</li> <li>review-reviewId</li> </ul>
	 *
	 * @param request
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 */
	public void updateReviewRating(ActionRequest request, ActionResponse response)
			throws SystemException, PortalException {
		// the liferay "star" score
		// get params
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
		CourseReview review = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
		String className = CourseReview.class.getName();
		long userId = themeDisplay.getUserId();
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(RatingsEntryLocalServiceUtil.class.getName(), request);
		long score = ParamUtil.getLong(request, "score");
		// check errors
		if (!request.getMethod().equalsIgnoreCase("post")) {
			SessionErrors.add(request, "error-method-get");
		}
		if (userId == review.getUserId()) {
			SessionErrors.add(request, "error-user-denied");
		}
		if (CourseLocalServiceUtil.getCourse(review.getCourseId()).isRemoved()) {
			SessionErrors.add(request, "error-course-removed");
		}
		// update database
		if (SessionErrors.isEmpty(request)) {
			if (score == 0) {
				RatingsEntryLocalServiceUtil.deleteEntry(userId, className, reviewId);
			} else {
				RatingsEntryLocalServiceUtil.updateEntry(userId, className, reviewId, score, serviceContext);
			}
		}
		// set response
		if (ParamUtil.getBoolean(request, "ajax")) {
			response.setRenderParameter("reviewId", String.valueOf(reviewId));
			response.setRenderParameter("jspPage", "/course-reviews/jsp/ajax-review-rating.jsp");
		} else {
			redirect(request, response);
		}
	}

	/**
	 * Unmarks a course as removed and returns an ajax response
	 *
	 * @param request
	 * @param response
     * @throws com.liferay.portal.kernel.exception.PortalException
	 */
	public void undoDeleteCourseReview(ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		if (!request.getMethod().equalsIgnoreCase("post")) {
			SessionErrors.add(request, "error-method-get");
		}
		long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
		try {
			CourseReviewLocalServiceUtil.setRemoved(reviewId, false);
		}catch (SystemException e) {
			SessionErrors.add(request, "error-review-not-found");
		}
		try {
			response.setWindowState(LiferayWindowState.EXCLUSIVE);
		}catch (WindowStateException e) {
			_log.error(e);
		}
		CourseReviewUpdateParameters params = new CourseReviewUpdateParameters(request);
		setCourseReviewUpdateResponse(request, response, params);
	}

	/**
	 * Deletes a Course Rating from the database. Process Action. <br> Required parameters: <ul> <li>review-classPK</li>
	 * </ul>
	 *
	 * @param request
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 */
	public void deleteCourseRating(ActionRequest request, ActionResponse response)
			throws SystemException, PortalException {

		if (!request.getMethod().equalsIgnoreCase("post")) {
			SessionErrors.add(request, "error-method-get");
		}

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
		CourseReview review = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
		Course course = CourseLocalServiceUtil.getCourse(review.getCourseId());
		if (course.isRemoved() && !themeDisplay.getPermissionChecker().isOmniadmin()) {
			SessionErrors.add(request, "error-course-removed");
		}
		if (!themeDisplay.getPermissionChecker()
				.hasPermission(review.getGroupId(), CourseReview.class.getName(), reviewId, ActionKeys.DELETE)
				&& themeDisplay.getUserId() != review.getUserId()) {
			SessionErrors.add(request, "error-user-denied");
		}
		if (SessionErrors.isEmpty(request)) {
			CourseReviewLocalServiceUtil.setRemoved(reviewId, true);
		}
		CourseReviewUpdateParameters params = new CourseReviewUpdateParameters(request);
		setCourseReviewUpdateResponse(request, response, params);
	}

    /**
     * Deletes a Course Rating from the database. Process Action. <br> Required parameters: <ul> <li>review-classPK</li>
     * </ul>
     *
     * @param request
     * @param response
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @throws com.liferay.portal.kernel.exception.PortalException
     */
    public void hideGlobalCourseReviewRating(ActionRequest request, ActionResponse response)
            throws SystemException, PortalException {

        if (!request.getMethod().equalsIgnoreCase("post")) {
            SessionErrors.add(request, "error-method-get");
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
        GlobalCourseReview globalReview = GlobalCourseReviewLocalServiceUtil.getGlobalCourseReview(reviewId);
        Course course = CourseLocalServiceUtil.getCourse(globalReview.getCourseId());
        if (course.isRemoved() && !themeDisplay.getPermissionChecker().isOmniadmin()) {
            SessionErrors.add(request, "error-course-removed");
        }

        if (!themeDisplay.getPermissionChecker().isOmniadmin()) {
            SessionErrors.add(request, "error-user-denied");
        }

        if (SessionErrors.isEmpty(request)) {
            GlobalCourseReviewLocalServiceUtil.setReviewIsHidden(reviewId, true);
        }

        if (ParamUtil.getBoolean(request, "ajax")) {

        } else {
            redirect(request, response);
        }
    }

	private void redirect(ActionRequest request, ActionResponse response) {
		String url = ParamUtil.getString(request, "redirect");
		if (!url.isEmpty()) {
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				_log.warn(e);
			}
		}
	}

	private static final class CourseReviewUpdateParameters {

		private ThemeDisplay themeDisplay;
		private long userId;
		private int rating;
		private long classPK;
		private long reviewId;
		private String content;

		CourseReviewUpdateParameters(ActionRequest request) throws SystemException, PortalException {
			themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			userId = themeDisplay.getUserId();
			// get Parameters
			rating = ParamUtil.getInteger(request, NterKeys.REVIEW_RATING);
			// classPK is the course.courseId
			classPK = ParamUtil.getLong(request, NterKeys.REVIEW_CLASSPK);
			reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
			if(Validator.isNull(classPK) && Validator.isNotNull(reviewId)) {
				CourseReview review = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
				classPK = review.getCourseId();
			}
			content = ParamUtil.getString(request, NterKeys.REVIEW_CONTENT);
			// Sanitize html input
			content = HtmlUtil.escape(content);
		}
	}

    public void createFlagEntry(ActionRequest request, ActionResponse response)
            throws Exception {
        long userId = ParamUtil.getLong(request, NterKeys.USER_ID);
        String reporterEmailAddress = ParamUtil.getString(request, NterKeys.REPORTER_EMAIL_ADDRESS);
        long reportedUserId = ParamUtil.getLong(request, NterKeys.REPORTED_USER_ID);
        long classNameId = ParamUtil.getLong(request, NterKeys.CLASS_NAME_ID);
        String className = ParamUtil.getString(request, NterKeys.CLASS_NAME);
	    long classPK = ParamUtil.getLong(request, NterKeys.CLASS_PK);
	    String contentTitle = ParamUtil.getString(request, NterKeys.CONTENT_TITLE);
        String content = ParamUtil.getString(request, NterKeys.CONTENT);
        String contentURL = ParamUtil.getString(request, NterKeys.CONTENT_URL);
        String flagReason = ParamUtil.getString(request, NterKeys.FLAG_REASON);
        String flagComment = ParamUtil.getString(request, NterKeys.FLAG_COMMENT);
        ServiceContext serviceContextReport = ServiceContextFactory.getInstance(FlagReportLocalServiceUtil.class.getName(), request);
        ServiceContext serviceContextFlag = ServiceContextFactory.getInstance("com.liferay.portlet.flags.model.FlagsEntry", request);

        FlagReport flagReport = FlagReportLocalServiceUtil.addFlagReport(
                userId, classNameId, classPK, contentTitle, content, flagReason, flagComment,
                null, null, serviceContextReport);
        _log.info(" Report flagged: " + content);


        // send flag mail message to administrators
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
			"com.liferay.portlet.flags.model.FlagsEntry", request);

        if (reportedUserId == 0) {
            //get company for user reporting the issue
            User user = UserLocalServiceUtil.getUser(userId);
            //use that company to retrieve a default user for global review user
            reportedUserId = UserLocalServiceUtil.getDefaultUserId(user.getCompanyId());
        }
		FlagsEntryServiceUtil.addEntry(
			className, classPK, reporterEmailAddress, reportedUserId,
			contentTitle, contentURL, flagReason, serviceContext);
		
		
		if (ParamUtil.getBoolean(request, "ajax")) {
			response.setRenderParameter(NterKeys.CONTENT, content);
			response.setRenderParameter(NterKeys.CONTENT_TITLE, contentTitle);
			response.setRenderParameter(NterKeys.CONTENT_URL, contentURL);
			response.setRenderParameter(NterKeys.FLAG_REASON, flagReason);
			response.setRenderParameter(NterKeys.FLAG_COMMENT, flagComment);
			response.setRenderParameter("jspPage", "/course-reviews/jsp/ajax-flag.jsp");
		} else {
			redirect(request, response);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(CourseReviewsPortlet.class);

}