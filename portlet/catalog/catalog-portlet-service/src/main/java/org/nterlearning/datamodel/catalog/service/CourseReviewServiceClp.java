package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class CourseReviewServiceClp implements CourseReviewService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _findScoreByReviewIdMethodKey0;
    private MethodKey _findScoreByCourseIdMethodKey1;
    private MethodKey _findByCourseIdMethodKey2;
    private MethodKey _findByCourseIdMethodKey3;
    private MethodKey _findByCourseIdWithUserIdMethodKey4;
    private MethodKey _findByCourseIdWithUserIdMethodKey5;
    private MethodKey _findByUserIdMethodKey6;
    private MethodKey _findByUserIdMethodKey7;
    private MethodKey _findByCourseIdWithRemovedMethodKey8;
    private MethodKey _countByCourseIdWithRemovedMethodKey9;

    public CourseReviewServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _findScoreByReviewIdMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "findScoreByReviewId", long.class);

        _findScoreByCourseIdMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "findScoreByCourseId", long.class);

        _findByCourseIdMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseId", long.class);

        _findByCourseIdMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseId", long.class, int.class, int.class);

        _findByCourseIdWithUserIdMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIdWithUserId", long.class, long.class);

        _findByCourseIdWithUserIdMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIdWithUserId", long.class, long.class, int.class,
                int.class);

        _findByUserIdMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByUserId", long.class);

        _findByUserIdMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByUserId", long.class, int.class, int.class);

        _findByCourseIdWithRemovedMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIdWithRemoved", long.class, boolean.class,
                int.class, int.class);

        _countByCourseIdWithRemovedMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "countByCourseIdWithRemoved", long.class, boolean.class);
    }

    public double findScoreByReviewId(long reviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findScoreByReviewIdMethodKey0,
                reviewId);

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

        return ((Double) returnObj).doubleValue();
    }

    public java.util.List<java.lang.Double> findScoreByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findScoreByCourseIdMethodKey1,
                courseId);

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

        return (java.util.List<java.lang.Double>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdMethodKey2,
                courseId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdMethodKey3,
                courseId, start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdWithUserIdMethodKey4,
                userId, courseId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithUserId(
        long userId, long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdWithUserIdMethodKey5,
                userId, courseId, start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByUserIdMethodKey6,
                userId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByUserIdMethodKey7,
                userId, start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdWithRemovedMethodKey8,
                courseId, removed, start, end);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public long countByCourseIdWithRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countByCourseIdWithRemovedMethodKey9,
                courseId, removed);

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

        return ((Long) returnObj).longValue();
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
