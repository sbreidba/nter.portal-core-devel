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

package org.nterlearning.exporter.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.apache.commons.lang.Validate;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.nterlearning.exporter.ReportReaper;
import org.nterlearning.exporter.reports.Last500UsersLoggedIn;
import org.nterlearning.exporter.reports.StudentTranscript;


public class ReportsPortlet extends MVCPortlet{
	
	private static Log log = LogFactoryUtil.getLog(ReportsPortlet.class);
	
	public static final String PARAM_USER_EMAIL = "reports-user-email";


    @Override
    public void init() throws PortletException {
        ReportReaper.getInstance().startReaping();
        super.init();
    }


    @Override
    public void destroy() {
        ReportReaper.getInstance().stopReaping();
        super.destroy();
    }


    /**
	 * 
	 * @param request
	 * @param response
	 */
	public void exportPdfTranscript(ActionRequest request, ActionResponse response){
		
		try {
			String userEmail = request.getParameter(PARAM_USER_EMAIL);

			if ((userEmail == null) || userEmail.equals("")) {
				log.error("The user email field is empty");
			    return;
			}
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long userId;
			//userId = themeDisplay.getUserId(); // get the current user's ID
			
			User user = UserLocalServiceUtil.getUserByEmailAddress(
					themeDisplay.getCompanyId(), userEmail); // get an email's user ID
			Validate.notNull(user, "Could not find a user with email address [" + userEmail + "]");
			userId = user.getUserId();
			
			StudentTranscript.exportToPdfAndRedirect(userId, request.getLocale(),request,response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void exportPdfLast500Users(ActionRequest request, ActionResponse response){
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Last500UsersLoggedIn last500 = new Last500UsersLoggedIn(themeDisplay.getCompanyId());
		try {
			last500.exportToPdfAndRedirect(request,response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
