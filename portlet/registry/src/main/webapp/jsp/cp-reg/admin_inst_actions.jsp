<%@include file="../init.jsp" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="org.nterlearning.registry.proxy.InstitutionBean" %>
<%@ page import="org.nterlearning.registry.proxy.RegistryInstance" %>

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
	<liferay-ui:icon image="edit" message="Edit" url="<%= editURL %>" />

	<% if (registryInstance == RegistryInstance.LOCAL) { %>
		<portlet:actionURL name="deleteInstitution" var="deleteURL">
			<portlet:param name="institutionKey" value="<%= institutionKey %>" />
		</portlet:actionURL>
		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	<% } %>

</liferay-ui:icon-menu>