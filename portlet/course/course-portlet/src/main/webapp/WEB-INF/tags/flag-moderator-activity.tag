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

<%-- flag-moderator-activity.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ tag import="org.nterlearning.datamodel.catalog.model.FlagReport" %>
<%@ tag import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ tag import="com.liferay.portal.kernel.log.Log" %>
<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ tag import="java.util.List" %>
<%@ tag import="org.nterlearning.course.util.FlagReportModeratorActivityResult" %>
<%@ tag import="org.nterlearning.course.util.FlagReportUtil" %>
<%@ tag import="java.text.DateFormat" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil" %>

<%@ attribute name="reviewId" type="java.lang.Long" required="true" %>
<%@ attribute name="classNameId" type="java.lang.Long" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
    _log.debug(" Entered flag-moderator-activity.tag");

    List<FlagReport> flagReports = FlagReportLocalServiceUtil.findByClassNameIdClassPK(classNameId, reviewId);
    List<FlagReportModeratorActivityResult> moderatorActivityResults = FlagReportUtil.moderatorActivity(flagReports);
%>

<table>

<%
        for (FlagReportModeratorActivityResult moderatorActivityResult:moderatorActivityResults) {
            //Only display statement if moderateReportCnt > 0
            if (moderatorActivityResult.getModerateReportCnt() > 0) {
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
                String moderateDateString = "";
                if (moderatorActivityResult.getModerateDate() != null) {
                    moderateDateString = df.format(moderatorActivityResult.getModerateDate());
                }
                String calloutModeratorString ="";
                if (moderatorActivityResult.getModeratorComment().isEmpty() && moderatorActivityResult.getModerateAction().isEmpty()) {
                    String [] moderator_callout_args = {String.format("%d", moderatorActivityResult.getModerateReportCnt())};
                    calloutModeratorString = LanguageUtil.format(pageContext, "flag-moderate-activity-entry-no-reason-comment", moderator_callout_args);
                } else if (moderatorActivityResult.getModeratorComment().isEmpty()) {
                    String [] moderator_callout_args = {moderatorActivityResult.getModerateAction(),
                        String.format("%d", moderatorActivityResult.getModerateReportCnt()),
                        moderatorActivityResult.getModeratorUserName()};
                        calloutModeratorString = LanguageUtil.format(pageContext, "flag-moderate-activity-entry-no-comment", moderator_callout_args);
                } else {
                    String [] moderator_callout_args = {moderatorActivityResult.getModerateAction(),
                        String.format("%d", moderatorActivityResult.getModerateReportCnt()),
                        moderatorActivityResult.getModeratorUserName(),
                        moderatorActivityResult.getModeratorComment()};
                        calloutModeratorString = LanguageUtil.format(pageContext, "flag-moderate-activity-entry-comment", moderator_callout_args);
                }

            %>
     <tr>
         <td><%= moderateDateString %></td>
         <td><%= calloutModeratorString %></td>
     </tr>
    <%
            } // moderateReportCnt zero check
        } //for loop
    %>

  </table>

<%! private static final Log _log = LogFactoryUtil.getLog("courseportlet.docroot.tags.flag-entry.tag"); %>

