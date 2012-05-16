package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.CourseRelated;

import java.util.List;

/**
 * The persistence utility for the course related service. This utility wraps {@link CourseRelatedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRelatedPersistence
 * @see CourseRelatedPersistenceImpl
 * @generated
 */
public class CourseRelatedUtil {
    private static CourseRelatedPersistence _persistence;

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
    public static void clearCache(CourseRelated courseRelated) {
        getPersistence().clearCache(courseRelated);
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
    public static List<CourseRelated> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CourseRelated> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CourseRelated> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static CourseRelated update(CourseRelated courseRelated,
        boolean merge) throws SystemException {
        return getPersistence().update(courseRelated, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static CourseRelated update(CourseRelated courseRelated,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(courseRelated, merge, serviceContext);
    }

    /**
    * Caches the course related in the entity cache if it is enabled.
    *
    * @param courseRelated the course related
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated) {
        getPersistence().cacheResult(courseRelated);
    }

    /**
    * Caches the course relateds in the entity cache if it is enabled.
    *
    * @param courseRelateds the course relateds
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> courseRelateds) {
        getPersistence().cacheResult(courseRelateds);
    }

    /**
    * Creates a new course related with the primary key. Does not add the course related to the database.
    *
    * @param courseRelatedId the primary key for the new course related
    * @return the new course related
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated create(
        long courseRelatedId) {
        return getPersistence().create(courseRelatedId);
    }

    /**
    * Removes the course related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseRelatedId the primary key of the course related
    * @return the course related that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated remove(
        long courseRelatedId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence().remove(courseRelatedId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRelated updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseRelated courseRelated,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(courseRelated, merge);
    }

    /**
    * Returns the course related with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException} if it could not be found.
    *
    * @param courseRelatedId the primary key of the course related
    * @return the course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated findByPrimaryKey(
        long courseRelatedId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence().findByPrimaryKey(courseRelatedId);
    }

    /**
    * Returns the course related with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseRelatedId the primary key of the course related
    * @return the course related, or <code>null</code> if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated fetchByPrimaryKey(
        long courseRelatedId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(courseRelatedId);
    }

    /**
    * Returns all the course relateds where courseId = &#63; and relationshipType = &#63;.
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @return the matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findByRelatedCourseIdWithRelationshipType(
        long courseId, java.lang.String relationshipType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType);
    }

    /**
    * Returns a range of all the course relateds where courseId = &#63; and relationshipType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @return the range of matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findByRelatedCourseIdWithRelationshipType(
        long courseId, java.lang.String relationshipType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType, start, end);
    }

    /**
    * Returns an ordered range of all the course relateds where courseId = &#63; and relationshipType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findByRelatedCourseIdWithRelationshipType(
        long courseId, java.lang.String relationshipType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType, start, end, orderByComparator);
    }

    /**
    * Returns the first course related in the ordered set where courseId = &#63; and relationshipType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated findByRelatedCourseIdWithRelationshipType_First(
        long courseId, java.lang.String relationshipType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence()
                   .findByRelatedCourseIdWithRelationshipType_First(courseId,
            relationshipType, orderByComparator);
    }

    /**
    * Returns the last course related in the ordered set where courseId = &#63; and relationshipType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated findByRelatedCourseIdWithRelationshipType_Last(
        long courseId, java.lang.String relationshipType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence()
                   .findByRelatedCourseIdWithRelationshipType_Last(courseId,
            relationshipType, orderByComparator);
    }

    /**
    * Returns the course relateds before and after the current course related in the ordered set where courseId = &#63; and relationshipType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRelatedId the primary key of the current course related
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated[] findByRelatedCourseIdWithRelationshipType_PrevAndNext(
        long courseRelatedId, long courseId, java.lang.String relationshipType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence()
                   .findByRelatedCourseIdWithRelationshipType_PrevAndNext(courseRelatedId,
            courseId, relationshipType, orderByComparator);
    }

    /**
    * Returns all the course relateds where relatedCourseIri = &#63;.
    *
    * @param relatedCourseIri the related course iri
    * @return the matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findByRelatedCourseIri(
        java.lang.String relatedCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByRelatedCourseIri(relatedCourseIri);
    }

    /**
    * Returns a range of all the course relateds where relatedCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedCourseIri the related course iri
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @return the range of matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findByRelatedCourseIri(
        java.lang.String relatedCourseIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRelatedCourseIri(relatedCourseIri, start, end);
    }

    /**
    * Returns an ordered range of all the course relateds where relatedCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedCourseIri the related course iri
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findByRelatedCourseIri(
        java.lang.String relatedCourseIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRelatedCourseIri(relatedCourseIri, start, end,
            orderByComparator);
    }

    /**
    * Returns the first course related in the ordered set where relatedCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedCourseIri the related course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated findByRelatedCourseIri_First(
        java.lang.String relatedCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence()
                   .findByRelatedCourseIri_First(relatedCourseIri,
            orderByComparator);
    }

    /**
    * Returns the last course related in the ordered set where relatedCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedCourseIri the related course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a matching course related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated findByRelatedCourseIri_Last(
        java.lang.String relatedCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence()
                   .findByRelatedCourseIri_Last(relatedCourseIri,
            orderByComparator);
    }

    /**
    * Returns the course relateds before and after the current course related in the ordered set where relatedCourseIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRelatedId the primary key of the current course related
    * @param relatedCourseIri the related course iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course related
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException if a course related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseRelated[] findByRelatedCourseIri_PrevAndNext(
        long courseRelatedId, java.lang.String relatedCourseIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseRelatedException {
        return getPersistence()
                   .findByRelatedCourseIri_PrevAndNext(courseRelatedId,
            relatedCourseIri, orderByComparator);
    }

    /**
    * Returns all the course relateds.
    *
    * @return the course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the course relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @return the range of course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the course relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course relateds
    * @param end the upper bound of the range of course relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseRelated> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the course relateds where courseId = &#63; and relationshipType = &#63; from the database.
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRelatedCourseIdWithRelationshipType(
        long courseId, java.lang.String relationshipType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType);
    }

    /**
    * Removes all the course relateds where relatedCourseIri = &#63; from the database.
    *
    * @param relatedCourseIri the related course iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRelatedCourseIri(
        java.lang.String relatedCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByRelatedCourseIri(relatedCourseIri);
    }

    /**
    * Removes all the course relateds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of course relateds where courseId = &#63; and relationshipType = &#63;.
    *
    * @param courseId the course ID
    * @param relationshipType the relationship type
    * @return the number of matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static int countByRelatedCourseIdWithRelationshipType(
        long courseId, java.lang.String relationshipType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByRelatedCourseIdWithRelationshipType(courseId,
            relationshipType);
    }

    /**
    * Returns the number of course relateds where relatedCourseIri = &#63;.
    *
    * @param relatedCourseIri the related course iri
    * @return the number of matching course relateds
    * @throws SystemException if a system exception occurred
    */
    public static int countByRelatedCourseIri(java.lang.String relatedCourseIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByRelatedCourseIri(relatedCourseIri);
    }

    /**
    * Returns the number of course relateds.
    *
    * @return the number of course relateds
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CourseRelatedPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CourseRelatedPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseRelatedPersistence.class.getName());

            ReferenceRegistry.registerReference(CourseRelatedUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(CourseRelatedPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(CourseRelatedUtil.class,
            "_persistence");
    }
}
