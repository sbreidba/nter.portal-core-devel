/**
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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


package org.nterlearning.atom.parser.dao;


public class CourseAndCategoryPair {

    private final long coursePk;
    private final String categoryName;


    public CourseAndCategoryPair(long coursePk, String categoryName) {
        this.coursePk = coursePk;
        this.categoryName = categoryName;
    }


    public long getCoursePk() {
        return coursePk;
    }


    public String getCategoryName() {
        return categoryName;
    }
}
