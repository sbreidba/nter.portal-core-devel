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
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.theme.ThemeDisplay;
import org.nterlearning.course.util.NterKeys;

import javax.portlet.PortletURL;
import java.awt.*;

public class ComponentOpenSearchImpl extends HitsOpenSearchImpl {

    public static final String SEARCH_PATH = "/c/component/open_search";
    public static final String TITLE = "NTER Component Search";

    @Override
    public Indexer getIndexer() {
        return IndexerRegistryUtil.getIndexer(Component.class);
    }


    @Override
    public String getPortletId() {
        return NterKeys.COMPONENT_SEARCH_PORTLET;
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

    public static boolean isSearchAuthorized() {
        return isSearchAuthorized(PermissionThreadLocal.getPermissionChecker());
    }

    public static boolean isSearchAuthorized(PermissionChecker permissionChecker) {
        return permissionChecker.hasPermission(0, NterKeys.COMPONENT_SEARCH_PORTLET,
                permissionChecker.getCompanyId(), ActionKeys.VIEW);
    }
}
