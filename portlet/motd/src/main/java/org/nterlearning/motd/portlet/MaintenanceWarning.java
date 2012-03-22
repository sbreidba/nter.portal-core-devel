/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.nterlearning.motd.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import org.nterlearning.motd.MaintenanceProperties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class is implemented by the Maintenance Warning Control Panel portlet
 */
public class MaintenanceWarning extends MVCPortlet {


    public void submitMaintenance(ActionRequest request, ActionResponse response)
            throws Exception {

        ThemeDisplay theme = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        String day = request.getParameter("startDay");
        String month = request.getParameter("startMonth");
        String year = request.getParameter("startYear");

        String hour = request.getParameter("startHour");
        String minutes = request.getParameter("startMinute");
        String ampm = request.getParameter("startAmPm");

        String duration = request.getParameter("duration");
        String message = request.getParameter("message");
        TimeZone timeZone = theme.getTimeZone();

        boolean errorFlag = false;

        // perform sanity checks
        if (duration.equals("")) {
            errorFlag = true;
            SessionErrors.add(request, "mw-error-no-duration");
        }
        else if (Integer.valueOf(duration) <= 0) {
            errorFlag = true;
            SessionErrors.add(request, "mw-error-invalid-duration");
        }

        if (message.equals("")) {
            errorFlag = true;
            SessionErrors.add(request, "mw-error-no-message");
        }

        if (errorFlag) {
            return;
        }

        // ensure all units have correct format
        Integer monthVal = Integer.valueOf(month) + 1;
        month = (monthVal < 10) ? "0" + monthVal.toString() : monthVal.toString();
        day = (Integer.valueOf(day) < 10) ? "0" + day : day;
        hour = (Integer.valueOf(hour) < 10) ? "0" + hour : hour;
        minutes = (Integer.valueOf(minutes) < 10) ? "0" + minutes : minutes;
        ampm = (ampm.equals("0")) ? "AM" : "PM";

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a z");

        String date_time = month + "/" + day + "/" + year + " " + hour + ":" + minutes + " " + ampm + " " + timeZone.getDisplayName();
        Date maintDate = df.parse(date_time);
        maintDate.setTime(maintDate.getTime() - timeZone.getRawOffset());
        long companyId = theme.getCompanyId();
        long ownerId = companyId ;
        int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

        MaintenanceProperties.setStartDate(theme.getCompanyId(), ownerId, ownerType, String.valueOf(maintDate.getTime()));
        MaintenanceProperties.setDuration(theme.getCompanyId(), ownerId, ownerType, duration);
        MaintenanceProperties.setMessage(theme.getCompanyId(), ownerId, ownerType, message);
    }


    public void clearMaintenance(ActionRequest request, ActionResponse response)
            throws Exception {

        ThemeDisplay theme = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        long companyId = theme.getCompanyId();
        long ownerId = companyId ;
        int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

        MaintenanceProperties.clear(theme.getCompanyId(), ownerId, ownerType);
    }

}
