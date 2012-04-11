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

package org.nterlearning.course.archived.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.nterlearning.course.CourseUtil;
//import org.nterlearning.exporter.reports.StudentTranscript;

public class ArchivedCoursesPortlet extends MVCPortlet {
	
	public void updateRemoved(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		CourseUtil.updateRemoved(request, response);
	}
	
	/**
	 * Exports the logged in student's course records to a PDF file, then redirects the portal to it
	 * 
	 * @param request
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	public void exportStudentTranscriptToPdf(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId(); // get the current user's ID
		
//		StudentTranscript.exportToPdfAndRedirect(userId, request.getLocale(),request, response);
	}

}