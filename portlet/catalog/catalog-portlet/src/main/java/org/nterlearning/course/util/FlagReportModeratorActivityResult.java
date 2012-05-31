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


package org.nterlearning.course.util;

import java.util.Date;

public final class FlagReportModeratorActivityResult {

    private final Date   moderateDate;
    private final String moderatorUserName;
    private final String moderateAction;
    private final String moderatorComment;
    private final long   moderateReportCnt;

    FlagReportModeratorActivityResult(Date moderateDate, String moderatorUserName,
                              String moderateAction, String moderatorComment,
                              long moderateReportCnt) {
        if (moderateDate == null) {
            throw new IllegalArgumentException("moderate activity is null");
        }

        this.moderateDate = moderateDate;
        this.moderatorUserName = moderatorUserName;
        this.moderateAction = moderateAction;
        this.moderatorComment = moderatorComment;
        this.moderateReportCnt = moderateReportCnt;
    }

    public Date getModerateDate() {
        return moderateDate;
    }

    public String getModeratorUserName() {
        return moderatorUserName;
    }

    public String getModerateAction() {
        return moderateAction;
    }

    public String getModeratorComment() {
        return moderatorComment;
    }

    public long getModerateReportCnt() {
        return moderateReportCnt;
    }

}