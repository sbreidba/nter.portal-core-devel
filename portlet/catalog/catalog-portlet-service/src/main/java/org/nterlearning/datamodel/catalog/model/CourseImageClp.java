package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.CourseImageLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class CourseImageClp extends BaseModelImpl<CourseImage>
    implements CourseImage {
    private long _courseImageId;
    private long _courseId;
    private double _orderWeight;
    private String _language;
    private long _imageId;
    private String _alternateText;
    private String _sourceImageUrl;
    private String _mimeType;

    public CourseImageClp() {
    }

    public Class<?> getModelClass() {
        return CourseImage.class;
    }

    public String getModelClassName() {
        return CourseImage.class.getName();
    }

    public long getPrimaryKey() {
        return _courseImageId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCourseImageId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_courseImageId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getCourseImageId() {
        return _courseImageId;
    }

    public void setCourseImageId(long courseImageId) {
        _courseImageId = courseImageId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public double getOrderWeight() {
        return _orderWeight;
    }

    public void setOrderWeight(double orderWeight) {
        _orderWeight = orderWeight;
    }

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public long getImageId() {
        return _imageId;
    }

    public void setImageId(long imageId) {
        _imageId = imageId;
    }

    public String getAlternateText() {
        return _alternateText;
    }

    public void setAlternateText(String alternateText) {
        _alternateText = alternateText;
    }

    public String getSourceImageUrl() {
        return _sourceImageUrl;
    }

    public void setSourceImageUrl(String sourceImageUrl) {
        _sourceImageUrl = sourceImageUrl;
    }

    public String getMimeType() {
        return _mimeType;
    }

    public void setMimeType(String mimeType) {
        _mimeType = mimeType;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseImageLocalServiceUtil.addCourseImage(this);
        } else {
            CourseImageLocalServiceUtil.updateCourseImage(this);
        }
    }

    @Override
    public CourseImage toEscapedModel() {
        return (CourseImage) Proxy.newProxyInstance(CourseImage.class.getClassLoader(),
            new Class[] { CourseImage.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CourseImageClp clone = new CourseImageClp();

        clone.setCourseImageId(getCourseImageId());
        clone.setCourseId(getCourseId());
        clone.setOrderWeight(getOrderWeight());
        clone.setLanguage(getLanguage());
        clone.setImageId(getImageId());
        clone.setAlternateText(getAlternateText());
        clone.setSourceImageUrl(getSourceImageUrl());
        clone.setMimeType(getMimeType());

        return clone;
    }

    public int compareTo(CourseImage courseImage) {
        int value = 0;

        if (getOrderWeight() < courseImage.getOrderWeight()) {
            value = -1;
        } else if (getOrderWeight() > courseImage.getOrderWeight()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        CourseImageClp courseImage = null;

        try {
            courseImage = (CourseImageClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courseImage.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{courseImageId=");
        sb.append(getCourseImageId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", orderWeight=");
        sb.append(getOrderWeight());
        sb.append(", language=");
        sb.append(getLanguage());
        sb.append(", imageId=");
        sb.append(getImageId());
        sb.append(", alternateText=");
        sb.append(getAlternateText());
        sb.append(", sourceImageUrl=");
        sb.append(getSourceImageUrl());
        sb.append(", mimeType=");
        sb.append(getMimeType());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.CourseImage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>courseImageId</column-name><column-value><![CDATA[");
        sb.append(getCourseImageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderWeight</column-name><column-value><![CDATA[");
        sb.append(getOrderWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>language</column-name><column-value><![CDATA[");
        sb.append(getLanguage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imageId</column-name><column-value><![CDATA[");
        sb.append(getImageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>alternateText</column-name><column-value><![CDATA[");
        sb.append(getAlternateText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sourceImageUrl</column-name><column-value><![CDATA[");
        sb.append(getSourceImageUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mimeType</column-name><column-value><![CDATA[");
        sb.append(getMimeType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
