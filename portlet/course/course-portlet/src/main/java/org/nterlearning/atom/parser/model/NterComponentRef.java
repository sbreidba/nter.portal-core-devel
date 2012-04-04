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

public class NterComponentRef extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterComponentRef(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get("nter"));
    }


    public NterComponentRef(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public String getComponentId() {
        // return either 'nter:componentId' or just 'componentId'
        String id =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_ID_ATTRIBUTE_NAME));
        if (id == null) {
            id = getAttributeValue(NterExtension.COMPONENT_ID_ATTRIBUTE_NAME);
        }

        return id;
    }


    public void setComponentId(String id) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_ID_ATTRIBUTE_NAME),
                          id);
    }


    public float getOrderWeight() {
        // get either 'nter:orderWeight' or just 'orderWeight'
        String orderWeightStr =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_ORDER_WEIGHT_ATTRIBUTE_NAME));
        if (orderWeightStr == null) {
            orderWeightStr = getAttributeValue(NterExtension.COMPONENT_ORDER_WEIGHT_ATTRIBUTE_NAME);
        }

        return (orderWeightStr != null) ? Float.valueOf(orderWeightStr)
                : NterExtension.MISSING_ORDER_WEIGHT;
    }


    public void setOrderWeight(float orderWeight) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_ORDER_WEIGHT_ATTRIBUTE_NAME),
                          String.valueOf(orderWeight));
    }


    public String getSectionType() {
        // return either 'nter:sectionType' or just 'sectionType'
        String sectionType =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_SECTION_TYPE_ATTRIBUTE_NAME));
        if (sectionType == null) {
            sectionType = getAttributeValue(NterExtension.COMPONENT_SECTION_TYPE_ATTRIBUTE_NAME);
        }

        return sectionType;
    }


    public void setSectionType(String sectionType) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_SECTION_TYPE_ATTRIBUTE_NAME),
                          sectionType.trim());
    }


    public String getComponentType() {
        // return either 'nter:componentType' or just 'componentType'
        String componentType =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_COMPONENT_TYPE_ATTRIBUTE_NAME));
        if (componentType == null) {
            componentType = getAttributeValue(NterExtension.COMPONENT_COMPONENT_TYPE_ATTRIBUTE_NAME);
        }

        return componentType;
    }


    public void setComponentType(String componentType) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_COMPONENT_TYPE_ATTRIBUTE_NAME),
                          componentType.trim());
    }


    public String getMimeType() {
        // return either 'nter:mimetype' or just 'mimetype'
        String mimeType =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_MIME_TYPE_ATTRIBUTE_NAME));
        if (mimeType == null) {
            mimeType = getAttributeValue(NterExtension.COMPONENT_MIME_TYPE_ATTRIBUTE_NAME);
        }

        return mimeType;
    }


    public void setMimeType(String mimeType) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_MIME_TYPE_ATTRIBUTE_NAME),
                          mimeType.trim());
    }


    public boolean getComponentPaymentRequired() {
        String componentPaymentRequiredStr =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_PAYMENT_REQUIRED_ATTRIBUTE_NAME));
        if (componentPaymentRequiredStr == null) {
            componentPaymentRequiredStr =
                    getAttributeValue(NterExtension.COMPONENT_PAYMENT_REQUIRED_ATTRIBUTE_NAME);
        }

        if ((componentPaymentRequiredStr != null) &&
                (componentPaymentRequiredStr.equalsIgnoreCase("true") ||
                        componentPaymentRequiredStr.equalsIgnoreCase("false"))) {
            return Boolean.parseBoolean(componentPaymentRequiredStr);
        }

        return NterExtension.MISSING_COMPONENT_PAYMENT_REQUIRED;
    }


    public void setComponentPaymentRequired(boolean componentPaymentRequired) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_PAYMENT_REQUIRED_ATTRIBUTE_NAME),
                          String.valueOf(componentPaymentRequired));
    }
}