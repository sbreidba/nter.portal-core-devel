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

        boolean valid = super.validateCourseEntry(courseEntry);

        // set up the error messages
        String entryId = courseEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        NterCourse course = mStaticParser.getCourse(courseEntry);

        // exactly one version per course required
        List<NterVersion> versions = course.getVersions();
        valid = FeedValidatorUtil.detectEmptyList(versions, errorPrefix, "version", valid, mLog);
        if (versions.size() > 1) {
            mLog.info(errorPrefix + "Multiple course versions exist, exactly 1 is permitted");
            valid = false;
        }

        // Optional organization, 1 maximum.
        if (course.getOrganizations().size() > 1) {
            mLog.info(errorPrefix +
                    "Multiple course organizations exist, maximum of 1 is permitted");
            valid = false;
        }

        // Optional duration, 1 maximum.
        if (course.getDurations().size() > 1) {
            mLog.info(errorPrefix +
                    "Multiple course durations exist, maximum of 1 is permitted");
            valid = false;
        }

        // Optional price, 1 maximum.
        List<NterPrice> prices = course.getPrices();
        if (prices.size() > 1) {
            mLog.info(errorPrefix +
                    "Multiple course prices exist, maximum of 1 is permitted");
            valid = false;
        }
        else if (prices.size() == 1) {
            if (prices.get(0).isPriceNull()) {
                mLog.info(errorPrefix + " A price entry exists, but a price is not set");
                valid = false;
            }
            else if (prices.get(0).getPrice() < 0f) {
                mLog.info(errorPrefix + " A negative price has been set");
                valid = false;
            }
        }

        return valid;
    }


    @Override
    public boolean validateCourseComponentEntry(Entry courseComponentEntry) {

        boolean valid = super.validateCourseComponentEntry(courseComponentEntry);

        // set up the error messages
        String entryId = courseComponentEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        NterComponent component = mStaticParser.getComponent(courseComponentEntry);

        // exactly 1 component version is required.
        List<NterVersion> versions = component.getVersions();
        valid = FeedValidatorUtil.detectEmptyList(versions, errorPrefix, "version", valid, mLog);
        if (versions.size() > 1) {
            mLog.info(errorPrefix + "Multiple component versions exist, exactly 1 is permitted");
            valid = false;
        }

        // Optional organization, 1 maximum.
        if (component.getOrganizations().size() > 1) {
            mLog.info(errorPrefix + "Multiple course organizations exist, maximum of 1 is permitted");
            valid = false;
        }

        // Optional price, 1 maximum.
        List<NterPrice> prices = component.getPrices();
        if (prices.size() > 1) {
            mLog.info(errorPrefix + "Multiple component prices exist, maximum of 1 is permitted");
            valid = false;
        }
        else if (prices.size() == 1) {
            if (prices.get(0).isPriceNull()) {
                mLog.info(errorPrefix + " A price entry exists, but a price is not set");
                valid = false;
            }
            else if (prices.get(0).getPrice() < 0f) {
                mLog.info(errorPrefix + " A negative price has been set");
                valid = false;
            }
        }

        return valid;
    }


    public boolean validateGlobalReviewEntry(Entry review) {

        boolean validEntry = true;

        // set up the error messages
        String entryId = review.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(review);
        if (!entryType.equals(NterEntryType.GLOBAL_REVIEW)) {
            mLog.info(errorPrefix + "the entry type is not GLOBAL REVIEW: " + entryType);
            validEntry = false;
        }

        // validate the common parts
        validEntry = validateReviewEntry(review);

        // trusted reviewer is required for global reviews
        Person actor = review.getAuthors().get(0);
        String trustedReviewer = mStaticParser.getTrustedReviewer(actor);

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

        return validEntry;
    }

    @Override
    public boolean validateLocalReviewEntry(Entry review) {

        boolean validEntry = true;

        // set up the error messages
        String entryId = review.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        // entry type must be correct
        NterEntryType entryType = mStaticParser.getEntryType(review);
        if (!entryType.equals(NterEntryType.LOCAL_REVIEW)) {
            mLog.info(errorPrefix + "the entry type is not LOCAL REVIEW: " + entryType);
            validEntry = false;
        }

        return validEntry;
    }

    @Override
    public boolean validateReviewEntry(Entry review) {

        boolean valid = true;

        // set up the error messages
        String entryId = review.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";


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
