package org.nterlearning.atom.parser.push;

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

/**
 * 
 * @author gjiva
 *
 */
public class PubSubHubbubUtils {
	
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
}
