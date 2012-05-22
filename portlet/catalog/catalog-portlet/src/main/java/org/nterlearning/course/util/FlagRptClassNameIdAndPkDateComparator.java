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

public class FlagRptClassNameIdAndPkDateComparator implements Comparator<FlagReportQueryResult> {

    public int compare(FlagReportQueryResult resulta, FlagReportQueryResult resultb) {
        // Sort by classNameId
        long resultaClassNameId = resulta.getFlagReport().getClassNameId();
        long resultbClassNameId = resultb.getFlagReport().getClassNameId();
        if (resultaClassNameId < resultbClassNameId) {
            return -1;
        } else if (resultaClassNameId > resultbClassNameId) {
            return 1;
        } else {
            long resultaClassPK = resulta.getFlagReport().getClassPK();
            long resultbClassPK = resultb.getFlagReport().getClassPK();
            if (resultaClassPK < resultbClassPK) {
                return -1;
            } else if (resultaClassPK > resultbClassPK) {
                return 1;
            } else {
                // date should be opposite asc/desc so a and b switched
                return resultb.getFlagReport().getStatusDate().compareTo(resulta.getFlagReport().getStatusDate());
            }
        }
    }
}