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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException;
import org.nterlearning.datamodel.catalog.model.FlagReportStats;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportStatsImpl;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportStatsModelImpl;
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
 * The persistence implementation for the flag report stats service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagReportStatsPersistence
 * @see FlagReportStatsUtil
 * @generated
 */
public class FlagReportStatsPersistenceImpl extends BasePersistenceImpl<FlagReportStats>
    implements FlagReportStatsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FlagReportStatsUtil} to access the flag report stats persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FlagReportStatsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED,
            FlagReportStatsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByflagReportStatsId", new String[] { Long.class.getName() },
            FlagReportStatsModelImpl.FLAGREPORTSTATSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FLAGREPORTSTATSID = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByflagReportStatsId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED,
            FlagReportStatsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByClassNameIdWithClassPK",
            new String[] { Long.class.getName(), Long.class.getName() },
            FlagReportStatsModelImpl.CLASSNAMEID_COLUMN_BITMASK |
            FlagReportStatsModelImpl.CLASSPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByClassNameIdWithClassPK",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED,
            FlagReportStatsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED,
            FlagReportStatsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FLAGREPORTSTATS = "SELECT flagReportStats FROM FlagReportStats flagReportStats";
    private static final String _SQL_SELECT_FLAGREPORTSTATS_WHERE = "SELECT flagReportStats FROM FlagReportStats flagReportStats WHERE ";
    private static final String _SQL_COUNT_FLAGREPORTSTATS = "SELECT COUNT(flagReportStats) FROM FlagReportStats flagReportStats";
    private static final String _SQL_COUNT_FLAGREPORTSTATS_WHERE = "SELECT COUNT(flagReportStats) FROM FlagReportStats flagReportStats WHERE ";
    private static final String _FINDER_COLUMN_FLAGREPORTSTATSID_FLAGREPORTSTATSID_2 =
        "flagReportStats.flagReportStatsId = ?";
    private static final String _FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSNAMEID_2 =
        "flagReportStats.classNameId = ? AND ";
    private static final String _FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSPK_2 = "flagReportStats.classPK = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "flagReportStats.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FlagReportStats exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FlagReportStats exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FlagReportStatsPersistenceImpl.class);
    private static FlagReportStats _nullFlagReportStats = new FlagReportStatsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FlagReportStats> toCacheModel() {
                return _nullFlagReportStatsCacheModel;
            }
        };

    private static CacheModel<FlagReportStats> _nullFlagReportStatsCacheModel = new CacheModel<FlagReportStats>() {
            public FlagReportStats toEntityModel() {
                return _nullFlagReportStats;
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
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the flag report stats in the entity cache if it is enabled.
     *
     * @param flagReportStats the flag report stats
     */
    public void cacheResult(FlagReportStats flagReportStats) {
        EntityCacheUtil.putResult(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsImpl.class, flagReportStats.getPrimaryKey(),
            flagReportStats);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
            new Object[] { Long.valueOf(flagReportStats.getFlagReportStatsId()) },
            flagReportStats);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
            new Object[] {
                Long.valueOf(flagReportStats.getClassNameId()),
                Long.valueOf(flagReportStats.getClassPK())
            }, flagReportStats);

        flagReportStats.resetOriginalValues();
    }

    /**
     * Caches the flag report statses in the entity cache if it is enabled.
     *
     * @param flagReportStatses the flag report statses
     */
    public void cacheResult(List<FlagReportStats> flagReportStatses) {
        for (FlagReportStats flagReportStats : flagReportStatses) {
            if (EntityCacheUtil.getResult(
                        FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
                        FlagReportStatsImpl.class,
                        flagReportStats.getPrimaryKey()) == null) {
                cacheResult(flagReportStats);
            } else {
                flagReportStats.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all flag report statses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FlagReportStatsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FlagReportStatsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the flag report stats.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FlagReportStats flagReportStats) {
        EntityCacheUtil.removeResult(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsImpl.class, flagReportStats.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(flagReportStats);
    }

    @Override
    public void clearCache(List<FlagReportStats> flagReportStatses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FlagReportStats flagReportStats : flagReportStatses) {
            EntityCacheUtil.removeResult(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
                FlagReportStatsImpl.class, flagReportStats.getPrimaryKey());

            clearUniqueFindersCache(flagReportStats);
        }
    }

    protected void clearUniqueFindersCache(FlagReportStats flagReportStats) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
            new Object[] { Long.valueOf(flagReportStats.getFlagReportStatsId()) });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
            new Object[] {
                Long.valueOf(flagReportStats.getClassNameId()),
                Long.valueOf(flagReportStats.getClassPK())
            });
    }

    /**
     * Creates a new flag report stats with the primary key. Does not add the flag report stats to the database.
     *
     * @param flagReportStatsId the primary key for the new flag report stats
     * @return the new flag report stats
     */
    public FlagReportStats create(long flagReportStatsId) {
        FlagReportStats flagReportStats = new FlagReportStatsImpl();

        flagReportStats.setNew(true);
        flagReportStats.setPrimaryKey(flagReportStatsId);

        return flagReportStats;
    }

    /**
     * Removes the flag report stats with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param flagReportStatsId the primary key of the flag report stats
     * @return the flag report stats that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats remove(long flagReportStatsId)
        throws NoSuchFlagReportStatsException, SystemException {
        return remove(Long.valueOf(flagReportStatsId));
    }

    /**
     * Removes the flag report stats with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the flag report stats
     * @return the flag report stats that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FlagReportStats remove(Serializable primaryKey)
        throws NoSuchFlagReportStatsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FlagReportStats flagReportStats = (FlagReportStats) session.get(FlagReportStatsImpl.class,
                    primaryKey);

            if (flagReportStats == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFlagReportStatsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(flagReportStats);
        } catch (NoSuchFlagReportStatsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FlagReportStats removeImpl(FlagReportStats flagReportStats)
        throws SystemException {
        flagReportStats = toUnwrappedModel(flagReportStats);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, flagReportStats);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(flagReportStats);

        return flagReportStats;
    }

    @Override
    public FlagReportStats updateImpl(
        org.nterlearning.datamodel.catalog.model.FlagReportStats flagReportStats,
        boolean merge) throws SystemException {
        flagReportStats = toUnwrappedModel(flagReportStats);

        boolean isNew = flagReportStats.isNew();

        FlagReportStatsModelImpl flagReportStatsModelImpl = (FlagReportStatsModelImpl) flagReportStats;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, flagReportStats, merge);

            flagReportStats.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !FlagReportStatsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
            FlagReportStatsImpl.class, flagReportStats.getPrimaryKey(),
            flagReportStats);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                new Object[] {
                    Long.valueOf(flagReportStats.getFlagReportStatsId())
                }, flagReportStats);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                new Object[] {
                    Long.valueOf(flagReportStats.getClassNameId()),
                    Long.valueOf(flagReportStats.getClassPK())
                }, flagReportStats);
        } else {
            if ((flagReportStatsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(flagReportStatsModelImpl.getOriginalFlagReportStatsId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FLAGREPORTSTATSID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                    new Object[] {
                        Long.valueOf(flagReportStats.getFlagReportStatsId())
                    }, flagReportStats);
            }

            if ((flagReportStatsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(flagReportStatsModelImpl.getOriginalClassNameId()),
                        Long.valueOf(flagReportStatsModelImpl.getOriginalClassPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                    new Object[] {
                        Long.valueOf(flagReportStats.getClassNameId()),
                        Long.valueOf(flagReportStats.getClassPK())
                    }, flagReportStats);
            }
        }

        return flagReportStats;
    }

    protected FlagReportStats toUnwrappedModel(FlagReportStats flagReportStats) {
        if (flagReportStats instanceof FlagReportStatsImpl) {
            return flagReportStats;
        }

        FlagReportStatsImpl flagReportStatsImpl = new FlagReportStatsImpl();

        flagReportStatsImpl.setNew(flagReportStats.isNew());
        flagReportStatsImpl.setPrimaryKey(flagReportStats.getPrimaryKey());

        flagReportStatsImpl.setFlagReportStatsId(flagReportStats.getFlagReportStatsId());
        flagReportStatsImpl.setClassNameId(flagReportStats.getClassNameId());
        flagReportStatsImpl.setClassPK(flagReportStats.getClassPK());
        flagReportStatsImpl.setTotalEntries(flagReportStats.getTotalEntries());
        flagReportStatsImpl.setTotalModerated(flagReportStats.getTotalModerated());
        flagReportStatsImpl.setTotalApproved(flagReportStats.getTotalApproved());

        return flagReportStatsImpl;
    }

    /**
     * Returns the flag report stats with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the flag report stats
     * @return the flag report stats
     * @throws com.liferay.portal.NoSuchModelException if a flag report stats with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FlagReportStats findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the flag report stats with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
     *
     * @param flagReportStatsId the primary key of the flag report stats
     * @return the flag report stats
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a flag report stats with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats findByPrimaryKey(long flagReportStatsId)
        throws NoSuchFlagReportStatsException, SystemException {
        FlagReportStats flagReportStats = fetchByPrimaryKey(flagReportStatsId);

        if (flagReportStats == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + flagReportStatsId);
            }

            throw new NoSuchFlagReportStatsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                flagReportStatsId);
        }

        return flagReportStats;
    }

    /**
     * Returns the flag report stats with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the flag report stats
     * @return the flag report stats, or <code>null</code> if a flag report stats with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FlagReportStats fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the flag report stats with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param flagReportStatsId the primary key of the flag report stats
     * @return the flag report stats, or <code>null</code> if a flag report stats with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats fetchByPrimaryKey(long flagReportStatsId)
        throws SystemException {
        FlagReportStats flagReportStats = (FlagReportStats) EntityCacheUtil.getResult(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
                FlagReportStatsImpl.class, flagReportStatsId);

        if (flagReportStats == _nullFlagReportStats) {
            return null;
        }

        if (flagReportStats == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                flagReportStats = (FlagReportStats) session.get(FlagReportStatsImpl.class,
                        Long.valueOf(flagReportStatsId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (flagReportStats != null) {
                    cacheResult(flagReportStats);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(FlagReportStatsModelImpl.ENTITY_CACHE_ENABLED,
                        FlagReportStatsImpl.class, flagReportStatsId,
                        _nullFlagReportStats);
                }

                closeSession(session);
            }
        }

        return flagReportStats;
    }

    /**
     * Returns the flag report stats where flagReportStatsId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
     *
     * @param flagReportStatsId the flag report stats ID
     * @return the matching flag report stats
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a matching flag report stats could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats findByflagReportStatsId(long flagReportStatsId)
        throws NoSuchFlagReportStatsException, SystemException {
        FlagReportStats flagReportStats = fetchByflagReportStatsId(flagReportStatsId);

        if (flagReportStats == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("flagReportStatsId=");
            msg.append(flagReportStatsId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFlagReportStatsException(msg.toString());
        }

        return flagReportStats;
    }

    /**
     * Returns the flag report stats where flagReportStatsId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param flagReportStatsId the flag report stats ID
     * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats fetchByflagReportStatsId(long flagReportStatsId)
        throws SystemException {
        return fetchByflagReportStatsId(flagReportStatsId, true);
    }

    /**
     * Returns the flag report stats where flagReportStatsId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param flagReportStatsId the flag report stats ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats fetchByflagReportStatsId(long flagReportStatsId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { flagReportStatsId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_FLAGREPORTSTATS_WHERE);

            query.append(_FINDER_COLUMN_FLAGREPORTSTATSID_FLAGREPORTSTATSID_2);

            query.append(FlagReportStatsModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(flagReportStatsId);

                List<FlagReportStats> list = q.list();

                result = list;

                FlagReportStats flagReportStats = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                        finderArgs, list);
                } else {
                    flagReportStats = list.get(0);

                    cacheResult(flagReportStats);

                    if ((flagReportStats.getFlagReportStatsId() != flagReportStatsId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                            finderArgs, flagReportStats);
                    }
                }

                return flagReportStats;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FLAGREPORTSTATSID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (FlagReportStats) result;
            }
        }
    }

    /**
     * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException} if it could not be found.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the matching flag report stats
     * @throws org.nterlearning.datamodel.catalog.NoSuchFlagReportStatsException if a matching flag report stats could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats findByClassNameIdWithClassPK(long classNameId,
        long classPK) throws NoSuchFlagReportStatsException, SystemException {
        FlagReportStats flagReportStats = fetchByClassNameIdWithClassPK(classNameId,
                classPK);

        if (flagReportStats == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("classNameId=");
            msg.append(classNameId);

            msg.append(", classPK=");
            msg.append(classPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFlagReportStatsException(msg.toString());
        }

        return flagReportStats;
    }

    /**
     * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats fetchByClassNameIdWithClassPK(long classNameId,
        long classPK) throws SystemException {
        return fetchByClassNameIdWithClassPK(classNameId, classPK, true);
    }

    /**
     * Returns the flag report stats where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching flag report stats, or <code>null</code> if a matching flag report stats could not be found
     * @throws SystemException if a system exception occurred
     */
    public FlagReportStats fetchByClassNameIdWithClassPK(long classNameId,
        long classPK, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_FLAGREPORTSTATS_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSNAMEID_2);

            query.append(_FINDER_COLUMN_CLASSNAMEIDWITHCLASSPK_CLASSPK_2);

            query.append(FlagReportStatsModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

                qPos.add(classPK);

                List<FlagReportStats> list = q.list();

                result = list;

                FlagReportStats flagReportStats = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                        finderArgs, list);
                } else {
                    flagReportStats = list.get(0);

                    cacheResult(flagReportStats);

                    if ((flagReportStats.getClassNameId() != classNameId) ||
                            (flagReportStats.getClassPK() != classPK)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                            finderArgs, flagReportStats);
                    }
                }

                return flagReportStats;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDWITHCLASSPK,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (FlagReportStats) result;
            }
        }
    }

    /**
     * Returns all the flag report statses.
     *
     * @return the flag report statses
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReportStats> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the flag report statses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of flag report statses
     * @param end the upper bound of the range of flag report statses (not inclusive)
     * @return the range of flag report statses
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReportStats> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the flag report statses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of flag report statses
     * @param end the upper bound of the range of flag report statses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of flag report statses
     * @throws SystemException if a system exception occurred
     */
    public List<FlagReportStats> findAll(int start, int end,
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

        List<FlagReportStats> list = (List<FlagReportStats>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FLAGREPORTSTATS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FLAGREPORTSTATS.concat(FlagReportStatsModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<FlagReportStats>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FlagReportStats>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Removes the flag report stats where flagReportStatsId = &#63; from the database.
     *
     * @param flagReportStatsId the flag report stats ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByflagReportStatsId(long flagReportStatsId)
        throws NoSuchFlagReportStatsException, SystemException {
        FlagReportStats flagReportStats = findByflagReportStatsId(flagReportStatsId);

        remove(flagReportStats);
    }

    /**
     * Removes the flag report stats where classNameId = &#63; and classPK = &#63; from the database.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @throws SystemException if a system exception occurred
     */
    public void removeByClassNameIdWithClassPK(long classNameId, long classPK)
        throws NoSuchFlagReportStatsException, SystemException {
        FlagReportStats flagReportStats = findByClassNameIdWithClassPK(classNameId,
                classPK);

        remove(flagReportStats);
    }

    /**
     * Removes all the flag report statses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (FlagReportStats flagReportStats : findAll()) {
            remove(flagReportStats);
        }
    }

    /**
     * Returns the number of flag report statses where flagReportStatsId = &#63;.
     *
     * @param flagReportStatsId the flag report stats ID
     * @return the number of matching flag report statses
     * @throws SystemException if a system exception occurred
     */
    public int countByflagReportStatsId(long flagReportStatsId)
        throws SystemException {
        Object[] finderArgs = new Object[] { flagReportStatsId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FLAGREPORTSTATSID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FLAGREPORTSTATS_WHERE);

            query.append(_FINDER_COLUMN_FLAGREPORTSTATSID_FLAGREPORTSTATSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(flagReportStatsId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FLAGREPORTSTATSID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of flag report statses where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the number of matching flag report statses
     * @throws SystemException if a system exception occurred
     */
    public int countByClassNameIdWithClassPK(long classNameId, long classPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { classNameId, classPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDWITHCLASSPK,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_FLAGREPORTSTATS_WHERE);

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
     * Returns the number of flag report statses.
     *
     * @return the number of flag report statses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FLAGREPORTSTATS);

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
     * Initializes the flag report stats persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.FlagReportStats")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FlagReportStats>> listenersList = new ArrayList<ModelListener<FlagReportStats>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FlagReportStats>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FlagReportStatsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
