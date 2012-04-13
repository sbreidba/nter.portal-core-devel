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
PortalUtil.addPageSubtitle(LanguageUtil.get(pageContext, "remove-review"), PortalUtil.getHttpServletRequest(renderRequest));
// we should be using renderResponse.createElement (http://blogs.oracle.com/deepakg/entry/setting_markup_head_elements_in)
// but it doesn't seem to work in all circumstances, e.g. maximized portlets
HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
List meta = new ArrayList();
if (oldMeta != null) meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
meta.add("<meta name=\"robots\" content=\"noindex\" />");
httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);

String redirectUrl = ParamUtil.getString(request, "redirect");
String currentURL = PortalUtil.getCurrentURL(request);

PortalUtil.addPageSubtitle(LanguageUtil.get(pageContext, "remove-review"), PortalUtil.getHttpServletRequest(renderRequest));

long reviewId = ParamUtil.getLong(request, NterKeys.REVIEW_ID);
long courseId = ParamUtil.getLong(request, NterKeys.REVIEW_CLASSPK);

CourseReview review = null;
try {
	review = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
} catch (Exception e) {}


if (review == null && reviewId != 0) {
	%>
	<p><liferay-ui:message key="review-does-not-exist" /></p>
<% } else {
	Course course = CourseLocalServiceUtil.getCourse(courseId);
	PortalUtil.addPageSubtitle(" - " + course.getTitle(locale), PortalUtil.getHttpServletRequest(renderRequest));
	
	if (review != null && review.getUserId() != user.getUserId() && !permissionChecker.isGroupAdmin(scopeGroupId)) { %>
		<p><liferay-ui:message key="not-allowed-to-remove-review" /></p>
		<a href="<%= course.getUrl() %>" class="button"><%= LanguageUtil.get(pageContext,"back-to-course") %></a>
	<% } else { %>

		<p><%= LanguageUtil.format(pageContext,"review-removed-confirmation", UserLocalServiceUtil.getUserById(review.getUserId()).getFullName()) %></p>
		
		<portlet:renderURL var="landingUrl" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
			<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(review.getPrimaryKey()) %>"/>
			<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(review.getCourseId()) %>"/>
			<portlet:param name="jspPage" value="/course-reviews/jsp/delete-review-landing.jsp"/>
			<portlet:param name="redirect" value="<%= course.getUrl() %>"/>
		</portlet:renderURL>
		<portlet:actionURL var="deleteUrl" name="deleteCourseRating">
			<portlet:param name="redirect" value="<%= landingUrl %>"/>
		</portlet:actionURL>
		<form action="<%= deleteUrl %>" method="post">
			<input type="hidden" name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(review.getPrimaryKey()) %>"/>
			<input type="hidden" name="courseId" value="<%= Long.toString(review.getCourseId()) %>"/>
			<input type="hidden" name="userId" value="<%= Long.toString(review.getUserId()) %>"/>
			<input type="hidden" name="cid" value="<%= Long.toString(review.getCourseId()) %>"/>
			<button class="delete" data-id-name="review-reviewId" data-id="<%= review.getPrimaryKey() %>"
					data-url="<portlet:actionURL name='deleteCourseRating' />"><%= LanguageUtil.get(pageContext, "remove") %>
			</button>
			<a href="<%= course.getUrl() %>" class="button"><%= LanguageUtil.get(pageContext,"cancel") %></a>
		</form>

	<% } %>
<% } %>