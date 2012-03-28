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

<%@ page import="com.sri.nter.registry.proxy.InstitutionBean" %>
<%@ page import="com.sri.nter.registry.proxy.RegistryInstance" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
InstitutionBean institution = (InstitutionBean)row.getObject();

String institutionName = institution.getName();
String institutionKey = String.valueOf(institution.getKey());
RegistryInstance registryInstance = institution.getRegistryInstance();
if (registryInstance == null) {
    System.out.println("admin_inst_actions - RegistryInstance is 'Null' defaulting to GLobal");
    registryInstance = RegistryInstance.GLOBAL;    
}
%>

<liferay-ui:icon-menu>

	<portlet:actionURL name="editInstitution" var="editURL">
	    <portlet:param name="institutionName" value="<%= institutionName %>" />
	    <portlet:param name="registryInstance" value="<%= registryInstance.name() %>" />
	</portlet:actionURL>
	<liferay-ui:icon image="edit" message="Edit" url="<%= editURL.toString() %>" />
		
	<% if (registryInstance == RegistryInstance.LOCAL) { %>
		<portlet:actionURL name="deleteInstitution" var="deleteURL">
			<portlet:param name="institutionKey" value="<%= institutionKey %>" />
		</portlet:actionURL>
		<liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
	<% } %>
	
</liferay-ui:icon-menu>