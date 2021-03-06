package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class FeedReferenceLocalServiceClp implements FeedReferenceLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addFeedReferenceMethodKey0;
    private MethodKey _createFeedReferenceMethodKey1;
    private MethodKey _deleteFeedReferenceMethodKey2;
    private MethodKey _deleteFeedReferenceMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchFeedReferenceMethodKey8;
    private MethodKey _getFeedReferenceMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getFeedReferencesMethodKey11;
    private MethodKey _getFeedReferencesCountMethodKey12;
    private MethodKey _updateFeedReferenceMethodKey13;
    private MethodKey _updateFeedReferenceMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _findByFeedIriMethodKey17;
    private MethodKey _fetchByFeedIriMethodKey18;
    private MethodKey _findByFeedHrefMethodKey19;
    private MethodKey _generateDynamicQueryMethodKey20;
    private MethodKey _generateDynamicQueryMethodKey21;
    private MethodKey _generateDynamicQueryMethodKey22;
    private MethodKey _generateDynamicQueryMethodKey23;
    private MethodKey _removeAssociatedVocabulariesMethodKey24;
    private MethodKey _clearCacheMethodKey25;
    private MethodKey _clearCacheMethodKey26;
    private MethodKey _clearCacheMethodKey27;

    public FeedReferenceLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addFeedReferenceMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFeedReference",
                org.nterlearning.datamodel.catalog.model.FeedReference.class);

        _createFeedReferenceMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createFeedReference", long.class);

        _deleteFeedReferenceMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteFeedReference", long.class);

        _deleteFeedReferenceMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteFeedReference",
                org.nterlearning.datamodel.catalog.model.FeedReference.class);

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

        _fetchFeedReferenceMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchFeedReference", long.class);

        _getFeedReferenceMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFeedReference", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getFeedReferencesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFeedReferences", int.class, int.class);

        _getFeedReferencesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFeedReferencesCount");

        _updateFeedReferenceMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFeedReference",
                org.nterlearning.datamodel.catalog.model.FeedReference.class);

        _updateFeedReferenceMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFeedReference",
                org.nterlearning.datamodel.catalog.model.FeedReference.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _findByFeedIriMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedIri", java.lang.String.class);

        _fetchByFeedIriMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchByFeedIri", java.lang.String.class);

        _findByFeedHrefMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedHref", java.lang.String.class);

        _generateDynamicQueryMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "generateDynamicQuery", long.class, boolean.class);

        _generateDynamicQueryMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "generateDynamicQuery", boolean.class);

        _generateDynamicQueryMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "generateDynamicQuery", boolean.class, java.lang.String.class);

        _generateDynamicQueryMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "generateDynamicQuery", java.lang.String.class, long.class,
                boolean.class);

        _removeAssociatedVocabulariesMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeAssociatedVocabularies", long.class);

        _clearCacheMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache");

        _clearCacheMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache",
                org.nterlearning.datamodel.catalog.model.FeedReference.class);

        _clearCacheMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache", java.lang.Long.class);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference addFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addFeedReferenceMethodKey0,
                ClpSerializer.translateInput(feedReference));

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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference createFeedReference(
        long feedReferenceId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createFeedReferenceMethodKey1,
                feedReferenceId);

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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteFeedReference(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteFeedReferenceMethodKey2,
                feedReferenceId);

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

    public void deleteFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteFeedReferenceMethodKey3,
                ClpSerializer.translateInput(feedReference));

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

    public org.nterlearning.datamodel.catalog.model.FeedReference fetchFeedReference(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchFeedReferenceMethodKey8,
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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference getFeedReference(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFeedReferenceMethodKey9,
                feedReferenceId);

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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> getFeedReferences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFeedReferencesMethodKey11,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference>) ClpSerializer.translateOutput(returnObj);
    }

    public int getFeedReferencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFeedReferencesCountMethodKey12);

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

    public org.nterlearning.datamodel.catalog.model.FeedReference updateFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateFeedReferenceMethodKey13,
                ClpSerializer.translateInput(feedReference));

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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference updateFeedReference(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateFeedReferenceMethodKey14,
                ClpSerializer.translateInput(feedReference), merge);

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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
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

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedIriMethodKey17,
                ClpSerializer.translateInput(feedIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference fetchByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchByFeedIriMethodKey18,
                ClpSerializer.translateInput(feedIri));

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

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedHrefMethodKey19,
                ClpSerializer.translateInput(href));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.FeedReference) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        long groupId, boolean removed) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_generateDynamicQueryMethodKey20,
                groupId, removed);

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

        return (com.liferay.portal.kernel.dao.orm.DynamicQuery) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        boolean removed) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_generateDynamicQueryMethodKey21,
                removed);

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

        return (com.liferay.portal.kernel.dao.orm.DynamicQuery) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        boolean removed, java.lang.String reasonCode) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_generateDynamicQueryMethodKey22,
                removed, ClpSerializer.translateInput(reasonCode));

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

        return (com.liferay.portal.kernel.dao.orm.DynamicQuery) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery generateDynamicQuery(
        java.lang.String feedType, long groupId, boolean removed) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_generateDynamicQueryMethodKey23,
                ClpSerializer.translateInput(feedType), groupId, removed);

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

        return (com.liferay.portal.kernel.dao.orm.DynamicQuery) ClpSerializer.translateOutput(returnObj);
    }

    public void removeAssociatedVocabularies(long feedReferenceId) {
        MethodHandler methodHandler = new MethodHandler(_removeAssociatedVocabulariesMethodKey24,
                feedReferenceId);

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

    public void clearCache() {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey25);

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

    public void clearCache(
        org.nterlearning.datamodel.catalog.model.FeedReference feedRef) {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey26,
                ClpSerializer.translateInput(feedRef));

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

    public void clearCache(java.lang.Long feedRefId) {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey27,
                ClpSerializer.translateInput(feedRefId));

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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
