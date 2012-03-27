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

<%@ page import="com.sri.nter.service.registry.RegistryUtil" %>
<%@ page import="com.sri.nter.registry.proxy.ServiceBean" %>
<%@ page import="com.sri.nter.registry.proxy.RegistryInstance" %>
<%@ page import="org.nterlearning.xml.nter_registry.blacklist_objects_0_1_0.ActiveStatusEnum" %>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<jsp:useBean id="institution" type="com.sri.nter.registry.proxy.InstitutionBean" scope="request" />
<jsp:useBean id="actionName" type="java.lang.String" scope="request" />

<portlet:actionURL name="<%= actionName %>" var="submitAction"/>
<portlet:actionURL name="viewInstitutions" var="cancelAction"/>

<portlet:defineObjects />

<liferay-ui:error key="error-adding-unique" message="error-adding-unique" />
<liferay-ui:error key="error-updating" message="error-updating" />
<liferay-ui:error key="error-required-name" message="error-required-name" />
<liferay-ui:error key="error-required-institution" message="error-required-institution" />
<liferay-ui:error key="error-required-endpoint" message="error-required-endpoint" />

<%

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
<h2><liferay-ui:message key="institution-label-name"/></h2>
<br/>
<aui:form action="<%= submitAction %>" method="post">

    <aui:fieldset>
    
        <aui:input type="hidden" name="institutionKey"
                value='<%= institution.getKey() %>' />
                
        <aui:input type="hidden" name="registryInstance"
                value='<%= institution.getRegistryInstance() %>' />
                
        <table cellspacing="2">
            <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-name"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getName() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-descr"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getDescription() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-contact-name"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getContactInfo().getPersonName() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-contact-descr"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getContactInfo().getDescription() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-contact-address"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getContactInfo().getAddress() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-contact-email"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getContactInfo().getEmail() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-contact-phone"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getContactInfo().getPhone() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="institution-label-status"/></label></td>
                <td class="align-left col-1 valign-middle"><%= institution.getActiveStatus() %></td>
           </tr>
        </table>     
           
  </aui:fieldset>
</aui:form>

<p><br/></p>
 
<table>
  <tr>
  <td>
    <portlet:actionURL name="editInstitution" var="editInstitution"/>
    <aui:form action="<%= editInstitution %>" method="post">
        <aui:fieldset>
            <aui:input type="hidden" name="institutionName"
                    value='<%= institution.getName() %>' />        
            <aui:button type="submit" value="institution-edit"/>
        </aui:fieldset>
    </aui:form>
  </td>
  <td>&nbsp;&nbsp;</td>
  <td>
    <aui:button type="cancel" onClick="<%= cancelAction %>"/>
  </td>
  </tr>
</table>
  
<p><br/></p>
     
<h2><liferay-ui:message key="service-label-services"/></h2>      
<br/>
        
<liferay-ui:search-container 
    emptyResultsMessage="service-data-empty" 
    delta="100"  >
    <liferay-ui:search-container-results>
        <%     
        List<ServiceBean> services = institution.getServiceBeans();
        
        total = services.size();
        searchContainer.setTotal(total);
        
        results = RegistryUtil.getServices(services,
            searchContainer.getStart(), searchContainer.getResultEnd()); 
                
        searchContainer.setResults(results);

        pageContext.setAttribute("page", total);
        
        pageContext.setAttribute("results", results);
        pageContext.setAttribute("total", total);
        %>
        
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
       className="com.sri.nter.registry.proxy.ServiceBean"
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
          property="name" href="<%= viewServiceURL.toString() %>" 
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

      <liferay-ui:search-container-column-jsp
          path="/html/cp-reg/admin_service_actions.jsp"
          align="right"
      />
      
   </liferay-ui:search-container-row>
   <liferay-ui:search-iterator />
</liferay-ui:search-container>
  

<p><br/></p>

<table>
  <tr>
  <td>
      <portlet:actionURL name="newService" var="newService"/>
      <aui:form action="<%= newService %>" method="post">
        <aui:fieldset>
            <aui:input type="hidden" name="institutionName"
                value='<%= institution.getName() %>' />
            <aui:button type="submit" value="service-add"
                disabled="<%= institution.getRegistryInstance() != RegistryInstance.LOCAL %>"/>
        </aui:fieldset>
      </aui:form>              
  </td>        
  </tr>
</table>
