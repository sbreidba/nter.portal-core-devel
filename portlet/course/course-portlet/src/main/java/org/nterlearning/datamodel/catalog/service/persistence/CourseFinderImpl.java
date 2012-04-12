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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.util.dao.orm.CustomSQLUtil;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;

import java.util.Date;
import java.util.List;

/**
 * CourseFinderImpl executes custom SQL queries agains the Course database table.
 */
public class CourseFinderImpl extends BasePersistenceImpl<Course> implements CourseFinder {

	private static final String FIND = CourseFinder.class.getName() + ".find";
	private static final String COUNT = CourseFinder.class.getName() + ".count";
    private static final String FROM_COURSE = CourseFinder.class.getName() + ".fromCourse";
    private static final String AND_ASSET = CourseFinder.class.getName() + ".andAsset";
    private static final String WHERE_VALID = CourseFinder.class.getName() + ".whereValid";
    private static final String AND_WHERE_ASSET = CourseFinder.class.getName() + ".andWhereAsset";


	public List<Course> findAllValidCourses(String filterSQL, String sortSQL, AssetCategory category,
			long vocabularyId, long groupId, int start, int end) throws SystemException {
		Session session = null;
		try {
			session = openSession();
			String completeSql = buildQuery(filterSQL, sortSQL, category, vocabularyId, groupId, false);
			_log.debug(completeSql);
			SQLQuery query = session.createSQLQuery(completeSql);
            query.addEntity("CATALOG_Course", CourseImpl.class);
			setQueryParameters(category, vocabularyId, groupId, query);
			_log.debug(query);

			return (List<Course>) QueryUtil.list(query, getDialect(), start, end);
		} catch (ORMException e) {
			throw new SystemException(e.getMessage());
		} finally {
			closeSession(session);
		}
	}

    public long countAllValidCourses(String filterSQL, AssetCategory category,
                                            long vocabularyId, long groupId) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String completeSql = buildQuery(filterSQL, null, category, vocabularyId, groupId, true);
            _log.debug(completeSql);
            SQLQuery query = session.createSQLQuery(completeSql);
            query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
            setQueryParameters(category, vocabularyId, groupId, query);
            _log.debug(query);
            @SuppressWarnings("unchecked")
            List<Long> list = (List<Long>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            return list.get(0);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

	private void setQueryParameters(AssetCategory category, long vocabularyId, long groupId, SQLQuery query) {
		QueryPos pos = QueryPos.getInstance(query);
		Date today = new Date();
		pos.add(today);
		pos.add(today);
		if (groupId > 0) {
			pos.add(groupId);
		}
		if(category != null || vocabularyId > 0) {
			pos.add(ClassNameLocalServiceUtil.getClassNameId(Course.class));
			if(category != null) {
				pos.add(category.getLeftCategoryId());
				pos.add(category.getRightCategoryId());
				pos.add(category.getVocabularyId());
			}else if(vocabularyId > 0) {
				pos.add(vocabularyId);
			}
		}
	}

	private String buildQuery(String filterSQL, String sortSQL, AssetCategory category,
			long vocabularyId, long groupId, boolean isCountQuery) {

        StringBuilder builder = new StringBuilder();
        if(isCountQuery){
            builder.append(CustomSQLUtil.get(COUNT));
        } else {
            builder.append(CustomSQLUtil.get(FIND));
        }
        builder.append(CustomSQLUtil.get(FROM_COURSE));
        boolean withAsset = (category != null || vocabularyId > 0);
        if(withAsset) {
            builder.append(CustomSQLUtil.get(AND_ASSET));
        }
        builder.append(CustomSQLUtil.get(WHERE_VALID));
        if(withAsset) {
            builder.append(CustomSQLUtil.get(AND_WHERE_ASSET));
        }

		// assign additional WHERE criteria
		if (filterSQL != null) {
			builder.append(filterSQL);
		}
		if (groupId > 0) {
			builder.append(" AND CATALOG_Course.groupId = ?");
		}
		if (withAsset) {
			builder.append(" AND AssetEntries_AssetCategories.categoryId IN ");
			builder.append("(SELECT categoryId FROM AssetCategory where ");
			if(category != null) {
				builder.append("leftCategoryId BETWEEN  ? AND ? and vocabularyId = ?)");
			}else {
				builder.append("vocabularyId = ?)");
			}
		}

		//Validate order by is in enumeration list or use default
        if (sortSQL != null) {
            builder.append(" ORDER BY ");
            builder.append(sortSQL);
        }

		return CustomSQLUtil.replaceAndOperator(builder.toString(), true);
	}


    private static final Log _log = LogFactoryUtil.getLog(CourseFinderUtil.class);
}