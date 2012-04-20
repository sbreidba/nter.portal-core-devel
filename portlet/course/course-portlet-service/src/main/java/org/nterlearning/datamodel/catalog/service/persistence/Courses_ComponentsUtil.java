package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.Courses_Components;

import java.util.List;

/**
 * The persistence utility for the courses_ components service. This utility wraps {@link Courses_ComponentsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Courses_ComponentsPersistence
 * @see Courses_ComponentsPersistenceImpl
 * @generated
 */
public class Courses_ComponentsUtil {
    private static Courses_ComponentsPersistence _persistence;

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
    public static void clearCache(Courses_Components courses_Components) {
        getPersistence().clearCache(courses_Components);
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
    public static List<Courses_Components> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Courses_Components> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Courses_Components> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static Courses_Components update(
        Courses_Components courses_Components, boolean merge)
        throws SystemException {
        return getPersistence().update(courses_Components, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static Courses_Components update(
        Courses_Components courses_Components, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(courses_Components, merge, serviceContext);
    }

    /**
    * Caches the courses_ components in the entity cache if it is enabled.
    *
    * @param courses_Components the courses_ components
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components) {
        getPersistence().cacheResult(courses_Components);
    }

    /**
    * Caches the courses_ componentses in the entity cache if it is enabled.
    *
    * @param courses_Componentses the courses_ componentses
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> courses_Componentses) {
        getPersistence().cacheResult(courses_Componentses);
    }

    /**
    * Creates a new courses_ components with the primary key. Does not add the courses_ components to the database.
    *
    * @param coursesComponentsId the primary key for the new courses_ components
    * @return the new courses_ components
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components create(
        long coursesComponentsId) {
        return getPersistence().create(coursesComponentsId);
    }

    /**
    * Removes the courses_ components with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param coursesComponentsId the primary key of the courses_ components
    * @return the courses_ components that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components remove(
        long coursesComponentsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence().remove(coursesComponentsId);
    }

    public static org.nterlearning.datamodel.catalog.model.Courses_Components updateImpl(
        org.nterlearning.datamodel.catalog.model.Courses_Components courses_Components,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(courses_Components, merge);
    }

    /**
    * Returns the courses_ components with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException} if it could not be found.
    *
    * @param coursesComponentsId the primary key of the courses_ components
    * @return the courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByPrimaryKey(
        long coursesComponentsId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence().findByPrimaryKey(coursesComponentsId);
    }

    /**
    * Returns the courses_ components with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param coursesComponentsId the primary key of the courses_ components
    * @return the courses_ components, or <code>null</code> if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components fetchByPrimaryKey(
        long coursesComponentsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(coursesComponentsId);
    }

    /**
    * Returns all the courses_ componentses where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId);
    }

    /**
    * Returns a range of all the courses_ componentses where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId, start, end);
    }

    /**
    * Returns an ordered range of all the courses_ componentses where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseId(courseId, start, end, orderByComparator);
    }

    /**
    * Returns the first courses_ components in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence().findByCourseId_First(courseId, orderByComparator);
    }

    /**
    * Returns the last courses_ components in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence().findByCourseId_Last(courseId, orderByComparator);
    }

    /**
    * Returns the courses_ componentses before and after the current courses_ components in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param coursesComponentsId the primary key of the current courses_ components
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components[] findByCourseId_PrevAndNext(
        long coursesComponentsId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByCourseId_PrevAndNext(coursesComponentsId, courseId,
            orderByComparator);
    }

    /**
    * Returns all the courses_ componentses where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseIri(
        java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIri(courseIri);
    }

    /**
    * Returns a range of all the courses_ componentses where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseIri(
        java.lang.String courseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIri(courseIri, start, end);
    }

    /**
    * Returns an ordered range of all the courses_ componentses where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByCourseIri(
        java.lang.String courseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIri(courseIri, start, end, orderByComparator);
    }

    /**
    * Returns the first courses_ components in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByCourseIri_First(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByCourseIri_First(courseIri, orderByComparator);
    }

    /**
    * Returns the last courses_ components in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByCourseIri_Last(
        java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByCourseIri_Last(courseIri, orderByComparator);
    }

    /**
    * Returns the courses_ componentses before and after the current courses_ components in the ordered set where courseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param coursesComponentsId the primary key of the current courses_ components
    * @param courseIri the course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components[] findByCourseIri_PrevAndNext(
        long coursesComponentsId, java.lang.String courseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByCourseIri_PrevAndNext(coursesComponentsId, courseIri,
            orderByComparator);
    }

    /**
    * Returns all the courses_ componentses where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentId(componentId);
    }

    /**
    * Returns a range of all the courses_ componentses where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentId(
        long componentId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentId(componentId, start, end);
    }

    /**
    * Returns an ordered range of all the courses_ componentses where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentId(
        long componentId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentId(componentId, start, end, orderByComparator);
    }

    /**
    * Returns the first courses_ components in the ordered set where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByComponentId_First(
        long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByComponentId_First(componentId, orderByComparator);
    }

    /**
    * Returns the last courses_ components in the ordered set where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByComponentId_Last(
        long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByComponentId_Last(componentId, orderByComparator);
    }

    /**
    * Returns the courses_ componentses before and after the current courses_ components in the ordered set where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param coursesComponentsId the primary key of the current courses_ components
    * @param componentId the component ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components[] findByComponentId_PrevAndNext(
        long coursesComponentsId, long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByComponentId_PrevAndNext(coursesComponentsId,
            componentId, orderByComparator);
    }

    /**
    * Returns all the courses_ componentses where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentIri(componentIri);
    }

    /**
    * Returns a range of all the courses_ componentses where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentIri(
        java.lang.String componentIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentIri(componentIri, start, end);
    }

    /**
    * Returns an ordered range of all the courses_ componentses where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findByComponentIri(
        java.lang.String componentIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentIri(componentIri, start, end,
            orderByComparator);
    }

    /**
    * Returns the first courses_ components in the ordered set where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByComponentIri_First(
        java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByComponentIri_First(componentIri, orderByComparator);
    }

    /**
    * Returns the last courses_ components in the ordered set where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a matching courses_ components could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components findByComponentIri_Last(
        java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByComponentIri_Last(componentIri, orderByComparator);
    }

    /**
    * Returns the courses_ componentses before and after the current courses_ components in the ordered set where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param coursesComponentsId the primary key of the current courses_ components
    * @param componentIri the component iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next courses_ components
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException if a courses_ components with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Courses_Components[] findByComponentIri_PrevAndNext(
        long coursesComponentsId, java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourses_ComponentsException {
        return getPersistence()
                   .findByComponentIri_PrevAndNext(coursesComponentsId,
            componentIri, orderByComparator);
    }

    /**
    * Returns all the courses_ componentses.
    *
    * @return the courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the courses_ componentses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the courses_ componentses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the courses_ componentses where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseId(courseId);
    }

    /**
    * Removes all the courses_ componentses where courseIri = &#63; from the database.
    *
    * @param courseIri the course iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIri(courseIri);
    }

    /**
    * Removes all the courses_ componentses where componentId = &#63; from the database.
    *
    * @param componentId the component ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByComponentId(componentId);
    }

    /**
    * Removes all the courses_ componentses where componentIri = &#63; from the database.
    *
    * @param componentIri the component iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByComponentIri(componentIri);
    }

    /**
    * Removes all the courses_ componentses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of courses_ componentses where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseId(courseId);
    }

    /**
    * Returns the number of courses_ componentses where courseIri = &#63;.
    *
    * @param courseIri the course iri
    * @return the number of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIri(java.lang.String courseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIri(courseIri);
    }

    /**
    * Returns the number of courses_ componentses where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the number of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentId(componentId);
    }

    /**
    * Returns the number of courses_ componentses where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the number of matching courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentIri(componentIri);
    }

    /**
    * Returns the number of courses_ componentses.
    *
    * @return the number of courses_ componentses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the components associated with the courses_ components.
    *
    * @param pk the primary key of the courses_ components
    * @return the components associated with the courses_ components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponents(pk);
    }

    /**
    * Returns a range of all the components associated with the courses_ components.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the courses_ components
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @return the range of components associated with the courses_ components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponents(pk, start, end);
    }

    /**
    * Returns an ordered range of all the components associated with the courses_ components.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the courses_ components
    * @param start the lower bound of the range of courses_ componentses
    * @param end the upper bound of the range of courses_ componentses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of components associated with the courses_ components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> getComponents(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponents(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of components associated with the courses_ components.
    *
    * @param pk the primary key of the courses_ components
    * @return the number of components associated with the courses_ components
    * @throws SystemException if a system exception occurred
    */
    public static int getComponentsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentsSize(pk);
    }

    /**
    * Returns <code>true</code> if the component is associated with the courses_ components.
    *
    * @param pk the primary key of the courses_ components
    * @param componentPK the primary key of the component
    * @return <code>true</code> if the component is associated with the courses_ components; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsComponent(long pk, long componentPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsComponent(pk, componentPK);
    }

    /**
    * Returns <code>true</code> if the courses_ components has any components associated with it.
    *
    * @param pk the primary key of the courses_ components to check for associations with components
    * @return <code>true</code> if the courses_ components has any components associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsComponents(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsComponents(pk);
    }

    public static Courses_ComponentsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (Courses_ComponentsPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    Courses_ComponentsPersistence.class.getName());

            ReferenceRegistry.registerReference(Courses_ComponentsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(Courses_ComponentsPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(Courses_ComponentsUtil.class,
            "_persistence");
    }
}
