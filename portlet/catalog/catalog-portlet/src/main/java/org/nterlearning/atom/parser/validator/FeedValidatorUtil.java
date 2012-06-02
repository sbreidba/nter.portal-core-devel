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

package org.nterlearning.atom.parser.validator;

import com.liferay.portal.kernel.log.Log;
import org.apache.abdera.model.ExtensibleElementWrapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class provides common utility routines for the feed validators.
 */
public class FeedValidatorUtil {


    /**
     * Given a list, checks if the list is empty, and if there are, logs that fact
     * with the given parameters
     *
     * @param list
     * @param errorPrefix
     * @param element
     * @param result
     * @param log
     *
     * @return Returns false if the list is empty (and shouldn't be), and
     * result otherwise
     */
    public static boolean detectEmptyList(List<? extends Object> list,
        String errorPrefix, String element, boolean result, Log log) {

        if (list.size() < 1) {
            log.info(errorPrefix + " must contain at least one " + element +
                    "; actual number: " + list.size());
            result = false;
        }
        return result;
    }


    /**
     * Given a list of Abdera Atom elements, checks if there are any duplicate
     * languages, and if there are, logs that fact with the given parameters
     *
     * @param elements
     * @param errorPrefix
     * @param elementLabel
     * @param result
     * @param log
     *
     * @return False if duplicate languages are detected, 'result' otherwise
     */
    public static boolean detectDuplicateLanguage(List<? extends ExtensibleElementWrapper> elements,
        String errorPrefix, String elementLabel, boolean result, Log log) {

        String lang;
        Set<String> langs = new HashSet<String>();

        for (ExtensibleElementWrapper element : elements) {
            lang = element.getLanguage();
            if (langs.contains(lang)) {
                log.info(errorPrefix + " duplicate " + elementLabel +
                        " for language [" + lang + "]");
                result = false;
            }
            else {
                langs.add(lang);
			}
		}
        return result;
	}
}
