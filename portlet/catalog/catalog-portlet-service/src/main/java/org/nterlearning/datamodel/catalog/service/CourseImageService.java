package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the course image remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseImageServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.CourseImageServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseImageServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface CourseImageService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseImageServiceUtil} to access the course image remote service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseImageServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
