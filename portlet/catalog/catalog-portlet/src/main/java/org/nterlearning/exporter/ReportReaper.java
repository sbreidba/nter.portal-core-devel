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
package org.nterlearning.exporter;

import java.io.File;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.utils.ExecutorUtil;

public class ReportReaper {

	private static Log log = LogFactoryUtil.getLog(ReportReaper.class);

    // executor and timer for thread pooling
    private static ScheduledExecutorService exec;
    public static final int REAPER_INTERVAL_MIN = 5;

    // singleton to prevent multiple reapers from being generated
    private static ReportReaper mReaper = new ReportReaper();


	private ReportReaper() {
        exec = Executors.newSingleThreadScheduledExecutor();
	}


    public static ReportReaper getInstance() {
        return mReaper;
    }

    public void startReaping(){
        log.info("Starting the Report Reaper and setting it to run every " +
                REAPER_INTERVAL_MIN + " minutes.");

        ExecutorUtil.safeSubmitFixedDelay(exec, new ReaperProcess(),
                REAPER_INTERVAL_MIN, REAPER_INTERVAL_MIN, TimeUnit.MINUTES);
    }


    public void stopReaping(){
        log.info("Shutting down ReportReaper service.");
        exec.shutdown();
        exec.shutdownNow();
    }


    /**
     * This class is used by the automatic ReportReaper to remove the old pdf files.
     */
    private class ReaperProcess implements Runnable {

        File pdfDir;

        public ReaperProcess() {
            pdfDir = new File(ReportExporter.getPdfReportDirPath());
        }

        public void run() {

            log.debug("Running report reaper");

            if (pdfDir.isDirectory()){

                Date timeOfLastReaping =
                        new Date(System.currentTimeMillis() - REAPER_INTERVAL_MIN*60*1000);
                Date dirLastModified;
                File[] tempDirs = pdfDir.listFiles();

                for (File tempDir:tempDirs){
                    dirLastModified = new Date(tempDir.lastModified());
                    if (dirLastModified.before(timeOfLastReaping)){

                        // empty out the directory, then delete it
                        File[] reports = tempDir.listFiles();
                        for (File report:reports){
                            if (!report.delete()) {
                                log.error("Could not delete PDF report file at " + report.getAbsolutePath());
                            }
                        }
                        if (!tempDir.delete()) {
                            log.error("Could not delete PDF report directory at " + tempDir.getAbsolutePath());
                        }
                    }
                }
            }
            else {
                log.error("The PDF report directory path is not a valid directory: [" + pdfDir.getAbsolutePath() +
                        "]. Make sure the directory is either set in the portal-ext.properties file or that " +
                        "the CATALINA_BASE environment variable is set.");
            }

            log.debug("Report reaping complete.");
        }
    }
}
