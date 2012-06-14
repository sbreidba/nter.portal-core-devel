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

<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
    User selUser = (User)request.getAttribute("user.selUser");
%>

<div>
    <liferay-portlet:resourceURL
            portletName="transcriptreports_WAR_ntercatalogportlet"
            var="printTranscriptURL"
            anchor="false">
        <liferay-portlet:param name="userId" value="<%= String.valueOf(selUser.getUserId()) %>"/>
    </liferay-portlet:resourceURL>

    <input type="button"
           value="<%=LanguageUtil.get(pageContext, "nter-student-records-export-transcript") %>"
           onclick="window.open('<%= printTranscriptURL.toString() %>',
                                '<%=LanguageUtil.get(pageContext, "nter-student-records") %>',
                                'toolbar=no,location=no,menubar=no,scrollbar=yes')"/>
</div>