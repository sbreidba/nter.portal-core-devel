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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

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
     * Creates a PDF report based on the given JRXML template and stores it in the given directory.
     * @param jasperReportPath - the path to the jrxml report
     * @param pdfPath          - the path where the PDF report should be created
     * @param params           - report parameters to fill in
     * @param data             - report data to fill in
     */
    @Deprecated
    public static void exportPdfToFile(String jasperReportPath, String pdfPath,
            Map<String, Object> params, Collection<Map<String, ?>> data) {

        try {
            JasperReport report = JasperCompileManager.compileReport(jasperReportPath);
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
            JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
            JasperExportManager.exportReportToPdfFile(print, pdfPath);
        }
        catch (JRException e) {
            log.error("Error compiling PDF report at " + jasperReportPath + ": " + e);
            e.printStackTrace();
        }
    }


    /**
     * Generates a PDF stream based on the template file, parameters, and data.
     *
     * @param reportTemplate Name of the template file. This file must be found under the META-INF
     * directory.
     * @param params JasperReport parameters
     * @param data dataset to export
     */
    public byte[] exportAsPdfStream(String reportTemplate, Map<String, Object> params,
            Collection<Map<String, ?>> data) {

        InputStream reportTemplateStream =
                this.getClass().getResourceAsStream("/META-INF/" + reportTemplate);
        ByteArrayOutputStream reportOutput = new ByteArrayOutputStream();

        try {
            JasperReport jrReport = JasperCompileManager.compileReport(reportTemplateStream);
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
            JasperPrint print = JasperFillManager.fillReport(jrReport, params, dataSource);
            JasperExportManager.exportReportToPdfStream(print, reportOutput);
            reportOutput.flush();
            reportOutput.close();
        }
        catch (JRException jre) {
            log.error("Error compiling PDF report: " + jre.getMessage());
        }
        catch (IOException ioe) {
            log.error("Error closing export stream: " + ioe.getMessage());
        }

        return  reportOutput.toByteArray();
    }
}
