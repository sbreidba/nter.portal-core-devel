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
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import org.nterlearning.course.current.portlet.CourseRecordUtil;
import org.nterlearning.course.enumerations.CourseRecordFilterType;
import org.nterlearning.course.enumerations.CourseRecordSortType;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl;

import java.util.List;
public class CourseRecordFinderImpl extends BasePersistenceImpl<CourseRecord> implements CourseRecordFinder{

	private static final String FIND_BY_USER_ID_FILTER_SORTED = CourseRecordFinder.class.getName() + ".findByUserIdFilterSorted";
	private static final String COUNT_BY_USER_ID_FILTER = CourseRecordFinder.class.getName() + ".countByUserIdFilter";
    private static final String FIND_BY_USER_ID_LANGUAGE_WITH_COMPONENTS_SORTED = CourseRecordFinder.class.getName() + ".findByUserIdLanguageWithComponentsSorted";
    private static final String COUNT_BY_USER_ID_LANGUAGE_WITH_COMPONENTS = CourseRecordFinder.class.getName() + ".countByUserIdLanguageWithComponents";
    private static final String COUNT_ACCESSED_BY_COURSE_IRI = CourseRecordFinder.class.getName() + ".countAccessedByCourseIri";
    private static final String COUNT_COMPLETED_BY_COURSE_IRI = CourseRecordFinder.class.getName() + ".countCompletedByCourseIri";

	@SuppressWarnings("unchecked")
	public List<Object[]> findByUserIdFilterSorted(long userId, long courseId, String filterType, String sortType, boolean asc, int start, int end) throws SystemException {
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_BY_USER_ID_FILTER_SORTED);
			StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            // assign additional WHERE criteria
            if (filterType != null) {
                CourseRecordFilterType courseRecordFilterType = CourseRecordFilterType.valueOf(filterType);
                String whereSql = courseRecordFilterType.getWhereSql();
                if (whereSql != " ") {
                    builder.append(whereSql);
                }
            }

            //Validate order by is in enumeration list or use default
            builder.append(" ORDER BY ");
            if (sortType != null) {
                CourseRecordSortType courseRecordSortType = CourseRecordSortType.valueOf(sortType);
                String sortSql = courseRecordSortType.getSortSql();
                if (sortSql != " ") {
                    builder.append(sortSql);
                } else {
                    String defaultSortSql = CourseRecordSortType.UPDATED_DATE.getSortSql();
                    builder.append(defaultSortSql);
                }
            } else {
                String defaultSortSql = CourseRecordSortType.UPDATED_DATE.getSortSql();
                builder.append(defaultSortSql);
            }
            if (asc) {
                builder.append(" ASC");
            } else {
                builder.append(" DESC");
            }

            SQLQuery query = session.createSQLQuery(builder.toString());
			query.addEntity(CourseRecordUtil.COURSE_RECORD_TABLE, CourseRecordImpl.class);
			query.addEntity("CATALOG_Course", CourseImpl.class);
			query.addScalar("RATING", Type.DOUBLE);
			QueryPos pos = QueryPos.getInstance(query);
            pos.add(ClassNameLocalServiceUtil.getClassNameId(Course.class));
			pos.add(userId);
            if (filterType != null && filterType.equals(CourseRecordFilterType.SPECIFIC_COURSE.toString())) {
                pos.add(courseId);
            }
            _log.debug(builder);
            return (List<Object[]>) QueryUtil.list(query, getDialect(), start, end);
		}catch (ORMException e){
			throw new SystemException(e.getMessage());
		}finally{
			closeSession(session);
		}
	}

	public long countByUserIdFilter(long userId, long courseId, String filterType) throws SystemException{
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(COUNT_BY_USER_ID_FILTER);
			StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            // assign additional WHERE criteria
            if (filterType != null) {
                CourseRecordFilterType courseRecordFilterType = CourseRecordFilterType.valueOf(filterType);
                String whereSql = courseRecordFilterType.getWhereSql();
                if (whereSql != " ") {
                    builder.append(whereSql);
                }
            }

			SQLQuery query = session.createSQLQuery(builder.toString());
			query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			QueryPos pos = QueryPos.getInstance(query);
            pos.add(ClassNameLocalServiceUtil.getClassNameId(Course.class));
 			pos.add(userId);
            if (filterType != null && filterType.equals(CourseRecordFilterType.SPECIFIC_COURSE.toString())) {
                pos.add(courseId);
            }
            _log.debug(builder);
			@SuppressWarnings("unchecked")
            List<Long> list = (List<Long>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			return list.get(0);
		}catch (ORMException e){
			throw new SystemException(e.getMessage());
		}finally{
			closeSession(session);
		}
	}

    public long countAccessedByCourseIri(String courseIri) throws SystemException {
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(COUNT_ACCESSED_BY_COURSE_IRI);
			StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));
			SQLQuery query = session.createSQLQuery(builder.toString());
			query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseIri);
			@SuppressWarnings("unchecked")
			List<Long> list = (List<Long>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			return list.get(0);
		}catch (ORMException e){
			throw new SystemException(e.getMessage());
		}finally{
			closeSession(session);
		}
    }

        public long countCompletedByCourseIri(String courseIri) throws SystemException {
 		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(COUNT_COMPLETED_BY_COURSE_IRI);
			StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));
			SQLQuery query = session.createSQLQuery(builder.toString());
			query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseIri);
			@SuppressWarnings("unchecked")
			List<Long> list = (List<Long>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			return list.get(0);
		}catch (ORMException e){
			throw new SystemException(e.getMessage());
		}finally{
			closeSession(session);
		}
    }

    private static final Log _log = LogFactoryUtil.getLog(CourseRecordFinderUtil.class);
}