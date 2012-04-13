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


package org.nterlearning.course.search;

import com.liferay.portal.kernel.search.*;
import com.liferay.portal.theme.ThemeDisplay;

import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.model.Course;

import javax.portlet.PortletURL;

/**
 * @author Brian Blonski
 */
public class CourseOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String SEARCH_PATH = "/c/course/open_search";
	public static final String TITLE = "Liferay Course Search";

    @Override
    public Indexer getIndexer() {
         return IndexerRegistryUtil.getIndexer(Course.class);
    }

	@Override
	public String getPortletId() {
		return NterKeys.COURSE_SEARCH_PORTLET;
	}

	@Override
	public String getSearchPath() {
		return SEARCH_PATH;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

	@Override
	protected String getURL(ThemeDisplay themeDisplay, long groupId,
                            Document result, PortletURL portletURL)
		    throws Exception {
		return result.get(Field.URL);
	}
}