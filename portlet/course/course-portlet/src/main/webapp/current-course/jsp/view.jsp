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

<%-- current-course/html/view.jsp --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.CourseRecord" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="org.nterlearning.course.enumerations.*" %>
<%@ page import="org.nterlearning.course.enumerations.ComponentRecordFilterType" %>
<%@ page import="org.nterlearning.course.util.*" %>
<%@ page import="org.nterlearning.course.util.ComponentRecordQueryUtils" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.Component" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="nter" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
    long userId = themeDisplay.getUserId();
    long courseId = 0;   // retrieve all courses not a specific
    boolean studentCourses = true;
    // hard-coded:
    boolean isPurchased = false;
    String buttonCssClass = "button";

    HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);

    // retrieve courses
    String orderByType = "desc";
    String sortType = CourseRecordSortType.UPDATED_DATE.toString();
    String filterType = CourseRecordFilterType.ALL.toString();
    int start = 0;
    int end = 4;
    Boolean dynamicSortEnabled = false;

    List<CourseRecordQueryResult> courseRecordQueryResults = CourseRecordQueryUtils.getCompoundQueryResults(userId, locale, courseId, filterType, sortType, orderByType.equals("asc"), start, end, dynamicSortEnabled);

    // grand total also to be used for link to history page later in jsp
    int courseRecordGrandTotal = (int) CourseRecordLocalServiceUtil.countByUserIdFilter(userId, courseId, filterType);

    String calloutText;
    // if student has no courseRecords then welcome them to NTER
    if (courseRecordGrandTotal == 0) {
        studentCourses = false;
        String[] callout_args = {user.getFirstName(), user.getLastName()};
        String calloutTitle = LanguageUtil.format(pageContext, "current-course-callout-welcome", callout_args);
        calloutText = LanguageUtil.get(pageContext, "current-course-callout-new-student");
%>
        <div class="current-course-callout">
			<h2><%= calloutTitle %></h2>
            <p><%= calloutText %></p>
            <%
                 String allCoursesUrl;
                 Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
                 allCoursesUrl = PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/courses";
            %>
             <a href="<%= allCoursesUrl %>" class="button"><liferay-ui:message key="current-course-callout-browse" /></a>

        </div>
	<% } %>


<div class="current-courses" data-refresh-url="<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" />">

	<div class="course-status-section">
        <c:choose>
            <c:when test='<%= courseRecordQueryResults.size() > 0 %>'>
				<h2><%= LanguageUtil.get(pageContext, "recent-activity") %></h2>

				<%
				// if all courseRecords returned are finished, then check to see if other courses exist which are active,  if found display a message
				int finishedCourseCount = CourseRecordQueryUtils.getCourseFinishedCount(courseRecordQueryResults);
				int activeCourseCount = (int) CourseRecordLocalServiceUtil.countByUserIdFilter(userId, courseId, CourseRecordFilterType.IN_PROGRESS_STATUS.toString());

				if (activeCourseCount > 0) {
					if ((courseRecordQueryResults.size() == finishedCourseCount) &&
						(courseRecordQueryResults.size() < courseRecordGrandTotal) ) {
						String historyUrl = PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() + "/course-history?_archivedcourses_WAR_courseportlet_orderByCol=COMPLETION_STATUS&_archivedcourses_WAR_courseportlet_orderByType=asc&p_p_id=archivedcourses_WAR_courseportlet";
						String[] callout_args = {Integer.toString(activeCourseCount), historyUrl};
						if (activeCourseCount == 1) {
							calloutText = LanguageUtil.format(pageContext, "current-course-callout-history-link-single", callout_args);
						} else {
							calloutText = LanguageUtil.format(pageContext, "current-course-callout-history-link-multiple", callout_args);
						}
						%>
						<div class="current-course-callout highlightbox">
							<p><%= calloutText %></p>
							<a class="button" href="<%= historyUrl %>"><liferay-ui:message key="current-course-view-incomplete" /></a>
						</div>
						<%
					}
				} else {
					// user has finished all courses that they started. when we have them, we might want to switch this to link to amazon-style recommendations.
					String browseUrl = PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() + "/courses";
					String[] callout_args = {browseUrl};
					calloutText = LanguageUtil.format(pageContext, "current-course-callout-no-incomplete-courses", callout_args);
					%>
					<div class="current-course-callout highlightbox">
						<p><%= calloutText %></p>
						<a class="button" href="<%= browseUrl %>"><liferay-ui:message key="current-course-callout-browse" /></a>
					</div>
					<%
				}
				%>

                <ul class="courses <% if (courseRecordQueryResults.size() == 1) { %>one-course-record<% } %>">
                    <%
                        boolean firstRecord = true;
                        for (CourseRecordQueryResult result : courseRecordQueryResults) {
                            Course course = result.getCourse();
                            CourseRecord courseRecord = result.getCourseRecord();
                            Double userRating = 0.0;
                            if (result.getUserRating() != null) userRating = result.getUserRating();

                            String completionStatus = courseRecord.getCompletionStatus();
                            course.startSafeImageEnumeration(locale, LocaleUtil.getDefault());
                            Group group = GroupLocalServiceUtil.getGroup(course.getGroupId());

                            // obtain students status on course components
                            String componentRecordFilterType = ComponentRecordFilterType.ALL.toString();
                            String componentRecordSortType = ComponentRecordSortType.COMPONENT_ORDER_WEIGHT.toString();
                            List<ComponentRecordQueryResult> componentResults = ComponentRecordQueryUtils.getCompoundQueryResults(courseRecord.getCourseRecordId(), userId, locale, componentRecordFilterType, componentRecordSortType, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

                            // retrieve count of active, finished components
                            int activeComponentCount = ComponentRecordQueryUtils.getComponentActiveCount(componentResults);
                            int finishedComponentCount = ComponentRecordQueryUtils.getComponentFinishedCount(componentResults);

                            //Determine resumeComponent for button to Continue, retry, Start component of a course
                            Component resumeComponent = null;
                            ComponentRecordQueryResult resumeComponentRecord = ComponentRecordQueryUtils.getFirstUncompletedComponent(componentResults);
                            if (resumeComponentRecord != null) resumeComponent = resumeComponentRecord.getComponent();

                            //Determine failedComponent for button failed retry of a course
                            Component failedComponent = null;
                            if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
								ComponentRecordQueryResult failedComponentRecord = ComponentRecordQueryUtils.getFirstFailedComponent(componentResults);
                                failedComponent = failedComponentRecord.getComponent();
                            }

							//Setup for review button
                            String reviewButtonText = "";
                            String reviewHref = "";
                            if ((completionStatus.equals(CompletionStatusType.FAILED.getDbValue()) ||
                                    completionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) &&
                                    (userRating == 0.0)) {
                                reviewButtonText = LanguageUtil.get(pageContext, "course-actions-review");
                                reviewHref = course.getUrl() + "#review";
                            }

							String statusClass = "";
							if (completionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue())) {
								statusClass = "progress";
							} else if (completionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) {
								statusClass = "complete";
							} else if (completionStatus.equals(CompletionStatusType.FAILED.getDbValue())) {
								statusClass = "failed";
							} else if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
								statusClass = "failed";
							} else {
								statusClass = "notstarted";
							}

							try{
								isPurchased = course.isPurchased(user.getUserId());
							}catch(IndexOutOfBoundsException e) {
								out.print("This user is for testing purposes only.  Commerce will not work as expected.");
							}

                            request.setAttribute("buttonCssClass"," ");
                            request.setAttribute("isPurchased",isPurchased);
                            request.setAttribute("course",course);
                            request.setAttribute("courseRecord", courseRecord);
                            request.setAttribute("finishedComponentCount",finishedComponentCount);
                            request.setAttribute("activeComponentCount", activeComponentCount);
                            request.setAttribute("resumeComponent", resumeComponent);
                            request.setAttribute("failedComponent", failedComponent);
                    %>
                    <li class='course <%= statusClass %>'><article>
                        <a href="<%= course.getUrl() %>" class="thumbnail-link"><img src='<%= course.getSafeImage(0)
                        .getSmallImageUrl(themeDisplay) %>' class="course-thumbnail" title="<%= course.getTitle(locale) %>"
						alt="<%= course.getTitle(locale) %>" /></a>
                        <div class="course-description">
                            <h4 class="course-title"><a href="<%= course.getUrl() %>"><%= course.getTitle(locale) %></a></h4>

                            <nter:course-last-visit 
                                course="<%=course%>" courseRecord="<%=courseRecord%>" finishedComponentCount="<%=finishedComponentCount%>"
                                activeComponentCount="<%=activeComponentCount%>" pageContext="<%=pageContext%>"/>
							<%
							if (course.hasNewerVersion()) {
								Course newestVersion = course.getMostRecentVersion();
								if (course.isRemoved()) { %>
									<div class="portlet-msg-error"><%= LanguageUtil.format(pageContext, "course-superseded-removed", newestVersion.getUrl()) %></div>
								<% } else { %>
									<div class="portlet-msg-error"><%= LanguageUtil.format(pageContext, "course-superseded", newestVersion.getUrl()) %></div>
								<%  }

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
								<% } else if (alertChangedComponentCount == 1) { %>
                                    <div class="portlet-msg-alert"><%= LanguageUtil.format(pageContext, "course-updated-with-one-component",alertChangedComponent) %> </div>
								<% } else { %>
                                    <div class="portlet-msg-alert"><%= LanguageUtil.format(pageContext, "course-updated-with-many-component",alertChangedComponent) %> </div>
								<%
                                }
							}
							%>

							<dl>
								<div class="course-attribute">
									<%
										String duration = course.getFriendlyDuration(pageContext);
									%>
									<dt><liferay-ui:message key="course-description-duration" />:</dt><dd><%= duration %> </dd>
								</div>
                                <div class="actions">
                                    <nter:course-next-section isPurchased="<%=isPurchased%>"
                                        course="<%=course%>" courseRecord="<%=courseRecord%>" finishedComponentCount="<%=finishedComponentCount%>"
                                        activeComponentCount="<%=activeComponentCount%>" resumeComponent="<%=resumeComponent%>"
                                        failedComponent="<%=failedComponent%>" pageContext="<%=pageContext%>"/>
							    </div>
                            </dl>

							<% if (userRating > 0.0) {%>
								<div>
									<span class="your-rating">
										<%= (userRating > 1) ?
										LanguageUtil.format(pageContext, "current-course-reviewed-multiple", userRating.intValue()) :
										LanguageUtil.get(pageContext, "current-course-reviewed-single") %>
									</span>
									<liferay-ui:ratings-score score="<%= userRating %>" />
								</div>
                            <% } %>

                            <div class="actions">
								<nter:course-button buttonCssClass="<%=buttonCssClass%>" isPurchased="<%=isPurchased%>"
									course="<%=course%>" courseRecord="<%=courseRecord%>" finishedComponentCount="<%=finishedComponentCount%>"
									activeComponentCount="<%=activeComponentCount%>" resumeComponent="<%=resumeComponent%>"
									failedComponent="<%=failedComponent%>" pageContext="<%=pageContext%>"/>

								<% if (!reviewButtonText.equals("") && !reviewHref.equals("")) { %>
									<a href="<%=reviewHref %>" class="button" ><%=reviewButtonText%></a>
								<% } %>
							</div>

                        </div>
                    </article></li>
                    <%
                            }
                    %>
                </ul>
            </c:when>
        </c:choose>

		<% if (studentCourses) {%>
				<ul class="actions">
					<li><a href="<%= PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() %>/course-history" class="button secondary"><liferay-ui:message key="current-course-history" /></a></li>
					<li><a href="<%= PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() %>/courses" class="button secondary"><liferay-ui:message key="current-course-browse" /></a></li>
				</ul>
		<% } %>

	</div>
</div>