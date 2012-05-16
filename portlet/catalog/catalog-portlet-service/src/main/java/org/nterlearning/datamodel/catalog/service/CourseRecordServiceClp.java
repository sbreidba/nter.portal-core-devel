package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class CourseRecordServiceClp implements CourseRecordService {
    private ClassLoaderProxy _classLoaderProxy;

    public CourseRecordServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
