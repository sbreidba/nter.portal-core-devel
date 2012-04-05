package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FlagReport}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FlagReport
 * @generated
 */
public class FlagReportWrapper implements FlagReport, ModelWrapper<FlagReport> {
    private FlagReport _flagReport;

    public FlagReportWrapper(FlagReport flagReport) {
        _flagReport = flagReport;
    }

    public Class<?> getModelClass() {
        return FlagReport.class;
    }

    public String getModelClassName() {
        return FlagReport.class.getName();
    }

    /**
    * Returns the primary key of this flag report.
    *
    * @return the primary key of this flag report
    */
    public long getPrimaryKey() {
        return _flagReport.getPrimaryKey();
    }

    /**
    * Sets the primary key of this flag report.
    *
    * @param primaryKey the primary key of this flag report
    */
    public void setPrimaryKey(long primaryKey) {
        _flagReport.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this flag report.
    *
    * @return the uuid of this flag report
    */
    public java.lang.String getUuid() {
        return _flagReport.getUuid();
    }

    /**
    * Sets the uuid of this flag report.
    *
    * @param uuid the uuid of this flag report
    */
    public void setUuid(java.lang.String uuid) {
        _flagReport.setUuid(uuid);
    }

    /**
    * Returns the flag report ID of this flag report.
    *
    * @return the flag report ID of this flag report
    */
    public long getFlagReportId() {
        return _flagReport.getFlagReportId();
    }

    /**
    * Sets the flag report ID of this flag report.
    *
    * @param flagReportId the flag report ID of this flag report
    */
    public void setFlagReportId(long flagReportId) {
        _flagReport.setFlagReportId(flagReportId);
    }

    /**
    * Returns the group ID of this flag report.
    *
    * @return the group ID of this flag report
    */
    public long getGroupId() {
        return _flagReport.getGroupId();
    }

    /**
    * Sets the group ID of this flag report.
    *
    * @param groupId the group ID of this flag report
    */
    public void setGroupId(long groupId) {
        _flagReport.setGroupId(groupId);
    }

    /**
    * Returns the company ID of this flag report.
    *
    * @return the company ID of this flag report
    */
    public long getCompanyId() {
        return _flagReport.getCompanyId();
    }

    /**
    * Sets the company ID of this flag report.
    *
    * @param companyId the company ID of this flag report
    */
    public void setCompanyId(long companyId) {
        _flagReport.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this flag report.
    *
    * @return the user ID of this flag report
    */
    public long getUserId() {
        return _flagReport.getUserId();
    }

    /**
    * Sets the user ID of this flag report.
    *
    * @param userId the user ID of this flag report
    */
    public void setUserId(long userId) {
        _flagReport.setUserId(userId);
    }

    /**
    * Returns the user uuid of this flag report.
    *
    * @return the user uuid of this flag report
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReport.getUserUuid();
    }

    /**
    * Sets the user uuid of this flag report.
    *
    * @param userUuid the user uuid of this flag report
    */
    public void setUserUuid(java.lang.String userUuid) {
        _flagReport.setUserUuid(userUuid);
    }

    /**
    * Returns the fully qualified class name of this flag report.
    *
    * @return the fully qualified class name of this flag report
    */
    public java.lang.String getClassName() {
        return _flagReport.getClassName();
    }

    /**
    * Returns the class name ID of this flag report.
    *
    * @return the class name ID of this flag report
    */
    public long getClassNameId() {
        return _flagReport.getClassNameId();
    }

    /**
    * Sets the class name ID of this flag report.
    *
    * @param classNameId the class name ID of this flag report
    */
    public void setClassNameId(long classNameId) {
        _flagReport.setClassNameId(classNameId);
    }

    /**
    * Returns the class p k of this flag report.
    *
    * @return the class p k of this flag report
    */
    public long getClassPK() {
        return _flagReport.getClassPK();
    }

    /**
    * Sets the class p k of this flag report.
    *
    * @param classPK the class p k of this flag report
    */
    public void setClassPK(long classPK) {
        _flagReport.setClassPK(classPK);
    }

    /**
    * Returns the create date of this flag report.
    *
    * @return the create date of this flag report
    */
    public java.util.Date getCreateDate() {
        return _flagReport.getCreateDate();
    }

    /**
    * Sets the create date of this flag report.
    *
    * @param createDate the create date of this flag report
    */
    public void setCreateDate(java.util.Date createDate) {
        _flagReport.setCreateDate(createDate);
    }

    /**
    * Returns the title of this flag report.
    *
    * @return the title of this flag report
    */
    public java.lang.String getTitle() {
        return _flagReport.getTitle();
    }

    /**
    * Sets the title of this flag report.
    *
    * @param title the title of this flag report
    */
    public void setTitle(java.lang.String title) {
        _flagReport.setTitle(title);
    }

    /**
    * Returns the content of this flag report.
    *
    * @return the content of this flag report
    */
    public java.lang.String getContent() {
        return _flagReport.getContent();
    }

    /**
    * Sets the content of this flag report.
    *
    * @param content the content of this flag report
    */
    public void setContent(java.lang.String content) {
        _flagReport.setContent(content);
    }

    /**
    * Returns the flag reason of this flag report.
    *
    * @return the flag reason of this flag report
    */
    public java.lang.String getFlagReason() {
        return _flagReport.getFlagReason();
    }

    /**
    * Sets the flag reason of this flag report.
    *
    * @param flagReason the flag reason of this flag report
    */
    public void setFlagReason(java.lang.String flagReason) {
        _flagReport.setFlagReason(flagReason);
    }

    /**
    * Returns the flag comment of this flag report.
    *
    * @return the flag comment of this flag report
    */
    public java.lang.String getFlagComment() {
        return _flagReport.getFlagComment();
    }

    /**
    * Sets the flag comment of this flag report.
    *
    * @param flagComment the flag comment of this flag report
    */
    public void setFlagComment(java.lang.String flagComment) {
        _flagReport.setFlagComment(flagComment);
    }

    /**
    * Returns the moderate action of this flag report.
    *
    * @return the moderate action of this flag report
    */
    public java.lang.String getModerateAction() {
        return _flagReport.getModerateAction();
    }

    /**
    * Sets the moderate action of this flag report.
    *
    * @param moderateAction the moderate action of this flag report
    */
    public void setModerateAction(java.lang.String moderateAction) {
        _flagReport.setModerateAction(moderateAction);
    }

    /**
    * Returns the moderator comment of this flag report.
    *
    * @return the moderator comment of this flag report
    */
    public java.lang.String getModeratorComment() {
        return _flagReport.getModeratorComment();
    }

    /**
    * Sets the moderator comment of this flag report.
    *
    * @param moderatorComment the moderator comment of this flag report
    */
    public void setModeratorComment(java.lang.String moderatorComment) {
        _flagReport.setModeratorComment(moderatorComment);
    }

    /**
    * Returns the status of this flag report.
    *
    * @return the status of this flag report
    */
    public int getStatus() {
        return _flagReport.getStatus();
    }

    /**
    * Sets the status of this flag report.
    *
    * @param status the status of this flag report
    */
    public void setStatus(int status) {
        _flagReport.setStatus(status);
    }

    /**
    * Returns the status by user ID of this flag report.
    *
    * @return the status by user ID of this flag report
    */
    public long getStatusByUserId() {
        return _flagReport.getStatusByUserId();
    }

    /**
    * Sets the status by user ID of this flag report.
    *
    * @param statusByUserId the status by user ID of this flag report
    */
    public void setStatusByUserId(long statusByUserId) {
        _flagReport.setStatusByUserId(statusByUserId);
    }

    /**
    * Returns the status by user uuid of this flag report.
    *
    * @return the status by user uuid of this flag report
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getStatusByUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _flagReport.getStatusByUserUuid();
    }

    /**
    * Sets the status by user uuid of this flag report.
    *
    * @param statusByUserUuid the status by user uuid of this flag report
    */
    public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
        _flagReport.setStatusByUserUuid(statusByUserUuid);
    }

    /**
    * Returns the status by user name of this flag report.
    *
    * @return the status by user name of this flag report
    */
    public java.lang.String getStatusByUserName() {
        return _flagReport.getStatusByUserName();
    }

    /**
    * Sets the status by user name of this flag report.
    *
    * @param statusByUserName the status by user name of this flag report
    */
    public void setStatusByUserName(java.lang.String statusByUserName) {
        _flagReport.setStatusByUserName(statusByUserName);
    }

    /**
    * Returns the status date of this flag report.
    *
    * @return the status date of this flag report
    */
    public java.util.Date getStatusDate() {
        return _flagReport.getStatusDate();
    }

    /**
    * Sets the status date of this flag report.
    *
    * @param statusDate the status date of this flag report
    */
    public void setStatusDate(java.util.Date statusDate) {
        _flagReport.setStatusDate(statusDate);
    }

    /**
    * @deprecated Renamed to {@link #isApproved()}
    */
    public boolean getApproved() {
        return _flagReport.getApproved();
    }

    /**
    * Returns <code>true</code> if this flag report is approved.
    *
    * @return <code>true</code> if this flag report is approved; <code>false</code> otherwise
    */
    public boolean isApproved() {
        return _flagReport.isApproved();
    }

    /**
    * Returns <code>true</code> if this flag report is a draft.
    *
    * @return <code>true</code> if this flag report is a draft; <code>false</code> otherwise
    */
    public boolean isDraft() {
        return _flagReport.isDraft();
    }

    /**
    * Returns <code>true</code> if this flag report is expired.
    *
    * @return <code>true</code> if this flag report is expired; <code>false</code> otherwise
    */
    public boolean isExpired() {
        return _flagReport.isExpired();
    }

    /**
    * Returns <code>true</code> if this flag report is pending.
    *
    * @return <code>true</code> if this flag report is pending; <code>false</code> otherwise
    */
    public boolean isPending() {
        return _flagReport.isPending();
    }

    public boolean isNew() {
        return _flagReport.isNew();
    }

    public void setNew(boolean n) {
        _flagReport.setNew(n);
    }

    public boolean isCachedModel() {
        return _flagReport.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _flagReport.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _flagReport.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _flagReport.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _flagReport.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _flagReport.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _flagReport.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FlagReportWrapper((FlagReport) _flagReport.clone());
    }

    public int compareTo(FlagReport flagReport) {
        return _flagReport.compareTo(flagReport);
    }

    @Override
    public int hashCode() {
        return _flagReport.hashCode();
    }

    public com.liferay.portal.model.CacheModel<FlagReport> toCacheModel() {
        return _flagReport.toCacheModel();
    }

    public FlagReport toEscapedModel() {
        return new FlagReportWrapper(_flagReport.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _flagReport.toString();
    }

    public java.lang.String toXmlString() {
        return _flagReport.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _flagReport.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public FlagReport getWrappedFlagReport() {
        return _flagReport;
    }

    public FlagReport getWrappedModel() {
        return _flagReport;
    }

    public void resetOriginalValues() {
        _flagReport.resetOriginalValues();
    }
}
