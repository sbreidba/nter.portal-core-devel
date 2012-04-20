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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseReview;

import java.util.List;

public class CourseReviewFinderImpl extends BasePersistenceImpl<CourseReview> implements CourseReviewFinder {

    private static final String FIND_RATINGS_ENTRY = CourseReviewFinder.class.getName() + ".findRatingsEntry";
    private static final String BY_COURSE_ID = CourseReviewFinder.class.getName() + ".byCourseId";
    private static final String BY_REVIEW_ID = CourseReviewFinder.class.getName() + ".byReviewId";

    public double findScoreByReviewId(long courseReviewId) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(FIND_RATINGS_ENTRY);
            sql += CustomSQLUtil.get(BY_REVIEW_ID);

            // create a SQLQuery object
            sql = CustomSQLUtil.replaceAndOperator(sql, true);
            SQLQuery query = session.createSQLQuery(sql);
            query.addScalar("score", Type.DOUBLE);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(ClassNameLocalServiceUtil.getClassNameId(Course.class.getName()));
            pos.add(courseReviewId);

            List<Double> list = (List<Double>)QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            if(list.isEmpty())
                throw new SystemException("No ratings entry found for Course Review " + courseReviewId);
            return list.get(0);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }
    public List<Double> findScoresByCourseId(long courseId) throws SystemException {
        Session session = null;
        setSessionFactory((SessionFactory) PortalBeanLocatorUtil.getBeanLocator().locate("liferaySessionFactory"));
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(FIND_RATINGS_ENTRY);
            sql += CustomSQLUtil.get(BY_COURSE_ID);

            // create a SQLQuery object
            sql = CustomSQLUtil.replaceAndOperator(sql, true);
            SQLQuery query = session.createSQLQuery(sql);
            query.addScalar("score", Type.DOUBLE);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(ClassNameLocalServiceUtil.getClassNameId(Course.class.getName()));
            pos.add(courseId);
            return (List<Double>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (ORMException e) {
            throw new SystemException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }
}
