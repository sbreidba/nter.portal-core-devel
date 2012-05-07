package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.ComponentRecord;

import java.util.List;

/**
 * The persistence utility for the component record service. This utility wraps {@link ComponentRecordPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecordPersistence
 * @see ComponentRecordPersistenceImpl
 * @generated
 */
public class ComponentRecordUtil {
    private static ComponentRecordPersistence _persistence;

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
    public static void clearCache(ComponentRecord componentRecord) {
        getPersistence().clearCache(componentRecord);
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
    public static List<ComponentRecord> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ComponentRecord> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ComponentRecord> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ComponentRecord update(ComponentRecord componentRecord,
        boolean merge) throws SystemException {
        return getPersistence().update(componentRecord, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ComponentRecord update(ComponentRecord componentRecord,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(componentRecord, merge, serviceContext);
    }

    /**
    * Caches the component record in the entity cache if it is enabled.
    *
    * @param componentRecord the component record
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord) {
        getPersistence().cacheResult(componentRecord);
    }

    /**
    * Caches the component records in the entity cache if it is enabled.
    *
    * @param componentRecords the component records
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> componentRecords) {
        getPersistence().cacheResult(componentRecords);
    }

    /**
    * Creates a new component record with the primary key. Does not add the component record to the database.
    *
    * @param componentRecordId the primary key for the new component record
    * @return the new component record
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord create(
        long componentRecordId) {
        return getPersistence().create(componentRecordId);
    }

    /**
    * Removes the component record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord remove(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence().remove(componentRecordId);
    }

    public static org.nterlearning.datamodel.catalog.model.ComponentRecord updateImpl(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(componentRecord, merge);
    }

    /**
    * Returns the component record with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentRecordException} if it could not be found.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord findByPrimaryKey(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence().findByPrimaryKey(componentRecordId);
    }

    /**
    * Returns the component record with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record, or <code>null</code> if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord fetchByPrimaryKey(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(componentRecordId);
    }

    /**
    * Returns all the component records where courseRecordId = &#63;.
    *
    * @param courseRecordId the course record ID
    * @return the matching component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseRecordId(courseRecordId);
    }

    /**
    * Returns a range of all the component records where courseRecordId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the course record ID
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @return the range of matching component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseRecordId(courseRecordId, start, end);
    }

    /**
    * Returns an ordered range of all the component records where courseRecordId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the course record ID
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseRecordId(courseRecordId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first component record in the ordered set where courseRecordId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the course record ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord findByCourseRecordId_First(
        long courseRecordId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence()
                   .findByCourseRecordId_First(courseRecordId, orderByComparator);
    }

    /**
    * Returns the last component record in the ordered set where courseRecordId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseRecordId the course record ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord findByCourseRecordId_Last(
        long courseRecordId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence()
                   .findByCourseRecordId_Last(courseRecordId, orderByComparator);
    }

    /**
    * Returns the component records before and after the current component record in the ordered set where courseRecordId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentRecordId the primary key of the current component record
    * @param courseRecordId the course record ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord[] findByCourseRecordId_PrevAndNext(
        long componentRecordId, long courseRecordId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence()
                   .findByCourseRecordId_PrevAndNext(componentRecordId,
            courseRecordId, orderByComparator);
    }

    /**
    * Returns all the component records where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the matching component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentIri(componentIri);
    }

    /**
    * Returns a range of all the component records where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @return the range of matching component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentIri(componentIri, start, end);
    }

    /**
    * Returns an ordered range of all the component records where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentIri(componentIri, start, end,
            orderByComparator);
    }

    /**
    * Returns the first component record in the ordered set where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord findByComponentIri_First(
        java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence()
                   .findByComponentIri_First(componentIri, orderByComparator);
    }

    /**
    * Returns the last component record in the ordered set where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentIri the component iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a matching component record could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord findByComponentIri_Last(
        java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence()
                   .findByComponentIri_Last(componentIri, orderByComparator);
    }

    /**
    * Returns the component records before and after the current component record in the ordered set where componentIri = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentRecordId the primary key of the current component record
    * @param componentIri the component iri
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ComponentRecord[] findByComponentIri_PrevAndNext(
        long componentRecordId, java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException {
        return getPersistence()
                   .findByComponentIri_PrevAndNext(componentRecordId,
            componentIri, orderByComparator);
    }

    /**
    * Returns all the component records.
    *
    * @return the component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the component records.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @return the range of component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the component records.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of component records
    * @param end the upper bound of the range of component records (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of component records
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the component records where courseRecordId = &#63; from the database.
    *
    * @param courseRecordId the course record ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseRecordId(long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseRecordId(courseRecordId);
    }

    /**
    * Removes all the component records where componentIri = &#63; from the database.
    *
    * @param componentIri the component iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByComponentIri(componentIri);
    }

    /**
    * Removes all the component records from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of component records where courseRecordId = &#63;.
    *
    * @param courseRecordId the course record ID
    * @return the number of matching component records
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseRecordId(long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseRecordId(courseRecordId);
    }

    /**
    * Returns the number of component records where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the number of matching component records
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentIri(componentIri);
    }

    /**
    * Returns the number of component records.
    *
    * @return the number of component records
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ComponentRecordPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ComponentRecordPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    ComponentRecordPersistence.class.getName());

            ReferenceRegistry.registerReference(ComponentRecordUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ComponentRecordPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ComponentRecordUtil.class,
            "_persistence");
    }
}
