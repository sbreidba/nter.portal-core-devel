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

public class UserRatingComparator implements Comparator<CourseRecordQueryResult> {
    private final boolean m_ascending;

    public UserRatingComparator(boolean ascending) {
        m_ascending = ascending;
    }

    public int compare(CourseRecordQueryResult resulta, CourseRecordQueryResult resultb) {
        Double resultaUserRating = 0.0;
        Double resultbUserRating = 0.0;
        if (resulta.getUserRating() != null) resultaUserRating = resulta.getUserRating();
        if (resultb.getUserRating() != null) resultbUserRating = resultb.getUserRating();

        int comparison = resultaUserRating.compareTo(resultbUserRating);
        return m_ascending ? comparison : -comparison;
    }
}