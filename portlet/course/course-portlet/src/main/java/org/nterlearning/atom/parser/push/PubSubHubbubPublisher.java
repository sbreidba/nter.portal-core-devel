/**
 * 
 */
package org.nterlearning.atom.parser.push;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author gjiva
 *
 */
public class PubSubHubbubPublisher {
	
	private static Log log = LogFactoryUtil.getLog(PubSubHubbubPublisher.class);
	
	/**
	 * Sends a publish notification for a specifi topic url to a hub.
     *
	 * @param topicUrl The Topic to publish for.  This is typically the HREF
     * of a feed.
	 * @param hubUrl The hub to publish to.
     *
	 * @throws java.io.IOException Thrown if unable to encode data
	 */
	public static void publish(String topicUrl, String hubUrl)
		    throws IOException {
		
		Validate.notNull(hubUrl, "hubUrl is null");
		log.info("Publishing URL [" + topicUrl + "] to PuSH hub [" + hubUrl + "]");
		
		HttpPost post = new HttpPost(hubUrl);
		List<NameValuePair> pushParams = new ArrayList<NameValuePair>();
		pushParams.add(new BasicNameValuePair(PushKeys.PARAM_MODE, PushKeys.PUBLISH_MODE));
		pushParams.add(new BasicNameValuePair(PushKeys.PARAM_FEED_URL, topicUrl));
		
		post.setEntity(new UrlEncodedFormEntity(pushParams));
		post.setHeader(PushKeys.CONTENT_TYPE_HEADER, PushKeys.WWW_FORM_URL_ENCODED_MIME_TYPE);
		post.setHeader(PushKeys.USER_AGENT_HEADER, PushKeys.USER_AGENT_STRING);

		BasicHttpContext context = new BasicHttpContext();

		HttpClient httpClient = PubSubHubbubUtils.createHttpClient();
		HttpResponse response = httpClient.execute(post, context);
		
		int responseCode = response.getStatusLine().getStatusCode();
		
		if (responseCode == 204){
			log.info("Publish request was successful to PuSH hub [" +
                    hubUrl + "] for feed [" + topicUrl + "]" );
		}
		else {
			log.error("Publish request was unacceptable. HTTP code from hub [" +
                    hubUrl + "] in response to notification for feed [" +
					topicUrl + "] " + response.getStatusLine().getStatusCode() +
                    ". Accompanying reason for the code: [" +
                    response.getStatusLine().getReasonPhrase() + "]");
		}
	}
}
