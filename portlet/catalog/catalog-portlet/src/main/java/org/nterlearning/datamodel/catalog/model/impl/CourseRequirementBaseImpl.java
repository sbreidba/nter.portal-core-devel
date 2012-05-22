package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.CourseRequirement;
import org.nterlearning.datamodel.catalog.service.CourseRequirementLocalServiceUtil;

/**
 * The extended model base implementation for the CourseRequirement service. Represents a row in the &quot;CATALOG_CourseRequirement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CourseRequirementImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRequirementImpl
 * @see org.nterlearning.datamodel.catalog.model.CourseRequirement
 * @generated
 */
public abstract class CourseRequirementBaseImpl
    extends CourseRequirementModelImpl implements CourseRequirement {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a course requirement model instance should use the {@link CourseRequirement} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseRequirementLocalServiceUtil.addCourseRequirement(this);
        } else {
            CourseRequirementLocalServiceUtil.updateCourseRequirement(this);
        }
    }
}
