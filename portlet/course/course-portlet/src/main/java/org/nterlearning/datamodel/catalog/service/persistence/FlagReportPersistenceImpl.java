package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;

import org.nterlearning.datamodel.catalog.NoSuchFlagReportException;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportImpl;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportModelImpl;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.ComponentRecordPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.ContributorPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseImagePersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CoursePersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRecordPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRelatedPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRequirementPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.CourseReviewPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.Courses_ComponentsPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.ExternalLinkPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FeedReferencePersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FeedSyncHistoryPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FlagReportPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.FlagReportStatsPersistence;
import org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the flag report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagReportPersistence
 * @see FlagReportUtil
 * @generated
 */
public class FlagReportPersistenceImpl extends BasePersistenceImpl<FlagReport>
    implements FlagReportPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FlagReportUtil} to access the flag report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FlagReportImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
            new String[] { String.class.getName() },
            FlagReportModelImpl.UUID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
            new String[] { String.class.getName(), Long.class.getName() },
            FlagReportModelImpl.UUID_COLUMN_BITMASK |
            FlagReportModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_FLAGREPORTID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByflagReportId",
            new String[] { Long.class.getName() },
            FlagReportModelImpl.FLAGREPORTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FLAGREPORTID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByflagReportId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
            new String[] { Long.class.getName() },
            FlagReportModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
            new String[] { Long.class.getName() },
            FlagReportModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK =
        new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByClassNameIdWithClassPK",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK =
        new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByClassNameIdWithClassPK",
            new String[] { Long.class.getName(), Long.class.getName() },
            FlagReportModelImpl.CLASSNAMEID_COLUMN_BITMASK |
            FlagReportModelImpl.CLASSPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByClassNameIdWithClassPK",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, FlagReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FLAGREPORT = "SELECT flagReport FROM FlagReport flagReport";
    private static final String _SQL_SELECT_FLAGREPORT_WHERE = "SELECT flagReport FROM FlagReport flagReport WHERE ";
    private static final String _SQL_COUNT_FLAGREPORT = "SELECT COUNT(flagReport) FROM FlagReport flagReport";
    private static final String _SQL_COUNT_FLAGREPORT_WHERE = "SELECT COUNT(flagReport) FROM FlagReport flagReport WHERE ";
    private static final String _FINDER_COLUMN_UUID_UUID_1 = "flagReport.uuid IS NULL";
    private static final String _FINDER_COLUMN_UUID_UUID_2 = "flagReport.uuid = ?";
    private static final String _FINDER_COLUMN_UUID_UUID_3 = "(flagReport.uuid IS NULL OR flagReport.uuid = ?)";
    private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "flagReport.uuid IS NULL AND ";
    private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "flagReport.uuid = ? AND ";
    private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(flagReport.uuid IS NULL OR flagReport.uuid = ?) AND ";
    private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "flagReport.groupId = ?";
    private static final String _FINDER_COLUMN_FLAGREPORTID_FLAGREPORTID_2 = "flagReport.flagReportId = ?";
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "flagReport.groupId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "flagReport.companyId = ?";
    private static final String _FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSNAMEID_2 =
        "flagReport.classNameId = ? AND ";
    private static final String _FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSPK_2 = "flagReport.classPK = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "flagReport.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FlagReport exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FlagReport exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FlagReportPersistenceImpl.class);
    private static FlagReport _nullFlagReport = new FlagReportImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FlagReport> toCacheModel() {
                return _nullFlagReportCacheModel;
            }
        };

    private static CacheModel<FlagReport> _nullFlagReportCacheModel = new CacheModel<FlagReport>() {
            public FlagReport toEntityModel() {
                return _nullFlagReport;
            }
        };

    @BeanReference(type = ComponentPersistence.class)
    protected ComponentPersistence componentPersistence;
    @BeanReference(type = ComponentRecordPersistence.class)
    protected ComponentRecordPersistence componentRecordPersistence;
    @BeanReference(type = ContributorPersistence.class)
    protected ContributorPersistence contributorPersistence;
    @BeanReference(type = CoursePersistence.class)
    protected CoursePersistence coursePersistence;
    @BeanReference(type = CourseImagePersistence.class)
    protected CourseImagePersistence courseImagePersistence;
    @BeanReference(type = CourseRecordPersistence.class)
    protected CourseRecordPersistence courseRecordPersistence;
    @BeanReference(type = CourseRelatedPersistence.class)
    protected CourseRelatedPersistence courseRelatedPersistence;
    @BeanReference(type = CourseRequirementPersistence.class)
    protected CourseRequirementPersistence courseRequirementPersistence;
    @BeanReference(type = CourseReviewPersistence.class)
    protected CourseReviewPersistence courseReviewPersistence;
    @BeanReference(type = Courses_ComponentsPersistence.class)
    protected Courses_ComponentsPersistence courses_ComponentsPersistence;
    @BeanReference(type = ExternalLinkPersistence.class)
    protected ExternalLinkPersistence externalLinkPersistence;
    @BeanReference(type = FeedReferencePersistence.class)
    protected FeedReferencePersistence feedReferencePersistence;
    @BeanReference(type = FeedSyncHistoryPersistence.class)
    protected FeedSyncHistoryPersistence feedSyncHistoryPersistence;
    @BeanReference(type = FlagReportPersistence.class)
    protected FlagReportPersistence flagReportPersistence;
    @BeanReference(type = FlagReportStatsPersistence.class)
    protected FlagReportStatsPersistence flagReportStatsPersistence;
    @BeanReference(type = GlobalCourseReviewPersistence.class)
    protected GlobalCourseReviewPersistence globalCourseReviewPersistence;
    @BeanReference(type = CompanyPersistence.class)
    protected CompanyPersistence companyPersistence;
    @BeanReference(type = GroupPersistence.class)
    protected GroupPersistence groupPersistence;
    @BeanReference(type = OrganizationPersistence.class)
    protected OrganizationPersistence organizationPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    @BeanReference(type = WorkflowInstanceLinkPersistence.class)
    protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
    @BeanReference(type = AssetEntryPersistence.class)
    protected AssetEntryPersistence assetEntryPersistence;

    /**
     * Caches the flag report in the entity cache if it is enabled.
     *
     * @param flagReport the flag report
     */
    public void cacheResult(FlagReport flagReport) {
        EntityCacheUtil.putResult(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportImpl.class, flagReport.getPrimaryKey(), flagReport);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
            new Object[] {
                flagReport.getUuid(), Long.valueOf(flagReport.getGroupId())
            }, flagReport);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
            new Object[] { Long.valueOf(flagReport.getFlagReportId()) },
            flagReport);

        flagReport.resetOriginalValues();
    }

    /**
     * Caches the flag reports in the entity cache if it is enabled.
     *
     * @param flagReports the flag reports
     */
    public void cacheResult(List<FlagReport> flagReports) {
        for (FlagReport flagReport : flagReports) {
            if (EntityCacheUtil.getResult(
                        FlagReportModelImpl.ENTITY_CACHE_ENABLED,
                        FlagReportImpl.class, flagReport.getPrimaryKey()) == null) {
                cacheResult(flagReport);
            } else {
                flagReport.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all flag reports.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FlagReportImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FlagReportImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the flag report.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FlagReport flagReport) {
        EntityCacheUtil.removeResult(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportImpl.class, flagReport.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(flagReport);
    }

    @Override
    public void clearCache(List<FlagReport> flagReports) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FlagReport flagReport : flagReports) {
            EntityCacheUtil.removeResult(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
                FlagReportImpl.class, flagReport.getPrimaryKey());

            clearUniqueFindersCache(flagReport);
        }
    }

    protected void clearUniqueFindersCache(FlagReport flagReport) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
            new Object[] {
                flagReport.getUuid(), Long.valueOf(flagReport.getGroupId())
            });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
            new Object[] { Long.valueOf(flagReport.getFlagReportId()) });
    }

    /**
     * Creates a new flag report with the primary key. Does not add the flag report to the database.
     *
     * @param flagReportId the primary key for the new flag report
     * @return the new flag report
     */
    public FlagReport create(long flagReportId) {
        FlagReport flagReport = new FlagReportImpl();

        flagReport.setNew(true);
        flagReport.setPrimaryKey(flagReportId);

        String uuid = PortalUUIDUtil.generate();

        flagReport.setUuid(uuid);

        return flagReport;
    }

    /**
     * Removes the flag report with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param flagReportId the primary key of the flag report
     * @return the flag report that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport remove(long flagReportId)
        throws NoSuchFlagReportException, SystemException {
        return remove(Long.valueOf(flagReportId));
    }

    /**
     * Removes the flag report with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the flag report
     * @return the flag report that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FlagReport remove(Serializable primaryKey)
        throws NoSuchFlagReportException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FlagReport flagReport = (FlagReport) session.get(FlagReportImpl.class,
                    primaryKey);

            if (flagReport == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFlagReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(flagReport);
        } catch (NoSuchFlagReportException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FlagReport removeImpl(FlagReport flagReport)
        throws SystemException {
        flagReport = toUnwrappedModel(flagReport);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, flagReport);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(flagReport);

        return flagReport;
    }

    @Override
    public FlagReport updateImpl(
        org.nterlearning.datamodel.catalog.model.FlagReport flagReport,
        boolean merge) throws SystemException {
        flagReport = toUnwrappedModel(flagReport);

        boolean isNew = flagReport.isNew();

        FlagReportModelImpl flagReportModelImpl = (FlagReportModelImpl) flagReport;

        if (Validator.isNull(flagReport.getUuid())) {
            String uuid = PortalUUIDUtil.generate();

            flagReport.setUuid(uuid);
        }

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, flagReport, merge);

            flagReport.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !FlagReportModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((flagReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        flagReportModelImpl.getOriginalUuid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);

                args = new Object[] { flagReportModelImpl.getUuid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);
            }

            if ((flagReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getOriginalGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }

            if ((flagReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getOriginalCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((flagReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getOriginalClassNameId()),
                        Long.valueOf(flagReportModelImpl.getOriginalClassPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK,
                    args);

                args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getClassNameId()),
                        Long.valueOf(flagReportModelImpl.getClassPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK,
                    args);
            }
        }

        EntityCacheUtil.putResult(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportImpl.class, flagReport.getPrimaryKey(), flagReport);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                new Object[] {
                    flagReport.getUuid(), Long.valueOf(flagReport.getGroupId())
                }, flagReport);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                new Object[] { Long.valueOf(flagReport.getFlagReportId()) },
                flagReport);
        } else {
            if ((flagReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        flagReportModelImpl.getOriginalUuid(),
                        Long.valueOf(flagReportModelImpl.getOriginalGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                    new Object[] {
                        flagReport.getUuid(),
                        Long.valueOf(flagReport.getGroupId())
                    }, flagReport);
            }

            if ((flagReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FLAGREPORTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(flagReportModelImpl.getOriginalFlagReportId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FLAGREPORTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                    new Object[] { Long.valueOf(flagReport.getFlagReportId()) },
                    flagReport);
            }
        }

        return flagReport;
    }

    protected FlagReport toUnwrappedModel(FlagReport flagReport) {
        if (flagReport instanceof FlagReportImpl) {
            return flagReport;
        }

        FlagReportImpl flagReportImpl = new FlagReportImpl();

        flagReportImpl.setNew(flagReport.isNew());
        flagReportImpl.setPrimaryKey(flagReport.getPrimaryKey());

        flagReportImpl.setUuid(flagReport.getUuid());
        flagReportImpl.setFlagReportId(flagReport.getFlagReportId());
        flagReportImpl.setGroupId(flagReport.getGroupId());
        flagReportImpl.setCompanyId(flagReport.getCompanyId());
        flagReportImpl.setUserId(flagReport.getUserId());
        flagReportImpl.setClassNameId(flagReport.getClassNameId());
        flagReportImpl.setClassPK(flagReport.getClassPK());
        flagReportImpl.setCreateDate(flagReport.getCreateDate());
        flagReportImpl.setTitle(flagReport.getTitle());
        flagReportImpl.setContent(flagReport.getContent());
        flagReportImpl.setFlagReason(flagReport.getFlagReason());
        flagReportImpl.setFlagComment(flagReport.getFlagComment());
        flagReportImpl.setModerateAction(flagReport.getModerateAction());
        flagReportImpl.setModeratorComment(flagReport.getModeratorComment());
        flagReportImpl.setStatus(flagReport.getStatus());
        flagReportImpl.setStatusByUserId(flagReport.getStatusByUserId());
        flagReportImpl.setStatusByUserName(flagReport.getStatusByUserName());
        flagReportImpl.setStatusDate(flagReport.getStatusDate());

        return flagReportImpl;
    }

    /**
     * Returns the flag report with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the flag report
     * @return the flag report
     * @throws com.liferay.portal.NoSuchModelException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FlagReport findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the flag report with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportException} if it could not be found.
     *
     * @param flagReportId the primary key of the flag report
     * @return the flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByPrimaryKey(long flagReportId)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = fetchByPrimaryKey(flagReportId);

        if (flagReport == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + flagReportId);
            }

            throw new NoSuchFlagReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                flagReportId);
        }

        return flagReport;
    }

    /**
     * Returns the flag report with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the flag report
     * @return the flag report, or <code>null</code> if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FlagReport fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the flag report with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param flagReportId the primary key of the flag report
     * @return the flag report, or <code>null</code> if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport fetchByPrimaryKey(long flagReportId)
        throws SystemException {
        FlagReport flagReport = (FlagReport) EntityCacheUtil.getResult(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
                FlagReportImpl.class, flagReportId);

        if (flagReport == _nullFlagReport) {
            return null;
        }

        if (flagReport == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                flagReport = (FlagReport) session.get(FlagReportImpl.class,
                        Long.valueOf(flagReportId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (flagReport != null) {
                    cacheResult(flagReport);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(FlagReportModelImpl.ENTITY_CACHE_ENABLED,
                        FlagReportImpl.class, flagReportId, _nullFlagReport);
                }

                closeSession(session);
            }
        }

        return flagReport;
    }

    /**
     * Returns all the flag reports where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByUuid(String uuid) throws SystemException {
        return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the flag reports where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param uuid the uuid
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @return the range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByUuid(String uuid, int start, int end)
        throws SystemException {
        return findByUuid(uuid, start, end, null);
    }

    /**
     * Returns an ordered range of all the flag reports where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param uuid the uuid
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByUuid(String uuid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid, start, end, orderByComparator };
        }

        List<FlagReport> list = (List<FlagReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FLAGREPORT_WHERE);

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else {
                if (uuid.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_UUID_UUID_3);
                } else {
                    query.append(_FINDER_COLUMN_UUID_UUID_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(FlagReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (uuid != null) {
                    qPos.add(uuid);
                }

                list = (List<FlagReport>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first flag report in the ordered set where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByUuid_First(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        List<FlagReport> list = findByUuid(uuid, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("uuid=");
            msg.append(uuid);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last flag report in the ordered set where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByUuid_Last(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        int count = countByUuid(uuid);

        List<FlagReport> list = findByUuid(uuid, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("uuid=");
            msg.append(uuid);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the flag reports before and after the current flag report in the ordered set where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param flagReportId the primary key of the current flag report
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport[] findByUuid_PrevAndNext(long flagReportId, String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = findByPrimaryKey(flagReportId);

        Session session = null;

        try {
            session = openSession();

            FlagReport[] array = new FlagReportImpl[3];

            array[0] = getByUuid_PrevAndNext(session, flagReport, uuid,
                    orderByComparator, true);

            array[1] = flagReport;

            array[2] = getByUuid_PrevAndNext(session, flagReport, uuid,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FlagReport getByUuid_PrevAndNext(Session session,
        FlagReport flagReport, String uuid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FLAGREPORT_WHERE);

        if (uuid == null) {
            query.append(_FINDER_COLUMN_UUID_UUID_1);
        } else {
            if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }
        else {
            query.append(FlagReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (uuid != null) {
            qPos.add(uuid);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(flagReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FlagReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the flag report where uuid = &#63; and groupId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportException} if it could not be found.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByUUID_G(String uuid, long groupId)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = fetchByUUID_G(uuid, groupId);

        if (flagReport == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("uuid=");
            msg.append(uuid);

            msg.append(", groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFlagReportException(msg.toString());
        }

        return flagReport;
    }

    /**
     * Returns the flag report where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the matching flag report, or <code>null</code> if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport fetchByUUID_G(String uuid, long groupId)
        throws SystemException {
        return fetchByUUID_G(uuid, groupId, true);
    }

    /**
     * Returns the flag report where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching flag report, or <code>null</code> if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport fetchByUUID_G(String uuid, long groupId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { uuid, groupId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_FLAGREPORT_WHERE);

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_1);
            } else {
                if (uuid.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_UUID_G_UUID_3);
                } else {
                    query.append(_FINDER_COLUMN_UUID_G_UUID_2);
                }
            }

            query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

            query.append(FlagReportModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (uuid != null) {
                    qPos.add(uuid);
                }

                qPos.add(groupId);

                List<FlagReport> list = q.list();

                result = list;

                FlagReport flagReport = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                        finderArgs, list);
                } else {
                    flagReport = list.get(0);

                    cacheResult(flagReport);

                    if ((flagReport.getUuid() == null) ||
                            !flagReport.getUuid().equals(uuid) ||
                            (flagReport.getGroupId() != groupId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                            finderArgs, flagReport);
                    }
                }

                return flagReport;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (FlagReport) result;
            }
        }
    }

    /**
     * Returns the flag report where flagReportId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportException} if it could not be found.
     *
     * @param flagReportId the flag report ID
     * @return the matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByflagReportId(long flagReportId)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = fetchByflagReportId(flagReportId);

        if (flagReport == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("flagReportId=");
            msg.append(flagReportId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFlagReportException(msg.toString());
        }

        return flagReport;
    }

    /**
     * Returns the flag report where flagReportId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param flagReportId the flag report ID
     * @return the matching flag report, or <code>null</code> if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport fetchByflagReportId(long flagReportId)
        throws SystemException {
        return fetchByflagReportId(flagReportId, true);
    }

    /**
     * Returns the flag report where flagReportId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param flagReportId the flag report ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching flag report, or <code>null</code> if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport fetchByflagReportId(long flagReportId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { flagReportId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_FLAGREPORTID_FLAGREPORTID_2);

            query.append(FlagReportModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(flagReportId);

                List<FlagReport> list = q.list();

                result = list;

                FlagReport flagReport = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                        finderArgs, list);
                } else {
                    flagReport = list.get(0);

                    cacheResult(flagReport);

                    if ((flagReport.getFlagReportId() != flagReportId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                            finderArgs, flagReport);
                    }
                }

                return flagReport;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FLAGREPORTID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (FlagReport) result;
            }
        }
    }

    /**
     * Returns all the flag reports where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByGroupId(long groupId)
        throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the flag reports where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @return the range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByGroupId(long groupId, int start, int end)
        throws SystemException {
        return findByGroupId(groupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the flag reports where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByGroupId(long groupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
            finderArgs = new Object[] { groupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
            finderArgs = new Object[] { groupId, start, end, orderByComparator };
        }

        List<FlagReport> list = (List<FlagReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(FlagReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                list = (List<FlagReport>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first flag report in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        List<FlagReport> list = findByGroupId(groupId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last flag report in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        int count = countByGroupId(groupId);

        List<FlagReport> list = findByGroupId(groupId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the flag reports before and after the current flag report in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param flagReportId the primary key of the current flag report
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport[] findByGroupId_PrevAndNext(long flagReportId,
        long groupId, OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = findByPrimaryKey(flagReportId);

        Session session = null;

        try {
            session = openSession();

            FlagReport[] array = new FlagReportImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, flagReport, groupId,
                    orderByComparator, true);

            array[1] = flagReport;

            array[2] = getByGroupId_PrevAndNext(session, flagReport, groupId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FlagReport getByGroupId_PrevAndNext(Session session,
        FlagReport flagReport, long groupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FLAGREPORT_WHERE);

        query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }
        else {
            query.append(FlagReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(flagReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FlagReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the flag reports where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByCompanyId(long companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the flag reports where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @return the range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByCompanyId(long companyId, int start, int end)
        throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the flag reports where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByCompanyId(long companyId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
            finderArgs = new Object[] { companyId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
            finderArgs = new Object[] { companyId, start, end, orderByComparator };
        }

        List<FlagReport> list = (List<FlagReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(FlagReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyId);

                list = (List<FlagReport>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first flag report in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByCompanyId_First(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        List<FlagReport> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last flag report in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByCompanyId_Last(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        int count = countByCompanyId(companyId);

        List<FlagReport> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the flag reports before and after the current flag report in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param flagReportId the primary key of the current flag report
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport[] findByCompanyId_PrevAndNext(long flagReportId,
        long companyId, OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = findByPrimaryKey(flagReportId);

        Session session = null;

        try {
            session = openSession();

            FlagReport[] array = new FlagReportImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, flagReport,
                    companyId, orderByComparator, true);

            array[1] = flagReport;

            array[2] = getByCompanyId_PrevAndNext(session, flagReport,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FlagReport getByCompanyId_PrevAndNext(Session session,
        FlagReport flagReport, long companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FLAGREPORT_WHERE);

        query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }
        else {
            query.append(FlagReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(flagReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FlagReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the flag reports where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByClassNameIdWithClassPK(long classNameId,
        long classPK) throws SystemException {
        return findByClassNameIdWithClassPK(classNameId, classPK,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the flag reports where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @return the range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByClassNameIdWithClassPK(long classNameId,
        long classPK, int start, int end) throws SystemException {
        return findByClassNameIdWithClassPK(classNameId, classPK, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the flag reports where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findByClassNameIdWithClassPK(long classNameId,
        long classPK, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK;
            finderArgs = new Object[] { classNameId, classPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDWITHCLASSPK;
            finderArgs = new Object[] {
                    classNameId, classPK,
                    
                    start, end, orderByComparator
                };
        }

        List<FlagReport> list = (List<FlagReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSNAMEID_2);

            query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(FlagReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

                qPos.add(classPK);

                list = (List<FlagReport>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first flag report in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByClassNameIdWithClassPK_First(long classNameId,
        long classPK, OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        List<FlagReport> list = findByClassNameIdWithClassPK(classNameId,
                classPK, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("classNameId=");
            msg.append(classNameId);

            msg.append(", classPK=");
            msg.append(classPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last flag report in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a matching flag report could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport findByClassNameIdWithClassPK_Last(long classNameId,
        long classPK, OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        int count = countByClassNameIdWithClassPK(classNameId, classPK);

        List<FlagReport> list = findByClassNameIdWithClassPK(classNameId,
                classPK, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("classNameId=");
            msg.append(classNameId);

            msg.append(", classPK=");
            msg.append(classPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFlagReportException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the flag reports before and after the current flag report in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param flagReportId the primary key of the current flag report
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next flag report
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportException if a flag report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReport[] findByClassNameIdWithClassPK_PrevAndNext(
        long flagReportId, long classNameId, long classPK,
        OrderByComparator orderByComparator)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = findByPrimaryKey(flagReportId);

        Session session = null;

        try {
            session = openSession();

            FlagReport[] array = new FlagReportImpl[3];

            array[0] = getByClassNameIdWithClassPK_PrevAndNext(session,
                    flagReport, classNameId, classPK, orderByComparator, true);

            array[1] = flagReport;

            array[2] = getByClassNameIdWithClassPK_PrevAndNext(session,
                    flagReport, classNameId, classPK, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FlagReport getByClassNameIdWithClassPK_PrevAndNext(
        Session session, FlagReport flagReport, long classNameId, long classPK,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FLAGREPORT_WHERE);

        query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSNAMEID_2);

        query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSPK_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }
        else {
            query.append(FlagReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(classNameId);

        qPos.add(classPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(flagReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FlagReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the flag reports.
     *
     * @return the flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the flag reports.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @return the range of flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the flag reports.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of flag reports
     * @param end the upper bound of the range of flag reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of flag reports
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReport> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<FlagReport> list = (List<FlagReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FLAGREPORT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FLAGREPORT.concat(FlagReportModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<FlagReport>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FlagReport>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the flag reports where uuid = &#63; from the database.
     *
     * @param uuid the uuid
     * @throws SystemException if a system exception occurred
     */
    public void removeByUuid(String uuid) throws SystemException {
        for (FlagReport flagReport : findByUuid(uuid)) {
            remove(flagReport);
        }
    }

    /**
     * Removes the flag report where uuid = &#63; and groupId = &#63; from the database.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUUID_G(String uuid, long groupId)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = findByUUID_G(uuid, groupId);

        remove(flagReport);
    }

    /**
     * Removes the flag report where flagReportId = &#63; from the database.
     *
     * @param flagReportId the flag report ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByflagReportId(long flagReportId)
        throws NoSuchFlagReportException, SystemException {
        FlagReport flagReport = findByflagReportId(flagReportId);

        remove(flagReport);
    }

    /**
     * Removes all the flag reports where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupId(long groupId) throws SystemException {
        for (FlagReport flagReport : findByGroupId(groupId)) {
            remove(flagReport);
        }
    }

    /**
     * Removes all the flag reports where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCompanyId(long companyId) throws SystemException {
        for (FlagReport flagReport : findByCompanyId(companyId)) {
            remove(flagReport);
        }
    }

    /**
     * Removes all the flag reports where classNameId = &#63; and classPK = &#63; from the database.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @throws SystemException if a system exception occurred
     */
    public void removeByClassNameIdWithClassPK(long classNameId, long classPK)
        throws SystemException {
        for (FlagReport flagReport : findByClassNameIdWithClassPK(classNameId,
                classPK)) {
            remove(flagReport);
        }
    }

    /**
     * Removes all the flag reports from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (FlagReport flagReport : findAll()) {
            remove(flagReport);
        }
    }

    /**
     * Returns the number of flag reports where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the number of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countByUuid(String uuid) throws SystemException {
        Object[] finderArgs = new Object[] { uuid };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FLAGREPORT_WHERE);

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else {
                if (uuid.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_UUID_UUID_3);
                } else {
                    query.append(_FINDER_COLUMN_UUID_UUID_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (uuid != null) {
                    qPos.add(uuid);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag reports where uuid = &#63; and groupId = &#63;.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the number of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countByUUID_G(String uuid, long groupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { uuid, groupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_FLAGREPORT_WHERE);

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_1);
            } else {
                if (uuid.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_UUID_G_UUID_3);
                } else {
                    query.append(_FINDER_COLUMN_UUID_G_UUID_2);
                }
            }

            query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (uuid != null) {
                    qPos.add(uuid);
                }

                qPos.add(groupId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag reports where flagReportId = &#63;.
     *
     * @param flagReportId the flag report ID
     * @return the number of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countByflagReportId(long flagReportId) throws SystemException {
        Object[] finderArgs = new Object[] { flagReportId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FLAGREPORTID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_FLAGREPORTID_FLAGREPORTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(flagReportId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FLAGREPORTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag reports where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupId(long groupId) throws SystemException {
        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag reports where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countByCompanyId(long companyId) throws SystemException {
        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag reports where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the number of matching flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countByClassNameIdWithClassPK(long classNameId, long classPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_FLAGREPORT_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSNAMEID_2);

            query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

                qPos.add(classPK);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag reports.
     *
     * @return the number of flag reports
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FLAGREPORT);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the flag report persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.FlagReport")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FlagReport>> listenersList = new ArrayList<ModelListener<FlagReport>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FlagReport>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FlagReportImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
