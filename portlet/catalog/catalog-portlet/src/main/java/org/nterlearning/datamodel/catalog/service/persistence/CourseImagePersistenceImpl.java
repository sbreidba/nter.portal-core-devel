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

import org.nterlearning.datamodel.catalog.NoSuchCourseImageException;
import org.nterlearning.datamodel.catalog.model.CourseImage;
import org.nterlearning.datamodel.catalog.model.impl.CourseImageImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseImageModelImpl;
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
 * The persistence implementation for the course image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImagePersistence
 * @see CourseImageUtil
 * @generated
 */
public class CourseImagePersistenceImpl extends BasePersistenceImpl<CourseImage>
    implements CourseImagePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CourseImageUtil} to access the course image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CourseImageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE =
        new FinderPath(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageModelImpl.FINDER_CACHE_ENABLED, CourseImageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCourseIdWithLanguage",
            new String[] {
                Long.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE =
        new FinderPath(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageModelImpl.FINDER_CACHE_ENABLED, CourseImageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCourseIdWithLanguage",
            new String[] { Long.class.getName(), String.class.getName() },
            CourseImageModelImpl.COURSEID_COLUMN_BITMASK |
            CourseImageModelImpl.LANGUAGE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEIDWITHLANGUAGE = new FinderPath(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCourseIdWithLanguage",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageModelImpl.FINDER_CACHE_ENABLED, CourseImageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageModelImpl.FINDER_CACHE_ENABLED, CourseImageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COURSEIMAGE = "SELECT courseImage FROM CourseImage courseImage";
    private static final String _SQL_SELECT_COURSEIMAGE_WHERE = "SELECT courseImage FROM CourseImage courseImage WHERE ";
    private static final String _SQL_COUNT_COURSEIMAGE = "SELECT COUNT(courseImage) FROM CourseImage courseImage";
    private static final String _SQL_COUNT_COURSEIMAGE_WHERE = "SELECT COUNT(courseImage) FROM CourseImage courseImage WHERE ";
    private static final String _FINDER_COLUMN_COURSEIDWITHLANGUAGE_COURSEID_2 = "courseImage.courseId = ? AND ";
    private static final String _FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_1 = "courseImage.language IS NULL";
    private static final String _FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_2 = "courseImage.language = ?";
    private static final String _FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_3 = "(courseImage.language IS NULL OR courseImage.language = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "courseImage.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CourseImage exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CourseImage exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CourseImagePersistenceImpl.class);
    private static CourseImage _nullCourseImage = new CourseImageImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CourseImage> toCacheModel() {
                return _nullCourseImageCacheModel;
            }
        };

    private static CacheModel<CourseImage> _nullCourseImageCacheModel = new CacheModel<CourseImage>() {
            public CourseImage toEntityModel() {
                return _nullCourseImage;
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
     * Caches the course image in the entity cache if it is enabled.
     *
     * @param courseImage the course image
     */
    public void cacheResult(CourseImage courseImage) {
        EntityCacheUtil.putResult(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageImpl.class, courseImage.getPrimaryKey(), courseImage);

        courseImage.resetOriginalValues();
    }

    /**
     * Caches the course images in the entity cache if it is enabled.
     *
     * @param courseImages the course images
     */
    public void cacheResult(List<CourseImage> courseImages) {
        for (CourseImage courseImage : courseImages) {
            if (EntityCacheUtil.getResult(
                        CourseImageModelImpl.ENTITY_CACHE_ENABLED,
                        CourseImageImpl.class, courseImage.getPrimaryKey()) == null) {
                cacheResult(courseImage);
            } else {
                courseImage.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all course images.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CourseImageImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CourseImageImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the course image.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CourseImage courseImage) {
        EntityCacheUtil.removeResult(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageImpl.class, courseImage.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CourseImage> courseImages) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CourseImage courseImage : courseImages) {
            EntityCacheUtil.removeResult(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
                CourseImageImpl.class, courseImage.getPrimaryKey());
        }
    }

    /**
     * Creates a new course image with the primary key. Does not add the course image to the database.
     *
     * @param courseImageId the primary key for the new course image
     * @return the new course image
     */
    public CourseImage create(long courseImageId) {
        CourseImage courseImage = new CourseImageImpl();

        courseImage.setNew(true);
        courseImage.setPrimaryKey(courseImageId);

        return courseImage;
    }

    /**
     * Removes the course image with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param courseImageId the primary key of the course image
     * @return the course image that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseImage remove(long courseImageId)
        throws NoSuchCourseImageException, SystemException {
        return remove(Long.valueOf(courseImageId));
    }

    /**
     * Removes the course image with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the course image
     * @return the course image that was removed
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseImage remove(Serializable primaryKey)
        throws NoSuchCourseImageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CourseImage courseImage = (CourseImage) session.get(CourseImageImpl.class,
                    primaryKey);

            if (courseImage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCourseImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(courseImage);
        } catch (NoSuchCourseImageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CourseImage removeImpl(CourseImage courseImage)
        throws SystemException {
        courseImage = toUnwrappedModel(courseImage);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, courseImage);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(courseImage);

        return courseImage;
    }

    @Override
    public CourseImage updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage,
        boolean merge) throws SystemException {
        courseImage = toUnwrappedModel(courseImage);

        boolean isNew = courseImage.isNew();

        CourseImageModelImpl courseImageModelImpl = (CourseImageModelImpl) courseImage;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, courseImage, merge);

            courseImage.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CourseImageModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((courseImageModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(courseImageModelImpl.getOriginalCourseId()),
                        
                        courseImageModelImpl.getOriginalLanguage()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHLANGUAGE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE,
                    args);

                args = new Object[] {
                        Long.valueOf(courseImageModelImpl.getCourseId()),
                        
                        courseImageModelImpl.getLanguage()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEIDWITHLANGUAGE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE,
                    args);
            }
        }

        EntityCacheUtil.putResult(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
            CourseImageImpl.class, courseImage.getPrimaryKey(), courseImage);

        return courseImage;
    }

    protected CourseImage toUnwrappedModel(CourseImage courseImage) {
        if (courseImage instanceof CourseImageImpl) {
            return courseImage;
        }

        CourseImageImpl courseImageImpl = new CourseImageImpl();

        courseImageImpl.setNew(courseImage.isNew());
        courseImageImpl.setPrimaryKey(courseImage.getPrimaryKey());

        courseImageImpl.setCourseImageId(courseImage.getCourseImageId());
        courseImageImpl.setCourseId(courseImage.getCourseId());
        courseImageImpl.setOrderWeight(courseImage.getOrderWeight());
        courseImageImpl.setLanguage(courseImage.getLanguage());
        courseImageImpl.setImageId(courseImage.getImageId());
        courseImageImpl.setAlternateText(courseImage.getAlternateText());
        courseImageImpl.setSourceImageUrl(courseImage.getSourceImageUrl());
        courseImageImpl.setMimeType(courseImage.getMimeType());

        return courseImageImpl;
    }

    /**
     * Returns the course image with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the course image
     * @return the course image
     * @throws com.liferay.portal.NoSuchModelException if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseImage findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course image with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseImageException} if it could not be found.
     *
     * @param courseImageId the primary key of the course image
     * @return the course image
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseImage findByPrimaryKey(long courseImageId)
        throws NoSuchCourseImageException, SystemException {
        CourseImage courseImage = fetchByPrimaryKey(courseImageId);

        if (courseImage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + courseImageId);
            }

            throw new NoSuchCourseImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                courseImageId);
        }

        return courseImage;
    }

    /**
     * Returns the course image with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the course image
     * @return the course image, or <code>null</code> if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CourseImage fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the course image with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param courseImageId the primary key of the course image
     * @return the course image, or <code>null</code> if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseImage fetchByPrimaryKey(long courseImageId)
        throws SystemException {
        CourseImage courseImage = (CourseImage) EntityCacheUtil.getResult(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
                CourseImageImpl.class, courseImageId);

        if (courseImage == _nullCourseImage) {
            return null;
        }

        if (courseImage == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                courseImage = (CourseImage) session.get(CourseImageImpl.class,
                        Long.valueOf(courseImageId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (courseImage != null) {
                    cacheResult(courseImage);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(CourseImageModelImpl.ENTITY_CACHE_ENABLED,
                        CourseImageImpl.class, courseImageId, _nullCourseImage);
                }

                closeSession(session);
            }
        }

        return courseImage;
    }

    /**
     * Returns all the course images where courseId = &#63; and language = &#63;.
     *
     * @param courseId the course ID
     * @param language the language
     * @return the matching course images
     * @throws SystemException if a system exception occurred
     */
    public List<CourseImage> findByCourseIdWithLanguage(long courseId,
        String language) throws SystemException {
        return findByCourseIdWithLanguage(courseId, language,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course images where courseId = &#63; and language = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param language the language
     * @param start the lower bound of the range of course images
     * @param end the upper bound of the range of course images (not inclusive)
     * @return the range of matching course images
     * @throws SystemException if a system exception occurred
     */
    public List<CourseImage> findByCourseIdWithLanguage(long courseId,
        String language, int start, int end) throws SystemException {
        return findByCourseIdWithLanguage(courseId, language, start, end, null);
    }

    /**
     * Returns an ordered range of all the course images where courseId = &#63; and language = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param language the language
     * @param start the lower bound of the range of course images
     * @param end the upper bound of the range of course images (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching course images
     * @throws SystemException if a system exception occurred
     */
    public List<CourseImage> findByCourseIdWithLanguage(long courseId,
        String language, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE;
            finderArgs = new Object[] { courseId, language };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEIDWITHLANGUAGE;
            finderArgs = new Object[] {
                    courseId, language,
                    
                    start, end, orderByComparator
                };
        }

        List<CourseImage> list = (List<CourseImage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_COURSEIMAGE_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_COURSEID_2);

            if (language == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_1);
            } else {
                if (language.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(CourseImageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                if (language != null) {
                    qPos.add(language);
                }

                list = (List<CourseImage>) QueryUtil.list(q, getDialect(),
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
     * Returns the first course image in the ordered set where courseId = &#63; and language = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param language the language
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching course image
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a matching course image could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseImage findByCourseIdWithLanguage_First(long courseId,
        String language, OrderByComparator orderByComparator)
        throws NoSuchCourseImageException, SystemException {
        List<CourseImage> list = findByCourseIdWithLanguage(courseId, language,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", language=");
            msg.append(language);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseImageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last course image in the ordered set where courseId = &#63; and language = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseId the course ID
     * @param language the language
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching course image
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a matching course image could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseImage findByCourseIdWithLanguage_Last(long courseId,
        String language, OrderByComparator orderByComparator)
        throws NoSuchCourseImageException, SystemException {
        int count = countByCourseIdWithLanguage(courseId, language);

        List<CourseImage> list = findByCourseIdWithLanguage(courseId, language,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("courseId=");
            msg.append(courseId);

            msg.append(", language=");
            msg.append(language);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchCourseImageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the course images before and after the current course image in the ordered set where courseId = &#63; and language = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseImageId the primary key of the current course image
     * @param courseId the course ID
     * @param language the language
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next course image
     * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public CourseImage[] findByCourseIdWithLanguage_PrevAndNext(
        long courseImageId, long courseId, String language,
        OrderByComparator orderByComparator)
        throws NoSuchCourseImageException, SystemException {
        CourseImage courseImage = findByPrimaryKey(courseImageId);

        Session session = null;

        try {
            session = openSession();

            CourseImage[] array = new CourseImageImpl[3];

            array[0] = getByCourseIdWithLanguage_PrevAndNext(session,
                    courseImage, courseId, language, orderByComparator, true);

            array[1] = courseImage;

            array[2] = getByCourseIdWithLanguage_PrevAndNext(session,
                    courseImage, courseId, language, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CourseImage getByCourseIdWithLanguage_PrevAndNext(
        Session session, CourseImage courseImage, long courseId,
        String language, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COURSEIMAGE_WHERE);

        query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_COURSEID_2);

        if (language == null) {
            query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_1);
        } else {
            if (language.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_3);
            } else {
                query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_2);
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
            query.append(CourseImageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(courseId);

        if (language != null) {
            qPos.add(language);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(courseImage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CourseImage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the course images.
     *
     * @return the course images
     * @throws SystemException if a system exception occurred
     */
    public List<CourseImage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the course images.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course images
     * @param end the upper bound of the range of course images (not inclusive)
     * @return the range of course images
     * @throws SystemException if a system exception occurred
     */
    public List<CourseImage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the course images.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of course images
     * @param end the upper bound of the range of course images (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of course images
     * @throws SystemException if a system exception occurred
     */
    public List<CourseImage> findAll(int start, int end,
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

        List<CourseImage> list = (List<CourseImage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COURSEIMAGE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COURSEIMAGE.concat(CourseImageModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<CourseImage>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<CourseImage>) QueryUtil.list(q, getDialect(),
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
     * Removes all the course images where courseId = &#63; and language = &#63; from the database.
     *
     * @param courseId the course ID
     * @param language the language
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseIdWithLanguage(long courseId, String language)
        throws SystemException {
        for (CourseImage courseImage : findByCourseIdWithLanguage(courseId,
                language)) {
            remove(courseImage);
        }
    }

    /**
     * Removes all the course images from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (CourseImage courseImage : findAll()) {
            remove(courseImage);
        }
    }

    /**
     * Returns the number of course images where courseId = &#63; and language = &#63;.
     *
     * @param courseId the course ID
     * @param language the language
     * @return the number of matching course images
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseIdWithLanguage(long courseId, String language)
        throws SystemException {
        Object[] finderArgs = new Object[] { courseId, language };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEIDWITHLANGUAGE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_COURSEIMAGE_WHERE);

            query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_COURSEID_2);

            if (language == null) {
                query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_1);
            } else {
                if (language.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_3);
                } else {
                    query.append(_FINDER_COLUMN_COURSEIDWITHLANGUAGE_LANGUAGE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(courseId);

                if (language != null) {
                    qPos.add(language);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEIDWITHLANGUAGE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of course images.
     *
     * @return the number of course images
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COURSEIMAGE);

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
     * Initializes the course image persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.org.nterlearning.datamodel.catalog.model.CourseImage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CourseImage>> listenersList = new ArrayList<ModelListener<CourseImage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CourseImage>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CourseImageImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
