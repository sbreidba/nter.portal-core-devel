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
public class FlagReportStatsSoap implements Serializable {
    private long _flagReportStatsId;
    private long _classNameId;
    private long _classPK;
    private Integer _totalEntries;
    private Integer _totalModerated;
    private Integer _totalApproved;

    public FlagReportStatsSoap() {
    }

    public static FlagReportStatsSoap toSoapModel(FlagReportStats model) {
        FlagReportStatsSoap soapModel = new FlagReportStatsSoap();

        soapModel.setFlagReportStatsId(model.getFlagReportStatsId());
        soapModel.setClassNameId(model.getClassNameId());
        soapModel.setClassPK(model.getClassPK());
        soapModel.setTotalEntries(model.getTotalEntries());
        soapModel.setTotalModerated(model.getTotalModerated());
        soapModel.setTotalApproved(model.getTotalApproved());

        return soapModel;
    }

    public static FlagReportStatsSoap[] toSoapModels(FlagReportStats[] models) {
        FlagReportStatsSoap[] soapModels = new FlagReportStatsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FlagReportStatsSoap[][] toSoapModels(
        FlagReportStats[][] models) {
        FlagReportStatsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FlagReportStatsSoap[models.length][models[0].length];
        } else {
            soapModels = new FlagReportStatsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FlagReportStatsSoap[] toSoapModels(
        List<FlagReportStats> models) {
        List<FlagReportStatsSoap> soapModels = new ArrayList<FlagReportStatsSoap>(models.size());

        for (FlagReportStats model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FlagReportStatsSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _flagReportStatsId;
    }

    public void setPrimaryKey(long pk) {
        setFlagReportStatsId(pk);
    }

    public long getFlagReportStatsId() {
        return _flagReportStatsId;
    }

    public void setFlagReportStatsId(long flagReportStatsId) {
        _flagReportStatsId = flagReportStatsId;
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
}
