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

public class ShibbolethConstants {

    public static final String SSO_TYPE = "Shibboleth";
    public static final String SSO_PROPERTY = "nter.sso.type";

    // mod_shib attributes
    public static final String UID_PROPERTY = "uid";
    public static final String EMAIL_PROPERTY = "mail";
    public static final String FIRST_NAME_PROPERTY = "givenName";
    public static final String LAST_NAME_PROPERTY = "sn";

    // properties found in portal-ext.properties
    public static final String SSO_LOGOUT_REDIRECT_PROPERTY = "nter.sso.logout.redirectUrl";
}
