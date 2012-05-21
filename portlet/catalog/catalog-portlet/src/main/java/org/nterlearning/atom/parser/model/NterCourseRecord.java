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


public class NterCourseRecord extends ExtensibleElementWrapper {

    // Used to manage multiple NTER schema versions
    private NterExtension mNterExtension;


    public NterCourseRecord(Element internal) {
        super(internal);
        mNterExtension = new NterExtension(internal.getNamespaces().get(NterExtension.NTER_NS_TAG));
    }


    public NterCourseRecord(Factory factory, QName qname) {
        super(factory, qname);
        mNterExtension = new NterExtension(qname.getNamespaceURI());
    }


    public String getStudentUserId() {
        // return either 'nter:studentUserId' or just 'studentUserId'
        String studentId =
                getAttributeValue(mNterExtension.getQName(NterExtension.STUDENT_USER_ID_ATTRIBUTE_NAME));
        if (studentId == null) {
            studentId = getAttributeValue(NterExtension.STUDENT_USER_ID_ATTRIBUTE_NAME);
        }

        return studentId;
    }


    public void setStudentUserId(String studentUserId) {
        setAttributeValue(mNterExtension.getQName(NterExtension.STUDENT_USER_ID_ATTRIBUTE_NAME),
                studentUserId.trim());
    }


    public String getCourseId() {
        // return either 'nter:courseId' or just 'courseId'
        String courseId =
                getAttributeValue(mNterExtension.getQName(NterExtension.COURSE_ID_ATTRIBUTE_NAME));
        if (courseId == null) {
            courseId = getAttributeValue(NterExtension.COURSE_ID_ATTRIBUTE_NAME);
        }

        return courseId;
    }


    public void setCourseId(String courseId) {
        setAttributeValue(mNterExtension.getQName(NterExtension.COURSE_ID_ATTRIBUTE_NAME),
                courseId.trim());
    }


    public String getProgress() {
        // return either 'nter:progress' or just 'progress'
        String progress =
                getAttributeValue(mNterExtension.getQName(NterExtension.PROGRESS_ATTRIBUTE_NAME));
        if (progress == null) {
            progress = getAttributeValue(NterExtension.PROGRESS_ATTRIBUTE_NAME);
        }

        return progress;
    }


    public void setProgress(String progress) {
        setAttributeValue(mNterExtension.getQName(NterExtension.PROGRESS_ATTRIBUTE_NAME),
                progress.trim());
    }


    public boolean getAssigned() {
        String assignedStr =
                getAttributeValue(mNterExtension.getQName(NterExtension.ASSIGNED_ATTRIBUTE_NAME));
        if (assignedStr == null) {
            assignedStr = getAttributeValue(NterExtension.ASSIGNED_ATTRIBUTE_NAME);
        }

        if ((assignedStr != null) &&
                (assignedStr.equalsIgnoreCase("true") || assignedStr.equalsIgnoreCase("false"))) {
            return Boolean.parseBoolean(assignedStr);
        }

        return NterExtension.MISSING_ASSIGNED;
    }


    public void setAssigned(boolean assigned) {
        setAttributeValue(mNterExtension.getQName(NterExtension.ASSIGNED_ATTRIBUTE_NAME),
                String.valueOf(assigned));
    }


    public List<NterComponentRecord> getComponentRecords() {
        return getExtensions(mNterExtension.getQName(NterExtension.COMPONENT_RECORD_ELEMENT_NAME));
    }


    public void addComponentRecord(NterComponentRecord componentRecord) {
        addExtension(componentRecord);
    }
}