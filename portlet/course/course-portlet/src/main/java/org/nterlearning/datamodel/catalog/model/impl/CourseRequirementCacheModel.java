package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.CourseRequirement;

import java.io.Serializable;

/**
 * The cache model class for representing CourseRequirement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CourseRequirement
 * @generated
 */
public class CourseRequirementCacheModel implements CacheModel<CourseRequirement>,
    Serializable {
    public long courseRequirementId;
    public long courseId;
    public String requirementType;
    public String requirementValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{courseRequirementId=");
        sb.append(courseRequirementId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", requirementType=");
        sb.append(requirementType);
        sb.append(", requirementValue=");
        sb.append(requirementValue);
        sb.append("}");

        return sb.toString();
    }

    public CourseRequirement toEntityModel() {
        CourseRequirementImpl courseRequirementImpl = new CourseRequirementImpl();

        courseRequirementImpl.setCourseRequirementId(courseRequirementId);
        courseRequirementImpl.setCourseId(courseId);

        if (requirementType == null) {
            courseRequirementImpl.setRequirementType(StringPool.BLANK);
        } else {
            courseRequirementImpl.setRequirementType(requirementType);
        }

        if (requirementValue == null) {
            courseRequirementImpl.setRequirementValue(StringPool.BLANK);
        } else {
            courseRequirementImpl.setRequirementValue(requirementValue);
        }

        courseRequirementImpl.resetOriginalValues();

        return courseRequirementImpl;
    }
}
