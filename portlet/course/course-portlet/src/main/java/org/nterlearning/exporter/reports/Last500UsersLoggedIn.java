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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.nterlearning.exporter.ReportExporter;

/**
 * 
 * @author gjiva
 *
 */
public class Last500UsersLoggedIn extends AbstractReport{
	
	public static final String PARAM_KEY_TRANSCRIPT_CREATE_DATE = "reportCreationDate";
	public static final String PARAM_KEY_NTER_ADDRESS = "nterServerAddress";
	public static final String PARAM_KEY_NUMBER_RECORDS = "numberOfRecords";
	public static final String RECORD_KEY_STUDENT_NAME = "studentName";
	public static final String RECORD_KEY_LAST_LOGIN_DATETIME = "lastLoginDateTime";
	
	// TODO: if needed, make these configurable
	private static final String REPORT_NAME = "last-500-users-logged-in";
	
	private static Log log = LogFactoryUtil.getLog(Last500UsersLoggedIn.class);
	
	private long companyId;
	
	public Last500UsersLoggedIn(long companyId){
		this.companyId = companyId;
	}

	@Override
	public Log getLogger() {
		return log;
	}

	@Override
	public String getReportName() {
		return REPORT_NAME;
	}

	@Override
	public Collection<Map<String, ?>> getReportData() {
		Collection<Map<String,?>> reportData = new Vector<Map<String,?>>();
		try {
			List<User> users = UserLocalServiceUtil.getCompanyUsers(companyId, 0, 
					UserLocalServiceUtil.getCompanyUsersCount(companyId));
			List<User> usersCopy = new Vector<User>(users.size());
			usersCopy.addAll(users);
			Collections.sort(usersCopy, new UserLastLoginComparator());

			Vector<User> last500;
			if (usersCopy.size() > 500){
				last500 = new Vector<User>(usersCopy.subList(usersCopy.size()-500, usersCopy.size()-1));
			}
			else {
				last500 = new Vector<User>(usersCopy);
			}
			
			Map<String, String> reportRecord;
			for (User user:last500){
				
				String lastLogin = "never";
				if (user.getLastLoginDate() != null){
					lastLogin = user.getLastLoginDate().toString();
				}
				
				String userName = user.getFullName();
				if (user.getFullName() == null || user.getFullName() == ""){
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
			throw new RuntimeException("Error getting report data: " + e,e);
		}
	}

	@Override
	public Map<String, Object> getReportParams(String host, Collection<Map<String, ?>> reportData) {
		Map<String,Object> reportParams = new HashMap<String, Object>();
		
		reportParams.put(PARAM_KEY_TRANSCRIPT_CREATE_DATE, (new Date(System.currentTimeMillis())).toString());
		reportParams.put(PARAM_KEY_NTER_ADDRESS, host);
		reportParams.put(PARAM_KEY_NUMBER_RECORDS, reportData.size());
		
		return reportParams;
	}
	
	/**
	 * 
	 * @author gjiva
	 *
	 */
	public class UserLastLoginComparator implements Comparator<User>{
		
		public int compare(User u1, User u2){
			
			Date d1 = u1.getLastLoginDate();
			Date d2 = u2.getLastLoginDate();
			
			// treat nulls as zeros, because... you have a better idea?
			if (d1 == null){
				d1 = new Date(0);
			}
			
			if (d2 == null){
				d2 = new Date(0);
			}
			
			// do the comparing
			if (d1.before(d2)){
				return -1;
			}
			else if (d1.equals(d2)){
				return 0;
			}
			else if (d1.after(d2)){
				return 1;
			}
			else {
				throw new RuntimeException("Date [" + d1 + "] is not before, equal to or after [" + d2 + "]");
			}
		}
		
		public boolean equals(User u1, User u2){
			
			Date d1 = u1.getLastLoginDate();
			Date d2 = u2.getLastLoginDate();
			
			return d1.equals(d2);
		}
	}
	
	

}
