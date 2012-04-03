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

public class NterCopyright extends ExtensibleElementWrapper {

	public NterCopyright(Element internal) {
		super(internal);
	}


	public NterCopyright(Factory factory, QName qname) {
		super(factory, qname);
	}
    

    @Override
    public String getLanguage() {
        // older feeds had 'xml:lang' instead of just 'lang', this catches both
        String language = super.getLanguage();
        if (language == null) {
            language = getAttributeValue(NterExtension.LANGUAGE_ATTRIBUTE_NAME);
        }

        return language;
    }
}