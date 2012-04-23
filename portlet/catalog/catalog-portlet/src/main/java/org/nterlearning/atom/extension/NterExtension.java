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

package org.nterlearning.atom.extension;

import org.nterlearning.atom.enumerations.NterNameSpace;

import javax.xml.namespace.QName;

public class NterExtension {


    private String NTER_NS;
    

    public NterExtension(NterNameSpace ns) {
        NTER_NS = ns.getNameSpace();
    }


    public NterExtension(String nameSpace) {
        NTER_NS = nameSpace;
    }


    public String getNterNameSpace() {
        return NTER_NS;
    }
    

    public QName getQName(String attribute) {
        return new QName(NTER_NS, attribute);
    }


    public static QName getQName(String ns, String attribute) {
        return new QName(ns, attribute);
    }


    // Namespaces
    public static final String ATOM_NS = "http://www.w3.org/2005/Atom";
    public static final String TS_NS = "http://purl.org/atompub/tombstones/1.0";
    public static final String FH_NS = "http://purl.org/syndication/history/1.0";

    public static final String NTER_NS_TAG = "nter";
    

    // NTER names
    // Top Level Types
    public static final String ENTRY_TYPE_ATTRIBUTE_NAME = "entryType";
    public static final String COURSE_ELEMENT_NAME = "course";
    public static final String COMPONENT_ELEMENT_NAME = "courseComponent";
    public static final String COURSE_RECORD_ELEMENT_NAME = "courseRecord";
    public static final String COMPONENT_RECORD_ELEMENT_NAME = "componentRecord";
    public static final String VOCABULARY_ELEMENT_NAME = "vocabulary";

    // Common Elements & Attributes
    public static final String COPYRIGHT_ELEMENT_NAME = "copyright";
    public static final String LANGUAGE_ATTRIBUTE_NAME = "lang";
    public static final String ORGANIZATION_ELEMENT_NAME = "organization";
    public static final String PRICE_ELEMENT_NAME = "price";

    // 0.6.2 pricing information
    public static final String PRICE_PRICEUNIT_ATTRIBUTE_NAME = "priceUnit";

    // 0.6.3 pricing information
    public static final String PRICE_ATTRIBUTE_NAME = "value";
    public static final String PRICE_UNIT_ATTRIBUTE_NAME = "unit";

    public static final String PRICE_TERMS_ATTRIBUTE_NAME = "terms";
    public static final String PRICE_EXPIRATION_ATTRIBUTE_NAME = "expiration";
    public static final String VERSION_ELEMENT_NAME = "version";

    // Course Elements & Attributes
    public static final String COURSE_DESCRIPTION_ELEMENT_NAME = "description";
    public static final String DURATION_ELEMENT_NAME = "duration";
    public static final String DURATION_STANDARD_ATTRIBUTE_NAME = "durationStandard";
    public static final String KEYWORD_ELEMENT_NAME = "keywords";
    public static final String TITLE_ELEMENT_NAME = "title";
    public static final String TRANSCRIPT_ABSTRACT_ELEMENT_NAME = "transcriptAbstract";
    public static final String VCARD_ELEMENT_NAME = "vcard";

    // Course Component Elements & Attributes
    public static final String COMPONENT_COMPONENT_TYPE_ATTRIBUTE_NAME = "componentType";
    public static final String COMPONENT_MIME_TYPE_ATTRIBUTE_NAME = "mimeType";
    public static final String COMPONENT_SECTION_TYPE_ATTRIBUTE_NAME = "sectionType";
    public static final String DISPLAY_HEIGHT_ATTRIBUTE_NAME = "displayHeight";
    public static final String DISPLAY_WIDTH_ATTRIBUTE_NAME = "displayWidth";

    // Component-Ref Elements & Attributes
    public static final String COMPONENT_REF_ELEMENT_NAME = "componentRef";
    public static final String COMPONENT_ID_ATTRIBUTE_NAME = "componentId";
    public static final String COMPONENT_ORDER_WEIGHT_ATTRIBUTE_NAME = "orderWeight";
    public static final String COMPONENT_PAYMENT_REQUIRED_ATTRIBUTE_NAME = "paymentRequired";

    // Image Element & Attributes
    public static final String IMAGE_ALT_ATTRIBUTE_NAME = "alternateText";
    public static final String IMAGE_ELEMENT_NAME = "image";
    public static final String IMAGE_HREF_ATTRIBUTE_NAME = "href";
    public static final String IMAGE_MIME_TYPE_ATTRIBUTE_NAME = "mimeType";
    public static final String IMAGE_ORDER_WEIGHT_ATTRIBUTE_NAME = "orderWeight";

    // Requirement Element & Attributes
    public static final String REQUIREMENT_ELEMENT_NAME = "requirement";
    public static final String REQUIREMENT_TYPE_ATTRIBUTE_NAME = "requirementType";

    // Rating Element & Attributes     
    public static final String RATING_ELEMENT_NAME = "rating";
    public static final String RATING_LEVEL_ATTRIBUTE_NAME = "ratingLevel";
    public static final String RATING_REASON_ATTRIBUTE_NAME = "ratingReason";

    // Course Related Element & Attributes
    public static final String RELATED_ELEMENT_NAME = "related";
    public static final String RELATED_ENTRY_ID_ATTRIBUTE_NAME = "relatedEntryId";
    public static final String RELATIONSHIP_ATTRIBUTE_NAME = "relationship";

    // Student Record Elements & Attributes
    public static final String STUDENT_USER_ID_ATTRIBUTE_NAME = "studentUserId";
    public static final String COURSE_ID_ATTRIBUTE_NAME = "courseId";
    public static final String COMPLETION_PERCENT_ATTRIBUTE_NAME = "completionPercent";
    public static final String PROGRESS_ATTRIBUTE_NAME = "progress";
    public static final String ASSIGNED_ATTRIBUTE_NAME = "assigned";
    public static final String PROGRESS_DATE_ATTRIBUTE_NAME = "progressDate";

    public static final String SEARCH_CONTEXT_ELEMENT_NAME = "searchContext";
    public static final String SEARCH_RELEVANCE_ELEMENT_NAME = "searchRelevance";

    public static final String CATEGORY_ELEMENT_NAME = "category";
    public static final String CATEGORY_REF_ELEMENT_NAME = "categoryRef";
    public static final String CATEGORY_ID_ATTRIBUTE_NAME = "categoryId";
    public static final String ID_ELEMENT_NAME = "id";
    public static final String TRUSTED_REVIEWER_ATTRIBUTE_NAME = "trustedReviewer";

    // Tombstone Attributes
    public static final String TS_DELETE_ENTRY = "deleted-entry";

    // Feed History attributes
    public static final String FH_COMPLETE = "complete";
    public static final String FH_ARCHIVE = "archive";

    // Tombstone qualified names
    public static final QName TS_DELETED_ENTRY = new QName(TS_NS, TS_DELETE_ENTRY);

    // Feed History qualified names
    public static final QName FH_COMPLETE_FEED = new QName(FH_NS, FH_COMPLETE);
    public static final QName FH_ARCHIVE_FEED = new QName(FH_NS, FH_ARCHIVE);

    // constants
    public static final int MISSING_ORDER_WEIGHT = -1;
    public static final boolean MISSING_ASSIGNED = false;
    public static final boolean MISSING_COMPONENT_PAYMENT_REQUIRED = false;

}