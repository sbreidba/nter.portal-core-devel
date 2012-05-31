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

import org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException;
import org.nterlearning.datamodel.catalog.model.CourseRelated;
import org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl;
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
 * The persistence implementation for the course related service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRelatedPersistence
 * @see CourseRelatedUtil
 * @generated
 */
public class CourseRelatedPersistenceImpl extends BasePersistenceImpl<CourseRelated>
    implements CourseRelatedPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CourseRelatedUtil} to access the course related persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CourseRelatedImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE =
        new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            CourseRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRelatedCourseIdWithRelationshipType",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE =
        new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            CourseRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRelatedCourseIdWithRelationshipType",
            new String[] { Long.class.getName(), String.class.getName() },
            CourseRelatedModelImpl.COURSEID_COLUMN_BITMASK |
            CourseRelatedModelImpl.RELATIONSHIPTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE =
        new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRelatedCourseIdWithRelationshipType",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RELATEDCOURSEIRI =
        new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            CourseRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRelatedCourseIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIRI =
        new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            CourseRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRelatedCourseIri", new String[] { String.class.getName() },
            CourseRelatedModelImpl.RELATEDCOURSEIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_RELATEDCOURSEIRI = new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRelatedCourseIri", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            CourseRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            CourseRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COURSERELATED = "SELECT courseRelated FROM CourseRelated courseRelated";
    private static final String _SQL_SELECT_COURSERELATED_WHERE = "SELECT courseRelated FROM CourseRelated courseRelated WHERE ";
    private static final String _SQL_COUNT_COURSERELATED = "SELECT COUNT(courseRelated) FROM CourseRelated courseRelated";
    private static final String _SQL_COUNT_COURSERELATED_WHERE = "SELECT COUNT(courseRelated) FROM CourseRelated courseRelated WHERE ";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_COURSEID_2 =
        "courseRelated.courseId = ? AND ";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_1 =
        "courseRelated.relationshipType IS NULL";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_2 =
        "courseRelated.relationshipType = ?";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_3 =
        "(courseRelated.relationshipType IS NULL OR courseRelated.relationshipType = ?)";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_1 =
        "courseRelated.relatedCourseIri IS NULL";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_2 =
        "courseRelated.relatedCourseIri = ?";
    private static final String _FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_3 =
        "(courseRelated.relatedCourseIri IS NULL OR courseRelated.relatedCourseIri = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "courseRelated.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CourseRelated exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CourseRelated exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CourseRelatedPersistenceImpl.class);
    private static CourseRelated _nullCourseRelated = new CourseRelatedImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CourseRelated> toCacheModel() {
                return _nullCourseRelatedCacheModel;
            }
        };

    private static CacheModel<CourseRelated> _nullCourseRelatedCacheModel = new CacheModel<CourseRelated>() {
            public CourseRelated toEntityModel() {
                return _nullCourseRelated;
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
     * Caches the course related in the entity cache if it is enabled.
     *
     * @param courseRelated the course related
     */
    public void cacheResult(CourseRelated courseRelated) {
        EntityCacheUtil.putResult(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedImpl.class, courseRelated.getPrimaryKey(),
            courseRelated);

        courseRelated.resetOriginalValues();
    }

    /**
     * Caches the course relateds in the entity cache if it is enabled.
     *
     * @param courseRelateds the course relateds
     */
    public void cacheResult(List<CourseRelated> courseRelateds) {
        for (CourseRelated courseRelated : courseRelateds) {
            if (EntityCacheUtil.getResult(
                        CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        CourseRelatedImpl.class, courseRelated.getPrimaryKey()) == null) {
                cacheResult(courseRelated);
            } else {
                courseRelated.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all course relateds.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CourseRelatedImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CourseRelatedImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the course related.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CourseRelated courseRelated) {
        EntityCacheUtil.removeResult(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedImpl.class, courseRelated.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CourseRelated> courseRelateds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CourseRelated courseRelated : courseRelateds) {
            EntityCacheUtil.removeResult(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
                CourseRelatedImpl.class, courseRelated.getPrimaryKey());
        }
    }

    /**
     * Creates a new course related with the primary key. Does not add the course related to the database.
     *
     * @param courseRelatedId the primary key for the new course related
     * @return the new course related
     */
    public CourseRelated create(long courseRelatedId) {
        CourseRelated courseRelated = new CourseRelatedImpl();

        courseRelated.setNew(true);
        courseRelated.setPrimaryKey(courseRelatedId);

        return courseRelated;
    }

    /**
     * Removes the course related with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseRelatedId the primary key of the course related
     * @return the course related that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated remove(long courseRelatedId)
        throws NoSuchCourseRelatedException, SystemException {
        return remove(Long.valueOf(courseRelatedId));
    }

    /**
     * Removes the course related with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the course related
     * @return the course related that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRelated remove(Serializable primaryKey)
        throws NoSuchCourseRelatedException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CourseRelated courseRelated = (CourseRelated) session.get(CourseRelatedImpl.class,
                    primaryKey);

            if (courseRelated == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourseRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(courseRelated);
        } catch (NoSuchCourseRelatedException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CourseRelated removeImpl(CourseRelated courseRelated)
        throws SystemException {
        courseRelated = toUnwrappedModel(courseRelated);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, courseRelated);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(courseRelated);

        return courseRelated;
    }

    @Override
    public CourseRelated updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated,
        boolean merge) throws SystemException {
        courseRelated = toUnwrappedModel(courseRelated);

        boolean isNew = courseRelated.isNew();

        CourseRelatedModelImpl courseRelatedModelImpl = (CourseRelatedModelImpl) courseRelated;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, courseRelated, merge);

            courseRelated.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CourseRelatedModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courseRelatedModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseRelatedModelImpl.getOriginalCourseId()),
                        
                        courseRelatedModelImpl.getOriginalRelationshipType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE,
                    args);

                args = new Object[] {
                        Long.valueOf(courseRelatedModelImpl.getCourseId()),
                        
                        courseRelatedModelImpl.getRelationshipType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE,
                    args);
            }

            if ((courseRelatedModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courseRelatedModelImpl.getOriginalRelatedCourseIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIRI,
                    args);

                args = new Object[] { courseRelatedModelImpl.getRelatedCourseIri() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIRI,
                    args);
            }
        }

        EntityCacheUtil.putResult(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            CourseRelatedImpl.class, courseRelated.getPrimaryKey(),
            courseRelated);

        return courseRelated;
    }

    protected CourseRelated toUnwrappedModel(CourseRelated courseRelated) {
        if (courseRelated instanceof CourseRelatedImpl) {
            return courseRelated;
        }

        CourseRelatedImpl courseRelatedImpl = new CourseRelatedImpl();

        courseRelatedImpl.setNew(courseRelated.isNew());
        courseRelatedImpl.setPrimaryKey(courseRelated.getPrimaryKey());

        courseRelatedImpl.setCourseRelatedId(courseRelated.getCourseRelatedId());
        courseRelatedImpl.setCourseId(courseRelated.getCourseId());
        courseRelatedImpl.setRelatedCourseId(courseRelated.getRelatedCourseId());
        courseRelatedImpl.setRelatedCourseIri(courseRelated.getRelatedCourseIri());
        courseRelatedImpl.setRelationshipType(courseRelated.getRelationshipType());

        return courseRelatedImpl;
    }

    /**
     * Returns the course related with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the course related
     * @return the course related
     * @throws com.liferay.portal.NoSuchModelException if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRelated findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course related with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException} if it could not be found.
     *
     * @param courseRelatedId the primary key of the course related
     * @return the course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated findByPrimaryKey(long courseRelatedId)
        throws NoSuchCourseRelatedException, SystemException {
        CourseRelated courseRelated = fetchByPrimaryKey(courseRelatedId);

        if (courseRelated == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + courseRelatedId);
            }

            throw new NoSuchCourseRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                courseRelatedId);
        }

        return courseRelated;
    }

    /**
     * Returns the course related with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the course related
     * @return the course related, or <code>null</code> if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRelated fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course related with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param courseRelatedId the primary key of the course related
     * @return the course related, or <code>null</code> if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated fetchByPrimaryKey(long courseRelatedId)
        throws SystemException {
        CourseRelated courseRelated = (CourseRelated) EntityCacheUtil.getResult(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
                CourseRelatedImpl.class, courseRelatedId);

        if (courseRelated == _nullCourseRelated) {
            return null;
        }

        if (courseRelated == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                courseRelated = (CourseRelated) session.get(CourseRelatedImpl.class,
                        Long.valueOf(courseRelatedId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (courseRelated != null) {
                    cacheResult(courseRelated);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        CourseRelatedImpl.class, courseRelatedId,
                        _nullCourseRelated);
                }

                closeSession(session);
            }
        }

        return courseRelated;
    }

    /**
     * Returns all the course relateds where courseId = &#63; and relationshipType = &#63;.
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @return the matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findByRelatedCourseIdWithRelationshipType(
        long courseId, String relationshipType) throws SystemException {
        return findByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course relateds where courseId = &#63; and relationshipType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @param start the lower bound of the range of course relateds
     * @param end the upper bound of the range of course relateds (not inclusive)
     * @return the range of matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findByRelatedCourseIdWithRelationshipType(
        long courseId, String relationshipType, int start, int end)
        throws SystemException {
        return findByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType, start, end, null);
    }

    /**
     * Returns an ordered range of all the course relateds where courseId = &#63; and relationshipType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @param start the lower bound of the range of course relateds
     * @param end the upper bound of the range of course relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findByRelatedCourseIdWithRelationshipType(
        long courseId, String relationshipType, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE;
            finderArgs = new Object[] { courseId, relationshipType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE;
            finderArgs = new Object[] {
                    courseId, relationshipType,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseRelated> list = (List<CourseRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSERELATED_WHERE);

            query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_COURSEID_2);

            if (relationshipType == null) {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_1);
            } else {
                if (relationshipType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_2);
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

                qPos.add(courseId);

                if (relationshipType != null) {
                    qPos.add(relationshipType);
                }

                list = (List<CourseRelated>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course related in the ordered set where courseId = &#63; and relationshipType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated findByRelatedCourseIdWithRelationshipType_First(
        long courseId, String relationshipType,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRelatedException, SystemException {
        List<CourseRelated> list = findByRelatedCourseIdWithRelationshipType(courseId,
                relationshipType, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", relationshipType=");
            msg.append(relationshipType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course related in the ordered set where courseId = &#63; and relationshipType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated findByRelatedCourseIdWithRelationshipType_Last(
        long courseId, String relationshipType,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRelatedException, SystemException {
        int count = countByRelatedCourseIdWithRelationshipType(courseId,
                relationshipType);

        List<CourseRelated> list = findByRelatedCourseIdWithRelationshipType(courseId,
                relationshipType, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", relationshipType=");
            msg.append(relationshipType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course relateds before and after the current course related in the ordered set where courseId = &#63; and relationshipType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRelatedId the primary key of the current course related
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated[] findByRelatedCourseIdWithRelationshipType_PrevAndNext(
        long courseRelatedId, long courseId, String relationshipType,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRelatedException, SystemException {
        CourseRelated courseRelated = findByPrimaryKey(courseRelatedId);

        Session session = null;

        try {
            session = openSession();

            CourseRelated[] array = new CourseRelatedImpl[3];

            array[0] = getByRelatedCourseIdWithRelationshipType_PrevAndNext(session,
                    courseRelated, courseId, relationshipType,
                    orderByComparator, true);

            array[1] = courseRelated;

            array[2] = getByRelatedCourseIdWithRelationshipType_PrevAndNext(session,
                    courseRelated, courseId, relationshipType,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRelated getByRelatedCourseIdWithRelationshipType_PrevAndNext(
        Session session, CourseRelated courseRelated, long courseId,
        String relationshipType, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSERELATED_WHERE);

        query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_COURSEID_2);

        if (relationshipType == null) {
            query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_1);
        } else {
            if (relationshipType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_3);
            } else {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_2);
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

        qPos.add(courseId);

        if (relationshipType != null) {
            qPos.add(relationshipType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseRelated);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRelated> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course relateds where relatedCourseIri = &#63;.
     *
     * @param relatedCourseIri the related course iri
     * @return the matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findByRelatedCourseIri(String relatedCourseIri)
        throws SystemException {
        return findByRelatedCourseIri(relatedCourseIri, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course relateds where relatedCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedCourseIri the related course iri
     * @param start the lower bound of the range of course relateds
     * @param end the upper bound of the range of course relateds (not inclusive)
     * @return the range of matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findByRelatedCourseIri(String relatedCourseIri,
        int start, int end) throws SystemException {
        return findByRelatedCourseIri(relatedCourseIri, start, end, null);
    }

    /**
     * Returns an ordered range of all the course relateds where relatedCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedCourseIri the related course iri
     * @param start the lower bound of the range of course relateds
     * @param end the upper bound of the range of course relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findByRelatedCourseIri(String relatedCourseIri,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RELATEDCOURSEIRI;
            finderArgs = new Object[] { relatedCourseIri };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RELATEDCOURSEIRI;
            finderArgs = new Object[] {
                    relatedCourseIri,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseRelated> list = (List<CourseRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_COURSERELATED_WHERE);

            if (relatedCourseIri == null) {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_1);
            } else {
                if (relatedCourseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_2);
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

                if (relatedCourseIri != null) {
                    qPos.add(relatedCourseIri);
                }

                list = (List<CourseRelated>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course related in the ordered set where relatedCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedCourseIri the related course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated findByRelatedCourseIri_First(String relatedCourseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRelatedException, SystemException {
        List<CourseRelated> list = findByRelatedCourseIri(relatedCourseIri, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("relatedCourseIri=");
            msg.append(relatedCourseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course related in the ordered set where relatedCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedCourseIri the related course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated findByRelatedCourseIri_Last(String relatedCourseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRelatedException, SystemException {
        int count = countByRelatedCourseIri(relatedCourseIri);

        List<CourseRelated> list = findByRelatedCourseIri(relatedCourseIri,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("relatedCourseIri=");
            msg.append(relatedCourseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course relateds before and after the current course related in the ordered set where relatedCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRelatedId the primary key of the current course related
     * @param relatedCourseIri the related course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course related
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRelated[] findByRelatedCourseIri_PrevAndNext(
        long courseRelatedId, String relatedCourseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRelatedException, SystemException {
        CourseRelated courseRelated = findByPrimaryKey(courseRelatedId);

        Session session = null;

        try {
            session = openSession();

            CourseRelated[] array = new CourseRelatedImpl[3];

            array[0] = getByRelatedCourseIri_PrevAndNext(session,
                    courseRelated, relatedCourseIri, orderByComparator, true);

            array[1] = courseRelated;

            array[2] = getByRelatedCourseIri_PrevAndNext(session,
                    courseRelated, relatedCourseIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRelated getByRelatedCourseIri_PrevAndNext(Session session,
        CourseRelated courseRelated, String relatedCourseIri,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSERELATED_WHERE);

        if (relatedCourseIri == null) {
            query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_1);
        } else {
            if (relatedCourseIri.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_3);
            } else {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_2);
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

        if (relatedCourseIri != null) {
            qPos.add(relatedCourseIri);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseRelated);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRelated> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course relateds.
     *
     * @return the course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course relateds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course relateds
     * @param end the upper bound of the range of course relateds (not inclusive)
     * @return the range of course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the course relateds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course relateds
     * @param end the upper bound of the range of course relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course relateds
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRelated> findAll(int start, int end,
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

        List<CourseRelated> list = (List<CourseRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSERELATED);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSERELATED;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<CourseRelated>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<CourseRelated>) QueryUtil.list(q,
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
     * Removes all the course relateds where courseId = &#63; and relationshipType = &#63; from the database.
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @throws SystemException if a system exception occurred
     */
    public void removeByRelatedCourseIdWithRelationshipType(long courseId,
        String relationshipType) throws SystemException {
        for (CourseRelated courseRelated : findByRelatedCourseIdWithRelationshipType(
                courseId, relationshipType)) {
            remove(courseRelated);
        }
    }

    /**
     * Removes all the course relateds where relatedCourseIri = &#63; from the database.
     *
     * @param relatedCourseIri the related course iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByRelatedCourseIri(String relatedCourseIri)
        throws SystemException {
        for (CourseRelated courseRelated : findByRelatedCourseIri(
                relatedCourseIri)) {
            remove(courseRelated);
        }
    }

    /**
     * Removes all the course relateds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (CourseRelated courseRelated : findAll()) {
            remove(courseRelated);
        }
    }

    /**
     * Returns the number of course relateds where courseId = &#63; and relationshipType = &#63;.
     *
     * @param courseId the course ID
     * @param relationshipType the relationship type
     * @return the number of matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public int countByRelatedCourseIdWithRelationshipType(long courseId,
        String relationshipType) throws SystemException {
        Object[] finderArgs = new Object[] { courseId, relationshipType };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_COURSERELATED_WHERE);

            query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_COURSEID_2);

            if (relationshipType == null) {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_1);
            } else {
                if (relationshipType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIDWITHRELATIONSHIPTYPE_RELATIONSHIPTYPE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                if (relationshipType != null) {
                    qPos.add(relationshipType);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIDWITHRELATIONSHIPTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course relateds where relatedCourseIri = &#63;.
     *
     * @param relatedCourseIri the related course iri
     * @return the number of matching course relateds
     * @throws SystemException if a system exception occurred
     */
    public int countByRelatedCourseIri(String relatedCourseIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { relatedCourseIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSERELATED_WHERE);

            if (relatedCourseIri == null) {
                query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_1);
            } else {
                if (relatedCourseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_RELATEDCOURSEIRI_RELATEDCOURSEIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (relatedCourseIri != null) {
                    qPos.add(relatedCourseIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RELATEDCOURSEIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course relateds.
     *
     * @return the number of course relateds
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSERELATED);

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
     * Initializes the course related persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.CourseRelated")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CourseRelated>> listenersList = new ArrayList<ModelListener<CourseRelated>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CourseRelated>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CourseRelatedImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
