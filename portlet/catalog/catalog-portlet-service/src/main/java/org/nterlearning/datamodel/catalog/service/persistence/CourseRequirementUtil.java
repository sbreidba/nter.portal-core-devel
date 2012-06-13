package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.CourseRequirement;

import java.util.List;

/**
 * The persistence utility for the course requirement service. This utility wraps {@link CourseRequirementPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRequirementPersistence
 * @see CourseRequirementPersistenceImpl
 * @generated
 */
public class CourseRequirementUtil {
    private static CourseRequirementPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(CourseRequirement courseRequirement) {
        getPersistence().clearCache(courseRequirement);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<CourseRequirement> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CourseRequirement> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CourseRequirement> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static CourseRequirement update(
        CourseRequirement courseRequirement, boolean merge)
        throws SystemException {
        return getPersistence().update(courseRequirement, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static CourseRequirement update(
        CourseRequirement courseRequirement, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(courseRequirement, merge, serviceContext);
    }

    /**
    * Caches the course requirement in the entity cache if it is enabled.
    *
    * @param courseRequirement the course requirement
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement) {
        getPersistence().cacheResult(courseRequirement);
    }

    /**
    * Caches the course requirements in the entity cache if it is enabled.
    *
    * @param courseRequirements the course requirements
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> courseRequirements) {
        getPersistence().cacheResult(courseRequirements);
    }

    /**
    * Creates a new course requirement with the primary key. Does not add the course requirement to the database.
    *
    * @param courseRequirementId the primary key for the new course requirement
    * @return the new course requirement
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement create(
        long courseRequirementId) {
        return getPersistence().create(courseRequirementId);
    }

    /**
    * Removes the course requirement with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement remove(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException {
        return getPersistence().remove(courseRequirementId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRequirement updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(courseRequirement, merge);
    }

    /**
    * Returns the course requirement with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException} if it could not be found.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement findByPrimaryKey(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException {
        return getPersistence().findByPrimaryKey(courseRequirementId);
    }

    /**
    * Returns the course requirement with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement, or <code>null</code> if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement fetchByPrimaryKey(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(courseRequirementId);
    }

    /**
    * Returns all the course requirements where courseId = &#63; and requirementType = &#63;.
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @return the matching course requirements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRequirementType(courseId, requirementType);
    }

    /**
    * Returns a range of all the course requirements where courseId = &#63; and requirementType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @param start the lower bound of the range of course requirements
    * @param end the upper bound of the range of course requirements (not inclusive)
    * @return the range of matching course requirements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, java.lang.String requirementType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRequirementType(courseId,
            requirementType, start, end);
    }

    /**
    * Returns an ordered range of all the course requirements where courseId = &#63; and requirementType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @param start the lower bound of the range of course requirements
    * @param end the upper bound of the range of course requirements (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course requirements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, java.lang.String requirementType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRequirementType(courseId,
            requirementType, start, end, orderByComparator);
    }

    /**
    * Returns the first course requirement in the ordered set where courseId = &#63; and requirementType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course requirement
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a matching course requirement could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement findByCourseIdWithRequirementType_First(
        long courseId, java.lang.String requirementType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException {
        return getPersistence()
                   .findByCourseIdWithRequirementType_First(courseId,
            requirementType, orderByComparator);
    }

    /**
    * Returns the last course requirement in the ordered set where courseId = &#63; and requirementType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course requirement
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a matching course requirement could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement findByCourseIdWithRequirementType_Last(
        long courseId, java.lang.String requirementType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException {
        return getPersistence()
                   .findByCourseIdWithRequirementType_Last(courseId,
            requirementType, orderByComparator);
    }

    /**
    * Returns the course requirements before and after the current course requirement in the ordered set where courseId = &#63; and requirementType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRequirementId the primary key of the current course requirement
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course requirement
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRequirement[] findByCourseIdWithRequirementType_PrevAndNext(
        long courseRequirementId, long courseId,
        java.lang.String requirementType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException {
        return getPersistence()
                   .findByCourseIdWithRequirementType_PrevAndNext(courseRequirementId,
            courseId, requirementType, orderByComparator);
    }

    /**
    * Returns all the course requirements.
    *
    * @return the course requirements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the course requirements.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course requirements
    * @param end the upper bound of the range of course requirements (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course requirements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the course requirements where courseId = &#63; and requirementType = &#63; from the database.
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIdWithRequirementType(long courseId,
        java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByCourseIdWithRequirementType(courseId, requirementType);
    }

    /**
    * Removes all the course requirements from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of course requirements where courseId = &#63; and requirementType = &#63;.
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @return the number of matching course requirements
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIdWithRequirementType(long courseId,
        java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByCourseIdWithRequirementType(courseId, requirementType);
    }

    /**
    * Returns the number of course requirements.
    *
    * @return the number of course requirements
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CourseRequirementPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CourseRequirementPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseRequirementPersistence.class.getName());

            ReferenceRegistry.registerReference(CourseRequirementUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(CourseRequirementPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(CourseRequirementUtil.class,
            "_persistence");
    }
}
