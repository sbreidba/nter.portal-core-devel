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


package org.nterlearning.atom.parser.push;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.nterlearning.utils.PortalProperties;
import org.nterlearning.utils.PortalPropertiesUtil;

/**
 * 
 * @author gjiva
 *
 */
public class PubSubHubbubProperties {

    static Log log = LogFactoryUtil.getLog(PubSubHubbubProperties.class);


    /**
     * Returns the PUSH callback URL.  If the URL is not configured in the
     * portal-ext.properties file, it is automatically calculated.
     *
     * @return Callback url, or NULL if an exception is encountered.
     */
    public static String getCallbackUrl() {
        String url = PropsUtil.get(PortalProperties.PUSH_CALLBACK_URL);

        if ((url == null) || url.equals("")) {
            url = computeCallbackUrl();
        }

        return url;
    }
    

    /**
     * Determines the PUSH callback URL based on properties set in
     * portal-ext.properties and the virtual host.
     *
     * @return The PUSH callback URL, or null if an error was encountered.
     */
    private static String computeCallbackUrl() {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();
            Company company = CompanyLocalServiceUtil.getCompany(companyId);
            String virtualHost = company.getVirtualHost();

            String httpScheme = PortalPropertiesUtil.getWebServerProtocol();
            String port = PortalPropertiesUtil.getWebServerPort();

            // only append the port if it is a non-standard one
            if (!port.equals("80") && !port.equals("443") && !port.equals("-1")) {
                virtualHost += ":" + port;
        }

            return httpScheme + "://" + virtualHost + PushKeys.RELATIVE_CALLBACK_URL;
    }
        catch (Exception e) {
            log.error("Could not compute callback url: " + e.getMessage());
            return null;
    }
    }
}
