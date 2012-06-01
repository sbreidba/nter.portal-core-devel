package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.CourseRelated;

import java.io.Serializable;

/**
 * The cache model class for representing CourseRelated in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CourseRelated
 * @generated
 */
public class CourseRelatedCacheModel implements CacheModel<CourseRelated>,
    Serializable {
    public long courseRelatedId;
    public long courseId;
    public long relatedCourseId;
    public String relatedCourseIri;
    public String relationshipType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{courseRelatedId=");
        sb.append(courseRelatedId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", relatedCourseId=");
        sb.append(relatedCourseId);
        sb.append(", relatedCourseIri=");
        sb.append(relatedCourseIri);
        sb.append(", relationshipType=");
        sb.append(relationshipType);
        sb.append("}");

        return sb.toString();
    }

    public CourseRelated toEntityModel() {
        CourseRelatedImpl courseRelatedImpl = new CourseRelatedImpl();

        courseRelatedImpl.setCourseRelatedId(courseRelatedId);
        courseRelatedImpl.setCourseId(courseId);
        courseRelatedImpl.setRelatedCourseId(relatedCourseId);

        if (relatedCourseIri == null) {
            courseRelatedImpl.setRelatedCourseIri(StringPool.BLANK);
        } else {
            courseRelatedImpl.setRelatedCourseIri(relatedCourseIri);
        }

        if (relationshipType == null) {
            courseRelatedImpl.setRelationshipType(StringPool.BLANK);
        } else {
            courseRelatedImpl.setRelationshipType(relationshipType);
        }

        courseRelatedImpl.resetOriginalValues();

        return courseRelatedImpl;
    }
}
