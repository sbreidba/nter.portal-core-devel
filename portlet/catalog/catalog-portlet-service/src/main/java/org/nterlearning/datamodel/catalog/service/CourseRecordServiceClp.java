package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class CourseRecordServiceClp implements CourseRecordService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _findByPrimaryKeyMethodKey0;
    private MethodKey _findByCourseRecordIriMethodKey1;
    private MethodKey _findByCourseIriMethodKey2;
    private MethodKey _findBySingleSignOnValueMethodKey3;
    private MethodKey _findByUserIdMethodKey4;
    private MethodKey _findByFeedReferenceIdMethodKey5;
    private MethodKey _getComponentRecordsMethodKey6;
    private MethodKey _getComponentRecordsMethodKey7;

    public CourseRecordServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _findByPrimaryKeyMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByPrimaryKey", long.class);

        _findByCourseRecordIriMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseRecordIri", java.lang.String.class);

        _findByCourseIriMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIri", java.lang.String.class);

        _findBySingleSignOnValueMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "findBySingleSignOnValue", java.lang.String.class);

        _findByUserIdMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByUserId", java.lang.Long.class);

        _findByFeedReferenceIdMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedReferenceId", long.class);

        _getComponentRecordsMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "getComponentRecords",
                org.nterlearning.datamodel.catalog.model.CourseRecord.class);

        _getComponentRecordsMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "getComponentRecords", long.class);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByPrimaryKeyMethodKey0,
                courseRecordId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseRecordException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseRecordException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.CourseRecord) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseRecordIriMethodKey1,
                ClpSerializer.translateInput(courseRecordIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseRecordException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseRecordException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.CourseRecord) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIriMethodKey2,
                ClpSerializer.translateInput(courseIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseRecordException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseRecordException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findBySingleSignOnValueMethodKey3,
                ClpSerializer.translateInput(singleSignOnValue));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByUserIdMethodKey4,
                ClpSerializer.translateInput(userId));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedReferenceIdMethodKey5,
                feedRefId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getComponentRecordsMethodKey6,
                ClpSerializer.translateInput(courseRecord));

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

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long courseRecordPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getComponentRecordsMethodKey7,
                courseRecordPrimaryKey);

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
