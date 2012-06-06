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


package org.nterlearning.course.listing.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.portlet.*;

public class CourseListingPortlet extends MVCPortlet {
    @Override
    public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute("defaultDisplayCount", getInitParameter("defaultDisplayCount"));
        super.render(request, response);
    }

    /**
	 * This Action sets the preferences for how many courses can be viewed per page.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setPrefs(ActionRequest request, ActionResponse response) throws Exception {
		String listingType = ParamUtil.getString(request, ListingConstants.PREF_TYPE);
		String coursesDisplayed = ParamUtil.getString(request, ListingConstants.PREF_NUM_DISPLAYED);

		ArrayList<String> errors = new ArrayList<String>();
		if (CourseListingValidator.validateCourseListingPreferences(coursesDisplayed, listingType, errors)) {
			PortletPreferences prefs = request.getPreferences();
			prefs.setValue(ListingConstants.PREF_NUM_DISPLAYED, coursesDisplayed);
			prefs.setValue(ListingConstants.PREF_TYPE, listingType);
            _log.debug("Setting preferences to: " + ListingConstants.PREF_NUM_DISPLAYED + 
            		"=" + coursesDisplayed + ", " + ListingConstants.PREF_TYPE +
                    "=" + listingType);
			prefs.store();

			response.setPortletMode(PortletMode.VIEW);
		}
		else {
			for (String error : errors) {
				SessionErrors.add(request, error);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseListingPortlet.class);
}