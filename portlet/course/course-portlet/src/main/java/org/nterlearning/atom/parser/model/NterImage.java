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


public class NterImage extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;

	public NterImage(Element internal) {
		super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
	}

	public NterImage(Factory factory, QName qname) {
		super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
	}

	public float getOrderWeight(){
        // get either 'nter:orderWeight' or just 'orderWeight'
		String orderWeightStr =
                getAttributeValue(mNterExtension.getQName(
                        NterExtension.COMPONENT_ORDER_WEIGHT_ATTRIBUTE_NAME));
        if (orderWeightStr == null) {
            orderWeightStr = getAttributeValue(NterExtension.COMPONENT_ORDER_WEIGHT_ATTRIBUTE_NAME);
        }

        return (orderWeightStr != null) ? Float.valueOf(orderWeightStr)
                                        : NterExtension.MISSING_ORDER_WEIGHT;

	}

	public void setOrderWeight(float orderWeight){
		setAttributeValue(
                mNterExtension.getQName(NterExtension.IMAGE_ORDER_WEIGHT_ATTRIBUTE_NAME),
                String.valueOf(orderWeight));
	}

	public String getAlt(){
        // return either 'nter:alternateText' or just 'alternateText'
		String alt =
                getAttributeValue(mNterExtension.getQName(NterExtension.IMAGE_ALT_ATTRIBUTE_NAME));
        if (alt == null) {
            alt = getAttributeValue(NterExtension.IMAGE_ALT_ATTRIBUTE_NAME);
        }

        return alt;
	}

	public void setAlt(String alt){
		setAttributeValue(
                mNterExtension.getQName(NterExtension.IMAGE_ALT_ATTRIBUTE_NAME),
                alt.trim());
	}

	public String getHref(){
        // return either 'nter:href' or just 'href'
		String href =
                getAttributeValue(mNterExtension.getQName(NterExtension.IMAGE_HREF_ATTRIBUTE_NAME));
        if (href == null) {
            href = getAttributeValue(NterExtension.IMAGE_HREF_ATTRIBUTE_NAME);
        }

        return href;
	}

	public void setHref(String href){
		setAttributeValue(
                mNterExtension.getQName(NterExtension.IMAGE_HREF_ATTRIBUTE_NAME),
                href.trim());
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

	public String getMimeType(){
        // return either 'nter:mimeType' or just 'mimeType'
		String mimeType =
                getAttributeValue(mNterExtension.getQName(
                        NterExtension.IMAGE_MIME_TYPE_ATTRIBUTE_NAME));
        if (mimeType == null) {
             mimeType = getAttributeValue(NterExtension.IMAGE_MIME_TYPE_ATTRIBUTE_NAME);
        }

        return mimeType;
	}

	public void setMimeType(String type){
		setAttributeValue(
                mNterExtension.getQName(NterExtension.IMAGE_MIME_TYPE_ATTRIBUTE_NAME),
                type.trim());
	}
}