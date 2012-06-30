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

<%@include file="/course-management/jsp/init.jsp" %>

<%
    ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    Course course = (Course)row.getObject();
    String primKey = String.valueOf(course.getPrimaryKey());

    String listCurrent = ParamUtil.getString(request, "cur", "1");
    String listDelta = ParamUtil.getString(request, "delta", "10");
    String tabs = ParamUtil.getString(request, "tabs", "Active");
%>

<liferay-ui:icon-menu showWhenSingleIcon="true">
    
		<portlet:renderURL var="displayDetailsURL">
		    <portlet:param name="jspPage" value="/course-management/jsp/courseDetails.jsp"></portlet:param>
		    <portlet:param name="resourcePrimKey" value="<%= primKey %>"></portlet:param>
		    <portlet:param name="redirect" value="<%=renderResponse.createRenderURL().toString() %>" />
            <portlet:param name="tabs1" value='<%= tabs %>' />
            <portlet:param name="listDelta" value='<%= listDelta %>'/>
            <portlet:param name="listCur" value='<%= listCurrent %>'/>
		</portlet:renderURL>

        <portlet:actionURL name="markCourseAsFeatured" var="markCourseAsFeaturedUrl">
            <portlet:param name="resourcePrimKey" value="<%= primKey %>" />
        </portlet:actionURL>

        <portlet:actionURL name="markCourseAsNotFeatured" var="markCourseAsNotFeaturedUrl">
            <portlet:param name="resourcePrimKey" value="<%= primKey %>" />
        </portlet:actionURL>

	    <liferay-security:permissionsURL var="permissionURL"
		    modelResource="<%= Course.class.getName() %>"
		    modelResourceDescription="<%= course.getTitle(locale) %>"
		    resourcePrimKey="<%= String.valueOf(course.getPrimaryKey()) %>"/>


	<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, themeDisplay.getPortletDisplay().getId(), ActionKeys.UPDATE) %>'>
		<c:if test='<%= !course.isRemoved() && !course.isFeatured() %>'>
			<liferay-ui:icon image="edit" message="mark-featured" url="<%= markCourseAsFeaturedUrl %>" />
		</c:if>
		<c:if test='<%= !course.isRemoved() && course.isFeatured() %>'>
			<liferay-ui:icon image="edit" message="mark-not-featured" url="<%= markCourseAsNotFeaturedUrl %>" />
		</c:if>

		<liferay-ui:icon image="attributes" message="course-management-show-details" url="<%= displayDetailsURL %>"/>

        <liferay-ui:icon image="download" message="course-management-show-course-page" url="<%= course.getUrl() %>" />
	</c:if>

	<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, themeDisplay.getPortletDisplay().getId(), ActionKeys.PERMISSIONS) %>'>
	    <liferay-ui:icon image="permissions" url="<%= permissionURL %>" />
	</c:if>

</liferay-ui:icon-menu>