package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class FeedReferenceClp extends BaseModelImpl<FeedReference>
    implements FeedReference {
    private long _feedReferenceId;
    private long _companyId;
    private long _groupId;
    private String _contentProviderId;
    private String _href;
    private String _hrefHash;
    private String _pshb;
    private boolean _pshbSubscribed;
    private String _feedIri;
    private String _feedType;
    private String _feedVersion;
    private double _trustworthyWeight;
    private Date _createDate;
    private boolean _removed;
    private Date _removedDate;
    private String _removedReason;
    private Date _syncDate;
    private boolean _syncSuccess;

    public FeedReferenceClp() {
    }

    public Class<?> getModelClass() {
        return FeedReference.class;
    }

    public String getModelClassName() {
        return FeedReference.class.getName();
    }

    public long getPrimaryKey() {
        return _feedReferenceId;
    }

    public void setPrimaryKey(long primaryKey) {
        setFeedReferenceId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_feedReferenceId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
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

    public String getContentProviderId() {
        return _contentProviderId;
    }

    public void setContentProviderId(String contentProviderId) {
        _contentProviderId = contentProviderId;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getHrefHash() {
        return _hrefHash;
    }

    public void setHrefHash(String hrefHash) {
        _hrefHash = hrefHash;
    }

    public String getPshb() {
        return _pshb;
    }

    public void setPshb(String pshb) {
        _pshb = pshb;
    }

    public boolean getPshbSubscribed() {
        return _pshbSubscribed;
    }

    public boolean isPshbSubscribed() {
        return _pshbSubscribed;
    }

    public void setPshbSubscribed(boolean pshbSubscribed) {
        _pshbSubscribed = pshbSubscribed;
    }

    public String getFeedIri() {
        return _feedIri;
    }

    public void setFeedIri(String feedIri) {
        _feedIri = feedIri;
    }

    public String getFeedType() {
        return _feedType;
    }

    public void setFeedType(String feedType) {
        _feedType = feedType;
    }

    public String getFeedVersion() {
        return _feedVersion;
    }

    public void setFeedVersion(String feedVersion) {
        _feedVersion = feedVersion;
    }

    public double getTrustworthyWeight() {
        return _trustworthyWeight;
    }

    public void setTrustworthyWeight(double trustworthyWeight) {
        _trustworthyWeight = trustworthyWeight;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
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

    public String getRemovedReason() {
        return _removedReason;
    }

    public void setRemovedReason(String removedReason) {
        _removedReason = removedReason;
    }

    public Date getSyncDate() {
        return _syncDate;
    }

    public void setSyncDate(Date syncDate) {
        _syncDate = syncDate;
    }

    public boolean getSyncSuccess() {
        return _syncSuccess;
    }

    public boolean isSyncSuccess() {
        return _syncSuccess;
    }

    public void setSyncSuccess(boolean syncSuccess) {
        _syncSuccess = syncSuccess;
    }

    public java.lang.String getOwnerName() {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getTagSubstring() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            FeedReferenceLocalServiceUtil.addFeedReference(this);
        } else {
            FeedReferenceLocalServiceUtil.updateFeedReference(this);
        }
    }

    @Override
    public FeedReference toEscapedModel() {
        return (FeedReference) Proxy.newProxyInstance(FeedReference.class.getClassLoader(),
            new Class[] { FeedReference.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FeedReferenceClp clone = new FeedReferenceClp();

        clone.setFeedReferenceId(getFeedReferenceId());
        clone.setCompanyId(getCompanyId());
        clone.setGroupId(getGroupId());
        clone.setContentProviderId(getContentProviderId());
        clone.setHref(getHref());
        clone.setHrefHash(getHrefHash());
        clone.setPshb(getPshb());
        clone.setPshbSubscribed(getPshbSubscribed());
        clone.setFeedIri(getFeedIri());
        clone.setFeedType(getFeedType());
        clone.setFeedVersion(getFeedVersion());
        clone.setTrustworthyWeight(getTrustworthyWeight());
        clone.setCreateDate(getCreateDate());
        clone.setRemoved(getRemoved());
        clone.setRemovedDate(getRemovedDate());
        clone.setRemovedReason(getRemovedReason());
        clone.setSyncDate(getSyncDate());
        clone.setSyncSuccess(getSyncSuccess());

        return clone;
    }

    public int compareTo(FeedReference feedReference) {
        long primaryKey = feedReference.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        FeedReferenceClp feedReference = null;

        try {
            feedReference = (FeedReferenceClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = feedReference.getPrimaryKey();

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

        sb.append("{feedReferenceId=");
        sb.append(getFeedReferenceId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", contentProviderId=");
        sb.append(getContentProviderId());
        sb.append(", href=");
        sb.append(getHref());
        sb.append(", hrefHash=");
        sb.append(getHrefHash());
        sb.append(", pshb=");
        sb.append(getPshb());
        sb.append(", pshbSubscribed=");
        sb.append(getPshbSubscribed());
        sb.append(", feedIri=");
        sb.append(getFeedIri());
        sb.append(", feedType=");
        sb.append(getFeedType());
        sb.append(", feedVersion=");
        sb.append(getFeedVersion());
        sb.append(", trustworthyWeight=");
        sb.append(getTrustworthyWeight());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", removedReason=");
        sb.append(getRemovedReason());
        sb.append(", syncDate=");
        sb.append(getSyncDate());
        sb.append(", syncSuccess=");
        sb.append(getSyncSuccess());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.FeedReference");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>feedReferenceId</column-name><column-value><![CDATA[");
        sb.append(getFeedReferenceId());
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
            "<column><column-name>contentProviderId</column-name><column-value><![CDATA[");
        sb.append(getContentProviderId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>href</column-name><column-value><![CDATA[");
        sb.append(getHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hrefHash</column-name><column-value><![CDATA[");
        sb.append(getHrefHash());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pshb</column-name><column-value><![CDATA[");
        sb.append(getPshb());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pshbSubscribed</column-name><column-value><![CDATA[");
        sb.append(getPshbSubscribed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedIri</column-name><column-value><![CDATA[");
        sb.append(getFeedIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedType</column-name><column-value><![CDATA[");
        sb.append(getFeedType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedVersion</column-name><column-value><![CDATA[");
        sb.append(getFeedVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>trustworthyWeight</column-name><column-value><![CDATA[");
        sb.append(getTrustworthyWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
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
            "<column><column-name>removedReason</column-name><column-value><![CDATA[");
        sb.append(getRemovedReason());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>syncDate</column-name><column-value><![CDATA[");
        sb.append(getSyncDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>syncSuccess</column-name><column-value><![CDATA[");
        sb.append(getSyncSuccess());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
