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

import org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl;
import org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl;
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
 * The persistence implementation for the component record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecordPersistence
 * @see ComponentRecordUtil
 * @generated
 */
public class ComponentRecordPersistenceImpl extends BasePersistenceImpl<ComponentRecord>
    implements ComponentRecordPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ComponentRecordUtil} to access the component record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ComponentRecordImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSERECORDID =
        new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            ComponentRecordImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCourseRecordId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSERECORDID =
        new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            ComponentRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseRecordId",
            new String[] { Long.class.getName() },
            ComponentRecordModelImpl.COURSERECORDID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSERECORDID = new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseRecordId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIRI =
        new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            ComponentRecordImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByComponentIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI =
        new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            ComponentRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByComponentIri",
            new String[] { String.class.getName() },
            ComponentRecordModelImpl.COMPONENTIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTIRI = new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByComponentIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            ComponentRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            ComponentRecordImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COMPONENTRECORD = "SELECT componentRecord FROM ComponentRecord componentRecord";
    private static final String _SQL_SELECT_COMPONENTRECORD_WHERE = "SELECT componentRecord FROM ComponentRecord componentRecord WHERE ";
    private static final String _SQL_COUNT_COMPONENTRECORD = "SELECT COUNT(componentRecord) FROM ComponentRecord componentRecord";
    private static final String _SQL_COUNT_COMPONENTRECORD_WHERE = "SELECT COUNT(componentRecord) FROM ComponentRecord componentRecord WHERE ";
    private static final String _FINDER_COLUMN_COURSERECORDID_COURSERECORDID_2 = "componentRecord.courseRecordId = ?";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_1 = "componentRecord.componentIri IS NULL";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_2 = "componentRecord.componentIri = ?";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_3 = "(componentRecord.componentIri IS NULL OR componentRecord.componentIri = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "componentRecord.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ComponentRecord exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ComponentRecord exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ComponentRecordPersistenceImpl.class);
    private static ComponentRecord _nullComponentRecord = new ComponentRecordImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ComponentRecord> toCacheModel() {
                return _nullComponentRecordCacheModel;
            }
        };

    private static CacheModel<ComponentRecord> _nullComponentRecordCacheModel = new CacheModel<ComponentRecord>() {
            public ComponentRecord toEntityModel() {
                return _nullComponentRecord;
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
     * Caches the component record in the entity cache if it is enabled.
     *
     * @param componentRecord the component record
     */
    public void cacheResult(ComponentRecord componentRecord) {
        EntityCacheUtil.putResult(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordImpl.class, componentRecord.getPrimaryKey(),
            componentRecord);

        componentRecord.resetOriginalValues();
    }

    /**
     * Caches the component records in the entity cache if it is enabled.
     *
     * @param componentRecords the component records
     */
    public void cacheResult(List<ComponentRecord> componentRecords) {
        for (ComponentRecord componentRecord : componentRecords) {
            if (EntityCacheUtil.getResult(
                        ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
                        ComponentRecordImpl.class,
                        componentRecord.getPrimaryKey()) == null) {
                cacheResult(componentRecord);
            } else {
                componentRecord.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all component records.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ComponentRecordImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ComponentRecordImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the component record.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ComponentRecord componentRecord) {
        EntityCacheUtil.removeResult(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordImpl.class, componentRecord.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ComponentRecord> componentRecords) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ComponentRecord componentRecord : componentRecords) {
            EntityCacheUtil.removeResult(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
                ComponentRecordImpl.class, componentRecord.getPrimaryKey());
        }
    }

    /**
     * Creates a new component record with the primary key. Does not add the component record to the database.
     *
     * @param componentRecordId the primary key for the new component record
     * @return the new component record
     */
    public ComponentRecord create(long componentRecordId) {
        ComponentRecord componentRecord = new ComponentRecordImpl();

        componentRecord.setNew(true);
        componentRecord.setPrimaryKey(componentRecordId);

        return componentRecord;
    }

    /**
     * Removes the component record with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param componentRecordId the primary key of the component record
     * @return the component record that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord remove(long componentRecordId)
        throws NoSuchComponentRecordException, SystemException {
        return remove(Long.valueOf(componentRecordId));
    }

    /**
     * Removes the component record with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the component record
     * @return the component record that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ComponentRecord remove(Serializable primaryKey)
        throws NoSuchComponentRecordException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ComponentRecord componentRecord = (ComponentRecord) session.get(ComponentRecordImpl.class,
                    primaryKey);

            if (componentRecord == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchComponentRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(componentRecord);
        } catch (NoSuchComponentRecordException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ComponentRecord removeImpl(ComponentRecord componentRecord)
        throws SystemException {
        componentRecord = toUnwrappedModel(componentRecord);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, componentRecord);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(componentRecord);

        return componentRecord;
    }

    @Override
    public ComponentRecord updateImpl(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord,
        boolean merge) throws SystemException {
        componentRecord = toUnwrappedModel(componentRecord);

        boolean isNew = componentRecord.isNew();

        ComponentRecordModelImpl componentRecordModelImpl = (ComponentRecordModelImpl) componentRecord;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, componentRecord, merge);

            componentRecord.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ComponentRecordModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((componentRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSERECORDID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(componentRecordModelImpl.getOriginalCourseRecordId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSERECORDID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSERECORDID,
                    args);

                args = new Object[] {
                        Long.valueOf(componentRecordModelImpl.getCourseRecordId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSERECORDID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSERECORDID,
                    args);
            }

            if ((componentRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        componentRecordModelImpl.getOriginalComponentIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI,
                    args);

                args = new Object[] { componentRecordModelImpl.getComponentIri() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIRI,
                    args);
            }
        }

        EntityCacheUtil.putResult(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            ComponentRecordImpl.class, componentRecord.getPrimaryKey(),
            componentRecord);

        return componentRecord;
    }

    protected ComponentRecord toUnwrappedModel(ComponentRecord componentRecord) {
        if (componentRecord instanceof ComponentRecordImpl) {
            return componentRecord;
        }

        ComponentRecordImpl componentRecordImpl = new ComponentRecordImpl();

        componentRecordImpl.setNew(componentRecord.isNew());
        componentRecordImpl.setPrimaryKey(componentRecord.getPrimaryKey());

        componentRecordImpl.setComponentRecordId(componentRecord.getComponentRecordId());
        componentRecordImpl.setCourseRecordId(componentRecord.getCourseRecordId());
        componentRecordImpl.setComponentIri(componentRecord.getComponentIri());
        componentRecordImpl.setUpdatedDate(componentRecord.getUpdatedDate());
        componentRecordImpl.setCompletionStatus(componentRecord.getCompletionStatus());
        componentRecordImpl.setCompletionPercent(componentRecord.getCompletionPercent());

        return componentRecordImpl;
    }

    /**
     * Returns the component record with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the component record
     * @return the component record
     * @throws com.liferay.portal.NoSuchModelException if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ComponentRecord findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the component record with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentRecordException} if it could not be found.
     *
     * @param componentRecordId the primary key of the component record
     * @return the component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord findByPrimaryKey(long componentRecordId)
        throws NoSuchComponentRecordException, SystemException {
        ComponentRecord componentRecord = fetchByPrimaryKey(componentRecordId);

        if (componentRecord == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + componentRecordId);
            }

            throw new NoSuchComponentRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                componentRecordId);
        }

        return componentRecord;
    }

    /**
     * Returns the component record with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the component record
     * @return the component record, or <code>null</code> if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ComponentRecord fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the component record with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param componentRecordId the primary key of the component record
     * @return the component record, or <code>null</code> if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord fetchByPrimaryKey(long componentRecordId)
        throws SystemException {
        ComponentRecord componentRecord = (ComponentRecord) EntityCacheUtil.getResult(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
                ComponentRecordImpl.class, componentRecordId);

        if (componentRecord == _nullComponentRecord) {
            return null;
        }

        if (componentRecord == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                componentRecord = (ComponentRecord) session.get(ComponentRecordImpl.class,
                        Long.valueOf(componentRecordId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (componentRecord != null) {
                    cacheResult(componentRecord);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
                        ComponentRecordImpl.class, componentRecordId,
                        _nullComponentRecord);
                }

                closeSession(session);
            }
        }

        return componentRecord;
    }

    /**
     * Returns all the component records where courseRecordId = &#63;.
     *
     * @param courseRecordId the course record ID
     * @return the matching component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findByCourseRecordId(long courseRecordId)
        throws SystemException {
        return findByCourseRecordId(courseRecordId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the component records where courseRecordId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the course record ID
     * @param start the lower bound of the range of component records
     * @param end the upper bound of the range of component records (not inclusive)
     * @return the range of matching component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findByCourseRecordId(long courseRecordId,
        int start, int end) throws SystemException {
        return findByCourseRecordId(courseRecordId, start, end, null);
    }

    /**
     * Returns an ordered range of all the component records where courseRecordId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the course record ID
     * @param start the lower bound of the range of component records
     * @param end the upper bound of the range of component records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findByCourseRecordId(long courseRecordId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSERECORDID;
            finderArgs = new Object[] { courseRecordId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSERECORDID;
            finderArgs = new Object[] {
                    courseRecordId,
                    
                    start, end, orderByComparator
                };
        }

        List<ComponentRecord> list = (List<ComponentRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COMPONENTRECORD_WHERE);

            query.append(_FINDER_COLUMN_COURSERECORDID_COURSERECORDID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ComponentRecordModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseRecordId);

                list = (List<ComponentRecord>) QueryUtil.list(q, getDialect(),
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
     * Returns the first component record in the ordered set where courseRecordId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the course record ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord findByCourseRecordId_First(long courseRecordId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentRecordException, SystemException {
        List<ComponentRecord> list = findByCourseRecordId(courseRecordId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseRecordId=");
            msg.append(courseRecordId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last component record in the ordered set where courseRecordId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the course record ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord findByCourseRecordId_Last(long courseRecordId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentRecordException, SystemException {
        int count = countByCourseRecordId(courseRecordId);

        List<ComponentRecord> list = findByCourseRecordId(courseRecordId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseRecordId=");
            msg.append(courseRecordId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the component records before and after the current component record in the ordered set where courseRecordId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentRecordId the primary key of the current component record
     * @param courseRecordId the course record ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord[] findByCourseRecordId_PrevAndNext(
        long componentRecordId, long courseRecordId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentRecordException, SystemException {
        ComponentRecord componentRecord = findByPrimaryKey(componentRecordId);

        Session session = null;

        try {
            session = openSession();

            ComponentRecord[] array = new ComponentRecordImpl[3];

            array[0] = getByCourseRecordId_PrevAndNext(session,
                    componentRecord, courseRecordId, orderByComparator, true);

            array[1] = componentRecord;

            array[2] = getByCourseRecordId_PrevAndNext(session,
                    componentRecord, courseRecordId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ComponentRecord getByCourseRecordId_PrevAndNext(Session session,
        ComponentRecord componentRecord, long courseRecordId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPONENTRECORD_WHERE);

        query.append(_FINDER_COLUMN_COURSERECORDID_COURSERECORDID_2);

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
            query.append(ComponentRecordModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseRecordId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(componentRecord);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ComponentRecord> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the component records where componentIri = &#63;.
     *
     * @param componentIri the component iri
     * @return the matching component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findByComponentIri(String componentIri)
        throws SystemException {
        return findByComponentIri(componentIri, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the component records where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param start the lower bound of the range of component records
     * @param end the upper bound of the range of component records (not inclusive)
     * @return the range of matching component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findByComponentIri(String componentIri,
        int start, int end) throws SystemException {
        return findByComponentIri(componentIri, start, end, null);
    }

    /**
     * Returns an ordered range of all the component records where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param start the lower bound of the range of component records
     * @param end the upper bound of the range of component records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findByComponentIri(String componentIri,
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

        List<ComponentRecord> list = (List<ComponentRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COMPONENTRECORD_WHERE);

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
                query.append(ComponentRecordModelImpl.ORDER_BY_JPQL);
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

                list = (List<ComponentRecord>) QueryUtil.list(q, getDialect(),
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
     * Returns the first component record in the ordered set where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord findByComponentIri_First(String componentIri,
        OrderByComparator orderByComparator)
        throws NoSuchComponentRecordException, SystemException {
        List<ComponentRecord> list = findByComponentIri(componentIri, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentIri=");
            msg.append(componentIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last component record in the ordered set where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentIri the component iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord findByComponentIri_Last(String componentIri,
        OrderByComparator orderByComparator)
        throws NoSuchComponentRecordException, SystemException {
        int count = countByComponentIri(componentIri);

        List<ComponentRecord> list = findByComponentIri(componentIri,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentIri=");
            msg.append(componentIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the component records before and after the current component record in the ordered set where componentIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentRecordId the primary key of the current component record
     * @param componentIri the component iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next component record
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ComponentRecord[] findByComponentIri_PrevAndNext(
        long componentRecordId, String componentIri,
        OrderByComparator orderByComparator)
        throws NoSuchComponentRecordException, SystemException {
        ComponentRecord componentRecord = findByPrimaryKey(componentRecordId);

        Session session = null;

        try {
            session = openSession();

            ComponentRecord[] array = new ComponentRecordImpl[3];

            array[0] = getByComponentIri_PrevAndNext(session, componentRecord,
                    componentIri, orderByComparator, true);

            array[1] = componentRecord;

            array[2] = getByComponentIri_PrevAndNext(session, componentRecord,
                    componentIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ComponentRecord getByComponentIri_PrevAndNext(Session session,
        ComponentRecord componentRecord, String componentIri,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPONENTRECORD_WHERE);

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
            query.append(ComponentRecordModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(componentRecord);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ComponentRecord> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the component records.
     *
     * @return the component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the component records.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of component records
     * @param end the upper bound of the range of component records (not inclusive)
     * @return the range of component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the component records.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of component records
     * @param end the upper bound of the range of component records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of component records
     * @throws SystemException if a system exception occurred
     */
    public List<ComponentRecord> findAll(int start, int end,
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

        List<ComponentRecord> list = (List<ComponentRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPONENTRECORD);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPONENTRECORD.concat(ComponentRecordModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ComponentRecord>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ComponentRecord>) QueryUtil.list(q,
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
     * Removes all the component records where courseRecordId = &#63; from the database.
     *
     * @param courseRecordId the course record ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseRecordId(long courseRecordId)
        throws SystemException {
        for (ComponentRecord componentRecord : findByCourseRecordId(
                courseRecordId)) {
            remove(componentRecord);
        }
    }

    /**
     * Removes all the component records where componentIri = &#63; from the database.
     *
     * @param componentIri the component iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentIri(String componentIri)
        throws SystemException {
        for (ComponentRecord componentRecord : findByComponentIri(componentIri)) {
            remove(componentRecord);
        }
    }

    /**
     * Removes all the component records from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ComponentRecord componentRecord : findAll()) {
            remove(componentRecord);
        }
    }

    /**
     * Returns the number of component records where courseRecordId = &#63;.
     *
     * @param courseRecordId the course record ID
     * @return the number of matching component records
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseRecordId(long courseRecordId)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseRecordId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSERECORDID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPONENTRECORD_WHERE);

            query.append(_FINDER_COLUMN_COURSERECORDID_COURSERECORDID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseRecordId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSERECORDID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of component records where componentIri = &#63;.
     *
     * @param componentIri the component iri
     * @return the number of matching component records
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentIri(String componentIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { componentIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPONENTRECORD_WHERE);

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
     * Returns the number of component records.
     *
     * @return the number of component records
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COMPONENTRECORD);

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
     * Initializes the component record persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.ComponentRecord")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ComponentRecord>> listenersList = new ArrayList<ModelListener<ComponentRecord>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ComponentRecord>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ComponentRecordImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
