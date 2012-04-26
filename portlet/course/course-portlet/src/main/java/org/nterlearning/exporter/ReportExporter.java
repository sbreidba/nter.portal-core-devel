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

package org.nterlearning.exporter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.lang.RandomStringUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import org.nterlearning.exporter.jasper.JasperExporter;
import org.nterlearning.utils.PortalProperties;

/**
 * 
 * @author gjiva
 *
 */
public class ReportExporter {
	
	private static Log log = LogFactoryUtil.getLog(ReportExporter.class);
	
	// TODO: if needed, make these configurable
	private static final String WEB_DIR_NAME = "webapps";
	private static String WEB_DIR_PATH = System.getenv("CATALINA_BASE") + File.separatorChar + WEB_DIR_NAME + "/";
	private static final String DEFAULT_PDF_REPORT_DIR_PATH = WEB_DIR_PATH + "course-portlet/reports/pdf/";
	private static final String DEFAULT_JRXML_REPORT_DIR_PATH = WEB_DIR_PATH + "course-portlet/reports/jrxml/";

	/**
	 * 
	 * @param pdfReportDirPath -- the output directory where reports go
	 * @param pdfReportFileName -- without the extension
	 * @param jrxmlReportPath - the path to the existing Jasper XML report template
	 * @param reportParams
	 * @param reportData
	 * @return
	 */
	public static String exportReportToPdf(String pdfReportDirPath, String pdfReportFileName, String jrxmlReportPath,
			Map<String,Object> reportParams, Collection<Map<String,?>> reportData){
		
		log.info("Exporting a report to PDF");
		
		// create the file paths needed
		String randomDirName = RandomStringUtils.randomAlphanumeric(10);
		File randomDir = new File(pdfReportDirPath + File.separator + randomDirName);// for security
		if (randomDir.mkdir()) {
			String pdfPath = randomDir.getAbsolutePath() + File.separator +	pdfReportFileName + ".pdf";
			//TODO: make sure a cron job somewhere periodically purges the transcript dir

			// create the report
			JasperExporter.exportPdf(jrxmlReportPath, pdfPath, reportParams, reportData);
			log.info("Report has been exported to a PDF file at: " + pdfPath);
			return pdfPath;
		}
		else {
			throw new RuntimeException("Could not create random report directory: " + randomDir.getAbsolutePath());
		}
	}
	
	/**
	 * Uses defaults for the output report and the input JRXML report directories
	 * 
	 * @param pdfReportFileName -- without the extension
	 * @param jrxmlFileName -- without the extension
	 * @param reportParams
	 * @param reportData
	 * @return
	 */
	public static String exportReportToPdf(String pdfReportFileName, String jrxmlFileName,
			Map<String,Object> reportParams, Collection<Map<String,?>> reportData){
		
		return exportReportToPdf(getPdfReportDirPath(),pdfReportFileName,
				getJrxmlReportDirPath() + jrxmlFileName + ".jrxml",reportParams,reportData);
	}
	
	/**
	 * Uses defaults for directories, and the same name for both PDF and JRXML reports
	 * 
	 * @param reportName
	 * @param reportParams
	 * @param reportData
	 * @return
	 */
	public static String exportReportToPdf(String reportName,
			Map<String,Object> reportParams, Collection<Map<String,?>> reportData){
		
		return exportReportToPdf(reportName,reportName,reportParams,reportData);
	}
	
	/**
	 * 
	 * @param reportPath
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	public static void redirectToReportUrl(String reportPath, ActionRequest request, ActionResponse response)
		throws PortalException, SystemException{
		
		// extract the transcript path inside the web directory
		int webDirIndex = reportPath.indexOf(WEB_DIR_NAME);
		if (webDirIndex > 0) {
			String reportWebDirPath = reportPath.substring(webDirIndex + WEB_DIR_NAME.length()+1);
			String transcriptUrl = getServerUrl(request) + reportWebDirPath;
			transcriptUrl = transcriptUrl.replace("\\", "/"); // replace any Windows backslashes
			log.info("Redirecting user to report at URL [" + transcriptUrl + "]");
			try {
				response.sendRedirect(transcriptUrl);
			}
			catch (IOException e) {
				throw new PortalException(
						"Error redirecting to report: " + e, e);
			}
		}
		else {
			throw new SystemException("Error parsing the report path: it does not contain the web directory, " +
					WEB_DIR_NAME + ". Report path: " + reportPath);
		}
	}
	
	/**
	 * 
	 * @param reportDirPath
	 * @param reportFileName
	 * @param jasperReportPath
	 * @param reportParams
	 * @param reportData
	 * @param response
	 * @throws com.liferay.portal.kernel.exception.PortalException
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	public static void exportReportToPdfAndRedirect(String reportDirPath, String reportFileName, 
			String jasperReportPath, Map<String,Object> reportParams, 
			Collection<Map<String,?>> reportData, ActionRequest request, ActionResponse response)
		throws PortalException, SystemException{
		
		redirectToReportUrl(exportReportToPdf(reportDirPath, reportFileName, jasperReportPath, 
				reportParams, reportData), request, response);
	}
	
	public static String getServerUrl(ActionRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	}

	/**
	 * 
	 * @return
	 */
	public static String getPdfReportDirPath() {
		
		String url = PropsUtil.get(PortalProperties.PDF_REPORT_DIR);

        if ((url == null) || url.equals("")) {
        	log.info("The PDF report dir key, " + PortalProperties.PDF_REPORT_DIR + ", is undefined. " +
        			"Using the default PDF reports path instead: " + DEFAULT_PDF_REPORT_DIR_PATH);
        	url = DEFAULT_PDF_REPORT_DIR_PATH;
        }

        return url;
	}

	/**
	 * 
	 * @return
	 */
	public static String getJrxmlReportDirPath() {
		String url = PropsUtil.get(PortalProperties.JRXML_REPORT_DIR);

        if ((url == null) || url.equals("")) {
        	log.info("The JRXML report dir key, " + PortalProperties.JRXML_REPORT_DIR + ", is undefined. " +
        			"Using the default JRXML reports path instead: " + DEFAULT_JRXML_REPORT_DIR_PATH);
        	url = DEFAULT_JRXML_REPORT_DIR_PATH;
        }

        return url;
	}
}
