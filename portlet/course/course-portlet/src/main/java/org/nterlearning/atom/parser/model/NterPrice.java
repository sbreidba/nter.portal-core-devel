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

import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;

/**
 * This class represents the <code>nter:price</code> element.
 */
public class NterPrice extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterPrice(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(
                internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterPrice(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    private String getPriceString() {
        String priceStr = null;

        if (mNterExtension.getNterNameSpace().equals(
                NterNameSpace.FEED_VERSION_062.getNameSpace())) {
            
            // get either 'nter:price' or just 'price'
            priceStr = getAttributeValue(mNterExtension.getQName(NterExtension.PRICE_ELEMENT_NAME));
            if (priceStr == null) {
                priceStr = getAttributeValue(NterExtension.PRICE_ELEMENT_NAME);
            }
        }
        else if (mNterExtension.getNterNameSpace().equals(
                NterNameSpace.FEED_VERSION_063.getNameSpace())) {

            // get either 'nter:value' or just 'value'
            priceStr = getAttributeValue(mNterExtension.getQName(NterExtension.PRICE_ATTRIBUTE_NAME));
            if (priceStr == null) {
                priceStr = getAttributeValue(NterExtension.PRICE_ATTRIBUTE_NAME);
            }
        }

        return priceStr;
    }


    public double getPrice() {
        String priceStr = getPriceString();
        return (priceStr != null) ? Double.parseDouble(priceStr) : 0.0;
    }


    public boolean isPriceNull() {
        return (getPriceString() == null);
    }


    public void setPrice(double price) {
        setAttributeValue(
                mNterExtension.getQName(NterExtension.PRICE_ATTRIBUTE_NAME),
                String.valueOf(price));
    }


    public String getPriceUnit() {
        String priceUnit = null;

        if (mNterExtension.getNterNameSpace().equals(
                NterNameSpace.FEED_VERSION_062.getNameSpace())) {
            // grab either the 'nter:unit' or 'unit' value
            priceUnit = getAttributeValue(mNterExtension.getQName(NterExtension.PRICE_PRICEUNIT_ATTRIBUTE_NAME));
            if (priceUnit == null) {
                priceUnit = getAttributeValue(NterExtension.PRICE_PRICEUNIT_ATTRIBUTE_NAME);
            }
        }
        else if (mNterExtension.getNterNameSpace().equals(
                NterNameSpace.FEED_VERSION_063.getNameSpace())) {
            // grab either the 'nter:unit' or 'unit' value
            priceUnit = getAttributeValue(mNterExtension.getQName(NterExtension.PRICE_UNIT_ATTRIBUTE_NAME));
            if (priceUnit == null) {
                priceUnit = getAttributeValue(NterExtension.PRICE_UNIT_ATTRIBUTE_NAME);
            }
        }         

        return priceUnit;
    }


    public void setPriceUnit(String priceUnit) {
        setAttributeValue(
                mNterExtension.getQName(NterExtension.PRICE_UNIT_ATTRIBUTE_NAME),
                priceUnit.trim());
    }


    public String getPriceTerms() {
        // grab either 'nter:terms' or just 'terms'
        String terms =
                getAttributeValue(mNterExtension.getQName(NterExtension.PRICE_TERMS_ATTRIBUTE_NAME));
        if (terms == null) {
            terms = getAttributeValue(NterExtension.PRICE_TERMS_ATTRIBUTE_NAME);
        }

        return terms;
    }


    public void setPriceTerms(String priceTerms) {
        setAttributeValue(mNterExtension.getQName(NterExtension.PRICE_TERMS_ATTRIBUTE_NAME),
                priceTerms.trim());
    }


    public String getPriceExpiration() {
        // returns either 'nter:expiration' or just 'expiration'
        String expiration =
                getAttributeValue(mNterExtension.getQName(NterExtension.PRICE_EXPIRATION_ATTRIBUTE_NAME));
        if (expiration == null) {
            expiration = getAttributeValue(NterExtension.PRICE_EXPIRATION_ATTRIBUTE_NAME);
        }

        return expiration;
    }


    public void setPriceExpiration(String priceExpiration) {
        setAttributeValue(mNterExtension.getQName(NterExtension.PRICE_EXPIRATION_ATTRIBUTE_NAME),
                priceExpiration.trim());
    }
}