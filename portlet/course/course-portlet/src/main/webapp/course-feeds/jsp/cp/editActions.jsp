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

<%@include file="init.jsp" %>

<%
    ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    String current = ParamUtil.getString(request, "cur", "1");

    FeedReference feedRef = (FeedReference)row.getObject();
    String feedRefId = String.valueOf(feedRef.getFeedReferenceId());

    String tabs = ParamUtil.getString(request, "tabs", "Active");
%>

<liferay-ui:icon-menu showWhenSingleIcon="true">

    <portlet:renderURL var="displayDetailsUrl">
        <portlet:param name="jspPage" value="/course-feeds/jsp/cp/feedDetails.jsp" />
        <portlet:param name="feedRedirect" value='<%=renderResponse.createRenderURL().toString() %>' />
        <portlet:param name="feedRefId" value="<%= feedRefId %>" />
        <portlet:param name="feedTabs" value='<%= tabs %>'/>
        <portlet:param name="feedCur" value='<%= current %>'/>
    </portlet:renderURL>

    <%-- will be superseded by a blacklist option
    <portlet:actionURL name="disableCourseFeed" var="disableCourseFeedUrl">
        <portlet:param name="feedRefId" value="<%= feedRefId %>" />
        <portlet:param name="feedCur" value='<%= ParamUtil.getString(request, "feedCur", "1") %>'/>
        <portlet:param name="feedDelta" value='<%= ParamUtil.getString(request, "feedDelta", "5") %>'/>
        <portlet:param name="feedRedirect" value='<%= ParamUtil.getString(request, "feedRedirect") %>'/>
        <portlet:param name="feedTabs" value='<%= ParamUtil.getString(request, "feedTabs", "Active") %>'/>
    </portlet:actionURL>
    --%>

    <%-- will be superseded by a blacklist option
    <portlet:actionURL name="enableCourseFeed" var="enableCourseFeedUrl">
        <portlet:param name="feedRefId" value="<%= feedRefId %>" />
        <portlet:param name="feedCur" value='<%= ParamUtil.getString(request, "feedCur", "1") %>'/>
        <portlet:param name="feedDelta" value='<%= ParamUtil.getString(request, "feedDelta", "5") %>'/>
        <portlet:param name="feedRedirect" value='<%= ParamUtil.getString(request, "feedRedirect") %>'/>
        <portlet:param name="feedTabs" value='<%= ParamUtil.getString(request, "feedTabs", "Active") %>'/>
    </portlet:actionURL>
    --%>

    <portlet:actionURL name="pushUnsubscribe" var="pushUnsubscribeURL">
        <portlet:param name="feedCur" value='<%= ParamUtil.getString(request, "feedCur", "1") %>'/>
        <portlet:param name="feedDelta" value='<%= ParamUtil.getString(request, "feedDelta", "5") %>'/>
        <portlet:param name="feedRedirect" value='<%= ParamUtil.getString(request, "feedRedirect") %>'/>
        <portlet:param name="feedRefId" value='<%= feedRefId %>'/>
        <portlet:param name="feedTabs" value='<%= ParamUtil.getString(request, "feedTabs", "Active") %>'/>
    </portlet:actionURL>

    <portlet:actionURL name="pushResubscribe" var="pushResubscribeURL">
        <portlet:param name="feedCur" value='<%= ParamUtil.getString(request, "feedCur", "1") %>'/>
        <portlet:param name="feedDelta" value='<%= ParamUtil.getString(request, "feedDelta", "5") %>'/>
        <portlet:param name="feedRedirect" value='<%= ParamUtil.getString(request, "feedRedirect") %>'/>
        <portlet:param name="feedRefId" value='<%= feedRefId %>'/>
        <portlet:param name="feedTabs" value='<%= ParamUtil.getString(request, "feedTabs", "Active") %>'/>
    </portlet:actionURL>


    <liferay-ui:icon image="attributes" message="course-feed-details" url='<%= displayDetailsUrl %>' />

    <%-- TODO : get this working again
    <c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, themeDisplay.getPortletDisplay().getId(), ActionKeys.UPDATE) %>'>
        <portlet:actionURL name="processCourseFeed" var="processCourseFeedUrl">
            <portlet:param name="feedRefId" value="<%= feedRefId %>" />
        </portlet:actionURL>
        <liferay-ui:icon image="edit" message="course-feed-process" url='<%= processCourseFeedUrl %>'/>
    </c:if>
    --%>

	<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid, themeDisplay.getPortletDisplay().getId(), ActionKeys.DELETE) %>'>

        <%--
        <c:if test='<%= !feedRef.getRemoved() %>'>
		    <liferay-ui:icon image="delete" message="course-feed-remove" url='<%= disableCourseFeedUrl %>' />
        </c:if>

        <c:if test='<%= feedRef.getRemoved() %>'>
		    <liferay-ui:icon image="delete" message="course-feed-enable" url='<%= enableCourseFeedUrl %>' />
        </c:if>
        --%>

        <c:if test='<%= feedRef.getPshbSubscribed() %>'>
            <liferay-ui:icon image="edit" message="course-feed-details-push-unsubscribe" url="<%= pushUnsubscribeURL %>"/>
        </c:if>

        <c:if test='<%= feedRef.getPshbSubscribed() || !feedRef.getPshb().equals("") %>'>
            <liferay-ui:icon image="edit" message="course-feed-details-push-resubscribe" url="<%= pushResubscribeURL %>"/>            
        </c:if>
	</c:if>
</liferay-ui:icon-menu>
