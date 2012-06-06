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


package org.nterlearning.course.current.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import org.nterlearning.course.CourseUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Handles the Current-Courses portlet process actions. Offers the following
 * actions.
 * <ul>
 * <li>updateUserHidden</li>
 * <li>updateRemoved</li>
 * </ul>
 *
 */
public class CurrentCoursePortlet extends MVCPortlet {

	public void updateRemoved(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {
		CourseUtil.updateRemoved(request, response);
	}

	private static final Log _log = LogFactoryUtil
			.getLog(CurrentCoursePortlet.class);

}