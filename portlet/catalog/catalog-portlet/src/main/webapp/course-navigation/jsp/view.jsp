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
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.permission.LayoutPermissionUtil" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<%
List ancestors = new ArrayList();
ancestors.add(layout);
ancestors.addAll(layout.getAncestors());

Layout rootLayout = (Layout) ancestors.get(ancestors.size() - 1);
List <Layout> siblings = rootLayout.getChildren();
%>

<h3 class="main-page-heading"><%= layout.getHTMLTitle(themeDisplay.getLocale()) %></h3>
<ul class="course-filter">
  <% if (layout.getLayoutId() == rootLayout.getLayoutId()) { %>
    <li class="current"><%= rootLayout.getHTMLTitle(themeDisplay.getLocale()) %></li>
  <% } else { %>
    <li><a href="<%= PortalUtil.getLayoutURL(rootLayout, themeDisplay) %>"><%= rootLayout.getHTMLTitle(themeDisplay.getLocale()) %></a></li>
  <% } %>
  <% for (Layout sibling : siblings) { 
    if (!sibling.isHidden() && LayoutPermissionUtil.contains(themeDisplay.getPermissionChecker(), sibling, ActionKeys.VIEW)) {
      if (layout.getLayoutId() == sibling.getLayoutId()) { %>
        <li class="current"><%= sibling.getName(themeDisplay.getLocale()) %></li>
      <% } else { %>
        <li><a href="<%= PortalUtil.getLayoutURL(sibling, themeDisplay) %>"><%= sibling.getName(themeDisplay.getLocale()) %></a></li>
      <% }
    }
  } %>
</ul>