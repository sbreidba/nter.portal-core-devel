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


package org.nterlearning.course.management.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.course.CoursePopularityProcessor;
import org.nterlearning.utils.ExecutorUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * This class is designed to manage the CourseManagement Tasks which update the
 * various course popularity weights.  It is designed as a singleton to prevent
 * multiple instances from running concurrently.  Additionally, it uses a
 * ScheduledExecutor to manage the timing.
 */
public class CourseManagementTask {

    private static Log log = LogFactoryUtil.getLog(CourseManagementTask.class);

    private static CourseManagementTask mInstance = new CourseManagementTask();

    private volatile ScheduledExecutorService mExecutor;

    private static final long COURSE_POPULARITY_UPDATE_PERIOD = 86400000;   // every 24 hours
    private static final int MILLISEC_PER_HOUR = 3600000;

    private CourseManagementTask() {
        mExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    public static CourseManagementTask getInstance() {
        return mInstance;
    }

    /**
     * Adds the CourseManagement tasks to the scheduled executor.  The task is
     * designed to run at 3am every morning.
     */
    public void initiateScheduledTask() {
        long offset = getOffset();

        log.info("Scheduling the Course Management Task.");
        ExecutorUtil.safeSubmitFixedRate(mExecutor, new CourseManagementProcess(), offset,  COURSE_POPULARITY_UPDATE_PERIOD);
    }

    /**
     * Adds the CourseManagement tasks to the scheduled exetor to be run
     * immediately.
     */
    public void runTask() {
        log.info("Starting a manual execution of the CourseManagement task.");
        ExecutorUtil.safeSubmit(mExecutor, new CourseManagementProcess());
    }

    /**
     * Shuts down the feed parser tasks and prevents any other tasks from being
     * started.  Ensure that this is called before garbage collection, or a
     * thread will leak.
     */
    public void shutdownTask() {
        log.info("Stopping Course Management Task.");
        mExecutor.shutdown();
        mExecutor.shutdownNow();
    }

    /**
     * Calculates the number of milliseconds from now, to the next 3am.  If it
     * is already after 3am, then the time until tomorrow's 3am is calculated.
     *
     * @return  The number of milliseconds to the next 3am.
     */
    private long getOffset() {
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.HOUR_OF_DAY) > 3) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        log.info("Proposed initial run of CourseManagement Task will be at " + calendar.getTime());

        Date now = new Date();

        return calendar.getTimeInMillis() - now.getTime();
    }

    /**
     * This class runs the actual course management task and updates the
     * various course popularity weights.
     */
    private class CourseManagementProcess implements Runnable {
        public void run() {
            log.info("Assigning course accessCount, completedCount, and popularWeight");
            try {

                double accessCountWeight = CourseManagementPortlet.getAccessCountWeight();
                double completedCountWeight = CourseManagementPortlet.getCompletedCountWeight();
                double averageScoreWeight = CourseManagementPortlet.getAverageScoreWeight();

                log.info("accessCountWeight=" + accessCountWeight + " completedCountWeight=" + completedCountWeight +
                         " averageScoreWeight" + averageScoreWeight);

                if (accessCountWeight != 0.0 && completedCountWeight != 0.0 && averageScoreWeight != 0.0) {
                    CoursePopularityProcessor.processCoursePopularity(accessCountWeight, completedCountWeight, averageScoreWeight);
                }
            }
            catch (Exception e) {
                log.error("Problem assigning course accessCount, completedCount, and popularWeight");
                log.error(e.getMessage());
            }
        }
    }
}