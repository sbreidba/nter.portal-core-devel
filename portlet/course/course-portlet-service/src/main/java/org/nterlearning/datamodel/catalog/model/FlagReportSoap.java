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
public class FlagReportSoap implements Serializable {
    private String _uuid;
    private long _flagReportId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private long _classNameId;
    private long _classPK;
    private Date _createDate;
    private String _title;
    private String _content;
    private String _flagReason;
    private String _flagComment;
    private String _moderateAction;
    private String _moderatorComment;
    private int _status;
    private long _statusByUserId;
    private String _statusByUserName;
    private Date _statusDate;

    public FlagReportSoap() {
    }

    public static FlagReportSoap toSoapModel(FlagReport model) {
        FlagReportSoap soapModel = new FlagReportSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setFlagReportId(model.getFlagReportId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setUserId(model.getUserId());
        soapModel.setClassNameId(model.getClassNameId());
        soapModel.setClassPK(model.getClassPK());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setTitle(model.getTitle());
        soapModel.setContent(model.getContent());
        soapModel.setFlagReason(model.getFlagReason());
        soapModel.setFlagComment(model.getFlagComment());
        soapModel.setModerateAction(model.getModerateAction());
        soapModel.setModeratorComment(model.getModeratorComment());
        soapModel.setStatus(model.getStatus());
        soapModel.setStatusByUserId(model.getStatusByUserId());
        soapModel.setStatusByUserName(model.getStatusByUserName());
        soapModel.setStatusDate(model.getStatusDate());

        return soapModel;
    }

    public static FlagReportSoap[] toSoapModels(FlagReport[] models) {
        FlagReportSoap[] soapModels = new FlagReportSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FlagReportSoap[][] toSoapModels(FlagReport[][] models) {
        FlagReportSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FlagReportSoap[models.length][models[0].length];
        } else {
            soapModels = new FlagReportSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FlagReportSoap[] toSoapModels(List<FlagReport> models) {
        List<FlagReportSoap> soapModels = new ArrayList<FlagReportSoap>(models.size());

        for (FlagReport model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FlagReportSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _flagReportId;
    }

    public void setPrimaryKey(long pk) {
        setFlagReportId(pk);
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public long getFlagReportId() {
        return _flagReportId;
    }

    public void setFlagReportId(long flagReportId) {
        _flagReportId = flagReportId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
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

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public String getFlagReason() {
        return _flagReason;
    }

    public void setFlagReason(String flagReason) {
        _flagReason = flagReason;
    }

    public String getFlagComment() {
        return _flagComment;
    }

    public void setFlagComment(String flagComment) {
        _flagComment = flagComment;
    }

    public String getModerateAction() {
        return _moderateAction;
    }

    public void setModerateAction(String moderateAction) {
        _moderateAction = moderateAction;
    }

    public String getModeratorComment() {
        return _moderatorComment;
    }

    public void setModeratorComment(String moderatorComment) {
        _moderatorComment = moderatorComment;
    }

    public int getStatus() {
        return _status;
    }

    public void setStatus(int status) {
        _status = status;
    }

    public long getStatusByUserId() {
        return _statusByUserId;
    }

    public void setStatusByUserId(long statusByUserId) {
        _statusByUserId = statusByUserId;
    }

    public String getStatusByUserName() {
        return _statusByUserName;
    }

    public void setStatusByUserName(String statusByUserName) {
        _statusByUserName = statusByUserName;
    }

    public Date getStatusDate() {
        return _statusDate;
    }

    public void setStatusDate(Date statusDate) {
        _statusDate = statusDate;
    }
}
