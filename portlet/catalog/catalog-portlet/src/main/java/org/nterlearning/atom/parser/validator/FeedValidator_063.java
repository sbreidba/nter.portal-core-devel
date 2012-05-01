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
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.atom.parser.model.*;

import org.apache.abdera.model.Entry;
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

        boolean validEntry = super.validateCourseEntry(courseEntry);

        // set up the error messages
        String entryId = courseEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        if (courseEntry.getLinks("self").size() > 1) {
            mLog.info(errorPrefix + "does not contain the correct number of 'self' links");
            validEntry = false;
        }

        if (courseEntry.getLinks("search").size() > 1) {
            mLog.info(errorPrefix + "does not contain the correct number of 'search' links");
            validEntry = false;
        }

        return validEntry;
    }
    

    @Override
    public boolean validateCourseComponentEntry(Entry courseComponentEntry) {

        boolean validEntry = super.validateCourseComponentEntry(courseComponentEntry);

        // set up the error messages
        String entryId = courseComponentEntry.getId().toString();
        String errorPrefix = "Validation error in entry with id [" + entryId + "]: ";

        NterComponent component = mStaticParser.getComponent(courseComponentEntry);

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

        // exactly 1 actor is required
        List<Person> authors = review.getAuthors();
        if (authors.size() != 1) {
            mLog.info(errorPrefix + " Incorrect number of 'authors' detected.");
            validEntry = false;
        }
        else {
            Person actor = authors.get(0);

            String actorId = mStaticParser.getActorId(actor);
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
