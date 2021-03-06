package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the course related remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseRelatedServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRelatedServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.CourseRelatedServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface CourseRelatedService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CourseRelatedServiceUtil} to access the course related remote service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.CourseRelatedServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
