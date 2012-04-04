package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class FlagReportClp extends BaseModelImpl<FlagReport>
    implements FlagReport {
    private String _uuid;
    private long _flagReportId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userUuid;
    private long _classNameId;
    private long _classPK;
    private Date _createDate;
    private String _title;
    private String _content;
    private String _flagReason;
    private String _flagComment;
    private String _moderateAction;
    private String _moderatorComment;
    private int _status;
    private long _statusByUserId;
    private String _statusByUserUuid;
    private String _statusByUserName;
    private Date _statusDate;

    public FlagReportClp() {
    }

    public Class<?> getModelClass() {
        return FlagReport.class;
    }

    public String getModelClassName() {
        return FlagReport.class.getName();
    }

    public long getPrimaryKey() {
        return _flagReportId;
    }

    public void setPrimaryKey(long primaryKey) {
        setFlagReportId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_flagReportId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public long getFlagReportId() {
        return _flagReportId;
    }

    public void setFlagReportId(long flagReportId) {
        _flagReportId = flagReportId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
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

    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    public long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(long classNameId) {
        _classNameId = classNameId;
    }

    public long getClassPK() {
        return _classPK;
    }

    public void setClassPK(long classPK) {
        _classPK = classPK;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public String getFlagReason() {
        return _flagReason;
    }

    public void setFlagReason(String flagReason) {
        _flagReason = flagReason;
    }

    public String getFlagComment() {
        return _flagComment;
    }

    public void setFlagComment(String flagComment) {
        _flagComment = flagComment;
    }

    public String getModerateAction() {
        return _moderateAction;
    }

    public void setModerateAction(String moderateAction) {
        _moderateAction = moderateAction;
    }

    public String getModeratorComment() {
        return _moderatorComment;
    }

    public void setModeratorComment(String moderatorComment) {
        _moderatorComment = moderatorComment;
    }

    public int getStatus() {
        return _status;
    }

    public void setStatus(int status) {
        _status = status;
    }

    public long getStatusByUserId() {
        return _statusByUserId;
    }

    public void setStatusByUserId(long statusByUserId) {
        _statusByUserId = statusByUserId;
    }

    public String getStatusByUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
            _statusByUserUuid);
    }

    public void setStatusByUserUuid(String statusByUserUuid) {
        _statusByUserUuid = statusByUserUuid;
    }

    public String getStatusByUserName() {
        return _statusByUserName;
    }

    public void setStatusByUserName(String statusByUserName) {
        _statusByUserName = statusByUserName;
    }

    public Date getStatusDate() {
        return _statusDate;
    }

    public void setStatusDate(Date statusDate) {
        _statusDate = statusDate;
    }

    public boolean isModerated() {
        throw new UnsupportedOperationException();
    }

    public boolean isAssigned() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated {@link #isApproved}
     */
    public boolean getApproved() {
        return isApproved();
    }

    public boolean isApproved() {
        if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDraft() {
        if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExpired() {
        if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPending() {
        if (getStatus() == WorkflowConstants.STATUS_PENDING) {
            return true;
        } else {
            return false;
        }
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            FlagReportLocalServiceUtil.addFlagReport(this);
        } else {
            FlagReportLocalServiceUtil.updateFlagReport(this);
        }
    }

    @Override
    public FlagReport toEscapedModel() {
        return (FlagReport) Proxy.newProxyInstance(FlagReport.class.getClassLoader(),
            new Class[] { FlagReport.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FlagReportClp clone = new FlagReportClp();

        clone.setUuid(getUuid());
        clone.setFlagReportId(getFlagReportId());
        clone.setGroupId(getGroupId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setClassNameId(getClassNameId());
        clone.setClassPK(getClassPK());
        clone.setCreateDate(getCreateDate());
        clone.setTitle(getTitle());
        clone.setContent(getContent());
        clone.setFlagReason(getFlagReason());
        clone.setFlagComment(getFlagComment());
        clone.setModerateAction(getModerateAction());
        clone.setModeratorComment(getModeratorComment());
        clone.setStatus(getStatus());
        clone.setStatusByUserId(getStatusByUserId());
        clone.setStatusByUserName(getStatusByUserName());
        clone.setStatusDate(getStatusDate());

        return clone;
    }

    public int compareTo(FlagReport flagReport) {
        int value = 0;

        if (getFlagReportId() < flagReport.getFlagReportId()) {
            value = -1;
        } else if (getFlagReportId() > flagReport.getFlagReportId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getCreateDate(), flagReport.getCreateDate());

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

        FlagReportClp flagReport = null;

        try {
            flagReport = (FlagReportClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = flagReport.getPrimaryKey();

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
        StringBundler sb = new StringBundler(37);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", flagReportId=");
        sb.append(getFlagReportId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", flagReason=");
        sb.append(getFlagReason());
        sb.append(", flagComment=");
        sb.append(getFlagComment());
        sb.append(", moderateAction=");
        sb.append(getModerateAction());
        sb.append(", moderatorComment=");
        sb.append(getModeratorComment());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", statusByUserId=");
        sb.append(getStatusByUserId());
        sb.append(", statusByUserName=");
        sb.append(getStatusByUserName());
        sb.append(", statusDate=");
        sb.append(getStatusDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.FlagReport");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagReportId</column-name><column-value><![CDATA[");
        sb.append(getFlagReportId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classNameId</column-name><column-value><![CDATA[");
        sb.append(getClassNameId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classPK</column-name><column-value><![CDATA[");
        sb.append(getClassPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagReason</column-name><column-value><![CDATA[");
        sb.append(getFlagReason());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagComment</column-name><column-value><![CDATA[");
        sb.append(getFlagComment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moderateAction</column-name><column-value><![CDATA[");
        sb.append(getModerateAction());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moderatorComment</column-name><column-value><![CDATA[");
        sb.append(getModeratorComment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
        sb.append(getStatusByUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
        sb.append(getStatusByUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusDate</column-name><column-value><![CDATA[");
        sb.append(getStatusDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
