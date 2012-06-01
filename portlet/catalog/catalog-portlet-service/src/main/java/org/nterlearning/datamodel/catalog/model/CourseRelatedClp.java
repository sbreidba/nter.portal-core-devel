package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.CourseRelatedLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class CourseRelatedClp extends BaseModelImpl<CourseRelated>
    implements CourseRelated {
    private long _courseRelatedId;
    private long _courseId;
    private long _relatedCourseId;
    private String _relatedCourseIri;
    private String _relationshipType;

    public CourseRelatedClp() {
    }

    public Class<?> getModelClass() {
        return CourseRelated.class;
    }

    public String getModelClassName() {
        return CourseRelated.class.getName();
    }

    public long getPrimaryKey() {
        return _courseRelatedId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCourseRelatedId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_courseRelatedId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getCourseRelatedId() {
        return _courseRelatedId;
    }

    public void setCourseRelatedId(long courseRelatedId) {
        _courseRelatedId = courseRelatedId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public long getRelatedCourseId() {
        return _relatedCourseId;
    }

    public void setRelatedCourseId(long relatedCourseId) {
        _relatedCourseId = relatedCourseId;
    }

    public String getRelatedCourseIri() {
        return _relatedCourseIri;
    }

    public void setRelatedCourseIri(String relatedCourseIri) {
        _relatedCourseIri = relatedCourseIri;
    }

    public String getRelationshipType() {
        return _relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        _relationshipType = relationshipType;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseRelatedLocalServiceUtil.addCourseRelated(this);
        } else {
            CourseRelatedLocalServiceUtil.updateCourseRelated(this);
        }
    }

    @Override
    public CourseRelated toEscapedModel() {
        return (CourseRelated) Proxy.newProxyInstance(CourseRelated.class.getClassLoader(),
            new Class[] { CourseRelated.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CourseRelatedClp clone = new CourseRelatedClp();

        clone.setCourseRelatedId(getCourseRelatedId());
        clone.setCourseId(getCourseId());
        clone.setRelatedCourseId(getRelatedCourseId());
        clone.setRelatedCourseIri(getRelatedCourseIri());
        clone.setRelationshipType(getRelationshipType());

        return clone;
    }

    public int compareTo(CourseRelated courseRelated) {
        long primaryKey = courseRelated.getPrimaryKey();

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

        CourseRelatedClp courseRelated = null;

        try {
            courseRelated = (CourseRelatedClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courseRelated.getPrimaryKey();

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

        sb.append("{courseRelatedId=");
        sb.append(getCourseRelatedId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", relatedCourseId=");
        sb.append(getRelatedCourseId());
        sb.append(", relatedCourseIri=");
        sb.append(getRelatedCourseIri());
        sb.append(", relationshipType=");
        sb.append(getRelationshipType());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.CourseRelated");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>courseRelatedId</column-name><column-value><![CDATA[");
        sb.append(getCourseRelatedId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedCourseId</column-name><column-value><![CDATA[");
        sb.append(getRelatedCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedCourseIri</column-name><column-value><![CDATA[");
        sb.append(getRelatedCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipType</column-name><column-value><![CDATA[");
        sb.append(getRelationshipType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
