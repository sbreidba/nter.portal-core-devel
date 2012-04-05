package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Courses_Components}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Courses_Components
 * @generated
 */
public class Courses_ComponentsWrapper implements Courses_Components,
    ModelWrapper<Courses_Components> {
    private Courses_Components _courses_Components;

    public Courses_ComponentsWrapper(Courses_Components courses_Components) {
        _courses_Components = courses_Components;
    }

    public Class<?> getModelClass() {
        return Courses_Components.class;
    }

    public String getModelClassName() {
        return Courses_Components.class.getName();
    }

    /**
    * Returns the primary key of this courses_ components.
    *
    * @return the primary key of this courses_ components
    */
    public long getPrimaryKey() {
        return _courses_Components.getPrimaryKey();
    }

    /**
    * Sets the primary key of this courses_ components.
    *
    * @param primaryKey the primary key of this courses_ components
    */
    public void setPrimaryKey(long primaryKey) {
        _courses_Components.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the courses components ID of this courses_ components.
    *
    * @return the courses components ID of this courses_ components
    */
    public long getCoursesComponentsId() {
        return _courses_Components.getCoursesComponentsId();
    }

    /**
    * Sets the courses components ID of this courses_ components.
    *
    * @param coursesComponentsId the courses components ID of this courses_ components
    */
    public void setCoursesComponentsId(long coursesComponentsId) {
        _courses_Components.setCoursesComponentsId(coursesComponentsId);
    }

    /**
    * Returns the course ID of this courses_ components.
    *
    * @return the course ID of this courses_ components
    */
    public long getCourseId() {
        return _courses_Components.getCourseId();
    }

    /**
    * Sets the course ID of this courses_ components.
    *
    * @param courseId the course ID of this courses_ components
    */
    public void setCourseId(long courseId) {
        _courses_Components.setCourseId(courseId);
    }

    /**
    * Returns the course iri of this courses_ components.
    *
    * @return the course iri of this courses_ components
    */
    public java.lang.String getCourseIri() {
        return _courses_Components.getCourseIri();
    }

    /**
    * Sets the course iri of this courses_ components.
    *
    * @param courseIri the course iri of this courses_ components
    */
    public void setCourseIri(java.lang.String courseIri) {
        _courses_Components.setCourseIri(courseIri);
    }

    /**
    * Returns the component ID of this courses_ components.
    *
    * @return the component ID of this courses_ components
    */
    public long getComponentId() {
        return _courses_Components.getComponentId();
    }

    /**
    * Sets the component ID of this courses_ components.
    *
    * @param componentId the component ID of this courses_ components
    */
    public void setComponentId(long componentId) {
        _courses_Components.setComponentId(componentId);
    }

    /**
    * Returns the component iri of this courses_ components.
    *
    * @return the component iri of this courses_ components
    */
    public java.lang.String getComponentIri() {
        return _courses_Components.getComponentIri();
    }

    /**
    * Sets the component iri of this courses_ components.
    *
    * @param componentIri the component iri of this courses_ components
    */
    public void setComponentIri(java.lang.String componentIri) {
        _courses_Components.setComponentIri(componentIri);
    }

    /**
    * Returns the order weight of this courses_ components.
    *
    * @return the order weight of this courses_ components
    */
    public double getOrderWeight() {
        return _courses_Components.getOrderWeight();
    }

    /**
    * Sets the order weight of this courses_ components.
    *
    * @param orderWeight the order weight of this courses_ components
    */
    public void setOrderWeight(double orderWeight) {
        _courses_Components.setOrderWeight(orderWeight);
    }

    /**
    * Returns the section type of this courses_ components.
    *
    * @return the section type of this courses_ components
    */
    public java.lang.String getSectionType() {
        return _courses_Components.getSectionType();
    }

    /**
    * Sets the section type of this courses_ components.
    *
    * @param sectionType the section type of this courses_ components
    */
    public void setSectionType(java.lang.String sectionType) {
        _courses_Components.setSectionType(sectionType);
    }

    /**
    * Returns the component type of this courses_ components.
    *
    * @return the component type of this courses_ components
    */
    public java.lang.String getComponentType() {
        return _courses_Components.getComponentType();
    }

    /**
    * Sets the component type of this courses_ components.
    *
    * @param componentType the component type of this courses_ components
    */
    public void setComponentType(java.lang.String componentType) {
        _courses_Components.setComponentType(componentType);
    }

    /**
    * Returns the mime type of this courses_ components.
    *
    * @return the mime type of this courses_ components
    */
    public java.lang.String getMimeType() {
        return _courses_Components.getMimeType();
    }

    /**
    * Sets the mime type of this courses_ components.
    *
    * @param mimeType the mime type of this courses_ components
    */
    public void setMimeType(java.lang.String mimeType) {
        _courses_Components.setMimeType(mimeType);
    }

    /**
    * Returns the course payment required of this courses_ components.
    *
    * @return the course payment required of this courses_ components
    */
    public boolean getCoursePaymentRequired() {
        return _courses_Components.getCoursePaymentRequired();
    }

    /**
    * Returns <code>true</code> if this courses_ components is course payment required.
    *
    * @return <code>true</code> if this courses_ components is course payment required; <code>false</code> otherwise
    */
    public boolean isCoursePaymentRequired() {
        return _courses_Components.isCoursePaymentRequired();
    }

    /**
    * Sets whether this courses_ components is course payment required.
    *
    * @param coursePaymentRequired the course payment required of this courses_ components
    */
    public void setCoursePaymentRequired(boolean coursePaymentRequired) {
        _courses_Components.setCoursePaymentRequired(coursePaymentRequired);
    }

    /**
    * Returns the component payment required of this courses_ components.
    *
    * @return the component payment required of this courses_ components
    */
    public boolean getComponentPaymentRequired() {
        return _courses_Components.getComponentPaymentRequired();
    }

    /**
    * Returns <code>true</code> if this courses_ components is component payment required.
    *
    * @return <code>true</code> if this courses_ components is component payment required; <code>false</code> otherwise
    */
    public boolean isComponentPaymentRequired() {
        return _courses_Components.isComponentPaymentRequired();
    }

    /**
    * Sets whether this courses_ components is component payment required.
    *
    * @param componentPaymentRequired the component payment required of this courses_ components
    */
    public void setComponentPaymentRequired(boolean componentPaymentRequired) {
        _courses_Components.setComponentPaymentRequired(componentPaymentRequired);
    }

    public boolean isNew() {
        return _courses_Components.isNew();
    }

    public void setNew(boolean n) {
        _courses_Components.setNew(n);
    }

    public boolean isCachedModel() {
        return _courses_Components.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _courses_Components.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _courses_Components.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _courses_Components.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _courses_Components.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _courses_Components.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _courses_Components.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Courses_ComponentsWrapper((Courses_Components) _courses_Components.clone());
    }

    public int compareTo(Courses_Components courses_Components) {
        return _courses_Components.compareTo(courses_Components);
    }

    @Override
    public int hashCode() {
        return _courses_Components.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Courses_Components> toCacheModel() {
        return _courses_Components.toCacheModel();
    }

    public Courses_Components toEscapedModel() {
        return new Courses_ComponentsWrapper(_courses_Components.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _courses_Components.toString();
    }

    public java.lang.String toXmlString() {
        return _courses_Components.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courses_Components.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Courses_Components getWrappedCourses_Components() {
        return _courses_Components;
    }

    public Courses_Components getWrappedModel() {
        return _courses_Components;
    }

    public void resetOriginalValues() {
        _courses_Components.resetOriginalValues();
    }
}
