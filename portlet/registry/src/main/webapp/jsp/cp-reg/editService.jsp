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
<%@ page import="org.nterlearning.registry.proxy.RegistryInstance" %>
<%@ page import="org.nterlearning.registry.proxy.InstitutionBean" %>
<%@ page import="org.nterlearning.registry.client.ServiceTypeEnum" %>
<%@ page import="org.nterlearning.registry.client.Binding" %>
<%@ page import="org.nterlearning.registry.client.ActiveStatusEnum" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<jsp:useBean id="service" type="org.nterlearning.registry.proxy.ServiceBean" scope="request" />
<jsp:useBean id="actionName" type="java.lang.String" scope="request" />

<portlet:actionURL name="<%= actionName %>" var="submitAction"/>

<portlet:defineObjects />

<liferay-ui:error key="error-adding-unique" message="error-adding-unique" />
<liferay-ui:error key="error-updating" message="error-updating" />
<liferay-ui:error key="error-required-name" message="error-required-name" />
<liferay-ui:error key="error-required-institution" message="error-required-institution" />
<liferay-ui:error key="error-required-endpoint" message="error-required-endpoint" />

<%
String tabs = ParamUtil.getString(request, "tabs", "Services");
String institutionName = (String)request.getAttribute("institutionName");

PortletURL servicesURL = renderResponse.createActionURL();
servicesURL.setParameter("tabs", tabs);
servicesURL.setParameter(ActionRequest.ACTION_NAME, "viewServices");

PortletURL institutionsURL = renderResponse.createActionURL();
institutionsURL.setParameter("tabs", tabs);
institutionsURL.setParameter(ActionRequest.ACTION_NAME, "viewInstitutions");
%>
<liferay-ui:tabs param="tabs"
                 names="Institutions,Services"
                 value="<%= tabs %>"
                 url0="<%= institutionsURL.toString() %>"
                 url1="<%= servicesURL.toString() %>" />

<% if (actionName.equals("addService")) { %>
    <h2><liferay-ui:message key="service-add"/></h2>
<% } else { %>
    <h2><liferay-ui:message key="service-edit"/></h2>
<% } %>
<aui:form action="<%= submitAction %>" method="post">

    <aui:fieldset>

            <aui:input type="hidden" name="tabs"
                value="<%= request.getAttribute(\"tabs\") %>" />

            <aui:input type="hidden" name="serviceKey"
                value="<%= service.getKey() %>" />

            <aui:input type="hidden" name="registryInstance"
                value="<%= service.getRegistryInstance().name() %>" />

        <% if (actionName.equals("addService")) { %>
            <aui:input type="text"
                       label="service-label-name-required"
                       name="serviceName"
                       value="<%= service.getName() %>"
                       maxlength="49"
                       size="25" />
        <% } else { %>
            <aui:input type="hidden" name="serviceName"
                value="<%= service.getName() %>" />
            <aui:input type="text"
                       label="service-label-name-required"
                       name="serviceName"
                       value="<%= service.getName() %>"
                       maxlength="49"
                       size="25"
                       disabled="true" />
        <% } %>

<%      if (actionName.equals("addService")) {
            if (institutionName != null && !institutionName.isEmpty()) {
%>
                <aui:input type="hidden" name="institutionName"
                        value="<%= institutionName %>" />
	            <aui:input type="text" label="service-label-institution-required"
	                       name="instName"
	                       value="<%= institutionName %>"
	                       maxlength="74"
	                       size="25"
	                       disabled="true" />
<%
            } else {
%>
                <aui:select label="service-label-institution-required" name="institutionName">
<%
                for (InstitutionBean institution : RegistryUtil.getLocalInstitutions()) {
%>
                    <aui:option value="<%= institution.getName() %>"><%= institution.getName() %></aui:option>
<%
                }
%>
                </aui:select>
<%
            }
        } else {
%>
            <aui:input type="hidden" name="institutionName"
                        value="<%= service.getInstitutionName() %>" />
            <aui:input type="text" label="service-label-institution-required"
                       name="instName"
                       value="<%= service.getInstitutionName() %>"
                       maxlength="74"
                       size="25"
                       disabled="true" />
<%
        }
%>

        <% if (service.getRegistryInstance() == RegistryInstance.LOCAL) { %>
	        <aui:input type="text" label="service-label-descr" name="description"
	                value="<%= service.getDescription() %>" maxlength="254" size="45" />

	        <aui:select label="service-label-serviceType" name="serviceType">
	        <%
	        ServiceTypeEnum currServiceType = service.getServiceType();
	        for (ServiceTypeEnum serviceType : RegistryUtil.getServiceTypes()) {
	        %>
	          <aui:option selected="<%= currServiceType == serviceType %>" value="<%= serviceType.value() %>"><%= serviceType.value() %></aui:option>
	        <%
	        }
	        %>
	        </aui:select>

        <% } else { %>
            <aui:input type="hidden" name="description"
                value="<%= service.getDescription() %>" />
            <aui:input type="text" label="service-label-descr" name="description"
                    value="<%= service.getDescription() %>" maxlength="254" size="45"
                    disabled="true" />

            <aui:input type="hidden" name="serviceType"
                value="<%= service.getServiceType().value() %>" />
            <aui:select label="service-label-serviceType" name="serviceType"
                disabled="true">
            <%
            for (ServiceTypeEnum serviceType : RegistryUtil.getServiceTypes()) {
                ServiceTypeEnum currServiceType = service.getServiceType();
            %>
              <aui:option selected="<%= currServiceType == serviceType %>"
                value="<%= serviceType.value() %>"><%= serviceType.value() %>
              </aui:option>
            <%
            }
            %>
            </aui:select>

        <% } %>

        <aui:select label="service-label-status" name="statusType">
        <%
        String currStatusTypeName = service.getActiveStatus().value();
        if (currStatusTypeName == null) {
            currStatusTypeName = "";
        }
        for (ActiveStatusEnum statusType : RegistryUtil.getStatusTypes()) {
            String statusTypeName = statusType.value();
        %>
          <aui:option selected="<%= currStatusTypeName.equals(statusTypeName) %>"
            value="<%= statusTypeName %>"><%= statusTypeName %>
          </aui:option>
        <%
        }
        %>
        </aui:select>

        <p><br/></p>

        <aui:button type="submit" />
        <aui:button type="cancel" onClick="history.go(-1)"/>

 </aui:fieldset>
</aui:form>

<% if (actionName.equals("updateService")) { %>

<p><br/></p>

<h2>Bindings</h2>
<br/>

<liferay-ui:search-container
    emptyResultsMessage="binding-data-empty" delta="10">
    <liferay-ui:search-container-results>
        <%
        List<Binding> bindings = service.getBinding();

        total = bindings.size();
        searchContainer.setTotal(total);

        results = RegistryUtil.getBindings(bindings,
                searchContainer.getStart(), searchContainer.getResultEnd());

        searchContainer.setResults(results);

        pageContext.setAttribute("results", results);
        pageContext.setAttribute("total", total);
        %>

    </liferay-ui:search-container-results>

     <liferay-ui:search-container-row
       className="org.nterlearning.registry.client.Binding"
       keyProperty="key"
       modelVar="binding"
    >
      <liferay-ui:search-container-column-text
          name="binding-label-descr"
          property="description"
      />
      <liferay-ui:search-container-column-text
          name="binding-label-bindingType"
          value="<%= service.getBindingTypeValue(binding.getBindingType()) %>"
      />
      <liferay-ui:search-container-column-text
          name="binding-label-endPoint"
          property="accessPoint"
          href="<%= binding.getAccessPoint() %>"
          target="_blank"
      />
      <% if (service.getRegistryInstance() == RegistryInstance.LOCAL) { %>
      <liferay-ui:search-container-column-jsp
          path="/jsp/cp-reg/admin_binding_actions.jsp"
          align="right"
      />
      <% } %>
   </liferay-ui:search-container-row>
   <liferay-ui:search-iterator />

</liferay-ui:search-container>

<p><br/></p>

<portlet:actionURL name="newBinding" var="newBinding"/>
<aui:form action="<%= newBinding %>" method="post">
   <aui:fieldset>
       <aui:input type="hidden" name="institutionName"
           value='<%= service.getInstitutionName() %>' />
       <aui:input type="hidden" name="serviceName"
           value='<%= service.getName() %>' />
       <aui:button type="submit" value="binding-add" disabled="<%= service.getRegistryInstance() == RegistryInstance.GLOBAL %>"/>
   </aui:fieldset>
 </aui:form>
 <% } %>
