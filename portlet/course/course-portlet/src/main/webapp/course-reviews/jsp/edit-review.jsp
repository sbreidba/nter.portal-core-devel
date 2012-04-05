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
// if the review does not exist?
// if the user does not have rights to edit the review, show an error and a link back to the course
// otherwise
	// if something was submitted, validate
		// if valid, save it
		// otherwise, show the form with the review, summary, and rating inside and errors (review-form will need to be modified to add those (and a cancel button back to the course) if they exist)
	// otherwise, show the review form with the review, summary, and rating inside (review-form will need to be modified to add those (and a cancel button back to the course) if they exist).

long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
long courseId = ParamUtil.getLong(request, NterKeys.REVIEW_CLASSPK);

CourseReview review = null;
try {
	review = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
} catch (Exception e) {}

PortalUtil.addPageSubtitle(LanguageUtil.get(pageContext, "edit-review"), PortalUtil.getHttpServletRequest(renderRequest));

if (review == null && reviewId != 0) {
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
	meta.add("<meta name=\"robots\" content=\"noindex\" />");
	httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);

	if (review != null && review.getUserId() != user.getUserId() && !permissionChecker.isCommunityAdmin(scopeGroupId)) { %>
		<p><liferay-ui:message key="not-allowed-to-edit-review" /></p>
		<a href="<%= course.getUrl() %>" class="button"><%= LanguageUtil.get(pageContext,"back-to-course") %></a>
	<% } else { %>
		<% if (review == null) { %>
			<h4><%= LanguageUtil.get(pageContext,"write-a-review") %></h4>
		<% } else if (review.getUserId() == user.getUserId()) { %>
			<h4><%= LanguageUtil.get(pageContext,"edit-your-review") %></h4>
		<% } else { %>
			<h4><%= LanguageUtil.format(pageContext,"edit-users-review", UserLocalServiceUtil.getUserById(review.getUserId()).getFullName()) %></h4>
		<% } %>
		<%
			String redirectUrl = ParamUtil.getString(request, "redirect");
			if (redirectUrl.equals("")) redirectUrl = PortalUtil.getCurrentCompleteURL(request);
		%>
        <nter:review-form review="<%=review%>" courseId="<%=courseId%>"
                          pageContext="<%=pageContext%>" redirect="<%= redirectUrl %>" />
	<% } %>
<% } %>