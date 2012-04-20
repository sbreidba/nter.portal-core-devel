package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class ContributorSoap implements Serializable {
    private long _contributorId;
    private long _courseId;
    private long _componentId;
    private String _role;
    private String _contributorName;
    private String _virtualCardData;

    public ContributorSoap() {
    }

    public static ContributorSoap toSoapModel(Contributor model) {
        ContributorSoap soapModel = new ContributorSoap();

        soapModel.setContributorId(model.getContributorId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setComponentId(model.getComponentId());
        soapModel.setRole(model.getRole());
        soapModel.setContributorName(model.getContributorName());
        soapModel.setVirtualCardData(model.getVirtualCardData());

        return soapModel;
    }

    public static ContributorSoap[] toSoapModels(Contributor[] models) {
        ContributorSoap[] soapModels = new ContributorSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContributorSoap[][] toSoapModels(Contributor[][] models) {
        ContributorSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContributorSoap[models.length][models[0].length];
        } else {
            soapModels = new ContributorSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContributorSoap[] toSoapModels(List<Contributor> models) {
        List<ContributorSoap> soapModels = new ArrayList<ContributorSoap>(models.size());

        for (Contributor model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContributorSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _contributorId;
    }

    public void setPrimaryKey(long pk) {
        setContributorId(pk);
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
}
