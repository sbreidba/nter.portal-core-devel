package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.ExternalLink;

import java.util.List;

/**
 * The persistence utility for the external link service. This utility wraps {@link ExternalLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkPersistence
 * @see ExternalLinkPersistenceImpl
 * @generated
 */
public class ExternalLinkUtil {
    private static ExternalLinkPersistence _persistence;

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
    public static void clearCache(ExternalLink externalLink) {
        getPersistence().clearCache(externalLink);
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
    public static List<ExternalLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ExternalLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ExternalLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ExternalLink update(ExternalLink externalLink, boolean merge)
        throws SystemException {
        return getPersistence().update(externalLink, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ExternalLink update(ExternalLink externalLink, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(externalLink, merge, serviceContext);
    }

    /**
    * Caches the external link in the entity cache if it is enabled.
    *
    * @param externalLink the external link
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink) {
        getPersistence().cacheResult(externalLink);
    }

    /**
    * Caches the external links in the entity cache if it is enabled.
    *
    * @param externalLinks the external links
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> externalLinks) {
        getPersistence().cacheResult(externalLinks);
    }

    /**
    * Creates a new external link with the primary key. Does not add the external link to the database.
    *
    * @param linkId the primary key for the new external link
    * @return the new external link
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink create(
        long linkId) {
        return getPersistence().create(linkId);
    }

    /**
    * Removes the external link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param linkId the primary key of the external link
    * @return the external link that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink remove(
        long linkId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence().remove(linkId);
    }

    public static org.nterlearning.datamodel.catalog.model.ExternalLink updateImpl(
        org.nterlearning.datamodel.catalog.model.ExternalLink externalLink,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(externalLink, merge);
    }

    /**
    * Returns the external link with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchExternalLinkException} if it could not be found.
    *
    * @param linkId the primary key of the external link
    * @return the external link
    * @throws org.nterlearning.datamodel.catalog.NoSuchExternalLinkException if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByPrimaryKey(
        long linkId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence().findByPrimaryKey(linkId);
    }

    /**
    * Returns the external link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param linkId the primary key of the external link
    * @return the external link, or <code>null</code> if a external link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.ExternalLink fetchByPrimaryKey(
        long linkId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(linkId);
    }

    /**
    * Returns all the external links where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        long courseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseId(courseId, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseId(
        long courseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseId(courseId, start, end, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseId_First(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence().findByCourseId_First(courseId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseId_Last(
        long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence().findByCourseId_Last(courseId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink[] findByCourseId_PrevAndNext(
        long linkId, long courseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByCourseId_PrevAndNext(linkId, courseId,
            orderByComparator);
    }

    /**
    * Returns all the external links where courseId = &#63; and linkType = &#63;.
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        long courseId, java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIdWithType(courseId, linkType);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        long courseId, java.lang.String linkType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithType(courseId, linkType, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByCourseIdWithType(
        long courseId, java.lang.String linkType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithType(courseId, linkType, start, end,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseIdWithType_First(
        long courseId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByCourseIdWithType_First(courseId, linkType,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByCourseIdWithType_Last(
        long courseId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByCourseIdWithType_Last(courseId, linkType,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink[] findByCourseIdWithType_PrevAndNext(
        long linkId, long courseId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByCourseIdWithType_PrevAndNext(linkId, courseId,
            linkType, orderByComparator);
    }

    /**
    * Returns all the external links where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentId(componentId);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        long componentId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentId(componentId, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentId(
        long componentId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentId(componentId, start, end, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentId_First(
        long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByComponentId_First(componentId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentId_Last(
        long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByComponentId_Last(componentId, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink[] findByComponentId_PrevAndNext(
        long linkId, long componentId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByComponentId_PrevAndNext(linkId, componentId,
            orderByComparator);
    }

    /**
    * Returns all the external links where componentId = &#63; and linkType = &#63;.
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @return the matching external links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        long componentId, java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByComponentIdWithType(componentId, linkType);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        long componentId, java.lang.String linkType, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentIdWithType(componentId, linkType, start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findByComponentIdWithType(
        long componentId, java.lang.String linkType, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByComponentIdWithType(componentId, linkType, start,
            end, orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentIdWithType_First(
        long componentId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByComponentIdWithType_First(componentId, linkType,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink findByComponentIdWithType_Last(
        long componentId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByComponentIdWithType_Last(componentId, linkType,
            orderByComparator);
    }

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
    public static org.nterlearning.datamodel.catalog.model.ExternalLink[] findByComponentIdWithType_PrevAndNext(
        long linkId, long componentId, java.lang.String linkType,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchExternalLinkException {
        return getPersistence()
                   .findByComponentIdWithType_PrevAndNext(linkId, componentId,
            linkType, orderByComparator);
    }

    /**
    * Returns all the external links.
    *
    * @return the external links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the external links where courseId = &#63; from the database.
    *
    * @param courseId the course ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseId(courseId);
    }

    /**
    * Removes all the external links where courseId = &#63; and linkType = &#63; from the database.
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIdWithType(long courseId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIdWithType(courseId, linkType);
    }

    /**
    * Removes all the external links where componentId = &#63; from the database.
    *
    * @param componentId the component ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByComponentId(componentId);
    }

    /**
    * Removes all the external links where componentId = &#63; and linkType = &#63; from the database.
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentIdWithType(long componentId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByComponentIdWithType(componentId, linkType);
    }

    /**
    * Removes all the external links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of external links where courseId = &#63;.
    *
    * @param courseId the course ID
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseId(long courseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseId(courseId);
    }

    /**
    * Returns the number of external links where courseId = &#63; and linkType = &#63;.
    *
    * @param courseId the course ID
    * @param linkType the link type
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIdWithType(long courseId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIdWithType(courseId, linkType);
    }

    /**
    * Returns the number of external links where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentId(componentId);
    }

    /**
    * Returns the number of external links where componentId = &#63; and linkType = &#63;.
    *
    * @param componentId the component ID
    * @param linkType the link type
    * @return the number of matching external links
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentIdWithType(long componentId,
        java.lang.String linkType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentIdWithType(componentId, linkType);
    }

    /**
    * Returns the number of external links.
    *
    * @return the number of external links
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ExternalLinkPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ExternalLinkPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    ExternalLinkPersistence.class.getName());

            ReferenceRegistry.registerReference(ExternalLinkUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ExternalLinkPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ExternalLinkUtil.class,
            "_persistence");
    }
}
