package org.nterlearning.datamodel.catalog.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.nterlearning.datamodel.catalog.model.CourseImage;

/**
 * The persistence interface for the course image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImagePersistenceImpl
 * @see CourseImageUtil
 * @generated
 */
public interface CourseImagePersistence extends BasePersistence<CourseImage> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseImageUtil} to access the course image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the course image in the entity cache if it is enabled.
    *
    * @param courseImage the course image
    */
    public void cacheResult(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage);

    /**
    * Caches the course images in the entity cache if it is enabled.
    *
    * @param courseImages the course images
    */
    public void cacheResult(
        java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> courseImages);

    /**
    * Creates a new course image with the primary key. Does not add the course image to the database.
    *
    * @param courseImageId the primary key for the new course image
    * @return the new course image
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage create(
        long courseImageId);

    /**
    * Removes the course image with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image that was removed
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage remove(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException;

    public org.nterlearning.datamodel.catalog.model.CourseImage updateImpl(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course image with the primary key or throws a {@link org.nterlearning.datamodel.catalog.NoSuchCourseImageException} if it could not be found.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image
    * @throws org.nterlearning.datamodel.catalog.NoSuchCourseImageException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage findByPrimaryKey(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException;

    /**
    * Returns the course image with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image, or <code>null</code> if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage fetchByPrimaryKey(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the course images where courseId = &#63; and language = &#63;.
    *
    * @param courseId the course ID
    * @param language the language
    * @return the matching course images
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findByCourseIdWithLanguage(
        long courseId, java.lang.String language)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findByCourseIdWithLanguage(
        long courseId, java.lang.String language, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findByCourseIdWithLanguage(
        long courseId, java.lang.String language, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.nterlearning.datamodel.catalog.model.CourseImage findByCourseIdWithLanguage_First(
        long courseId, java.lang.String language,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException;

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
    public org.nterlearning.datamodel.catalog.model.CourseImage findByCourseIdWithLanguage_Last(
        long courseId, java.lang.String language,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException;

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
    public org.nterlearning.datamodel.catalog.model.CourseImage[] findByCourseIdWithLanguage_PrevAndNext(
        long courseImageId, long courseId, java.lang.String language,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchCourseImageException;

    /**
    * Returns all the course images.
    *
    * @return the course images
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course images where courseId = &#63; and language = &#63; from the database.
    *
    * @param courseId the course ID
    * @param language the language
    * @throws SystemException if a system exception occurred
    */
    public void removeByCourseIdWithLanguage(long courseId,
        java.lang.String language)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the course images from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course images where courseId = &#63; and language = &#63;.
    *
    * @param courseId the course ID
    * @param language the language
    * @return the number of matching course images
    * @throws SystemException if a system exception occurred
    */
    public int countByCourseIdWithLanguage(long courseId,
        java.lang.String language)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course images.
    *
    * @return the number of course images
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
