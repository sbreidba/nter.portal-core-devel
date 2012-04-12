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

<%@ include file="/course-feeds/jsp/cp/init.jsp" %>

<liferay-ui:error key="course-feed-details-push-unsubscribe-failed"
                  message="course-feed-details-push-unsubscribe-failed" />
<liferay-ui:error key="course-feed-details-push-resubscribe-failed"
                  message="course-feed-details-push-resubscribe-failed" />

<%
    Long feedId = ParamUtil.getLong(request, "feedRefId");

    String returnURL = ParamUtil.getString(request, "feedRedirect");
    returnURL += "&tabs=" + ParamUtil.getString(request, "feedTabs", "Active");
    returnURL += "&cur=" + ParamUtil.getString(request, "feedCur", "1");
    returnURL += "&delta=" + ParamUtil.getString(request, "feedDelta", "5");

    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("jspPage", "/course-feeds/jsp/cp/feedDetails.jsp");
    portletURL.setParameter("feedRedirect", returnURL);
    portletURL.setParameter("feedTabs", ParamUtil.getString(request, "feedTabs"));
    portletURL.setParameter("feedCur", ParamUtil.getString(request, "feedCur"));
    portletURL.setParameter("feedDelta", ParamUtil.getString(request, "feedDelta"));
    portletURL.setParameter("feedRefId", feedId.toString());

    FeedReference feedRef = FeedReferenceLocalServiceUtil.getFeedReference(feedId);

    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
    String createDate = df.format(feedRef.getCreateDate());

    String syncDate = "Not Processed";
    if ((feedRef.getSyncDate() != null) && (!feedRef.getSyncDate().equals(""))) {
        syncDate = df.format(feedRef.getSyncDate());
    }

    String feedVersion = "Unknown";
    if ((feedRef.getFeedVersion() != null) && (!feedRef.getFeedVersion().equals(""))) {
        feedVersion = feedRef.getFeedVersion();
    }

    String numOfEntriesHeader = "course-feed-details-number-of-entries";
    if (feedRef.getFeedType().equals(FeedType.course.getCodeValue())) {
        numOfEntriesHeader = "course-feed-details-number-of-courses";
    }
    else if (feedRef.getFeedType().equals(FeedType.record.getCodeValue())) {
        numOfEntriesHeader = "course-feed-details-number-of-records";
    }
%>

<a href='<%=returnURL%>' cssClass="moreLink"><%= LanguageUtil.get(pageContext, "course-feed-details-return") %></a>

<article class="course-feed">
<div class="course-feed-detail">
    <h3 class="course-feed-title"><%= feedRef.getHref() %></h3>
    <dl>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-feed-iri") %>: </dt><dd><%= feedRef.getFeedIri() %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-feed-version") %>: </dt><dd><%= feedVersion %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-content-provider") %>: </dt><%= feedRef.getContentProviderId() %></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-trustworthy") %>: </dt><dd><%= feedRef.getTrustworthyWeight() %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-create-date") %>: </dt><dd><%= createDate %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-sync-date") %>: </dt><dd><%= syncDate %></dd></div>

        <%  if (feedRef.getRemoved()) { %>
            <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-delete-date") %>: </dt><dd><%= df.format(feedRef.getRemovedDate()) %></dd></div>
            <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-delete-detail") %>: </dt><dd><%= FeedRemovalReasonType.getLabelFromCode(feedRef.getRemovedReason()) %></dd></div>
        <% } %>

        <% if (feedRef.getFeedType().equals(FeedType.course.getCodeValue())) { %>
            <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-assignment") %>: </dt><dd><%= feedRef.getOwnerName() %></dd></div>
        <% } %>
    </dl>
</div>



<div class="separator"></div>
<article class="course-feed">
<div class="course-feed-detail">
    <h3 class="course-feed-title"><%= LanguageUtil.get(pageContext, "course-feed-details-push") %></h3>
    <dl>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-push-server") %>: </dt><dd><%= feedRef.getPshb()%></dd></div>

        <% if (!feedRef.getPshb().equals("")) { %>
            <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-feed-details-push-subscribed") %>: </dt><dd><%= feedRef.getPshbSubscribed() ? "Yes" : "No" %></dd></div>        
        <% } %>
    </dl>
</div>

<portlet:actionURL name="pushUnsubscribe" var="pushUnsubscribeURL">
    <portlet:param name="feedCur" value='<%= ParamUtil.getString(request, "feedCur", "1") %>'/>
    <portlet:param name="feedDelta" value='<%= ParamUtil.getString(request, "feedDelta", "5") %>'/>
    <portlet:param name="feedRedirect" value='<%= ParamUtil.getString(request, "feedRedirect") %>'/>
    <portlet:param name="feedRefId" value='<%= feedId.toString() %>'/>
    <portlet:param name="feedTabs" value='<%= ParamUtil.getString(request, "feedTabs", "Active") %>'/>
    <portlet:param name="jspPage" value='/course-feeds/jsp/cp/feedDetails.jsp'/>
</portlet:actionURL>

<portlet:actionURL name="pushResubscribe" var="pushResubscribeURL">
    <portlet:param name="feedCur" value='<%= ParamUtil.getString(request, "feedCur", "1") %>'/>
    <portlet:param name="feedDelta" value='<%= ParamUtil.getString(request, "feedDelta", "5") %>'/>
    <portlet:param name="feedRedirect" value='<%= ParamUtil.getString(request, "feedRedirect") %>'/>
    <portlet:param name="feedRefId" value='<%= feedId.toString() %>'/>
    <portlet:param name="feedTabs" value='<%= ParamUtil.getString(request, "feedTabs", "Active") %>'/>
    <portlet:param name="jspPage" value='/course-feeds/jsp/cp/feedDetails.jsp'/>
</portlet:actionURL>

<aui:form method="post" >
    <aui:fieldset>
        <aui:button-row>
            <aui:button value="course-feed-details-push-unsubscribe"
                        disabled="<%= !feedRef.getPshbSubscribed()%>"
                        onClick="<%= pushUnsubscribeURL.toString()%>"/>
            <aui:button value="course-feed-details-push-resubscribe"
                        disabled='<%= !(feedRef.getPshbSubscribed() || !feedRef.getPshb().equals("")) %>'
                        onClick="<%= pushResubscribeURL.toString() %>"/>
        </aui:button-row>
    </aui:fieldset>
</aui:form>
</article>


<div class="separator"></div>
<h3 class="main-page-heading"><%= LanguageUtil.get(pageContext, "course-feed-details-history") %></h3>
<liferay-ui:search-container
        searchContainer='<%= new SearchContainer(renderRequest, null, null, "cur", 10, portletURL, null, "course-feed-no-sync-logs") %>'
        emptyResultsMessage="course-feed-no-sync-logs"
        delta="10">

    <liferay-ui:search-container-results>
        <%
            total = (int)FeedSyncHistoryLocalServiceUtil.dynamicQueryCount(
                        FeedSyncHistoryLocalServiceUtil.generateDynamicQuery(feedId));
            results = FeedSyncHistoryLocalServiceUtil.dynamicQuery(
                        FeedSyncHistoryLocalServiceUtil.generateDynamicQuery(feedId)
                                                       .addOrder(OrderFactoryUtil.desc("syncDate")));

            searchContainer.setResults(results);
            searchContainer.setTotal(total);

            pageContext.setAttribute("results", results.subList(searchContainer.getStart(),
                                      searchContainer.getResultEnd()));
            pageContext.setAttribute("total", total);

            searchContainer.setIteratorURL(portletURL);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="org.nterlearning.datamodel.catalog.model.FeedSyncHistory"
            keyProperty="syncId"
            modelVar="feedSyncHistory">

        <liferay-ui:search-container-column-text
            name="course-feed-details-date">
            <%= df.format(feedSyncHistory.getSyncDate()) %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
            name="<%= numOfEntriesHeader %>"
            property="numberOfEntries" />

        <liferay-ui:search-container-column-text
            name="course-feed-details-status">
            <%= feedSyncHistory.getSuccess() ? "Success" : "Failed" %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
            name="course-feed-details-sync-message"
            property="syncMessage" />
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator searchContainer='<%= searchContainer %>'
                                paginate='<%= true %>'/>
</liferay-ui:search-container>



<portlet:actionURL name="clearFeedSyncHistory" var="clearFeedSyncHistoryUrl">
    <portlet:param name="feedRefId" value="<%= feedId.toString() %>" />
</portlet:actionURL>

<aui:form method="post" action="<%= clearFeedSyncHistoryUrl.toString() %>">
    <aui:input name="feedRefId" value='<%= feedId %>' type="hidden" />
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="course-feed-details-clear-sync-history" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>