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

import org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl;
import org.nterlearning.datamodel.catalog.model.impl.FeedReferenceModelImpl;
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
 * The persistence implementation for the feed reference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferencePersistence
 * @see FeedReferenceUtil
 * @generated
 */
public class FeedReferencePersistenceImpl extends BasePersistenceImpl<FeedReference>
    implements FeedReferencePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FeedReferenceUtil} to access the feed reference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FeedReferenceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByGroupId", new String[] { Long.class.getName() },
            FeedReferenceModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyId", new String[] { Long.class.getName() },
            FeedReferenceModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDWITHREMOVED =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByGroupIdWithRemoved",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHREMOVED =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByGroupIdWithRemoved",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            FeedReferenceModelImpl.GROUPID_COLUMN_BITMASK |
            FeedReferenceModelImpl.REMOVED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDWITHREMOVED = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByGroupIdWithRemoved",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDWITHTYPE =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByGroupIdWithType",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHTYPE =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByGroupIdWithType",
            new String[] { Long.class.getName(), String.class.getName() },
            FeedReferenceModelImpl.GROUPID_COLUMN_BITMASK |
            FeedReferenceModelImpl.FEEDTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDWITHTYPE = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByGroupIdWithType",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_FEEDIRI = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByFeedIri", new String[] { String.class.getName() },
            FeedReferenceModelImpl.FEEDIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDIRI = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFeedIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDTYPE = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByFeedType",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDTYPE =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByFeedType", new String[] { String.class.getName() },
            FeedReferenceModelImpl.FEEDTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDTYPE = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFeedType",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDVERSION =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByFeedVersion",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDVERSION =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByFeedVersion", new String[] { String.class.getName() },
            FeedReferenceModelImpl.FEEDVERSION_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDVERSION = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFeedVersion",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTPROVIDER =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContentProvider",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPROVIDER =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContentProvider", new String[] { String.class.getName() },
            FeedReferenceModelImpl.CONTENTPROVIDERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTPROVIDER = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContentProvider", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HREFHASH = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByHrefHash",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HREFHASH =
        new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByHrefHash", new String[] { String.class.getName() },
            FeedReferenceModelImpl.HREFHASH_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_HREFHASH = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByHrefHash",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED,
            FeedReferenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FEEDREFERENCE = "SELECT feedReference FROM FeedReference feedReference";
    private static final String _SQL_SELECT_FEEDREFERENCE_WHERE = "SELECT feedReference FROM FeedReference feedReference WHERE ";
    private static final String _SQL_COUNT_FEEDREFERENCE = "SELECT COUNT(feedReference) FROM FeedReference feedReference";
    private static final String _SQL_COUNT_FEEDREFERENCE_WHERE = "SELECT COUNT(feedReference) FROM FeedReference feedReference WHERE ";
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "feedReference.groupId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "feedReference.companyId = ?";
    private static final String _FINDER_COLUMN_GROUPIDWITHREMOVED_GROUPID_2 = "feedReference.groupId = ? AND ";
    private static final String _FINDER_COLUMN_GROUPIDWITHREMOVED_REMOVED_2 = "feedReference.removed = ?";
    private static final String _FINDER_COLUMN_GROUPIDWITHTYPE_GROUPID_2 = "feedReference.groupId = ? AND ";
    private static final String _FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_1 = "feedReference.feedType IS NULL";
    private static final String _FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_2 = "feedReference.feedType = ?";
    private static final String _FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_3 = "(feedReference.feedType IS NULL OR feedReference.feedType = ?)";
    private static final String _FINDER_COLUMN_FEEDIRI_FEEDIRI_1 = "feedReference.feedIri IS NULL";
    private static final String _FINDER_COLUMN_FEEDIRI_FEEDIRI_2 = "feedReference.feedIri = ?";
    private static final String _FINDER_COLUMN_FEEDIRI_FEEDIRI_3 = "(feedReference.feedIri IS NULL OR feedReference.feedIri = ?)";
    private static final String _FINDER_COLUMN_FEEDTYPE_FEEDTYPE_1 = "feedReference.feedType IS NULL";
    private static final String _FINDER_COLUMN_FEEDTYPE_FEEDTYPE_2 = "feedReference.feedType = ?";
    private static final String _FINDER_COLUMN_FEEDTYPE_FEEDTYPE_3 = "(feedReference.feedType IS NULL OR feedReference.feedType = ?)";
    private static final String _FINDER_COLUMN_FEEDVERSION_FEEDVERSION_1 = "feedReference.feedVersion IS NULL";
    private static final String _FINDER_COLUMN_FEEDVERSION_FEEDVERSION_2 = "feedReference.feedVersion = ?";
    private static final String _FINDER_COLUMN_FEEDVERSION_FEEDVERSION_3 = "(feedReference.feedVersion IS NULL OR feedReference.feedVersion = ?)";
    private static final String _FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_1 =
        "feedReference.contentProviderId IS NULL";
    private static final String _FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_2 =
        "feedReference.contentProviderId = ?";
    private static final String _FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_3 =
        "(feedReference.contentProviderId IS NULL OR feedReference.contentProviderId = ?)";
    private static final String _FINDER_COLUMN_HREFHASH_HREFHASH_1 = "feedReference.hrefHash IS NULL";
    private static final String _FINDER_COLUMN_HREFHASH_HREFHASH_2 = "feedReference.hrefHash = ?";
    private static final String _FINDER_COLUMN_HREFHASH_HREFHASH_3 = "(feedReference.hrefHash IS NULL OR feedReference.hrefHash = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "feedReference.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FeedReference exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FeedReference exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FeedReferencePersistenceImpl.class);
    private static FeedReference _nullFeedReference = new FeedReferenceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FeedReference> toCacheModel() {
                return _nullFeedReferenceCacheModel;
            }
        };

    private static CacheModel<FeedReference> _nullFeedReferenceCacheModel = new CacheModel<FeedReference>() {
            public FeedReference toEntityModel() {
                return _nullFeedReference;
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
     * Caches the feed reference in the entity cache if it is enabled.
     *
     * @param feedReference the feed reference
     */
    public void cacheResult(FeedReference feedReference) {
        EntityCacheUtil.putResult(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceImpl.class, feedReference.getPrimaryKey(),
            feedReference);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FEEDIRI,
            new Object[] { feedReference.getFeedIri() }, feedReference);

        feedReference.resetOriginalValues();
    }

    /**
     * Caches the feed references in the entity cache if it is enabled.
     *
     * @param feedReferences the feed references
     */
    public void cacheResult(List<FeedReference> feedReferences) {
        for (FeedReference feedReference : feedReferences) {
            if (EntityCacheUtil.getResult(
                        FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
                        FeedReferenceImpl.class, feedReference.getPrimaryKey()) == null) {
                cacheResult(feedReference);
            } else {
                feedReference.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all feed references.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FeedReferenceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FeedReferenceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the feed reference.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FeedReference feedReference) {
        EntityCacheUtil.removeResult(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceImpl.class, feedReference.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(feedReference);
    }

    @Override
    public void clearCache(List<FeedReference> feedReferences) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FeedReference feedReference : feedReferences) {
            EntityCacheUtil.removeResult(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
                FeedReferenceImpl.class, feedReference.getPrimaryKey());

            clearUniqueFindersCache(feedReference);
        }
    }

    protected void clearUniqueFindersCache(FeedReference feedReference) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FEEDIRI,
            new Object[] { feedReference.getFeedIri() });
    }

    /**
     * Creates a new feed reference with the primary key. Does not add the feed reference to the database.
     *
     * @param feedReferenceId the primary key for the new feed reference
     * @return the new feed reference
     */
    public FeedReference create(long feedReferenceId) {
        FeedReference feedReference = new FeedReferenceImpl();

        feedReference.setNew(true);
        feedReference.setPrimaryKey(feedReferenceId);

        return feedReference;
    }

    /**
     * Removes the feed reference with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param feedReferenceId the primary key of the feed reference
     * @return the feed reference that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference remove(long feedReferenceId)
        throws NoSuchFeedReferenceException, SystemException {
        return remove(Long.valueOf(feedReferenceId));
    }

    /**
     * Removes the feed reference with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the feed reference
     * @return the feed reference that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FeedReference remove(Serializable primaryKey)
        throws NoSuchFeedReferenceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FeedReference feedReference = (FeedReference) session.get(FeedReferenceImpl.class,
                    primaryKey);

            if (feedReference == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFeedReferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(feedReference);
        } catch (NoSuchFeedReferenceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FeedReference removeImpl(FeedReference feedReference)
        throws SystemException {
        feedReference = toUnwrappedModel(feedReference);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, feedReference);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(feedReference);

        return feedReference;
    }

    @Override
    public FeedReference updateImpl(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference,
        boolean merge) throws SystemException {
        feedReference = toUnwrappedModel(feedReference);

        boolean isNew = feedReference.isNew();

        FeedReferenceModelImpl feedReferenceModelImpl = (FeedReferenceModelImpl) feedReference;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, feedReference, merge);

            feedReference.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !FeedReferenceModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getOriginalGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getOriginalCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHREMOVED.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getOriginalGroupId()),
                        Boolean.valueOf(feedReferenceModelImpl.getOriginalRemoved())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDWITHREMOVED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHREMOVED,
                    args);

                args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getGroupId()),
                        Boolean.valueOf(feedReferenceModelImpl.getRemoved())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDWITHREMOVED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHREMOVED,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getOriginalGroupId()),
                        
                        feedReferenceModelImpl.getOriginalFeedType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDWITHTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHTYPE,
                    args);

                args = new Object[] {
                        Long.valueOf(feedReferenceModelImpl.getGroupId()),
                        
                        feedReferenceModelImpl.getFeedType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDWITHTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHTYPE,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        feedReferenceModelImpl.getOriginalFeedType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDTYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDTYPE,
                    args);

                args = new Object[] { feedReferenceModelImpl.getFeedType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDTYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDTYPE,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDVERSION.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        feedReferenceModelImpl.getOriginalFeedVersion()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDVERSION,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDVERSION,
                    args);

                args = new Object[] { feedReferenceModelImpl.getFeedVersion() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDVERSION,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDVERSION,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPROVIDER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        feedReferenceModelImpl.getOriginalContentProviderId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTENTPROVIDER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPROVIDER,
                    args);

                args = new Object[] {
                        feedReferenceModelImpl.getContentProviderId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTENTPROVIDER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPROVIDER,
                    args);
            }

            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HREFHASH.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        feedReferenceModelImpl.getOriginalHrefHash()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HREFHASH, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HREFHASH,
                    args);

                args = new Object[] { feedReferenceModelImpl.getHrefHash() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HREFHASH, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HREFHASH,
                    args);
            }
        }

        EntityCacheUtil.putResult(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
            FeedReferenceImpl.class, feedReference.getPrimaryKey(),
            feedReference);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FEEDIRI,
                new Object[] { feedReference.getFeedIri() }, feedReference);
        } else {
            if ((feedReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FEEDIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        feedReferenceModelImpl.getOriginalFeedIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDIRI, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FEEDIRI, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FEEDIRI,
                    new Object[] { feedReference.getFeedIri() }, feedReference);
            }
        }

        return feedReference;
    }

    protected FeedReference toUnwrappedModel(FeedReference feedReference) {
        if (feedReference instanceof FeedReferenceImpl) {
            return feedReference;
        }

        FeedReferenceImpl feedReferenceImpl = new FeedReferenceImpl();

        feedReferenceImpl.setNew(feedReference.isNew());
        feedReferenceImpl.setPrimaryKey(feedReference.getPrimaryKey());

        feedReferenceImpl.setFeedReferenceId(feedReference.getFeedReferenceId());
        feedReferenceImpl.setCompanyId(feedReference.getCompanyId());
        feedReferenceImpl.setGroupId(feedReference.getGroupId());
        feedReferenceImpl.setContentProviderId(feedReference.getContentProviderId());
        feedReferenceImpl.setHref(feedReference.getHref());
        feedReferenceImpl.setHrefHash(feedReference.getHrefHash());
        feedReferenceImpl.setPshb(feedReference.getPshb());
        feedReferenceImpl.setPshbSubscribed(feedReference.isPshbSubscribed());
        feedReferenceImpl.setFeedIri(feedReference.getFeedIri());
        feedReferenceImpl.setFeedType(feedReference.getFeedType());
        feedReferenceImpl.setFeedVersion(feedReference.getFeedVersion());
        feedReferenceImpl.setTrustworthyWeight(feedReference.getTrustworthyWeight());
        feedReferenceImpl.setCreateDate(feedReference.getCreateDate());
        feedReferenceImpl.setRemoved(feedReference.isRemoved());
        feedReferenceImpl.setRemovedDate(feedReference.getRemovedDate());
        feedReferenceImpl.setRemovedReason(feedReference.getRemovedReason());
        feedReferenceImpl.setSyncDate(feedReference.getSyncDate());
        feedReferenceImpl.setSyncSuccess(feedReference.isSyncSuccess());

        return feedReferenceImpl;
    }

    /**
     * Returns the feed reference with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the feed reference
     * @return the feed reference
     * @throws com.liferay.portal.NoSuchModelException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FeedReference findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the feed reference with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException} if it could not be found.
     *
     * @param feedReferenceId the primary key of the feed reference
     * @return the feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByPrimaryKey(long feedReferenceId)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = fetchByPrimaryKey(feedReferenceId);

        if (feedReference == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + feedReferenceId);
            }

            throw new NoSuchFeedReferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                feedReferenceId);
        }

        return feedReference;
    }

    /**
     * Returns the feed reference with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the feed reference
     * @return the feed reference, or <code>null</code> if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FeedReference fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the feed reference with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param feedReferenceId the primary key of the feed reference
     * @return the feed reference, or <code>null</code> if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference fetchByPrimaryKey(long feedReferenceId)
        throws SystemException {
        FeedReference feedReference = (FeedReference) EntityCacheUtil.getResult(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
                FeedReferenceImpl.class, feedReferenceId);

        if (feedReference == _nullFeedReference) {
            return null;
        }

        if (feedReference == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                feedReference = (FeedReference) session.get(FeedReferenceImpl.class,
                        Long.valueOf(feedReferenceId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (feedReference != null) {
                    cacheResult(feedReference);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(FeedReferenceModelImpl.ENTITY_CACHE_ENABLED,
                        FeedReferenceImpl.class, feedReferenceId,
                        _nullFeedReference);
                }

                closeSession(session);
            }
        }

        return feedReference;
    }

    /**
     * Returns all the feed references where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupId(long groupId)
        throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed references where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupId(long groupId, int start, int end)
        throws SystemException {
        return findByGroupId(groupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupId(long groupId, int start, int end,
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

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByGroupId(groupId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByGroupId(groupId);

        List<FeedReference> list = findByGroupId(groupId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByGroupId_PrevAndNext(long feedReferenceId,
        long groupId, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, feedReference,
                    groupId, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByGroupId_PrevAndNext(session, feedReference,
                    groupId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByGroupId_PrevAndNext(Session session,
        FeedReference feedReference, long groupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByCompanyId(long companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the feed references where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByCompanyId(long companyId, int start,
        int end) throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByCompanyId(long companyId, int start,
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

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyId);

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByCompanyId_First(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByCompanyId_Last(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByCompanyId(companyId);

        List<FeedReference> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByCompanyId_PrevAndNext(long feedReferenceId,
        long companyId, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, feedReference,
                    companyId, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByCompanyId_PrevAndNext(session, feedReference,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByCompanyId_PrevAndNext(Session session,
        FeedReference feedReference, long companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references where groupId = &#63; and removed = &#63;.
     *
     * @param groupId the group ID
     * @param removed the removed
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupIdWithRemoved(long groupId,
        boolean removed) throws SystemException {
        return findByGroupIdWithRemoved(groupId, removed, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed references where groupId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param removed the removed
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupIdWithRemoved(long groupId,
        boolean removed, int start, int end) throws SystemException {
        return findByGroupIdWithRemoved(groupId, removed, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where groupId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param removed the removed
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupIdWithRemoved(long groupId,
        boolean removed, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHREMOVED;
            finderArgs = new Object[] { groupId, removed };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDWITHREMOVED;
            finderArgs = new Object[] {
                    groupId, removed,
                    
                    start, end, orderByComparator
                };
        }

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_GROUPIDWITHREMOVED_GROUPID_2);

            query.append(_FINDER_COLUMN_GROUPIDWITHREMOVED_REMOVED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                qPos.add(removed);

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where groupId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByGroupIdWithRemoved_First(long groupId,
        boolean removed, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByGroupIdWithRemoved(groupId, removed,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(", removed=");
            msg.append(removed);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where groupId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByGroupIdWithRemoved_Last(long groupId,
        boolean removed, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByGroupIdWithRemoved(groupId, removed);

        List<FeedReference> list = findByGroupIdWithRemoved(groupId, removed,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(", removed=");
            msg.append(removed);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where groupId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param groupId the group ID
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByGroupIdWithRemoved_PrevAndNext(
        long feedReferenceId, long groupId, boolean removed,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByGroupIdWithRemoved_PrevAndNext(session,
                    feedReference, groupId, removed, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByGroupIdWithRemoved_PrevAndNext(session,
                    feedReference, groupId, removed, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByGroupIdWithRemoved_PrevAndNext(
        Session session, FeedReference feedReference, long groupId,
        boolean removed, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

        query.append(_FINDER_COLUMN_GROUPIDWITHREMOVED_GROUPID_2);

        query.append(_FINDER_COLUMN_GROUPIDWITHREMOVED_REMOVED_2);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        qPos.add(removed);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references where groupId = &#63; and feedType = &#63;.
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupIdWithType(long groupId,
        String feedType) throws SystemException {
        return findByGroupIdWithType(groupId, feedType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed references where groupId = &#63; and feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupIdWithType(long groupId,
        String feedType, int start, int end) throws SystemException {
        return findByGroupIdWithType(groupId, feedType, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where groupId = &#63; and feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByGroupIdWithType(long groupId,
        String feedType, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDWITHTYPE;
            finderArgs = new Object[] { groupId, feedType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDWITHTYPE;
            finderArgs = new Object[] {
                    groupId, feedType,
                    
                    start, end, orderByComparator
                };
        }

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_GROUPID_2);

            if (feedType == null) {
                query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_1);
            } else {
                if (feedType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                if (feedType != null) {
                    qPos.add(feedType);
                }

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where groupId = &#63; and feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByGroupIdWithType_First(long groupId,
        String feedType, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByGroupIdWithType(groupId, feedType, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(", feedType=");
            msg.append(feedType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where groupId = &#63; and feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByGroupIdWithType_Last(long groupId,
        String feedType, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByGroupIdWithType(groupId, feedType);

        List<FeedReference> list = findByGroupIdWithType(groupId, feedType,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(", feedType=");
            msg.append(feedType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where groupId = &#63; and feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param groupId the group ID
     * @param feedType the feed type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByGroupIdWithType_PrevAndNext(
        long feedReferenceId, long groupId, String feedType,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByGroupIdWithType_PrevAndNext(session, feedReference,
                    groupId, feedType, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByGroupIdWithType_PrevAndNext(session, feedReference,
                    groupId, feedType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByGroupIdWithType_PrevAndNext(Session session,
        FeedReference feedReference, long groupId, String feedType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

        query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_GROUPID_2);

        if (feedType == null) {
            query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_1);
        } else {
            if (feedType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_3);
            } else {
                query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_2);
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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (feedType != null) {
            qPos.add(feedType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the feed reference where feedIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException} if it could not be found.
     *
     * @param feedIri the feed iri
     * @return the matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByFeedIri(String feedIri)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = fetchByFeedIri(feedIri);

        if (feedReference == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedIri=");
            msg.append(feedIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFeedReferenceException(msg.toString());
        }

        return feedReference;
    }

    /**
     * Returns the feed reference where feedIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param feedIri the feed iri
     * @return the matching feed reference, or <code>null</code> if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference fetchByFeedIri(String feedIri)
        throws SystemException {
        return fetchByFeedIri(feedIri, true);
    }

    /**
     * Returns the feed reference where feedIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param feedIri the feed iri
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching feed reference, or <code>null</code> if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference fetchByFeedIri(String feedIri,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { feedIri };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FEEDIRI,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            if (feedIri == null) {
                query.append(_FINDER_COLUMN_FEEDIRI_FEEDIRI_1);
            } else {
                if (feedIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FEEDIRI_FEEDIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_FEEDIRI_FEEDIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (feedIri != null) {
                    qPos.add(feedIri);
                }

                List<FeedReference> list = q.list();

                result = list;

                FeedReference feedReference = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FEEDIRI,
                        finderArgs, list);
                } else {
                    feedReference = list.get(0);

                    cacheResult(feedReference);

                    if ((feedReference.getFeedIri() == null) ||
                            !feedReference.getFeedIri().equals(feedIri)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FEEDIRI,
                            finderArgs, feedReference);
                    }
                }

                return feedReference;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FEEDIRI,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (FeedReference) result;
            }
        }
    }

    /**
     * Returns all the feed references where feedType = &#63;.
     *
     * @param feedType the feed type
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByFeedType(String feedType)
        throws SystemException {
        return findByFeedType(feedType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the feed references where feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedType the feed type
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByFeedType(String feedType, int start,
        int end) throws SystemException {
        return findByFeedType(feedType, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedType the feed type
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByFeedType(String feedType, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDTYPE;
            finderArgs = new Object[] { feedType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDTYPE;
            finderArgs = new Object[] { feedType, start, end, orderByComparator };
        }

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            if (feedType == null) {
                query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_1);
            } else {
                if (feedType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (feedType != null) {
                    qPos.add(feedType);
                }

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedType the feed type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByFeedType_First(String feedType,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByFeedType(feedType, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedType=");
            msg.append(feedType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedType the feed type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByFeedType_Last(String feedType,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByFeedType(feedType);

        List<FeedReference> list = findByFeedType(feedType, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedType=");
            msg.append(feedType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where feedType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param feedType the feed type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByFeedType_PrevAndNext(long feedReferenceId,
        String feedType, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByFeedType_PrevAndNext(session, feedReference,
                    feedType, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByFeedType_PrevAndNext(session, feedReference,
                    feedType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByFeedType_PrevAndNext(Session session,
        FeedReference feedReference, String feedType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

        if (feedType == null) {
            query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_1);
        } else {
            if (feedType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_3);
            } else {
                query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_2);
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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (feedType != null) {
            qPos.add(feedType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references where feedVersion = &#63;.
     *
     * @param feedVersion the feed version
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByFeedVersion(String feedVersion)
        throws SystemException {
        return findByFeedVersion(feedVersion, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed references where feedVersion = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedVersion the feed version
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByFeedVersion(String feedVersion, int start,
        int end) throws SystemException {
        return findByFeedVersion(feedVersion, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where feedVersion = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedVersion the feed version
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByFeedVersion(String feedVersion, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDVERSION;
            finderArgs = new Object[] { feedVersion };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDVERSION;
            finderArgs = new Object[] { feedVersion, start, end, orderByComparator };
        }

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            if (feedVersion == null) {
                query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_1);
            } else {
                if (feedVersion.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_3);
                } else {
                    query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (feedVersion != null) {
                    qPos.add(feedVersion);
                }

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where feedVersion = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedVersion the feed version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByFeedVersion_First(String feedVersion,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByFeedVersion(feedVersion, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedVersion=");
            msg.append(feedVersion);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where feedVersion = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedVersion the feed version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByFeedVersion_Last(String feedVersion,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByFeedVersion(feedVersion);

        List<FeedReference> list = findByFeedVersion(feedVersion, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedVersion=");
            msg.append(feedVersion);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where feedVersion = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param feedVersion the feed version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByFeedVersion_PrevAndNext(long feedReferenceId,
        String feedVersion, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByFeedVersion_PrevAndNext(session, feedReference,
                    feedVersion, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByFeedVersion_PrevAndNext(session, feedReference,
                    feedVersion, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByFeedVersion_PrevAndNext(Session session,
        FeedReference feedReference, String feedVersion,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

        if (feedVersion == null) {
            query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_1);
        } else {
            if (feedVersion.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_3);
            } else {
                query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_2);
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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (feedVersion != null) {
            qPos.add(feedVersion);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references where contentProviderId = &#63;.
     *
     * @param contentProviderId the content provider ID
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByContentProvider(String contentProviderId)
        throws SystemException {
        return findByContentProvider(contentProviderId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed references where contentProviderId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contentProviderId the content provider ID
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByContentProvider(String contentProviderId,
        int start, int end) throws SystemException {
        return findByContentProvider(contentProviderId, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where contentProviderId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contentProviderId the content provider ID
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByContentProvider(String contentProviderId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPROVIDER;
            finderArgs = new Object[] { contentProviderId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTPROVIDER;
            finderArgs = new Object[] {
                    contentProviderId,
                    
                    start, end, orderByComparator
                };
        }

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            if (contentProviderId == null) {
                query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_1);
            } else {
                if (contentProviderId.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_3);
                } else {
                    query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (contentProviderId != null) {
                    qPos.add(contentProviderId);
                }

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where contentProviderId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contentProviderId the content provider ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByContentProvider_First(String contentProviderId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByContentProvider(contentProviderId, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contentProviderId=");
            msg.append(contentProviderId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where contentProviderId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contentProviderId the content provider ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByContentProvider_Last(String contentProviderId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByContentProvider(contentProviderId);

        List<FeedReference> list = findByContentProvider(contentProviderId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contentProviderId=");
            msg.append(contentProviderId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where contentProviderId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param contentProviderId the content provider ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByContentProvider_PrevAndNext(
        long feedReferenceId, String contentProviderId,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByContentProvider_PrevAndNext(session, feedReference,
                    contentProviderId, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByContentProvider_PrevAndNext(session, feedReference,
                    contentProviderId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByContentProvider_PrevAndNext(Session session,
        FeedReference feedReference, String contentProviderId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

        if (contentProviderId == null) {
            query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_1);
        } else {
            if (contentProviderId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_3);
            } else {
                query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_2);
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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (contentProviderId != null) {
            qPos.add(contentProviderId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references where hrefHash = &#63;.
     *
     * @param hrefHash the href hash
     * @return the matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByHrefHash(String hrefHash)
        throws SystemException {
        return findByHrefHash(hrefHash, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the feed references where hrefHash = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param hrefHash the href hash
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByHrefHash(String hrefHash, int start,
        int end) throws SystemException {
        return findByHrefHash(hrefHash, start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references where hrefHash = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param hrefHash the href hash
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findByHrefHash(String hrefHash, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HREFHASH;
            finderArgs = new Object[] { hrefHash };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HREFHASH;
            finderArgs = new Object[] { hrefHash, start, end, orderByComparator };
        }

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

            if (hrefHash == null) {
                query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_1);
            } else {
                if (hrefHash.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_3);
                } else {
                    query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (hrefHash != null) {
                    qPos.add(hrefHash);
                }

                list = (List<FeedReference>) QueryUtil.list(q, getDialect(),
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
     * Returns the first feed reference in the ordered set where hrefHash = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param hrefHash the href hash
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByHrefHash_First(String hrefHash,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        List<FeedReference> list = findByHrefHash(hrefHash, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("hrefHash=");
            msg.append(hrefHash);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last feed reference in the ordered set where hrefHash = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param hrefHash the href hash
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference findByHrefHash_Last(String hrefHash,
        OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        int count = countByHrefHash(hrefHash);

        List<FeedReference> list = findByHrefHash(hrefHash, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("hrefHash=");
            msg.append(hrefHash);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFeedReferenceException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the feed references before and after the current feed reference in the ordered set where hrefHash = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the primary key of the current feed reference
     * @param hrefHash the href hash
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next feed reference
     * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FeedReference[] findByHrefHash_PrevAndNext(long feedReferenceId,
        String hrefHash, OrderByComparator orderByComparator)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByPrimaryKey(feedReferenceId);

        Session session = null;

        try {
            session = openSession();

            FeedReference[] array = new FeedReferenceImpl[3];

            array[0] = getByHrefHash_PrevAndNext(session, feedReference,
                    hrefHash, orderByComparator, true);

            array[1] = feedReference;

            array[2] = getByHrefHash_PrevAndNext(session, feedReference,
                    hrefHash, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FeedReference getByHrefHash_PrevAndNext(Session session,
        FeedReference feedReference, String hrefHash,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FEEDREFERENCE_WHERE);

        if (hrefHash == null) {
            query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_1);
        } else {
            if (hrefHash.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_3);
            } else {
                query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_2);
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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (hrefHash != null) {
            qPos.add(hrefHash);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(feedReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FeedReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the feed references.
     *
     * @return the feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the feed references.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @return the range of feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the feed references.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of feed references
     * @param end the upper bound of the range of feed references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of feed references
     * @throws SystemException if a system exception occurred
     */
    public List<FeedReference> findAll(int start, int end,
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

        List<FeedReference> list = (List<FeedReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FEEDREFERENCE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FEEDREFERENCE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<FeedReference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FeedReference>) QueryUtil.list(q,
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
     * Removes all the feed references where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupId(long groupId) throws SystemException {
        for (FeedReference feedReference : findByGroupId(groupId)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCompanyId(long companyId) throws SystemException {
        for (FeedReference feedReference : findByCompanyId(companyId)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references where groupId = &#63; and removed = &#63; from the database.
     *
     * @param groupId the group ID
     * @param removed the removed
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupIdWithRemoved(long groupId, boolean removed)
        throws SystemException {
        for (FeedReference feedReference : findByGroupIdWithRemoved(groupId,
                removed)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references where groupId = &#63; and feedType = &#63; from the database.
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupIdWithType(long groupId, String feedType)
        throws SystemException {
        for (FeedReference feedReference : findByGroupIdWithType(groupId,
                feedType)) {
            remove(feedReference);
        }
    }

    /**
     * Removes the feed reference where feedIri = &#63; from the database.
     *
     * @param feedIri the feed iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByFeedIri(String feedIri)
        throws NoSuchFeedReferenceException, SystemException {
        FeedReference feedReference = findByFeedIri(feedIri);

        remove(feedReference);
    }

    /**
     * Removes all the feed references where feedType = &#63; from the database.
     *
     * @param feedType the feed type
     * @throws SystemException if a system exception occurred
     */
    public void removeByFeedType(String feedType) throws SystemException {
        for (FeedReference feedReference : findByFeedType(feedType)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references where feedVersion = &#63; from the database.
     *
     * @param feedVersion the feed version
     * @throws SystemException if a system exception occurred
     */
    public void removeByFeedVersion(String feedVersion)
        throws SystemException {
        for (FeedReference feedReference : findByFeedVersion(feedVersion)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references where contentProviderId = &#63; from the database.
     *
     * @param contentProviderId the content provider ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByContentProvider(String contentProviderId)
        throws SystemException {
        for (FeedReference feedReference : findByContentProvider(
                contentProviderId)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references where hrefHash = &#63; from the database.
     *
     * @param hrefHash the href hash
     * @throws SystemException if a system exception occurred
     */
    public void removeByHrefHash(String hrefHash) throws SystemException {
        for (FeedReference feedReference : findByHrefHash(hrefHash)) {
            remove(feedReference);
        }
    }

    /**
     * Removes all the feed references from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (FeedReference feedReference : findAll()) {
            remove(feedReference);
        }
    }

    /**
     * Returns the number of feed references where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupId(long groupId) throws SystemException {
        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

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
     * Returns the number of feed references where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByCompanyId(long companyId) throws SystemException {
        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

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
     * Returns the number of feed references where groupId = &#63; and removed = &#63;.
     *
     * @param groupId the group ID
     * @param removed the removed
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupIdWithRemoved(long groupId, boolean removed)
        throws SystemException {
        Object[] finderArgs = new Object[] { groupId, removed };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPIDWITHREMOVED,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_GROUPIDWITHREMOVED_GROUPID_2);

            query.append(_FINDER_COLUMN_GROUPIDWITHREMOVED_REMOVED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                qPos.add(removed);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPIDWITHREMOVED,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references where groupId = &#63; and feedType = &#63;.
     *
     * @param groupId the group ID
     * @param feedType the feed type
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupIdWithType(long groupId, String feedType)
        throws SystemException {
        Object[] finderArgs = new Object[] { groupId, feedType };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPIDWITHTYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_GROUPID_2);

            if (feedType == null) {
                query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_1);
            } else {
                if (feedType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_GROUPIDWITHTYPE_FEEDTYPE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                if (feedType != null) {
                    qPos.add(feedType);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPIDWITHTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references where feedIri = &#63;.
     *
     * @param feedIri the feed iri
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByFeedIri(String feedIri) throws SystemException {
        Object[] finderArgs = new Object[] { feedIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            if (feedIri == null) {
                query.append(_FINDER_COLUMN_FEEDIRI_FEEDIRI_1);
            } else {
                if (feedIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FEEDIRI_FEEDIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_FEEDIRI_FEEDIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (feedIri != null) {
                    qPos.add(feedIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FEEDIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references where feedType = &#63;.
     *
     * @param feedType the feed type
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByFeedType(String feedType) throws SystemException {
        Object[] finderArgs = new Object[] { feedType };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDTYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            if (feedType == null) {
                query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_1);
            } else {
                if (feedType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_FEEDTYPE_FEEDTYPE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (feedType != null) {
                    qPos.add(feedType);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FEEDTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references where feedVersion = &#63;.
     *
     * @param feedVersion the feed version
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByFeedVersion(String feedVersion) throws SystemException {
        Object[] finderArgs = new Object[] { feedVersion };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDVERSION,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            if (feedVersion == null) {
                query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_1);
            } else {
                if (feedVersion.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_3);
                } else {
                    query.append(_FINDER_COLUMN_FEEDVERSION_FEEDVERSION_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (feedVersion != null) {
                    qPos.add(feedVersion);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FEEDVERSION,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references where contentProviderId = &#63;.
     *
     * @param contentProviderId the content provider ID
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByContentProvider(String contentProviderId)
        throws SystemException {
        Object[] finderArgs = new Object[] { contentProviderId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTENTPROVIDER,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            if (contentProviderId == null) {
                query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_1);
            } else {
                if (contentProviderId.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_3);
                } else {
                    query.append(_FINDER_COLUMN_CONTENTPROVIDER_CONTENTPROVIDERID_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (contentProviderId != null) {
                    qPos.add(contentProviderId);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTENTPROVIDER,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references where hrefHash = &#63;.
     *
     * @param hrefHash the href hash
     * @return the number of matching feed references
     * @throws SystemException if a system exception occurred
     */
    public int countByHrefHash(String hrefHash) throws SystemException {
        Object[] finderArgs = new Object[] { hrefHash };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_HREFHASH,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FEEDREFERENCE_WHERE);

            if (hrefHash == null) {
                query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_1);
            } else {
                if (hrefHash.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_3);
                } else {
                    query.append(_FINDER_COLUMN_HREFHASH_HREFHASH_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (hrefHash != null) {
                    qPos.add(hrefHash);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HREFHASH,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of feed references.
     *
     * @return the number of feed references
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FEEDREFERENCE);

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
     * Initializes the feed reference persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.FeedReference")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FeedReference>> listenersList = new ArrayList<ModelListener<FeedReference>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FeedReference>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FeedReferenceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
