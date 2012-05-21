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
long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
long courseId = ParamUtil.getLong(request, NterKeys.REVIEW_CLASSPK);

CourseReview review = null;
try {
	review = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
} catch (Exception e) {}

PortalUtil.addPageSubtitle(LanguageUtil.get(pageContext, "view-review"), PortalUtil.getHttpServletRequest(renderRequest));

if (review == null) {
	%>
	<p><liferay-ui:message key="review-does-not-exist" /></p>
<% } else {
	Course course = CourseLocalServiceUtil.getCourse(courseId);
	PortalUtil.addPageSubtitle(" - " + course.getTitle(locale), PortalUtil.getHttpServletRequest(renderRequest));

	// we should be using renderResponse.createElement (http://blogs.oracle.com/deepakg/entry/setting_markup_head_elements_in)
	// but it doesn't seem to work in all circumstances, e.g. maximized portlets
	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
	Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	List meta = new ArrayList();
	if (oldMeta != null) meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	meta.add("<link rel=\"canonical\" href=\"" + course.getUrl() + "\" />");
	httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);

	if (review.getUserId() == user.getUserId()) { %>
		<h4><%= LanguageUtil.format(pageContext,"your-review-for-course", course.getTitle(locale)) %></h4>
	<% } else { %>
		<% String[] vars = {UserLocalServiceUtil.getUserById(review.getUserId()).getFullName(), course.getTitle(locale)}; %>
		<h4><%= LanguageUtil.format(pageContext,"users-review-for-course", vars) %></h4>
	<% } %>
	
	<div id="review" class="single-review">
		<%//remove summary; may reactivate based on user feedback
          //<div class="review-title">< review.getSummary()></div> %>
		<div class="user-rating">
			<% double score = CourseReviewLocalServiceUtil.findScoreByReviewId(review.getCourseReviewId()); %>
			<liferay-ui:ratings-score score="<%= score %>"/>
		</div>
		<div class="review-text">
			<%= review.getContent() %>
		</div>
	</div>
	
	<a href="<%= course.getUrl() %>" class="button"><%= LanguageUtil.get(pageContext,"back-to-course") %></a>
<% } %>