package org.nterlearning.datamodel.catalog.service.persistence;

public interface FlagReportFinder {
    public java.util.List<java.lang.Object[]> findByClassNameIdAndFilter(
        long classNameId, java.lang.String filterType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;
}
