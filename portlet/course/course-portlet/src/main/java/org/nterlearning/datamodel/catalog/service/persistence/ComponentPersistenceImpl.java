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

import org.nterlearning.datamodel.catalog.NoSuchComponentException;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.ComponentModelImpl;
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
 * The persistence implementation for the component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentPersistence
 * @see ComponentUtil
 * @generated
 */
public class ComponentPersistenceImpl extends BasePersistenceImpl<Component>
    implements ComponentPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ComponentUtil} to access the component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ComponentImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_COMPONENTID = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByComponentId",
            new String[] { Long.class.getName() },
            ComponentModelImpl.COMPONENTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTID = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByComponentId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
            new String[] { Long.class.getName() },
            ComponentModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_COMPONENTIRI = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByComponentIri",
            new String[] { String.class.getName() },
            ComponentModelImpl.COMPONENTIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPONENTIRI = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByComponentIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFeedReferenceId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFeedReferenceId",
            new String[] { Long.class.getName() },
            ComponentModelImpl.FEEDREFERENCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDREFERENCEID = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByFeedReferenceId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, ComponentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_COURSES_COMPONENTSES = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.Courses_ComponentsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourses_Componentses",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COURSES_COMPONENTSES_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.Courses_ComponentsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourses_ComponentsesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COURSES_COMPONENTS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.Courses_ComponentsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsCourses_Components",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_CONTRIBUTORS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ContributorImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ContributorPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getContributors",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_CONTRIBUTORS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ContributorImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ContributorPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getContributorsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_CONTRIBUTOR = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ContributorModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ContributorImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ContributorPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsContributor",
            new String[] { Long.class.getName(), Long.class.getName() });
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
    public static final FinderPath FINDER_PATH_GET_EXTERNALLINKS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ExternalLinkPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getExternalLinks",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_EXTERNALLINKS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ExternalLinkPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getExternalLinksSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_EXTERNALLINK = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ExternalLinkModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.ExternalLinkPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsExternalLink",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_SELECT_COMPONENT = "SELECT component FROM Component component";
    private static final String _SQL_SELECT_COMPONENT_WHERE = "SELECT component FROM Component component WHERE ";
    private static final String _SQL_COUNT_COMPONENT = "SELECT COUNT(component) FROM Component component";
    private static final String _SQL_COUNT_COMPONENT_WHERE = "SELECT COUNT(component) FROM Component component WHERE ";
    private static final String _SQL_GETCOURSES_COMPONENTSES = "SELECT {CATALOG_Courses_Components.*} FROM CATALOG_Courses_Components INNER JOIN CATALOG_Component ON (CATALOG_Component.componentId = CATALOG_Courses_Components.componentId) WHERE (CATALOG_Component.componentId = ?)";
    private static final String _SQL_GETCOURSES_COMPONENTSESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Courses_Components WHERE componentId = ?";
    private static final String _SQL_CONTAINSCOURSES_COMPONENTS = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Courses_Components WHERE componentId = ? AND coursesComponentsId = ?";
    private static final String _SQL_GETCONTRIBUTORS = "SELECT {CATALOG_Contributor.*} FROM CATALOG_Contributor INNER JOIN CATALOG_Component ON (CATALOG_Component.componentId = CATALOG_Contributor.componentId) WHERE (CATALOG_Component.componentId = ?)";
    private static final String _SQL_GETCONTRIBUTORSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Contributor WHERE componentId = ?";
    private static final String _SQL_CONTAINSCONTRIBUTOR = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Contributor WHERE componentId = ? AND contributorId = ?";
    private static final String _SQL_GETCOMPONENTRECORDS = "SELECT {CATALOG_ComponentRecord.*} FROM CATALOG_ComponentRecord INNER JOIN CATALOG_Component ON (CATALOG_Component.componentId = CATALOG_ComponentRecord.componentId) WHERE (CATALOG_Component.componentId = ?)";
    private static final String _SQL_GETCOMPONENTRECORDSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ComponentRecord WHERE componentId = ?";
    private static final String _SQL_CONTAINSCOMPONENTRECORD = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ComponentRecord WHERE componentId = ? AND componentRecordId = ?";
    private static final String _SQL_GETEXTERNALLINKS = "SELECT {CATALOG_ExternalLink.*} FROM CATALOG_ExternalLink INNER JOIN CATALOG_Component ON (CATALOG_Component.componentId = CATALOG_ExternalLink.componentId) WHERE (CATALOG_Component.componentId = ?)";
    private static final String _SQL_GETEXTERNALLINKSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ExternalLink WHERE componentId = ?";
    private static final String _SQL_CONTAINSEXTERNALLINK = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ExternalLink WHERE componentId = ? AND linkId = ?";
    private static final String _FINDER_COLUMN_COMPONENTID_COMPONENTID_2 = "component.componentId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "component.companyId = ?";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_1 = "component.componentIri IS NULL";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_2 = "component.componentIri = ?";
    private static final String _FINDER_COLUMN_COMPONENTIRI_COMPONENTIRI_3 = "(component.componentIri IS NULL OR component.componentIri = ?)";
    private static final String _FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2 =
        "component.feedReferenceId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "component.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Component exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Component exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ComponentPersistenceImpl.class);
    private static Component _nullComponent = new ComponentImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Component> toCacheModel() {
                return _nullComponentCacheModel;
            }
        };

    private static CacheModel<Component> _nullComponentCacheModel = new CacheModel<Component>() {
            public Component toEntityModel() {
                return _nullComponent;
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
    protected ContainsCourses_Components containsCourses_Components;
    protected ContainsContributor containsContributor;
    protected ContainsComponentRecord containsComponentRecord;
    protected ContainsExternalLink containsExternalLink;

    /**
     * Caches the component in the entity cache if it is enabled.
     *
     * @param component the component
     */
    public void cacheResult(Component component) {
        EntityCacheUtil.putResult(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentImpl.class, component.getPrimaryKey(), component);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTID,
            new Object[] { Long.valueOf(component.getComponentId()) }, component);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
            new Object[] { component.getComponentIri() }, component);

        component.resetOriginalValues();
    }

    /**
     * Caches the components in the entity cache if it is enabled.
     *
     * @param components the components
     */
    public void cacheResult(List<Component> components) {
        for (Component component : components) {
            if (EntityCacheUtil.getResult(
                        ComponentModelImpl.ENTITY_CACHE_ENABLED,
                        ComponentImpl.class, component.getPrimaryKey()) == null) {
                cacheResult(component);
            } else {
                component.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all components.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ComponentImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ComponentImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the component.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Component component) {
        EntityCacheUtil.removeResult(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentImpl.class, component.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(component);
    }

    @Override
    public void clearCache(List<Component> components) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Component component : components) {
            EntityCacheUtil.removeResult(ComponentModelImpl.ENTITY_CACHE_ENABLED,
                ComponentImpl.class, component.getPrimaryKey());

            clearUniqueFindersCache(component);
        }
    }

    protected void clearUniqueFindersCache(Component component) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPONENTID,
            new Object[] { Long.valueOf(component.getComponentId()) });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
            new Object[] { component.getComponentIri() });
    }

    /**
     * Creates a new component with the primary key. Does not add the component to the database.
     *
     * @param componentId the primary key for the new component
     * @return the new component
     */
    public Component create(long componentId) {
        Component component = new ComponentImpl();

        component.setNew(true);
        component.setPrimaryKey(componentId);

        return component;
    }

    /**
     * Removes the component with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param componentId the primary key of the component
     * @return the component that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component remove(long componentId)
        throws NoSuchComponentException, SystemException {
        return remove(Long.valueOf(componentId));
    }

    /**
     * Removes the component with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the component
     * @return the component that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Component remove(Serializable primaryKey)
        throws NoSuchComponentException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Component component = (Component) session.get(ComponentImpl.class,
                    primaryKey);

            if (component == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(component);
        } catch (NoSuchComponentException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Component removeImpl(Component component)
        throws SystemException {
        component = toUnwrappedModel(component);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, component);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(component);

        return component;
    }

    @Override
    public Component updateImpl(
        org.nterlearning.datamodel.catalog.model.Component component,
        boolean merge) throws SystemException {
        component = toUnwrappedModel(component);

        boolean isNew = component.isNew();

        ComponentModelImpl componentModelImpl = (ComponentModelImpl) component;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, component, merge);

            component.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ComponentModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((componentModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(componentModelImpl.getOriginalCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] {
                        Long.valueOf(componentModelImpl.getCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((componentModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(componentModelImpl.getOriginalFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);

                args = new Object[] {
                        Long.valueOf(componentModelImpl.getFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ComponentModelImpl.ENTITY_CACHE_ENABLED,
            ComponentImpl.class, component.getPrimaryKey(), component);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                new Object[] { Long.valueOf(component.getComponentId()) },
                component);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                new Object[] { component.getComponentIri() }, component);
        } else {
            if ((componentModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COMPONENTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(componentModelImpl.getOriginalComponentId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                    new Object[] { Long.valueOf(component.getComponentId()) },
                    component);
            }

            if ((componentModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COMPONENTIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        componentModelImpl.getOriginalComponentIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                    new Object[] { component.getComponentIri() }, component);
            }
        }

        return component;
    }

    protected Component toUnwrappedModel(Component component) {
        if (component instanceof ComponentImpl) {
            return component;
        }

        ComponentImpl componentImpl = new ComponentImpl();

        componentImpl.setNew(component.isNew());
        componentImpl.setPrimaryKey(component.getPrimaryKey());

        componentImpl.setComponentId(component.getComponentId());
        componentImpl.setCompanyId(component.getCompanyId());
        componentImpl.setGroupId(component.getGroupId());
        componentImpl.setFeedReferenceId(component.getFeedReferenceId());
        componentImpl.setComponentIri(component.getComponentIri());
        componentImpl.setUpdatedDate(component.getUpdatedDate());
        componentImpl.setLanguage(component.getLanguage());
        componentImpl.setHref(component.getHref());
        componentImpl.setFullTextHref(component.getFullTextHref());
        componentImpl.setTitle(component.getTitle());
        componentImpl.setDescription(component.getDescription());
        componentImpl.setCopyright(component.getCopyright());
        componentImpl.setDisplayWidth(component.getDisplayWidth());
        componentImpl.setDisplayHeight(component.getDisplayHeight());
        componentImpl.setCreateDate(component.getCreateDate());
        componentImpl.setRemoved(component.isRemoved());
        componentImpl.setRemovedDate(component.getRemovedDate());
        componentImpl.setVersion(component.getVersion());
        componentImpl.setVersionDate(component.getVersionDate());
        componentImpl.setPrice(component.getPrice());
        componentImpl.setPriceUnit(component.getPriceUnit());
        componentImpl.setPriceTerms(component.getPriceTerms());
        componentImpl.setPriceExpiration(component.getPriceExpiration());

        return componentImpl;
    }

    /**
     * Returns the component with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the component
     * @return the component
     * @throws com.liferay.portal.NoSuchModelException if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Component findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the component with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentException} if it could not be found.
     *
     * @param componentId the primary key of the component
     * @return the component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByPrimaryKey(long componentId)
        throws NoSuchComponentException, SystemException {
        Component component = fetchByPrimaryKey(componentId);

        if (component == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + componentId);
            }

            throw new NoSuchComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                componentId);
        }

        return component;
    }

    /**
     * Returns the component with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the component
     * @return the component, or <code>null</code> if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Component fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the component with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param componentId the primary key of the component
     * @return the component, or <code>null</code> if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component fetchByPrimaryKey(long componentId)
        throws SystemException {
        Component component = (Component) EntityCacheUtil.getResult(ComponentModelImpl.ENTITY_CACHE_ENABLED,
                ComponentImpl.class, componentId);

        if (component == _nullComponent) {
            return null;
        }

        if (component == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                component = (Component) session.get(ComponentImpl.class,
                        Long.valueOf(componentId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (component != null) {
                    cacheResult(component);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ComponentModelImpl.ENTITY_CACHE_ENABLED,
                        ComponentImpl.class, componentId, _nullComponent);
                }

                closeSession(session);
            }
        }

        return component;
    }

    /**
     * Returns the component where componentId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentException} if it could not be found.
     *
     * @param componentId the component ID
     * @return the matching component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByComponentId(long componentId)
        throws NoSuchComponentException, SystemException {
        Component component = fetchByComponentId(componentId);

        if (component == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentId=");
            msg.append(componentId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchComponentException(msg.toString());
        }

        return component;
    }

    /**
     * Returns the component where componentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param componentId the component ID
     * @return the matching component, or <code>null</code> if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component fetchByComponentId(long componentId)
        throws SystemException {
        return fetchByComponentId(componentId, true);
    }

    /**
     * Returns the component where componentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param componentId the component ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching component, or <code>null</code> if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component fetchByComponentId(long componentId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { componentId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_COMPONENT_WHERE);

            query.append(_FINDER_COLUMN_COMPONENTID_COMPONENTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(componentId);

                List<Component> list = q.list();

                result = list;

                Component component = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                        finderArgs, list);
                } else {
                    component = list.get(0);

                    cacheResult(component);

                    if ((component.getComponentId() != componentId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                            finderArgs, component);
                    }
                }

                return component;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPONENTID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (Component) result;
            }
        }
    }

    /**
     * Returns all the components where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findByCompanyId(long companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the components where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of matching components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findByCompanyId(long companyId, int start, int end)
        throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the components where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findByCompanyId(long companyId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
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

        List<Component> list = (List<Component>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_COMPONENT_WHERE);

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

                list = (List<Component>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first component in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByCompanyId_First(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentException, SystemException {
        List<Component> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last component in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByCompanyId_Last(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentException, SystemException {
        int count = countByCompanyId(companyId);

        List<Component> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the components before and after the current component in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the primary key of the current component
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component[] findByCompanyId_PrevAndNext(long componentId,
        long companyId, OrderByComparator orderByComparator)
        throws NoSuchComponentException, SystemException {
        Component component = findByPrimaryKey(componentId);

        Session session = null;

        try {
            session = openSession();

            Component[] array = new ComponentImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, component,
                    companyId, orderByComparator, true);

            array[1] = component;

            array[2] = getByCompanyId_PrevAndNext(session, component,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Component getByCompanyId_PrevAndNext(Session session,
        Component component, long companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPONENT_WHERE);

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
            Object[] values = orderByComparator.getOrderByConditionValues(component);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Component> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the component where componentIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentException} if it could not be found.
     *
     * @param componentIri the component iri
     * @return the matching component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByComponentIri(String componentIri)
        throws NoSuchComponentException, SystemException {
        Component component = fetchByComponentIri(componentIri);

        if (component == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("componentIri=");
            msg.append(componentIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchComponentException(msg.toString());
        }

        return component;
    }

    /**
     * Returns the component where componentIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param componentIri the component iri
     * @return the matching component, or <code>null</code> if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component fetchByComponentIri(String componentIri)
        throws SystemException {
        return fetchByComponentIri(componentIri, true);
    }

    /**
     * Returns the component where componentIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param componentIri the component iri
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching component, or <code>null</code> if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component fetchByComponentIri(String componentIri,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { componentIri };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_COMPONENT_WHERE);

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

                List<Component> list = q.list();

                result = list;

                Component component = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                        finderArgs, list);
                } else {
                    component = list.get(0);

                    cacheResult(component);

                    if ((component.getComponentIri() == null) ||
                            !component.getComponentIri().equals(componentIri)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                            finderArgs, component);
                    }
                }

                return component;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPONENTIRI,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (Component) result;
            }
        }
    }

    /**
     * Returns all the components where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the matching components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        return findByFeedReferenceId(feedReferenceId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the components where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of matching components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findByFeedReferenceId(long feedReferenceId,
        int start, int end) throws SystemException {
        return findByFeedReferenceId(feedReferenceId, start, end, null);
    }

    /**
     * Returns an ordered range of all the components where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findByFeedReferenceId(long feedReferenceId,
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

        List<Component> list = (List<Component>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_COMPONENT_WHERE);

            query.append(_FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2);

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

                qPos.add(feedReferenceId);

                list = (List<Component>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first component in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByFeedReferenceId_First(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentException, SystemException {
        List<Component> list = findByFeedReferenceId(feedReferenceId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last component in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component findByFeedReferenceId_Last(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchComponentException, SystemException {
        int count = countByFeedReferenceId(feedReferenceId);

        List<Component> list = findByFeedReferenceId(feedReferenceId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchComponentException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the components before and after the current component in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param componentId the primary key of the current component
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next component
     * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Component[] findByFeedReferenceId_PrevAndNext(long componentId,
        long feedReferenceId, OrderByComparator orderByComparator)
        throws NoSuchComponentException, SystemException {
        Component component = findByPrimaryKey(componentId);

        Session session = null;

        try {
            session = openSession();

            Component[] array = new ComponentImpl[3];

            array[0] = getByFeedReferenceId_PrevAndNext(session, component,
                    feedReferenceId, orderByComparator, true);

            array[1] = component;

            array[2] = getByFeedReferenceId_PrevAndNext(session, component,
                    feedReferenceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Component getByFeedReferenceId_PrevAndNext(Session session,
        Component component, long feedReferenceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPONENT_WHERE);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(feedReferenceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(component);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Component> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the components.
     *
     * @return the components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the components.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the components.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of components
     * @throws SystemException if a system exception occurred
     */
    public List<Component> findAll(int start, int end,
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

        List<Component> list = (List<Component>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPONENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPONENT;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<Component>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Component>) QueryUtil.list(q, getDialect(),
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
     * Removes the component where componentId = &#63; from the database.
     *
     * @param componentId the component ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentId(long componentId)
        throws NoSuchComponentException, SystemException {
        Component component = findByComponentId(componentId);

        remove(component);
    }

    /**
     * Removes all the components where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCompanyId(long companyId) throws SystemException {
        for (Component component : findByCompanyId(companyId)) {
            remove(component);
        }
    }

    /**
     * Removes the component where componentIri = &#63; from the database.
     *
     * @param componentIri the component iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByComponentIri(String componentIri)
        throws NoSuchComponentException, SystemException {
        Component component = findByComponentIri(componentIri);

        remove(component);
    }

    /**
     * Removes all the components where feedReferenceId = &#63; from the database.
     *
     * @param feedReferenceId the feed reference ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        for (Component component : findByFeedReferenceId(feedReferenceId)) {
            remove(component);
        }
    }

    /**
     * Removes all the components from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (Component component : findAll()) {
            remove(component);
        }
    }

    /**
     * Returns the number of components where componentId = &#63;.
     *
     * @param componentId the component ID
     * @return the number of matching components
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentId(long componentId) throws SystemException {
        Object[] finderArgs = new Object[] { componentId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPONENT_WHERE);

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
     * Returns the number of components where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching components
     * @throws SystemException if a system exception occurred
     */
    public int countByCompanyId(long companyId) throws SystemException {
        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPONENT_WHERE);

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
     * Returns the number of components where componentIri = &#63;.
     *
     * @param componentIri the component iri
     * @return the number of matching components
     * @throws SystemException if a system exception occurred
     */
    public int countByComponentIri(String componentIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { componentIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPONENTIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPONENT_WHERE);

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
     * Returns the number of components where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the number of matching components
     * @throws SystemException if a system exception occurred
     */
    public int countByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        Object[] finderArgs = new Object[] { feedReferenceId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPONENT_WHERE);

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
     * Returns the number of components.
     *
     * @return the number of components
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COMPONENT);

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
     * Returns all the courses_ componentses associated with the component.
     *
     * @param pk the primary key of the component
     * @return the courses_ componentses associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk) throws SystemException {
        return getCourses_Componentses(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the courses_ componentses associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of courses_ componentses associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end) throws SystemException {
        return getCourses_Componentses(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of courses_ componentses associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.Courses_Components> list = (List<org.nterlearning.datamodel.catalog.model.Courses_Components>) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSES_COMPONENTSES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOURSES_COMPONENTSES.concat(ORDER_BY_CLAUSE)
                                                      .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOURSES_COMPONENTSES.concat(org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsModelImpl.ORDER_BY_SQL);
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_Courses_Components",
                    org.nterlearning.datamodel.catalog.model.impl.Courses_ComponentsImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.Courses_Components>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COURSES_COMPONENTSES,
                        finderArgs);
                } else {
                    courses_ComponentsPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COURSES_COMPONENTSES,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of courses_ componentses associated with the component.
     *
     * @param pk the primary key of the component
     * @return the number of courses_ componentses associated with the component
     * @throws SystemException if a system exception occurred
     */
    public int getCourses_ComponentsesSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSES_COMPONENTSES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOURSES_COMPONENTSESSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_COURSES_COMPONENTSES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the courses_ components is associated with the component.
     *
     * @param pk the primary key of the component
     * @param courses_ComponentsPK the primary key of the courses_ components
     * @return <code>true</code> if the courses_ components is associated with the component; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourses_Components(long pk, long courses_ComponentsPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, courses_ComponentsPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COURSES_COMPONENTS,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsCourses_Components.contains(
                            pk, courses_ComponentsPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COURSES_COMPONENTS,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the component has any courses_ componentses associated with it.
     *
     * @param pk the primary key of the component to check for associations with courses_ componentses
     * @return <code>true</code> if the component has any courses_ componentses associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourses_Componentses(long pk)
        throws SystemException {
        if (getCourses_ComponentsesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the contributors associated with the component.
     *
     * @param pk the primary key of the component
     * @return the contributors associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk) throws SystemException {
        return getContributors(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the contributors associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of contributors associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end) throws SystemException {
        return getContributors(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the contributors associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contributors associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.Contributor> list = (List<org.nterlearning.datamodel.catalog.model.Contributor>) FinderCacheUtil.getResult(FINDER_PATH_GET_CONTRIBUTORS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCONTRIBUTORS.concat(ORDER_BY_CLAUSE)
                                              .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCONTRIBUTORS;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_Contributor",
                    org.nterlearning.datamodel.catalog.model.impl.ContributorImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.Contributor>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_CONTRIBUTORS,
                        finderArgs);
                } else {
                    contributorPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_CONTRIBUTORS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of contributors associated with the component.
     *
     * @param pk the primary key of the component
     * @return the number of contributors associated with the component
     * @throws SystemException if a system exception occurred
     */
    public int getContributorsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_CONTRIBUTORS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCONTRIBUTORSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_CONTRIBUTORS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the contributor is associated with the component.
     *
     * @param pk the primary key of the component
     * @param contributorPK the primary key of the contributor
     * @return <code>true</code> if the contributor is associated with the component; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsContributor(long pk, long contributorPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, contributorPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_CONTRIBUTOR,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsContributor.contains(pk,
                            contributorPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_CONTRIBUTOR,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the component has any contributors associated with it.
     *
     * @param pk the primary key of the component to check for associations with contributors
     * @return <code>true</code> if the component has any contributors associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsContributors(long pk) throws SystemException {
        if (getContributorsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the component records associated with the component.
     *
     * @param pk the primary key of the component
     * @return the component records associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk) throws SystemException {
        return getComponentRecords(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the component records associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of component records associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end) throws SystemException {
        return getComponentRecords(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the component records associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of component records associated with the component
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
     * Returns the number of component records associated with the component.
     *
     * @param pk the primary key of the component
     * @return the number of component records associated with the component
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
     * Returns <code>true</code> if the component record is associated with the component.
     *
     * @param pk the primary key of the component
     * @param componentRecordPK the primary key of the component record
     * @return <code>true</code> if the component record is associated with the component; <code>false</code> otherwise
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
     * Returns <code>true</code> if the component has any component records associated with it.
     *
     * @param pk the primary key of the component to check for associations with component records
     * @return <code>true</code> if the component has any component records associated with it; <code>false</code> otherwise
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
     * Returns all the external links associated with the component.
     *
     * @param pk the primary key of the component
     * @return the external links associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk) throws SystemException {
        return getExternalLinks(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the external links associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @return the range of external links associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end) throws SystemException {
        return getExternalLinks(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the external links associated with the component.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the component
     * @param start the lower bound of the range of components
     * @param end the upper bound of the range of components (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of external links associated with the component
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.ExternalLink> list = (List<org.nterlearning.datamodel.catalog.model.ExternalLink>) FinderCacheUtil.getResult(FINDER_PATH_GET_EXTERNALLINKS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETEXTERNALLINKS.concat(ORDER_BY_CLAUSE)
                                               .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETEXTERNALLINKS;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_ExternalLink",
                    org.nterlearning.datamodel.catalog.model.impl.ExternalLinkImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.ExternalLink>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_EXTERNALLINKS,
                        finderArgs);
                } else {
                    externalLinkPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_EXTERNALLINKS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of external links associated with the component.
     *
     * @param pk the primary key of the component
     * @return the number of external links associated with the component
     * @throws SystemException if a system exception occurred
     */
    public int getExternalLinksSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_EXTERNALLINKS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETEXTERNALLINKSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_EXTERNALLINKS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the external link is associated with the component.
     *
     * @param pk the primary key of the component
     * @param externalLinkPK the primary key of the external link
     * @return <code>true</code> if the external link is associated with the component; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsExternalLink(long pk, long externalLinkPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, externalLinkPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_EXTERNALLINK,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsExternalLink.contains(pk,
                            externalLinkPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_EXTERNALLINK,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the component has any external links associated with it.
     *
     * @param pk the primary key of the component to check for associations with external links
     * @return <code>true</code> if the component has any external links associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsExternalLinks(long pk) throws SystemException {
        if (getExternalLinksSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the component persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.Component")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Component>> listenersList = new ArrayList<ModelListener<Component>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Component>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsCourses_Components = new ContainsCourses_Components();

        containsContributor = new ContainsContributor();

        containsComponentRecord = new ContainsComponentRecord();

        containsExternalLink = new ContainsExternalLink();
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ComponentImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    protected class ContainsCourses_Components {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsCourses_Components() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOURSES_COMPONENTS,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long componentId, long coursesComponentsId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(componentId), new Long(coursesComponentsId)
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

    protected class ContainsContributor {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsContributor() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCONTRIBUTOR,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long componentId, long contributorId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(componentId), new Long(contributorId)
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

    protected class ContainsComponentRecord {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsComponentRecord() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOMPONENTRECORD,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long componentId, long componentRecordId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(componentId), new Long(componentRecordId)
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

    protected class ContainsExternalLink {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsExternalLink() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSEXTERNALLINK,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long componentId, long linkId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(componentId), new Long(linkId)
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
