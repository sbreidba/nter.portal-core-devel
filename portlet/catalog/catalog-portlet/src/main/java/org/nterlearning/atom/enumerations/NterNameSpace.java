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

package org.nterlearning.atom.enumerations;

public enum NterNameSpace {

    UNKNOWN             ("UNKNOWN", "unknown"),
    FEED_VERSION_061    ("http://nterlearning.org/xml/feeds/atom-extensions-schema_0.6.1.xsd", "0.6.1"),
    FEED_VERSION_062    ("http://nterlearning.org/xml/feeds/atom-extensions-schema_0.6.2.xsd", "0.6.2"),
    FEED_VERSION_063    ("http://nterlearning.org/xml/feeds/atom-extensions-schema_0.6.3.xsd", "0.6.3");

    private final String mNameSpace;
    private final String mVersion;

    NterNameSpace(String ns, String version) {
        mNameSpace = ns;
        mVersion = version;
    }


    public String getNameSpace() {
        return mNameSpace;
    }


    public String getVersion() {
        return mVersion;
    }

    
    public static NterNameSpace fromNameSpace(String ns) {
        for (NterNameSpace c : NterNameSpace.values()) {
            if (c.mNameSpace.equalsIgnoreCase(ns)) {
                return c;
            }
        }

        return UNKNOWN;
    }


    public static String versionFromNameSpace(String ns) {
        return fromNameSpace(ns).getVersion();
    }

    
    public static NterNameSpace getLatestNameSpace() {
        return NterNameSpace.values()[NterNameSpace.values().length - 1];
    }
}
