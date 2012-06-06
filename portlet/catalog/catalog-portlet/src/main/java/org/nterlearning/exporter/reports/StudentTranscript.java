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

/**
 * 
 */
package org.nterlearning.exporter.reports;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.lang.Validate;

import com.liferay.portal.kernel.exception.PortalException;
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
import org.nterlearning.exporter.ReportExporter;


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
	
	// TODO: if needed, make these configurable
	private static final String PDF_REPORT_PREFIX = "nter-transcript-";
	private static final String JRXML_REPORT_FILENAME = "nter-transcript";
	
	private static Log log = LogFactoryUtil.getLog(StudentTranscript.class);
	
	/**
	 * Exports a student's course records into a PDF file
	 * 
	 * @param userId - the user ID of the student whose transcript to create
	 * @param locale - the user's locale
	 */
	public static String exportToPdf(long userId, String host, Locale locale)
		throws SystemException, PortalException{
		
		User user = UserLocalServiceUtil.getUserById(userId);
		Validate.notNull(user, "Could not find user with id: " + userId);
		
		log.info("Creating transcript for student [" + user.getFullName() + "]");
		
		// put the report data into the formats required
		Collection<Map<String,?>> reportData = getReportData(userId, locale);
		Map<String,Object> reportParams = getReportParams(user,host,reportData);
		
		return ReportExporter.exportReportToPdf(PDF_REPORT_PREFIX + user.getLastName(), 
				JRXML_REPORT_FILENAME, reportParams, reportData);
	}
	
	/**
	 * Exports a student's course records into a PDF file, then redirects the response to the URL of the PDF
	 * 
	 * @param userId
	 * @param locale
	 * @param response
	 */
	public static void exportToPdfAndRedirect(long userId, Locale locale, ActionRequest request, ActionResponse response)
		throws PortalException, SystemException{
		
		ReportExporter.redirectToReportUrl(exportToPdf(userId, request.getServerName(),locale),request,response);
		
	}
	
	/**
	 * 
	 * @param user
	 * @param reportData
	 * @return
	 */
	private static Map<String,Object> getReportParams(User user, String host, Collection<Map<String,?>> reportData){
		
		Map<String,Object> reportParams = new HashMap<String, Object>();
		
		reportParams.put(KEY_USER_NAME, user.getFullName());
		reportParams.put(KEY_TRANSCRIPT_CREATE_DATE, (new Date(System.currentTimeMillis())).toString());
		reportParams.put(KEY_NTER_ADDRESS, host);
		reportParams.put(KEY_NUMBER_RECORDS, reportData.size());
		
		return reportParams;
	}
	
	/**
	 * Converts a student's course records into a format that Jasper can use
	 * 
	 * @param user
	 * @return
	 */
	private static Collection<Map<String,?>> getReportData(long userId, Locale locale)
		throws SystemException{
		
		Collection<Map<String,?>> reportData = new Vector<Map<String,?>>();
		
		List<CourseRecord> dbRecords = CourseRecordLocalServiceUtil.findByUserId(userId);
		log.info("found " + dbRecords.size() + " course records for user id " + userId);
			
		Map<String, String> reportRecord;
		Course course;
		Contributor courseAuthor;
		String courseAuthorName;
		for (CourseRecord dbRecord:dbRecords) {
			
			// get the course referenced by this record
			course = CourseLocalServiceUtil.fetchByCourseIri(dbRecord.getCourseIri());
			
			if (course != null) {
				
				courseAuthor = course.getCourseAuthor();
				if (courseAuthor != null){
					courseAuthorName = courseAuthor.getContributorName();
				}
				else {
					courseAuthorName = "none";
				}
				
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
				log.warn("Course record in persistence with IRI " + dbRecord.getCourseRecordIri() + " references " +
						" course with IRI " + dbRecord.getCourseIri() + ", which does not appear to exist");
			}
		}
		
		return reportData;
	}
}
