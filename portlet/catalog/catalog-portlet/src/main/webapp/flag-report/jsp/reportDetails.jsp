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

<%-- flag-report/jsp/reportDetails.jsp --%>

<%@include file="/flag-report/jsp/init.jsp" %>
<%
    long reviewId = ParamUtil.getLong(request,"reviewPrimKey");
    long classNameId = ParamUtil.getLong(request,"reviewClassNameId");

    // Establish content from the object that was flagged
    String calloutTitle = "";
    String currentContent = "";
    if (classNameId == ClassNameLocalServiceUtil.getClassNameId(CourseReview.class)) {
        CourseReview courseReview = CourseReviewLocalServiceUtil.getCourseReview(reviewId);
        String[] callout_args = {UserLocalServiceUtil.getUserById(courseReview.getUserId()).getFullName()};
        calloutTitle = LanguageUtil.format(pageContext, "flag-detail-title", callout_args);
        currentContent = courseReview.getContent();
    } else if (classNameId == ClassNameLocalServiceUtil.getClassNameId(GlobalCourseReview.class)) {
        GlobalCourseReview globalCourseReview = GlobalCourseReviewLocalServiceUtil.getGlobalCourseReview(reviewId);
        String[] callout_args = {globalCourseReview.getUserDisplayName()};
        calloutTitle = LanguageUtil.format(pageContext, "flag-detail-title", callout_args);
        currentContent = globalCourseReview.getContent();
    }

    // Retrieve set of flag reports.
    List<FlagReport> flagReports = FlagReportLocalServiceUtil.findByClassNameIdClassPK(classNameId, reviewId);
    List<FlagReportModeratorActivityResult> moderatorActivityResults = FlagReportUtil.moderatorActivity(flagReports);
    FlagReportStats flagReportStats = FlagReportStatsLocalServiceUtil.findByClassNameIdWithClassPK(classNameId, reviewId);
    PortletURL portletURL = renderResponse.createRenderURL();
%>
<div class="flag-report-detail">
    <h2 id="report-title"><%= calloutTitle %></h2>
    <h4 id="report-detail-content"><%= LanguageUtil.get(pageContext, "flag-detail-content")%></h4>
    <p><%=currentContent%></p>

<div class="separator"></div>

   <h3 id="report-table"><%= LanguageUtil.get(pageContext, "flag-detail-table-title") %> </h3>
    <%--<nter:flag-report-list--%>
        <%--reviewId="<%=reviewId%>"--%>
        <%--classNameId="<%=classNameId%>"--%>
        <%--pageContext="<%=pageContext%>" />--%>
    <liferay-ui:search-container
        searchContainer='<%= new SearchContainer(renderRequest, null, null, "curFlagReport", 5,  portletURL, null, "flag-report-no-reports") %>'
        id="flagReportSearchContainer"
        delta="<%= flagReports.size() %>">
        <liferay-ui:search-container-results>
        <%
            // set the total number of results in the search container.
            searchContainer.setResults(flagReports);
            searchContainer.setTotal(flagReports.size());

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
        <liferay-ui:search-iterator paginate="<%= false %>" />
    </liferay-ui:search-container>
</div>

<div class="separator"></div>
<div>
    <h3 id="report-moderator-activity"><%= LanguageUtil.get(pageContext, "flag-moderate-moderator-activity")%></h3>
    <%--<nter:flag-moderator-activity--%>
    <%--reviewId="<%=reviewId%>"--%>
    <%--classNameId="<%=classNameId%>"--%>
    <%--pageContext="<%=pageContext%>" />--%>

    <liferay-ui:search-container
        searchContainer='<%= new SearchContainer(renderRequest, null, null, "curModerateReport", 5,  portletURL, null, "flag-report-no-moderates") %>'
        id="moderatorSearchContainer"
        delta="<%= moderatorActivityResults.size() %>">
        <liferay-ui:search-container-results>
        <%
            // set the total number of results in the search container.
            searchContainer.setResults(moderatorActivityResults);
            searchContainer.setTotal(moderatorActivityResults.size());

            // set the pagination sublist of results on the page.
            pageContext.setAttribute("results", moderatorActivityResults);
            pageContext.setAttribute("total", moderatorActivityResults.size());
        %>
        </liferay-ui:search-container-results>
        <liferay-ui:search-container-row
            className="FlagReportModeratorActivityResult"
            modelVar="moderatorActivityResult"
            rowVar="thisrow"  >
            <%

                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
                String moderateDateString = "";
                if (moderatorActivityResult.getModerateDate() != null) {
                    moderateDateString = df.format(moderatorActivityResult.getModerateDate());
                }
                String calloutModeratorString ="";
                String moderatorComment="";
                if (moderatorActivityResult.getModeratorComment().isEmpty() && moderatorActivityResult.getModerateAction().isEmpty()) {
                    String [] moderator_callout_args = {String.format("%d", moderatorActivityResult.getModerateReportCnt())};
                    calloutModeratorString = LanguageUtil.format(pageContext, "flag-moderate-activity-entry-no-reason-comment", moderator_callout_args);
                } else if (moderatorActivityResult.getModeratorComment().isEmpty()) {
                    String [] moderator_callout_args = {moderatorActivityResult.getModerateAction(),
                        String.format("%d", moderatorActivityResult.getModerateReportCnt()),
                        moderatorActivityResult.getModeratorUserName()};
                        calloutModeratorString = LanguageUtil.format(pageContext, "flag-moderate-activity-entry-reason", moderator_callout_args);
                } else {
                    String [] moderator_callout_args = {moderatorActivityResult.getModerateAction(),
                        String.format("%d", moderatorActivityResult.getModerateReportCnt()),
                        moderatorActivityResult.getModeratorUserName()};
                        calloutModeratorString = LanguageUtil.format(pageContext, "flag-moderate-activity-entry-reason", moderator_callout_args);
                        moderatorComment = moderatorActivityResult.getModeratorComment();
                }

            %>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"moderate-table-column-date\") %>">
                <%= moderateDateString %>
            </liferay-ui:search-container-column-text>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"moderate-table-column-moderate-action\") %>">
                 <%= calloutModeratorString %>
            </liferay-ui:search-container-column-text>
            <liferay-ui:search-container-column-text
                 name="<%= LanguageUtil.get(pageContext, \"moderate-table-column-moderate-comment\") %>">
                 <%= moderatorComment %>
            </liferay-ui:search-container-column-text>
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator paginate="<%= false %>" />
    </liferay-ui:search-container>

<div class="separator"></div>
<div class="flag-report-group-stats">
<%  if (flagReportStats == null) {  %>
        <h3><%= LanguageUtil.get(pageContext, "flag-detail-no-stats") %></h3>
<%  } else {  %>
    <h3><%= LanguageUtil.get(pageContext, "flag-detail-stats") %></h3>
        <div class="flag-report-stats-attribute"><dt><%= LanguageUtil.get(pageContext, "flag-detail-total-entries") %></dt><dd><%= flagReportStats.getTotalEntries() %></dd></div>
        <div class="flag-report-stats-attribute"><dt><%= LanguageUtil.get(pageContext, "flag-detail-total-moderated") %></dt><dd><%= flagReportStats.getTotalModerated() %></dd></div>
        <div class="flag-report-stats-attribute"><dt><%= LanguageUtil.get(pageContext, "flag-detail-total-approved") %></dt><dd><%= flagReportStats.getTotalApproved() %></dd></div>
<%  } %>
</div>


<aui:button-row>
    <%
        String backURL = ParamUtil.getString(request, "redirect", "location.href='';");
    %>
    <aui:button name="returnButton" type="button" value="flag-return-to-report-view" last="true" onClick="<%=backURL %>"/>
</aui:button-row>
