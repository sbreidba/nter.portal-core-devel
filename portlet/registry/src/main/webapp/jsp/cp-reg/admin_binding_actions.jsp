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

<%
/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.	
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */
%>

<%@include file="/html/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil"%>

<%@ page import="com.sri.nter.registry.proxy.ServiceBean" %>
<%@ page import="org.nterlearning.xml.nter_registry.domain_objects_0_1_0.Binding" %>
<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Binding binding = (Binding)row.getObject();

String bindingKey = String.valueOf(binding.getKey());

String institutionName = (String)request.getAttribute("institutionName");
String serviceName = (String)request.getAttribute("serviceName");
%>

<liferay-ui:icon-menu>

	<portlet:actionURL name="editBinding" var="editURL">
	    <portlet:param name="bindingKey" value="<%= bindingKey %>" />
	    <portlet:param name="description" value="<%= binding.getDescription() %>" />
	    <portlet:param name="accessPoint" value="<%= binding.getAccessPoint() %>" />
	    <portlet:param name="bindingType" value="<%= binding.getBindingType().value() %>" />
	    <portlet:param name="institutionName" value="<%= institutionName %>" />
	    <portlet:param name="serviceName" value="<%= serviceName %>" />
	</portlet:actionURL>
	<liferay-ui:icon image="edit" message="Edit" url="<%= editURL %>" />

	<portlet:actionURL name="deleteBinding" var="deleteURL">
		<portlet:param name="bindingKey" value="<%= bindingKey %>" />
		<portlet:param name="institutionName" value="<%= institutionName %>" />
           <portlet:param name="serviceName" value="<%= serviceName %>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="<%= deleteURL %>" />
	
</liferay-ui:icon-menu>