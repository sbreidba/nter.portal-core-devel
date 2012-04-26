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
String reviewParam = httpRequest.getParameter("crid");
Long courseParam = Long.parseLong(httpRequest.getParameter("cid"));
Long classNameId = Long.parseLong(httpRequest.getParameter("classid"));

String jspPage = ParamUtil.getString(request, "jspPage");
if (Validator.isNull(reviewParam)){
%>
<div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext,"review-does-not-exist") %> </div>
<%
} else {
	long classPK = Long.parseLong(reviewParam);
	Course course = CourseLocalServiceUtil.getCourse(courseParam);
  long reportedUserId = 0;
  String className = null;
  String content = null;

  //System.out.print("courseReviewID=" + classPK + " ");
  CourseReview courseReview = null;
  GlobalCourseReview globalCourseReview = null;
  if (classNameId == ClassNameLocalServiceUtil.getClassNameId(CourseReview.class)) {
    courseReview = CourseReviewLocalServiceUtil.getCourseReview(classPK);
    reportedUserId = courseReview.getUserId();
    className = CourseReview.class.getName();
    content = courseReview.getContent();
  } else if (classNameId == ClassNameLocalServiceUtil.getClassNameId(GlobalCourseReview.class)) {
    globalCourseReview = GlobalCourseReviewLocalServiceUtil.getGlobalCourseReview(classPK);
    //the user is a String, unknown userId in global review so just default to zero.
    //the custom queries for moderating flagreports obtain the username
    //separately outside the flagreport via a complex query with joins
    //String reportedUser = globalCourseReview.getUserDisplayName();
    reportedUserId = 0;
    className = GlobalCourseReview.class.getName();
    content = globalCourseReview.getContent();
  }
  
  if (courseReview == null && globalCourseReview == null) {
    %>
    <div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext,"review-does-not-exist") %> </div>
    <%
  } else {

    // fill in title with PK and first few characters of content
    String contentTitle = "Inappropriate Review: classPK=" + classPK + "; Content: " + StringUtil.shorten(content, 30); ;

    // ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    long userId = themeDisplay.getUserId();
    String reporterEmailAddress = themeDisplay.getUser().getEmailAddress();
    String contentURL = course.getUrl();

    Boolean showBackButton = true;
    %>
    <portlet:renderURL var="landingUrl" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
    	<portlet:param name="jspPage" value="/course-reviews/jsp/flag-landing.jsp"/>
    	<portlet:param name="redirect" value="<%= contentURL %>"/>
    </portlet:renderURL>
    <nter:flag-entry userId="<%=userId%>"
        reporterEmailAddress="<%=reporterEmailAddress%>"
        reportedUserId="<%=reportedUserId%>"
        classNameId="<%=classNameId%>"
        className="<%=className%>"
        classPK="<%=classPK%>"
        contentTitle="<%=contentTitle%>"
        content="<%=content%>"
        contentURL="<%=contentURL%>"
        pageContext="<%=pageContext%>"
        jspPage="<%=jspPage%>"
        redirect="<%=landingUrl%>"
        showBackButton="<%=showBackButton%>"/>

  <% }
}
%>