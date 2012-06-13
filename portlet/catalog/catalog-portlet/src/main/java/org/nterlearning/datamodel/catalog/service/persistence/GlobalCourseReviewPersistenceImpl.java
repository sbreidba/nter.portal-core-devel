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

import org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl;
import org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl;
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
 * The persistence implementation for the global course review service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReviewPersistence
 * @see GlobalCourseReviewUtil
 * @generated
 */
public class GlobalCourseReviewPersistenceImpl extends BasePersistenceImpl<GlobalCourseReview>
    implements GlobalCourseReviewPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GlobalCourseReviewUtil} to access the global course review persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GlobalCourseReviewImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_COURSEREVIEWIRI = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByCourseReviewIri", new String[] { String.class.getName() },
            GlobalCourseReviewModelImpl.COURSEREVIEWIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEREVIEWIRI = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseReviewIri", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID =
        new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
            new String[] { Long.class.getName() },
            GlobalCourseReviewModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIRI =
        new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI =
        new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseIri",
            new String[] { String.class.getName() },
            GlobalCourseReviewModelImpl.COURSEIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIRI = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
            new String[] { Long.class.getName() },
            GlobalCourseReviewModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
            new String[] { Long.class.getName() },
            GlobalCourseReviewModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            GlobalCourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_GLOBALCOURSEREVIEW = "SELECT globalCourseReview FROM GlobalCourseReview globalCourseReview";
    private static final String _SQL_SELECT_GLOBALCOURSEREVIEW_WHERE = "SELECT globalCourseReview FROM GlobalCourseReview globalCourseReview WHERE ";
    private static final String _SQL_COUNT_GLOBALCOURSEREVIEW = "SELECT COUNT(globalCourseReview) FROM GlobalCourseReview globalCourseReview";
    private static final String _SQL_COUNT_GLOBALCOURSEREVIEW_WHERE = "SELECT COUNT(globalCourseReview) FROM GlobalCourseReview globalCourseReview WHERE ";
    private static final String _FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_1 =
        "globalCourseReview.courseReviewIri IS NULL";
    private static final String _FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_2 =
        "globalCourseReview.courseReviewIri = ?";
    private static final String _FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_3 =
        "(globalCourseReview.courseReviewIri IS NULL OR globalCourseReview.courseReviewIri = ?)";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "globalCourseReview.courseId = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_1 = "globalCourseReview.courseIri IS NULL";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_2 = "globalCourseReview.courseIri = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_3 = "(globalCourseReview.courseIri IS NULL OR globalCourseReview.courseIri = ?)";
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "globalCourseReview.groupId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "globalCourseReview.companyId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "globalCourseReview.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GlobalCourseReview exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GlobalCourseReview exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GlobalCourseReviewPersistenceImpl.class);
    private static GlobalCourseReview _nullGlobalCourseReview = new GlobalCourseReviewImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GlobalCourseReview> toCacheModel() {
                return _nullGlobalCourseReviewCacheModel;
            }
        };

    private static CacheModel<GlobalCourseReview> _nullGlobalCourseReviewCacheModel =
        new CacheModel<GlobalCourseReview>() {
            public GlobalCourseReview toEntityModel() {
                return _nullGlobalCourseReview;
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
     * Caches the global course review in the entity cache if it is enabled.
     *
     * @param globalCourseReview the global course review
     */
    public void cacheResult(GlobalCourseReview globalCourseReview) {
        EntityCacheUtil.putResult(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewImpl.class, globalCourseReview.getPrimaryKey(),
            globalCourseReview);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
            new Object[] { globalCourseReview.getCourseReviewIri() },
            globalCourseReview);

        globalCourseReview.resetOriginalValues();
    }

    /**
     * Caches the global course reviews in the entity cache if it is enabled.
     *
     * @param globalCourseReviews the global course reviews
     */
    public void cacheResult(List<GlobalCourseReview> globalCourseReviews) {
        for (GlobalCourseReview globalCourseReview : globalCourseReviews) {
            if (EntityCacheUtil.getResult(
                        GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                        GlobalCourseReviewImpl.class,
                        globalCourseReview.getPrimaryKey()) == null) {
                cacheResult(globalCourseReview);
            } else {
                globalCourseReview.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all global course reviews.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GlobalCourseReviewImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GlobalCourseReviewImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the global course review.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GlobalCourseReview globalCourseReview) {
        EntityCacheUtil.removeResult(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewImpl.class, globalCourseReview.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(globalCourseReview);
    }

    @Override
    public void clearCache(List<GlobalCourseReview> globalCourseReviews) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GlobalCourseReview globalCourseReview : globalCourseReviews) {
            EntityCacheUtil.removeResult(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                GlobalCourseReviewImpl.class, globalCourseReview.getPrimaryKey());

            clearUniqueFindersCache(globalCourseReview);
        }
    }

    protected void clearUniqueFindersCache(
        GlobalCourseReview globalCourseReview) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
            new Object[] { globalCourseReview.getCourseReviewIri() });
    }

    /**
     * Creates a new global course review with the primary key. Does not add the global course review to the database.
     *
     * @param globalCourseReviewId the primary key for the new global course review
     * @return the new global course review
     */
    public GlobalCourseReview create(long globalCourseReviewId) {
        GlobalCourseReview globalCourseReview = new GlobalCourseReviewImpl();

        globalCourseReview.setNew(true);
        globalCourseReview.setPrimaryKey(globalCourseReviewId);

        return globalCourseReview;
    }

    /**
     * Removes the global course review with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param globalCourseReviewId the primary key of the global course review
     * @return the global course review that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview remove(long globalCourseReviewId)
        throws NoSuchGlobalCourseReviewException, SystemException {
        return remove(Long.valueOf(globalCourseReviewId));
    }

    /**
     * Removes the global course review with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the global course review
     * @return the global course review that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlobalCourseReview remove(Serializable primaryKey)
        throws NoSuchGlobalCourseReviewException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GlobalCourseReview globalCourseReview = (GlobalCourseReview) session.get(GlobalCourseReviewImpl.class,
                    primaryKey);

            if (globalCourseReview == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGlobalCourseReviewException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(globalCourseReview);
        } catch (NoSuchGlobalCourseReviewException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GlobalCourseReview removeImpl(
        GlobalCourseReview globalCourseReview) throws SystemException {
        globalCourseReview = toUnwrappedModel(globalCourseReview);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, globalCourseReview);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(globalCourseReview);

        return globalCourseReview;
    }

    @Override
    public GlobalCourseReview updateImpl(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview,
        boolean merge) throws SystemException {
        globalCourseReview = toUnwrappedModel(globalCourseReview);

        boolean isNew = globalCourseReview.isNew();

        GlobalCourseReviewModelImpl globalCourseReviewModelImpl = (GlobalCourseReviewModelImpl) globalCourseReview;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, globalCourseReview, merge);

            globalCourseReview.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !GlobalCourseReviewModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((globalCourseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(globalCourseReviewModelImpl.getOriginalCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);

                args = new Object[] {
                        Long.valueOf(globalCourseReviewModelImpl.getCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);
            }

            if ((globalCourseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        globalCourseReviewModelImpl.getOriginalCourseIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI,
                    args);

                args = new Object[] { globalCourseReviewModelImpl.getCourseIri() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI,
                    args);
            }

            if ((globalCourseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(globalCourseReviewModelImpl.getOriginalGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] {
                        Long.valueOf(globalCourseReviewModelImpl.getGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }

            if ((globalCourseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(globalCourseReviewModelImpl.getOriginalCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] {
                        Long.valueOf(globalCourseReviewModelImpl.getCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }
        }

        EntityCacheUtil.putResult(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            GlobalCourseReviewImpl.class, globalCourseReview.getPrimaryKey(),
            globalCourseReview);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                new Object[] { globalCourseReview.getCourseReviewIri() },
                globalCourseReview);
        } else {
            if ((globalCourseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COURSEREVIEWIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        globalCourseReviewModelImpl.getOriginalCourseReviewIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEREVIEWIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                    new Object[] { globalCourseReview.getCourseReviewIri() },
                    globalCourseReview);
            }
        }

        return globalCourseReview;
    }

    protected GlobalCourseReview toUnwrappedModel(
        GlobalCourseReview globalCourseReview) {
        if (globalCourseReview instanceof GlobalCourseReviewImpl) {
            return globalCourseReview;
        }

        GlobalCourseReviewImpl globalCourseReviewImpl = new GlobalCourseReviewImpl();

        globalCourseReviewImpl.setNew(globalCourseReview.isNew());
        globalCourseReviewImpl.setPrimaryKey(globalCourseReview.getPrimaryKey());

        globalCourseReviewImpl.setGlobalCourseReviewId(globalCourseReview.getGlobalCourseReviewId());
        globalCourseReviewImpl.setCompanyId(globalCourseReview.getCompanyId());
        globalCourseReviewImpl.setGroupId(globalCourseReview.getGroupId());
        globalCourseReviewImpl.setCourseReviewIri(globalCourseReview.getCourseReviewIri());
        globalCourseReviewImpl.setUpdatedDate(globalCourseReview.getUpdatedDate());
        globalCourseReviewImpl.setCourseIri(globalCourseReview.getCourseIri());
        globalCourseReviewImpl.setHref(globalCourseReview.getHref());
        globalCourseReviewImpl.setNterInstance(globalCourseReview.getNterInstance());
        globalCourseReviewImpl.setCourseId(globalCourseReview.getCourseId());
        globalCourseReviewImpl.setUserDisplayName(globalCourseReview.getUserDisplayName());
        globalCourseReviewImpl.setSingleSignOnValue(globalCourseReview.getSingleSignOnValue());
        globalCourseReviewImpl.setSummary(globalCourseReview.getSummary());
        globalCourseReviewImpl.setContent(globalCourseReview.getContent());
        globalCourseReviewImpl.setCreateDate(globalCourseReview.getCreateDate());
        globalCourseReviewImpl.setModifiedDate(globalCourseReview.getModifiedDate());
        globalCourseReviewImpl.setStarScore(globalCourseReview.getStarScore());
        globalCourseReviewImpl.setFromTrustedReviewer(globalCourseReview.isFromTrustedReviewer());
        globalCourseReviewImpl.setRemoved(globalCourseReview.isRemoved());
        globalCourseReviewImpl.setRemovedDate(globalCourseReview.getRemovedDate());
        globalCourseReviewImpl.setIsHidden(globalCourseReview.isIsHidden());

        return globalCourseReviewImpl;
    }

    /**
     * Returns the global course review with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the global course review
     * @return the global course review
     * @throws com.liferay.portal.NoSuchModelException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlobalCourseReview findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the global course review with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException} if it could not be found.
     *
     * @param globalCourseReviewId the primary key of the global course review
     * @return the global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByPrimaryKey(long globalCourseReviewId)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = fetchByPrimaryKey(globalCourseReviewId);

        if (globalCourseReview == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    globalCourseReviewId);
            }

            throw new NoSuchGlobalCourseReviewException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                globalCourseReviewId);
        }

        return globalCourseReview;
    }

    /**
     * Returns the global course review with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the global course review
     * @return the global course review, or <code>null</code> if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlobalCourseReview fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the global course review with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param globalCourseReviewId the primary key of the global course review
     * @return the global course review, or <code>null</code> if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview fetchByPrimaryKey(long globalCourseReviewId)
        throws SystemException {
        GlobalCourseReview globalCourseReview = (GlobalCourseReview) EntityCacheUtil.getResult(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                GlobalCourseReviewImpl.class, globalCourseReviewId);

        if (globalCourseReview == _nullGlobalCourseReview) {
            return null;
        }

        if (globalCourseReview == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                globalCourseReview = (GlobalCourseReview) session.get(GlobalCourseReviewImpl.class,
                        Long.valueOf(globalCourseReviewId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (globalCourseReview != null) {
                    cacheResult(globalCourseReview);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                        GlobalCourseReviewImpl.class, globalCourseReviewId,
                        _nullGlobalCourseReview);
                }

                closeSession(session);
            }
        }

        return globalCourseReview;
    }

    /**
     * Returns the global course review where courseReviewIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException} if it could not be found.
     *
     * @param courseReviewIri the course review iri
     * @return the matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCourseReviewIri(String courseReviewIri)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = fetchByCourseReviewIri(courseReviewIri);

        if (globalCourseReview == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseReviewIri=");
            msg.append(courseReviewIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        }

        return globalCourseReview;
    }

    /**
     * Returns the global course review where courseReviewIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param courseReviewIri the course review iri
     * @return the matching global course review, or <code>null</code> if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview fetchByCourseReviewIri(String courseReviewIri)
        throws SystemException {
        return fetchByCourseReviewIri(courseReviewIri, true);
    }

    /**
     * Returns the global course review where courseReviewIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param courseReviewIri the course review iri
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching global course review, or <code>null</code> if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview fetchByCourseReviewIri(String courseReviewIri,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { courseReviewIri };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

            if (courseReviewIri == null) {
                query.append(_FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_1);
            } else {
                if (courseReviewIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_2);
                }
            }

            query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseReviewIri != null) {
                    qPos.add(courseReviewIri);
                }

                List<GlobalCourseReview> list = q.list();

                result = list;

                GlobalCourseReview globalCourseReview = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                        finderArgs, list);
                } else {
                    globalCourseReview = list.get(0);

                    cacheResult(globalCourseReview);

                    if ((globalCourseReview.getCourseReviewIri() == null) ||
                            !globalCourseReview.getCourseReviewIri()
                                                   .equals(courseReviewIri)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                            finderArgs, globalCourseReview);
                    }
                }

                return globalCourseReview;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEREVIEWIRI,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (GlobalCourseReview) result;
            }
        }
    }

    /**
     * Returns all the global course reviews where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCourseId(long courseId)
        throws SystemException {
        return findByCourseId(courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the global course reviews where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @return the range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCourseId(long courseId, int start,
        int end) throws SystemException {
        return findByCourseId(courseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the global course reviews where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCourseId(long courseId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID;
            finderArgs = new Object[] { courseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID;
            finderArgs = new Object[] { courseId, start, end, orderByComparator };
        }

        List<GlobalCourseReview> list = (List<GlobalCourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                list = (List<GlobalCourseReview>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first global course review in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCourseId_First(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        List<GlobalCourseReview> list = findByCourseId(courseId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last global course review in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCourseId_Last(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        int count = countByCourseId(courseId);

        List<GlobalCourseReview> list = findByCourseId(courseId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the global course reviews before and after the current global course review in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param globalCourseReviewId the primary key of the current global course review
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview[] findByCourseId_PrevAndNext(
        long globalCourseReviewId, long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = findByPrimaryKey(globalCourseReviewId);

        Session session = null;

        try {
            session = openSession();

            GlobalCourseReview[] array = new GlobalCourseReviewImpl[3];

            array[0] = getByCourseId_PrevAndNext(session, globalCourseReview,
                    courseId, orderByComparator, true);

            array[1] = globalCourseReview;

            array[2] = getByCourseId_PrevAndNext(session, globalCourseReview,
                    courseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlobalCourseReview getByCourseId_PrevAndNext(Session session,
        GlobalCourseReview globalCourseReview, long courseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

        query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

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
            query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(globalCourseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlobalCourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the global course reviews where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCourseIri(String courseIri)
        throws SystemException {
        return findByCourseIri(courseIri, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the global course reviews where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @return the range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCourseIri(String courseIri,
        int start, int end) throws SystemException {
        return findByCourseIri(courseIri, start, end, null);
    }

    /**
     * Returns an ordered range of all the global course reviews where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCourseIri(String courseIri,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI;
            finderArgs = new Object[] { courseIri };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIRI;
            finderArgs = new Object[] { courseIri, start, end, orderByComparator };
        }

        List<GlobalCourseReview> list = (List<GlobalCourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

            if (courseIri == null) {
                query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_1);
            } else {
                if (courseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseIri != null) {
                    qPos.add(courseIri);
                }

                list = (List<GlobalCourseReview>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first global course review in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCourseIri_First(String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        List<GlobalCourseReview> list = findByCourseIri(courseIri, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last global course review in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCourseIri_Last(String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        int count = countByCourseIri(courseIri);

        List<GlobalCourseReview> list = findByCourseIri(courseIri, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the global course reviews before and after the current global course review in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param globalCourseReviewId the primary key of the current global course review
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview[] findByCourseIri_PrevAndNext(
        long globalCourseReviewId, String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = findByPrimaryKey(globalCourseReviewId);

        Session session = null;

        try {
            session = openSession();

            GlobalCourseReview[] array = new GlobalCourseReviewImpl[3];

            array[0] = getByCourseIri_PrevAndNext(session, globalCourseReview,
                    courseIri, orderByComparator, true);

            array[1] = globalCourseReview;

            array[2] = getByCourseIri_PrevAndNext(session, globalCourseReview,
                    courseIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlobalCourseReview getByCourseIri_PrevAndNext(Session session,
        GlobalCourseReview globalCourseReview, String courseIri,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

        if (courseIri == null) {
            query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_1);
        } else {
            if (courseIri.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_3);
            } else {
                query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_2);
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
            query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (courseIri != null) {
            qPos.add(courseIri);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(globalCourseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlobalCourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the global course reviews where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByGroupId(long groupId)
        throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the global course reviews where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @return the range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByGroupId(long groupId, int start,
        int end) throws SystemException {
        return findByGroupId(groupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the global course reviews where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByGroupId(long groupId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<GlobalCourseReview> list = (List<GlobalCourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                list = (List<GlobalCourseReview>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first global course review in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        List<GlobalCourseReview> list = findByGroupId(groupId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last global course review in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        int count = countByGroupId(groupId);

        List<GlobalCourseReview> list = findByGroupId(groupId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the global course reviews before and after the current global course review in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param globalCourseReviewId the primary key of the current global course review
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview[] findByGroupId_PrevAndNext(
        long globalCourseReviewId, long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = findByPrimaryKey(globalCourseReviewId);

        Session session = null;

        try {
            session = openSession();

            GlobalCourseReview[] array = new GlobalCourseReviewImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, globalCourseReview,
                    groupId, orderByComparator, true);

            array[1] = globalCourseReview;

            array[2] = getByGroupId_PrevAndNext(session, globalCourseReview,
                    groupId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlobalCourseReview getByGroupId_PrevAndNext(Session session,
        GlobalCourseReview globalCourseReview, long groupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

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
            query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(globalCourseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlobalCourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the global course reviews where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCompanyId(long companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the global course reviews where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @return the range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCompanyId(long companyId, int start,
        int end) throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the global course reviews where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findByCompanyId(long companyId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<GlobalCourseReview> list = (List<GlobalCourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyId);

                list = (List<GlobalCourseReview>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first global course review in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCompanyId_First(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        List<GlobalCourseReview> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last global course review in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a matching global course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview findByCompanyId_Last(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        int count = countByCompanyId(companyId);

        List<GlobalCourseReview> list = findByCompanyId(companyId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchGlobalCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the global course reviews before and after the current global course review in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param globalCourseReviewId the primary key of the current global course review
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next global course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException if a global course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public GlobalCourseReview[] findByCompanyId_PrevAndNext(
        long globalCourseReviewId, long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = findByPrimaryKey(globalCourseReviewId);

        Session session = null;

        try {
            session = openSession();

            GlobalCourseReview[] array = new GlobalCourseReviewImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, globalCourseReview,
                    companyId, orderByComparator, true);

            array[1] = globalCourseReview;

            array[2] = getByCompanyId_PrevAndNext(session, globalCourseReview,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlobalCourseReview getByCompanyId_PrevAndNext(Session session,
        GlobalCourseReview globalCourseReview, long companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLOBALCOURSEREVIEW_WHERE);

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
            query.append(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(globalCourseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlobalCourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the global course reviews.
     *
     * @return the global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the global course reviews.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @return the range of global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the global course reviews.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of global course reviews
     * @param end the upper bound of the range of global course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of global course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<GlobalCourseReview> findAll(int start, int end,
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

        List<GlobalCourseReview> list = (List<GlobalCourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GLOBALCOURSEREVIEW);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GLOBALCOURSEREVIEW.concat(GlobalCourseReviewModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<GlobalCourseReview>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<GlobalCourseReview>) QueryUtil.list(q,
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
     * Removes the global course review where courseReviewIri = &#63; from the database.
     *
     * @param courseReviewIri the course review iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseReviewIri(String courseReviewIri)
        throws NoSuchGlobalCourseReviewException, SystemException {
        GlobalCourseReview globalCourseReview = findByCourseReviewIri(courseReviewIri);

        remove(globalCourseReview);
    }

    /**
     * Removes all the global course reviews where courseId = &#63; from the database.
     *
     * @param courseId the course ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(long courseId) throws SystemException {
        for (GlobalCourseReview globalCourseReview : findByCourseId(courseId)) {
            remove(globalCourseReview);
        }
    }

    /**
     * Removes all the global course reviews where courseIri = &#63; from the database.
     *
     * @param courseIri the course iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIri(String courseIri) throws SystemException {
        for (GlobalCourseReview globalCourseReview : findByCourseIri(courseIri)) {
            remove(globalCourseReview);
        }
    }

    /**
     * Removes all the global course reviews where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupId(long groupId) throws SystemException {
        for (GlobalCourseReview globalCourseReview : findByGroupId(groupId)) {
            remove(globalCourseReview);
        }
    }

    /**
     * Removes all the global course reviews where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCompanyId(long companyId) throws SystemException {
        for (GlobalCourseReview globalCourseReview : findByCompanyId(companyId)) {
            remove(globalCourseReview);
        }
    }

    /**
     * Removes all the global course reviews from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (GlobalCourseReview globalCourseReview : findAll()) {
            remove(globalCourseReview);
        }
    }

    /**
     * Returns the number of global course reviews where courseReviewIri = &#63;.
     *
     * @param courseReviewIri the course review iri
     * @return the number of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseReviewIri(String courseReviewIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseReviewIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEREVIEWIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLOBALCOURSEREVIEW_WHERE);

            if (courseReviewIri == null) {
                query.append(_FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_1);
            } else {
                if (courseReviewIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEREVIEWIRI_COURSEREVIEWIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseReviewIri != null) {
                    qPos.add(courseReviewIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEREVIEWIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of global course reviews where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the number of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(long courseId) throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLOBALCOURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of global course reviews where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the number of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIri(String courseIri) throws SystemException {
        Object[] finderArgs = new Object[] { courseIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLOBALCOURSEREVIEW_WHERE);

            if (courseIri == null) {
                query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_1);
            } else {
                if (courseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseIri != null) {
                    qPos.add(courseIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of global course reviews where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupId(long groupId) throws SystemException {
        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLOBALCOURSEREVIEW_WHERE);

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
     * Returns the number of global course reviews where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching global course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCompanyId(long companyId) throws SystemException {
        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLOBALCOURSEREVIEW_WHERE);

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
     * Returns the number of global course reviews.
     *
     * @return the number of global course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_GLOBALCOURSEREVIEW);

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
     * Initializes the global course review persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.GlobalCourseReview")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GlobalCourseReview>> listenersList = new ArrayList<ModelListener<GlobalCourseReview>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GlobalCourseReview>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GlobalCourseReviewImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
