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

package org.nterlearning.utils;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;


public class PortalPropertiesUtil {

    /**
     * Reads various property entries from portal-ext.properties and
     * portal.properties to generate the web protocol used by the Portlet.
     *
     * @return "http{s}" depending on the property values
     */
    public static String getWebServerProtocol() {

        String protocol = null;
        try {
            protocol = PrefsPropsUtil.getString(PropsKeys.WEB_SERVER_PROTOCOL);
        }
        catch (Exception e) {
            // Exceptions would only be raised by PrefPropsUtil if it's not
            // passed a valid string to search for
        }

        return ((protocol == null) || protocol.equals("")) ? "http" : protocol;
    }


    /**
     * Reads various property entries from portal-ext.properties and
     * portal.properties to generate the web server port used by the Portlet.
     *
     * @return either the default port for the given protocol, or the custom
     * port
     */
    public static String getWebServerPort() {
        String protocol = getWebServerProtocol();
        String port = null;

        try {
            if (protocol.equals("http")) {
                port = PrefsPropsUtil.getString(PropsKeys.WEB_SERVER_HTTP_PORT);
                port = ((port == null) || port.equals("")) ? "80" : port;
            }
            else if (protocol.equals("https")) {
                port = PrefsPropsUtil.getString(PropsKeys.WEB_SERVER_HTTPS_PORT);
                port = ((port == null) || port.equals("")) ? "443" : port;
            }
        }
        catch (Exception e) {
            // Exceptions would only be raised by PrefPropsUtil if it's not
            // passed a valid string to search for
        }

        return port;
    }


    /**
     * Returns the SSO Implementation used based on the portal-ext.properties
     * file.  If no implementation is listed, attempt to determine the
     * implementation used by searching for known SSO portlets.  If nothing is
     * listed and no portlets are found, returns an empty string.
     *
     * @return SSO implementation used, or an empty string
     */
    public static String getSsoImplementation() {
        try {
            String ssoType = PrefsPropsUtil.getString(PortalProperties.SSO_PROPERTY);

            if (PortalProperties.SUPPORTED_SSO_IMPLEMENTATIONS.contains(ssoType)) {
                return ssoType;
            }
            else {
                // try to determine if one of the sso portlets is installed
                Portlet portlet  = PortletLocalServiceUtil.getPortletById("shibsso_WAR_shibssoportlet");
                if (portlet != null) {
                    return PortalProperties.SSO_SHIB_PROPERTY_VALUE;
                }

                return "";
            }
        }
        catch (Exception e) {
            return "";
        }
    }
}
