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

<%@ page import="org.nterlearning.utils.ReviewUtil" %>
<%@include file="/course-carousel/jsp/init.jsp" %>

<%
CarouselType carouselType = CarouselType.valueOf(
		prefs.getValue(CarouselConstants.PREF_TYPE, CarouselType.UNDEFINED.toString()));

boolean showDetails = prefs.getValue(CarouselConstants.PREF_DETAILS, "Undefined").equals("true");
int coursesDisplayed = Integer.parseInt(prefs.getValue(CarouselConstants.PREF_NUM_DISPLAYED, "16"));

//set course filter type (where clause) and course sort type (order by clause)
CourseFilterType courseFilterType = CourseFilterType.ALL;
CourseSortType courseSortType = CourseSortType.NEW_POPULAR;
String morestring = LanguageUtil.get(pageContext, "view-more");
switch (carouselType) {
   case NEW:
	courseSortType = CourseSortType.NEW_POPULAR;
	morestring = LanguageUtil.get(pageContext, "view-more-new");
	break;
   case POPULAR:
	courseSortType = CourseSortType.POPULAR_NEW;
	morestring = LanguageUtil.get(pageContext, "view-more-popular");
	break;
   case FEATURED:
	courseFilterType = CourseFilterType.FEATURED;
	courseSortType = CourseSortType.NEW_POPULAR;
	morestring = LanguageUtil.get(pageContext, "view-more-featured");
	break;
}

long groupId = -1; //carousel not filtered by group

HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
Course courseRef;
String courseParam = httpRequest.getParameter("cid");
if (Validator.isNull(courseParam)) {
	courseRef = new CourseImpl();
}
else {
	long courseId = Long.parseLong(courseParam);
	courseRef = CourseLocalServiceUtil.getCourse(courseId);
}
%>

<c:choose>
	<c:when test='<%= carouselType != CarouselType.UNDEFINED %>'>
		<div class="carousel-wrapper">
			<ul class="carousel thumbnail-listing" role="list" aria-live="polite">
			<%
                List<Course> courses = new ArrayList<Course>();
                courses.addAll(CourseLocalServiceUtil.findAllValidCourses(courseFilterType.getWhereSql(),
                        courseSortType.getSortSql(), groupId, 0, coursesDisplayed));
                courses.remove(courseRef);

				for (Course course : courses) {
				    course.startSafeImageEnumeration(locale, LocaleUtil.getDefault());

			%>
				<li role="listitem" class="course">
					<a href="<%= course.getUrl() %>" class="thumbnail-link"><img src='<%= course.getSafeImage(0)
					.getLargeImageUrl(themeDisplay) %>' class="course-thumbnail no-png-fix" title="<%= course.getTitle(locale)
					%>" alt="<%= course.getTitle(locale) %>" /></a>
					<h4><a href="<%= course.getUrl() %>"><%= course.getTitle(locale) %></a></h4>
					<div class="rating"><liferay-ui:ratings-score score="<%= ReviewUtil.getCourseAverageRating(course) %>" /></div>
					<div class="organization"><%=course.getCourseDomain()%></div>
                    <% if (showDetails) { %>
					<div class="description"><%= course.getDescription(locale) %></div>
					<% } %>
				</li>
			<% } %>
			</ul>
		</div>
		<a href="<%= PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() %>/courses?filter=<%= carouselType.toString().toLowerCase() %>" class="morelink"><%= morestring %></a>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="portlet-needs-to-be-configured" />
	</c:otherwise>
</c:choose>