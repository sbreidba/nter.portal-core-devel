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
    FlagReportStats flagReportStats = FlagReportStatsLocalServiceUtil.findByClassNameIdWithClassPK(classNameId, reviewId);

%>
<div class="flag-report-detail">
    <h2 id="report-title"><%= calloutTitle %></h2>
    <h4 id="report-detail-content"><%= LanguageUtil.get(pageContext, "flag-detail-content")%></h4>
    <p><%=currentContent%></p>

<div class="separator"></div>

   <h3 id="report-table"><%= LanguageUtil.get(pageContext, "flag-detail-table-title") %> </h3>
   <table>
        <tr>
            <th><%= LanguageUtil.get(pageContext, "flag-table-column-date") %> </th>
            <th><%= LanguageUtil.get(pageContext, "flag-table-column-status") %></th>
            <th><%= LanguageUtil.get(pageContext, "flag-table-column-reporter") %></th>
            <th><%= LanguageUtil.get(pageContext, "flag-table-column-reason") %></th>
            <th><%= LanguageUtil.get(pageContext, "flag-table-column-comment") %></th>
        </tr>
<%
        for (FlagReport flagReport:flagReports) {
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
        <tr>
            <td><%= createDateString %></td>
            <td><%= moderateStatus %></td>
            <td><%= UserLocalServiceUtil.getUserById(flagReport.getUserId()).getFullName() %></td>
            <td><%= flagReport.getFlagReason() %></td>
            <td><%= flagReport.getFlagComment() %></td>
        </tr>
    <%
        } //for loop
    %>

    </table>
</div>

<div class="separator"></div>
<h3 id="report-moderator-activity"><%= LanguageUtil.get(pageContext, "flag-moderate-moderator-activity")%></h3>
<nter:flag-moderator-activity
    reviewId="<%=reviewId%>"
    classNameId="<%=classNameId%>"
    pageContext="<%=pageContext%>" />


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
