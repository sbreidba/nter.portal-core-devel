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

public enum FeedRemovalReasonType {

    UNKNOWN         ("Unknown",                 "u"),
    BLACKLISTED     ("Blacklisted Feed",        "b"),
    INACTIVE        ("Inactive Feed",           "i"),
    REMOVED         ("Removed Feed",            "r");

    private String mLabel;
    private String mCode;

    private static Log mLog = LogFactoryUtil.getLog(FeedRemovalReasonType.class);

    FeedRemovalReasonType(String label, String code) {
        mLabel = label;
        mCode = code;
    }


    public String getLabel() {
        return mLabel;
    }


    public String getCode() {
        return mCode;
    }


    public static FeedRemovalReasonType valueOfCode(String code) {
        for (FeedRemovalReasonType type : FeedRemovalReasonType.values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }

        mLog.warn("No FeedRemovalReasonType exists with code: " + code);
        return UNKNOWN;
    }


    public static String getLabelFromCode(String code) {
        return valueOfCode(code).getLabel();
    }
}
