package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course requirement local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseRequirementLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRequirementLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRequirementLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseRequirementLocalServiceImpl
 * @generated
 */
public class CourseRequirementLocalServiceUtil {
    private static CourseRequirementLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseRequirementLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the course requirement to the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @return the course requirement that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement addCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addCourseRequirement(courseRequirement);
    }

    /**
    * Creates a new course requirement with the primary key. Does not add the course requirement to the database.
    *
    * @param courseRequirementId the primary key for the new course requirement
    * @return the new course requirement
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement createCourseRequirement(
        long courseRequirementId) {
        return getService().createCourseRequirement(courseRequirementId);
    }

    /**
    * Deletes the course requirement with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @throws PortalException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourseRequirement(long courseRequirementId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourseRequirement(courseRequirementId);
    }

    /**
    * Deletes the course requirement from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourseRequirement(courseRequirement);
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

    public static org.nterlearning.datamodel.catalog.model.CourseRequirement fetchCourseRequirement(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCourseRequirement(courseRequirementId);
    }

    /**
    * Returns the course requirement with the primary key.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement
    * @throws PortalException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement getCourseRequirement(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRequirement(courseRequirementId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the course requirements.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course requirements
    * @param end the upper bound of the range of course requirements (not inclusive)
    * @return the range of course requirements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> getCourseRequirements(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRequirements(start, end);
    }

    /**
    * Returns the number of course requirements.
    *
    * @return the number of course requirements
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseRequirementsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRequirementsCount();
    }

    /**
    * Updates the course requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @return the course requirement that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement updateCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourseRequirement(courseRequirement);
    }

    /**
    * Updates the course requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRequirement the course requirement
    * @param merge whether to merge the course requirement with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course requirement that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement updateCourseRequirement(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourseRequirement(courseRequirement, merge);
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

    public static void clearService() {
        _service = null;
    }

    public static CourseRequirementLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseRequirementLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseRequirementLocalService.class.getName(),
                    portletClassLoader);

            _service = new CourseRequirementLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseRequirementLocalServiceUtil.class,
                "_service");
            MethodCache.remove(CourseRequirementLocalService.class);
        }

        return _service;
    }

    public void setService(CourseRequirementLocalService service) {
        MethodCache.remove(CourseRequirementLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseRequirementLocalServiceUtil.class,
            "_service");
        MethodCache.remove(CourseRequirementLocalService.class);
    }
}
