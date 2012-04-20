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
<%@ page import="org.nterlearning.xml.nter_registry.domain_objects_0_1_0.Binding" %>
<%@ page import="org.nterlearning.registry.proxy.RegistryInstance" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<jsp:useBean id="service" type="org.nterlearning.registry.proxy.ServiceBean" scope="request" />
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
String tabs = ParamUtil.getString(request, "tabs", "Services");

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
<h2><liferay-ui:message key="service-label-service"/></h2>
<br/>
<aui:form action="<%= submitAction %>" method="post">

    <aui:fieldset>

        <aui:input type="hidden" name="tabs"
                value="<%= request.getAttribute(\"tabs\") %>" />

        <aui:input type="hidden" name="serviceKey"
                value='<%= service.getKey() %>' />

        <aui:input type="hidden" name="serviceName"
                value='<%= service.getName() %>' />

        <aui:input type="hidden" name="institutionName"
                value='<%= service.getInstitutionName() %>' />

        <table cellspacing="2">
            <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="service-label-name"/></label></td>
                <td class="align-left col-1 valign-middle"><%= service.getName() %></td>
            </tr>
            <tr class="portlet-section-alternate results-row">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="service-label-institution"/></label></td>
                <td class="align-left col-1 valign-middle"><%= service.getInstitutionName() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="service-label-descr"/></label></td>
                <td class="align-left col-1 valign-middle"><%= service.getDescription() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="service-label-serviceType"/></label></td>
                <td class="align-left col-1 valign-middle"><%= service.getServiceType().value() %></td>
           </tr>
           <tr class="portlet-section-alternate results-row alt">
                <td class="align-left col-0 first valign-middle"><label class="aui-field-label"><liferay-ui:message key="service-label-status"/></label></td>
                <td class="align-left col-1 valign-middle"><%= service.getActiveStatus() %></td>
           </tr>
        </table>

 </aui:fieldset>
</aui:form>

<p><br/></p>

<table>
  <tr>
  <td>
    <portlet:actionURL name="editService" var="editService"/>
    <aui:form action="<%= editService %>" method="post">
        <aui:fieldset>
            <aui:input type="hidden" name="serviceName"
                value='<%= service.getName() %>' />
            <aui:input type="hidden" name="institutionName"
                value='<%= service.getInstitutionName() %>' />
                <aui:input type="hidden" name="tabs"
                value='<%= tabs %>' />
            <aui:button type="submit" value="service-edit"/>
        </aui:fieldset>
    </aui:form>
  </td>
  <td>&nbsp;&nbsp;</td>
  <td>
    <aui:button type="cancel" onClick="history.go(-1);return false;"/>
  </td>
  </tr>
</table>

<p><br/></p>

<h2><liferay-ui:message key="binding-label-bindings"/></h2>
<br/>

<liferay-ui:search-container
    emptyResultsMessage="binding-data-empty" delta="100">
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
       className="org.nterlearning.xml.nter_registry.domain_objects_0_1_0.Binding"
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

<table>
  <tr>
  <td>
      <portlet:actionURL name="newBinding" var="newBinding"/>
      <aui:form action="<%= newBinding %>" method="post">
        <aui:fieldset>
            <aui:input type="hidden" name="serviceName"
                    value='<%= service.getName() %>' />

            <aui:input type="hidden" name="institutionName"
                    value='<%= service.getInstitutionName() %>' />
            <aui:button type="submit" value="binding-add"
                disabled="<%= service.getRegistryInstance() == RegistryInstance.GLOBAL %>"/>
        </aui:fieldset>
      </aui:form>
  </td>
  </tr>
</table>