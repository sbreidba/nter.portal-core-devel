package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

/**
 * The extended model base implementation for the Course service. Represents a row in the &quot;CATALOG_Course&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CourseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImpl
 * @see org.nterlearning.datamodel.catalog.model.Course
 * @generated
 */
public abstract class CourseBaseImpl extends CourseModelImpl implements Course {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a course model instance should use the {@link Course} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseLocalServiceUtil.addCourse(this);
        } else {
            CourseLocalServiceUtil.updateCourse(this);
        }
    }
}
