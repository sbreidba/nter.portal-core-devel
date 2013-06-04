package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class FeedReferenceServiceClp implements FeedReferenceService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _findByFeedIriMethodKey0;
    private MethodKey _findByFeedHrefMethodKey1;

    public FeedReferenceServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _findByFeedIriMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedIri", java.lang.String.class);

        _findByFeedHrefMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedHref", java.lang.String.class);
    }

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedIriMethodKey0,
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

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedHrefMethodKey1,
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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
