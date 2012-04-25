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

<%-- flag-report-list.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ tag import="org.nterlearning.datamodel.catalog.model.FlagReport" %>
<%@ tag import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ tag import="com.liferay.portal.kernel.log.Log" %>
<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ tag import="java.util.List" %>
<%@ tag import="java.text.DateFormat" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ tag import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ tag import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ tag import="javax.portlet.PortletURL" %>

<%@ attribute name="reviewId" type="java.lang.Long" required="true" %>
<%@ attribute name="classNameId" type="java.lang.Long" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
    _log.info(" Entered flag-report-list.tag");

    List<FlagReport> flagReports = FlagReportLocalServiceUtil.findByClassNameIdClassPK(classNameId, reviewId);
    _log.info("flag report size = " + flagReports.size());
    PortletURL portletURL = renderResponse.createRenderURL();
%>
   <liferay-ui:search-container
        searchContainer='<%= new SearchContainer(renderRequest, null, null, "curFlagReport", 5,  portletURL, null, "flag-report-no-reports") %>'
        id="flagReportSearchContainer"
        delta="<%= flagReports.size() %>">
        <liferay-ui:search-container-results>
        <%
            // set the total number of results in the search container.
            searchContainer.setResults(flagReports);
            searchContainer.setTotal(flagReports.size());
            _log.info("search container  total = " + searchContainer.getTotal());

            // set the pagination sublist of results on the page.
            pageContext.setAttribute("results", flagReports);
            pageContext.setAttribute("total", flagReports.size());
        %>
        </liferay-ui:search-container-results>
        <liferay-ui:search-container-row
            className="FlagReport"
            modelVar="flagReport"
            rowVar="thisrow"  >
            <%
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
                String createDateString = "";
                if (flagReport.getCreateDate() != null) {
                    createDateString = df.format(flagReport.getCreateDate());
                }
                String statusDateString = "";
                if (flagReport.getStatusDate() != null) {
                    statusDateString = df.format(flagReport.getStatusDate());
                }
                String moderateStatus ="";
                if (flagReport.isModerated()) {
                     // group status string
                    moderateStatus = WorkflowConstants.toLabel(flagReport.getStatus()) + " by " +
                         UserLocalServiceUtil.getUserById(flagReport.getStatusByUserId()).getFullName() + " on " + statusDateString;
                } else {
                    moderateStatus = WorkflowConstants.toLabel(flagReport.getStatus());
                }
            %>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"flag-table-column-date\") %>">
                <%= createDateString %>
            </liferay-ui:search-container-column-text>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"flag-table-column-status\") %>">
                 <%= moderateStatus %>
            </liferay-ui:search-container-column-text>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"flag-table-column-reporter\") %>">
                 <%= UserLocalServiceUtil.getUserById(flagReport.getUserId()).getFullName() %>
            </liferay-ui:search-container-column-text>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"flag-table-column-reason\") %>">
                 <%= flagReport.getFlagReason() %>
            </liferay-ui:search-container-column-text>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"flag-table-column-comment\") %>">
                 <%= flagReport.getFlagComment() %>
            </liferay-ui:search-container-column-text>
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator paginate="<%= true %>" />
    </liferay-ui:search-container>
<%! private static final Log _log = LogFactoryUtil.getLog("ntercatalogportlet.docroot.tags.flag-entry.tag"); %>


