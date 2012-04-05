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

<%@ include file="../init.jsp" %>

<portlet:actionURL name="submitMaintenance" var="submitMaintenanceUrl"/>
<portlet:actionURL name="clearMaintenance" var="clearMaintenanceUrl" />

<%
    Calendar defaultDate = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), themeDisplay.getLocale());
    Date startDate = new Date();

    long companyId = themeDisplay.getCompanyId();
    long ownerId = companyId ;
    int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

    PortletPreferences  mwPref = PortalPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType);

    String mwStart = mwPref.getValue(MaintenanceConstants.START_DATE_TIME_PROPERTY, "unknown");
    String mwDuration = mwPref.getValue(MaintenanceConstants.DURATION_PROPERTY, "unknown");
    String mwMessage = mwPref.getValue(MaintenanceConstants.MESSAGE_PROPERTY, "unknown");

    String maintenanceSchedule = LanguageUtil.get(pageContext, "mw-no-messages");

    if ((mwStart != null) && (!mwStart.equals("unknown"))) {
        startDate.setTime(Long.valueOf(mwStart));
        maintenanceSchedule = "Start Time: " + startDate.toString() + " for " + mwDuration +
                        " hour(s), stating that: \"" + mwMessage + "\"";
    }
%>

<h3><%= LanguageUtil.get(pageContext, "mw-create-new-warning") %></h3>
<aui:form action="<%= submitMaintenanceUrl.toString() %>"
          method="post">
    <aui:field-wrapper label='mw-start-date-time'>
        <liferay-ui:input-date
                dayParam="startDay"
                dayValue="<%= defaultDate.get(Calendar.DATE) %>"
                monthParam="startMonth"
                monthValue="<%= defaultDate.get(Calendar.MONTH) %>"
                yearParam="startYear"
                yearValue="<%= defaultDate.get(Calendar.YEAR) %>"
                yearRangeStart="<%= defaultDate.get(Calendar.YEAR) %>"
                yearRangeEnd="<%= defaultDate.get(Calendar.YEAR) + 50 %>"
                firstDayOfWeek="<%= defaultDate.getFirstDayOfWeek() - 1 %>"
                disabled="<%= false %>" />

        <liferay-ui:input-time
                hourParam="startHour"
                hourValue="<%= defaultDate.get(Calendar.HOUR) %>"
                minuteParam="startMinute"
                minuteValue="<%= defaultDate.get(Calendar.MINUTE) %>"
                minuteInterval="<%= 15 %>"
                amPmParam="startAmPm"
                amPmValue="<%= defaultDate.get(Calendar.AM_PM)%>" />
        &nbsp;&nbsp;
        (<%= defaultDate.getTimeZone().getDisplayName() %>)
    </aui:field-wrapper>

    <aui:input name="duration" label='mw-duration' size="20"/>
    <aui:input name="message" label='mw-message' size="45"/>

    <aui:button-row>
        <aui:button type="submit" value='mw-submit-button'/>
        <aui:button type="cancel" value='mw-clear-button' onClick="<%= clearMaintenanceUrl.toString() %>"/>
    </aui:button-row>
</aui:form>

<div class="separator"></div>
<h3><%= LanguageUtil.get(pageContext, "mw-current-warning") %></h3>
<div>
    <%= maintenanceSchedule %>
</div>


