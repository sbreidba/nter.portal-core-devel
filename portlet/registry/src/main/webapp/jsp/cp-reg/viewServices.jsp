<%@include file="../init.jsp" %>

<%@ page import="org.nterlearning.service.registry.*" %>
<%@ page import="org.nterlearning.registry.proxy.*" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%
PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/jsp/cp-reg/viewServices.jsp");
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
       className="org.nterlearning.registry.proxy.ServiceBean"
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
          path="/jsp/cp-reg/admin_service_actions.jsp"
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

