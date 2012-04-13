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
import org.nterlearning.atom.enumerations.NterEntryType;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.model.*;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Link;
import org.apache.abdera.model.Person;

import java.util.Date;
import java.util.List;

public class FeedValidator_062 extends FeedValidator_061 implements FeedValidator {

    private final Log mLog = LogFactoryUtil.getLog(FeedValidator_062.class);


    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;

    public FeedValidator_062() {
        setExtension();
    }


    @Override
    public void setExtension() {
        mNterExtension = new NterExtension(NterNameSpace.FEED_VERSION_062);
        mStaticParser = new StaticNterAtomParser(mNterExtension);

        super.setExtension(mNterExtension);
    }


    @Override
    public void setExtension(NterExtension extension) {
        mNterExtension = extension;
        mStaticParser = new StaticNterAtomParser(mNterExtension);

        super.setExtension(mNterExtension);
    }


    @Override
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
        List<NterCourse> courses =
                courseEntry.getExtensions(
                        mNterExtension.getQName(NterExtension.COURSE_ELEMENT_NAME));

        // only 1 course per entry
        if (courses.size() > 1) {
            mLog.info(errorPrefix + " contains more than one course; actual number: "
                    + courses.size());
            valid = false;
        }

        NterCourse course = mStaticParser.getCourse(courseEntry);

        if (course == null) {
            mLog.info(errorPrefix + "the course element is missing");
            valid = false;
        }
        else {

            // exactly one version per course required
            List<NterVersion> versions = course.getVersions();
            valid = FeedValidatorUtil.detectEmptyList(versions, errorPrefix, "version", valid, mLog);
            if (versions.size() > 1) {
                mLog.info(errorPrefix +
                        "Multiple course versions exist, exactly 1 is permitted");
                valid = false;
            }

            // Optional organization, 1 maximum.
            List<NterOrganization> organizations = course.getOrganizations();
            if (organizations.size() > 1) {
                mLog.info(errorPrefix +
                        "Multiple course organizations exist, maximum of 1 is permitted");
                valid = false;
            }

            // Optional duration, 1 maximum.
            List<NterDuration> durations = course.getDurations();
            if (durations.size() > 1) {
                mLog.info(errorPrefix +
                        "Multiple course durations exist, maximum of 1 is permitted");
                valid = false;
            }

            // at least one title per course, exactly one per language
            List<NterTitle> titles = course.getTitles();
            valid = FeedValidatorUtil.detectEmptyList(titles, errorPrefix, "title", valid, mLog);
            valid = FeedValidatorUtil.detectDuplicateLanguage(titles, errorPrefix, "title", valid, mLog);

            // at most one transcript abstract per language
            valid = FeedValidatorUtil.detectDuplicateLanguage(
                        course.getTranscriptAbstracts(), errorPrefix,
                        "transcriptAbstract", valid, mLog);

            // at least one description per course, exactly one per language, and exactly one per title
            List<NterCourseDescription> descriptions = course.getDescriptions();
            valid = FeedValidatorUtil.detectEmptyList(descriptions, errorPrefix,
                             "description", valid, mLog);
            valid = FeedValidatorUtil.detectDuplicateLanguage(titles, errorPrefix,
                             "description", valid, mLog);
            if (titles.size() != descriptions.size()) {
                mLog.info(errorPrefix + "there must be exactly one description per language per title");
                valid = false;
            }

            // at most one copyright per language
            valid = FeedValidatorUtil.detectDuplicateLanguage(course.getCopyrights(),
                        errorPrefix, "copyright", valid, mLog);

            // at most one rating per language
            valid = FeedValidatorUtil.detectDuplicateLanguage(course.getRatings(),
                        errorPrefix, "copyright", valid, mLog);

            // at least one component ref per course
            List<NterComponentRef> componentRefs = course.getComponentRefs();
            valid = FeedValidatorUtil.detectEmptyList(componentRefs, errorPrefix,
                        "componentRef", valid, mLog);

            // component ref ID required
            for (NterComponentRef componentRef : componentRefs) {
                if (componentRef.getComponentId() == null) {
                    mLog.info(errorPrefix + "componentId attribute is missing in a link");
                    valid = false;
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
                    mLog.info(errorPrefix +
                            "relatedEntryId attribute is missing in a 'related' element");
                    valid = false;
                }

                if (related.getRelationship() == null) {
                    mLog.info(errorPrefix +
                            "relationship attribute is missing in a 'related' element");
                    valid = false;
                }
            }

            // requirement type required
            for (NterRequirement requirement : course.getRequirements()) {
                if (requirement.getRequirementType() == null) {
                    mLog.info(errorPrefix +
                            "requirementType attribute is missing in a requirement");
                    valid = false;
                }
            }

            // Optional price, 1 maximum.
            List<NterPrice> prices = course.getPrices();
            if (prices.size() > 1) {
                mLog.info(errorPrefix +
                        "Multiple course prices exist, maximum of 1 is permitted");
                valid = false;
            }
        }

        return valid;
    }


    @Override
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

        NterComponent component = mStaticParser.getComponent(courseComponentEntry);

        // exactly 1 component version is required.
        List<NterVersion> versions = component.getVersions();
        valid = FeedValidatorUtil.detectEmptyList(versions, errorPrefix, "version", valid, mLog);
        if (versions.size() > 1) {
            mLog.info(errorPrefix +
                    "Multiple component versions exist, exactly 1 is permitted");
            valid = false;
        }

        // Optional organization, 1 maximum.
        List<NterOrganization> organizations = component.getOrganizations();
        if (organizations.size() > 1) {
            mLog.info(errorPrefix +
                    "Multiple course organizations exist, maximum of 1 is permitted");
            valid = false;
        }

        // course component title is required
        String entryTitle = courseComponentEntry.getTitle();
        if ((entryTitle == null) || (entryTitle.isEmpty())) {
            mLog.info(errorPrefix + "the entry title is missing: " + entryTitle);
            valid = false;
        }

        // get the list of links
        List<Link> links = courseComponentEntry.getLinks();

        // only 1 link per entry
        if (links.size() != 1) {
            mLog.info(errorPrefix +
                    " contains more than one link; actual number: " + links.size());
            valid = false;
        }

        Link link = links.get(0);

        // link href is required
        if (link.getHref() == null) {
            mLog.info(errorPrefix + "the link href is missing");
            valid = false;
        }

        // display width is optional, but if it's there, it has to be a positive integer
        String displayWidthStr =
                link.getAttributeValue(mNterExtension.getQName(
                        NterExtension.DISPLAY_WIDTH_ATTRIBUTE_NAME));
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
                link.getAttributeValue(mNterExtension.getQName(
                        NterExtension.DISPLAY_HEIGHT_ATTRIBUTE_NAME));
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

        // Optional price, 1 maximum.
        List<NterPrice> prices = component.getPrices();
        if (prices.size() > 1) {
            mLog.info(errorPrefix + "Multiple course prices exist, maximum of 1 is permitted");
            valid = false;
        }

        return valid;
    }


    public boolean validateReviewEntry(Entry review) {

        boolean valid = true;

        // set up the error messages
        String entryId = review.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(review);
        if (!entryType.equals(NterEntryType.REVIEW)) {
            mLog.info(errorPrefix + "the entry type is not REVIEW: " + entryType);
            valid = false;
        }

        // exactly 1 actor is required
        List<Person> authors = review.getAuthors();
        if (authors.size() > 1) {
            mLog.info(errorPrefix +
                    " contains more than one author; actual number: " + authors.size());
            valid = false;
        }

        if (authors.isEmpty()) {
            mLog.info(errorPrefix + " contains no actor");
            valid = false;
        }
        else {
            Person actor = authors.get(0);
            String actorId = mStaticParser.getActorId(actor);
            String trustedReviewer = mStaticParser.getTrustedReviewer(actor);
            AsObjectType objectType = StaticNterAtomParser.getActorObjectType(actor);

            // object type has to be person
            if (objectType == null) {
                mLog.info(errorPrefix + "objectType is missing: " + objectType);
                valid = false;
            }
            else {
                String objectTypeStr = objectType.getText();
                if (!objectTypeStr.equals(AsObjectType.AsObjectTypeType.PERSON.value())) {
                    mLog.info(errorPrefix + "the object type is not " +
                            AsObjectType.AsObjectTypeType.PERSON.value() +
                            ", but rather " + actorId);
                    valid = false;
                }
            }

            // actor ID is required
            if ((actorId == null) || (actorId.isEmpty())) {
                mLog.info(errorPrefix + "the author id is missing: " + actorId);
                valid = false;
            }

            // trusted reviewer is required
            if ((trustedReviewer == null) || (trustedReviewer.isEmpty())) {
                mLog.info(errorPrefix + "" +
                        "trustedReviewer is missing: " + trustedReviewer);
                valid = false;
            }
            else {
                // make sure it's a boolean
                try {
                    Boolean.parseBoolean(trustedReviewer);
                }
                catch (Exception e) {
                    valid = false;
                }
            }
        }

        // verb is required
        AsVerb verb = StaticNterAtomParser.getVerb(review);
        if (verb == null) {
            mLog.info(errorPrefix + "verb is missing");
            valid = false;
        }

        // object is required
        AsObject obj = StaticNterAtomParser.getObject(review);
        if (obj == null) {
            mLog.info(errorPrefix + "object is missing");
            valid = false;
        }
        else if (!validateObject(errorPrefix, obj)) {
            valid = false;
        }

        // at most one target
        AsTarget target = StaticNterAtomParser.getTarget(review);
        if (target == null) {
            mLog.info(errorPrefix + "target is missing");
            valid = false;
        }
        else if (!validateObject(errorPrefix, target)) {
            valid = false;
        }

        // time is required
        Date time = review.getPublished();
        if (time == null) {
            mLog.info(errorPrefix +
                    "the time (represented by the 'atom:published' element is missing");
            valid = false;
        }

        // title and summary are optional

        return valid;
    }

    @Override
    public boolean validateObject(String errorPrefix, AsObject obj) {

        boolean valid = true;
        String name = obj.getName();
        AsObjectType.AsObjectTypeType objType = obj.getObjectType();

        // name is required
        if ((name == null) || name.isEmpty()) {
            mLog.info(errorPrefix + "the name element is missing");
            valid = false;
        }

        // object type is required
        if (objType == null) {
            mLog.info(errorPrefix + "the object type element is missing or invalid");
            valid = false;
        }
        else if (objType.equals(AsObjectType.AsObjectTypeType.REVIEW)) {

            float rating = obj.getRating();

            if (rating == 0) {
                mLog.info(errorPrefix + "the rating element is missing");
                valid = false;
            }
            else if (rating < 0) {
                mLog.info(errorPrefix + "the rating element is improperly formatted");
                valid = false;
            }
        }
        
        return valid;
    }
}
