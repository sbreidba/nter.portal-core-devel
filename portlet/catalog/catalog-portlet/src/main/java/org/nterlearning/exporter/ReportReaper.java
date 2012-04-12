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

public class ReportReaper implements Runnable {
	
	public static final int REAPER_INTERVAL_MIN = 5;
	
	private static Log log = LogFactoryUtil.getLog(ReportReaper.class);
	private static ScheduledExecutorService exec;

	/**
	 * 
	 */
	public ReportReaper() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		log.debug("Running report reaper");
		
		File pdfDir = new File(ReportExporter.getPdfReportDirPath());
		if (pdfDir.isDirectory()){
			
			Date timeOfLastReaping = new Date(System.currentTimeMillis() - REAPER_INTERVAL_MIN*60*1000);
			Date dirLastModified;
			File[] tempDirs = pdfDir.listFiles();
			
			for (File tempDir:tempDirs){
				dirLastModified = new Date(tempDir.lastModified());
				// we only want to delete older files and leave more recent ones alone, so only delete ones
				// that were created before our last reaping
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
		
		log.debug("Report reaping complete, for now...");
	}
	
	/**
	 * 
	 */
	public static void startReaping(){
		
		exec = Executors.newSingleThreadScheduledExecutor();
    	log.info("Starting the Report Reaper and setting it to run every " + REAPER_INTERVAL_MIN + " minutes.");

    	ExecutorUtil.safeSubmitFixedDelay(exec, new ReportReaper(),
    			REAPER_INTERVAL_MIN, REAPER_INTERVAL_MIN, TimeUnit.MINUTES);
	}
	
	/**
	 * 
	 */
	public static void stopReaping(){
		
		exec.shutdown();
	}

}
