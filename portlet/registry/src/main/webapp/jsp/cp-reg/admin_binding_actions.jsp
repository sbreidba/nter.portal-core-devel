<%@include file="../init.jsp" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

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