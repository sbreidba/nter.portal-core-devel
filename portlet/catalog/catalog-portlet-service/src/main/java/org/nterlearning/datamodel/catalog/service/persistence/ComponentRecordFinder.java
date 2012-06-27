package org.nterlearning.datamodel.catalog.service.persistence;

public interface ComponentRecordFinder {
    public java.util.List<java.lang.Object[]> findByCourseRecordIdUserIdLanguageFilterSorted(
        long courseRecordId, long userId, java.util.Locale locale,
        java.lang.String filterType, java.lang.String sortType, boolean asc,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countByCourseRecordIdUserIdLanguageStatus(long courseRecordId,
        long userId, java.util.Locale locale, java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException;
}
