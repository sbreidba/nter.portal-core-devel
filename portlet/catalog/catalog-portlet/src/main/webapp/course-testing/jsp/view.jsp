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

<%-- course-testing/jsp/view.jsp --%>
<%@include file="/course-testing/jsp/init.jsp" %>


<%
	DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());

    // set parameters so different search containers can be on different pages with different deltas
    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("curCourse", ParamUtil.getString(request, "curCourse", "1"));
    portletURL.setParameter("deltaCourse", ParamUtil.getString(request, "deltaCourse", "5"));
    portletURL.setParameter("curComponent", ParamUtil.getString(request, "curComponent", "1"));
    portletURL.setParameter("deltaComponent", ParamUtil.getString(request, "deltaComponent", "5"));
    portletURL.setParameter("curCourseRecord", ParamUtil.getString(request, "curCourseRecord", "1"));
    portletURL.setParameter("deltaCourseRecord", ParamUtil.getString(request, "deltaCourseRecord", "5"));
    portletURL.setParameter("curComponentRecord", ParamUtil.getString(request, "curComponentRecord", "1"));
    portletURL.setParameter("deltaComponentRecord", ParamUtil.getString(request, "deltaComponentRecord", "5"));
%>

<h3><%= LanguageUtil.get(pageContext, "course-testing-feeds") %></h3>
<liferay-ui:success key="course-testing-success-feed" message="course-testing-success-feed" />
<liferay-ui:error key="course-testing-invalid-feed" message="course-testing-invalid-feed" />

<portlet:actionURL name="parseUrl" var="parseUrlUrl" />
<aui:form action="<%= parseUrlUrl.toString() %>" method="post">
	<aui:fieldset>
	    <aui:input name="course-testing-feeds-url-to-parse" size="100" />
		<aui:button-row>
			<aui:button type="submit" value="course-testing-feeds-parse-url" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<portlet:actionURL name="purgeFeeds" var="purgeFeeds" />
<portlet:actionURL name="purgeOrphans" var="purgeOrphans"/>
<aui:form method="post">
    <aui:fieldset>
        <aui:button value="course-testing-purge-feeds"
                    onClick="<%= purgeFeeds.toString() %>"/>
        <aui:button value="course-testing-purge-orphaned-entry"
                    onClick="<%= purgeOrphans.toString() %>"/>
    </aui:fieldset>
</aui:form>


<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-courses") %></h3>
<liferay-ui:search-container
    searchContainer='<%= new SearchContainer(renderRequest, null, null, "curCourse", 5, portletURL, null, "course-testing-no-courses") %>'
    id="courseSearchContainer"
    deltaParam="deltaCourse">

	<liferay-ui:search-container-results>
 		<%
			total = CourseLocalServiceUtil.countAllCourses();
			searchContainer.setTotal(total);

			results = CourseLocalServiceUtil.findAllCourses(
					searchContainer.getStart(), searchContainer.getResultEnd());
			searchContainer.setResults(results);

			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="org.nterlearning.datamodel.catalog.model.Course"
		keyProperty="courseId"
		modelVar="course">
		<liferay-ui:search-container-column-text
			name="Title">
            <%
                String courseTitle = course.getTitle(locale);
                if ((courseTitle == null) || courseTitle.equals("")) {
                    courseTitle = LanguageUtil.get(pageContext, "course-feed-blank");
                }
            %>
			<a href='<%= course.getUrl() %>'><%= courseTitle %></a>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
			name="Updated Date">
			<%= df.format(course.getUpdatedDate()) %>
		</liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text
            name="Removed Flag">
            <%= course.getRemoved() %>
        </liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text
            name="PopularWeight">
            <%= course.getPopularWeight() %>
        </liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
			name="Tags">
			<liferay-ui:asset-tags-summary
				classPK="<%= course.getCourseId() %>"
				className="<%= Course.class.getName() %>" />
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Categories">
            <div class="course-attribute">
                <dd>
                    <nter:category className="<%=Course.class.getName() %>" classPK="<%= course.getPrimaryKey() %>" />
                 </dd>
            </div>
            <div class="course-attribute">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-jsp
			path="/course-testing/jsp/editActions.jsp"
			align="right" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="<%= true%>" />
</liferay-ui:search-container>

<% // Test feature: remove courses. %>
<portlet:actionURL name="purgeAllCourses" var="purgeAllCourses" />
<aui:form action="<%= purgeAllCourses.toString() %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-testing-purge-all-courses" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>


<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-course-components") %></h3>
<liferay-ui:search-container
    searchContainer='<%= new SearchContainer(renderRequest, null, null, "curComponent", 5, portletURL, null, "course-testing-no-course-components") %>'
    id="componentSearchContainer"
    deltaParam="deltaComponent">

	<liferay-ui:search-container-results>
		<%
			total = ComponentUtil.getComponentCount();
			searchContainer.setTotal(total);

			results = ComponentUtil.getComponents(
					searchContainer.getStart(), searchContainer.getResultEnd());
			searchContainer.setResults(results);

			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="org.nterlearning.datamodel.catalog.model.Component"
		keyProperty="componentId"
		modelVar="component">

		<liferay-ui:search-container-column-text
			name="Component Title">
            <%
                String componentTitle = component.getTitle();
                if ((componentTitle == null) || componentTitle.equals("")) {
                    componentTitle = LanguageUtil.get(pageContext, "course-feed-blank");
                }

                try { %>
                    <a href='<%= component.getUrl() %>'><%= componentTitle %></a>
                <% } catch (Exception e) { %>
                    <%= componentTitle %>
                <% } %>
        </liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="Updated Date">
			<%= df.format(component.getUpdatedDate()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="IRI"
			property="componentIri" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="<%= true%>" />
</liferay-ui:search-container>


<% // Test feature: Remove all Components %>
<portlet:actionURL name="purgeAllComponents" var="purgeAllComponents" />
<aui:form action="<%= purgeAllComponents %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-testing-purge-all-components" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>


<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-course-popular-weights") %></h3>
<portlet:actionURL name="assignAllAccessCounts" var="assignAllAccessCounts" />
<aui:form action="<%= assignAllAccessCounts %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-testing-assign-all-course-access-counts" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<portlet:actionURL name="assignAllCompletedCounts" var="assignAllCompletedCounts" />
<aui:form action="<%= assignAllCompletedCounts %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-testing-assign-all-course-completed-counts" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<portlet:actionURL name="assignAllPopularWeights" var="assignAllPopularWeights" />
<aui:form action="<%= assignAllPopularWeights %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-testing-assign-all-course-popular-weights" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<portlet:actionURL name="updateUserHelpfulness" var="updateUserHelpfulnessUrl" />
<aui:form action="<%= updateUserHelpfulnessUrl %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="Update User Helpfulness" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<portlet:actionURL name="reloadTopReviewerThreshold" var="reloadTopReviewerThresholdUrl" />
<aui:form action="<%= reloadTopReviewerThresholdUrl %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="Reload Top Reviewer Threshold" />
			<label><%=ReviewUtil.getTopReviewerThreshold()%></label>
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<portlet:actionURL name="setSearchAddressFromRegistry" var="searchAddressUrl" />
<aui:form action="<%= searchAddressUrl %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="Reset Search Address" />
			<label><%=ExternalOpenSearchImpl.getSearchAddress()%></label>
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-course-vocabulary") %></h3>
<portlet:actionURL name="purgeAllCourseTypeVocabularies" var="purgeAllCourseTypeVocabularies" />
<aui:form action="<%= purgeAllCourseTypeVocabularies %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-testing-purge-all-course-type-vocabularies" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>


<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-course-records") %></h3>
<liferay-ui:search-container
    searchContainer='<%= new SearchContainer(renderRequest, null, null, "curCourseRecord", 5, portletURL, null, "course-testing-no-course-records") %>'
    id="courseRecordSearchContainer"
    deltaParam="deltaCourseRecord">
    <liferay-ui:search-container-results>
        <%
            total = CourseRecordUtil.getCourseRecordsCount();
            searchContainer.setTotal(total);

            results = CourseRecordUtil.getCourseRecords(
                    searchContainer.getStart(), searchContainer.getResultEnd());
            searchContainer.setResults(results);

            pageContext.setAttribute("results", results);
            pageContext.setAttribute("total", total);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="org.nterlearning.datamodel.catalog.model.CourseRecord"
            keyProperty="courseRecordId"
            modelVar="courseRecord">

        <liferay-ui:search-container-column-text
                name="Student Name">
            <%
                String userName;
                try {
                    UserIdMapper userMapper =
                            UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(
                                PortalPropertiesUtil.getSsoImplementation(), courseRecord.getSingleSignOnValue());
                    userName = UserLocalServiceUtil.getUser(userMapper.getUserId()).getFullName();
                }
                catch (NoSuchUserIdMapperException e) {
                    userName = courseRecord.getSingleSignOnValue() + " (unregistered)";
                }
            %>
            <%= userName %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="Record ID"
                property="courseRecordId"
                align="center" />

        <liferay-ui:search-container-column-text
                name="Course">
            <%
                String entry;
                try {
                    Course course = CourseLocalServiceUtil.findByCourseIri(courseRecord.getCourseIri());
                    String url = course.getUrl();
                    String title = course.getTitle(locale);
                    entry = "<a href='" + url + "'>" + title + "</a>";
                }
                catch (Exception e) {
                    entry = LanguageUtil.get(pageContext, "Unregistered Course");
                }
            %>
            <%= entry %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="Updated Date">
            <%= df.format(courseRecord.getUpdatedDate()) %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="Completion Status"
                property="completionStatus"
                align="center" />

        <liferay-ui:search-container-column-text
                name="Removed"
                align="center">
            <%= courseRecord.getRemoved() %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="User Hidden"
                align="center">
            <%= courseRecord.getUserHidden() %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="Assigned"
                align="center">
            <%= courseRecord.getAssigned() %>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator paginate="<%= true%>" />
</liferay-ui:search-container>


<% // Test Feature: remove CourseRecords %>
<portlet:actionURL name="purgeAllCourseRecords" var="purgeAllCourseRecords" />
<aui:form action="<%= purgeAllCourseRecords %>" method="post">
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="course-testing-purge-all-courserecords" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>


<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-course-component-records") %></h3>
<liferay-ui:search-container
    searchContainer='<%= new SearchContainer(renderRequest, null, null, "curComponentRecord", 5, portletURL, null, "course-testing-no-course-component-records") %>'
    id="componentRecorSearchContainer"
    deltaParam="deltaComponentRecord">
    <liferay-ui:search-container-results>
        <%
            total = ComponentRecordUtil.getComponentRecordCount();
            searchContainer.setTotal(total);

            results = ComponentRecordUtil.getComponentRecords(
                    searchContainer.getStart(), searchContainer.getResultEnd());
            searchContainer.setResults(results);

            pageContext.setAttribute("results", results);
            pageContext.setAttribute("total", total);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="org.nterlearning.datamodel.catalog.model.ComponentRecord"
            keyProperty="componentRecordId"
            modelVar="componentRecord">

        <liferay-ui:search-container-column-text
                name="Record ID"
                property="courseRecordId"
                align="center" />

        <liferay-ui:search-container-column-text
                name="Component">
            <%
                String entry;
                try {
                    Component component = ComponentLocalServiceUtil.findByComponentIri(componentRecord.getComponentIri());
                    String title = component.getTitle();
                    entry = title;
                }
                catch (Exception e) {
                    entry = LanguageUtil.get(pageContext, "Unregistered Component");
                }
            %>
            <%= entry %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="Updated Date">
            <%= df.format(componentRecord.getUpdatedDate()) %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="Completion Status"
                property="completionStatus"
                align="center" />

        <liferay-ui:search-container-column-text
                name="Completion Percent"
                align="center">
            <%= componentRecord.getCompletionPercent() + "%" %>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator paginate="<%= true%>" />
</liferay-ui:search-container>

<% // Test Feature: remove CourseComponent records %>
<portlet:actionURL name="purgeAllComponentRecords"
                   var="purgeAllComponentRecords" />
<aui:form action="<%= purgeAllComponentRecords %>" method="post">
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="course-testing-purge-all-componentrecords" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>

<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "course-testing-global-course-reviews") %></h3>
<% // Test Feature: remove GlobalCourseReview entries %>
<portlet:actionURL name="purgeAllGlobalCourseReviews"
                   var="purgeAllGlobalCourseReviews" />
<aui:form action="<%= purgeAllGlobalCourseReviews %>" method="post">
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="course-testing-purge-all-globalcoursereviews" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>
<% // Test Feature: insert a canned local course review into the DB %>
<portlet:actionURL name="insertCannedLocalCourseReview"
                   var="insertCannedLocalCourseReview" />
<aui:form action="<%= insertCannedLocalCourseReview %>" method="post">
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="course-testing-insert-canned-course-review" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>