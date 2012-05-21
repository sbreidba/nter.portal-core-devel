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

import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.model.Feed;

import java.util.Map;

public class FeedValidatorFactory {


    public static FeedValidator getFeedValidator(NterNameSpace ns) {
        if (ns == NterNameSpace.FEED_VERSION_061) {
            return new FeedValidator_061();
        }
        else if (ns == NterNameSpace.FEED_VERSION_062) {
            return new FeedValidator_062();
        }
        else if (ns == NterNameSpace.FEED_VERSION_063) {
            return new FeedValidator_063();
        }
        else {
            return null;
        }
    }

    public static FeedValidator getFeedValidator(Feed feed) {
        Map<String, String> nsMap = feed.getNamespaces();
        String nterNS = nsMap.get(NterExtension.NTER_NS_TAG);

        if (nterNS != null) {
            return getFeedValidator(NterNameSpace.valueOf(nterNS));
        }

        return null;
    }


    public static FeedValidator getFeedValidator(String nterNS) {
        if (nterNS.equals(NterNameSpace.FEED_VERSION_061.getNameSpace())) {
            return new FeedValidator_061();
        }
        else if (nterNS.equals(NterNameSpace.FEED_VERSION_062.getNameSpace())) {
            return new FeedValidator_062();
        }
        else if (nterNS.equals(NterNameSpace.FEED_VERSION_063.getNameSpace())) {
            return new FeedValidator_063();
        }
        else {
            return null;
        }
    }
}
