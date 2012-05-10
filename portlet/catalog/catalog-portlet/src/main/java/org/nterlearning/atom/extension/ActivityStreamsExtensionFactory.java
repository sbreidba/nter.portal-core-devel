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

import org.nterlearning.atom.parser.model.*;

import org.apache.abdera.util.AbstractExtensionFactory;

/**
 * This class is used to map a specific AsExtension to an NTER model class
 * and then add that mapping to the Abdera parser.
 */
public class ActivityStreamsExtensionFactory extends AbstractExtensionFactory {

    public ActivityStreamsExtensionFactory() {
        super(AsExtension.AS_NS);

        addImpl(AsExtension.CONTENT_ELEMENT, AsContent.class);
        addImpl(AsExtension.OBJECT_ELEMENT, AsObject.class);
        addImpl(AsExtension.OBJECT_TYPE_ELEMENT, AsObjectType.class);
        addImpl(AsExtension.PERMALINK_ELEMENT, AsPermalinkUrl.class);
        addImpl(AsExtension.RATING_ELEMENT, AsRating.class);
        addImpl(AsExtension.REPRESENTATIVE_IMAGE_ELEMENT, AsRepresentativeImage.class);
        addImpl(AsExtension.TARGET_ELEMENT, AsTarget.class);
        addImpl(AsExtension.VERB_ELEMENT, AsVerb.class);
    }
}
