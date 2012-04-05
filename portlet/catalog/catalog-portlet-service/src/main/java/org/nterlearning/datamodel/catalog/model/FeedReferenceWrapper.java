package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedReference}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedReference
 * @generated
 */
public class FeedReferenceWrapper implements FeedReference,
    ModelWrapper<FeedReference> {
    private FeedReference _feedReference;

    public FeedReferenceWrapper(FeedReference feedReference) {
        _feedReference = feedReference;
    }

    public Class<?> getModelClass() {
        return FeedReference.class;
    }

    public String getModelClassName() {
        return FeedReference.class.getName();
    }

    /**
    * Returns the primary key of this feed reference.
    *
    * @return the primary key of this feed reference
    */
    public long getPrimaryKey() {
        return _feedReference.getPrimaryKey();
    }

    /**
    * Sets the primary key of this feed reference.
    *
    * @param primaryKey the primary key of this feed reference
    */
    public void setPrimaryKey(long primaryKey) {
        _feedReference.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the feed reference ID of this feed reference.
    *
    * @return the feed reference ID of this feed reference
    */
    public long getFeedReferenceId() {
        return _feedReference.getFeedReferenceId();
    }

    /**
    * Sets the feed reference ID of this feed reference.
    *
    * @param feedReferenceId the feed reference ID of this feed reference
    */
    public void setFeedReferenceId(long feedReferenceId) {
        _feedReference.setFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the company ID of this feed reference.
    *
    * @return the company ID of this feed reference
    */
    public long getCompanyId() {
        return _feedReference.getCompanyId();
    }

    /**
    * Sets the company ID of this feed reference.
    *
    * @param companyId the company ID of this feed reference
    */
    public void setCompanyId(long companyId) {
        _feedReference.setCompanyId(companyId);
    }

    /**
    * Returns the group ID of this feed reference.
    *
    * @return the group ID of this feed reference
    */
    public long getGroupId() {
        return _feedReference.getGroupId();
    }

    /**
    * Sets the group ID of this feed reference.
    *
    * @param groupId the group ID of this feed reference
    */
    public void setGroupId(long groupId) {
        _feedReference.setGroupId(groupId);
    }

    /**
    * Returns the content provider ID of this feed reference.
    *
    * @return the content provider ID of this feed reference
    */
    public java.lang.String getContentProviderId() {
        return _feedReference.getContentProviderId();
    }

    /**
    * Sets the content provider ID of this feed reference.
    *
    * @param contentProviderId the content provider ID of this feed reference
    */
    public void setContentProviderId(java.lang.String contentProviderId) {
        _feedReference.setContentProviderId(contentProviderId);
    }

    /**
    * Returns the href of this feed reference.
    *
    * @return the href of this feed reference
    */
    public java.lang.String getHref() {
        return _feedReference.getHref();
    }

    /**
    * Sets the href of this feed reference.
    *
    * @param href the href of this feed reference
    */
    public void setHref(java.lang.String href) {
        _feedReference.setHref(href);
    }

    /**
    * Returns the href hash of this feed reference.
    *
    * @return the href hash of this feed reference
    */
    public java.lang.String getHrefHash() {
        return _feedReference.getHrefHash();
    }

    /**
    * Sets the href hash of this feed reference.
    *
    * @param hrefHash the href hash of this feed reference
    */
    public void setHrefHash(java.lang.String hrefHash) {
        _feedReference.setHrefHash(hrefHash);
    }

    /**
    * Returns the pshb of this feed reference.
    *
    * @return the pshb of this feed reference
    */
    public java.lang.String getPshb() {
        return _feedReference.getPshb();
    }

    /**
    * Sets the pshb of this feed reference.
    *
    * @param pshb the pshb of this feed reference
    */
    public void setPshb(java.lang.String pshb) {
        _feedReference.setPshb(pshb);
    }

    /**
    * Returns the pshb subscribed of this feed reference.
    *
    * @return the pshb subscribed of this feed reference
    */
    public boolean getPshbSubscribed() {
        return _feedReference.getPshbSubscribed();
    }

    /**
    * Returns <code>true</code> if this feed reference is pshb subscribed.
    *
    * @return <code>true</code> if this feed reference is pshb subscribed; <code>false</code> otherwise
    */
    public boolean isPshbSubscribed() {
        return _feedReference.isPshbSubscribed();
    }

    /**
    * Sets whether this feed reference is pshb subscribed.
    *
    * @param pshbSubscribed the pshb subscribed of this feed reference
    */
    public void setPshbSubscribed(boolean pshbSubscribed) {
        _feedReference.setPshbSubscribed(pshbSubscribed);
    }

    /**
    * Returns the feed iri of this feed reference.
    *
    * @return the feed iri of this feed reference
    */
    public java.lang.String getFeedIri() {
        return _feedReference.getFeedIri();
    }

    /**
    * Sets the feed iri of this feed reference.
    *
    * @param feedIri the feed iri of this feed reference
    */
    public void setFeedIri(java.lang.String feedIri) {
        _feedReference.setFeedIri(feedIri);
    }

    /**
    * Returns the feed type of this feed reference.
    *
    * @return the feed type of this feed reference
    */
    public java.lang.String getFeedType() {
        return _feedReference.getFeedType();
    }

    /**
    * Sets the feed type of this feed reference.
    *
    * @param feedType the feed type of this feed reference
    */
    public void setFeedType(java.lang.String feedType) {
        _feedReference.setFeedType(feedType);
    }

    /**
    * Returns the feed version of this feed reference.
    *
    * @return the feed version of this feed reference
    */
    public java.lang.String getFeedVersion() {
        return _feedReference.getFeedVersion();
    }

    /**
    * Sets the feed version of this feed reference.
    *
    * @param feedVersion the feed version of this feed reference
    */
    public void setFeedVersion(java.lang.String feedVersion) {
        _feedReference.setFeedVersion(feedVersion);
    }

    /**
    * Returns the trustworthy weight of this feed reference.
    *
    * @return the trustworthy weight of this feed reference
    */
    public double getTrustworthyWeight() {
        return _feedReference.getTrustworthyWeight();
    }

    /**
    * Sets the trustworthy weight of this feed reference.
    *
    * @param trustworthyWeight the trustworthy weight of this feed reference
    */
    public void setTrustworthyWeight(double trustworthyWeight) {
        _feedReference.setTrustworthyWeight(trustworthyWeight);
    }

    /**
    * Returns the create date of this feed reference.
    *
    * @return the create date of this feed reference
    */
    public java.util.Date getCreateDate() {
        return _feedReference.getCreateDate();
    }

    /**
    * Sets the create date of this feed reference.
    *
    * @param createDate the create date of this feed reference
    */
    public void setCreateDate(java.util.Date createDate) {
        _feedReference.setCreateDate(createDate);
    }

    /**
    * Returns the removed of this feed reference.
    *
    * @return the removed of this feed reference
    */
    public boolean getRemoved() {
        return _feedReference.getRemoved();
    }

    /**
    * Returns <code>true</code> if this feed reference is removed.
    *
    * @return <code>true</code> if this feed reference is removed; <code>false</code> otherwise
    */
    public boolean isRemoved() {
        return _feedReference.isRemoved();
    }

    /**
    * Sets whether this feed reference is removed.
    *
    * @param removed the removed of this feed reference
    */
    public void setRemoved(boolean removed) {
        _feedReference.setRemoved(removed);
    }

    /**
    * Returns the removed date of this feed reference.
    *
    * @return the removed date of this feed reference
    */
    public java.util.Date getRemovedDate() {
        return _feedReference.getRemovedDate();
    }

    /**
    * Sets the removed date of this feed reference.
    *
    * @param removedDate the removed date of this feed reference
    */
    public void setRemovedDate(java.util.Date removedDate) {
        _feedReference.setRemovedDate(removedDate);
    }

    /**
    * Returns the removed reason of this feed reference.
    *
    * @return the removed reason of this feed reference
    */
    public java.lang.String getRemovedReason() {
        return _feedReference.getRemovedReason();
    }

    /**
    * Sets the removed reason of this feed reference.
    *
    * @param removedReason the removed reason of this feed reference
    */
    public void setRemovedReason(java.lang.String removedReason) {
        _feedReference.setRemovedReason(removedReason);
    }

    /**
    * Returns the sync date of this feed reference.
    *
    * @return the sync date of this feed reference
    */
    public java.util.Date getSyncDate() {
        return _feedReference.getSyncDate();
    }

    /**
    * Sets the sync date of this feed reference.
    *
    * @param syncDate the sync date of this feed reference
    */
    public void setSyncDate(java.util.Date syncDate) {
        _feedReference.setSyncDate(syncDate);
    }

    /**
    * Returns the sync success of this feed reference.
    *
    * @return the sync success of this feed reference
    */
    public boolean getSyncSuccess() {
        return _feedReference.getSyncSuccess();
    }

    /**
    * Returns <code>true</code> if this feed reference is sync success.
    *
    * @return <code>true</code> if this feed reference is sync success; <code>false</code> otherwise
    */
    public boolean isSyncSuccess() {
        return _feedReference.isSyncSuccess();
    }

    /**
    * Sets whether this feed reference is sync success.
    *
    * @param syncSuccess the sync success of this feed reference
    */
    public void setSyncSuccess(boolean syncSuccess) {
        _feedReference.setSyncSuccess(syncSuccess);
    }

    public boolean isNew() {
        return _feedReference.isNew();
    }

    public void setNew(boolean n) {
        _feedReference.setNew(n);
    }

    public boolean isCachedModel() {
        return _feedReference.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _feedReference.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _feedReference.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _feedReference.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _feedReference.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _feedReference.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _feedReference.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FeedReferenceWrapper((FeedReference) _feedReference.clone());
    }

    public int compareTo(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference) {
        return _feedReference.compareTo(feedReference);
    }

    @Override
    public int hashCode() {
        return _feedReference.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.FeedReference> toCacheModel() {
        return _feedReference.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference toEscapedModel() {
        return new FeedReferenceWrapper(_feedReference.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _feedReference.toString();
    }

    public java.lang.String toXmlString() {
        return _feedReference.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _feedReference.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public FeedReference getWrappedFeedReference() {
        return _feedReference;
    }

    public FeedReference getWrappedModel() {
        return _feedReference;
    }

    public void resetOriginalValues() {
        _feedReference.resetOriginalValues();
    }
}
