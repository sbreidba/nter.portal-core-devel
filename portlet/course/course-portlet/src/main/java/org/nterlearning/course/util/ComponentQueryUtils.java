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


package org.nterlearning.course.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portal.kernel.util.LocaleUtil;
import org.nterlearning.course.enumerations.SectionType;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class ComponentQueryUtils {

    private ComponentQueryUtils() {
    }

    public static List<ComponentQueryResult> getCompoundQueryResults(long courseId, Locale locale, int start, int end) {
        List<ComponentQueryResult> result = new ArrayList <ComponentQueryResult>();
        try {
			List<Object[]> rows = ComponentLocalServiceUtil.findByCourseIdLanguageSorted(courseId, locale, start, end);

            for (Object[] row : rows) {
                Component component = null;
                Courses_Components courses_components = null;

                if (row.length != 2) {
                    _log.error("Row result with an incorrect number of objects: " +
                            "expected 2 but got " + row.length);
                    continue;
                }

                // the instanceof operator will return false if the argument is null

                if (row[0] instanceof Component) {
                    component = (Component)row[0];
                }
                if (row[1] instanceof Courses_Components) {
                    courses_components = (Courses_Components)row[1];
                }

                _log.debug("----------  ROW  ----------");
                _log.debug("  component = " + component);
                _log.debug("  courses_components = " + courses_components);
                _log.debug("---------------------------");

                result.add(new ComponentQueryResult(component, courses_components));

            }

            return result;
        }
        catch (SystemException e) {
            _log.error(e);
            return new ArrayList<ComponentQueryResult>();
        }
        catch (IllegalArgumentException e) {
            _log.error(e);
            return new ArrayList<ComponentQueryResult>();
        }
    }

    // Return first component with locale
    public static Component getFirst(List<ComponentQueryResult> componentQueryResults, Locale locale) {
        Component firstComponent = null;
        for (ComponentQueryResult componentQueryResult : componentQueryResults) {
            Component component = componentQueryResult.getComponent();
            Locale componentLocale = LocaleUtil.fromLanguageId(component.getLanguage());
            if (componentLocale.equals(locale)) {
                return component;
            }
        }
        return firstComponent;
    }

    //Return content components
    public static List<ComponentQueryResult> getContentComponents(List<ComponentQueryResult> resultList){
        List<ComponentQueryResult> content = new ArrayList <ComponentQueryResult>();
        for (ComponentQueryResult result : resultList) {
            if (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isContent() ) {
                content.add(new ComponentQueryResult(result.getComponent(),result.getCourses_Components()));
            }
        }
        return content;
    }

	//Return content resources
    public static List<ComponentQueryResult> getResourceComponents(List<ComponentQueryResult> resultList){
        List<ComponentQueryResult> resource = new ArrayList <ComponentQueryResult>();
        for (ComponentQueryResult result : resultList) {
            if (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isResource()) {
                resource.add(new ComponentQueryResult(result.getComponent(),result.getCourses_Components()));
            }
        }
        return resource;
    }

	private static Log _log = LogFactoryUtil.getLog(ComponentRecordQueryUtils.class);
}