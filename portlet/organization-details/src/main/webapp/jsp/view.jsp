<%
/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.	
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */
%>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ page import="com.liferay.portal.service.OrganizationLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.Organization" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
Group grp = themeDisplay.getScopeGroup();
Organization org = OrganizationLocalServiceUtil.getOrganization(grp.getOrganizationId());

String[] tags = AssetTagLocalServiceUtil.getTagNames(grp.getClassNameId(), grp.getClassPK());
StringBuilder tagString = new StringBuilder();
for (String tag : tags) tagString.append(tag + ",");

PortalUtil.addPageTitle(LanguageUtil.format(pageContext, "about-partner", org.getName()), PortalUtil.getHttpServletRequest(renderRequest));
PortalUtil.addPageKeywords(tagString.toString(), PortalUtil.getHttpServletRequest(renderRequest));
PortalUtil.addPageDescription(org.getComments(), PortalUtil.getHttpServletRequest(renderRequest));
%>

<article class="course-details">
	<h2 class="main-page-heading"><%= LanguageUtil.format(pageContext, "about-partner", org.getName()) %></h2>
	<div class="description"><%= org.getComments() %></div>
</article>