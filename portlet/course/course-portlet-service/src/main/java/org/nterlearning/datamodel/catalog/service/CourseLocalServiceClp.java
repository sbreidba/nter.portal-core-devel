package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class CourseLocalServiceClp implements CourseLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addCourseMethodKey0;
    private MethodKey _createCourseMethodKey1;
    private MethodKey _deleteCourseMethodKey2;
    private MethodKey _deleteCourseMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchCourseMethodKey8;
    private MethodKey _getCourseMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getCoursesMethodKey11;
    private MethodKey _getCoursesCountMethodKey12;
    private MethodKey _updateCourseMethodKey13;
    private MethodKey _updateCourseMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _findAllValidCoursesMethodKey17;
    private MethodKey _findAllValidCoursesMethodKey18;
    private MethodKey _findAllValidCoursesMethodKey19;
    private MethodKey _findAllValidCoursesMethodKey20;
    private MethodKey _findAllValidCoursesMethodKey21;
    private MethodKey _countAllValidCoursesMethodKey22;
    private MethodKey _countAllValidCoursesMethodKey23;
    private MethodKey _countAllValidCoursesMethodKey24;
    private MethodKey _countAllValidCoursesMethodKey25;
    private MethodKey _updateReviewHistogramMethodKey26;
    private MethodKey _addCourseMethodKey27;
    private MethodKey _addCourseMethodKey28;
    private MethodKey _addCourseResourcesMethodKey29;
    private MethodKey _addCourseResourcesMethodKey30;
    private MethodKey _addCourseResourcesMethodKey31;
    private MethodKey _addCourseResourcesMethodKey32;
    private MethodKey _markCourseRemovedMethodKey33;
    private MethodKey _setCourseFeaturedStatusMethodKey34;
    private MethodKey _deleteCoursesMethodKey35;
    private MethodKey _deleteAllChildrenMethodKey36;
    private MethodKey _updateAssetMethodKey37;
    private MethodKey _updateAssetMethodKey38;
    private MethodKey _updateCourseHistogramMethodKey39;
    private MethodKey _updateCourseMethodKey40;
    private MethodKey _updateCourseMethodKey41;
    private MethodKey _updateCourseResourcesMethodKey42;
    private MethodKey _updateStatusMethodKey43;
    private MethodKey _countByGroupIdMethodKey44;
    private MethodKey _findByCourseIdMethodKey45;
    private MethodKey _fetchByCourseIdMethodKey46;
    private MethodKey _findByCourseIriMethodKey47;
    private MethodKey _fetchByCourseIriMethodKey48;
    private MethodKey _findByGroupIdMethodKey49;
    private MethodKey _findByGroupIdMethodKey50;
    private MethodKey _findByCompanyIdMethodKey51;
    private MethodKey _findByCompanyIdMethodKey52;
    private MethodKey _findByFeedReferenceIdMethodKey53;
    private MethodKey _findBySupersededByCourseIriMethodKey54;
    private MethodKey _findBySupersededByCourseIriMethodKey55;
    private MethodKey _findAllCoursesMethodKey56;
    private MethodKey _countAllCoursesMethodKey57;
    private MethodKey _getContributorsMethodKey58;
    private MethodKey _getContributorsMethodKey59;
    private MethodKey _getCourseImagesMethodKey60;
    private MethodKey _getCourseImagesMethodKey61;
    private MethodKey _getCourseRelatedsMethodKey62;
    private MethodKey _getCourseRelatedsMethodKey63;
    private MethodKey _getCourseRequirementsMethodKey64;
    private MethodKey _getCourseRequirementsMethodKey65;
    private MethodKey _getCourseReviewsMethodKey66;
    private MethodKey _getGlobalCourseReviewsMethodKey67;
    private MethodKey _getCourses_ComponentsesMethodKey68;
    private MethodKey _getCourses_ComponentsesMethodKey69;
    private MethodKey _findAssetCategoryByG_NMethodKey70;
    private MethodKey _findAllAssetEntriesMethodKey71;
    private MethodKey _getCategoryAssetEntriesMethodKey72;
    private MethodKey _getExternalLinksMethodKey73;
    private MethodKey _getExternalLinksMethodKey74;
    private MethodKey _containsCategoryAssetEntriesMethodKey75;
    private MethodKey _findAllAssetVocabulariesMethodKey76;
    private MethodKey _fetchVocabularyByG_NMethodKey77;
    private MethodKey _searchMethodKey78;
    private MethodKey _searchMethodKey79;
    private MethodKey _assignAllAccessCountsMethodKey80;
    private MethodKey _assignAllCompletedCountsMethodKey81;
    private MethodKey _assignAllPopularWeightsMethodKey82;
    private MethodKey _clearCacheMethodKey83;
    private MethodKey _clearCacheMethodKey84;
    private MethodKey _clearCacheMethodKey85;

    public CourseLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addCourseMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourse",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _createCourseMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createCourse", long.class);

        _deleteCourseMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteCourse", long.class);

        _deleteCourseMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteCourse",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class);

        _dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class,
                com.liferay.portal.kernel.util.OrderByComparator.class);

        _dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQueryCount",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _fetchCourseMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchCourse", long.class);

        _getCourseMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourse", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getCoursesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourses", int.class, int.class);

        _getCoursesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCoursesCount");

        _updateCourseMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateCourse",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _updateCourseMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateCourse",
                org.nterlearning.datamodel.catalog.model.Course.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _findAllValidCoursesMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses");

        _findAllValidCoursesMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses", int.class, int.class);

        _findAllValidCoursesMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses", java.lang.String.class,
                java.lang.String.class, long.class, int.class, int.class);

        _findAllValidCoursesMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses",
                com.liferay.portlet.asset.model.AssetCategory.class,
                long.class, int.class, int.class);

        _findAllValidCoursesMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllValidCourses", long.class, long.class, int.class,
                int.class);

        _countAllValidCoursesMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "countAllValidCourses");

        _countAllValidCoursesMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "countAllValidCourses", java.lang.String.class, long.class);

        _countAllValidCoursesMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "countAllValidCourses",
                com.liferay.portlet.asset.model.AssetCategory.class, long.class);

        _countAllValidCoursesMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "countAllValidCourses", long.class, long.class);

        _updateReviewHistogramMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateReviewHistogram", long.class);

        _addCourseMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourse", long.class, long.class, java.lang.String.class,
                java.lang.String.class, java.util.Date.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, double.class, java.lang.String.class,
                java.lang.String.class, java.util.Date.class,
                java.util.Date.class, java.lang.String.class,
                java.util.Date.class, double.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.service.ServiceContext.class);

        _addCourseMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourse", long.class, long.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.util.Date.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, double.class,
                java.lang.String.class, java.lang.String.class,
                java.util.Date.class, java.util.Date.class,
                java.lang.String.class, java.util.Date.class, double.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class,
                com.liferay.portal.service.ServiceContext.class);

        _addCourseResourcesMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourseResources",
                org.nterlearning.datamodel.catalog.model.Course.class,
                boolean.class, boolean.class);

        _addCourseResourcesMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourseResources",
                org.nterlearning.datamodel.catalog.model.Course.class,
                java.lang.String[].class, java.lang.String[].class);

        _addCourseResourcesMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourseResources", long.class, boolean.class, boolean.class);

        _addCourseResourcesMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCourseResources", long.class, java.lang.String[].class,
                java.lang.String[].class);

        _markCourseRemovedMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "markCourseRemoved", long.class, boolean.class);

        _setCourseFeaturedStatusMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "setCourseFeaturedStatus", long.class, double.class);

        _deleteCoursesMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteCourses", long.class);

        _deleteAllChildrenMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteAllChildren",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _updateAssetMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAsset", long.class,
                org.nterlearning.datamodel.catalog.model.Course.class,
                long[].class, java.lang.String[].class);

        _updateAssetMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAsset", long.class,
                org.nterlearning.datamodel.catalog.model.Course.class,
                long[].class, java.lang.String[].class, boolean.class);

        _updateCourseHistogramMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateCourseHistogram", long.class, long.class, long.class,
                long.class, long.class, long.class);

        _updateCourseMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateCourse", long.class, long.class, long.class,
                java.lang.String.class, java.lang.String.class,
                java.util.Date.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, double.class,
                double.class, long.class, long.class, boolean.class,
                java.util.Date.class, java.lang.String.class,
                java.lang.String.class, java.util.Date.class,
                java.util.Date.class, java.lang.String.class,
                java.util.Date.class, double.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, long.class,
                long.class, long.class, long.class, long.class,
                com.liferay.portal.service.ServiceContext.class);

        _updateCourseMethodKey41 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateCourse", long.class, long.class, long.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.util.Date.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, double.class, double.class, long.class,
                long.class, boolean.class, java.util.Date.class,
                java.lang.String.class, java.lang.String.class,
                java.util.Date.class, java.util.Date.class,
                java.lang.String.class, java.util.Date.class, double.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class, long.class, long.class, long.class,
                long.class, long.class,
                com.liferay.portal.service.ServiceContext.class);

        _updateCourseResourcesMethodKey42 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateCourseResources",
                org.nterlearning.datamodel.catalog.model.Course.class,
                java.lang.String[].class, java.lang.String[].class);

        _updateStatusMethodKey43 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateStatus", long.class, long.class, int.class,
                com.liferay.portal.service.ServiceContext.class);

        _countByGroupIdMethodKey44 = new MethodKey(_classLoaderProxy.getClassName(),
                "countByGroupId", long.class);

        _findByCourseIdMethodKey45 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseId", long.class);

        _fetchByCourseIdMethodKey46 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchByCourseId", long.class);

        _findByCourseIriMethodKey47 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCourseIri", java.lang.String.class);

        _fetchByCourseIriMethodKey48 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchByCourseIri", java.lang.String.class);

        _findByGroupIdMethodKey49 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByGroupId", long.class);

        _findByGroupIdMethodKey50 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByGroupId", long.class, int.class, int.class);

        _findByCompanyIdMethodKey51 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCompanyId", long.class);

        _findByCompanyIdMethodKey52 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCompanyId", long.class, int.class, int.class);

        _findByFeedReferenceIdMethodKey53 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByFeedReferenceId", long.class);

        _findBySupersededByCourseIriMethodKey54 = new MethodKey(_classLoaderProxy.getClassName(),
                "findBySupersededByCourseIri", java.lang.String.class);

        _findBySupersededByCourseIriMethodKey55 = new MethodKey(_classLoaderProxy.getClassName(),
                "findBySupersededByCourseIri", java.lang.String.class,
                int.class, int.class);

        _findAllCoursesMethodKey56 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllCourses", int.class, int.class);

        _countAllCoursesMethodKey57 = new MethodKey(_classLoaderProxy.getClassName(),
                "countAllCourses");

        _getContributorsMethodKey58 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContributors",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getContributorsMethodKey59 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContributors", long.class);

        _getCourseImagesMethodKey60 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseImages",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getCourseImagesMethodKey61 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseImages", long.class);

        _getCourseRelatedsMethodKey62 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseRelateds",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getCourseRelatedsMethodKey63 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseRelateds", long.class);

        _getCourseRequirementsMethodKey64 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseRequirements",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getCourseRequirementsMethodKey65 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseRequirements", long.class);

        _getCourseReviewsMethodKey66 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourseReviews",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getGlobalCourseReviewsMethodKey67 = new MethodKey(_classLoaderProxy.getClassName(),
                "getGlobalCourseReviews",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getCourses_ComponentsesMethodKey68 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourses_Componentses",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getCourses_ComponentsesMethodKey69 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCourses_Componentses", long.class);

        _findAssetCategoryByG_NMethodKey70 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAssetCategoryByG_N", long.class, java.lang.String.class);

        _findAllAssetEntriesMethodKey71 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllAssetEntries");

        _getCategoryAssetEntriesMethodKey72 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoryAssetEntries", long.class);

        _getExternalLinksMethodKey73 = new MethodKey(_classLoaderProxy.getClassName(),
                "getExternalLinks",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _getExternalLinksMethodKey74 = new MethodKey(_classLoaderProxy.getClassName(),
                "getExternalLinks", long.class);

        _containsCategoryAssetEntriesMethodKey75 = new MethodKey(_classLoaderProxy.getClassName(),
                "containsCategoryAssetEntries", long.class);

        _findAllAssetVocabulariesMethodKey76 = new MethodKey(_classLoaderProxy.getClassName(),
                "findAllAssetVocabularies");

        _fetchVocabularyByG_NMethodKey77 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchVocabularyByG_N", long.class, java.lang.String.class);

        _searchMethodKey78 = new MethodKey(_classLoaderProxy.getClassName(),
                "search", long.class, java.lang.String.class, boolean.class,
                int.class, int.class);

        _searchMethodKey79 = new MethodKey(_classLoaderProxy.getClassName(),
                "search", long.class, long.class, java.lang.String.class,
                boolean.class, int.class, int.class);

        _assignAllAccessCountsMethodKey80 = new MethodKey(_classLoaderProxy.getClassName(),
                "assignAllAccessCounts");

        _assignAllCompletedCountsMethodKey81 = new MethodKey(_classLoaderProxy.getClassName(),
                "assignAllCompletedCounts");

        _assignAllPopularWeightsMethodKey82 = new MethodKey(_classLoaderProxy.getClassName(),
                "assignAllPopularWeights", double.class, double.class,
                double.class);

        _clearCacheMethodKey83 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache");

        _clearCacheMethodKey84 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache",
                org.nterlearning.datamodel.catalog.model.Course.class);

        _clearCacheMethodKey85 = new MethodKey(_classLoaderProxy.getClassName(),
                "clearCache", java.lang.Long.class);
    }

    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addCourseMethodKey0,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course createCourse(
        long courseId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createCourseMethodKey1,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteCourse(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteCourseMethodKey2,
                courseId);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void deleteCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteCourseMethodKey3,
                ClpSerializer.translateInput(course));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
                ClpSerializer.translateInput(dynamicQuery));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
                ClpSerializer.translateInput(dynamicQuery), start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
                ClpSerializer.translateInput(dynamicQuery), start, end,
                ClpSerializer.translateInput(orderByComparator));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
                ClpSerializer.translateInput(dynamicQuery));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public org.nterlearning.datamodel.catalog.model.Course fetchCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchCourseMethodKey8,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course getCourse(
        long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseMethodKey9,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPersistedModelMethodKey10,
                ClpSerializer.translateInput(primaryKeyObj));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.model.PersistedModel) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> getCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCoursesMethodKey11,
                start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public int getCoursesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCoursesCountMethodKey12);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateCourseMethodKey13,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        org.nterlearning.datamodel.catalog.model.Course course, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateCourseMethodKey14,
                ClpSerializer.translateInput(course), merge);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey15);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey16,
                ClpSerializer.translateInput(beanIdentifier));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey17);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey18,
                start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        java.lang.String filterSQL, java.lang.String sortSQL, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey19,
                ClpSerializer.translateInput(filterSQL),
                ClpSerializer.translateInput(sortSQL), groupId, start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey20,
                ClpSerializer.translateInput(category), groupId, start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllValidCourses(
        long vocabularyId, long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllValidCoursesMethodKey21,
                vocabularyId, groupId, start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public long countAllValidCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countAllValidCoursesMethodKey22);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public long countAllValidCourses(java.lang.String filterSQL, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countAllValidCoursesMethodKey23,
                ClpSerializer.translateInput(filterSQL), groupId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public long countAllValidCourses(
        com.liferay.portlet.asset.model.AssetCategory category, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countAllValidCoursesMethodKey24,
                ClpSerializer.translateInput(category), groupId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public long countAllValidCourses(long vocabularyId, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countAllValidCoursesMethodKey25,
                vocabularyId, groupId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public void updateReviewHistogram(long courseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateReviewHistogramMethodKey26,
                courseId);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        long userId, long feedReferenceId, java.lang.String href,
        java.lang.String courseIri, java.util.Date updatedDate,
        java.lang.String title, java.lang.String transcriptAbstract,
        java.lang.String description, java.lang.String keywords,
        java.lang.String copyright, java.lang.String ratingLevel,
        java.lang.String ratingReason, java.lang.String duration,
        java.lang.String durationStandard, double featuredStatus,
        java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addCourseMethodKey27,
                userId, feedReferenceId, ClpSerializer.translateInput(href),
                ClpSerializer.translateInput(courseIri),
                ClpSerializer.translateInput(updatedDate),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(transcriptAbstract),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(keywords),
                ClpSerializer.translateInput(copyright),
                ClpSerializer.translateInput(ratingLevel),
                ClpSerializer.translateInput(ratingReason),
                ClpSerializer.translateInput(duration),
                ClpSerializer.translateInput(durationStandard), featuredStatus,
                ClpSerializer.translateInput(supersedesCourseIri),
                ClpSerializer.translateInput(supersededByCourseIri),
                ClpSerializer.translateInput(releaseOnDate),
                ClpSerializer.translateInput(acceptUntilDate),
                ClpSerializer.translateInput(version),
                ClpSerializer.translateInput(versionDate), price,
                ClpSerializer.translateInput(priceUnit),
                ClpSerializer.translateInput(priceTerms),
                ClpSerializer.translateInput(priceExpiration),
                ClpSerializer.translateInput(serviceContext));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course addCourse(
        long userId, long feedReferenceId, java.lang.String href,
        java.lang.String fullTextHref, java.lang.String courseIri,
        java.util.Date updatedDate, java.lang.String title,
        java.lang.String transcriptAbstract, java.lang.String description,
        java.lang.String keywords, java.lang.String copyright,
        java.lang.String ratingLevel, java.lang.String ratingReason,
        java.lang.String duration, java.lang.String durationStandard,
        double featuredStatus, java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addCourseMethodKey28,
                userId, feedReferenceId, ClpSerializer.translateInput(href),
                ClpSerializer.translateInput(fullTextHref),
                ClpSerializer.translateInput(courseIri),
                ClpSerializer.translateInput(updatedDate),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(transcriptAbstract),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(keywords),
                ClpSerializer.translateInput(copyright),
                ClpSerializer.translateInput(ratingLevel),
                ClpSerializer.translateInput(ratingReason),
                ClpSerializer.translateInput(duration),
                ClpSerializer.translateInput(durationStandard), featuredStatus,
                ClpSerializer.translateInput(supersedesCourseIri),
                ClpSerializer.translateInput(supersededByCourseIri),
                ClpSerializer.translateInput(releaseOnDate),
                ClpSerializer.translateInput(acceptUntilDate),
                ClpSerializer.translateInput(version),
                ClpSerializer.translateInput(versionDate), price,
                ClpSerializer.translateInput(priceUnit),
                ClpSerializer.translateInput(priceTerms),
                ClpSerializer.translateInput(priceExpiration),
                ClpSerializer.translateInput(serviceContext));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addCourseResourcesMethodKey29,
                ClpSerializer.translateInput(course), addCommunityPermissions,
                addGuestPermissions);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void addCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addCourseResourcesMethodKey30,
                ClpSerializer.translateInput(course),
                ClpSerializer.translateInput(communityPermissions),
                ClpSerializer.translateInput(guestPermissions));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void addCourseResources(long courseId,
        boolean addCommunityPermissions, boolean addGuestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addCourseResourcesMethodKey31,
                courseId, addCommunityPermissions, addGuestPermissions);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void addCourseResources(long courseId,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addCourseResourcesMethodKey32,
                courseId, ClpSerializer.translateInput(communityPermissions),
                ClpSerializer.translateInput(guestPermissions));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void markCourseRemoved(long courseId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        MethodHandler methodHandler = new MethodHandler(_markCourseRemovedMethodKey33,
                courseId, removed);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.search.SearchException) {
                throw (com.liferay.portal.kernel.search.SearchException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setCourseFeaturedStatus(long courseId, double featured)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        MethodHandler methodHandler = new MethodHandler(_setCourseFeaturedStatusMethodKey34,
                courseId, featured);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.search.SearchException) {
                throw (com.liferay.portal.kernel.search.SearchException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void deleteCourses(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteCoursesMethodKey35,
                groupId);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteAllChildrenMethodKey36,
                ClpSerializer.translateInput(course));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateAssetMethodKey37,
                userId, ClpSerializer.translateInput(course),
                ClpSerializer.translateInput(assetCategoryIds),
                ClpSerializer.translateInput(assetTagNames));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void updateAsset(long userId,
        org.nterlearning.datamodel.catalog.model.Course course,
        long[] assetCategoryIds, java.lang.String[] assetTagNames,
        boolean reIndex)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateAssetMethodKey38,
                userId, ClpSerializer.translateInput(course),
                ClpSerializer.translateInput(assetCategoryIds),
                ClpSerializer.translateInput(assetTagNames), reIndex);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public org.nterlearning.datamodel.catalog.model.Course updateCourseHistogram(
        long courseId, long oneStarsCount, long twoStarsCount,
        long threeStarsCount, long fourStarsCount, long fiveStarsCount)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateCourseHistogramMethodKey39,
                courseId, oneStarsCount, twoStarsCount, threeStarsCount,
                fourStarsCount, fiveStarsCount);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        long userId, long courseId, long feedReferenceId,
        java.lang.String href, java.lang.String courseIri,
        java.util.Date updatedDate, java.lang.String title,
        java.lang.String transcriptAbstract, java.lang.String description,
        java.lang.String keywords, java.lang.String copyright,
        java.lang.String ratingLevel, java.lang.String ratingReason,
        java.lang.String duration, java.lang.String durationStandard,
        double featuredStatus, double popularWeight, long accessCount,
        long completedCount, boolean removed, java.util.Date removedDate,
        java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        long oneStarRateCount, long twoStarRateCount, long threeStarRateCount,
        long fourStarRateCount, long fiveStarRateCount,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateCourseMethodKey40,
                userId, courseId, feedReferenceId,
                ClpSerializer.translateInput(href),
                ClpSerializer.translateInput(courseIri),
                ClpSerializer.translateInput(updatedDate),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(transcriptAbstract),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(keywords),
                ClpSerializer.translateInput(copyright),
                ClpSerializer.translateInput(ratingLevel),
                ClpSerializer.translateInput(ratingReason),
                ClpSerializer.translateInput(duration),
                ClpSerializer.translateInput(durationStandard), featuredStatus,
                popularWeight, accessCount, completedCount, removed,
                ClpSerializer.translateInput(removedDate),
                ClpSerializer.translateInput(supersedesCourseIri),
                ClpSerializer.translateInput(supersededByCourseIri),
                ClpSerializer.translateInput(releaseOnDate),
                ClpSerializer.translateInput(acceptUntilDate),
                ClpSerializer.translateInput(version),
                ClpSerializer.translateInput(versionDate), price,
                ClpSerializer.translateInput(priceUnit),
                ClpSerializer.translateInput(priceTerms),
                ClpSerializer.translateInput(priceExpiration),
                oneStarRateCount, twoStarRateCount, threeStarRateCount,
                fourStarRateCount, fiveStarRateCount,
                ClpSerializer.translateInput(serviceContext));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course updateCourse(
        long userId, long courseId, long feedReferenceId,
        java.lang.String href, java.lang.String fullTextHref,
        java.lang.String courseIri, java.util.Date updatedDate,
        java.lang.String title, java.lang.String transcriptAbstract,
        java.lang.String description, java.lang.String keywords,
        java.lang.String copyright, java.lang.String ratingLevel,
        java.lang.String ratingReason, java.lang.String duration,
        java.lang.String durationStandard, double featuredStatus,
        double popularWeight, long accessCount, long completedCount,
        boolean removed, java.util.Date removedDate,
        java.lang.String supersedesCourseIri,
        java.lang.String supersededByCourseIri, java.util.Date releaseOnDate,
        java.util.Date acceptUntilDate, java.lang.String version,
        java.util.Date versionDate, double price, java.lang.String priceUnit,
        java.lang.String priceTerms, java.lang.String priceExpiration,
        long oneStarRateCount, long twoStarRateCount, long threeStarRateCount,
        long fourStarRateCount, long fiveStarRateCount,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateCourseMethodKey41,
                userId, courseId, feedReferenceId,
                ClpSerializer.translateInput(href),
                ClpSerializer.translateInput(fullTextHref),
                ClpSerializer.translateInput(courseIri),
                ClpSerializer.translateInput(updatedDate),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(transcriptAbstract),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(keywords),
                ClpSerializer.translateInput(copyright),
                ClpSerializer.translateInput(ratingLevel),
                ClpSerializer.translateInput(ratingReason),
                ClpSerializer.translateInput(duration),
                ClpSerializer.translateInput(durationStandard), featuredStatus,
                popularWeight, accessCount, completedCount, removed,
                ClpSerializer.translateInput(removedDate),
                ClpSerializer.translateInput(supersedesCourseIri),
                ClpSerializer.translateInput(supersededByCourseIri),
                ClpSerializer.translateInput(releaseOnDate),
                ClpSerializer.translateInput(acceptUntilDate),
                ClpSerializer.translateInput(version),
                ClpSerializer.translateInput(versionDate), price,
                ClpSerializer.translateInput(priceUnit),
                ClpSerializer.translateInput(priceTerms),
                ClpSerializer.translateInput(priceExpiration),
                oneStarRateCount, twoStarRateCount, threeStarRateCount,
                fourStarRateCount, fiveStarRateCount,
                ClpSerializer.translateInput(serviceContext));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public void updateCourseResources(
        org.nterlearning.datamodel.catalog.model.Course course,
        java.lang.String[] communityPermissions,
        java.lang.String[] guestPermissions)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateCourseResourcesMethodKey42,
                ClpSerializer.translateInput(course),
                ClpSerializer.translateInput(communityPermissions),
                ClpSerializer.translateInput(guestPermissions));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public org.nterlearning.datamodel.catalog.model.Course updateStatus(
        long userId, long courseId, int status,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateStatusMethodKey43,
                userId, courseId, status,
                ClpSerializer.translateInput(serviceContext));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countByGroupIdMethodKey44,
                groupId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIdMethodKey45,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchByCourseIdMethodKey46,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCourseIriMethodKey47,
                ClpSerializer.translateInput(courseIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof org.nterlearning.datamodel.catalog.NoSuchCourseException) {
                throw (org.nterlearning.datamodel.catalog.NoSuchCourseException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public org.nterlearning.datamodel.catalog.model.Course fetchByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchByCourseIriMethodKey48,
                ClpSerializer.translateInput(courseIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (org.nterlearning.datamodel.catalog.model.Course) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByGroupIdMethodKey49,
                groupId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByGroupIdMethodKey50,
                groupId, start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCompanyIdMethodKey51,
                companyId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCompanyIdMethodKey52,
                companyId, start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByFeedReferenceIdMethodKey53,
                feedReferenceId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findBySupersededByCourseIriMethodKey54,
                ClpSerializer.translateInput(courseIri));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findBySupersededByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findBySupersededByCourseIriMethodKey55,
                ClpSerializer.translateInput(courseIri), start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Course> findAllCourses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllCoursesMethodKey56,
                start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Course>) ClpSerializer.translateOutput(returnObj);
    }

    public int countAllCourses()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countAllCoursesMethodKey57);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getContributorsMethodKey58,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Contributor>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getContributorsMethodKey59,
                coursePrimaryKey);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Contributor>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseImagesMethodKey60,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseImagesMethodKey61,
                coursePrimaryKey);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseRelatedsMethodKey62,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> getCourseRelateds(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseRelatedsMethodKey63,
                coursePrimaryKey);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseRequirementsMethodKey64,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseRequirementsMethodKey65,
                coursePrimaryKey);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> getCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourseReviewsMethodKey66,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getGlobalCourseReviewsMethodKey67,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourses_ComponentsesMethodKey68,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long coursePrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCourses_ComponentsesMethodKey69,
                coursePrimaryKey);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components>) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portlet.asset.model.AssetCategory findAssetCategoryByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portlet.asset.NoSuchCategoryException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAssetCategoryByG_NMethodKey70,
                groupId, ClpSerializer.translateInput(name));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof com.liferay.portlet.asset.NoSuchCategoryException) {
                throw (com.liferay.portlet.asset.NoSuchCategoryException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portlet.asset.model.AssetCategory) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.liferay.portlet.asset.model.AssetEntry> findAllAssetEntries()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllAssetEntriesMethodKey71);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.liferay.portlet.asset.model.AssetEntry>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getCategoryAssetEntries(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryAssetEntriesMethodKey72,
                categoryId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.liferay.portlet.asset.model.AssetEntry>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        org.nterlearning.datamodel.catalog.model.Course course)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getExternalLinksMethodKey73,
                ClpSerializer.translateInput(course));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getExternalLinksMethodKey74,
                courseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink>) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Boolean containsCategoryAssetEntries(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_containsCategoryAssetEntriesMethodKey75,
                categoryId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Boolean) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> findAllAssetVocabularies()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findAllAssetVocabulariesMethodKey76);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.liferay.portlet.asset.model.AssetVocabulary>) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portlet.asset.model.AssetVocabulary fetchVocabularyByG_N(
        long groupId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchVocabularyByG_NMethodKey77,
                groupId, ClpSerializer.translateInput(name));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portlet.asset.model.AssetVocabulary) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.search.Hits search(long companyId,
        java.lang.String keywords, boolean andSearch, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_searchMethodKey78,
                companyId, ClpSerializer.translateInput(keywords), andSearch,
                start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.search.Hits) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.search.Hits search(long companyId,
        long groupId, java.lang.String keywords, boolean andSearch, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_searchMethodKey79,
                companyId, groupId, ClpSerializer.translateInput(keywords),
                andSearch, start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.search.Hits) ClpSerializer.translateOutput(returnObj);
    }

    public void assignAllAccessCounts()
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_assignAllAccessCountsMethodKey80);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void assignAllCompletedCounts()
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_assignAllCompletedCountsMethodKey81);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void assignAllPopularWeights(double accessCountWeight,
        double completedCountWeight, double scoreWeight)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_assignAllPopularWeightsMethodKey82,
                accessCountWeight, completedCountWeight, scoreWeight);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void clearCache() {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey83);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void clearCache(
        org.nterlearning.datamodel.catalog.model.Course course) {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey84,
                ClpSerializer.translateInput(course));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void clearCache(java.lang.Long courseId) {
        MethodHandler methodHandler = new MethodHandler(_clearCacheMethodKey85,
                ClpSerializer.translateInput(courseId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
