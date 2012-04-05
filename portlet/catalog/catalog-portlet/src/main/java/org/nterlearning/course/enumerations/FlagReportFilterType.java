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


package org.nterlearning.course.enumerations;

/**
 * This enumeration is designed to describe the various types of filters
 * we can apply to the flagReport custom query.
 */
public enum FlagReportFilterType {

    // workflow status: 1 pending, 2 draft, 3 expired, 4 denied, 0 approved

    ALL                  (" "),
    IN_PROGRESS_STATUS   (" AND CATALOG_FlagReport.status in (1,3,2) "),
    FINISHED_STATUS      (" AND CATALOG_FlagReport.status in (0,4) ");

    private final String whereSql;

    FlagReportFilterType(String whereSql) {
        this.whereSql = whereSql;
    }

    public String getWhereSql() {
        return whereSql;
    }
}