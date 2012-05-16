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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl;

import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: plpeters
 * Date: Dec 22, 2010
 * Time: 11:19:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ComponentFinderImpl extends BasePersistenceImpl<Component> implements ComponentFinder {

    private static final String FIND_BY_COURSE_ID_LANGUAGE_SORTED = ComponentFinder.class.getName() + ".findByCourseIdLanguageSorted";

    @SuppressWarnings("unchecked")
    public List<Object[]> findByCourseIdLanguageSorted(long courseId, Locale locale, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(FIND_BY_COURSE_ID_LANGUAGE_SORTED);
            StringBuilder builder = new StringBuilder(CustomSQLUtil.replaceAndOperator(sql, true));
            builder.append(" ORDER BY CATALOG_Courses_Components.orderWeight");

            SQLQuery query = session.createSQLQuery(builder.toString());
            query.addEntity("CATALOG_Component", ComponentImpl.class);
            query.addEntity("CATALOG_Courses_Components", Courses_ComponentsImpl.class);
            QueryPos pos = QueryPos.getInstance(query);
            pos.add(courseId);
            pos.add(locale.toString());
            return (List<Object[]>) QueryUtil.list(query, getDialect(), start, end);
        }catch (ORMException e){
            throw new SystemException(e.getMessage());
        }finally{
            closeSession(session);
        }
    }

}