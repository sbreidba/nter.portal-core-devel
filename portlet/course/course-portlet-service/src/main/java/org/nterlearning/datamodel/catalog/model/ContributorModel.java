package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Contributor service. Represents a row in the &quot;CATALOG_Contributor&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.nterlearning.datamodel.catalog.model.impl.ContributorImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Contributor
 * @see org.nterlearning.datamodel.catalog.model.impl.ContributorImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl
 * @generated
 */
public interface ContributorModel extends BaseModel<Contributor> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a contributor model instance should use the {@link Contributor} interface instead.
     */

    /**
     * Returns the primary key of this contributor.
     *
     * @return the primary key of this contributor
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this contributor.
     *
     * @param primaryKey the primary key of this contributor
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the contributor ID of this contributor.
     *
     * @return the contributor ID of this contributor
     */
    public long getContributorId();

    /**
     * Sets the contributor ID of this contributor.
     *
     * @param contributorId the contributor ID of this contributor
     */
    public void setContributorId(long contributorId);

    /**
     * Returns the course ID of this contributor.
     *
     * @return the course ID of this contributor
     */
    public long getCourseId();

    /**
     * Sets the course ID of this contributor.
     *
     * @param courseId the course ID of this contributor
     */
    public void setCourseId(long courseId);

    /**
     * Returns the component ID of this contributor.
     *
     * @return the component ID of this contributor
     */
    public long getComponentId();

    /**
     * Sets the component ID of this contributor.
     *
     * @param componentId the component ID of this contributor
     */
    public void setComponentId(long componentId);

    /**
     * Returns the role of this contributor.
     *
     * @return the role of this contributor
     */
    @AutoEscape
    public String getRole();

    /**
     * Sets the role of this contributor.
     *
     * @param role the role of this contributor
     */
    public void setRole(String role);

    /**
     * Returns the contributor name of this contributor.
     *
     * @return the contributor name of this contributor
     */
    @AutoEscape
    public String getContributorName();

    /**
     * Sets the contributor name of this contributor.
     *
     * @param contributorName the contributor name of this contributor
     */
    public void setContributorName(String contributorName);

    /**
     * Returns the virtual card data of this contributor.
     *
     * @return the virtual card data of this contributor
     */
    @AutoEscape
    public String getVirtualCardData();

    /**
     * Sets the virtual card data of this contributor.
     *
     * @param virtualCardData the virtual card data of this contributor
     */
    public void setVirtualCardData(String virtualCardData);

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

    public int compareTo(Contributor contributor);

    public int hashCode();

    public CacheModel<Contributor> toCacheModel();

    public Contributor toEscapedModel();

    public String toString();

    public String toXmlString();
}