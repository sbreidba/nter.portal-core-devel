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


package org.nterlearning.course;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public class CourseUtil {

	public static void updateRemoved(
		ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long courseRecordId = ParamUtil.getLong(request, "courseRecordId");
		boolean removed = ParamUtil.getBoolean(request, "removed");
		long userId = themeDisplay.getUserId();
		CourseRecord record =
			CourseRecordLocalServiceUtil.getCourseRecord(courseRecordId);
		if (record == null) {
			throw new SystemException("Course Record with Id '" +
				courseRecordId + "' does not exist");
		}
		else if (record.getUserId() != userId) {
			throw new SystemException(
				"Users does not match.  Permission denied.");
		}
		record.setUserHidden(removed);
		CourseRecordLocalServiceUtil.updateCourseRecord(record);
	}

}