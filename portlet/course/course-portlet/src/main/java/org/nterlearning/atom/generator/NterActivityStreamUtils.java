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

package org.nterlearning.atom.generator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.AsExtension;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.feedParser.FeedParser;
import org.nterlearning.atom.parser.feedParser.FeedParserFactory;
import org.nterlearning.atom.parser.model.AsObject;
import org.nterlearning.atom.parser.model.AsVerb;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.Validate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author gjiva
 */
public class NterActivityStreamUtils {

    private static Log log = LogFactoryUtil.getLog(NterActivityStreamUtils.class);


    /**
     * Creates a new activity stream, but with no entries
     *
     * @param streamUrl - the URL at which the stream will be published
     * @param streamTitle
     * @param streamId
     * @param pushHubUrls - URLs to PuSH hubs which to advertise in the feed
     *
     * @return
     */
    public static Feed createNewStream(String streamUrl, String streamTitle,
            String streamId, List<String> pushHubUrls) {

        Feed feed = AbderaAtomGenerator.createFeed();
        feed.setTitle(streamTitle);
        feed.setId(streamId);
        Link selfLink = feed.addLink(streamUrl, "self");
        selfLink.setMimeType("application/atom+xml");

        // declare the namespaces at the top so the XML is pretty
        feed.declareNS(NterNameSpace.getLatestNameSpace().getNameSpace(), "nter");
        feed.declareNS(NterExtension.ATOM_NS, "atom");
        feed.declareNS(AsExtension.AS_NS, "activity");

        addPushHubLinks(feed, pushHubUrls);

        feed.setUpdated(new Date(System.currentTimeMillis()));

        return feed;
    }


    /**
     * @param feed
     * @param pushHubUrls
     */
    public static void addPushHubLinks(Feed feed, List<String> pushHubUrls) {
        for (String hubUrl : pushHubUrls) {
            feed.addLink(hubUrl, "hub");
        }
    }


    /**
     * Finds a course review in an Activity Stream feed, given the review's ID
     *
     * @param as ActivityStream feed
     * @param courseReviewId CourseReview Id to find in the feed
     *
     * @return
     */
    public static Entry findCourseReviewEntry(Feed as, long courseReviewId) {
        AsObject review;

        List<Entry> entries = as.getEntries();
        for (Entry existingEntry : entries) {
            review = StaticNterAtomParser.getObject(existingEntry);

            if (AbderaAtomGenerator.extractIdFromAtomId(review.getId(),
                    AsExtension.ATOM_ID_DATA_TYPE_REVIEW) == courseReviewId) {
                return existingEntry;
            }
        }

        return null;
    }


    /**
     * Properly adds the reviews passed in to the feed passed in, if they are
     * past the cutoff date
     *
     * @param reviews
     * @param as
     * @param cutoffDate
     */
    public static void addReviewsToStream(List<CourseReview> reviews,
            Feed as, Date cutoffDate)
            throws PortalException, SystemException {

        Entry entry;
        for (CourseReview dbRev : reviews) {
            if (!dbRev.getRemoved()) {

                entry = findCourseReviewEntry(as, dbRev.getCourseReviewId());
                if ((entry == null) && dbRev.getModifiedDate().after(cutoffDate)) {

                    if (dbRev.getModifiedDate().equals(dbRev.getCreateDate())) {
                        addReviewActivity(as, dbRev.getCourseReviewId(), AsVerb.VerbType.ADD);
                    }
                    else if (dbRev.getModifiedDate().after(dbRev.getCreateDate())) {
                        addReviewActivity(as, dbRev.getCourseReviewId(), AsVerb.VerbType.UPDATE);
                    }
                    else {
                        log.warn("Course review ID " + dbRev.getCourseReviewId() +
                                " was somehow modified on " + dbRev.getModifiedDate() +
                                ", BEFORE it was created on " + dbRev.getCreateDate());
                    }
                }
            }
        }
    }


    /**
     * @param as - The feed to add the review to
     * @param dbCourseReviewId - the id of the course review in persistence
     * @param verbType - the AS verb type
     *
     * @return the id of the entry added
     *
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public static String addReviewActivity(Feed as,
            long dbCourseReviewId, AsVerb.VerbType verbType)
            throws PortalException, SystemException {

        FeedParser parser = FeedParserFactory.getFeedParser(as);

        // get the review in question and validate it
        CourseReview catalogReview =
                CourseReviewLocalServiceUtil.getCourseReview(dbCourseReviewId);
        Validate.notNull(catalogReview,
                "Cannot create activity stream entry for addition of course " +
                "review with ID " + dbCourseReviewId +
                 " because it doesn't seem to be in persistence");

        // prepare the new entry
        Entry entry = as.addEntry();
        String entryId = getNextEntryId(as);
        entry.setId(entryId);

        // special case for updates -- could actually be removals
        // (i.e., the review was removed, but not destroyed)
        if (catalogReview.getRemoved() && (verbType.equals(AsVerb.VerbType.UPDATE))) {
            verbType = AsVerb.VerbType.REMOVE;
        }

        // populate it with the review
        parser.catalogReviewToParser(catalogReview, entry, verbType);
        return entryId;
    }


    /**
     * @param as - an Activity Stream feed
     *
     * @return the id to be used for a new entry in the feed passed in
     */
    public static String getNextEntryId(Feed as) {

        long maxEntryId = 0;
        long currentEntryId;
        // go through all the entries and find the biggest id
        for (Entry entry : as.getEntries()) {

            // for some reason, empty feeds have one entry with no id
            if (entry.getId() != null) {
                currentEntryId = AbderaAtomGenerator.extractIdFromAtomId(
                        entry.getId().toString(), AsExtension.ATOM_ID_DATA_TYPE_ACTIVITY);
                if (currentEntryId > maxEntryId) {
                    maxEntryId = currentEntryId;
                }
            }
        }

        maxEntryId++;

        // generate an ID based on one higher than the max
        return AbderaAtomGenerator.generateAtomId(maxEntryId,
                    AsExtension.ATOM_ID_DATA_TYPE_ACTIVITY);
    }


    /**
     * Removes entries from a feed that were updated before a certain time
     *
     * @param as - the activity stream feed
     * @param timestamp - the timestamp prior to which entries will be removed
     */
    public static void removeOlderEntries(Feed as, long timestamp) {

        Date expirationDate = new Date(timestamp);
        Date updatedDate = null;

        Iterator<Entry> it = as.getEntries().iterator();
        Entry entry;
        while (it.hasNext()) {
            entry = it.next();
            updatedDate = entry.getUpdated();
            if (updatedDate != null) {
                if (updatedDate.before(expirationDate)) {
                    log.debug("Discarding entry ID " + entry.getId() +
                        " from activity stream " + as.getId() +
                        " because it was last updated on " + entry.getUpdated() +
                        ", which is older than the requested date of " + expirationDate);
                    entry.discard();
                }
            }
            else {
                log.warn("While removing entries older than " + expirationDate +
                        " from the feed at " + as.getLink("self").getHref().toString() +
                        ", an entry was found (with id " + entry.getId() +
                        ") that has no updated date.");
            }
        }
    }


    /**
     * Copies a feed into a new one, excluding entries made after the cutoff
     * timestamp
     *
     * @param as
     * @param cutoffTimestamp
     */
    public static Feed copyFeed(Feed as, long cutoffTimestamp){
		Feed newAs = (Feed) as.clone();
		removeOlderEntries(newAs, cutoffTimestamp);
		return newAs;
	}
}
