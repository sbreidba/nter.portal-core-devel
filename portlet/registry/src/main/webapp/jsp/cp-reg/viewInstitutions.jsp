<%@include file="../init.jsp" %>

<%@ page import="org.nterlearning.service.registry.RegistryUtil" %>
<%@ page import="org.nterlearning.registry.proxy.InstitutionBean" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="javax.portlet.ActionRequest" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<portlet:defineObjects />

<portlet:actionURL name="viewInstitutions" var="cancelAction"/>

<%
PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "viewInstitutions.jsp");
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
        List<InstitutionBean> institutions = new ArrayList<InstitutionBean>();
        try {
            institutions = RegistryUtil.getInstitutions();
        } catch (Exception e) {
            System.out.println(e);
        }
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
       className="org.nterlearning.registry.proxy.InstitutionBean"
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
          path="/jsp/cp-reg/admin_inst_actions.jsp"
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
