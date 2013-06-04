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

package org.nterlearning.atom.parser;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.tika.io.IOUtils;
import org.nterlearning.atom.AbderaSingleton;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.staticParser.StaticParser;

import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Map;

public class AbderaAtomParser {

    public Log mLog = LogFactoryUtil.getLog(AbderaAtomParser.class);

    private Feed mFeed = null;
    private URL mFeedUrl = null;
    private File mFeedFile = null;
    private StaticParser mStaticParser = null;
    

    /**
     * Public constructor that is used most often during testing when processing
     * a local Atom Feed File.
     *
     * @param file File containing the Atom Feed
     */
    @Deprecated
    public AbderaAtomParser(File file) {
        setFeedFile(file);
    }


    /**
     * Public constructor used 1) when connecting to the service registry for
     * automated feed processing or 2) when testing an individual feed.
     *
     * @param feedUrl URL of the Atom Feed
     */
    public AbderaAtomParser(URL feedUrl) {
        setFeedUrl(feedUrl);
    }


    /**
     * Public constructor used by the PuSH interface.
     *
     * @param feed InputStream of the Atom feed.
     */
    public AbderaAtomParser(InputStream feed) {
        try {
			setFeed(feed);
		}
		catch (Exception e) {
			mLog.error("Error setting up the Atom feed: " + e,e);
		}
    }

    
    public AbderaAtomParser(Feed feed) {
        mFeed = feed;
        setFeedParser();
    }


    /**
     * Sets the parser's feed file and updates the static parser accordingly.
     *
     * @param file New file to process as an Atom Feed
     */
    @Deprecated
    public void setFeedFile(File file) {
        try {
            mFeedFile = file;
            setFeed(new FileInputStream(file));
        }
        catch (Exception e) {
            resetParser();
            mLog.error("Error setting the feed file at [" + file.getName() + "]: " + e,e);
        }
    }

    public File getFeedFile() {
        return mFeedFile;
    }


    /**
     * Sets the parsers feed url and updates the static parser accordingly.
     *
     * @param url New URL to point to for an Atom Feed.
     */
    public void setFeedUrl(URL url) {
        InputStream urlInput = null;
        try {
            mFeedUrl = url;
            urlInput = url.openStream();
            setFeed(urlInput);
        }
        catch (IOException e) {
            resetParser();
            IOUtils.closeQuietly(urlInput);
            mLog.error("Could not open the Feed URL at [" + url.toString() + "]: " + e.getMessage());
        }
        catch (Exception e) {
            resetParser();
            IOUtils.closeQuietly(urlInput);
            mLog.error("Could not process the Feed at [" + url.toString() + "]: " + e.getMessage());
        }
    }

    public URL getFeedUrl() {
        return mFeedUrl;
    }


    /**
     * Sets the parser feed contents to the InputStream and updates the
     * static parser accordingly.
     *
     * @param feedInputStream InputStream of the Atom feed.
     */
    public void setFeed(InputStream feedInputStream) {
        Parser parser = AbderaSingleton.getInstance().getParser();
        
        BufferedInputStream feed = new BufferedInputStream(feedInputStream);
        try {
        	
        	// make sure the stream isn't empty, to avoid an ArrayIndexOutOfBounds
        	feed.mark(4); // arbitrary, low number. As long as we read more than zero, it's fine
        	int readBytes = feed.read();
        	if (readBytes > 0){
        		feed.reset();        		
        		Document<Feed> doc = parser.parse(feed);
        		mFeed = doc.getRoot();
        		setFeedParser();
        	}
        	else {
        		mLog.debug("The feed input stream is empty (zero bytes)");
        	}
        }
        catch (ClassCastException cce) {
            throw new IllegalArgumentException(
                    "The feed appears to not contain a valid Atom Feed.", cce);
        }
        catch (ParseException a) {
            mLog.warn("Error while trying to parse feed: " + a);
        }
        catch (IOException e){
        	mLog.error("Error while trying to parse feed: " + e);
        }
        catch (Exception e) {
            mLog.error("Error while trying to parse feed: " + e);
        }
    }


    /**
     * Sets the parser feed contents to the <code>Feed</code> and updates the
     * static parser accordingly.
     *
     * @param feed Feed object to set the parser to.
     */
    public void setFeed(Feed feed) {
        mFeed = feed;
        setFeedParser();
    }

    public Feed getFeed() {
        return mFeed;
    }


    public StaticParser getStaticParser() {
        return mStaticParser;
    }


    public void persistFeed(FeedContext fc) {
        if (mStaticParser != null) {
            try {
                mStaticParser.persistFeed(mFeed, fc);
            }
            catch (Exception e) {
                mLog.error("Could not persist feed: " + e,e);
            }
        }
        else {
            mLog.debug("Parser has not been correctly configured for feed.");
        }
    }


    /**
     * Determines the correct static parser based on the <code>nter:{ns}</code>
     * value found in the feed.  If a valid namespace is not found, then the
     * parser is not set.
     */
    private void setFeedParser() {
        Map<String, String> nsMap = mFeed.getNamespaces();
        String nterNs = nsMap.get(NterExtension.NTER_NS_TAG);

        if (nterNs == null) {
            mLog.error("Feed [" + mFeed.getId() + "] does not contain an NTER namespace.");
        }
        else {
            mStaticParser = new StaticParser(nterNs);
        }
    }


    /**
     * Resets all parser information to null.  This should be done whenever
     * an exception is raised to prevent inaccurate feed processing.
      */
    private void resetParser() {
        mFeed = null;
        mFeedFile = null;
        mStaticParser = null;
    }
}
