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

import org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;
import org.nterlearning.datamodel.catalog.model.ExternalLink;
import org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl;
import org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl;
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
 * The persistence implementation for the external link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkPersistence
 * @see ExternalLinkUtil
 * @generated
 */
public class ExternalLinkPersistenceImpl extends BasePersistenceImpl<ExternalLink>
    implements ExternalLinkPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ExternalLinkUtil} to access the external link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ExternalLinkImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
            new String[] { Long.class.getName() },
            ExternalLinkModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHTYPE =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIdWithType",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHTYPE =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCourseIdWithType",
            new String[] { Long.class.getName(), String.class.getName() },
            ExternalLinkModelImpl.COURSEID_COLUMN_BITMASK |
            ExternalLinkModelImpl.LINKTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIDWITHTYPE = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseIdWithType",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTID =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByComponentId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByComponentId",
            new String[] { Long.class.getName() },
            ExternalLinkModelImpl.COMPONENTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTID = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByComponentId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByComponentIdWithType",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE =
        new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByComponentIdWithType",
            new String[] { Long.class.getName(), String.class.getName() },
            ExternalLinkModelImpl.COMPONENTID_COLUMN_BITMASK |
            ExternalLinkModelImpl.LINKTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTIDWITHTYPE = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByComponentIdWithType",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, ExternalLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_EXTERNALLINK = "SELECT externalLink FROM ExternalLink externalLink";
    private static final String _SQL_SELECT_EXTERNALLINK_WHERE = "SELECT externalLink FROM ExternalLink externalLink WHERE ";
    private static final String _SQL_COUNT_EXTERNALLINK = "SELECT COUNT(externalLink) FROM ExternalLink externalLink";
    private static final String _SQL_COUNT_EXTERNALLINK_WHERE = "SELECT COUNT(externalLink) FROM ExternalLink externalLink WHERE ";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "externalLink.courseId = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHTYPE_COURSEID_2 = "externalLink.courseId = ? AND ";
    private static final String _FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_1 = "externalLink.linkType IS NULL";
    private static final String _FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_2 = "externalLink.linkType = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_3 = "(externalLink.linkType IS NULL OR externalLink.linkType = ?)";
    private static final String _FINDER_COLUMN_COMPONENTID_COMPONENTID_2 = "externalLink.componentId = ?";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHTYPE_COMPONENTID_2 =
        "externalLink.componentId = ? AND ";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_1 = "externalLink.linkType IS NULL";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_2 = "externalLink.linkType = ?";
    private static final String _FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_3 = "(externalLink.linkType IS NULL OR externalLink.linkType = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "externalLink.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExternalLink exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExternalLink exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ExternalLinkPersistenceImpl.class);
    private static ExternalLink _nullExternalLink = new ExternalLinkImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ExternalLink> toCacheModel() {
                return _nullExternalLinkCacheModel;
            }
        };

    private static CacheModel<ExternalLink> _nullExternalLinkCacheModel = new CacheModel<ExternalLink>() {
            public ExternalLink toEntityModel() {
                return _nullExternalLink;
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
     * Caches the external link in the entity cache if it is enabled.
     *
     * @param externalLink the external link
     */
    public void cacheResult(ExternalLink externalLink) {
        EntityCacheUtil.putResult(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkImpl.class, externalLink.getPrimaryKey(), externalLink);

        externalLink.resetOriginalValues();
    }

    /**
     * Caches the external links in the entity cache if it is enabled.
     *
     * @param externalLinks the external links
     */
    public void cacheResult(List<ExternalLink> externalLinks) {
        for (ExternalLink externalLink : externalLinks) {
            if (EntityCacheUtil.getResult(
                        ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
                        ExternalLinkImpl.class, externalLink.getPrimaryKey()) == null) {
                cacheResult(externalLink);
            } else {
                externalLink.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all external links.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ExternalLinkImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ExternalLinkImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the external link.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ExternalLink externalLink) {
        EntityCacheUtil.removeResult(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkImpl.class, externalLink.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ExternalLink> externalLinks) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ExternalLink externalLink : externalLinks) {
            EntityCacheUtil.removeResult(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
                ExternalLinkImpl.class, externalLink.getPrimaryKey());
        }
    }

    /**
     * Creates a new external link with the primary key. Does not add the external link to the database.
     *
     * @param linkId the primary key for the new external link
     * @return the new external link
     */
    public ExternalLink create(long linkId) {
        ExternalLink externalLink = new ExternalLinkImpl();

        externalLink.setNew(true);
        externalLink.setPrimaryKey(linkId);

        return externalLink;
    }

    /**
     * Removes the external link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param linkId the primary key of the external link
     * @return the external link that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink remove(long linkId)
        throws NoSuchExternalLinkException, SystemException {
        return remove(Long.valueOf(linkId));
    }

    /**
     * Removes the external link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the external link
     * @return the external link that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ExternalLink remove(Serializable primaryKey)
        throws NoSuchExternalLinkException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ExternalLink externalLink = (ExternalLink) session.get(ExternalLinkImpl.class,
                    primaryKey);

            if (externalLink == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchExternalLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(externalLink);
        } catch (NoSuchExternalLinkException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ExternalLink removeImpl(ExternalLink externalLink)
        throws SystemException {
        externalLink = toUnwrappedModel(externalLink);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, externalLink);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(externalLink);

        return externalLink;
    }

    @Override
    public ExternalLink updateImpl(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink,
        boolean merge) throws SystemException {
        externalLink = toUnwrappedModel(externalLink);

        boolean isNew = externalLink.isNew();

        ExternalLinkModelImpl externalLinkModelImpl = (ExternalLinkModelImpl) externalLink;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, externalLink, merge);

            externalLink.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ExternalLinkModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((externalLinkModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getOriginalCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);

                args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);
            }

            if ((externalLinkModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getOriginalCourseId()),
                        
                        externalLinkModelImpl.getOriginalLinkType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHTYPE,
                    args);

                args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getCourseId()),
                        
                        externalLinkModelImpl.getLinkType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHTYPE,
                    args);
            }

            if ((externalLinkModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getOriginalComponentId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID,
                    args);

                args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getComponentId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTID,
                    args);
            }

            if ((externalLinkModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getOriginalComponentId()),
                        
                        externalLinkModelImpl.getOriginalLinkType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE,
                    args);

                args = new Object[] {
                        Long.valueOf(externalLinkModelImpl.getComponentId()),
                        
                        externalLinkModelImpl.getLinkType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            ExternalLinkImpl.class, externalLink.getPrimaryKey(), externalLink);

        return externalLink;
    }

    protected ExternalLink toUnwrappedModel(ExternalLink externalLink) {
        if (externalLink instanceof ExternalLinkImpl) {
            return externalLink;
        }

        ExternalLinkImpl externalLinkImpl = new ExternalLinkImpl();

        externalLinkImpl.setNew(externalLink.isNew());
        externalLinkImpl.setPrimaryKey(externalLink.getPrimaryKey());

        externalLinkImpl.setLinkId(externalLink.getLinkId());
        externalLinkImpl.setCourseId(externalLink.getCourseId());
        externalLinkImpl.setComponentId(externalLink.getComponentId());
        externalLinkImpl.setLinkType(externalLink.getLinkType());
        externalLinkImpl.setLinkUrl(externalLink.getLinkUrl());

        return externalLinkImpl;
    }

    /**
     * Returns the external link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the external link
     * @return the external link
     * @throws com.liferay.portal.NoSuchModelException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ExternalLink findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the external link with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchExternalLinkException} if it could not be found.
     *
     * @param linkId the primary key of the external link
     * @return the external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByPrimaryKey(long linkId)
        throws NoSuchExternalLinkException, SystemException {
        ExternalLink externalLink = fetchByPrimaryKey(linkId);

        if (externalLink == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + linkId);
            }

            throw new NoSuchExternalLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                linkId);
        }

        return externalLink;
    }

    /**
     * Returns the external link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the external link
     * @return the external link, or <code>null</code> if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ExternalLink fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the external link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param linkId the primary key of the external link
     * @return the external link, or <code>null</code> if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink fetchByPrimaryKey(long linkId)
        throws SystemException {
        ExternalLink externalLink = (ExternalLink) EntityCacheUtil.getResult(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
                ExternalLinkImpl.class, linkId);

        if (externalLink == _nullExternalLink) {
            return null;
        }

        if (externalLink == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                externalLink = (ExternalLink) session.get(ExternalLinkImpl.class,
                        Long.valueOf(linkId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (externalLink != null) {
                    cacheResult(externalLink);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
                        ExternalLinkImpl.class, linkId, _nullExternalLink);
                }

                closeSession(session);
            }
        }

        return externalLink;
    }

    /**
     * Returns all the external links where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByCourseId(long courseId)
        throws SystemException {
        return findByCourseId(courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the external links where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @return the range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByCourseId(long courseId, int start, int end)
        throws SystemException {
        return findByCourseId(courseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the external links where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByCourseId(long courseId, int start, int end,
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

        List<ExternalLink> list = (List<ExternalLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

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

                list = (List<ExternalLink>) QueryUtil.list(q, getDialect(),
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
     * Returns the first external link in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByCourseId_First(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        List<ExternalLink> list = findByCourseId(courseId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last external link in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByCourseId_Last(long courseId,
        OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        int count = countByCourseId(courseId);

        List<ExternalLink> list = findByCourseId(courseId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the external links before and after the current external link in the ordered set where courseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param linkId the primary key of the current external link
     * @param courseId the course ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink[] findByCourseId_PrevAndNext(long linkId,
        long courseId, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        ExternalLink externalLink = findByPrimaryKey(linkId);

        Session session = null;

        try {
            session = openSession();

            ExternalLink[] array = new ExternalLinkImpl[3];

            array[0] = getByCourseId_PrevAndNext(session, externalLink,
                    courseId, orderByComparator, true);

            array[1] = externalLink;

            array[2] = getByCourseId_PrevAndNext(session, externalLink,
                    courseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ExternalLink getByCourseId_PrevAndNext(Session session,
        ExternalLink externalLink, long courseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

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
            Object[] values = orderByComparator.getOrderByConditionValues(externalLink);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ExternalLink> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the external links where courseId = &#63; and linkType = &#63;.
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @return the matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByCourseIdWithType(long courseId,
        String linkType) throws SystemException {
        return findByCourseIdWithType(courseId, linkType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the external links where courseId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @return the range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByCourseIdWithType(long courseId,
        String linkType, int start, int end) throws SystemException {
        return findByCourseIdWithType(courseId, linkType, start, end, null);
    }

    /**
     * Returns an ordered range of all the external links where courseId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByCourseIdWithType(long courseId,
        String linkType, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHTYPE;
            finderArgs = new Object[] { courseId, linkType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHTYPE;
            finderArgs = new Object[] {
                    courseId, linkType,
                    
                    start, end, orderByComparator
                };
        }

        List<ExternalLink> list = (List<ExternalLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_COURSEID_2);

            if (linkType == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_1);
            } else {
                if (linkType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_2);
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

                if (linkType != null) {
                    qPos.add(linkType);
                }

                list = (List<ExternalLink>) QueryUtil.list(q, getDialect(),
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
     * Returns the first external link in the ordered set where courseId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByCourseIdWithType_First(long courseId,
        String linkType, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        List<ExternalLink> list = findByCourseIdWithType(courseId, linkType, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", linkType=");
            msg.append(linkType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last external link in the ordered set where courseId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByCourseIdWithType_Last(long courseId,
        String linkType, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        int count = countByCourseIdWithType(courseId, linkType);

        List<ExternalLink> list = findByCourseIdWithType(courseId, linkType,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", linkType=");
            msg.append(linkType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the external links before and after the current external link in the ordered set where courseId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param linkId the primary key of the current external link
     * @param courseId the course ID
     * @param linkType the link type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink[] findByCourseIdWithType_PrevAndNext(long linkId,
        long courseId, String linkType, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        ExternalLink externalLink = findByPrimaryKey(linkId);

        Session session = null;

        try {
            session = openSession();

            ExternalLink[] array = new ExternalLinkImpl[3];

            array[0] = getByCourseIdWithType_PrevAndNext(session, externalLink,
                    courseId, linkType, orderByComparator, true);

            array[1] = externalLink;

            array[2] = getByCourseIdWithType_PrevAndNext(session, externalLink,
                    courseId, linkType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ExternalLink getByCourseIdWithType_PrevAndNext(Session session,
        ExternalLink externalLink, long courseId, String linkType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

        query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_COURSEID_2);

        if (linkType == null) {
            query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_1);
        } else {
            if (linkType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_3);
            } else {
                query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_2);
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

        if (linkType != null) {
            qPos.add(linkType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(externalLink);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ExternalLink> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the external links where componentId = &#63;.
     *
     * @param componentId the component ID
     * @return the matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByComponentId(long componentId)
        throws SystemException {
        return findByComponentId(componentId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the external links where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @return the range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByComponentId(long componentId, int start,
        int end) throws SystemException {
        return findByComponentId(componentId, start, end, null);
    }

    /**
     * Returns an ordered range of all the external links where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByComponentId(long componentId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<ExternalLink> list = (List<ExternalLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTID_COMPONENTID_2);

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

                list = (List<ExternalLink>) QueryUtil.list(q, getDialect(),
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
     * Returns the first external link in the ordered set where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByComponentId_First(long componentId,
        OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        List<ExternalLink> list = findByComponentId(componentId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last external link in the ordered set where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByComponentId_Last(long componentId,
        OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        int count = countByComponentId(componentId);

        List<ExternalLink> list = findByComponentId(componentId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the external links before and after the current external link in the ordered set where componentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param linkId the primary key of the current external link
     * @param componentId the component ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink[] findByComponentId_PrevAndNext(long linkId,
        long componentId, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        ExternalLink externalLink = findByPrimaryKey(linkId);

        Session session = null;

        try {
            session = openSession();

            ExternalLink[] array = new ExternalLinkImpl[3];

            array[0] = getByComponentId_PrevAndNext(session, externalLink,
                    componentId, orderByComparator, true);

            array[1] = externalLink;

            array[2] = getByComponentId_PrevAndNext(session, externalLink,
                    componentId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ExternalLink getByComponentId_PrevAndNext(Session session,
        ExternalLink externalLink, long componentId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(componentId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(externalLink);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ExternalLink> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the external links where componentId = &#63; and linkType = &#63;.
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @return the matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByComponentIdWithType(long componentId,
        String linkType) throws SystemException {
        return findByComponentIdWithType(componentId, linkType,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the external links where componentId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @return the range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByComponentIdWithType(long componentId,
        String linkType, int start, int end) throws SystemException {
        return findByComponentIdWithType(componentId, linkType, start, end, null);
    }

    /**
     * Returns an ordered range of all the external links where componentId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findByComponentIdWithType(long componentId,
        String linkType, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE;
            finderArgs = new Object[] { componentId, linkType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPONENTIDWITHTYPE;
            finderArgs = new Object[] {
                    componentId, linkType,
                    
                    start, end, orderByComparator
                };
        }

        List<ExternalLink> list = (List<ExternalLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_COMPONENTID_2);

            if (linkType == null) {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_1);
            } else {
                if (linkType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_2);
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

                if (linkType != null) {
                    qPos.add(linkType);
                }

                list = (List<ExternalLink>) QueryUtil.list(q, getDialect(),
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
     * Returns the first external link in the ordered set where componentId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByComponentIdWithType_First(long componentId,
        String linkType, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        List<ExternalLink> list = findByComponentIdWithType(componentId,
                linkType, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(", linkType=");
            msg.append(linkType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last external link in the ordered set where componentId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink findByComponentIdWithType_Last(long componentId,
        String linkType, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        int count = countByComponentIdWithType(componentId, linkType);

        List<ExternalLink> list = findByComponentIdWithType(componentId,
                linkType, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(", linkType=");
            msg.append(linkType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchExternalLinkException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the external links before and after the current external link in the ordered set where componentId = &#63; and linkType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param linkId the primary key of the current external link
     * @param componentId the component ID
     * @param linkType the link type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next external link
     * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ExternalLink[] findByComponentIdWithType_PrevAndNext(long linkId,
        long componentId, String linkType, OrderByComparator orderByComparator)
        throws NoSuchExternalLinkException, SystemException {
        ExternalLink externalLink = findByPrimaryKey(linkId);

        Session session = null;

        try {
            session = openSession();

            ExternalLink[] array = new ExternalLinkImpl[3];

            array[0] = getByComponentIdWithType_PrevAndNext(session,
                    externalLink, componentId, linkType, orderByComparator, true);

            array[1] = externalLink;

            array[2] = getByComponentIdWithType_PrevAndNext(session,
                    externalLink, componentId, linkType, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ExternalLink getByComponentIdWithType_PrevAndNext(
        Session session, ExternalLink externalLink, long componentId,
        String linkType, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_EXTERNALLINK_WHERE);

        query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_COMPONENTID_2);

        if (linkType == null) {
            query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_1);
        } else {
            if (linkType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_3);
            } else {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_2);
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

        if (linkType != null) {
            qPos.add(linkType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(externalLink);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ExternalLink> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the external links.
     *
     * @return the external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the external links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @return the range of external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the external links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of external links
     * @param end the upper bound of the range of external links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of external links
     * @throws SystemException if a system exception occurred
     */
    public List<ExternalLink> findAll(int start, int end,
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

        List<ExternalLink> list = (List<ExternalLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_EXTERNALLINK);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_EXTERNALLINK;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ExternalLink>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ExternalLink>) QueryUtil.list(q, getDialect(),
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
     * Removes all the external links where courseId = &#63; from the database.
     *
     * @param courseId the course ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(long courseId) throws SystemException {
        for (ExternalLink externalLink : findByCourseId(courseId)) {
            remove(externalLink);
        }
    }

    /**
     * Removes all the external links where courseId = &#63; and linkType = &#63; from the database.
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIdWithType(long courseId, String linkType)
        throws SystemException {
        for (ExternalLink externalLink : findByCourseIdWithType(courseId,
                linkType)) {
            remove(externalLink);
        }
    }

    /**
     * Removes all the external links where componentId = &#63; from the database.
     *
     * @param componentId the component ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentId(long componentId) throws SystemException {
        for (ExternalLink externalLink : findByComponentId(componentId)) {
            remove(externalLink);
        }
    }

    /**
     * Removes all the external links where componentId = &#63; and linkType = &#63; from the database.
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentIdWithType(long componentId, String linkType)
        throws SystemException {
        for (ExternalLink externalLink : findByComponentIdWithType(
                componentId, linkType)) {
            remove(externalLink);
        }
    }

    /**
     * Removes all the external links from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ExternalLink externalLink : findAll()) {
            remove(externalLink);
        }
    }

    /**
     * Returns the number of external links where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the number of matching external links
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(long courseId) throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_EXTERNALLINK_WHERE);

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
     * Returns the number of external links where courseId = &#63; and linkType = &#63;.
     *
     * @param courseId the course ID
     * @param linkType the link type
     * @return the number of matching external links
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIdWithType(long courseId, String linkType)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseId, linkType };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIDWITHTYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_EXTERNALLINK_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_COURSEID_2);

            if (linkType == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_1);
            } else {
                if (linkType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHTYPE_LINKTYPE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                if (linkType != null) {
                    qPos.add(linkType);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIDWITHTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of external links where componentId = &#63;.
     *
     * @param componentId the component ID
     * @return the number of matching external links
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentId(long componentId) throws SystemException {
        Object[] finderArgs = new Object[] { componentId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_EXTERNALLINK_WHERE);

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
     * Returns the number of external links where componentId = &#63; and linkType = &#63;.
     *
     * @param componentId the component ID
     * @param linkType the link type
     * @return the number of matching external links
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentIdWithType(long componentId, String linkType)
        throws SystemException {
        Object[] finderArgs = new Object[] { componentId, linkType };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHTYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_EXTERNALLINK_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_COMPONENTID_2);

            if (linkType == null) {
                query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_1);
            } else {
                if (linkType.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_3);
                } else {
                    query.append(_FINDER_COLUMN_COMPONENTIDWITHTYPE_LINKTYPE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(componentId);

                if (linkType != null) {
                    qPos.add(linkType);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPONENTIDWITHTYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of external links.
     *
     * @return the number of external links
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_EXTERNALLINK);

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
     * Initializes the external link persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.ExternalLink")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ExternalLink>> listenersList = new ArrayList<ModelListener<ExternalLink>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ExternalLink>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ExternalLinkImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
