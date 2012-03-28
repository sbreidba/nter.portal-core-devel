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

package org.nterlearning.sso;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShibbolethLogoutAction extends Action {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ActionException {
        try {
            request.getSession().invalidate();

            // If using Apache mod_shib, this value should look something like:
            //     /Shibboleth.sso/Logout?return=https%3A%2F%2Fnterlearning.org%2F
            String logoutRedirectUrl = PropsUtil.get(ShibbolethConstants.SSO_LOGOUT_REDIRECT_PROPERTY);
            _log.debug("logoutRedirectUrl = " + logoutRedirectUrl);

            if (logoutRedirectUrl == null) {
                long companyId = PortalUtil.getCompany(request).getCompanyId();
                logoutRedirectUrl = PrefsPropsUtil.getString(companyId, PropsKeys.COMPANY_DEFAULT_WEB_ID);
            }

            response.sendRedirect(logoutRedirectUrl);
        }
        catch (Exception e) {
            _log.error(e, e);
        }
    }

    private static Log _log = LogFactoryUtil.getLog(ShibbolethLogoutAction.class);
}