package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
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

import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.ComponentRecordModel;
import org.nterlearning.datamodel.catalog.model.ComponentRecordSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The base model implementation for the ComponentRecord service. Represents a row in the &quot;CATALOG_ComponentRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.nterlearning.datamodel.catalog.model.ComponentRecordModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ComponentRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecordImpl
 * @see org.nterlearning.datamodel.catalog.model.ComponentRecord
 * @see org.nterlearning.datamodel.catalog.model.ComponentRecordModel
 * @generated
 */
@JSON(strict = true)
public class ComponentRecordModelImpl extends BaseModelImpl<ComponentRecord>
    implements ComponentRecordModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a component record model instance should use the {@link org.nterlearning.datamodel.catalog.model.ComponentRecord} interface instead.
     */
    public static final String TABLE_NAME = "CATALOG_ComponentRecord";
    public static final Object[][] TABLE_COLUMNS = {
            { "componentRecordId", Types.BIGINT },
            { "courseRecordId", Types.BIGINT },
            { "componentIri", Types.VARCHAR },
            { "updatedDate", Types.TIMESTAMP },
            { "completionStatus", Types.VARCHAR },
            { "completionPercent", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table CATALOG_ComponentRecord (componentRecordId LONG not null primary key,courseRecordId LONG,componentIri VARCHAR(255) null,updatedDate DATE null,completionStatus VARCHAR(50) null,completionPercent INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table CATALOG_ComponentRecord";
    public static final String ORDER_BY_JPQL = " ORDER BY componentRecord.updatedDate ASC";
    public static final String ORDER_BY_SQL = " ORDER BY CATALOG_ComponentRecord.updatedDate ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.org.nterlearning.datamodel.catalog.model.ComponentRecord"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.org.nterlearning.datamodel.catalog.model.ComponentRecord"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.org.nterlearning.datamodel.catalog.model.ComponentRecord"),
            true);
    public static long COMPONENTIRI_COLUMN_BITMASK = 1L;
    public static long COURSERECORDID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.org.nterlearning.datamodel.catalog.model.ComponentRecord"));
    private static ClassLoader _classLoader = ComponentRecord.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            ComponentRecord.class
        };
    private long _componentRecordId;
    private long _courseRecordId;
    private long _originalCourseRecordId;
    private boolean _setOriginalCourseRecordId;
    private String _componentIri;
    private String _originalComponentIri;
    private Date _updatedDate;
    private String _completionStatus;
    private Integer _completionPercent;
    private transient ExpandoBridge _expandoBridge;
    private long _columnBitmask;
    private ComponentRecord _escapedModelProxy;

    public ComponentRecordModelImpl() {
    }

    /**
     * Converts the soap model instance into a normal model instance.
     *
     * @param soapModel the soap model instance to convert
     * @return the normal model instance
     */
    public static ComponentRecord toModel(ComponentRecordSoap soapModel) {
        ComponentRecord model = new ComponentRecordImpl();

        model.setComponentRecordId(soapModel.getComponentRecordId());
        model.setCourseRecordId(soapModel.getCourseRecordId());
        model.setComponentIri(soapModel.getComponentIri());
        model.setUpdatedDate(soapModel.getUpdatedDate());
        model.setCompletionStatus(soapModel.getCompletionStatus());
        model.setCompletionPercent(soapModel.getCompletionPercent());

        return model;
    }

    /**
     * Converts the soap model instances into normal model instances.
     *
     * @param soapModels the soap model instances to convert
     * @return the normal model instances
     */
    public static List<ComponentRecord> toModels(
        ComponentRecordSoap[] soapModels) {
        List<ComponentRecord> models = new ArrayList<ComponentRecord>(soapModels.length);

        for (ComponentRecordSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public long getPrimaryKey() {
        return _componentRecordId;
    }

    public void setPrimaryKey(long primaryKey) {
        setComponentRecordId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_componentRecordId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return ComponentRecord.class;
    }

    public String getModelClassName() {
        return ComponentRecord.class.getName();
    }

    @JSON
    public long getComponentRecordId() {
        return _componentRecordId;
    }

    public void setComponentRecordId(long componentRecordId) {
        _componentRecordId = componentRecordId;
    }

    @JSON
    public long getCourseRecordId() {
        return _courseRecordId;
    }

    public void setCourseRecordId(long courseRecordId) {
        _columnBitmask |= COURSERECORDID_COLUMN_BITMASK;

        if (!_setOriginalCourseRecordId) {
            _setOriginalCourseRecordId = true;

            _originalCourseRecordId = _courseRecordId;
        }

        _courseRecordId = courseRecordId;
    }

    public long getOriginalCourseRecordId() {
        return _originalCourseRecordId;
    }

    @JSON
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

    @JSON
    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _columnBitmask = -1L;

        _updatedDate = updatedDate;
    }

    @JSON
    public String getCompletionStatus() {
        if (_completionStatus == null) {
            return StringPool.BLANK;
        } else {
            return _completionStatus;
        }
    }

    public void setCompletionStatus(String completionStatus) {
        _completionStatus = completionStatus;
    }

    @JSON
    public Integer getCompletionPercent() {
        return _completionPercent;
    }

    public void setCompletionPercent(Integer completionPercent) {
        _completionPercent = completionPercent;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ComponentRecord toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (ComponentRecord) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
                    ComponentRecord.class.getName(), getPrimaryKey());
        }

        return _expandoBridge;
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        getExpandoBridge().setAttributes(serviceContext);
    }

    @Override
    public Object clone() {
        ComponentRecordImpl componentRecordImpl = new ComponentRecordImpl();

        componentRecordImpl.setComponentRecordId(getComponentRecordId());
        componentRecordImpl.setCourseRecordId(getCourseRecordId());
        componentRecordImpl.setComponentIri(getComponentIri());
        componentRecordImpl.setUpdatedDate(getUpdatedDate());
        componentRecordImpl.setCompletionStatus(getCompletionStatus());
        componentRecordImpl.setCompletionPercent(getCompletionPercent());

        componentRecordImpl.resetOriginalValues();

        return componentRecordImpl;
    }

    public int compareTo(ComponentRecord componentRecord) {
        int value = 0;

        value = DateUtil.compareTo(getUpdatedDate(),
                componentRecord.getUpdatedDate());

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

        ComponentRecord componentRecord = null;

        try {
            componentRecord = (ComponentRecord) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = componentRecord.getPrimaryKey();

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
        ComponentRecordModelImpl componentRecordModelImpl = this;

        componentRecordModelImpl._originalCourseRecordId = componentRecordModelImpl._courseRecordId;

        componentRecordModelImpl._setOriginalCourseRecordId = false;

        componentRecordModelImpl._originalComponentIri = componentRecordModelImpl._componentIri;

        componentRecordModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<ComponentRecord> toCacheModel() {
        ComponentRecordCacheModel componentRecordCacheModel = new ComponentRecordCacheModel();

        componentRecordCacheModel.componentRecordId = getComponentRecordId();

        componentRecordCacheModel.courseRecordId = getCourseRecordId();

        componentRecordCacheModel.componentIri = getComponentIri();

        String componentIri = componentRecordCacheModel.componentIri;

        if ((componentIri != null) && (componentIri.length() == 0)) {
            componentRecordCacheModel.componentIri = null;
        }

        Date updatedDate = getUpdatedDate();

        if (updatedDate != null) {
            componentRecordCacheModel.updatedDate = updatedDate.getTime();
        } else {
            componentRecordCacheModel.updatedDate = Long.MIN_VALUE;
        }

        componentRecordCacheModel.completionStatus = getCompletionStatus();

        String completionStatus = componentRecordCacheModel.completionStatus;

        if ((completionStatus != null) && (completionStatus.length() == 0)) {
            componentRecordCacheModel.completionStatus = null;
        }

        componentRecordCacheModel.completionPercent = getCompletionPercent();

        return componentRecordCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{componentRecordId=");
        sb.append(getComponentRecordId());
        sb.append(", courseRecordId=");
        sb.append(getCourseRecordId());
        sb.append(", componentIri=");
        sb.append(getComponentIri());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", completionStatus=");
        sb.append(getCompletionStatus());
        sb.append(", completionPercent=");
        sb.append(getCompletionPercent());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.ComponentRecord");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>componentRecordId</column-name><column-value><![CDATA[");
        sb.append(getComponentRecordId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseRecordId</column-name><column-value><![CDATA[");
        sb.append(getCourseRecordId());
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
            "<column><column-name>completionStatus</column-name><column-value><![CDATA[");
        sb.append(getCompletionStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>completionPercent</column-name><column-value><![CDATA[");
        sb.append(getCompletionPercent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
