package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;

/**
 * The extended model base implementation for the CourseReview service. Represents a row in the &quot;CATALOG_CourseReview&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CourseReviewImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseReviewImpl
 * @see org.nterlearning.datamodel.catalog.model.CourseReview
 * @generated
 */
public abstract class CourseReviewBaseImpl extends CourseReviewModelImpl
    implements CourseReview {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a course review model instance should use the {@link CourseReview} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            CourseReviewLocalServiceUtil.addCourseReview(this);
        } else {
            CourseReviewLocalServiceUtil.updateCourseReview(this);
        }
    }
}