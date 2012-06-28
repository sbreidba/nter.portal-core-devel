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
<%@ page import="com.liferay.portlet.PortletURLFactoryUtil" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.PortletRequest" %>

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
    User selUser = (User)request.getAttribute("user.selUser");

    PortletURL viewUrl = PortletURLFactoryUtil.create(request,
                            "transcriptreports_WAR_ntercatalogportlet",
                            themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
    viewUrl.setParameter("userId", String.valueOf(selUser.getUserId()));
%>

<div>
    <liferay-portlet:resourceURL
            portletName="transcriptreports_WAR_ntercatalogportlet"
            var="printTranscriptURL"
            anchor="false">
        <liferay-portlet:param name="userId" value="<%= String.valueOf(selUser.getUserId()) %>"/>
    </liferay-portlet:resourceURL>

    <div class="">
        <a href="<%= viewUrl.toString() %>">
            <%= LanguageUtil.get(pageContext, "nter-student-records-view-transcript")%>
        </a>
    </div>

    <input type="button"
           value="<%=LanguageUtil.get(pageContext, "nter-student-records-export-transcript") %>"
           onclick="window.open('<%= printTranscriptURL.toString() %>')"/>
</div>