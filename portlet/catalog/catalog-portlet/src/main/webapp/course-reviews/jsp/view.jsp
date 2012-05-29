<%--
  National Training and Education Resource (NTER)
  Copyright (C) 2012 SRI International

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or (at
  your option) any later version.

  This program is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
  02110-1301, USA.
  --%>

<%@include file="init.jsp" %>

<%
	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
	long courseId = ParamUtil.getLong(httpRequest, NterKeys.COURSE_ID);

	int coursesPerPage = 10;

	if (courseId != 0) {
		Course course = CourseLocalServiceUtil.getCourse(courseId);
		
		// we should be using renderResponse.createElement (http://blogs.oracle
		// .com/deepakg/entry/setting_markup_head_elements_in)
		// but it doesn't seem to work in all circumstances, e.g. maximized portlets
		Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
		List meta = new ArrayList();
		if (oldMeta != null) {
			meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
		}
		meta.add("<link rel=\"canonical\" href=\"" + course.getUrl() + "\" />");
		httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);
		
        List<CourseReview> reviews = CourseReviewLocalServiceUtil.findByCourseIdWithRemoved(course.getPrimaryKey(), false, 0, coursesPerPage);
        long reviewCount = CourseReviewLocalServiceUtil.countByCourseIdWithRemoved(course.getPrimaryKey(), false);

        List<GlobalCourseReview> globalReviews = GlobalCourseReviewLocalServiceUtil.findValidByCourseId(course.getPrimaryKey(), 0, coursesPerPage);
        long globalReviewCount = GlobalCourseReviewLocalServiceUtil.countValidByCourseId(course.getPrimaryKey());

		RatingsStats ratingsStats = RatingsStatsLocalServiceUtil
				.getStats(Course.class.getName(), course.getPrimaryKey());
		List<CourseReview> usersCourseReviews = CourseReviewLocalServiceUtil
				.findByCourseIdWithUserId(themeDisplay.getUserId(), courseId);
        CourseReview userCourseReview = null;
        for(CourseReview review : usersCourseReviews) {
            if(review.isRemoved()) {
                CourseReviewLocalServiceUtil.deleteCourseReview(review);
            }else {
                userCourseReview = review;
            }
        }

		String redirectUrl = course.getUrl();

		Double userScore = 0.0;
		try {
			RatingsEntry userRating = RatingsEntryLocalServiceUtil.getEntry(themeDisplay.getUserId(), Course.class.getName(), course.getPrimaryKey());
			userScore = userRating.getScore();
		}
		catch (NoSuchEntryException nsee) {
		}

		// placeholders
		boolean expertReviews = false;
		boolean userIsExpert = false;
		boolean userHasTakenCourse = true;

		long[] partialRatings = {course.getOneStarRateCount(),
				course.getTwoStarRateCount(),
				course.getThreeStarRateCount(),
				course.getFourStarRateCount(),
				course.getFiveStarRateCount()};
		long histogramTotal = partialRatings[0] + partialRatings[1] + partialRatings[2] + partialRatings[3] +
				partialRatings[4];

%>

<div class="ratings-stats <%= (histogramTotal > 0) ? "ratings-with-histogram" : "" %>">

	<c:if test="<%= reviews.isEmpty() && ratingsStats.getTotalEntries() == 0 %>">
		<div class="no-reviews-message">
			<c:choose>
				<c:when test="<%= globalReviewCount == 0 %>"> <!-- no local reviews and no global reviews --
					<c:choose>
						<c:when test="<%= expertReviews %>"> <!-- only experts may review -->
							<c:choose>
								<c:when test="<%= userIsExpert %>">
									<c:choose>
										<c:when test="<%= userHasTakenCourse %>">
											<liferay-ui:message key="no-course-ratings-or-reviews-can-review" /> <!-- tell them to review -->
										</c:when>
										<c:otherwise>
											<liferay-ui:message key="no-course-ratings-or-reviews-take-course" /> <!-- tell them to take the course -->
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="no-course-ratings-or-reviews" /> <!-- user may only rate, don't tell them to review -->
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise> <!-- everyone may review -->
							<c:choose>
								<c:when test="<%= !themeDisplay.isSignedIn() || course.isRemoved() %>">
									<liferay-ui:message key="no-course-reviews" /> <!-- tell them to sign in -->
								</c:when>
								<c:when test="<%= userHasTakenCourse %>">
									<liferay-ui:message key="no-course-reviews-can-review" /> <!-- tell them to review -->
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="no-course-reviews-take-course" /> <!-- tell them to take the course -->
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise> <!-- no local reviews but there are global reviews -->
					<c:choose>
						<c:when test="<%= expertReviews %>"> <!-- only experts may review -->
							<c:choose>
								<c:when test="<%= userIsExpert %>">
									<c:choose>
										<c:when test="<%= userHasTakenCourse %>">
											<liferay-ui:message key="only-global-course-ratings-or-reviews-can-review" /> <!-- tell them to review -->
										</c:when>
										<c:otherwise>
											<liferay-ui:message key="only-global-course-ratings-or-reviews-take-course" /> <!-- tell them to take the course -->
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="only-global-course-ratings-or-reviews" /> <!-- user may only rate, don't tell them to review -->
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise> <!-- everyone may review -->
							<c:choose>
								<c:when test="<%= !themeDisplay.isSignedIn() %>">
									<liferay-ui:message key="only-global-course-reviews" /> <!-- tell them to sign in -->
								</c:when>
								<c:when test="<%= userHasTakenCourse %>">
									<liferay-ui:message key="only-global-course-reviews-can-review" /> <!-- tell them to review -->
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="only-global-course-reviews-take-course" /> <!-- tell them to take the course -->
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>

	<% if (histogramTotal > 0) { %>
	<div class="ratings-histogram">
		<%
			long part = 0;
			String[] replacements = {"0", "0"};
			DecimalFormat dec = new DecimalFormat("#.##");

			for (int i = 5; i > 0; i--) {
		%>
		<label data-value="<%= i %>">
			<% if (i > 1) { %>
			<%= LanguageUtil.format(pageContext, "star-rating", Integer.toString(i)) %>
			<% } else { %>
			<%= LanguageUtil.format(pageContext, "star-rating-singular", Integer.toString(i)) %>
			<% } %>
			<div class="total">
				<%
					part = partialRatings[i - 1];
					replacements[0] = String.valueOf(part);
					if (histogramTotal > 0) {
						replacements[1] = dec.format((((double) part) / histogramTotal) * 100);
					}
				%>
				<div class="percent" style="width:<%= replacements[1] %>%"><%= LanguageUtil
						.format(pageContext, "rating-type-percent", replacements) %>
				</div>
			</div>
		</label>
		<% } %>
	</div>
    <div id="average-rating" class="rating-label">
        <liferay-ui:message key="average-rating" />
        <liferay-ui:ratings-score score="<%= ReviewUtil.getCourseAverageRating(course) %>" />
    </div>
	<% } %>

	<div id="review">
		<% if (themeDisplay.isSignedIn()) { /* is signed in */
			if (userHasTakenCourse) { /* has taken course */
				if (!expertReviews || userIsExpert) { /* can write full text review */
					if (!course.isRemoved()) { /* course is not removed */
						if (userCourseReview == null) { /* has not reviewed course */ %>
							<h4><%= LanguageUtil.get(pageContext,"write-a-review") %></h4>
							<nter:review-form courseId="<%=courseId%>"
										  pageContext="<%=pageContext%>"
										  redirect="<%= redirectUrl %>"
										  showBackButton="<%=false%>" />
						<% } else { /* has reviewed course */ %>
							<div class="ratings-stats">
								<liferay-ui:message key="your-rating-for-this-course" />
								<liferay-ui:ratings-score score="<%= userScore %>" />
								<div class="update-review">
									<portlet:renderURL var="editUrl">
										<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(userCourseReview.getPrimaryKey()) %>" />
										<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(userCourseReview.getCourseId()) %>" />
										<portlet:param name="userId" value="<%= Long.toString(userCourseReview.getUserId()) %>" />
										<portlet:param name="jspPage" value="/course-reviews/jsp/edit-review.jsp" />
										<portlet:param name="redirect" value="<%= redirectUrl %>" />
									</portlet:renderURL>
									<a href="<%= editUrl %>&cid=<%=userCourseReview.getCourseId() %>#review-form" class="button"><liferay-ui:message key="edit-your-review" /></a>
								</div>
							</div>
						<% } %>
					<% } else if (userScore > 0) { /* course is removed but they rated it before */ %>
						<div class="ratings-stats">
							<liferay-ui:message key="your-rating-for-this-course" />
							<liferay-ui:ratings-score score="<%= userScore %>" />
							<div class="update-review">
								<portlet:renderURL var="viewUrl">
										<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(userCourseReview.getPrimaryKey()) %>" />
										<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(userCourseReview.getCourseId()) %>" />
										<portlet:param name="userId" value="<%= Long.toString(userCourseReview.getUserId()) %>" />
										<portlet:param name="jspPage" value="/course-reviews/jsp/single-review.jsp" />
								</portlet:renderURL>
								<a href="<%= viewUrl %>&cid=<%=userCourseReview.getCourseId() %>#review" class="button"><liferay-ui:message key="view-your-review" /></a>
							</div>
						</div>
					<% } %>
				<% } else { /* can only rate */ %>
					<div class="ratings-stats">
						<liferay-ui:message key="your-rating-for-this-course" />
						<div class="update-rating">
							<liferay-ui:ratings
								className="<%= Course.class.getName() %>"
								classPK="<%= course.getPrimaryKey() %>"
								numberOfStars="5"
								/>
						</div>
					</div>
				<% } %>
			<% } else if (!reviews.isEmpty() || ratingsStats.getTotalEntries() > 0) { /* has not taken course and there are reviews (no reviews case address above) */ %>
				<% if (!expertReviews || userIsExpert) { /* can write full text review */ %>
					<liferay-ui:message key="take-course-to-review" />
				<% } else { /* can only rate */ %>
					<liferay-ui:message key="take-course-to-rate" />
				<% } %>
			<% } %>
		<% } else { /* logged out */ %>
			<% if (!course.isRemoved()) { %>
				<% if (!expertReviews) { /* anyone can write full review */ %>
					<a href="<%= themeDisplay.getURLSignIn() %>" class="button"><%= LanguageUtil.get(pageContext,"login-review-course") %></a>
				<% } else { /* most users can only rate */ %>
					<a href="<%= themeDisplay.getURLSignIn() %>" class="button"><%= LanguageUtil.get(pageContext,"login-rate-course") %></a>
				<% } %>
			<% } %>
		<% } %>
	</div>
</div>

<% if (!reviews.isEmpty()) { %>
<section class="reviews">
<% if (expertReviews) { %>
<h4><liferay-ui:message key="expert-opinions" /></h4>
<% } %>
<portlet:renderURL var="moreURL" windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/course-reviews/jsp/ajax-course-reviews.jsp" />
	<portlet:param name="<%=NterKeys.COURSE_ID%>" value="<%=String.valueOf(courseId) %>" />
</portlet:renderURL>
<ul class="reviewlist" data-more-url="<%= moreURL %>" data-start-param-name="_coursereviews_WAR_ntercatalogportlet_start" data-per-page="<%= coursesPerPage %>" data-total="<%= reviewCount %>">
	<% for (CourseReview review : reviews) { %>
	   <% request.setAttribute("review", review); %>
		<nter:review review="<%=review%>" pageContext="<%=pageContext%>" />
	<% } %>
</ul>
</section>
<% } %>

<% if (globalReviewCount > 0) { %>
<section class="remote-reviews">
<h4><liferay-ui:message key="remote-reviews" /></h4>
<portlet:renderURL var="remoteMoreURL" windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/course-reviews/jsp/ajax-global-course-reviews.jsp" />
	<portlet:param name="<%=NterKeys.COURSE_ID%>" value="<%=String.valueOf(courseId) %>" />
</portlet:renderURL>
<ul class="reviewlist" data-more-url="<%= remoteMoreURL %>" data-start-param-name="_coursereviews_WAR_ntercatalogportlet_start" data-per-page="<%= coursesPerPage %>" data-total="<%= globalReviewCount %>">
	<% for (GlobalCourseReview globalReview : globalReviews) { %>
	   <% request.setAttribute("globalReview", globalReview); %>
		<nter:globalReview globalReview="<%=globalReview%>" pageContext="<%=pageContext%>" />
	<% } %>
</ul>
</section>
<% } %>

<%
} else {
	// no courseId param
%>
<div class="portlet-msg-alert">
	<liferay-ui:message key="no-course-param" />
</div>
<% } %>