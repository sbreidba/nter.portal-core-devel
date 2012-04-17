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

package org.nterlearning.atom.parser.staticParser;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.enumerations.NterEntryType;
import org.nterlearning.atom.enumerations.NterFeedType;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.AsExtension;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.generator.AbderaAtomGenerator;
import org.nterlearning.atom.parser.model.*;
import org.apache.abdera.ext.history.FeedPagingHelper;
import org.apache.abdera.ext.tombstones.Tombstone;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.ExtensibleElement;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import java.util.List;
import java.util.Vector;


public class StaticNterAtomParser {

    private NterExtension mNterExtension;
    private NterNameSpace mNterNS;


    private static Log log = LogFactoryUtil.getLog(StaticNterAtomParser.class);


    public StaticNterAtomParser(NterExtension extension) {
        mNterExtension = extension;
        mNterNS = NterNameSpace.fromNameSpace(mNterExtension.getNterNameSpace());
    }


    public NterFeedType getFeedType(Feed feed) {

        log.info("Determining feed type for NTER v" + mNterNS.getVersion() +
                " feed ID [" + feed.getId() + "]");
        List<Entry> entries = feed.getEntries();
        List<Tombstone> tombstones = getTombstoneEntries(feed);

        if (entries.size() > 0) {

            // find the first valid entry and use its type as the feed's type.
            // Keep going until we either run out of entries or find a
            // valid one
            NterFeedType currentFeedType = null;
            int i = 0;
            while ((currentFeedType == null) && (i < entries.size())) {
                try {
                    currentFeedType = mapEntryToFeedType(entries.get(i));
                }
                catch (IllegalArgumentException e) {
                    log.warn("Problem with entry: " + e.getMessage());
                    i++;
                }
            }

            if (currentFeedType != null) {
                NterFeedType currentEntryFeedType;
                for (Entry entry : entries) {
                    try {
                        currentEntryFeedType = mapEntryToFeedType(entry);
                        if (!currentEntryFeedType.equals(currentFeedType)) {
                            log.warn("Could not determine the NTER type for feed id [" + feed.getId() +
                                    "] because it contains multiple types of entries");
                            return NterFeedType.NONE;
                        }
                    }
                    catch (IllegalArgumentException e) {
                        // ignore IAEs because they indicate invalid entries,
                        // which will be skipped
                    }
                }

                return currentFeedType;
            }
            else {
                log.warn("Could not determine the NTER type for feed id " + feed.getId());
                return NterFeedType.NONE;
            }
        }
        else if (tombstones.size() > 0) {
            return NterFeedType.TOMBSTONE;
        }
        else {
            log.warn("There are no entries in feed id " + feed.getId());
            return NterFeedType.NONE;
        }
    }


    /**
     * Returns a list of all course entries in a given feed.
     *
     * @param feed Feed to parse course entries from
     *
     * @return List of Atom entries containing course objects
     */
    public List<Entry> getCourseEntries(Feed feed) {

        List<Entry> courseEntries = new Vector<Entry>();
        for (Entry entry : feed.getEntries()) {
            if (getEntryType(entry).equals(NterEntryType.COURSE)) {
                courseEntries.add(entry);
            }
        }

        return courseEntries;
    }


    /**
     * Returns a list of all course component entries in a given feed.
     *
     * @param feed Feed to parse course components from
     *
     * @return List of Atom entries containing course components.
     */
    public List<Entry> getCourseComponentEntries(Feed feed) {

        List<Entry> courseComponentEntries = new Vector<Entry>();
        for (Entry entry : feed.getEntries()) {
            if (getEntryType(entry).equals(NterEntryType.COURSE_COMPONENT)) {
                courseComponentEntries.add(entry);
            }
        }

        return courseComponentEntries;
    }


    /**
     * Returns a list of all course record entries in a given feed.
     *
     * @param feed Feed to parse course records from.
     *
     * @return List of Atom entries containing course records.
     */
    public List<Entry> getCourseRecordEntries(Feed feed) {

        List<Entry> courseRecordEntries = new Vector<Entry>();
        for (Entry entry : feed.getEntries()) {
            if (getEntryType(entry).equals(NterEntryType.COURSE_RECORD)) {
                courseRecordEntries.add(entry);
            }
        }

        return courseRecordEntries;
    }


    /**
     * Returns a list of all vocabulary entries in a given feed
     *
     * @param feed Feed to parse vocabulary entries from.
     *
     * @return List of Atom entries containing vocabulary objects.
     */
    public List<Entry> getVocabularyEntries(Feed feed) {

        List<Entry> vocabularyEntries = new Vector<Entry>();
        for (Entry entry : feed.getEntries()) {
            if (getEntryType(entry).equals(NterEntryType.VOCABULARY)) {
                vocabularyEntries.add(entry);
            }
        }

        return vocabularyEntries;
    }


    /**
     * Returns a list of entries containing course reviews.
     *
     * @param feed Feed to parse course review entries from.
     *
     * @return List of course review entries.
     */
    public List<Entry> getReviewEntries(Feed feed) {

        List<Entry> reviewEntries = new Vector<Entry>();
        for (Entry entry : feed.getEntries()) {
            if (getEntryType(entry).equals(NterEntryType.REVIEW)) {
                reviewEntries.add(entry);
            }
        }

        return reviewEntries;
    }


    /**
     * Returns a list of all tombstone entries in a given feed.
     *
     * @param feed The feed to process
     *
     * @return List of tomb stoned entries.
     */
    public List<Tombstone> getTombstoneEntries(Feed feed) {
        return feed.getExtensions(NterExtension.TS_DELETED_ENTRY);
    }


    /**
     * Returns true if the feed is considered complete, and false otherwise.
     * Completeness is determined by the includes of a <code><fh:complete></code>
     * entry.
     *
     * @param feed The feed to process
     *
     * @return True if feed is complete, false otherwise.
     */
    public boolean isFeedComplete(Feed feed) {
        return FeedPagingHelper.isComplete(feed);
    }


    /**
     * Given an NTER entry, returns the type of NTER feed it should be in.
     *
     * @param entry Entry to process
     *
     * @return Feed type (course, record, none) of the feed
     */
    public NterFeedType mapEntryToFeedType(Entry entry) {
        return mapEntryTypeToFeedType(getEntryType(entry));
    }


    /**
     * Given an NTER entry type, returns the type of NTER feed it should be in
     *
     * @param entryType The NterEntryType to base the feed type on.
     *
     * @return The Feed Type
     */
    public NterFeedType mapEntryTypeToFeedType(NterEntryType entryType) {
        if (entryType.equals(NterEntryType.COURSE) ||
                entryType.equals(NterEntryType.COURSE_COMPONENT) ||
                entryType.equals(NterEntryType.VOCABULARY)) {
            return NterFeedType.COURSES;
        }
        else if (entryType.equals(NterEntryType.COURSE_RECORD)) {
            return NterFeedType.RECORDS;
        }
        else if (entryType.equals(NterEntryType.REVIEW)) {
            return NterFeedType.REVIEW;
        }
        else if (entryType.equals(NterEntryType.NONE)) {
            return NterFeedType.NONE;
        }
        else {
            throw new IllegalArgumentException("Unknown entry type: " + entryType);
        }
    }


    public NterEntryType getEntryType(Entry entry) {
        return NterEntryType.fromValue(
                entry.getAttributeValue(
                    mNterExtension.getQName(NterExtension.ENTRY_TYPE_ATTRIBUTE_NAME)));
    }


    /**
     * Returns the nter:course element of an Atom Entry.
     *
     * @param entry Atom entry to parse
     *
     * @return nter:course element from entry
     */
    public NterCourse getCourse(Entry entry) {
        return entry.getExtension(
                mNterExtension.getQName(NterExtension.COURSE_ELEMENT_NAME));
    }


    /**
     * Returns the nter:component element of an Atom entry.
     *
     * @param entry Atom entry to parse
     *
     * @return the nter:courseComponent element from the entry
     */
    public NterComponent getComponent(Entry entry) {
        return entry.getExtension(
                mNterExtension.getQName(NterExtension.COMPONENT_ELEMENT_NAME));
    }


    /**
     * Returns the nter:courseRecord element from an Atom entry.
     *
     * @param entry Atom entry to parse.
     *
     * @return the nter:courseRecord element.
     */
    public NterCourseRecord getCourseRecord(Entry entry) {
        return entry.getExtension(
                mNterExtension.getQName(NterExtension.COURSE_RECORD_ELEMENT_NAME));
    }


    /**
     * Returns the nter:vocabulary element from an Atom entry.
     *
     * @param entry Atom entry to parse
     *
     * @return the nter:vocabulary element from the entry.
     */
    public NterVocabulary getVocabulary(Entry entry) {
        return entry.getExtension(
                mNterExtension.getQName(NterExtension.VOCABULARY_ELEMENT_NAME));
    }


    /**
     * Returns the nter:vcard element from a Person object.
     *
     * @param person Person to process a vcard from.
     *
     * @return Either the vcard, if found, or null.
     */
    public String getVCard(Person person) {
        ExtensibleElement vcard =
                person.getExtension(mNterExtension.getQName(NterExtension.VCARD_ELEMENT_NAME));
        return (vcard != null) ? vcard.getText() : null;
    }


    /**
     * Gets the 'studentUserId' attribute from an author element in an Activity
     * Stream review entry
     *
     * @param person Person to retrieve user id from
     *
     * @return the nter:studentUserId attribute.
     */
    public String getActorId(Person person) {
        return person.getAttributeValue(
                mNterExtension.getQName(NterExtension.STUDENT_USER_ID_ATTRIBUTE_NAME));
    }


    /**
     * Gets the 'trustedReviewer' attribute from an author element in an
     * Activity Stream review entry
     *
     * @param person Person to retrieve user id from
     *
     * @return the nter:trustedReviewer attribute.
     */
    public String getTrustedReviewer(Person person) {
        return person.getAttributeValue(
                mNterExtension.getQName(NterExtension.TRUSTED_REVIEWER_ATTRIBUTE_NAME));
    }


    public String getNterInstance(Entry entry) {
        return AbderaAtomGenerator.extractHostFromAtomId(entry.getId().toString());
    }


    /**
     * Gets the 'object-type' element from an author element in an Activity
     * Stream review entry
     *
     * @param person Atom <code>Person</code> entry
     *
     * @return ActivityStream object-type
     */
    public static AsObjectType getActorObjectType(Person person) {
        return person.getExtension(AsExtension.OBJECT_TYPE_ELEMENT);
    }


    /**
     * Returns the associated action verb for the Activity Stream Entry.
     *
     * @param activityStreamEntry Abdera Entry object
     *
     * @return Activity Stream Verb
     */
    public static AsVerb getVerb(Entry activityStreamEntry) {
        return activityStreamEntry.getExtension(AsExtension.VERB_ELEMENT);
    }


    /**
     * @param activityStreamEntry Atom ActivityStream Entry
     *
     * @return Activity Stream Object Element of the Entry
     */
    public static AsObject getObject(Entry activityStreamEntry) {
        return activityStreamEntry.getExtension(AsExtension.OBJECT_ELEMENT);
    }


    /**
     * @param activityStreamEntry Atom ActivityStream Entry
     *
     * @return Activity Stream Verb element of the Entry
     */
    public static AsTarget getTarget(Entry activityStreamEntry) {
        return activityStreamEntry.getExtension(AsExtension.TARGET_ELEMENT);
	}
}