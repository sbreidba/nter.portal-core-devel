package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.nterlearning.datamodel.catalog.service.http.FeedReferenceServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.nterlearning.datamodel.catalog.service.http.FeedReferenceServiceSoap
 * @generated
 */
public class FeedReferenceSoap implements Serializable {
    private long _feedReferenceId;
    private long _companyId;
    private long _groupId;
    private String _contentProviderId;
    private String _href;
    private String _hrefHash;
    private String _pshb;
    private boolean _pshbSubscribed;
    private String _feedIri;
    private String _feedType;
    private String _feedVersion;
    private double _trustworthyWeight;
    private Date _createDate;
    private boolean _removed;
    private Date _removedDate;
    private String _removedReason;
    private Date _syncDate;
    private boolean _syncSuccess;

    public FeedReferenceSoap() {
    }

    public static FeedReferenceSoap toSoapModel(FeedReference model) {
        FeedReferenceSoap soapModel = new FeedReferenceSoap();

        soapModel.setFeedReferenceId(model.getFeedReferenceId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setContentProviderId(model.getContentProviderId());
        soapModel.setHref(model.getHref());
        soapModel.setHrefHash(model.getHrefHash());
        soapModel.setPshb(model.getPshb());
        soapModel.setPshbSubscribed(model.getPshbSubscribed());
        soapModel.setFeedIri(model.getFeedIri());
        soapModel.setFeedType(model.getFeedType());
        soapModel.setFeedVersion(model.getFeedVersion());
        soapModel.setTrustworthyWeight(model.getTrustworthyWeight());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setRemoved(model.getRemoved());
        soapModel.setRemovedDate(model.getRemovedDate());
        soapModel.setRemovedReason(model.getRemovedReason());
        soapModel.setSyncDate(model.getSyncDate());
        soapModel.setSyncSuccess(model.getSyncSuccess());

        return soapModel;
    }

    public static FeedReferenceSoap[] toSoapModels(FeedReference[] models) {
        FeedReferenceSoap[] soapModels = new FeedReferenceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FeedReferenceSoap[][] toSoapModels(FeedReference[][] models) {
        FeedReferenceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FeedReferenceSoap[models.length][models[0].length];
        } else {
            soapModels = new FeedReferenceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FeedReferenceSoap[] toSoapModels(List<FeedReference> models) {
        List<FeedReferenceSoap> soapModels = new ArrayList<FeedReferenceSoap>(models.size());

        for (FeedReference model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FeedReferenceSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _feedReferenceId;
    }

    public void setPrimaryKey(long pk) {
        setFeedReferenceId(pk);
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
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

    public String getContentProviderId() {
        return _contentProviderId;
    }

    public void setContentProviderId(String contentProviderId) {
        _contentProviderId = contentProviderId;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getHrefHash() {
        return _hrefHash;
    }

    public void setHrefHash(String hrefHash) {
        _hrefHash = hrefHash;
    }

    public String getPshb() {
        return _pshb;
    }

    public void setPshb(String pshb) {
        _pshb = pshb;
    }

    public boolean getPshbSubscribed() {
        return _pshbSubscribed;
    }

    public boolean isPshbSubscribed() {
        return _pshbSubscribed;
    }

    public void setPshbSubscribed(boolean pshbSubscribed) {
        _pshbSubscribed = pshbSubscribed;
    }

    public String getFeedIri() {
        return _feedIri;
    }

    public void setFeedIri(String feedIri) {
        _feedIri = feedIri;
    }

    public String getFeedType() {
        return _feedType;
    }

    public void setFeedType(String feedType) {
        _feedType = feedType;
    }

    public String getFeedVersion() {
        return _feedVersion;
    }

    public void setFeedVersion(String feedVersion) {
        _feedVersion = feedVersion;
    }

    public double getTrustworthyWeight() {
        return _trustworthyWeight;
    }

    public void setTrustworthyWeight(double trustworthyWeight) {
        _trustworthyWeight = trustworthyWeight;
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

    public String getRemovedReason() {
        return _removedReason;
    }

    public void setRemovedReason(String removedReason) {
        _removedReason = removedReason;
    }

    public Date getSyncDate() {
        return _syncDate;
    }

    public void setSyncDate(Date syncDate) {
        _syncDate = syncDate;
    }

    public boolean getSyncSuccess() {
        return _syncSuccess;
    }

    public boolean isSyncSuccess() {
        return _syncSuccess;
    }

    public void setSyncSuccess(boolean syncSuccess) {
        _syncSuccess = syncSuccess;
    }
}
