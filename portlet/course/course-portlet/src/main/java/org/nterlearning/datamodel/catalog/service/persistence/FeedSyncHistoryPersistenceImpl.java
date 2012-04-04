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

import org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;
import org.nterlearning.datamodel.catalog.model.FeedSyncHistory;
import org.nterlearning.datamodel.catalog.model.impl.FeedSyncHistoryImpl;
import org.nterlearning.datamodel.catalog.model.impl.FeedSyncHistoryModelImpl;
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
 * The persistence implementation for the feed sync history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistoryPersistence
 * @see FeedSyncHistoryUtil
 * @generated
 */
public class FeedSyncHistoryPersistenceImpl extends BasePersistenceImpl<FeedSyncHistory>
    implements FeedSyncHistoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FeedSyncHistoryUtil} to access the feed sync history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FeedSyncHistoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryModelImpl.FINDER_CACHE_ENABLED,
            FeedSyncHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByfeedReferenceId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryModelImpl.FINDER_CACHE_ENABLED,
            FeedSyncHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfeedReferenceId",
            new String[] { Long.class.getName() },
            FeedSyncHistoryModelImpl.FEEDREFERENCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDREFERENCEID = new FinderPath(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfeedReferenceId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryModelImpl.FINDER_CACHE_ENABLED,
            FeedSyncHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryModelImpl.FINDER_CACHE_ENABLED,
            FeedSyncHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FEEDSYNCHISTORY = "SELECT feedSyncHistory FROM FeedSyncHistory feedSyncHistory";
    private static final String _SQL_SELECT_FEEDSYNCHISTORY_WHERE = "SELECT feedSyncHistory FROM FeedSyncHistory feedSyncHistory WHERE ";
    private static final String _SQL_COUNT_FEEDSYNCHISTORY = "SELECT COUNT(feedSyncHistory) FROM FeedSyncHistory feedSyncHistory";
    private static final String _SQL_COUNT_FEEDSYNCHISTORY_WHERE = "SELECT COUNT(feedSyncHistory) FROM FeedSyncHistory feedSyncHistory WHERE ";
    private static final String _FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2 =
        "feedSyncHistory.feedReferenceId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "feedSyncHistory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FeedSyncHistory exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FeedSyncHistory exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FeedSyncHistoryPersistenceImpl.class);
    private static FeedSyncHistory _nullFeedSyncHistory = new FeedSyncHistoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FeedSyncHistory> toCacheModel() {
                return _nullFeedSyncHistoryCacheModel;
            }
        };

    private static CacheModel<FeedSyncHistory> _nullFeedSyncHistoryCacheModel = new CacheModel<FeedSyncHistory>() {
            public FeedSyncHistory toEntityModel() {
                return _nullFeedSyncHistory;
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
     * Caches the feed sync history in the entity cache if it is enabled.
     *
     * @param feedSyncHistory the feed sync history
     */
    public void cacheResult(FeedSyncHistory feedSyncHistory) {
        EntityCacheUtil.putResult(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryImpl.class, feedSyncHistory.getPrimaryKey(),
            feedSyncHistory);

        feedSyncHistory.resetOriginalValues();
    }

    /**
     * Caches the feed sync histories in the entity cache if it is enabled.
     *
     * @param feedSyncHistories the feed sync histories
     */
    public void cacheResult(List<FeedSyncHistory> feedSyncHistories) {
        for (FeedSyncHistory feedSyncHistory : feedSyncHistories) {
            if (EntityCacheUtil.getResult(
                        FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        FeedSyncHistoryImpl.class,
                        feedSyncHistory.getPrimaryKey()) == null) {
                cacheResult(feedSyncHistory);
            } else {
                feedSyncHistory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all feed sync histories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FeedSyncHistoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FeedSyncHistoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the feed sync history.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FeedSyncHistory feedSyncHistory) {
        EntityCacheUtil.removeResult(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryImpl.class, feedSyncHistory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<FeedSyncHistory> feedSyncHistories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FeedSyncHistory feedSyncHistory : feedSyncHistories) {
            EntityCacheUtil.removeResult(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
                FeedSyncHistoryImpl.class, feedSyncHistory.getPrimaryKey());
        }
    }

    /**
     * Creates a new feed sync history with the primary key. Does not add the feed sync history to the database.
     *
     * @param syncId the primary key for the new feed sync history
     * @return the new feed sync history
     */
    public FeedSyncHistory create(long syncId) {
        FeedSyncHistory feedSyncHistory = new FeedSyncHistoryImpl();

        feedSyncHistory.setNew(true);
        feedSyncHistory.setPrimaryKey(syncId);

        return feedSyncHistory;
    }

    /**
     * Removes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param syncId the primary key of the feed sync history
     * @return the feed sync history that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedSyncHistory remove(long syncId)
        throws NoSuchFeedSyncHistoryException, SystemException {
        return remove(Long.valueOf(syncId));
    }

    /**
     * Removes the feed sync history with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the feed sync history
     * @return the feed sync history that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FeedSyncHistory remove(Serializable primaryKey)
        throws NoSuchFeedSyncHistoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FeedSyncHistory feedSyncHistory = (FeedSyncHistory) session.get(FeedSyncHistoryImpl.class,
                    primaryKey);

            if (feedSyncHistory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFeedSyncHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(feedSyncHistory);
        } catch (NoSuchFeedSyncHistoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FeedSyncHistory removeImpl(FeedSyncHistory feedSyncHistory)
        throws SystemException {
        feedSyncHistory = toUnwrappedModel(feedSyncHistory);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, feedSyncHistory);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(feedSyncHistory);

        return feedSyncHistory;
    }

    @Override
    public FeedSyncHistory updateImpl(
        org.nterlearning.datamodel.catalog.model.FeedSyncHistory feedSyncHistory,
        boolean merge) throws SystemException {
        feedSyncHistory = toUnwrappedModel(feedSyncHistory);

        boolean isNew = feedSyncHistory.isNew();

        FeedSyncHistoryModelImpl feedSyncHistoryModelImpl = (FeedSyncHistoryModelImpl) feedSyncHistory;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, feedSyncHistory, merge);

            feedSyncHistory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !FeedSyncHistoryModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((feedSyncHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(feedSyncHistoryModelImpl.getOriginalFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);

                args = new Object[] {
                        Long.valueOf(feedSyncHistoryModelImpl.getFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
            FeedSyncHistoryImpl.class, feedSyncHistory.getPrimaryKey(),
            feedSyncHistory);

        return feedSyncHistory;
    }

    protected FeedSyncHistory toUnwrappedModel(FeedSyncHistory feedSyncHistory) {
        if (feedSyncHistory instanceof FeedSyncHistoryImpl) {
            return feedSyncHistory;
        }

        FeedSyncHistoryImpl feedSyncHistoryImpl = new FeedSyncHistoryImpl();

        feedSyncHistoryImpl.setNew(feedSyncHistory.isNew());
        feedSyncHistoryImpl.setPrimaryKey(feedSyncHistory.getPrimaryKey());

        feedSyncHistoryImpl.setSyncId(feedSyncHistory.getSyncId());
        feedSyncHistoryImpl.setFeedReferenceId(feedSyncHistory.getFeedReferenceId());
        feedSyncHistoryImpl.setSyncDate(feedSyncHistory.getSyncDate());
        feedSyncHistoryImpl.setSuccess(feedSyncHistory.getSuccess());
        feedSyncHistoryImpl.setSyncMessage(feedSyncHistory.getSyncMessage());
        feedSyncHistoryImpl.setNumberOfEntries(feedSyncHistory.getNumberOfEntries());

        return feedSyncHistoryImpl;
    }

    /**
     * Returns the feed sync history with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the feed sync history
     * @return the feed sync history
     * @throws com.liferay.portal.NoSuchModelException if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FeedSyncHistory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the feed sync history with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException} if it could not be found.
     *
     * @param syncId the primary key of the feed sync history
     * @return the feed sync history
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedSyncHistory findByPrimaryKey(long syncId)
        throws NoSuchFeedSyncHistoryException, SystemException {
        FeedSyncHistory feedSyncHistory = fetchByPrimaryKey(syncId);

        if (feedSyncHistory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + syncId);
            }

            throw new NoSuchFeedSyncHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                syncId);
        }

        return feedSyncHistory;
    }

    /**
     * Returns the feed sync history with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the feed sync history
     * @return the feed sync history, or <code>null</code> if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FeedSyncHistory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the feed sync history with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param syncId the primary key of the feed sync history
     * @return the feed sync history, or <code>null</code> if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedSyncHistory fetchByPrimaryKey(long syncId)
        throws SystemException {
        FeedSyncHistory feedSyncHistory = (FeedSyncHistory) EntityCacheUtil.getResult(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
                FeedSyncHistoryImpl.class, syncId);

        if (feedSyncHistory == _nullFeedSyncHistory) {
            return null;
        }

        if (feedSyncHistory == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                feedSyncHistory = (FeedSyncHistory) session.get(FeedSyncHistoryImpl.class,
                        Long.valueOf(syncId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (feedSyncHistory != null) {
                    cacheResult(feedSyncHistory);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(FeedSyncHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        FeedSyncHistoryImpl.class, syncId, _nullFeedSyncHistory);
                }

                closeSession(session);
            }
        }

        return feedSyncHistory;
    }

    /**
     * Returns all the feed sync histories where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the matching feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public List<FeedSyncHistory> findByfeedReferenceId(long feedReferenceId)
        throws SystemException {
        return findByfeedReferenceId(feedReferenceId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed sync histories where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of feed sync histories
     * @param end the upper bound of the range of feed sync histories (not inclusive)
     * @return the range of matching feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public List<FeedSyncHistory> findByfeedReferenceId(long feedReferenceId,
        int start, int end) throws SystemException {
        return findByfeedReferenceId(feedReferenceId, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed sync histories where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of feed sync histories
     * @param end the upper bound of the range of feed sync histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public List<FeedSyncHistory> findByfeedReferenceId(long feedReferenceId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID;
            finderArgs = new Object[] { feedReferenceId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDREFERENCEID;
            finderArgs = new Object[] {
                    feedReferenceId,
                    
                    start, end, orderByComparator
                };
        }

        List<FeedSyncHistory> list = (List<FeedSyncHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FEEDSYNCHISTORY_WHERE);

            query.append(_FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(FeedSyncHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(feedReferenceId);

                list = (List<FeedSyncHistory>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed sync history in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed sync history
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a matching feed sync history could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedSyncHistory findByfeedReferenceId_First(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedSyncHistoryException, SystemException {
        List<FeedSyncHistory> list = findByfeedReferenceId(feedReferenceId, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedSyncHistoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed sync history in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed sync history
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a matching feed sync history could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedSyncHistory findByfeedReferenceId_Last(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedSyncHistoryException, SystemException {
        int count = countByfeedReferenceId(feedReferenceId);

        List<FeedSyncHistory> list = findByfeedReferenceId(feedReferenceId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedSyncHistoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed sync histories before and after the current feed sync history in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param syncId the primary key of the current feed sync history
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed sync history
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException if a feed sync history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedSyncHistory[] findByfeedReferenceId_PrevAndNext(long syncId,
        long feedReferenceId, OrderByComparator orderByComparator)
        throws NoSuchFeedSyncHistoryException, SystemException {
        FeedSyncHistory feedSyncHistory = findByPrimaryKey(syncId);

        Session session = null;

        try {
            session = openSession();

            FeedSyncHistory[] array = new FeedSyncHistoryImpl[3];

            array[0] = getByfeedReferenceId_PrevAndNext(session,
                    feedSyncHistory, feedReferenceId, orderByComparator, true);

            array[1] = feedSyncHistory;

            array[2] = getByfeedReferenceId_PrevAndNext(session,
                    feedSyncHistory, feedReferenceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedSyncHistory getByfeedReferenceId_PrevAndNext(
        Session session, FeedSyncHistory feedSyncHistory, long feedReferenceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDSYNCHISTORY_WHERE);

        query.append(_FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2);

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
            query.append(FeedSyncHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(feedReferenceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedSyncHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedSyncHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed sync histories.
     *
     * @return the feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public List<FeedSyncHistory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed sync histories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of feed sync histories
     * @param end the upper bound of the range of feed sync histories (not inclusive)
     * @return the range of feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public List<FeedSyncHistory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the feed sync histories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of feed sync histories
     * @param end the upper bound of the range of feed sync histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public List<FeedSyncHistory> findAll(int start, int end,
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

        List<FeedSyncHistory> list = (List<FeedSyncHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FEEDSYNCHISTORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FEEDSYNCHISTORY.concat(FeedSyncHistoryModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<FeedSyncHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FeedSyncHistory>) QueryUtil.list(q,
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
     * Removes all the feed sync histories where feedReferenceId = &#63; from the database.
     *
     * @param feedReferenceId the feed reference ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByfeedReferenceId(long feedReferenceId)
        throws SystemException {
        for (FeedSyncHistory feedSyncHistory : findByfeedReferenceId(
                feedReferenceId)) {
            remove(feedSyncHistory);
        }
    }

    /**
     * Removes all the feed sync histories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (FeedSyncHistory feedSyncHistory : findAll()) {
            remove(feedSyncHistory);
        }
    }

    /**
     * Returns the number of feed sync histories where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the number of matching feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public int countByfeedReferenceId(long feedReferenceId)
        throws SystemException {
        Object[] finderArgs = new Object[] { feedReferenceId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDSYNCHISTORY_WHERE);

            query.append(_FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(feedReferenceId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed sync histories.
     *
     * @return the number of feed sync histories
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FEEDSYNCHISTORY);

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
     * Initializes the feed sync history persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.FeedSyncHistory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FeedSyncHistory>> listenersList = new ArrayList<ModelListener<FeedSyncHistory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FeedSyncHistory>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FeedSyncHistoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
