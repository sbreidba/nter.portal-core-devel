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

package org.nterlearning.course.enumerations;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * This package is designed to enumerate the various types of external download
 * links supported by NTER and the various LMS systems. 
 */
public enum ExternalLinkType {

    unknown         ("",                                    "Unknown Link Type"),
    direct          ("application/octet-stream",            "Direct Link"),
    html            ("text/html",                           "HTML"),
    pdf             ("application/pdf",                     "PDF"),

    aicc            ("application/vnd.nter.aicc",           "AICC HACP"),
    digibook        ("application/vnd.nter.dbk",            "Digibook"),
    exercise        ("application/vnd.nter.exc",            "Exercise"),
    htlm            ("application/vnd.nter.htlm",           "HTML Learning Module"),
    glossary        ("application/vnd.nter.glo",            "Glossary"),
    iliasLM         ("application/vnd.nter.lm",             "Ilias Learning Module"),
    iliasTest       ("application/vnd.nter.tst",            "NWTP Test"),
    ims             ("application/vnd.nter.ims",            "IMS"),
    imsCommon       ("application/vnd.nter.ims.common",     "IMS Common Cartridge"),
    imsBlti         ("application/vnd.nter.ims.blti",       "IMS BLTI"),
    scorm           ("application/vnd.nter.scorm",          "SCORM"),
    scorm10         ("application/vnd.nter.scorm1_0",       "SCORM 1.0"),
    scorm11         ("application/vnd.nter.scorm1_1",       "SCORM 1.1"),
    scorm12         ("application/vnd.nter.scorm1_2",       "SCORM 1.2"),
    scorm2004       ("application/vnd.nter.scorm2004",      "SCORM 2004"),
    scorm2004_1     ("application/vnd.nter.scorm2004_1",    "SCORM 2004, 1st Edition"),
    scorm2004_2     ("application/vnd.nter.scorm2004_2",    "SCORM 2004, 2nd Edition"),
    scorm2004_3     ("application/vnd.nter.scorm2004_3",    "SCORM 2004, 3rd Edition"),
    scorm2004_4     ("application/vnd.nter.scorm2004_4",    "SCORM 2004, 4th Edition"),
    survey          ("application/vnd.nter.svy",            "Survey"),
    webResource     ("application/vnd.nter.webr",           "Web Resource");


    private String mMimeType;
    private String mLabel;


	private static Log mLog = LogFactoryUtil.getLog(ExternalLinkType.class);


    ExternalLinkType(String type, String label) {
        mMimeType = type;
        mLabel = label;
    }


    public String getMimeType() {
        return mMimeType;
    }

    public String getLabel() {
        return mLabel;
    }


    public static ExternalLinkType valueOfMimeType(String mime) {
        for (ExternalLinkType type : ExternalLinkType.values()) {
            if (type.getMimeType().equalsIgnoreCase(mime)) {
                return type;
            }
        }

        mLog.error("No ExternalLinkType exists with MIME Type: " + mime);
        return unknown;
    }


    public static String labelOfMimeType(String mime) {
        return valueOfMimeType(mime).getLabel();
    }


    public static ExternalLinkType valueOfLabel(String label) {
        for (ExternalLinkType type : ExternalLinkType.values()) {
            if (type.getLabel().equalsIgnoreCase(label)) {
                return type;
            }
        }

        mLog.error("No ExternalLinkType exists with label: " + label);
        return unknown;
    }
}
