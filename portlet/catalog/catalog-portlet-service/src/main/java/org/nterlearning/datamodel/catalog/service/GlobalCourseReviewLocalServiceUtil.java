package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the global course review local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReviewLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewLocalServiceImpl
 * @generated
 */
public class GlobalCourseReviewLocalServiceUtil {
    private static GlobalCourseReviewLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.GlobalCourseReviewLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the global course review to the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @return the global course review that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview addGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addGlobalCourseReview(globalCourseReview);
    }

    /**
    * Creates a new global course review with the primary key. Does not add the global course review to the database.
    *
    * @param globalCourseReviewId the primary key for the new global course review
    * @return the new global course review
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview createGlobalCourseReview(
        long globalCourseReviewId) {
        return getService().createGlobalCourseReview(globalCourseReviewId);
    }

    /**
    * Deletes the global course review with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @throws PortalException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteGlobalCourseReview(long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteGlobalCourseReview(globalCourseReviewId);
    }

    /**
    * Deletes the global course review from the database. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @throws SystemException if a system exception occurred
    */
    public static void deleteGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteGlobalCourseReview(globalCourseReview);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview fetchGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchGlobalCourseReview(globalCourseReviewId);
    }

    /**
    * Returns the global course review with the primary key.
    *
    * @param globalCourseReviewId the primary key of the global course review
    * @return the global course review
    * @throws PortalException if a global course review with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview getGlobalCourseReview(
        long globalCourseReviewId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getGlobalCourseReview(globalCourseReviewId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the global course reviews.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of global course reviews
    * @param end the upper bound of the range of global course reviews (not inclusive)
    * @return the range of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> getGlobalCourseReviews(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getGlobalCourseReviews(start, end);
    }

    /**
    * Returns the number of global course reviews.
    *
    * @return the number of global course reviews
    * @throws SystemException if a system exception occurred
    */
    public static int getGlobalCourseReviewsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getGlobalCourseReviewsCount();
    }

    /**
    * Updates the global course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @return the global course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateGlobalCourseReview(globalCourseReview);
    }

    /**
    * Updates the global course review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param globalCourseReview the global course review
    * @param merge whether to merge the global course review with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the global course review that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview updateGlobalCourseReview(
        org.nterlearning.datamodel.catalog.model.GlobalCourseReview globalCourseReview,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateGlobalCourseReview(globalCourseReview, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static void setReviewIsHidden(long globalCourseReviewId,
        java.lang.Boolean hidden)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        getService().setReviewIsHidden(globalCourseReviewId, hidden);
    }

    public static org.nterlearning.datamodel.catalog.model.GlobalCourseReview findByCourseReviewIri(
        java.lang.String courseReviewIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException {
        return getService().findByCourseReviewIri(courseReviewIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseId(courseId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.GlobalCourseReview> findValidByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findValidByCourseId(courseId, start, end);
    }

    public static long countValidByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countValidByCourseId(courseId);
    }

    public static void clearService() {
        _service = null;
    }

    public static GlobalCourseReviewLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    GlobalCourseReviewLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    GlobalCourseReviewLocalService.class.getName(),
                    portletClassLoader);

            _service = new GlobalCourseReviewLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(GlobalCourseReviewLocalServiceUtil.class,
                "_service");
            MethodCache.remove(GlobalCourseReviewLocalService.class);
        }

        return _service;
    }

    public void setService(GlobalCourseReviewLocalService service) {
        MethodCache.remove(GlobalCourseReviewLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(GlobalCourseReviewLocalServiceUtil.class,
            "_service");
        MethodCache.remove(GlobalCourseReviewLocalService.class);
    }
}
