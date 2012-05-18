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
 * User Review Helpful/Not Helpful score information extracted for migration
 */
public class UserReviewHelpExtract {

    private String voteSsoValue;
    private String voteMapperType;
    private String voteEmailAddress;
    private String reviewSsoValue;
    private String reviewMapperType;
    private String reviewEmailAddress;
    private String courseIri;
    private long score;

    public String getVoteSsoValue() {
        return voteSsoValue;
    }
    public void setVoteSsoValue(String newValue) {
        voteSsoValue = newValue;
    }

    public String getVoteMapperType() {
        return voteMapperType;
    }
    public void setVoteMapperType(String newValue) {
        voteMapperType = newValue;
    }

    public String getVoteEmailAddress() {
        return voteEmailAddress;
    }
    public void setVoteEmailAddress(String newValue) {
        voteEmailAddress = newValue;
    }

    public String getReviewSsoValue() {
        return reviewSsoValue;
    }
    public void setReviewSsoValue(String newValue) {
        reviewSsoValue = newValue;
    }

    public String getReviewMapperType() {
        return reviewMapperType;
    }
    public void setReviewMapperType(String newValue) {
        reviewMapperType = newValue;
    }

    public String getReviewEmailAddress() {
        return reviewEmailAddress;
    }
    public void setReviewEmailAddress(String newValue) {
        reviewEmailAddress = newValue;
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

}
