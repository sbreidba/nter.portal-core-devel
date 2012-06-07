package org.nterlearning.datamodel.catalog.service.persistence;

public interface ComponentFinder {
    public java.util.List<java.lang.Object[]> findByCourseIdLanguageSorted(
        long courseId, java.util.Locale locale, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;
}
