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

<%@include file="../init.jsp" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="org.nterlearning.registry.proxy.ServiceBean" %>
<%@ page import="org.nterlearning.registry.proxy.RegistryInstance" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
ServiceBean serviceBean = (ServiceBean)row.getObject();

String institutionName = serviceBean.getInstitutionName();
String serviceKey = String.valueOf(serviceBean.getKey());
String serviceName = serviceBean.getName();
RegistryInstance registryInstance = serviceBean.getRegistryInstance();
if (registryInstance == null) {
    System.out.println("admin_service_actions - RegistryInstance is 'Null' defaulting to GLobal");
    registryInstance = RegistryInstance.GLOBAL;
}

String tabs = (String)request.getAttribute("tabs");
%>

<liferay-ui:icon-menu>

	<portlet:actionURL name="editService" var="editURL">
	    <portlet:param name="tabs" value="<%= tabs %>" />
	    <portlet:param name="serviceName" value="<%= serviceName %>" />
	    <portlet:param name="institutionName" value="<%= institutionName %>" />
	    <portlet:param name="registryInstance" value="<%= registryInstance.name() %>" />
	</portlet:actionURL>
	<liferay-ui:icon image="edit" message="Edit" url="<%= editURL %>" />

	<% if (registryInstance == RegistryInstance.LOCAL) { %>
		<portlet:actionURL name="deleteService" var="deleteURL">
		  <portlet:param name="tabs" value="<%= tabs %>" />
		  <portlet:param name="serviceKey" value="<%= serviceKey %>" />
		  <portlet:param name="institutionName" value="<%= institutionName %>" />
		</portlet:actionURL>
		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	<% } %>
</liferay-ui:icon-menu>