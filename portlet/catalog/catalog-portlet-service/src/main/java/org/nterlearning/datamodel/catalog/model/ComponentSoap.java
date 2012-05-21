package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.ComponentServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.ComponentServiceSoap
 * @generated
 */
public class ComponentSoap implements Serializable {
    private long _componentId;
    private long _companyId;
    private long _groupId;
    private long _feedReferenceId;
    private String _componentIri;
    private Date _updatedDate;
    private String _language;
    private String _href;
    private String _fullTextHref;
    private String _title;
    private String _description;
    private String _copyright;
    private int _displayWidth;
    private int _displayHeight;
    private Date _createDate;
    private boolean _removed;
    private Date _removedDate;
    private String _version;
    private Date _versionDate;
    private double _price;
    private String _priceUnit;
    private String _priceTerms;
    private String _priceExpiration;

    public ComponentSoap() {
    }

    public static ComponentSoap toSoapModel(Component model) {
        ComponentSoap soapModel = new ComponentSoap();

        soapModel.setComponentId(model.getComponentId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setFeedReferenceId(model.getFeedReferenceId());
        soapModel.setComponentIri(model.getComponentIri());
        soapModel.setUpdatedDate(model.getUpdatedDate());
        soapModel.setLanguage(model.getLanguage());
        soapModel.setHref(model.getHref());
        soapModel.setFullTextHref(model.getFullTextHref());
        soapModel.setTitle(model.getTitle());
        soapModel.setDescription(model.getDescription());
        soapModel.setCopyright(model.getCopyright());
        soapModel.setDisplayWidth(model.getDisplayWidth());
        soapModel.setDisplayHeight(model.getDisplayHeight());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setRemoved(model.getRemoved());
        soapModel.setRemovedDate(model.getRemovedDate());
        soapModel.setVersion(model.getVersion());
        soapModel.setVersionDate(model.getVersionDate());
        soapModel.setPrice(model.getPrice());
        soapModel.setPriceUnit(model.getPriceUnit());
        soapModel.setPriceTerms(model.getPriceTerms());
        soapModel.setPriceExpiration(model.getPriceExpiration());

        return soapModel;
    }

    public static ComponentSoap[] toSoapModels(Component[] models) {
        ComponentSoap[] soapModels = new ComponentSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ComponentSoap[][] toSoapModels(Component[][] models) {
        ComponentSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ComponentSoap[models.length][models[0].length];
        } else {
            soapModels = new ComponentSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ComponentSoap[] toSoapModels(List<Component> models) {
        List<ComponentSoap> soapModels = new ArrayList<ComponentSoap>(models.size());

        for (Component model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ComponentSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _componentId;
    }

    public void setPrimaryKey(long pk) {
        setComponentId(pk);
    }

    public long getComponentId() {
        return _componentId;
    }

    public void setComponentId(long componentId) {
        _componentId = componentId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
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

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getFullTextHref() {
        return _fullTextHref;
    }

    public void setFullTextHref(String fullTextHref) {
        _fullTextHref = fullTextHref;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getCopyright() {
        return _copyright;
    }

    public void setCopyright(String copyright) {
        _copyright = copyright;
    }

    public int getDisplayWidth() {
        return _displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        _displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return _displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        _displayHeight = displayHeight;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public boolean getRemoved() {
        return _removed;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public void setRemoved(boolean removed) {
        _removed = removed;
    }

    public Date getRemovedDate() {
        return _removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        _removedDate = removedDate;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public Date getVersionDate() {
        return _versionDate;
    }

    public void setVersionDate(Date versionDate) {
        _versionDate = versionDate;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public String getPriceUnit() {
        return _priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        _priceUnit = priceUnit;
    }

    public String getPriceTerms() {
        return _priceTerms;
    }

    public void setPriceTerms(String priceTerms) {
        _priceTerms = priceTerms;
    }

    public String getPriceExpiration() {
        return _priceExpiration;
    }

    public void setPriceExpiration(String priceExpiration) {
        _priceExpiration = priceExpiration;
    }
}
