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

<%@ include file="init.jsp" %>

<%
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());

    long companyId = themeDisplay.getCompanyId();
    long ownerId = companyId ;
    int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

    PortletPreferences  mwPref = PortalPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType);

    String mwStart = mwPref.getValue(MaintenanceConstants.START_DATE_TIME_PROPERTY, "unknown");
    String mwDuration = mwPref.getValue(MaintenanceConstants.DURATION_PROPERTY, "unknown");
    String mwMessage = mwPref.getValue(MaintenanceConstants.MESSAGE_PROPERTY, "unknown");

    String maintMessage = null;

    Calendar now = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), themeDisplay.getLocale());
    Calendar maintCalStart = Calendar.getInstance(themeDisplay.getTimeZone(), themeDisplay.getLocale());
    Calendar maintCalEnd = Calendar.getInstance(themeDisplay.getTimeZone(), themeDisplay.getLocale());

    Date localMaintDate = new Date();

    if ((mwStart != null) && (!mwStart.equals("unknown"))) {
        localMaintDate.setTime(Long.valueOf(mwStart) + themeDisplay.getTimeZone().getRawOffset());
        maintCalStart.setTime(localMaintDate);

        maintCalEnd.setTime(localMaintDate);
        maintCalEnd.add(Calendar.HOUR, Integer.valueOf(mwDuration));

        if (now.after(maintCalStart) && now.before(maintCalEnd)) {
            maintMessage = mwMessage;
        }
    }
%>

<% if (maintMessage != null) { %>
    <div id="shut-down-notice"><%= maintMessage %></div>
<% } else { %>
	<!-- magical comment fix for IE7 giving height to empty div -->
<% } %>