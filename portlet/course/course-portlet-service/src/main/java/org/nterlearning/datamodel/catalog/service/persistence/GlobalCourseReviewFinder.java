package org.nterlearning.datamodel.catalog.service.persistence;

public interface GlobalCourseReviewFinder {
    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;
}
