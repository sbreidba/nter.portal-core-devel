package org.nterlearning.datamodel.catalog.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the CourseImage service. Represents a row in the &quot;CATALOG_CourseImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImage
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl
 * @see org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl
 * @generated
 */
public interface CourseImageModel extends BaseModel<CourseImage> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a course image model instance should use the {@link CourseImage} interface instead.
     */

    /**
     * Returns the primary key of this course image.
     *
     * @return the primary key of this course image
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this course image.
     *
     * @param primaryKey the primary key of this course image
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the course image ID of this course image.
     *
     * @return the course image ID of this course image
     */
    public long getCourseImageId();

    /**
     * Sets the course image ID of this course image.
     *
     * @param courseImageId the course image ID of this course image
     */
    public void setCourseImageId(long courseImageId);

    /**
     * Returns the course ID of this course image.
     *
     * @return the course ID of this course image
     */
    public long getCourseId();

    /**
     * Sets the course ID of this course image.
     *
     * @param courseId the course ID of this course image
     */
    public void setCourseId(long courseId);

    /**
     * Returns the order weight of this course image.
     *
     * @return the order weight of this course image
     */
    public double getOrderWeight();

    /**
     * Sets the order weight of this course image.
     *
     * @param orderWeight the order weight of this course image
     */
    public void setOrderWeight(double orderWeight);

    /**
     * Returns the language of this course image.
     *
     * @return the language of this course image
     */
    @AutoEscape
    public String getLanguage();

    /**
     * Sets the language of this course image.
     *
     * @param language the language of this course image
     */
    public void setLanguage(String language);

    /**
     * Returns the image ID of this course image.
     *
     * @return the image ID of this course image
     */
    public long getImageId();

    /**
     * Sets the image ID of this course image.
     *
     * @param imageId the image ID of this course image
     */
    public void setImageId(long imageId);

    /**
     * Returns the alternate text of this course image.
     *
     * @return the alternate text of this course image
     */
    @AutoEscape
    public String getAlternateText();

    /**
     * Sets the alternate text of this course image.
     *
     * @param alternateText the alternate text of this course image
     */
    public void setAlternateText(String alternateText);

    /**
     * Returns the source image url of this course image.
     *
     * @return the source image url of this course image
     */
    @AutoEscape
    public String getSourceImageUrl();

    /**
     * Sets the source image url of this course image.
     *
     * @param sourceImageUrl the source image url of this course image
     */
    public void setSourceImageUrl(String sourceImageUrl);

    /**
     * Returns the mime type of this course image.
     *
     * @return the mime type of this course image
     */
    @AutoEscape
    public String getMimeType();

    /**
     * Sets the mime type of this course image.
     *
     * @param mimeType the mime type of this course image
     */
    public void setMimeType(String mimeType);

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

    public int compareTo(CourseImage courseImage);

    public int hashCode();

    public CacheModel<CourseImage> toCacheModel();

    public CourseImage toEscapedModel();

    public String toString();

    public String toXmlString();
}
