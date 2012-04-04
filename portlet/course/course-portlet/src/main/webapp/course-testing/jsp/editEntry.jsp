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

<%-- course-testing/jsp/editEntry.jsp --%>

<%@include file="/course-testing/jsp/init.jsp" %>

<%
long courseId = ParamUtil.getLong(request, "resourcePrimKey");
List<AssetTag> curTags = AssetTagLocalServiceUtil.getEntryTags(courseId);
Course course = CourseLocalServiceUtil.getCourse(courseId);
%>

<portlet:actionURL var="updateURL" name="updateCourse">
</portlet:actionURL>
<aui:form action="<%= updateURL %>" method="post">
    <aui:fieldset>
	    <aui:input name="resourcePrimaryKey" type="hidden" value="<%= courseId %>"></aui:input>
	    <aui:input name="title" value="<%=course.getTitle(locale) %>"></aui:input>
	    <aui:input name="description" value="<%=course.getDescription(locale) %>"></aui:input>
        <!-- TODO: date fields    -->
	    <!-- aui:input name="releaseOnDate" value="<%=course.getReleaseOnDate() %>" type="Date" / -->
	    <!-- aui:input name="removedDate" value="<%=course.getRemovedDate() %>" type="Date" / -->
	    <aui:field-wrapper label="Categories">
	           <liferay-ui:asset-categories-selector className="<%=Course.class.getName() %>" classPK="<%=courseId %>" hiddenInput="categories"></liferay-ui:asset-categories-selector>
	    </aui:field-wrapper>
	    <aui:field-wrapper name="Tags">
	           <liferay-ui:asset-tags-selector className="<%= Course.class.getName() %>" classPK="<%= courseId %>" hiddenInput="tags"></liferay-ui:asset-tags-selector>
	    </aui:field-wrapper>
	    <aui:button-row>
	        <aui:button name="saveButton" type="submit" value="save"  />
	        <%
	        String backURL = ParamUtil.getString(request, "redirect", "location.href='';");
	        %>
	        <aui:button name="cancelButton" type="cancel" last="true" onClick="<%=backURL %>" />
	    </aui:button-row>
    </aui:fieldset>
</aui:form>