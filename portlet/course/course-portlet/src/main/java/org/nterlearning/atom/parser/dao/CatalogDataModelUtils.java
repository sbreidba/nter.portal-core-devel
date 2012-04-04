/**
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

package org.nterlearning.atom.parser.dao;

import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.model.CourseRelated;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl;
import org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl;

import java.util.Date;

/**
 * Static methods for dealing with objects from the Catalog data model.
 */
public class CatalogDataModelUtils {

    /**
     * Creates a complete Courses_Components object.
     *
     * @param courseIri IRI of the parent course
     * @param courseId ID of the parent course
     * @param componentIri IRI of the component
     * @param componentId ID of the component
     * @param orderWeight Order weight for the component
     * @param sectionType
     * @param componentType
     * @param mimeType
     * @param coursePaymentRequired True if course payment is required,
     * false otherwise
     * @param componentPaymentRequired True if component payment is required,
     * false otherwise
     *
     * @return Completed courses_components object
     */
	public static Courses_Components createCourses_Components(String courseIri,
        long courseId, String componentIri, long componentId, float orderWeight,
        String sectionType, String componentType, String mimeType,
        boolean coursePaymentRequired, boolean componentPaymentRequired){

		Courses_Components coursesComponent =
                createCourses_Components(courseIri, courseId, componentIri,
                        orderWeight, sectionType, componentType, mimeType,
                        coursePaymentRequired, componentPaymentRequired);
        coursesComponent.setComponentId(componentId);

		return coursesComponent;
	}


    /**
	 * Creates a Courses_Components object that does not depend on a database
     * component ID
     *
     * @param courseIri IRI of the parent course
     * @param courseId ID of the parent course
     * @param componentIri IRI of the component
     * @param orderWeight Order weight for the component
     * @param sectionType
     * @param componentType
     * @param mimeType
     * @param coursePaymentRequired True if course payment is required,
     * false otherwise
     * @param componentPaymentRequired True if component payment is required,
     * false otherwise
     *
     * @return
     */
	public static Courses_Components createCourses_Components(String courseIri,
        long courseId, String componentIri, float orderWeight,
        String sectionType, String componentType, String mimeType,
        boolean coursePaymentRequired, boolean componentPaymentRequired){

		Courses_Components coursesComponent =
                createCourses_Components(componentIri, orderWeight, sectionType,
                        componentType, mimeType, coursePaymentRequired,
                        componentPaymentRequired);
		coursesComponent.setCourseIri(courseIri);
		coursesComponent.setCourseId(courseId);

		return coursesComponent;
	}


    /**
	 * Creates a Courses_Components object that does not depend on a database
     * course ID
     *
     * @param courseIri IRI of the parent course
     * @param componentIri IRI of the component
     * @param componentId ID of the component
     * @param orderWeight Order weight for the component
     * @param sectionType
     * @param componentType
     * @param mimeType
     * @param coursePaymentRequired True if course payment is required,
     * false otherwise
     * @param componentPaymentRequired True if component payment is required,
     * false otherwise
     *
     * @return
     */
	public static Courses_Components createCourses_Components(String courseIri,
			String componentIri, long componentId, float orderWeight,
            String sectionType, String componentType, String mimeType,
            boolean coursePaymentRequired, boolean componentPaymentRequired){

		Courses_Components coursesComponent =
                createCourses_Components(componentIri, orderWeight, sectionType,
                        componentType, mimeType, coursePaymentRequired,
                        componentPaymentRequired);
		coursesComponent.setCourseIri(courseIri);
		coursesComponent.setComponentId(componentId);

		return coursesComponent;
	}


    /**
	 * Creates a Courses_Components object without requiring any database IDs
     *
     * @param componentIri IRI of the parent component
     * @param orderWeight Order weight for the component
     * @param sectionType
     * @param componentType
     * @param mimeType
     * @param coursePaymentRequired True if course payment is required,
     * false otherwise
     * @param componentPaymentRequired True if component payment is required,
     * false otherwise
     *
     * @return
     */
	public static Courses_Components createCourses_Components(
            String componentIri,float orderWeight, String sectionType,
            String componentType, String mimeType, boolean coursePaymentRequired,
            boolean componentPaymentRequired){

		Courses_Components coursesComponent = new Courses_ComponentsImpl();

		coursesComponent.setComponentIri(componentIri);
		coursesComponent.setOrderWeight(orderWeight);
        coursesComponent.setSectionType(sectionType);
        coursesComponent.setComponentType(componentType);
        coursesComponent.setMimeType(mimeType);
        coursesComponent.setCoursePaymentRequired(coursePaymentRequired);
        coursesComponent.setComponentPaymentRequired(componentPaymentRequired);

		return coursesComponent;
	}


	/**
	 * Creates a complete CatalogRelated object
	 *
	 * @param courseId CourseId of the new courseRelated object
	 * @param relatedCourseId CourseId of the related course
	 * @param relatedCourseIri Course IRI of the related course
	 * @param relationshipType Relationship type between the courses
     *
	 * @return Completed CourseRelated object
	 */
	public static CourseRelated createCourseRelated(long courseId,
            long relatedCourseId, String relatedCourseIri,
            String relationshipType){

		CourseRelated courseRelated = createCourseRelated(courseId,
                relatedCourseIri,relationshipType);
		courseRelated.setRelatedCourseId(relatedCourseId);

		return courseRelated;
	}


	/**
	 * Creates a CatalogRelated object without requiring any database related
     * course ID
	 *
     * @param courseId CourseId of the new courseRelated object
	 * @param relatedCourseIri Course IRI of the related course
	 * @param relationshipType Relationship type between the courses
     *
	 * @return Completed CourseRelated object
	 */
	public static CourseRelated createCourseRelated(long courseId,
            String relatedCourseIri, String relationshipType){

		CourseRelated courseRelated = new CourseRelatedImpl();

		courseRelated.setCourseId(courseId);
        courseRelated.setRelatedCourseIri(relatedCourseIri);
		courseRelated.setRelationshipType(relationshipType);

		return courseRelated;
	}


	/**
	 * Creates a complete CourseRecord object
	 *
     * @param feedRefId PrimaryKey of the feedReference containing the record
	 * @param userId Primarykey of the user
     * @param singleSignOnValue unique identifier of the user
	 * @param courseRecordIri Unique courseRecord IRI
	 * @param courseIri Associated Course IRI
	 * @param updatedDate Date this status was updated on
	 * @param completionStatus String version of course completion
	 * @param removed True if record should be removed, false otherwise
	 * @param userHidden True if record is hidden from desktop, false otherwise
	 * @param assigned True if record is assigned to user by provider, false otherwise
     *
	 * @return Completed Course Record
	 */
	public static CourseRecord createCourseRecord(long feedRefId, long userId,
            String singleSignOnValue, String courseRecordIri, String courseIri,
            Date updatedDate, String completionStatus, boolean removed,
            boolean userHidden, boolean assigned) {

		CourseRecord courseRecord = createCourseRecord(feedRefId, singleSignOnValue,
                courseRecordIri, courseIri, updatedDate, completionStatus,
                removed, userHidden, assigned);
		courseRecord.setUserId(userId);

		return courseRecord;
	}


	/**
	 * Creates a CourseRecord object without requiring any database IDs
	 *
     * @param feedRefId PrimaryKey of the feedReference containing the record
     * @param singleSignOnValue unique identifier for a user
	 * @param courseRecordIri Unique courseRecord IRI
	 * @param courseIri Associated Course IRI
	 * @param updatedDate Date this status was updated on
	 * @param completionStatus String version of course completion
	 * @param removed True if record should be removed, false otherwise
	 * @param userHidden True if record is hidden from desktop, false otherwise
     * @param assigned True if partner enrolled student, false otherwise
     *
	 * @return Completed Course Record
	 */
	public static CourseRecord createCourseRecord(long feedRefId,
            String singleSignOnValue, String courseRecordIri, String courseIri,
            Date updatedDate, String completionStatus, boolean removed,
            boolean userHidden, boolean assigned){

		CourseRecord courseRecord = new CourseRecordImpl();

        courseRecord.setFeedReferenceId(feedRefId);
        courseRecord.setSingleSignOnValue(singleSignOnValue);
		courseRecord.setCourseRecordIri(courseRecordIri);
		courseRecord.setCourseIri(courseIri);
		courseRecord.setUpdatedDate(updatedDate);
		courseRecord.setCompletionStatus(completionStatus);
		courseRecord.setRemoved(removed);
		courseRecord.setUserHidden(userHidden);
        courseRecord.setAssigned(assigned);
		return courseRecord;
	}

    
	/**
	 * Creates a complete ComponentRecord object
	 *
     * @param courseRecordId PrimaryKey of the CourseRecord containing this record
	 * @param componentIri Unique IRI of the associated course component
	 * @param updatedDate date this record was updated
	 * @param completionPercent Percentage of course component completed
	 * @param completionStatus String representation of the completion
     *
	 * @return Completed ComponentRecord
	 */
	public static ComponentRecord createComponentRecord(long courseRecordId,
            String componentIri, Date updatedDate, int completionPercent,
            String completionStatus){

		ComponentRecord componentRecord = new ComponentRecordImpl();

        componentRecord.setCourseRecordId(courseRecordId);
		componentRecord.setComponentIri(componentIri);
		componentRecord.setUpdatedDate(updatedDate);
		componentRecord.setCompletionPercent(completionPercent);
		componentRecord.setCompletionStatus(completionStatus);

		return componentRecord;
	}
}