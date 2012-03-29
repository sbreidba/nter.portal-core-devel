package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRelated}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRelated
 * @generated
 */
public class CourseRelatedWrapper implements CourseRelated,
    ModelWrapper<CourseRelated> {
    private CourseRelated _courseRelated;

    public CourseRelatedWrapper(CourseRelated courseRelated) {
        _courseRelated = courseRelated;
    }

    public Class<?> getModelClass() {
        return CourseRelated.class;
    }

    public String getModelClassName() {
        return CourseRelated.class.getName();
    }

    /**
    * Returns the primary key of this course related.
    *
    * @return the primary key of this course related
    */
    public long getPrimaryKey() {
        return _courseRelated.getPrimaryKey();
    }

    /**
    * Sets the primary key of this course related.
    *
    * @param primaryKey the primary key of this course related
    */
    public void setPrimaryKey(long primaryKey) {
        _courseRelated.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the course related ID of this course related.
    *
    * @return the course related ID of this course related
    */
    public long getCourseRelatedId() {
        return _courseRelated.getCourseRelatedId();
    }

    /**
    * Sets the course related ID of this course related.
    *
    * @param courseRelatedId the course related ID of this course related
    */
    public void setCourseRelatedId(long courseRelatedId) {
        _courseRelated.setCourseRelatedId(courseRelatedId);
    }

    /**
    * Returns the course ID of this course related.
    *
    * @return the course ID of this course related
    */
    public long getCourseId() {
        return _courseRelated.getCourseId();
    }

    /**
    * Sets the course ID of this course related.
    *
    * @param courseId the course ID of this course related
    */
    public void setCourseId(long courseId) {
        _courseRelated.setCourseId(courseId);
    }

    /**
    * Returns the related course ID of this course related.
    *
    * @return the related course ID of this course related
    */
    public long getRelatedCourseId() {
        return _courseRelated.getRelatedCourseId();
    }

    /**
    * Sets the related course ID of this course related.
    *
    * @param relatedCourseId the related course ID of this course related
    */
    public void setRelatedCourseId(long relatedCourseId) {
        _courseRelated.setRelatedCourseId(relatedCourseId);
    }

    /**
    * Returns the related course iri of this course related.
    *
    * @return the related course iri of this course related
    */
    public java.lang.String getRelatedCourseIri() {
        return _courseRelated.getRelatedCourseIri();
    }

    /**
    * Sets the related course iri of this course related.
    *
    * @param relatedCourseIri the related course iri of this course related
    */
    public void setRelatedCourseIri(java.lang.String relatedCourseIri) {
        _courseRelated.setRelatedCourseIri(relatedCourseIri);
    }

    /**
    * Returns the relationship type of this course related.
    *
    * @return the relationship type of this course related
    */
    public java.lang.String getRelationshipType() {
        return _courseRelated.getRelationshipType();
    }

    /**
    * Sets the relationship type of this course related.
    *
    * @param relationshipType the relationship type of this course related
    */
    public void setRelationshipType(java.lang.String relationshipType) {
        _courseRelated.setRelationshipType(relationshipType);
    }

    public boolean isNew() {
        return _courseRelated.isNew();
    }

    public void setNew(boolean n) {
        _courseRelated.setNew(n);
    }

    public boolean isCachedModel() {
        return _courseRelated.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _courseRelated.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _courseRelated.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _courseRelated.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _courseRelated.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _courseRelated.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _courseRelated.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CourseRelatedWrapper((CourseRelated) _courseRelated.clone());
    }

    public int compareTo(CourseRelated courseRelated) {
        return _courseRelated.compareTo(courseRelated);
    }

    @Override
    public int hashCode() {
        return _courseRelated.hashCode();
    }

    public com.liferay.portal.model.CacheModel<CourseRelated> toCacheModel() {
        return _courseRelated.toCacheModel();
    }

    public CourseRelated toEscapedModel() {
        return new CourseRelatedWrapper(_courseRelated.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _courseRelated.toString();
    }

    public java.lang.String toXmlString() {
        return _courseRelated.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseRelated.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public CourseRelated getWrappedCourseRelated() {
        return _courseRelated;
    }

    public CourseRelated getWrappedModel() {
        return _courseRelated;
    }

    public void resetOriginalValues() {
        _courseRelated.resetOriginalValues();
    }
}
