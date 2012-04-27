package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedSyncHistory}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedSyncHistory
 * @generated
 */
public class FeedSyncHistoryWrapper implements FeedSyncHistory,
    ModelWrapper<FeedSyncHistory> {
    private FeedSyncHistory _feedSyncHistory;

    public FeedSyncHistoryWrapper(FeedSyncHistory feedSyncHistory) {
        _feedSyncHistory = feedSyncHistory;
    }

    public Class<?> getModelClass() {
        return FeedSyncHistory.class;
    }

    public String getModelClassName() {
        return FeedSyncHistory.class.getName();
    }

    /**
    * Returns the primary key of this feed sync history.
    *
    * @return the primary key of this feed sync history
    */
    public long getPrimaryKey() {
        return _feedSyncHistory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this feed sync history.
    *
    * @param primaryKey the primary key of this feed sync history
    */
    public void setPrimaryKey(long primaryKey) {
        _feedSyncHistory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the sync ID of this feed sync history.
    *
    * @return the sync ID of this feed sync history
    */
    public long getSyncId() {
        return _feedSyncHistory.getSyncId();
    }

    /**
    * Sets the sync ID of this feed sync history.
    *
    * @param syncId the sync ID of this feed sync history
    */
    public void setSyncId(long syncId) {
        _feedSyncHistory.setSyncId(syncId);
    }

    /**
    * Returns the feed reference ID of this feed sync history.
    *
    * @return the feed reference ID of this feed sync history
    */
    public long getFeedReferenceId() {
        return _feedSyncHistory.getFeedReferenceId();
    }

    /**
    * Sets the feed reference ID of this feed sync history.
    *
    * @param feedReferenceId the feed reference ID of this feed sync history
    */
    public void setFeedReferenceId(long feedReferenceId) {
        _feedSyncHistory.setFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the sync date of this feed sync history.
    *
    * @return the sync date of this feed sync history
    */
    public java.util.Date getSyncDate() {
        return _feedSyncHistory.getSyncDate();
    }

    /**
    * Sets the sync date of this feed sync history.
    *
    * @param syncDate the sync date of this feed sync history
    */
    public void setSyncDate(java.util.Date syncDate) {
        _feedSyncHistory.setSyncDate(syncDate);
    }

    /**
    * Returns the success of this feed sync history.
    *
    * @return the success of this feed sync history
    */
    public java.lang.Boolean getSuccess() {
        return _feedSyncHistory.getSuccess();
    }

    /**
    * Sets the success of this feed sync history.
    *
    * @param success the success of this feed sync history
    */
    public void setSuccess(java.lang.Boolean success) {
        _feedSyncHistory.setSuccess(success);
    }

    /**
    * Returns the sync message of this feed sync history.
    *
    * @return the sync message of this feed sync history
    */
    public java.lang.String getSyncMessage() {
        return _feedSyncHistory.getSyncMessage();
    }

    /**
    * Sets the sync message of this feed sync history.
    *
    * @param syncMessage the sync message of this feed sync history
    */
    public void setSyncMessage(java.lang.String syncMessage) {
        _feedSyncHistory.setSyncMessage(syncMessage);
    }

    /**
    * Returns the number of entries of this feed sync history.
    *
    * @return the number of entries of this feed sync history
    */
    public java.lang.Integer getNumberOfEntries() {
        return _feedSyncHistory.getNumberOfEntries();
    }

    /**
    * Sets the number of entries of this feed sync history.
    *
    * @param numberOfEntries the number of entries of this feed sync history
    */
    public void setNumberOfEntries(java.lang.Integer numberOfEntries) {
        _feedSyncHistory.setNumberOfEntries(numberOfEntries);
    }

    public boolean isNew() {
        return _feedSyncHistory.isNew();
    }

    public void setNew(boolean n) {
        _feedSyncHistory.setNew(n);
    }

    public boolean isCachedModel() {
        return _feedSyncHistory.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _feedSyncHistory.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _feedSyncHistory.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _feedSyncHistory.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _feedSyncHistory.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _feedSyncHistory.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _feedSyncHistory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FeedSyncHistoryWrapper((FeedSyncHistory) _feedSyncHistory.clone());
    }

    public int compareTo(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory) {
        return _feedSyncHistory.compareTo(feedSyncHistory);
    }

    @Override
    public int hashCode() {
        return _feedSyncHistory.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.FeedSyncHistory> toCacheModel() {
        return _feedSyncHistory.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.FeedSyncHistory toEscapedModel() {
        return new FeedSyncHistoryWrapper(_feedSyncHistory.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _feedSyncHistory.toString();
    }

    public java.lang.String toXmlString() {
        return _feedSyncHistory.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _feedSyncHistory.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public FeedSyncHistory getWrappedFeedSyncHistory() {
        return _feedSyncHistory;
    }

    public FeedSyncHistory getWrappedModel() {
        return _feedSyncHistory;
    }

    public void resetOriginalValues() {
        _feedSyncHistory.resetOriginalValues();
    }
}
