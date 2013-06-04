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
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author gjiva
 *
 */
public class PubSubHubbubUtils {

    private static Log mLog = LogFactoryUtil.getLog(PubSubHubbubUtils.class);


    /**
	 * 
	 * @return
	 */
    public static HttpClient createHttpClient() {
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 200);
        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(20);
        connPerRoute.setDefaultMaxPerRoute(50);
        ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);

        DefaultHttpClient httpClient = new DefaultHttpClient(params);
        httpClient.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						}
                        catch (NumberFormatException ignore) {
						}
					}
				}
				// default keepalive is 60 seconds. This is higher than usual
				// since the number of hubs it should be talking to should be
				// small
				return 30000;
			}
        });

        return httpClient;
    }


    /**
     * Generates a fully qualified URL for a server based on a ServletRequest.
     *
     * @param request ServletRequest to glean server URL from.
     *
     * @return Fully qualified URL, or null if an error occurred.
     */
    public static String getServerUrlFromRequest(HttpServletRequest request) {
        int port = request.getServerPort();
        if (request.getScheme().equals("http") && (port == 80)) {
            port = -1;
        }
        else if (request.getScheme().equals("https") && (port == 443)) {
            port = -1;
        }

        try {
            URL serverURL = new URL(request.getScheme(), request.getServerName(), port, "");
            return serverURL.toString();
        }
        catch (MalformedURLException e) {
            mLog.warn("Unable to create URL for PuSH server at: " + request.getServerName());
            return null;
        }
    }
}
