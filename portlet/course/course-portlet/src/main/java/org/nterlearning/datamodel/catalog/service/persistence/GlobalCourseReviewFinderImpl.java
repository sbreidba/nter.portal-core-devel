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

import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: plpeters
 * Date: Sept 2, 2011
 */
public class GlobalCourseReviewFinderImpl extends BasePersistenceImpl<GlobalCourseReview> implements GlobalCourseReviewFinder {

    private static final String FIND_VALID_BY_COURSE_ID = GlobalCourseReviewFinder.class.getName() + ".findValidByCourseId";
    private static final String COUNT_VALID_BY_COURSE_ID = GlobalCourseReviewFinder.class.getName() + ".countValidByCourseId";

    @SuppressWarnings("unchecked")
    public List<GlobalCourseReview> findValidByCourseId(long courseId, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(FIND_VALID_BY_COURSE_ID);
            StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            //append order by
            builder.append(" ORDER BY CATALOG_GlobalCourseReview.fromTrustedReviewer desc, " +
                    "CATALOG_GlobalCourseReview.modifiedDate desc");

            SQLQuery query = session.createSQLQuery(builder.toString());
            query.addEntity("CATALOG_GlobalCourseReview", GlobalCourseReviewImpl.class);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseId);

            _log.debug(builder);
            return (List<GlobalCourseReview>) QueryUtil.list(query, getDialect(), start, end);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

        public long countValidByCourseId(long courseId) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(COUNT_VALID_BY_COURSE_ID);
            StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));

            SQLQuery query = session.createSQLQuery(builder.toString());
            query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseId);

            _log.debug(builder);
            List<Long> list = (List<Long>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            return list.get(0);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(GlobalCourseReviewFinderUtil.class);
}