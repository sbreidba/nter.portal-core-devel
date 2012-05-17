package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class FlagReportFinderUtil {
    private static FlagReportFinder _finder;

    public static java.util.List<java.lang.Object[]> findByClassNameIdAndFilter(
        long classNameId, java.lang.String filterType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findByClassNameIdAndFilter(classNameId, filterType, start,
            end);
    }

    public static FlagReportFinder getFinder() {
        if (_finder == null) {
            _finder = (FlagReportFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    FlagReportFinder.class.getName());

            ReferenceRegistry.registerReference(FlagReportFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(FlagReportFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(FlagReportFinderUtil.class,
            "_finder");
    }
}
