/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
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


package org.nterlearning.motd;


import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import org.nterlearning.motd.utils.MaintenanceConstants;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;

public class MaintenanceProperties {

    public static PortletPreferences getPreferences(long companyId)
		throws SystemException {

		long ownerId = 0;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = PortletKeys.LIFERAY_PORTAL;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			companyId, ownerId, ownerType, plid, portletId);
	}


    public static void setStartDate(long companyId, long ownerId, int ownerType, String value)
        throws SystemException, ReadOnlyException, ValidatorException, IOException {
        // v 606
        // PortletPreferences prefs = getPreferences(companyId);

        PortletPreferences prefs = PortalPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType);
        prefs.setValue(MaintenanceConstants.START_DATE_TIME_PROPERTY, value);
        prefs.store();
    }


    public static void setDuration(long companyId, long ownerId, int ownerType, String value)
        throws SystemException, ReadOnlyException, ValidatorException, IOException {

        PortletPreferences prefs = PortalPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType);
        prefs.setValue(MaintenanceConstants.DURATION_PROPERTY, value);
        prefs.store();
    }


    public static void setMessage(long companyId, long ownerId, int ownerType, String value)
        throws SystemException, ReadOnlyException, ValidatorException, IOException {

        PortletPreferences prefs = PortalPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType);
        prefs.setValue(MaintenanceConstants.MESSAGE_PROPERTY, value);
        prefs.store();
    }
    

    public static void clear(long companyId, long ownerId, int ownerType )
        throws SystemException, ReadOnlyException, ValidatorException, IOException {

        PortletPreferences preferences = PortalPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType);
        preferences.setValue(MaintenanceConstants.START_DATE_TIME_PROPERTY, "");
        preferences.setValue(MaintenanceConstants.DURATION_PROPERTY, "");
        preferences.setValue(MaintenanceConstants.MESSAGE_PROPERTY, "");
        preferences.store();
    }
}
