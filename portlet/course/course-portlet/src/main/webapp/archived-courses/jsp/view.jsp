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

<%-- archived-courses/html/view.jsp --%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.PortalPreferences" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="org.nterlearning.datamodel.catalog.model.Component" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.ComponentRecord" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.CourseRecord" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.Courses_Components" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="org.nterlearning.course.util.*" %>
<%@ page import="org.nterlearning.course.enumerations.*" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="nter" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%  // Display special message when user not yet enrolled
        String[] msg_callout_args = {user.getFirstName(), user.getLastName()};
        String msg_calloutText = LanguageUtil.format(pageContext, "current-course-callout-new-student", msg_callout_args);

		long userId = themeDisplay.getUserId();
		long courseId = 0; //retrieve all courses not a specific;
%>


<div class="my-courses">
	<div class="notification-area" aria-live="polite" aria-atomic="false"></div>
	<liferay-ui:search-container emptyResultsMessage="<%= msg_calloutText %>" orderByCol="updateDate" orderByType="desc" delta="5">
		<liferay-ui:search-container-results>

		<%
		// These parameters are set by the search container when you click on a sortable
		// header.  The orderByCol element is set to the value of the
		// orderableProperty element of the column clicked.  orderByType toggles
		// between asc and desc.  It's null at the beginning.

        String orderByCol = ParamUtil.getString(renderRequest, "orderByCol");
		String orderByType = ParamUtil.getString(request, "orderByType");
        String sortType = "";
		// and pagination.  Get values from preferences if parameters are not set.
		PortalPreferences prefs = PortletPreferencesFactoryUtil.getPortalPreferences(request);

        if (orderByCol != null && !orderByCol.equals("")) {
            prefs.setValue(portletName, "archived-courses-order-by-col", orderByCol);
            sortType = CourseRecordSortType.valueOf(orderByCol).toString();
        } else {
            orderByCol = prefs.getValue(portletName, "archived-courses-order-by-col");
            if (orderByCol == null) {
              sortType = CourseRecordSortType.UPDATED_DATE.toString();
            } else {
                 sortType = CourseRecordSortType.valueOf(orderByCol).toString();
            }
        }

		if(orderByType != null && !orderByType.equals("")){
			prefs.setValue(portletName, "archived-courses-order-by-type", orderByType);
		}else{
			orderByType = prefs.getValue(portletName, "archived-courses-order-by-type");
			if (orderByType == null) orderByType = "desc";
		}

 	searchContainer.setOrderByCol(sortType);
	searchContainer.setOrderByType(orderByType);

	// Get the results.  "results" is a variable defined within the search-container-results tag.
    // Dynamic sort enabled - allowing post-query sort by title, updatedDate, CompletionStatus, UserRating
    // Dynamic sort will return a maximum of 500 courseRecords.
    Boolean dynamicSortEnabled = true;
    String filterType = CourseRecordFilterType.ALL.toString();

    List<CourseRecordQueryResult> courseRecordQueryResults = CourseRecordQueryUtils.getCompoundQueryResults(userId, locale, courseId, filterType, sortType, orderByType.equals("asc"), searchContainer.getStart(), searchContainer.getEnd(), dynamicSortEnabled);

    // Set the total number of results in the search container.
    int totalRecords = (int) CourseRecordLocalServiceUtil.countByUserIdFilter(userId, courseId, filterType);
    if (totalRecords >= 500) {
        total = 500;
        String[] callout_args = {Integer.toString(totalRecords)};
        String calloutText = LanguageUtil.format(pageContext, "course-history-callout-max-post-sort", callout_args);
%>
        <div class="course-history-callout highlightbox">
            <p><%= calloutText %></p>
<%  // TODO create a new portlet which queries all courseRecords but has dynamic sort disabled
    // Then adjust following href statement to link to the new portlet
    //        <a href="zz= PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() zz/course-history"><liferay-ui:message key="course-history-link-to-history-nosort" /></a>
%>
        </div>
<%  } else {
        total = totalRecords;
    }

//System.out.print("\n filterType =" + filterType + "\n sortType = " + sortType + " asc/desc order type = " + orderByType + "\n total returned by count query = " + total + " size of result list = " + courseRecordQueryResults.size());

	searchContainer.setResults(courseRecordQueryResults);
	searchContainer.setTotal(total);

	// Set the pagination sublist of results on the page.
	pageContext.setAttribute("results", courseRecordQueryResults);
	pageContext.setAttribute("total", total);
%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row className="CourseRecordQueryResult" modelVar="courseRecordQueryResult" rowVar="thisrow">
			<%
            // hard-coded:
            boolean isPurchased = false;
            String buttonCssClass = "button";

            CourseRecord courseRecord = courseRecordQueryResult.getCourseRecord();
			Course course = courseRecordQueryResult.getCourse();
			Double userRating = 0.0;
			if (courseRecordQueryResult.getUserRating() != null) userRating = courseRecordQueryResult.getUserRating();

            String courseCompletionStatus = courseRecord.getCompletionStatus();

            userId = themeDisplay.getUserId();

            // obtain students status on course components
            String componentRecordFilterType = ComponentRecordFilterType.ALL.toString();
            String componentRecordSortType = ComponentRecordSortType.COMPONENT_ORDER_WEIGHT.toString();
            List<ComponentRecordQueryResult> allComponentResults = ComponentRecordQueryUtils.getCompoundQueryResults(courseRecord.getCourseRecordId(), userId, locale, componentRecordFilterType, componentRecordSortType, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            // Separate results of combined query into contents and resources.
            List<ComponentRecordQueryResult> componentResults = ComponentRecordQueryUtils.getContentComponents(allComponentResults);
            List<ComponentRecordQueryResult> resourceResults = ComponentRecordQueryUtils.getResourceComponents(allComponentResults);

            // establish status message overall count
            int totalComponentCount = componentResults.size();

            // retrieve count of active, finished components
            int activeComponentCount = ComponentRecordQueryUtils.getComponentActiveCount(componentResults);
            int finishedComponentCount = ComponentRecordQueryUtils.getComponentFinishedCount(componentResults);

            //Determine resumeComponent for button to Continue, retry, Start component of a course
            Component resumeComponent = null;
            ComponentRecordQueryResult resumeComponentRecord = ComponentRecordQueryUtils.getFirstUncompletedComponent(componentResults);
            if (resumeComponentRecord != null) resumeComponent = resumeComponentRecord.getComponent();

            //Determine failedComponent for button failed retry of a course		
            Component failedComponent = null;
            if (courseCompletionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
				ComponentRecordQueryResult failedComponentRecord = ComponentRecordQueryUtils.getFirstFailedComponent(componentResults);
                failedComponent = failedComponentRecord.getComponent();
            }
			
			try{
				isPurchased = course.isPurchased(user.getUserId());
			}catch(IndexOutOfBoundsException e) {
				out.print("This user is for testing purposes only.  Commerce will not work as expected.");
			}

            request.setAttribute("buttonCssClass",buttonCssClass);
            request.setAttribute("isPurchased",isPurchased);
            request.setAttribute("course",course);
            request.setAttribute("courseRecord", courseRecord);
            request.setAttribute("finishedComponentCount",finishedComponentCount);
            request.setAttribute("activeComponentCount", activeComponentCount);
            request.setAttribute("resumeComponent", resumeComponent);
            request.setAttribute("failedComponent", failedComponent);

            %>

			<liferay-ui:search-container-column-text name='<%= LanguageUtil.get(pageContext, "course-title-heading") %>' orderable="true" orderableProperty='<%= CourseRecordSortType.COURSE_TITLE.toString() %>'>
				<div class="details">
					<%
					course.startSafeImageEnumeration(locale, LocaleUtil.getDefault());
					Group group = GroupLocalServiceUtil.getGroup(course.getGroupId());
					%>
					<a href="<%= course.getUrl() %>" class="thumbnail-link"><img src="<%= course.getSafeImage(0)
						.getSmallImageUrl(themeDisplay) %>" class="course-mini-thumbnail" title="<%= course.getTitle(locale) %>"
						alt="<%= course.getTitle(locale) %>" /></a>
					<div class="course-description">
						<h4 class="course-title" data-record-id="<%= courseRecord.getCourseRecordId() %>"><a href="<%= course.getUrl() %>"><%= course.getTitle(locale) %></a></h4>
						<div class="organization"><a href="<%= course.getOwnerUrl(themeDisplay.getCompanyId()) %>"><%= course.getOwnerName(themeDisplay.getCompanyId()) %></a></div>

						<% if (course.hasNewerVersion()) {
							Course newestVersion = course.getMostRecentVersion();
							if (course.isRemoved()) { %>
							<div class="portlet-msg-error"><%= LanguageUtil.format(pageContext, "course-superseded-removed", newestVersion.getUrl()) %></div>
							<% } else { %>
							<div class="portlet-msg-error"><%= LanguageUtil.format(pageContext, "course-superseded", newestVersion.getUrl()) %></div>
							<% }
						} else if (course.isRemoved()) { %>
							<div class="portlet-msg-error"><%= LanguageUtil.get(pageContext, "course-removed") %></div>
					 <% } else if (course.getVersionDate().compareTo(courseRecord.getUpdatedDate()) > 0) {
                           //loop to retrieve all components which were updated to display in alert
                           StringBuffer alertChangedComponent = new StringBuffer();
                           int alertChangedComponentCount = 0;
                           for (ComponentRecordQueryResult componentResult: componentResults) {
                             if (componentResult.getComponentRecord() != null) {
                               if (componentResult.getComponent().getUpdatedDate().compareTo(componentResult.getComponentRecord().getUpdatedDate()) > 0) {
                                  if (alertChangedComponentCount == 0) {
                                      alertChangedComponent.append("<a href='");
                                  } else {
                                            alertChangedComponent.append(", <a href='");
                                  }
                                   alertChangedComponent.append(componentResult.getComponent().getHref());
                                   alertChangedComponent.append("'>");
                                   alertChangedComponent.append(componentResult.getComponent().getTitle());
                                   alertChangedComponent.append("</a>");
                                   alertChangedComponentCount++;
                               }
                             }
                           }
                                if (alertChangedComponentCount == 0) {
                            %>
                                    <div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext, "course-updated") %> </div>
                            <%  } else if (alertChangedComponentCount == 1) { %>
                                    <div class="portlet-msg-alert"><%= LanguageUtil.format(pageContext, "course-updated-with-one-component",alertChangedComponent) %> </div>
                            <%  } else { %>
                                    <div class="portlet-msg-alert"><%= LanguageUtil.format(pageContext, "course-updated-with-many-component",alertChangedComponent) %> </div>
                            <%
                                }
                        } %>
						<dl>
							<div class="course-attribute">
								<dt><liferay-ui:message key="course-description-duration" />:</dt><dd><%= course.getFriendlyDuration(pageContext) %></dd>
							</div>
						</dl>


						<ul class="toc" role="tree" aria-labelledby="contents-label-1">
							<li role="treeitem"><span class="tree-description"><span class="state-collapsed"><liferay-ui:message key="show" /></span><span class="state-expanded"><liferay-ui:message key="hide" /></span> <span id="contents-label-1"><liferay-ui:message key="course-contents" /></span></span>
								<ul role="group">
									<%
										int linkId = 1;

                                        for (ComponentRecordQueryResult componentResult : componentResults) {

                                            Component component = componentResult.getComponent();
                                            Courses_Components courses_components = componentResult.getCourses_Components();
                                            ComponentRecord componentRecord = componentResult.getComponentRecord();
                                    %>
                                            <nter:component-list courseIsPurchased="<%=isPurchased%>" courseIsRemoved="<%=course.isRemoved()%>"
                                                component="<%=component%>" courses_components="<%=courses_components%>"
                                                componentRecord="<%=componentRecord%>" linkId="<%=linkId%>"
                                                pageContext="<%=pageContext%>" componentIsResource="<%=false%>"></nter:component-list>
                                    <%
											linkId++;
										}
									%>
								</ul>
							</li>
						</ul>
                        <%
                        if (resourceResults.size()> 0) { %>
 						<ul class="toc" role="tree" aria-labelledby="contents-label-1">
							<li role="treeitem"><span class="tree-description"><span class="state-collapsed"><liferay-ui:message key="show" /></span><span class="state-expanded"><liferay-ui:message key="hide" /></span> <span id="contents-label-1"><liferay-ui:message key="course-resources" /></span></span>
								<ul role="group">
									<%
                                        int linkId2 = 1;
                                        for (ComponentRecordQueryResult resourceResult : resourceResults) {
                                            Component resource = resourceResult.getComponent();
                                            Courses_Components courses_components = resourceResult.getCourses_Components();
                                            ComponentRecord componentRecord = null;       //resources will not have progress records
                                    %>
                                            <nter:component-list courseIsPurchased="<%=isPurchased%>" courseIsRemoved="<%=course.isRemoved()%>"
                                                component="<%=resource%>" courses_components="<%=courses_components%>"
                                                componentRecord="<%=componentRecord%>" linkId="<%=linkId2%>"
                                                pageContext="<%=pageContext%>" componentIsResource="<%=true%>"></nter:component-list>
                                    <%
											linkId2++;
										}
									%>
								</ul>
							</li>
						</ul>
                     <% } %>
					</div>

					<ul class="actions">
                         <li>
                             <nter:course-button buttonCssClass="<%=buttonCssClass%>" isPurchased="<%=isPurchased%>"
                                                    course="<%=course%>" courseRecord="<%=courseRecord%>" finishedComponentCount="<%=finishedComponentCount%>"
                                                    activeComponentCount="<%=activeComponentCount%>" resumeComponent="<%=resumeComponent%>"
                                                    failedComponent="<%=failedComponent%>" pageContext="<%=pageContext%>"/>
                         </li>

                        <li><button class="remove-course" data-url="<portlet:actionURL name='updateRemoved' windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'/>" data-id-name="courseRecordId" data-id="<%= courseRecord.getCourseRecordId() %>" data-flag-name="removed"><liferay-ui:message key="course-actions-remove" /></button></li>
					</ul>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text name='<%= LanguageUtil.get(pageContext, "course-status-heading") %>' orderable="true"  orderableProperty="<%= CourseRecordSortType.COMPLETION_STATUS.toString() %>">
				<%
					String courseStatus;
					String statusClass = "";
					String completionStatus = courseRecord.getCompletionStatus();
					if (completionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue())) {
						courseStatus = LanguageUtil.get(pageContext, "course-status-started");
						statusClass = "progress";
					} else if (completionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) {
						courseStatus = LanguageUtil.get(pageContext, "course-status-complete");
						statusClass = "complete";
					} else if (completionStatus.equals(CompletionStatusType.FAILED.getDbValue())) {
                        courseStatus = LanguageUtil.get(pageContext, "course-status-failed");
						statusClass = "failed";
                    } else if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
                        courseStatus = LanguageUtil.get(pageContext, "course-status-failed");
						statusClass = "failed";
                    } else {
						courseStatus = LanguageUtil.get(pageContext, "course-status-not-started");
						statusClass = "notstarted";
					}

					thisrow.setClassName(statusClass);

                    if (completionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue()) ) {
                        String[] callout_args = {Integer.toString(finishedComponentCount), Integer.toString(totalComponentCount)};
                        String calloutText = LanguageUtil.format(pageContext, "course-status-component-completion", callout_args);
                %>
                        <strong class="course-status-message"><%= courseStatus %></strong>
						<p><%= calloutText %></p>
                <%
                    } else {
				%>
                        <strong class="course-status-message"><%= courseStatus %></strong>
						<% if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) { %>
							<p><%= LanguageUtil.get(pageContext, "course-status-retry") %></p>
						<% } %>
                <%
                   }
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text name='<%= LanguageUtil.get(pageContext, "course-visited-heading") %>' orderable="true"  orderableProperty="<%= CourseRecordSortType.UPDATED_DATE.toString() %>">
				<%= courseRecord.getFriendlyUpdatedDate(pageContext) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text name='<%= LanguageUtil.get(pageContext, "course-rating-heading") %>' orderable="true" orderableProperty="<%= CourseRecordSortType.USER_RATING.toString() %>">
                <%
                    if (userRating > 0.0) {
         		%>
                        <liferay-ui:ratings-score score="<%= userRating %>" />
                <%
                    } else {
                %>
                        <div><liferay-ui:message key="no-course-review-for-user" /></div>
						<a href="<%= course.getUrl() %>#review"><liferay-ui:message key="course-actions-review" /></a>
                <%
                    }
                %>
			</liferay-ui:search-container-column-text>

		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator paginate="<%= false %>" />

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
	</liferay-ui:search-container>
</div>


<div>
    <portlet:actionURL name="exportStudentTranscriptToPdf"
                   var="exportStudentTranscriptToPdf" />

    <%
        int totalRecords = (int) CourseRecordLocalServiceUtil.countByUserIdFilter(userId, courseId, CourseRecordFilterType.ALL.toString());
        if (totalRecords > 0) { %>
            <aui:form action="<%= exportStudentTranscriptToPdf %>" method="post">
                <aui:fieldset>
                    <aui:button-row>
                        <aui:button type="submit" value="archived-courses-export-pdf" />
                    </aui:button-row>
                </aui:fieldset>
            </aui:form>
    <%  } %>
</div>