package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class CourseRecordClp extends BaseModelImpl<CourseRecord>
    implements CourseRecord {
    private long _courseRecordId;
    private long _feedReferenceId;
    private String _courseRecordIri;
    private long _userId;
    private String _userUuid;
    private String _singleSignOnValue;
    private String _courseIri;
    private Date _updatedDate;
    private String _completionStatus;
    private boolean _removed;
    private Date _removedDate;
    private boolean _userHidden;
    private boolean _assigned;

    public CourseRecordClp() {
    }

    public Class<?> getModelClass() {
        return CourseRecord.class;
    }

    public String getModelClassName() {
        return CourseRecord.class.getName();
    }

    public long getPrimaryKey() {
        return _courseRecordId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCourseRecordId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_courseRecordId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
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

    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseRecordLocalServiceUtil.addCourseRecord(this);
        } else {
            CourseRecordLocalServiceUtil.updateCourseRecord(this);
        }
    }

    @Override
    public CourseRecord toEscapedModel() {
        return (CourseRecord) Proxy.newProxyInstance(CourseRecord.class.getClassLoader(),
            new Class[] { CourseRecord.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CourseRecordClp clone = new CourseRecordClp();

        clone.setCourseRecordId(getCourseRecordId());
        clone.setFeedReferenceId(getFeedReferenceId());
        clone.setCourseRecordIri(getCourseRecordIri());
        clone.setUserId(getUserId());
        clone.setSingleSignOnValue(getSingleSignOnValue());
        clone.setCourseIri(getCourseIri());
        clone.setUpdatedDate(getUpdatedDate());
        clone.setCompletionStatus(getCompletionStatus());
        clone.setRemoved(getRemoved());
        clone.setRemovedDate(getRemovedDate());
        clone.setUserHidden(getUserHidden());
        clone.setAssigned(getAssigned());

        return clone;
    }

    public int compareTo(CourseRecord courseRecord) {
        int value = 0;

        value = DateUtil.compareTo(getUpdatedDate(),
                courseRecord.getUpdatedDate());

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

        CourseRecordClp courseRecord = null;

        try {
            courseRecord = (CourseRecordClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courseRecord.getPrimaryKey();

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

        sb.append("{courseRecordId=");
        sb.append(getCourseRecordId());
        sb.append(", feedReferenceId=");
        sb.append(getFeedReferenceId());
        sb.append(", courseRecordIri=");
        sb.append(getCourseRecordIri());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", singleSignOnValue=");
        sb.append(getSingleSignOnValue());
        sb.append(", courseIri=");
        sb.append(getCourseIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", completionStatus=");
        sb.append(getCompletionStatus());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", userHidden=");
        sb.append(getUserHidden());
        sb.append(", assigned=");
        sb.append(getAssigned());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.CourseRecord");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>courseRecordId</column-name><column-value><![CDATA[");
        sb.append(getCourseRecordId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedReferenceId</column-name><column-value><![CDATA[");
        sb.append(getFeedReferenceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseRecordIri</column-name><column-value><![CDATA[");
        sb.append(getCourseRecordIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>singleSignOnValue</column-name><column-value><![CDATA[");
        sb.append(getSingleSignOnValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseIri</column-name><column-value><![CDATA[");
        sb.append(getCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updatedDate</column-name><column-value><![CDATA[");
        sb.append(getUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>completionStatus</column-name><column-value><![CDATA[");
        sb.append(getCompletionStatus());
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
            "<column><column-name>userHidden</column-name><column-value><![CDATA[");
        sb.append(getUserHidden());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>assigned</column-name><column-value><![CDATA[");
        sb.append(getAssigned());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
