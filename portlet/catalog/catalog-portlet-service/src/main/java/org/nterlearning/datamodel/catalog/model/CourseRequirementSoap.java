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
public class CourseRequirementSoap implements Serializable {
    private long _courseRequirementId;
    private long _courseId;
    private String _requirementType;
    private String _requirementValue;

    public CourseRequirementSoap() {
    }

    public static CourseRequirementSoap toSoapModel(CourseRequirement model) {
        CourseRequirementSoap soapModel = new CourseRequirementSoap();

        soapModel.setCourseRequirementId(model.getCourseRequirementId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setRequirementType(model.getRequirementType());
        soapModel.setRequirementValue(model.getRequirementValue());

        return soapModel;
    }

    public static CourseRequirementSoap[] toSoapModels(
        CourseRequirement[] models) {
        CourseRequirementSoap[] soapModels = new CourseRequirementSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CourseRequirementSoap[][] toSoapModels(
        CourseRequirement[][] models) {
        CourseRequirementSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CourseRequirementSoap[models.length][models[0].length];
        } else {
            soapModels = new CourseRequirementSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CourseRequirementSoap[] toSoapModels(
        List<CourseRequirement> models) {
        List<CourseRequirementSoap> soapModels = new ArrayList<CourseRequirementSoap>(models.size());

        for (CourseRequirement model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CourseRequirementSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _courseRequirementId;
    }

    public void setPrimaryKey(long pk) {
        setCourseRequirementId(pk);
    }

    public long getCourseRequirementId() {
        return _courseRequirementId;
    }

    public void setCourseRequirementId(long courseRequirementId) {
        _courseRequirementId = courseRequirementId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public String getRequirementType() {
        return _requirementType;
    }

    public void setRequirementType(String requirementType) {
        _requirementType = requirementType;
    }

    public String getRequirementValue() {
        return _requirementValue;
    }

    public void setRequirementValue(String requirementValue) {
        _requirementValue = requirementValue;
    }
}
