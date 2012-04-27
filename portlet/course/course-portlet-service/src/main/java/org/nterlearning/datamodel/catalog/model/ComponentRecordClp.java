package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ComponentRecordClp extends BaseModelImpl<ComponentRecord>
    implements ComponentRecord {
    private long _componentRecordId;
    private long _courseRecordId;
    private String _componentIri;
    private Date _updatedDate;
    private String _completionStatus;
    private Integer _completionPercent;

    public ComponentRecordClp() {
    }

    public Class<?> getModelClass() {
        return ComponentRecord.class;
    }

    public String getModelClassName() {
        return ComponentRecord.class.getName();
    }

    public long getPrimaryKey() {
        return _componentRecordId;
    }

    public void setPrimaryKey(long primaryKey) {
        setComponentRecordId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_componentRecordId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getComponentRecordId() {
        return _componentRecordId;
    }

    public void setComponentRecordId(long componentRecordId) {
        _componentRecordId = componentRecordId;
    }

    public long getCourseRecordId() {
        return _courseRecordId;
    }

    public void setCourseRecordId(long courseRecordId) {
        _courseRecordId = courseRecordId;
    }

    public String getComponentIri() {
        return _componentIri;
    }

    public void setComponentIri(String componentIri) {
        _componentIri = componentIri;
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

    public Integer getCompletionPercent() {
        return _completionPercent;
    }

    public void setCompletionPercent(Integer completionPercent) {
        _completionPercent = completionPercent;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ComponentRecordLocalServiceUtil.addComponentRecord(this);
        } else {
            ComponentRecordLocalServiceUtil.updateComponentRecord(this);
        }
    }

    @Override
    public ComponentRecord toEscapedModel() {
        return (ComponentRecord) Proxy.newProxyInstance(ComponentRecord.class.getClassLoader(),
            new Class[] { ComponentRecord.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ComponentRecordClp clone = new ComponentRecordClp();

        clone.setComponentRecordId(getComponentRecordId());
        clone.setCourseRecordId(getCourseRecordId());
        clone.setComponentIri(getComponentIri());
        clone.setUpdatedDate(getUpdatedDate());
        clone.setCompletionStatus(getCompletionStatus());
        clone.setCompletionPercent(getCompletionPercent());

        return clone;
    }

    public int compareTo(ComponentRecord componentRecord) {
        int value = 0;

        value = DateUtil.compareTo(getUpdatedDate(),
                componentRecord.getUpdatedDate());

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

        ComponentRecordClp componentRecord = null;

        try {
            componentRecord = (ComponentRecordClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = componentRecord.getPrimaryKey();

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

        sb.append("{componentRecordId=");
        sb.append(getComponentRecordId());
        sb.append(", courseRecordId=");
        sb.append(getCourseRecordId());
        sb.append(", componentIri=");
        sb.append(getComponentIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", completionStatus=");
        sb.append(getCompletionStatus());
        sb.append(", completionPercent=");
        sb.append(getCompletionPercent());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.ComponentRecord");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>componentRecordId</column-name><column-value><![CDATA[");
        sb.append(getComponentRecordId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseRecordId</column-name><column-value><![CDATA[");
        sb.append(getCourseRecordId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentIri</column-name><column-value><![CDATA[");
        sb.append(getComponentIri());
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
            "<column><column-name>completionPercent</column-name><column-value><![CDATA[");
        sb.append(getCompletionPercent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
