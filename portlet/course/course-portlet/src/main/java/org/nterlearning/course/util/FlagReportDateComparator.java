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

import org.nterlearning.datamodel.catalog.model.FlagReport;

import java.util.Comparator;
import java.util.Date;

public class FlagReportDateComparator implements Comparator<FlagReport> {

    private final boolean m_createDate;
    private final boolean m_ascending;

    public FlagReportDateComparator(boolean createDate, boolean ascending) {
        m_createDate = createDate;
        m_ascending = ascending;
    }

    public int compare(FlagReport resulta, FlagReport resultb) {
        Date resultaDate = new Date();
        Date resultbDate = new Date();
        if (m_createDate) {
            resultaDate = resulta.getCreateDate();
            resultbDate = resultb.getCreateDate();
        } else {
            resultaDate = resulta.getStatusDate();
            resultbDate = resultb.getStatusDate();
        }

        int comparison = resultaDate.compareTo(resultbDate);
        return m_ascending ? comparison : -comparison;
    }
}