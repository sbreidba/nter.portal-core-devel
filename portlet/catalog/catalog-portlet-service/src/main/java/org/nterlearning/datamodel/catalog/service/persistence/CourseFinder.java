package org.nterlearning.datamodel.catalog.service.persistence;

public interface CourseFinder {
    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        java.lang.String filterSQL, java.lang.String sortSQL,
        com.liferay.portlet.asset.model.AssetCategory category,
        long vocabularyId, long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    public long countAllValidCourses(java.lang.String filterSQL,
        com.liferay.portlet.asset.model.AssetCategory category,
        long vocabularyId, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;
}
