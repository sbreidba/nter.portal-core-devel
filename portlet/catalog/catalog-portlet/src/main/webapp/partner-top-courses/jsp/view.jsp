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

<%@include file="/partner-top-courses/jsp/init.jsp" %>

<%
    Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());

    int coursesDisplayed = 9;
    int total = 0;
%>

<div id="partner-top-courses">
	<ul>
		<li><a href="#newcourses"><%=LanguageUtil.get(pageContext, "new-courses") %></a></li>
		<li><a href="#topcourses"><%=LanguageUtil.get(pageContext, "top-courses") %></a></li>
	</ul>

	<div>
		<div id="newcourses">
            <ul class="course-listing thumbnail-listing">
            <%
                total = (int) CourseLocalServiceUtil.countAllValidCourses(CourseFilterType.ALL.getWhereSql(), group.getGroupId());
                List<Course> newCourses = new ArrayList<Course>();
                if (total > 0) {
                    newCourses.addAll(CourseLocalServiceUtil.findAllValidCourses(CourseFilterType.ALL.getWhereSql(),
                            CourseSortType.NEW_POPULAR.getSortSql(), group.getGroupId(), 0, coursesDisplayed));

                    // Highlight first course in return list.
                    Course mainFeaturedNewCourse = newCourses.get(0);
                    mainFeaturedNewCourse.startSafeImageEnumeration(locale, LocaleUtil.getDefault()); %>

                    <li class="main-course">
                        <article class="course">
                            <a href='<%= mainFeaturedNewCourse.getUrl() %>'>
                                <img class="course-thumbnail no-png-fix" src='<%= mainFeaturedNewCourse.getSafeImage
                                (0).getLargeImageUrl(themeDisplay) %>' alt='<%= mainFeaturedNewCourse.getTitle(locale) %>'
									 title='<%= mainFeaturedNewCourse.getTitle(locale) %>' />
                            </a>
                            <h4><a href='<%= mainFeaturedNewCourse.getUrl() %>'><%= mainFeaturedNewCourse.getTitle(locale) %></a></h4>
                            <dl>
                                <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext,"course-description-avg-rating") %>:</dt><dd><liferay-ui:ratings-score score="<%= ReviewUtil.getCourseAverageRating(mainFeaturedNewCourse) %>" /></dd></div>
                                <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext,"course-description-duration") %>:</dt><dd><%= mainFeaturedNewCourse.getFriendlyDuration(pageContext) %></dd></div>
                            </dl>
                            <div class="description"><%= mainFeaturedNewCourse.getDescription(locale) %></div>
                        </article>
                    </li> <%
                 }
                else { %>
                    <h3>No Courses Currently Available</h3>
                <% }
            %>

            <%
                Boolean firstNew = true;
                for (Course course : newCourses) {
                    if (firstNew) {
                        firstNew = false; //skip first course, it has been displayed as featured
                    } else {
                        course.startSafeImageEnumeration(locale, LocaleUtil.getDefault()); %>
                    <li>
                        <article class="course">
                            <a href='<%= course.getUrl() %>' class="thumbnail-link">
                                <img class="course-thumbnail no-png-fix" src='<%= course.getSafeImage(0)
                                .getMediumImageUrl(themeDisplay) %>' alt='<%= course.getTitle(locale) %>' title='<%= course
                                .getTitle(locale) %>'/>
                            </a>
                            <h4><a href='<%= course.getUrl() %>'><%=course.getTitle(locale) %></a></h4>
                        </article>
                    </li> <%
                    }
                }
            %>
            </ul>

            <% if (newCourses.size() > 0) { %>
			    <a href="<%= PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() %>/courses?filter=new" class="morelink">See more courses</a>
            <% } %>
		</div>

		<div id="topcourses">
            <ul class="course-listing thumbnail-listing">
            <%
                total = (int) CourseLocalServiceUtil.countAllValidCourses(CourseFilterType.ALL.getWhereSql(), group.getGroupId());
                List<Course> topCourses = new ArrayList<Course>();
                if (total > 0) {
                    topCourses.addAll(CourseLocalServiceUtil.findAllValidCourses(CourseFilterType.ALL.getWhereSql(), CourseSortType.POPULAR_NEW.getSortSql(), group.getGroupId(), 0, coursesDisplayed));

                    // Highlight first course in return list.
                    Course mainFeaturedPopularCourse = topCourses.get(0);
                    mainFeaturedPopularCourse.startSafeImageEnumeration(locale, LocaleUtil.getDefault()); %>

                    <li class="main-course">
                        <article class="course">
                            <a href='<%= mainFeaturedPopularCourse.getUrl() %>'>
                                <img class="course-thumbnail no-png-fix" src='<%= mainFeaturedPopularCourse
                                .getSafeImage(0).getLargeImageUrl(themeDisplay) %>' alt='<%=mainFeaturedPopularCourse.getTitle
                                (locale) %>' title='<%=mainFeaturedPopularCourse.getTitle(locale) %>' />
                            </a>
                            <h4><a href='<%=mainFeaturedPopularCourse.getUrl() %>'><%= mainFeaturedPopularCourse.getTitle(locale) %></a></h4>
                            <dl>
                                <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext,"course-description-avg-rating") %>:</dt>
                                    <dd><liferay-ui:ratings-score score='<%= ReviewUtil.getCourseAverageRating(mainFeaturedPopularCourse) %>' /></dd>
                                </div>
                                <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext,"course-description-duration") %>:</dt>
                                    <dd><%= mainFeaturedPopularCourse.getFriendlyDuration(pageContext) %></dd>
                                </div>
                            </dl>
                            <div class="description"><%= mainFeaturedPopularCourse.getDescription(locale) %></div>
                        </article>
                    </li>
                <% } else { %>
                    <h3><%=LanguageUtil.get(pageContext, "partner-courses-no-courses") %></h3>
                <%
                    }
                    Boolean firstTop = true;
                    for (Course course : topCourses) {
                        if (firstTop) {
                            firstTop = false; //skip first course, it has been displayed as featured
                        } else {
                            course.startSafeImageEnumeration(locale, LocaleUtil.getDefault()); %>
                        <li>
                            <article class="course">
                                <a href='<%= course.getUrl() %>' class="thumbnail-link">
                                    <img class="course-thumbnail no-png-fix" src='<%= course.getSafeImage(0)
                                    .getMediumImageUrl(themeDisplay) %>' alt='<%= course.getTitle(locale) %>' title='<%= course
                                    .getTitle(locale) %>'/>
                                </a>
                                <h4><a href='<%= course.getUrl() %>'><%=course.getTitle(locale) %></a></h4>
                            </article>
                        </li> <%
                        }
                    }%>
            </ul>

            <% if (topCourses.size() > 0) { %>
			    <a href="<%= PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() %>/courses?filter=popular" class="morelink"><%=LanguageUtil.get(pageContext, "partner-courses-more-courses") %></a>
            <% } %>
		</div>
	</div>
</div>

<script>
YUI().use('tabview', function(Y) {
    var tabview = new Y.TabView({
        srcNode: '#partner-top-courses'
    });

    tabview.render();
});
</script>