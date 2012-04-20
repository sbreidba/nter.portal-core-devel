package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.model.FeedReferenceModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the FeedReference service. Represents a row in the &quot;CATALOG_FeedReference&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.nterlearning.datamodel.catalog.model.FeedReferenceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FeedReferenceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferenceImpl
 * @see org.nterlearning.datamodel.catalog.model.FeedReference
 * @see org.nterlearning.datamodel.catalog.model.FeedReferenceModel
 * @generated
 */
public class FeedReferenceModelImpl extends BaseModelImpl<FeedReference>
    implements FeedReferenceModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a feed reference model instance should use the {@link org.nterlearning.datamodel.catalog.model.FeedReference} interface instead.
     */
    public static final String TABLE_NAME = "CATALOG_FeedReference";
    public static final Object[][] TABLE_COLUMNS = {
            { "feedReferenceId", Types.BIGINT },
            { "companyId", Types.BIGINT },
            { "groupId", Types.BIGINT },
            { "contentProviderId", Types.VARCHAR },
            { "href", Types.VARCHAR },
            { "hrefHash", Types.VARCHAR },
            { "pshb", Types.VARCHAR },
            { "pshbSubscribed", Types.BOOLEAN },
            { "feedIri", Types.VARCHAR },
            { "feedType", Types.VARCHAR },
            { "feedVersion", Types.VARCHAR },
            { "trustworthyWeight", Types.DOUBLE },
            { "createDate", Types.TIMESTAMP },
            { "removed", Types.BOOLEAN },
            { "removedDate", Types.TIMESTAMP },
            { "removedReason", Types.VARCHAR },
            { "syncDate", Types.TIMESTAMP },
            { "syncSuccess", Types.BOOLEAN }
        };
    public static final String TABLE_SQL_CREATE = "create table CATALOG_FeedReference (feedReferenceId LONG not null primary key,companyId LONG,groupId LONG,contentProviderId VARCHAR(255) null,href VARCHAR(3999) null,hrefHash VARCHAR(255) null,pshb VARCHAR(3999) null,pshbSubscribed BOOLEAN,feedIri VARCHAR(255) null,feedType VARCHAR(1) null,feedVersion VARCHAR(25) null,trustworthyWeight DOUBLE,createDate DATE null,removed BOOLEAN,removedDate DATE null,removedReason VARCHAR(1) null,syncDate DATE null,syncSuccess BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table CATALOG_FeedReference";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.org.nterlearning.datamodel.catalog.model.FeedReference"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.org.nterlearning.datamodel.catalog.model.FeedReference"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.org.nterlearning.datamodel.catalog.model.FeedReference"),
            true);
    public static long COMPANYID_COLUMN_BITMASK = 1L;
    public static long CONTENTPROVIDERID_COLUMN_BITMASK = 2L;
    public static long FEEDIRI_COLUMN_BITMASK = 4L;
    public static long FEEDTYPE_COLUMN_BITMASK = 8L;
    public static long FEEDVERSION_COLUMN_BITMASK = 16L;
    public static long GROUPID_COLUMN_BITMASK = 32L;
    public static long HREFHASH_COLUMN_BITMASK = 64L;
    public static long REMOVED_COLUMN_BITMASK = 128L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.org.nterlearning.datamodel.catalog.model.FeedReference"));
    private static ClassLoader _classLoader = FeedReference.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            FeedReference.class
        };
    private long _feedReferenceId;
    private long _companyId;
    private long _originalCompanyId;
    private boolean _setOriginalCompanyId;
    private long _groupId;
    private long _originalGroupId;
    private boolean _setOriginalGroupId;
    private String _contentProviderId;
    private String _originalContentProviderId;
    private String _href;
    private String _hrefHash;
    private String _originalHrefHash;
    private String _pshb;
    private boolean _pshbSubscribed;
    private String _feedIri;
    private String _originalFeedIri;
    private String _feedType;
    private String _originalFeedType;
    private String _feedVersion;
    private String _originalFeedVersion;
    private double _trustworthyWeight;
    private Date _createDate;
    private boolean _removed;
    private boolean _originalRemoved;
    private boolean _setOriginalRemoved;
    private Date _removedDate;
    private String _removedReason;
    private Date _syncDate;
    private boolean _syncSuccess;
    private transient ExpandoBridge _expandoBridge;
    private long _columnBitmask;
    private FeedReference _escapedModelProxy;

    public FeedReferenceModelImpl() {
    }

    public long getPrimaryKey() {
        return _feedReferenceId;
    }

    public void setPrimaryKey(long primaryKey) {
        setFeedReferenceId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_feedReferenceId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return FeedReference.class;
    }

    public String getModelClassName() {
        return FeedReference.class.getName();
    }

    public long getFeedReferenceId() {
        return _feedReferenceId;
    }

    public void setFeedReferenceId(long feedReferenceId) {
        _feedReferenceId = feedReferenceId;
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
        _columnBitmask |= GROUPID_COLUMN_BITMASK;

        if (!_setOriginalGroupId) {
            _setOriginalGroupId = true;

            _originalGroupId = _groupId;
        }

        _groupId = groupId;
    }

    public long getOriginalGroupId() {
        return _originalGroupId;
    }

    public String getContentProviderId() {
        if (_contentProviderId == null) {
            return StringPool.BLANK;
        } else {
            return _contentProviderId;
        }
    }

    public void setContentProviderId(String contentProviderId) {
        _columnBitmask |= CONTENTPROVIDERID_COLUMN_BITMASK;

        if (_originalContentProviderId == null) {
            _originalContentProviderId = _contentProviderId;
        }

        _contentProviderId = contentProviderId;
    }

    public String getOriginalContentProviderId() {
        return GetterUtil.getString(_originalContentProviderId);
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

    public String getHrefHash() {
        if (_hrefHash == null) {
            return StringPool.BLANK;
        } else {
            return _hrefHash;
        }
    }

    public void setHrefHash(String hrefHash) {
        _columnBitmask |= HREFHASH_COLUMN_BITMASK;

        if (_originalHrefHash == null) {
            _originalHrefHash = _hrefHash;
        }

        _hrefHash = hrefHash;
    }

    public String getOriginalHrefHash() {
        return GetterUtil.getString(_originalHrefHash);
    }

    public String getPshb() {
        if (_pshb == null) {
            return StringPool.BLANK;
        } else {
            return _pshb;
        }
    }

    public void setPshb(String pshb) {
        _pshb = pshb;
    }

    public boolean getPshbSubscribed() {
        return _pshbSubscribed;
    }

    public boolean isPshbSubscribed() {
        return _pshbSubscribed;
    }

    public void setPshbSubscribed(boolean pshbSubscribed) {
        _pshbSubscribed = pshbSubscribed;
    }

    public String getFeedIri() {
        if (_feedIri == null) {
            return StringPool.BLANK;
        } else {
            return _feedIri;
        }
    }

    public void setFeedIri(String feedIri) {
        _columnBitmask |= FEEDIRI_COLUMN_BITMASK;

        if (_originalFeedIri == null) {
            _originalFeedIri = _feedIri;
        }

        _feedIri = feedIri;
    }

    public String getOriginalFeedIri() {
        return GetterUtil.getString(_originalFeedIri);
    }

    public String getFeedType() {
        if (_feedType == null) {
            return StringPool.BLANK;
        } else {
            return _feedType;
        }
    }

    public void setFeedType(String feedType) {
        _columnBitmask |= FEEDTYPE_COLUMN_BITMASK;

        if (_originalFeedType == null) {
            _originalFeedType = _feedType;
        }

        _feedType = feedType;
    }

    public String getOriginalFeedType() {
        return GetterUtil.getString(_originalFeedType);
    }

    public String getFeedVersion() {
        if (_feedVersion == null) {
            return StringPool.BLANK;
        } else {
            return _feedVersion;
        }
    }

    public void setFeedVersion(String feedVersion) {
        _columnBitmask |= FEEDVERSION_COLUMN_BITMASK;

        if (_originalFeedVersion == null) {
            _originalFeedVersion = _feedVersion;
        }

        _feedVersion = feedVersion;
    }

    public String getOriginalFeedVersion() {
        return GetterUtil.getString(_originalFeedVersion);
    }

    public double getTrustworthyWeight() {
        return _trustworthyWeight;
    }

    public void setTrustworthyWeight(double trustworthyWeight) {
        _trustworthyWeight = trustworthyWeight;
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
        _columnBitmask |= REMOVED_COLUMN_BITMASK;

        if (!_setOriginalRemoved) {
            _setOriginalRemoved = true;

            _originalRemoved = _removed;
        }

        _removed = removed;
    }

    public boolean getOriginalRemoved() {
        return _originalRemoved;
    }

    public Date getRemovedDate() {
        return _removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        _removedDate = removedDate;
    }

    public String getRemovedReason() {
        if (_removedReason == null) {
            return StringPool.BLANK;
        } else {
            return _removedReason;
        }
    }

    public void setRemovedReason(String removedReason) {
        _removedReason = removedReason;
    }

    public Date getSyncDate() {
        return _syncDate;
    }

    public void setSyncDate(Date syncDate) {
        _syncDate = syncDate;
    }

    public boolean getSyncSuccess() {
        return _syncSuccess;
    }

    public boolean isSyncSuccess() {
        return _syncSuccess;
    }

    public void setSyncSuccess(boolean syncSuccess) {
        _syncSuccess = syncSuccess;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public FeedReference toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (FeedReference) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
                    FeedReference.class.getName(), getPrimaryKey());
        }

        return _expandoBridge;
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        getExpandoBridge().setAttributes(serviceContext);
    }

    @Override
    public Object clone() {
        FeedReferenceImpl feedReferenceImpl = new FeedReferenceImpl();

        feedReferenceImpl.setFeedReferenceId(getFeedReferenceId());
        feedReferenceImpl.setCompanyId(getCompanyId());
        feedReferenceImpl.setGroupId(getGroupId());
        feedReferenceImpl.setContentProviderId(getContentProviderId());
        feedReferenceImpl.setHref(getHref());
        feedReferenceImpl.setHrefHash(getHrefHash());
        feedReferenceImpl.setPshb(getPshb());
        feedReferenceImpl.setPshbSubscribed(getPshbSubscribed());
        feedReferenceImpl.setFeedIri(getFeedIri());
        feedReferenceImpl.setFeedType(getFeedType());
        feedReferenceImpl.setFeedVersion(getFeedVersion());
        feedReferenceImpl.setTrustworthyWeight(getTrustworthyWeight());
        feedReferenceImpl.setCreateDate(getCreateDate());
        feedReferenceImpl.setRemoved(getRemoved());
        feedReferenceImpl.setRemovedDate(getRemovedDate());
        feedReferenceImpl.setRemovedReason(getRemovedReason());
        feedReferenceImpl.setSyncDate(getSyncDate());
        feedReferenceImpl.setSyncSuccess(getSyncSuccess());

        feedReferenceImpl.resetOriginalValues();

        return feedReferenceImpl;
    }

    public int compareTo(FeedReference feedReference) {
        long primaryKey = feedReference.getPrimaryKey();

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

        FeedReference feedReference = null;

        try {
            feedReference = (FeedReference) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = feedReference.getPrimaryKey();

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
        FeedReferenceModelImpl feedReferenceModelImpl = this;

        feedReferenceModelImpl._originalCompanyId = feedReferenceModelImpl._companyId;

        feedReferenceModelImpl._setOriginalCompanyId = false;

        feedReferenceModelImpl._originalGroupId = feedReferenceModelImpl._groupId;

        feedReferenceModelImpl._setOriginalGroupId = false;

        feedReferenceModelImpl._originalContentProviderId = feedReferenceModelImpl._contentProviderId;

        feedReferenceModelImpl._originalHrefHash = feedReferenceModelImpl._hrefHash;

        feedReferenceModelImpl._originalFeedIri = feedReferenceModelImpl._feedIri;

        feedReferenceModelImpl._originalFeedType = feedReferenceModelImpl._feedType;

        feedReferenceModelImpl._originalFeedVersion = feedReferenceModelImpl._feedVersion;

        feedReferenceModelImpl._originalRemoved = feedReferenceModelImpl._removed;

        feedReferenceModelImpl._setOriginalRemoved = false;

        feedReferenceModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<FeedReference> toCacheModel() {
        FeedReferenceCacheModel feedReferenceCacheModel = new FeedReferenceCacheModel();

        feedReferenceCacheModel.feedReferenceId = getFeedReferenceId();

        feedReferenceCacheModel.companyId = getCompanyId();

        feedReferenceCacheModel.groupId = getGroupId();

        feedReferenceCacheModel.contentProviderId = getContentProviderId();

        String contentProviderId = feedReferenceCacheModel.contentProviderId;

        if ((contentProviderId != null) && (contentProviderId.length() == 0)) {
            feedReferenceCacheModel.contentProviderId = null;
        }

        feedReferenceCacheModel.href = getHref();

        String href = feedReferenceCacheModel.href;

        if ((href != null) && (href.length() == 0)) {
            feedReferenceCacheModel.href = null;
        }

        feedReferenceCacheModel.hrefHash = getHrefHash();

        String hrefHash = feedReferenceCacheModel.hrefHash;

        if ((hrefHash != null) && (hrefHash.length() == 0)) {
            feedReferenceCacheModel.hrefHash = null;
        }

        feedReferenceCacheModel.pshb = getPshb();

        String pshb = feedReferenceCacheModel.pshb;

        if ((pshb != null) && (pshb.length() == 0)) {
            feedReferenceCacheModel.pshb = null;
        }

        feedReferenceCacheModel.pshbSubscribed = getPshbSubscribed();

        feedReferenceCacheModel.feedIri = getFeedIri();

        String feedIri = feedReferenceCacheModel.feedIri;

        if ((feedIri != null) && (feedIri.length() == 0)) {
            feedReferenceCacheModel.feedIri = null;
        }

        feedReferenceCacheModel.feedType = getFeedType();

        String feedType = feedReferenceCacheModel.feedType;

        if ((feedType != null) && (feedType.length() == 0)) {
            feedReferenceCacheModel.feedType = null;
        }

        feedReferenceCacheModel.feedVersion = getFeedVersion();

        String feedVersion = feedReferenceCacheModel.feedVersion;

        if ((feedVersion != null) && (feedVersion.length() == 0)) {
            feedReferenceCacheModel.feedVersion = null;
        }

        feedReferenceCacheModel.trustworthyWeight = getTrustworthyWeight();

        Date createDate = getCreateDate();

        if (createDate != null) {
            feedReferenceCacheModel.createDate = createDate.getTime();
        } else {
            feedReferenceCacheModel.createDate = Long.MIN_VALUE;
        }

        feedReferenceCacheModel.removed = getRemoved();

        Date removedDate = getRemovedDate();

        if (removedDate != null) {
            feedReferenceCacheModel.removedDate = removedDate.getTime();
        } else {
            feedReferenceCacheModel.removedDate = Long.MIN_VALUE;
        }

        feedReferenceCacheModel.removedReason = getRemovedReason();

        String removedReason = feedReferenceCacheModel.removedReason;

        if ((removedReason != null) && (removedReason.length() == 0)) {
            feedReferenceCacheModel.removedReason = null;
        }

        Date syncDate = getSyncDate();

        if (syncDate != null) {
            feedReferenceCacheModel.syncDate = syncDate.getTime();
        } else {
            feedReferenceCacheModel.syncDate = Long.MIN_VALUE;
        }

        feedReferenceCacheModel.syncSuccess = getSyncSuccess();

        return feedReferenceCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{feedReferenceId=");
        sb.append(getFeedReferenceId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", contentProviderId=");
        sb.append(getContentProviderId());
        sb.append(", href=");
        sb.append(getHref());
        sb.append(", hrefHash=");
        sb.append(getHrefHash());
        sb.append(", pshb=");
        sb.append(getPshb());
        sb.append(", pshbSubscribed=");
        sb.append(getPshbSubscribed());
        sb.append(", feedIri=");
        sb.append(getFeedIri());
        sb.append(", feedType=");
        sb.append(getFeedType());
        sb.append(", feedVersion=");
        sb.append(getFeedVersion());
        sb.append(", trustworthyWeight=");
        sb.append(getTrustworthyWeight());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", removedReason=");
        sb.append(getRemovedReason());
        sb.append(", syncDate=");
        sb.append(getSyncDate());
        sb.append(", syncSuccess=");
        sb.append(getSyncSuccess());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.FeedReference");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>feedReferenceId</column-name><column-value><![CDATA[");
        sb.append(getFeedReferenceId());
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
            "<column><column-name>contentProviderId</column-name><column-value><![CDATA[");
        sb.append(getContentProviderId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>href</column-name><column-value><![CDATA[");
        sb.append(getHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hrefHash</column-name><column-value><![CDATA[");
        sb.append(getHrefHash());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pshb</column-name><column-value><![CDATA[");
        sb.append(getPshb());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pshbSubscribed</column-name><column-value><![CDATA[");
        sb.append(getPshbSubscribed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedIri</column-name><column-value><![CDATA[");
        sb.append(getFeedIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedType</column-name><column-value><![CDATA[");
        sb.append(getFeedType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feedVersion</column-name><column-value><![CDATA[");
        sb.append(getFeedVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>trustworthyWeight</column-name><column-value><![CDATA[");
        sb.append(getTrustworthyWeight());
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
            "<column><column-name>removedReason</column-name><column-value><![CDATA[");
        sb.append(getRemovedReason());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>syncDate</column-name><column-value><![CDATA[");
        sb.append(getSyncDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>syncSuccess</column-name><column-value><![CDATA[");
        sb.append(getSyncSuccess());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
