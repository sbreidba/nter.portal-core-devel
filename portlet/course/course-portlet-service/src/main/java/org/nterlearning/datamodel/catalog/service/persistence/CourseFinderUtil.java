package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class CourseFinderUtil {
    private static CourseFinder _finder;

    public static java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        java.lang.String filterSQL, java.lang.String sortSQL,
        com.liferay.portlet.asset.model.AssetCategory category,
        long vocabularyId, long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findAllValidCourses(filterSQL, sortSQL, category,
            vocabularyId, groupId, start, end);
    }

    public static long countAllValidCourses(java.lang.String filterSQL,
        com.liferay.portlet.asset.model.AssetCategory category,
        long vocabularyId, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .countAllValidCourses(filterSQL, category, vocabularyId,
            groupId);
    }

    public static CourseFinder getFinder() {
        if (_finder == null) {
            _finder = (CourseFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseFinder.class.getName());

            ReferenceRegistry.registerReference(CourseFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CourseFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CourseFinderUtil.class, "_finder");
    }
}
