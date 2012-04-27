package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.Contributor;

/**
 * The persistence interface for the contributor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContributorPersistenceImpl
 * @see ContributorUtil
 * @generated
 */
public interface ContributorPersistence extends BasePersistence<Contributor> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContributorUtil} to access the contributor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contributor in the entity cache if it is enabled.
    *
    * @param contributor the contributor
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.Contributor contributor);

    /**
    * Caches the contributors in the entity cache if it is enabled.
    *
    * @param contributors the contributors
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> contributors);

    /**
    * Creates a new contributor with the primary key. Does not add the contributor to the database.
    *
    * @param contributorId the primary key for the new contributor
    * @return the new contributor
    */
    public org.nterlearning.datamodel.catalog.model.Contributor create(
        long contributorId);

    /**
    * Removes the contributor with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor remove(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

    public org.nterlearning.datamodel.catalog.model.Contributor updateImpl(
        org.nterlearning.datamodel.catalog.model.Contributor contributor,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contributor with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchContributorException} if it could not be found.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor
    * @throws org.nterlearning.datamodel.catalog.NoSuchContributorException if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor findByPrimaryKey(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

    /**
    * Returns the contributor with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param contributorId the primary key of the contributor
    * @return the contributor, or <code>null</code> if a contributor with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.Contributor fetchByPrimaryKey(
        long contributorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contributors where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching contributors
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor[] findByCourseId_PrevAndNext(
        long contributorId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

    /**
    * Returns all the contributors where courseId = &#63; and role = &#63;.
    *
    * @param courseId the course ID
    * @param role the role
    * @return the matching contributors
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        long courseId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        long courseId, java.lang.String role, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByCourseIdWithRole(
        long courseId, java.lang.String role, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor findByCourseIdWithRole_First(
        long courseId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor findByCourseIdWithRole_Last(
        long courseId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor[] findByCourseIdWithRole_PrevAndNext(
        long contributorId, long courseId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

    /**
    * Returns all the contributors where componentId = &#63; and role = &#63;.
    *
    * @param componentId the component ID
    * @param role the role
    * @return the matching contributors
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        long componentId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        long componentId, java.lang.String role, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findByComponentIdWithRole(
        long componentId, java.lang.String role, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor findByComponentIdWithRole_First(
        long componentId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor findByComponentIdWithRole_Last(
        long componentId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

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
    public org.nterlearning.datamodel.catalog.model.Contributor[] findByComponentIdWithRole_PrevAndNext(
        long contributorId, long componentId, java.lang.String role,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchContributorException;

    /**
    * Returns all the contributors.
    *
    * @return the contributors
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contributors where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contributors where courseId = &#63; and role = &#63; from the database.
    *
    * @param courseId the course ID
    * @param role the role
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseIdWithRole(long courseId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contributors where componentId = &#63; and role = &#63; from the database.
    *
    * @param componentId the component ID
    * @param role the role
    * @throws SystemException if a system exception occurred
    */
    public void removeByComponentIdWithRole(long componentId,
        java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contributors from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contributors where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contributors where courseId = &#63; and role = &#63;.
    *
    * @param courseId the course ID
    * @param role the role
    * @return the number of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseIdWithRole(long courseId, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contributors where componentId = &#63; and role = &#63;.
    *
    * @param componentId the component ID
    * @param role the role
    * @return the number of matching contributors
    * @throws SystemException if a system exception occurred
    */
    public int countByComponentIdWithRole(long componentId,
        java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contributors.
    *
    * @return the number of contributors
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
