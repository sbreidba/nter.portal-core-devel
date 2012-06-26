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
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;
import com.liferay.portlet.ratings.service.persistence.RatingsStatsPersistence;

import org.nterlearning.datamodel.catalog.NoSuchCourseException;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseModelImpl;
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
 * The persistence implementation for the course service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CoursePersistence
 * @see CourseUtil
 * @generated
 */
public class CoursePersistenceImpl extends BasePersistenceImpl<Course>
    implements CoursePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CourseUtil} to access the course persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CourseImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_COURSEID = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByCourseId",
            new String[] { Long.class.getName() },
            CourseModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
            new String[] { Long.class.getName() },
            CourseModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
            new String[] { Long.class.getName() },
            CourseModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_COURSEIRI = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByCourseIri",
            new String[] { String.class.getName() },
            CourseModelImpl.COURSEIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIRI = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFeedReferenceId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFeedReferenceId",
            new String[] { Long.class.getName() },
            CourseModelImpl.FEEDREFERENCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FEEDREFERENCEID = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByFeedReferenceId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBySupersededByCourseIri",
            new String[] {
                String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI =
        new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBySupersededByCourseIri",
            new String[] { String.class.getName() },
            CourseModelImpl.SUPERSEDEDBYCOURSEIRI_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SUPERSEDEDBYCOURSEIRI = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySupersededByCourseIri",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, CourseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
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
    public static final FinderPath FINDER_PATH_GET_COURSEIMAGES = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseImagePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseImages",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COURSEIMAGES_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseImagePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseImagesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COURSEIMAGE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseImagePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsCourseImage",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_COURSERELATEDS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseRelatedPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseRelateds",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COURSERELATEDS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseRelatedPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseRelatedsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COURSERELATED = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRelatedModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseRelatedPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsCourseRelated",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_COURSEREQUIREMENTS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseRequirementPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseRequirements",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COURSEREQUIREMENTS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseRequirementPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseRequirementsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COURSEREQUIREMENT = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRequirementModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseRequirementPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsCourseRequirement",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_COURSEREVIEWS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseReviewPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseReviews",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_COURSEREVIEWS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseReviewPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getCourseReviewsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_COURSEREVIEW = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.CourseReviewPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsCourseReview",
            new String[] { Long.class.getName(), Long.class.getName() });
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
    public static final FinderPath FINDER_PATH_GET_GLOBALCOURSEREVIEWS = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getGlobalCourseReviews",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_GLOBALCOURSEREVIEWS_SIZE = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getGlobalCourseReviewsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_GLOBALCOURSEREVIEW = new FinderPath(org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.ENTITY_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.FINDER_CACHE_ENABLED,
            org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl.class,
            org.nterlearning.datamodel.catalog.service.persistence.GlobalCourseReviewPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsGlobalCourseReview",
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
    private static final String _SQL_SELECT_COURSE = "SELECT course FROM Course course";
    private static final String _SQL_SELECT_COURSE_WHERE = "SELECT course FROM Course course WHERE ";
    private static final String _SQL_COUNT_COURSE = "SELECT COUNT(course) FROM Course course";
    private static final String _SQL_COUNT_COURSE_WHERE = "SELECT COUNT(course) FROM Course course WHERE ";
    private static final String _SQL_GETCONTRIBUTORS = "SELECT {CATALOG_Contributor.*} FROM CATALOG_Contributor INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_Contributor.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETCONTRIBUTORSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Contributor WHERE courseId = ?";
    private static final String _SQL_CONTAINSCONTRIBUTOR = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Contributor WHERE courseId = ? AND contributorId = ?";
    private static final String _SQL_GETCOURSEIMAGES = "SELECT {CATALOG_CourseImage.*} FROM CATALOG_CourseImage INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_CourseImage.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETCOURSEIMAGESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseImage WHERE courseId = ?";
    private static final String _SQL_CONTAINSCOURSEIMAGE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseImage WHERE courseId = ? AND courseImageId = ?";
    private static final String _SQL_GETCOURSERELATEDS = "SELECT {CATALOG_CourseRelated.*} FROM CATALOG_CourseRelated INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_CourseRelated.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETCOURSERELATEDSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseRelated WHERE courseId = ?";
    private static final String _SQL_CONTAINSCOURSERELATED = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseRelated WHERE courseId = ? AND courseRelatedId = ?";
    private static final String _SQL_GETCOURSEREQUIREMENTS = "SELECT {CATALOG_CourseRequirement.*} FROM CATALOG_CourseRequirement INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_CourseRequirement.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETCOURSEREQUIREMENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseRequirement WHERE courseId = ?";
    private static final String _SQL_CONTAINSCOURSEREQUIREMENT = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseRequirement WHERE courseId = ? AND courseRequirementId = ?";
    private static final String _SQL_GETCOURSEREVIEWS = "SELECT {CATALOG_CourseReview.*} FROM CATALOG_CourseReview INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_CourseReview.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETCOURSEREVIEWSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseReview WHERE courseId = ?";
    private static final String _SQL_CONTAINSCOURSEREVIEW = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_CourseReview WHERE courseId = ? AND courseReviewId = ?";
    private static final String _SQL_GETCOURSES_COMPONENTSES = "SELECT {CATALOG_Courses_Components.*} FROM CATALOG_Courses_Components INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_Courses_Components.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETCOURSES_COMPONENTSESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Courses_Components WHERE courseId = ?";
    private static final String _SQL_CONTAINSCOURSES_COMPONENTS = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_Courses_Components WHERE courseId = ? AND coursesComponentsId = ?";
    private static final String _SQL_GETGLOBALCOURSEREVIEWS = "SELECT {CATALOG_GlobalCourseReview.*} FROM CATALOG_GlobalCourseReview INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_GlobalCourseReview.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETGLOBALCOURSEREVIEWSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_GlobalCourseReview WHERE courseId = ?";
    private static final String _SQL_CONTAINSGLOBALCOURSEREVIEW = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_GlobalCourseReview WHERE courseId = ? AND globalCourseReviewId = ?";
    private static final String _SQL_GETEXTERNALLINKS = "SELECT {CATALOG_ExternalLink.*} FROM CATALOG_ExternalLink INNER JOIN CATALOG_Course ON (CATALOG_Course.courseId = CATALOG_ExternalLink.courseId) WHERE (CATALOG_Course.courseId = ?)";
    private static final String _SQL_GETEXTERNALLINKSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ExternalLink WHERE courseId = ?";
    private static final String _SQL_CONTAINSEXTERNALLINK = "SELECT COUNT(*) AS COUNT_VALUE FROM CATALOG_ExternalLink WHERE courseId = ? AND linkId = ?";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "course.courseId = ?";
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "course.groupId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "course.companyId = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_1 = "course.courseIri IS NULL";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_2 = "course.courseIri = ?";
    private static final String _FINDER_COLUMN_COURSEIRI_COURSEIRI_3 = "(course.courseIri IS NULL OR course.courseIri = ?)";
    private static final String _FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2 =
        "course.feedReferenceId = ?";
    private static final String _FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_1 =
        "course.supersededByCourseIri IS NULL";
    private static final String _FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_2 =
        "course.supersededByCourseIri = ?";
    private static final String _FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_3 =
        "(course.supersededByCourseIri IS NULL OR course.supersededByCourseIri = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "course.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Course exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Course exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CoursePersistenceImpl.class);
    private static Course _nullCourse = new CourseImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Course> toCacheModel() {
                return _nullCourseCacheModel;
            }
        };

    private static CacheModel<Course> _nullCourseCacheModel = new CacheModel<Course>() {
            public Course toEntityModel() {
                return _nullCourse;
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
    @BeanReference(type = CompanyPersistence.class)
    protected CompanyPersistence companyPersistence;
    @BeanReference(type = GroupPersistence.class)
    protected GroupPersistence groupPersistence;
    @BeanReference(type = OrganizationPersistence.class)
    protected OrganizationPersistence organizationPersistence;
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
    protected ContainsContributor containsContributor;
    protected ContainsCourseImage containsCourseImage;
    protected ContainsCourseRelated containsCourseRelated;
    protected ContainsCourseRequirement containsCourseRequirement;
    protected ContainsCourseReview containsCourseReview;
    protected ContainsCourses_Components containsCourses_Components;
    protected ContainsGlobalCourseReview containsGlobalCourseReview;
    protected ContainsExternalLink containsExternalLink;

    /**
     * Caches the course in the entity cache if it is enabled.
     *
     * @param course the course
     */
    public void cacheResult(Course course) {
        EntityCacheUtil.putResult(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseImpl.class, course.getPrimaryKey(), course);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEID,
            new Object[] { Long.valueOf(course.getCourseId()) }, course);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEIRI,
            new Object[] { course.getCourseIri() }, course);

        course.resetOriginalValues();
    }

    /**
     * Caches the courses in the entity cache if it is enabled.
     *
     * @param courses the courses
     */
    public void cacheResult(List<Course> courses) {
        for (Course course : courses) {
            if (EntityCacheUtil.getResult(
                        CourseModelImpl.ENTITY_CACHE_ENABLED, CourseImpl.class,
                        course.getPrimaryKey()) == null) {
                cacheResult(course);
            } else {
                course.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all courses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CourseImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CourseImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the course.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Course course) {
        EntityCacheUtil.removeResult(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseImpl.class, course.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(course);
    }

    @Override
    public void clearCache(List<Course> courses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Course course : courses) {
            EntityCacheUtil.removeResult(CourseModelImpl.ENTITY_CACHE_ENABLED,
                CourseImpl.class, course.getPrimaryKey());

            clearUniqueFindersCache(course);
        }
    }

    protected void clearUniqueFindersCache(Course course) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEID,
            new Object[] { Long.valueOf(course.getCourseId()) });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEIRI,
            new Object[] { course.getCourseIri() });
    }

    /**
     * Creates a new course with the primary key. Does not add the course to the database.
     *
     * @param courseId the primary key for the new course
     * @return the new course
     */
    public Course create(long courseId) {
        Course course = new CourseImpl();

        course.setNew(true);
        course.setPrimaryKey(courseId);

        return course;
    }

    /**
     * Removes the course with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseId the primary key of the course
     * @return the course that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course remove(long courseId)
        throws NoSuchCourseException, SystemException {
        return remove(Long.valueOf(courseId));
    }

    /**
     * Removes the course with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the course
     * @return the course that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Course remove(Serializable primaryKey)
        throws NoSuchCourseException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Course course = (Course) session.get(CourseImpl.class, primaryKey);

            if (course == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(course);
        } catch (NoSuchCourseException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Course removeImpl(Course course) throws SystemException {
        course = toUnwrappedModel(course);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, course);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(course);

        return course;
    }

    @Override
    public Course updateImpl(
        org.nterlearning.datamodel.catalog.model.Course course, boolean merge)
        throws SystemException {
        course = toUnwrappedModel(course);

        boolean isNew = course.isNew();

        CourseModelImpl courseModelImpl = (CourseModelImpl) course;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, course, merge);

            course.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CourseModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseModelImpl.getOriginalGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] { Long.valueOf(courseModelImpl.getGroupId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }

            if ((courseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseModelImpl.getOriginalCompanyId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] { Long.valueOf(courseModelImpl.getCompanyId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((courseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseModelImpl.getOriginalFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);

                args = new Object[] {
                        Long.valueOf(courseModelImpl.getFeedReferenceId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FEEDREFERENCEID,
                    args);
            }

            if ((courseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courseModelImpl.getOriginalSupersededByCourseIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPERSEDEDBYCOURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI,
                    args);

                args = new Object[] { courseModelImpl.getSupersededByCourseIri() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPERSEDEDBYCOURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI,
                    args);
            }
        }

        EntityCacheUtil.putResult(CourseModelImpl.ENTITY_CACHE_ENABLED,
            CourseImpl.class, course.getPrimaryKey(), course);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEID,
                new Object[] { Long.valueOf(course.getCourseId()) }, course);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                new Object[] { course.getCourseIri() }, course);
        } else {
            if ((courseModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseModelImpl.getOriginalCourseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEID, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEID,
                    new Object[] { Long.valueOf(course.getCourseId()) }, course);
            }

            if ((courseModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COURSEIRI.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        courseModelImpl.getOriginalCourseIri()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                    new Object[] { course.getCourseIri() }, course);
            }
        }

        return course;
    }

    protected Course toUnwrappedModel(Course course) {
        if (course instanceof CourseImpl) {
            return course;
        }

        CourseImpl courseImpl = new CourseImpl();

        courseImpl.setNew(course.isNew());
        courseImpl.setPrimaryKey(course.getPrimaryKey());

        courseImpl.setCourseId(course.getCourseId());
        courseImpl.setCompanyId(course.getCompanyId());
        courseImpl.setGroupId(course.getGroupId());
        courseImpl.setUserId(course.getUserId());
        courseImpl.setFeedReferenceId(course.getFeedReferenceId());
        courseImpl.setHref(course.getHref());
        courseImpl.setFullTextHref(course.getFullTextHref());
        courseImpl.setCourseIri(course.getCourseIri());
        courseImpl.setUpdatedDate(course.getUpdatedDate());
        courseImpl.setTitle(course.getTitle());
        courseImpl.setTranscriptAbstract(course.getTranscriptAbstract());
        courseImpl.setDescription(course.getDescription());
        courseImpl.setKeywords(course.getKeywords());
        courseImpl.setCopyright(course.getCopyright());
        courseImpl.setRatingLevel(course.getRatingLevel());
        courseImpl.setRatingReason(course.getRatingReason());
        courseImpl.setDuration(course.getDuration());
        courseImpl.setDurationStandard(course.getDurationStandard());
        courseImpl.setFeaturedStatus(course.getFeaturedStatus());
        courseImpl.setPopularWeight(course.getPopularWeight());
        courseImpl.setAccessCount(course.getAccessCount());
        courseImpl.setCompletedCount(course.getCompletedCount());
        courseImpl.setCreateDate(course.getCreateDate());
        courseImpl.setRemoved(course.isRemoved());
        courseImpl.setRemovedDate(course.getRemovedDate());
        courseImpl.setSupersedesCourseIri(course.getSupersedesCourseIri());
        courseImpl.setSupersededByCourseIri(course.getSupersededByCourseIri());
        courseImpl.setReleaseOnDate(course.getReleaseOnDate());
        courseImpl.setAcceptUntilDate(course.getAcceptUntilDate());
        courseImpl.setVersion(course.getVersion());
        courseImpl.setVersionDate(course.getVersionDate());
        courseImpl.setPrice(course.getPrice());
        courseImpl.setPriceUnit(course.getPriceUnit());
        courseImpl.setPriceTerms(course.getPriceTerms());
        courseImpl.setPriceExpiration(course.getPriceExpiration());
        courseImpl.setOneStarRateCount(course.getOneStarRateCount());
        courseImpl.setTwoStarRateCount(course.getTwoStarRateCount());
        courseImpl.setThreeStarRateCount(course.getThreeStarRateCount());
        courseImpl.setFourStarRateCount(course.getFourStarRateCount());
        courseImpl.setFiveStarRateCount(course.getFiveStarRateCount());

        return courseImpl;
    }

    /**
     * Returns the course with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the course
     * @return the course
     * @throws com.liferay.portal.NoSuchModelException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Course findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
     *
     * @param courseId the primary key of the course
     * @return the course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByPrimaryKey(long courseId)
        throws NoSuchCourseException, SystemException {
        Course course = fetchByPrimaryKey(courseId);

        if (course == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + courseId);
            }

            throw new NoSuchCourseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                courseId);
        }

        return course;
    }

    /**
     * Returns the course with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the course
     * @return the course, or <code>null</code> if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Course fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param courseId the primary key of the course
     * @return the course, or <code>null</code> if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course fetchByPrimaryKey(long courseId) throws SystemException {
        Course course = (Course) EntityCacheUtil.getResult(CourseModelImpl.ENTITY_CACHE_ENABLED,
                CourseImpl.class, courseId);

        if (course == _nullCourse) {
            return null;
        }

        if (course == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                course = (Course) session.get(CourseImpl.class,
                        Long.valueOf(courseId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (course != null) {
                    cacheResult(course);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(CourseModelImpl.ENTITY_CACHE_ENABLED,
                        CourseImpl.class, courseId, _nullCourse);
                }

                closeSession(session);
            }
        }

        return course;
    }

    /**
     * Returns the course where courseId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
     *
     * @param courseId the course ID
     * @return the matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByCourseId(long courseId)
        throws NoSuchCourseException, SystemException {
        Course course = fetchByCourseId(courseId);

        if (course == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchCourseException(msg.toString());
        }

        return course;
    }

    /**
     * Returns the course where courseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param courseId the course ID
     * @return the matching course, or <code>null</code> if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course fetchByCourseId(long courseId) throws SystemException {
        return fetchByCourseId(courseId, true);
    }

    /**
     * Returns the course where courseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param courseId the course ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching course, or <code>null</code> if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course fetchByCourseId(long courseId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COURSEID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_COURSE_WHERE);

            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

            query.append(CourseModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                List<Course> list = q.list();

                result = list;

                Course course = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEID,
                        finderArgs, list);
                } else {
                    course = list.get(0);

                    cacheResult(course);

                    if ((course.getCourseId() != courseId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEID,
                            finderArgs, course);
                    }
                }

                return course;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (Course) result;
            }
        }
    }

    /**
     * Returns all the courses where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByGroupId(long groupId) throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByGroupId(long groupId, int start, int end)
        throws SystemException {
        return findByGroupId(groupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByGroupId(long groupId, int start, int end,
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

        List<Course> list = (List<Course>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSE_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                list = (List<Course>) QueryUtil.list(q, getDialect(), start, end);
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
     * Returns the first course in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        List<Course> list = findByGroupId(groupId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        int count = countByGroupId(groupId);

        List<Course> list = findByGroupId(groupId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses before and after the current course in the ordered set where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the primary key of the current course
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course[] findByGroupId_PrevAndNext(long courseId, long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        Course course = findByPrimaryKey(courseId);

        Session session = null;

        try {
            session = openSession();

            Course[] array = new CourseImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, course, groupId,
                    orderByComparator, true);

            array[1] = course;

            array[2] = getByGroupId_PrevAndNext(session, course, groupId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Course getByGroupId_PrevAndNext(Session session, Course course,
        long groupId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSE_WHERE);

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
            query.append(CourseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(course);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Course> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByCompanyId(long companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the courses where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByCompanyId(long companyId, int start, int end)
        throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByCompanyId(long companyId, int start, int end,
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

        List<Course> list = (List<Course>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSE_WHERE);

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyId);

                list = (List<Course>) QueryUtil.list(q, getDialect(), start, end);
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
     * Returns the first course in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByCompanyId_First(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        List<Course> list = findByCompanyId(companyId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByCompanyId_Last(long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        int count = countByCompanyId(companyId);

        List<Course> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyId=");
            msg.append(companyId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses before and after the current course in the ordered set where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the primary key of the current course
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course[] findByCompanyId_PrevAndNext(long courseId, long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        Course course = findByPrimaryKey(courseId);

        Session session = null;

        try {
            session = openSession();

            Course[] array = new CourseImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, course, companyId,
                    orderByComparator, true);

            array[1] = course;

            array[2] = getByCompanyId_PrevAndNext(session, course, companyId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Course getByCompanyId_PrevAndNext(Session session, Course course,
        long companyId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSE_WHERE);

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
            query.append(CourseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(course);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Course> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the course where courseIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseException} if it could not be found.
     *
     * @param courseIri the course iri
     * @return the matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByCourseIri(String courseIri)
        throws NoSuchCourseException, SystemException {
        Course course = fetchByCourseIri(courseIri);

        if (course == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseIri=");
            msg.append(courseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchCourseException(msg.toString());
        }

        return course;
    }

    /**
     * Returns the course where courseIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param courseIri the course iri
     * @return the matching course, or <code>null</code> if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course fetchByCourseIri(String courseIri) throws SystemException {
        return fetchByCourseIri(courseIri, true);
    }

    /**
     * Returns the course where courseIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param courseIri the course iri
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching course, or <code>null</code> if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course fetchByCourseIri(String courseIri, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseIri };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_COURSE_WHERE);

            if (courseIri == null) {
                query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_1);
            } else {
                if (courseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIRI_COURSEIRI_2);
                }
            }

            query.append(CourseModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseIri != null) {
                    qPos.add(courseIri);
                }

                List<Course> list = q.list();

                result = list;

                Course course = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                        finderArgs, list);
                } else {
                    course = list.get(0);

                    cacheResult(course);

                    if ((course.getCourseIri() == null) ||
                            !course.getCourseIri().equals(courseIri)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                            finderArgs, course);
                    }
                }

                return course;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COURSEIRI,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (Course) result;
            }
        }
    }

    /**
     * Returns all the courses where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        return findByFeedReferenceId(feedReferenceId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByFeedReferenceId(long feedReferenceId, int start,
        int end) throws SystemException {
        return findByFeedReferenceId(feedReferenceId, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findByFeedReferenceId(long feedReferenceId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<Course> list = (List<Course>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSE_WHERE);

            query.append(_FINDER_COLUMN_FEEDREFERENCEID_FEEDREFERENCEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(feedReferenceId);

                list = (List<Course>) QueryUtil.list(q, getDialect(), start, end);
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
     * Returns the first course in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByFeedReferenceId_First(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        List<Course> list = findByFeedReferenceId(feedReferenceId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findByFeedReferenceId_Last(long feedReferenceId,
        OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        int count = countByFeedReferenceId(feedReferenceId);

        List<Course> list = findByFeedReferenceId(feedReferenceId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("feedReferenceId=");
            msg.append(feedReferenceId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses before and after the current course in the ordered set where feedReferenceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the primary key of the current course
     * @param feedReferenceId the feed reference ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course[] findByFeedReferenceId_PrevAndNext(long courseId,
        long feedReferenceId, OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        Course course = findByPrimaryKey(courseId);

        Session session = null;

        try {
            session = openSession();

            Course[] array = new CourseImpl[3];

            array[0] = getByFeedReferenceId_PrevAndNext(session, course,
                    feedReferenceId, orderByComparator, true);

            array[1] = course;

            array[2] = getByFeedReferenceId_PrevAndNext(session, course,
                    feedReferenceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Course getByFeedReferenceId_PrevAndNext(Session session,
        Course course, long feedReferenceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSE_WHERE);

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
            query.append(CourseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(feedReferenceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(course);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Course> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses where supersededByCourseIri = &#63;.
     *
     * @param supersededByCourseIri the superseded by course iri
     * @return the matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findBySupersededByCourseIri(
        String supersededByCourseIri) throws SystemException {
        return findBySupersededByCourseIri(supersededByCourseIri,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses where supersededByCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param supersededByCourseIri the superseded by course iri
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findBySupersededByCourseIri(
        String supersededByCourseIri, int start, int end)
        throws SystemException {
        return findBySupersededByCourseIri(supersededByCourseIri, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the courses where supersededByCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param supersededByCourseIri the superseded by course iri
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findBySupersededByCourseIri(
        String supersededByCourseIri, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI;
            finderArgs = new Object[] { supersededByCourseIri };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPERSEDEDBYCOURSEIRI;
            finderArgs = new Object[] {
                    supersededByCourseIri,
                    
                    start, end, orderByComparator
                };
        }

        List<Course> list = (List<Course>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COURSE_WHERE);

            if (supersededByCourseIri == null) {
                query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_1);
            } else {
                if (supersededByCourseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (supersededByCourseIri != null) {
                    qPos.add(supersededByCourseIri);
                }

                list = (List<Course>) QueryUtil.list(q, getDialect(), start, end);
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
     * Returns the first course in the ordered set where supersededByCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param supersededByCourseIri the superseded by course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findBySupersededByCourseIri_First(
        String supersededByCourseIri, OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        List<Course> list = findBySupersededByCourseIri(supersededByCourseIri,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("supersededByCourseIri=");
            msg.append(supersededByCourseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course in the ordered set where supersededByCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param supersededByCourseIri the superseded by course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a matching course could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course findBySupersededByCourseIri_Last(
        String supersededByCourseIri, OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        int count = countBySupersededByCourseIri(supersededByCourseIri);

        List<Course> list = findBySupersededByCourseIri(supersededByCourseIri,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("supersededByCourseIri=");
            msg.append(supersededByCourseIri);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the courses before and after the current course in the ordered set where supersededByCourseIri = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the primary key of the current course
     * @param supersededByCourseIri the superseded by course iri
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseException if a course with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Course[] findBySupersededByCourseIri_PrevAndNext(long courseId,
        String supersededByCourseIri, OrderByComparator orderByComparator)
        throws NoSuchCourseException, SystemException {
        Course course = findByPrimaryKey(courseId);

        Session session = null;

        try {
            session = openSession();

            Course[] array = new CourseImpl[3];

            array[0] = getBySupersededByCourseIri_PrevAndNext(session, course,
                    supersededByCourseIri, orderByComparator, true);

            array[1] = course;

            array[2] = getBySupersededByCourseIri_PrevAndNext(session, course,
                    supersededByCourseIri, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Course getBySupersededByCourseIri_PrevAndNext(Session session,
        Course course, String supersededByCourseIri,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSE_WHERE);

        if (supersededByCourseIri == null) {
            query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_1);
        } else {
            if (supersededByCourseIri.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_3);
            } else {
                query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_2);
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
            query.append(CourseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (supersededByCourseIri != null) {
            qPos.add(supersededByCourseIri);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(course);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Course> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the courses.
     *
     * @return the courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the courses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the courses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of courses
     * @throws SystemException if a system exception occurred
     */
    public List<Course> findAll(int start, int end,
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

        List<Course> list = (List<Course>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSE.concat(CourseModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<Course>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Course>) QueryUtil.list(q, getDialect(),
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
     * Removes the course where courseId = &#63; from the database.
     *
     * @param courseId the course ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(long courseId)
        throws NoSuchCourseException, SystemException {
        Course course = findByCourseId(courseId);

        remove(course);
    }

    /**
     * Removes all the courses where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByGroupId(long groupId) throws SystemException {
        for (Course course : findByGroupId(groupId)) {
            remove(course);
        }
    }

    /**
     * Removes all the courses where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCompanyId(long companyId) throws SystemException {
        for (Course course : findByCompanyId(companyId)) {
            remove(course);
        }
    }

    /**
     * Removes the course where courseIri = &#63; from the database.
     *
     * @param courseIri the course iri
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIri(String courseIri)
        throws NoSuchCourseException, SystemException {
        Course course = findByCourseIri(courseIri);

        remove(course);
    }

    /**
     * Removes all the courses where feedReferenceId = &#63; from the database.
     *
     * @param feedReferenceId the feed reference ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        for (Course course : findByFeedReferenceId(feedReferenceId)) {
            remove(course);
        }
    }

    /**
     * Removes all the courses where supersededByCourseIri = &#63; from the database.
     *
     * @param supersededByCourseIri the superseded by course iri
     * @throws SystemException if a system exception occurred
     */
    public void removeBySupersededByCourseIri(String supersededByCourseIri)
        throws SystemException {
        for (Course course : findBySupersededByCourseIri(supersededByCourseIri)) {
            remove(course);
        }
    }

    /**
     * Removes all the courses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (Course course : findAll()) {
            remove(course);
        }
    }

    /**
     * Returns the number of courses where courseId = &#63;.
     *
     * @param courseId the course ID
     * @return the number of matching courses
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(long courseId) throws SystemException {
        Object[] finderArgs = new Object[] { courseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSE_WHERE);

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
     * Returns the number of courses where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching courses
     * @throws SystemException if a system exception occurred
     */
    public int countByGroupId(long groupId) throws SystemException {
        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSE_WHERE);

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
     * Returns the number of courses where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching courses
     * @throws SystemException if a system exception occurred
     */
    public int countByCompanyId(long companyId) throws SystemException {
        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSE_WHERE);

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
     * Returns the number of courses where courseIri = &#63;.
     *
     * @param courseIri the course iri
     * @return the number of matching courses
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIri(String courseIri) throws SystemException {
        Object[] finderArgs = new Object[] { courseIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSE_WHERE);

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
     * Returns the number of courses where feedReferenceId = &#63;.
     *
     * @param feedReferenceId the feed reference ID
     * @return the number of matching courses
     * @throws SystemException if a system exception occurred
     */
    public int countByFeedReferenceId(long feedReferenceId)
        throws SystemException {
        Object[] finderArgs = new Object[] { feedReferenceId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FEEDREFERENCEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSE_WHERE);

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
     * Returns the number of courses where supersededByCourseIri = &#63;.
     *
     * @param supersededByCourseIri the superseded by course iri
     * @return the number of matching courses
     * @throws SystemException if a system exception occurred
     */
    public int countBySupersededByCourseIri(String supersededByCourseIri)
        throws SystemException {
        Object[] finderArgs = new Object[] { supersededByCourseIri };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPERSEDEDBYCOURSEIRI,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COURSE_WHERE);

            if (supersededByCourseIri == null) {
                query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_1);
            } else {
                if (supersededByCourseIri.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_3);
                } else {
                    query.append(_FINDER_COLUMN_SUPERSEDEDBYCOURSEIRI_SUPERSEDEDBYCOURSEIRI_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (supersededByCourseIri != null) {
                    qPos.add(supersededByCourseIri);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPERSEDEDBYCOURSEIRI,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of courses.
     *
     * @return the number of courses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSE);

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
     * Returns all the contributors associated with the course.
     *
     * @param pk the primary key of the course
     * @return the contributors associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk) throws SystemException {
        return getContributors(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the contributors associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of contributors associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end) throws SystemException {
        return getContributors(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the contributors associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contributors associated with the course
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
     * Returns the number of contributors associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of contributors associated with the course
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
     * Returns <code>true</code> if the contributor is associated with the course.
     *
     * @param pk the primary key of the course
     * @param contributorPK the primary key of the contributor
     * @return <code>true</code> if the contributor is associated with the course; <code>false</code> otherwise
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
     * Returns <code>true</code> if the course has any contributors associated with it.
     *
     * @param pk the primary key of the course to check for associations with contributors
     * @return <code>true</code> if the course has any contributors associated with it; <code>false</code> otherwise
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
     * Returns all the course images associated with the course.
     *
     * @param pk the primary key of the course
     * @return the course images associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk) throws SystemException {
        return getCourseImages(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the course images associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of course images associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk, int start, int end) throws SystemException {
        return getCourseImages(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the course images associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course images associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.CourseImage> list = (List<org.nterlearning.datamodel.catalog.model.CourseImage>) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSEIMAGES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOURSEIMAGES.concat(ORDER_BY_CLAUSE)
                                              .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOURSEIMAGES.concat(org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl.ORDER_BY_SQL);
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_CourseImage",
                    org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.CourseImage>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COURSEIMAGES,
                        finderArgs);
                } else {
                    courseImagePersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COURSEIMAGES,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of course images associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of course images associated with the course
     * @throws SystemException if a system exception occurred
     */
    public int getCourseImagesSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSEIMAGES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOURSEIMAGESSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_COURSEIMAGES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the course image is associated with the course.
     *
     * @param pk the primary key of the course
     * @param courseImagePK the primary key of the course image
     * @return <code>true</code> if the course image is associated with the course; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseImage(long pk, long courseImagePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, courseImagePK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COURSEIMAGE,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsCourseImage.contains(pk,
                            courseImagePK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COURSEIMAGE,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the course has any course images associated with it.
     *
     * @param pk the primary key of the course to check for associations with course images
     * @return <code>true</code> if the course has any course images associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseImages(long pk) throws SystemException {
        if (getCourseImagesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the course relateds associated with the course.
     *
     * @param pk the primary key of the course
     * @return the course relateds associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk) throws SystemException {
        return getCourseRelateds(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the course relateds associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of course relateds associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk, int start, int end) throws SystemException {
        return getCourseRelateds(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the course relateds associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course relateds associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.CourseRelated> list = (List<org.nterlearning.datamodel.catalog.model.CourseRelated>) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSERELATEDS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOURSERELATEDS.concat(ORDER_BY_CLAUSE)
                                                .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOURSERELATEDS;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_CourseRelated",
                    org.nterlearning.datamodel.catalog.model.impl.CourseRelatedImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.CourseRelated>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COURSERELATEDS,
                        finderArgs);
                } else {
                    courseRelatedPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COURSERELATEDS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of course relateds associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of course relateds associated with the course
     * @throws SystemException if a system exception occurred
     */
    public int getCourseRelatedsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSERELATEDS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOURSERELATEDSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_COURSERELATEDS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the course related is associated with the course.
     *
     * @param pk the primary key of the course
     * @param courseRelatedPK the primary key of the course related
     * @return <code>true</code> if the course related is associated with the course; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseRelated(long pk, long courseRelatedPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, courseRelatedPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COURSERELATED,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsCourseRelated.contains(pk,
                            courseRelatedPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COURSERELATED,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the course has any course relateds associated with it.
     *
     * @param pk the primary key of the course to check for associations with course relateds
     * @return <code>true</code> if the course has any course relateds associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseRelateds(long pk) throws SystemException {
        if (getCourseRelatedsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the course requirements associated with the course.
     *
     * @param pk the primary key of the course
     * @return the course requirements associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk) throws SystemException {
        return getCourseRequirements(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the course requirements associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of course requirements associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk, int start, int end) throws SystemException {
        return getCourseRequirements(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the course requirements associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course requirements associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.CourseRequirement> list = (List<org.nterlearning.datamodel.catalog.model.CourseRequirement>) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSEREQUIREMENTS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOURSEREQUIREMENTS.concat(ORDER_BY_CLAUSE)
                                                    .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOURSEREQUIREMENTS;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_CourseRequirement",
                    org.nterlearning.datamodel.catalog.model.impl.CourseRequirementImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.CourseRequirement>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COURSEREQUIREMENTS,
                        finderArgs);
                } else {
                    courseRequirementPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COURSEREQUIREMENTS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of course requirements associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of course requirements associated with the course
     * @throws SystemException if a system exception occurred
     */
    public int getCourseRequirementsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSEREQUIREMENTS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOURSEREQUIREMENTSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_COURSEREQUIREMENTS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the course requirement is associated with the course.
     *
     * @param pk the primary key of the course
     * @param courseRequirementPK the primary key of the course requirement
     * @return <code>true</code> if the course requirement is associated with the course; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseRequirement(long pk, long courseRequirementPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, courseRequirementPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COURSEREQUIREMENT,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsCourseRequirement.contains(pk,
                            courseRequirementPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COURSEREQUIREMENT,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the course has any course requirements associated with it.
     *
     * @param pk the primary key of the course to check for associations with course requirements
     * @return <code>true</code> if the course has any course requirements associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseRequirements(long pk)
        throws SystemException {
        if (getCourseRequirementsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the course reviews associated with the course.
     *
     * @param pk the primary key of the course
     * @return the course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk) throws SystemException {
        return getCourseReviews(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the course reviews associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk, int start, int end) throws SystemException {
        return getCourseReviews(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the course reviews associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.CourseReview> list = (List<org.nterlearning.datamodel.catalog.model.CourseReview>) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSEREVIEWS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETCOURSEREVIEWS.concat(ORDER_BY_CLAUSE)
                                               .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETCOURSEREVIEWS.concat(org.nterlearning.datamodel.catalog.model.impl.CourseReviewModelImpl.ORDER_BY_SQL);
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_CourseReview",
                    org.nterlearning.datamodel.catalog.model.impl.CourseReviewImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.CourseReview>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_COURSEREVIEWS,
                        finderArgs);
                } else {
                    courseReviewPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_COURSEREVIEWS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of course reviews associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public int getCourseReviewsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_COURSEREVIEWS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETCOURSEREVIEWSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_COURSEREVIEWS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the course review is associated with the course.
     *
     * @param pk the primary key of the course
     * @param courseReviewPK the primary key of the course review
     * @return <code>true</code> if the course review is associated with the course; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseReview(long pk, long courseReviewPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, courseReviewPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_COURSEREVIEW,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsCourseReview.contains(pk,
                            courseReviewPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_COURSEREVIEW,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the course has any course reviews associated with it.
     *
     * @param pk the primary key of the course to check for associations with course reviews
     * @return <code>true</code> if the course has any course reviews associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsCourseReviews(long pk) throws SystemException {
        if (getCourseReviewsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the courses_ componentses associated with the course.
     *
     * @param pk the primary key of the course
     * @return the courses_ componentses associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk) throws SystemException {
        return getCourses_Componentses(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the courses_ componentses associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of courses_ componentses associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end) throws SystemException {
        return getCourses_Componentses(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the courses_ componentses associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of courses_ componentses associated with the course
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
     * Returns the number of courses_ componentses associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of courses_ componentses associated with the course
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
     * Returns <code>true</code> if the courses_ components is associated with the course.
     *
     * @param pk the primary key of the course
     * @param courses_ComponentsPK the primary key of the courses_ components
     * @return <code>true</code> if the courses_ components is associated with the course; <code>false</code> otherwise
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
     * Returns <code>true</code> if the course has any courses_ componentses associated with it.
     *
     * @param pk the primary key of the course to check for associations with courses_ componentses
     * @return <code>true</code> if the course has any courses_ componentses associated with it; <code>false</code> otherwise
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
     * Returns all the global course reviews associated with the course.
     *
     * @param pk the primary key of the course
     * @return the global course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk) throws SystemException {
        return getGlobalCourseReviews(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the global course reviews associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of global course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk, int start, int end) throws SystemException {
        return getGlobalCourseReviews(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the global course reviews associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of global course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> list = (List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) FinderCacheUtil.getResult(FINDER_PATH_GET_GLOBALCOURSEREVIEWS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETGLOBALCOURSEREVIEWS.concat(ORDER_BY_CLAUSE)
                                                     .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETGLOBALCOURSEREVIEWS.concat(org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewModelImpl.ORDER_BY_SQL);
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("CATALOG_GlobalCourseReview",
                    org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_GLOBALCOURSEREVIEWS,
                        finderArgs);
                } else {
                    globalCourseReviewPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_GLOBALCOURSEREVIEWS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of global course reviews associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of global course reviews associated with the course
     * @throws SystemException if a system exception occurred
     */
    public int getGlobalCourseReviewsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_GLOBALCOURSEREVIEWS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETGLOBALCOURSEREVIEWSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_GLOBALCOURSEREVIEWS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the global course review is associated with the course.
     *
     * @param pk the primary key of the course
     * @param globalCourseReviewPK the primary key of the global course review
     * @return <code>true</code> if the global course review is associated with the course; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsGlobalCourseReview(long pk, long globalCourseReviewPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, globalCourseReviewPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_GLOBALCOURSEREVIEW,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsGlobalCourseReview.contains(
                            pk, globalCourseReviewPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_GLOBALCOURSEREVIEW,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the course has any global course reviews associated with it.
     *
     * @param pk the primary key of the course to check for associations with global course reviews
     * @return <code>true</code> if the course has any global course reviews associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsGlobalCourseReviews(long pk)
        throws SystemException {
        if (getGlobalCourseReviewsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the external links associated with the course.
     *
     * @param pk the primary key of the course
     * @return the external links associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk) throws SystemException {
        return getExternalLinks(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the external links associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @return the range of external links associated with the course
     * @throws SystemException if a system exception occurred
     */
    public List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end) throws SystemException {
        return getExternalLinks(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the external links associated with the course.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the course
     * @param start the lower bound of the range of courses
     * @param end the upper bound of the range of courses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of external links associated with the course
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
     * Returns the number of external links associated with the course.
     *
     * @param pk the primary key of the course
     * @return the number of external links associated with the course
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
     * Returns <code>true</code> if the external link is associated with the course.
     *
     * @param pk the primary key of the course
     * @param externalLinkPK the primary key of the external link
     * @return <code>true</code> if the external link is associated with the course; <code>false</code> otherwise
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
     * Returns <code>true</code> if the course has any external links associated with it.
     *
     * @param pk the primary key of the course to check for associations with external links
     * @return <code>true</code> if the course has any external links associated with it; <code>false</code> otherwise
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
     * Initializes the course persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.Course")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Course>> listenersList = new ArrayList<ModelListener<Course>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Course>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsContributor = new ContainsContributor();

        containsCourseImage = new ContainsCourseImage();

        containsCourseRelated = new ContainsCourseRelated();

        containsCourseRequirement = new ContainsCourseRequirement();

        containsCourseReview = new ContainsCourseReview();

        containsCourses_Components = new ContainsCourses_Components();

        containsGlobalCourseReview = new ContainsGlobalCourseReview();

        containsExternalLink = new ContainsExternalLink();
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CourseImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    protected class ContainsContributor {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsContributor() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCONTRIBUTOR,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long contributorId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(contributorId)
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

    protected class ContainsCourseImage {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsCourseImage() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOURSEIMAGE,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long courseImageId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(courseImageId)
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

    protected class ContainsCourseRelated {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsCourseRelated() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOURSERELATED,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long courseRelatedId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(courseRelatedId)
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

    protected class ContainsCourseRequirement {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsCourseRequirement() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOURSEREQUIREMENT,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long courseRequirementId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(courseRequirementId)
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

    protected class ContainsCourseReview {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsCourseReview() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOURSEREVIEW,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long courseReviewId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(courseReviewId)
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

    protected class ContainsCourses_Components {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsCourses_Components() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSCOURSES_COMPONENTS,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long coursesComponentsId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(coursesComponentsId)
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

    protected class ContainsGlobalCourseReview {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsGlobalCourseReview() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSGLOBALCOURSEREVIEW,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long courseId, long globalCourseReviewId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(globalCourseReviewId)
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

        protected boolean contains(long courseId, long linkId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(courseId), new Long(linkId)
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
