package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class CourseReviewClp extends BaseModelImpl<CourseReview>
    implements CourseReview {
    private long _courseReviewId;
    private long _companyId;
    private long _groupId;
    private long _courseId;
    private long _userId;
    private String _userUuid;
    private String _summary;
    private String _content;
    private Date _createDate;
    private Date _modifiedDate;
    private double _weightedScore;
    private boolean _removed;
    private Date _removedDate;

    public CourseReviewClp() {
    }

    public Class<?> getModelClass() {
        return CourseReview.class;
    }

    public String getModelClassName() {
        return CourseReview.class.getName();
    }

    public long getPrimaryKey() {
        return _courseReviewId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCourseReviewId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_courseReviewId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
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

    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseReviewLocalServiceUtil.addCourseReview(this);
        } else {
            CourseReviewLocalServiceUtil.updateCourseReview(this);
        }
    }

    @Override
    public CourseReview toEscapedModel() {
        return (CourseReview) Proxy.newProxyInstance(CourseReview.class.getClassLoader(),
            new Class[] { CourseReview.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CourseReviewClp clone = new CourseReviewClp();

        clone.setCourseReviewId(getCourseReviewId());
        clone.setCompanyId(getCompanyId());
        clone.setGroupId(getGroupId());
        clone.setCourseId(getCourseId());
        clone.setUserId(getUserId());
        clone.setSummary(getSummary());
        clone.setContent(getContent());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setWeightedScore(getWeightedScore());
        clone.setRemoved(getRemoved());
        clone.setRemovedDate(getRemovedDate());

        return clone;
    }

    public int compareTo(CourseReview courseReview) {
        int value = 0;

        if (getWeightedScore() < courseReview.getWeightedScore()) {
            value = -1;
        } else if (getWeightedScore() > courseReview.getWeightedScore()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getModifiedDate(),
                courseReview.getModifiedDate());

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

        CourseReviewClp courseReview = null;

        try {
            courseReview = (CourseReviewClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courseReview.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{courseReviewId=");
        sb.append(getCourseReviewId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", summary=");
        sb.append(getSummary());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", weightedScore=");
        sb.append(getWeightedScore());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.CourseReview");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>courseReviewId</column-name><column-value><![CDATA[");
        sb.append(getCourseReviewId());
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
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
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
            "<column><column-name>weightedScore</column-name><column-value><![CDATA[");
        sb.append(getWeightedScore());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removed</column-name><column-value><![CDATA[");
        sb.append(getRemoved());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removedDate</column-name><column-value><![CDATA[");
        sb.append(getRemovedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
