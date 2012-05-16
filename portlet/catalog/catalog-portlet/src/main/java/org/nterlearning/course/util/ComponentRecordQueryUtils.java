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

import org.nterlearning.course.enumerations.CompletionStatusType;
import org.nterlearning.course.enumerations.SectionType;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class ComponentRecordQueryUtils {

    private ComponentRecordQueryUtils() {
    }

    public static List<ComponentRecordQueryResult> getCompoundQueryResults(long courseRecordId, long userId, Locale locale, String filterType, String sortType, boolean asc, int start, int end) {
        List<ComponentRecordQueryResult> result = new ArrayList <ComponentRecordQueryResult>();
        try {
            List<Object[]> rows = ComponentRecordLocalServiceUtil.findByCourseRecordIdUserIdLanguageFilterSorted(courseRecordId, userId, locale, filterType, sortType, asc, start, end);

            for (Object[] row : rows) {
                Component component = null;
                ComponentRecord componentRecord = null;
                Courses_Components courses_components = null;

                if (row.length != 3) {
                    _log.error("Row result with an incorrect number of objects: " +
                            "expected 3 but got " + row.length);
                    continue;
                }

                // the instanceof operator will return false if the argument is null

                if (row[0] instanceof ComponentRecord) {
                    componentRecord = (ComponentRecord)row[0];
                }
                if (row[1] instanceof Component) {
                    component = (Component)row[1];
                }
                if (row[2] instanceof Courses_Components) {
                    courses_components=(Courses_Components)row[2];
                }
                _log.debug("----------  ROW  ----------");
                _log.debug("  component = " + component);
                _log.debug("  componentRecord = " + componentRecord);
                _log.debug("  courses_components = " + courses_components);
                _log.debug("---------------------------");

                result.add(new ComponentRecordQueryResult(component, componentRecord, courses_components));

            }

            return result;
        }
        catch (SystemException e) {
            _log.error(e);
            return new ArrayList<ComponentRecordQueryResult>();
        }
        catch (IllegalArgumentException e) {
            _log.error(e);
            return new ArrayList<ComponentRecordQueryResult>();
        }
    }

    //Return total number of student componentRecord with completed or failed status
    public static int getComponentFinishedCount(List<ComponentRecordQueryResult> resultList){
        int finishedComponentCount = 0;
        for (ComponentRecordQueryResult result : resultList) {
            if ((result.getComponentRecord() != null)  &&
               (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isContent() )) {

                if (CompletionStatusType.valueOfDbValue(result.getComponentRecord().getCompletionStatus()).isFinished()) {
                    finishedComponentCount++;
                }
            }
        }
        return finishedComponentCount;
    }

        //Return total number of student componentRecord with completed or failed status
    public static int getComponentActiveCount(List<ComponentRecordQueryResult> resultList){
        int activeComponentCount = 0;
        for (ComponentRecordQueryResult result : resultList) {
            if ((result.getComponentRecord() != null) &&
               (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isContent() )) {

                if (CompletionStatusType.valueOfDbValue(result.getComponentRecord().getCompletionStatus()).isActive()) {
                    activeComponentCount++;
                }
            }
        }
        return activeComponentCount;
    }

    public static ComponentRecordQueryResult getFirstUncompletedComponent(List<ComponentRecordQueryResult> resultList) {

        // return most recent component found in result list that is active
        // if none started return first in list
        // if all components finished return null.
        boolean foundNotStartedComponent = false;
        boolean foundActiveComponent = false;
        ComponentRecordQueryResult firstUncompletedResult = null;

        for (ComponentRecordQueryResult result : resultList) {

            if (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isContent()) {

                if (result.getComponentRecord() == null) {

                    if (!foundNotStartedComponent && !foundActiveComponent) {
                        //capture first not started (null) componentRecord
                        firstUncompletedResult = result;
                        foundNotStartedComponent = true;
                    }
                    continue;
                }
                CompletionStatusType completion = CompletionStatusType.valueOfDbValue(result.getComponentRecord().getCompletionStatus());
                if (completion.isActive()) {
                    // determine most recent active componentRecord
                    if (!foundActiveComponent) {
                        firstUncompletedResult = result;
                        foundActiveComponent = true;
                    } else {
                        boolean newer = result.getComponentRecord().getUpdatedDate().after(firstUncompletedResult.getComponentRecord().getUpdatedDate());
                        if (newer) {
                            firstUncompletedResult = result;
                        }
                    }
                }
            }
        }
        return firstUncompletedResult;
    }

        public static ComponentRecordQueryResult getFirstFailedComponent(List<ComponentRecordQueryResult> resultList) {

        // return first failed component found in result list
        // if not found return first non-complete in list
        boolean foundFirstComponent = false;
        ComponentRecordQueryResult firstFailedResult = null;

            for (ComponentRecordQueryResult result : resultList) {

                if (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isContent()) {

                    if (result.getComponentRecord() == null) {

                        if (!foundFirstComponent) {
                            //capture first not started (null) componentRecord
                            firstFailedResult = result;
                            foundFirstComponent = true;
                        }
                        continue;
                    }
                    CompletionStatusType completion = CompletionStatusType.valueOfDbValue(result.getComponentRecord().getCompletionStatus());
                    if (completion.equals(CompletionStatusType.FAILED_RETRY)) {
                        // determine most recent active componentRecord
                        return result;
                    }
                    if (!foundFirstComponent) {
                        //capture first not started (null) componentRecord
                        firstFailedResult = result;
                        foundFirstComponent = true;
                    }
                }
            }
        return firstFailedResult;
    }

    //Return content components
    public static List<ComponentRecordQueryResult> getContentComponents(List<ComponentRecordQueryResult> resultList){
        List<ComponentRecordQueryResult> content = new ArrayList <ComponentRecordQueryResult>();
        for (ComponentRecordQueryResult result : resultList) {
            if (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isContent() ) {
                content.add(new ComponentRecordQueryResult(result.getComponent(), result.getComponentRecord(), result.getCourses_Components()));
            }
        }
        return content;
    }

	//Return content resources
    public static List<ComponentRecordQueryResult> getResourceComponents(List<ComponentRecordQueryResult> resultList){
        List<ComponentRecordQueryResult> resource = new ArrayList <ComponentRecordQueryResult>();
        for (ComponentRecordQueryResult result : resultList) {
            if (SectionType.valueOfDbValue(result.getCourses_Components().getSectionType()).isResource()) {
                resource.add(new ComponentRecordQueryResult(result.getComponent(), result.getComponentRecord(), result.getCourses_Components()));
            }
        }
        return resource;
    }

	private static Log _log = LogFactoryUtil.getLog(ComponentRecordQueryUtils.class);
}