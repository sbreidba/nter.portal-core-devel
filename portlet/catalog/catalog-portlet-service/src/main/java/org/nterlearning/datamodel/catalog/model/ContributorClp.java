package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ContributorClp extends BaseModelImpl<Contributor>
    implements Contributor {
    private long _contributorId;
    private long _courseId;
    private long _componentId;
    private String _role;
    private String _contributorName;
    private String _virtualCardData;

    public ContributorClp() {
    }

    public Class<?> getModelClass() {
        return Contributor.class;
    }

    public String getModelClassName() {
        return Contributor.class.getName();
    }

    public long getPrimaryKey() {
        return _contributorId;
    }

    public void setPrimaryKey(long primaryKey) {
        setContributorId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_contributorId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getContributorId() {
        return _contributorId;
    }

    public void setContributorId(long contributorId) {
        _contributorId = contributorId;
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

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }

    public String getContributorName() {
        return _contributorName;
    }

    public void setContributorName(String contributorName) {
        _contributorName = contributorName;
    }

    public String getVirtualCardData() {
        return _virtualCardData;
    }

    public void setVirtualCardData(String virtualCardData) {
        _virtualCardData = virtualCardData;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContributorLocalServiceUtil.addContributor(this);
        } else {
            ContributorLocalServiceUtil.updateContributor(this);
        }
    }

    @Override
    public Contributor toEscapedModel() {
        return (Contributor) Proxy.newProxyInstance(Contributor.class.getClassLoader(),
            new Class[] { Contributor.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContributorClp clone = new ContributorClp();

        clone.setContributorId(getContributorId());
        clone.setCourseId(getCourseId());
        clone.setComponentId(getComponentId());
        clone.setRole(getRole());
        clone.setContributorName(getContributorName());
        clone.setVirtualCardData(getVirtualCardData());

        return clone;
    }

    public int compareTo(Contributor contributor) {
        long primaryKey = contributor.getPrimaryKey();

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

        ContributorClp contributor = null;

        try {
            contributor = (ContributorClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contributor.getPrimaryKey();

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

        sb.append("{contributorId=");
        sb.append(getContributorId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", componentId=");
        sb.append(getComponentId());
        sb.append(", role=");
        sb.append(getRole());
        sb.append(", contributorName=");
        sb.append(getContributorName());
        sb.append(", virtualCardData=");
        sb.append(getVirtualCardData());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.Contributor");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>contributorId</column-name><column-value><![CDATA[");
        sb.append(getContributorId());
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
            "<column><column-name>role</column-name><column-value><![CDATA[");
        sb.append(getRole());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contributorName</column-name><column-value><![CDATA[");
        sb.append(getContributorName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>virtualCardData</column-name><column-value><![CDATA[");
        sb.append(getVirtualCardData());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
