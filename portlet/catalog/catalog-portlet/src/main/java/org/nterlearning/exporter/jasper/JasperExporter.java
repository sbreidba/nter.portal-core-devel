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

package org.nterlearning.exporter.jasper;

import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


public class JasperExporter {
	
	private static Log log = LogFactoryUtil.getLog(JasperExporter.class);
	
	/**
	 * 
	 * @param jasperReportPath - the path to the jrxml report
	 * @param pdfPath - the path where the PDF report should be created
	 * @param params - report parameters to fill in
	 * @param data - report data to fill in
	 */
	public static void exportPdf(String jasperReportPath, String pdfPath, Map<String,Object> params, 
			Collection<Map<String, ?>> data){
		
		try {
			JasperReport report = JasperCompileManager.compileReport(jasperReportPath);
			JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
			exportPdf(report,pdfPath,params,dataSource);
		}
		catch (JRException e) {
			log.error("Error compiling PDF report at " + jasperReportPath + ": " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param report - the report object
	 * @param pdfPath - the path where the PDF report should be created
	 * @param params - report parameters to fill in
	 * @param dataSource - the data source to use to fill in the report
	 */
	public static void exportPdf(JasperReport report, String pdfPath, Map<String,Object> params, JRDataSource dataSource){
		
		try {
			
			JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
			JasperExportManager.exportReportToPdfFile(print, pdfPath);
		}
		catch (Exception e) {
			log.error("Error exporting PDF report to " + pdfPath + ": " + e);
			e.printStackTrace();
		}
	}

}
