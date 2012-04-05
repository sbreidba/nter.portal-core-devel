package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class CourseRecordFinderUtil {
    private static CourseRecordFinder _finder;

    public static java.util.List<java.lang.Object[]> findByUserIdFilterSorted(
        long userId, long courseId, java.lang.String filterType,
        java.lang.String sortType, boolean asc, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findByUserIdFilterSorted(userId, courseId, filterType,
            sortType, asc, start, end);
    }

    public static long countByUserIdFilter(long userId, long courseId,
        java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().countByUserIdFilter(userId, courseId, filterType);
    }

    public static long countAccessedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().countAccessedByCourseIri(courseIri);
    }

    public static long countCompletedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().countCompletedByCourseIri(courseIri);
    }

    public static CourseRecordFinder getFinder() {
        if (_finder == null) {
            _finder = (CourseRecordFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseRecordFinder.class.getName());

            ReferenceRegistry.registerReference(CourseRecordFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CourseRecordFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CourseRecordFinderUtil.class,
            "_finder");
    }
}
