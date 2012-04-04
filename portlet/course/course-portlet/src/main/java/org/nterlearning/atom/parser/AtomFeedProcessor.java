package org.nterlearning.atom.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.abdera.model.Feed;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * This class is designed to provide the tools to actually process an incoming
 * Atom Feed.
 */
public class AtomFeedProcessor {

    private static Log mLog = LogFactoryUtil.getLog(AtomFeedProcessor.class);


    /**
     * Processes an Atom feed at the URL found in the FeedContext object.
     *
     * @param fc - the Feed Context associated with the URL
     */
    public static void processFeed(FeedContext fc) {

        String feedUrl = fc.getFeedUrl();
        mLog.info("Processing Atom feed at URL: " + feedUrl);

        try {
            AbderaAtomParser atomParser = new AbderaAtomParser(new URL(feedUrl));
            atomParser.persistFeed(fc);

            mLog.info("Finished processing Atom feed at URL " + feedUrl);
        }
        catch (MalformedURLException url_e) {
            mLog.error("MalformedURL found for " + fc.getFeedUrl());
        }
        catch (ArrayIndexOutOfBoundsException a) {
            // this happens if the feed is empty (typically an activity stream),
            // need a better way to track this though
        	mLog.warn("Feed at URL [" + feedUrl + "] is probably empty, but this " +
        			"exception should have been prevented.");
        }
        catch (Exception e) {
            mLog.error("Error processing feed at [" + fc.getFeedUrl() + "]: " + e.getMessage());
        }
        catch (Throwable t) {
            mLog.error("Error processing feed at [" + fc.getFeedUrl() + "]: " + t.getMessage());
        }
    }


    public static void processFeed(FeedContext fc, Feed feed) {
        AbderaAtomParser parser = new AbderaAtomParser(feed);
        parser.persistFeed(fc);
    }


    /**
     * Processes an Atom feed at the given URL
     *
     * @param feedUrl - URL of the feed to process
     * @throws Exception Standard Liferay Exceptions
     */
    public static void processFeed(String feedUrl) throws Exception {
        processFeed(new FeedContext(feedUrl));
    }


    /**
     * Processes Atom feeds given a list of feed URLs
     *
     * @param fcs The list of FeedContexts to process
     */
    public static void processFeeds(List<FeedContext> fcs) {
        for (FeedContext fc : fcs) {
            try {
                processFeed(fc);
            }
            catch (Exception e) {
                mLog.error("Skipping feed at URL [" + fc.getFeedUrl() +
                            "] due to error while processing: " + e,e);
            }
		}
	}
}