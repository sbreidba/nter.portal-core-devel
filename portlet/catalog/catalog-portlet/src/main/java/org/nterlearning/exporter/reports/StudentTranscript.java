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

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;
import org.nterlearning.exporter.jasper.JasperExporter;


public class StudentTranscript {

    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_TRANSCRIPT_CREATE_DATE = "transcriptCreationDate";
    public static final String KEY_NTER_ADDRESS = "nterServerAddress";
    public static final String KEY_NUMBER_RECORDS = "numberOfRecords";
    public static final String KEY_COURSE_TITLE = "courseTitle";
    public static final String KEY_COURSE_AUTHOR = "courseAuthor";
    public static final String KEY_COURSE_DESCRIPTION = "courseDescription";
    public static final String KEY_COURSE_DOMAIN = "courseDomain";
    public static final String KEY_COURSE_IRI = "courseIri";
    public static final String KEY_COURSE_PROGRESS = "courseProgress";
    public static final String KEY_COURSE_ENROLLED = "courseEnrolled";
    public static final String KEY_RECORD_UPDATED = "recordUpdated";
    public static final String KEY_RECORD_IRI = "recordIri";

    private static final String JRXML_REPORT_FILENAME = "nter-transcript.jrxml";

    private static Log log = LogFactoryUtil.getLog(StudentTranscript.class);


    /**
     * Creates and returns the student transcript report.
     *
     * @param userId User to create the transcript for
     * @param locale User's locale (for language selection)
     * @param serverName Current NTER server name (for report heading).
     *
     * @return Byte array of student report, in PDF format
     */
    public byte[] exportAsPdf(long userId, Locale locale, String serverName) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);

            JasperExporter exporter = new JasperExporter();
            Collection<Map<String, ?>> reportData = getReportData(userId, locale);
            Map<String, Object> reportParams =
                    getReportParams(user, serverName, reportData.size());

            return exporter.exportAsPdfStream(JRXML_REPORT_FILENAME, reportParams, reportData);
        }
        catch (Exception e) {
            log.error("Error creating transcript: " + e.getMessage());
            return new byte[0];
        }
    }


    /**
     * Generates a Map of parameters used by JasperReports to create the transcript report.
     *
     * @param user           User generating report for
     * @param host           NTER host the report is being generated on
     * @param reportDataSize Number of records found for the student's transcript
     * @return Map containing both Jasper report parameters and data.
     */
    private static Map<String, Object> getReportParams(User user, String host,
            long reportDataSize) {

        Map<String, Object> reportParams = new HashMap<String, Object>();

        reportParams.put(KEY_USER_NAME, user.getFullName());
        reportParams.put(KEY_TRANSCRIPT_CREATE_DATE,
                         (new Date(System.currentTimeMillis())).toString());
        reportParams.put(KEY_NTER_ADDRESS, host);
        reportParams.put(KEY_NUMBER_RECORDS, reportDataSize);

        return reportParams;
    }


    /**
     * Converts a student's course records into a format that Jasper can use
     *
     * @param userId User to collect course records for
     * @param locale User's locale
     * @return Collection mapping used by JasperReports
     */
    private static Collection<Map<String, ?>> getReportData(long userId, Locale locale)
            throws SystemException {

        Collection<Map<String, ?>> reportData = new Vector<Map<String, ?>>();

        List<CourseRecord> dbRecords = CourseRecordLocalServiceUtil.findByUserId(userId);
        log.debug("found " + dbRecords.size() + " course records for user id " + userId);

        Map<String, String> reportRecord;
        Course course;
        Contributor courseAuthor;
        String courseAuthorName;

        for (CourseRecord dbRecord : dbRecords) {

            // get the course referenced by this record
            course = CourseLocalServiceUtil.fetchByCourseIri(dbRecord.getCourseIri());

            if (course != null) {

                courseAuthor = course.getCourseAuthor();
                courseAuthorName = (courseAuthor != null)
                                        ? courseAuthor.getContributorName()
                                        : "(none)";

                // create a new output record and fill it in
                reportRecord = new HashMap<String, String>();
                reportRecord.put(KEY_RECORD_IRI, dbRecord.getCourseRecordIri());
                reportRecord.put(KEY_COURSE_TITLE, course.getTitle(locale));
                reportRecord.put(KEY_COURSE_AUTHOR, courseAuthorName);
                reportRecord.put(KEY_COURSE_DOMAIN, course.getCourseDomain());
                reportRecord.put(KEY_COURSE_DESCRIPTION, course.getDescription(locale));
                reportRecord.put(KEY_COURSE_IRI, course.getCourseIri());
                reportRecord.put(KEY_COURSE_PROGRESS, dbRecord.getCompletionStatus());
                reportRecord.put(KEY_COURSE_ENROLLED, String.valueOf(dbRecord.getAssigned()));
                reportRecord.put(KEY_RECORD_UPDATED, dbRecord.getUpdatedDate().toString());
                reportData.add(reportRecord);
            }
            else {
                log.warn("Course record in persistence with IRI " + dbRecord.getCourseRecordIri() +
                        " references course with IRI " + dbRecord.getCourseIri() +
                        ", which does not appear to exist");
            }
        }

        return reportData;
    }
}
