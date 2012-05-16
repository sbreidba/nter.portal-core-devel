package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ComponentRecordServiceClp implements ComponentRecordService {
    private ClassLoaderProxy _classLoaderProxy;

    public ComponentRecordServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
