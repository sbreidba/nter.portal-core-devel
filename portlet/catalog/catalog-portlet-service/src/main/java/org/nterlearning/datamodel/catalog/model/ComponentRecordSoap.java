package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.ComponentRecordServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.ComponentRecordServiceSoap
 * @generated
 */
public class ComponentRecordSoap implements Serializable {
    private long _componentRecordId;
    private long _courseRecordId;
    private String _componentIri;
    private Date _updatedDate;
    private String _completionStatus;
    private Integer _completionPercent;

    public ComponentRecordSoap() {
    }

    public static ComponentRecordSoap toSoapModel(ComponentRecord model) {
        ComponentRecordSoap soapModel = new ComponentRecordSoap();

        soapModel.setComponentRecordId(model.getComponentRecordId());
        soapModel.setCourseRecordId(model.getCourseRecordId());
        soapModel.setComponentIri(model.getComponentIri());
        soapModel.setUpdatedDate(model.getUpdatedDate());
        soapModel.setCompletionStatus(model.getCompletionStatus());
        soapModel.setCompletionPercent(model.getCompletionPercent());

        return soapModel;
    }

    public static ComponentRecordSoap[] toSoapModels(ComponentRecord[] models) {
        ComponentRecordSoap[] soapModels = new ComponentRecordSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ComponentRecordSoap[][] toSoapModels(
        ComponentRecord[][] models) {
        ComponentRecordSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ComponentRecordSoap[models.length][models[0].length];
        } else {
            soapModels = new ComponentRecordSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ComponentRecordSoap[] toSoapModels(
        List<ComponentRecord> models) {
        List<ComponentRecordSoap> soapModels = new ArrayList<ComponentRecordSoap>(models.size());

        for (ComponentRecord model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ComponentRecordSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _componentRecordId;
    }

    public void setPrimaryKey(long pk) {
        setComponentRecordId(pk);
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
}
