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

import org.nterlearning.datamodel.catalog.NoSuchContributorException;
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.model.impl.ContributorImpl;
import org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl;
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
 * The persistence implementation for the contributor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContributorPersistence
 * @see ContributorUtil
 * @generated
 */
public class ContributorPersistenceImpl extends BasePersistenceImpl<Contributor>
    implements ContributorPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContributorUtil} to access the contributor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContributorImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID =
        new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
            new String[] { Long.class.getName() },
            ContributorModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHROLE =
        new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIdWithRole",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHROLE =
        new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCourseIdWithRole",
            new String[] { Long.class.getName(), String.class.getName() },
            ContributorModelImpl.COURSEID_COLUMN_BITMASK |
            ContributorModelImpl.ROLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIDWITHROLE = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseIdWithRole",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIDWITHROLE =
        new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByComponentIdWithRole",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHROLE =
        new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByComponentIdWithRole",
            new String[] { Long.class.getName(), String.class.getName() },
            ContributorModelImpl.COMPONENTID_COLUMN_BITMASK |
            ContributorModelImpl.ROLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTIDWITHROLE = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByComponentIdWithRole",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, ContributorImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTRIBUTOR = "SELECT contributor FROM Contributor contributor";
    private static final String _SQL_SELECT_CONTRIBUTOR_WHERE = "SELECT contributor FROM Contributor contributor WHERE ";
    private static final String _SQL_COUNT_CONTRIBUTOR = "SELECT COUNT(contributor) FROM Contributor contributor";
    private static final String _SQL_COUNT_CONTRIBUTOR_WHERE = "SELECT COUNT(contributor) FROM Contributor contributor WHERE ";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "contributor.courseId = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHROLE_COURSEID_2 = "contributor.courseId = ? AND ";
    private static final String _FINDER_COLUMN_COURSEIDWITHROLE_ROLE_1 = "contributor.role IS NULL";
    private static final String _FINDER_COLUMN_COURSEIDWITHROLE_ROLE_2 = "contributor.role = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHROLE_ROLE_3 = "(contributor.role IS NULL OR contributor.role = ?)";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHROLE_COMPONENTID_2 =
        "contributor.componentId = ? AND ";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_1 = "contributor.role IS NULL";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_2 = "contributor.role = ?";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_3 = "(contributor.role IS NULL OR contributor.role = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contributor.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Contributor exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Contributor exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContributorPersistenceImpl.class);
    private static Contributor _nullContributor = new ContributorImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Contributor> toCacheModel() {
                return _nullContributorCacheModel;
            }
        };

    private static CacheModel<Contributor> _nullContributorCacheModel = new CacheModel<Contributor>() {
            public Contributor toEntityModel() {
                return _nullContributor;
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
     * Caches the contributor in the entity cache if it is enabled.
     *
     * @param contributor the contributor
     */
    public void cacheResult(Contributor contributor) {
        EntityCacheUtil.putResult(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorImpl.class, contributor.getPrimaryKey(), contributor);

        contributor.resetOriginalValues();
    }

    /**
     * Caches the contributors in the entity cache if it is enabled.
     *
     * @param contributors the contributors
     */
    public void cacheResult(List<Contributor> contributors) {
        for (Contributor contributor : contributors) {
            if (EntityCacheUtil.getResult(
                        ContributorModelImpl.ENTITY_CACHE_ENABLED,
                        ContributorImpl.class, contributor.getPrimaryKey()) == null) {
                cacheResult(contributor);
            } else {
                contributor.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contributors.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContributorImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContributorImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contributor.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Contributor contributor) {
        EntityCacheUtil.removeResult(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorImpl.class, contributor.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Contributor> contributors) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Contributor contributor : contributors) {
            EntityCacheUtil.removeResult(ContributorModelImpl.ENTITY_CACHE_ENABLED,
                ContributorImpl.class, contributor.getPrimaryKey());
        }
    }

    /**
     * Creates a new contributor with the primary key. Does not add the contributor to the database.
     *
     * @param contributorId the primary key for the new contributor
     * @return the new contributor
     */
    public Contributor create(long contributorId) {
        Contributor contributor = new ContributorImpl();

        contributor.setNew(true);
        contributor.setPrimaryKey(contributorId);

        return contributor;
    }

    /**
     * Removes the contributor with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param contributorId the primary key of the contributor
     * @return the contributor that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor remove(long contributorId)
        throws NoSuchContributorException, SystemException {
        return remove(Long.valueOf(contributorId));
    }

    /**
     * Removes the contributor with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contributor
     * @return the contributor that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contributor remove(Serializable primaryKey)
        throws NoSuchContributorException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Contributor contributor = (Contributor) session.get(ContributorImpl.class,
                    primaryKey);

            if (contributor == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContributorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contributor);
        } catch (NoSuchContributorException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Contributor removeImpl(Contributor contributor)
        throws SystemException {
        contributor = toUnwrappedModel(contributor);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, contributor);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(contributor);

        return contributor;
    }

    @Override
    public Contributor updateImpl(
        org.nterlearning.datamodel.catalog.model.Contributor contributor,
        boolean merge) throws SystemException {
        contributor = toUnwrappedModel(contributor);

        boolean isNew = contributor.isNew();

        ContributorModelImpl contributorModelImpl = (ContributorModelImpl) contributor;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contributor, merge);

            contributor.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContributorModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contributorModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contributorModelImpl.getOriginalCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);

                args = new Object[] {
                        Long.valueOf(contributorModelImpl.getCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);
            }

            if ((contributorModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHROLE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contributorModelImpl.getOriginalCourseId()),
                        
                        contributorModelImpl.getOriginalRole()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHROLE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHROLE,
                    args);

                args = new Object[] {
                        Long.valueOf(contributorModelImpl.getCourseId()),
                        
                        contributorModelImpl.getRole()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHROLE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHROLE,
                    args);
            }

            if ((contributorModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHROLE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contributorModelImpl.getOriginalComponentId()),
                        
                        contributorModelImpl.getOriginalRole()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHROLE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHROLE,
                    args);

                args = new Object[] {
                        Long.valueOf(contributorModelImpl.getComponentId()),
                        
                        contributorModelImpl.getRole()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHROLE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHROLE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContributorModelImpl.ENTITY_CACHE_ENABLED,
            ContributorImpl.class, contributor.getPrimaryKey(), contributor);

        return contributor;
    }

    protected Contributor toUnwrappedModel(Contributor contributor) {
        if (contributor instanceof ContributorImpl) {
            return contributor;
        }

        ContributorImpl contributorImpl = new ContributorImpl();

        contributorImpl.setNew(contributor.isNew());
        contributorImpl.setPrimaryKey(contributor.getPrimaryKey());

        contributorImpl.setContributorId(contributor.getContributorId());
        contributorImpl.setCourseId(contributor.getCourseId());
        contributorImpl.setComponentId(contributor.getComponentId());
        contributorImpl.setRole(contributor.getRole());
        contributorImpl.setContributorName(contributor.getContributorName());
        contributorImpl.setVirtualCardData(contributor.getVirtualCardData());

        return contributorImpl;
    }

    /**
     * Returns the contributor with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contributor
     * @return the contributor
     * @throws com.liferay.portal.NoSuchModelException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contributor findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contributor with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchContributorException} if it could not be found.
     *
     * @param contributorId the primary key of the contributor
     * @return the contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByPrimaryKey(long contributorId)
        throws NoSuchContributorException, SystemException {
        Contributor contributor = fetchByPrimaryKey(contributorId);

        if (contributor == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contributorId);
            }

            throw new NoSuchContributorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                contributorId);
        }

        return contributor;
    }

    /**
     * Returns the contributor with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contributor
     * @return the contributor, or <code>null</code> if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contributor fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contributor with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param contributorId the primary key of the contributor
     * @return the contributor, or <code>null</code> if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor fetchByPrimaryKey(long contributorId)
        throws SystemException {
        Contributor contributor = (Contributor) EntityCacheUtil.getResult(ContributorModelImpl.ENTITY_CACHE_ENABLED,
                ContributorImpl.class, contributorId);

        if (contributor == _nullContributor) {
            return null;
        }

        if (contributor == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                contributor = (Contributor) session.get(ContributorImpl.class,
                        Long.valueOf(contributorId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (contributor != null) {
                    cacheResult(contributor);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ContributorModelImpl.ENTITY_CACHE_ENABLED,
                        ContributorImpl.class, contributorId, _nullContributor);
                }

                closeSession(session);
            }
        }

        return contributor;
    }

    /**
     * Returns all the contributors where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByCourseId(long courseId)
        throws SystemException {
        return findByCourseId(courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the contributors where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @return the range of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByCourseId(long courseId, int start, int end)
        throws SystemException {
        return findByCourseId(courseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contributors where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByCourseId(long courseId, int start, int end,
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

        List<Contributor> list = (List<Contributor>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_CONTRIBUTOR_WHERE);

            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

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

                list = (List<Contributor>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contributor in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByCourseId_First(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        List<Contributor> list = findByCourseId(courseId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContributorException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contributor in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByCourseId_Last(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        int count = countByCourseId(courseId);

        List<Contributor> list = findByCourseId(courseId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContributorException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contributors before and after the current contributor in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contributorId the primary key of the current contributor
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor[] findByCourseId_PrevAndNext(long contributorId,
        long courseId, OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        Contributor contributor = findByPrimaryKey(contributorId);

        Session session = null;

        try {
            session = openSession();

            Contributor[] array = new ContributorImpl[3];

            array[0] = getByCourseId_PrevAndNext(session, contributor,
                    courseId, orderByComparator, true);

            array[1] = contributor;

            array[2] = getByCourseId_PrevAndNext(session, contributor,
                    courseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contributor getByCourseId_PrevAndNext(Session session,
        Contributor contributor, long courseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTRIBUTOR_WHERE);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contributor);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contributor> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contributors where courseId = &#63; and role = &#63;.
     *
     * @param courseId the course ID
     * @param role the role
     * @return the matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByCourseIdWithRole(long courseId, String role)
        throws SystemException {
        return findByCourseIdWithRole(courseId, role, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contributors where courseId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param role the role
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @return the range of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByCourseIdWithRole(long courseId, String role,
        int start, int end) throws SystemException {
        return findByCourseIdWithRole(courseId, role, start, end, null);
    }

    /**
     * Returns an ordered range of all the contributors where courseId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param role the role
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByCourseIdWithRole(long courseId, String role,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHROLE;
            finderArgs = new Object[] { courseId, role };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHROLE;
            finderArgs = new Object[] {
                    courseId, role,
                    
                    start, end, orderByComparator
                };
        }

        List<Contributor> list = (List<Contributor>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_CONTRIBUTOR_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHROLE_COURSEID_2);

            if (role == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_1);
            } else {
                if (role.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_2);
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

                if (role != null) {
                    qPos.add(role);
                }

                list = (List<Contributor>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contributor in the ordered set where courseId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param role the role
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByCourseIdWithRole_First(long courseId, String role,
        OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        List<Contributor> list = findByCourseIdWithRole(courseId, role, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", role=");
            msg.append(role);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContributorException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contributor in the ordered set where courseId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param role the role
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByCourseIdWithRole_Last(long courseId, String role,
        OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        int count = countByCourseIdWithRole(courseId, role);

        List<Contributor> list = findByCourseIdWithRole(courseId, role,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", role=");
            msg.append(role);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContributorException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contributors before and after the current contributor in the ordered set where courseId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contributorId the primary key of the current contributor
     * @param courseId the course ID
     * @param role the role
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor[] findByCourseIdWithRole_PrevAndNext(
        long contributorId, long courseId, String role,
        OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        Contributor contributor = findByPrimaryKey(contributorId);

        Session session = null;

        try {
            session = openSession();

            Contributor[] array = new ContributorImpl[3];

            array[0] = getByCourseIdWithRole_PrevAndNext(session, contributor,
                    courseId, role, orderByComparator, true);

            array[1] = contributor;

            array[2] = getByCourseIdWithRole_PrevAndNext(session, contributor,
                    courseId, role, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contributor getByCourseIdWithRole_PrevAndNext(Session session,
        Contributor contributor, long courseId, String role,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTRIBUTOR_WHERE);

        query.append(_FINDER_COLUMN_COURSEIDWITHROLE_COURSEID_2);

        if (role == null) {
            query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_1);
        } else {
            if (role.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_3);
            } else {
                query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_2);
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

        if (role != null) {
            qPos.add(role);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contributor);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contributor> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contributors where componentId = &#63; and role = &#63;.
     *
     * @param componentId the component ID
     * @param role the role
     * @return the matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByComponentIdWithRole(long componentId,
        String role) throws SystemException {
        return findByComponentIdWithRole(componentId, role, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contributors where componentId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param role the role
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @return the range of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByComponentIdWithRole(long componentId,
        String role, int start, int end) throws SystemException {
        return findByComponentIdWithRole(componentId, role, start, end, null);
    }

    /**
     * Returns an ordered range of all the contributors where componentId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param role the role
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findByComponentIdWithRole(long componentId,
        String role, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHROLE;
            finderArgs = new Object[] { componentId, role };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIDWITHROLE;
            finderArgs = new Object[] {
                    componentId, role,
                    
                    start, end, orderByComparator
                };
        }

        List<Contributor> list = (List<Contributor>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_CONTRIBUTOR_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_COMPONENTID_2);

            if (role == null) {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_1);
            } else {
                if (role.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_3);
                } else {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_2);
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

                qPos.add(componentId);

                if (role != null) {
                    qPos.add(role);
                }

                list = (List<Contributor>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contributor in the ordered set where componentId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param role the role
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByComponentIdWithRole_First(long componentId,
        String role, OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        List<Contributor> list = findByComponentIdWithRole(componentId, role,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(", role=");
            msg.append(role);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContributorException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contributor in the ordered set where componentId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param role the role
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor findByComponentIdWithRole_Last(long componentId,
        String role, OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        int count = countByComponentIdWithRole(componentId, role);

        List<Contributor> list = findByComponentIdWithRole(componentId, role,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(", role=");
            msg.append(role);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContributorException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contributors before and after the current contributor in the ordered set where componentId = &#63; and role = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contributorId the primary key of the current contributor
     * @param componentId the component ID
     * @param role the role
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contributor
     * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contributor[] findByComponentIdWithRole_PrevAndNext(
        long contributorId, long componentId, String role,
        OrderByComparator orderByComparator)
        throws NoSuchContributorException, SystemException {
        Contributor contributor = findByPrimaryKey(contributorId);

        Session session = null;

        try {
            session = openSession();

            Contributor[] array = new ContributorImpl[3];

            array[0] = getByComponentIdWithRole_PrevAndNext(session,
                    contributor, componentId, role, orderByComparator, true);

            array[1] = contributor;

            array[2] = getByComponentIdWithRole_PrevAndNext(session,
                    contributor, componentId, role, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contributor getByComponentIdWithRole_PrevAndNext(
        Session session, Contributor contributor, long componentId,
        String role, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTRIBUTOR_WHERE);

        query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_COMPONENTID_2);

        if (role == null) {
            query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_1);
        } else {
            if (role.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_3);
            } else {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_2);
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

        qPos.add(componentId);

        if (role != null) {
            qPos.add(role);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contributor);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contributor> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contributors.
     *
     * @return the contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contributors.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @return the range of contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contributors.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contributors
     * @param end the upper bound of the range of contributors (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contributors
     * @throws SystemException if a system exception occurred
     */
    public List<Contributor> findAll(int start, int end,
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

        List<Contributor> list = (List<Contributor>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTRIBUTOR);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTRIBUTOR;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<Contributor>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Contributor>) QueryUtil.list(q, getDialect(),
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
     * Removes all the contributors where courseId = &#63; from the database.
     *
     * @param courseId the course ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(long courseId) throws SystemException {
        for (Contributor contributor : findByCourseId(courseId)) {
            remove(contributor);
        }
    }

    /**
     * Removes all the contributors where courseId = &#63; and role = &#63; from the database.
     *
     * @param courseId the course ID
     * @param role the role
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIdWithRole(long courseId, String role)
        throws SystemException {
        for (Contributor contributor : findByCourseIdWithRole(courseId, role)) {
            remove(contributor);
        }
    }

    /**
     * Removes all the contributors where componentId = &#63; and role = &#63; from the database.
     *
     * @param componentId the component ID
     * @param role the role
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentIdWithRole(long componentId, String role)
        throws SystemException {
        for (Contributor contributor : findByComponentIdWithRole(componentId,
                role)) {
            remove(contributor);
        }
    }

    /**
     * Removes all the contributors from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (Contributor contributor : findAll()) {
            remove(contributor);
        }
    }

    /**
     * Returns the number of contributors where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the number of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(long courseId) throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTRIBUTOR_WHERE);

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
     * Returns the number of contributors where courseId = &#63; and role = &#63;.
     *
     * @param courseId the course ID
     * @param role the role
     * @return the number of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIdWithRole(long courseId, String role)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseId, role };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIDWITHROLE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTRIBUTOR_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHROLE_COURSEID_2);

            if (role == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_1);
            } else {
                if (role.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHROLE_ROLE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                if (role != null) {
                    qPos.add(role);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIDWITHROLE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contributors where componentId = &#63; and role = &#63;.
     *
     * @param componentId the component ID
     * @param role the role
     * @return the number of matching contributors
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentIdWithRole(long componentId, String role)
        throws SystemException {
        Object[] finderArgs = new Object[] { componentId, role };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHROLE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTRIBUTOR_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_COMPONENTID_2);

            if (role == null) {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_1);
            } else {
                if (role.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_3);
                } else {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHROLE_ROLE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(componentId);

                if (role != null) {
                    qPos.add(role);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHROLE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contributors.
     *
     * @return the number of contributors
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_CONTRIBUTOR);

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
     * Initializes the contributor persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.Contributor")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Contributor>> listenersList = new ArrayList<ModelListener<Contributor>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Contributor>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContributorImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
