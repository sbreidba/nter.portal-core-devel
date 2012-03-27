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

<%@ page import="com.sri.nter.service.registry.*" %>
<%@ page import="com.sri.nter.registry.proxy.*" %>
<%@ page import="org.nterlearning.xml.nter_registry.domain_objects_0_1_0.*" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%
PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/html/cp-reg/viewServices.jsp");
iteratorURL.setParameter(ActionRequest.ACTION_NAME, "viewServices");

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

<liferay-ui:search-container 
    emptyResultsMessage="service-data-empty" 
    iteratorURL="<%= iteratorURL %>" delta="10">

    <liferay-ui:search-container-results>
        <%
        List<ServiceBean> services = RegistryUtil.getServices();
        
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
       className="com.sri.nter.registry.proxy.ServiceBean"
       keyProperty="key"
       modelVar="service"
    >   
    <%
        PortletURL viewServiceURL = renderResponse.createActionURL();   
        viewServiceURL.setParameter(ActionRequest.ACTION_NAME, "viewService"); 
        viewServiceURL.setParameter("institutionName", service.getInstitutionName());
        viewServiceURL.setParameter("serviceName", service.getName()); 
        viewServiceURL.setParameter("tabs", tabs); 
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
          name="service-label-institution"
          property="institutionName"
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

<table>
<tr>
  <td>
	<portlet:actionURL name="newService" var="newService"/>
	<aui:form action="<%= newService %>" method="post">
	    <aui:fieldset>
	       <aui:input name="tabs" type="hidden" value="<%= tabs %>"/>
	       <aui:button-row>
	           <aui:button type="submit" value="service-add"/>
	       </aui:button-row>
	    </aui:fieldset>
	</aui:form>
  </td>
</tr>  
</table>

