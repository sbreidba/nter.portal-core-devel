package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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

import org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException;
import org.nterlearning.datamodel.catalog.model.Courses_Components;
import org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl;
import org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl;
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
 * The persistence implementation for the courses_ components service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Courses_ComponentsPersistence
 * @see Courses_ComponentsUtil
 * @generated
 */
public class Courses_ComponentsPersistenceImpl extends BasePersistenceImpl<Courses_Components>
    implements Courses_ComponentsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link Courses_ComponentsUtil} to access the courses_ components persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = Courses_ComponentsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
            new String[] { Long.class.getName() },
            Courses_ComponentsModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIRI =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseIri",
            new String[] { String.class.getName() },
            Courses_ComponentsModelImpl.COURSEIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIRI = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTID =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByComponentId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByComponentId",
            new String[] { Long.class.getName() },
            Courses_ComponentsModelImpl.COMPONENTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTID = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByComponentId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIRI =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByComponentIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI =
        new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByComponentIri",
            new String[] { String.class.getName() },
            Courses_ComponentsModelImpl.COMPONENTIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTIRI = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByComponentIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            Courses_ComponentsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_COMPONENTS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ComponentPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getComponents",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COMPONENTS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ComponentPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getComponentsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COMPONENT = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ComponentPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsComponent",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_SELECT_COURSES_COMPONENTS = "SELECT courses_Components FROM Courses_Components courses_Components";
    private static final String _SQL_SELECT_COURSES_COMPONENTS_WHERE = "SELECT courses_Components FROM Courses_Components courses_Components WHERE ";
    private static final String _SQL_COUNT_COURSES_COMPONENTS = "SELECT COUNT(courses_Components) FROM Courses_Components courses_Components";
    private static final String _SQL_COUNT_COURSES_COMPONENTS_WHERE = "SELECT COUNT(courses_Components) FROM Courses_Components courses_Components WHERE ";
    private static final String _SQL_GETCOMPONENTS = "SELECT {CATALOG_Component.*} FROM CATALOG_Component INNER JOIN CATALOG_Courses_Components ON (CATALOG_Courses_Components.coursesComponentsId = CATALOG_Component.coursesComponentsId) WHERE (CATALOG_Courses_Components.coursesComponentsId = ?)";
    private static final String _SQL_GETCOMPONENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Component WHERE coursesComponentsId = ?";
    private static final String _SQL_CONTAINSCOMPONENT = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Component WHERE coursesComponentsId = ? AND componentId = ?";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "courses_Components.courseId = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_1 = "courses_Components.courseIri IS NULL";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_2 = "courses_Components.courseIri = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_3 = "(courses_Components.courseIri IS NULL OR courses_Components.courseIri = ?)";
    private static final String _FINDER_COLUMN_COMPONENTID_COMPONENTID_2 = "courses_Components.componentId = ?";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_1 = "courses_Components.componentIri IS NULL";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_2 = "courses_Components.componentIri = ?";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_3 = "(courses_Components.componentIri IS NULL OR courses_Components.componentIri = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "courses_Components.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Courses_Components exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Courses_Components exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(Courses_ComponentsPersistenceImpl.class);
    private static Courses_Components _nullCourses_Components = new Courses_ComponentsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Courses_Components> toCacheModel() {
                return _nullCourses_ComponentsCacheModel;
            }
        };

    private static CacheModel<Courses_Components> _nullCourses_ComponentsCacheModel =
        new CacheModel<Courses_Components>() {
            public Courses_Components toEntityModel() {
                return _nullCourses_Components;
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
    protected ContainsComponent containsComponent;

    /**
     * Caches the courses_ components in the entity cache if it is enabled.
     *
     * @param courses_Components the courses_ components
     */
    public void cacheResult(Courses_Components courses_Components) {
        EntityCacheUtil.putResult(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsImpl.class, courses_Components.getPrimaryKey(),
            courses_Components);

        courses_Components.resetOriginalValues();
    }

    /**
     * Caches the courses_ componentses in the entity cache if it is enabled.
     *
     * @param courses_Componentses the courses_ componentses
     */
    public void cacheResult(List<Courses_Components> courses_Componentses) {
        for (Courses_Components courses_Components : courses_Componentses) {
            if (EntityCacheUtil.getResult(
                        Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
                        Courses_ComponentsImpl.class,
                        courses_Components.getPrimaryKey()) == null) {
                cacheResult(courses_Components);
            } else {
                courses_Components.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all courses_ componentses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(Courses_ComponentsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(Courses_ComponentsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the courses_ components.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Courses_Components courses_Components) {
        EntityCacheUtil.removeResult(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsImpl.class, courses_Components.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Courses_Components> courses_Componentses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Courses_Components courses_Components : courses_Componentses) {
            EntityCacheUtil.removeResult(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
                Courses_ComponentsImpl.class, courses_Components.getPrimaryKey());
        }
    }

    /**
     * Creates a new courses_ components with the primary key. Does not add the courses_ components to the database.
     *
     * @param coursesComponentsId the primary key for the new courses_ components
     * @return the new courses_ components
     */
    public Courses_Components create(long coursesComponentsId) {
        Courses_Components courses_Components = new Courses_ComponentsImpl();

        courses_Components.setNew(true);
        courses_Components.setPrimaryKey(coursesComponentsId);

        return courses_Components;
    }

    /**
     * Removes the courses_ components with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param coursesComponentsId the primary key of the courses_ components
     * @return the courses_ components that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components remove(long coursesComponentsId)
        throws NoSuchCourses_ComponentsException, SystemException {
        return remove(Long.valueOf(coursesComponentsId));
    }

    /**
     * Removes the courses_ components with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the courses_ components
     * @return the courses_ components that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Courses_Components remove(Serializable primaryKey)
        throws NoSuchCourses_ComponentsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Courses_Components courses_Components = (Courses_Components) session.get(Courses_ComponentsImpl.class,
                    primaryKey);

            if (courses_Components == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourses_ComponentsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(courses_Components);
        } catch (NoSuchCourses_ComponentsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Courses_Components removeImpl(
        Courses_Components courses_Components) throws SystemException {
        courses_Components = toUnwrappedModel(courses_Components);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, courses_Components);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(courses_Components);

        return courses_Components;
    }

    @Override
    public Courses_Components updateImpl(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components,
        boolean merge) throws SystemException {
        courses_Components = toUnwrappedModel(courses_Components);

        boolean isNew = courses_Components.isNew();

        Courses_ComponentsModelImpl courses_ComponentsModelImpl = (Courses_ComponentsModelImpl) courses_Components;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, courses_Components, merge);

            courses_Components.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !Courses_ComponentsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courses_ComponentsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courses_ComponentsModelImpl.getOriginalCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);

                args = new Object[] {
                        Long.valueOf(courses_ComponentsModelImpl.getCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);
            }

            if ((courses_ComponentsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courses_ComponentsModelImpl.getOriginalCourseIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI,
                    args);

                args = new Object[] { courses_ComponentsModelImpl.getCourseIri() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI,
                    args);
            }

            if ((courses_ComponentsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courses_ComponentsModelImpl.getOriginalComponentId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID,
                    args);

                args = new Object[] {
                        Long.valueOf(courses_ComponentsModelImpl.getComponentId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID,
                    args);
            }

            if ((courses_ComponentsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courses_ComponentsModelImpl.getOriginalComponentIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI,
                    args);

                args = new Object[] {
                        courses_ComponentsModelImpl.getComponentIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI,
                    args);
            }
        }

        EntityCacheUtil.putResult(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            Courses_ComponentsImpl.class, courses_Components.getPrimaryKey(),
            courses_Components);

        return courses_Components;
    }

    protected Courses_Components toUnwrappedModel(
        Courses_Components courses_Components) {
        if (courses_Components instanceof Courses_ComponentsImpl) {
            return courses_Components;
        }

        Courses_ComponentsImpl courses_ComponentsImpl = new Courses_ComponentsImpl();

        courses_ComponentsImpl.setNew(courses_Components.isNew());
        courses_ComponentsImpl.setPrimaryKey(courses_Components.getPrimaryKey());

        courses_ComponentsImpl.setCoursesComponentsId(courses_Components.getCoursesComponentsId());
        courses_ComponentsImpl.setCourseId(courses_Components.getCourseId());
        courses_ComponentsImpl.setCourseIri(courses_Components.getCourseIri());
        courses_ComponentsImpl.setComponentId(courses_Components.getComponentId());
        courses_ComponentsImpl.setComponentIri(courses_Components.getComponentIri());
        courses_ComponentsImpl.setOrderWeight(courses_Components.getOrderWeight());
        courses_ComponentsImpl.setSectionType(courses_Components.getSectionType());
        courses_ComponentsImpl.setComponentType(courses_Components.getComponentType());
        courses_ComponentsImpl.setMimeType(courses_Components.getMimeType());
        courses_ComponentsImpl.setCoursePaymentRequired(courses_Components.isCoursePaymentRequired());
        courses_ComponentsImpl.setComponentPaymentRequired(courses_Components.isComponentPaymentRequired());

        return courses_ComponentsImpl;
    }

    /**
     * Returns the courses_ components with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the courses_ components
     * @return the courses_ components
     * @throws com.liferay.portal.NoSuchModelException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Courses_Components findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the courses_ components with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException} if it could not be found.
     *
     * @param coursesComponentsId the primary key of the courses_ components
     * @return the courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByPrimaryKey(long coursesComponentsId)
        throws NoSuchCourses_ComponentsException, SystemException {
        Courses_Components courses_Components = fetchByPrimaryKey(coursesComponentsId);

        if (courses_Components == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    coursesComponentsId);
            }

            throw new NoSuchCourses_ComponentsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                coursesComponentsId);
        }

        return courses_Components;
    }

    /**
     * Returns the courses_ components with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the courses_ components
     * @return the courses_ components, or <code>null</code> if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Courses_Components fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the courses_ components with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param coursesComponentsId the primary key of the courses_ components
     * @return the courses_ components, or <code>null</code> if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components fetchByPrimaryKey(long coursesComponentsId)
        throws SystemException {
        Courses_Components courses_Components = (Courses_Components) EntityCacheUtil.getResult(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
                Courses_ComponentsImpl.class, coursesComponentsId);

        if (courses_Components == _nullCourses_Components) {
            return null;
        }

        if (courses_Components == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                courses_Components = (Courses_Components) session.get(Courses_ComponentsImpl.class,
                        Long.valueOf(coursesComponentsId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (courses_Components != null) {
                    cacheResult(courses_Components);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
                        Courses_ComponentsImpl.class, coursesComponentsId,
                        _nullCourses_Components);
                }

                closeSession(session);
            }
        }

        return courses_Components;
    }

    /**
     * Returns all the courses_ componentses where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByCourseId(long courseId)
        throws SystemException {
        return findByCourseId(courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the courses_ componentses where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @return the range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByCourseId(long courseId, int start,
        int end) throws SystemException {
        return findByCourseId(courseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByCourseId(long courseId, int start,
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

        List<Courses_Components> list = (List<Courses_Components>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                list = (List<Courses_Components>) QueryUtil.list(q,
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
     * Returns the first courses_ components in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByCourseId_First(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        List<Courses_Components> list = findByCourseId(courseId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last courses_ components in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByCourseId_Last(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        int count = countByCourseId(courseId);

        List<Courses_Components> list = findByCourseId(courseId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses_ componentses before and after the current courses_ components in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param coursesComponentsId the primary key of the current courses_ components
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components[] findByCourseId_PrevAndNext(
        long coursesComponentsId, long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        Courses_Components courses_Components = findByPrimaryKey(coursesComponentsId);

        Session session = null;

        try {
            session = openSession();

            Courses_Components[] array = new Courses_ComponentsImpl[3];

            array[0] = getByCourseId_PrevAndNext(session, courses_Components,
                    courseId, orderByComparator, true);

            array[1] = courses_Components;

            array[2] = getByCourseId_PrevAndNext(session, courses_Components,
                    courseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Courses_Components getByCourseId_PrevAndNext(Session session,
        Courses_Components courses_Components, long courseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

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
            query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courses_Components);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Courses_Components> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses_ componentses where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByCourseIri(String courseIri)
        throws SystemException {
        return findByCourseIri(courseIri, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the courses_ componentses where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @return the range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByCourseIri(String courseIri,
        int start, int end) throws SystemException {
        return findByCourseIri(courseIri, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByCourseIri(String courseIri,
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

        List<Courses_Components> list = (List<Courses_Components>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

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
                query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
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

                list = (List<Courses_Components>) QueryUtil.list(q,
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
     * Returns the first courses_ components in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByCourseIri_First(String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        List<Courses_Components> list = findByCourseIri(courseIri, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last courses_ components in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByCourseIri_Last(String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        int count = countByCourseIri(courseIri);

        List<Courses_Components> list = findByCourseIri(courseIri, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses_ componentses before and after the current courses_ components in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param coursesComponentsId the primary key of the current courses_ components
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components[] findByCourseIri_PrevAndNext(
        long coursesComponentsId, String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        Courses_Components courses_Components = findByPrimaryKey(coursesComponentsId);

        Session session = null;

        try {
            session = openSession();

            Courses_Components[] array = new Courses_ComponentsImpl[3];

            array[0] = getByCourseIri_PrevAndNext(session, courses_Components,
                    courseIri, orderByComparator, true);

            array[1] = courses_Components;

            array[2] = getByCourseIri_PrevAndNext(session, courses_Components,
                    courseIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Courses_Components getByCourseIri_PrevAndNext(Session session,
        Courses_Components courses_Components, String courseIri,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

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
            query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(courses_Components);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Courses_Components> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses_ componentses where componentId = &#63;.
     *
     * @param componentId the component ID
     * @return the matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByComponentId(long componentId)
        throws SystemException {
        return findByComponentId(componentId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses_ componentses where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @return the range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByComponentId(long componentId,
        int start, int end) throws SystemException {
        return findByComponentId(componentId, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByComponentId(long componentId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID;
            finderArgs = new Object[] { componentId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTID;
            finderArgs = new Object[] { componentId, start, end, orderByComparator };
        }

        List<Courses_Components> list = (List<Courses_Components>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTID_COMPONENTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(componentId);

                list = (List<Courses_Components>) QueryUtil.list(q,
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
     * Returns the first courses_ components in the ordered set where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByComponentId_First(long componentId,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        List<Courses_Components> list = findByComponentId(componentId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last courses_ components in the ordered set where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByComponentId_Last(long componentId,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        int count = countByComponentId(componentId);

        List<Courses_Components> list = findByComponentId(componentId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses_ componentses before and after the current courses_ components in the ordered set where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param coursesComponentsId the primary key of the current courses_ components
     * @param componentId the component ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components[] findByComponentId_PrevAndNext(
        long coursesComponentsId, long componentId,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        Courses_Components courses_Components = findByPrimaryKey(coursesComponentsId);

        Session session = null;

        try {
            session = openSession();

            Courses_Components[] array = new Courses_ComponentsImpl[3];

            array[0] = getByComponentId_PrevAndNext(session,
                    courses_Components, componentId, orderByComparator, true);

            array[1] = courses_Components;

            array[2] = getByComponentId_PrevAndNext(session,
                    courses_Components, componentId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Courses_Components getByComponentId_PrevAndNext(Session session,
        Courses_Components courses_Components, long componentId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

        query.append(_FINDER_COLUMN_COMPONENTID_COMPONENTID_2);

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
            query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(componentId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courses_Components);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Courses_Components> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses_ componentses where componentIri = &#63;.
     *
     * @param componentIri the component iri
     * @return the matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByComponentIri(String componentIri)
        throws SystemException {
        return findByComponentIri(componentIri, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses_ componentses where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @return the range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByComponentIri(String componentIri,
        int start, int end) throws SystemException {
        return findByComponentIri(componentIri, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findByComponentIri(String componentIri,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI;
            finderArgs = new Object[] { componentIri };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIRI;
            finderArgs = new Object[] {
                    componentIri,
                    
                    start, end, orderByComparator
                };
        }

        List<Courses_Components> list = (List<Courses_Components>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

            if (componentIri == null) {
                query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_1);
            } else {
                if (componentIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (componentIri != null) {
                    qPos.add(componentIri);
                }

                list = (List<Courses_Components>) QueryUtil.list(q,
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
     * Returns the first courses_ components in the ordered set where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByComponentIri_First(String componentIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        List<Courses_Components> list = findByComponentIri(componentIri, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentIri=");
            msg.append(componentIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last courses_ components in the ordered set where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components findByComponentIri_Last(String componentIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        int count = countByComponentIri(componentIri);

        List<Courses_Components> list = findByComponentIri(componentIri,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentIri=");
            msg.append(componentIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourses_ComponentsException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses_ componentses before and after the current courses_ components in the ordered set where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param coursesComponentsId the primary key of the current courses_ components
     * @param componentIri the component iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next courses_ components
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Courses_Components[] findByComponentIri_PrevAndNext(
        long coursesComponentsId, String componentIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourses_ComponentsException, SystemException {
        Courses_Components courses_Components = findByPrimaryKey(coursesComponentsId);

        Session session = null;

        try {
            session = openSession();

            Courses_Components[] array = new Courses_ComponentsImpl[3];

            array[0] = getByComponentIri_PrevAndNext(session,
                    courses_Components, componentIri, orderByComparator, true);

            array[1] = courses_Components;

            array[2] = getByComponentIri_PrevAndNext(session,
                    courses_Components, componentIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Courses_Components getByComponentIri_PrevAndNext(
        Session session, Courses_Components courses_Components,
        String componentIri, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSES_COMPONENTS_WHERE);

        if (componentIri == null) {
            query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_1);
        } else {
            if (componentIri.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_3);
            } else {
                query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_2);
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
            query.append(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (componentIri != null) {
            qPos.add(componentIri);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courses_Components);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Courses_Components> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses_ componentses.
     *
     * @return the courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses_ componentses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @return the range of courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public List<Courses_Components> findAll(int start, int end,
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

        List<Courses_Components> list = (List<Courses_Components>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSES_COMPONENTS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSES_COMPONENTS.concat(Courses_ComponentsModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<Courses_Components>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Courses_Components>) QueryUtil.list(q,
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
     * Removes all the courses_ componentses where courseId = &#63; from the database.
     *
     * @param courseId the course ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(long courseId) throws SystemException {
        for (Courses_Components courses_Components : findByCourseId(courseId)) {
            remove(courses_Components);
        }
    }

    /**
     * Removes all the courses_ componentses where courseIri = &#63; from the database.
     *
     * @param courseIri the course iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIri(String courseIri) throws SystemException {
        for (Courses_Components courses_Components : findByCourseIri(courseIri)) {
            remove(courses_Components);
        }
    }

    /**
     * Removes all the courses_ componentses where componentId = &#63; from the database.
     *
     * @param componentId the component ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentId(long componentId) throws SystemException {
        for (Courses_Components courses_Components : findByComponentId(
                componentId)) {
            remove(courses_Components);
        }
    }

    /**
     * Removes all the courses_ componentses where componentIri = &#63; from the database.
     *
     * @param componentIri the component iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentIri(String componentIri)
        throws SystemException {
        for (Courses_Components courses_Components : findByComponentIri(
                componentIri)) {
            remove(courses_Components);
        }
    }

    /**
     * Removes all the courses_ componentses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (Courses_Components courses_Components : findAll()) {
            remove(courses_Components);
        }
    }

    /**
     * Returns the number of courses_ componentses where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the number of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(long courseId) throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSES_COMPONENTS_WHERE);

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
     * Returns the number of courses_ componentses where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the number of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIri(String courseIri) throws SystemException {
        Object[] finderArgs = new Object[] { courseIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSES_COMPONENTS_WHERE);

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
     * Returns the number of courses_ componentses where componentId = &#63;.
     *
     * @param componentId the component ID
     * @return the number of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentId(long componentId) throws SystemException {
        Object[] finderArgs = new Object[] { componentId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSES_COMPONENTS_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTID_COMPONENTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(componentId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of courses_ componentses where componentIri = &#63;.
     *
     * @param componentIri the component iri
     * @return the number of matching courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentIri(String componentIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { componentIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSES_COMPONENTS_WHERE);

            if (componentIri == null) {
                query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_1);
            } else {
                if (componentIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (componentIri != null) {
                    qPos.add(componentIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of courses_ componentses.
     *
     * @return the number of courses_ componentses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSES_COMPONENTS);

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
     * Returns all the components associated with the courses_ components.
     *
     * @param pk the primary key of the courses_ components
     * @return the components associated with the courses_ components
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        long pk) throws SystemException {
        return getComponents(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the components associated with the courses_ components.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the courses_ components
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @return the range of components associated with the courses_ components
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        long pk, int start, int end) throws SystemException {
        return getComponents(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the components associated with the courses_ components.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the courses_ components
     * @param start the lower bound of the range of courses_ componentses
     * @param end the upper bound of the range of courses_ componentses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of components associated with the courses_ components
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.Component> list = (List<org.nterlearning.datamodel.catalog.model.Component>) FinderCacheUtil.getResult(FINDER_PATH_GET_COMPONENTS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOMPONENTS.concat(ORDER_BY_CLAUSE)
                                            .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOMPONENTS;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_Component",
                    org.nterlearning.datamodel.catalog.model.impl.ComponentImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.Component>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COMPONENTS,
                        finderArgs);
                } else {
                    componentPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COMPONENTS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of components associated with the courses_ components.
     *
     * @param pk the primary key of the courses_ components
     * @return the number of components associated with the courses_ components
     * @throws SystemException if a system exception occurred
     */
    public int getComponentsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COMPONENTS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOMPONENTSSIZE);

                q.addScalar(COUNT_COLUMN_NAME,
                    com.liferay.portal.kernel.dao.orm.Type.LONG);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_GET_COMPONENTS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the component is associated with the courses_ components.
     *
     * @param pk the primary key of the courses_ components
     * @param componentPK the primary key of the component
     * @return <code>true</code> if the component is associated with the courses_ components; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsComponent(long pk, long componentPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, componentPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COMPONENT,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsComponent.contains(pk,
                            componentPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COMPONENT,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the courses_ components has any components associated with it.
     *
     * @param pk the primary key of the courses_ components to check for associations with components
     * @return <code>true</code> if the courses_ components has any components associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsComponents(long pk) throws SystemException {
        if (getComponentsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the courses_ components persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.Courses_Components")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Courses_Components>> listenersList = new ArrayList<ModelListener<Courses_Components>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Courses_Components>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsComponent = new ContainsComponent();
    }

    public void destroy() {
        EntityCacheUtil.removeCache(Courses_ComponentsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    protected class ContainsComponent {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsComponent() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOMPONENT,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long coursesComponentsId, long componentId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(coursesComponentsId), new Long(componentId)
                    });

            if (results.size() > 0) {
                Integer count = results.get(0);

                if (count.intValue() > 0) {
                    return true;
                }
            }

            return false;
        }
    }
}
