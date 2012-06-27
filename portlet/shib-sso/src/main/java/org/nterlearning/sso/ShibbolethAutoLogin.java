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

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserIdMapper;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserIdMapperLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.PwdGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Locale;

public class ShibbolethAutoLogin implements AutoLogin {    


    private static Log _log = LogFactoryUtil.getLog(ShibbolethAutoLogin.class);

    public String[] login(HttpServletRequest request, HttpServletResponse response)
            throws AutoLoginException {

        try {
            long companyId = getCompanyId(request);

            // This attribute is set by mod_shib when a shibboleth session exists
            Object shibIdProvider = request.getAttribute("Shib-Identity-Provider");
            _log.debug("Shib-Identity-Provider = " + shibIdProvider);
            if (Validator.isNull(shibIdProvider)) {
                return null;
            }

            // Grab the attributes passed in by mod_shib that we require
            Object uid = request.getAttribute(ShibbolethConstants.UID_PROPERTY);
            Object emailAddress = request.getAttribute(ShibbolethConstants.EMAIL_PROPERTY);
            Object firstName = request.getAttribute(ShibbolethConstants.FIRST_NAME_PROPERTY);
            Object lastName = request.getAttribute(ShibbolethConstants.LAST_NAME_PROPERTY);

            _log.debug("SSO User attributes:");
            _log.debug("    sso id = " + uid);
            _log.debug("    email address = " + emailAddress);
            _log.debug("    first name = " + firstName);
            _log.debug("    last name = " + lastName);

            if (Validator.isNull(uid)) {
                throw new AutoLoginException("SSO uid is null");
            }
            if (Validator.isNull(emailAddress)) {
                throw new AutoLoginException("Email address is null");
            }
            if (Validator.isNull(firstName) || firstName.equals("")) {
                firstName = "-";
            }
            if (Validator.isNull(lastName) || lastName.equals("")) {
                lastName = "-";
            }

            UserIdMapper userMapper;
            User user;
            try {
                userMapper =
                        UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(
                            ShibbolethConstants.SSO_TYPE, uid.toString());
            }
            catch (NoSuchUserIdMapperException e) {

                _log.debug("Searching for existing user " + emailAddress.toString());
                try {
                    user = UserLocalServiceUtil.getUserByEmailAddress(
                            companyId, emailAddress.toString());
                }
                catch (NoSuchUserException nsue) {

                    ThemeDisplay themeDisplay =
                            (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

                    Locale locale = LocaleUtil.getDefault();
                    if (themeDisplay != null) {
                        // ThemeDisplay should never be null, but some users
                        // complain of this error. Cause is unknown.
                        locale = themeDisplay.getLocale();
                    }

                    user = addUser(companyId, firstName.toString(), lastName.toString(),
                                   emailAddress.toString(), locale);
                }

                userMapper =
                        UserIdMapperLocalServiceUtil.updateUserIdMapper(
                                user.getUserId(), ShibbolethConstants.SSO_TYPE,
                                null, uid.toString());
            }

            _log.debug("Updating user " + uid.toString());
            try {
                user = UserLocalServiceUtil.getUserById(companyId, userMapper.getUserId());
            }
            catch (NoSuchUserException nsue) {
                // This is an error condition resulting from inconsistent data. If
                // the userId is filled in in the SSO table, then the user should exist
                throw new AutoLoginException("Inconsistency in the NTER User store: " +
                    "user id " + userMapper.getUserId() + " from sso id " +
                    uid.toString() + " does not exist", nsue);
            }

            updateUser(user, firstName.toString(), lastName.toString(),
                       emailAddress.toString());

            String username = String.valueOf(user.getUserId());
            String password = user.getPassword();
            String isPasswordEncrypted = Boolean.TRUE.toString();

            _log.debug("Credentials: [ " + username + ", " + password + ", " +
                       isPasswordEncrypted + " ]");

            return new String[] {username, password, isPasswordEncrypted};
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }

        return null;
    }


    private long getCompanyId(HttpServletRequest request) {
        try {
            return PortalUtil.getCompany(request).getCompanyId();
        }
        catch (Exception e) {
            _log.warn("Could not read companyId from request, using default company instead.");
            return PortalUtil.getCompanyIds()[0];
        }
    }


    private static User addUser(long companyId, String firstName,
            String lastName, String emailAddress, Locale locale) throws Exception {
        long creatorUserId = 0;
        boolean autoPassword = false;
        String password = PwdGenerator.getPassword();
        boolean autoScreenName = true;
        String screenName = StringPool.BLANK;
        long facebookId = 0;
        String openId = StringPool.BLANK;
        String middleName = StringPool.BLANK;
        int prefixId = 0;
        int suffixId = 0;
        boolean male = true;
        int birthdayMonth = Calendar.JANUARY;
        int birthdayDay = 1;
        int birthdayYear = 1970;
        String jobTitle = StringPool.BLANK;
        long[] groupIds = null;
        long[] organizationIds = null;
        long[] roleIds = null;
        long[] userGroupIds = null;
        boolean sendEmail = false;
        ServiceContext serviceContext = new ServiceContext();

        return UserLocalServiceUtil.addUser(creatorUserId, companyId,
                autoPassword, password, password, autoScreenName,
                screenName, emailAddress, facebookId, openId, locale,
                firstName, middleName, lastName, prefixId, suffixId, male,
                birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
                organizationIds, roleIds, userGroupIds, sendEmail,
                serviceContext);
    }


    private static void updateUser(User user, String firstName,
            String lastName, String emailAddress) {
        boolean fieldChanged = false;

        if (!user.getFirstName().equals(firstName)) {
            _log.debug("updating user's first name from " +
                    user.getFirstName() + " to " + firstName);
            user.setFirstName(firstName);
            fieldChanged = true;
        }
        if (!user.getLastName().equals(lastName)) {
            _log.debug("updating user's last name from " +
                    user.getLastName() + " to " + lastName);
            user.setLastName(lastName);
            fieldChanged = true;
        }
        if (!user.getEmailAddress().equals(emailAddress)) {
            _log.debug("updating user's email address from " +
                    user.getEmailAddress() + " to " + emailAddress);
            user.setEmailAddress(emailAddress);
            fieldChanged = true;
        }

        user.setPasswordReset(false);

        if (fieldChanged) {
            try {
                UserLocalServiceUtil.updateUser(user);
            }
            catch (SystemException e) {
                _log.error("Unable to update user attributes", e);
            }
        }
    }
}