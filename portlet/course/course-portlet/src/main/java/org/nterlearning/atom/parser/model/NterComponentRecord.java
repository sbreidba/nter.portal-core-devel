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
import org.apache.abdera.model.AtomDate;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;
import java.util.Date;

public class NterComponentRecord extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterComponentRecord(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterComponentRecord(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public String getComponentId() {
        // return either 'nter:componentId' or just 'componentId'
        String componentId =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_ID_ATTRIBUTE_NAME));
        if (componentId == null) {
            componentId = getAttributeValue(NterExtension.COMPONENT_ID_ATTRIBUTE_NAME);
        }

        return componentId;
    }


    public void setComponentId(String componentId) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPONENT_ID_ATTRIBUTE_NAME),
                componentId.trim());
    }


    public int getCompletionPercent() {
        // get either 'nter:completionPercent' or just 'completionPercent'
        String completionPercentStr =
                getAttributeValue(mNterExtension.getQName(NterExtension.COMPLETION_PERCENT_ATTRIBUTE_NAME));
        if (completionPercentStr == null) {
            completionPercentStr = getAttributeValue(NterExtension.COMPLETION_PERCENT_ATTRIBUTE_NAME);
        }

        return (completionPercentStr != null)
                ? Integer.parseInt(completionPercentStr)
                : -1;
    }


    public void setCompletionPercent(int completionPercent) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COMPLETION_PERCENT_ATTRIBUTE_NAME),
                String.valueOf(completionPercent));
    }


    public String getProgress() {
        // return either 'nter:progress' or just 'progress'
        String progress =
                getAttributeValue(mNterExtension.getQName(NterExtension.PROGRESS_ATTRIBUTE_NAME));
        if (progress == null) {
            progress = getAttributeValue(NterExtension.PROGRESS_DATE_ATTRIBUTE_NAME);
        }

        return progress;
    }


    public void setProgress(String progress) {
        setAttributeValue(mNterExtension.getQName(NterExtension.PROGRESS_ATTRIBUTE_NAME),
                progress.trim());
    }


    public Date getProgressDate() {
        // get either 'nter:progressDate' or just 'progressDate'
        String progressDateStr =
                getAttributeValue(mNterExtension.getQName(NterExtension.PROGRESS_DATE_ATTRIBUTE_NAME));
        if (progressDateStr == null) {
            progressDateStr = getAttributeValue(NterExtension.PROGRESS_DATE_ATTRIBUTE_NAME);
        }

        return (progressDateStr != null) ? AtomDate.parse(progressDateStr)
                : null;
    }


    public void setProgressDate(Date progressDate) {
        setAttributeValue(mNterExtension.getQName(NterExtension.PROGRESS_DATE_ATTRIBUTE_NAME),
                String.valueOf(progressDate));
    }
}