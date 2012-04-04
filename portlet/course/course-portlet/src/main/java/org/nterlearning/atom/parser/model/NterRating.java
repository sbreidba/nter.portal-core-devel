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

/**
 *
 */

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;

public class NterRating extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterRating(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterRating(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public String getLevel() {
        // return either 'nter:ratingLevel' or just 'ratingLevel'
        String ratingLevel =
                getAttributeValue(mNterExtension.getQName(NterExtension.RATING_LEVEL_ATTRIBUTE_NAME));
        if (ratingLevel == null) {
            ratingLevel = getAttributeValue(NterExtension.RATING_LEVEL_ATTRIBUTE_NAME);
        }

        return ratingLevel;
    }


    public void setLevel(String level) {
        setAttributeValue(mNterExtension.getQName(NterExtension.RATING_LEVEL_ATTRIBUTE_NAME),
                level.trim());
    }


    public String getReason() {
        // return either 'nter:ratingReason' or just 'ratingReason'
        String reason =
                getAttributeValue(mNterExtension.getQName(NterExtension.RATING_REASON_ATTRIBUTE_NAME));
        if (reason == null) {
            reason = getAttributeValue(NterExtension.RATING_REASON_ATTRIBUTE_NAME);
        }

        return reason;
    }


    public void setReason(String reason) {
        setAttributeValue(mNterExtension.getQName(NterExtension.RATING_REASON_ATTRIBUTE_NAME), reason.trim());
    }


    @Override
    public String getLanguage() {
        // older feeds had 'xml:lang' instead of just 'lang', this catches both
        String language = super.getLanguage();
        if (language == null) {
            language = getAttributeValue(NterExtension.LANGUAGE_ATTRIBUTE_NAME);
        }

        return language;
    }
}