package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the course record local service. This utility wraps {@link org.nterlearning.datamodel.catalog.service.impl.CourseRecordLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecordLocalService
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRecordLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseRecordLocalServiceImpl
 * @generated
 */
public class CourseRecordLocalServiceUtil {
    private static CourseRecordLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseRecordLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the course record to the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @return the course record that was added
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord addCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addCourseRecord(courseRecord);
    }

    /**
    * Creates a new course record with the primary key. Does not add the course record to the database.
    *
    * @param courseRecordId the primary key for the new course record
    * @return the new course record
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord createCourseRecord(
        long courseRecordId) {
        return getService().createCourseRecord(courseRecordId);
    }

    /**
    * Deletes the course record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecordId the primary key of the course record
    * @throws PortalException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourseRecord(long courseRecordId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourseRecord(courseRecordId);
    }

    /**
    * Deletes the course record from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @throws PortalException
    * @throws SystemException if a system exception occurred
    */
    public static void deleteCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCourseRecord(courseRecord);
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

    public static org.nterlearning.datamodel.catalog.model.CourseRecord fetchCourseRecord(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCourseRecord(courseRecordId);
    }

    /**
    * Returns the course record with the primary key.
    *
    * @param courseRecordId the primary key of the course record
    * @return the course record
    * @throws PortalException if a course record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord getCourseRecord(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRecord(courseRecordId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> getCourseRecords(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRecords(start, end);
    }

    /**
    * Returns the number of course records.
    *
    * @return the number of course records
    * @throws SystemException if a system exception occurred
    */
    public static int getCourseRecordsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCourseRecordsCount();
    }

    /**
    * Updates the course record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @return the course record that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord updateCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourseRecord(courseRecord);
    }

    /**
    * Updates the course record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseRecord the course record
    * @param merge whether to merge the course record with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course record that was updated
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRecord updateCourseRecord(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCourseRecord(courseRecord, merge);
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

    public static org.nterlearning.datamodel.catalog.model.CourseRecord addCourseRecord(
        java.lang.Long feedRefId, java.lang.String courseRecordIRI,
        long userId, java.lang.String singleSignOnValue,
        java.lang.String courseIRI, java.util.Date updatedDate,
        java.lang.String completionStatus, boolean userHidden, boolean assigned)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addCourseRecord(feedRefId, courseRecordIRI, userId,
            singleSignOnValue, courseIRI, updatedDate, completionStatus,
            userHidden, assigned);
    }

    public static void deleteAllChildren(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteAllChildren(courseRecord);
    }

    public static void addCourseRecordResource(
        org.nterlearning.datamodel.catalog.model.CourseRecord record)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addCourseRecordResource(record);
    }

    public static void deleteCourseRecordResource(
        org.nterlearning.datamodel.catalog.model.CourseRecord record) {
        getService().deleteCourseRecordResource(record);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getService().findByPrimaryKey(courseRecordId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecord fetchByPrimaryKey(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByPrimaryKey(courseRecordId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecord findByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getService().findByCourseRecordIri(courseRecordIri);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecord fetchByCourseRecordIri(
        java.lang.String courseRecordIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchByCourseRecordIri(courseRecordIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRecordException {
        return getService().findByCourseIri(courseIri);
    }

    public static java.util.List<java.lang.Object[]> findByUserIdFilterSorted(
        long userId, long classNameId, java.lang.String filterType,
        java.lang.String sortType, boolean asc, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findByUserIdFilterSorted(userId, classNameId, filterType,
            sortType, asc, start, end);
    }

    public static long countByUserIdFilter(long userId, long classNameId,
        java.lang.String filterType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countByUserIdFilter(userId, classNameId, filterType);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findBySingleSignOnValue(singleSignOnValue);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findBySingleSignOnValue(
        java.lang.String singleSignOnValue, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findBySingleSignOnValue(singleSignOnValue, start, end);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUserId(userId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUserId(userId, start, end);
    }

    public static long countAccessedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countAccessedByCourseIri(courseIri);
    }

    public static long countCompletedByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countCompletedByCourseIri(courseIri);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> findByFeedReferenceId(
        long feedRefId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByFeedReferenceId(feedRefId);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponentRecords(courseRecord);
    }

    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long courseRecordPrimaryKey)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getComponentRecords(courseRecordPrimaryKey);
    }

    public static void clearService() {
        _service = null;
    }

    public static CourseRecordLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CourseRecordLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    CourseRecordLocalService.class.getName(), portletClassLoader);

            _service = new CourseRecordLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(CourseRecordLocalServiceUtil.class,
                "_service");
            MethodCache.remove(CourseRecordLocalService.class);
        }

        return _service;
    }

    public void setService(CourseRecordLocalService service) {
        MethodCache.remove(CourseRecordLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(CourseRecordLocalServiceUtil.class,
            "_service");
        MethodCache.remove(CourseRecordLocalService.class);
    }
}
