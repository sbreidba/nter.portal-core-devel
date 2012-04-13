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
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.atom.parser.model.*;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Link;
import org.apache.abdera.model.Person;

import java.util.List;

public class FeedValidator_063 extends FeedValidator_062 implements FeedValidator {

    private final Log mLog = LogFactoryUtil.getLog(FeedValidator_063.class);

    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;

    public FeedValidator_063() {
        setExtension();
    }


    @Override
    public void setExtension() {
        mNterExtension = new NterExtension(NterNameSpace.FEED_VERSION_063);
        mStaticParser = new StaticNterAtomParser(mNterExtension);

        super.setExtension(mNterExtension);
    }


    @Override
    public void setExtension(NterExtension extension) {
        mNterExtension = extension;
        mStaticParser = new StaticNterAtomParser(mNterExtension);
    }


    @Override
    public boolean validateCourseEntry(Entry courseEntry) {

        boolean validEntry = true;

        // set up the error messages
        String entryId = courseEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(courseEntry);
        if (!entryType.equals(NterEntryType.COURSE)) {
            mLog.info(errorPrefix + "the entry type is not COURSE: " + entryType);
            validEntry = false;
        }

        if (courseEntry.getLinks("self").size() > 1) {
            mLog.info(errorPrefix + "does not contain the correct number of 'self' links");
            validEntry = false;
        }

        if (courseEntry.getLinks("search").size() > 1) {
            mLog.info(errorPrefix + "does not contain the correct number of 'search' links");
            validEntry = false;
        }

        // get the list of courses listed in the entry, only 1 is allowed
        List<NterCourse> courses =
                courseEntry.getExtensions(
                        mNterExtension.getQName(NterExtension.COURSE_ELEMENT_NAME));
        if (courses.size() > 1) {
            mLog.info(errorPrefix +
                    " contains more than one course; actual number: " + courses.size());
            validEntry = false;
        }

        NterCourse course = mStaticParser.getCourse(courseEntry);
        if (course == null) {
            mLog.info(errorPrefix + "the course element is missing");
            validEntry = false;
        }
        else {

            // exactly one version per course required
            if (course.getVersions().size() != 1) {
                mLog.info(errorPrefix +
                        "Incorrect number of 'version' elements detected.");
                validEntry = false;
            }

            // Optional organization, 1 maximum.
            if (course.getOrganizations().size() > 1) {
                mLog.info(errorPrefix + "Multiple course organizations exist, maximum of 1 is permitted");
                validEntry = false;
            }

            // Optional duration, 1 maximum.
            if (course.getDurations().size() > 1) {
                mLog.info(errorPrefix + "Multiple course durations exist, maximum of 1 is permitted");
                validEntry = false;
            }

            // at least one title per course, exactly one per language
            List<NterTitle> titles = course.getTitles();
            validEntry = FeedValidatorUtil.detectEmptyList(titles, errorPrefix,
                    "title", validEntry, mLog);
            validEntry = FeedValidatorUtil.detectDuplicateLanguage(titles,
                    errorPrefix, "title", validEntry, mLog);

            // at most one transcript abstract per language
            validEntry = FeedValidatorUtil.detectDuplicateLanguage(course.getTranscriptAbstracts(),
                    errorPrefix, "transcriptAbstract", validEntry, mLog);

            // at least one description per course, exactly one per language, and exactly one per title
            List<NterCourseDescription> descriptions = course.getDescriptions();
            String descriptionLabel = "description";
            validEntry = FeedValidatorUtil.detectEmptyList(descriptions,
                    errorPrefix, descriptionLabel, validEntry, mLog);
            validEntry = FeedValidatorUtil.detectDuplicateLanguage(titles,
                    errorPrefix, descriptionLabel, validEntry, mLog);

            if (titles.size() != descriptions.size()) {
                mLog.info(errorPrefix +
                        "there must be exactly one description per language per title");
                validEntry = false;
            }

            // at most one copyright per language
            validEntry = FeedValidatorUtil.detectDuplicateLanguage(
                    course.getCopyrights(), errorPrefix, "copyright", validEntry, mLog);

            // at most one rating per language
            validEntry = FeedValidatorUtil.detectDuplicateLanguage(
                    course.getRatings(), errorPrefix, "copyright", validEntry, mLog);

            // at least one component ref per course
            List<NterComponentRef> componentRefs = course.getComponentRefs();
            validEntry = FeedValidatorUtil.detectEmptyList(componentRefs,
                    errorPrefix, "componentRef", validEntry, mLog);

            // component ref ID required
            for (NterComponentRef componentRef : componentRefs) {
                if (componentRef.getComponentId() == null) {
                    mLog.info(errorPrefix + "componentId attribute is missing in a link");
                    validEntry = false;
                }
            }

            // at least one image translation per image, one per language
            for (NterImage image : course.getImages()) {
                if (image.getAlt() == null) {
                    mLog.info(errorPrefix + "alt attribute is missing in an image");
                    validEntry = false;
                }

                if (image.getHref() == null) {
                    mLog.info(errorPrefix + "href attribute is missing in an image");
                    validEntry = false;
                }

                if (image.getMimeType() == null) {
                    mLog.info(errorPrefix + "mimeType attribute is missing in an image");
                    validEntry = false;
                }
            }

            // related entry Id required
            for (NterRelated related : course.getRelateds()) {
                if (related.getRelatedEntryId() == null) {
                    mLog.info(errorPrefix + "relatedEntryId attribute is missing in a 'related' element");
                    validEntry = false;
                }

                if (related.getRelationship() == null) {
                    mLog.info(errorPrefix + "relationship attribute is missing in a 'related' element");
                    validEntry = false;
                }
            }

            // requirement type required
            for (NterRequirement requirement : course.getRequirements()) {
                if (requirement.getRequirementType() == null) {
                    mLog.info(errorPrefix + "requirementType attribute is missing in a requirement");
                    validEntry = false;
                }
            }

            // Optional price, 1 maximum.
            List<NterPrice> prices = course.getPrices();
            if (prices.size() > 1) {
                mLog.info(errorPrefix + "Multiple course prices exist, maximum of 1 is permitted");
                validEntry = false;
            }
            else if (prices.size() == 1) {
                if (prices.get(0).isPriceNull()) {
                    mLog.info(errorPrefix + " A price entry exists, but a price is not set");
                    validEntry = false;
                }
                else if (prices.get(0).getPrice() < 0f) {
                    mLog.info(errorPrefix + " A negative price has been set");
                    validEntry = false;
                }
            }
        }

        return validEntry;
    }
    

    @Override
    public boolean validateCourseComponentEntry(Entry courseComponentEntry) {

        boolean validEntry = true;

        // set up the error messages
        String entryId = courseComponentEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(courseComponentEntry);
        if (!entryType.equals(NterEntryType.COURSE_COMPONENT)) {
            mLog.info(errorPrefix + "the entry type is not COURSE_COMPONENT: " + entryType);
            validEntry = false;
        }

        // course component ID is required
        if ((entryId == null) || (entryId.isEmpty())) {
            mLog.info(errorPrefix + "the entry ID is missing: " + entryId);
            validEntry = false;
        }

        NterComponent component = mStaticParser.getComponent(courseComponentEntry);

        // exactly 1 component version is required.
        if (component.getVersions().size() != 1) {
            mLog.info(errorPrefix + "Incorrect number of 'version' elements detected.");
            validEntry = false;
        }

        // Optional organization, 1 maximum.
        if (component.getOrganizations().size() > 1) {
            mLog.info(errorPrefix + "Multiple course organizations exist, maximum of 1 is permitted");
            validEntry = false;
        }

        // course component title is required
        String entryTitle = courseComponentEntry.getTitle();
        if ((entryTitle == null) || (entryTitle.isEmpty())) {
            mLog.info(errorPrefix + "the entry title is missing: " + entryTitle);
            validEntry = false;
        }

        // ensure that only 1 'rel=self' link exists
        List<Link> selfLinks = courseComponentEntry.getLinks("self");
        if (courseComponentEntry.getLinks("self").size() != 1) {
            mLog.info(errorPrefix + "does not contain the correct number of "
                    + "'self' links. Actual number is: " + selfLinks.size());
            validEntry = false;
        }
        else {
            Link selfLink = selfLinks.get(0);

            // display width is optional, but if it's there, it has to be a positive integer
            String displayWidthStr =
                    selfLink.getAttributeValue(
                            mNterExtension.getQName(NterExtension.DISPLAY_WIDTH_ATTRIBUTE_NAME));
            if (displayWidthStr != null) {
                try {
                    int displayWidth = Integer.valueOf(displayWidthStr);
                    if (displayWidth < 1) {
                        mLog.info(errorPrefix + "the display width is not a positive integer");
                        validEntry = false;
                    }
                }
                catch (NumberFormatException e) {
                    mLog.info(errorPrefix + "the display width is not a number");
                    validEntry = false;
                }
            }

            // display height is optional, but if it's there, is has to be a positive integer
            String displayHeightStr =
                    selfLink.getAttributeValue(
                            mNterExtension.getQName(NterExtension.DISPLAY_HEIGHT_ATTRIBUTE_NAME));
            if (displayHeightStr != null) {
                try {
                    int displayHeight = Integer.valueOf(displayHeightStr);
                    if (displayHeight < 1) {
                        mLog.info(errorPrefix + "the display height is not a positive integer");
                        validEntry = false;
                    }
                }
                catch (NumberFormatException e) {
                    mLog.info(errorPrefix + "the display height is not a number");
                    validEntry = false;
                }
            }
        }

        // ensure that only 1 'rel=search' link exists
        if (courseComponentEntry.getLinks("search").size() > 1) {
            mLog.info(errorPrefix + "does not contain the correct number of 'search' links ");
            validEntry = false;
        }

        // updated date is required
        if (courseComponentEntry.getUpdated() == null) {
            mLog.info(errorPrefix + "the updated date is missing");
            validEntry = false;
        }

        // summary is required
        String summary = courseComponentEntry.getSummary();
        if ((summary == null) || (summary.isEmpty())) {
            mLog.info(errorPrefix + "the summary is missing.");
            validEntry = false;
        }

        // Optional price, 1 maximum.
        List<NterPrice> prices = component.getPrices();
        if (prices.size() > 1) {
            mLog.info(errorPrefix + "Multiple component prices exist, maximum of 1 is permitted");
            validEntry = false;
        }
        else if (prices.size() == 1) {
            if (prices.get(0).isPriceNull()) {
                mLog.info(errorPrefix + " A price entry exists, but a price is not set");
                validEntry = false;
            }
            else if (prices.get(0).getPrice() < 0f) {
                mLog.info(errorPrefix + " A negative price has been set");
                validEntry = false;
            }
        }

        // copyright is required
        if (component.getCopyrights().size() == 0) {
            mLog.info(errorPrefix + "Component copyright is required.");
            validEntry=false;
        }
        // at most one copyright per language
        validEntry = FeedValidatorUtil.detectDuplicateLanguage(
                component.getCopyrights(), errorPrefix, "copyright", validEntry, mLog);

        return validEntry;
    }


    @Override
    public boolean validateReviewEntry(Entry review) {

        boolean validEntry = true;

        // set up the error messages
        String entryId = review.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(review);
        if (!entryType.equals(NterEntryType.REVIEW)) {
            mLog.info(errorPrefix + "the entry type is not REVIEW: " + entryType);
            validEntry = false;
        }

        // exactly 1 actor is required
        List<Person> authors = review.getAuthors();
        if (authors.size() != 1) {
            mLog.info(errorPrefix + " Incorrect number of 'authors' detected.");
            validEntry = false;
        }
        else {
            Person actor = authors.get(0);

            String actorId = mStaticParser.getActorId(actor);
            String trustedReviewer = mStaticParser.getTrustedReviewer(actor);
            AsObjectType objectType = StaticNterAtomParser.getActorObjectType(actor);

            // object type has to be person
            if (objectType == null) {
                mLog.info(errorPrefix + "objectType is missing: " + objectType);
                validEntry = false;
            }
            else {
                String objectTypeStr = objectType.getText();
                if (!objectTypeStr.equals(AsObjectType.AsObjectTypeType.PERSON.value())) {
                    mLog.info(errorPrefix + "the object type is not " +
                            AsObjectType.AsObjectTypeType.PERSON.value() +
                            ", but rather " + actorId);
                    validEntry = false;
                }
            }

            // actor ID is required
            if ((actorId == null) || (actorId.isEmpty())) {
                mLog.info(errorPrefix + "the author id is missing: " + actorId);
                validEntry = false;
            }

            // trusted reviewer is required
            if ((trustedReviewer == null) || (trustedReviewer.isEmpty())) {
                mLog.info(errorPrefix + "trustedReviewer is missing: " + trustedReviewer);
                validEntry = false;
            }
            else { 
                try {
                    Boolean.parseBoolean(trustedReviewer);
                }
                catch (Exception e) {
                    validEntry = false;
                }
            }
        }

        // verb is required
        if (StaticNterAtomParser.getVerb(review) == null) {
            mLog.info(errorPrefix + "verb is missing");
            validEntry = false;
        }

        // object is required
        AsObject obj = StaticNterAtomParser.getObject(review);
        if (obj == null) {
            mLog.info(errorPrefix + "object is missing");
            validEntry = false;
        }
        else if (!validateActivityStreamObject(errorPrefix, obj)) {
            validEntry = false;
        }

        // at most one target
        AsTarget target = StaticNterAtomParser.getTarget(review);
        if (target == null) {
            mLog.info(errorPrefix + "target is missing");
            validEntry = false;
        }
        else if (!validateActivityStreamObject(errorPrefix, target)) {
            validEntry = false;
        }

        // time is required
        if (review.getPublished() == null) {
            mLog.info(errorPrefix +
                    "the time (represented by the 'atom:published' element is missing");
            validEntry = false;
        }

        return validEntry;
    }


    @Override
    public boolean validateObject(String errorPrefix, AsObject object) {

        boolean validAsObject = true;

        String name = object.getName();
        AsObjectType.AsObjectTypeType objType = object.getObjectType();

        // name is required
        if ((name == null) || name.isEmpty()) {
            mLog.info(errorPrefix + "the name element is missing");
            validAsObject = false;
        }

        // object type is required
        if (objType == null) {
            mLog.info(errorPrefix + "the object type element is missing or invalid");
            validAsObject = false;
        }
        else if (objType.equals(AsObjectType.AsObjectTypeType.REVIEW)) {

            float rating = object.getRating();
            if (rating == 0) {
                mLog.info(errorPrefix + "the rating element is missing");
                validAsObject = false;
            }
            else if (rating < 0) {
                mLog.info(errorPrefix + "the rating element is improperly formatted");
                validAsObject = false;
            }
        }

        return validAsObject;
    }


    private boolean validateActivityStreamObject(String errorPrefix,
                                                 AsObject obj) {

        boolean validAsObject = true;

        String name = obj.getName();
        AsObjectType.AsObjectTypeType objType = obj.getObjectType();

        // name is required
        if ((name == null) || name.isEmpty()) {
            mLog.info(errorPrefix + "the name element is missing");
            validAsObject = false;
        }

        // object type is required
        if (objType == null) {
            mLog.info(errorPrefix + "the object type element is missing or invalid");
            validAsObject = false;
        }
        else if (objType.equals(AsObjectType.AsObjectTypeType.REVIEW)) {

            float rating = obj.getRating();
            if (rating == 0) {
                mLog.info(errorPrefix + "the rating element is missing");
                validAsObject = false;
            }
            else if (rating < 0) {
                mLog.info(errorPrefix + "the rating element is improperly formatted");
                validAsObject = false;
            }
        }

        return validAsObject;
    }
}
