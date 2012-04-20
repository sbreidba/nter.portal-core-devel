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

<%@include file="/course-carousel/jsp/init.jsp" %>

<portlet:actionURL name="setCarouselPrefs" var="setCarouselPrefs" />
<aui:form action="<%= setCarouselPrefs.toString() %>" method="post">
	<aui:fieldset>
		<aui:input type="text" name="<%= CarouselConstants.PREF_NUM_DISPLAYED %>"
				   value='<%= prefs.getValue(CarouselConstants.PREF_NUM_DISPLAYED, "16") %>' size="45" />
		<aui:select name="<%= CarouselConstants.PREF_TYPE %>">
			<% CarouselType carouselType = CarouselType.valueOf(prefs.getValue(
					CarouselConstants.PREF_TYPE, CarouselType.UNDEFINED.toString())); %>
			<aui:option value='<%= CarouselType.UNDEFINED.toString() %>'
						selected='<%= carouselType == CarouselType.UNDEFINED %>'>
				<liferay-ui:message key="undefined-course-carousel" />
			</aui:option>
			<aui:option value='<%= CarouselType.NEW.toString() %>'
						selected='<%= carouselType == CarouselType.NEW %>'>
				<liferay-ui:message key="new-course-carousel" />
			</aui:option>
			<aui:option value='<%= CarouselType.POPULAR.toString() %>'
						selected='<%= carouselType == CarouselType.POPULAR %>'>
				<liferay-ui:message key="popular-course-carousel" />
			</aui:option>
			<aui:option value='<%= CarouselType.FEATURED.toString() %>'
						selected='<%= carouselType == CarouselType.FEATURED %>'>
				<liferay-ui:message key="featured-course-carousel" />
			</aui:option>
		</aui:select>
		<aui:input type="checkbox" name="<%= CarouselConstants.PREF_DETAILS %>"
				   value='<%= prefs.getValue(CarouselConstants.PREF_DETAILS, "false") %>' />
		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>