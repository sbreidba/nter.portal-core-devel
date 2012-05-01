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

package org.nterlearning.atom.parser.validator;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.atom.enumerations.NterFeedType;
import org.nterlearning.atom.enumerations.NterEntryType;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.model.*;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

import java.util.List;

public class FeedValidator_061 implements FeedValidator {

    private final Log mLog = LogFactoryUtil.getLog(FeedValidator_061.class);

    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;

    public FeedValidator_061() {
        setExtension();
    }

    public void setExtension() {
        mNterExtension = new NterExtension(NterNameSpace.FEED_VERSION_061);
        mStaticParser = new StaticNterAtomParser(mNterExtension);
    }


    public void setExtension(NterExtension extension) {
        mNterExtension = extension;
        mStaticParser = new StaticNterAtomParser(mNterExtension);
    }
    

    public boolean validate(Feed feed) {

        boolean valid = true;
        List<Entry> entries = feed.getEntries();

        NterFeedType feedType = mStaticParser.getFeedType(feed);
        if (feedType.equals(NterFeedType.COURSES)) {
            for (Entry entry : entries) {
                NterEntryType entryType = mStaticParser.getEntryType(entry);
                if (entryType.equals(NterEntryType.COURSE)) {
                    if (!validateCourseEntry(entry)) {
                        valid = false;
                    }
                }
                else if (entryType.equals(NterEntryType.COURSE_COMPONENT)) {
                    if (!validateCourseComponentEntry(entry)) {
                        valid = false;
                    }
                }
                else {
                    valid = false;
                }
            }
        }
        else if (feedType.equals(NterFeedType.RECORDS)) {
            for (Entry entry : entries) {
                NterEntryType entryType = mStaticParser.getEntryType(entry);
                if (entryType.equals(NterEntryType.COURSE_RECORD)) {
                    if (!validateCourseRecordEntry(entry)) {
                        valid = false;
                    }
                }
                else {
                    valid = false;
                }
            }
        }
        else if (feedType.equals(NterFeedType.NONE)) {
            valid = false;
        }
        else {
            valid = false;
        }

        return valid;
    }


    public boolean validateVocabularyEntry(Entry vocabEntry) {

        boolean valid = true;

        // set up the error messages
        String entryId = vocabEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(vocabEntry);
        if (!entryType.equals(NterEntryType.VOCABULARY)) {
            mLog.info(errorPrefix + "the entry type is not VOCABULARY: " + entryType);
            valid = false;
        }

        // get the list of vocabularies
        List<NterVocabulary> vocabularies =
                vocabEntry.getExtensions(
                        mNterExtension.getQName(NterExtension.VOCABULARY_ELEMENT_NAME));

        // only 1 vocabulary per entry
        if (vocabularies.size() > 1) {
            mLog.info(errorPrefix + " contains more than one vocabulary; actual number: " +
                    vocabularies.size());
            valid = false;
        }

        NterVocabulary vocabulary = mStaticParser.getVocabulary(vocabEntry);

        if (vocabulary == null) {
            mLog.info(errorPrefix + "the vocabulary element is missing");
            valid = false;
        }
        else {
            // at least one title per vocabulary, exactly one per language
            List<NterTitle> titles = vocabulary.getTitles();
            valid = FeedValidatorUtil.detectEmptyList(titles, errorPrefix, "title", valid, mLog);
            valid = FeedValidatorUtil.detectDuplicateLanguage(titles, errorPrefix, "title", valid, mLog);

            // at least one category per vocabulary
            List<NterCategory> categories = vocabulary.getCategories();
            valid = FeedValidatorUtil.detectEmptyList(categories, errorPrefix,
                                                      "category", valid, mLog);

            for (NterCategory category : categories) {
                if (!validateCategory(category)) {
                    valid = false;
                    break;
                }
            }
        }

        return valid;
    }


    public boolean validateCategory(NterCategory category) {

        boolean valid = true;

        //exactly one ID
        String categoryId = category.getCategoryId().getText();
        if ((categoryId == null) || categoryId.isEmpty()) {
            categoryId = "MISSING ID";
            mLog.info("Validation error in an unknown category: it does not have an ID");
        }
        String errorPrefix = "Validation error in category with id [" + categoryId + "]: ";

        //at least one title
        valid = FeedValidatorUtil.detectEmptyList(category.getTitles(),
                        errorPrefix, "title", valid, mLog);

        //validate any sub-categories
        for (NterCategory subCategory : category.getCategories()) {
            if (!validateCategory(subCategory)) {
                valid = false;
                break;
            }
        }

        return valid;
    }


    public boolean validateCourseEntry(Entry courseEntry) {

        boolean valid = true;

        // set up the error messages
        String entryId = courseEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(courseEntry);
        if (!entryType.equals(NterEntryType.COURSE)) {
            mLog.info(errorPrefix + "the entry type is not COURSE: " + entryType);
            valid = false;
        }

        // get the list of courses
        List<NterCourse> courses = courseEntry.getExtensions(
                        mNterExtension.getQName(NterExtension.COURSE_ELEMENT_NAME));

        // only 1 course per entry
        if (courses.size() > 1) {
            mLog.info(errorPrefix + " contains more than one course; actual number: " + courses.size());
            valid = false;
        }

        NterCourse course = mStaticParser.getCourse(courseEntry);

        if (course == null) {
            mLog.info(errorPrefix + "the course element is missing");
            valid = false;
        }
        else {

            // at least one title per course, exactly one per language
            List<NterTitle> titles = course.getTitles();
            valid = FeedValidatorUtil.detectEmptyList(titles, errorPrefix, "title", valid, mLog);
            valid = FeedValidatorUtil.detectDuplicateLanguage(titles, errorPrefix, "title", valid, mLog);

            // at most one transcript abstract per language
            FeedValidatorUtil.detectDuplicateLanguage(
                    course.getTranscriptAbstracts(), errorPrefix, "transcriptAbstract", valid, mLog);

            // at least one description per course, exactly one per language, and exactly one per title
            List<NterCourseDescription> descriptions = course.getDescriptions();
            valid = FeedValidatorUtil.detectEmptyList(descriptions, errorPrefix, "description", valid, mLog);
            valid = FeedValidatorUtil.detectDuplicateLanguage(titles, errorPrefix, "description", valid, mLog);

            if (titles.size() != descriptions.size()) {
                mLog.info(errorPrefix + "there must be exactly one description per language per title");
                valid = false;
            }

            valid = FeedValidatorUtil.detectDuplicateLanguage(course.getCopyrights(),
                            errorPrefix, "copyright", valid, mLog);

            valid = FeedValidatorUtil.detectDuplicateLanguage(course.getRatings(),
                            errorPrefix, "copyright", valid, mLog);

            // at least one component ref per course
            List<NterComponentRef> componentRefs = course.getComponentRefs();
            valid = FeedValidatorUtil.detectEmptyList(componentRefs, errorPrefix, "componentRef", valid, mLog);

            // component ref ID required
            for (NterComponentRef componentRef : componentRefs) {
                if (componentRef.getComponentId() == null) {
                    mLog.info(errorPrefix + "componentId attribute is missing in a link");
                    valid = false;
                    break;
                }
            }

            // at least one image translation per image, one per language
            for (NterImage image : course.getImages()) {
                if (image.getAlt() == null) {
                    mLog.info(errorPrefix + "alt attribute is missing in an image");
                    valid = false;
                }

                if (image.getHref() == null) {
                    mLog.info(errorPrefix + "href attribute is missing in an image");
                    valid = false;
                }

                if (image.getMimeType() == null) {
                    mLog.info(errorPrefix + "mimeType attribute is missing in an image");
                    valid = false;
                }
            }

            // related entry Id required
            for (NterRelated related : course.getRelateds()) {
                if (related.getRelatedEntryId() == null) {
                    mLog.info(errorPrefix + "relatedEntryId attribute is missing in a 'related' element");
                    valid = false;
                }
                else if (related.getRelatedEntryId().equals(entryId)) {
                    mLog.info(errorPrefix + "relatedEntryId must not match courseId");
                    valid = false;
                }

                if (related.getRelationship() == null) {
                    mLog.info(errorPrefix + "relationship attribute is missing in a 'related' element");
                    valid = false;
                }
            }

            // requirement type required
            for (NterRequirement requirement : course.getRequirements()) {
                if (requirement.getRequirementType() == null) {
                    mLog.info(errorPrefix + "requirementType attribute is missing in a requirement");
                    valid = false;
                }
            }
        }

        return valid;
    }


    public boolean validateCourseRecordEntry(Entry courseRecordEntry) {

        boolean valid = true;

        // set up the error messages
        String entryId = courseRecordEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(courseRecordEntry);
        if (!entryType.equals(NterEntryType.COURSE_RECORD)) {
            mLog.info(errorPrefix + "the entry type is not COURSE_RECORD: " + entryType);
            valid = false;
        }

        // get the list of student records
        List<NterCourse> records =
                courseRecordEntry.getExtensions(
                        mNterExtension.getQName(NterExtension.COURSE_RECORD_ELEMENT_NAME));

        // only 1 student record per entry
        if (records.size() > 1) {
            mLog.info(errorPrefix + " contains more than one course record; actual number: " + records.size());
            valid = false;
        }

        NterCourseRecord record = mStaticParser.getCourseRecord(courseRecordEntry);

        if (record == null) {
            mLog.info(errorPrefix + "the course record is missing");
            valid = false;
        }
        else {
            // student id required
            if (record.getStudentUserId() == null) {
                mLog.info(errorPrefix + "the student user ID is missing");
                valid = false;
            }

            // target entry id required
            if (record.getCourseId() == null) {
                mLog.info(errorPrefix + "the course ID is missing");
                valid = false;
            }

            // progress required
            if (record.getProgress() == null) {
                mLog.info(errorPrefix + "the progress is missing");
                valid = false;
            }

            // ComponentRecord required items
            for (NterComponentRecord componentRecord : record.getComponentRecords()) {
                // component id required
                if (componentRecord.getComponentId() == null) {
                    mLog.info(errorPrefix + "the component ID is missing");
                    valid = false;
                }

                // completion percent required
                if (componentRecord.getCompletionPercent() < 0) {
                    mLog.info(errorPrefix + "the completion percentage is missing");
                    valid = false;
                }

                // progress required
                if (componentRecord.getProgress() == null) {
                    mLog.info(errorPrefix + "the progress is missing");
                    valid = false;
                }

                // progress date required
                if (componentRecord.getProgressDate() == null) {
                    mLog.info(errorPrefix + "the progress date is missing");
                    valid = false;
                }
            }
        }

        return valid;
    }


    public boolean validateCourseComponentEntry(Entry courseComponentEntry) {

        boolean valid = true;

        // set up the error messages
        String entryId = courseComponentEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(courseComponentEntry);
        if (!entryType.equals(NterEntryType.COURSE_COMPONENT)) {
            mLog.info(errorPrefix + "the entry type is not COURSE_COMPONENT: " + entryType);
            valid = false;
        }

        // course component ID is required
        if ((entryId == null) || (entryId.isEmpty())) {
            mLog.info(errorPrefix + "the entry ID is missing: " + entryId);
            valid = false;
        }

        // course component title is required
        String entryTitle = courseComponentEntry.getTitle();
        if ((entryTitle == null) || (entryTitle.isEmpty())) {
            mLog.info(errorPrefix + "the entry title is missing: " + entryTitle);
            valid = false;
        }

        // get the list of links
        List<Link> links = courseComponentEntry.getLinks("self");
        if (links.size() != 1) {
            mLog.info(errorPrefix + "contains more than one link; actual number: " + links.size());
            valid = false;
        }
        else {
            Link selfLink = links.get(0);

            // link href is required
            if (selfLink.getHref() == null) {
                mLog.info(errorPrefix + "the link href is missing");
                valid = false;
            }

            // display width is optional, but if it's there, it has to be a positive integer
            String displayWidthStr =
                    selfLink.getAttributeValue(mNterExtension.getQName(NterExtension.DISPLAY_WIDTH_ATTRIBUTE_NAME));
            if (displayWidthStr != null) {
                try {
                    int displayWidth = Integer.valueOf(displayWidthStr);
                    if (displayWidth < 1) {
                        mLog.info(errorPrefix + "the display width is not a positive integer");
                        valid = false;
                    }
                }
                catch (NumberFormatException e) {
                    mLog.info(errorPrefix + "the display width is not a number");
                    valid = false;
                }
            }

            // display height is optional, but if it's there, is has to be a positive integer
            String displayHeightStr =
                    selfLink.getAttributeValue(mNterExtension.getQName(NterExtension.DISPLAY_HEIGHT_ATTRIBUTE_NAME));
            if (displayHeightStr != null) {
                try {
                    int displayHeight = Integer.valueOf(displayHeightStr);
                    if (displayHeight < 1) {
                        mLog.info(errorPrefix + "the display height is not a positive integer");
                        valid = false;
                    }
                }
                catch (NumberFormatException e) {
                    mLog.info(errorPrefix + "the display height is not a number");
                    valid = false;
                }
            }

        }

        // updated date is required
        if (courseComponentEntry.getUpdated() == null) {
            mLog.info(errorPrefix + "the updated date is missing");
            valid = false;
        }

        // summary is required
        String summary = courseComponentEntry.getSummary();
        if ((summary == null) || (summary.isEmpty())) {
            mLog.info(errorPrefix + "the summary is missing: " + summary);
            valid = false;
        }

        return valid;
    }


    public boolean validateGlobalReviewEntry(Entry review) {
        return true;
    }

	public boolean validateLocalReviewEntry(Entry review) {
		return true;
	}

	public boolean validateReviewEntry(Entry review) {
        return true;
    }

    public boolean validateObject(String errorPrefix, AsObject object) {
        return true;
    }
}
