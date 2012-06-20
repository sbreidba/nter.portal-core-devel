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


package org.nterlearning.course.carousel.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;

/**
 *
 * Display courses in a carousel format
 *
 */
public class CourseCarouselPortlet extends MVCPortlet {

	/**
	 * This Action sets the preferences for the carousel.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setCarouselPrefs(ActionRequest request, ActionResponse response) throws Exception {
		String coursesDisplayed = ParamUtil.getString(request, CarouselConstants.PREF_NUM_DISPLAYED);
		String carouselType = ParamUtil.getString(request, CarouselConstants.PREF_TYPE);
		String showDetails = ParamUtil.getString(request, CarouselConstants.PREF_DETAILS);

		ArrayList<String> errors = new ArrayList<String>();
		if (CourseCarouselValidator.validateCourseCarouselPreferences(
				coursesDisplayed, carouselType, showDetails, errors)) {
			PortletPreferences prefs = request.getPreferences();
			prefs.setValue(CarouselConstants.PREF_NUM_DISPLAYED, coursesDisplayed);
			prefs.setValue(CarouselConstants.PREF_TYPE, carouselType);
			prefs.setValue(CarouselConstants.PREF_DETAILS, showDetails);
            _log.debug("Setting preferences to: " + CarouselConstants.PREF_NUM_DISPLAYED +
                    "=" + coursesDisplayed + ", " + CarouselConstants.PREF_TYPE +
                    "=" + carouselType + ", " + CarouselConstants.PREF_DETAILS +
                    "=" + showDetails);
			prefs.store();

			response.setPortletMode(PortletMode.VIEW);
		}
		else {
			for (String error : errors) {
				SessionErrors.add(request, error);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseCarouselPortlet.class);
}