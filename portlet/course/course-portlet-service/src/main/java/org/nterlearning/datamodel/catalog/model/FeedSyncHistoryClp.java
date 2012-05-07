package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class FeedSyncHistoryClp extends BaseModelImpl<FeedSyncHistory>
    implements FeedSyncHistory {
    private long _syncId;
    private long _feedReferenceId;
    private Date _syncDate;
    private Boolean _success;
    private String _syncMessage;
    private Integer _numberOfEntries;

    public FeedSyncHistoryClp() {
    }

    public Class<?> getModelClass() {
        return FeedSyncHistory.class;
    }

    public String getModelClassName() {
        return FeedSyncHistory.class.getName();
    }

    public long getPrimaryKey() {
        return _syncId;
    }

    public void setPrimaryKey(long primaryKey) {
        setSyncId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_syncId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getSyncId() {
        return _syncId;
    }

    public void setSyncId(long syncId) {
        _syncId = syncId;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
    }

    public Date getSyncDate() {
        return _syncDate;
    }

    public void setSyncDate(Date syncDate) {
        _syncDate = syncDate;
    }

    public Boolean getSuccess() {
        return _success;
    }

    public void setSuccess(Boolean success) {
        _success = success;
    }

    public String getSyncMessage() {
        return _syncMessage;
    }

    public void setSyncMessage(String syncMessage) {
        _syncMessage = syncMessage;
    }

    public Integer getNumberOfEntries() {
        return _numberOfEntries;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        _numberOfEntries = numberOfEntries;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            FeedSyncHistoryLocalServiceUtil.addFeedSyncHistory(this);
        } else {
            FeedSyncHistoryLocalServiceUtil.updateFeedSyncHistory(this);
        }
    }

    @Override
    public FeedSyncHistory toEscapedModel() {
        return (FeedSyncHistory) Proxy.newProxyInstance(FeedSyncHistory.class.getClassLoader(),
            new Class[] { FeedSyncHistory.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FeedSyncHistoryClp clone = new FeedSyncHistoryClp();

        clone.setSyncId(getSyncId());
        clone.setFeedReferenceId(getFeedReferenceId());
        clone.setSyncDate(getSyncDate());
        clone.setSuccess(getSuccess());
        clone.setSyncMessage(getSyncMessage());
        clone.setNumberOfEntries(getNumberOfEntries());

        return clone;
    }

    public int compareTo(FeedSyncHistory feedSyncHistory) {
        int value = 0;

        if (getSyncId() < feedSyncHistory.getSyncId()) {
            value = -1;
        } else if (getSyncId() > feedSyncHistory.getSyncId()) {
            value = 1;
        } else {
            value = 0;
        }

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

        FeedSyncHistoryClp feedSyncHistory = null;

        try {
            feedSyncHistory = (FeedSyncHistoryClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = feedSyncHistory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{syncId=");
        sb.append(getSyncId());
        sb.append(", feedReferenceId=");
        sb.append(getFeedReferenceId());
        sb.append(", syncDate=");
        sb.append(getSyncDate());
        sb.append(", success=");
        sb.append(getSuccess());
        sb.append(", syncMessage=");
        sb.append(getSyncMessage());
        sb.append(", numberOfEntries=");
        sb.append(getNumberOfEntries());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.FeedSyncHistory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>syncId</column-name><column-value><![CDATA[");
        sb.append(getSyncId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedReferenceId</column-name><column-value><![CDATA[");
        sb.append(getFeedReferenceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>syncDate</column-name><column-value><![CDATA[");
        sb.append(getSyncDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>success</column-name><column-value><![CDATA[");
        sb.append(getSuccess());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>syncMessage</column-name><column-value><![CDATA[");
        sb.append(getSyncMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numberOfEntries</column-name><column-value><![CDATA[");
        sb.append(getNumberOfEntries());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
