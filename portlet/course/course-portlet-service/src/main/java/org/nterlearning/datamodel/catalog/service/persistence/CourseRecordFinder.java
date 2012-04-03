package org.nterlearning.datamodel.catalog.service.persistence;

public interface CourseRecordFinder {
    public java.util.List<java.lang.Object[]> findByUserIdFilterSorted(
        long userId, long courseId, java.lang.String filterType,
        java.lang.String sortType, boolean asc, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countByUserIdFilter(long userId, long courseId,
        java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countAccessedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countCompletedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException;
}
