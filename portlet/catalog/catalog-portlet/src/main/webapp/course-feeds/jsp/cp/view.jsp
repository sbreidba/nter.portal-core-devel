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

<portlet:actionURL name="updateFeedOwner" var="updateFeedOwnerUrl" />
<portlet:actionURL name="processCourseRegistryFeeds" var="processCourseRegistryFeedsUrl" />
<portlet:actionURL name="processMigrateReviewFeedImport" var="processMigrateReviewFeedImport" />
<%
    Long themeCompanyId = themeDisplay.getCompanyId();
    Long scopeOrgId = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getClassPK();
    Long guestGroupId = GroupLocalServiceUtil.getGroup(themeCompanyId, GroupConstants.GUEST).getGroupId();

    String tabs = ParamUtil.getString(request, "tabs", "Active");

    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("tabs", tabs);
%>

<aui:form method="post" name="<portlet:namespace/>fm">
    <liferay-ui:tabs param="tabs"
                     names="Active,Inactive,Blacklisted,All"
                     url='<%=portletURL.toString() %>'/>

    <liferay-ui:search-container
            searchContainer='<%= new SearchContainer(renderRequest, null, null, "cur", 10, portletURL, null, "course-feed-no-feed-records") %>'
            emptyResultsMessage="course-feed-no-feed-records"
            delta="10">

        <liferay-ui:search-container-results>
            <%
                if (tabs.equals("All")) {
                    total = FeedReferenceLocalServiceUtil.getFeedReferencesCount();
                    results = FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
                }
                else {
                    boolean removedTab = (tabs.equals("Inactive") || tabs.equals("Blacklisted"));
                    String removedCode = null;

                    if (removedTab) {
                        removedCode = (tabs.equals("Inactive")) ? FeedRemovalReasonType.INACTIVE.getCode()
                                                               : FeedRemovalReasonType.BLACKLISTED.getCode();
                    }

                    total = (int)FeedReferenceLocalServiceUtil.dynamicQueryCount(
                                     FeedReferenceLocalServiceUtil.generateDynamicQuery(removedTab, removedCode));
                    results = (List<FeedReference>)FeedReferenceLocalServiceUtil.dynamicQuery(
                                     FeedReferenceLocalServiceUtil.generateDynamicQuery(removedTab, removedCode));

                }

                searchContainer.setResults(results);
                searchContainer.setTotal(total);

                pageContext.setAttribute("results", results.subList(searchContainer.getStart(),
                                          searchContainer.getResultEnd()));
                pageContext.setAttribute("total", total);

                request.setAttribute("cur", searchContainer.getCur());
            %>
        </liferay-ui:search-container-results>

        <liferay-ui:search-container-row
                className="org.nterlearning.datamodel.catalog.model.FeedReference"
                keyProperty="feedReferenceId"
                modelVar="feedReference">

            <liferay-ui:search-container-column-text
                    name="course-feed-feed">
                <portlet:renderURL var="displayDetailsUrl">
                    <portlet:param name="jspPage" value="/course-feeds/jsp/cp/feedDetails.jsp" />
                    <portlet:param name="feedRedirect" value='<%=renderResponse.createRenderURL().toString() %>' />
                    <portlet:param name="feedRefId" value='<%= String.valueOf(feedReference.getFeedReferenceId()) %>'/>
                    <portlet:param name="feedTabs" value='<%= tabs %>'/>
                    <portlet:param name="feedCur" value='<%= String.valueOf(searchContainer.getCur()) %>'/>
                    <portlet:param name="feedDelta" value='<%= String.valueOf(searchContainer.getDelta()) %>'/>
                </portlet:renderURL>
                <%
                    String feedTitle = feedReference.getHref();

                    if (feedTitle.equals("")) {
                        feedTitle = LanguageUtil.get(pageContext, "course-feed-blank");
                    }

                    if (feedReference.getRemoved()) {
                        feedTitle += " " + LanguageUtil.get(pageContext, "course-feed-disabled");
                    }
                %>
                <aui:a href="<%= displayDetailsUrl %>"><%= feedTitle %></aui:a>
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    name="course-feed-status"
                    align="center">
                <%
                    String iconRef;
                    String title;
                    if (feedReference.getPshbSubscribed() && feedReference.getSyncSuccess()) {
                        iconRef = request.getContextPath() + "/course-feeds/images/feed.png";
                        title = LanguageUtil.get(pageContext, "course-feed-icon-subscribed");
                    }
                    else if (feedReference.getPshbSubscribed() && !feedReference.getSyncSuccess()) {
                        iconRef = request.getContextPath() + "/course-feeds/images/feed_error.png";
                        title = LanguageUtil.get(pageContext, "course-feed-icon-error-subscribed");
                    }
                    else if (!feedReference.getPshb().equals("") && !feedReference.getPshbSubscribed()) {
                        iconRef = request.getContextPath() + "/course-feeds/images/feed_error.png";
                        title = LanguageUtil.get(pageContext, "course-feed-icon-subscribed-error");
                    }
                    else if (feedReference.getSyncDate() == null) {
                        iconRef = request.getContextPath() + "/course-feeds/images/error.png";
                        title = LanguageUtil.get(pageContext, "course-feed-icon-no-sync");
                    }
                    else if (feedReference.getSyncSuccess()) {
                        iconRef = request.getContextPath() + "/course-feeds/images/accept.png";
                        title = LanguageUtil.get(pageContext, "course-feed-icon-ok");
                    }
                    else  {
                        iconRef = request.getContextPath() + "/course-feeds/images/exclamation.png";
                        title = LanguageUtil.get(pageContext, "course-feed-icon-error");
                    }
                %>
                <img src="<%= iconRef %>" alt="<%= title %>" title="<%= title %>" />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    name="course-feed-assign-to">
                <%
                    Boolean ownerSelectDisabled = true;
                    Boolean courseRecordFlag = feedReference.getFeedType().equals(FeedType.course.getCodeValue());

                    List<Organization> orgs = new ArrayList<Organization>();
                    Boolean showUnassign = (feedReference.getGroupId() != guestGroupId);
                    Group assignedGroup = GroupLocalServiceUtil.getGroup(feedReference.getGroupId());

                    if (courseRecordFlag && !feedReference.getRemoved()) {
                        // if the user is a site administrator, they can assign to any organization
                        // else if they are an organization administrator, they can assign to their own org and suborgs
                        if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeCompanyId, RoleConstants.ADMINISTRATOR, true)) {
                            ownerSelectDisabled = false;
                            orgs = OrganizationLocalServiceUtil.getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
                        }
                        else if (themeDisplay.getScopeGroup().isOrganization() &&
                                 OrganizationPermissionUtil.contains(permissionChecker, scopeOrgId, RoleConstants.ORGANIZATION_ADMINISTRATOR)) {
                            ownerSelectDisabled = false;
                            orgs.add(OrganizationLocalServiceUtil.getOrganization(scopeOrgId));
                            orgs.addAll(OrganizationLocalServiceUtil.getSuborganizations(orgs));
                        }

                        if (assignedGroup.isOrganization()) {
                            orgs.remove(OrganizationLocalServiceUtil.getOrganization(assignedGroup.getClassPK()));
                        }
                    }
                %>

                <% if (courseRecordFlag) { %>
                    <%--TODO : manage this update via javascript, instead of sending the entire list to the server for every change--%>
                    <aui:input name="feedRefId" value='<%= feedReference.getFeedReferenceId() %>' type="hidden" />
                    <aui:select name="ownerSelect" label=""
                                disabled="<%= ownerSelectDisabled %>"
                                onchange='<%= "this.form.action=\'" + updateFeedOwnerUrl.toString() + "\'; this.form.submit();" %>'>

                        <aui:option label="<%= feedReference.getOwnerName() %>" value="<%= feedReference.getGroupId() %>" selected="1" />

                        <% for (Organization org : orgs) { %>
                            <aui:option label='<%= org.getName() %>'
                                        value='<%= GroupLocalServiceUtil.getOrganizationGroup(themeCompanyId, org.getOrganizationId()).getGroupId() %>' />
                        <% } %>

                        <% if (showUnassign) { %>
                            <aui:option label="Unassign" value="<%= guestGroupId %>" selected="0" />
                        <% } %>
                    </aui:select>
                <% } else { %>
                        <%= FeedType.valueOfCode(feedReference.getFeedType()).getLabel() %>
                <% } %>
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-jsp
                    path="/course-feeds/jsp/cp/editActions.jsp"
                    align="right" />
        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate='<%= true %>'/>
    </liferay-ui:search-container>
</aui:form>

<div class="separator"></div>
<h3 class="course-feed-title"><%= LanguageUtil.get(pageContext, "course-feed-process-heading") %></h3>
<p><%= LanguageUtil.get(pageContext, "course-feed-process-label") %></p>
<aui:form action="<%= processCourseRegistryFeedsUrl.toString() %>" method="post">
	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="course-feed-process-registry" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>
