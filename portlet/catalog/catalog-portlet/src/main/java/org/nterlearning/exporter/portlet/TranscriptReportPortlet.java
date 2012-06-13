/*
 National Training and Education Resource (NTER)
 Copyright (C) 2012  SRI International

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or (at
 your option) any later version.

 This program is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.
 */

package org.nterlearning.exporter.portlet;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;

import org.nterlearning.exporter.reports.LastUsersLoggedIn;
import org.nterlearning.exporter.reports.StudentTranscript;

import javax.portlet.*;
import java.io.IOException;

public class TranscriptReportPortlet extends MVCPortlet {


    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response)
            throws IOException, PortletException {

        byte pdfData[] = null;
        long userId = GetterUtil.getLong(request.getParameter("userId"));
        int lastUserCount = GetterUtil.getInteger(request.getParameter("lastUserCount"));

        if (Validator.isNotNull(userId)) {
            StudentTranscript transcript = new StudentTranscript();
            pdfData = transcript.exportAsPdf(userId, request.getLocale(), request.getServerName());
        }
        else if (Validator.isNotNull(lastUserCount)) {
            long companyId = Long.valueOf(request.getPreferences().getValue("companyId", "0"));
            LastUsersLoggedIn userList = new LastUsersLoggedIn();
            pdfData = userList.exportAsPdf(companyId, lastUserCount, request.getLocale(), request.getServerName());
        }

        response.reset();
        response.setContentType("application/pdf");
        response.setContentLength(pdfData.length);
        response.getPortletOutputStream().write(pdfData);
        response.getPortletOutputStream().flush();
        response.getPortletOutputStream().close();
    }
}
