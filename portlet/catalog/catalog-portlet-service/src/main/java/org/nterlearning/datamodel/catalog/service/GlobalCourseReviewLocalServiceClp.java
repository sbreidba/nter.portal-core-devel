package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class GlobalCourseReviewLocalServiceClp
    implements GlobalCourseReviewLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addGlobalCourseReviewMethodKey0;
    private MethodKey _createGlobalCourseReviewMethodKey1;
    private MethodKey _deleteGlobalCourseReviewMethodKey2;
    private MethodKey _deleteGlobalCourseReviewMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchGlobalCourseReviewMethodKey8;
    private MethodKey _getGlobalCourseReviewMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getGlobalCourseReviewsMethodKey11;
    private MethodKey _getGlobalCourseReviewsCountMethodKey12;
    private MethodKey _updateGlobalCourseReviewMethodKey13;
    private MethodKey _updateGlobalCourseReviewMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _setReviewIsHiddenMethodKey17;
    private MethodKey _findByCourseReviewIriMethodKey18;
    private MethodKey _findByCourseIdMethodKey19;
    private MethodKey _findByCourseIriMethodKey20;
    private MethodKey _findValidByCourseIdMethodKey21;
    private MethodKey _countValidByCourseIdMethodKey22;

    public GlobalCourseReviewLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addGlobalCourseReviewMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addGlobalCourseReview",
                org.nterlearning.datamodel.catalog.model.GlobalCourseReview.class);

        _createGlobalCourseReviewMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createGlobalCourseReview", long.class);

        _deleteGlobalCourseReviewMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteGlobalCourseReview", long.class);

        _deleteGlobalCourseReviewMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteGlobalCourseReview",
                org.nterlearning.datamodel.catalog.model.GlobalCourseReview.class);

        _dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class);

        _dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class,
                com.liferay.portal.kernel.util.OrderByComparator.class);

        _dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQueryCount",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _fetchGlobalCourseReviewMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchGlobalCourseReview", long.class);

        _getGlobalCourseReviewMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getGlobalCourseReview", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getGlobalCourseReviewsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getGlobalCourseReviews", int.class, int.class);

        _getGlobalCourseReviewsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getGlobalCourseReviewsCount");

        _updateGlobalCourseReviewMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateGlobalCourseReview",
                org.nterlearning.datamodel.catalog.model.GlobalCourseReview.class);

        _updateGlobalCourseReviewMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateGlobalCourseReview",
                org.nterlearning.datamodel.catalog.model.GlobalCourseReview.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _setReviewIsHiddenMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "setReviewIsHidden", long.class, java.lang.Boolean.class);

        _findByCourseReviewIriMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseReviewIri", java.lang.String.class);

        _findByCourseIdMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseId", long.class);

        _findByCourseIriMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIri", java.lang.String.class);

        _findValidByCourseIdMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "findValidByCourseId", long.class, int.class, int.class);

        _countValidByCourseIdMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "countValidByCourseId", long.class);
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview addGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addGlobalCourseReviewMethodKey0,
                ClpSerializer.translateInput(globalCourseReview));

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

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview createGlobalCourseReview(
        long globalCourseReviewId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createGlobalCourseReviewMethodKey1,
                globalCourseReviewId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteGlobalCourseReview(long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteGlobalCourseReviewMethodKey2,
                globalCourseReviewId);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

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
    }

    public void deleteGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteGlobalCourseReviewMethodKey3,
                ClpSerializer.translateInput(globalCourseReview));

        try {
            _classLoaderProxy.invoke(methodHandler);
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
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
                ClpSerializer.translateInput(dynamicQuery));

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

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
                ClpSerializer.translateInput(dynamicQuery), start, end);

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

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
                ClpSerializer.translateInput(dynamicQuery), start, end,
                ClpSerializer.translateInput(orderByComparator));

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

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
                ClpSerializer.translateInput(dynamicQuery));

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

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchGlobalCourseReviewMethodKey8,
                globalCourseReviewId);

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

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview getGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getGlobalCourseReviewMethodKey9,
                globalCourseReviewId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

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

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPersistedModelMethodKey10,
                ClpSerializer.translateInput(primaryKeyObj));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

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

        return (com.liferay.portal.model.PersistedModel) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getGlobalCourseReviewsMethodKey11,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public int getGlobalCourseReviewsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getGlobalCourseReviewsCountMethodKey12);

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

        return ((Integer) returnObj).intValue();
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateGlobalCourseReviewMethodKey13,
                ClpSerializer.translateInput(globalCourseReview));

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

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateGlobalCourseReviewMethodKey14,
                ClpSerializer.translateInput(globalCourseReview), merge);

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

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey15);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey16,
                ClpSerializer.translateInput(beanIdentifier));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setReviewIsHidden(long globalCourseReviewId,
        java.lang.Boolean hidden)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        MethodHandler methodHandler = new MethodHandler(_setReviewIsHiddenMethodKey17,
                globalCourseReviewId, ClpSerializer.translateInput(hidden));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseReviewIriMethodKey18,
                ClpSerializer.translateInput(courseReviewIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.GlobalCourseReview) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdMethodKey19,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIriMethodKey20,
                ClpSerializer.translateInput(courseIri));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findValidByCourseIdMethodKey21,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countValidByCourseIdMethodKey22,
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

        return ((Long) returnObj).longValue();
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
