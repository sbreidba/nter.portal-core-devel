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

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.tika.io.IOUtils;
import org.nterlearning.atom.parser.AbderaAtomParser;
import org.nterlearning.atom.parser.FeedParser;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

/**
 * This class handles subscription, un-subscription, and update processing 
 * between NTER and a Push hub.  It is implemented as a singleton to help ensure
 * only one list of hubs and feeds are subscribed to at a time.
 *
 * @author gjiva
 *
 */
public class PubSubHubbubSubscriber {

    private static Log log = LogFactoryUtil.getLog(PubSubHubbubSubscriber.class);

    // singleton to prevent multiple subscribers from being created
    private static PubSubHubbubSubscriber mSubscriber = new PubSubHubbubSubscriber();

	private static volatile HashSet<String> subscribedTopics = new HashSet<String>();
    private static volatile HashMap<String, String> mVerificationTokens = new HashMap<String, String>();


    private PubSubHubbubSubscriber() {
        // repopulate the list of subscribed feeds so that we know what we've
        // already subscribed to.  This is important for when the hub attempts
        // to verify subscriptions due to expired lease times.
        try {
            List<FeedReference> feedRefs =
                    FeedReferenceLocalServiceUtil.getFeedReferences(
                                    QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            for (FeedReference feedRef : feedRefs) {
                if (feedRef.getPshbSubscribed()) {
                    subscribedTopics.add(feedRef.getHref());
                }
            }
        }
        catch (Exception e) {
            // could not get feed refs, but logging's not available in constructor
        }
    }

    public static PubSubHubbubSubscriber getInstance() {
        return mSubscriber;
    }


	/**
	 * Handles new content distribution from a PuSH hub.
	 *
	 * @param request incoming HTTP request
	 * @param response outbound HTTP response
	 */
	public void contentDistribution(HttpServletRequest request,
                                    HttpServletResponse response) {

		try {
			if (request.getContentType().equals(PushKeys.ATOM_CONTENT_TYPE)){
				log.debug("Received Atom feed update");

				// parse the feed
				InputStream requestInputStream = request.getInputStream();
				AbderaAtomParser atomParser = new AbderaAtomParser(requestInputStream);

                FeedParser.getInstance().runFeedParser(atomParser.getFeed());                
				response.setStatus(HttpServletResponse.SC_OK);

                IOUtils.closeQuietly(requestInputStream);
			}
			else {
				log.error("Unsupported HTTP Content-Type in content distribution: " +
                        request.getContentType());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
        catch (Exception e) {
			log.error(e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
    

	/**
	 * Verifies that a subscription was request was made by this client.  Note
     * that only subscription requests are verified.  Unsubscription requests
     * are processed immediately and do not go through a verification stage.
	 *
	 * @param request incoming HTTP request
	 * @param response outbound HTTP response
	 */
	public void verify(HttpServletRequest request, HttpServletResponse response){

		String mode = request.getParameter(PushKeys.PARAM_MODE);
		String topic = request.getParameter(PushKeys.PARAM_TOPIC);
		String challenge = request.getParameter(PushKeys.PARAM_CHALLENGE);
		String verifyToken = request.getParameter(PushKeys.PARAM_VERIFY_TOKEN);

		log.info("Verification request:  mode [" + mode + "] for [" + topic + "]");

		Validate.notNull(mode, "hub.mode is null");
		Validate.notNull(topic, "hub.topic is null");
		Validate.notNull(challenge, "hub.challenge is null");

        if (mode.equals(PushKeys.SUBSCRIBE_MODE)) {
            if (subscribedTopics.contains(topic)) {
                if (verifyToken != null) {
                    // ensure that the verification tokens match what was sent
                    if (mVerificationTokens.containsKey(topic) &&
                            mVerificationTokens.get(topic).equals(verifyToken)) {
                        log.info("Verification token accepted for [" + topic + "]");
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                    else {
                        log.info("Verification token does not match for [" + topic + "]");
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
                else {
                    log.info("Verification successful for [" + topic + "]");
                    response.setStatus(HttpServletResponse.SC_OK);
                }

                try {
                    response.getWriter().print(challenge);
                }
                catch (IOException e) {
                    log.error("Error writing HTTP GET response: " + e);
                }

                updateFeedRefSubscriptionStatus(topic, mode.equals(PushKeys.SUBSCRIBE_MODE));
            }
            else {
                log.info("Verification failed for [" + topic + "]");

                updateFeedRefSubscriptionStatus(topic, false);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        else {
            // unsubscribe from the feed
            String pushServer = PubSubHubbubUtils.getServerUrlFromRequest(request);
            unsubscribe(pushServer, topic);
            response.setStatus(HttpServletResponse.SC_OK);
        }
	}


    /**
     * Subscribes or unsubscribes a given feedReference object from its hub(s).
     *
     * @param feedReference the FeedReference object to process
     * @param subscribe True to subscribe to the hub(s), false to unsubscribe
     *
     * @return True if subscribing and processing was successful, false if unsubscribing or if
     * subscribing and processing failed.
     */
    public boolean subscribe(FeedReference feedReference, Boolean subscribe) {
        Boolean subscribeSuccess = false;

        String hubs[] = feedReference.getPshb().split(",");
        if (subscribe) {
            for (String hub : hubs) {
                if (!hub.equals("")) {
                    int statusCode = subscribe(hub, feedReference.getHref());
                    subscribeSuccess = (statusCode == HttpServletResponse.SC_NO_CONTENT) ||
                            (statusCode == HttpServletResponse.SC_ACCEPTED) ||
                            subscribeSuccess;
                }
            }
        }
        else {
            for (String hub : hubs) {
                if (!hub.equals("")) {
                    unsubscribe(hub, feedReference.getHref());
                }
            }
        }

        return subscribeSuccess;
    }


	/**
	 * Subscribes to a topic on a PuSH hub for indefinite duration and without a
     * verification token.
	 *
	 * @param hubUrl - the hub's URL
	 * @param topicUrl - the URL of the topic
     *
     * @return HTTP Response code, a 200 represents success, 400 represents failure
	 */
	public int subscribe(String hubUrl, String topicUrl) {
        String absoluteCallbackUrl = PubSubHubbubProperties.getCallbackUrl();
		return subscribe(hubUrl, absoluteCallbackUrl, topicUrl, 0, null);
	}


	/**
	 * Subscribes to a topic on a PuSH hub without a verification token.
	 *
	 * @param hubUrl - the hub's URL
	 * @param topicUrl - the URL of the topic
     * @param leaseSeconds - the number of seconds to subscribe for.  Enter 0
     * for a permanent subscription.
     *
     * @return HTTP Response code 200 represents success, 400 represents failure
	 */
	public int subscribe(String hubUrl, String topicUrl, long leaseSeconds) {
        String absoluteCallbackUrl = PubSubHubbubProperties.getCallbackUrl();
		return subscribe(hubUrl, absoluteCallbackUrl, topicUrl, leaseSeconds, null);
	}


	/**
	 * Subscribes to a topic on a PuSH hub
	 *
	 * @param hubUrl - the hub's URL
     * @param callbackUrl - NTER's callback URL to receive hub connections
	 * @param topicUrl - the URL of the topic (the feed's href)
	 * @param leaseSeconds - the number of seconds to subscribe for, or 0 for a
     * permanent lease
	 * @param verifyToken - opaque token that will be echoed back in the
     * verification request to assist in identifying which subscription request
     * is being verified, or null if no such assistance is required.
     *
     * @return HTTP Response code 2xx represents success, 4xx or 5xx represents failure
	 */
	public int subscribe(String hubUrl, String callbackUrl, String topicUrl,
                         long leaseSeconds, String verifyToken)  {

		Validate.notNull(hubUrl, "hubUrl is null");
		Validate.notNull(topicUrl, "topicUrl is null");

		log.info("Subscribing to [" + topicUrl + "] on PuSH hub [" + hubUrl +
                "] to send subscriptions to [" + callbackUrl + "]");

		subscribedTopics.add(topicUrl);

        try {
            List<NameValuePair> pushParams = new ArrayList<NameValuePair>();
            pushParams.add(new BasicNameValuePair(PushKeys.PARAM_CALLBACK, callbackUrl));
            pushParams.add(new BasicNameValuePair(PushKeys.PARAM_MODE, PushKeys.SUBSCRIBE_MODE));
            pushParams.add(new BasicNameValuePair(PushKeys.PARAM_TOPIC, topicUrl));
            pushParams.add(new BasicNameValuePair(PushKeys.PARAM_VERIFY, PushKeys.SYNC_VERIFY));

            if (leaseSeconds > 0){
                pushParams.add(new BasicNameValuePair(PushKeys.PARAM_LEASE_SECONDS, String.valueOf(leaseSeconds)));
            }

            // force a verification token and add it to the parameter set
            if (verifyToken == null) {
                verifyToken =  UUID.randomUUID().toString();
            }
            pushParams.add(new BasicNameValuePair(PushKeys.PARAM_VERIFY_TOKEN, verifyToken));
            mVerificationTokens.put(topicUrl, verifyToken);

            HttpPost post = new HttpPost(hubUrl);
            post.setEntity(new UrlEncodedFormEntity(pushParams));
            post.setHeader(PushKeys.CONTENT_TYPE_HEADER, PushKeys.WWW_FORM_URL_ENCODED_MIME_TYPE);
            post.setHeader(PushKeys.USER_AGENT_HEADER, PushKeys.USER_AGENT_STRING);

            HttpClient httpClient = PubSubHubbubUtils.createHttpClient();
            HttpResponse response = httpClient.execute(post, new BasicHttpContext());

            log.info("HTTP response from PuSH hub [" + hubUrl +
                    "] for subscription request for feed [" + topicUrl + "] " +
                    response.getStatusLine().getStatusCode() + " - " +
                    response.getStatusLine().getReasonPhrase());

            return response.getStatusLine().getStatusCode();
        }
        catch (Exception e) {
            log.error("Could not subscribe to hub [" + hubUrl + "] for feed [" + topicUrl + "]");

            subscribedTopics.remove(topicUrl);

            if (verifyToken != null) {
                mVerificationTokens.remove(topicUrl);
            }

            return HttpServletResponse.SC_BAD_REQUEST;
        }
	}


    /**
     * Unsubscribes from a particular feed on a particular hub.
     *
     * @param hub The hub to send an unsubscribe message to
     * @param topicUrl The Feed URL to unsubscribe to
     *
     * @return HTTP Response code. A 200 represents success, 400 failure
     */
    public int unsubscribe(String hub, String topicUrl)  {
        String absoluteCallbackUrl = PubSubHubbubProperties.getCallbackUrl();
        return unsubscribe(hub, topicUrl, absoluteCallbackUrl, null);
    }


    /**
     * Unsubscribes from a particular topic on a particular hub.
     *
     * @param hub The hub to send an unsubscribe message to
     * @param topicUrl The Feed URL to unsubscribe from
     * @param callbackUrl NTER's callback URL to receive hub connections
     * @param verifyToken A verification token (may be null)
     *
     * @return HTTP Response code.  A 200 represents success, 400 represents failure
     */
    public int unsubscribe(String hub, String topicUrl, String callbackUrl,
                           String verifyToken) {
        try {
            if (topicUrl != null) {

                List<NameValuePair> pushParams = new ArrayList<NameValuePair>();
                pushParams.add(new BasicNameValuePair(PushKeys.PARAM_CALLBACK, callbackUrl));
                pushParams.add(new BasicNameValuePair(PushKeys.PARAM_MODE, PushKeys.UNSUBSCRIBE_MODE));
                pushParams.add(new BasicNameValuePair(PushKeys.PARAM_TOPIC, topicUrl));
                pushParams.add(new BasicNameValuePair(PushKeys.PARAM_VERIFY, PushKeys.SYNC_VERIFY));

                if (verifyToken != null) {
                    pushParams.add(new BasicNameValuePair(PushKeys.PARAM_VERIFY_TOKEN, verifyToken));
                }

                HttpPost post = new HttpPost(hub);
                post.setEntity(new UrlEncodedFormEntity(pushParams));
                post.setHeader(PushKeys.CONTENT_TYPE_HEADER, PushKeys.WWW_FORM_URL_ENCODED_MIME_TYPE);
                post.setHeader(PushKeys.USER_AGENT_HEADER, PushKeys.USER_AGENT_STRING);

                HttpClient httpClient = PubSubHubbubUtils.createHttpClient();
                BasicHttpContext context = new BasicHttpContext();
                HttpResponse response = httpClient.execute(post, context);

                if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_NO_CONTENT) {
                    try {
                        log.info("Unsubscribing from[ " + hub + "] for feed [" + topicUrl + "]");
                        FeedReference feedRef = FeedReferenceLocalServiceUtil.findByFeedHref(topicUrl);
                        feedRef.setPshbSubscribed(false);
                        FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);

                        subscribedTopics.remove(topicUrl);
                    }
                    catch (Exception e) {
                        log.error("Could not update Push subscription for FeedReference with Href: " + topicUrl);
                    }
                }
                else {
                    log.error("Could not unsubscribe Push notifications for FeedReference with Href: " + topicUrl);
                }

                return response.getStatusLine().getStatusCode();
            }
            else {
                return HttpServletResponse.SC_BAD_REQUEST;
            }
        }
        catch (Exception e) {
            log.error("Could not unsubscribe from hub: " + e.getMessage());
            return HttpServletResponse.SC_BAD_REQUEST;
        }
    }
    

    private void updateFeedRefSubscriptionStatus(String feedHref, Boolean subscribed) {
        try {
            FeedReference feedRef = FeedReferenceLocalServiceUtil.findByFeedHref(feedHref);
            feedRef.setPshbSubscribed(subscribed);

            FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
        }
        catch (Exception e) {
            log.error("Error updating subscription status of feedReference: " +
                    feedHref + " : " + e.getMessage());
        }
    }


    private void updateFeedRefSubscriptionStatus(FeedReference feedRef, Boolean subscribed) {
        try {
            feedRef.setPshbSubscribed(subscribed);
            FeedReferenceLocalServiceUtil.updateFeedReference(feedRef);
        }
        catch (Exception e) {
            log.error("Error updating subscription status of feedReference: " +
                    feedRef.getFeedReferenceId() + " : " + e.getMessage());
        }
    }
}