/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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

package org.nterlearning.atom.parser.staticParser;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetCategoryUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.nterlearning.atom.enumerations.NterEntryType;
import org.nterlearning.atom.enumerations.NterFeedType;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.generator.AbderaAtomGenerator;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.ServiceContextUtil;
import org.nterlearning.atom.parser.dao.*;
import org.nterlearning.atom.parser.feedParser.FeedParser;
import org.nterlearning.atom.parser.feedParser.FeedParserFactory;
import org.nterlearning.atom.parser.graph.CyclicGraphException;
import org.nterlearning.atom.parser.graph.DirectedAcyclicGraph;
import org.nterlearning.atom.parser.graph.DirectedAcyclicGraphNode;
import org.nterlearning.atom.parser.model.*;
import org.nterlearning.atom.parser.validator.FeedValidator;
import org.nterlearning.atom.parser.validator.FeedValidatorFactory;
import org.nterlearning.course.enumerations.FeedType;
import org.nterlearning.course.enumerations.RelationshipType;
import org.nterlearning.crawl.nutch.CrawlTool;
import org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.model.impl.FeedSyncHistoryImpl;
import org.nterlearning.datamodel.catalog.service.*;
import org.nterlearning.utils.ExpandoConstants;
import org.apache.abdera.ext.tombstones.Tombstone;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.abdera.model.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


public class StaticParser {

    public Log mLog = LogFactoryUtil.getLog(StaticParser.class);

    public static final String COURSE_VOCABULARY_TYPE = "COURSE";

    private NterNameSpace mNterNs;
    private NterExtension mNterExtension;
    private StaticNterAtomParser mNterParser;
    private FeedValidator mValidator;
    private FeedParser mFeedParser;


    public StaticParser(String ns) {
        mNterNs = NterNameSpace.fromNameSpace(ns);

        mNterExtension = new NterExtension(mNterNs.getNameSpace());
        mNterParser = new StaticNterAtomParser(mNterExtension);
        mValidator = FeedValidatorFactory.getFeedValidator(mNterNs);
        mFeedParser = FeedParserFactory.getFeedParser(mNterNs);
    }


    public StaticParser(NterNameSpace ns) {
        mNterNs = ns;

        mNterExtension = new NterExtension(mNterNs.getNameSpace());
        mNterParser = new StaticNterAtomParser(mNterExtension);
        mValidator = FeedValidatorFactory.getFeedValidator(mNterNs);
        mFeedParser = FeedParserFactory.getFeedParser(mNterNs);
    }


    public NterNameSpace getNterNameSpace() {
        return mNterNs;
    }


    public NterExtension getNterExtension() {
        return mNterExtension;
    }


    /**
     * Processes a feed object, persisting all included components.
     *
     * @param feed Atom Feed to process
     * @param fc Associated Feed Context object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay Exception for Database errors
     * @throws com.liferay.portal.kernel.exception.PortalException Liferay Exception for database errors
     */
    public void persistFeed(Feed feed, FeedContext fc)
            throws SystemException, PortalException {

        String ourHost = ServiceContextUtil.getDefaultVirtualHost();
        String feedHost = AbderaAtomGenerator.extractHostFromAtomId(feed.getId().toString());
        if (ourHost.equals(feedHost)) {
            mLog.info("Not persisting feed with ID " + feed.getId() +
                    " because it appears to have been created by this server.");
            return;
        }

        FeedSyncHistory feedHistory = new FeedSyncHistoryImpl();
        Boolean completeFeed = mNterParser.isFeedComplete(feed);

        NterFeedType feedType = mNterParser.getFeedType(feed);
        mLog.info("Persisting " + feedType + " feed with ID [" + feed.getId().toString() + "]");

        FeedReference feedReference = mFeedParser.parserToCatalog(feed, fc);
        feedReference = persistFeedReference(feedReference);

        // process any pubsubhubbubs that may be included in the feed
        feedReference = StaticParserUtil.processHubs(feed, fc, feedReference);

        fc.setFeedReferenceId(feedReference.getFeedReferenceId());
        fc.setFeedReferenceGroupId(feedReference.getGroupId());

        persistFeedPersons(feed.getAuthors(), feed.getContributors());

        try {
            // begin storing the historical information
            feedHistory.setFeedReferenceId(feedReference.getFeedReferenceId());
            feedHistory.setSyncDate(new Date());

            if (feedType.equals(NterFeedType.COURSES)) {
                List<Entry> vocabularyEntries = mNterParser.getVocabularyEntries(feed);
                List<Entry> courseComponentEntries = mNterParser.getCourseComponentEntries(feed);
                List<Entry> courseEntries = mNterParser.getCourseEntries(feed);
                List<Tombstone> tombstonesEntries = mNterParser.getTombstoneEntries(feed);

                if (vocabularyEntries.size() > 1) {
                    String errorMsg = "Feed with ID [" + feed.getId() + "] contains more than one vocabulary";
                    mLog.error(errorMsg);

                    feedReference.setSyncDate(new Date());
                    feedReference.setSyncSuccess(false);
                    feedHistory.setSuccess(false);
                    feedHistory.setSyncMessage(errorMsg);
                    FeedSyncHistoryLocalServiceUtil.updateFeedSyncHistory(feedHistory);
                    return;
                }
                else {
                    mLog.info("Persisting " + vocabularyEntries.size() + " VOCABULARY feed entries");
                    persistVocabularyEntries(vocabularyEntries, fc);
                }

                mLog.info("Persisting " + courseComponentEntries.size() + " COURSE COMPONENT feed entries");
                persistCourseComponentEntries(courseComponentEntries, fc);

                mLog.info("Persisting " + courseEntries.size() + " COURSE feed entries");
                persistCourseEntries(courseEntries, fc);

                mLog.info("Persisting " + tombstonesEntries.size() + " Tombstone Entries");
                persistTombstoneEntries(tombstonesEntries, fc);

                if (completeFeed) {
                    StaticParserUtil.removeMissingCourseComponentEntries(courseComponentEntries, fc);
                    StaticParserUtil.removeMissingCourseEntries(courseEntries, fc);
                }

                // since everything processed correctly, update the sync time
                feedReference.setSyncDate(new Date());
                feedReference.setSyncSuccess(true);
                feedReference.setFeedType(FeedType.course.getCodeValue());
                persistFeedReference(feedReference);

                feedHistory.setSuccess(true);
                feedHistory.setNumberOfEntries(courseEntries.size() +
                        courseComponentEntries.size() + vocabularyEntries.size()
                        + tombstonesEntries.size());
            }
            else if (feedType.equals(NterFeedType.RECORDS)) {
                mLog.debug("Persisting COURSE RECORD feed entries");
                List<Entry> courseRecords = mNterParser.getCourseRecordEntries(feed);
                persistCourseRecordEntries(courseRecords, fc);

                List<Tombstone> tombstones = mNterParser.getTombstoneEntries(feed);
                mLog.info("Persisting " + tombstones.size() + " Tombstone Entries");
                persistTombstoneEntries(tombstones, fc);

                // since everything processed correctly, update the sync time
                feedReference.setSyncDate(new Date());
                feedReference.setSyncSuccess(true);
                feedReference.setFeedType(FeedType.record.getCodeValue());
                persistFeedReference(feedReference);

                feedHistory.setSuccess(true);
                feedHistory.setNumberOfEntries(courseRecords.size() + tombstones.size());
            }
            else if (feedType.equals(NterFeedType.TOMBSTONE)) {
                List<Tombstone> tombstones = mNterParser.getTombstoneEntries(feed);
                mLog.info("Persisting " + tombstones.size() + " Tombstone Entries");
                persistTombstoneEntries(tombstones, fc);

                // since everything processed correctly, update the feed info
                // note that tombstone feeds are simply extensions of an existing feed
                feedReference.setSyncDate(new Date());
                feedReference.setSyncSuccess(true);
                persistFeedReference(feedReference);

                feedHistory.setSuccess(true);
                feedHistory.setNumberOfEntries(tombstones.size());
            }
            else if (feedType.equals(NterFeedType.REVIEW)) {
                List<Entry> reviews = mNterParser.getReviewEntries(feed);
                mLog.info("Persisting " + reviews.size() + " REVIEW feed entries");
                persistReviewEntries(reviews, fc);

                // since everything processed correctly, update the sync time
                feedReference.setSyncDate(new Date());
                feedReference.setSyncSuccess(true);
                feedReference.setFeedType(FeedType.review.getCodeValue());
                persistFeedReference(feedReference);

                feedHistory.setSuccess(true);
                feedHistory.setNumberOfEntries(reviews.size());
            }
            else if (feedType.equals(NterFeedType.NONE)) {
                String error = "Cannot persist feed entries due to the feed " +
                        "type being undetermined.  In feed " + feed.getId();
                mLog.error(error);

                // even though the feed could not be processed, we want to track
                // that the attempt was made
                feedReference.setSyncDate(new Date());
                feedReference.setSyncSuccess(false);
                persistFeedReference(feedReference);

                feedHistory.setSuccess(false);
                feedHistory.setSyncMessage(error);
            }
            else {
                String error = "Cannot persist feed entries due to unknown feed " +
                        "type [" + feedType + "] in feed " + feed.getId();
                mLog.error(error);

                // even though the feed could not be processed, we want to track
                // that the attempt was made
                feedReference.setSyncDate(new Date());
                feedReference.setSyncSuccess(false);
                persistFeedReference(feedReference);

                feedHistory.setSuccess(false);
                feedHistory.setSyncMessage(error);
            }
        }
        catch (Exception e) {
            // even though the feed could not be processed, we want to track
            // that the attempt was made
            feedReference.setSyncDate(new Date());
            feedReference.setSyncSuccess(false);
            persistFeedReference(feedReference);

            feedHistory.setSyncDate(new Date());
            feedHistory.setSuccess(false);
            feedHistory.setSyncMessage(e.getMessage());
            FeedSyncHistoryLocalServiceUtil.addFeedSyncHistory(feedHistory);

            mLog.error("Error persisting " + feedType +
                    " feed with ID [" + feed.getId().toString() + "]: " + e, e);
        }

        if (feedHistory.getSuccess()) {
            feedHistory.setSyncMessage(fc.getSyncMessage());
        }

        FeedSyncHistoryLocalServiceUtil.addFeedSyncHistory(feedHistory);
        FeedSyncHistoryLocalServiceUtil.pruneFeedSyncHistory(feedReference.getFeedReferenceId());
    }


    /**
     * Persists a list of tombstone entries found in the feed.
     *
     * @param tombstones List of tombstone entries.
     * @param fc Associated feed
     */
    public void persistTombstoneEntries(List<Tombstone> tombstones, FeedContext fc) {
        for (Tombstone tombstone : tombstones) {
            try {
                persistTombstoneEntry(tombstone, fc);
            }
            catch (SystemException se) {
                mLog.error("Could not process tombstone record for " + tombstone.getRef());
                fc.addSyncMessage("ERR-Tombstone persist id [" + tombstone.getRef() + "]");
            }
        }
    }


    /**
     * Persists a tombstone entry by setting the remove state of the associated
     * object (course, course-component, student-record).  If the object does
     * not exist yet, a new one is created.
     *
     * @param tombstone A tombstone entry
     * @param fc Associated feed
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Standard Liferay Database Exception
     */
    private void persistTombstoneEntry(Tombstone tombstone, FeedContext fc)
            throws SystemException {

        NterEntryType type = NterEntryType.fromValue(
                tombstone.getAttributeValue(
                        mNterExtension.getQName(NterExtension.ENTRY_TYPE_ATTRIBUTE_NAME)));
        String refId = tombstone.getRef();
        Date removedDate = tombstone.getWhen();

        switch (type) {
            case COURSE:

                Course course = CourseLocalServiceUtil.fetchByCourseIri(refId);
                if (course == null) {
                    try {
                        course = CourseLocalServiceUtil.addCourse(fc.getUserId(),
                                fc.getFeedReferenceId(), null, refId, removedDate,
                                null, null, null, null, null, null, null, null, null,
                                0.0, "", "", null, null, null, null, 0.0, null, null, null,
                                ServiceContextUtil.createDefaultServiceContext());
                    }
                    catch (Exception e) {
                        mLog.error("Could not create course [" + refId + "]");
                        fc.addSyncMessage("ERR-Tombstone Course persist id [" + refId + "]");
                        break;
                    }
                }

                course.setRemoved(true);
                course.setRemovedDate(removedDate);
                CourseLocalServiceUtil.updateCourse(course, true);
                break;

            case COURSE_COMPONENT:

                Component component = ComponentLocalServiceUtil.fetchByComponentIri(refId);
                if (component == null) {
                    try {
                        component = ComponentLocalServiceUtil.addComponent(
                                fc.getCompanyId(), fc.getFeedReferenceId(),
                                refId, null, null, null, null, removedDate, 0, 0,
                                null, null, 0.0, null, null, null);
                    }
                    catch (Exception e) {
                        mLog.error("Could not create course component [" + refId + "]");
                        fc.addSyncMessage("ERR-Tombstone Component persist id [" + refId + "]");
                        break;
                    }
                }

                component.setRemoved(true);
                component.setRemovedDate(removedDate);
                ComponentLocalServiceUtil.updateComponent(component, true);
                break;

            case COURSE_RECORD:

                CourseRecord record = CourseRecordLocalServiceUtil.fetchByCourseRecordIri(refId);
                if (record == null) {
                    // insert a skeleton tombstone entry into courseRecord using the
                    // removedDate for the updatedDate and zero for user information
                    try {
                        record = CourseRecordLocalServiceUtil.addCourseRecord(
                                fc.getFeedReferenceId(), refId, 0, null, null,
                                removedDate, null, false, false);
                    }
                    catch (Exception e) {
                        mLog.error("Could not create courseRecord [" + refId + "]");
                        fc.addSyncMessage("ERR-Tombstone CourseRecord persist id [" + refId + "]");
                        break;
                    }
                }

                // now update the courseRecord with tombstone removed date and flag
                record.setRemoved(true);
                record.setRemovedDate(removedDate);
                CourseRecordLocalServiceUtil.updateCourseRecord(record, true);
                break;

            default:
                break;
        }
    }


    /**
     * Persists an NTER FeedReference to the database.
     *
     * @param feedReference The FeedReference object to commit to the database.
     *
     * @return Freshly committed FeedReference object.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     */
    private FeedReference persistFeedReference(FeedReference feedReference)
            throws SystemException {

        String iri = feedReference.getFeedIri();
        FeedReference fetchedFr = FeedReferenceLocalServiceUtil.fetchByFeedIri(iri);

        if (fetchedFr == null) {
            mLog.debug("Creating feed reference with IRI [" + iri + "]");

            feedReference.setCreateDate(new Date());
            feedReference.setRemoved(false);
            feedReference.setRemovedDate(null);

            return FeedReferenceLocalServiceUtil.addFeedReference(feedReference);
        }
        else {
            mLog.debug("Updating feed reference with IRI [" + iri + "]");

            fetchedFr.setSyncDate(feedReference.getSyncDate());
            fetchedFr.setSyncSuccess(feedReference.getSyncSuccess());
            fetchedFr.setFeedType(feedReference.getFeedType());

            fetchedFr.setFeedVersion(feedReference.getFeedVersion());
            fetchedFr.setContentProviderId(feedReference.getContentProviderId());

            fetchedFr.setPshb(feedReference.getPshb());
            fetchedFr.setPshbSubscribed(feedReference.getPshbSubscribed());

            return FeedReferenceLocalServiceUtil.updateFeedReference(fetchedFr);
        }
    }


    /**
     * Persists entries which contain NTER courses
     *
     * @param courseEntries Atom feed entries corresponding to Course object.
     * @param fc FeedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void persistCourseEntries(List<Entry> courseEntries, FeedContext fc)
            throws PortalException, SystemException {

        for (Entry courseEntry : sortCourseEntries(courseEntries)) {
            if (mValidator.validateCourseEntry(courseEntry)) {
                try {
                    mLog.debug("Persisting course entry with id [" + courseEntry.getId() + "]");
                    persistCourseEntry(courseEntry, fc);
                }
                catch (Exception e) {
                    String errorMsg = "Error persisting course with id [" + courseEntry.getId() + "]: " + e;
                    mLog.error(errorMsg, e);
                    fc.addSyncMessage("ERR-Course persist id [" + courseEntry.getId() + "]");
                }
            }
            else {
                mLog.warn("Skipping persistence of course entry with id ["
                        + courseEntry.getId() + "] due to validation errors");
            }
        }
    }


    /**
     * Persist a single Atom feed entry that corresponds to a Course object.
     *
     * @param courseEntry Atom feed entry representing a course object
     * @param fc corresponding FeedContext
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void persistCourseEntry(Entry courseEntry, FeedContext fc)
            throws PortalException, SystemException {

        boolean crawlThisCourse = false;  // true only when updated or new

        Course course =
                CourseLocalServiceUtil.fetchByCourseIri(courseEntry.getId().toString());
        if (course != null) {
            // if the entry was updated before what we already have, don't do anything
            if ((courseEntry.getUpdated().compareTo(course.getUpdatedDate()) < 1)) {
                mLog.debug("Entry with ID " + courseEntry.getId() + " will be ignored because " +
                        " it's not newer than what's already persisted: " +
                        "its updated date is " + courseEntry.getUpdated() +
                        ", and the corresponding course was updated on " +
                        course.getUpdatedDate());
            }
            else {
                updateParserCourse(courseEntry, course.getCourseId(), fc);
                crawlThisCourse = true;
            }
        }
        else {
            createParserCourse(courseEntry, fc);
            crawlThisCourse = true;
        }

        // Crawl this course (only if this is the master crawl node)
        if (crawlThisCourse && CrawlTool.getInstance().isMaster()) {
            try {
                course = CourseLocalServiceUtil.fetchByCourseIri(courseEntry.getId().toString());
                CrawlTool.getInstance().addCourse(course);
            }
            catch (Exception e) {
                mLog.error(e.getMessage());
            }
        }
    }


    /**
     * Creates a new parser course from an Atom Feed entry and stores in the
     * database.
     *
     * @param courseEntry Atom feed entry corresponding to the course object
     * @param fc Associated FeedContext object
     *
     * @return Newly created course object.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private Course createParserCourse(Entry courseEntry, FeedContext fc)
            throws SystemException, PortalException {

        mLog.debug("Creating course with entry id: " + courseEntry.getId());
        Course catalogCourse = mFeedParser.parserCourseToCatalog(courseEntry, fc);
        catalogCourse = CourseLocalServiceUtil.addCourse(
                fc.getUserId(),
                fc.getFeedReferenceId(),
                catalogCourse.getHref(),
                catalogCourse.getFullTextHref(),
                catalogCourse.getCourseIri(),
                catalogCourse.getUpdatedDate(),
                catalogCourse.getTitle(),
                catalogCourse.getTranscriptAbstract(),
                catalogCourse.getDescription(),
                catalogCourse.getKeywords(),
                catalogCourse.getCopyright(),
                catalogCourse.getRatingLevel(),
                catalogCourse.getRatingReason(),
                catalogCourse.getDuration(),
                catalogCourse.getDurationStandard(),
                catalogCourse.getFeaturedStatus(),
                catalogCourse.getSupersedesCourseIri(),
                catalogCourse.getSupersededByCourseIri(),
                catalogCourse.getReleaseOnDate(),
                catalogCourse.getAcceptUntilDate(),
                catalogCourse.getVersion(),
                catalogCourse.getVersionDate(),
                catalogCourse.getPrice(),
                catalogCourse.getPriceUnit(),
                catalogCourse.getPriceTerms(),
                catalogCourse.getPriceExpiration(),
                fc.getSc());
        NterCourse parserCourse = mNterParser.getCourse(courseEntry);

        try {
            persistParserCourseChildren(parserCourse, catalogCourse,
                    courseEntry.getAuthors(), courseEntry.getContributors(),
                    courseEntry.getLinks("download"), fc);
            return catalogCourse;
        }
        catch (Exception e) {
            // something bad happened, rollback the persist
            mLog.error("Failure while persisting the children for course [" +
                    courseEntry.getId() + "].  Error: [" + e +
                    "].  Removing course from persistence.", e);
            CourseLocalServiceUtil.deleteCourse(catalogCourse);
            throw new SystemException(e);
        }
    }


    /**
     * Updates a course object already stored in the database.  This process finds
     * the course in the database and overrides the values with those found in the
     * entry object.
     *
     * @param courseEntry Atom feed entry representing a course object
     * @param dbCourseId course Id to search for in the database
     * @param fc Associated feedContext object
     *
     * @return Updated course.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private Course updateParserCourse(Entry courseEntry, long dbCourseId, FeedContext fc)
            throws PortalException, SystemException {

        mLog.debug("Updating course with entry id: " + courseEntry.getId());

        Course existingCourse = CourseLocalServiceUtil.getCourse(dbCourseId);
        Course catalogCourse = mFeedParser.parserCourseToCatalog(courseEntry, fc);

        // if the current remove date is more recent than the update date,
        // keep the component removed, otherwise, let it become enabled
        if (existingCourse.isRemoved() &&
                existingCourse.getRemovedDate().after(catalogCourse.getUpdatedDate())) {
            catalogCourse.setRemoved(true);
            catalogCourse.setRemovedDate(existingCourse.getRemovedDate());
        }

        // if the version has changed then set the versionDate otherwise leave it
        if (existingCourse.getVersion().equals(catalogCourse.getVersion())) {
            catalogCourse.setVersionDate(existingCourse.getUpdatedDate());
        }
        else {
            catalogCourse.setVersionDate(catalogCourse.getUpdatedDate());
        }

        // certain properties are not found in the feed, so their values are
        // pulled from the database (existingCourse)
        catalogCourse = CourseLocalServiceUtil.updateCourse(
                fc.getUserId(),
                dbCourseId,
                fc.getFeedReferenceId(),
                catalogCourse.getHref(),
                catalogCourse.getFullTextHref(),
                catalogCourse.getCourseIri(),
                catalogCourse.getUpdatedDate(),
                catalogCourse.getTitle(),
                catalogCourse.getTranscriptAbstract(),
                catalogCourse.getDescription(),
                catalogCourse.getKeywords(),
                catalogCourse.getCopyright(),
                catalogCourse.getRatingLevel(),
                catalogCourse.getRatingReason(),
                catalogCourse.getDuration(),
                catalogCourse.getDurationStandard(),
                existingCourse.getFeaturedStatus(),
                existingCourse.getPopularWeight(),
                existingCourse.getAccessCount(),
                existingCourse.getCompletedCount(),
                catalogCourse.getRemoved(),
                catalogCourse.getRemovedDate(),
                existingCourse.getSupersedesCourseIri(),
                existingCourse.getSupersededByCourseIri(),
                catalogCourse.getReleaseOnDate(),
                catalogCourse.getAcceptUntilDate(),
                catalogCourse.getVersion(),
                catalogCourse.getVersionDate(),
                catalogCourse.getPrice(),
                catalogCourse.getPriceUnit(),
                catalogCourse.getPriceTerms(),
                catalogCourse.getPriceExpiration(),
                existingCourse.getOneStarRateCount(),
                existingCourse.getTwoStarRateCount(),
                existingCourse.getThreeStarRateCount(),
                existingCourse.getFourStarRateCount(),
                existingCourse.getFiveStarRateCount(),
                fc.getSc());
        NterCourse parserCourse = mNterParser.getCourse(courseEntry);

        try {
            persistParserCourseChildren(parserCourse, catalogCourse,
                    courseEntry.getAuthors(), courseEntry.getContributors(),
                    courseEntry.getLinks("download"), fc);
            return catalogCourse;
        }
        catch (Exception e) {
            // something bad happened, rollback the persist
            mLog.error("Failure while persisting the children for course [" +
                    courseEntry.getId() + "]. Error: [" + e +
                    "]. Removing course from persistence.", e);
            CourseLocalServiceUtil.updateCourse(existingCourse.getUserId(),
                    existingCourse.getCourseId(),
                    existingCourse.getFeedReferenceId(),
                    existingCourse.getHref(),
                    existingCourse.getFullTextHref(),
                    existingCourse.getCourseIri(),
                    existingCourse.getUpdatedDate(),
                    existingCourse.getTitle(),
                    existingCourse.getTranscriptAbstract(),
                    existingCourse.getDescription(),
                    existingCourse.getKeywords(),
                    existingCourse.getCopyright(),
                    existingCourse.getRatingLevel(),
                    existingCourse.getRatingReason(),
                    existingCourse.getDuration(),
                    existingCourse.getDurationStandard(),
                    existingCourse.getFeaturedStatus(),
                    existingCourse.getPopularWeight(),
                    existingCourse.getAccessCount(),
                    existingCourse.getCompletedCount(),
                    existingCourse.getRemoved(),
                    existingCourse.getRemovedDate(),
                    existingCourse.getSupersedesCourseIri(),
                    existingCourse.getSupersededByCourseIri(),
                    existingCourse.getReleaseOnDate(),
                    existingCourse.getAcceptUntilDate(),
                    existingCourse.getVersion(),
                    existingCourse.getVersionDate(),
                    existingCourse.getPrice(),
                    existingCourse.getPriceUnit(),
                    existingCourse.getPriceTerms(),
                    existingCourse.getPriceExpiration(),
                    existingCourse.getOneStarRateCount(),
                    existingCourse.getTwoStarRateCount(),
                    existingCourse.getThreeStarRateCount(),
                    existingCourse.getFourStarRateCount(),
                    existingCourse.getFiveStarRateCount(),
                    fc.getSc());
            throw new SystemException(e);
        }
    }


    /**
     * Persists the children elements and objects of a course (e.g., images, links,
     * etc).
     *
     * @param parserCourse The NterCourse object representation of the course
     * @param catalogCourse The Course object representation
     * @param parserAuthors List of course authors
     * @param parserContributors List of course contributors
     * @param downloadLinks List of download links
     * @param fc The associated feedContext object for the course
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     * @throws NterCatalogCourseDependencyException Thrown if required dependencies
     * are missing
     */
    private void persistParserCourseChildren(NterCourse parserCourse,
            Course catalogCourse, List<Person> parserAuthors,
            List<Person> parserContributors, List<Link> downloadLinks, FeedContext fc)
            throws PortalException, SystemException, NterCatalogCourseDependencyException {

        mLog.debug("persisting course children");

        // save the already persisted children in case of rollback
        List<Contributor> existingContributors =
                CourseLocalServiceUtil.getContributors(catalogCourse);
        List<CourseImage> existingCourseImages =
                CourseLocalServiceUtil.getCourseImages(catalogCourse);
        List<Courses_Components> existingCourses_Componentses =
                CourseLocalServiceUtil.getCourses_Componentses(catalogCourse);
        List<CourseRelated> existingCourseRelateds =
                CourseLocalServiceUtil.getCourseRelateds(catalogCourse);
        List<CourseRequirement> existingCourseRequirements =
                CourseLocalServiceUtil.getCourseRequirements(catalogCourse);

        // now persist all of children in this course
        long catalogCourseId = catalogCourse.getCourseId();
        List<Contributor> catalogContributors =
                Converter.parserAuthorsToCatalog(
                        parserAuthors, catalogCourseId, NterEntryType.COURSE, mNterParser);
        catalogContributors.addAll(
                Converter.parserContributorsToCatalog(
                        parserContributors, catalogCourseId, NterEntryType.COURSE, mNterParser));
        catalogContributors.addAll(
                Converter.parserOrganizationsToCatalog(
                        parserCourse.getOrganizations(), catalogCourseId, NterEntryType.COURSE));

        List<CourseImage> catalogImages = Converter.parserImagesToCatalog(
                parserCourse.getImages(), catalogCourseId, true);
        List<Courses_Components> catalogComponents = Converter.parserComponentRefsToCatalog(
                parserCourse.getComponentRefs(), catalogCourseId, true, false);
        List<CourseRelated> catalogRelateds = Converter.parserRelatedsToCatalog(
                parserCourse.getRelateds(), catalogCourseId, false);
        List<CourseRequirement> catalogRequirements = Converter.parserRequirementsToCatalog(
                parserCourse.getRequirements(), catalogCourseId);

        // persist all the children, catching exceptions along the way and rolling back
        // the parts already persisted when they happen
        CatalogContributorDao ccd = new CatalogContributorDao();
        CatalogImageDao cid = new CatalogImageDao();
        CatalogComponentDao cld = new CatalogComponentDao();
        CatalogRelatedDao creld = new CatalogRelatedDao(fc);
        CatalogRequirementDao creqd = new CatalogRequirementDao();

        try {
            ccd.persistListWithCompareAndRollback(existingContributors,
                    catalogContributors, catalogCourseId);
        }
        catch (Exception e) {
            throw new SystemException("Error while persisting catalog contributors. ", e);
        }

        try {
            cid.persistListWithCompareAndRollback(existingCourseImages,
                    catalogImages, catalogCourseId);
        }
        catch (Exception e) {
            List<Contributor> revertContributors = ccd.getRevertList(catalogCourseId);
            ccd.deleteList(revertContributors);
            ccd.addList(existingContributors);
            throw new SystemException("Error while persisting catalog images. " +
                    "Rolled back catalog contributors as well.", e);
        }

        try {
            cld.persistListWithCompareAndRollback(existingCourses_Componentses,
                    catalogComponents, catalogCourseId);
        }
        catch (Exception e) {
            List<Contributor> revertContributors = ccd.getRevertList(catalogCourseId);
            ccd.deleteList(revertContributors);
            ccd.addList(existingContributors);
            List<CourseImage> revertCourseImages = cid.getRevertList(catalogCourseId);
            cid.deleteList(revertCourseImages);
            cid.addList(existingCourseImages);
            throw new SystemException("Error while persisting catalog Courses_components. " +
                    "Rolled back catalog contributors and images as well.", e);
        }

        try {
            creld.persistListWithCompareAndRollback(existingCourseRelateds,
                    catalogRelateds, catalogCourseId);
        }
        catch (Exception e) {
            List<Contributor> revertContributors = ccd.getRevertList(catalogCourseId);
            ccd.deleteList(revertContributors);
            ccd.addList(existingContributors);
            List<CourseImage> revertCourseImages = cid.getRevertList(catalogCourseId);
            cid.deleteList(revertCourseImages);
            cid.addList(existingCourseImages);
            List<Courses_Components> revertCourses_Componentses = cld.getRevertList(catalogCourseId);
            cld.deleteList(revertCourses_Componentses);
            cld.addList(existingCourses_Componentses);
            throw new SystemException("Error while persisting catalog relateds. " +
                    "Rolled back catalog contributors, images and components as well.", e);
        }

        try {
            creqd.persistListWithCompareAndRollback(existingCourseRequirements,
                    catalogRequirements, catalogCourseId);
        }
        catch (Exception e) {
            List<Contributor> revertContributors = ccd.getRevertList(catalogCourseId);
            ccd.deleteList(revertContributors);
            ccd.addList(existingContributors);
            List<CourseImage> revertCourseImages = cid.getRevertList(catalogCourseId);
            cid.deleteList(revertCourseImages);
            cid.addList(existingCourseImages);
            List<Courses_Components> revertCourses_Componentses = cld.getRevertList(catalogCourseId);
            cld.deleteList(revertCourses_Componentses);
            cld.addList(existingCourses_Componentses);
            List<CourseRelated> revertCourseRelateds = creld.getRevertList(catalogCourseId);
            creld.deleteList(revertCourseRelateds);
            creld.addList(existingCourseRelateds);
            throw new SystemException("Error while persisting catalog requirements. " +
                    "Rolled back catalog contributors, images, components and relateds as well.", e);
        }

        //Special check for relatedCourseIri to update relatedCourseId set to zero.
        try {
            CatalogRelatedDao.updateCourseRelatedWithIriAndRollback(catalogCourse, fc);
        }
        catch (Exception e) {
            List<Contributor> revertContributors = ccd.getRevertList(catalogCourseId);
            ccd.deleteList(revertContributors);
            ccd.addList(existingContributors);
            List<CourseImage> revertCourseImages = cid.getRevertList(catalogCourseId);
            cid.deleteList(revertCourseImages);
            cid.addList(existingCourseImages);
            List<Courses_Components> revertCourses_Componentses = cld.getRevertList(catalogCourseId);
            cld.deleteList(revertCourses_Componentses);
            cld.addList(existingCourses_Componentses);
            List<CourseRelated> revertCourseRelateds = creld.getRevertList(catalogCourseId);
            creld.deleteList(revertCourseRelateds);
            creld.addList(existingCourseRelateds);
            List<CourseRequirement> revertCourseRequirements = creqd.getRevertList(catalogCourseId);
            creqd.deleteList(revertCourseRequirements);
            creqd.addList(existingCourseRequirements);
            throw new SystemException("Error while updating catalog course related entries using IRI. " +
                    "Rolled back catalog contributors, images, components, relateds and requirements as well.", e);
        }

        // persist any external links
        StaticParserUtil.persistExternalDownloadLinks(catalogCourseId, 0, downloadLinks);
    }


    /**
     * Given a list of entries containing NTER courses, sorts them such that the
     * returned list is sorted such that entries with dependencies are listed after
     * the entries on which they are dependent
     *
     * @param unsortedEntries List of Atom Feed Entries corresponding to course
     * objects
     *
     * @return A sorted list of feed entries
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     */
    private List<Entry> sortCourseEntries(List<Entry> unsortedEntries)
            throws SystemException {

        List<Entry> sortedEntries = new Vector<Entry>(unsortedEntries.size());
        List<Entry> externalReferenceEntries = new Vector<Entry>();

        NterCourse course;

        // get the entries which reference other entries in this list and go
        // through all of the entries in the feed and convert them to a DAG
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        for (Entry entry : unsortedEntries) {
            String entryId = entry.getId().toString();

            // get the course from the entry
            course = mNterParser.getCourse(entry);
            if (course == null) {
                mLog.error("course is null in entry " + entryId);
                continue;
            }

            // get the related courses from the course and for each related
            // course try to find it in the feed
            for (NterRelated related : course.getRelateds()) {
                for (Entry relatedEntry : unsortedEntries) {
                    if (related.getText().trim().equals(relatedEntry.getId().toString().trim())) {
                        if (related.getRelationship().equals(RelationshipType.PREREQUISITE)) {
                            // if it is, add an edge from that entry to this one
                            dag.addEdge(relatedEntry.getId().toString(), entryId);
                            mLog.debug("Adding DAG edge from entry [" + relatedEntry.getId().toString() +
                                    "] to entry [" + entryId + "]");
                        }
                        else {
                            throw new RuntimeException("Unknown Related Relationship type [" +
                                    related.getRelationship() + "]");
                        }
                    }
                }
            }

            // if the graph doesn't have a node with this ID yet, it never will.
            // add it to the foreigners list
            if (dag.getNode(entryId) == null) {
                mLog.debug("Adding entry with id [" + entry.getId() + "] to external references");
                externalReferenceEntries.add(entry);
            }
        }

        try {
            // sort the graph
            List<DirectedAcyclicGraphNode> sortedNodes = dag.sortTopologicalKahn();

            StringBuffer sb = new StringBuffer();
            for (DirectedAcyclicGraphNode node : sortedNodes) {
                sb.append(node.getId()).append(", ");
            }

            // convert them back to Entries
            for (int i = 0; i < sortedNodes.size(); i++) {
                DirectedAcyclicGraphNode sortedNode = sortedNodes.get(i);
                for (Entry entry : unsortedEntries) {
                    if (sortedNode.getId().equals(entry.getId().toString())) {
                        sortedEntries.add(i, entry);
                    }
                }

                if (sortedEntries.get(i) == null) {
                    throw new RuntimeException("Couldn't find an entry match for sorted " +
                            "DAG node with id [" + sortedNode.getId() + "]");
                }
            }

            // add the entries that reference entries not in this list
            // these don't need to be sorted.
            sortedEntries.addAll(externalReferenceEntries);

            return sortedEntries;
        }
        catch (CyclicGraphException e) {
            throw new SystemException(
                    "There's a circular reference in the related courses in the feed", e);
        }
    }


    /**
     * Persists a list of authors and contributors to the database.
     *
     * @param authors List of authors to persist
     * @param contributors List of contributors to persist
     */
    private void persistFeedPersons(List<Person> authors, List<Person> contributors) {
        for (Person person : contributors) {
            persistFeedPerson(person);
        }

        for (Person person : authors) {
            persistFeedPerson(person);
        }
    }


    /**
     * For now, does nothing because the catalog data model doesn't support it
     *
     * @param person The Person to persist
     */
    private void persistFeedPerson(Person person) {
    }


    /**
     * Persists a list of course review entries into the database.
     *
     * @param reviews List of reviews to persist.
     * @param fc Associated feedContext object for this feed.
     */
    private void persistReviewEntries(List<Entry> reviews, FeedContext fc) {
        for (Entry review : reviews) {
            if (mValidator.validateReviewEntry(review)) {
                mLog.debug("Persisting review entry with id [" + review.getId() + "]");
                try {
                    persistReviewEntry(review, fc);
                }
                catch (Exception e) {
                    mLog.error("Error persisting review with id [" + review.getId() + "]", e);
                    fc.addSyncMessage("ERR-review persist id [" + review.getId() + "]");
                }
            }
            else {
                mLog.warn("Skipping persistence of review entry with id ["
                        + review.getId() + "] due to validation errors");
            }
        }
    }


    /**
     * Persists an individual course review entry into the database.
     *
     * @param review The course review to persist
     * @param fc The associated feedContext object for the feed
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - Standard Liferay Exception
     */
    private void persistReviewEntry(Entry review, FeedContext fc)
            throws SystemException {

        AsVerb.VerbType verb = StaticNterAtomParser.getVerb(review).getVerb();

        if (verb.equals(AsVerb.VerbType.ADD)) {
            addReview(review, fc);
        }
        else if (verb.equals(AsVerb.VerbType.DELETE)) {
            removeReview(review, fc);
        }
        else if (verb.equals(AsVerb.VerbType.REMOVE)) {
            updateReview(review, fc);
        }
        else if (verb.equals(AsVerb.VerbType.UPDATE)) {
            updateReview(review, fc);
        }
        else {
            throw new RuntimeException(
                    "Unsupported activity stream verb in review with id [" +
                    review.getId() + ": " + verb);
        }
    }


    /**
     * Adds a course review to the system.
     *
     * @param review Course review entry to add
     * @param fc Associated feed context.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Standard Liferay exception
     */
    private void addReview(Entry review, FeedContext fc)
            throws SystemException {

        GlobalCourseReview incomingGcr = mFeedParser.parserReviewToCatalog(review, fc);

        if (incomingGcr == null) {
            return;
        }

        try {
            mLog.debug("Adding review ID [" + incomingGcr.getCourseReviewIri() + "]");

            GlobalCourseReviewLocalServiceUtil.findByCourseReviewIri(
                    incomingGcr.getCourseReviewIri());

            // if no exception happened, that means it's persisted already
            mLog.warn("Activity Stream review with entry ID [" + review.getId() +
                    "] with the verb '" + StaticNterAtomParser.getVerb(review).getVerb() +
                    "', cannot be added because it is already persisted.");
        }
        catch (NoSuchGlobalCourseReviewException e) {
            // if the exception happened, we're good: add it
            GlobalCourseReviewLocalServiceUtil.addGlobalCourseReview(incomingGcr);
            mLog.debug("Added Global Course Review ID [" + incomingGcr.getCourseReviewIri() + "]");
        }
    }


    /**
     * Removes a global course review from the system.
     *
     * @param review Course review to remove
     * @param fc Associated feedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Standard Liferay exception
     */
    private void removeReview(Entry review, FeedContext fc)
            throws SystemException {

        GlobalCourseReview incomingGcr = mFeedParser.parserReviewToCatalog(review, fc);

        if (incomingGcr == null) {
            return;
        }

        try {
            mLog.debug("Removing review ID [" + incomingGcr.getCourseReviewIri() + "]");
            GlobalCourseReview persistedGcr =
                    GlobalCourseReviewLocalServiceUtil.findByCourseReviewIri(
                            incomingGcr.getCourseReviewIri());

            // if the entry was updated before what we already have, warn that something looks fishy
            if ((review.getUpdated().compareTo(persistedGcr.getUpdatedDate()) < 1)) {
                mLog.warn("Removal request for review entry [" + review.getId() +
                        "] is older than what's already persisted: " +
                        "the removal request date is " + review.getUpdated() +
                        ", and the persisted review " +
                        "was updated on " + persistedGcr.getUpdatedDate());
            }

            incomingGcr.setGlobalCourseReviewId(persistedGcr.getGlobalCourseReviewId());
        }
        // if it's not actually an existing review
        catch (NoSuchGlobalCourseReviewException e) {
            mLog.warn("Activity Stream review with entry ID [" + review.getId() +
                    "] with the verb '" + StaticNterAtomParser.getVerb(review).getVerb() +
                    "', cannot be removed because it is NOT yet persisted. " +
                    "For completeness, adding the review and marking it is removed.");

            incomingGcr = GlobalCourseReviewLocalServiceUtil.addGlobalCourseReview(incomingGcr);
            mLog.debug("Added Global Course Review ID [" + incomingGcr.getCourseReviewIri() + "]");
        }

        // regardless of if we found it in persistence, or added it,
        // mark it as removed and update
        incomingGcr.setRemoved(true);
        incomingGcr.setRemovedDate(incomingGcr.getModifiedDate());
        GlobalCourseReviewLocalServiceUtil.updateGlobalCourseReview(incomingGcr);
        mLog.debug("Marked Global Course Review ID [" +
                incomingGcr.getCourseReviewIri() + "] as removed.");
    }


    /**
     * Updates an existing global course review already in the system with an
     * updated one from the feed.
     *
     * @param review Updated course review entry
     * @param fc Associated feedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Standard Liferay exception for dbo issues
     */
    private void updateReview(Entry review, FeedContext fc)
            throws SystemException {

        GlobalCourseReview incomingGcr = mFeedParser.parserReviewToCatalog(review, fc);

        if (incomingGcr == null) {
            return;
        }

        try {
            mLog.debug("Updating review ID [" + incomingGcr.getCourseReviewIri() + "]");
            GlobalCourseReview persistedGcr =
                    GlobalCourseReviewLocalServiceUtil.findByCourseReviewIri(
                            incomingGcr.getCourseReviewIri());

            // if the entry was updated before what we already have, don't
            // do anything, otherwise, update the entry
            if ((review.getUpdated().compareTo(persistedGcr.getUpdatedDate()) < 1)) {
                mLog.debug("Update for review entry [" + review.getId() +
                        "] will be ignored because it is not newer than what's already persisted: " +
                        "its updated date is " + review.getUpdated() + ", and the persisted review " +
                        "was updated on " + persistedGcr.getUpdatedDate());
            }
            else {
                incomingGcr.setGlobalCourseReviewId(persistedGcr.getGlobalCourseReviewId());
                GlobalCourseReviewLocalServiceUtil.updateGlobalCourseReview(incomingGcr);
            }
        }
        catch (NoSuchGlobalCourseReviewException e) {
            mLog.warn("Activity Stream review with entry ID [" + review.getId() +
                    "] with the verb '" + StaticNterAtomParser.getVerb(review).getVerb() +
                    "', cannot be updated because it is NOT yet persisted. " +
                    "For completeness, adding the review.");

            GlobalCourseReviewLocalServiceUtil.addGlobalCourseReview(incomingGcr);
            mLog.debug("Added Global Course Review ID [" + incomingGcr.getCourseReviewIri() + "]");
        }
    }


    /**
     * Converts the CourseComponent objects found in the feed entries and saves
     * them to the database.
     *
     * @param componentEntries List of Atom entries corresponding to the
     * CourseComponent objects
     * @param fc Associated FeedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - Standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - Standard Liferay Exception
     */
    private void persistCourseComponentEntries(List<Entry> componentEntries, FeedContext fc)
            throws SystemException, PortalException {

        for (Entry componentEntry : componentEntries) {
            if (mValidator.validateCourseComponentEntry(componentEntry)) {
                mLog.debug("Persisting course component entry with id [" +
                        componentEntry.getId() + "]");
                try {
                    persistCourseComponentEntry(componentEntry, fc);
                }
                catch (Exception e) {
                    mLog.error("Error persisting course component with id ["
                            + componentEntry.getId() + "]: " + e);
                    fc.addSyncMessage("ERR-Component persist id ["
                            + componentEntry.getId() + "]");
                }
            }
            else {
                String errorMsg = "Skipping persistence of course component entry with id ["
                        + componentEntry.getId() + "] due to validation errors";
                fc.addSyncMessage(errorMsg);
                mLog.warn(errorMsg);
            }
        }
    }


    /**
     * Saves or updates a course component entry.  If the course component is new,
     * it is saved to the database, otherwise, if it is newer than what is
     * currently stored, it is updated.
     *
     * @param componentEntry entry to pull the course component from
     * @param fc FeedContext the entry was found in
     *
     * @throws com.liferay.portal.kernel.exception.SystemException Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException Liferay Exception
     */
    private void persistCourseComponentEntry(Entry componentEntry, FeedContext fc)
            throws SystemException, PortalException {

        boolean crawlThis = false;

        Component courseComponent =
                ComponentLocalServiceUtil.fetchByComponentIri(componentEntry.getId().toString());

        if (courseComponent != null) {
            // if the entry was updated before what we already have, don't
            // do anything, otherwise, update the entry
            if ((componentEntry.getUpdated().compareTo(courseComponent.getUpdatedDate()) < 1)) {
                mLog.debug("Entry [" + componentEntry.getId() + "] will be ignored because " +
                        "it is not newer than what's already persisted: " +
                        "its updatedate is " + componentEntry.getUpdated() +
                        ", and the corresponding course " +
                        "was updated on " + courseComponent.getUpdatedDate());
            }
            else {
                updateCourseComponent(componentEntry, courseComponent.getComponentId(), fc);
                crawlThis = true;
            }
        }
        else {
            createCourseComponent(componentEntry, fc);
            crawlThis = true;
        }

        if (crawlThis && CrawlTool.getInstance().isMaster()) {
            try {
                courseComponent = ComponentLocalServiceUtil.fetchByComponentIri(componentEntry.getId().toString());
                CrawlTool.getInstance().addCourseComponent(courseComponent);
            }
            catch (Exception e) {
                mLog.error(e.getMessage());
            }
        }
    }


    /**
     * Creates a new CourseComponent from a feed entry and stores it in the
     * database.
     *
     * @param componentEntry Atom Feed Entry containing the course component
     * @param fc Feed Context containing the feed Entry
     *
     * @return The newly created CourseComponent object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private Component createCourseComponent(Entry componentEntry, FeedContext fc)
            throws SystemException, PortalException {

        mLog.debug("Creating course component with entry id: " + componentEntry.getId());
        Component catalogCourseComponent =
                mFeedParser.parserCourseComponentToCatalog(componentEntry, fc);
        catalogCourseComponent =
                ComponentLocalServiceUtil.addComponent(catalogCourseComponent);

        try {
            NterComponent parserComponent = mNterParser.getComponent(componentEntry);
            persistCourseComponentChildren(parserComponent, catalogCourseComponent,
                    componentEntry.getAuthors(), componentEntry.getContributors(),
                    componentEntry.getLinks("download"));
            return catalogCourseComponent;
        }
        catch (Exception e) {
            // something bad happened, rollback the persist
            mLog.error("Failure while persisting the children for course [" +
                    componentEntry.getId() + "]: " + e + ".  Removing course from persistence.");
            ComponentLocalServiceUtil.deleteComponent(catalogCourseComponent);
            throw new SystemException(e);
        }
    }


    /**
     * Updates a CourseComponent record that has already stored in the database.
     *
     * @param componentEntry Entry that corresponds to a CourseComponent object
     * @param courseComponentId Primary key of the existing CourseComponent
     * @param fc Associated FeedContext object
     *
     * @return The updated CourseComponent object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private Component updateCourseComponent(Entry componentEntry,
            long courseComponentId, FeedContext fc)
            throws PortalException, SystemException {

        mLog.debug("Updating course component with entry id: " + componentEntry.getId());

        //save existing entry in case of rollback due to error
        Component existingComponent = ComponentLocalServiceUtil.getComponent(courseComponentId);

        //process feed
        Component catalogCourseComponent =
                mFeedParser.parserCourseComponentToCatalog(componentEntry, fc);
        Component dbCourseComponent =
                ComponentLocalServiceUtil.getComponent(courseComponentId);

        NterComponent parserComponent = mNterParser.getComponent(componentEntry);

        catalogCourseComponent.setComponentId(courseComponentId);

        // if the current remove date is more recent than the update date,
        // keep the component removed, otherwise, let it become enabled
        if (dbCourseComponent.isRemoved() &&
                dbCourseComponent.getRemovedDate().after(catalogCourseComponent.getUpdatedDate())) {
            catalogCourseComponent.setRemoved(true);
            catalogCourseComponent.setRemovedDate(dbCourseComponent.getRemovedDate());
        }

        // if the version has changed then set the versionDate otherwise leave it
        if (dbCourseComponent.getVersion().equals(catalogCourseComponent.getVersion())) {
            catalogCourseComponent.setVersionDate(dbCourseComponent.getUpdatedDate());
        }
        else {
            catalogCourseComponent.setVersionDate(catalogCourseComponent.getUpdatedDate());
        }

        catalogCourseComponent =
                ComponentLocalServiceUtil.updateComponent(catalogCourseComponent);

        try {
            persistCourseComponentChildren(parserComponent, catalogCourseComponent,
                    componentEntry.getAuthors(), componentEntry.getContributors(),
                    componentEntry.getLinks("download"));
            return catalogCourseComponent;
        }
        catch (Exception e) {
            // something bad happened, rollback the persist
            mLog.error("Failure while persisting the children for course [" +
                    componentEntry.getId() + "]: " + e + ".  Removing course from persistence.");
            ComponentLocalServiceUtil.updateComponent(existingComponent);
            throw new SystemException(e);
        }
    }


    /**
     * Persists the children objects of a course component, e.g., authors,
     * contributors, and download links
     *
     * @param parserComponent The "nter" namespace section of a component entry
     * @param catalogComponent The component's catalog data model
     * @param parserAuthors List of the component entry's authors
     * @param parserContributors List of the component entry's contributors
     * @param downloadLinks List of the component entry's links
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     * @throws NterCatalogCourseDependencyException Thrown if required course
     * dependencies are not available
     */
    private void persistCourseComponentChildren(NterComponent parserComponent,
            Component catalogComponent, List<Person> parserAuthors,
            List<Person> parserContributors, List<Link> downloadLinks)
            throws PortalException, SystemException, NterCatalogCourseDependencyException {

        List<Contributor> existingContributors =
                ComponentLocalServiceUtil.getContributors(catalogComponent);

        // now parse all of child elements in this component
        long catalogComponentId = catalogComponent.getComponentId();
        List<Contributor> catalogContributors = Converter.parserAuthorsToCatalog(
                parserAuthors, catalogComponentId, NterEntryType.COURSE_COMPONENT,
                mNterParser);
        catalogContributors.addAll(Converter.parserContributorsToCatalog(
                parserContributors, catalogComponentId, NterEntryType.COURSE_COMPONENT,
                mNterParser));

        if (parserComponent != null) {
            catalogContributors.addAll(Converter.parserOrganizationsToCatalog(
                    parserComponent.getOrganizations(), catalogComponentId,
                    NterEntryType.COURSE_COMPONENT));
        }

        // persist all the children, catching exceptions along the way and
        // rolling back the parts already persisted when they happen
        CatalogContributorDao ccd = new CatalogContributorDao();

        try {
            ccd.persistListWithCompareAndRollback(existingContributors,
                    catalogContributors, catalogComponentId);
        }
        catch (Exception e) {
            throw new SystemException("Error while persisting catalog contributors.", e);
        }

        try {
            CatalogComponentDao.updateCourses_ComponentsWithIriAndRollback(catalogComponent);
        }
        catch (Exception e) {
            List<Contributor> revertContributors = ccd.getRevertList(catalogComponentId);
            ccd.deleteList(revertContributors);
            ccd.addList(existingContributors);
            throw new SystemException("Error while persisting catalog Courses_Components. " +
                    "Rolled back catalog contributors as well.", e);
        }

        // persist any external links
        StaticParserUtil.persistExternalDownloadLinks(0, catalogComponentId, downloadLinks);
    }


    /**
     * Persists entries which contain NTER course records
     *
     * @param courseRecordEntries List of Atom Feed entries containing
     * course-record objects
     * @param fc Associated FeedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void persistCourseRecordEntries(List<Entry> courseRecordEntries, FeedContext fc)
            throws PortalException, SystemException {

        for (Entry courseRecordEntry : courseRecordEntries) {
            if (mValidator.validateCourseRecordEntry(courseRecordEntry)) {
                mLog.debug("Persisting course record entry with id [" +
                        courseRecordEntry.getId() + "]");

                try {
                    persistCourseRecordEntry(courseRecordEntry, fc);
                }
                catch (Exception e) {
                    String errorMsg = "Error persisting course record with id [" +
                            courseRecordEntry.getId() + "]: " + e;
                    mLog.error(errorMsg);
                    fc.addSyncMessage("ERR-CourseRecord persist id [" +
                            courseRecordEntry.getId() + "]");
                }
            }
            else {
                mLog.warn("Skipping persistence of course record entry with id [" +
                        courseRecordEntry.getId() + "] due to validation errors");
            }
        }
    }


    /**
     * Converts an Atom Feed entry into a Course record object and saves it to the
     * database.  If the course-record does not exist, one is created, otherwise,
     * if the existing course-record needs to be updated, it is.
     *
     * @param courseRecordEntry Atom Feed entry corresponding to a course-record
     * object
     * @param fc Associated feedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void persistCourseRecordEntry(Entry courseRecordEntry, FeedContext fc)
            throws SystemException, PortalException {

        CourseRecord catalogCourseRecord =
                CourseRecordLocalServiceUtil.fetchByCourseRecordIri(
                        courseRecordEntry.getId().toString());

        try {
            if (catalogCourseRecord != null) {
                // if the entry was updated before what we already have
                if (courseRecordEntry.getUpdated().compareTo(catalogCourseRecord.getUpdatedDate()) < 1) {
                    mLog.debug("Entry with ID " + courseRecordEntry.getId() + " will be ignored because " +
                            "it's not newer than what's already persisted: " +
                            "its updated date is " + courseRecordEntry.getUpdated() +
                            ", and the corresponding course record " +
                            "was updated on " + catalogCourseRecord.getUpdatedDate());
                }
                else {
                    updateParserCourseRecord(courseRecordEntry,
                            catalogCourseRecord.getCourseRecordId(), fc);
                }
            }
            else {
                createParserCourseRecord(courseRecordEntry, fc);
            }
        }
        catch (NterCatalogRecordDependencyException e) {
            throw new SystemException("Could not persist student courseRecord id ["
                    + courseRecordEntry.getId() + "] because one or both of its"
                    + " targets were not found in the database", e);
        }
    }


    /**
     * Creates a new course record and stores in the database
     *
     * @param courseRecordEntry Atom Feed entry object that corresponds to a
     * course-record
     * @param fc Associated feedcontext object
     *
     * @return Newly created courseRecord
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     * @throws NterCatalogRecordDependencyException Thrown if required dependencies
     * are not available.
     */
    private CourseRecord createParserCourseRecord(Entry courseRecordEntry, FeedContext fc)
            throws PortalException, SystemException, NterCatalogRecordDependencyException {

        mLog.debug("Creating course record with entry id: " + courseRecordEntry.getId());
        CourseRecord catalogCourseRecord =
                mFeedParser.parserCourseRecordToCatalog(courseRecordEntry,
                        fc.getFeedReferenceId(), fc.getCompanyId(), false);
        catalogCourseRecord = CourseRecordLocalServiceUtil.addCourseRecord(catalogCourseRecord);

        NterCourseRecord parserCourseRecord = mNterParser.getCourseRecord(courseRecordEntry);
        try {
            persistParserCourseRecordChildren(parserCourseRecord, catalogCourseRecord);
            return catalogCourseRecord;
        }
        catch (Exception e) {
            // something bad happened, rollback the persist
            mLog.error("Failure while persisting the children for CourseRecord ["
                    + courseRecordEntry.getId() + "]. Error: [" + e
                    + "].  Removing CourseRecord from persistence.", e);
            CourseRecordLocalServiceUtil.deleteCourseRecord(catalogCourseRecord);
            throw new SystemException(e);
        }
    }


    /**
     * Updates a student course record that has already stored in the database
     *
     * @param courseRecordEntry Atom Feed entry corresponding to a student record
     * @param dbCourseRecordId Peristed PrimaryKey
     * @param fc Associated feedContext object
     *
     * @return Updated student course record
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     * @throws NterCatalogRecordDependencyException - Thrown if required
     * dependencies are not available
     */
    private CourseRecord updateParserCourseRecord(Entry courseRecordEntry,
            long dbCourseRecordId, FeedContext fc)
            throws PortalException, SystemException, NterCatalogRecordDependencyException {

        mLog.debug("Updating course record with entry id: " + courseRecordEntry.getId());

        //save existing record in case of rollback due to error
        CourseRecord existingCourseRecord =
                CourseRecordLocalServiceUtil.getCourseRecord(dbCourseRecordId);

        //process feed
        CourseRecord catalogCourseRecord =
                mFeedParser.parserCourseRecordToCatalog(courseRecordEntry,
                        fc.getFeedReferenceId(), fc.getCompanyId(), false);
        CourseRecord dbCourseRecord =
                CourseRecordLocalServiceUtil.getCourseRecord(dbCourseRecordId);

        catalogCourseRecord.setCourseRecordId(dbCourseRecordId);

        // if the current remove date is more recent than the update date,
        // keep the courseRecord removed, otherwise, let it become enabled
        if (dbCourseRecord.isRemoved() &&
                dbCourseRecord.getRemovedDate().after(catalogCourseRecord.getUpdatedDate())) {
            catalogCourseRecord.setRemoved(true);
            catalogCourseRecord.setRemovedDate(dbCourseRecord.getRemovedDate());
        }

        catalogCourseRecord = CourseRecordLocalServiceUtil.updateCourseRecord(catalogCourseRecord);

        NterCourseRecord parserCourseRecord = mNterParser.getCourseRecord(courseRecordEntry);
        try {
            persistParserCourseRecordChildren(parserCourseRecord, catalogCourseRecord);
            return catalogCourseRecord;
        }
        catch (Exception e) {
            // something bad happened, rollback the persist
            mLog.error("Failure while persisting the children for CourseRecord [" +
                    courseRecordEntry.getId() + "]. Error: [" + e +
                    "].  Removing CourseRecord from persistence.", e);
            CourseRecordLocalServiceUtil.updateCourseRecord(existingCourseRecord);
            throw new SystemException(e);
        }
    }


    /**
     * Persists the children objects (componentRecord) of a courseRecord
     *
     * @param parserCourseRecord NterCourseRecord implementation of a course
     * record
     * @param catalogCourseRecord CourseRecord object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     * @throws NterCatalogRecordDependencyException Thrown if required dependencies
     * are missing
     */
    private void persistParserCourseRecordChildren(NterCourseRecord parserCourseRecord,
            CourseRecord catalogCourseRecord)
            throws PortalException, SystemException, NterCatalogRecordDependencyException {

        mLog.debug("persisting courseRecord children");
        List<ComponentRecord> existingComponentRecords =
                CourseRecordLocalServiceUtil.getComponentRecords(catalogCourseRecord);

        // send false for dependencies because it is ok if the corresponding
        // component does not yet exist.
        long catalogCourseRecordId = catalogCourseRecord.getCourseRecordId();
        List<ComponentRecord> catalogComponentRecords =
                Converter.parserComponentRecordsToCatalog(
                        parserCourseRecord.getComponentRecords(), catalogCourseRecordId, false);

        // persist all the children, catching exceptions along the way and rolling back
        // the parts already persisted when they happen
        CatalogComponentRecordDao ccomprecd = new CatalogComponentRecordDao();

        try {
            ccomprecd.persistListWithCompareAndRollback(existingComponentRecords,
                    catalogComponentRecords, catalogCourseRecordId);
        }
        catch (Exception e) {
            throw new SystemException("Error while persisting catalog componentRecords. ", e);
        }
    }


    /**
     * Persists entries which contain an NTER vocabulary
     *
     * @param vocabularyEntries List of Atom entries corresponding to vocabulary
     * objects
     * @param fc Associated feedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void persistVocabularyEntries(List<Entry> vocabularyEntries,
                                          FeedContext fc)
            throws PortalException, SystemException {

        for (Entry vocabularyEntry : vocabularyEntries) {
            if (mValidator.validateVocabularyEntry(vocabularyEntry)) {
                mLog.debug("Persisting vocabulary entry with id [" + vocabularyEntry.getId() + "]");
                try {
                    persistVocabularyEntry(vocabularyEntry, fc);
                }
                catch (Exception e) {
                    mLog.warn("The vocabulary with id [" + vocabularyEntry.getId() + "] cannot be " +
                            "persisted because its dependencies have not yet been persisted");
                    fc.addSyncMessage("ERR-Vocabulary persist id [" + vocabularyEntry.getId() + "]");
                }
            }
            else {
                mLog.warn("Skipping persistence of vocabulary entry with id [" +
                        vocabularyEntry.getId() + "] due to validation errors");
            }
        }
    }


    /**
     * Converts an Atom Feed entry into a vocabulary object and stores it in the
     * database.
     *
     * @param vocabEntry - required due to some vocabulary information actually
     * residing in the entry
     * @param fc Associated feedContext object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void persistVocabularyEntry(Entry vocabEntry, FeedContext fc)
            throws PortalException, SystemException {

        // See if Vocabulary is already persisted in database
        // Cannot fetch by group because we want to use the groupId setting in the database if it exists
        long vocabularyId = -1;

        List<AssetVocabulary> vocabularies = CourseLocalServiceUtil.findAllAssetVocabularies();
        for (AssetVocabulary vocabulary : vocabularies) {
            if (vocabulary.getName().equals(vocabEntry.getId().toString())) {
                vocabularyId = vocabulary.getVocabularyId();
            }
        }

        if (vocabularyId != -1) {
            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy");

            Date vDate = (Date) ExpandoValueLocalServiceUtil.getData(fc.getCompanyId(),
                    AssetVocabulary.class.getName(), ExpandoConstants.ASSET_VOCABULARY_TABLENAME,
                    ExpandoConstants.UPDATE_DATE, vocabularyId);

            ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(
                    fc.getCompanyId(), AssetVocabulary.class.getName(),
                    ExpandoConstants.ASSET_VOCABULARY_TABLENAME, ExpandoConstants.UPDATE_DATE, vocabularyId);

            try {
                Date vocabularyDate = dateFormat.parse(expandoValue.getData());
                mLog.debug("Vocabulary Date: " + vocabularyDate + " vDate: " + vDate);

                // Check if the entry was updated before what we already have
                if (vocabEntry.getUpdated().compareTo(vocabularyDate) < 1) {
                    mLog.debug("Entry with ID " + vocabEntry.getId() + " will be ignored because " +
                            " it's not newer than what's already persisted: " +
                            "its updated date is " + vocabEntry.getUpdated() +
                            ", and the corresponding vocabulary was updated on "
                            + vocabularyDate);
                }
                else {
                    // if it's been updated after what we have, that means what we have is stale
                    mLog.debug("Updating existing vocabulary with entry id: " + vocabEntry.getId());
                    updateVocabularyEntry(vocabEntry, vocabularyId, fc);
                }
            }
            catch (ParseException e) {
                mLog.warn("Vocabulary date format parse error: " + e.getMessage());
            }
        }
        else {
            // we don't have this entry stored, so create it
            createVocabularyEntry(vocabEntry, fc);
        }
    }


    /**
     * Creates a new vocabulary from the Atom Feed entry and stores in the
     * database
     *
     * @param vocabEntry Atom feed entry containing a vocabulary
     * @param fc Associated feedContext object
     *
     * @return Freshly created assetVocabulary object
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private AssetVocabulary createVocabularyEntry(Entry vocabEntry, FeedContext fc)
            throws SystemException, PortalException {

        mLog.debug("Creating vocabulary with entry id: " + vocabEntry.getId());
        AssetVocabulary catalogVocabulary =
                mFeedParser.parserVocabularyToCatalog(vocabEntry, fc);
        try {
            long id = CounterLocalServiceUtil.increment();
            catalogVocabulary.setPrimaryKey(id);
            catalogVocabulary =
                    AssetVocabularyLocalServiceUtil.addAssetVocabulary(catalogVocabulary);
        }
        catch (SystemException e) {
            mLog.error("vocabularyId Increment Error: " + e.getMessage());
            throw new SystemException(e);
        }

        // Create the expando vocabulary updatedDate entry
        ExpandoTable table =
                ExpandoTableLocalServiceUtil.getTable(fc.getCompanyId(),
                        AssetVocabulary.class.getName(), ExpandoConstants.ASSET_VOCABULARY_TABLENAME);
        ExpandoColumn udColumn =
                ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
                        ExpandoConstants.UPDATE_DATE);
        ExpandoColumn vtColumn =
                ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
                        ExpandoConstants.VOCABULARY_TYPE);
        ExpandoColumn frColumn =
                ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
                        ExpandoConstants.FEED_REFERENCE_ID);
        ExpandoRowLocalServiceUtil.addRow(table.getTableId(), catalogVocabulary.getPrimaryKey());
        ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(),
                table.getTableId(), udColumn.getColumnId(), catalogVocabulary.getPrimaryKey(),
                vocabEntry.getUpdated().toString());
        ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(),
                table.getTableId(), vtColumn.getColumnId(), catalogVocabulary.getPrimaryKey(),
                COURSE_VOCABULARY_TYPE);
        ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(),
                table.getTableId(), frColumn.getColumnId(), catalogVocabulary.getPrimaryKey(),
                Long.toString(fc.getFeedReferenceId()));

        // Persist vocabulary category entries
        NterVocabulary parserVocabulary = mNterParser.getVocabulary(vocabEntry);
        try {
            createVocabularyCategories(parserVocabulary, catalogVocabulary, fc);
            return catalogVocabulary;
        }
        catch (Exception e) {
            mLog.error("Failure while persisting the categories for vocabulary  [" +
                    vocabEntry.getId() + "]");
            throw new SystemException(e);
        }
    }


    /**
     * Finds an existing AssetVocabulary in the database and updates.
     *
     * @param vocabEntry Atom feed entry containing an assetVocabulary object
     * @param vocabularyId Id for the original assetVocabulary to update
     * @param fc Associated feed context
     *
     * @return AssetVocabulary found in the feed entry object.
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private AssetVocabulary updateVocabularyEntry(Entry vocabEntry,
                                                  long vocabularyId, FeedContext fc)
            throws SystemException, PortalException {

        // Update the expando vocabulary updatedDate entry
        ExpandoTable table =
                ExpandoTableLocalServiceUtil.getTable(fc.getCompanyId(),
                        AssetVocabulary.class.getName(), ExpandoConstants.ASSET_VOCABULARY_TABLENAME);
        ExpandoColumn udColumn = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
                        ExpandoConstants.UPDATE_DATE);
        ExpandoColumn vtColumn = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
                        ExpandoConstants.VOCABULARY_TYPE);
        ExpandoColumn frColumn = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
                        ExpandoConstants.FEED_REFERENCE_ID);

        if ((udColumn == null) || (vtColumn == null) || (frColumn == null)) {
            mLog.error("Error creating entry in Expando tables for AssetVocabulary");
            return null;
        }
        else {

            // Update the vocabulary entry
            mLog.debug("Updating the vocabulary " + "with entry id: " + vocabEntry.getId());

            AssetVocabulary catalogVocabulary = mFeedParser.parserVocabularyToCatalog(vocabEntry, fc);
            // assign primary key and date from existing vocabulary to new parsed vocabulary
            AssetVocabulary existingVocabulary =
                    AssetVocabularyLocalServiceUtil.getVocabulary(vocabularyId);
            catalogVocabulary.setGroupId(existingVocabulary.getGroupId());
            catalogVocabulary.setPrimaryKey(existingVocabulary.getPrimaryKey());
            catalogVocabulary.setCreateDate(existingVocabulary.getCreateDate());
            catalogVocabulary =
                    AssetVocabularyLocalServiceUtil.updateAssetVocabulary(catalogVocabulary);

            // remove old ExpandoValue records and replace with new
            ExpandoValueLocalServiceUtil.deleteValues(table.getClassNameId(),
                    catalogVocabulary.getPrimaryKey());
            ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
                    udColumn.getColumnId(), catalogVocabulary.getPrimaryKey(),
                    vocabEntry.getUpdated().toString());
            ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
                    vtColumn.getColumnId(), catalogVocabulary.getPrimaryKey(), COURSE_VOCABULARY_TYPE);
            ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
                    frColumn.getColumnId(), catalogVocabulary.getPrimaryKey(),
                    Long.toString(fc.getFeedReferenceId()));
            // save course-category assignments
            List<CourseAndCategoryPair> resultMap =
                    captureCourseCategoryAssignments(catalogVocabulary.getPrimaryKey());

            // Persist vocabulary category entries
            NterVocabulary parserVocabulary = mNterParser.getVocabulary(vocabEntry);
            try {
                createVocabularyCategories(parserVocabulary, catalogVocabulary, fc);
            }
            catch (Exception e) {
                mLog.error("Failure while persisting the categories for vocabulary  [" +
                        vocabEntry.getId() + "]");
                throw new SystemException(e);
            }

            // assign saved course-category assignments
            if (resultMap.size() > 0) {
                assignSavedCourseCategories(resultMap, catalogVocabulary.getPrimaryKey());
            }
            return catalogVocabulary;
        }
    }


    /**
     * Persists the categories of a vocabulary to the database.
     *
     * @param parserVocabulary The NterVocabulary version of the vocabulary
     * @param catalogVocabulary The AssetVocabulary associated with the
     * vocabulary
     * @param fc The FeedContext the vocabulary was in
     *
     * @throws com.liferay.portal.kernel.exception.SystemException - standard Liferay Exception
     * @throws com.liferay.portal.kernel.exception.PortalException - standard Liferay Exception
     */
    private void createVocabularyCategories(NterVocabulary parserVocabulary,
            AssetVocabulary catalogVocabulary, FeedContext fc)
            throws PortalException, SystemException {

        mLog.debug("persisting vocabulary categories");

        //  Process all of categories of this Vocabulary
        long catalogVocabularyId = catalogVocabulary.getVocabularyId();
        List<AssetCategory> catalogAssetCategories =
                Converter.parserAssetCategoriesToCatalog(
                parserVocabulary.getCategories(), catalogVocabularyId, fc);

        // capture category hierarchy
        ArrayList<Long> categoryIdCatalog = new ArrayList<Long>();
        ArrayList<Long> categoryIdPK = new ArrayList<Long>();

        // assign groupId - if existing vocabulary, its groupId or if new the
        // feeds groupId.  assign all PK values (categoryId) to
        // catalogAssetCategories while capturing category hierarchy
        for (AssetCategory catalogAssetCategory : catalogAssetCategories) {
            try {
                long id = CounterLocalServiceUtil.increment();
                categoryIdCatalog.add(catalogAssetCategory.getCategoryId());
                categoryIdPK.add(id);

                catalogAssetCategory.setGroupId(fc.getFeedReferenceGroupId());
                catalogAssetCategory.setPrimaryKey(id);
            }
            catch (SystemException e) {
                mLog.error("PK categoryId Increment Error: " + e.getMessage());
            }
        }

        // assign all parentCategoryId to catalogAssetCategories
        // root categories have a parentCategoryId set to zero
        for (AssetCategory catalogAssetCategory : catalogAssetCategories) {
            Boolean parentMatch = false;
            for (int i = 0; i < categoryIdCatalog.size(); i++) {
                if ((parentMatch) || (catalogAssetCategory.getParentCategoryId() == 0)) {
                    break;
                }
                if (catalogAssetCategory.getParentCategoryId() == (categoryIdCatalog.get(i))) {
                    catalogAssetCategory.setParentCategoryId(categoryIdPK.get(i));
                    parentMatch = true;
                }
            }
        }

        // persist category set in database
        for (AssetCategory catalogAssetCategory : catalogAssetCategories) {
            mLog.debug("Adding category: " + catalogAssetCategory.getName());
            try {
                AssetCategoryLocalServiceUtil.addAssetCategory(catalogAssetCategory);
            }
            catch (Exception e) {
                mLog.error("Failure while persisting the categories for vocabulary  [" +
                        catalogVocabularyId + "], category [" + catalogAssetCategory.getName() + "]");
                fc.addSyncMessage("ERR-Category persist id [" + catalogAssetCategory.getName() + "]");
            }
        }
    }


    /**
     * Saves course category assignments to reapply after vocabulary/category
     * update.
     *
     * @param catalogVocabularyId The vocabulary Id to search for
     *
     * @return A list of Course, Category pairs generated from the provided id
     *
     * @throws com.liferay.portal.kernel.exception.PortalException - Standard Liferay exception
     * @throws com.liferay.portal.kernel.exception.SystemException - Standard Liferay exception
     */
    private List<CourseAndCategoryPair> captureCourseCategoryAssignments(long catalogVocabularyId)
            throws PortalException, SystemException {

        List<AssetCategory> existingAssetCategories =
                AssetCategoryLocalServiceUtil.getVocabularyCategories(catalogVocabularyId,
                        QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
        List<CourseAndCategoryPair> resultMap = new ArrayList<CourseAndCategoryPair>();

        for (AssetCategory existingAssetCategory : existingAssetCategories) {
            try {
                if (CourseLocalServiceUtil.containsCategoryAssetEntries(
                        existingAssetCategory.getPrimaryKey())) {

                    mLog.debug("Saving course category assignments: " +
                            existingAssetCategory.getName());
                    List<AssetEntry> assetEntries =
                            CourseLocalServiceUtil.getCategoryAssetEntries(
                                    existingAssetCategory.getPrimaryKey());
                    for (AssetEntry assetEntry : assetEntries) {
                        resultMap.add(new CourseAndCategoryPair(assetEntry.getPrimaryKey(),
                                existingAssetCategory.getName()));
                    }
                    AssetCategoryUtil.removeAssetEntries(existingAssetCategory.getPrimaryKey(),
                            assetEntries);
                }
                AssetCategoryLocalServiceUtil.deleteAssetCategory(existingAssetCategory);
            }
            catch (Exception e) {
                mLog.error("Failure while capturing course the categories assignments  [" +
                        existingAssetCategory.getName() + "]");
                throw new SystemException(e);
            }
        }
        return resultMap;
    }


    /**
     * Assigns saved course category assignments after vocabulary/category
     * update.
     *
     * @param courseCategoryPairs List of course, category pairs to assign
     * @param catalogVocabularyId Id to assign them to
     *
     * @throws com.liferay.portal.kernel.exception.PortalException - Standard Liferay exception
     * @throws com.liferay.portal.kernel.exception.SystemException - Standard Liferay exception
     */
    private void assignSavedCourseCategories(
            List<CourseAndCategoryPair> courseCategoryPairs, long catalogVocabularyId)
            throws PortalException, SystemException {

        // reassign all courses to new category set
        List<AssetCategory> newAssetCategories =
                AssetCategoryLocalServiceUtil.getVocabularyCategories(
                        catalogVocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
        mLog.debug("Updating course category assignments: ");

        for (AssetCategory newAssetCategory : newAssetCategories) {
            for (CourseAndCategoryPair courseCategoryPair : courseCategoryPairs) {
                if (newAssetCategory.getName() .equals(courseCategoryPair.getCategoryName())) {
                    try {
                        AssetEntryUtil.addAssetCategory(courseCategoryPair.getCoursePk(),
                                                        newAssetCategory.getCategoryId());
                    }
                    catch (Exception e) {
                        mLog.error("Failure while assigning category [" +
                                newAssetCategory.getName() + "], to course  [" +
                                courseCategoryPair.getCoursePk() + "]");
                    }
                }
            }
        }
    }
}
