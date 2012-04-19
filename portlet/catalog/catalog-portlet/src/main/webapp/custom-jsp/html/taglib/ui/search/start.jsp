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

<%@ include file="/html/taglib/ui/search/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");

Group group = themeDisplay.getScopeGroup();

String keywords = ParamUtil.getString(request, "keywords");

String searchPage = PrefsPropsUtil.getString(PropsKeys.COMPANY_DEFAULT_HOME_URL) + "/search";
%>

<form action='<%= searchPage%>' method="get" name="<%= randomNamespace %><%= namespace %>fm">
<label for="<%= randomNamespace %><%= namespace %>search"><liferay-ui:message key="search" /></label>
<input id="<%= randomNamespace %><%= namespace %>search" class="textbox" name="keywords" size="30" type="text" value="<%= HtmlUtil.escapeAttribute(keywords) %>" />

<select name="groupId">
	<option value="0" <%= (groupId == 0) ? "selected" : "" %>><liferay-ui:message key="everything" /></option>
	<option value="<%= group.getGroupId() %>" <%= (groupId != 0) ? "selected" : "" %>><liferay-ui:message key='<%= "this-" + (group.isOrganization() ? "organization" : "community") %>' /></option>
</select>

<input value="<liferay-ui:message key="search" />" class="submit" type="submit" />