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
 * This enumeration is designed to describe the various types of roles
 * that can be assigned to a course or component.
 */
public enum ContributorRoleType {

    AUTHOR              ("author"),
    CONTRIBUTOR         ("contributor"),
    ORGANIZATION        ("organization");

    private final String value;

    ContributorRoleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContributorRoleType fromValue(String v) {
        for (ContributorRoleType c: ContributorRoleType.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
