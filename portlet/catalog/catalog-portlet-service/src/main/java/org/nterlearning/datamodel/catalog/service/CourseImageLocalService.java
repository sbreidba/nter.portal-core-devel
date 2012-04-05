package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the course image local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImageLocalServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.CourseImageLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseImageLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface CourseImageLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseImageLocalServiceUtil} to access the course image local service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseImageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the course image to the database. Also notifies the appropriate model listeners.
    *
    * @param courseImage the course image
    * @return the course image that was added
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage addCourseImage(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new course image with the primary key. Does not add the course image to the database.
    *
    * @param courseImageId the primary key for the new course image
    * @return the new course image
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage createCourseImage(
        long courseImageId);

    /**
    * Deletes the course image with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param courseImageId the primary key of the course image
    * @throws PortalException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseImage(long courseImageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the course image from the database. Also notifies the appropriate model listeners.
    *
    * @param courseImage the course image
    * @throws SystemException if a system exception occurred
    */
    public void deleteCourseImage(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.CourseImage fetchCourseImage(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the course image with the primary key.
    *
    * @param courseImageId the primary key of the course image
    * @return the course image
    * @throws PortalException if a course image with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.nterlearning.datamodel.catalog.model.CourseImage getCourseImage(
        long courseImageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.nterlearning.datamodel.catalog.model.CourseImage> getCourseImages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of course images.
    *
    * @return the number of course images
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCourseImagesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the course image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseImage the course image
    * @return the course image that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage updateCourseImage(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the course image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param courseImage the course image
    * @param merge whether to merge the course image with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the course image that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.nterlearning.datamodel.catalog.model.CourseImage updateCourseImage(
        org.nterlearning.datamodel.catalog.model.CourseImage courseImage,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);
}
