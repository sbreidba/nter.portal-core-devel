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

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ include file="/html/taglib/init.jsp" %>

<%
String key = (String)request.getAttribute("liferay-ui:error:key");
String message = (String)request.getAttribute("liferay-ui:error:message");
boolean translateMessage = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:error:translateMessage"));
String rowBreak = (String)request.getAttribute("liferay-ui:error:rowBreak");
String portletId = themeDisplay.getPortletDisplay().getId();
%>

<c:choose>
	<c:when test="<%= (key != null) && Validator.isNull(message) %>">
		<c:if test="<%= SessionErrors.contains(portletRequest, key) %>">
			</div>

			<%= rowBreak %>
		</c:if>
	</c:when>
	<c:when test="<%= key == null %>">
		<c:if test='<%= !SessionErrors.isEmpty(portletRequest) && ParamUtil.getBoolean(request,
		"hide-default-error-msg") %>'>
			<div class="portlet-msg-error">
				<liferay-ui:message key="you-have-entered-invalid-data" />
			</div>

			<%= rowBreak %>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="<%= SessionErrors.contains(portletRequest, key) %>">
			<div class="portlet-msg-error">

			<c:choose>
				<c:when test="<%= translateMessage %>">
					<%= LanguageUtil.get(pageContext, message) %>
				</c:when>
				<c:otherwise>
					<%= message %>
				</c:otherwise>
			</c:choose>

			</div>

			<%= rowBreak %>
		</c:if>
	</c:otherwise>
</c:choose>