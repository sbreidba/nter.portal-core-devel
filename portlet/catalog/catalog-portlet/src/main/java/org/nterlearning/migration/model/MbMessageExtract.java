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
 * Message Board Message information extracted for migration
 */
public class MbMessageExtract {

    private String ssoValue;
    private String mapperType;
    private String emailAddress;
    private String userName;
    private String name;
    private String description;
    private Date createDate;
    private Date modifiedDate;
    private long messageId;
    private long threadId;
    private long rootMessageId;
    private long parentMessageId;
    private String subject;
    private String body;
    private boolean attachments;
    private boolean anonymous;
    private double priority;
    private boolean allowPingbacks;

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

    public long getMessageId() {
        return messageId;
    }
    public void setMessageId(long newValue) {
        messageId = newValue;
    }

    public long getThreadId() {
        return threadId;
    }
    public void setThreadId(long newValue) {
        threadId = newValue;
    }

    public long getRootMessageId() {
        return rootMessageId;
    }
    public void setRootMessageId(long newValue) {
        rootMessageId = newValue;
    }

    public long getParentMessageId() {
        return parentMessageId;
    }
    public void setParentMessageId(long newValue) {
        parentMessageId = newValue;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String newValue) {
        subject = newValue;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String newValue) {
        body = newValue;
    }

    public boolean getAttachments() {
        return attachments;
    }
    public void setAttachments(boolean newValue) {
        attachments = newValue;
    }

    public boolean getAnonymous() {
        return anonymous;
    }
    public void setAnonymous(boolean newValue) {
        anonymous = newValue;
    }

    public double getPriority() {
        return priority;
    }
    public void setPriority(double newValue) {
        priority = newValue;
    }

    public boolean getAllowPingbacks() {
        return allowPingbacks;
    }
    public void setAllowPingbacks(boolean newValue) {
        allowPingbacks = newValue;
    }

}