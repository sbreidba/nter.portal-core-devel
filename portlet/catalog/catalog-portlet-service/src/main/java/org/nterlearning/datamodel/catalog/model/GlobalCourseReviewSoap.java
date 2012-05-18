package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.GlobalCourseReviewServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.GlobalCourseReviewServiceSoap
 * @generated
 */
public class GlobalCourseReviewSoap implements Serializable {
    private long _globalCourseReviewId;
    private long _companyId;
    private long _groupId;
    private String _courseReviewIri;
    private Date _updatedDate;
    private String _courseIri;
    private String _href;
    private String _nterInstance;
    private long _courseId;
    private String _userDisplayName;
    private String _singleSignOnValue;
    private String _summary;
    private String _content;
    private Date _createDate;
    private Date _modifiedDate;
    private double _starScore;
    private boolean _fromTrustedReviewer;
    private boolean _removed;
    private Date _removedDate;
    private boolean _isHidden;

    public GlobalCourseReviewSoap() {
    }

    public static GlobalCourseReviewSoap toSoapModel(GlobalCourseReview model) {
        GlobalCourseReviewSoap soapModel = new GlobalCourseReviewSoap();

        soapModel.setGlobalCourseReviewId(model.getGlobalCourseReviewId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCourseReviewIri(model.getCourseReviewIri());
        soapModel.setUpdatedDate(model.getUpdatedDate());
        soapModel.setCourseIri(model.getCourseIri());
        soapModel.setHref(model.getHref());
        soapModel.setNterInstance(model.getNterInstance());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setUserDisplayName(model.getUserDisplayName());
        soapModel.setSingleSignOnValue(model.getSingleSignOnValue());
        soapModel.setSummary(model.getSummary());
        soapModel.setContent(model.getContent());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setStarScore(model.getStarScore());
        soapModel.setFromTrustedReviewer(model.getFromTrustedReviewer());
        soapModel.setRemoved(model.getRemoved());
        soapModel.setRemovedDate(model.getRemovedDate());
        soapModel.setIsHidden(model.getIsHidden());

        return soapModel;
    }

    public static GlobalCourseReviewSoap[] toSoapModels(
        GlobalCourseReview[] models) {
        GlobalCourseReviewSoap[] soapModels = new GlobalCourseReviewSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static GlobalCourseReviewSoap[][] toSoapModels(
        GlobalCourseReview[][] models) {
        GlobalCourseReviewSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new GlobalCourseReviewSoap[models.length][models[0].length];
        } else {
            soapModels = new GlobalCourseReviewSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static GlobalCourseReviewSoap[] toSoapModels(
        List<GlobalCourseReview> models) {
        List<GlobalCourseReviewSoap> soapModels = new ArrayList<GlobalCourseReviewSoap>(models.size());

        for (GlobalCourseReview model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new GlobalCourseReviewSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _globalCourseReviewId;
    }

    public void setPrimaryKey(long pk) {
        setGlobalCourseReviewId(pk);
    }

    public long getGlobalCourseReviewId() {
        return _globalCourseReviewId;
    }

    public void setGlobalCourseReviewId(long globalCourseReviewId) {
        _globalCourseReviewId = globalCourseReviewId;
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

    public String getCourseReviewIri() {
        return _courseReviewIri;
    }

    public void setCourseReviewIri(String courseReviewIri) {
        _courseReviewIri = courseReviewIri;
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getCourseIri() {
        return _courseIri;
    }

    public void setCourseIri(String courseIri) {
        _courseIri = courseIri;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getNterInstance() {
        return _nterInstance;
    }

    public void setNterInstance(String nterInstance) {
        _nterInstance = nterInstance;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public String getUserDisplayName() {
        return _userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        _userDisplayName = userDisplayName;
    }

    public String getSingleSignOnValue() {
        return _singleSignOnValue;
    }

    public void setSingleSignOnValue(String singleSignOnValue) {
        _singleSignOnValue = singleSignOnValue;
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

    public double getStarScore() {
        return _starScore;
    }

    public void setStarScore(double starScore) {
        _starScore = starScore;
    }

    public boolean getFromTrustedReviewer() {
        return _fromTrustedReviewer;
    }

    public boolean isFromTrustedReviewer() {
        return _fromTrustedReviewer;
    }

    public void setFromTrustedReviewer(boolean fromTrustedReviewer) {
        _fromTrustedReviewer = fromTrustedReviewer;
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

    public boolean getIsHidden() {
        return _isHidden;
    }

    public boolean isIsHidden() {
        return _isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        _isHidden = isHidden;
    }
}
