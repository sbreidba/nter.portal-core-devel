package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Component}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Component
 * @generated
 */
public class ComponentWrapper implements Component, ModelWrapper<Component> {
    private Component _component;

    public ComponentWrapper(Component component) {
        _component = component;
    }

    public Class<?> getModelClass() {
        return Component.class;
    }

    public String getModelClassName() {
        return Component.class.getName();
    }

    /**
    * Returns the primary key of this component.
    *
    * @return the primary key of this component
    */
    public long getPrimaryKey() {
        return _component.getPrimaryKey();
    }

    /**
    * Sets the primary key of this component.
    *
    * @param primaryKey the primary key of this component
    */
    public void setPrimaryKey(long primaryKey) {
        _component.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the component ID of this component.
    *
    * @return the component ID of this component
    */
    public long getComponentId() {
        return _component.getComponentId();
    }

    /**
    * Sets the component ID of this component.
    *
    * @param componentId the component ID of this component
    */
    public void setComponentId(long componentId) {
        _component.setComponentId(componentId);
    }

    /**
    * Returns the company ID of this component.
    *
    * @return the company ID of this component
    */
    public long getCompanyId() {
        return _component.getCompanyId();
    }

    /**
    * Sets the company ID of this component.
    *
    * @param companyId the company ID of this component
    */
    public void setCompanyId(long companyId) {
        _component.setCompanyId(companyId);
    }

    /**
    * Returns the group ID of this component.
    *
    * @return the group ID of this component
    */
    public long getGroupId() {
        return _component.getGroupId();
    }

    /**
    * Sets the group ID of this component.
    *
    * @param groupId the group ID of this component
    */
    public void setGroupId(long groupId) {
        _component.setGroupId(groupId);
    }

    /**
    * Returns the feed reference ID of this component.
    *
    * @return the feed reference ID of this component
    */
    public long getFeedReferenceId() {
        return _component.getFeedReferenceId();
    }

    /**
    * Sets the feed reference ID of this component.
    *
    * @param feedReferenceId the feed reference ID of this component
    */
    public void setFeedReferenceId(long feedReferenceId) {
        _component.setFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the component iri of this component.
    *
    * @return the component iri of this component
    */
    public java.lang.String getComponentIri() {
        return _component.getComponentIri();
    }

    /**
    * Sets the component iri of this component.
    *
    * @param componentIri the component iri of this component
    */
    public void setComponentIri(java.lang.String componentIri) {
        _component.setComponentIri(componentIri);
    }

    /**
    * Returns the updated date of this component.
    *
    * @return the updated date of this component
    */
    public java.util.Date getUpdatedDate() {
        return _component.getUpdatedDate();
    }

    /**
    * Sets the updated date of this component.
    *
    * @param updatedDate the updated date of this component
    */
    public void setUpdatedDate(java.util.Date updatedDate) {
        _component.setUpdatedDate(updatedDate);
    }

    /**
    * Returns the language of this component.
    *
    * @return the language of this component
    */
    public java.lang.String getLanguage() {
        return _component.getLanguage();
    }

    /**
    * Sets the language of this component.
    *
    * @param language the language of this component
    */
    public void setLanguage(java.lang.String language) {
        _component.setLanguage(language);
    }

    /**
    * Returns the href of this component.
    *
    * @return the href of this component
    */
    public java.lang.String getHref() {
        return _component.getHref();
    }

    /**
    * Sets the href of this component.
    *
    * @param href the href of this component
    */
    public void setHref(java.lang.String href) {
        _component.setHref(href);
    }

    /**
    * Returns the full text href of this component.
    *
    * @return the full text href of this component
    */
    public java.lang.String getFullTextHref() {
        return _component.getFullTextHref();
    }

    /**
    * Sets the full text href of this component.
    *
    * @param fullTextHref the full text href of this component
    */
    public void setFullTextHref(java.lang.String fullTextHref) {
        _component.setFullTextHref(fullTextHref);
    }

    /**
    * Returns the title of this component.
    *
    * @return the title of this component
    */
    public java.lang.String getTitle() {
        return _component.getTitle();
    }

    /**
    * Sets the title of this component.
    *
    * @param title the title of this component
    */
    public void setTitle(java.lang.String title) {
        _component.setTitle(title);
    }

    /**
    * Returns the description of this component.
    *
    * @return the description of this component
    */
    public java.lang.String getDescription() {
        return _component.getDescription();
    }

    /**
    * Sets the description of this component.
    *
    * @param description the description of this component
    */
    public void setDescription(java.lang.String description) {
        _component.setDescription(description);
    }

    /**
    * Returns the copyright of this component.
    *
    * @return the copyright of this component
    */
    public java.lang.String getCopyright() {
        return _component.getCopyright();
    }

    /**
    * Returns the localized copyright of this component in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized copyright of this component
    */
    public java.lang.String getCopyright(java.util.Locale locale) {
        return _component.getCopyright(locale);
    }

    /**
    * Returns the localized copyright of this component in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized copyright of this component. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getCopyright(java.util.Locale locale,
        boolean useDefault) {
        return _component.getCopyright(locale, useDefault);
    }

    /**
    * Returns the localized copyright of this component in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized copyright of this component
    */
    public java.lang.String getCopyright(java.lang.String languageId) {
        return _component.getCopyright(languageId);
    }

    /**
    * Returns the localized copyright of this component in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized copyright of this component
    */
    public java.lang.String getCopyright(java.lang.String languageId,
        boolean useDefault) {
        return _component.getCopyright(languageId, useDefault);
    }

    public java.lang.String getCopyrightCurrentLanguageId() {
        return _component.getCopyrightCurrentLanguageId();
    }

    public java.lang.String getCopyrightCurrentValue() {
        return _component.getCopyrightCurrentValue();
    }

    /**
    * Returns a map of the locales and localized copyrights of this component.
    *
    * @return the locales and localized copyrights of this component
    */
    public java.util.Map<java.util.Locale, java.lang.String> getCopyrightMap() {
        return _component.getCopyrightMap();
    }

    /**
    * Sets the copyright of this component.
    *
    * @param copyright the copyright of this component
    */
    public void setCopyright(java.lang.String copyright) {
        _component.setCopyright(copyright);
    }

    /**
    * Sets the localized copyright of this component in the language.
    *
    * @param copyright the localized copyright of this component
    * @param locale the locale of the language
    */
    public void setCopyright(java.lang.String copyright, java.util.Locale locale) {
        _component.setCopyright(copyright, locale);
    }

    /**
    * Sets the localized copyright of this component in the language, and sets the default locale.
    *
    * @param copyright the localized copyright of this component
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setCopyright(java.lang.String copyright,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _component.setCopyright(copyright, locale, defaultLocale);
    }

    public void setCopyrightCurrentLanguageId(java.lang.String languageId) {
        _component.setCopyrightCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized copyrights of this component from the map of locales and localized copyrights.
    *
    * @param copyrightMap the locales and localized copyrights of this component
    */
    public void setCopyrightMap(
        java.util.Map<java.util.Locale, java.lang.String> copyrightMap) {
        _component.setCopyrightMap(copyrightMap);
    }

    /**
    * Sets the localized copyrights of this component from the map of locales and localized copyrights, and sets the default locale.
    *
    * @param copyrightMap the locales and localized copyrights of this component
    * @param defaultLocale the default locale
    */
    public void setCopyrightMap(
        java.util.Map<java.util.Locale, java.lang.String> copyrightMap,
        java.util.Locale defaultLocale) {
        _component.setCopyrightMap(copyrightMap, defaultLocale);
    }

    /**
    * Returns the display width of this component.
    *
    * @return the display width of this component
    */
    public int getDisplayWidth() {
        return _component.getDisplayWidth();
    }

    /**
    * Sets the display width of this component.
    *
    * @param displayWidth the display width of this component
    */
    public void setDisplayWidth(int displayWidth) {
        _component.setDisplayWidth(displayWidth);
    }

    /**
    * Returns the display height of this component.
    *
    * @return the display height of this component
    */
    public int getDisplayHeight() {
        return _component.getDisplayHeight();
    }

    /**
    * Sets the display height of this component.
    *
    * @param displayHeight the display height of this component
    */
    public void setDisplayHeight(int displayHeight) {
        _component.setDisplayHeight(displayHeight);
    }

    /**
    * Returns the create date of this component.
    *
    * @return the create date of this component
    */
    public java.util.Date getCreateDate() {
        return _component.getCreateDate();
    }

    /**
    * Sets the create date of this component.
    *
    * @param createDate the create date of this component
    */
    public void setCreateDate(java.util.Date createDate) {
        _component.setCreateDate(createDate);
    }

    /**
    * Returns the removed of this component.
    *
    * @return the removed of this component
    */
    public boolean getRemoved() {
        return _component.getRemoved();
    }

    /**
    * Returns <code>true</code> if this component is removed.
    *
    * @return <code>true</code> if this component is removed; <code>false</code> otherwise
    */
    public boolean isRemoved() {
        return _component.isRemoved();
    }

    /**
    * Sets whether this component is removed.
    *
    * @param removed the removed of this component
    */
    public void setRemoved(boolean removed) {
        _component.setRemoved(removed);
    }

    /**
    * Returns the removed date of this component.
    *
    * @return the removed date of this component
    */
    public java.util.Date getRemovedDate() {
        return _component.getRemovedDate();
    }

    /**
    * Sets the removed date of this component.
    *
    * @param removedDate the removed date of this component
    */
    public void setRemovedDate(java.util.Date removedDate) {
        _component.setRemovedDate(removedDate);
    }

    /**
    * Returns the version of this component.
    *
    * @return the version of this component
    */
    public java.lang.String getVersion() {
        return _component.getVersion();
    }

    /**
    * Sets the version of this component.
    *
    * @param version the version of this component
    */
    public void setVersion(java.lang.String version) {
        _component.setVersion(version);
    }

    /**
    * Returns the version date of this component.
    *
    * @return the version date of this component
    */
    public java.util.Date getVersionDate() {
        return _component.getVersionDate();
    }

    /**
    * Sets the version date of this component.
    *
    * @param versionDate the version date of this component
    */
    public void setVersionDate(java.util.Date versionDate) {
        _component.setVersionDate(versionDate);
    }

    /**
    * Returns the price of this component.
    *
    * @return the price of this component
    */
    public double getPrice() {
        return _component.getPrice();
    }

    /**
    * Sets the price of this component.
    *
    * @param price the price of this component
    */
    public void setPrice(double price) {
        _component.setPrice(price);
    }

    /**
    * Returns the price unit of this component.
    *
    * @return the price unit of this component
    */
    public java.lang.String getPriceUnit() {
        return _component.getPriceUnit();
    }

    /**
    * Sets the price unit of this component.
    *
    * @param priceUnit the price unit of this component
    */
    public void setPriceUnit(java.lang.String priceUnit) {
        _component.setPriceUnit(priceUnit);
    }

    /**
    * Returns the price terms of this component.
    *
    * @return the price terms of this component
    */
    public java.lang.String getPriceTerms() {
        return _component.getPriceTerms();
    }

    /**
    * Sets the price terms of this component.
    *
    * @param priceTerms the price terms of this component
    */
    public void setPriceTerms(java.lang.String priceTerms) {
        _component.setPriceTerms(priceTerms);
    }

    /**
    * Returns the price expiration of this component.
    *
    * @return the price expiration of this component
    */
    public java.lang.String getPriceExpiration() {
        return _component.getPriceExpiration();
    }

    /**
    * Sets the price expiration of this component.
    *
    * @param priceExpiration the price expiration of this component
    */
    public void setPriceExpiration(java.lang.String priceExpiration) {
        _component.setPriceExpiration(priceExpiration);
    }

    public boolean isNew() {
        return _component.isNew();
    }

    public void setNew(boolean n) {
        _component.setNew(n);
    }

    public boolean isCachedModel() {
        return _component.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _component.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _component.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _component.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _component.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _component.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _component.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ComponentWrapper((Component) _component.clone());
    }

    public int compareTo(Component component) {
        return _component.compareTo(component);
    }

    @Override
    public int hashCode() {
        return _component.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Component> toCacheModel() {
        return _component.toCacheModel();
    }

    public Component toEscapedModel() {
        return new ComponentWrapper(_component.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _component.toString();
    }

    public java.lang.String toXmlString() {
        return _component.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _component.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Component getWrappedComponent() {
        return _component;
    }

    public Component getWrappedModel() {
        return _component;
    }

    public void resetOriginalValues() {
        _component.resetOriginalValues();
    }
}
