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

<%-- course-next-section.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ tag import="org.nterlearning.course.enumerations.*" %>

<%@ attribute name="course" type="org.nterlearning.datamodel.catalog.model.Course" required="true" %>
<%@ attribute name="courseRecord" type="org.nterlearning.datamodel.catalog.model.CourseRecord" required="false" %>
<%@ attribute name="finishedComponentCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="activeComponentCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>
<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<% // Course removed then do not display a button to start, pay, continue etc. %>

<% if (!course.isRemoved()) { %>

    <% // When student signed in check to see if course progress exists for course.
        if (themeDisplay.isSignedIn()) {

                String lastStatusDate = "";
                String completedStatusMsg = "";
                if (courseRecord != null) {

                    // Must have progress to display next section on student desktop
                    String courseCompletionStatus = courseRecord.getCompletionStatus();

                    if (courseCompletionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue())) {
                        if (activeComponentCount > 0 || finishedComponentCount > 0) {
                            //student has started course
                            lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-visited", courseRecord.getFriendlyUpdatedDate(pageContext));
						} else {
							//student might be assigned but not started
							if (courseRecord.getAssigned()) {
								lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-assigned", courseRecord.getFriendlyUpdatedDate(pageContext));
							} else {
							    // student self-enrolled but not started
								lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-enrolled", courseRecord.getFriendlyUpdatedDate(pageContext));
							}
                        }
                    } else if (courseCompletionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
                        lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-failed", courseRecord.getFriendlyUpdatedDate(pageContext));
                    } else if (courseCompletionStatus.equals(CompletionStatusType.FAILED.getDbValue())) {
                        lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-failed", courseRecord.getFriendlyUpdatedDate(pageContext));
                    } else if (courseCompletionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) {
                        lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-completed", courseRecord.getFriendlyUpdatedDate(pageContext));
                    } else {
                        lastStatusDate = LanguageUtil.format(pageContext, "last-status-date-unknown", courseRecord.getFriendlyUpdatedDate(pageContext));
                        completedStatusMsg = LanguageUtil.get(pageContext, "current-course-status-unknown");
                    }
    %>
                    <div class="visited"><%= lastStatusDate %></div>

                    <% if (!completedStatusMsg.equals("")) { %>
                        <div class="completed-msg"><%= completedStatusMsg %></div>
                    <% }
                }  //courseRecord check - do not display if no progress reported

     } //login check - do not display if not logged in%>

<% }  // Course Removed - do not display if course removed  %>

	
	
			


