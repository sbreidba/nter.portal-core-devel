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

import java.util.Date;

/**
 * Message Board Category information extracted for migration
 */
public class MbCategoryExtract {

    private String ssoValue;
    private String mapperType;
    private String emailAddress;
    private String userName;
    private long categoryId;
    private long parentCategoryId;
    private String name;
    private String description;
    private String displayStyle;
    private Date createDate;
    private Date modifiedDate;
    private Date lastPostDate;
    private String inEmailAddress;
    private String inProtocol;
    private String inServerName;
    private int inServerPort;
    private boolean inUseSSL;
    private String inUserName;
    private String inPassword;
    private int inReadInterval;
    private String outEmailAddress;
    private boolean outCustom;
    private String outServerName;
    private int outServerPort;
    private boolean outUseSSL;
    private String outUserName;
    private String outPassword;
    private boolean allowAnonymous;
    private boolean mailingListActive;

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

    public String getUserName() {
        return userName;
    }
    public void setUserName(String newValue) {
        userName = newValue;
    }

    public long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(long newValue) {
        categoryId = newValue;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }
    public void setParentCategoryId(long newValue) {
        parentCategoryId = newValue;
    }

    public String getName() {
        return name;
    }
    public void setName(String newValue) {
        name = newValue;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String newValue) {
        description = newValue;
    }

    public String getDisplayStyle() {
        return displayStyle;
    }
    public void setDisplayStyle(String newValue) {
        displayStyle = newValue;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date newValue) {
        createDate = newValue;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date newValue) {
        modifiedDate = newValue;
    }

    public Date getLastPostDate() {
        return lastPostDate;
    }
    public void setLastPostDate(Date newValue) {
        lastPostDate = newValue;
    }

    public String getInEmailAddress() {
        return inEmailAddress;
    }
    public void setInEmailAddress(String newValue) {
        inEmailAddress = newValue;
    }

    public String getInProtocol() {
        return inProtocol;
    }
    public void setInProtocol(String newValue) {
        inProtocol = newValue;
    }

    public String getInServerName() {
        return inServerName;
    }
    public void setInServerName(String newValue) {
        inServerName = newValue;
    }

    public int getInServerPort() {
        return inServerPort;
    }
    public void setInServerPort(int newValue) {
        inServerPort = newValue;
    }

    public boolean getInUseSSL() {
        return inUseSSL;
    }
    public void setInUseSSL(boolean newValue) {
        inUseSSL = newValue;
    }

    public String getInUserName() {
        return inUserName;
    }
    public void setInUserName(String newValue) {
        inUserName = newValue;
    }

    public String getInPassword() {
        return inPassword;
    }
    public void setInPassword(String newValue) {
        inPassword = newValue;
    }

    public int getInReadInterval() {
        return inReadInterval;
    }
    public void setInReadInterval(int newValue) {
        inReadInterval = newValue;
    }

    public String getOutEmailAddress() {
        return outEmailAddress;
    }
    public void setOutEmailAddress(String newValue) {
        outEmailAddress = newValue;
    }

    public boolean getOutCustom() {
        return outCustom;
    }
    public void setOutCustom(boolean newValue) {
        outCustom = newValue;
    }

    public String getOutServerName() {
        return outServerName;
    }
    public void setOutServerName(String newValue) {
        outServerName = newValue;
    }

    public int getOutServerPort() {
        return outServerPort;
    }
    public void setOutServerPort(int newValue) {
        outServerPort = newValue;
    }

    public boolean getOutUseSSL() {
        return outUseSSL;
    }
    public void setOutUseSSL(boolean newValue) {
        outUseSSL = newValue;
    }

    public String getOutUserName() {
        return outUserName;
    }
    public void setOutUserName(String newValue) {
        outUserName = newValue;
    }

    public String getOutPassword() {
        return outPassword;
    }
    public void setOutPassword(String newValue) {
        outPassword = newValue;
    }

    public boolean getAllowAnonymous() {
        return allowAnonymous;
    }
    public void setAllowAnonymous(boolean newValue) {
        allowAnonymous = newValue;
    }

    public boolean getMailingListActive() {
        return mailingListActive;
    }
    public void setMailingListActive(boolean newValue) {
        mailingListActive = newValue;
    }

}