package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.nterlearning.datamodel.catalog.model.CourseImage;

import java.util.List;

/**
 * The persistence utility for the course image service. This utility wraps {@link CourseImagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImagePersistence
 * @see CourseImagePersistenceImpl
 * @generated
 */
public class CourseImageUtil {
    private static CourseImagePersistence _persistence;

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
    public static void clearCache(CourseImage courseImage) {
        getPersistence().clearCache(courseImage);
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
    public static List<CourseImage> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CourseImage> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CourseImage> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static CourseImage update(CourseImage courseImage, boolean merge)
        throws SystemException {
        return getPersistence().update(courseImage, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static CourseImage update(CourseImage courseImage, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(courseImage, merge, serviceContext);
    }

    /**
    * Caches the course image in the entity cache if it is enabled.
    *
    * @param courseImage the course image
    */
    public static void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage) {
        getPersistence().cacheResult(courseImage);
    }

    /**
    * Caches the course images in the entity cache if it is enabled.
    *
    * @param courseImages the course images
    */
    public static void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> courseImages) {
        getPersistence().cacheResult(courseImages);
    }

    /**
    * Creates a new course image with the primary key. Does not add the course image to the database.
    *
    * @param courseImageId the primary key for the new course image
    * @return the new course image
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage create(
        long courseImageId) {
        return getPersistence().create(courseImageId);
    }

    /**
    * Removes the course image with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage remove(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException {
        return getPersistence().remove(courseImageId);
    }

    public static org.nterlearning.datamodel.catalog.model.CourseImage updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(courseImage, merge);
    }

    /**
    * Returns the course image with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseImageException} if it could not be found.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage findByPrimaryKey(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException {
        return getPersistence().findByPrimaryKey(courseImageId);
    }

    /**
    * Returns the course image with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image, or <code>null</code> if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage fetchByPrimaryKey(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(courseImageId);
    }

    /**
    * Returns all the course images where courseId = &#63; and language = &#63;.
    *
    * @param courseId the course ID
    * @param language the language
    * @return the matching course images
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findByCourseIdWithLanguage(
        long courseId, java.lang.String language)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCourseIdWithLanguage(courseId, language);
    }

    /**
    * Returns a range of all the course images where courseId = &#63; and language = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param language the language
    * @param start the lower bound of the range of course images
    * @param end the upper bound of the range of course images (not inclusive)
    * @return the range of matching course images
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findByCourseIdWithLanguage(
        long courseId, java.lang.String language, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithLanguage(courseId, language, start, end);
    }

    /**
    * Returns an ordered range of all the course images where courseId = &#63; and language = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param language the language
    * @param start the lower bound of the range of course images
    * @param end the upper bound of the range of course images (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching course images
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findByCourseIdWithLanguage(
        long courseId, java.lang.String language, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCourseIdWithLanguage(courseId, language, start, end,
            orderByComparator);
    }

    /**
    * Returns the first course image in the ordered set where courseId = &#63; and language = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param language the language
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching course image
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a matching course image could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage findByCourseIdWithLanguage_First(
        long courseId, java.lang.String language,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException {
        return getPersistence()
                   .findByCourseIdWithLanguage_First(courseId, language,
            orderByComparator);
    }

    /**
    * Returns the last course image in the ordered set where courseId = &#63; and language = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseId the course ID
    * @param language the language
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching course image
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a matching course image could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage findByCourseIdWithLanguage_Last(
        long courseId, java.lang.String language,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException {
        return getPersistence()
                   .findByCourseIdWithLanguage_Last(courseId, language,
            orderByComparator);
    }

    /**
    * Returns the course images before and after the current course image in the ordered set where courseId = &#63; and language = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param courseImageId the primary key of the current course image
    * @param courseId the course ID
    * @param language the language
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next course image
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static org.nterlearning.datamodel.catalog.model.CourseImage[] findByCourseIdWithLanguage_PrevAndNext(
        long courseImageId, long courseId, java.lang.String language,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException {
        return getPersistence()
                   .findByCourseIdWithLanguage_PrevAndNext(courseImageId,
            courseId, language, orderByComparator);
    }

    /**
    * Returns all the course images.
    *
    * @return the course images
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the course images.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course images
    * @param end the upper bound of the range of course images (not inclusive)
    * @return the range of course images
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the course images.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of course images
    * @param end the upper bound of the range of course images (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of course images
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the course images where courseId = &#63; and language = &#63; from the database.
    *
    * @param courseId the course ID
    * @param language the language
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCourseIdWithLanguage(long courseId,
        java.lang.String language)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCourseIdWithLanguage(courseId, language);
    }

    /**
    * Removes all the course images from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of course images where courseId = &#63; and language = &#63;.
    *
    * @param courseId the course ID
    * @param language the language
    * @return the number of matching course images
    * @throws SystemException if a system exception occurred
    */
    public static int countByCourseIdWithLanguage(long courseId,
        java.lang.String language)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCourseIdWithLanguage(courseId, language);
    }

    /**
    * Returns the number of course images.
    *
    * @return the number of course images
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CourseImagePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CourseImagePersistence) PortletBeanLocatorUtil.locate(org.nterlearning.datamodel.catalog.service.ClpSerializer.getServletContextName(),
                    CourseImagePersistence.class.getName());

            ReferenceRegistry.registerReference(CourseImageUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(CourseImagePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(CourseImageUtil.class,
            "_persistence");
    }
}
