package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.CourseReviewServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.CourseReviewServiceSoap
 * @generated
 */
public class CourseReviewSoap implements Serializable {
    private long _courseReviewId;
    private long _companyId;
    private long _groupId;
    private long _courseId;
    private long _userId;
    private String _summary;
    private String _content;
    private Date _createDate;
    private Date _modifiedDate;
    private double _weightedScore;
    private boolean _removed;
    private Date _removedDate;

    public CourseReviewSoap() {
    }

    public static CourseReviewSoap toSoapModel(CourseReview model) {
        CourseReviewSoap soapModel = new CourseReviewSoap();

        soapModel.setCourseReviewId(model.getCourseReviewId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setUserId(model.getUserId());
        soapModel.setSummary(model.getSummary());
        soapModel.setContent(model.getContent());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setWeightedScore(model.getWeightedScore());
        soapModel.setRemoved(model.getRemoved());
        soapModel.setRemovedDate(model.getRemovedDate());

        return soapModel;
    }

    public static CourseReviewSoap[] toSoapModels(CourseReview[] models) {
        CourseReviewSoap[] soapModels = new CourseReviewSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CourseReviewSoap[][] toSoapModels(CourseReview[][] models) {
        CourseReviewSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CourseReviewSoap[models.length][models[0].length];
        } else {
            soapModels = new CourseReviewSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CourseReviewSoap[] toSoapModels(List<CourseReview> models) {
        List<CourseReviewSoap> soapModels = new ArrayList<CourseReviewSoap>(models.size());

        for (CourseReview model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CourseReviewSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _courseReviewId;
    }

    public void setPrimaryKey(long pk) {
        setCourseReviewId(pk);
    }

    public long getCourseReviewId() {
        return _courseReviewId;
    }

    public void setCourseReviewId(long courseReviewId) {
        _courseReviewId = courseReviewId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getSummary() {
        return _summary;
    }

    public void setSummary(String summary) {
        _summary = summary;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public double getWeightedScore() {
        return _weightedScore;
    }

    public void setWeightedScore(double weightedScore) {
        _weightedScore = weightedScore;
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
}
