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

<%@ page import="java.util.List" %>

<%@ page import="org.nterlearning.registry.proxy.RegistryInstance" %>
<%@ page import="org.nterlearning.registry.proxy.ServiceBean" %>
<%@ page import="org.nterlearning.registry.client.ActiveStatusEnum" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<jsp:useBean id="institution" type="org.nterlearning.registry.proxy.InstitutionBean" scope="request" />
<jsp:useBean id="actionName" type="java.lang.String" scope="request" />

<portlet:actionURL name="<%= actionName %>" var="submitAction"/>
<portlet:actionURL name="viewInstitutions" var="cancelAction"/>

<portlet:defineObjects />

<liferay-ui:error key="error-updating" message="error-updating" />
<liferay-ui:error key="error-adding-unique-inst" message="error-adding-unique-inst" />
<liferay-ui:error key="error-required-institution-name" message="error-required-institution-name" />
<liferay-ui:error key="error-required-contact" message="error-required-contact" />
<liferay-ui:error key="error-required-email" message="error-required-email" />

<%
PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/jsp/cp-reg/viewServices.jsp");
iteratorURL.setParameter(ActionRequest.ACTION_NAME, "viewServices");

String tabs = ParamUtil.getString(request, "tabs", "Institutions");

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

<% if (actionName.equals("updateInstitution")) { %>
    <h2><liferay-ui:message key="institution-edit"/></h2>
<% } else { %>
    <h2><liferay-ui:message key="institution-add"/></h2>
<% } %>

<aui:form action="<%= submitAction %>" method="post">

    <aui:fieldset>

        <aui:input type="hidden" name="institutionKey"
                value="<%= institution.getKey() %>" />

        <aui:input type="hidden" name="registryInstance"
                value="<%= institution.getRegistryInstance().value() %>" />

        <% if (institution.getRegistryInstance() == RegistryInstance.LOCAL) { %>
            <% if (actionName.equals("updateInstitution")) { %>
	            <aui:input type="hidden" name="institutionName"
	                value="<%= institution.getName() %>" />
	            <aui:input type="text"
	                label="institution-label-name-required"
	                name="instName"
	                value="<%= institution.getName() %>"
	                maxlength="74"
	                size="25" disabled="true" />
            <% } else { %>
                <aui:input type="text"
	                label="institution-label-name-required"
	                name="institutionName"
	                value="<%= institution.getName() %>"
	                maxlength="74"
	                size="25" />
            <% }%>

            <aui:input type="text" label="institution-label-descr" name="description"
                value="<%= institution.getDescription() %>" maxlength="254" size="45"/>

            <aui:input type="text" label="institution-label-contact-name-required" name="contactName"
                value="<%= institution.getContactInfo().getPersonName() %>"
                maxlength="99" size="25" />

            <aui:input type="text" label="institution-label-contact-descr" name="contactDescription"
                value="<%= institution.getContactInfo().getDescription() %>"
                maxlength="254" size="25" />

	        <aui:input type="text" label="institution-label-contact-address" name="contactAddress"
	                value="<%= institution.getContactInfo().getAddress() %>"
	                maxlength="254" size="25" />

	        <aui:input type="text" label="institution-label-contact-email-required" name="contactEmail"
	                value="<%= institution.getContactInfo().getEmail() %>"
	                maxlength="254" size="25" />

	        <aui:input type="text" label="institution-label-contact-phone" name="contactPhone"
	                value="<%= institution.getContactInfo().getPhone() %>"
	                maxlength="254" size="25" />

        <% } else { %>
            <aui:input type="hidden"
                name="institutionName"
                value="<%= institution.getName() %>" />

            <aui:input type="text"
                label="institution-label-name"
                name="institutionName"
                value="<%= institution.getName() %>"
                maxlength="74"
                size="25"
                disabled="true"/>

            <aui:input type="hidden"
                name="description"
                value="<%= institution.getDescription() %>" />
            <aui:input type="text"
                label="Description"
                name="description"
                value="<%= institution.getDescription() %>"
                maxlength="254"
                size="45"
                disabled="true"/>

            <aui:input type="hidden"
                name="contactName"
                value="<%= institution.getContactInfo().getPersonName() %>" />
            <aui:input type="text"
                label="institution-label-contact-name"
                name="contactName"
                value="<%= institution.getContactInfo().getPersonName() %>"
                maxlength="99"
                size="25"
                disabled="true"/>

            <aui:input type="hidden"
                name="contactDescription"
                value="<%= institution.getContactInfo().getDescription() %>" />
	        <aui:input type="text"
	           label="institution-label-contact-descr"
	           name="contactDescription"
	           value="<%= institution.getContactInfo().getDescription() %>"
	           maxlength="254"
	           size="25"
	           disabled="true" />

	        <aui:input type="hidden"
	           name="contactAddress"
                value="<%= institution.getContactInfo().getAddress() %>" />
	        <aui:input type="text"
	           label="institution-label-contact-address"
	           name="contactAddress"
	           value="<%= institution.getContactInfo().getAddress() %>"
	           maxlength="254"
	           size="25"
	           disabled="true" />

	        <aui:input type="hidden"
	           name="contactEmail"
               value="<%= institution.getContactInfo().getEmail() %>" />
	        <aui:input type="text"
	           label="institution-label-contact-email"
	           name="contactEmail"
	           value="<%= institution.getContactInfo().getEmail() %>"
	           maxlength="254"
	           size="25"
	           disabled="true" />

	        <aui:input type="hidden"
	           name="contactPhone"
               value="<%= institution.getContactInfo().getPhone() %>" />
	        <aui:input type="text"
	           label="institution-label-contact-phone"
	           name="contactPhone"
	           value="<%= institution.getContactInfo().getPhone() %>"
	           maxlength="254"
	           size="25"
	           disabled="true" />

        <% } %>

        <aui:select label="institution-label-status" name="statusType">
        <%
        String currStatusTypeName = institution.getActiveStatus().value();
        for (ActiveStatusEnum statusType : RegistryUtil.getStatusTypes()) {
            String statusTypeName = statusType.value();
        %>
          <aui:option selected="<%= currStatusTypeName.equals(statusTypeName) %>"
            value="<%= statusTypeName %>"><%= statusTypeName %>
          </aui:option>
     <% } %>
        </aui:select>

        <p><br/></p>

        <aui:button-row>
            <aui:button type="submit"/>
            <aui:button type="cancel" onClick="history.go(-1)"/>
        </aui:button-row>
    </aui:fieldset>
</aui:form>

<% if (actionName.equals("updateInstitution")) { %>

<p><br/></p>

<h2><liferay-ui:message key="service-label-services"/></h2>
<br/>
<liferay-ui:search-container
    emptyResultsMessage="service-data-empty" delta="10">
    <liferay-ui:search-container-results>
        <%
        List<ServiceBean> services = institution.getServiceBeans();

        total = services.size();
        searchContainer.setTotal(total);

        results = RegistryUtil.getServices(services,
                searchContainer.getStart(), searchContainer.getResultEnd());

        searchContainer.setResults(results);

        pageContext.setAttribute("results", results);
        pageContext.setAttribute("total", total);
        %>

    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
       className="org.nterlearning.registry.proxy.ServiceBean"
       keyProperty="key"
       modelVar="service"
    >
        <%
          PortletURL viewServiceURL = renderResponse.createActionURL();
          viewServiceURL.setParameter(ActionRequest.ACTION_NAME, "viewService");
          viewServiceURL.setParameter("institutionName", institution.getName());
          viewServiceURL.setParameter("serviceName", service.getName());
          %>
      <liferay-ui:search-container-column-text
          name="service-label-service"
          property="name" href="<%= viewServiceURL %>"
      />
      <liferay-ui:search-container-column-text
          name="service-label-descr"
          property="description"
      />
      <liferay-ui:search-container-column-text
          name="service-label-serviceType"
          property="serviceTypeValue"
      />
      <liferay-ui:search-container-column-text
          name="service-label-status"
          property="activeStatus"
      />
      <% if (service.getRegistryInstance() == RegistryInstance.LOCAL) { %>
      <liferay-ui:search-container-column-jsp
          path="/jsp/cp-reg/admin_service_actions.jsp"
          align="right"
      />
      <% } %>

   </liferay-ui:search-container-row>
   <liferay-ui:search-iterator />
</liferay-ui:search-container>

<p><br/></p>

<portlet:actionURL name="newService" var="newService"/>
<aui:form action="<%= newService %>" method="post">
    <aui:fieldset>
        <aui:input type="hidden" name="institutionKey"
                value='<%= institution.getKey() %>' />
        <aui:input type="hidden" name="institutionName"
                   value='<%= institution.getName() %>' />
        <aui:button-row>
            <aui:button type="submit" value="service-add"
                disabled="<%= institution.getRegistryInstance() != RegistryInstance.LOCAL %>"/>
        </aui:button-row>
    </aui:fieldset>
</aui:form>
<% } %>

