package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.Component;

import java.util.List;

/**
 * The persistence utility for the component service. This utility wraps {@link ComponentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ComponentPersistence
 * @see ComponentPersistenceImpl
 * @generated
 */
public class ComponentUtil {
    private static ComponentPersistence _persistence;

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
    public static void clearCache(Component component) {
        getPersistence().clearCache(component);
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
    public static List<Component> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Component> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Component> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static Component update(Component component, boolean merge)
        throws SystemException {
        return getPersistence().update(component, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static Component update(Component component, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(component, merge, serviceContext);
    }

    /**
    * Caches the component in the entity cache if it is enabled.
    *
    * @param component the component
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.Component component) {
        getPersistence().cacheResult(component);
    }

    /**
    * Caches the components in the entity cache if it is enabled.
    *
    * @param components the components
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.Component> components) {
        getPersistence().cacheResult(components);
    }

    /**
    * Creates a new component with the primary key. Does not add the component to the database.
    *
    * @param componentId the primary key for the new component
    * @return the new component
    */
    public static org.nterlearning.datamodel.catalog.model.Component create(
        long componentId) {
        return getPersistence().create(componentId);
    }

    /**
    * Removes the component with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentId the primary key of the component
    * @return the component that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component remove(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence().remove(componentId);
    }

    public static org.nterlearning.datamodel.catalog.model.Component updateImpl(
        org.nterlearning.datamodel.catalog.model.Component component,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(component, merge);
    }

    /**
    * Returns the component with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentException} if it could not be found.
    *
    * @param componentId the primary key of the component
    * @return the component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByPrimaryKey(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence().findByPrimaryKey(componentId);
    }

    /**
    * Returns the component with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param componentId the primary key of the component
    * @return the component, or <code>null</code> if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component fetchByPrimaryKey(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(componentId);
    }

    /**
    * Returns the component where componentId = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentException} if it could not be found.
    *
    * @param componentId the component ID
    * @return the matching component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence().findByComponentId(componentId);
    }

    /**
    * Returns the component where componentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param componentId the component ID
    * @return the matching component, or <code>null</code> if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component fetchByComponentId(
        long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByComponentId(componentId);
    }

    /**
    * Returns the component where componentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param componentId the component ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching component, or <code>null</code> if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component fetchByComponentId(
        long componentId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByComponentId(componentId, retrieveFromCache);
    }

    /**
    * Returns all the components where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

    /**
    * Returns a range of all the components where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of matching components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

    /**
    * Returns an ordered range of all the components where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByCompanyId(
        long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first component in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByCompanyId_First(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the last component in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByCompanyId_Last(
        long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the components before and after the current component in the ordered set where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the primary key of the current component
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component[] findByCompanyId_PrevAndNext(
        long componentId, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(componentId, companyId,
            orderByComparator);
    }

    /**
    * Returns the component where componentIri = &#63; or throws a {@link org.nterlearning.datamodel.catalog.NoSuchComponentException} if it could not be found.
    *
    * @param componentIri the component iri
    * @return the matching component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence().findByComponentIri(componentIri);
    }

    /**
    * Returns the component where componentIri = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param componentIri the component iri
    * @return the matching component, or <code>null</code> if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component fetchByComponentIri(
        java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByComponentIri(componentIri);
    }

    /**
    * Returns the component where componentIri = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param componentIri the component iri
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching component, or <code>null</code> if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component fetchByComponentIri(
        java.lang.String componentIri, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByComponentIri(componentIri, retrieveFromCache);
    }

    /**
    * Returns all the components where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the matching components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns a range of all the components where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of matching components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        long feedReferenceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFeedReferenceId(feedReferenceId, start, end);
    }

    /**
    * Returns an ordered range of all the components where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findByFeedReferenceId(
        long feedReferenceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFeedReferenceId(feedReferenceId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first component in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByFeedReferenceId_First(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence()
                   .findByFeedReferenceId_First(feedReferenceId,
            orderByComparator);
    }

    /**
    * Returns the last component in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a matching component could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component findByFeedReferenceId_Last(
        long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence()
                   .findByFeedReferenceId_Last(feedReferenceId,
            orderByComparator);
    }

    /**
    * Returns the components before and after the current component in the ordered set where feedReferenceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param componentId the primary key of the current component
    * @param feedReferenceId the feed reference ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next component
    * @throws org.nterlearning.datamodel.catalog.NoSuchComponentException if a component with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.Component[] findByFeedReferenceId_PrevAndNext(
        long componentId, long feedReferenceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        return getPersistence()
                   .findByFeedReferenceId_PrevAndNext(componentId,
            feedReferenceId, orderByComparator);
    }

    /**
    * Returns all the components.
    *
    * @return the components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the components.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the components.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of components
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Component> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the component where componentId = &#63; from the database.
    *
    * @param componentId the component ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        getPersistence().removeByComponentId(componentId);
    }

    /**
    * Removes all the components where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Removes the component where componentIri = &#63; from the database.
    *
    * @param componentIri the component iri
    * @throws SystemException if a system exception occurred
    */
    public static void removeByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchComponentException {
        getPersistence().removeByComponentIri(componentIri);
    }

    /**
    * Removes all the components where feedReferenceId = &#63; from the database.
    *
    * @param feedReferenceId the feed reference ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByFeedReferenceId(feedReferenceId);
    }

    /**
    * Removes all the components from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of components where componentId = &#63;.
    *
    * @param componentId the component ID
    * @return the number of matching components
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentId(long componentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentId(componentId);
    }

    /**
    * Returns the number of components where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching components
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns the number of components where componentIri = &#63;.
    *
    * @param componentIri the component iri
    * @return the number of matching components
    * @throws SystemException if a system exception occurred
    */
    public static int countByComponentIri(java.lang.String componentIri)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByComponentIri(componentIri);
    }

    /**
    * Returns the number of components where feedReferenceId = &#63;.
    *
    * @param feedReferenceId the feed reference ID
    * @return the number of matching components
    * @throws SystemException if a system exception occurred
    */
    public static int countByFeedReferenceId(long feedReferenceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByFeedReferenceId(feedReferenceId);
    }

    /**
    * Returns the number of components.
    *
    * @return the number of components
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the courses_ componentses associated with the component.
    *
    * @param pk the primary key of the component
    * @return the courses_ componentses associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourses_Componentses(pk);
    }

    /**
    * Returns a range of all the courses_ componentses associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of courses_ componentses associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourses_Componentses(pk, start, end);
    }

    /**
    * Returns an ordered range of all the courses_ componentses associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of courses_ componentses associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Courses_Components> getCourses_Componentses(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getCourses_Componentses(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of courses_ componentses associated with the component.
    *
    * @param pk the primary key of the component
    * @return the number of courses_ componentses associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static int getCourses_ComponentsesSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getCourses_ComponentsesSize(pk);
    }

    /**
    * Returns <code>true</code> if the courses_ components is associated with the component.
    *
    * @param pk the primary key of the component
    * @param courses_ComponentsPK the primary key of the courses_ components
    * @return <code>true</code> if the courses_ components is associated with the component; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourses_Components(long pk,
        long courses_ComponentsPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .containsCourses_Components(pk, courses_ComponentsPK);
    }

    /**
    * Returns <code>true</code> if the component has any courses_ componentses associated with it.
    *
    * @param pk the primary key of the component to check for associations with courses_ componentses
    * @return <code>true</code> if the component has any courses_ componentses associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsCourses_Componentses(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsCourses_Componentses(pk);
    }

    /**
    * Returns all the contributors associated with the component.
    *
    * @param pk the primary key of the component
    * @return the contributors associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getContributors(pk);
    }

    /**
    * Returns a range of all the contributors associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of contributors associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getContributors(pk, start, end);
    }

    /**
    * Returns an ordered range of all the contributors associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contributors associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.Contributor> getContributors(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getContributors(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of contributors associated with the component.
    *
    * @param pk the primary key of the component
    * @return the number of contributors associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static int getContributorsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getContributorsSize(pk);
    }

    /**
    * Returns <code>true</code> if the contributor is associated with the component.
    *
    * @param pk the primary key of the component
    * @param contributorPK the primary key of the contributor
    * @return <code>true</code> if the contributor is associated with the component; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsContributor(long pk, long contributorPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsContributor(pk, contributorPK);
    }

    /**
    * Returns <code>true</code> if the component has any contributors associated with it.
    *
    * @param pk the primary key of the component to check for associations with contributors
    * @return <code>true</code> if the component has any contributors associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsContributors(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsContributors(pk);
    }

    /**
    * Returns all the component records associated with the component.
    *
    * @param pk the primary key of the component
    * @return the component records associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentRecords(pk);
    }

    /**
    * Returns a range of all the component records associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of component records associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentRecords(pk, start, end);
    }

    /**
    * Returns an ordered range of all the component records associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of component records associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> getComponentRecords(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getComponentRecords(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of component records associated with the component.
    *
    * @param pk the primary key of the component
    * @return the number of component records associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static int getComponentRecordsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getComponentRecordsSize(pk);
    }

    /**
    * Returns <code>true</code> if the component record is associated with the component.
    *
    * @param pk the primary key of the component
    * @param componentRecordPK the primary key of the component record
    * @return <code>true</code> if the component record is associated with the component; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsComponentRecord(long pk,
        long componentRecordPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsComponentRecord(pk, componentRecordPK);
    }

    /**
    * Returns <code>true</code> if the component has any component records associated with it.
    *
    * @param pk the primary key of the component to check for associations with component records
    * @return <code>true</code> if the component has any component records associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsComponentRecords(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsComponentRecords(pk);
    }

    /**
    * Returns all the external links associated with the component.
    *
    * @param pk the primary key of the component
    * @return the external links associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getExternalLinks(pk);
    }

    /**
    * Returns a range of all the external links associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @return the range of external links associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getExternalLinks(pk, start, end);
    }

    /**
    * Returns an ordered range of all the external links associated with the component.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the component
    * @param start the lower bound of the range of components
    * @param end the upper bound of the range of components (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of external links associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.ExternalLink> getExternalLinks(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getExternalLinks(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of external links associated with the component.
    *
    * @param pk the primary key of the component
    * @return the number of external links associated with the component
    * @throws SystemException if a system exception occurred
    */
    public static int getExternalLinksSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getExternalLinksSize(pk);
    }

    /**
    * Returns <code>true</code> if the external link is associated with the component.
    *
    * @param pk the primary key of the component
    * @param externalLinkPK the primary key of the external link
    * @return <code>true</code> if the external link is associated with the component; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsExternalLink(long pk, long externalLinkPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsExternalLink(pk, externalLinkPK);
    }

    /**
    * Returns <code>true</code> if the component has any external links associated with it.
    *
    * @param pk the primary key of the component to check for associations with external links
    * @return <code>true</code> if the component has any external links associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsExternalLinks(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsExternalLinks(pk);
    }

    public static ComponentPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ComponentPersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    ComponentPersistence.class.getName());

            ReferenceRegistry.registerReference(ComponentUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ComponentPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ComponentUtil.class, "_persistence");
    }
}
