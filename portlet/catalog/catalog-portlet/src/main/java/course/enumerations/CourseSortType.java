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

/**
 * This enumeration is designed to describe the various types of sorting we can
 * apply to the general course query.
 */
public enum CourseSortType {

    NEW_POPULAR      ("CATALOG_Course.updatedDate DESC, CATALOG_Course.popularWeight DESC "),
    POPULAR_NEW      ("CATALOG_Course.popularWeight DESC, CATALOG_Course.updatedDate DESC "),
    FEATURED_NEW     ("CATALOG_Course.featuredStatus ASC, CATALOG_Course.updatedDate DESC "),
	FEATURED_POPULAR ("CATALOG_COURSE.featuredStatus ASC, CATALOG_Course.popularWeight DESC ");

    private final String sortSql;

    CourseSortType(String sortSql) {
         this.sortSql = sortSql;
    }

    public String getSortSql() {
        return sortSql;
    }

}