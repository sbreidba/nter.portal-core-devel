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

public class PushKeys {

    // Atom feed keys
	public static final String PUSH_HUB_LINK_REL_ATTR_VAL = "hub";

    // HTTP methods
    public static final String HTTP_SCHEME = "http";
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";

    // HTTP payload types
    public static final String CONTENT_TYPE_HEADER = "Content-type";
    public static final String USER_AGENT_HEADER = "User-agent";
    public static final String WWW_FORM_URL_ENCODED_MIME_TYPE = "application/x-www-form-urlencoded";
    public static final String ATOM_CONTENT_TYPE = "application/atom+xml";

    // pshb payload properties
    public static final String USER_AGENT_STRING = "RSS pubsubhubbub 0.3";
    public static final String PARAM_CALLBACK = "hub.callback";
    public static final String PARAM_MODE = "hub.mode";
    public static final String PARAM_TOPIC = "hub.topic";
    public static final String PARAM_VERIFY = "hub.verify";
    public static final String PARAM_LEASE_SECONDS = "hub.lease_seconds";
    public static final String PARAM_VERIFY_TOKEN = "hub.verify_token";
    public static final String PARAM_CHALLENGE = "hub.challenge";
    public static final String PARAM_FEED_URL = "hub.url";
    
    // subscription modes
    public static final String SUBSCRIBE_MODE = "subscribe";
    public static final String UNSUBSCRIBE_MODE = "unsubscribe";
    public static final String PUBLISH_MODE = "publish";

    // sync modes
    public static final String ASYNC_VERIFY = "async";
    public static final String SYNC_VERIFY = "sync";

    public static final String DEFAULT_HOST = "localhost";
    public static final String RELATIVE_CALLBACK_URL = "/nter-catalog-portlet/push";
}
