package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.Courses_ComponentsServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.Courses_ComponentsServiceSoap
 * @generated
 */
public class Courses_ComponentsSoap implements Serializable {
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

    public Courses_ComponentsSoap() {
    }

    public static Courses_ComponentsSoap toSoapModel(Courses_Components model) {
        Courses_ComponentsSoap soapModel = new Courses_ComponentsSoap();

        soapModel.setCoursesComponentsId(model.getCoursesComponentsId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setCourseIri(model.getCourseIri());
        soapModel.setComponentId(model.getComponentId());
        soapModel.setComponentIri(model.getComponentIri());
        soapModel.setOrderWeight(model.getOrderWeight());
        soapModel.setSectionType(model.getSectionType());
        soapModel.setComponentType(model.getComponentType());
        soapModel.setMimeType(model.getMimeType());
        soapModel.setCoursePaymentRequired(model.getCoursePaymentRequired());
        soapModel.setComponentPaymentRequired(model.getComponentPaymentRequired());

        return soapModel;
    }

    public static Courses_ComponentsSoap[] toSoapModels(
        Courses_Components[] models) {
        Courses_ComponentsSoap[] soapModels = new Courses_ComponentsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static Courses_ComponentsSoap[][] toSoapModels(
        Courses_Components[][] models) {
        Courses_ComponentsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new Courses_ComponentsSoap[models.length][models[0].length];
        } else {
            soapModels = new Courses_ComponentsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static Courses_ComponentsSoap[] toSoapModels(
        List<Courses_Components> models) {
        List<Courses_ComponentsSoap> soapModels = new ArrayList<Courses_ComponentsSoap>(models.size());

        for (Courses_Components model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new Courses_ComponentsSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _coursesComponentsId;
    }

    public void setPrimaryKey(long pk) {
        setCoursesComponentsId(pk);
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
}
