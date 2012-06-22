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

package org.nterlearning.atom.parser.feedParser;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

public class FeedParserUtil {

	/**
	 * Converts from a language in a parser object to a Locale in a catalog
     * object.
	 *
	 * @param langId original language
     *
	 * @return Locale based on the provided String
	 */
	public static Locale parserLangToCatalogLocale(String langId) {
        return LocaleUtil.fromLanguageId(parserLangToCatalogLang(langId));
    }

	/**
	 * XML requires language abbreviations to be separated from their country
     * with a hyphen (e.g., "en-US"), but Liferay (or possibly Java) requires
     * an underscore (e.g., "en_US").  This method converts from the
     * parser/hyphen way to the Liferay/underscore way.
	 *
	 * @param langId String representing an object's language
     *
	 * @return Liferay compliant representation of the language
	 */
	public static String parserLangToCatalogLang(String langId) {
        return (langId == null) ? null : langId.replace('-', '_');
    }

}
