package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseReview}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseReview
 * @generated
 */
public class CourseReviewWrapper implements CourseReview,
    ModelWrapper<CourseReview> {
    private CourseReview _courseReview;

    public CourseReviewWrapper(CourseReview courseReview) {
        _courseReview = courseReview;
    }

    public Class<?> getModelClass() {
        return CourseReview.class;
    }

    public String getModelClassName() {
        return CourseReview.class.getName();
    }

    /**
    * Returns the primary key of this course review.
    *
    * @return the primary key of this course review
    */
    public long getPrimaryKey() {
        return _courseReview.getPrimaryKey();
    }

    /**
    * Sets the primary key of this course review.
    *
    * @param primaryKey the primary key of this course review
    */
    public void setPrimaryKey(long primaryKey) {
        _courseReview.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the course review ID of this course review.
    *
    * @return the course review ID of this course review
    */
    public long getCourseReviewId() {
        return _courseReview.getCourseReviewId();
    }

    /**
    * Sets the course review ID of this course review.
    *
    * @param courseReviewId the course review ID of this course review
    */
    public void setCourseReviewId(long courseReviewId) {
        _courseReview.setCourseReviewId(courseReviewId);
    }

    /**
    * Returns the company ID of this course review.
    *
    * @return the company ID of this course review
    */
    public long getCompanyId() {
        return _courseReview.getCompanyId();
    }

    /**
    * Sets the company ID of this course review.
    *
    * @param companyId the company ID of this course review
    */
    public void setCompanyId(long companyId) {
        _courseReview.setCompanyId(companyId);
    }

    /**
    * Returns the group ID of this course review.
    *
    * @return the group ID of this course review
    */
    public long getGroupId() {
        return _courseReview.getGroupId();
    }

    /**
    * Sets the group ID of this course review.
    *
    * @param groupId the group ID of this course review
    */
    public void setGroupId(long groupId) {
        _courseReview.setGroupId(groupId);
    }

    /**
    * Returns the course ID of this course review.
    *
    * @return the course ID of this course review
    */
    public long getCourseId() {
        return _courseReview.getCourseId();
    }

    /**
    * Sets the course ID of this course review.
    *
    * @param courseId the course ID of this course review
    */
    public void setCourseId(long courseId) {
        _courseReview.setCourseId(courseId);
    }

    /**
    * Returns the user ID of this course review.
    *
    * @return the user ID of this course review
    */
    public long getUserId() {
        return _courseReview.getUserId();
    }

    /**
    * Sets the user ID of this course review.
    *
    * @param userId the user ID of this course review
    */
    public void setUserId(long userId) {
        _courseReview.setUserId(userId);
    }

    /**
    * Returns the user uuid of this course review.
    *
    * @return the user uuid of this course review
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _courseReview.getUserUuid();
    }

    /**
    * Sets the user uuid of this course review.
    *
    * @param userUuid the user uuid of this course review
    */
    public void setUserUuid(java.lang.String userUuid) {
        _courseReview.setUserUuid(userUuid);
    }

    /**
    * Returns the summary of this course review.
    *
    * @return the summary of this course review
    */
    public java.lang.String getSummary() {
        return _courseReview.getSummary();
    }

    /**
    * Sets the summary of this course review.
    *
    * @param summary the summary of this course review
    */
    public void setSummary(java.lang.String summary) {
        _courseReview.setSummary(summary);
    }

    /**
    * Returns the content of this course review.
    *
    * @return the content of this course review
    */
    public java.lang.String getContent() {
        return _courseReview.getContent();
    }

    /**
    * Sets the content of this course review.
    *
    * @param content the content of this course review
    */
    public void setContent(java.lang.String content) {
        _courseReview.setContent(content);
    }

    /**
    * Returns the create date of this course review.
    *
    * @return the create date of this course review
    */
    public java.util.Date getCreateDate() {
        return _courseReview.getCreateDate();
    }

    /**
    * Sets the create date of this course review.
    *
    * @param createDate the create date of this course review
    */
    public void setCreateDate(java.util.Date createDate) {
        _courseReview.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this course review.
    *
    * @return the modified date of this course review
    */
    public java.util.Date getModifiedDate() {
        return _courseReview.getModifiedDate();
    }

    /**
    * Sets the modified date of this course review.
    *
    * @param modifiedDate the modified date of this course review
    */
    public void setModifiedDate(java.util.Date modifiedDate) {
        _courseReview.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the weighted score of this course review.
    *
    * @return the weighted score of this course review
    */
    public double getWeightedScore() {
        return _courseReview.getWeightedScore();
    }

    /**
    * Sets the weighted score of this course review.
    *
    * @param weightedScore the weighted score of this course review
    */
    public void setWeightedScore(double weightedScore) {
        _courseReview.setWeightedScore(weightedScore);
    }

    /**
    * Returns the removed of this course review.
    *
    * @return the removed of this course review
    */
    public boolean getRemoved() {
        return _courseReview.getRemoved();
    }

    /**
    * Returns <code>true</code> if this course review is removed.
    *
    * @return <code>true</code> if this course review is removed; <code>false</code> otherwise
    */
    public boolean isRemoved() {
        return _courseReview.isRemoved();
    }

    /**
    * Sets whether this course review is removed.
    *
    * @param removed the removed of this course review
    */
    public void setRemoved(boolean removed) {
        _courseReview.setRemoved(removed);
    }

    /**
    * Returns the removed date of this course review.
    *
    * @return the removed date of this course review
    */
    public java.util.Date getRemovedDate() {
        return _courseReview.getRemovedDate();
    }

    /**
    * Sets the removed date of this course review.
    *
    * @param removedDate the removed date of this course review
    */
    public void setRemovedDate(java.util.Date removedDate) {
        _courseReview.setRemovedDate(removedDate);
    }

    public boolean isNew() {
        return _courseReview.isNew();
    }

    public void setNew(boolean n) {
        _courseReview.setNew(n);
    }

    public boolean isCachedModel() {
        return _courseReview.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _courseReview.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _courseReview.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _courseReview.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _courseReview.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _courseReview.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _courseReview.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CourseReviewWrapper((CourseReview) _courseReview.clone());
    }

    public int compareTo(CourseReview courseReview) {
        return _courseReview.compareTo(courseReview);
    }

    @Override
    public int hashCode() {
        return _courseReview.hashCode();
    }

    public com.liferay.portal.model.CacheModel<CourseReview> toCacheModel() {
        return _courseReview.toCacheModel();
    }

    public CourseReview toEscapedModel() {
        return new CourseReviewWrapper(_courseReview.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _courseReview.toString();
    }

    public java.lang.String toXmlString() {
        return _courseReview.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseReview.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public CourseReview getWrappedCourseReview() {
        return _courseReview;
    }

    public CourseReview getWrappedModel() {
        return _courseReview;
    }

    public void resetOriginalValues() {
        _courseReview.resetOriginalValues();
    }
}
