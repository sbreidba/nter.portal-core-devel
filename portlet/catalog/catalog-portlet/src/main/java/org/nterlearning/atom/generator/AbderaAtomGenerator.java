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

package org.nterlearning.atom.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.abdera.model.Feed;
import org.apache.abdera.writer.Writer;

import org.nterlearning.atom.AbderaSingleton;
import org.nterlearning.atom.parser.ServiceContextUtil;


public class AbderaAtomGenerator {

    private static Writer prettyWriter = null;
    private static Writer regularWriter = null;

    private static String FEED_IRI_REGEX_PATTERN = "^tag:[A-Za-z0-9._%+-]+,\\d{4}-\\d{2}-\\d{2}:.*";
    private static String AS_IRI_REGEX_PATTERN = "^tag:[A-Za-z0-9._%+-]+:.*";

    private static Log mLog = LogFactoryUtil.getLog(AbderaAtomGenerator.class);


    public static Feed createFeed() {
        return AbderaSingleton.getInstance().newFeed();
    }


    public static Writer getPrettyWriter() {
        if (prettyWriter == null) {
            prettyWriter =
                    AbderaSingleton.getInstance().getWriterFactory().getWriter("prettyxml");
        }

        return prettyWriter;
    }


    public static Writer getRegularWriter() {
        if (regularWriter == null) {
            regularWriter =
                    AbderaSingleton.getInstance().getWriterFactory().getWriter();
        }

        return regularWriter;
    }


    /**
     * Creates an IRI in the form of <code>tag:host:/dataType/id</code>
     *
     * @param id
     * @param dataType
     *
     * @return
     */
    public static String generateAtomId(long id, String dataType) {
        return generateAtomId(String.valueOf(id), dataType);
    }


    /**
     * Creates an IRI in the form: <code>tag:host:/dataType/id</code>
     *
     * @param id The Id of the component
     * @param dataType Type of object creating an id for, such as course-review
     * or component
     *
     * @return Formed atomId
     */
    public static String generateAtomId(String id, String dataType) {
        return generateAtomId(dataType) + "/" + id;
    }


    public static String generateAtomId(String dataType) {
        return "tag:" + ServiceContextUtil.getDefaultVirtualHost() + ":/" + dataType;
    }


    /**
     * Extracts a numeric ID from an atom ID in the form of
     * 'tag:<host>:/<data-type>/<course-review-id>'. The
     * published date has to be in the form of YYYY-MM-DD.
     *
     * @param atomId
     * @param dataType
     *
     * @return
     */
    public static long extractIdFromAtomId(String atomId, String dataType) {

        if (validateString(atomId, FEED_IRI_REGEX_PATTERN) ||
            validateString(atomId, AS_IRI_REGEX_PATTERN)) {
            return Long.valueOf(atomId.substring(atomId.lastIndexOf("/") + 1));
        }
        else {
            mLog.error("The atom ID '" + atomId + "' does not match a valid form");
            return 0;
        }
    }


    public static String extractHostFromAtomId(String atomId) {

        if (validateString(atomId, FEED_IRI_REGEX_PATTERN)) {
            return atomId.substring(4, atomId.indexOf(",", 4));
        }
        else if (validateString(atomId, AS_IRI_REGEX_PATTERN)) {
            return atomId.substring(4, atomId.indexOf(":", 4));
        }
        else {
            mLog.error("The atom ID '" + atomId + "' does not match a valid form");
            return null;
        }
    }


    public static boolean validateString(String string, String regex) {
        Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(string);
	    
	    return m.matches();
	}
}
