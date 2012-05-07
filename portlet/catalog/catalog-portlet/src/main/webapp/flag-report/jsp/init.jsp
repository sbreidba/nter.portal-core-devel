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

<%-- flag-report/jsp/init.jsp --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.JavaConstants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.service.ClassNameLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>

<%@ page import="org.nterlearning.course.enumerations.FlagReportFilterType" %>
<%@ page import="org.nterlearning.course.util.FlagReportModeratorActivityResult" %>
<%@ page import="org.nterlearning.course.util.FlagReportQueryUtils" %>
<%@ page import="org.nterlearning.course.util.FlagReportUtil" %>
<%@ page import="org.nterlearning.course.util.FlagReportMasterSetResult" %>
<%@ page import="org.nterlearning.course.util.NterKeys" %>
<%@ page import="org.nterlearning.course.util.WebKeys" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.CourseReview" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.FlagReport" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.FlagReportStats" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.GlobalCourseReview" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil" %>


<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.*" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="nter" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
PortletPreferences prefs = renderRequest.getPreferences();
%>