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

package org.nterlearning.atom.parser;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.*;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.nterlearning.atom.enumerations.NterFeedType;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.portlet.ActionRequest;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class provides a number of tools and utilities for working with the
 * Liferay ServiceContext object.
 */
public class ServiceContextUtil {

    private static Log mLog = LogFactoryUtil.getLog(ServiceContextUtil.class);


    /**
     * Generates a default ServiceContext object.  This should only be done
     * when the ServiceContext can not be pulled from an ActionRequest.
     *
     * @return Default ServiceContext
     */
    public static ServiceContext createDefaultServiceContext() {

        long companyId = getDefaultCompanyId();

        ServiceContext sc = new ServiceContext();
        sc.setAddCommunityPermissions(false);
        sc.setAddGuestPermissions(false);
        sc.setCompanyId(companyId);

        try {
            sc.setScopeGroupId(GroupLocalServiceUtil.getCompanyGroup(companyId).getGroupId());
            sc.setUserId(UserLocalServiceUtil.getDefaultUserId(companyId));
        }
        catch (Exception e) {
            mLog.error("Could not find valid Default Group or User information for the companyId: " + companyId);
            sc.setScopeGroupId(0);
            sc.setUserId(0);
        }

        return sc;
    }


    /**
     * Generates a default ServiceContext object.  This should only be used when
     * a ActionRequest is available, but the feedtype of the incoming feed is
     * unknown.
     *
     * @param request ActionRequest for the corresponding feed.
     * @return Default ServiceContext object
     */
    public static ServiceContext createDefaultServiceContext(ActionRequest request) {

        ThemeDisplay themeDisplay =
                (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();

        ServiceContext sc = new ServiceContext();
        sc.setAddCommunityPermissions(false);
        sc.setAddGuestPermissions(false);
        sc.setCompanyId(companyId);

        try {
            sc.setScopeGroupId(GroupLocalServiceUtil.getCompanyGroup(companyId).getGroupId());
            sc.setUserId(UserLocalServiceUtil.getDefaultUserId(companyId));
        }
        catch (Exception e) {
            sc.setScopeGroupId(0);
            sc.setUserId(0);
        }

        return sc;
    }


    /**
     * Gets the ServiceContext out of an action request
     *
     * @param request ActionRequest for the corresponding feed
     * @param feedUrl URL of the feed to generate a servicecontext for
     * @return ServiceContext object
     * @throws com.liferay.portal.kernel.exception.SystemException Default Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException Default Liferay Exception
     * @throws java.net.MalformedURLException Thrown when the URL is invalid or cannot
     * be parsed.
     */
    public static ServiceContext getServiceContext(ActionRequest request, String feedUrl)
            throws SystemException, PortalException, MalformedURLException {

        AbderaAtomParser atomParser = new AbderaAtomParser(new URL(feedUrl));
        StaticNterAtomParser nterParser =
                new StaticNterAtomParser(atomParser.getStaticParser().getNterExtension());

        NterFeedType nft = nterParser.getFeedType(atomParser.getFeed());

        if (nft.equals(NterFeedType.COURSES)) {
            return ServiceContextFactory.getInstance(Course.class.getName(), request);
        }
        else if (nft.equals(NterFeedType.RECORDS)) {
            return ServiceContextFactory.getInstance(CourseRecord.class.getName(), request);
        }
        else if (nft.equals(NterFeedType.TOMBSTONE)) {
            return ServiceContextFactory.getInstance(Course.class.getName(), request);
        }
        else {
            mLog.error("Unknown feedtype for " + feedUrl);
            return ServiceContextUtil.createDefaultServiceContext(request);
        }
    }


    /**
     * Determines the default company Id.
     *
     * @return the CompanyThread's company ID, or if invalid, a valid company ID
     */
    public static long getDefaultCompanyId() {

        long companyId = CompanyThreadLocal.getCompanyId();
        if (companyId == CompanyConstants.SYSTEM) {
            // Note: This method assumes only one company per NTER instance
            companyId = PortalUtil.getCompanyIds()[0];
        }

        return companyId;
    }


    /**
     * @return the virtual host based on the default company ID
     */
    public static String getDefaultVirtualHost() {
        try {
            return CompanyLocalServiceUtil.getCompanyById(
                            getDefaultCompanyId()).getVirtualHostname();
        }
        catch (Exception e) {
            mLog.error(ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }


    /**
     *
     * @param companyId The companyId to find the virtual host for.
     *
     * @return the virtual host based on the default company ID
     */
    public static String getDefaultVirtualHost(Long companyId) {
        try {
            return CompanyLocalServiceUtil.getCompanyById(companyId).getVirtualHostname();
        }
        catch (Exception e) {
            mLog.error(ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }
}