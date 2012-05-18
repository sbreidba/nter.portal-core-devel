package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.CourseRecordServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.CourseRecordServiceSoap
 * @generated
 */
public class CourseRecordSoap implements Serializable {
    private long _courseRecordId;
    private long _feedReferenceId;
    private String _courseRecordIri;
    private long _userId;
    private String _singleSignOnValue;
    private String _courseIri;
    private Date _updatedDate;
    private String _completionStatus;
    private boolean _removed;
    private Date _removedDate;
    private boolean _userHidden;
    private boolean _assigned;

    public CourseRecordSoap() {
    }

    public static CourseRecordSoap toSoapModel(CourseRecord model) {
        CourseRecordSoap soapModel = new CourseRecordSoap();

        soapModel.setCourseRecordId(model.getCourseRecordId());
        soapModel.setFeedReferenceId(model.getFeedReferenceId());
        soapModel.setCourseRecordIri(model.getCourseRecordIri());
        soapModel.setUserId(model.getUserId());
        soapModel.setSingleSignOnValue(model.getSingleSignOnValue());
        soapModel.setCourseIri(model.getCourseIri());
        soapModel.setUpdatedDate(model.getUpdatedDate());
        soapModel.setCompletionStatus(model.getCompletionStatus());
        soapModel.setRemoved(model.getRemoved());
        soapModel.setRemovedDate(model.getRemovedDate());
        soapModel.setUserHidden(model.getUserHidden());
        soapModel.setAssigned(model.getAssigned());

        return soapModel;
    }

    public static CourseRecordSoap[] toSoapModels(CourseRecord[] models) {
        CourseRecordSoap[] soapModels = new CourseRecordSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CourseRecordSoap[][] toSoapModels(CourseRecord[][] models) {
        CourseRecordSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CourseRecordSoap[models.length][models[0].length];
        } else {
            soapModels = new CourseRecordSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CourseRecordSoap[] toSoapModels(List<CourseRecord> models) {
        List<CourseRecordSoap> soapModels = new ArrayList<CourseRecordSoap>(models.size());

        for (CourseRecord model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CourseRecordSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _courseRecordId;
    }

    public void setPrimaryKey(long pk) {
        setCourseRecordId(pk);
    }

    public long getCourseRecordId() {
        return _courseRecordId;
    }

    public void setCourseRecordId(long courseRecordId) {
        _courseRecordId = courseRecordId;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
    }

    public String getCourseRecordIri() {
        return _courseRecordIri;
    }

    public void setCourseRecordIri(String courseRecordIri) {
        _courseRecordIri = courseRecordIri;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getSingleSignOnValue() {
        return _singleSignOnValue;
    }

    public void setSingleSignOnValue(String singleSignOnValue) {
        _singleSignOnValue = singleSignOnValue;
    }

    public String getCourseIri() {
        return _courseIri;
    }

    public void setCourseIri(String courseIri) {
        _courseIri = courseIri;
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getCompletionStatus() {
        return _completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        _completionStatus = completionStatus;
    }

    public boolean getRemoved() {
        return _removed;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public void setRemoved(boolean removed) {
        _removed = removed;
    }

    public Date getRemovedDate() {
        return _removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        _removedDate = removedDate;
    }

    public boolean getUserHidden() {
        return _userHidden;
    }

    public boolean isUserHidden() {
        return _userHidden;
    }

    public void setUserHidden(boolean userHidden) {
        _userHidden = userHidden;
    }

    public boolean getAssigned() {
        return _assigned;
    }

    public boolean isAssigned() {
        return _assigned;
    }

    public void setAssigned(boolean assigned) {
        _assigned = assigned;
    }
}
