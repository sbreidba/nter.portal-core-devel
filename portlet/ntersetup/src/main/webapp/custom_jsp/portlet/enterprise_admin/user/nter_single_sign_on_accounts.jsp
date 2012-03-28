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

<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ page import="com.liferay.portal.service.UserIdMapperLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
    User selUser = (User)request.getAttribute("user.selUser");
%>

<h3><liferay-ui:message key="nter-single-sign-on-accounts"/></h3>
<liferay-ui:search-container delta="10" emptyResultsMessage="nter-sso-no-accounts-found">
    <liferay-ui:search-container-results
            results="<%= UserIdMapperLocalServiceUtil.getUserIdMappers(selUser.getUserId())%>"
            total="<%=UserIdMapperLocalServiceUtil.getUserIdMappers(selUser.getUserId()).size() %>">
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="com.liferay.portal.model.UserIdMapper"
            keyProperty="userIdMapperId"
            modelVar="userMap">

        <%
            String ssoType = userMap.getType();
            ssoType = (ssoType.isEmpty()) ? LanguageUtil.get(pageContext, "nter-sso-type-local") : ssoType;
        %>
        
        <liferay-ui:search-container-column-text
                name="nter-sso-external-id"
                value="<%= userMap.getExternalUserId() %>"/>
        <liferay-ui:search-container-column-text
                name="nter-sso-type"
                value="<%= ssoType %>"/>
        <liferay-ui:search-container-column-text
                name="nter-sso-description"
                value="<%= userMap.getDescription() %>"/>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>