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