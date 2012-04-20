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

<%-- flag-report/jsp/editActions.jsp --%>

<%@include file="/flag-report/jsp/init.jsp" %>

<%
    ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

    FlagReportMasterSetResult flagReportMasterSetResult = (FlagReportMasterSetResult)row.getObject();
    String reviewPrimKey = String.valueOf(flagReportMasterSetResult.getFlagReport().getClassPK());
    String reviewClassNameId = String.valueOf(flagReportMasterSetResult.getFlagReport().getClassNameId());
%>

<portlet:renderURL var="displayDetailsURL">
    <portlet:param name="jspPage" value="/flag-report/html/reportDetails.jsp"></portlet:param>
    <portlet:param name="reviewPrimKey" value="<%= reviewPrimKey %>"></portlet:param>
    <portlet:param name="reviewClassNameId" value="<%= reviewClassNameId %>"></portlet:param>
    <portlet:param name="redirect" value="<%=renderResponse.createRenderURL().toString() %>" />
</portlet:renderURL>

<portlet:renderURL var="moderateReportURL">
    <portlet:param name="jspPage" value="/flag-report/html/moderateReport.jsp"></portlet:param>
    <portlet:param name="reviewPrimKey" value="<%= reviewPrimKey %>"></portlet:param>
    <portlet:param name="reviewClassNameId" value="<%= reviewClassNameId %>"></portlet:param>
    <portlet:param name="redirect" value="<%=renderResponse.createRenderURL().toString() %>" />
</portlet:renderURL>

<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, themeDisplay.getPortletDisplay().getId(), ActionKeys.UPDATE) %>'>
    <c:choose>
        <c:when test='<%= flagReportMasterSetResult.getUnmoderatedReportCnt() > 0 %>'>
            <aui:button name="moderateButton" type="button" value="flag-report-moderate" onClick="<%= moderateReportURL %>" />
        </c:when>
        <c:otherwise>
            <aui:button name="detailButton" type="button" value="flag-report-details" onClick="<%= displayDetailsURL %>" />
        </c:otherwise>
    </c:choose>
</c:if>
