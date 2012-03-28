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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.sri.nter.service.registry.RegistryUtil" %>
<%@ page import="com.sri.nter.registry.proxy.ServiceBean" %>
<%@ page import="com.sri.nter.registry.proxy.InstitutionBean" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<portlet:actionURL name="viewInstitutions" var="cancelAction"/>

<%
PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/html/cp-reg/viewInstitutions.jsp");
iteratorURL.setParameter(ActionRequest.ACTION_NAME, "viewInstitutions");

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
                     
<liferay-ui:search-container iteratorURL="<%= iteratorURL %>" 
    emptyResultsMessage="institution-data-empty" delta="10">
    <liferay-ui:search-container-results>
        <%
        List<InstitutionBean> institutions = 
            RegistryUtil.getInstitutions();
        
        total = institutions.size();
        searchContainer.setTotal(total);
        
        results = RegistryUtil.getInstitutions(institutions,
                searchContainer.getStart(), searchContainer.getResultEnd());
        
        searchContainer.setResults(results);

        pageContext.setAttribute("results", results);
        pageContext.setAttribute("total", total);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
       className="com.sri.nter.registry.proxy.InstitutionBean"
       keyProperty="key"
       modelVar="institution"
    >
     <%
        PortletURL viewInstitutionURL = renderResponse.createActionURL();
        viewInstitutionURL.setParameter(ActionRequest.ACTION_NAME, "viewInstitution");        
        viewInstitutionURL.setParameter("institutionName", institution.getName());         
     %>
      <liferay-ui:search-container-column-text
          name="institution-label-institution" 
          property="name" href="<%= viewInstitutionURL.toString() %>"/>
      
      <liferay-ui:search-container-column-text
          name="institution-label-descr"
          property="description" 
      />
      
      <liferay-ui:search-container-column-text
          name="institution-label-contact-name"
          property="contactInfo.personName"
      />
      
      <liferay-ui:search-container-column-text
          name="institution-label-contact-email"
          property="contactInfo.email"
      />
      
      <liferay-ui:search-container-column-text
          name="institution-label-status"
          property="activeStatus"
      />   
     
      <liferay-ui:search-container-column-jsp
          path="/html/cp-reg/admin_inst_actions.jsp"
          align="right"
      />
 
   </liferay-ui:search-container-row>
   <liferay-ui:search-iterator />

</liferay-ui:search-container>


<portlet:actionURL name="newInstitution" var="newInstitution"/>
<aui:form action="<%= newInstitution %>" method="post">
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="institution-add"/>
        </aui:button-row>
    </aui:fieldset>
</aui:form>
