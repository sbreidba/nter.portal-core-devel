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

import java.util.Comparator;

public class FlagReportStatsComparator implements Comparator<FlagReportQueryResult> {

    public int compare(FlagReportQueryResult resulta, FlagReportQueryResult resultb) {
        // Sort by completionStatus and updated date
        long resultaUnmodCnt = resulta.getFlagReportStats().getTotalEntries()-resulta.getFlagReportStats().getTotalModerated();
        long resultbUnmodCnt = resultb.getFlagReportStats().getTotalEntries()-resultb.getFlagReportStats().getTotalModerated();
        if (resultaUnmodCnt < resultbUnmodCnt) {
            return -1;
        } else if (resultaUnmodCnt > resultbUnmodCnt) {
            return 1;
        } else {
            // date should be opposite asc/desc so a and b switched
            return resultb.getModifiedDate().compareTo(resulta.getModifiedDate());
        }
    }
}