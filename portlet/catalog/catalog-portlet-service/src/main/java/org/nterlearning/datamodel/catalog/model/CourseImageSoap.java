package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class CourseImageSoap implements Serializable {
    private long _courseImageId;
    private long _courseId;
    private double _orderWeight;
    private String _language;
    private long _imageId;
    private String _alternateText;
    private String _sourceImageUrl;
    private String _mimeType;

    public CourseImageSoap() {
    }

    public static CourseImageSoap toSoapModel(CourseImage model) {
        CourseImageSoap soapModel = new CourseImageSoap();

        soapModel.setCourseImageId(model.getCourseImageId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setOrderWeight(model.getOrderWeight());
        soapModel.setLanguage(model.getLanguage());
        soapModel.setImageId(model.getImageId());
        soapModel.setAlternateText(model.getAlternateText());
        soapModel.setSourceImageUrl(model.getSourceImageUrl());
        soapModel.setMimeType(model.getMimeType());

        return soapModel;
    }

    public static CourseImageSoap[] toSoapModels(CourseImage[] models) {
        CourseImageSoap[] soapModels = new CourseImageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CourseImageSoap[][] toSoapModels(CourseImage[][] models) {
        CourseImageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CourseImageSoap[models.length][models[0].length];
        } else {
            soapModels = new CourseImageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CourseImageSoap[] toSoapModels(List<CourseImage> models) {
        List<CourseImageSoap> soapModels = new ArrayList<CourseImageSoap>(models.size());

        for (CourseImage model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CourseImageSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _courseImageId;
    }

    public void setPrimaryKey(long pk) {
        setCourseImageId(pk);
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
}
