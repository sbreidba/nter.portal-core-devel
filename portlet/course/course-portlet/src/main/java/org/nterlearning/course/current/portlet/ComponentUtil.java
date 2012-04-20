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


package org.nterlearning.course.current.portlet;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;

import java.util.List;

/**
 * Used for Component Custom Queries
 */
public class ComponentUtil {
    public static final String COMPONENT_TABLE = "CATALOG_Component";
    public static final String COMPONENT_TITLE = COMPONENT_TABLE + ".title";
    public static final String UPDATED_DATE = COMPONENT_TABLE + ".updatedDate";
    public static final String COMPONENT_IRI = COMPONENT_TABLE + ".componentIri";

    private static DynamicQuery generateBaseQuery() {
        return DynamicQueryFactoryUtil.forClass(Component.class);
    }

    public static int getComponentCount() {
        DynamicQuery query = generateBaseQuery();
        long total = 0;
        try {
            total = ComponentLocalServiceUtil.dynamicQueryCount(query);
        }
        catch (SystemException e) {
            _log.warn("Error processing dynamic query in ComponentUtil.getComponentCount");
        }
        return (int) total;
    }

    @SuppressWarnings("unchecked")
    public static List<Component> getComponents(int start, int end) {
        DynamicQuery query = generateBaseQuery();

        List<Component> results=null;
        try {
            results = (List<Component>) ComponentLocalServiceUtil.dynamicQuery(query, start, end);
        }
        catch (SystemException e) {
            _log.warn("Error processing dynamic query in ComponentUtil.getComponents", e);
        }
        return results;
    }

    private static final Log _log = LogFactoryUtil.getLog(ComponentUtil.class);
}