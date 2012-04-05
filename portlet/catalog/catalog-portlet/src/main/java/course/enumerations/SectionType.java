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
 * This enumeration is designed to describe the various values found in
 * sectionType column of the Courses_Components entity.
 */
public enum SectionType {

    TOC        ("toc"),
    RESOURCE   ("resource"),
    EMPTY      ("");

    private final String dbValue;
    

    SectionType( String databaseValue) {
        this.dbValue = databaseValue;
    }


    public String getDbValue() {
        return dbValue;
    }


    public static SectionType valueOfDbValue(String dbValue) {
        for (SectionType type : SectionType.values()) {
            if (type.getDbValue().equalsIgnoreCase(dbValue)) {
                return type;
            }
        }

        throw new IllegalArgumentException(
                "No SectionType exists with database value: " + dbValue );
    }


    public boolean isContent() {
        // Note! assumes that there are 3 states:
        // TOC, EMPTY or RESOURCE
        // This method may need to be adjusted if more states are added!
        return (this == TOC || this == EMPTY);
    }


    public boolean isResource() {
        // Note! assumes that there are four states:
        // TOC or RESOURCE
        // This method may need to be adjusted if more states are added!
        return (this == RESOURCE);
    }
}