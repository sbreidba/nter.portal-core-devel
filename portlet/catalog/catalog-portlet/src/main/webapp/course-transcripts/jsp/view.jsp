<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portlet.PortalPreferences" %>
<%@ page import="org.nterlearning.course.util.CourseRecordQueryResult" %>
<%@ page import="java.util.List" %>
<%@ page import="org.nterlearning.course.util.CourseRecordQueryUtils" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.CourseRecord" %>
<%@ page import="org.nterlearning.course.util.ComponentRecordQueryResult" %>
<%@ page import="org.nterlearning.course.util.ComponentRecordQueryUtils" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="org.nterlearning.course.enumerations.*" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%--
National Training and Education Resource (NTER)
Copyright (C) 2012  SRI International

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

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<%
    long userId = ParamUtil.getLong(request, "userId");

    PortletURL portletUrl = renderResponse.createRenderURL();
    portletUrl.setParameter("userId", String.valueOf(userId));

    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,
            themeDisplay.getLocale());

%>

<liferay-ui:search-container
    searchContainer='<%= new SearchContainer(renderRequest, null, null, "cur", 20, portletUrl, null,
                                "nter-student-records-no-record")%>'
    emptyResultsMessage="nter-student-records-no-record"
    delta="20"
    curParam="cur"
    orderByCol="updateDate"
    orderByType="desc">

    <liferay-ui:search-container-results>
        <%
            // These params are set by the search container when you click on a sortable
            // header.
            String orderByCol = ParamUtil.getString(renderRequest, "orderByCol");
            String orderByType = ParamUtil.getString(renderRequest, "orderByType");
            String sortType = "";

            PortalPreferences prefs = PortletPreferencesFactoryUtil.getPortalPreferences(request);
            if ((orderByCol != null) && !orderByCol.equals("")) {
                prefs.setValue(portletName, "transcript-order-by-col", orderByCol);
                sortType = CourseRecordSortType.valueOf(orderByCol).toString();
            }
            else {
                orderByCol = prefs.getValue(portletName, "transcript-order-by-col");
                sortType = (orderByCol == null) ? CourseRecordSortType.UPDATED_DATE.toString()
                                                : CourseRecordSortType.valueOf(orderByCol).toString();
            }

            if ((orderByType != null) && !orderByType.equals("")) {
                prefs.setValue(portletName, "transcript-order-by-type", orderByType);
            }
            else {
                orderByType = prefs.getValue(portletName, "transcript-order-by-type");

                if (orderByType == null) {
                    orderByType = "desc";
                }
            }

            searchContainer.setOrderByCol(sortType);
            searchContainer.setOrderByType(orderByType);

            Boolean dynamicSortEnabled = true;

            List<CourseRecordQueryResult> courseRecordQueryResults =
                    CourseRecordQueryUtils.getCompoundQueryResults(userId, locale, 0,
                            CourseRecordFilterType.ALL.toString(), sortType, orderByType.equals("asc"),
                            searchContainer.getStart(), searchContainer.getEnd(), dynamicSortEnabled);
            total = (int) CourseRecordLocalServiceUtil.countByUserIdFilter(userId, 0,
                    CourseRecordFilterType.ALL.toString());

            searchContainer.setResults(courseRecordQueryResults);
            searchContainer.setTotal(total);

            pageContext.setAttribute("results", courseRecordQueryResults);
            pageContext.setAttribute("total", total);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="CourseRecordQueryResult"
            modelVar="courseRecordQueryResult"
            rowVar="thisRow" >

        <%
            // process course record
            CourseRecord courseRecord = courseRecordQueryResult.getCourseRecord();
            Course course = courseRecordQueryResult.getCourse();
            String courseCompletionStatus = courseRecord.getCompletionStatus();

            // process component status
            String componentRecordFilterType = ComponentRecordFilterType.ALL.toString();
            String componentRecordSortType = ComponentRecordSortType.COMPONENT_ORDER_WEIGHT.toString();
            List<ComponentRecordQueryResult> allComponentResults = ComponentRecordQueryUtils
                    .getCompoundQueryResults(courseRecord.getCourseRecordId(), userId, locale,
                            componentRecordFilterType, componentRecordSortType, true,
                            QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            // Separate results of combined query into contents and resources.
            List<ComponentRecordQueryResult> componentResults =
                    ComponentRecordQueryUtils.getContentComponents(allComponentResults);
            List<ComponentRecordQueryResult> resourceResults =
                    ComponentRecordQueryUtils.getResourceComponents(allComponentResults);

            // establish status message overall count
            int totalComponentCount = componentResults.size();

            // retrieve count of active, finished components
            int activeComponentCount = ComponentRecordQueryUtils.getComponentActiveCount(componentResults);
            int finishedComponentCount = ComponentRecordQueryUtils.getComponentFinishedCount(componentResults);
        %>

        <liferay-ui:search-container-column-text
                name="course-title-heading"
                orderable="true"
                orderableProperty="<%= CourseRecordSortType.COURSE_TITLE.toString() %>">
            <%= course.getTitle(locale) %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="course-status-heading"
                orderable="true"
                orderableProperty="<%= CourseRecordSortType.COMPLETION_STATUS.toString() %>">
            <%
                String courseStatus;
                String statusClass = "";
                String completionStatus = courseRecord.getCompletionStatus();
                if (completionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue()) &&
                        (activeComponentCount > 0 || finishedComponentCount > 0)) {
                    courseStatus = LanguageUtil.get(pageContext, "course-status-started");
                    statusClass = "progress";
                }
                else if (completionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) {
                    courseStatus = LanguageUtil.get(pageContext, "course-status-complete");
                    statusClass = "complete";
                }
                else if (completionStatus.equals(CompletionStatusType.FAILED.getDbValue())) {
                    courseStatus = LanguageUtil.get(pageContext, "course-status-failed");
                    statusClass = "failed";
                }
                else if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
                    courseStatus = LanguageUtil.get(pageContext, "course-status-failed");
                    statusClass = "failed failed-retry";
                }
                else {
                    courseStatus = LanguageUtil.get(pageContext, "course-status-not-started");
                    statusClass = "notstarted";
                }

                thisRow.setClassName(statusClass);
            %>

            <%= courseStatus %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name="course-visited-heading"
                orderable="true"
                orderableProperty="<%= CourseRecordSortType.UPDATED_DATE.toString() %>">
            <%= courseRecord.getFriendlyUpdatedDate(pageContext)%>
        </liferay-ui:search-container-column-text>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator paginate="<%= true %>"/>
</liferay-ui:search-container>