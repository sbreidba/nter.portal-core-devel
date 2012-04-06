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


package org.nterlearning.crawl.nutch;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;

import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.nterlearning.utils.PortalProperties;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.util.NutchConfiguration;

/**
 * This class is designed to create the various configurations needed by the
 * crawl and search Nutch components.
 */
public class Config {

    private static Log log = LogFactoryUtil.getLog(CrawlTool.class);

    private final static String KEY = Config.class.getName();

    /**
     * Creates the default configuration for a crawl operation.
     *
     * @return Default crawl configuration
     */
    public static Configuration createCrawlConfiguration() {

        String nutchHome = getNutchHomeDir();
        String companyName = "NTER";

        try {
            companyName = PrefsPropsUtil.getString(PropsKeys.COMPANY_DEFAULT_WEB_ID);
        }
        catch (Exception e) {
            log.error("Could not load properties from portal-ext.properties");
        }

        String nutchDataDir = getNutchDataDir();
        String hadoopDir = getNutchHomeDir() + "/hadoop";

        Configuration config = NutchConfiguration.create();
        config.addResource("META-INF/nutch-default.xml");
        config.addResource("META-INF/nutch-site.xml");
        config.addResource("META-INF/mapred-site.xml");

        // nter settings
        config.set(PortalProperties.NUTCH_HOME_DIR_PROPERTY, nutchHome);
        config.set(PortalProperties.NUTCH_DATA_DIR_PROPERTY, nutchDataDir);

        // injector settings
        config.set("hadoop.log.dir", hadoopDir + "/logs");
        config.set("hadoop.log.file", hadoopDir + "/logs/hadoop.log");
        config.set("hadoop.tmp.dir", hadoopDir + "/tmp");

        config.set("http.agent.description", companyName + " crawler");
        config.set("http.agent.host", companyName);
        config.set("http.agent.name", companyName);
        config.set("http.robots.agents", companyName + ",*");

        config.set("mapred.child.tmp", nutchDataDir + "/tmp");
        config.set("mapred.job.tracker.persist.jobstatus.dir", nutchDataDir + "/jobtracker/jobsInfo");
        config.set("mapred.temp.dir", nutchDataDir + "/tmp");

        // plugins
        config.set("plugin.folders", nutchHome + "/plugins");
        config.set("protocol.plugin.check.robots", "true");

        config = configureSolrSecurity(config);

        // for testing/debugging
        /*
        config.set("http.verbose", "true");
        config.set("fetcher.verbose", "true");
        */

        return config;
    }


    /**
     * Creates the default configuration for a search operation.
     *
     * @return Default search configuration
     */
    public static Configuration createSearchConfiguration() {

        String nutchHome = getNutchHomeDir();
        String nutchDataDir = getNutchDataDir();
        String companyName = "NTER";

        try {
            companyName = PrefsPropsUtil.getString(PropsKeys.COMPANY_DEFAULT_WEB_ID);
        }
        catch (Exception e) {
            log.error("Could not find property: company.default.web.id.  Using '" + companyName +"'.");
        }

        Configuration config = NutchConfiguration.create();
        config.addResource("META-INF/nutch-default.xml");
        config.addResource("META-INF/nutch-site.xml");
        config.addResource("META-INF/mapred-site.xml");

        // nter settings
        config.set(PortalProperties.NUTCH_HOME_DIR_PROPERTY, nutchHome);
        config.set(PortalProperties.NUTCH_DATA_DIR_PROPERTY, nutchDataDir);

        config.set("http.agent.host", companyName);
        config.set("http.agent.name", companyName);

        config.set("plugin.folders", nutchHome + "/plugins");

        return config;
    }


    /**
     * Create a {@link Configuration} for Nutch front-end.
     *
     * If a {@link Configuration} is found in the
     * {@link javax.servlet.ServletContext} it is simply returned, otherwise,
     * a new {@link Configuration} is created, and then all the init parameters
     * found in the {@link javax.servlet.ServletContext} are added to the
     * {@link Configuration} (the created {@link Configuration} is then saved
     * into the {@link javax.servlet.ServletContext}).
     *
     * @param application is the ServletContext whose init parameters
     *        must override those of Nutch.
     *
     * @return Configuration based on the application
     */
    public static Configuration get(ServletContext application) {
        Configuration conf = (Configuration)application.getAttribute(KEY);

        if (conf == null) {
            conf = createSearchConfiguration();

            Enumeration e = application.getInitParameterNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                conf.set(name, application.getInitParameter(name));
            }

            application.setAttribute(KEY, conf);
        }

        return conf;
    }


    /**
     * Returns the default liferay home directory.
     *
     * @return Liferay home
     */
    private static String getLiferayHome() {
        try {
            return PrefsPropsUtil.getString(PropsKeys.LIFERAY_HOME);
        }
        catch (Exception e) {
            return ".";
        }
    }


    /**
     * Returns the default home directory to be used by Nutch.  This directory
     * is based on the value set for <code>nter.nutch.home.dir</code> in the
     * <code>portal-ext.properties</code> file.
     *
     * @return Default nutch home directory
     */
    public static String getNutchHomeDir() {
        try {
            return PrefsPropsUtil.getString(PortalProperties.NUTCH_HOME_DIR_PROPERTY);
        }
        catch (Exception e) {
            log.error("Could not find default nutch home directory property. Using ["
                      + getLiferayHome() + "/nutch]");
            return getLiferayHome() + "/nutch";
        }
    }


    /**
     * Returns the default data directory to be used by Nutch.  This directory
     * is based on the value set for <code>nter.nutch.home.dir</code> in the
     * <code>portal-ext.properties</code> file, with <code>/data</code> appended
     * to it.
     *
     * @return Default data directory.
     */
    public static String getNutchDataDir() {
        return getNutchHomeDir() + "/data";
    }


    /**
     * Reads the Solr authorization information from the portal-ext.properties
     * file and configures Nutch accordingly.
     *
     * @param conf Nutch Configuration file
     *
     * @return Updated configuration file
     */
    private static Configuration configureSolrSecurity(Configuration conf) {
        String solrUser = null;
        String solrPassword = null;

        try {
            solrUser = PrefsPropsUtil.getString(PortalProperties.SOLR_USER_PROPERTY);
            solrPassword = PrefsPropsUtil.getString(PortalProperties.SOLR_PASSWORD_PROPERTY);
        }
        catch (Exception e) {
            log.error("Could not read Solr configuration information from portal-ext.properties");
        }

        if ((solrUser.isEmpty()) || (solrPassword.isEmpty())) {
            conf.set(NutchConstants.SOLR_AUTH_PROPERTY, "false");
        }
        else {
            conf.set(NutchConstants.SOLR_AUTH_PROPERTY, "true");
            conf.set(NutchConstants.SOLR_AUTH_USER_PROPERTY, solrUser);
            conf.set(NutchConstants.SOLR_AUTH_PASSWORD_PROPERTY, solrPassword);
        }

        return conf;
    }
}