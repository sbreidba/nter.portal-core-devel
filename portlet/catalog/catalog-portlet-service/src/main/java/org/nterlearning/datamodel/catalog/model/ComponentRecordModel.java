package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ComponentRecord service. Represents a row in the &quot;CATALOG_ComponentRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecord
 * @see org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl
 * @generated
 */
public interface ComponentRecordModel extends BaseModel<ComponentRecord> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a component record model instance should use the {@link ComponentRecord} interface instead.
     */

    /**
     * Returns the primary key of this component record.
     *
     * @return the primary key of this component record
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this component record.
     *
     * @param primaryKey the primary key of this component record
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the component record ID of this component record.
     *
     * @return the component record ID of this component record
     */
    public long getComponentRecordId();

    /**
     * Sets the component record ID of this component record.
     *
     * @param componentRecordId the component record ID of this component record
     */
    public void setComponentRecordId(long componentRecordId);

    /**
     * Returns the course record ID of this component record.
     *
     * @return the course record ID of this component record
     */
    public long getCourseRecordId();

    /**
     * Sets the course record ID of this component record.
     *
     * @param courseRecordId the course record ID of this component record
     */
    public void setCourseRecordId(long courseRecordId);

    /**
     * Returns the component iri of this component record.
     *
     * @return the component iri of this component record
     */
    @AutoEscape
    public String getComponentIri();

    /**
     * Sets the component iri of this component record.
     *
     * @param componentIri the component iri of this component record
     */
    public void setComponentIri(String componentIri);

    /**
     * Returns the updated date of this component record.
     *
     * @return the updated date of this component record
     */
    public Date getUpdatedDate();

    /**
     * Sets the updated date of this component record.
     *
     * @param updatedDate the updated date of this component record
     */
    public void setUpdatedDate(Date updatedDate);

    /**
     * Returns the completion status of this component record.
     *
     * @return the completion status of this component record
     */
    @AutoEscape
    public String getCompletionStatus();

    /**
     * Sets the completion status of this component record.
     *
     * @param completionStatus the completion status of this component record
     */
    public void setCompletionStatus(String completionStatus);

    /**
     * Returns the completion percent of this component record.
     *
     * @return the completion percent of this component record
     */
    public Integer getCompletionPercent();

    /**
     * Sets the completion percent of this component record.
     *
     * @param completionPercent the completion percent of this component record
     */
    public void setCompletionPercent(Integer completionPercent);

    public boolean isNew();

    public void setNew(boolean n);

    public boolean isCachedModel();

    public void setCachedModel(boolean cachedModel);

    public boolean isEscapedModel();

    public Serializable getPrimaryKeyObj();

    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    public ExpandoBridge getExpandoBridge();

    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    public Object clone();

    public int compareTo(ComponentRecord componentRecord);

    public int hashCode();

    public CacheModel<ComponentRecord> toCacheModel();

    public ComponentRecord toEscapedModel();

    public String toString();

    public String toXmlString();
}