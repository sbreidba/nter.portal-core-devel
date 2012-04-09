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

import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.model.FlagReportStats;

import java.util.Date;

public final class FlagReportMasterSetResult {

    private final FlagReport flagReport;
    private final FlagReportStats flagReportStats;
    private final Date modifiedDate;
    private final String userDisplay;
    private final String contentType;
    private final String mostRecentContent;
    private final String allFlagReasons;
    private final String rollupStatus;
    private final String statsSummary;
    private final long unmoderatedReportCnt;

    FlagReportMasterSetResult(FlagReportStats flagReportStats, FlagReport flagReport, Date modifiedDate, String userDisplay,
                              String contentType, String mostRecentContent, String allFlagReasons, String rollupStatus,
                              String statsSummary, long unmoderatedReportCnt) {
        if (flagReport == null) {
            throw new IllegalArgumentException("flagReport cannot be null");
        }

        this.flagReportStats = flagReportStats;
        this.flagReport = flagReport;
        this.modifiedDate = modifiedDate;
        this.userDisplay = userDisplay;
        this.contentType = contentType;
        this.mostRecentContent = mostRecentContent;
        this.allFlagReasons = allFlagReasons;
        this.rollupStatus = rollupStatus;
        this.statsSummary = statsSummary;
        this.unmoderatedReportCnt = unmoderatedReportCnt;
    }

    public FlagReportStats getFlagReportStats() {
        return flagReportStats;
    }

    public FlagReport getFlagReport() {
        return flagReport;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getUserDisplay() {
        return userDisplay;
    }

    public String getContentType() {
        return contentType;
    }

    public String getMostRecentContent() {
        return mostRecentContent;
    }

    public String getAllFlagReasons() {
        return allFlagReasons;
    }

    public String getRollupStatus() {
        return rollupStatus;
    }

    public String getStatsSummary() {
        return statsSummary;
    }

    public long getUnmoderatedReportCnt() {
        return unmoderatedReportCnt;
    }
}