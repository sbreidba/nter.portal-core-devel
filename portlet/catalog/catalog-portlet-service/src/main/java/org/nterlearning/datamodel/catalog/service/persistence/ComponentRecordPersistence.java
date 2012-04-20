package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.ComponentRecord;

/**
 * The persistence interface for the component record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecordPersistenceImpl
 * @see ComponentRecordUtil
 * @generated
 */
public interface ComponentRecordPersistence extends BasePersistence<ComponentRecord> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ComponentRecordUtil} to access the component record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the component record in the entity cache if it is enabled.
    *
    * @param componentRecord the component record
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord);

    /**
    * Caches the component records in the entity cache if it is enabled.
    *
    * @param componentRecords the component records
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> componentRecords);

    /**
    * Creates a new component record with the primary key. Does not add the component record to the database.
    *
    * @param componentRecordId the primary key for the new component record
    * @return the new component record
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord create(
        long componentRecordId);

    /**
    * Removes the component record with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord remove(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

    public org.nterlearning.datamodel.catalog.model.ComponentRecord updateImpl(
        org.nterlearning.datamodel.catalog.model.ComponentRecord componentRecord,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the component record with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentRecordException} if it could not be found.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentRecordException if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord findByPrimaryKey(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

    /**
    * Returns the component record with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param componentRecordId the primary key of the component record
    * @return the component record, or <code>null</code> if a component record with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ComponentRecord fetchByPrimaryKey(
        long componentRecordId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the component records where courseRecordId = &#63;.
    *
    * @param courseRecordId the course record ID
    * @return the matching component records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByCourseRecordId(
        long courseRecordId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.ComponentRecord findByCourseRecordId_First(
        long courseRecordId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

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
    public org.nterlearning.datamodel.catalog.model.ComponentRecord findByCourseRecordId_Last(
        long courseRecordId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

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
    public org.nterlearning.datamodel.catalog.model.ComponentRecord[] findByCourseRecordId_PrevAndNext(
        long componentRecordId, long courseRecordId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

    /**
    * Returns all the component records where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the matching component records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findByComponentIri(
        java.lang.String componentIri, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.ComponentRecord findByComponentIri_First(
        java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

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
    public org.nterlearning.datamodel.catalog.model.ComponentRecord findByComponentIri_Last(
        java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

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
    public org.nterlearning.datamodel.catalog.model.ComponentRecord[] findByComponentIri_PrevAndNext(
        long componentRecordId, java.lang.String componentIri,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentRecordException;

    /**
    * Returns all the component records.
    *
    * @return the component records
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the component records where courseRecordId = &#63; from the database.
    *
    * @param courseRecordId the course record ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseRecordId(long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the component records where componentIri = &#63; from the database.
    *
    * @param componentIri the component iri
    * @throws SystemException if a system exception occurred
    */
    public void removeByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the component records from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of component records where courseRecordId = &#63;.
    *
    * @param courseRecordId the course record ID
    * @return the number of matching component records
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseRecordId(long courseRecordId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of component records where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the number of matching component records
    * @throws SystemException if a system exception occurred
    */
    public int countByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of component records.
    *
    * @return the number of component records
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
