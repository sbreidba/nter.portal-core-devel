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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.model.FlagReportStats;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportImpl;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;

import java.util.*;

public final class FlagReportQueryUtils {

    private FlagReportQueryUtils() {
    }

    public static List<FlagReportQueryResult> getCompoundQueryResults(String filterType, int start, int end) {
        List<FlagReportQueryResult> result = new ArrayList<FlagReportQueryResult>();  //new flagReportResult
        try {

            //retrieve local review flag reports and add to result
            long classNameId = ClassNameLocalServiceUtil.getClassNameId(CourseReview.class);
            List<Object[]> rows = FlagReportLocalServiceUtil.findByClassNameIdAndFilter(classNameId, filterType, start, end);

            for (Object[] row : rows) {
                FlagReportStats flagReportStats = null;
                FlagReport flagReport = null;
                Date modifiedDate = null;
                String userDisplay = null;
                String contentType = null;

                if (row.length != 5) {
                    _log.error("Row result with an incorrect number of objects: " +
                            "expected 5 but got " + row.length);
                    continue;
                }

                // the instanceof operator will return false if the argument is null
                if (row[0] instanceof FlagReportStats) {
                    flagReportStats = (FlagReportStats) row[0];
                }
                if (row[1] instanceof FlagReport) {
                    flagReport = (FlagReport) row[1];
                }
                if (row[2] instanceof String) {
                    contentType = (String) row[2];
                }
                if (row[3] instanceof String) {
                    userDisplay = (String) row[3];
                }
                if (row[4] instanceof Date) {
                    modifiedDate = (Date) row[4];
                 }
                _log.debug("----------  ROW  ----------");
                _log.debug("  stats = " + flagReportStats);
                _log.debug("  flag report = " + flagReport);
                _log.debug("  content type = " + contentType);
                _log.debug("  user display = " + userDisplay);
                _log.debug("  modified date = " + modifiedDate);
                _log.debug("---------------------------");

                //Summarize reports for distinct content item.

                result.add(new FlagReportQueryResult(flagReportStats, flagReport, modifiedDate, userDisplay, contentType));

            }

            //retrieve global review flag reports and add to result
            long grClassNameId = ClassNameLocalServiceUtil.getClassNameId(GlobalCourseReview.class);
            List<Object[]> grRows = FlagReportLocalServiceUtil.findByClassNameIdAndFilter(grClassNameId, filterType, start, end);

            for (Object[] row : grRows) {
                FlagReportStats flagReportStats = null;
                FlagReport flagReport = null;
                Date modifiedDate = null;
                String userDisplay = null;
                String contentType = null;

                if (row.length != 5) {
                    _log.error("Row result with an incorrect number of objects: " +
                            "expected 5 but got " + row.length);
                    continue;
                }

                // the instanceof operator will return false if the argument is null
                if (row[0] instanceof FlagReportStats) {
                    flagReportStats = (FlagReportStats) row[0];
                }
                if (row[1] instanceof FlagReport) {
                    flagReport = (FlagReport) row[1];
                }
                if (row[2] instanceof String) {
                    contentType = (String) row[2];
                }
                if (row[3] instanceof String) {
                    userDisplay = (String) row[3];
                }
                if (row[4] instanceof Date) {
                    modifiedDate = (Date) row[4];
                 }
                _log.debug("----------  ROW  ----------");
                _log.debug("  stats = " + flagReportStats);
                _log.debug("  flag report = " + flagReport);
                _log.debug("  content type = " + contentType);
                _log.debug("  user display = " + userDisplay);
                _log.debug("  modified date = " + modifiedDate);
                _log.debug("---------------------------");

                //Summarize reports for distinct content item.

                result.add(new FlagReportQueryResult(flagReportStats, flagReport, modifiedDate, userDisplay, contentType));

            }

            // sort results based on stats and modified date
            Collections.sort(result, new FlagReportStatsComparator());

            return result;

        }
        catch (SystemException e) {
            _log.error(e);
            return new ArrayList<FlagReportQueryResult>();
        }
        catch (IllegalArgumentException e) {
            _log.error(e);
            return new ArrayList<FlagReportQueryResult>();
        }
    }

    //Return total number of reports and count with unmoderated status
    public static String getReportStatsSummary(FlagReportQueryResult result){
        String reportStatsSummary = (result.getFlagReportStats().getTotalEntries() + "(" +
            (result.getFlagReportStats().getTotalEntries() - result.getFlagReportStats().getTotalModerated()) + " new)");
       return reportStatsSummary;
    }

    //Return total number of reports with unmoderated status
    public static long getUnmoderatedReportCnt(FlagReportQueryResult result){
        long unmoderatedReportCnt = result.getFlagReportStats().getTotalEntries() - result.getFlagReportStats().getTotalModerated();
       return unmoderatedReportCnt;
    }

    //Return rolled-up, master flag report for each set
    public static List<FlagReportMasterSetResult> getMasterSet(List<FlagReportQueryResult> resultList) {
        List<FlagReportMasterSetResult> mostRecentFlagReportSet = new ArrayList<FlagReportMasterSetResult>();  //rollup of flagReportResult

        if (resultList.size() >= 1) {
            Collections.sort(resultList, new FlagRptClassNameIdAndPkDateComparator());

            FlagReportQueryResult flagReportSetMaster = resultList.get(0);
            String flagReasonMaster = "~" + flagReportSetMaster.getFlagReport().getFlagReason();
            String flagReasonSave = flagReportSetMaster.getFlagReport().getFlagReason();
            String statusMaster = "Processed";
            boolean statusHandler = false;

            for (FlagReportQueryResult result : resultList) {
                if (result.getFlagReport().getClassPK() != flagReportSetMaster.getFlagReport().getClassPK() ||
                        result.getFlagReport().getClassNameId() != flagReportSetMaster.getFlagReport().getClassNameId()) {

                    mostRecentFlagReportSet.add(new FlagReportMasterSetResult(flagReportSetMaster.getFlagReportStats(),
                            flagReportSetMaster.getFlagReport(), flagReportSetMaster.getModifiedDate(),
                            flagReportSetMaster.getUserDisplay(), flagReportSetMaster.getContentType(),
                            flagReportSetMaster.getFlagReport().getContent(), flagReasonMaster, statusMaster,
                            getReportStatsSummary(flagReportSetMaster),
                            getUnmoderatedReportCnt(flagReportSetMaster)));

                    flagReportSetMaster = result;
                    flagReasonMaster = "~" + result.getFlagReport().getFlagReason();
                    flagReasonSave = result.getFlagReport().getFlagReason();

                    if (result.getFlagReport().getStatus() == WorkflowConstants.STATUS_PENDING) {
                        //check to see if assigned to an admin yet
                        if (result.getFlagReport().getUserId() != result.getFlagReport().getStatusByUserId()) {
                            statusMaster = (result.getFlagReport().getStatusByUserName() + " is handling this");
                            statusHandler = true;
                        } else {
                            statusMaster= "New";
                        }
                    } else if (!result.getFlagReport().isModerated() && !statusHandler) {
                        statusMaster = "New";
                    } else {
                        statusMaster = "Processed";
                    }


                } else {

                    if (!result.getFlagReport().getFlagReason().equalsIgnoreCase(flagReasonSave)) {
                        flagReasonMaster = (flagReasonMaster + "<br/>~" + result.getFlagReport().getFlagReason());
                        flagReasonSave = result.getFlagReport().getFlagReason();
                    }

                    if (result.getFlagReport().getStatus() == WorkflowConstants.STATUS_PENDING) {
                        //check to see if assigned to an admin yet
                        if (result.getFlagReport().getUserId() != result.getFlagReport().getStatusByUserId()) {
                            statusMaster = (result.getFlagReport().getStatusByUserName() + " is handling this");
                            statusHandler = true;
                        } else {
                            statusMaster= "New";
                        }
                    } else if (!result.getFlagReport().isModerated() && !statusHandler) {
                        statusMaster = "New";
                    }
                }
            }
            mostRecentFlagReportSet.add(new FlagReportMasterSetResult(flagReportSetMaster.getFlagReportStats(),
                    flagReportSetMaster.getFlagReport(), flagReportSetMaster.getModifiedDate(),
                    flagReportSetMaster.getUserDisplay(), flagReportSetMaster.getContentType(),
                    flagReportSetMaster.getFlagReport().getContent(), flagReasonMaster, statusMaster,
                    getReportStatsSummary(flagReportSetMaster),
                    getUnmoderatedReportCnt(flagReportSetMaster)));
        }
        return mostRecentFlagReportSet;
    }

	private static Log _log = LogFactoryUtil.getLog(FlagReportQueryUtils.class);
}