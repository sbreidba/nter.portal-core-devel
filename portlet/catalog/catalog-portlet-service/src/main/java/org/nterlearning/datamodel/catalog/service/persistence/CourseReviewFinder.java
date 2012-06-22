package org.nterlearning.datamodel.catalog.service.persistence;

public interface CourseReviewFinder {
    public double findScoreByReviewId(long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<java.lang.Double> findScoresByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;
}
