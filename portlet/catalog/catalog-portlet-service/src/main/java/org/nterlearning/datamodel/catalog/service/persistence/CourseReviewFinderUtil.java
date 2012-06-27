package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class CourseReviewFinderUtil {
    private static CourseReviewFinder _finder;

    public static double findScoreByReviewId(long courseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().findScoreByReviewId(courseReviewId);
    }

    public static java.util.List<java.lang.Double> findScoresByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().findScoresByCourseId(courseId);
    }

    public static CourseReviewFinder getFinder() {
        if (_finder == null) {
            _finder = (CourseReviewFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseReviewFinder.class.getName());

            ReferenceRegistry.registerReference(CourseReviewFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CourseReviewFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CourseReviewFinderUtil.class,
            "_finder");
    }
}
