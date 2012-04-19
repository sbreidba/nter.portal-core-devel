package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReviewModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the GlobalCourseReview service. Represents a row in the &quot;CATALOG_GlobalCourseReview&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.nterlearning.datamodel.catalog.model.GlobalCourseReviewModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GlobalCourseReviewImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReviewImpl
 * @see org.nterlearning.datamodel.catalog.model.GlobalCourseReview
 * @see org.nterlearning.datamodel.catalog.model.GlobalCourseReviewModel
 * @generated
 */
public class GlobalCourseReviewModelImpl extends BaseModelImpl<GlobalCourseReview>
    implements GlobalCourseReviewModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a global course review model instance should use the {@link org.nterlearning.datamodel.catalog.model.GlobalCourseReview} interface instead.
     */
    public static final String TABLE_NAME = "CATALOG_GlobalCourseReview";
    public static final Object[][] TABLE_COLUMNS = {
            { "globalCourseReviewId", Types.BIGINT },
            { "companyId", Types.BIGINT },
            { "groupId", Types.BIGINT },
            { "courseReviewIri", Types.VARCHAR },
            { "updatedDate", Types.TIMESTAMP },
            { "courseIri", Types.VARCHAR },
            { "href", Types.VARCHAR },
            { "nterInstance", Types.VARCHAR },
            { "courseId", Types.BIGINT },
            { "userDisplayName", Types.VARCHAR },
            { "singleSignOnValue", Types.VARCHAR },
            { "summary", Types.VARCHAR },
            { "content", Types.VARCHAR },
            { "createDate", Types.TIMESTAMP },
            { "modifiedDate", Types.TIMESTAMP },
            { "starScore", Types.DOUBLE },
            { "fromTrustedReviewer", Types.BOOLEAN },
            { "removed", Types.BOOLEAN },
            { "removedDate", Types.TIMESTAMP },
            { "isHidden", Types.BOOLEAN }
        };
    public static final String TABLE_SQL_CREATE = "create table CATALOG_GlobalCourseReview (globalCourseReviewId LONG not null primary key,companyId LONG,groupId LONG,courseReviewIri VARCHAR(255) null,updatedDate DATE null,courseIri VARCHAR(255) null,href VARCHAR(3999) null,nterInstance VARCHAR(75) null,courseId LONG,userDisplayName VARCHAR(255) null,singleSignOnValue VARCHAR(255) null,summary VARCHAR(250) null,content VARCHAR(3999) null,createDate DATE null,modifiedDate DATE null,starScore DOUBLE,fromTrustedReviewer BOOLEAN,removed BOOLEAN,removedDate DATE null,isHidden BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table CATALOG_GlobalCourseReview";
    public static final String ORDER_BY_JPQL = " ORDER BY globalCourseReview.fromTrustedReviewer DESC, globalCourseReview.starScore DESC, globalCourseReview.modifiedDate DESC";
    public static final String ORDER_BY_SQL = " ORDER BY CATALOG_GlobalCourseReview.fromTrustedReviewer DESC, CATALOG_GlobalCourseReview.starScore DESC, CATALOG_GlobalCourseReview.modifiedDate DESC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.org.nterlearning.datamodel.catalog.model.GlobalCourseReview"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.org.nterlearning.datamodel.catalog.model.GlobalCourseReview"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.org.nterlearning.datamodel.catalog.model.GlobalCourseReview"),
            true);
    public static long COMPANYID_COLUMN_BITMASK = 1L;
    public static long COURSEID_COLUMN_BITMASK = 2L;
    public static long COURSEIRI_COLUMN_BITMASK = 4L;
    public static long COURSEREVIEWIRI_COLUMN_BITMASK = 8L;
    public static long GROUPID_COLUMN_BITMASK = 16L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.org.nterlearning.datamodel.catalog.model.GlobalCourseReview"));
    private static ClassLoader _classLoader = GlobalCourseReview.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            GlobalCourseReview.class
        };
    private long _globalCourseReviewId;
    private long _companyId;
    private long _originalCompanyId;
    private boolean _setOriginalCompanyId;
    private long _groupId;
    private long _originalGroupId;
    private boolean _setOriginalGroupId;
    private String _courseReviewIri;
    private String _originalCourseReviewIri;
    private Date _updatedDate;
    private String _courseIri;
    private String _originalCourseIri;
    private String _href;
    private String _nterInstance;
    private long _courseId;
    private long _originalCourseId;
    private boolean _setOriginalCourseId;
    private String _userDisplayName;
    private String _singleSignOnValue;
    private String _summary;
    private String _content;
    private Date _createDate;
    private Date _modifiedDate;
    private double _starScore;
    private boolean _fromTrustedReviewer;
    private boolean _removed;
    private Date _removedDate;
    private boolean _isHidden;
    private transient ExpandoBridge _expandoBridge;
    private long _columnBitmask;
    private GlobalCourseReview _escapedModelProxy;

    public GlobalCourseReviewModelImpl() {
    }

    public long getPrimaryKey() {
        return _globalCourseReviewId;
    }

    public void setPrimaryKey(long primaryKey) {
        setGlobalCourseReviewId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_globalCourseReviewId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return GlobalCourseReview.class;
    }

    public String getModelClassName() {
        return GlobalCourseReview.class.getName();
    }

    public long getGlobalCourseReviewId() {
        return _globalCourseReviewId;
    }

    public void setGlobalCourseReviewId(long globalCourseReviewId) {
        _globalCourseReviewId = globalCourseReviewId;
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

    public String getCourseReviewIri() {
        if (_courseReviewIri == null) {
            return StringPool.BLANK;
        } else {
            return _courseReviewIri;
        }
    }

    public void setCourseReviewIri(String courseReviewIri) {
        _columnBitmask |= COURSEREVIEWIRI_COLUMN_BITMASK;

        if (_originalCourseReviewIri == null) {
            _originalCourseReviewIri = _courseReviewIri;
        }

        _courseReviewIri = courseReviewIri;
    }

    public String getOriginalCourseReviewIri() {
        return GetterUtil.getString(_originalCourseReviewIri);
    }

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
    }

    public String getCourseIri() {
        if (_courseIri == null) {
            return StringPool.BLANK;
        } else {
            return _courseIri;
        }
    }

    public void setCourseIri(String courseIri) {
        _columnBitmask |= COURSEIRI_COLUMN_BITMASK;

        if (_originalCourseIri == null) {
            _originalCourseIri = _courseIri;
        }

        _courseIri = courseIri;
    }

    public String getOriginalCourseIri() {
        return GetterUtil.getString(_originalCourseIri);
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

    public String getNterInstance() {
        if (_nterInstance == null) {
            return StringPool.BLANK;
        } else {
            return _nterInstance;
        }
    }

    public void setNterInstance(String nterInstance) {
        _nterInstance = nterInstance;
    }

    public long getCourseId() {
        return _courseId;
    }

    public void setCourseId(long courseId) {
        _columnBitmask |= COURSEID_COLUMN_BITMASK;

        if (!_setOriginalCourseId) {
            _setOriginalCourseId = true;

            _originalCourseId = _courseId;
        }

        _courseId = courseId;
    }

    public long getOriginalCourseId() {
        return _originalCourseId;
    }

    public String getUserDisplayName() {
        if (_userDisplayName == null) {
            return StringPool.BLANK;
        } else {
            return _userDisplayName;
        }
    }

    public void setUserDisplayName(String userDisplayName) {
        _userDisplayName = userDisplayName;
    }

    public String getSingleSignOnValue() {
        if (_singleSignOnValue == null) {
            return StringPool.BLANK;
        } else {
            return _singleSignOnValue;
        }
    }

    public void setSingleSignOnValue(String singleSignOnValue) {
        _singleSignOnValue = singleSignOnValue;
    }

    public String getSummary() {
        if (_summary == null) {
            return StringPool.BLANK;
        } else {
            return _summary;
        }
    }

    public void setSummary(String summary) {
        _summary = summary;
    }

    public String getContent() {
        if (_content == null) {
            return StringPool.BLANK;
        } else {
            return _content;
        }
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _columnBitmask = -1L;

        _modifiedDate = modifiedDate;
    }

    public double getStarScore() {
        return _starScore;
    }

    public void setStarScore(double starScore) {
        _columnBitmask = -1L;

        _starScore = starScore;
    }

    public boolean getFromTrustedReviewer() {
        return _fromTrustedReviewer;
    }

    public boolean isFromTrustedReviewer() {
        return _fromTrustedReviewer;
    }

    public void setFromTrustedReviewer(boolean fromTrustedReviewer) {
        _columnBitmask = -1L;

        _fromTrustedReviewer = fromTrustedReviewer;
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

    public boolean getIsHidden() {
        return _isHidden;
    }

    public boolean isIsHidden() {
        return _isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        _isHidden = isHidden;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public GlobalCourseReview toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (GlobalCourseReview) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
                    GlobalCourseReview.class.getName(), getPrimaryKey());
        }

        return _expandoBridge;
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        getExpandoBridge().setAttributes(serviceContext);
    }

    @Override
    public Object clone() {
        GlobalCourseReviewImpl globalCourseReviewImpl = new GlobalCourseReviewImpl();

        globalCourseReviewImpl.setGlobalCourseReviewId(getGlobalCourseReviewId());
        globalCourseReviewImpl.setCompanyId(getCompanyId());
        globalCourseReviewImpl.setGroupId(getGroupId());
        globalCourseReviewImpl.setCourseReviewIri(getCourseReviewIri());
        globalCourseReviewImpl.setUpdatedDate(getUpdatedDate());
        globalCourseReviewImpl.setCourseIri(getCourseIri());
        globalCourseReviewImpl.setHref(getHref());
        globalCourseReviewImpl.setNterInstance(getNterInstance());
        globalCourseReviewImpl.setCourseId(getCourseId());
        globalCourseReviewImpl.setUserDisplayName(getUserDisplayName());
        globalCourseReviewImpl.setSingleSignOnValue(getSingleSignOnValue());
        globalCourseReviewImpl.setSummary(getSummary());
        globalCourseReviewImpl.setContent(getContent());
        globalCourseReviewImpl.setCreateDate(getCreateDate());
        globalCourseReviewImpl.setModifiedDate(getModifiedDate());
        globalCourseReviewImpl.setStarScore(getStarScore());
        globalCourseReviewImpl.setFromTrustedReviewer(getFromTrustedReviewer());
        globalCourseReviewImpl.setRemoved(getRemoved());
        globalCourseReviewImpl.setRemovedDate(getRemovedDate());
        globalCourseReviewImpl.setIsHidden(getIsHidden());

        globalCourseReviewImpl.resetOriginalValues();

        return globalCourseReviewImpl;
    }

    public int compareTo(GlobalCourseReview globalCourseReview) {
        int value = 0;

        if (getFromTrustedReviewer() == globalCourseReview.getFromTrustedReviewer()) {
            value = -1;
        } else if (getFromTrustedReviewer() != globalCourseReview.getFromTrustedReviewer()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        if (getStarScore() < globalCourseReview.getStarScore()) {
            value = -1;
        } else if (getStarScore() > globalCourseReview.getStarScore()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getModifiedDate(),
                globalCourseReview.getModifiedDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        GlobalCourseReview globalCourseReview = null;

        try {
            globalCourseReview = (GlobalCourseReview) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = globalCourseReview.getPrimaryKey();

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
        GlobalCourseReviewModelImpl globalCourseReviewModelImpl = this;

        globalCourseReviewModelImpl._originalCompanyId = globalCourseReviewModelImpl._companyId;

        globalCourseReviewModelImpl._setOriginalCompanyId = false;

        globalCourseReviewModelImpl._originalGroupId = globalCourseReviewModelImpl._groupId;

        globalCourseReviewModelImpl._setOriginalGroupId = false;

        globalCourseReviewModelImpl._originalCourseReviewIri = globalCourseReviewModelImpl._courseReviewIri;

        globalCourseReviewModelImpl._originalCourseIri = globalCourseReviewModelImpl._courseIri;

        globalCourseReviewModelImpl._originalCourseId = globalCourseReviewModelImpl._courseId;

        globalCourseReviewModelImpl._setOriginalCourseId = false;

        globalCourseReviewModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<GlobalCourseReview> toCacheModel() {
        GlobalCourseReviewCacheModel globalCourseReviewCacheModel = new GlobalCourseReviewCacheModel();

        globalCourseReviewCacheModel.globalCourseReviewId = getGlobalCourseReviewId();

        globalCourseReviewCacheModel.companyId = getCompanyId();

        globalCourseReviewCacheModel.groupId = getGroupId();

        globalCourseReviewCacheModel.courseReviewIri = getCourseReviewIri();

        String courseReviewIri = globalCourseReviewCacheModel.courseReviewIri;

        if ((courseReviewIri != null) && (courseReviewIri.length() == 0)) {
            globalCourseReviewCacheModel.courseReviewIri = null;
        }

        Date updatedDate = getUpdatedDate();

        if (updatedDate != null) {
            globalCourseReviewCacheModel.updatedDate = updatedDate.getTime();
        } else {
            globalCourseReviewCacheModel.updatedDate = Long.MIN_VALUE;
        }

        globalCourseReviewCacheModel.courseIri = getCourseIri();

        String courseIri = globalCourseReviewCacheModel.courseIri;

        if ((courseIri != null) && (courseIri.length() == 0)) {
            globalCourseReviewCacheModel.courseIri = null;
        }

        globalCourseReviewCacheModel.href = getHref();

        String href = globalCourseReviewCacheModel.href;

        if ((href != null) && (href.length() == 0)) {
            globalCourseReviewCacheModel.href = null;
        }

        globalCourseReviewCacheModel.nterInstance = getNterInstance();

        String nterInstance = globalCourseReviewCacheModel.nterInstance;

        if ((nterInstance != null) && (nterInstance.length() == 0)) {
            globalCourseReviewCacheModel.nterInstance = null;
        }

        globalCourseReviewCacheModel.courseId = getCourseId();

        globalCourseReviewCacheModel.userDisplayName = getUserDisplayName();

        String userDisplayName = globalCourseReviewCacheModel.userDisplayName;

        if ((userDisplayName != null) && (userDisplayName.length() == 0)) {
            globalCourseReviewCacheModel.userDisplayName = null;
        }

        globalCourseReviewCacheModel.singleSignOnValue = getSingleSignOnValue();

        String singleSignOnValue = globalCourseReviewCacheModel.singleSignOnValue;

        if ((singleSignOnValue != null) && (singleSignOnValue.length() == 0)) {
            globalCourseReviewCacheModel.singleSignOnValue = null;
        }

        globalCourseReviewCacheModel.summary = getSummary();

        String summary = globalCourseReviewCacheModel.summary;

        if ((summary != null) && (summary.length() == 0)) {
            globalCourseReviewCacheModel.summary = null;
        }

        globalCourseReviewCacheModel.content = getContent();

        String content = globalCourseReviewCacheModel.content;

        if ((content != null) && (content.length() == 0)) {
            globalCourseReviewCacheModel.content = null;
        }

        Date createDate = getCreateDate();

        if (createDate != null) {
            globalCourseReviewCacheModel.createDate = createDate.getTime();
        } else {
            globalCourseReviewCacheModel.createDate = Long.MIN_VALUE;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            globalCourseReviewCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            globalCourseReviewCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        globalCourseReviewCacheModel.starScore = getStarScore();

        globalCourseReviewCacheModel.fromTrustedReviewer = getFromTrustedReviewer();

        globalCourseReviewCacheModel.removed = getRemoved();

        Date removedDate = getRemovedDate();

        if (removedDate != null) {
            globalCourseReviewCacheModel.removedDate = removedDate.getTime();
        } else {
            globalCourseReviewCacheModel.removedDate = Long.MIN_VALUE;
        }

        globalCourseReviewCacheModel.isHidden = getIsHidden();

        return globalCourseReviewCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(41);

        sb.append("{globalCourseReviewId=");
        sb.append(getGlobalCourseReviewId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", courseReviewIri=");
        sb.append(getCourseReviewIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", courseIri=");
        sb.append(getCourseIri());
        sb.append(", href=");
        sb.append(getHref());
        sb.append(", nterInstance=");
        sb.append(getNterInstance());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", userDisplayName=");
        sb.append(getUserDisplayName());
        sb.append(", singleSignOnValue=");
        sb.append(getSingleSignOnValue());
        sb.append(", summary=");
        sb.append(getSummary());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", starScore=");
        sb.append(getStarScore());
        sb.append(", fromTrustedReviewer=");
        sb.append(getFromTrustedReviewer());
        sb.append(", removed=");
        sb.append(getRemoved());
        sb.append(", removedDate=");
        sb.append(getRemovedDate());
        sb.append(", isHidden=");
        sb.append(getIsHidden());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(64);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.GlobalCourseReview");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>globalCourseReviewId</column-name><column-value><![CDATA[");
        sb.append(getGlobalCourseReviewId());
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
            "<column><column-name>courseReviewIri</column-name><column-value><![CDATA[");
        sb.append(getCourseReviewIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updatedDate</column-name><column-value><![CDATA[");
        sb.append(getUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseIri</column-name><column-value><![CDATA[");
        sb.append(getCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>href</column-name><column-value><![CDATA[");
        sb.append(getHref());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nterInstance</column-name><column-value><![CDATA[");
        sb.append(getNterInstance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userDisplayName</column-name><column-value><![CDATA[");
        sb.append(getUserDisplayName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>singleSignOnValue</column-name><column-value><![CDATA[");
        sb.append(getSingleSignOnValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>summary</column-name><column-value><![CDATA[");
        sb.append(getSummary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>starScore</column-name><column-value><![CDATA[");
        sb.append(getStarScore());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromTrustedReviewer</column-name><column-value><![CDATA[");
        sb.append(getFromTrustedReviewer());
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
            "<column><column-name>isHidden</column-name><column-value><![CDATA[");
        sb.append(getIsHidden());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
