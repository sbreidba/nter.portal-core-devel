package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class FlagReportLocalServiceClp implements FlagReportLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addFlagReportMethodKey0;
    private MethodKey _createFlagReportMethodKey1;
    private MethodKey _deleteFlagReportMethodKey2;
    private MethodKey _deleteFlagReportMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchFlagReportMethodKey8;
    private MethodKey _getFlagReportMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getFlagReportByUuidAndGroupIdMethodKey11;
    private MethodKey _getFlagReportsMethodKey12;
    private MethodKey _getFlagReportsCountMethodKey13;
    private MethodKey _updateFlagReportMethodKey14;
    private MethodKey _updateFlagReportMethodKey15;
    private MethodKey _getBeanIdentifierMethodKey16;
    private MethodKey _setBeanIdentifierMethodKey17;
    private MethodKey _addFlagReportMethodKey18;
    private MethodKey _addFlagReportResourcesMethodKey19;
    private MethodKey _addFlagReportResourcesMethodKey20;
    private MethodKey _updateAssetMethodKey21;
    private MethodKey _deleteReportsMethodKey22;
    private MethodKey _deleteReportMethodKey23;
    private MethodKey _deleteReportMethodKey24;
    private MethodKey _updateReportMethodKey25;
    private MethodKey _updateFlagReportMethodKey26;
    private MethodKey _moderateFlagReportMethodKey27;
    private MethodKey _updateFlagReportResourcesMethodKey28;
    private MethodKey _updateFlagReportStatsMethodKey29;
    private MethodKey _updateStatusMethodKey30;
    private MethodKey _removeWorkflowInstanceMethodKey31;
    private MethodKey _findByFlagReportIdMethodKey32;
    private MethodKey _fetchByFlagReportIdMethodKey33;
    private MethodKey _findByGroupIdMethodKey34;
    private MethodKey _findByCompanyIdMethodKey35;
    private MethodKey _findByClassNameIdClassPKMethodKey36;
    private MethodKey _findByClassNameIdAndFilterMethodKey37;

    public FlagReportLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addFlagReportMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFlagReport",
                org.nterlearning.datamodel.catalog.model.FlagReport.class);

        _createFlagReportMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createFlagReport", long.class);

        _deleteFlagReportMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteFlagReport", long.class);

        _deleteFlagReportMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteFlagReport",
                org.nterlearning.datamodel.catalog.model.FlagReport.class);

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

        _fetchFlagReportMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchFlagReport", long.class);

        _getFlagReportMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFlagReport", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getFlagReportByUuidAndGroupIdMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFlagReportByUuidAndGroupId", java.lang.String.class,
                long.class);

        _getFlagReportsMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFlagReports", int.class, int.class);

        _getFlagReportsCountMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFlagReportsCount");

        _updateFlagReportMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFlagReport",
                org.nterlearning.datamodel.catalog.model.FlagReport.class);

        _updateFlagReportMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFlagReport",
                org.nterlearning.datamodel.catalog.model.FlagReport.class,
                boolean.class);

        _getBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _addFlagReportMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFlagReport", long.class, long.class, long.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.service.ServiceContext.class);

        _addFlagReportResourcesMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFlagReportResources",
                org.nterlearning.datamodel.catalog.model.FlagReport.class,
                boolean.class, boolean.class);

        _addFlagReportResourcesMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFlagReportResources",
                org.nterlearning.datamodel.catalog.model.FlagReport.class,
                java.lang.String[].class, java.lang.String[].class);

        _updateAssetMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAsset", long.class,
                org.nterlearning.datamodel.catalog.model.FlagReport.class,
                long[].class, java.lang.String[].class);

        _deleteReportsMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteReports", long.class);

        _deleteReportMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteReport",
                org.nterlearning.datamodel.catalog.model.FlagReport.class);

        _deleteReportMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteReport", long.class);

        _updateReportMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateReport",
                org.nterlearning.datamodel.catalog.model.FlagReport.class);

        _updateFlagReportMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFlagReport", long.class, long.class, long.class,
                long.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.Integer.class, long.class, java.lang.String.class,
                java.util.Date.class,
                com.liferay.portal.service.ServiceContext.class);

        _moderateFlagReportMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "moderateFlagReport", long.class, long.class,
                java.lang.Integer.class, java.lang.String.class,
                java.lang.String.class,
                com.liferay.portal.service.ServiceContext.class);

        _updateFlagReportResourcesMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFlagReportResources",
                org.nterlearning.datamodel.catalog.model.FlagReport.class,
                java.lang.String[].class, java.lang.String[].class);

        _updateFlagReportStatsMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateFlagReportStats", long.class, long.class);

        _updateStatusMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateStatus", long.class, long.class, int.class,
                com.liferay.portal.service.ServiceContext.class);

        _removeWorkflowInstanceMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeWorkflowInstance", long.class, long.class,
                java.lang.String.class, long.class);

        _findByFlagReportIdMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFlagReportId", long.class);

        _fetchByFlagReportIdMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchByFlagReportId", long.class);

        _findByGroupIdMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByGroupId", long.class);

        _findByCompanyIdMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCompanyId", long.class);

        _findByClassNameIdClassPKMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByClassNameIdClassPK", long.class, long.class);

        _findByClassNameIdAndFilterMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByClassNameIdAndFilter", long.class,
                java.lang.String.class, int.class, int.class);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport addFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addFlagReportMethodKey0,
                ClpSerializer.translateInput(flagReport));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport createFlagReport(
        long flagReportId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createFlagReportMethodKey1,
                flagReportId);

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteFlagReport(long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteFlagReportMethodKey2,
                flagReportId);

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

    public void deleteFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteFlagReportMethodKey3,
                ClpSerializer.translateInput(flagReport));

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

    public org.nterlearning.datamodel.catalog.model.FlagReport fetchFlagReport(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchFlagReportMethodKey8,
                flagReportId);

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport getFlagReport(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFlagReportMethodKey9,
                flagReportId);

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
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

    public org.nterlearning.datamodel.catalog.model.FlagReport getFlagReportByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFlagReportByUuidAndGroupIdMethodKey11,
                ClpSerializer.translateInput(uuid), groupId);

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> getFlagReports(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFlagReportsMethodKey12,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport>) ClpSerializer.translateOutput(returnObj);
    }

    public int getFlagReportsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFlagReportsCountMethodKey13);

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

    public org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateFlagReportMethodKey14,
                ClpSerializer.translateInput(flagReport));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateFlagReportMethodKey15,
                ClpSerializer.translateInput(flagReport), merge);

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey16);

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
        MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey17,
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

    public org.nterlearning.datamodel.catalog.model.FlagReport addFlagReport(
        long userId, long classNameId, long classPK, java.lang.String title,
        java.lang.String content, java.lang.String flagReason,
        java.lang.String flagComment, java.lang.String moderateAction,
        java.lang.String moderatorComment,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addFlagReportMethodKey18,
                userId, classNameId, classPK,
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(content),
                ClpSerializer.translateInput(flagReason),
                ClpSerializer.translateInput(flagComment),
                ClpSerializer.translateInput(moderateAction),
                ClpSerializer.translateInput(moderatorComment),
                ClpSerializer.translateInput(serviceContext));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public void addFlagReportResources(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addFlagReportResourcesMethodKey19,
                ClpSerializer.translateInput(flagReport),
                addCommunityPermissions, addGuestPermissions);

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

    public void addFlagReportResources(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addFlagReportResourcesMethodKey20,
                ClpSerializer.translateInput(flagReport),
                ClpSerializer.translateInput(communityPermissions),
                ClpSerializer.translateInput(guestPermissions));

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

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateAssetMethodKey21,
                userId, ClpSerializer.translateInput(flagReport),
                ClpSerializer.translateInput(assetCategoryIds),
                ClpSerializer.translateInput(assetTagNames));

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

    public void deleteReports(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteReportsMethodKey22,
                groupId);

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

    public void deleteReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteReportMethodKey23,
                ClpSerializer.translateInput(flagReport));

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

    public void deleteReport(long flagReportId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteReportMethodKey24,
                flagReportId);

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

    public org.nterlearning.datamodel.catalog.model.FlagReport updateReport(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateReportMethodKey25,
                ClpSerializer.translateInput(flagReport));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport updateFlagReport(
        long userId, long flagReportId, long classNameId, long classPK,
        java.lang.String title, java.lang.String content,
        java.lang.String flagReason, java.lang.String flagComment,
        java.lang.String moderateAction, java.lang.String moderatorComment,
        java.lang.Integer Status, long statusByUserId,
        java.lang.String statusByUserName, java.util.Date StatusDate,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateFlagReportMethodKey26,
                userId, flagReportId, classNameId, classPK,
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(content),
                ClpSerializer.translateInput(flagReason),
                ClpSerializer.translateInput(flagComment),
                ClpSerializer.translateInput(moderateAction),
                ClpSerializer.translateInput(moderatorComment),
                ClpSerializer.translateInput(Status), statusByUserId,
                ClpSerializer.translateInput(statusByUserName),
                ClpSerializer.translateInput(StatusDate),
                ClpSerializer.translateInput(serviceContext));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport moderateFlagReport(
        long userId, long flagReportId, java.lang.Integer moderateStatus,
        java.lang.String moderateAction, java.lang.String moderatorComment,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_moderateFlagReportMethodKey27,
                userId, flagReportId,
                ClpSerializer.translateInput(moderateStatus),
                ClpSerializer.translateInput(moderateAction),
                ClpSerializer.translateInput(moderatorComment),
                ClpSerializer.translateInput(serviceContext));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public void updateFlagReportResources(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateFlagReportResourcesMethodKey28,
                ClpSerializer.translateInput(flagReport),
                ClpSerializer.translateInput(communityPermissions),
                ClpSerializer.translateInput(guestPermissions));

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

    public void updateFlagReportStats(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateFlagReportStatsMethodKey29,
                classNameId, classPK);

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

    public org.nterlearning.datamodel.catalog.model.FlagReport updateStatus(
        long userId, long resourcePrimKey, int status,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateStatusMethodKey30,
                userId, resourcePrimKey, status,
                ClpSerializer.translateInput(serviceContext));

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public void removeWorkflowInstance(long groupId, long companyId,
        java.lang.String className, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_removeWorkflowInstanceMethodKey31,
                groupId, companyId, ClpSerializer.translateInput(className),
                classPK);

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

    public org.nterlearning.datamodel.catalog.model.FlagReport findByFlagReportId(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFlagReportException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFlagReportIdMethodKey32,
                flagReportId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchFlagReportException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchFlagReportException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.FlagReport fetchByFlagReportId(
        long flagReportId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchByFlagReportIdMethodKey33,
                flagReportId);

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

        return (org.nterlearning.datamodel.catalog.model.FlagReport) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByGroupIdMethodKey34,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCompanyIdMethodKey35,
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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport> findByClassNameIdClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByClassNameIdClassPKMethodKey36,
                classNameId, classPK);

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

        return (java.util.List<org.nterlearning.datamodel.catalog.model.FlagReport>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<java.lang.Object[]> findByClassNameIdAndFilter(
        long classNameId, java.lang.String filterType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByClassNameIdAndFilterMethodKey37,
                classNameId, ClpSerializer.translateInput(filterType), start,
                end);

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
