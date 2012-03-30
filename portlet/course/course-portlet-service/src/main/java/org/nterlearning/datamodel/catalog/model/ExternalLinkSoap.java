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
public class ExternalLinkSoap implements Serializable {
    private long _linkId;
    private long _courseId;
    private long _componentId;
    private String _linkType;
    private String _linkUrl;

    public ExternalLinkSoap() {
    }

    public static ExternalLinkSoap toSoapModel(ExternalLink model) {
        ExternalLinkSoap soapModel = new ExternalLinkSoap();

        soapModel.setLinkId(model.getLinkId());
        soapModel.setCourseId(model.getCourseId());
        soapModel.setComponentId(model.getComponentId());
        soapModel.setLinkType(model.getLinkType());
        soapModel.setLinkUrl(model.getLinkUrl());

        return soapModel;
    }

    public static ExternalLinkSoap[] toSoapModels(ExternalLink[] models) {
        ExternalLinkSoap[] soapModels = new ExternalLinkSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ExternalLinkSoap[][] toSoapModels(ExternalLink[][] models) {
        ExternalLinkSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ExternalLinkSoap[models.length][models[0].length];
        } else {
            soapModels = new ExternalLinkSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ExternalLinkSoap[] toSoapModels(List<ExternalLink> models) {
        List<ExternalLinkSoap> soapModels = new ArrayList<ExternalLinkSoap>(models.size());

        for (ExternalLink model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ExternalLinkSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _linkId;
    }

    public void setPrimaryKey(long pk) {
        setLinkId(pk);
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
}
