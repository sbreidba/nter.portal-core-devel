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

/**
 *
 */

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;

public class NterCategoryRef extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


	public NterCategoryRef(Element internal) {
		super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
	}

	public NterCategoryRef(Factory factory, QName qname) {
		super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
	}

	public String getCategoryId(){
        // get either 'nter:categoryId' or just 'categoryId'
        String categoryId =
                getAttributeValue(mNterExtension.getQName(NterExtension.CATEGORY_ID_ATTRIBUTE_NAME));
        if (categoryId == null) {
            categoryId = getAttributeValue(NterExtension.CATEGORY_ID_ATTRIBUTE_NAME);
        }

        return categoryId;
	}

	public void setComponentId(String id){
		setAttributeValue(mNterExtension.getQName(NterExtension.CATEGORY_ID_ATTRIBUTE_NAME),
                          id.trim());
	}
}