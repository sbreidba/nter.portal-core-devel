package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.CourseRequirement;

/**
 * The persistence interface for the course requirement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRequirementPersistenceImpl
 * @see CourseRequirementUtil
 * @generated
 */
public interface CourseRequirementPersistence extends BasePersistence<CourseRequirement> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseRequirementUtil} to access the course requirement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the course requirement in the entity cache if it is enabled.
    *
    * @param courseRequirement the course requirement
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement);

    /**
    * Caches the course requirements in the entity cache if it is enabled.
    *
    * @param courseRequirements the course requirements
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> courseRequirements);

    /**
    * Creates a new course requirement with the primary key. Does not add the course requirement to the database.
    *
    * @param courseRequirementId the primary key for the new course requirement
    * @return the new course requirement
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement create(
        long courseRequirementId);

    /**
    * Removes the course requirement with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement remove(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException;

    public org.nterlearning.datamodel.catalog.model.CourseRequirement updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRequirement courseRequirement,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course requirement with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException} if it could not be found.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement findByPrimaryKey(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException;

    /**
    * Returns the course requirement with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseRequirementId the primary key of the course requirement
    * @return the course requirement, or <code>null</code> if a course requirement with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseRequirement fetchByPrimaryKey(
        long courseRequirementId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course requirements where courseId = &#63; and requirementType = &#63;.
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @return the matching course requirements
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, java.lang.String requirementType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findByCourseIdWithRequirementType(
        long courseId, java.lang.String requirementType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRequirement findByCourseIdWithRequirementType_First(
        long courseId, java.lang.String requirementType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRequirement findByCourseIdWithRequirementType_Last(
        long courseId, java.lang.String requirementType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException;

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
    public org.nterlearning.datamodel.catalog.model.CourseRequirement[] findByCourseIdWithRequirementType_PrevAndNext(
        long courseRequirementId, long courseId,
        java.lang.String requirementType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRequirementException;

    /**
    * Returns all the course requirements.
    *
    * @return the course requirements
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseRequirement> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course requirements where courseId = &#63; and requirementType = &#63; from the database.
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseIdWithRequirementType(long courseId,
        java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course requirements from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course requirements where courseId = &#63; and requirementType = &#63;.
    *
    * @param courseId the course ID
    * @param requirementType the requirement type
    * @return the number of matching course requirements
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseIdWithRequirementType(long courseId,
        java.lang.String requirementType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course requirements.
    *
    * @return the number of course requirements
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
