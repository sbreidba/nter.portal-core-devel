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
 * User Local Course Review information extracted for migration
 */
public class UserReviewExtract {

    private String ssoValue;
    private String mapperType;
    private String emailAddress;
    private String courseIri;
    private long score;
    private String summary;
    private String content;
    private Date createDate;
    private Date modifiedDate;
    private boolean removed;
    private Date removedDate;

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

    public String getCourseIri() {
        return courseIri;
    }
    public void setCourseIri(String newValue) {
        courseIri = newValue;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long newValue) {
        score = newValue;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String newValue) {
        summary = newValue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newValue) {
        content = newValue;
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

    public boolean  getRemoved() {
        return removed;
    }

    public void setRemoved(boolean newValue) {
        removed = newValue;
    }

    public Date getRemovedDate() {
        return removedDate;
    }

    public void setRemovedDate(Date newValue) {
        removedDate = newValue;
    }
}
