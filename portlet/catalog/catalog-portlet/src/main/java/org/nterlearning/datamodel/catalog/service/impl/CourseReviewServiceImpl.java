package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.CourseReviewServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the course review remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.CourseReviewService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.CourseReviewServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil
 */
public class CourseReviewServiceImpl extends CourseReviewServiceBaseImpl {


    public double findScoreByReviewId(long reviewId)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findScoreByReviewId(reviewId);
    }


    public List<Double> findScoreByCourseId(long courseId)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findScoreByCourseId(courseId);
    }


    public List<CourseReview> findByCourseId(long courseId)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findByCourseId(courseId);
    }


    public List<CourseReview> findByCourseId(long courseId, int start, int end)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findByCourseId(courseId, start, end);
    }


    public List<CourseReview> findByCourseIdWithUserId(long userId, long courseId)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findByCourseIdWithUserId(courseId, userId);
    }


    public List<CourseReview> findByCourseIdWithUserId(long userId, long courseId, int start, int end)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findByCourseIdWithUserId(courseId, userId, start, end);
    }


    public List<CourseReview> findByUserId(long userId)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findByUserId(userId);
    }


    public List<CourseReview> findByUserId(long userId, int start, int end)
            throws SystemException {
        return CourseReviewLocalServiceUtil.findByUserId(userId, start, end);
    }


    public List<CourseReview> findByCourseIdWithRemoved(long courseId, boolean removed,
            int start, int end) throws SystemException {
        return CourseReviewLocalServiceUtil.findByCourseIdWithRemoved(courseId, removed, start, end);
    }


    public long countByCourseIdWithRemoved(long courseId, boolean removed)
            throws SystemException {
        return CourseReviewLocalServiceUtil.countByCourseIdWithRemoved(courseId, removed);
    }
}
