package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRecord}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRecord
 * @generated
 */
public class CourseRecordWrapper implements CourseRecord,
    ModelWrapper<CourseRecord> {
    private CourseRecord _courseRecord;

    public CourseRecordWrapper(CourseRecord courseRecord) {
        _courseRecord = courseRecord;
    }

    public Class<?> getModelClass() {
        return CourseRecord.class;
    }

    public String getModelClassName() {
        return CourseRecord.class.getName();
    }

    /**
    * Returns the primary key of this course record.
    *
    * @return the primary key of this course record
    */
    public long getPrimaryKey() {
        return _courseRecord.getPrimaryKey();
    }

    /**
    * Sets the primary key of this course record.
    *
    * @param primaryKey the primary key of this course record
    */
    public void setPrimaryKey(long primaryKey) {
        _courseRecord.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the course record ID of this course record.
    *
    * @return the course record ID of this course record
    */
    public long getCourseRecordId() {
        return _courseRecord.getCourseRecordId();
    }

    /**
    * Sets the course record ID of this course record.
    *
    * @param courseRecordId the course record ID of this course record
    */
    public void setCourseRecordId(long courseRecordId) {
        _courseRecord.setCourseRecordId(courseRecordId);
    }

    /**
    * Returns the feed reference ID of this course record.
    *
    * @return the feed reference ID of this course record
    */
    public long getFeedReferenceId() {
        return _courseRecord.getFeedReferenceId();
    }

    /**
    * Sets the feed reference ID of this course record.
    *
    * @param feedReferenceId the feed reference ID of this course record
    */
    public void setFeedReferenceId(long feedReferenceId) {
        _courseRecord.setFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the course record iri of this course record.
    *
    * @return the course record iri of this course record
    */
    public java.lang.String getCourseRecordIri() {
        return _courseRecord.getCourseRecordIri();
    }

    /**
    * Sets the course record iri of this course record.
    *
    * @param courseRecordIri the course record iri of this course record
    */
    public void setCourseRecordIri(java.lang.String courseRecordIri) {
        _courseRecord.setCourseRecordIri(courseRecordIri);
    }

    /**
    * Returns the user ID of this course record.
    *
    * @return the user ID of this course record
    */
    public long getUserId() {
        return _courseRecord.getUserId();
    }

    /**
    * Sets the user ID of this course record.
    *
    * @param userId the user ID of this course record
    */
    public void setUserId(long userId) {
        _courseRecord.setUserId(userId);
    }

    /**
    * Returns the user uuid of this course record.
    *
    * @return the user uuid of this course record
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseRecord.getUserUuid();
    }

    /**
    * Sets the user uuid of this course record.
    *
    * @param userUuid the user uuid of this course record
    */
    public void setUserUuid(java.lang.String userUuid) {
        _courseRecord.setUserUuid(userUuid);
    }

    /**
    * Returns the single sign on value of this course record.
    *
    * @return the single sign on value of this course record
    */
    public java.lang.String getSingleSignOnValue() {
        return _courseRecord.getSingleSignOnValue();
    }

    /**
    * Sets the single sign on value of this course record.
    *
    * @param singleSignOnValue the single sign on value of this course record
    */
    public void setSingleSignOnValue(java.lang.String singleSignOnValue) {
        _courseRecord.setSingleSignOnValue(singleSignOnValue);
    }

    /**
    * Returns the course iri of this course record.
    *
    * @return the course iri of this course record
    */
    public java.lang.String getCourseIri() {
        return _courseRecord.getCourseIri();
    }

    /**
    * Sets the course iri of this course record.
    *
    * @param courseIri the course iri of this course record
    */
    public void setCourseIri(java.lang.String courseIri) {
        _courseRecord.setCourseIri(courseIri);
    }

    /**
    * Returns the updated date of this course record.
    *
    * @return the updated date of this course record
    */
    public java.util.Date getUpdatedDate() {
        return _courseRecord.getUpdatedDate();
    }

    /**
    * Sets the updated date of this course record.
    *
    * @param updatedDate the updated date of this course record
    */
    public void setUpdatedDate(java.util.Date updatedDate) {
        _courseRecord.setUpdatedDate(updatedDate);
    }

    /**
    * Returns the completion status of this course record.
    *
    * @return the completion status of this course record
    */
    public java.lang.String getCompletionStatus() {
        return _courseRecord.getCompletionStatus();
    }

    /**
    * Sets the completion status of this course record.
    *
    * @param completionStatus the completion status of this course record
    */
    public void setCompletionStatus(java.lang.String completionStatus) {
        _courseRecord.setCompletionStatus(completionStatus);
    }

    /**
    * Returns the removed of this course record.
    *
    * @return the removed of this course record
    */
    public boolean getRemoved() {
        return _courseRecord.getRemoved();
    }

    /**
    * Returns <code>true</code> if this course record is removed.
    *
    * @return <code>true</code> if this course record is removed; <code>false</code> otherwise
    */
    public boolean isRemoved() {
        return _courseRecord.isRemoved();
    }

    /**
    * Sets whether this course record is removed.
    *
    * @param removed the removed of this course record
    */
    public void setRemoved(boolean removed) {
        _courseRecord.setRemoved(removed);
    }

    /**
    * Returns the removed date of this course record.
    *
    * @return the removed date of this course record
    */
    public java.util.Date getRemovedDate() {
        return _courseRecord.getRemovedDate();
    }

    /**
    * Sets the removed date of this course record.
    *
    * @param removedDate the removed date of this course record
    */
    public void setRemovedDate(java.util.Date removedDate) {
        _courseRecord.setRemovedDate(removedDate);
    }

    /**
    * Returns the user hidden of this course record.
    *
    * @return the user hidden of this course record
    */
    public boolean getUserHidden() {
        return _courseRecord.getUserHidden();
    }

    /**
    * Returns <code>true</code> if this course record is user hidden.
    *
    * @return <code>true</code> if this course record is user hidden; <code>false</code> otherwise
    */
    public boolean isUserHidden() {
        return _courseRecord.isUserHidden();
    }

    /**
    * Sets whether this course record is user hidden.
    *
    * @param userHidden the user hidden of this course record
    */
    public void setUserHidden(boolean userHidden) {
        _courseRecord.setUserHidden(userHidden);
    }

    /**
    * Returns the assigned of this course record.
    *
    * @return the assigned of this course record
    */
    public boolean getAssigned() {
        return _courseRecord.getAssigned();
    }

    /**
    * Returns <code>true</code> if this course record is assigned.
    *
    * @return <code>true</code> if this course record is assigned; <code>false</code> otherwise
    */
    public boolean isAssigned() {
        return _courseRecord.isAssigned();
    }

    /**
    * Sets whether this course record is assigned.
    *
    * @param assigned the assigned of this course record
    */
    public void setAssigned(boolean assigned) {
        _courseRecord.setAssigned(assigned);
    }

    public boolean isNew() {
        return _courseRecord.isNew();
    }

    public void setNew(boolean n) {
        _courseRecord.setNew(n);
    }

    public boolean isCachedModel() {
        return _courseRecord.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _courseRecord.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _courseRecord.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _courseRecord.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _courseRecord.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _courseRecord.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _courseRecord.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CourseRecordWrapper((CourseRecord) _courseRecord.clone());
    }

    public int compareTo(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord) {
        return _courseRecord.compareTo(courseRecord);
    }

    @Override
    public int hashCode() {
        return _courseRecord.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.CourseRecord> toCacheModel() {
        return _courseRecord.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord toEscapedModel() {
        return new CourseRecordWrapper(_courseRecord.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _courseRecord.toString();
    }

    public java.lang.String toXmlString() {
        return _courseRecord.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseRecord.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public CourseRecord getWrappedCourseRecord() {
        return _courseRecord;
    }

    public CourseRecord getWrappedModel() {
        return _courseRecord;
    }

    public void resetOriginalValues() {
        _courseRecord.resetOriginalValues();
    }
}
