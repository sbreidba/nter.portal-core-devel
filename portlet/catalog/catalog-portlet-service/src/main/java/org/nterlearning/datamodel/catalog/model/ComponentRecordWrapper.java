package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ComponentRecord}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ComponentRecord
 * @generated
 */
public class ComponentRecordWrapper implements ComponentRecord,
    ModelWrapper<ComponentRecord> {
    private ComponentRecord _componentRecord;

    public ComponentRecordWrapper(ComponentRecord componentRecord) {
        _componentRecord = componentRecord;
    }

    public Class<?> getModelClass() {
        return ComponentRecord.class;
    }

    public String getModelClassName() {
        return ComponentRecord.class.getName();
    }

    /**
    * Returns the primary key of this component record.
    *
    * @return the primary key of this component record
    */
    public long getPrimaryKey() {
        return _componentRecord.getPrimaryKey();
    }

    /**
    * Sets the primary key of this component record.
    *
    * @param primaryKey the primary key of this component record
    */
    public void setPrimaryKey(long primaryKey) {
        _componentRecord.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the component record ID of this component record.
    *
    * @return the component record ID of this component record
    */
    public long getComponentRecordId() {
        return _componentRecord.getComponentRecordId();
    }

    /**
    * Sets the component record ID of this component record.
    *
    * @param componentRecordId the component record ID of this component record
    */
    public void setComponentRecordId(long componentRecordId) {
        _componentRecord.setComponentRecordId(componentRecordId);
    }

    /**
    * Returns the course record ID of this component record.
    *
    * @return the course record ID of this component record
    */
    public long getCourseRecordId() {
        return _componentRecord.getCourseRecordId();
    }

    /**
    * Sets the course record ID of this component record.
    *
    * @param courseRecordId the course record ID of this component record
    */
    public void setCourseRecordId(long courseRecordId) {
        _componentRecord.setCourseRecordId(courseRecordId);
    }

    /**
    * Returns the component iri of this component record.
    *
    * @return the component iri of this component record
    */
    public java.lang.String getComponentIri() {
        return _componentRecord.getComponentIri();
    }

    /**
    * Sets the component iri of this component record.
    *
    * @param componentIri the component iri of this component record
    */
    public void setComponentIri(java.lang.String componentIri) {
        _componentRecord.setComponentIri(componentIri);
    }

    /**
    * Returns the updated date of this component record.
    *
    * @return the updated date of this component record
    */
    public java.util.Date getUpdatedDate() {
        return _componentRecord.getUpdatedDate();
    }

    /**
    * Sets the updated date of this component record.
    *
    * @param updatedDate the updated date of this component record
    */
    public void setUpdatedDate(java.util.Date updatedDate) {
        _componentRecord.setUpdatedDate(updatedDate);
    }

    /**
    * Returns the completion status of this component record.
    *
    * @return the completion status of this component record
    */
    public java.lang.String getCompletionStatus() {
        return _componentRecord.getCompletionStatus();
    }

    /**
    * Sets the completion status of this component record.
    *
    * @param completionStatus the completion status of this component record
    */
    public void setCompletionStatus(java.lang.String completionStatus) {
        _componentRecord.setCompletionStatus(completionStatus);
    }

    /**
    * Returns the completion percent of this component record.
    *
    * @return the completion percent of this component record
    */
    public java.lang.Integer getCompletionPercent() {
        return _componentRecord.getCompletionPercent();
    }

    /**
    * Sets the completion percent of this component record.
    *
    * @param completionPercent the completion percent of this component record
    */
    public void setCompletionPercent(java.lang.Integer completionPercent) {
        _componentRecord.setCompletionPercent(completionPercent);
    }

    public boolean isNew() {
        return _componentRecord.isNew();
    }

    public void setNew(boolean n) {
        _componentRecord.setNew(n);
    }

    public boolean isCachedModel() {
        return _componentRecord.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _componentRecord.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _componentRecord.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _componentRecord.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _componentRecord.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _componentRecord.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _componentRecord.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ComponentRecordWrapper((ComponentRecord) _componentRecord.clone());
    }

    public int compareTo(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord) {
        return _componentRecord.compareTo(componentRecord);
    }

    @Override
    public int hashCode() {
        return _componentRecord.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.ComponentRecord> toCacheModel() {
        return _componentRecord.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.ComponentRecord toEscapedModel() {
        return new ComponentRecordWrapper(_componentRecord.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _componentRecord.toString();
    }

    public java.lang.String toXmlString() {
        return _componentRecord.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _componentRecord.persist();
    }

    public java.lang.String getFriendlyUpdatedDate(
        javax.servlet.jsp.PageContext pageContext) {
        return _componentRecord.getFriendlyUpdatedDate(pageContext);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ComponentRecord getWrappedComponentRecord() {
        return _componentRecord;
    }

    public ComponentRecord getWrappedModel() {
        return _componentRecord;
    }

    public void resetOriginalValues() {
        _componentRecord.resetOriginalValues();
    }
}
