package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class Courses_ComponentsClp extends BaseModelImpl<Courses_Components>
    implements Courses_Components {
    private long _coursesComponentsId;
    private long _courseId;
    private String _courseIri;
    private long _componentId;
    private String _componentIri;
    private double _orderWeight;
    private String _sectionType;
    private String _componentType;
    private String _mimeType;
    private boolean _coursePaymentRequired;
    private boolean _componentPaymentRequired;

    public Courses_ComponentsClp() {
    }

    public Class<?> getModelClass() {
        return Courses_Components.class;
    }

    public String getModelClassName() {
        return Courses_Components.class.getName();
    }

    public long getPrimaryKey() {
        return _coursesComponentsId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCoursesComponentsId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_coursesComponentsId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getCoursesComponentsId() {
        return _coursesComponentsId;
    }

    public void setCoursesComponentsId(long coursesComponentsId) {
        _coursesComponentsId = coursesComponentsId;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _courseId = courseId;
    }

    public String getCourseIri() {
        return _courseIri;
    }

    public void setCourseIri(String courseIri) {
        _courseIri = courseIri;
    }

    public long getComponentId() {
        return _componentId;
    }

    public void setComponentId(long componentId) {
        _componentId = componentId;
    }

    public String getComponentIri() {
        return _componentIri;
    }

    public void setComponentIri(String componentIri) {
        _componentIri = componentIri;
    }

    public double getOrderWeight() {
        return _orderWeight;
    }

    public void setOrderWeight(double orderWeight) {
        _orderWeight = orderWeight;
    }

    public String getSectionType() {
        return _sectionType;
    }

    public void setSectionType(String sectionType) {
        _sectionType = sectionType;
    }

    public String getComponentType() {
        return _componentType;
    }

    public void setComponentType(String componentType) {
        _componentType = componentType;
    }

    public String getMimeType() {
        return _mimeType;
    }

    public void setMimeType(String mimeType) {
        _mimeType = mimeType;
    }

    public boolean getCoursePaymentRequired() {
        return _coursePaymentRequired;
    }

    public boolean isCoursePaymentRequired() {
        return _coursePaymentRequired;
    }

    public void setCoursePaymentRequired(boolean coursePaymentRequired) {
        _coursePaymentRequired = coursePaymentRequired;
    }

    public boolean getComponentPaymentRequired() {
        return _componentPaymentRequired;
    }

    public boolean isComponentPaymentRequired() {
        return _componentPaymentRequired;
    }

    public void setComponentPaymentRequired(boolean componentPaymentRequired) {
        _componentPaymentRequired = componentPaymentRequired;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            Courses_ComponentsLocalServiceUtil.addCourses_Components(this);
        } else {
            Courses_ComponentsLocalServiceUtil.updateCourses_Components(this);
        }
    }

    @Override
    public Courses_Components toEscapedModel() {
        return (Courses_Components) Proxy.newProxyInstance(Courses_Components.class.getClassLoader(),
            new Class[] { Courses_Components.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Courses_ComponentsClp clone = new Courses_ComponentsClp();

        clone.setCoursesComponentsId(getCoursesComponentsId());
        clone.setCourseId(getCourseId());
        clone.setCourseIri(getCourseIri());
        clone.setComponentId(getComponentId());
        clone.setComponentIri(getComponentIri());
        clone.setOrderWeight(getOrderWeight());
        clone.setSectionType(getSectionType());
        clone.setComponentType(getComponentType());
        clone.setMimeType(getMimeType());
        clone.setCoursePaymentRequired(getCoursePaymentRequired());
        clone.setComponentPaymentRequired(getComponentPaymentRequired());

        return clone;
    }

    public int compareTo(Courses_Components courses_Components) {
        int value = 0;

        if (getOrderWeight() < courses_Components.getOrderWeight()) {
            value = -1;
        } else if (getOrderWeight() > courses_Components.getOrderWeight()) {
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

        Courses_ComponentsClp courses_Components = null;

        try {
            courses_Components = (Courses_ComponentsClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courses_Components.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{coursesComponentsId=");
        sb.append(getCoursesComponentsId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", courseIri=");
        sb.append(getCourseIri());
        sb.append(", componentId=");
        sb.append(getComponentId());
        sb.append(", componentIri=");
        sb.append(getComponentIri());
        sb.append(", orderWeight=");
        sb.append(getOrderWeight());
        sb.append(", sectionType=");
        sb.append(getSectionType());
        sb.append(", componentType=");
        sb.append(getComponentType());
        sb.append(", mimeType=");
        sb.append(getMimeType());
        sb.append(", coursePaymentRequired=");
        sb.append(getCoursePaymentRequired());
        sb.append(", componentPaymentRequired=");
        sb.append(getComponentPaymentRequired());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.Courses_Components");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>coursesComponentsId</column-name><column-value><![CDATA[");
        sb.append(getCoursesComponentsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseIri</column-name><column-value><![CDATA[");
        sb.append(getCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentId</column-name><column-value><![CDATA[");
        sb.append(getComponentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentIri</column-name><column-value><![CDATA[");
        sb.append(getComponentIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderWeight</column-name><column-value><![CDATA[");
        sb.append(getOrderWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sectionType</column-name><column-value><![CDATA[");
        sb.append(getSectionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentType</column-name><column-value><![CDATA[");
        sb.append(getComponentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mimeType</column-name><column-value><![CDATA[");
        sb.append(getMimeType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>coursePaymentRequired</column-name><column-value><![CDATA[");
        sb.append(getCoursePaymentRequired());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentPaymentRequired</column-name><column-value><![CDATA[");
        sb.append(getComponentPaymentRequired());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
