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

<%-- flag-report/jsp/moderateReport.jsp --%>

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
        calloutTitle = LanguageUtil.format(pageContext, "flag-moderate-title", callout_args);
        currentContent = courseReview.getContent();
    } else if (classNameId == ClassNameLocalServiceUtil.getClassNameId(GlobalCourseReview.class)) {
        GlobalCourseReview globalCourseReview = GlobalCourseReviewLocalServiceUtil.getGlobalCourseReview(reviewId);
        String[] callout_args = {globalCourseReview.getUserDisplayName()};
        calloutTitle = LanguageUtil.format(pageContext, "flag-moderate-title", callout_args);
        currentContent = globalCourseReview.getContent();
    }

    // Retrieve set of flag reports.  Need to determine other content entries, number of new reports,
    // moderator activity and display of all reports
    List<FlagReport> flagReports = FlagReportLocalServiceUtil.findByClassNameIdClassPK(classNameId, reviewId);
    long newCount = FlagReportUtil.calculateNewCount(flagReports);

    List<String> contentList = FlagReportUtil.previousContentList(flagReports,currentContent);

    PortletURL portletURL = renderResponse.createRenderURL();
%>

<div class="flag-report-detail">
    <h2 id="report-title"><%= calloutTitle %></h2>
    <h4 id="report-current-content"><%= LanguageUtil.get(pageContext, "flag-moderate-current-content")%></h4>
    <p><%=currentContent%></p>
<%
    if (contentList.size() > 0 ) {
%>
        <h4 id="report-previous-content"><%= LanguageUtil.get(pageContext, "flag-moderate-previous-content")%></h4>
<%
        for (String previousContent:contentList) {
%>
            <p><%=previousContent%></p>
<%      }
    } 
%>
<div class="separator"></div>
<%
    String [] table_callout_args = {String.format("%d", newCount )};
    String calloutTableTitle = LanguageUtil.format(pageContext, "flag-moderate-table-title", table_callout_args);
%>
    <h3 id="report-table"><%= calloutTableTitle %> </h3>
    <liferay-ui:search-container
        searchContainer='<%= new SearchContainer(renderRequest, null, null, "curFlagReport", 5, portletURL, null, "flag-report-no-reports") %>'
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
<h3 id="report-moderator-activity"><%= LanguageUtil.get(pageContext, "flag-moderate-moderator-activity")%></h3>
<nter:flag-moderator-activity
    reviewId="<%=reviewId%>"
    classNameId="<%=classNameId%>"
    pageContext="<%=pageContext%>" />

<%
    // Only allow moderate action if there are new flag reports
    if (newCount > 0) {
%>
<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, themeDisplay.getPortletDisplay().getId(), ActionKeys.UPDATE) %>'>
    <div class="separator"></div>
    <h3 id="report-action-title"><%= LanguageUtil.get(pageContext, "flag-moderate-action-title")%></h3>

    <portlet:actionURL name="moderateReportSet" var="moderateReportSet" >
        <portlet:param name="reviewPrimKey" value="<%= String.valueOf(reviewId) %>"></portlet:param>
        <portlet:param name="reviewClassNameId" value="<%= String.valueOf(classNameId) %>"></portlet:param>
    </portlet:actionURL>

    <aui:form action="<%= moderateReportSet %>"  method="post" >
            <aui:fieldset>
                <aui:select name="<%=NterKeys.MODERATE_ACTION%>"
                       label='<%= LanguageUtil.get(pageContext, "flag-moderate-action") %>'>
                    <aui:option value="IGNORE" selected='IGNORE'><liferay-ui:message key="flag-moderate-ignore" /></aui:option>
                    <aui:option value="REMOVE" selected='REMOVE'><liferay-ui:message key="flag-moderate-approve" /></aui:option>
                </aui:select>
                <aui:input name="<%=NterKeys.MODERATOR_COMMENT%>" type="textarea"
                       label='<%= LanguageUtil.get(pageContext, "flag-moderate-comment") %>'></aui:input>

                <aui:button-row>
                    <aui:button type="submit" class="submit primaryAction" value="submit">
                        <%= LanguageUtil.get(pageContext, "flag-moderate-form-submit") %>
                    </aui:button>
                    <%
                    String backURL = ParamUtil.getString(request, "redirect", "location.href='';");
                    %>
                    <aui:button type="cancel" class="button secondaryAction cancel" onClick="<%=backURL %>" >
                        <%= LanguageUtil.get(pageContext, "flag-moderate-form-cancel" ) %>
                    </aui:button>
                </aui:button-row>
            </aui:fieldset>
    </aui:form>
</c:if>

    <%
    } // if statement moderate action only if new flag reports
    %>
<aui:button-row>
    <%
        String backURL = ParamUtil.getString(request, "redirect", "location.href='';");
    %>
    <aui:button name="returnButton" type="button" value="flag-return-to-report-view" last="true" onClick="<%=backURL %>"/>
</aui:button-row>
