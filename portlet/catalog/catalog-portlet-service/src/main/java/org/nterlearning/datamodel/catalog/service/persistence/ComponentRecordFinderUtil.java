package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class ComponentRecordFinderUtil {
    private static ComponentRecordFinder _finder;

    public static java.util.List<java.lang.Object[]> findByCourseRecordIdUserIdLanguageFilterSorted(
        long courseRecordId, long userId, java.util.Locale locale,
        java.lang.String filterType, java.lang.String sortType, boolean asc,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findByCourseRecordIdUserIdLanguageFilterSorted(courseRecordId,
            userId, locale, filterType, sortType, asc, start, end);
    }

    public static long countByCourseRecordIdUserIdLanguageStatus(
        long courseRecordId, long userId, java.util.Locale locale,
        java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .countByCourseRecordIdUserIdLanguageStatus(courseRecordId,
            userId, locale, filterType);
    }

    public static ComponentRecordFinder getFinder() {
        if (_finder == null) {
            _finder = (ComponentRecordFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    ComponentRecordFinder.class.getName());

            ReferenceRegistry.registerReference(ComponentRecordFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ComponentRecordFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ComponentRecordFinderUtil.class,
            "_finder");
    }
}
