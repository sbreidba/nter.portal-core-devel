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


package course.enumerations;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * This enumeration is designed to describe the various types of feeds we can
 * pull from the ServiceRegistry.
 */
public enum FeedType {

    unknown     ("Unknown Feed Type",    "u"),       // unknown feed type
    course      ("Course Feed",          "c"),       // course feed
    record      ("Student Record Feed",  "s"),       // student record feed
    review      ("Course Review Feed",   "r");       // review activity stream

    private final String code;
    private String label;

    private static Log mLog = LogFactoryUtil.getLog(FeedType.class);

    FeedType(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public String getCodeValue() {
        return code;
    }
    

    public static FeedType valueOfLabel(String label) {
        for (FeedType type : FeedType.values()) {
            if (type.getLabel().equalsIgnoreCase(label)) {
                return type;
            }
        }

        mLog.warn("No FeedType exists with label: " + label);
        return unknown;
    }


    public static FeedType valueOfCode(String code) {
        for (FeedType type : FeedType.values()) {
            if (type.getCodeValue().equals(code)) {
                return type;
            }
        }

        mLog.warn("No FeedType exists with code value of: " + code);
        return unknown;
    }
}