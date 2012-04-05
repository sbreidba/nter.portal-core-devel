package org.nterlearning.datamodel.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class FeedSyncHistorySoap implements Serializable {
    private long _syncId;
    private long _feedReferenceId;
    private Date _syncDate;
    private Boolean _success;
    private String _syncMessage;
    private Integer _numberOfEntries;

    public FeedSyncHistorySoap() {
    }

    public static FeedSyncHistorySoap toSoapModel(FeedSyncHistory model) {
        FeedSyncHistorySoap soapModel = new FeedSyncHistorySoap();

        soapModel.setSyncId(model.getSyncId());
        soapModel.setFeedReferenceId(model.getFeedReferenceId());
        soapModel.setSyncDate(model.getSyncDate());
        soapModel.setSuccess(model.getSuccess());
        soapModel.setSyncMessage(model.getSyncMessage());
        soapModel.setNumberOfEntries(model.getNumberOfEntries());

        return soapModel;
    }

    public static FeedSyncHistorySoap[] toSoapModels(FeedSyncHistory[] models) {
        FeedSyncHistorySoap[] soapModels = new FeedSyncHistorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FeedSyncHistorySoap[][] toSoapModels(
        FeedSyncHistory[][] models) {
        FeedSyncHistorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FeedSyncHistorySoap[models.length][models[0].length];
        } else {
            soapModels = new FeedSyncHistorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FeedSyncHistorySoap[] toSoapModels(
        List<FeedSyncHistory> models) {
        List<FeedSyncHistorySoap> soapModels = new ArrayList<FeedSyncHistorySoap>(models.size());

        for (FeedSyncHistory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FeedSyncHistorySoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _syncId;
    }

    public void setPrimaryKey(long pk) {
        setSyncId(pk);
    }

    public long getSyncId() {
        return _syncId;
    }

    public void setSyncId(long syncId) {
        _syncId = syncId;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
    }

    public Date getSyncDate() {
        return _syncDate;
    }

    public void setSyncDate(Date syncDate) {
        _syncDate = syncDate;
    }

    public Boolean getSuccess() {
        return _success;
    }

    public void setSuccess(Boolean success) {
        _success = success;
    }

    public String getSyncMessage() {
        return _syncMessage;
    }

    public void setSyncMessage(String syncMessage) {
        _syncMessage = syncMessage;
    }

    public Integer getNumberOfEntries() {
        return _numberOfEntries;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        _numberOfEntries = numberOfEntries;
    }
}
