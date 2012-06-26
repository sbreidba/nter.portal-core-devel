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

package org.nterlearning.course.flag.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;
import org.nterlearning.utils.FlagReportConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;
import java.util.List;

public class FlagReportPortlet extends MVCPortlet {

    public void moderateReport(ActionRequest request, ActionResponse response)
            throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	long userId = themeDisplay.getUserId();
        ServiceContext serviceContext = ServiceContextFactory.getInstance(FlagReportLocalServiceUtil.class.getName(), request);

        long flagReportId = ParamUtil.getLong(request, "resourcePrimKey");
        String moderateAction = ParamUtil.getString(request, NterKeys.MODERATE_ACTION);
        String moderatorComment = ParamUtil.getString(request,NterKeys.MODERATOR_COMMENT);

       if (Validator.isNotNull(flagReportId) && (moderateAction .equals(FlagReportConstants.MODERATE_ACTION_REMOVE))) {
            _log.debug("Moderating flag report: " + flagReportId + ", action: " + moderateAction);
            FlagReportLocalServiceUtil.moderateFlagReport(userId, flagReportId, WorkflowConstants.STATUS_APPROVED, moderateAction, moderatorComment, serviceContext);
        } else if (Validator.isNotNull(flagReportId) && (moderateAction .equals(FlagReportConstants.MODERATE_ACTION_IGNORE))) {
                _log.debug("Moderating flag report: " + flagReportId + ", action: " + moderateAction);
                FlagReportLocalServiceUtil.moderateFlagReport(userId, flagReportId, WorkflowConstants.STATUS_DENIED, moderateAction, moderatorComment, serviceContext);
        } else {
            SessionErrors.add(request, "flag-error-moderating-report");
        }

    }

   public void moderateReportSet(ActionRequest request, ActionResponse response)
            throws Exception {

       ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
       long userId = themeDisplay.getUserId();
       ServiceContext serviceContext = ServiceContextFactory.getInstance(FlagReportLocalServiceUtil.class.getName(), request);

       long reviewId = ParamUtil.getLong(request, "reviewPrimKey");
       long classNameId = ParamUtil.getLong(request, "reviewClassNameId");
       String moderateAction = ParamUtil.getString(request, NterKeys.MODERATE_ACTION);
       String moderatorComment = ParamUtil.getString(request, NterKeys.MODERATOR_COMMENT);

       if (Validator.isNotNull(reviewId) && (Validator.isNotNull(classNameId))) {
           List<FlagReport> flagReports = FlagReportLocalServiceUtil.findByClassNameIdClassPK(classNameId, reviewId);

           for (FlagReport flagReport : flagReports) {

               if (!flagReport.isModerated() && moderateAction.equals(FlagReportConstants.MODERATE_ACTION_REMOVE)) {
                   _log.debug("Moderating flag report: " + flagReport.getPrimaryKey() + ", action: " + moderateAction);
                   FlagReportLocalServiceUtil.moderateFlagReport(userId, flagReport.getPrimaryKey(), WorkflowConstants.STATUS_APPROVED, moderateAction, moderatorComment, serviceContext);
                   // remove workflow instance since we used custom portlet for moderation instead of kaleo's
                   FlagReportLocalServiceUtil.removeWorkflowInstance(flagReport.getGroupId(), flagReport.getCompanyId(), FlagReport.class.getName(), flagReport.getFlagReportId());

               } else if (!flagReport.isModerated() && moderateAction.equals(FlagReportConstants.MODERATE_ACTION_IGNORE)) {
                   _log.debug("Moderating flag report: " + flagReport.getPrimaryKey() + ", action: " + moderateAction);
                   FlagReportLocalServiceUtil.moderateFlagReport(userId, flagReport.getPrimaryKey(), WorkflowConstants.STATUS_DENIED, moderateAction, moderatorComment, serviceContext);
                   // remove workflow instance since we used custom portlet for moderation instead of kaleo's
                   FlagReportLocalServiceUtil.removeWorkflowInstance(flagReport.getGroupId(), flagReport.getCompanyId(), FlagReport.class.getName(), flagReport.getFlagReportId());
               }

           }
       }
   }

    private void redirect(ActionRequest request, ActionResponse response) {
		String url = ParamUtil.getString(request, "redirect");
		if (!url.isEmpty()) {
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				_log.warn(e);
			}
		}
	}

    private static Log _log = LogFactoryUtil.getLog(FlagReportPortlet.class);

}
