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

import org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.model.impl.CourseRecordImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseRecordModelImpl;
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
 * The persistence implementation for the course record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecordPersistence
 * @see CourseRecordUtil
 * @generated
 */
public class CourseRecordPersistenceImpl extends BasePersistenceImpl<CourseRecord>
    implements CourseRecordPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CourseRecordUtil} to access the course record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CourseRecordImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_COURSERECORDIRI = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByCourseRecordIri",
            new String[] { String.class.getName() },
            CourseRecordModelImpl.COURSERECORDIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSERECORDIRI = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseRecordIri", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIRI =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseIri",
            new String[] { String.class.getName() },
            CourseRecordModelImpl.COURSEIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIRI = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SINGLESIGNONVALUE =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySingleSignOnValue",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SINGLESIGNONVALUE =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBySingleSignOnValue", new String[] { String.class.getName() },
            CourseRecordModelImpl.SINGLESIGNONVALUE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SINGLESIGNONVALUE = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySingleSignOnValue", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            CourseRecordModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFeedReferenceId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFeedReferenceId",
            new String[] { Long.class.getName() },
            CourseRecordModelImpl.FEEDREFERENCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDREFERENCEID = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByFeedReferenceId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, CourseRecordImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_COMPONENTRECORDS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ComponentRecordPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getComponentRecords",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COMPONENTRECORDS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ComponentRecordPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getComponentRecordsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COMPONENTRECORD = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ComponentRecordPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsComponentRecord",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_SELECT_COURSERECORD = "SELECT courseRecord FROM CourseRecord courseRecord";
    private static final String _SQL_SELECT_COURSERECORD_WHERE = "SELECT courseRecord FROM CourseRecord courseRecord WHERE ";
    private static final String _SQL_COUNT_COURSERECORD = "SELECT COUNT(courseRecord) FROM CourseRecord courseRecord";
    private static final String _SQL_COUNT_COURSERECORD_WHERE = "SELECT COUNT(courseRecord) FROM CourseRecord courseRecord WHERE ";
    private static final String _SQL_GETCOMPONENTRECORDS = "SELECT {CATALOG_ComponentRecord.*} FROM CATALOG_ComponentRecord INNER JOIN CATALOG_CourseRecord ON (CATALOG_CourseRecord.courseRecordId = CATALOG_ComponentRecord.courseRecordId) WHERE (CATALOG_CourseRecord.courseRecordId = ?)";
    private static final String _SQL_GETCOMPONENTRECORDSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ComponentRecord WHERE courseRecordId = ?";
    private static final String _SQL_CONTAINSCOMPONENTRECORD = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ComponentRecord WHERE courseRecordId = ? AND componentRecordId = ?";
    private static final String _FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_1 =
        "courseRecord.courseRecordIri IS NULL";
    private static final String _FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_2 =
        "courseRecord.courseRecordIri = ?";
    private static final String _FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_3 =
        "(courseRecord.courseRecordIri IS NULL OR courseRecord.courseRecordIri = ?)";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_1 = "courseRecord.courseIri IS NULL";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_2 = "courseRecord.courseIri = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_3 = "(courseRecord.courseIri IS NULL OR courseRecord.courseIri = ?)";
    private static final String _FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_1 =
        "courseRecord.singleSignOnValue IS NULL";
    private static final String _FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_2 =
        "courseRecord.singleSignOnValue = ?";
    private static final String _FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_3 =
        "(courseRecord.singleSignOnValue IS NULL OR courseRecord.singleSignOnValue = ?)";
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "courseRecord.userId = ?";
    private static final String _FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2 =
        "courseRecord.feedReferenceId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "courseRecord.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CourseRecord exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CourseRecord exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CourseRecordPersistenceImpl.class);
    private static CourseRecord _nullCourseRecord = new CourseRecordImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CourseRecord> toCacheModel() {
                return _nullCourseRecordCacheModel;
            }
        };

    private static CacheModel<CourseRecord> _nullCourseRecordCacheModel = new CacheModel<CourseRecord>() {
            public CourseRecord toEntityModel() {
                return _nullCourseRecord;
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
    protected ContainsComponentRecord containsComponentRecord;

    /**
     * Caches the course record in the entity cache if it is enabled.
     *
     * @param courseRecord the course record
     */
    public void cacheResult(CourseRecord courseRecord) {
        EntityCacheUtil.putResult(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordImpl.class, courseRecord.getPrimaryKey(), courseRecord);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
            new Object[] { courseRecord.getCourseRecordIri() }, courseRecord);

        courseRecord.resetOriginalValues();
    }

    /**
     * Caches the course records in the entity cache if it is enabled.
     *
     * @param courseRecords the course records
     */
    public void cacheResult(List<CourseRecord> courseRecords) {
        for (CourseRecord courseRecord : courseRecords) {
            if (EntityCacheUtil.getResult(
                        CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
                        CourseRecordImpl.class, courseRecord.getPrimaryKey()) == null) {
                cacheResult(courseRecord);
            } else {
                courseRecord.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all course records.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CourseRecordImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CourseRecordImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the course record.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CourseRecord courseRecord) {
        EntityCacheUtil.removeResult(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordImpl.class, courseRecord.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(courseRecord);
    }

    @Override
    public void clearCache(List<CourseRecord> courseRecords) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CourseRecord courseRecord : courseRecords) {
            EntityCacheUtil.removeResult(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
                CourseRecordImpl.class, courseRecord.getPrimaryKey());

            clearUniqueFindersCache(courseRecord);
        }
    }

    protected void clearUniqueFindersCache(CourseRecord courseRecord) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
            new Object[] { courseRecord.getCourseRecordIri() });
    }

    /**
     * Creates a new course record with the primary key. Does not add the course record to the database.
     *
     * @param courseRecordId the primary key for the new course record
     * @return the new course record
     */
    public CourseRecord create(long courseRecordId) {
        CourseRecord courseRecord = new CourseRecordImpl();

        courseRecord.setNew(true);
        courseRecord.setPrimaryKey(courseRecordId);

        return courseRecord;
    }

    /**
     * Removes the course record with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseRecordId the primary key of the course record
     * @return the course record that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord remove(long courseRecordId)
        throws NoSuchCourseRecordException, SystemException {
        return remove(Long.valueOf(courseRecordId));
    }

    /**
     * Removes the course record with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the course record
     * @return the course record that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRecord remove(Serializable primaryKey)
        throws NoSuchCourseRecordException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CourseRecord courseRecord = (CourseRecord) session.get(CourseRecordImpl.class,
                    primaryKey);

            if (courseRecord == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourseRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(courseRecord);
        } catch (NoSuchCourseRecordException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CourseRecord removeImpl(CourseRecord courseRecord)
        throws SystemException {
        courseRecord = toUnwrappedModel(courseRecord);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, courseRecord);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(courseRecord);

        return courseRecord;
    }

    @Override
    public CourseRecord updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord,
        boolean merge) throws SystemException {
        courseRecord = toUnwrappedModel(courseRecord);

        boolean isNew = courseRecord.isNew();

        CourseRecordModelImpl courseRecordModelImpl = (CourseRecordModelImpl) courseRecord;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, courseRecord, merge);

            courseRecord.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CourseRecordModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courseRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courseRecordModelImpl.getOriginalCourseIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI,
                    args);

                args = new Object[] { courseRecordModelImpl.getCourseIri() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIRI,
                    args);
            }

            if ((courseRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SINGLESIGNONVALUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courseRecordModelImpl.getOriginalSingleSignOnValue()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SINGLESIGNONVALUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SINGLESIGNONVALUE,
                    args);

                args = new Object[] { courseRecordModelImpl.getSingleSignOnValue() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SINGLESIGNONVALUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SINGLESIGNONVALUE,
                    args);
            }

            if ((courseRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseRecordModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseRecordModelImpl.getUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }

            if ((courseRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseRecordModelImpl.getOriginalFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseRecordModelImpl.getFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
            CourseRecordImpl.class, courseRecord.getPrimaryKey(), courseRecord);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                new Object[] { courseRecord.getCourseRecordIri() }, courseRecord);
        } else {
            if ((courseRecordModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COURSERECORDIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courseRecordModelImpl.getOriginalCourseRecordIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSERECORDIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                    new Object[] { courseRecord.getCourseRecordIri() },
                    courseRecord);
            }
        }

        return courseRecord;
    }

    protected CourseRecord toUnwrappedModel(CourseRecord courseRecord) {
        if (courseRecord instanceof CourseRecordImpl) {
            return courseRecord;
        }

        CourseRecordImpl courseRecordImpl = new CourseRecordImpl();

        courseRecordImpl.setNew(courseRecord.isNew());
        courseRecordImpl.setPrimaryKey(courseRecord.getPrimaryKey());

        courseRecordImpl.setCourseRecordId(courseRecord.getCourseRecordId());
        courseRecordImpl.setFeedReferenceId(courseRecord.getFeedReferenceId());
        courseRecordImpl.setCourseRecordIri(courseRecord.getCourseRecordIri());
        courseRecordImpl.setUserId(courseRecord.getUserId());
        courseRecordImpl.setSingleSignOnValue(courseRecord.getSingleSignOnValue());
        courseRecordImpl.setCourseIri(courseRecord.getCourseIri());
        courseRecordImpl.setUpdatedDate(courseRecord.getUpdatedDate());
        courseRecordImpl.setCompletionStatus(courseRecord.getCompletionStatus());
        courseRecordImpl.setRemoved(courseRecord.isRemoved());
        courseRecordImpl.setRemovedDate(courseRecord.getRemovedDate());
        courseRecordImpl.setUserHidden(courseRecord.isUserHidden());
        courseRecordImpl.setAssigned(courseRecord.isAssigned());

        return courseRecordImpl;
    }

    /**
     * Returns the course record with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the course record
     * @return the course record
     * @throws com.liferay.portal.NoSuchModelException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRecord findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course record with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRecordException} if it could not be found.
     *
     * @param courseRecordId the primary key of the course record
     * @return the course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByPrimaryKey(long courseRecordId)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = fetchByPrimaryKey(courseRecordId);

        if (courseRecord == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + courseRecordId);
            }

            throw new NoSuchCourseRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                courseRecordId);
        }

        return courseRecord;
    }

    /**
     * Returns the course record with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the course record
     * @return the course record, or <code>null</code> if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseRecord fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course record with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param courseRecordId the primary key of the course record
     * @return the course record, or <code>null</code> if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord fetchByPrimaryKey(long courseRecordId)
        throws SystemException {
        CourseRecord courseRecord = (CourseRecord) EntityCacheUtil.getResult(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
                CourseRecordImpl.class, courseRecordId);

        if (courseRecord == _nullCourseRecord) {
            return null;
        }

        if (courseRecord == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                courseRecord = (CourseRecord) session.get(CourseRecordImpl.class,
                        Long.valueOf(courseRecordId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (courseRecord != null) {
                    cacheResult(courseRecord);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(CourseRecordModelImpl.ENTITY_CACHE_ENABLED,
                        CourseRecordImpl.class, courseRecordId,
                        _nullCourseRecord);
                }

                closeSession(session);
            }
        }

        return courseRecord;
    }

    /**
     * Returns the course record where courseRecordIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRecordException} if it could not be found.
     *
     * @param courseRecordIri the course record iri
     * @return the matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByCourseRecordIri(String courseRecordIri)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = fetchByCourseRecordIri(courseRecordIri);

        if (courseRecord == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseRecordIri=");
            msg.append(courseRecordIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchCourseRecordException(msg.toString());
        }

        return courseRecord;
    }

    /**
     * Returns the course record where courseRecordIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param courseRecordIri the course record iri
     * @return the matching course record, or <code>null</code> if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord fetchByCourseRecordIri(String courseRecordIri)
        throws SystemException {
        return fetchByCourseRecordIri(courseRecordIri, true);
    }

    /**
     * Returns the course record where courseRecordIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param courseRecordIri the course record iri
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching course record, or <code>null</code> if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord fetchByCourseRecordIri(String courseRecordIri,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { courseRecordIri };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_COURSERECORD_WHERE);

            if (courseRecordIri == null) {
                query.append(_FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_1);
            } else {
                if (courseRecordIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_2);
                }
            }

            query.append(CourseRecordModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseRecordIri != null) {
                    qPos.add(courseRecordIri);
                }

                List<CourseRecord> list = q.list();

                result = list;

                CourseRecord courseRecord = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                        finderArgs, list);
                } else {
                    courseRecord = list.get(0);

                    cacheResult(courseRecord);

                    if ((courseRecord.getCourseRecordIri() == null) ||
                            !courseRecord.getCourseRecordIri()
                                             .equals(courseRecordIri)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                            finderArgs, courseRecord);
                    }
                }

                return courseRecord;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSERECORDIRI,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (CourseRecord) result;
            }
        }
    }

    /**
     * Returns all the course records where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByCourseIri(String courseIri)
        throws SystemException {
        return findByCourseIri(courseIri, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the course records where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @return the range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByCourseIri(String courseIri, int start,
        int end) throws SystemException {
        return findByCourseIri(courseIri, start, end, null);
    }

    /**
     * Returns an ordered range of all the course records where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByCourseIri(String courseIri, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<CourseRecord> list = (List<CourseRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSERECORD_WHERE);

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
                query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
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

                list = (List<CourseRecord>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course record in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByCourseIri_First(String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        List<CourseRecord> list = findByCourseIri(courseIri, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course record in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByCourseIri_Last(String courseIri,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        int count = countByCourseIri(courseIri);

        List<CourseRecord> list = findByCourseIri(courseIri, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course records before and after the current course record in the ordered set where courseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the primary key of the current course record
     * @param courseIri the course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord[] findByCourseIri_PrevAndNext(long courseRecordId,
        String courseIri, OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = findByPrimaryKey(courseRecordId);

        Session session = null;

        try {
            session = openSession();

            CourseRecord[] array = new CourseRecordImpl[3];

            array[0] = getByCourseIri_PrevAndNext(session, courseRecord,
                    courseIri, orderByComparator, true);

            array[1] = courseRecord;

            array[2] = getByCourseIri_PrevAndNext(session, courseRecord,
                    courseIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRecord getByCourseIri_PrevAndNext(Session session,
        CourseRecord courseRecord, String courseIri,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSERECORD_WHERE);

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
            query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(courseRecord);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRecord> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course records where singleSignOnValue = &#63;.
     *
     * @param singleSignOnValue the single sign on value
     * @return the matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findBySingleSignOnValue(String singleSignOnValue)
        throws SystemException {
        return findBySingleSignOnValue(singleSignOnValue, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course records where singleSignOnValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param singleSignOnValue the single sign on value
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @return the range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findBySingleSignOnValue(
        String singleSignOnValue, int start, int end) throws SystemException {
        return findBySingleSignOnValue(singleSignOnValue, start, end, null);
    }

    /**
     * Returns an ordered range of all the course records where singleSignOnValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param singleSignOnValue the single sign on value
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findBySingleSignOnValue(
        String singleSignOnValue, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SINGLESIGNONVALUE;
            finderArgs = new Object[] { singleSignOnValue };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SINGLESIGNONVALUE;
            finderArgs = new Object[] {
                    singleSignOnValue,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseRecord> list = (List<CourseRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSERECORD_WHERE);

            if (singleSignOnValue == null) {
                query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_1);
            } else {
                if (singleSignOnValue.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_3);
                } else {
                    query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (singleSignOnValue != null) {
                    qPos.add(singleSignOnValue);
                }

                list = (List<CourseRecord>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course record in the ordered set where singleSignOnValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param singleSignOnValue the single sign on value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findBySingleSignOnValue_First(
        String singleSignOnValue, OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        List<CourseRecord> list = findBySingleSignOnValue(singleSignOnValue, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("singleSignOnValue=");
            msg.append(singleSignOnValue);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course record in the ordered set where singleSignOnValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param singleSignOnValue the single sign on value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findBySingleSignOnValue_Last(String singleSignOnValue,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        int count = countBySingleSignOnValue(singleSignOnValue);

        List<CourseRecord> list = findBySingleSignOnValue(singleSignOnValue,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("singleSignOnValue=");
            msg.append(singleSignOnValue);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course records before and after the current course record in the ordered set where singleSignOnValue = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the primary key of the current course record
     * @param singleSignOnValue the single sign on value
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord[] findBySingleSignOnValue_PrevAndNext(
        long courseRecordId, String singleSignOnValue,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = findByPrimaryKey(courseRecordId);

        Session session = null;

        try {
            session = openSession();

            CourseRecord[] array = new CourseRecordImpl[3];

            array[0] = getBySingleSignOnValue_PrevAndNext(session,
                    courseRecord, singleSignOnValue, orderByComparator, true);

            array[1] = courseRecord;

            array[2] = getBySingleSignOnValue_PrevAndNext(session,
                    courseRecord, singleSignOnValue, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRecord getBySingleSignOnValue_PrevAndNext(Session session,
        CourseRecord courseRecord, String singleSignOnValue,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSERECORD_WHERE);

        if (singleSignOnValue == null) {
            query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_1);
        } else {
            if (singleSignOnValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_3);
            } else {
                query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_2);
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
            query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (singleSignOnValue != null) {
            qPos.add(singleSignOnValue);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseRecord);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRecord> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course records where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByUserId(long userId)
        throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course records where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @return the range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course records where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByUserId(long userId, int start, int end,
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

        List<CourseRecord> list = (List<CourseRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSERECORD_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                list = (List<CourseRecord>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course record in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        List<CourseRecord> list = findByUserId(userId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course record in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        int count = countByUserId(userId);

        List<CourseRecord> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course records before and after the current course record in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the primary key of the current course record
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord[] findByUserId_PrevAndNext(long courseRecordId,
        long userId, OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = findByPrimaryKey(courseRecordId);

        Session session = null;

        try {
            session = openSession();

            CourseRecord[] array = new CourseRecordImpl[3];

            array[0] = getByUserId_PrevAndNext(session, courseRecord, userId,
                    orderByComparator, true);

            array[1] = courseRecord;

            array[2] = getByUserId_PrevAndNext(session, courseRecord, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRecord getByUserId_PrevAndNext(Session session,
        CourseRecord courseRecord, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSERECORD_WHERE);

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
            query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseRecord);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRecord> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course records where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        return findByFeedReferenceId(feedReferenceId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course records where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @return the range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByFeedReferenceId(long feedReferenceId,
        int start, int end) throws SystemException {
        return findByFeedReferenceId(feedReferenceId, start, end, null);
    }

    /**
     * Returns an ordered range of all the course records where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findByFeedReferenceId(long feedReferenceId,
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

        List<CourseRecord> list = (List<CourseRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSERECORD_WHERE);

            query.append(_FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(feedReferenceId);

                list = (List<CourseRecord>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course record in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByFeedReferenceId_First(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        List<CourseRecord> list = findByFeedReferenceId(feedReferenceId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course record in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a matching course record could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord findByFeedReferenceId_Last(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        int count = countByFeedReferenceId(feedReferenceId);

        List<CourseRecord> list = findByFeedReferenceId(feedReferenceId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseRecordException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course records before and after the current course record in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseRecordId the primary key of the current course record
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course record
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRecordException if a course record with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseRecord[] findByFeedReferenceId_PrevAndNext(
        long courseRecordId, long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = findByPrimaryKey(courseRecordId);

        Session session = null;

        try {
            session = openSession();

            CourseRecord[] array = new CourseRecordImpl[3];

            array[0] = getByFeedReferenceId_PrevAndNext(session, courseRecord,
                    feedReferenceId, orderByComparator, true);

            array[1] = courseRecord;

            array[2] = getByFeedReferenceId_PrevAndNext(session, courseRecord,
                    feedReferenceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseRecord getByFeedReferenceId_PrevAndNext(Session session,
        CourseRecord courseRecord, long feedReferenceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSERECORD_WHERE);

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
            query.append(CourseRecordModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(feedReferenceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseRecord);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseRecord> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course records.
     *
     * @return the course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course records.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @return the range of course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the course records.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course records
     * @throws SystemException if a system exception occurred
     */
    public List<CourseRecord> findAll(int start, int end,
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

        List<CourseRecord> list = (List<CourseRecord>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSERECORD);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSERECORD.concat(CourseRecordModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<CourseRecord>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<CourseRecord>) QueryUtil.list(q, getDialect(),
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
     * Removes the course record where courseRecordIri = &#63; from the database.
     *
     * @param courseRecordIri the course record iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseRecordIri(String courseRecordIri)
        throws NoSuchCourseRecordException, SystemException {
        CourseRecord courseRecord = findByCourseRecordIri(courseRecordIri);

        remove(courseRecord);
    }

    /**
     * Removes all the course records where courseIri = &#63; from the database.
     *
     * @param courseIri the course iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIri(String courseIri) throws SystemException {
        for (CourseRecord courseRecord : findByCourseIri(courseIri)) {
            remove(courseRecord);
        }
    }

    /**
     * Removes all the course records where singleSignOnValue = &#63; from the database.
     *
     * @param singleSignOnValue the single sign on value
     * @throws SystemException if a system exception occurred
     */
    public void removeBySingleSignOnValue(String singleSignOnValue)
        throws SystemException {
        for (CourseRecord courseRecord : findBySingleSignOnValue(
                singleSignOnValue)) {
            remove(courseRecord);
        }
    }

    /**
     * Removes all the course records where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUserId(long userId) throws SystemException {
        for (CourseRecord courseRecord : findByUserId(userId)) {
            remove(courseRecord);
        }
    }

    /**
     * Removes all the course records where feedReferenceId = &#63; from the database.
     *
     * @param feedReferenceId the feed reference ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        for (CourseRecord courseRecord : findByFeedReferenceId(feedReferenceId)) {
            remove(courseRecord);
        }
    }

    /**
     * Removes all the course records from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (CourseRecord courseRecord : findAll()) {
            remove(courseRecord);
        }
    }

    /**
     * Returns the number of course records where courseRecordIri = &#63;.
     *
     * @param courseRecordIri the course record iri
     * @return the number of matching course records
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseRecordIri(String courseRecordIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseRecordIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSERECORDIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSERECORD_WHERE);

            if (courseRecordIri == null) {
                query.append(_FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_1);
            } else {
                if (courseRecordIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSERECORDIRI_COURSERECORDIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseRecordIri != null) {
                    qPos.add(courseRecordIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSERECORDIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course records where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the number of matching course records
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIri(String courseIri) throws SystemException {
        Object[] finderArgs = new Object[] { courseIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSERECORD_WHERE);

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
     * Returns the number of course records where singleSignOnValue = &#63;.
     *
     * @param singleSignOnValue the single sign on value
     * @return the number of matching course records
     * @throws SystemException if a system exception occurred
     */
    public int countBySingleSignOnValue(String singleSignOnValue)
        throws SystemException {
        Object[] finderArgs = new Object[] { singleSignOnValue };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SINGLESIGNONVALUE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSERECORD_WHERE);

            if (singleSignOnValue == null) {
                query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_1);
            } else {
                if (singleSignOnValue.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_3);
                } else {
                    query.append(_FINDER_COLUMN_SINGLESIGNONVALUE_SINGLESIGNONVALUE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (singleSignOnValue != null) {
                    qPos.add(singleSignOnValue);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SINGLESIGNONVALUE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course records where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching course records
     * @throws SystemException if a system exception occurred
     */
    public int countByUserId(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSERECORD_WHERE);

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
     * Returns the number of course records where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the number of matching course records
     * @throws SystemException if a system exception occurred
     */
    public int countByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        Object[] finderArgs = new Object[] { feedReferenceId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSERECORD_WHERE);

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
     * Returns the number of course records.
     *
     * @return the number of course records
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSERECORD);

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
     * Returns all the component records associated with the course record.
     *
     * @param pk the primary key of the course record
     * @return the component records associated with the course record
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk) throws SystemException {
        return getComponentRecords(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the component records associated with the course record.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course record
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @return the range of component records associated with the course record
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end) throws SystemException {
        return getComponentRecords(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the component records associated with the course record.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course record
     * @param start the lower bound of the range of course records
     * @param end the upper bound of the range of course records (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of component records associated with the course record
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.ComponentRecord> list = (List<org.nterlearning.datamodel.catalog.model.ComponentRecord>) FinderCacheUtil.getResult(FINDER_PATH_GET_COMPONENTRECORDS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOMPONENTRECORDS.concat(ORDER_BY_CLAUSE)
                                                  .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOMPONENTRECORDS.concat(org.nterlearning.datamodel.catalog.model.impl.ComponentRecordModelImpl.ORDER_BY_SQL);
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_ComponentRecord",
                    org.nterlearning.datamodel.catalog.model.impl.ComponentRecordImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.ComponentRecord>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COMPONENTRECORDS,
                        finderArgs);
                } else {
                    componentRecordPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COMPONENTRECORDS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of component records associated with the course record.
     *
     * @param pk the primary key of the course record
     * @return the number of component records associated with the course record
     * @throws SystemException if a system exception occurred
     */
    public int getComponentRecordsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COMPONENTRECORDS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOMPONENTRECORDSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_COMPONENTRECORDS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the component record is associated with the course record.
     *
     * @param pk the primary key of the course record
     * @param componentRecordPK the primary key of the component record
     * @return <code>true</code> if the component record is associated with the course record; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsComponentRecord(long pk, long componentRecordPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, componentRecordPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COMPONENTRECORD,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsComponentRecord.contains(pk,
                            componentRecordPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COMPONENTRECORD,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the course record has any component records associated with it.
     *
     * @param pk the primary key of the course record to check for associations with component records
     * @return <code>true</code> if the course record has any component records associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsComponentRecords(long pk) throws SystemException {
        if (getComponentRecordsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the course record persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.CourseRecord")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CourseRecord>> listenersList = new ArrayList<ModelListener<CourseRecord>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CourseRecord>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsComponentRecord = new ContainsComponentRecord();
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CourseRecordImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    protected class ContainsComponentRecord {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsComponentRecord() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOMPONENTRECORD,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseRecordId, long componentRecordId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseRecordId), new Long(componentRecordId)
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
