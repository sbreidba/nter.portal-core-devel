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

import org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException;
import org.nterlearning.datamodel.catalog.model.CourseRequirement;
import org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl;
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
 * The persistence implementation for the course requirement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRequirementPersistence
 * @see CourseRequirementUtil
 * @generated
 */
public class CourseRequirementPersistenceImpl extends BasePersistenceImpl<CourseRequirement>
    implements CourseRequirementPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CourseRequirementUtil} to access the course requirement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CourseRequirementImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE =
        new FinderPath(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            CourseRequirementImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCourseIdWithRequirementType",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE =
        new FinderPath(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            CourseRequirementImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCourseIdWithRequirementType",
            new String[] { Long.class.getName(), String.class.getName() },
            CourseRequirementModelImpl.COURSEID_COLUMN_BITMASK |
            CourseRequirementModelImpl.REQUIREMENTTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIDWITHREQUIREMENTTYPE =
        new FinderPath(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseIdWithRequirementType",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            CourseRequirementImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            CourseRequirementImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COURSEREQUIREMENT = "SELECT courseRequirement FROM CourseRequirement courseRequirement";
    private static final String _SQL_SELECT_COURSEREQUIREMENT_WHERE = "SELECT courseRequirement FROM CourseRequirement courseRequirement WHERE ";
    private static final String _SQL_COUNT_COURSEREQUIREMENT = "SELECT COUNT(courseRequirement) FROM CourseRequirement courseRequirement";
    private static final String _SQL_COUNT_COURSEREQUIREMENT_WHERE = "SELECT COUNT(courseRequirement) FROM CourseRequirement courseRequirement WHERE ";
    private static final String _FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_COURSEID_2 =
        "courseRequirement.courseId = ? AND ";
    private static final String _FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_1 =
        "courseRequirement.requirementType IS NULL";
    private static final String _FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_2 =
        "courseRequirement.requirementType = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_3 =
        "(courseRequirement.requirementType IS NULL OR courseRequirement.requirementType = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "courseRequirement.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CourseRequirement exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CourseRequirement exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CourseRequirementPersistenceImpl.class);
    private static CourseRequirement _nullCourseRequirement = new CourseRequirementImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CourseRequirement> toCacheModel() {
                return _nullCourseRequirementCacheModel;
            }
        };

    private static CacheModel<CourseRequirement> _nullCourseRequirementCacheModel =
        new CacheModel<CourseRequirement>() {
            public CourseRequirement toEntityModel() {
                return _nullCourseRequirement;
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
     * Caches the course requirement in the entity cache if it is enabled.
     *
     * @param courseRequirement the course requirement
     */
    public void cacheResult(CourseRequirement courseRequirement) {
        EntityCacheUtil.putResult(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementImpl.class, courseRequirement.getPrimaryKey(),
            courseRequirement);

        courseRequirement.resetOriginalValues();
    }

    /**
     * Caches the course requirements in the entity cache if it is enabled.
     *
     * @param courseRequirements the course requirements
     */
    public void cacheResult(List<CourseRequirement> courseRequirements) {
        for (CourseRequirement courseRequirement : courseRequirements) {
            if (EntityCacheUtil.getResult(
                        CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
                        CourseRequirementImpl.class,
                        courseRequirement.getPrimaryKey()) == null) {
                cacheResult(courseRequirement);
            } else {
                courseRequirement.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all course requirements.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CourseRequirementImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CourseRequirementImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the course requirement.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CourseRequirement courseRequirement) {
        EntityCacheUtil.removeResult(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementImpl.class, courseRequirement.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CourseRequirement> courseRequirements) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CourseRequirement courseRequirement : courseRequirements) {
            EntityCacheUtil.removeResult(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
                CourseRequirementImpl.class, courseRequirement.getPrimaryKey());
        }
    }

    /**
     * Creates a new course requirement with the primary key. Does not add the course requirement to the database.
     *
     * @param courseRequirementId the primary key for the new course requirement
     * @return the new course requirement
     */
    public CourseRequirement create(long courseRequirementId) {
        CourseRequirement courseRequirement = new CourseRequirementImpl();

        courseRequirement.setNew(true);
        courseRequirement.setPrimaryKey(courseRequirementId);

        return courseRequirement;
    }

    /**
     * Removes the course requirement with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseRequirementId the primary key of the course requirement
     * @return the course requirement that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRequirement remove(long courseRequirementId)
        throws NoSuchCourseRequirementException, SystemException {
        return remove(Long.valueOf(courseRequirementId));
    }

    /**
     * Removes the course requirement with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the course requirement
     * @return the course requirement that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRequirement remove(Serializable primaryKey)
        throws NoSuchCourseRequirementException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CourseRequirement courseRequirement = (CourseRequirement) session.get(CourseRequirementImpl.class,
                    primaryKey);

            if (courseRequirement == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourseRequirementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(courseRequirement);
        } catch (NoSuchCourseRequirementException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CourseRequirement removeImpl(CourseRequirement courseRequirement)
        throws SystemException {
        courseRequirement = toUnwrappedModel(courseRequirement);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, courseRequirement);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(courseRequirement);

        return courseRequirement;
    }

    @Override
    public CourseRequirement updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement,
        boolean merge) throws SystemException {
        courseRequirement = toUnwrappedModel(courseRequirement);

        boolean isNew = courseRequirement.isNew();

        CourseRequirementModelImpl courseRequirementModelImpl = (CourseRequirementModelImpl) courseRequirement;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, courseRequirement, merge);

            courseRequirement.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CourseRequirementModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courseRequirementModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseRequirementModelImpl.getOriginalCourseId()),
                        
                        courseRequirementModelImpl.getOriginalRequirementType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREQUIREMENTTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE,
                    args);

                args = new Object[] {
                        Long.valueOf(courseRequirementModelImpl.getCourseId()),
                        
                        courseRequirementModelImpl.getRequirementType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREQUIREMENTTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE,
                    args);
            }
        }

        EntityCacheUtil.putResult(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            CourseRequirementImpl.class, courseRequirement.getPrimaryKey(),
            courseRequirement);

        return courseRequirement;
    }

    protected CourseRequirement toUnwrappedModel(
        CourseRequirement courseRequirement) {
        if (courseRequirement instanceof CourseRequirementImpl) {
            return courseRequirement;
        }

        CourseRequirementImpl courseRequirementImpl = new CourseRequirementImpl();

        courseRequirementImpl.setNew(courseRequirement.isNew());
        courseRequirementImpl.setPrimaryKey(courseRequirement.getPrimaryKey());

        courseRequirementImpl.setCourseRequirementId(courseRequirement.getCourseRequirementId());
        courseRequirementImpl.setCourseId(courseRequirement.getCourseId());
        courseRequirementImpl.setRequirementType(courseRequirement.getRequirementType());
        courseRequirementImpl.setRequirementValue(courseRequirement.getRequirementValue());

        return courseRequirementImpl;
    }

    /**
     * Returns the course requirement with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the course requirement
     * @return the course requirement
     * @throws com.liferay.portal.NoSuchModelException if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRequirement findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course requirement with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException} if it could not be found.
     *
     * @param courseRequirementId the primary key of the course requirement
     * @return the course requirement
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRequirement findByPrimaryKey(long courseRequirementId)
        throws NoSuchCourseRequirementException, SystemException {
        CourseRequirement courseRequirement = fetchByPrimaryKey(courseRequirementId);

        if (courseRequirement == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    courseRequirementId);
            }

            throw new NoSuchCourseRequirementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                courseRequirementId);
        }

        return courseRequirement;
    }

    /**
     * Returns the course requirement with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the course requirement
     * @return the course requirement, or <code>null</code> if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRequirement fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course requirement with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param courseRequirementId the primary key of the course requirement
     * @return the course requirement, or <code>null</code> if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRequirement fetchByPrimaryKey(long courseRequirementId)
        throws SystemException {
        CourseRequirement courseRequirement = (CourseRequirement) EntityCacheUtil.getResult(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
                CourseRequirementImpl.class, courseRequirementId);

        if (courseRequirement == _nullCourseRequirement) {
            return null;
        }

        if (courseRequirement == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                courseRequirement = (CourseRequirement) session.get(CourseRequirementImpl.class,
                        Long.valueOf(courseRequirementId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (courseRequirement != null) {
                    cacheResult(courseRequirement);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
                        CourseRequirementImpl.class, courseRequirementId,
                        _nullCourseRequirement);
                }

                closeSession(session);
            }
        }

        return courseRequirement;
    }

    /**
     * Returns all the course requirements where courseId = &#63; and requirementType = &#63;.
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @return the matching course requirements
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, String requirementType) throws SystemException {
        return findByCourseIdWithRequirementType(courseId, requirementType,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course requirements where courseId = &#63; and requirementType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @param start the lower bound of the range of course requirements
     * @param end the upper bound of the range of course requirements (not inclusive)
     * @return the range of matching course requirements
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, String requirementType, int start, int end)
        throws SystemException {
        return findByCourseIdWithRequirementType(courseId, requirementType,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the course requirements where courseId = &#63; and requirementType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @param start the lower bound of the range of course requirements
     * @param end the upper bound of the range of course requirements (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course requirements
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, String requirementType, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE;
            finderArgs = new Object[] { courseId, requirementType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHREQUIREMENTTYPE;
            finderArgs = new Object[] {
                    courseId, requirementType,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseRequirement> list = (List<CourseRequirement>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSEREQUIREMENT_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_COURSEID_2);

            if (requirementType == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_1);
            } else {
                if (requirementType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_2);
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

                if (requirementType != null) {
                    qPos.add(requirementType);
                }

                list = (List<CourseRequirement>) QueryUtil.list(q,
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
     * Returns the first course requirement in the ordered set where courseId = &#63; and requirementType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course requirement
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a matching course requirement could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRequirement findByCourseIdWithRequirementType_First(
        long courseId, String requirementType,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRequirementException, SystemException {
        List<CourseRequirement> list = findByCourseIdWithRequirementType(courseId,
                requirementType, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", requirementType=");
            msg.append(requirementType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRequirementException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course requirement in the ordered set where courseId = &#63; and requirementType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course requirement
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a matching course requirement could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRequirement findByCourseIdWithRequirementType_Last(
        long courseId, String requirementType,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRequirementException, SystemException {
        int count = countByCourseIdWithRequirementType(courseId, requirementType);

        List<CourseRequirement> list = findByCourseIdWithRequirementType(courseId,
                requirementType, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", requirementType=");
            msg.append(requirementType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRequirementException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course requirements before and after the current course requirement in the ordered set where courseId = &#63; and requirementType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRequirementId the primary key of the current course requirement
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course requirement
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRequirement[] findByCourseIdWithRequirementType_PrevAndNext(
        long courseRequirementId, long courseId, String requirementType,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRequirementException, SystemException {
        CourseRequirement courseRequirement = findByPrimaryKey(courseRequirementId);

        Session session = null;

        try {
            session = openSession();

            CourseRequirement[] array = new CourseRequirementImpl[3];

            array[0] = getByCourseIdWithRequirementType_PrevAndNext(session,
                    courseRequirement, courseId, requirementType,
                    orderByComparator, true);

            array[1] = courseRequirement;

            array[2] = getByCourseIdWithRequirementType_PrevAndNext(session,
                    courseRequirement, courseId, requirementType,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRequirement getByCourseIdWithRequirementType_PrevAndNext(
        Session session, CourseRequirement courseRequirement, long courseId,
        String requirementType, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEREQUIREMENT_WHERE);

        query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_COURSEID_2);

        if (requirementType == null) {
            query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_1);
        } else {
            if (requirementType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_3);
            } else {
                query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_2);
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

        if (requirementType != null) {
            qPos.add(requirementType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseRequirement);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRequirement> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course requirements.
     *
     * @return the course requirements
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRequirement> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course requirements.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course requirements
     * @param end the upper bound of the range of course requirements (not inclusive)
     * @return the range of course requirements
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRequirement> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the course requirements.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course requirements
     * @param end the upper bound of the range of course requirements (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course requirements
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRequirement> findAll(int start, int end,
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

        List<CourseRequirement> list = (List<CourseRequirement>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSEREQUIREMENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSEREQUIREMENT;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<CourseRequirement>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<CourseRequirement>) QueryUtil.list(q,
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
     * Removes all the course requirements where courseId = &#63; and requirementType = &#63; from the database.
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIdWithRequirementType(long courseId,
        String requirementType) throws SystemException {
        for (CourseRequirement courseRequirement : findByCourseIdWithRequirementType(
                courseId, requirementType)) {
            remove(courseRequirement);
        }
    }

    /**
     * Removes all the course requirements from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (CourseRequirement courseRequirement : findAll()) {
            remove(courseRequirement);
        }
    }

    /**
     * Returns the number of course requirements where courseId = &#63; and requirementType = &#63;.
     *
     * @param courseId the course ID
     * @param requirementType the requirement type
     * @return the number of matching course requirements
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIdWithRequirementType(long courseId,
        String requirementType) throws SystemException {
        Object[] finderArgs = new Object[] { courseId, requirementType };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREQUIREMENTTYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_COURSEREQUIREMENT_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_COURSEID_2);

            if (requirementType == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_1);
            } else {
                if (requirementType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHREQUIREMENTTYPE_REQUIREMENTTYPE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                if (requirementType != null) {
                    qPos.add(requirementType);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIDWITHREQUIREMENTTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course requirements.
     *
     * @return the number of course requirements
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSEREQUIREMENT);

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
     * Initializes the course requirement persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.CourseRequirement")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CourseRequirement>> listenersList = new ArrayList<ModelListener<CourseRequirement>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CourseRequirement>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CourseRequirementImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
