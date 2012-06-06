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


<%-- course-records/jsp/view.jsp --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>

<%@ page import="org.nterlearning.course.current.portlet.ComponentRecordUtil" %>
<%@ page import="org.nterlearning.course.current.portlet.CourseRecordUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.Component" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="com.liferay.portal.service.UserIdMapperLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.UserIdMapper" %>
<%@ page import="org.nterlearning.utils.PortalPropertiesUtil" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<%
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
%>

<h3><%=LanguageUtil.get(pageContext, "course-records-heading")%></h3>
<liferay-ui:search-container
        emptyResultsMessage="course-records-no-course-records"
        delta="10"
        curParam="curCourseRecords"
        deltaParam="deltaCourseRecords">
    <liferay-ui:search-container-results>
        <%
            total = CourseRecordUtil.getCourseRecordsCount();
            searchContainer.setTotal(total);

            results = CourseRecordUtil.getCourseRecords(
                    searchContainer.getStart(), searchContainer.getResultEnd());
            searchContainer.setResults(results);

            pageContext.setAttribute("results", results);
            pageContext.setAttribute("total", total);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="org.nterlearning.datamodel.catalog.model.CourseRecord"
            keyProperty="courseRecordId"
            modelVar="courseRecord">


        <liferay-ui:search-container-column-text
                name='course-record-id'
                property="courseRecordId"
                align="center" />

        <liferay-ui:search-container-column-text
                name='course-record-student-name'>
            <%
                String userName;
                try {
                    UserIdMapper userMapper =
                            UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(
                                    PortalPropertiesUtil.getSsoImplementation(),
                                    courseRecord.getSingleSignOnValue());
                    userName = UserLocalServiceUtil.getUser(userMapper.getUserId()).getFullName();
                }
                catch (Exception e) {
                    userName = courseRecord.getSingleSignOnValue() +
                               LanguageUtil.get(pageContext, "course-record-unregistered");
                }
            %>
            <%= userName %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-course'>
            <%
                String entry;
                try {
                    Course course = CourseLocalServiceUtil.findByCourseIri(courseRecord.getCourseIri());
                    String url = course.getUrl();
                    String title = course.getTitle(locale);
                    entry = "<a href='" + url + "'>" + title + "</a>";
                }
                catch (Exception e) {
                    entry = LanguageUtil.get(pageContext, "course-record-unregistered-course");
                }
            %>
            <%= entry %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-update-date'>
            <%= df.format(courseRecord.getUpdatedDate()) %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-completion-status'
                property="completionStatus"
                align="center" />

        <liferay-ui:search-container-column-text
                name='course-record-removed'
                align="center">
            <%= courseRecord.getRemoved() %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-user-hidden'
                align="center">
            <%= courseRecord.getUserHidden() %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-assigned'
                align="center">
            <%= courseRecord.getAssigned() %>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>
</liferay-ui:search-container>


<div class="separator"></div>
<h3><%=LanguageUtil.get(pageContext, "course-records-component-heading")%></h3>
<liferay-ui:search-container
        emptyResultsMessage="course-records-no-course-component-records"
        delta="10"
        curParam="curComponentRecords"
        deltaParam="deltaComonentRecords">
    <liferay-ui:search-container-results>
        <%
            total = ComponentRecordUtil.getComponentRecordCount();
            searchContainer.setTotal(total);

            results = ComponentRecordUtil.getComponentRecords(
                    searchContainer.getStart(), searchContainer.getResultEnd());
            searchContainer.setResults(results);

            pageContext.setAttribute("results", results);
            pageContext.setAttribute("total", total);
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="org.nterlearning.datamodel.catalog.model.ComponentRecord"
            keyProperty="componentRecordId"
            modelVar="componentRecord">

        <liferay-ui:search-container-column-text
                name='course-record-id'
                property="courseRecordId"
                align="center" />

        <liferay-ui:search-container-column-text
                name='course-record-component'>
            <%
                String entry;
                try {
                    Component component = ComponentLocalServiceUtil.findByComponentIri(componentRecord.getComponentIri());
                    String title = component.getTitle();
                    entry = title;
                }
                catch (Exception e) {
                    entry = LanguageUtil.get(pageContext, "course-record-unregistered-component");
                }
            %>
            <%= entry %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-update-date'>
            <%= df.format(componentRecord.getUpdatedDate()) %>
        </liferay-ui:search-container-column-text>

        <liferay-ui:search-container-column-text
                name='course-record-completion-status'
                property="completionStatus"
                align="center" />

        <liferay-ui:search-container-column-text
                name='course-record-completion-percent'
                align="center">
            <%= componentRecord.getCompletionPercent() + "%" %>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>
</liferay-ui:search-container>