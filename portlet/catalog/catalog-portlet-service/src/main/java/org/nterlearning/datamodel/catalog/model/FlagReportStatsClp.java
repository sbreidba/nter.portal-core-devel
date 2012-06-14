package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class FlagReportStatsClp extends BaseModelImpl<FlagReportStats>
    implements FlagReportStats {
    private long _flagReportStatsId;
    private long _classNameId;
    private long _classPK;
    private Integer _totalEntries;
    private Integer _totalModerated;
    private Integer _totalApproved;

    public FlagReportStatsClp() {
    }

    public Class<?> getModelClass() {
        return FlagReportStats.class;
    }

    public String getModelClassName() {
        return FlagReportStats.class.getName();
    }

    public long getPrimaryKey() {
        return _flagReportStatsId;
    }

    public void setPrimaryKey(long primaryKey) {
        setFlagReportStatsId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_flagReportStatsId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getFlagReportStatsId() {
        return _flagReportStatsId;
    }

    public void setFlagReportStatsId(long flagReportStatsId) {
        _flagReportStatsId = flagReportStatsId;
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

    public Integer getTotalEntries() {
        return _totalEntries;
    }

    public void setTotalEntries(Integer totalEntries) {
        _totalEntries = totalEntries;
    }

    public Integer getTotalModerated() {
        return _totalModerated;
    }

    public void setTotalModerated(Integer totalModerated) {
        _totalModerated = totalModerated;
    }

    public Integer getTotalApproved() {
        return _totalApproved;
    }

    public void setTotalApproved(Integer totalApproved) {
        _totalApproved = totalApproved;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            FlagReportStatsLocalServiceUtil.addFlagReportStats(this);
        } else {
            FlagReportStatsLocalServiceUtil.updateFlagReportStats(this);
        }
    }

    @Override
    public FlagReportStats toEscapedModel() {
        return (FlagReportStats) Proxy.newProxyInstance(FlagReportStats.class.getClassLoader(),
            new Class[] { FlagReportStats.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FlagReportStatsClp clone = new FlagReportStatsClp();

        clone.setFlagReportStatsId(getFlagReportStatsId());
        clone.setClassNameId(getClassNameId());
        clone.setClassPK(getClassPK());
        clone.setTotalEntries(getTotalEntries());
        clone.setTotalModerated(getTotalModerated());
        clone.setTotalApproved(getTotalApproved());

        return clone;
    }

    public int compareTo(FlagReportStats flagReportStats) {
        int value = 0;

        if (getClassNameId() < flagReportStats.getClassNameId()) {
            value = -1;
        } else if (getClassNameId() > flagReportStats.getClassNameId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        if (getClassPK() < flagReportStats.getClassPK()) {
            value = -1;
        } else if (getClassPK() > flagReportStats.getClassPK()) {
            value = 1;
        } else {
            value = 0;
        }

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

        FlagReportStatsClp flagReportStats = null;

        try {
            flagReportStats = (FlagReportStatsClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = flagReportStats.getPrimaryKey();

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

        sb.append("{flagReportStatsId=");
        sb.append(getFlagReportStatsId());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append(", totalEntries=");
        sb.append(getTotalEntries());
        sb.append(", totalModerated=");
        sb.append(getTotalModerated());
        sb.append(", totalApproved=");
        sb.append(getTotalApproved());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.FlagReportStats");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>flagReportStatsId</column-name><column-value><![CDATA[");
        sb.append(getFlagReportStatsId());
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
            "<column><column-name>totalEntries</column-name><column-value><![CDATA[");
        sb.append(getTotalEntries());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalModerated</column-name><column-value><![CDATA[");
        sb.append(getTotalModerated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalApproved</column-name><column-value><![CDATA[");
        sb.append(getTotalApproved());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
