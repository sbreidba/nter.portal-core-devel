
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
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.GroupConstants" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ taglib prefix="portlet" uri="http://liferay.com/tld/portlet" %>


<portlet:defineObjects />
<liferay-theme:defineObjects />

<%-- /migration/jsp/view.jsp --%>

<portlet:actionURL name="processMigrateUserImport" var="processMigrateUserImport" />
<portlet:actionURL name="processMigrateReviewFeedImport" var="processMigrateReviewFeedImport" />
<%
    PortletURL portletURL = renderResponse.createRenderURL();

%>
<h3 class="course-feed-title"><%= LanguageUtil.get(pageContext, "migrate-user-import-heading") %></h3>
<p><%= LanguageUtil.get(pageContext, "migrate-user-import-label") %></p>
<aui:form action="<%= processMigrateUserImport.toString() %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="migrate-user-import" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<div class="separator"></div>
<h3 class="course-feed-title"><%= LanguageUtil.get(pageContext, "migrate-review-feed-import-heading") %></h3>
<p><%= LanguageUtil.get(pageContext, "migrate-review-feed-imort-label") %></p>
<aui:form action="<%= processMigrateReviewFeedImport.toString() %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="migrate-review-feed-import" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>