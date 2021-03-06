package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.CourseImage;
import org.nterlearning.datamodel.catalog.service.CourseImageLocalServiceUtil;

/**
 * The extended model base implementation for the CourseImage service. Represents a row in the &quot;CATALOG_CourseImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CourseImageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImageImpl
 * @see org.nterlearning.datamodel.catalog.model.CourseImage
 * @generated
 */
public abstract class CourseImageBaseImpl extends CourseImageModelImpl
    implements CourseImage {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a course image model instance should use the {@link CourseImage} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseImageLocalServiceUtil.addCourseImage(this);
        } else {
            CourseImageLocalServiceUtil.updateCourseImage(this);
        }
    }
}
