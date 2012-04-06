<%@include file="../init.jsp" %>

<%@ page import="org.nterlearning.service.registry.RegistryUtil" %>

<%@ page import="org.nterlearning.xml.nter_registry.domain_objects_0_1_0.BindingTypeEnum" %>

<jsp:useBean id="binding" type="org.nterlearning.xml.nter_registry.domain_objects_0_1_0.Binding" scope="request" />
<jsp:useBean id="service" type="org.nterlearning.registry.proxy.ServiceBean" scope="request" />

<jsp:useBean id="actionName" type="java.lang.String" scope="request" />

<portlet:actionURL name="<%= actionName %>" var="submitAction"/>

<liferay-ui:error key="binding-required-descr" message="binding-required-descr" />
<liferay-ui:error key="binding-required-endpoint" message="binding-required-endpoint" />

<% if (actionName.equals("addBinding")) { %>
    <h2><liferay-ui:message key="binding-add"/></h2>
<% } else { %>
    <h2><liferay-ui:message key="binding-edit"/></h2>
<% } %>

<aui:form action="<%= submitAction %>" method="post">

    <aui:fieldset>

        <aui:input type="hidden" name="serviceName"
                value="<%= service.getName() %>" />

        <aui:input type="hidden" name="institutionName"
                value="<%= service.getInstitutionName() %>" />

        <aui:input type="hidden" name="bindingKey"
                value="<%= binding.getKey() %>" />

        <aui:input type="text" label="binding-label-descr-required" name="description"
                value="<%= binding.getDescription() %>"
                maxlength="254" size="45" first="true"/>

        <aui:input type="text" label="binding-label-endPoint-required" name="accessPoint"
                value="<%= binding.getAccessPoint() %>" maxlength="254" size="45" />

        <aui:select label="binding-label-bindingType" name="bindingType">
        <%
        BindingTypeEnum currBindingType = binding.getBindingType();
        for (BindingTypeEnum bindingType : RegistryUtil.getBindingTypes()) {
        %>
          <aui:option selected="<%= currBindingType == bindingType %>" value="<%= bindingType.value() %>"><%= bindingType.value() %></aui:option>
        <%
        }
        %>
        </aui:select>

        <p><br/></p>

        <aui:button type="submit" />
        <aui:button type="cancel" onClick="history.go(-1);return false;"/>

    </aui:fieldset>
</aui:form>