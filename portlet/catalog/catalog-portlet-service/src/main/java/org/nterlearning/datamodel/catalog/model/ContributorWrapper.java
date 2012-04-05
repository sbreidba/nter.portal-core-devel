package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Contributor}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Contributor
 * @generated
 */
public class ContributorWrapper implements Contributor,
    ModelWrapper<Contributor> {
    private Contributor _contributor;

    public ContributorWrapper(Contributor contributor) {
        _contributor = contributor;
    }

    public Class<?> getModelClass() {
        return Contributor.class;
    }

    public String getModelClassName() {
        return Contributor.class.getName();
    }

    /**
    * Returns the primary key of this contributor.
    *
    * @return the primary key of this contributor
    */
    public long getPrimaryKey() {
        return _contributor.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contributor.
    *
    * @param primaryKey the primary key of this contributor
    */
    public void setPrimaryKey(long primaryKey) {
        _contributor.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contributor ID of this contributor.
    *
    * @return the contributor ID of this contributor
    */
    public long getContributorId() {
        return _contributor.getContributorId();
    }

    /**
    * Sets the contributor ID of this contributor.
    *
    * @param contributorId the contributor ID of this contributor
    */
    public void setContributorId(long contributorId) {
        _contributor.setContributorId(contributorId);
    }

    /**
    * Returns the course ID of this contributor.
    *
    * @return the course ID of this contributor
    */
    public long getCourseId() {
        return _contributor.getCourseId();
    }

    /**
    * Sets the course ID of this contributor.
    *
    * @param courseId the course ID of this contributor
    */
    public void setCourseId(long courseId) {
        _contributor.setCourseId(courseId);
    }

    /**
    * Returns the component ID of this contributor.
    *
    * @return the component ID of this contributor
    */
    public long getComponentId() {
        return _contributor.getComponentId();
    }

    /**
    * Sets the component ID of this contributor.
    *
    * @param componentId the component ID of this contributor
    */
    public void setComponentId(long componentId) {
        _contributor.setComponentId(componentId);
    }

    /**
    * Returns the role of this contributor.
    *
    * @return the role of this contributor
    */
    public java.lang.String getRole() {
        return _contributor.getRole();
    }

    /**
    * Sets the role of this contributor.
    *
    * @param role the role of this contributor
    */
    public void setRole(java.lang.String role) {
        _contributor.setRole(role);
    }

    /**
    * Returns the contributor name of this contributor.
    *
    * @return the contributor name of this contributor
    */
    public java.lang.String getContributorName() {
        return _contributor.getContributorName();
    }

    /**
    * Sets the contributor name of this contributor.
    *
    * @param contributorName the contributor name of this contributor
    */
    public void setContributorName(java.lang.String contributorName) {
        _contributor.setContributorName(contributorName);
    }

    /**
    * Returns the virtual card data of this contributor.
    *
    * @return the virtual card data of this contributor
    */
    public java.lang.String getVirtualCardData() {
        return _contributor.getVirtualCardData();
    }

    /**
    * Sets the virtual card data of this contributor.
    *
    * @param virtualCardData the virtual card data of this contributor
    */
    public void setVirtualCardData(java.lang.String virtualCardData) {
        _contributor.setVirtualCardData(virtualCardData);
    }

    public boolean isNew() {
        return _contributor.isNew();
    }

    public void setNew(boolean n) {
        _contributor.setNew(n);
    }

    public boolean isCachedModel() {
        return _contributor.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contributor.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contributor.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contributor.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contributor.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contributor.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contributor.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContributorWrapper((Contributor) _contributor.clone());
    }

    public int compareTo(Contributor contributor) {
        return _contributor.compareTo(contributor);
    }

    @Override
    public int hashCode() {
        return _contributor.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Contributor> toCacheModel() {
        return _contributor.toCacheModel();
    }

    public Contributor toEscapedModel() {
        return new ContributorWrapper(_contributor.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contributor.toString();
    }

    public java.lang.String toXmlString() {
        return _contributor.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contributor.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Contributor getWrappedContributor() {
        return _contributor;
    }

    public Contributor getWrappedModel() {
        return _contributor;
    }

    public void resetOriginalValues() {
        _contributor.resetOriginalValues();
    }
}
