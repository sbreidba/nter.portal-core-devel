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
 * User information extracted for migration
 */
public class UserExtract {

    private String ssoValue;
    private String mapperType;
    private String screenName;
    private String emailAddress;
    private String firstName;
    private String middleName;
    private String lastName;
    private String jobTitle;

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

    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String newValue) {
        screenName = newValue;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String newValue) {
        emailAddress = newValue;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String newValue) {
        firstName = newValue;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String newValue) {
        middleName = newValue;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String newValue) {
        lastName = newValue;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String newValue) {
        jobTitle = newValue;
    }

}
