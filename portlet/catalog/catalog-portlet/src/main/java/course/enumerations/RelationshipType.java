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
 * This enumeration is designed to describe the various course relationship types found in
 * relationshipType column of the CATALOG_CourseRelated entity.
 */
public enum RelationshipType {

    PREREQUISITE            ("PREREQUISITE"),
    SUPERSEDES              ("SUPERSEDES");

    private final String value;

    RelationshipType( String databaseValue) {
        value = databaseValue;
    }

    public String getValue() {
        return value;
    }


    /**
     * Maintained for backwards compatibility.
     * @return
     */
    public String getDbValue() {
        return value;
    }


    public static RelationshipType fromValue(String value) {
        for (RelationshipType type : RelationshipType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException(
                "No RelationshipType exists with database value: " + value );
    }


    /**
     * Maintained for backwards compatibility.
     * @param value
     * @return
     */
    public static RelationshipType fromDbValue(String value) {
        return fromValue(value);
    }
}