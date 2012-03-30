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

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * This class represents an Atom entry with an <code>nter:entryType</code>
 * equal to <code>course-component</code>.
 */
public class NterComponent extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


	public NterComponent(Element internal) {
		super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
	}

	public NterComponent(Factory factory, QName qname) {
		super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
	}

    public List<NterVersion> getVersions(){
		return getExtensions(mNterExtension.getQName(NterExtension.VERSION_ELEMENT_NAME));
	}

	public void addVersion(NterVersion version) {
        addExtension(version);
	}

    public List<NterOrganization> getOrganizations(){
        return getExtensions(mNterExtension.getQName(NterExtension.ORGANIZATION_ELEMENT_NAME));
 	}

	public void addOrganization(NterOrganization organization) {
        addExtension(organization);
	}

    public List<NterPrice> getPrices(){
        return getExtensions(mNterExtension.getQName(NterExtension.PRICE_ELEMENT_NAME));
 	}

	public void addPrice(NterPrice price) {
        addExtension(price);
	}

	public List<NterCopyright> getCopyrights(){
		return getExtensions(mNterExtension.getQName(NterExtension.COPYRIGHT_ELEMENT_NAME));
	}

	public void addCopyright(NterCopyright copyright){
		addExtension(copyright);
	}
}
