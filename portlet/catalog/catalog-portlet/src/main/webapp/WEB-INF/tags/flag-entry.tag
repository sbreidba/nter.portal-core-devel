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

<%-- flag-entry.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ tag import="org.nterlearning.datamodel.catalog.model.FlagReport" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.impl.FlagReportImpl" %>
<%@ tag import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ tag import="com.liferay.portal.kernel.log.Log" %>
<%@ tag import="org.nterlearning.course.util.NterKeys" %>
<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>

<%@ attribute name="userId" type="java.lang.Long" required="true" %>
<%@ attribute name="reporterEmailAddress" type="java.lang.String" required="true" %>
<%@ attribute name="reportedUserId" type="java.lang.Long" required="true" %>
<%@ attribute name="classNameId" type="java.lang.Long" required="true" %>
<%@ attribute name="className" type="java.lang.String" required="true" %>
<%@ attribute name="classPK" type="java.lang.Long" required="true" %>
<%@ attribute name="contentTitle" type="java.lang.String" required="true" %>
<%@ attribute name="content" type="java.lang.String" required="true" %>
<%@ attribute name="contentURL"  type="java.lang.String" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>
<%@ attribute name="jspPage" type="java.lang.String" %>
<%@ attribute name="redirect" type="java.lang.String" %>
<%@ attribute name="showBackButton" type="java.lang.Boolean" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
    _log.debug(" Entered flag-entry.tag");

if (themeDisplay.isSignedIn()) {
        FlagReport flagReport = new FlagReportImpl();
%>
<portlet:actionURL var="createFlagEntry" name='createFlagEntry'>
	<portlet:param name="redirect" value="<%=(redirect != null) ? redirect : PortalUtil.getCurrentURL(request)%>" />
</portlet:actionURL>

<!-- aui:form makes it impossible to override the default submission action,
     use the plain html form tag instead (which is identical except for the xml namespace -->
<form id="report-inappropriate" action="<%= createFlagEntry %>" method="post" class="uniForm">
	<aui:input name="<%=NterKeys.USER_ID%>" type="hidden" value="<%= userId %>" ></aui:input>
	<aui:input name="<%=NterKeys.REPORTER_EMAIL_ADDRESS%>" type="hidden" value="<%= reporterEmailAddress %>"></aui:input>
	<aui:input name="<%=NterKeys.REPORTED_USER_ID%>" type="hidden" value="<%= reportedUserId %>"></aui:input>
	<aui:input name="<%=NterKeys.CLASS_NAME_ID%>" type="hidden" value="<%= classNameId %>"></aui:input>
	<aui:input name="<%=NterKeys.CLASS_NAME%>" type="hidden" value="<%= className %>"></aui:input>
	<aui:input name="<%=NterKeys.CLASS_PK%>" type="hidden" value="<%= classPK %>"></aui:input>
	<aui:input name="<%=NterKeys.CONTENT_TITLE%>" type="hidden" value="<%= contentTitle %>"></aui:input>
	<aui:input name="<%=NterKeys.CONTENT%>" type="hidden" value="<%= content %>"></aui:input>
	<aui:input name="<%=NterKeys.CONTENT_URL%>" type="hidden" value="<%= contentURL %>"></aui:input>

	<span class="aui-field-label"><%= LanguageUtil.get(pageContext, "flag-entry-content") %></span>
	<div class="flagged-content uneditable"><%= content %></div>

	<aui:select name="<%=NterKeys.FLAG_REASON%>" label='<%= LanguageUtil.get(pageContext, "flag-entry-flag-reason") %>'>
		<aui:option value="spam" selected='<%= flagReport.getFlagReason().equals("spam") %>'><liferay-ui:message key="flag-entry-reason-spam" /></aui:option>
		<aui:option value="hatespeech" selected='<%= flagReport.getFlagReason().equals("hatespeech") %>'><liferay-ui:message key="flag-entry-reason-hatespeech" /></aui:option>
		<aui:option value="language" selected='<%= flagReport.getFlagReason().equals("language") %>'><liferay-ui:message key="flag-entry-reason-language" /></aui:option>
		<aui:option value="sexualcontent" selected='<%= flagReport.getFlagReason().equals("sexualcontent") %>'><liferay-ui:message key="flag-entry-reason-sexualcontent" /></aui:option>
		<aui:option value="offtopic" selected='<%= flagReport.getFlagReason().equals("offtopic") %>'><liferay-ui:message key="flag-entry-reason-offtopic" /></aui:option>
		<aui:option value="other" selected='<%= flagReport.getFlagReason().equals("other") %>'><liferay-ui:message key="flag-entry-reason-other" /></aui:option>
	</aui:select>

	<aui:input name="<%=NterKeys.FLAG_COMMENT%>" type="textarea" value="<%= flagReport.getFlagComment() %>" label='<%= LanguageUtil.get(pageContext, "flag-entry-flag-comment") %>' ></aui:input>

	<aui:button-row>
		<button type="submit" class="submit primaryAction" value="submit">
			<%= LanguageUtil.get(pageContext, "flag-form-submit") %>
		</button>
		<a href="<%= contentURL %>" class="button secondaryAction cancel">
			<%= LanguageUtil.get(pageContext, "flag-form-cancel") %>
		</a>
	</aui:button-row>
</form>
<% } %>
<%! private static final Log _log = LogFactoryUtil.getLog("ntercatalogportlet.docroot.tags.flag-entry.tag"); %>

