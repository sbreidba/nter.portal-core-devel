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


package org.nterlearning.crawl.nutch.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.util.bridges.mvc.MVCPortlet;

import org.nterlearning.crawl.nutch.CrawlTool;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;


public class NutchCrawlerPortlet extends MVCPortlet {

    private CrawlTool mCrawlTool;

    // timer properties
    private String mTimerName = "Nutch Crawl Timer";

    private static Log mLog = LogFactoryUtil.getLog(NutchCrawlerPortlet.class);

    @Override
    public void init() throws PortletException {

        mCrawlTool = CrawlTool.getInstance();

        String threads = getInitParameter("threadsLimit");
        String depth = getInitParameter("depthLimit");
        String nutchCrawlTimer = getInitParameter("crawlTimer");

        if (threads != null) {
            mCrawlTool.setMaxThreads(Integer.valueOf(threads));
        }

        if (depth != null) {
            mCrawlTool.setMaxDepth(Integer.valueOf(depth));
        }

        if (nutchCrawlTimer != null) {
            mCrawlTool.setCrawlIntervalTimer(Long.valueOf(nutchCrawlTimer));
        }

        // start the task timer
        initTimerTask();

        super.init();
    }

    @Override
    public void destroy() {
        destroyTimerTask();
        super.destroy();
    }

    /**
     * Enables the Nutch timer task to run every 'x' minutes, based on the
     * value found in the <code>liferay-portal.xml</code> file.
     */
    private void initTimerTask() {
        if (mCrawlTool.isMaster()) {
            mLog.info("Starting " + mTimerName + " to run every " +
                    mCrawlTool.getCrawlIntervalMinutes() + " minutes.");
            mCrawlTool.initiateCrawlSchedule();
        }
    }

    /**
     * Cancels the timer task.
     */
    private void destroyTimerTask() {
        if (mCrawlTool.isMaster()) {
            mLog.info("Stopping " + mTimerName);
            mCrawlTool.shutdownTasks();
        }
    }

    /**
     * Forces a crawl of all existing courses and components in the system.
     *
     * @param request HTTP Request handler
     * @param response HTTP Response handler
     *
     * @throws Exception Standard Liferay exception
     */
    public void forceCrawl(ActionRequest request, ActionResponse response) {

        if (!mCrawlTool.isMaster()) {
            SessionErrors.add(request, "crawl-cp-master-node-error");
            return;
        }

        try {
            List<Course> courses = CourseLocalServiceUtil.findAllValidCourses();
            for (Course course : courses) {
                if (!course.isRemoved()) {
                    mCrawlTool.addCourse(course);
                }
            }
        }
        catch (Exception e) {
            mLog.error("Could not generate valid course list for crawl tool.");
        }

        try {
            List<Component> components =
                    ComponentLocalServiceUtil.getComponents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (Component component : components) {
                if (!component.isRemoved()) {
                    mCrawlTool.addCourseComponent(component);
                }
            }
        }
        catch (Exception e) {
            mLog.error("Could not generate valid component list for crawl tool.");
        }

        mLog.info("Initiating manual nutch crawl...");
        mCrawlTool.runCrawl();
    }


    /**
     * Purges the index of all data.
     *
     * @param request HTTP Request handler
     * @param response HTTP Response handler
     */
    public void purgeIndex(ActionRequest request, ActionResponse response) {
        if (!mCrawlTool.isMaster()) {
            SessionErrors.add(request, "crawl-cp-master-node-purge-error");
            return;
        }

        mCrawlTool.purgeIndex();
    }
}