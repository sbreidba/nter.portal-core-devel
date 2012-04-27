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

import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.model.Courses_ComponentsModel;

import java.io.Serializable;

import java.sql.Types;

/**
 * The base model implementation for the Courses_Components service. Represents a row in the &quot;CATALOG_Courses_Components&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.nterlearning.datamodel.catalog.model.Courses_ComponentsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link Courses_ComponentsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Courses_ComponentsImpl
 * @see org.nterlearning.datamodel.catalog.model.Courses_Components
 * @see org.nterlearning.datamodel.catalog.model.Courses_ComponentsModel
 * @generated
 */
public class Courses_ComponentsModelImpl extends BaseModelImpl<Courses_Components>
    implements Courses_ComponentsModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a courses_ components model instance should use the {@link org.nterlearning.datamodel.catalog.model.Courses_Components} interface instead.
     */
    public static final String TABLE_NAME = "CATALOG_Courses_Components";
    public static final Object[][] TABLE_COLUMNS = {
            { "coursesComponentsId", Types.BIGINT },
            { "courseId", Types.BIGINT },
            { "courseIri", Types.VARCHAR },
            { "componentId", Types.BIGINT },
            { "componentIri", Types.VARCHAR },
            { "orderWeight", Types.DOUBLE },
            { "sectionType", Types.VARCHAR },
            { "componentType", Types.VARCHAR },
            { "mimeType", Types.VARCHAR },
            { "coursePaymentRequired", Types.BOOLEAN },
            { "componentPaymentRequired", Types.BOOLEAN }
        };
    public static final String TABLE_SQL_CREATE = "create table CATALOG_Courses_Components (coursesComponentsId LONG not null primary key,courseId LONG,courseIri VARCHAR(255) null,componentId LONG,componentIri VARCHAR(255) null,orderWeight DOUBLE,sectionType VARCHAR(50) null,componentType VARCHAR(50) null,mimeType VARCHAR(50) null,coursePaymentRequired BOOLEAN,componentPaymentRequired BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table CATALOG_Courses_Components";
    public static final String ORDER_BY_JPQL = " ORDER BY courses_Components.orderWeight ASC";
    public static final String ORDER_BY_SQL = " ORDER BY CATALOG_Courses_Components.orderWeight ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.org.nterlearning.datamodel.catalog.model.Courses_Components"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.org.nterlearning.datamodel.catalog.model.Courses_Components"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.org.nterlearning.datamodel.catalog.model.Courses_Components"),
            true);
    public static long COMPONENTID_COLUMN_BITMASK = 1L;
    public static long COMPONENTIRI_COLUMN_BITMASK = 2L;
    public static long COURSEID_COLUMN_BITMASK = 4L;
    public static long COURSEIRI_COLUMN_BITMASK = 8L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.org.nterlearning.datamodel.catalog.model.Courses_Components"));
    private static ClassLoader _classLoader = Courses_Components.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            Courses_Components.class
        };
    private long _coursesComponentsId;
    private long _courseId;
    private long _originalCourseId;
    private boolean _setOriginalCourseId;
    private String _courseIri;
    private String _originalCourseIri;
    private long _componentId;
    private long _originalComponentId;
    private boolean _setOriginalComponentId;
    private String _componentIri;
    private String _originalComponentIri;
    private double _orderWeight;
    private String _sectionType;
    private String _componentType;
    private String _mimeType;
    private boolean _coursePaymentRequired;
    private boolean _componentPaymentRequired;
    private transient ExpandoBridge _expandoBridge;
    private long _columnBitmask;
    private Courses_Components _escapedModelProxy;

    public Courses_ComponentsModelImpl() {
    }

    public long getPrimaryKey() {
        return _coursesComponentsId;
    }

    public void setPrimaryKey(long primaryKey) {
        setCoursesComponentsId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_coursesComponentsId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return Courses_Components.class;
    }

    public String getModelClassName() {
        return Courses_Components.class.getName();
    }

    public long getCoursesComponentsId() {
        return _coursesComponentsId;
    }

    public void setCoursesComponentsId(long coursesComponentsId) {
        _coursesComponentsId = coursesComponentsId;
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

    public double getOrderWeight() {
        return _orderWeight;
    }

    public void setOrderWeight(double orderWeight) {
        _columnBitmask = -1L;

        _orderWeight = orderWeight;
    }

    public String getSectionType() {
        if (_sectionType == null) {
            return StringPool.BLANK;
        } else {
            return _sectionType;
        }
    }

    public void setSectionType(String sectionType) {
        _sectionType = sectionType;
    }

    public String getComponentType() {
        if (_componentType == null) {
            return StringPool.BLANK;
        } else {
            return _componentType;
        }
    }

    public void setComponentType(String componentType) {
        _componentType = componentType;
    }

    public String getMimeType() {
        if (_mimeType == null) {
            return StringPool.BLANK;
        } else {
            return _mimeType;
        }
    }

    public void setMimeType(String mimeType) {
        _mimeType = mimeType;
    }

    public boolean getCoursePaymentRequired() {
        return _coursePaymentRequired;
    }

    public boolean isCoursePaymentRequired() {
        return _coursePaymentRequired;
    }

    public void setCoursePaymentRequired(boolean coursePaymentRequired) {
        _coursePaymentRequired = coursePaymentRequired;
    }

    public boolean getComponentPaymentRequired() {
        return _componentPaymentRequired;
    }

    public boolean isComponentPaymentRequired() {
        return _componentPaymentRequired;
    }

    public void setComponentPaymentRequired(boolean componentPaymentRequired) {
        _componentPaymentRequired = componentPaymentRequired;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public Courses_Components toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (Courses_Components) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
                    Courses_Components.class.getName(), getPrimaryKey());
        }

        return _expandoBridge;
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        getExpandoBridge().setAttributes(serviceContext);
    }

    @Override
    public Object clone() {
        Courses_ComponentsImpl courses_ComponentsImpl = new Courses_ComponentsImpl();

        courses_ComponentsImpl.setCoursesComponentsId(getCoursesComponentsId());
        courses_ComponentsImpl.setCourseId(getCourseId());
        courses_ComponentsImpl.setCourseIri(getCourseIri());
        courses_ComponentsImpl.setComponentId(getComponentId());
        courses_ComponentsImpl.setComponentIri(getComponentIri());
        courses_ComponentsImpl.setOrderWeight(getOrderWeight());
        courses_ComponentsImpl.setSectionType(getSectionType());
        courses_ComponentsImpl.setComponentType(getComponentType());
        courses_ComponentsImpl.setMimeType(getMimeType());
        courses_ComponentsImpl.setCoursePaymentRequired(getCoursePaymentRequired());
        courses_ComponentsImpl.setComponentPaymentRequired(getComponentPaymentRequired());

        courses_ComponentsImpl.resetOriginalValues();

        return courses_ComponentsImpl;
    }

    public int compareTo(Courses_Components courses_Components) {
        int value = 0;

        if (getOrderWeight() < courses_Components.getOrderWeight()) {
            value = -1;
        } else if (getOrderWeight() > courses_Components.getOrderWeight()) {
            value = 1;
        } else {
            value = 0;
        }

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

        Courses_Components courses_Components = null;

        try {
            courses_Components = (Courses_Components) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = courses_Components.getPrimaryKey();

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
        Courses_ComponentsModelImpl courses_ComponentsModelImpl = this;

        courses_ComponentsModelImpl._originalCourseId = courses_ComponentsModelImpl._courseId;

        courses_ComponentsModelImpl._setOriginalCourseId = false;

        courses_ComponentsModelImpl._originalCourseIri = courses_ComponentsModelImpl._courseIri;

        courses_ComponentsModelImpl._originalComponentId = courses_ComponentsModelImpl._componentId;

        courses_ComponentsModelImpl._setOriginalComponentId = false;

        courses_ComponentsModelImpl._originalComponentIri = courses_ComponentsModelImpl._componentIri;

        courses_ComponentsModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<Courses_Components> toCacheModel() {
        Courses_ComponentsCacheModel courses_ComponentsCacheModel = new Courses_ComponentsCacheModel();

        courses_ComponentsCacheModel.coursesComponentsId = getCoursesComponentsId();

        courses_ComponentsCacheModel.courseId = getCourseId();

        courses_ComponentsCacheModel.courseIri = getCourseIri();

        String courseIri = courses_ComponentsCacheModel.courseIri;

        if ((courseIri != null) && (courseIri.length() == 0)) {
            courses_ComponentsCacheModel.courseIri = null;
        }

        courses_ComponentsCacheModel.componentId = getComponentId();

        courses_ComponentsCacheModel.componentIri = getComponentIri();

        String componentIri = courses_ComponentsCacheModel.componentIri;

        if ((componentIri != null) && (componentIri.length() == 0)) {
            courses_ComponentsCacheModel.componentIri = null;
        }

        courses_ComponentsCacheModel.orderWeight = getOrderWeight();

        courses_ComponentsCacheModel.sectionType = getSectionType();

        String sectionType = courses_ComponentsCacheModel.sectionType;

        if ((sectionType != null) && (sectionType.length() == 0)) {
            courses_ComponentsCacheModel.sectionType = null;
        }

        courses_ComponentsCacheModel.componentType = getComponentType();

        String componentType = courses_ComponentsCacheModel.componentType;

        if ((componentType != null) && (componentType.length() == 0)) {
            courses_ComponentsCacheModel.componentType = null;
        }

        courses_ComponentsCacheModel.mimeType = getMimeType();

        String mimeType = courses_ComponentsCacheModel.mimeType;

        if ((mimeType != null) && (mimeType.length() == 0)) {
            courses_ComponentsCacheModel.mimeType = null;
        }

        courses_ComponentsCacheModel.coursePaymentRequired = getCoursePaymentRequired();

        courses_ComponentsCacheModel.componentPaymentRequired = getComponentPaymentRequired();

        return courses_ComponentsCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{coursesComponentsId=");
        sb.append(getCoursesComponentsId());
        sb.append(", courseId=");
        sb.append(getCourseId());
        sb.append(", courseIri=");
        sb.append(getCourseIri());
        sb.append(", componentId=");
        sb.append(getComponentId());
        sb.append(", componentIri=");
        sb.append(getComponentIri());
        sb.append(", orderWeight=");
        sb.append(getOrderWeight());
        sb.append(", sectionType=");
        sb.append(getSectionType());
        sb.append(", componentType=");
        sb.append(getComponentType());
        sb.append(", mimeType=");
        sb.append(getMimeType());
        sb.append(", coursePaymentRequired=");
        sb.append(getCoursePaymentRequired());
        sb.append(", componentPaymentRequired=");
        sb.append(getComponentPaymentRequired());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("org.nterlearning.datamodel.catalog.model.Courses_Components");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>coursesComponentsId</column-name><column-value><![CDATA[");
        sb.append(getCoursesComponentsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseId</column-name><column-value><![CDATA[");
        sb.append(getCourseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>courseIri</column-name><column-value><![CDATA[");
        sb.append(getCourseIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentId</column-name><column-value><![CDATA[");
        sb.append(getComponentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentIri</column-name><column-value><![CDATA[");
        sb.append(getComponentIri());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderWeight</column-name><column-value><![CDATA[");
        sb.append(getOrderWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sectionType</column-name><column-value><![CDATA[");
        sb.append(getSectionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentType</column-name><column-value><![CDATA[");
        sb.append(getComponentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mimeType</column-name><column-value><![CDATA[");
        sb.append(getMimeType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>coursePaymentRequired</column-name><column-value><![CDATA[");
        sb.append(getCoursePaymentRequired());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentPaymentRequired</column-name><column-value><![CDATA[");
        sb.append(getComponentPaymentRequired());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
