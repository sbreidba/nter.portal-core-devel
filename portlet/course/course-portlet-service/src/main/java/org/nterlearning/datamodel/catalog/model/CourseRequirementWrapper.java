package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CourseRequirement}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CourseRequirement
 * @generated
 */
public class CourseRequirementWrapper implements CourseRequirement,
    ModelWrapper<CourseRequirement> {
    private CourseRequirement _courseRequirement;

    public CourseRequirementWrapper(CourseRequirement courseRequirement) {
        _courseRequirement = courseRequirement;
    }

    public Class<?> getModelClass() {
        return CourseRequirement.class;
    }

    public String getModelClassName() {
        return CourseRequirement.class.getName();
    }

    /**
    * Returns the primary key of this course requirement.
    *
    * @return the primary key of this course requirement
    */
    public long getPrimaryKey() {
        return _courseRequirement.getPrimaryKey();
    }

    /**
    * Sets the primary key of this course requirement.
    *
    * @param primaryKey the primary key of this course requirement
    */
    public void setPrimaryKey(long primaryKey) {
        _courseRequirement.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the course requirement ID of this course requirement.
    *
    * @return the course requirement ID of this course requirement
    */
    public long getCourseRequirementId() {
        return _courseRequirement.getCourseRequirementId();
    }

    /**
    * Sets the course requirement ID of this course requirement.
    *
    * @param courseRequirementId the course requirement ID of this course requirement
    */
    public void setCourseRequirementId(long courseRequirementId) {
        _courseRequirement.setCourseRequirementId(courseRequirementId);
    }

    /**
    * Returns the course ID of this course requirement.
    *
    * @return the course ID of this course requirement
    */
    public long getCourseId() {
        return _courseRequirement.getCourseId();
    }

    /**
    * Sets the course ID of this course requirement.
    *
    * @param courseId the course ID of this course requirement
    */
    public void setCourseId(long courseId) {
        _courseRequirement.setCourseId(courseId);
    }

    /**
    * Returns the requirement type of this course requirement.
    *
    * @return the requirement type of this course requirement
    */
    public java.lang.String getRequirementType() {
        return _courseRequirement.getRequirementType();
    }

    /**
    * Sets the requirement type of this course requirement.
    *
    * @param requirementType the requirement type of this course requirement
    */
    public void setRequirementType(java.lang.String requirementType) {
        _courseRequirement.setRequirementType(requirementType);
    }

    /**
    * Returns the requirement value of this course requirement.
    *
    * @return the requirement value of this course requirement
    */
    public java.lang.String getRequirementValue() {
        return _courseRequirement.getRequirementValue();
    }

    /**
    * Returns the localized requirement value of this course requirement in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized requirement value of this course requirement
    */
    public java.lang.String getRequirementValue(java.util.Locale locale) {
        return _courseRequirement.getRequirementValue(locale);
    }

    /**
    * Returns the localized requirement value of this course requirement in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized requirement value of this course requirement. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getRequirementValue(java.util.Locale locale,
        boolean useDefault) {
        return _courseRequirement.getRequirementValue(locale, useDefault);
    }

    /**
    * Returns the localized requirement value of this course requirement in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized requirement value of this course requirement
    */
    public java.lang.String getRequirementValue(java.lang.String languageId) {
        return _courseRequirement.getRequirementValue(languageId);
    }

    /**
    * Returns the localized requirement value of this course requirement in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized requirement value of this course requirement
    */
    public java.lang.String getRequirementValue(java.lang.String languageId,
        boolean useDefault) {
        return _courseRequirement.getRequirementValue(languageId, useDefault);
    }

    public java.lang.String getRequirementValueCurrentLanguageId() {
        return _courseRequirement.getRequirementValueCurrentLanguageId();
    }

    public java.lang.String getRequirementValueCurrentValue() {
        return _courseRequirement.getRequirementValueCurrentValue();
    }

    /**
    * Returns a map of the locales and localized requirement values of this course requirement.
    *
    * @return the locales and localized requirement values of this course requirement
    */
    public java.util.Map<java.util.Locale, java.lang.String> getRequirementValueMap() {
        return _courseRequirement.getRequirementValueMap();
    }

    /**
    * Sets the requirement value of this course requirement.
    *
    * @param requirementValue the requirement value of this course requirement
    */
    public void setRequirementValue(java.lang.String requirementValue) {
        _courseRequirement.setRequirementValue(requirementValue);
    }

    /**
    * Sets the localized requirement value of this course requirement in the language.
    *
    * @param requirementValue the localized requirement value of this course requirement
    * @param locale the locale of the language
    */
    public void setRequirementValue(java.lang.String requirementValue,
        java.util.Locale locale) {
        _courseRequirement.setRequirementValue(requirementValue, locale);
    }

    /**
    * Sets the localized requirement value of this course requirement in the language, and sets the default locale.
    *
    * @param requirementValue the localized requirement value of this course requirement
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setRequirementValue(java.lang.String requirementValue,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _courseRequirement.setRequirementValue(requirementValue, locale,
            defaultLocale);
    }

    public void setRequirementValueCurrentLanguageId(
        java.lang.String languageId) {
        _courseRequirement.setRequirementValueCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized requirement values of this course requirement from the map of locales and localized requirement values.
    *
    * @param requirementValueMap the locales and localized requirement values of this course requirement
    */
    public void setRequirementValueMap(
        java.util.Map<java.util.Locale, java.lang.String> requirementValueMap) {
        _courseRequirement.setRequirementValueMap(requirementValueMap);
    }

    /**
    * Sets the localized requirement values of this course requirement from the map of locales and localized requirement values, and sets the default locale.
    *
    * @param requirementValueMap the locales and localized requirement values of this course requirement
    * @param defaultLocale the default locale
    */
    public void setRequirementValueMap(
        java.util.Map<java.util.Locale, java.lang.String> requirementValueMap,
        java.util.Locale defaultLocale) {
        _courseRequirement.setRequirementValueMap(requirementValueMap,
            defaultLocale);
    }

    public boolean isNew() {
        return _courseRequirement.isNew();
    }

    public void setNew(boolean n) {
        _courseRequirement.setNew(n);
    }

    public boolean isCachedModel() {
        return _courseRequirement.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _courseRequirement.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _courseRequirement.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _courseRequirement.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _courseRequirement.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _courseRequirement.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _courseRequirement.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CourseRequirementWrapper((CourseRequirement) _courseRequirement.clone());
    }

    public int compareTo(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement) {
        return _courseRequirement.compareTo(courseRequirement);
    }

    @Override
    public int hashCode() {
        return _courseRequirement.hashCode();
    }

    public com.liferay.portal.model.CacheModel<org.nterlearning.datamodel.catalog.model.CourseRequirement> toCacheModel() {
        return _courseRequirement.toCacheModel();
    }

    public org.nterlearning.datamodel.catalog.model.CourseRequirement toEscapedModel() {
        return new CourseRequirementWrapper(_courseRequirement.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _courseRequirement.toString();
    }

    public java.lang.String toXmlString() {
        return _courseRequirement.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _courseRequirement.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public CourseRequirement getWrappedCourseRequirement() {
        return _courseRequirement;
    }

    public CourseRequirement getWrappedModel() {
        return _courseRequirement;
    }

    public void resetOriginalValues() {
        _courseRequirement.resetOriginalValues();
    }
}
