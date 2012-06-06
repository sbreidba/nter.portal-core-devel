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
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

public class CourseCarouselValidator {

	public static boolean validateCourseCarouselPreferences(String coursesDisplayed,
			String carouselType, String showDetails, List<String> errors) {
		boolean valid = true;

		if (Validator.isNull(coursesDisplayed)) {
            _log.debug("Number of courses is missing");
			errors.add("courses-displayed-required");
			valid = false;
		}
		else if (!Validator.isNumber(coursesDisplayed)) {
            _log.debug("Number of courses is not a number: " + coursesDisplayed);
			errors.add("courses-displayed-invalid");
			valid = false;
		}
        else {
            try {
                CarouselConstants.CarouselType type = CarouselConstants.CarouselType.valueOf(carouselType);
                if (type == CarouselConstants.CarouselType.UNDEFINED) {
                    _log.debug("Carousel Type is undefined");
                    errors.add("carousel-type-required");
                    valid = false;
                }
            }
            catch (IllegalArgumentException e) {
                _log.error("Carousel Type " + carouselType + " is invalid");
                errors.add("carousel-type-invalid");
                valid = false;
            }
        }

		return valid;
	}

    private static Log _log = LogFactoryUtil.getLog(CourseCarouselValidator.class);
}