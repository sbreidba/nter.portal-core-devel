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
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;

public class NterDuration extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;

    public NterDuration(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }
    

    public NterDuration(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public String getDurationStandard(){
        // return either 'nter:durationStandard' or just 'durationStandard'
        String duration =
                getAttributeValue(mNterExtension.getQName(NterExtension.DURATION_STANDARD_ATTRIBUTE_NAME));
        if (duration == null) {
            duration = getAttributeValue(NterExtension.DURATION_STANDARD_ATTRIBUTE_NAME);
        }

        return duration;
    }


    public void setDurationStandard(String durationStandard){
        setAttributeValue(mNterExtension.getQName(NterExtension.DURATION_STANDARD_ATTRIBUTE_NAME),
                          durationStandard.trim());
    }
}