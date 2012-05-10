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

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;
import com.liferay.portlet.ratings.service.persistence.RatingsStatsPersistence;

import org.nterlearning.datamodel.catalog.NoSuchCourseReviewException;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl;
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
 * The persistence implementation for the course review service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseReviewPersistence
 * @see CourseReviewUtil
 * @generated
 */
public class CourseReviewPersistenceImpl extends BasePersistenceImpl<CourseReview>
    implements CourseReviewPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CourseReviewUtil} to access the course review persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CourseReviewImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
            new String[] { Long.class.getName() },
            CourseReviewModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            CourseReviewModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHUSERID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIdWithUserId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHUSERID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCourseIdWithUserId",
            new String[] { Long.class.getName(), Long.class.getName() },
            CourseReviewModelImpl.COURSEID_COLUMN_BITMASK |
            CourseReviewModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIDWITHUSERID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseIdWithUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
            new String[] { Long.class.getName() },
            CourseReviewModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
            new String[] { Long.class.getName() },
            CourseReviewModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REMOVED = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRemoved",
            new String[] {
                Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVED =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRemoved",
            new String[] { Boolean.class.getName() },
            CourseReviewModelImpl.REMOVED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REMOVED = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRemoved",
            new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHREMOVED =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCourseIdWithRemoved",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREMOVED =
        new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCourseIdWithRemoved",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            CourseReviewModelImpl.COURSEID_COLUMN_BITMASK |
            CourseReviewModelImpl.REMOVED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIDWITHREMOVED = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseIdWithRemoved",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, CourseReviewImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COURSEREVIEW = "SELECT courseReview FROM CourseReview courseReview";
    private static final String _SQL_SELECT_COURSEREVIEW_WHERE = "SELECT courseReview FROM CourseReview courseReview WHERE ";
    private static final String _SQL_COUNT_COURSEREVIEW = "SELECT COUNT(courseReview) FROM CourseReview courseReview";
    private static final String _SQL_COUNT_COURSEREVIEW_WHERE = "SELECT COUNT(courseReview) FROM CourseReview courseReview WHERE ";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "courseReview.courseId = ?";
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "courseReview.userId = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHUSERID_COURSEID_2 = "courseReview.courseId = ? AND ";
    private static final String _FINDER_COLUMN_COURSEIDWITHUSERID_USERID_2 = "courseReview.userId = ?";
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "courseReview.groupId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "courseReview.companyId = ?";
    private static final String _FINDER_COLUMN_REMOVED_REMOVED_2 = "courseReview.removed = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHREMOVED_COURSEID_2 = "courseReview.courseId = ? AND ";
    private static final String _FINDER_COLUMN_COURSEIDWITHREMOVED_REMOVED_2 = "courseReview.removed = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "courseReview.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CourseReview exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CourseReview exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CourseReviewPersistenceImpl.class);
    private static CourseReview _nullCourseReview = new CourseReviewImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CourseReview> toCacheModel() {
                return _nullCourseReviewCacheModel;
            }
        };

    private static CacheModel<CourseReview> _nullCourseReviewCacheModel = new CacheModel<CourseReview>() {
            public CourseReview toEntityModel() {
                return _nullCourseReview;
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
    @BeanReference(type = AssetEntryPersistence.class)
    protected AssetEntryPersistence assetEntryPersistence;
    @BeanReference(type = AssetTagPersistence.class)
    protected AssetTagPersistence assetTagPersistence;
    @BeanReference(type = RatingsStatsPersistence.class)
    protected RatingsStatsPersistence ratingsStatsPersistence;

    /**
     * Caches the course review in the entity cache if it is enabled.
     *
     * @param courseReview the course review
     */
    public void cacheResult(CourseReview courseReview) {
        EntityCacheUtil.putResult(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewImpl.class, courseReview.getPrimaryKey(), courseReview);

        courseReview.resetOriginalValues();
    }

    /**
     * Caches the course reviews in the entity cache if it is enabled.
     *
     * @param courseReviews the course reviews
     */
    public void cacheResult(List<CourseReview> courseReviews) {
        for (CourseReview courseReview : courseReviews) {
            if (EntityCacheUtil.getResult(
                        CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                        CourseReviewImpl.class, courseReview.getPrimaryKey()) == null) {
                cacheResult(courseReview);
            } else {
                courseReview.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all course reviews.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CourseReviewImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CourseReviewImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the course review.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CourseReview courseReview) {
        EntityCacheUtil.removeResult(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewImpl.class, courseReview.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CourseReview> courseReviews) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CourseReview courseReview : courseReviews) {
            EntityCacheUtil.removeResult(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                CourseReviewImpl.class, courseReview.getPrimaryKey());
        }
    }

    /**
     * Creates a new course review with the primary key. Does not add the course review to the database.
     *
     * @param courseReviewId the primary key for the new course review
     * @return the new course review
     */
    public CourseReview create(long courseReviewId) {
        CourseReview courseReview = new CourseReviewImpl();

        courseReview.setNew(true);
        courseReview.setPrimaryKey(courseReviewId);

        return courseReview;
    }

    /**
     * Removes the course review with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseReviewId the primary key of the course review
     * @return the course review that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview remove(long courseReviewId)
        throws NoSuchCourseReviewException, SystemException {
        return remove(Long.valueOf(courseReviewId));
    }

    /**
     * Removes the course review with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the course review
     * @return the course review that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseReview remove(Serializable primaryKey)
        throws NoSuchCourseReviewException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CourseReview courseReview = (CourseReview) session.get(CourseReviewImpl.class,
                    primaryKey);

            if (courseReview == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourseReviewException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(courseReview);
        } catch (NoSuchCourseReviewException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CourseReview removeImpl(CourseReview courseReview)
        throws SystemException {
        courseReview = toUnwrappedModel(courseReview);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, courseReview);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(courseReview);

        return courseReview;
    }

    @Override
    public CourseReview updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseReview courseReview,
        boolean merge) throws SystemException {
        courseReview = toUnwrappedModel(courseReview);

        boolean isNew = courseReview.isNew();

        CourseReviewModelImpl courseReviewModelImpl = (CourseReviewModelImpl) courseReview;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, courseReview, merge);

            courseReview.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CourseReviewModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getOriginalCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);
            }

            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }

            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getOriginalCourseId()),
                        Long.valueOf(courseReviewModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHUSERID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getCourseId()),
                        Long.valueOf(courseReviewModelImpl.getUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHUSERID,
                    args);
            }

            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getOriginalGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }

            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getOriginalCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVED.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Boolean.valueOf(courseReviewModelImpl.getOriginalRemoved())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REMOVED, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVED,
                    args);

                args = new Object[] {
                        Boolean.valueOf(courseReviewModelImpl.getRemoved())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REMOVED, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVED,
                    args);
            }

            if ((courseReviewModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREMOVED.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getOriginalCourseId()),
                        Boolean.valueOf(courseReviewModelImpl.getOriginalRemoved())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREMOVED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREMOVED,
                    args);

                args = new Object[] {
                        Long.valueOf(courseReviewModelImpl.getCourseId()),
                        Boolean.valueOf(courseReviewModelImpl.getRemoved())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREMOVED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREMOVED,
                    args);
            }
        }

        EntityCacheUtil.putResult(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            CourseReviewImpl.class, courseReview.getPrimaryKey(), courseReview);

        return courseReview;
    }

    protected CourseReview toUnwrappedModel(CourseReview courseReview) {
        if (courseReview instanceof CourseReviewImpl) {
            return courseReview;
        }

        CourseReviewImpl courseReviewImpl = new CourseReviewImpl();

        courseReviewImpl.setNew(courseReview.isNew());
        courseReviewImpl.setPrimaryKey(courseReview.getPrimaryKey());

        courseReviewImpl.setCourseReviewId(courseReview.getCourseReviewId());
        courseReviewImpl.setCompanyId(courseReview.getCompanyId());
        courseReviewImpl.setGroupId(courseReview.getGroupId());
        courseReviewImpl.setCourseId(courseReview.getCourseId());
        courseReviewImpl.setUserId(courseReview.getUserId());
        courseReviewImpl.setSummary(courseReview.getSummary());
        courseReviewImpl.setContent(courseReview.getContent());
        courseReviewImpl.setCreateDate(courseReview.getCreateDate());
        courseReviewImpl.setModifiedDate(courseReview.getModifiedDate());
        courseReviewImpl.setWeightedScore(courseReview.getWeightedScore());
        courseReviewImpl.setRemoved(courseReview.isRemoved());
        courseReviewImpl.setRemovedDate(courseReview.getRemovedDate());

        return courseReviewImpl;
    }

    /**
     * Returns the course review with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the course review
     * @return the course review
     * @throws com.liferay.portal.NoSuchModelException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseReview findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course review with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseReviewException} if it could not be found.
     *
     * @param courseReviewId the primary key of the course review
     * @return the course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByPrimaryKey(long courseReviewId)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = fetchByPrimaryKey(courseReviewId);

        if (courseReview == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + courseReviewId);
            }

            throw new NoSuchCourseReviewException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                courseReviewId);
        }

        return courseReview;
    }

    /**
     * Returns the course review with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the course review
     * @return the course review, or <code>null</code> if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseReview fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course review with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param courseReviewId the primary key of the course review
     * @return the course review, or <code>null</code> if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview fetchByPrimaryKey(long courseReviewId)
        throws SystemException {
        CourseReview courseReview = (CourseReview) EntityCacheUtil.getResult(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                CourseReviewImpl.class, courseReviewId);

        if (courseReview == _nullCourseReview) {
            return null;
        }

        if (courseReview == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                courseReview = (CourseReview) session.get(CourseReviewImpl.class,
                        Long.valueOf(courseReviewId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (courseReview != null) {
                    cacheResult(courseReview);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
                        CourseReviewImpl.class, courseReviewId,
                        _nullCourseReview);
                }

                closeSession(session);
            }
        }

        return courseReview;
    }

    /**
     * Returns all the course reviews where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseId(long courseId)
        throws SystemException {
        return findByCourseId(courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the course reviews where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseId(long courseId, int start, int end)
        throws SystemException {
        return findByCourseId(courseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseId(long courseId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
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

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCourseId_First(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByCourseId(courseId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCourseId_Last(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByCourseId(courseId);

        List<CourseReview> list = findByCourseId(courseId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByCourseId_PrevAndNext(long courseReviewId,
        long courseId, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByCourseId_PrevAndNext(session, courseReview,
                    courseId, orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByCourseId_PrevAndNext(session, courseReview,
                    courseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByCourseId_PrevAndNext(Session session,
        CourseReview courseReview, long courseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByUserId(long userId)
        throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course reviews where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByUserId(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByUserId(userId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByUserId(userId);

        List<CourseReview> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByUserId_PrevAndNext(long courseReviewId,
        long userId, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByUserId_PrevAndNext(session, courseReview, userId,
                    orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByUserId_PrevAndNext(session, courseReview, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByUserId_PrevAndNext(Session session,
        CourseReview courseReview, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

        query.append(_FINDER_COLUMN_USERID_USERID_2);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews where courseId = &#63; and userId = &#63;.
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseIdWithUserId(long courseId,
        long userId) throws SystemException {
        return findByCourseIdWithUserId(courseId, userId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course reviews where courseId = &#63; and userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseIdWithUserId(long courseId,
        long userId, int start, int end) throws SystemException {
        return findByCourseIdWithUserId(courseId, userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where courseId = &#63; and userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseIdWithUserId(long courseId,
        long userId, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHUSERID;
            finderArgs = new Object[] { courseId, userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHUSERID;
            finderArgs = new Object[] {
                    courseId, userId,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHUSERID_COURSEID_2);

            query.append(_FINDER_COLUMN_COURSEIDWITHUSERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                qPos.add(userId);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where courseId = &#63; and userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCourseIdWithUserId_First(long courseId,
        long userId, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByCourseIdWithUserId(courseId, userId, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where courseId = &#63; and userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCourseIdWithUserId_Last(long courseId,
        long userId, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByCourseIdWithUserId(courseId, userId);

        List<CourseReview> list = findByCourseIdWithUserId(courseId, userId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where courseId = &#63; and userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param courseId the course ID
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByCourseIdWithUserId_PrevAndNext(
        long courseReviewId, long courseId, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByCourseIdWithUserId_PrevAndNext(session,
                    courseReview, courseId, userId, orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByCourseIdWithUserId_PrevAndNext(session,
                    courseReview, courseId, userId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByCourseIdWithUserId_PrevAndNext(
        Session session, CourseReview courseReview, long courseId, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

        query.append(_FINDER_COLUMN_COURSEIDWITHUSERID_COURSEID_2);

        query.append(_FINDER_COLUMN_COURSEIDWITHUSERID_USERID_2);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByGroupId(long groupId)
        throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course reviews where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByGroupId(long groupId, int start, int end)
        throws SystemException {
        return findByGroupId(groupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByGroupId(long groupId, int start, int end,
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

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByGroupId(groupId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByGroupId(groupId);

        List<CourseReview> list = findByGroupId(groupId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByGroupId_PrevAndNext(long courseReviewId,
        long groupId, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, courseReview, groupId,
                    orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByGroupId_PrevAndNext(session, courseReview, groupId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByGroupId_PrevAndNext(Session session,
        CourseReview courseReview, long groupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCompanyId(long companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the course reviews where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCompanyId(long companyId, int start, int end)
        throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCompanyId(long companyId, int start,
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

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyId);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCompanyId_First(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCompanyId_Last(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByCompanyId(companyId);

        List<CourseReview> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByCompanyId_PrevAndNext(long courseReviewId,
        long companyId, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, courseReview,
                    companyId, orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByCompanyId_PrevAndNext(session, courseReview,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByCompanyId_PrevAndNext(Session session,
        CourseReview courseReview, long companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews where removed = &#63;.
     *
     * @param removed the removed
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByRemoved(boolean removed)
        throws SystemException {
        return findByRemoved(removed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course reviews where removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param removed the removed
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByRemoved(boolean removed, int start, int end)
        throws SystemException {
        return findByRemoved(removed, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param removed the removed
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByRemoved(boolean removed, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOVED;
            finderArgs = new Object[] { removed };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REMOVED;
            finderArgs = new Object[] { removed, start, end, orderByComparator };
        }

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_REMOVED_REMOVED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(removed);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByRemoved_First(boolean removed,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByRemoved(removed, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("removed=");
            msg.append(removed);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByRemoved_Last(boolean removed,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByRemoved(removed);

        List<CourseReview> list = findByRemoved(removed, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("removed=");
            msg.append(removed);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByRemoved_PrevAndNext(long courseReviewId,
        boolean removed, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByRemoved_PrevAndNext(session, courseReview, removed,
                    orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByRemoved_PrevAndNext(session, courseReview, removed,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByRemoved_PrevAndNext(Session session,
        CourseReview courseReview, boolean removed,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

        query.append(_FINDER_COLUMN_REMOVED_REMOVED_2);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(removed);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews where courseId = &#63; and removed = &#63;.
     *
     * @param courseId the course ID
     * @param removed the removed
     * @return the matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseIdWithRemoved(long courseId,
        boolean removed) throws SystemException {
        return findByCourseIdWithRemoved(courseId, removed, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course reviews where courseId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param removed the removed
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseIdWithRemoved(long courseId,
        boolean removed, int start, int end) throws SystemException {
        return findByCourseIdWithRemoved(courseId, removed, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews where courseId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param removed the removed
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findByCourseIdWithRemoved(long courseId,
        boolean removed, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREMOVED;
            finderArgs = new Object[] { courseId, removed };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHREMOVED;
            finderArgs = new Object[] {
                    courseId, removed,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHREMOVED_COURSEID_2);

            query.append(_FINDER_COLUMN_COURSEIDWITHREMOVED_REMOVED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                qPos.add(removed);

                list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course review in the ordered set where courseId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCourseIdWithRemoved_First(long courseId,
        boolean removed, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        List<CourseReview> list = findByCourseIdWithRemoved(courseId, removed,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", removed=");
            msg.append(removed);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course review in the ordered set where courseId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a matching course review could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview findByCourseIdWithRemoved_Last(long courseId,
        boolean removed, OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        int count = countByCourseIdWithRemoved(courseId, removed);

        List<CourseReview> list = findByCourseIdWithRemoved(courseId, removed,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", removed=");
            msg.append(removed);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseReviewException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course reviews before and after the current course review in the ordered set where courseId = &#63; and removed = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseReviewId the primary key of the current course review
     * @param courseId the course ID
     * @param removed the removed
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course review
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseReviewException if a course review with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseReview[] findByCourseIdWithRemoved_PrevAndNext(
        long courseReviewId, long courseId, boolean removed,
        OrderByComparator orderByComparator)
        throws NoSuchCourseReviewException, SystemException {
        CourseReview courseReview = findByPrimaryKey(courseReviewId);

        Session session = null;

        try {
            session = openSession();

            CourseReview[] array = new CourseReviewImpl[3];

            array[0] = getByCourseIdWithRemoved_PrevAndNext(session,
                    courseReview, courseId, removed, orderByComparator, true);

            array[1] = courseReview;

            array[2] = getByCourseIdWithRemoved_PrevAndNext(session,
                    courseReview, courseId, removed, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseReview getByCourseIdWithRemoved_PrevAndNext(
        Session session, CourseReview courseReview, long courseId,
        boolean removed, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREVIEW_WHERE);

        query.append(_FINDER_COLUMN_COURSEIDWITHREMOVED_COURSEID_2);

        query.append(_FINDER_COLUMN_COURSEIDWITHREMOVED_REMOVED_2);

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
            query.append(CourseReviewModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        qPos.add(removed);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseReview);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseReview> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course reviews.
     *
     * @return the course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course reviews.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @return the range of course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course reviews
     * @param end the upper bound of the range of course reviews (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course reviews
     * @throws SystemException if a system exception occurred
     */
    public List<CourseReview> findAll(int start, int end,
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

        List<CourseReview> list = (List<CourseReview>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSEREVIEW);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSEREVIEW.concat(CourseReviewModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<CourseReview>) QueryUtil.list(q, getDialect(),
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
     * Removes all the course reviews where courseId = &#63; from the database.
     *
     * @param courseId the course ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(long courseId) throws SystemException {
        for (CourseReview courseReview : findByCourseId(courseId)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUserId(long userId) throws SystemException {
        for (CourseReview courseReview : findByUserId(userId)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews where courseId = &#63; and userId = &#63; from the database.
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIdWithUserId(long courseId, long userId)
        throws SystemException {
        for (CourseReview courseReview : findByCourseIdWithUserId(courseId,
                userId)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupId(long groupId) throws SystemException {
        for (CourseReview courseReview : findByGroupId(groupId)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCompanyId(long companyId) throws SystemException {
        for (CourseReview courseReview : findByCompanyId(companyId)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews where removed = &#63; from the database.
     *
     * @param removed the removed
     * @throws SystemException if a system exception occurred
     */
    public void removeByRemoved(boolean removed) throws SystemException {
        for (CourseReview courseReview : findByRemoved(removed)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews where courseId = &#63; and removed = &#63; from the database.
     *
     * @param courseId the course ID
     * @param removed the removed
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIdWithRemoved(long courseId, boolean removed)
        throws SystemException {
        for (CourseReview courseReview : findByCourseIdWithRemoved(courseId,
                removed)) {
            remove(courseReview);
        }
    }

    /**
     * Removes all the course reviews from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (CourseReview courseReview : findAll()) {
            remove(courseReview);
        }
    }

    /**
     * Returns the number of course reviews where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(long courseId) throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

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
     * Returns the number of course reviews where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByUserId(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course reviews where courseId = &#63; and userId = &#63;.
     *
     * @param courseId the course ID
     * @param userId the user ID
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIdWithUserId(long courseId, long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIDWITHUSERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHUSERID_COURSEID_2);

            query.append(_FINDER_COLUMN_COURSEIDWITHUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIDWITHUSERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course reviews where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupId(long groupId) throws SystemException {
        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

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
     * Returns the number of course reviews where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCompanyId(long companyId) throws SystemException {
        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

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
     * Returns the number of course reviews where removed = &#63;.
     *
     * @param removed the removed
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByRemoved(boolean removed) throws SystemException {
        Object[] finderArgs = new Object[] { removed };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REMOVED,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_REMOVED_REMOVED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(removed);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REMOVED,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course reviews where courseId = &#63; and removed = &#63;.
     *
     * @param courseId the course ID
     * @param removed the removed
     * @return the number of matching course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIdWithRemoved(long courseId, boolean removed)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseId, removed };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREMOVED,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_COURSEREVIEW_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHREMOVED_COURSEID_2);

            query.append(_FINDER_COLUMN_COURSEIDWITHREMOVED_REMOVED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                qPos.add(removed);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREMOVED,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course reviews.
     *
     * @return the number of course reviews
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSEREVIEW);

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
     * Initializes the course review persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.CourseReview")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CourseReview>> listenersList = new ArrayList<ModelListener<CourseReview>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CourseReview>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CourseReviewImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
