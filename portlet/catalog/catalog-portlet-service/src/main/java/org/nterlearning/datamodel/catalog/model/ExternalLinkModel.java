package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the ExternalLink service. Represents a row in the &quot;CATALOG_ExternalLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLink
 * @see org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl
 * @generated
 */
public interface ExternalLinkModel extends BaseModel<ExternalLink> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a external link model instance should use the {@link ExternalLink} interface instead.
     */

    /**
     * Returns the primary key of this external link.
     *
     * @return the primary key of this external link
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this external link.
     *
     * @param primaryKey the primary key of this external link
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the link ID of this external link.
     *
     * @return the link ID of this external link
     */
    public long getLinkId();

    /**
     * Sets the link ID of this external link.
     *
     * @param linkId the link ID of this external link
     */
    public void setLinkId(long linkId);

    /**
     * Returns the course ID of this external link.
     *
     * @return the course ID of this external link
     */
    public long getCourseId();

    /**
     * Sets the course ID of this external link.
     *
     * @param courseId the course ID of this external link
     */
    public void setCourseId(long courseId);

    /**
     * Returns the component ID of this external link.
     *
     * @return the component ID of this external link
     */
    public long getComponentId();

    /**
     * Sets the component ID of this external link.
     *
     * @param componentId the component ID of this external link
     */
    public void setComponentId(long componentId);

    /**
     * Returns the link type of this external link.
     *
     * @return the link type of this external link
     */
    @AutoEscape
    public String getLinkType();

    /**
     * Sets the link type of this external link.
     *
     * @param linkType the link type of this external link
     */
    public void setLinkType(String linkType);

    /**
     * Returns the link url of this external link.
     *
     * @return the link url of this external link
     */
    @AutoEscape
    public String getLinkUrl();

    /**
     * Sets the link url of this external link.
     *
     * @param linkUrl the link url of this external link
     */
    public void setLinkUrl(String linkUrl);

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

    public int compareTo(ExternalLink externalLink);

    public int hashCode();

    public CacheModel<ExternalLink> toCacheModel();

    public ExternalLink toEscapedModel();

    public String toString();

    public String toXmlString();
}