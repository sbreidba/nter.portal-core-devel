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

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.course.enumerations.RelationshipType;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;

public class NterRelated extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterRelated(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterRelated(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public RelationshipType getRelationship() {
        try {
            // get either 'nter:relationship' or just 'relationship'
            String type =
                    getAttributeValue(mNterExtension.getQName(NterExtension.RELATIONSHIP_ATTRIBUTE_NAME));
            if (type == null) {
                type = getAttributeValue((NterExtension.RELATIONSHIP_ATTRIBUTE_NAME));
            }

            return RelationshipType.fromValue(type);
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException("Unkown Related Relationship type [" +
                    getAttributeValue(mNterExtension.getQName(NterExtension.RELATIONSHIP_ATTRIBUTE_NAME)) + "]", e);
        }
    }


    public void setRelationship(RelationshipType relationship) {
        setAttributeValue(mNterExtension.getQName(NterExtension.RELATIONSHIP_ATTRIBUTE_NAME),
                relationship.getValue());
    }


    public String getRelatedEntryId() {
        // return either 'nter:relatedEntryId' or just 'relatedEntryId'
        String relatedId =
                getAttributeValue(mNterExtension.getQName(NterExtension.RELATED_ENTRY_ID_ATTRIBUTE_NAME));
        if (relatedId == null) {
            relatedId = getAttributeValue(NterExtension.RELATED_ENTRY_ID_ATTRIBUTE_NAME);
        }

        return relatedId;
    }


    public void setRelatedEntryId(String relatedEntryId) {
        setAttributeValue(mNterExtension.getQName(NterExtension.RELATED_ENTRY_ID_ATTRIBUTE_NAME),
                relatedEntryId.trim());
    }
}