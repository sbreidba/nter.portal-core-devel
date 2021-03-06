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

package org.nterlearning.datamodel.catalog.model.impl;

/**
 * The extended model implementation for the CourseImage service. Represents a row in the &quot;CATALOG_CourseImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.model.CourseImage} interface.
 * </p>
 *
 */

import com.liferay.portal.theme.ThemeDisplay;

import org.nterlearning.datamodel.catalog.model.CourseImage;

public class CourseImageImpl extends CourseImageBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a course image model instance should use the {@link org.nterlearning.datamodel.catalog.model.CourseImage} interface instead.
     */
    public CourseImageImpl() {
    }

	public String getLargeImageUrl(ThemeDisplay themeDisplay) {
		String url = getSourceImageUrl();
		if (!url.isEmpty()) {
			return url;
		}

		return themeDisplay.getPathThemeImages() + "/default_images/course_default_large.jpg";
	}

    public String getMediumImageUrl(ThemeDisplay themeDisplay) {
		String url = getSourceImageUrl();
		if (!url.isEmpty()) {
			return url;
		}

		return themeDisplay.getPathThemeImages() + "/default_images/course_default_medium.jpg";
	}

	public String getSmallImageUrl(ThemeDisplay themeDisplay) {
		String url = getSourceImageUrl();
		if (!url.isEmpty()) {
			return url;
		}

		return themeDisplay.getPathThemeImages() + "/default_images/course_default_small.jpg";
	}
}