package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ComponentLocalServiceClp implements ComponentLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addComponentMethodKey0;
    private MethodKey _createComponentMethodKey1;
    private MethodKey _deleteComponentMethodKey2;
    private MethodKey _deleteComponentMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchComponentMethodKey8;
    private MethodKey _getComponentMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getComponentsMethodKey11;
    private MethodKey _getComponentsCountMethodKey12;
    private MethodKey _updateComponentMethodKey13;
    private MethodKey _updateComponentMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _addComponentMethodKey17;
    private MethodKey _addComponentMethodKey18;
    private MethodKey _deleteAllChildrenMethodKey19;
    private MethodKey _clearCacheMethodKey20;
    private MethodKey _clearCacheMethodKey21;
    private MethodKey _clearCacheMethodKey22;
    private MethodKey _findByCompanyIdMethodKey23;
    private MethodKey _findByComponentIdMethodKey24;
    private MethodKey _fetchByComponentIdMethodKey25;
    private MethodKey _findByComponentIriMethodKey26;
    private MethodKey _findByFeedReferenceIdMethodKey27;
    private MethodKey _fetchByComponentIriMethodKey28;
    private MethodKey _getAuthorsMethodKey29;
    private MethodKey _getContributorsMethodKey30;
    private MethodKey _getContributorsMethodKey31;
    private MethodKey _getComponentRecordsMethodKey32;
    private MethodKey _getCourses_ComponentsesMethodKey33;
    private MethodKey _getExternalLinksMethodKey34;
    private MethodKey _getExternalLinksMethodKey35;
    private MethodKey _findByCourseIdLanguageSortedMethodKey36;

    public ComponentLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addComponentMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addComponent",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _createComponentMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createComponent", long.class);

        _deleteComponentMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteComponent", long.class);

        _deleteComponentMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteComponent",
                org.nterlearning.datamodel.catalog.model.Component.class);

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

        _fetchComponentMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchComponent", long.class);

        _getComponentMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getComponent", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getComponentsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getComponents", int.class, int.class);

        _getComponentsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getComponentsCount");

        _updateComponentMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateComponent",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _updateComponentMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateComponent",
                org.nterlearning.datamodel.catalog.model.Component.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _addComponentMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "addComponent", long.class, long.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.util.Date.class, int.class, int.class,
                java.lang.String.class, java.util.Date.class, double.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class);

        _addComponentMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "addComponent", long.class, long.class, long.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.util.Date.class, int.class, int.class,
                java.lang.String.class, java.util.Date.class, double.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class);

        _deleteAllChildrenMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteAllChildren",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _clearCacheMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache");

        _clearCacheMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _clearCacheMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache", java.lang.Long.class);

        _findByCompanyIdMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCompanyId", long.class);

        _findByComponentIdMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByComponentId", long.class);

        _fetchByComponentIdMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchByComponentId", long.class);

        _findByComponentIriMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByComponentIri", java.lang.String.class);

        _findByFeedReferenceIdMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedReferenceId", java.lang.Long.class);

        _fetchByComponentIriMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchByComponentIri", java.lang.String.class);

        _getAuthorsMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthors",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _getContributorsMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContributors",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _getContributorsMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContributors", long.class);

        _getComponentRecordsMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "getComponentRecords",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _getCourses_ComponentsesMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourses_Componentses",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _getExternalLinksMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "getExternalLinks",
                org.nterlearning.datamodel.catalog.model.Component.class);

        _getExternalLinksMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "getExternalLinks", long.class);

        _findByCourseIdLanguageSortedMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIdLanguageSorted", long.class,
                java.util.Locale.class, int.class, int.class);
    }

    public org.nterlearning.datamodel.catalog.model.Component addComponent(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addComponentMethodKey0,
                ClpSerializer.translateInput(component));

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component createComponent(
        long componentId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createComponentMethodKey1,
                componentId);

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteComponent(long componentId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteComponentMethodKey2,
                componentId);

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

    public void deleteComponent(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteComponentMethodKey3,
                ClpSerializer.translateInput(component));

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

    public org.nterlearning.datamodel.catalog.model.Component fetchComponent(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchComponentMethodKey8,
                componentId);

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component getComponent(
        long componentId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getComponentMethodKey9,
                componentId);

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getComponentsMethodKey11,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Component>) ClpSerializer.translateOutput(returnObj);
    }

    public int getComponentsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getComponentsCountMethodKey12);

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

    public org.nterlearning.datamodel.catalog.model.Component updateComponent(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateComponentMethodKey13,
                ClpSerializer.translateInput(component));

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component updateComponent(
        org.nterlearning.datamodel.catalog.model.Component component,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateComponentMethodKey14,
                ClpSerializer.translateInput(component), merge);

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
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

    public org.nterlearning.datamodel.catalog.model.Component addComponent(
        long companyId, long feedReferenceId, java.lang.String componentIRI,
        java.lang.String description, java.lang.String title,
        java.lang.String href, java.lang.String lang,
        java.util.Date updateDate, int displayHeight, int displayWidth,
        java.lang.String version, java.util.Date versionDate, double price,
        java.lang.String priceUnit, java.lang.String priceTerms,
        java.lang.String priceExpiration)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addComponentMethodKey17,
                companyId, feedReferenceId,
                ClpSerializer.translateInput(componentIRI),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(href),
                ClpSerializer.translateInput(lang),
                ClpSerializer.translateInput(updateDate), displayHeight,
                displayWidth, ClpSerializer.translateInput(version),
                ClpSerializer.translateInput(versionDate), price,
                ClpSerializer.translateInput(priceUnit),
                ClpSerializer.translateInput(priceTerms),
                ClpSerializer.translateInput(priceExpiration));

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component addComponent(
        long companyId, long groupId, long feedReferenceId,
        java.lang.String componentIRI, java.lang.String description,
        java.lang.String title, java.lang.String href,
        java.lang.String fullTextHref, java.lang.String lang,
        java.util.Date updateDate, int displayHeight, int displayWidth,
        java.lang.String version, java.util.Date versionDate, double price,
        java.lang.String priceUnit, java.lang.String priceTerms,
        java.lang.String priceExpiration)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addComponentMethodKey18,
                companyId, groupId, feedReferenceId,
                ClpSerializer.translateInput(componentIRI),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(href),
                ClpSerializer.translateInput(fullTextHref),
                ClpSerializer.translateInput(lang),
                ClpSerializer.translateInput(updateDate), displayHeight,
                displayWidth, ClpSerializer.translateInput(version),
                ClpSerializer.translateInput(versionDate), price,
                ClpSerializer.translateInput(priceUnit),
                ClpSerializer.translateInput(priceTerms),
                ClpSerializer.translateInput(priceExpiration));

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteAllChildrenMethodKey19,
                ClpSerializer.translateInput(component));

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

    public void clearCache() {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey20);

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
        org.nterlearning.datamodel.catalog.model.Component component) {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey21,
                ClpSerializer.translateInput(component));

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

    public void clearCache(java.lang.Long componentId) {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey22,
                ClpSerializer.translateInput(componentId));

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

    public java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCompanyIdMethodKey23,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Component>) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByComponentIdMethodKey24,
                componentId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchComponentException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchComponentException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component fetchByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchByComponentIdMethodKey25,
                componentId);

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByComponentIriMethodKey26,
                ClpSerializer.translateInput(componentIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchComponentException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchComponentException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        java.lang.Long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedReferenceIdMethodKey27,
                ClpSerializer.translateInput(feedRefId));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Component>) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Component fetchByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchByComponentIriMethodKey28,
                ClpSerializer.translateInput(componentIri));

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

        return (org.nterlearning.datamodel.catalog.model.Component) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getAuthors(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAuthorsMethodKey29,
                ClpSerializer.translateInput(component));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Contributor>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getContributorsMethodKey30,
                ClpSerializer.translateInput(component));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Contributor>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long componentPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getContributorsMethodKey31,
                componentPrimaryKey);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Contributor>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getComponentRecordsMethodKey32,
                ClpSerializer.translateInput(component));

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

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourses_ComponentsesMethodKey33,
                ClpSerializer.translateInput(component));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        org.nterlearning.datamodel.catalog.model.Component component)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getExternalLinksMethodKey34,
                ClpSerializer.translateInput(component));

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getExternalLinksMethodKey35,
                componentId);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<java.lang.Object[]> findByCourseIdLanguageSorted(
        long courseId, java.util.Locale locale, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdLanguageSortedMethodKey36,
                courseId, ClpSerializer.translateInput(locale), start, end);

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

        return (java.util.List<java.lang.Object[]>) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
