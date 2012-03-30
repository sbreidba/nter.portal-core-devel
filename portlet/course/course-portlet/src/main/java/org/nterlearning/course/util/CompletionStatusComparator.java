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

import org.nterlearning.course.enumerations.CompletionStatusType;

import java.util.Comparator;

public class CompletionStatusComparator implements Comparator<CourseRecordQueryResult> {

    public int compare(CourseRecordQueryResult resulta, CourseRecordQueryResult resultb) {
        // Sort by completionStatus and updated date
        CompletionStatusType resultaStatus = CompletionStatusType.valueOfDbValue(resulta.getCourseRecord().getCompletionStatus());
        CompletionStatusType resultbStatus = CompletionStatusType.valueOfDbValue(resultb.getCourseRecord().getCompletionStatus());
        if (resultaStatus.sortOrder() < resultbStatus.sortOrder()) {
            return -1;
        } else if (resultaStatus.sortOrder() > resultbStatus.sortOrder()) {
            return 1;
        } else {
            // date should be opposite asc/desc so a and b switched
            return resultb.getCourseRecord().getUpdatedDate().compareTo(resulta.getCourseRecord().getUpdatedDate());
        }
    }
}