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

/**
 * 
 */
package org.nterlearning.atom.parser.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipWriter;
import org.nterlearning.atom.AbderaSingleton;
import org.nterlearning.atom.generator.AbderaAtomGenerator;
import org.nterlearning.atom.generator.NterActivityStreamGenerator;
import org.nterlearning.atom.parser.AtomFeedProcessor;
import org.nterlearning.atom.parser.FeedContext;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.ParseException;
import org.apache.abdera.parser.Parser;

import javax.portlet.PortletPreferences;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author gjiva
 *
 */
public class AtomParserPortletDataHandler implements PortletDataHandler {
	
	public static final String REVIEWS_FILE_NAME = "reviews.xml";
	
	private static Log log = LogFactoryUtil.getLog(AtomParserPortletDataHandler.class);

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#deleteData(com.liferay.portal.kernel.lar.PortletDataContext, java.lang.String, javax.portlet.PortletPreferences)
	 */
	public PortletPreferences deleteData(PortletDataContext context, String portletId,
			PortletPreferences prefs) throws PortletDataException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#exportData(com.liferay.portal.kernel.lar.PortletDataContext, java.lang.String, javax.portlet.PortletPreferences)
	 */
	public String exportData(PortletDataContext context, String portletId,
			PortletPreferences prefs) throws PortletDataException {
		
		log.debug("Exporting course reviews");
		
		
		try {
			// get the local reviews feed and marshal it into a String
			Feed as = NterActivityStreamGenerator.getLocalReviews(context.getPortletPath(portletId));
			StringWriter sw = new StringWriter();
			as.writeTo(AbderaAtomGenerator.getRegularWriter(), sw);

			// write the string into a file in the LAR
			ZipWriter zipWriter = context.getZipWriter();
			String filePath = getReviewsFilePath(portletId);
			zipWriter.addEntry(filePath, sw.toString());
			
			return  "<data-file>" + filePath + "</data-file>";
		}
		catch (IOException e) {
			throw new PortletDataException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#getExportControls()
	 */
	public PortletDataHandlerControl[] getExportControls()
			throws PortletDataException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#getImportControls()
	 */
	public PortletDataHandlerControl[] getImportControls()
			throws PortletDataException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#importData(com.liferay.portal.kernel.lar.PortletDataContext, java.lang.String, javax.portlet.PortletPreferences, java.lang.String)
	 */
	public PortletPreferences importData(PortletDataContext context, String portletId,
			PortletPreferences prefs, String data) throws PortletDataException {
		
		log.debug("Importing reviews");
		try {

			// read the XML in the LAR file
			ZipReader zipReader = context.getZipReader();
			String feedString = zipReader.getEntryAsString(getReviewsFilePath(portletId));
			
			// parse it into an Abdera Feed
			Parser parser = AbderaSingleton.getInstance().getParser();
			Document<Feed> doc = parser.parse(new StringReader(feedString));
			Feed feed = doc.getRoot();
			
			// create the Feed Context
			FeedContext fc = new FeedContext(context.getPortletPath(portletId));
			
			// process it like a normal feed
			AtomFeedProcessor.processFeed(fc, feed);
			
			return prefs;
		}
		catch (ParseException e) {
			throw new PortletDataException(e);
		}
		catch (SystemException e) {
			throw new PortletDataException(e);
		}
		catch (PortalException e) {
			throw new PortletDataException(e);
		}
        catch (Exception e) {
            log.error("Could not import reviews: " + e.getMessage());
            throw new PortletDataException(e);
        }
	}

	/* non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#isAlwaysStaged()
	 */
	public boolean isAlwaysStaged() {
		return true;
	}

	/* non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#isAlwaysExportable()
	 */
	public boolean isAlwaysExportable() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.lar.PortletDataHandler#isPublishToLiveByDefault()
	 */
	public boolean isPublishToLiveByDefault() {
		return true;
	}
	
	public static String getReviewsFilePath(String portletId){
		return portletId + "/" + REVIEWS_FILE_NAME;
	}

}
