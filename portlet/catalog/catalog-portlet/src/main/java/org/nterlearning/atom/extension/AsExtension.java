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

import javax.xml.namespace.QName;

/**
 * 
 * @author gjiva
 *
 */
public class AsExtension {
	
	// Namespaces
	public static final String ATOM_NS = "http://www.w3.org/2005/Atom";
	public static final String AS_NS = "http://activitystrea.ms/spec/1.0/";
	
	// Activity Stream names
    public static final String VERB_ELEMENT_NAME = "verb";
    public static final String OBJECT_ELEMENT_NAME = "object";
    public static final String OBJECT_TYPE_ELEMENT_NAME = "object-type";
    public static final String ID_ELEMENT_NAME = "id";
    public static final String NAME_ELEMENT_NAME = "name";
    public static final String PUBLISHED_ELEMENT_NAME = "published";
    public static final String UPDATED_ELEMENT_NAME = "updated";
    public static final String RATING_ELEMENT_NAME = "rating";
    public static final String PERMALINK_ELEMENT_NAME = "permalinkUrl";
    public static final String SUMMARY_ELEMENT_NAME = "summary";
    public static final String CONTENT_ELEMENT_NAME = "content";
    public static final String TARGET_ELEMENT_NAME = "target";
    public static final String REPRESENTATIVE_IMAGE_ELEMENT_NAME = "representative-image";

    // Activity Streams qualified names
    public static final QName VERB_ELEMENT = new QName(AS_NS, VERB_ELEMENT_NAME);
    public static final QName OBJECT_ELEMENT = new QName(AS_NS, OBJECT_ELEMENT_NAME);
    public static final QName OBJECT_TYPE_ELEMENT = new QName(AS_NS, OBJECT_TYPE_ELEMENT_NAME);
    public static final QName ID_ELEMENT = new QName(AS_NS, ID_ELEMENT_NAME);
    public static final QName NAME_ELEMENT = new QName(AS_NS, NAME_ELEMENT_NAME);
    public static final QName PUBLISHED_ELEMENT = new QName(AS_NS, PUBLISHED_ELEMENT_NAME);
    public static final QName UPDATED_ELEMENT = new QName(AS_NS, UPDATED_ELEMENT_NAME);
    public static final QName RATING_ELEMENT = new QName(AS_NS, RATING_ELEMENT_NAME);
    public static final QName PERMALINK_ELEMENT = new QName(AS_NS, PERMALINK_ELEMENT_NAME);
    public static final QName SUMMARY_ELEMENT = new QName(AS_NS, SUMMARY_ELEMENT_NAME);
    public static final QName CONTENT_ELEMENT = new QName(AS_NS, CONTENT_ELEMENT_NAME);
    public static final QName TARGET_ELEMENT = new QName(AS_NS, TARGET_ELEMENT_NAME);
    public static final QName REPRESENTATIVE_IMAGE_ELEMENT = new QName(AS_NS, REPRESENTATIVE_IMAGE_ELEMENT_NAME);
    
    // Atom attributes
	public static final String ATOM_REL_ATTRIBUTE_NAME = "rel";
	public static final String ATOM_TYPE_ATTRIBUTE_NAME = "type";
	public static final String ATOM_HREF_ATTRIBUTE_NAME = "href";
	public static final String ATOM_ID_DATA_TYPE_REVIEW = "course-review";
	public static final String ATOM_ID_DATA_TYPE_ACTIVITY = "activity";
	
	// Atom attribute qualified names
	public static final QName ATOM_REL_ATTRIBUTE = new QName(ATOM_NS, ATOM_REL_ATTRIBUTE_NAME);
	public static final QName ATOM_TYPE_ATTRIBUTE = new QName(ATOM_NS, ATOM_TYPE_ATTRIBUTE_NAME);
	public static final QName ATOM_HREF_ATTRIBUTE = new QName(ATOM_NS, ATOM_HREF_ATTRIBUTE_NAME);

}
