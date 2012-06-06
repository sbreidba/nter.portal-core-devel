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

/**
 *
 */

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.NterExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * This class represents an Atom entry with an <code>nter:entryType</code> equal
 * to <code>course</code>.
 */
public class NterCourse extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterCourse(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterCourse(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public List<NterVersion> getVersions() {
        return getExtensions(mNterExtension.getQName(NterExtension.VERSION_ELEMENT_NAME));
    }


    public void addVersion(NterVersion version) {
        addExtension(version);
    }


    public List<NterOrganization> getOrganizations() {
        return getExtensions(mNterExtension.getQName(NterExtension.ORGANIZATION_ELEMENT_NAME));
    }


    public void addOrganization(NterOrganization organization) {
        addExtension(organization);
    }


    public List<NterDuration> getDurations() {
        return getExtensions(mNterExtension.getQName(NterExtension.DURATION_ELEMENT_NAME));
    }


    public void addDuration(NterDuration duration) {
        addExtension(duration);
    }


    public List<NterTitle> getTitles() {
        return getExtensions(mNterExtension.getQName(NterExtension.TITLE_ELEMENT_NAME));
    }


    public void addTitle(NterTitle title) {
        addExtension(title);
    }


    public List<NterTranscriptAbstract> getTranscriptAbstracts() {
        return getExtensions(mNterExtension.getQName(NterExtension.TRANSCRIPT_ABSTRACT_ELEMENT_NAME));
    }


    public void addTranscriptAbstract(NterTranscriptAbstract transcriptAbstract) {
        addExtension(transcriptAbstract);
    }


    public List<NterCourseDescription> getDescriptions() {
        return getExtensions(mNterExtension.getQName(NterExtension.COURSE_DESCRIPTION_ELEMENT_NAME));
    }


    public void addDescription(NterCourseDescription description) {
        addExtension(description);
    }


    public List<NterCopyright> getCopyrights() {
        return getExtensions(mNterExtension.getQName(NterExtension.COPYRIGHT_ELEMENT_NAME));
    }


    public void addCopyright(NterCopyright copyright) {
        addExtension(copyright);
    }


    public List<NterRating> getRatings() {
        return getExtensions(mNterExtension.getQName(NterExtension.RATING_ELEMENT_NAME));
    }


    public void addRating(NterRating rating) {
        addExtension(rating);
    }


    public List<NterComponentRef> getComponentRefs() {
        return getExtensions(mNterExtension.getQName(NterExtension.COMPONENT_REF_ELEMENT_NAME));
    }


    public void addComponentRef(NterComponentRef componentRef) {
        addExtension(componentRef);
    }


    public List<NterCategoryRef> getCategoryRefs() {
        return getExtensions(mNterExtension.getQName(NterExtension.CATEGORY_REF_ELEMENT_NAME));
    }


    public void addCategoryRef(NterCategoryRef categoryRef) {
        addExtension(categoryRef);
    }


    public List<NterImage> getImages() {
        return getExtensions(mNterExtension.getQName(NterExtension.IMAGE_ELEMENT_NAME));
    }


    public void addImage(NterImage image) {
        addExtension(image);
    }


    public List<NterKeyword> getKeywords() {
        return getExtensions(mNterExtension.getQName(NterExtension.KEYWORD_ELEMENT_NAME));
    }


    public void addKeyword(NterKeyword keyword) {
        addExtension(keyword);
    }


    public List<NterRelated> getRelateds() {
        return getExtensions(mNterExtension.getQName(NterExtension.RELATED_ELEMENT_NAME));
    }


    public void addRelated(NterRelated related) {
        addExtension(related);
    }


    public List<NterRequirement> getRequirements() {
        return getExtensions(mNterExtension.getQName(NterExtension.REQUIREMENT_ELEMENT_NAME));
    }


    public void addRequirement(NterRequirement requirement) {
        addExtension(requirement);
    }


    public List<NterPrice> getPrices() {
        return getExtensions(mNterExtension.getQName(NterExtension.PRICE_ELEMENT_NAME));
    }


    public void addPrice(NterPrice price) {
        addExtension(price);
    }
}