package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ComponentRecordServiceClp implements ComponentRecordService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _findByComponentIriMethodKey0;
    private MethodKey _findByCourseRecordIdMethodKey1;

    public ComponentRecordServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _findByComponentIriMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByComponentIri", java.lang.String.class);

        _findByCourseRecordIdMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseRecordId", long.class);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByComponentIriMethodKey0,
                ClpSerializer.translateInput(componentIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchComponentRecordException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchComponentRecordException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseRecordIdMethodKey1,
                courseRecordId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord>) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
