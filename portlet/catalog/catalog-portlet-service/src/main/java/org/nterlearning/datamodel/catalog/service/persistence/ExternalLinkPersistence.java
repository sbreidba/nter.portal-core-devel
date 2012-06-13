package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.ExternalLink;

/**
 * The persistence interface for the external link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkPersistenceImpl
 * @see ExternalLinkUtil
 * @generated
 */
public interface ExternalLinkPersistence extends BasePersistence<ExternalLink> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ExternalLinkUtil} to access the external link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the external link in the entity cache if it is enabled.
    *
    * @param externalLink the external link
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink);

    /**
    * Caches the external links in the entity cache if it is enabled.
    *
    * @param externalLinks the external links
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> externalLinks);

    /**
    * Creates a new external link with the primary key. Does not add the external link to the database.
    *
    * @param linkId the primary key for the new external link
    * @return the new external link
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink create(
        long linkId);

    /**
    * Removes the external link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param linkId the primary key of the external link
    * @return the external link that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink remove(
        long linkId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    public org.nterlearning.datamodel.catalog.model.ExternalLink updateImpl(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the external link with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchExternalLinkException} if it could not be found.
    *
    * @param linkId the primary key of the external link
    * @return the external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByPrimaryKey(
        long linkId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the external link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param linkId the primary key of the external link
    * @return the external link, or <code>null</code> if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink fetchByPrimaryKey(
        long linkId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the external links where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the external links where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @return the range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the external links where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first external link in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the last external link in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the external links before and after the current external link in the ordered set where courseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param linkId the primary key of the current external link
    * @param courseId the course ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink[] findByCourseId_PrevAndNext(
        long linkId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns all the external links where courseId = &#63; and linkType = &#63;.
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        long courseId, java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the external links where courseId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @return the range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        long courseId, java.lang.String linkType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the external links where courseId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        long courseId, java.lang.String linkType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first external link in the ordered set where courseId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseIdWithType_First(
        long courseId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the last external link in the ordered set where courseId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseIdWithType_Last(
        long courseId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the external links before and after the current external link in the ordered set where courseId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param linkId the primary key of the current external link
    * @param courseId the course ID
    * @param linkType the link type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink[] findByCourseIdWithType_PrevAndNext(
        long linkId, long courseId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns all the external links where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the external links where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @return the range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        long componentId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the external links where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        long componentId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first external link in the ordered set where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentId_First(
        long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the last external link in the ordered set where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentId_Last(
        long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the external links before and after the current external link in the ordered set where componentId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param linkId the primary key of the current external link
    * @param componentId the component ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink[] findByComponentId_PrevAndNext(
        long linkId, long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns all the external links where componentId = &#63; and linkType = &#63;.
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        long componentId, java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the external links where componentId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @return the range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        long componentId, java.lang.String linkType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the external links where componentId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        long componentId, java.lang.String linkType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first external link in the ordered set where componentId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentIdWithType_First(
        long componentId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the last external link in the ordered set where componentId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a matching external link could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentIdWithType_Last(
        long componentId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns the external links before and after the current external link in the ordered set where componentId = &#63; and linkType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param linkId the primary key of the current external link
    * @param componentId the component ID
    * @param linkType the link type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.ExternalLink[] findByComponentIdWithType_PrevAndNext(
        long linkId, long componentId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException;

    /**
    * Returns all the external links.
    *
    * @return the external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the external links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @return the range of external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the external links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of external links
    * @param end the upper bound of the range of external links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of external links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the external links where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the external links where courseId = &#63; and linkType = &#63; from the database.
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseIdWithType(long courseId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the external links where componentId = &#63; from the database.
    *
    * @param componentId the component ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the external links where componentId = &#63; and linkType = &#63; from the database.
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @throws SystemException if a system exception occurred
    */
    public void removeByComponentIdWithType(long componentId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the external links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of external links where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of external links where courseId = &#63; and linkType = &#63;.
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseIdWithType(long courseId, java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of external links where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public int countByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of external links where componentId = &#63; and linkType = &#63;.
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public int countByComponentIdWithType(long componentId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of external links.
    *
    * @return the number of external links
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
