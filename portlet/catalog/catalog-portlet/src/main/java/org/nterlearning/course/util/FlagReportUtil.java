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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class FlagReportUtil {

    /**
     * Determine number of new reports within a set of flag reports
     * @param flagReports  (list of reports from same inappropriate content)
     * @return long (number of new reports)
     */
    public static long calculateNewCount(List<FlagReport> flagReports) {
        long newCount = 0;
        for (FlagReport flagReport:flagReports) {
            if (!flagReport.isModerated()){
                newCount++;
            }
        }
        return newCount;
    }

    /**
     * Determine distinct inappropriate content values within a set of flag reports
     * @param flagReports (list of reports with same inappropriate content)
     * @return contentList (list of distinct contents reported as inappropriate)
     */
    public static List<String> previousContentList(List<FlagReport> flagReports, String currentContent) {
        //sort list so that most recent content is presented first
        //create date - true
        //ascending - false
        List<FlagReport> flagReportSortList = new ArrayList(flagReports);
        Collections.sort(flagReportSortList, new FlagReportDateComparator(true, false));

        //create new list of distinct content
        //since entries are sorted by date, content modifications should be ordered
        //do not include the current content as previousContent
        List<String> contentList = new ArrayList<String>();
        String previousContent = "";
        for (FlagReport flagReportSort:flagReportSortList) {
            if (!previousContent.equals(flagReportSort.getContent()))  {
                previousContent = flagReportSort.getContent();
                if (!previousContent.equals(currentContent)) {
                    contentList.add(previousContent + "<br/> Last reported on " + flagReportSort.getCreateDate());
                }
            }
        }
        return contentList;
    }

    /**
     * Rollup moderator activity within a set of flag reports
     * @param flagReports (list of reports with same inappropriate content)
     * @return contentList (list of distinct contents reported as inappropriate)
     */
    public static List<FlagReportModeratorActivityResult> moderatorActivity(List<FlagReport> flagReports) {
        //sort list so that most recent moderation is presented first
        //create date - false, use status instead
        //ascending - false
        List<FlagReport> flagReportSortList = new ArrayList(flagReports);
        Collections.sort(flagReportSortList, new FlagReportDateComparator(false, false));

        //create list of distinct moderator activity
        List<FlagReportModeratorActivityResult> activityList = new ArrayList<FlagReportModeratorActivityResult>();

        Date moderateDate = flagReportSortList.get(0).getStatusDate();
        String moderatorUserName = flagReportSortList.get(0).getStatusByUserName();
        String moderateAction = flagReportSortList.get(0).getModerateAction();
        String moderatorComment = flagReportSortList.get(0).getModeratorComment();
        long moderateReportCnt = 0;

        for (FlagReport flagReportSort:flagReportSortList) {
             if (flagReportSort.isModerated() && (!moderateDate.equals(flagReportSort.getStatusDate()) ||
                    !moderatorUserName.equals(flagReportSort.getStatusByUserName()) ||
                    !moderateAction.equals(flagReportSort.getModerateAction()) ||
                    !moderatorComment.equals(flagReportSort.getModeratorComment())) )  {

                activityList.add(new FlagReportModeratorActivityResult(moderateDate, moderatorUserName, moderateAction, moderatorComment, moderateReportCnt) );

                moderateDate = flagReportSort.getStatusDate();
                moderatorUserName = flagReportSort.getStatusByUserName();
                moderateAction = flagReportSort.getModerateAction();
                moderatorComment = flagReportSort.getModeratorComment();
                moderateReportCnt = 1;
            } else if ( flagReportSort.isModerated() ) {
                moderateReportCnt++;
            }
        }
        activityList.add(new FlagReportModeratorActivityResult(moderateDate, moderatorUserName, moderateAction, moderatorComment, moderateReportCnt) );
        
        return activityList;
    }
}
