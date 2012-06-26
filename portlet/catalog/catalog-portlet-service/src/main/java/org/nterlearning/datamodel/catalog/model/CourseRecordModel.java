package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CourseRecord service. Represents a row in the &quot;CATALOG_CourseRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.nterlearning.datamodel.catalog.model.impl.CourseRecordModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecord
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseRecordModelImpl
 * @generated
 */
public interface CourseRecordModel extends BaseModel<CourseRecord> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a course record model instance should use the {@link CourseRecord} interface instead.
     */

    /**
     * Returns the primary key of this course record.
     *
     * @return the primary key of this course record
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this course record.
     *
     * @param primaryKey the primary key of this course record
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the course record ID of this course record.
     *
     * @return the course record ID of this course record
     */
    public long getCourseRecordId();

    /**
     * Sets the course record ID of this course record.
     *
     * @param courseRecordId the course record ID of this course record
     */
    public void setCourseRecordId(long courseRecordId);

    /**
     * Returns the feed reference ID of this course record.
     *
     * @return the feed reference ID of this course record
     */
    public long getFeedReferenceId();

    /**
     * Sets the feed reference ID of this course record.
     *
     * @param feedReferenceId the feed reference ID of this course record
     */
    public void setFeedReferenceId(long feedReferenceId);

    /**
     * Returns the course record iri of this course record.
     *
     * @return the course record iri of this course record
     */
    @AutoEscape
    public String getCourseRecordIri();

    /**
     * Sets the course record iri of this course record.
     *
     * @param courseRecordIri the course record iri of this course record
     */
    public void setCourseRecordIri(String courseRecordIri);

    /**
     * Returns the user ID of this course record.
     *
     * @return the user ID of this course record
     */
    public long getUserId();

    /**
     * Sets the user ID of this course record.
     *
     * @param userId the user ID of this course record
     */
    public void setUserId(long userId);

    /**
     * Returns the user uuid of this course record.
     *
     * @return the user uuid of this course record
     * @throws SystemException if a system exception occurred
     */
    public String getUserUuid() throws SystemException;

    /**
     * Sets the user uuid of this course record.
     *
     * @param userUuid the user uuid of this course record
     */
    public void setUserUuid(String userUuid);

    /**
     * Returns the single sign on value of this course record.
     *
     * @return the single sign on value of this course record
     */
    @AutoEscape
    public String getSingleSignOnValue();

    /**
     * Sets the single sign on value of this course record.
     *
     * @param singleSignOnValue the single sign on value of this course record
     */
    public void setSingleSignOnValue(String singleSignOnValue);

    /**
     * Returns the course iri of this course record.
     *
     * @return the course iri of this course record
     */
    @AutoEscape
    public String getCourseIri();

    /**
     * Sets the course iri of this course record.
     *
     * @param courseIri the course iri of this course record
     */
    public void setCourseIri(String courseIri);

    /**
     * Returns the updated date of this course record.
     *
     * @return the updated date of this course record
     */
    public Date getUpdatedDate();

    /**
     * Sets the updated date of this course record.
     *
     * @param updatedDate the updated date of this course record
     */
    public void setUpdatedDate(Date updatedDate);

    /**
     * Returns the completion status of this course record.
     *
     * @return the completion status of this course record
     */
    @AutoEscape
    public String getCompletionStatus();

    /**
     * Sets the completion status of this course record.
     *
     * @param completionStatus the completion status of this course record
     */
    public void setCompletionStatus(String completionStatus);

    /**
     * Returns the removed of this course record.
     *
     * @return the removed of this course record
     */
    public boolean getRemoved();

    /**
     * Returns <code>true</code> if this course record is removed.
     *
     * @return <code>true</code> if this course record is removed; <code>false</code> otherwise
     */
    public boolean isRemoved();

    /**
     * Sets whether this course record is removed.
     *
     * @param removed the removed of this course record
     */
    public void setRemoved(boolean removed);

    /**
     * Returns the removed date of this course record.
     *
     * @return the removed date of this course record
     */
    public Date getRemovedDate();

    /**
     * Sets the removed date of this course record.
     *
     * @param removedDate the removed date of this course record
     */
    public void setRemovedDate(Date removedDate);

    /**
     * Returns the user hidden of this course record.
     *
     * @return the user hidden of this course record
     */
    public boolean getUserHidden();

    /**
     * Returns <code>true</code> if this course record is user hidden.
     *
     * @return <code>true</code> if this course record is user hidden; <code>false</code> otherwise
     */
    public boolean isUserHidden();

    /**
     * Sets whether this course record is user hidden.
     *
     * @param userHidden the user hidden of this course record
     */
    public void setUserHidden(boolean userHidden);

    /**
     * Returns the assigned of this course record.
     *
     * @return the assigned of this course record
     */
    public boolean getAssigned();

    /**
     * Returns <code>true</code> if this course record is assigned.
     *
     * @return <code>true</code> if this course record is assigned; <code>false</code> otherwise
     */
    public boolean isAssigned();

    /**
     * Sets whether this course record is assigned.
     *
     * @param assigned the assigned of this course record
     */
    public void setAssigned(boolean assigned);

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

    public int compareTo(CourseRecord courseRecord);

    public int hashCode();

    public CacheModel<CourseRecord> toCacheModel();

    public CourseRecord toEscapedModel();

    public String toString();

    public String toXmlString();
}
