package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.ExternalLinkLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ExternalLinkClp extends BaseModelImpl<ExternalLink>
    implements ExternalLink {
    private long _linkId;
    private long _courseId;
    private long _componentId;
    private String _linkType;
    private String _linkUrl;

    public ExternalLinkClp() {
    }

    public Class<?> getModelClass() {
        return ExternalLink.class;
    }

    public String getModelClassName() {
        return ExternalLink.class.getName();
    }

    public long getPrimaryKey() {
        return _linkId;
    }

    public void setPrimaryKey(long primaryKey) {
        setLinkId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_linkId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getLinkId() {
        return _linkId;
    }

    public void setLinkId(long linkId) {
        _linkId = linkId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public long getComponentId() {
        return _componentId;
    }

    public void setComponentId(long componentId) {
        _componentId = componentId;
    }

    public String getLinkType() {
        return _linkType;
    }

    public void setLinkType(String linkType) {
        _linkType = linkType;
    }

    public String getLinkUrl() {
        return _linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        _linkUrl = linkUrl;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ExternalLinkLocalServiceUtil.addExternalLink(this);
        } else {
            ExternalLinkLocalServiceUtil.updateExternalLink(this);
        }
    }

    @Override
    public ExternalLink toEscapedModel() {
        return (ExternalLink) Proxy.newProxyInstance(ExternalLink.class.getClassLoader(),
            new Class[] { ExternalLink.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ExternalLinkClp clone = new ExternalLinkClp();

        clone.setLinkId(getLinkId());
        clone.setCourseId(getCourseId());
        clone.setComponentId(getComponentId());
        clone.setLinkType(getLinkType());
        clone.setLinkUrl(getLinkUrl());

        return clone;
    }

    public int compareTo(ExternalLink externalLink) {
        long primaryKey = externalLink.getPrimaryKey();

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

        ExternalLinkClp externalLink = null;

        try {
            externalLink = (ExternalLinkClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = externalLink.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{linkId=");
        sb.append(getLinkId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", componentId=");
        sb.append(getComponentId());
        sb.append(", linkType=");
        sb.append(getLinkType());
        sb.append(", linkUrl=");
        sb.append(getLinkUrl());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.ExternalLink");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>linkId</column-name><column-value><![CDATA[");
        sb.append(getLinkId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentId</column-name><column-value><![CDATA[");
        sb.append(getComponentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>linkType</column-name><column-value><![CDATA[");
        sb.append(getLinkType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>linkUrl</column-name><column-value><![CDATA[");
        sb.append(getLinkUrl());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
