package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.CourseImage;

import java.io.Serializable;

/**
 * The cache model class for representing CourseImage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CourseImage
 * @generated
 */
public class CourseImageCacheModel implements CacheModel<CourseImage>,
    Serializable {
    public long courseImageId;
    public long courseId;
    public double orderWeight;
    public String language;
    public long imageId;
    public String alternateText;
    public String sourceImageUrl;
    public String mimeType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{courseImageId=");
        sb.append(courseImageId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", orderWeight=");
        sb.append(orderWeight);
        sb.append(", language=");
        sb.append(language);
        sb.append(", imageId=");
        sb.append(imageId);
        sb.append(", alternateText=");
        sb.append(alternateText);
        sb.append(", sourceImageUrl=");
        sb.append(sourceImageUrl);
        sb.append(", mimeType=");
        sb.append(mimeType);
        sb.append("}");

        return sb.toString();
    }

    public CourseImage toEntityModel() {
        CourseImageImpl courseImageImpl = new CourseImageImpl();

        courseImageImpl.setCourseImageId(courseImageId);
        courseImageImpl.setCourseId(courseId);
        courseImageImpl.setOrderWeight(orderWeight);

        if (language == null) {
            courseImageImpl.setLanguage(StringPool.BLANK);
        } else {
            courseImageImpl.setLanguage(language);
        }

        courseImageImpl.setImageId(imageId);

        if (alternateText == null) {
            courseImageImpl.setAlternateText(StringPool.BLANK);
        } else {
            courseImageImpl.setAlternateText(alternateText);
        }

        if (sourceImageUrl == null) {
            courseImageImpl.setSourceImageUrl(StringPool.BLANK);
        } else {
            courseImageImpl.setSourceImageUrl(sourceImageUrl);
        }

        if (mimeType == null) {
            courseImageImpl.setMimeType(StringPool.BLANK);
        } else {
            courseImageImpl.setMimeType(mimeType);
        }

        courseImageImpl.resetOriginalValues();

        return courseImageImpl;
    }
}
