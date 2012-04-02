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
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;

import java.util.List;

/**
 * Used for CourseRecord Custom Queries
 */
public class CourseRecordUtil {

	public static final String COURSE_RECORD_TABLE = "CATALOG_CourseRecord";

    private static DynamicQuery generateBaseQuery() {
        return DynamicQueryFactoryUtil.forClass(CourseRecord.class).
                add(PropertyFactoryUtil.forName("removed").eq(false));
    }

    private static DynamicQuery generateBaseFilteredQuery(long userId, boolean userHidden) {
        return generateBaseQuery().
                add(PropertyFactoryUtil.forName("userId").eq(userId)).
                add(PropertyFactoryUtil.forName("userHidden").eq(false));
    }

    public static int getCourseRecordsCount() {
        DynamicQuery query = generateBaseQuery();
        long total = 0;
        try {
            total = CourseRecordLocalServiceUtil.dynamicQueryCount(query);
        }
        catch (SystemException e) {
            _log.warn("Error processing dynamic query in CourseRecordUtil.getCourseRecordsCount", e);
        }

        if (total > Integer.MAX_VALUE) {
            _log.warn("dynamic query in CourseRecordUtil.getCourseRecordsCount returned more than " + Integer.MAX_VALUE + " results");
            return Integer.MAX_VALUE;
        }

        return (int)total;
    }

    @SuppressWarnings("unchecked")
	public static List<CourseRecord> getCourseRecords(int start, int end) {
        DynamicQuery query = generateBaseQuery().addOrder(OrderFactoryUtil.desc("updatedDate"));

        List<CourseRecord> results = null;
        try {
            results = (List<CourseRecord>)CourseRecordLocalServiceUtil.dynamicQuery(query, start, end);
        }
        catch (SystemException e) {
            _log.warn("Error processing dynamic query in CourseRecordUtil.getCourseRecords", e);
        }

        return results;
    }

    public static DynamicQuery getFilteredCourseRecordsQuery(long userId, boolean userHidden) {
        return generateBaseFilteredQuery(userId, userHidden).
                addOrder(OrderFactoryUtil.desc("updatedDate"));
    }

    public static DynamicQuery getFilteredCourseRecordsCount(long userId, boolean userHidden) {
        return generateBaseFilteredQuery(userId, userHidden);
    }

    public static DynamicQuery getFilteredCourseRecordsSortedQuery(long userId, boolean userHidden, String sortField, boolean asc){
    	DynamicQuery query = generateBaseFilteredQuery(userId, userHidden);
    	if (asc) {
    		query.addOrder(OrderFactoryUtil.asc(sortField));
    	}
        else {
    		query.addOrder(OrderFactoryUtil.desc(sortField));
    	}
    	return query;
    }

    private static final Log _log = LogFactoryUtil.getLog(CourseRecordUtil.class);
}