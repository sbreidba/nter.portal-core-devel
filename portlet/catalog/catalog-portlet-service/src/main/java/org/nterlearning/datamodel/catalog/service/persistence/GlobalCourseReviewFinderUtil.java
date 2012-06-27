package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class GlobalCourseReviewFinderUtil {
    private static GlobalCourseReviewFinder _finder;

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().findValidByCourseId(courseId, start, end);
    }

    public static long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().countValidByCourseId(courseId);
    }

    public static GlobalCourseReviewFinder getFinder() {
        if (_finder == null) {
            _finder = (GlobalCourseReviewFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    GlobalCourseReviewFinder.class.getName());

            ReferenceRegistry.registerReference(GlobalCourseReviewFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(GlobalCourseReviewFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(GlobalCourseReviewFinderUtil.class,
            "_finder");
    }
}
