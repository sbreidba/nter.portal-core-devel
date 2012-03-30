package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ExternalLink}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExternalLink
 * @generated
 */
public class ExternalLinkWrapper implements ExternalLink,
    ModelWrapper<ExternalLink> {
    private ExternalLink _externalLink;

    public ExternalLinkWrapper(ExternalLink externalLink) {
        _externalLink = externalLink;
    }

    public Class<?> getModelClass() {
        return ExternalLink.class;
    }

    public String getModelClassName() {
        return ExternalLink.class.getName();
    }

    /**
    * Returns the primary key of this external link.
    *
    * @return the primary key of this external link
    */
    public long getPrimaryKey() {
        return _externalLink.getPrimaryKey();
    }

    /**
    * Sets the primary key of this external link.
    *
    * @param primaryKey the primary key of this external link
    */
    public void setPrimaryKey(long primaryKey) {
        _externalLink.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the link ID of this external link.
    *
    * @return the link ID of this external link
    */
    public long getLinkId() {
        return _externalLink.getLinkId();
    }

    /**
    * Sets the link ID of this external link.
    *
    * @param linkId the link ID of this external link
    */
    public void setLinkId(long linkId) {
        _externalLink.setLinkId(linkId);
    }

    /**
    * Returns the course ID of this external link.
    *
    * @return the course ID of this external link
    */
    public long getCourseId() {
        return _externalLink.getCourseId();
    }

    /**
    * Sets the course ID of this external link.
    *
    * @param courseId the course ID of this external link
    */
    public void setCourseId(long courseId) {
        _externalLink.setCourseId(courseId);
    }

    /**
    * Returns the component ID of this external link.
    *
    * @return the component ID of this external link
    */
    public long getComponentId() {
        return _externalLink.getComponentId();
    }

    /**
    * Sets the component ID of this external link.
    *
    * @param componentId the component ID of this external link
    */
    public void setComponentId(long componentId) {
        _externalLink.setComponentId(componentId);
    }

    /**
    * Returns the link type of this external link.
    *
    * @return the link type of this external link
    */
    public java.lang.String getLinkType() {
        return _externalLink.getLinkType();
    }

    /**
    * Sets the link type of this external link.
    *
    * @param linkType the link type of this external link
    */
    public void setLinkType(java.lang.String linkType) {
        _externalLink.setLinkType(linkType);
    }

    /**
    * Returns the link url of this external link.
    *
    * @return the link url of this external link
    */
    public java.lang.String getLinkUrl() {
        return _externalLink.getLinkUrl();
    }

    /**
    * Sets the link url of this external link.
    *
    * @param linkUrl the link url of this external link
    */
    public void setLinkUrl(java.lang.String linkUrl) {
        _externalLink.setLinkUrl(linkUrl);
    }

    public boolean isNew() {
        return _externalLink.isNew();
    }

    public void setNew(boolean n) {
        _externalLink.setNew(n);
    }

    public boolean isCachedModel() {
        return _externalLink.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _externalLink.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _externalLink.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _externalLink.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _externalLink.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _externalLink.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _externalLink.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ExternalLinkWrapper((ExternalLink) _externalLink.clone());
    }

    public int compareTo(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink) {
        return _externalLink.compareTo(externalLink);
    }

    @Override
    public int hashCode() {
        return _externalLink.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.ExternalLink> toCacheModel() {
        return _externalLink.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.ExternalLink toEscapedModel() {
        return new ExternalLinkWrapper(_externalLink.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _externalLink.toString();
    }

    public java.lang.String toXmlString() {
        return _externalLink.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _externalLink.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ExternalLink getWrappedExternalLink() {
        return _externalLink;
    }

    public ExternalLink getWrappedModel() {
        return _externalLink;
    }

    public void resetOriginalValues() {
        _externalLink.resetOriginalValues();
    }
}
