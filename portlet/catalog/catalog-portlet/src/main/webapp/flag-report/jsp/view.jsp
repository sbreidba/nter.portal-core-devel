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

<%-- flag-report/jsp/view.jsp --%>

<%@include file="/flag-report/jsp/init.jsp" %>
<%

	DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
    String tabs1 = ParamUtil.getString(request, "tabs1", "Active");

    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("curFlagReport", ParamUtil.getString(request, "curFlagReport", "1"));
    portletURL.setParameter("deltaFlagReport", ParamUtil.getString(request, "deltaFlagReport", "5"));
    portletURL.setParameter("tabs1",tabs1);
%>

<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "flag-reports") %></h3>
<liferay-ui:tabs
    names="Active,Moderated"
    url="<%=renderResponse.createRenderURL().toString() %>" />

<liferay-ui:search-container
    searchContainer='<%= new SearchContainer(renderRequest, null, null, "curFlagReport", 5, portletURL, null, "flag-report-no-reports") %>'
    id="flagReportSearchContainer"
    delta="10">
	<liferay-ui:search-container-results>
    <%

        String filterType = FlagReportFilterType.IN_PROGRESS_STATUS.toString();
        List<FlagReportMasterSetResult> flagReportMasterSetResults = new ArrayList<FlagReportMasterSetResult>();

        if (tabs1.equalsIgnoreCase("active")) {
            flagReportMasterSetResults = FlagReportQueryUtils.getMasterSet(FlagReportQueryUtils.getCompoundQueryResults(filterType, QueryUtil.ALL_POS, QueryUtil.ALL_POS));

        // Alternative: Could set this to FlagReportFilterType.ALL.toString();
        } else {
            filterType = FlagReportFilterType.FINISHED_STATUS.toString();
            flagReportMasterSetResults = FlagReportQueryUtils.getMasterSet(FlagReportQueryUtils.getCompoundQueryResults(filterType, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
        }

        // set the total number of results in the search container.
        searchContainer.setResults(flagReportMasterSetResults);
        searchContainer.setTotal(flagReportMasterSetResults.size());

        // set the pagination sublist of results on the page.
        pageContext.setAttribute("results", flagReportMasterSetResults);
        pageContext.setAttribute("total", total);
    %>
	</liferay-ui:search-container-results>


	<liferay-ui:search-container-row
		className="FlagReportMasterSetResult"
		modelVar="flagReportMasterSetResult"
        rowVar="thisrow"  >
        <%
            String statsSummary = flagReportMasterSetResult.getStatsSummary();
            String contentType = flagReportMasterSetResult.getContentType();
            String userDisplay = flagReportMasterSetResult.getUserDisplay();
            String mostRecentContent = flagReportMasterSetResult.getMostRecentContent();
            String allFlagReasons = flagReportMasterSetResult.getAllFlagReasons();
            String rollupStatus = flagReportMasterSetResult.getRollupStatus();
        %>
        <liferay-ui:search-container-column-text
             name="# Reports">
             <%= statsSummary %>
        </liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text
            name="Content Type">
            <%= contentType %>
        </liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
			name="Content Author">
			<%= userDisplay %>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
			name="Content">
			<%= mostRecentContent %>
		</liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text
            name="Reason">
            <%= allFlagReasons %>
        </liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text
            name="Status">
             <%= rollupStatus %>
        </liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-jsp
			path="/flag-report/jsp/editActions.jsp" align="right" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="<%= true%>" />

</liferay-ui:search-container>


