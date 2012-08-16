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

package org.nterlearning.atom.extension;

import org.nterlearning.atom.parser.model.*;
import org.apache.abdera.util.AbstractExtensionFactory;

/**
 * This class is used to map a specific NterExtension to an NTER model class
 * and then add that mapping to the Abdera parser.
 */
public class NterCourseExtensionFactory extends AbstractExtensionFactory {

    public NterCourseExtensionFactory(String ns) {
        super(ns);

        NterExtension extension = new NterExtension(ns);

        addImpl(extension.getQName(NterExtension.COURSE_ELEMENT_NAME), NterCourse.class);
        addImpl(extension.getQName(NterExtension.COMPONENT_ELEMENT_NAME), NterComponent.class);
        addImpl(extension.getQName(NterExtension.VERSION_ELEMENT_NAME), NterVersion.class);
        addImpl(extension.getQName(NterExtension.ORGANIZATION_ELEMENT_NAME), NterOrganization.class);
        addImpl(extension.getQName(NterExtension.DURATION_ELEMENT_NAME), NterDuration.class);
        addImpl(extension.getQName(NterExtension.TITLE_ELEMENT_NAME), NterTitle.class);
        addImpl(extension.getQName(NterExtension.TRANSCRIPT_ABSTRACT_ELEMENT_NAME), NterTranscriptAbstract.class);
        addImpl(extension.getQName(NterExtension.COURSE_DESCRIPTION_ELEMENT_NAME), NterCourseDescription.class);
        addImpl(extension.getQName(NterExtension.COPYRIGHT_ELEMENT_NAME), NterCopyright.class);
        addImpl(extension.getQName(NterExtension.RATING_ELEMENT_NAME), NterRating.class);
        addImpl(extension.getQName(NterExtension.COMPONENT_REF_ELEMENT_NAME), NterComponentRef.class);
        addImpl(extension.getQName(NterExtension.IMAGE_ELEMENT_NAME), NterImage.class);
        addImpl(extension.getQName(NterExtension.KEYWORD_ELEMENT_NAME), NterKeyword.class);
        addImpl(extension.getQName(NterExtension.RELATED_ELEMENT_NAME), NterRelated.class);
        addImpl(extension.getQName(NterExtension.REQUIREMENT_ELEMENT_NAME), NterRequirement.class);
        addImpl(extension.getQName(NterExtension.PRICE_ELEMENT_NAME), NterPrice.class);

        addImpl(extension.getQName(NterExtension.COURSE_RECORD_ELEMENT_NAME), NterCourseRecord.class);
        addImpl(extension.getQName(NterExtension.COMPONENT_RECORD_ELEMENT_NAME), NterComponentRecord.class);

        addImpl(extension.getQName(NterExtension.CATEGORY_ELEMENT_NAME), NterCategory.class);
        addImpl(extension.getQName(NterExtension.CATEGORY_REF_ELEMENT_NAME), NterCategoryRef.class);
        addImpl(extension.getQName(NterExtension.ID_ELEMENT_NAME), NterId.class);
        addImpl(extension.getQName(NterExtension.VOCABULARY_ELEMENT_NAME), NterVocabulary.class);
    }
}