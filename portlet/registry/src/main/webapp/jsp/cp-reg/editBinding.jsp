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

<%@ page import="org.nterlearning.service.registry.RegistryUtil" %>

<%@ page import="org.nterlearning.registry.client.BindingTypeEnum" %>

<jsp:useBean id="binding" type="org.nterlearning.registry.client.Binding" scope="request" />
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