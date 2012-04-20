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

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import org.nterlearning.course.current.portlet.ComponentRecordUtil;
import org.nterlearning.course.enumerations.ComponentRecordFilterType;
import org.nterlearning.course.enumerations.ComponentRecordSortType;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl;
import org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl;

import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: plpeters
 * Date: Dec 20, 2010
 * Time: 9:00:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class ComponentRecordFinderImpl extends BasePersistenceImpl<ComponentRecord> implements ComponentRecordFinder {

    private static final String FIND_BY_COURSE_RECORD_ID_USER_ID_LANGUAGE_FILTER_SORTED = ComponentRecordFinder.class.getName() + ".findByCourseRecordIdUserIdLanguageFilterSorted";
    private static final String COUNT_BY_COURSE_RECORD_ID_USER_ID_LANGUAGE_FILTER = ComponentRecordFinder.class.getName() + ".countByCourseRecordIdUserIdLanguageFilter";

    @SuppressWarnings("unchecked")
    public List<Object[]> findByCourseRecordIdUserIdLanguageFilterSorted(long courseRecordId, long userId, Locale locale, String filterType, String sortType, boolean asc, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(FIND_BY_COURSE_RECORD_ID_USER_ID_LANGUAGE_FILTER_SORTED);
            StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            // assign additional WHERE criteria
            if (filterType != null) {                                                                    //to different type
                ComponentRecordFilterType componentRecordFilterType = ComponentRecordFilterType.valueOf(filterType);
                String whereSql = componentRecordFilterType.getWhereSql();
                if (whereSql != " ") {
                    builder.append(whereSql);
                }
            }

            //Validate order by is in enumeration list or use default
            builder.append(" ORDER BY ");
            if (sortType != null) {
                ComponentRecordSortType componentRecordSortType = ComponentRecordSortType.valueOf(sortType);
                String sortSql = componentRecordSortType.getSortSql();
                if (sortSql != " ") {
                    builder.append(sortSql);
                } else {
                    String defaultSortSql = ComponentRecordSortType.UPDATED_DATE.getSortSql();
                    builder.append(defaultSortSql);
                }
            } else {
                String defaultSortSql = ComponentRecordSortType.UPDATED_DATE.getSortSql();
                builder.append(defaultSortSql);
            }
            if (asc) {
                builder.append(" ASC");
            } else {
                builder.append(" DESC");
            }

            SQLQuery query = session.createSQLQuery(builder.toString());
            query.addEntity(ComponentRecordUtil.COMPONENT_RECORD_TABLE, ComponentRecordImpl.class);
            query.addEntity("CATALOG_Component", ComponentImpl.class);
            query.addEntity("CATALOG_Courses_Components", Courses_ComponentsImpl.class);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseRecordId);
            pos.add(userId);
            pos.add(locale.toString());
             _log.debug(builder);
            return (List<Object[]>) QueryUtil.list(query, getDialect(), start, end);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

    public long countByCourseRecordIdUserIdLanguageStatus(long courseRecordId, long userId, Locale locale, String filterType) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(COUNT_BY_COURSE_RECORD_ID_USER_ID_LANGUAGE_FILTER);
            StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            // assign additional WHERE criteria
            if (filterType != null) {
                ComponentRecordFilterType componentRecordFilterType = ComponentRecordFilterType.valueOf(filterType);
                String whereSql = componentRecordFilterType.getWhereSql();
                if (whereSql != " ") {
                    builder.append(whereSql);
                }
            }

            SQLQuery query = session.createSQLQuery(builder.toString());
            query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseRecordId);
            pos.add(userId);
            pos.add(locale.toString());
            @SuppressWarnings("unchecked")
            List<Long> list = (List<Long>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            return list.get(0);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(ComponentRecordFinderUtil.class);
}