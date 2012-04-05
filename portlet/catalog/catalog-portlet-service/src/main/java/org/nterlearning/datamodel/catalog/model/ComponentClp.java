package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.Locale;
import java.util.Map;


public class ComponentClp extends BaseModelImpl<Component> implements Component {
    private long _componentId;
    private long _companyId;
    private long _groupId;
    private long _feedReferenceId;
    private String _componentIri;
    private Date _updatedDate;
    private String _language;
    private String _href;
    private String _fullTextHref;
    private String _title;
    private String _description;
    private String _copyright;
    private String _copyrightCurrentLanguageId;
    private int _displayWidth;
    private int _displayHeight;
    private Date _createDate;
    private boolean _removed;
    private Date _removedDate;
    private String _version;
    private Date _versionDate;
    private double _price;
    private String _priceUnit;
    private String _priceTerms;
    private String _priceExpiration;

    public ComponentClp() {
    }

    public Class<?> getModelClass() {
        return Component.class;
    }

    public String getModelClassName() {
        return Component.class.getName();
    }

    public long getPrimaryKey() {
        return _componentId;
    }

    public void setPrimaryKey(long primaryKey) {
        setComponentId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_componentId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getComponentId() {
        return _componentId;
    }

    public void setComponentId(long componentId) {
        _componentId = componentId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
    }

    public String getComponentIri() {
        return _componentIri;
    }

    public void setComponentIri(String componentIri) {
        _componentIri = componentIri;
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getHref() {
        return _href;
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getFullTextHref() {
        return _fullTextHref;
    }

    public void setFullTextHref(String fullTextHref) {
        _fullTextHref = fullTextHref;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getCopyright() {
        return _copyright;
    }

    public String getCopyright(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getCopyright(languageId);
    }

    public String getCopyright(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getCopyright(languageId, useDefault);
    }

    public String getCopyright(String languageId) {
        return LocalizationUtil.getLocalization(getCopyright(), languageId);
    }

    public String getCopyright(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getCopyright(), languageId,
            useDefault);
    }

    public String getCopyrightCurrentLanguageId() {
        return _copyrightCurrentLanguageId;
    }

    public String getCopyrightCurrentValue() {
        Locale locale = getLocale(_copyrightCurrentLanguageId);

        return getCopyright(locale);
    }

    public Map<Locale, String> getCopyrightMap() {
        return LocalizationUtil.getLocalizationMap(getCopyright());
    }

    public void setCopyright(String copyright) {
        _copyright = copyright;
    }

    public void setCopyright(String copyright, Locale locale) {
        setCopyright(copyright, locale, LocaleUtil.getDefault());
    }

    public void setCopyright(String copyright, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(copyright)) {
            setCopyright(LocalizationUtil.updateLocalization(getCopyright(),
                    "Copyright", copyright, languageId, defaultLanguageId));
        } else {
            setCopyright(LocalizationUtil.removeLocalization(getCopyright(),
                    "Copyright", languageId));
        }
    }

    public void setCopyrightCurrentLanguageId(String languageId) {
        _copyrightCurrentLanguageId = languageId;
    }

    public void setCopyrightMap(Map<Locale, String> copyrightMap) {
        setCopyrightMap(copyrightMap, LocaleUtil.getDefault());
    }

    public void setCopyrightMap(Map<Locale, String> copyrightMap,
        Locale defaultLocale) {
        if (copyrightMap == null) {
            return;
        }

        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(portalClassLoader);
            }

            Locale[] locales = LanguageUtil.getAvailableLocales();

            for (Locale locale : locales) {
                String copyright = copyrightMap.get(locale);

                setCopyright(copyright, locale, defaultLocale);
            }
        } finally {
            if (contextClassLoader != portalClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public int getDisplayWidth() {
        return _displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        _displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return _displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        _displayHeight = displayHeight;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public boolean getRemoved() {
        return _removed;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public void setRemoved(boolean removed) {
        _removed = removed;
    }

    public Date getRemovedDate() {
        return _removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        _removedDate = removedDate;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public Date getVersionDate() {
        return _versionDate;
    }

    public void setVersionDate(Date versionDate) {
        _versionDate = versionDate;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public String getPriceUnit() {
        return _priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        _priceUnit = priceUnit;
    }

    public String getPriceTerms() {
        return _priceTerms;
    }

    public void setPriceTerms(String priceTerms) {
        _priceTerms = priceTerms;
    }

    public String getPriceExpiration() {
        return _priceExpiration;
    }

    public void setPriceExpiration(String priceExpiration) {
        _priceExpiration = priceExpiration;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ComponentLocalServiceUtil.addComponent(this);
        } else {
            ComponentLocalServiceUtil.updateComponent(this);
        }
    }

    @Override
    public Component toEscapedModel() {
        return (Component) Proxy.newProxyInstance(Component.class.getClassLoader(),
            new Class[] { Component.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ComponentClp clone = new ComponentClp();

        clone.setComponentId(getComponentId());
        clone.setCompanyId(getCompanyId());
        clone.setGroupId(getGroupId());
        clone.setFeedReferenceId(getFeedReferenceId());
        clone.setComponentIri(getComponentIri());
        clone.setUpdatedDate(getUpdatedDate());
        clone.setLanguage(getLanguage());
        clone.setHref(getHref());
        clone.setFullTextHref(getFullTextHref());
        clone.setTitle(getTitle());
        clone.setDescription(getDescription());
        clone.setCopyright(getCopyright());
        clone.setDisplayWidth(getDisplayWidth());
        clone.setDisplayHeight(getDisplayHeight());
        clone.setCreateDate(getCreateDate());
        clone.setRemoved(getRemoved());
        clone.setRemovedDate(getRemovedDate());
        clone.setVersion(getVersion());
        clone.setVersionDate(getVersionDate());
        clone.setPrice(getPrice());
        clone.setPriceUnit(getPriceUnit());
        clone.setPriceTerms(getPriceTerms());
        clone.setPriceExpiration(getPriceExpiration());

        return clone;
    }

    public int compareTo(Component component) {
        long primaryKey = component.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ComponentClp component = null;

        try {
            component = (ComponentClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = component.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(47);

        sb.append("{componentId=");
        sb.append(getComponentId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", feedReferenceId=");
        sb.append(getFeedReferenceId());
        sb.append(", componentIri=");
        sb.append(getComponentIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", language=");
        sb.append(getLanguage());
        sb.append(", href=");
        sb.append(getHref());
        sb.append(", fullTextHref=");
        sb.append(getFullTextHref());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", copyright=");
        sb.append(getCopyright());
        sb.append(", displayWidth=");
        sb.append(getDisplayWidth());
        sb.append(", displayHeight=");
        sb.append(getDisplayHeight());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", versionDate=");
        sb.append(getVersionDate());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", priceUnit=");
        sb.append(getPriceUnit());
        sb.append(", priceTerms=");
        sb.append(getPriceTerms());
        sb.append(", priceExpiration=");
        sb.append(getPriceExpiration());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.Component");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>componentId</column-name><column-value><![CDATA[");
        sb.append(getComponentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedReferenceId</column-name><column-value><![CDATA[");
        sb.append(getFeedReferenceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentIri</column-name><column-value><![CDATA[");
        sb.append(getComponentIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updatedDate</column-name><column-value><![CDATA[");
        sb.append(getUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>language</column-name><column-value><![CDATA[");
        sb.append(getLanguage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>href</column-name><column-value><![CDATA[");
        sb.append(getHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fullTextHref</column-name><column-value><![CDATA[");
        sb.append(getFullTextHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>copyright</column-name><column-value><![CDATA[");
        sb.append(getCopyright());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayWidth</column-name><column-value><![CDATA[");
        sb.append(getDisplayWidth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayHeight</column-name><column-value><![CDATA[");
        sb.append(getDisplayHeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removed</column-name><column-value><![CDATA[");
        sb.append(getRemoved());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>removedDate</column-name><column-value><![CDATA[");
        sb.append(getRemovedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionDate</column-name><column-value><![CDATA[");
        sb.append(getVersionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceUnit</column-name><column-value><![CDATA[");
        sb.append(getPriceUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceTerms</column-name><column-value><![CDATA[");
        sb.append(getPriceTerms());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceExpiration</column-name><column-value><![CDATA[");
        sb.append(getPriceExpiration());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
