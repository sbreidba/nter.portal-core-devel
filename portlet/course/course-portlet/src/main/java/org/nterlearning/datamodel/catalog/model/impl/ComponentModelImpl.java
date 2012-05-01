package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.ComponentModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model implementation for the Component service. Represents a row in the &quot;CATALOG_Component&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.nterlearning.datamodel.catalog.model.ComponentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ComponentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentImpl
 * @see org.nterlearning.datamodel.catalog.model.Component
 * @see org.nterlearning.datamodel.catalog.model.ComponentModel
 * @generated
 */
public class ComponentModelImpl extends BaseModelImpl<Component>
    implements ComponentModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a component model instance should use the {@link org.nterlearning.datamodel.catalog.model.Component} interface instead.
     */
    public static final String TABLE_NAME = "CATALOG_Component";
    public static final Object[][] TABLE_COLUMNS = {
            { "componentId", Types.BIGINT },
            { "companyId", Types.BIGINT },
            { "groupId", Types.BIGINT },
            { "feedReferenceId", Types.BIGINT },
            { "componentIri", Types.VARCHAR },
            { "updatedDate", Types.TIMESTAMP },
            { "language", Types.VARCHAR },
            { "href", Types.VARCHAR },
            { "fullTextHref", Types.VARCHAR },
            { "title", Types.VARCHAR },
            { "description", Types.VARCHAR },
            { "copyright", Types.VARCHAR },
            { "displayWidth", Types.INTEGER },
            { "displayHeight", Types.INTEGER },
            { "createDate", Types.TIMESTAMP },
            { "removed", Types.BOOLEAN },
            { "removedDate", Types.TIMESTAMP },
            { "version", Types.VARCHAR },
            { "versionDate", Types.TIMESTAMP },
            { "price", Types.DOUBLE },
            { "priceUnit", Types.VARCHAR },
            { "priceTerms", Types.VARCHAR },
            { "priceExpiration", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table CATALOG_Component (componentId LONG not null primary key,companyId LONG,groupId LONG,feedReferenceId LONG,componentIri VARCHAR(255) null,updatedDate DATE null,language VARCHAR(10) null,href VARCHAR(3999) null,fullTextHref VARCHAR(3999) null,title VARCHAR(2000) null,description VARCHAR(3999) null,copyright STRING null,displayWidth INTEGER,displayHeight INTEGER,createDate DATE null,removed BOOLEAN,removedDate DATE null,version VARCHAR(75) null,versionDate DATE null,price DOUBLE,priceUnit VARCHAR(75) null,priceTerms VARCHAR(75) null,priceExpiration VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table CATALOG_Component";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.org.nterlearning.datamodel.catalog.model.Component"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.org.nterlearning.datamodel.catalog.model.Component"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.org.nterlearning.datamodel.catalog.model.Component"),
            true);
    public static long COMPANYID_COLUMN_BITMASK = 1L;
    public static long COMPONENTID_COLUMN_BITMASK = 2L;
    public static long COMPONENTIRI_COLUMN_BITMASK = 4L;
    public static long FEEDREFERENCEID_COLUMN_BITMASK = 8L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.org.nterlearning.datamodel.catalog.model.Component"));
    private static ClassLoader _classLoader = Component.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            Component.class
        };
    private long _componentId;
    private long _originalComponentId;
    private boolean _setOriginalComponentId;
    private long _companyId;
    private long _originalCompanyId;
    private boolean _setOriginalCompanyId;
    private long _groupId;
    private long _feedReferenceId;
    private long _originalFeedReferenceId;
    private boolean _setOriginalFeedReferenceId;
    private String _componentIri;
    private String _originalComponentIri;
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
    private transient ExpandoBridge _expandoBridge;
    private long _columnBitmask;
    private Component _escapedModelProxy;

    public ComponentModelImpl() {
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

    public Class<?> getModelClass() {
        return Component.class;
    }

    public String getModelClassName() {
        return Component.class.getName();
    }

    public long getComponentId() {
        return _componentId;
    }

    public void setComponentId(long componentId) {
        _columnBitmask |= COMPONENTID_COLUMN_BITMASK;

        if (!_setOriginalComponentId) {
            _setOriginalComponentId = true;

            _originalComponentId = _componentId;
        }

        _componentId = componentId;
    }

    public long getOriginalComponentId() {
        return _originalComponentId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _columnBitmask |= COMPANYID_COLUMN_BITMASK;

        if (!_setOriginalCompanyId) {
            _setOriginalCompanyId = true;

            _originalCompanyId = _companyId;
        }

        _companyId = companyId;
    }

    public long getOriginalCompanyId() {
        return _originalCompanyId;
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
        _columnBitmask |= FEEDREFERENCEID_COLUMN_BITMASK;

        if (!_setOriginalFeedReferenceId) {
            _setOriginalFeedReferenceId = true;

            _originalFeedReferenceId = _feedReferenceId;
        }

        _feedReferenceId = feedReferenceId;
    }

    public long getOriginalFeedReferenceId() {
        return _originalFeedReferenceId;
    }

    public String getComponentIri() {
        if (_componentIri == null) {
            return StringPool.BLANK;
        } else {
            return _componentIri;
        }
    }

    public void setComponentIri(String componentIri) {
        _columnBitmask |= COMPONENTIRI_COLUMN_BITMASK;

        if (_originalComponentIri == null) {
            _originalComponentIri = _componentIri;
        }

        _componentIri = componentIri;
    }

    public String getOriginalComponentIri() {
        return GetterUtil.getString(_originalComponentIri);
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getLanguage() {
        if (_language == null) {
            return StringPool.BLANK;
        } else {
            return _language;
        }
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getHref() {
        if (_href == null) {
            return StringPool.BLANK;
        } else {
            return _href;
        }
    }

    public void setHref(String href) {
        _href = href;
    }

    public String getFullTextHref() {
        if (_fullTextHref == null) {
            return StringPool.BLANK;
        } else {
            return _fullTextHref;
        }
    }

    public void setFullTextHref(String fullTextHref) {
        _fullTextHref = fullTextHref;
    }

    public String getTitle() {
        if (_title == null) {
            return StringPool.BLANK;
        } else {
            return _title;
        }
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDescription() {
        if (_description == null) {
            return StringPool.BLANK;
        } else {
            return _description;
        }
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getCopyright() {
        if (_copyright == null) {
            return StringPool.BLANK;
        } else {
            return _copyright;
        }
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

    @JSON
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

        Locale[] locales = LanguageUtil.getAvailableLocales();

        for (Locale locale : locales) {
            String copyright = copyrightMap.get(locale);

            setCopyright(copyright, locale, defaultLocale);
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
        if (_version == null) {
            return StringPool.BLANK;
        } else {
            return _version;
        }
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
        if (_priceUnit == null) {
            return StringPool.BLANK;
        } else {
            return _priceUnit;
        }
    }

    public void setPriceUnit(String priceUnit) {
        _priceUnit = priceUnit;
    }

    public String getPriceTerms() {
        if (_priceTerms == null) {
            return StringPool.BLANK;
        } else {
            return _priceTerms;
        }
    }

    public void setPriceTerms(String priceTerms) {
        _priceTerms = priceTerms;
    }

    public String getPriceExpiration() {
        if (_priceExpiration == null) {
            return StringPool.BLANK;
        } else {
            return _priceExpiration;
        }
    }

    public void setPriceExpiration(String priceExpiration) {
        _priceExpiration = priceExpiration;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public Component toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (Component) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
                    Component.class.getName(), getPrimaryKey());
        }

        return _expandoBridge;
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        getExpandoBridge().setAttributes(serviceContext);
    }

    @Override
    public Object clone() {
        ComponentImpl componentImpl = new ComponentImpl();

        componentImpl.setComponentId(getComponentId());
        componentImpl.setCompanyId(getCompanyId());
        componentImpl.setGroupId(getGroupId());
        componentImpl.setFeedReferenceId(getFeedReferenceId());
        componentImpl.setComponentIri(getComponentIri());
        componentImpl.setUpdatedDate(getUpdatedDate());
        componentImpl.setLanguage(getLanguage());
        componentImpl.setHref(getHref());
        componentImpl.setFullTextHref(getFullTextHref());
        componentImpl.setTitle(getTitle());
        componentImpl.setDescription(getDescription());
        componentImpl.setCopyright(getCopyright());
        componentImpl.setDisplayWidth(getDisplayWidth());
        componentImpl.setDisplayHeight(getDisplayHeight());
        componentImpl.setCreateDate(getCreateDate());
        componentImpl.setRemoved(getRemoved());
        componentImpl.setRemovedDate(getRemovedDate());
        componentImpl.setVersion(getVersion());
        componentImpl.setVersionDate(getVersionDate());
        componentImpl.setPrice(getPrice());
        componentImpl.setPriceUnit(getPriceUnit());
        componentImpl.setPriceTerms(getPriceTerms());
        componentImpl.setPriceExpiration(getPriceExpiration());

        componentImpl.resetOriginalValues();

        return componentImpl;
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

        Component component = null;

        try {
            component = (Component) obj;
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
    public void resetOriginalValues() {
        ComponentModelImpl componentModelImpl = this;

        componentModelImpl._originalComponentId = componentModelImpl._componentId;

        componentModelImpl._setOriginalComponentId = false;

        componentModelImpl._originalCompanyId = componentModelImpl._companyId;

        componentModelImpl._setOriginalCompanyId = false;

        componentModelImpl._originalFeedReferenceId = componentModelImpl._feedReferenceId;

        componentModelImpl._setOriginalFeedReferenceId = false;

        componentModelImpl._originalComponentIri = componentModelImpl._componentIri;

        componentModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<Component> toCacheModel() {
        ComponentCacheModel componentCacheModel = new ComponentCacheModel();

        componentCacheModel.componentId = getComponentId();

        componentCacheModel.companyId = getCompanyId();

        componentCacheModel.groupId = getGroupId();

        componentCacheModel.feedReferenceId = getFeedReferenceId();

        componentCacheModel.componentIri = getComponentIri();

        String componentIri = componentCacheModel.componentIri;

        if ((componentIri != null) && (componentIri.length() == 0)) {
            componentCacheModel.componentIri = null;
        }

        Date updatedDate = getUpdatedDate();

        if (updatedDate != null) {
            componentCacheModel.updatedDate = updatedDate.getTime();
        } else {
            componentCacheModel.updatedDate = Long.MIN_VALUE;
        }

        componentCacheModel.language = getLanguage();

        String language = componentCacheModel.language;

        if ((language != null) && (language.length() == 0)) {
            componentCacheModel.language = null;
        }

        componentCacheModel.href = getHref();

        String href = componentCacheModel.href;

        if ((href != null) && (href.length() == 0)) {
            componentCacheModel.href = null;
        }

        componentCacheModel.fullTextHref = getFullTextHref();

        String fullTextHref = componentCacheModel.fullTextHref;

        if ((fullTextHref != null) && (fullTextHref.length() == 0)) {
            componentCacheModel.fullTextHref = null;
        }

        componentCacheModel.title = getTitle();

        String title = componentCacheModel.title;

        if ((title != null) && (title.length() == 0)) {
            componentCacheModel.title = null;
        }

        componentCacheModel.description = getDescription();

        String description = componentCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            componentCacheModel.description = null;
        }

        componentCacheModel.copyright = getCopyright();

        String copyright = componentCacheModel.copyright;

        if ((copyright != null) && (copyright.length() == 0)) {
            componentCacheModel.copyright = null;
        }

        componentCacheModel.displayWidth = getDisplayWidth();

        componentCacheModel.displayHeight = getDisplayHeight();

        Date createDate = getCreateDate();

        if (createDate != null) {
            componentCacheModel.createDate = createDate.getTime();
        } else {
            componentCacheModel.createDate = Long.MIN_VALUE;
        }

        componentCacheModel.removed = getRemoved();

        Date removedDate = getRemovedDate();

        if (removedDate != null) {
            componentCacheModel.removedDate = removedDate.getTime();
        } else {
            componentCacheModel.removedDate = Long.MIN_VALUE;
        }

        componentCacheModel.version = getVersion();

        String version = componentCacheModel.version;

        if ((version != null) && (version.length() == 0)) {
            componentCacheModel.version = null;
        }

        Date versionDate = getVersionDate();

        if (versionDate != null) {
            componentCacheModel.versionDate = versionDate.getTime();
        } else {
            componentCacheModel.versionDate = Long.MIN_VALUE;
        }

        componentCacheModel.price = getPrice();

        componentCacheModel.priceUnit = getPriceUnit();

        String priceUnit = componentCacheModel.priceUnit;

        if ((priceUnit != null) && (priceUnit.length() == 0)) {
            componentCacheModel.priceUnit = null;
        }

        componentCacheModel.priceTerms = getPriceTerms();

        String priceTerms = componentCacheModel.priceTerms;

        if ((priceTerms != null) && (priceTerms.length() == 0)) {
            componentCacheModel.priceTerms = null;
        }

        componentCacheModel.priceExpiration = getPriceExpiration();

        String priceExpiration = componentCacheModel.priceExpiration;

        if ((priceExpiration != null) && (priceExpiration.length() == 0)) {
            componentCacheModel.priceExpiration = null;
        }

        return componentCacheModel;
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
