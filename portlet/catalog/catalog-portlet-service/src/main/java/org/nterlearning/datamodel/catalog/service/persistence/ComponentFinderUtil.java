package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class ComponentFinderUtil {
    private static ComponentFinder _finder;

    public static java.util.List<java.lang.Object[]> findByCourseIdLanguageSorted(
        long courseId, java.util.Locale locale, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .findByCourseIdLanguageSorted(courseId, locale, start, end);
    }

    public static ComponentFinder getFinder() {
        if (_finder == null) {
            _finder = (ComponentFinder) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    ComponentFinder.class.getName());

            ReferenceRegistry.registerReference(ComponentFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ComponentFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ComponentFinderUtil.class, "_finder");
    }
}
