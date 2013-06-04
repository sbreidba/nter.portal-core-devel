package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class CourseServiceClp implements CourseService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _findAllValidCoursesMethodKey0;
    private MethodKey _findAllValidCoursesMethodKey1;
    private MethodKey _findByCourseIdMethodKey2;
    private MethodKey _findByCourseIriMethodKey3;
    private MethodKey _findByGroupIdMethodKey4;
    private MethodKey _findByGroupIdMethodKey5;
    private MethodKey _findByCompanyIdMethodKey6;
    private MethodKey _findByCompanyIdMethodKey7;
    private MethodKey _findByFeedReferenceIdMethodKey8;
    private MethodKey _findAllCoursesMethodKey9;

    public CourseServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _findAllValidCoursesMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses");

        _findAllValidCoursesMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses", int.class, int.class);

        _findByCourseIdMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseId", long.class);

        _findByCourseIriMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIri", java.lang.String.class);

        _findByGroupIdMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByGroupId", long.class);

        _findByGroupIdMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByGroupId", long.class, int.class, int.class);

        _findByCompanyIdMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCompanyId", long.class);

        _findByCompanyIdMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCompanyId", long.class, int.class, int.class);

        _findByFeedReferenceIdMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedReferenceId", long.class);

        _findAllCoursesMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllCourses", int.class, int.class);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey0);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey1,
                start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdMethodKey2,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIriMethodKey3,
                ClpSerializer.translateInput(courseIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByGroupIdMethodKey4,
                groupId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByGroupIdMethodKey5,
                groupId, start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCompanyIdMethodKey6,
                companyId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCompanyIdMethodKey7,
                companyId, start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedReferenceIdMethodKey8,
                feedReferenceId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllCoursesMethodKey9,
                start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
