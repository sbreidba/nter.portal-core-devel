package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class GlobalCourseReviewClp extends BaseModelImpl<GlobalCourseReview>
    implements GlobalCourseReview {
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

    public GlobalCourseReviewClp() {
    }

    public Class<?> getModelClass() {
        return GlobalCourseReview.class;
    }

    public String getModelClassName() {
        return GlobalCourseReview.class.getName();
    }

    public long getPrimaryKey() {
        return _globalCourseReviewId;
    }

    public void setPrimaryKey(long primaryKey) {
        setGlobalCourseReviewId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_globalCourseReviewId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    public void persist() throws SystemException {
        if (this.isNew()) {
            GlobalCourseReviewLocalServiceUtil.addGlobalCourseReview(this);
        } else {
            GlobalCourseReviewLocalServiceUtil.updateGlobalCourseReview(this);
        }
    }

    @Override
    public GlobalCourseReview toEscapedModel() {
        return (GlobalCourseReview) Proxy.newProxyInstance(GlobalCourseReview.class.getClassLoader(),
            new Class[] { GlobalCourseReview.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GlobalCourseReviewClp clone = new GlobalCourseReviewClp();

        clone.setGlobalCourseReviewId(getGlobalCourseReviewId());
        clone.setCompanyId(getCompanyId());
        clone.setGroupId(getGroupId());
        clone.setCourseReviewIri(getCourseReviewIri());
        clone.setUpdatedDate(getUpdatedDate());
        clone.setCourseIri(getCourseIri());
        clone.setHref(getHref());
        clone.setNterInstance(getNterInstance());
        clone.setCourseId(getCourseId());
        clone.setUserDisplayName(getUserDisplayName());
        clone.setSingleSignOnValue(getSingleSignOnValue());
        clone.setSummary(getSummary());
        clone.setContent(getContent());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setStarScore(getStarScore());
        clone.setFromTrustedReviewer(getFromTrustedReviewer());
        clone.setRemoved(getRemoved());
        clone.setRemovedDate(getRemovedDate());
        clone.setIsHidden(getIsHidden());

        return clone;
    }

    public int compareTo(GlobalCourseReview globalCourseReview) {
        int value = 0;

        if (getFromTrustedReviewer() == globalCourseReview.getFromTrustedReviewer()) {
            value = -1;
        } else if (getFromTrustedReviewer() != globalCourseReview.getFromTrustedReviewer()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        if (getStarScore() < globalCourseReview.getStarScore()) {
            value = -1;
        } else if (getStarScore() > globalCourseReview.getStarScore()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getModifiedDate(),
                globalCourseReview.getModifiedDate());

        value = value * -1;

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

        GlobalCourseReviewClp globalCourseReview = null;

        try {
            globalCourseReview = (GlobalCourseReviewClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = globalCourseReview.getPrimaryKey();

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
        StringBundler sb = new StringBundler(41);

        sb.append("{globalCourseReviewId=");
        sb.append(getGlobalCourseReviewId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", courseReviewIri=");
        sb.append(getCourseReviewIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", courseIri=");
        sb.append(getCourseIri());
        sb.append(", href=");
        sb.append(getHref());
        sb.append(", nterInstance=");
        sb.append(getNterInstance());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", userDisplayName=");
        sb.append(getUserDisplayName());
        sb.append(", singleSignOnValue=");
        sb.append(getSingleSignOnValue());
        sb.append(", summary=");
        sb.append(getSummary());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", starScore=");
        sb.append(getStarScore());
        sb.append(", fromTrustedReviewer=");
        sb.append(getFromTrustedReviewer());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", isHidden=");
        sb.append(getIsHidden());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(64);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.GlobalCourseReview");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>globalCourseReviewId</column-name><column-value><![CDATA[");
        sb.append(getGlobalCourseReviewId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseReviewIri</column-name><column-value><![CDATA[");
        sb.append(getCourseReviewIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updatedDate</column-name><column-value><![CDATA[");
        sb.append(getUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseIri</column-name><column-value><![CDATA[");
        sb.append(getCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>href</column-name><column-value><![CDATA[");
        sb.append(getHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nterInstance</column-name><column-value><![CDATA[");
        sb.append(getNterInstance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userDisplayName</column-name><column-value><![CDATA[");
        sb.append(getUserDisplayName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>singleSignOnValue</column-name><column-value><![CDATA[");
        sb.append(getSingleSignOnValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>summary</column-name><column-value><![CDATA[");
        sb.append(getSummary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>starScore</column-name><column-value><![CDATA[");
        sb.append(getStarScore());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromTrustedReviewer</column-name><column-value><![CDATA[");
        sb.append(getFromTrustedReviewer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removed</column-name><column-value><![CDATA[");
        sb.append(getRemoved());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removedDate</column-name><column-value><![CDATA[");
        sb.append(getRemovedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isHidden</column-name><column-value><![CDATA[");
        sb.append(getIsHidden());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
