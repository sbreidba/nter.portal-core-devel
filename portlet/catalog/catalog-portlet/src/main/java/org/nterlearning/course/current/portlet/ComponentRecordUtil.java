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
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;

import java.util.List;

/**
 * Used for ComponentRecord Custom Query
 */
public class ComponentRecordUtil {
    public static final String COMPONENT_RECORD_TABLE = "CATALOG_ComponentRecord";

    private static DynamicQuery generateBaseQuery() {
        return DynamicQueryFactoryUtil.forClass(ComponentRecord.class);
    }

    public static int getComponentRecordCount() {
        DynamicQuery query = generateBaseQuery();
        long total = 0;
        try {
            total = ComponentRecordLocalServiceUtil.dynamicQueryCount(query);
        }
        catch (SystemException e) {
            _log.warn("Error processing dynamic query in ComponentRecordUtil.getComponentRecordsCount", e);
        }

        if (total > Integer.MAX_VALUE) {
            _log.warn("dynamic query in ComponentRecordUtil.getComponentRecordsCount returned more than " + Integer.MAX_VALUE + " results");
            return Integer.MAX_VALUE;
        }

        return (int) total;
    }

    @SuppressWarnings("unchecked")
    public static List<ComponentRecord> getComponentRecords(int start, int end) {
        DynamicQuery query = generateBaseQuery().addOrder(OrderFactoryUtil.desc("updatedDate"));

        List<ComponentRecord> results = null;
        try {
            results = (List<ComponentRecord>) ComponentRecordLocalServiceUtil.dynamicQuery(query, start, end);
        }
        catch (SystemException e) {
            _log.warn("Error processing dynamic query in ComponentRecordUtil.getComponentRecords", e);
        }
        return results;
    }

    private static final Log _log = LogFactoryUtil.getLog(CourseRecordUtil.class);
}