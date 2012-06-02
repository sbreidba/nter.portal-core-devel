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

<%-- course-management/jsp/view.jsp --%>
<%@include file="/course-management/jsp/init.jsp" %>

<%
	DateFormat df = DateFormat.getDateTimeInstance(
			            DateFormat.SHORT, DateFormat.SHORT,
			            themeDisplay.getLocale());
	String searchTerms = ParamUtil.getString(request, NterKeys.SEARCH_TERMS);
	String tabs1 = ParamUtil.getString(request, "tabs1", "Active");

    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("tabs1", tabs1);
%>
<liferay-ui:search-container
        searchContainer='<%= new SearchContainer(renderRequest, null, null, "cur", 10, portletURL, null, "course-management-no-courses") %>'
        emptyResultsMessage="course-management-no-courses"
        delta="10">
	<%
		String tag =
				searchContainer.getPortletRequest().getParameter("tag");
			String categoryId =
				searchContainer.getPortletRequest().getParameter("categoryId");
			if (Validator.isNotNull(tag)) {
				searchTerms =
					Field.ASSET_TAG_NAMES + StringPool.COLON + tag;
			}
			else if (Validator.isNotNull(categoryId)) {
				searchTerms =
					Field.ASSET_CATEGORY_IDS + StringPool.COLON +
						categoryId;
			}
	%>
    
	<aui:form action="<%= renderResponse.createRenderURL().toString() %>"
		      method="POST">
		<aui:field-wrapper inlineField="true">
			<aui:button-row>
				<aui:input label="Keywords" size="50"
					name="<%= NterKeys.SEARCH_TERMS %>" value="<%=searchTerms %>"
					inlineLabel="true" />
				<aui:button name="" value="Search" type="Submit" />
			</aui:button-row>
		</aui:field-wrapper>
	</aui:form>

	<liferay-ui:tabs
		names="Active,Removed"
		url="<%=renderResponse.createRenderURL().toString() %>" />

	<liferay-ui:search-container-results>
		<%
			boolean removed = !tabs1.equals("Active");

			searchContainer = CourseSearchUtil.search(searchContainer, removed,
                                    themeDisplay.getCompanyId(),
                                    themeDisplay.getScopeGroupId());
			pageContext.setAttribute("total", searchContainer.getTotal());
			pageContext.setAttribute("results", searchContainer.getResults());
			if (Validator.isNotNull(searchTerms) ||
				Validator.isNotNull(tag) ||
				Validator.isNotNull(categoryId)) {
		%>
		<aui:a href="<%=renderResponse.createRenderURL().toString() %>">Show All</aui:a>
		<%
			}
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="org.nterlearning.datamodel.catalog.model.Course"
		keyProperty="courseId"
        modelVar="course">

		<liferay-ui:search-container-column-text name="course-management-title">
            <portlet:renderURL var="displayDetailsUrl">
                <portlet:param name="resourcePrimKey" value='<%= String.valueOf(course.getCourseId()) %>'/>
                <portlet:param name="jspPage" value="/course-management/jsp/courseDetails.jsp"/>
                <portlet:param name="redirect" value='<%= renderResponse.createRenderURL().toString() %>' />
                <portlet:param name="tabs1" value='<%= tabs1 %>' />
                <portlet:param name="listCur" value='<%= String.valueOf(searchContainer.getCur()) %>' />
                <portlet:param name="listDelta" value='<%= String.valueOf(searchContainer.getDelta()) %>' />
            </portlet:renderURL>
            <%
                String courseTitle = course.getTitle(locale);
                if (courseTitle.isEmpty()) {
                    courseTitle = LanguageUtil.get(pageContext, "course-management-blank");
                }
            %>
			<a href='<%= displayDetailsUrl %>'><%= courseTitle %></a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="course-management-updated-date">
			<%=df.format(course.getUpdatedDate()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="course-management-tags">
			<liferay-ui:asset-tags-summary
                    classPK="<%= course.getCourseId() %>"
				    className="<%= Course.class.getName() %>"
				    portletURL="<%= renderResponse.createRenderURL() %>" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="course-management-categories">
			<div class="course-attribute">
				<dd>
                    <nter:category className="<%=Course.class.getName() %>"
                                   classPK="<%= course.getPrimaryKey() %>" />
				</dd>
			</div>
			<div class="course-attribute">
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			path="/course-management/jsp/editActions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator searchContainer='<%= searchContainer %>' paginate="<%= true %>" />
</liferay-ui:search-container>
