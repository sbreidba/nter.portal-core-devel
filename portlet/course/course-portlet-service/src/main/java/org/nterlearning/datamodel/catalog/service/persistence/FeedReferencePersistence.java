package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.FeedReference;

/**
 * The persistence interface for the feed reference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferencePersistenceImpl
 * @see FeedReferenceUtil
 * @generated
 */
public interface FeedReferencePersistence extends BasePersistence<FeedReference> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FeedReferenceUtil} to access the feed reference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the feed reference in the entity cache if it is enabled.
    *
    * @param feedReference the feed reference
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference);

    /**
    * Caches the feed references in the entity cache if it is enabled.
    *
    * @param feedReferences the feed references
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> feedReferences);

    /**
    * Creates a new feed reference with the primary key. Does not add the feed reference to the database.
    *
    * @param feedReferenceId the primary key for the new feed reference
    * @return the new feed reference
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference create(
        long feedReferenceId);

    /**
    * Removes the feed reference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @return the feed reference that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference remove(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    public org.nterlearning.datamodel.catalog.model.FeedReference updateImpl(
        org.nterlearning.datamodel.catalog.model.FeedReference feedReference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the feed reference with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException} if it could not be found.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @return the feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByPrimaryKey(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed reference with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param feedReferenceId the primary key of the feed reference
    * @return the feed reference, or <code>null</code> if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference fetchByPrimaryKey(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the feed references where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByGroupId_PrevAndNext(
        long feedReferenceId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByCompanyId(
        long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByCompanyId_First(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByCompanyId_Last(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByCompanyId_PrevAndNext(
        long feedReferenceId, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references where groupId = &#63; and removed = &#63;.
    *
    * @param groupId the group ID
    * @param removed the removed
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupIdWithRemoved(
        long groupId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where groupId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param removed the removed
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupIdWithRemoved(
        long groupId, boolean removed, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where groupId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param removed the removed
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupIdWithRemoved(
        long groupId, boolean removed, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where groupId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByGroupIdWithRemoved_First(
        long groupId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where groupId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByGroupIdWithRemoved_Last(
        long groupId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where groupId = &#63; and removed = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param groupId the group ID
    * @param removed the removed
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByGroupIdWithRemoved_PrevAndNext(
        long feedReferenceId, long groupId, boolean removed,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references where groupId = &#63; and feedType = &#63;.
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupIdWithType(
        long groupId, java.lang.String feedType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where groupId = &#63; and feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupIdWithType(
        long groupId, java.lang.String feedType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where groupId = &#63; and feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByGroupIdWithType(
        long groupId, java.lang.String feedType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where groupId = &#63; and feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByGroupIdWithType_First(
        long groupId, java.lang.String feedType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where groupId = &#63; and feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByGroupIdWithType_Last(
        long groupId, java.lang.String feedType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where groupId = &#63; and feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param groupId the group ID
    * @param feedType the feed type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByGroupIdWithType_PrevAndNext(
        long feedReferenceId, long groupId, java.lang.String feedType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed reference where feedIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException} if it could not be found.
    *
    * @param feedIri the feed iri
    * @return the matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed reference where feedIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param feedIri the feed iri
    * @return the matching feed reference, or <code>null</code> if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference fetchByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the feed reference where feedIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param feedIri the feed iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching feed reference, or <code>null</code> if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference fetchByFeedIri(
        java.lang.String feedIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the feed references where feedType = &#63;.
    *
    * @param feedType the feed type
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByFeedType(
        java.lang.String feedType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedType the feed type
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByFeedType(
        java.lang.String feedType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedType the feed type
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByFeedType(
        java.lang.String feedType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedType the feed type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedType_First(
        java.lang.String feedType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedType the feed type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedType_Last(
        java.lang.String feedType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where feedType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param feedType the feed type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByFeedType_PrevAndNext(
        long feedReferenceId, java.lang.String feedType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references where feedVersion = &#63;.
    *
    * @param feedVersion the feed version
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByFeedVersion(
        java.lang.String feedVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where feedVersion = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedVersion the feed version
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByFeedVersion(
        java.lang.String feedVersion, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where feedVersion = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedVersion the feed version
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByFeedVersion(
        java.lang.String feedVersion, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where feedVersion = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedVersion the feed version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedVersion_First(
        java.lang.String feedVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where feedVersion = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedVersion the feed version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedVersion_Last(
        java.lang.String feedVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where feedVersion = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param feedVersion the feed version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByFeedVersion_PrevAndNext(
        long feedReferenceId, java.lang.String feedVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references where contentProviderId = &#63;.
    *
    * @param contentProviderId the content provider ID
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByContentProvider(
        java.lang.String contentProviderId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where contentProviderId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contentProviderId the content provider ID
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByContentProvider(
        java.lang.String contentProviderId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where contentProviderId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contentProviderId the content provider ID
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByContentProvider(
        java.lang.String contentProviderId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where contentProviderId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contentProviderId the content provider ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByContentProvider_First(
        java.lang.String contentProviderId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where contentProviderId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contentProviderId the content provider ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByContentProvider_Last(
        java.lang.String contentProviderId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where contentProviderId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param contentProviderId the content provider ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByContentProvider_PrevAndNext(
        long feedReferenceId, java.lang.String contentProviderId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references where hrefHash = &#63;.
    *
    * @param hrefHash the href hash
    * @return the matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByHrefHash(
        java.lang.String hrefHash)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references where hrefHash = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param hrefHash the href hash
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByHrefHash(
        java.lang.String hrefHash, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references where hrefHash = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param hrefHash the href hash
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findByHrefHash(
        java.lang.String hrefHash, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first feed reference in the ordered set where hrefHash = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param hrefHash the href hash
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByHrefHash_First(
        java.lang.String hrefHash,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the last feed reference in the ordered set where hrefHash = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param hrefHash the href hash
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a matching feed reference could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByHrefHash_Last(
        java.lang.String hrefHash,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns the feed references before and after the current feed reference in the ordered set where hrefHash = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the primary key of the current feed reference
    * @param hrefHash the href hash
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next feed reference
    * @throws org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException if a feed reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.FeedReference[] findByHrefHash_PrevAndNext(
        long feedReferenceId, java.lang.String hrefHash,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Returns all the feed references.
    *
    * @return the feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the feed references.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @return the range of feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the feed references.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of feed references
    * @param end the upper bound of the range of feed references (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of feed references
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.FeedReference> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where groupId = &#63; and removed = &#63; from the database.
    *
    * @param groupId the group ID
    * @param removed the removed
    * @throws SystemException if a system exception occurred
    */
    public void removeByGroupIdWithRemoved(long groupId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where groupId = &#63; and feedType = &#63; from the database.
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @throws SystemException if a system exception occurred
    */
    public void removeByGroupIdWithType(long groupId, java.lang.String feedType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the feed reference where feedIri = &#63; from the database.
    *
    * @param feedIri the feed iri
    * @throws SystemException if a system exception occurred
    */
    public void removeByFeedIri(java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    /**
    * Removes all the feed references where feedType = &#63; from the database.
    *
    * @param feedType the feed type
    * @throws SystemException if a system exception occurred
    */
    public void removeByFeedType(java.lang.String feedType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where feedVersion = &#63; from the database.
    *
    * @param feedVersion the feed version
    * @throws SystemException if a system exception occurred
    */
    public void removeByFeedVersion(java.lang.String feedVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where contentProviderId = &#63; from the database.
    *
    * @param contentProviderId the content provider ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContentProvider(java.lang.String contentProviderId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references where hrefHash = &#63; from the database.
    *
    * @param hrefHash the href hash
    * @throws SystemException if a system exception occurred
    */
    public void removeByHrefHash(java.lang.String hrefHash)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the feed references from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where groupId = &#63; and removed = &#63;.
    *
    * @param groupId the group ID
    * @param removed the removed
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByGroupIdWithRemoved(long groupId, boolean removed)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where groupId = &#63; and feedType = &#63;.
    *
    * @param groupId the group ID
    * @param feedType the feed type
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByGroupIdWithType(long groupId, java.lang.String feedType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where feedIri = &#63;.
    *
    * @param feedIri the feed iri
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByFeedIri(java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where feedType = &#63;.
    *
    * @param feedType the feed type
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByFeedType(java.lang.String feedType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where feedVersion = &#63;.
    *
    * @param feedVersion the feed version
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByFeedVersion(java.lang.String feedVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where contentProviderId = &#63;.
    *
    * @param contentProviderId the content provider ID
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByContentProvider(java.lang.String contentProviderId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references where hrefHash = &#63;.
    *
    * @param hrefHash the href hash
    * @return the number of matching feed references
    * @throws SystemException if a system exception occurred
    */
    public int countByHrefHash(java.lang.String hrefHash)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of feed references.
    *
    * @return the number of feed references
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
