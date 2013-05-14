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

package org.nterlearning.exporter.reports;

import java.util.*;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.nterlearning.exporter.jasper.JasperExporter;


public class LastUsersLoggedIn {

    public static final String PARAM_KEY_TRANSCRIPT_CREATE_DATE = "reportCreationDate";
    public static final String PARAM_KEY_NTER_ADDRESS = "nterServerAddress";
    public static final String PARAM_KEY_NUMBER_RECORDS = "numberOfRecords";
    public static final String RECORD_KEY_STUDENT_NAME = "studentName";
    public static final String RECORD_KEY_LAST_LOGIN_DATETIME = "lastLoginDateTime";

    private static final String JRXML_REPORT_FILENAME = "last-users-logged-in.jrxml";

    private static Log log = LogFactoryUtil.getLog(LastUsersLoggedIn.class);


    public byte[] exportAsPdf(long companyId, int numberOfUsers, Locale locale, String host) {
        try {
            JasperExporter exporter = new JasperExporter();
            Collection<Map<String, ?>> reportData = getReportData(companyId, numberOfUsers);
            Map<String, Object> reportParams = getReportParams(host, reportData.size());
            return exporter.exportAsPdfStream(JRXML_REPORT_FILENAME, reportParams, reportData);
        }
        catch (Exception e) {
            log.error("Error creating report: " + e.getMessage());
            return new byte[0];
        }
    }


    private Collection<Map<String, ?>> getReportData(long companyId, int numberOfUsers) {

        Collection<Map<String, ?>> reportData = new Vector<Map<String, ?>>();

        try {
            List<User> users = UserLocalServiceUtil.getCompanyUsers(companyId, 0, numberOfUsers);
            Collections.sort(users, new UserLastLoginComparator());

            Map<String, String> reportRecord;
            for (User user : users) {

                String lastLogin = "never";
                if (user.getLastLoginDate() != null) {
                    lastLogin = user.getLastLoginDate().toString();
                }

                String userName = user.getFullName();
                if (user.getFullName() == null || user.getFullName() == "") {
                    userName = "<no name given>";
                }

                // create a new output record and fill it in
                reportRecord = new HashMap<String, String>();
                reportRecord.put(RECORD_KEY_STUDENT_NAME, userName);
                reportRecord.put(RECORD_KEY_LAST_LOGIN_DATETIME, lastLogin);
                reportData.add(reportRecord);
            }

            return reportData;
        }
        catch (SystemException e) {
            throw new RuntimeException("Error getting report data: " + e, e);
        }
    }


    private Map<String, Object> getReportParams(String host, int reportDataSize) {
        Map<String, Object> reportParams = new HashMap<String, Object>();

        reportParams.put(PARAM_KEY_TRANSCRIPT_CREATE_DATE,
                (new Date(System.currentTimeMillis())).toString());
        reportParams.put(PARAM_KEY_NTER_ADDRESS, host);
        reportParams.put(PARAM_KEY_NUMBER_RECORDS, reportDataSize);

        return reportParams;
    }


    /**
     * @author gjiva
     */
    public static class UserLastLoginComparator implements Comparator<User> {

        public int compare(User u1, User u2) {

            Date d1 = u1.getLastLoginDate();
            Date d2 = u2.getLastLoginDate();

            // treat nulls as zeros
            if (d1 == null) {
                d1 = new Date(0);
            }

            if (d2 == null) {
                d2 = new Date(0);
            }

            // do the comparing
            if (d1.before(d2)) {
                return -1;
            }
            else if (d1.equals(d2)) {
                return 0;
            }
            else if (d1.after(d2)) {
                return 1;
            }
            else {
                throw new RuntimeException(
                        "Date [" + d1 + "] is not before, equal to or after [" + d2 + "]");
            }
        }

        public boolean equals(User u1, User u2) {
            Date d1 = u1.getLastLoginDate();
            Date d2 = u2.getLastLoginDate();

            return d1.equals(d2);
        }
    }
}
