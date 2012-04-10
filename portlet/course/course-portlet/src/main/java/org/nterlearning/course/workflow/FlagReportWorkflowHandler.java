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

package org.nterlearning.course.workflow;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

/**
 * workflow handling of flag report created to record inappropriate content
 */
public class FlagReportWorkflowHandler extends BaseWorkflowHandler {
    public String getClassName() {
        return CLASS_NAME;
    }

    public String getType(Locale locale) {
        return LanguageUtil.get(locale, "model.resource." + CLASS_NAME);
    }

    public Object updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException, SystemException {

        long userId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

        long resourcePrimKey = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

        ServiceContext serviceContext = (ServiceContext) workflowContext.get("serviceContext");

        return FlagReportLocalServiceUtil.updateStatus(userId, resourcePrimKey, status, serviceContext);
    }

    public static final String CLASS_NAME = FlagReport.class.getName();
}
