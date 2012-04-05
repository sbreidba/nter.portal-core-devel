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

<%-- component-list.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ tag import="org.nterlearning.course.enumerations.*" %>

<%@ attribute name="courseIsPurchased" type="java.lang.Boolean" required="true" %>
<%@ attribute name="courseIsRemoved" type="java.lang.Boolean" required="true" %>
<%@ attribute name="component" type="org.nterlearning.datamodel.catalog.model.Component" required="true" %>
<%@ attribute name="courses_components" type="org.nterlearning.datamodel.catalog.model.Courses_Components" required="true" %>
<%@ attribute name="componentRecord" type="org.nterlearning.datamodel.catalog.model.ComponentRecord" required="true" %>
<%@ attribute name="linkId" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>
<%@ attribute name="componentIsResource" type="java.lang.Boolean" required="true" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<% // Course removed or student not logged in then do not display a componentlink 
   // When student signed in check to see if course requires payment and check payment status
if (!themeDisplay.isSignedIn()) {
%>
    <li role="treeitem" id="course-contents-<%= linkId %>"><%= component.getTitle()%></li>
<%
} else if (courseIsRemoved) {
%>
    <li role="treeitem" id="course-contents-<%= linkId %>"><%= component.getTitle()%></li>
<%
} else if (componentRecord == null) {
    if (courses_components.getComponentPaymentRequired() && !courseIsPurchased) { 
%>
        <li role="treeitem" id="course-contents-<%= linkId %>"><%= component.getTitle()%></li>
<%
    } else if (componentIsResource) {
%>
        <li role="treeitem" id="course-contents-<%= linkId %>">
            <a href='<%= component.getHref() %>' target="_blank" data-window-width="<%= component.getDisplayWidth() %>" data-window-height="<%= component.getDisplayHeight() %>"><%= component.getTitle() %></a>
        </li>
<%
   } else {
       String componentStatus = LanguageUtil.get(pageContext, "course-status-not-started");
%>
        <li role="treeitem" id="course-contents-<%= linkId %>">
            <a href='<%= component.getHref() %>' target="_blank" data-window-width="<%= component.getDisplayWidth() %>" data-window-height="<%= component.getDisplayHeight() %>"><%= component.getTitle() %></a>
            <span class="status"><%= componentStatus %></span>
        </li>
<%
    }
} else  {
    String componentStatus = null;
    String completionStatus = componentRecord.getCompletionStatus();
    if (completionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue())) {
        componentStatus = LanguageUtil.get(pageContext, "course-status-started");
    } else if (completionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) {
        componentStatus = LanguageUtil.get(pageContext, "course-status-complete");
    } else if (completionStatus.equals(CompletionStatusType.FAILED.getDbValue())) {
        componentStatus = LanguageUtil.get(pageContext, "course-status-failed");
    } else if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
        componentStatus = LanguageUtil.get(pageContext, "course-status-failed-retry");
    }

    if (componentStatus == null) {
        componentStatus = LanguageUtil.get(pageContext, "course-status-not-started");
    }

    if (courses_components.getComponentPaymentRequired() && !courseIsPurchased) { 
%>
		<li role="treeitem" id="course-contents-<%= linkId %>"><%= component.getTitle() %>
            <span class="status"><%= componentStatus %></span>
		    <span class="visited"><%= componentRecord.getFriendlyUpdatedDate(pageContext) %></span>
<%          if (component.getVersionDate().compareTo(componentRecord.getUpdatedDate()) > 0) { %>
                <span class="updated"><%= LanguageUtil.format(pageContext, "course-updated-date", component.getFriendlyVersionDate(pageContext)) %></span>
<%          } %>
		</li>
<%
    } else {
%>
		<li role="treeitem" id="course-contents-<%= linkId %>">
            <a href='<%= component.getHref() %>' target="_blank" data-window-width="<%= component.getDisplayWidth() %>" data-window-height="<%= component.getDisplayHeight() %>"><%= component.getTitle() %></a>
            <span class="status"><%= componentStatus %></span>
		    <span class="visited"><%= componentRecord.getFriendlyUpdatedDate(pageContext) %></span>
<%          if (component.getVersionDate().compareTo(componentRecord.getUpdatedDate()) > 0) { %>
                <span class="updated"><%= LanguageUtil.format(pageContext, "course-updated-date", component.getFriendlyVersionDate(pageContext)) %></span>
<%          } %>
        </li>
<%
    }
} //login check %>

			


