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

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;
import java.util.List;


public class NterVocabulary extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterVocabulary(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterVocabulary(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public List<NterTitle> getTitles() {
        return getExtensions(mNterExtension.getQName(NterExtension.TITLE_ELEMENT_NAME));
    }


    public void addTitle(NterTitle title) {
        addExtension(title);
    }


    public List<NterCategory> getCategories() {
        return getExtensions(mNterExtension.getQName(NterExtension.CATEGORY_ELEMENT_NAME));
    }


    public void addCategory(NterCategory category) {
        addExtension(category);
    }
}