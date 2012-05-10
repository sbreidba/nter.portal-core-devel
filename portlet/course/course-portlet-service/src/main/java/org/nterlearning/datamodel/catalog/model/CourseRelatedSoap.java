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
public class CourseRelatedSoap implements Serializable {
    private long _courseRelatedId;
    private long _courseId;
    private long _relatedCourseId;
    private String _relatedCourseIri;
    private String _relationshipType;

    public CourseRelatedSoap() {
    }

    public static CourseRelatedSoap toSoapModel(CourseRelated model) {
        CourseRelatedSoap soapModel = new CourseRelatedSoap();

        soapModel.setCourseRelatedId(model.getCourseRelatedId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setRelatedCourseId(model.getRelatedCourseId());
        soapModel.setRelatedCourseIri(model.getRelatedCourseIri());
        soapModel.setRelationshipType(model.getRelationshipType());

        return soapModel;
    }

    public static CourseRelatedSoap[] toSoapModels(CourseRelated[] models) {
        CourseRelatedSoap[] soapModels = new CourseRelatedSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CourseRelatedSoap[][] toSoapModels(CourseRelated[][] models) {
        CourseRelatedSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CourseRelatedSoap[models.length][models[0].length];
        } else {
            soapModels = new CourseRelatedSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CourseRelatedSoap[] toSoapModels(List<CourseRelated> models) {
        List<CourseRelatedSoap> soapModels = new ArrayList<CourseRelatedSoap>(models.size());

        for (CourseRelated model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CourseRelatedSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _courseRelatedId;
    }

    public void setPrimaryKey(long pk) {
        setCourseRelatedId(pk);
    }

    public long getCourseRelatedId() {
        return _courseRelatedId;
    }

    public void setCourseRelatedId(long courseRelatedId) {
        _courseRelatedId = courseRelatedId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public long getRelatedCourseId() {
        return _relatedCourseId;
    }

    public void setRelatedCourseId(long relatedCourseId) {
        _relatedCourseId = relatedCourseId;
    }

    public String getRelatedCourseIri() {
        return _relatedCourseIri;
    }

    public void setRelatedCourseIri(String relatedCourseIri) {
        _relatedCourseIri = relatedCourseIri;
    }

    public String getRelationshipType() {
        return _relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        _relationshipType = relationshipType;
    }
}
