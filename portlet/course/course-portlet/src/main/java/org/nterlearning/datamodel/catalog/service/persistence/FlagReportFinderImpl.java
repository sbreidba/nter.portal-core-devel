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


package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;
import org.nterlearning.course.enumerations.FlagReportFilterType;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportImpl;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportStatsImpl;

import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: plpeters
 * Date: Dec 22, 2010
 * Time: 11:19:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class FlagReportFinderImpl extends BasePersistenceImpl<FlagReport> implements FlagReportFinder {

    private static final String localCourseReviewQuery = FlagReportFinder.class.getName() + ".localCourseReview";
    private static final String globalCourseReviewQuery = FlagReportFinder.class.getName() + ".globalCourseReview";

    @SuppressWarnings("unchecked")
    public List<Object[]> findByClassNameIdAndFilter(long classNameId, String filterType, int start, int end) throws SystemException {
        Session session = null;
        String sql = "";
        try {
            session = openSession();
            if (classNameId == ClassNameLocalServiceUtil.getClassNameId(GlobalCourseReview.class)) {
                sql = CustomSQLUtil.get(globalCourseReviewQuery);
            } else {
                 sql = CustomSQLUtil.get(localCourseReviewQuery);
            }

            StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            // assign additional criteria if filterType provided
            // ALL, IN_PROGRESS_STATUS, FINISHED_STATUS
            if (filterType != null) {
                FlagReportFilterType flagReportFilterType = FlagReportFilterType.valueOf(filterType);
                String whereSql = flagReportFilterType.getWhereSql();
                if (whereSql != " ") {
                    builder.append(whereSql);
                }
            }

            SQLQuery query = session.createSQLQuery(builder.toString());
            query.addEntity("CATALOG_FlagReportStats", FlagReportStatsImpl.class);
            query.addEntity("CATALOG_FlagReport", FlagReportImpl.class);
            query.addScalar("CONTENT_TYPE", Type.STRING);
            query.addScalar("USER_DISPLAY", Type.STRING);
            query.addScalar("MOD_DATE", Type.DATE);

            QueryPos pos = QueryPos.getInstance(query);
            pos.add(classNameId);

            return (List<Object[]>) QueryUtil.list(query, getDialect(), start, end);
        }catch (ORMException e){
            throw new SystemException(e.getMessage());
        }finally{
            closeSession(session);
        }
    }

}