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

<%-- review-form.tag --%>

<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ tag import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ tag import="com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil" %>
<%@ tag import="org.nterlearning.course.util.NterKeys" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>
<%@ tag import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ tag import="com.liferay.portal.kernel.util.Validator" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ attribute name="review" type="org.nterlearning.datamodel.catalog.model.CourseReview" %>
<%@ attribute name="courseId" type="java.lang.Long" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>
<%@ attribute name="redirect" type="java.lang.String"%>
<%@ attribute name="showBackButton" type="java.lang.Boolean"%>

<portlet:defineObjects/>
<liferay-theme:defineObjects/>
<%
	double score = ParamUtil.getDouble(request, NterKeys.REVIEW_RATING);
	String content = ParamUtil.getString(request, NterKeys.REVIEW_CONTENT);
	//remove summary; may reactivate based on user feedback
    // String summary = ParamUtil.getString(request, NterKeys.REVIEW_SUMMARY);
	String summary = "";
    String jspPage = ParamUtil.getString(request, "jspPage");
	if (review != null) {
		if(score == 0.0)
			score = RatingsEntryLocalServiceUtil.getEntry(
				review.getUserId(), Course.class.getName(), review.getCourseId()).getScore();
		if(Validator.isNull(content))
			content = review.getContent();
		//remove summary; may reactivate based on user feedback
        // if(Validator.isNull(summary))
		//	summary = review.getSummary();
	}
	Course course = CourseLocalServiceUtil.getCourse(courseId);
%>

<liferay-ui:error key="error-review-no-content" message="error-review-no-content" />
<liferay-ui:error key="error-review-no-summary" message="error-review-no-summary" />
<liferay-ui:error key="error-review-no-rating" message="error-review-no-rating" />
<liferay-ui:error key="error-review-content-length" message="error-review-content-length" />
<liferay-ui:error key="error-review-summary-length" message="error-review-summary-length" />
<liferay-ui:error key="error-course-removed" message="error-course-removed" />
<liferay-ui:error key="error-method-get" message="error-method-get" />
<!-- this should only be here if you are logged in: -->
<% if (themeDisplay.isSignedIn()) { %>
<portlet:actionURL var="updateUrl" name='updateCourseReview' windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<portlet:param name="redirect" value="<%=(redirect != null) ? redirect : PortalUtil.getCurrentURL(request)%>" />
	<portlet:param name="hide-default-error-msg" value="true"/>
	<% if (jspPage != null) { %>
	<portlet:param name="jspPage" value="<%= jspPage %>" />
	<% } %>
</portlet:actionURL>
<form id="review-form" class="uniForm" method="post" action="<%= updateUrl %>&cid=<%= courseId %>#review">
	<fieldset>
		<div class="ctrlHolder">
			<label for="review-content">
				<%= LanguageUtil.get(pageContext, "review-form-review") %>
			</label>
			<textarea name="review-content" id="review-content"><%= content %></textarea>
		</div>
		<%//remove summary; may reactivate based on user feedback
        //<div class="ctrlHolder"><label for="review-summary">< LanguageUtil.get(pageContext, "review-form-summary") >
		//	</label><input type="text" name="review-summary" id="review-summary" class="textInput" value="< summary >"/></div>%>
		<div class="ctrlHolder">
			<label for="<%= NterKeys.REVIEW_RATING %>">
				<%= LanguageUtil.get(pageContext, "review-form-rating") %>
			</label>
			<div class="rating">
				<% String[] vars = {"1","5"}; %>
				<ul class="blockLabels">
					<li>
						<label>
							<input type="radio" name="<%= NterKeys.REVIEW_RATING %>" value="1" <%= (score == 1) ? "checked" : "" %> />
							<% vars[0] = "1"; %>
							<%= LanguageUtil.format(pageContext, "rate-this-x-stars-out-of-x-singular", vars) %>
						</label>
					</li>
					<li>
						<label>
							<input type="radio" name="<%= NterKeys.REVIEW_RATING %>" value="2" <%= (score == 2) ? "checked" : "" %> />
							<% vars[0] = "2"; %>
							<%= LanguageUtil.format(pageContext, "rate-this-x-stars-out-of-x", vars) %>
						</label>
					</li>
					<li>
						<label>
							<input type="radio" name="<%= NterKeys.REVIEW_RATING %>" value="3" <%= (score == 3) ? "checked" : "" %> />
							<% vars[0] = "3"; %>
							<%= LanguageUtil.format(pageContext, "rate-this-x-stars-out-of-x", vars) %>
						</label>
					</li>
					<li>
						<label>
							<input type="radio" name="<%= NterKeys.REVIEW_RATING %>" value="4" <%= (score == 4) ? "checked" : "" %> />
							<% vars[0] = "4"; %>
							<%= LanguageUtil.format(pageContext, "rate-this-x-stars-out-of-x", vars) %>
						</label>
					</li>
					<li>
						<label>
							<input type="radio" name="<%= NterKeys.REVIEW_RATING %>" value="5" <%= (score == 5) ? "checked" : "" %> />
							<% vars[0] = "5"; %>
							<%= LanguageUtil.format(pageContext, "rate-this-x-stars-out-of-x", vars) %>
						</label>
					</li>
				</ul>
			</div>
		</div>
		<div class="buttonHolder">
			<button type="submit" class="submit primaryAction" value="submit">
				<% if (review != null) { %>
					<%= LanguageUtil.get(pageContext, "review-form-edit") %>
				<% } else { %>
					<%= LanguageUtil.get(pageContext, "review-form-submit") %>
				<% } %>
			</button>
			<% if (showBackButton == null || showBackButton) { %>
			<a href="<%= course.getUrl() %>" class="button secondaryAction">
				<%= LanguageUtil.get(pageContext, "review-form-cancel") %>
			</a>
			<% } %>
		</div>
	</fieldset>
	<input type="hidden" name="<%= NterKeys.REVIEW_CLASSPK %>" value="<%= courseId %>"/>
	<input type="hidden" name="<%= NterKeys.COURSE_ID %>" value="<%= courseId %>"/>
	<% if (review != null) { %>
	<input type="hidden" name="<%= NterKeys.REVIEW_ID %>" value="<%= review.getPrimaryKey() %>"/>
	<% } %>
</form>
<% }
else { %>
<div class="open-review">
	<a href="<%= themeDisplay.getURLSignIn() %>" class="login-review-course button">
		<%= LanguageUtil.get(pageContext, "login-review-course") %>
	</a>
</div>
<% } %>
