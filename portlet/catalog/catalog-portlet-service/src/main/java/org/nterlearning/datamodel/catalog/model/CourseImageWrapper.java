package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseImage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseImage
 * @generated
 */
public class CourseImageWrapper implements CourseImage,
    ModelWrapper<CourseImage> {
    private CourseImage _courseImage;

    public CourseImageWrapper(CourseImage courseImage) {
        _courseImage = courseImage;
    }

    public Class<?> getModelClass() {
        return CourseImage.class;
    }

    public String getModelClassName() {
        return CourseImage.class.getName();
    }

    /**
    * Returns the primary key of this course image.
    *
    * @return the primary key of this course image
    */
    public long getPrimaryKey() {
        return _courseImage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this course image.
    *
    * @param primaryKey the primary key of this course image
    */
    public void setPrimaryKey(long primaryKey) {
        _courseImage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the course image ID of this course image.
    *
    * @return the course image ID of this course image
    */
    public long getCourseImageId() {
        return _courseImage.getCourseImageId();
    }

    /**
    * Sets the course image ID of this course image.
    *
    * @param courseImageId the course image ID of this course image
    */
    public void setCourseImageId(long courseImageId) {
        _courseImage.setCourseImageId(courseImageId);
    }

    /**
    * Returns the course ID of this course image.
    *
    * @return the course ID of this course image
    */
    public long getCourseId() {
        return _courseImage.getCourseId();
    }

    /**
    * Sets the course ID of this course image.
    *
    * @param courseId the course ID of this course image
    */
    public void setCourseId(long courseId) {
        _courseImage.setCourseId(courseId);
    }

    /**
    * Returns the order weight of this course image.
    *
    * @return the order weight of this course image
    */
    public double getOrderWeight() {
        return _courseImage.getOrderWeight();
    }

    /**
    * Sets the order weight of this course image.
    *
    * @param orderWeight the order weight of this course image
    */
    public void setOrderWeight(double orderWeight) {
        _courseImage.setOrderWeight(orderWeight);
    }

    /**
    * Returns the language of this course image.
    *
    * @return the language of this course image
    */
    public java.lang.String getLanguage() {
        return _courseImage.getLanguage();
    }

    /**
    * Sets the language of this course image.
    *
    * @param language the language of this course image
    */
    public void setLanguage(java.lang.String language) {
        _courseImage.setLanguage(language);
    }

    /**
    * Returns the image ID of this course image.
    *
    * @return the image ID of this course image
    */
    public long getImageId() {
        return _courseImage.getImageId();
    }

    /**
    * Sets the image ID of this course image.
    *
    * @param imageId the image ID of this course image
    */
    public void setImageId(long imageId) {
        _courseImage.setImageId(imageId);
    }

    /**
    * Returns the alternate text of this course image.
    *
    * @return the alternate text of this course image
    */
    public java.lang.String getAlternateText() {
        return _courseImage.getAlternateText();
    }

    /**
    * Sets the alternate text of this course image.
    *
    * @param alternateText the alternate text of this course image
    */
    public void setAlternateText(java.lang.String alternateText) {
        _courseImage.setAlternateText(alternateText);
    }

    /**
    * Returns the source image url of this course image.
    *
    * @return the source image url of this course image
    */
    public java.lang.String getSourceImageUrl() {
        return _courseImage.getSourceImageUrl();
    }

    /**
    * Sets the source image url of this course image.
    *
    * @param sourceImageUrl the source image url of this course image
    */
    public void setSourceImageUrl(java.lang.String sourceImageUrl) {
        _courseImage.setSourceImageUrl(sourceImageUrl);
    }

    /**
    * Returns the mime type of this course image.
    *
    * @return the mime type of this course image
    */
    public java.lang.String getMimeType() {
        return _courseImage.getMimeType();
    }

    /**
    * Sets the mime type of this course image.
    *
    * @param mimeType the mime type of this course image
    */
    public void setMimeType(java.lang.String mimeType) {
        _courseImage.setMimeType(mimeType);
    }

    public boolean isNew() {
        return _courseImage.isNew();
    }

    public void setNew(boolean n) {
        _courseImage.setNew(n);
    }

    public boolean isCachedModel() {
        return _courseImage.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _courseImage.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _courseImage.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _courseImage.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _courseImage.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _courseImage.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _courseImage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CourseImageWrapper((CourseImage) _courseImage.clone());
    }

    public int compareTo(CourseImage courseImage) {
        return _courseImage.compareTo(courseImage);
    }

    @Override
    public int hashCode() {
        return _courseImage.hashCode();
    }

    public com.liferay.portal.model.CacheModel<CourseImage> toCacheModel() {
        return _courseImage.toCacheModel();
    }

    public CourseImage toEscapedModel() {
        return new CourseImageWrapper(_courseImage.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _courseImage.toString();
    }

    public java.lang.String toXmlString() {
        return _courseImage.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseImage.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public CourseImage getWrappedCourseImage() {
        return _courseImage;
    }

    public CourseImage getWrappedModel() {
        return _courseImage;
    }

    public void resetOriginalValues() {
        _courseImage.resetOriginalValues();
    }
}
