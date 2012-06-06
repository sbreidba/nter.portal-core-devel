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

<%@ include file="/course-listing/jsp/init.jsp" %>

<portlet:actionURL name="setPrefs" var="setPrefsUrl" />
<aui:form action="<%= setPrefsUrl.toString() %>" method="post">
	<aui:fieldset>
		<aui:input type="text" name="<%= ListingConstants.PREF_NUM_DISPLAYED %>"
				   value='<%= prefs.getValue(ListingConstants.PREF_NUM_DISPLAYED, defaultDisplayCount) %>' size="45" />

    <aui:select name="<%= ListingConstants.PREF_TYPE %>">
      <% ListingType listingType = ListingType.valueOf(prefs.getValue(
          ListingConstants.PREF_TYPE, ListingType.UNDEFINED.toString())); %>
      <aui:option value='<%= ListingType.UNDEFINED.toString() %>'
            selected='<%= listingType == ListingType.UNDEFINED %>'>
        <liferay-ui:message key="all-course-listing" />
      </aui:option>
      <aui:option value='<%= ListingType.NEW.toString() %>'
            selected='<%= listingType == ListingType.NEW %>'>
        <liferay-ui:message key="new-course-listing" />
      </aui:option>
      <aui:option value='<%= ListingType.POPULAR.toString() %>'
            selected='<%= listingType == ListingType.POPULAR %>'>
        <liferay-ui:message key="popular-course-listing" />
      </aui:option>
      <aui:option value='<%= ListingType.FEATURED.toString() %>'
            selected='<%= listingType == ListingType.FEATURED %>'>
        <liferay-ui:message key="featured-course-listing" />
      </aui:option>
    </aui:select>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>