package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class Courses_ComponentsServiceClp implements Courses_ComponentsService {
    private ClassLoaderProxy _classLoaderProxy;

    public Courses_ComponentsServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
