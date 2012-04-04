package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.Contributor;

import java.util.List;

/**
 * The persistence utility for the contributor service. This utility wraps {@link ContributorPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContributorPersistence
 * @see ContributorPersistenceImpl
 * @generated
 */
public class ContributorUtil {
    private static ContributorPersistence _persistence;

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
    public static void clearCache(Contributor contributor) {
        getPersistence().clearCache(contributor);
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
    public static List<Contributor> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Contributor> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Contributor> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static Contributor update(Contributor contributor, boolean merge)
        throws SystemException {
        return getPersistence().update(contributor, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static Contributor update(Contributor contributor, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contributor, merge, serviceContext);
    }

    /**
    * Caches the contributor in the entity cache if it is enabled.
    *
    * @param contributor the contributor
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.Contributor contributor) {
        getPersistence().cacheResult(contributor);
    }

    /**
    * Caches the contributors in the entity cache if it is enabled.
    *
    * @param contributors the contributors
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> contributors) {
        getPersistence().cacheResult(contributors);
    }

    /**
    * Creates a new contributor with the primary key. Does not add the contributor to the database.
    *
    * @param contributorId the primary key for the new contributor
    * @return the new contributor
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor create(
        long contributorId) {
        return getPersistence().create(contributorId);
    }

    /**
    * Removes the contributor with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor remove(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence().remove(contributorId);
    }

    public static org.nterlearning.datamodel.catalog.model.Contributor updateImpl(
        org.nterlearning.datamodel.catalog.model.Contributor contributor,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contributor, merge);
    }

    /**
    * Returns the contributor with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchContributorException} if it could not be found.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByPrimaryKey(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence().findByPrimaryKey(contributorId);
    }

    /**
    * Returns the contributor with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor, or <code>null</code> if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor fetchByPrimaryKey(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(contributorId);
    }

    /**
    * Returns all the contributors where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId);
    }

    /**
    * Returns a range of all the contributors where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @return the range of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId, start, end);
    }

    /**
    * Returns an ordered range of all the contributors where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseId(courseId, start, end, orderByComparator);
    }

    /**
    * Returns the first contributor in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence().findByCourseId_First(courseId, orderByComparator);
    }

    /**
    * Returns the last contributor in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence().findByCourseId_Last(courseId, orderByComparator);
    }

    /**
    * Returns the contributors before and after the current contributor in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contributorId the primary key of the current contributor
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor[] findByCourseId_PrevAndNext(
        long contributorId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByCourseId_PrevAndNext(contributorId, courseId,
            orderByComparator);
    }

    /**
    * Returns all the contributors where courseId = &#63; and role = &#63;.
    *
    * @param courseId the course ID
    * @param role the role
    * @return the matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        long courseId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIdWithRole(courseId, role);
    }

    /**
    * Returns a range of all the contributors where courseId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param role the role
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @return the range of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        long courseId, java.lang.String role, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRole(courseId, role, start, end);
    }

    /**
    * Returns an ordered range of all the contributors where courseId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param role the role
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        long courseId, java.lang.String role, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithRole(courseId, role, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contributor in the ordered set where courseId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param role the role
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByCourseIdWithRole_First(
        long courseId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByCourseIdWithRole_First(courseId, role,
            orderByComparator);
    }

    /**
    * Returns the last contributor in the ordered set where courseId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param role the role
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByCourseIdWithRole_Last(
        long courseId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByCourseIdWithRole_Last(courseId, role,
            orderByComparator);
    }

    /**
    * Returns the contributors before and after the current contributor in the ordered set where courseId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contributorId the primary key of the current contributor
    * @param courseId the course ID
    * @param role the role
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor[] findByCourseIdWithRole_PrevAndNext(
        long contributorId, long courseId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByCourseIdWithRole_PrevAndNext(contributorId, courseId,
            role, orderByComparator);
    }

    /**
    * Returns all the contributors where componentId = &#63; and role = &#63;.
    *
    * @param componentId the component ID
    * @param role the role
    * @return the matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        long componentId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentIdWithRole(componentId, role);
    }

    /**
    * Returns a range of all the contributors where componentId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param role the role
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @return the range of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        long componentId, java.lang.String role, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentIdWithRole(componentId, role, start, end);
    }

    /**
    * Returns an ordered range of all the contributors where componentId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param role the role
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        long componentId, java.lang.String role, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentIdWithRole(componentId, role, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contributor in the ordered set where componentId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param role the role
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByComponentIdWithRole_First(
        long componentId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByComponentIdWithRole_First(componentId, role,
            orderByComparator);
    }

    /**
    * Returns the last contributor in the ordered set where componentId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param role the role
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a matching contributor could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor findByComponentIdWithRole_Last(
        long componentId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByComponentIdWithRole_Last(componentId, role,
            orderByComparator);
    }

    /**
    * Returns the contributors before and after the current contributor in the ordered set where componentId = &#63; and role = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contributorId the primary key of the current contributor
    * @param componentId the component ID
    * @param role the role
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Contributor[] findByComponentIdWithRole_PrevAndNext(
        long contributorId, long componentId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException {
        return getPersistence()
                   .findByComponentIdWithRole_PrevAndNext(contributorId,
            componentId, role, orderByComparator);
    }

    /**
    * Returns all the contributors.
    *
    * @return the contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contributors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @return the range of contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contributors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contributors
    * @param end the upper bound of the range of contributors (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contributors
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contributors where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseId(courseId);
    }

    /**
    * Removes all the contributors where courseId = &#63; and role = &#63; from the database.
    *
    * @param courseId the course ID
    * @param role the role
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIdWithRole(long courseId,
        java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIdWithRole(courseId, role);
    }

    /**
    * Removes all the contributors where componentId = &#63; and role = &#63; from the database.
    *
    * @param componentId the component ID
    * @param role the role
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentIdWithRole(long componentId,
        java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByComponentIdWithRole(componentId, role);
    }

    /**
    * Removes all the contributors from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contributors where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseId(courseId);
    }

    /**
    * Returns the number of contributors where courseId = &#63; and role = &#63;.
    *
    * @param courseId the course ID
    * @param role the role
    * @return the number of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIdWithRole(long courseId,
        java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIdWithRole(courseId, role);
    }

    /**
    * Returns the number of contributors where componentId = &#63; and role = &#63;.
    *
    * @param componentId the component ID
    * @param role the role
    * @return the number of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentIdWithRole(long componentId,
        java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentIdWithRole(componentId, role);
    }

    /**
    * Returns the number of contributors.
    *
    * @return the number of contributors
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContributorPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContributorPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    ContributorPersistence.class.getName());

            ReferenceRegistry.registerReference(ContributorUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ContributorPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ContributorUtil.class,
            "_persistence");
    }
}
