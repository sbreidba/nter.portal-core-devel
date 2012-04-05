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


package course.enumerations;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * This enumeration is designed to describe the various student progress types
 * found in completionStatus column of the CourseRecord and ComponentRecord
 * entities.
 */
public enum CompletionStatusType {

    UNKNOWN         ("Unknown",             0),
    IN_PROGRESS     ("In Progress",         1),
    COMPLETED       ("Completed",           4),
    FAILED          ("Failed",              3),
    FAILED_RETRY    ("Failed Retry",        2);

    private final String dbValue;
    private final int sortOrder;

	private static Log mLog = LogFactoryUtil.getLog(CompletionStatusType.class);

    CompletionStatusType( String databaseValue, int sortOrder) {
        this.dbValue = databaseValue;
        this.sortOrder = sortOrder;
    }

    public String getDbValue() {
        return dbValue;
    }

    public int sortOrder() {
        return sortOrder;
    }

    public static CompletionStatusType valueOfDbValue(String dbValue) {
        for (CompletionStatusType type : CompletionStatusType.values()) {
            if (type.getDbValue().equalsIgnoreCase(dbValue)) {
                return type;
            }
        }

        mLog.error("No CompletionStatusType exists with database value: " + dbValue);
        return UNKNOWN;
    }

    public boolean isFinished() {
        // Note! assumes that there are four states:
        // COMPLETED, FAILED, FAILED_RETRY and IN_PROGRESS.
        // This method may need to be adjusted if more states are added!
        return ((this == COMPLETED) || (this == FAILED));
    }

    public boolean isActive() {
        // Note! assumes that there are four states:
        // COMPLETED, FAILED, FAILED_RETRY and IN_PROGRESS.
        // This method may need to be adjusted if more states are added!
        return ((this == IN_PROGRESS) || (this == FAILED_RETRY));
    }
}