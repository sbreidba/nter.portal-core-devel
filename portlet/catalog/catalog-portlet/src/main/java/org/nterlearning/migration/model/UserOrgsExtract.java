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

package org.nterlearning.migration.model;

/**
 * User organization information extracted for migration
 */
public class UserOrgsExtract {

    private String ssoValue;
    private String mapperType;
    private String emailAddress;
    private String orgName;

    public String getSsoValue() {
        return ssoValue;
    }
    public void setSsoValue(String newValue) {
        ssoValue = newValue;
    }

    public String getMapperType() {
        return mapperType;
    }
    public void setMapperType(String newValue) {
        mapperType = newValue;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String newValue) {
        emailAddress = newValue;
    }

    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String newValue) {
        orgName = newValue;
    }

}
