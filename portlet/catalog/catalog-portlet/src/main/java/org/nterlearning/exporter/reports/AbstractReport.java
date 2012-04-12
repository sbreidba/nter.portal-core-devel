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
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import org.nterlearning.exporter.ReportExporter;


public abstract class AbstractReport {
	
	/**
	 * Exports a student's course records into a PDF file
	 * 
	 * @return
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 */
	public String exportToPdf(String host)
		throws SystemException, PortalException{

		this.getLogger().info("Creating '" + this.getReportName() + "' report");

		// put the report data into the formats required
		Collection<Map<String,?>> reportData = getReportData();
		Map<String,Object> reportParams = getReportParams(host, reportData);

		return ReportExporter.exportReportToPdf(this.getReportName(), reportParams, reportData);
	}
	
	/**
	 * Exports a student's course records into a PDF file, then redirects the response to the URL of the PDF
	 * 
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 */
	public void exportToPdfAndRedirect(ActionRequest request, ActionResponse response)
		throws SystemException, PortalException{
		
		ReportExporter.redirectToReportUrl(exportToPdf(request.getServerName()), request, response);
	}
	
	public abstract Log getLogger();
	
	public abstract String getReportName();
	
	public abstract Collection<Map<String,?>> getReportData();
	
	public abstract Map<String,Object> getReportParams(String host, Collection<Map<String, ?>> reportData);

}
