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

<%@ attribute name="isPurchased" type="java.lang.Boolean" required="true" %>
<%@ attribute name="course" type="org.nterlearning.datamodel.catalog.model.Course" required="true" %>
<%@ attribute name="courseRecord" type="org.nterlearning.datamodel.catalog.model.CourseRecord" required="false" %>
<%@ attribute name="finishedComponentCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="activeComponentCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="resumeComponent" type="org.nterlearning.datamodel.catalog.model.Component" required="false" %>
<%@ attribute name="failedComponent" type="org.nterlearning.datamodel.catalog.model.Component" required="false" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<% // Course removed then do not display a button to start, pay, continue etc. %>

<% if (!course.isRemoved()) { %>

    <% // When student signed in check to see if course requires payment and check payment status
        if (themeDisplay.isSignedIn()) {

            if ((course.getPrice() == 0) || (course.getPrice() > 0 && isPurchased)) {

                // course is free or has been purchased.

                // assign a href
                 String resumeComponentHref = "";
                 String resumeComponentTitle = "";
                 if (resumeComponent != null) {
                    resumeComponentHref = resumeComponent.getHref();
                    resumeComponentTitle = resumeComponent.getTitle();
                 }

                 String sectionDescription = "";
                 Boolean popup = true;
                 if (courseRecord != null) {

                    // Must have progress to display next section on student desktop
                    String courseCompletionStatus = courseRecord.getCompletionStatus();

                    if (courseCompletionStatus.equals(CompletionStatusType.IN_PROGRESS.getDbValue())) {
                        if (activeComponentCount > 0 || finishedComponentCount > 0) {
                            //student has started course
                            sectionDescription = LanguageUtil.get(pageContext, "current-course-status-started");
                        }
                    } else if (courseCompletionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
                        if (failedComponent != null) {
                            resumeComponentHref = failedComponent.getHref();
                            resumeComponentTitle = failedComponent.getTitle();
                        }
                        sectionDescription = LanguageUtil.get(pageContext, "current-course-status-failed-retry");
                    } else if (courseCompletionStatus.equals(CompletionStatusType.FAILED.getDbValue()) ||
                           courseCompletionStatus.equals(CompletionStatusType.COMPLETED.getDbValue())) {
                          popup = false;
                    }

                     if (!sectionDescription.equals("")) { %>

                         <div class="course-attribute">
						    <dt><%= sectionDescription %>:</dt>
								   <dd><a href="<%= resumeComponentHref %>" <% if (popup) { %>target="_blank"<% } %>><%= resumeComponentTitle %></a></dd>
				         </div>

    <%               }
                } else {
                    //no courseRecord yet so display first resumeComponentHref
    %>
                         <div class="course-attribute">
						    <dt><%= sectionDescription %>:</dt>
								   <dd><a href="<%= resumeComponentHref %>" <% if (popup) { %>target="_blank"<% } %>><%= resumeComponentTitle %></a></dd>
				         </div>
    <%          } // courseRecordCheck

            }  //price check  do we need to check to see if free component exists to display?

       } //login check - do not display next section
    %>

<% }  // Course Removed - do not display next section  %>
	
	
			


