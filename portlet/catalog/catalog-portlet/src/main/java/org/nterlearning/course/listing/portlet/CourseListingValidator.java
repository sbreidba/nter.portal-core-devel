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

import java.util.List;

public class CourseListingValidator {

	public static boolean validateCourseListingPreferences(String coursesDisplayed, String listingType, List<String> errors) {
		boolean valid = true;

		if (com.liferay.portal.kernel.util.Validator.isNull(coursesDisplayed)) {
            _log.debug("Number of courses is missing");
			errors.add("courses-displayed-required");
			valid = false;
		} else if (!com.liferay.portal.kernel.util.Validator.isNumber(coursesDisplayed)) {
            _log.debug("Number of courses is not a number: " + coursesDisplayed);
			errors.add("courses-displayed-invalid");
			valid = false;
		} else {
            try {
                ListingConstants.ListingType type = ListingConstants.ListingType.valueOf(listingType);
                if (type == ListingConstants.ListingType.UNDEFINED) {
                    _log.debug("Listing Type is undefined");
                    errors.add("listing-type-required");
                    valid = false;
                }
            }
            catch (IllegalArgumentException e) {
                _log.error("Listing Type " + listingType + " is invalid");
                errors.add("listing-type-invalid");
                valid = false;
            }
		}

		return valid;
	}

    private static Log _log = LogFactoryUtil.getLog(CourseListingValidator.class);
}