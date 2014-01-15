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

package org.nterlearning.crawl.nutch;

/**
 * This class contains Nutch specific constants that are used primarily as
 * property names.
 */
public class NutchConstants {

    // Nutch properties
    public final static String SOLR_AUTH_PROPERTY = "solr.auth";
    public final static String SOLR_AUTH_USER_PROPERTY = "solr.auth.username";
    public final static String SOLR_AUTH_PASSWORD_PROPERTY = "solr.auth.password";

    // Custom Nutch index fields
    // These must be prefixed with 'nter_' to be indexed correctly
    public static final String COURSE_DETAILS_INDEX_TAG = "nter_courseDetail";
    public static final String FEED_IRI_INDEX_TAG = "nter_feedIri";
    public static final String IRI_INDEX_TAG = "nter_entryIri";
    public static final String TITLE_INDEX_TAG = "nter_entryTitle";
    public static final String COURSE_TITLE_INDEX_TAG = "nter_courseTitle";

    // Nutch directory names
    public static final String NUTCH_CRAWLDB = "crawldb";
    public static final String NUTCH_LINKDB = "linkdb";
    public static final String NUTCH_SEGMENTS = "segments";
    public static final String NUTCH_URL = "urls";

}