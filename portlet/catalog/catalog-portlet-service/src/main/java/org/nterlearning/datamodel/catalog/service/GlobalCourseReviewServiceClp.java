package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class GlobalCourseReviewServiceClp implements GlobalCourseReviewService {
    private ClassLoaderProxy _classLoaderProxy;

    public GlobalCourseReviewServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
