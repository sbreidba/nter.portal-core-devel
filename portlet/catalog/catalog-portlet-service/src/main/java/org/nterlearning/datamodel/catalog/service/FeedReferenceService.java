package org.nterlearning.datamodel.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the feed reference remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedReferenceServiceUtil
 * @see org.nterlearning.datamodel.catalog.service.base.FeedReferenceServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.impl.FeedReferenceServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface FeedReferenceService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FeedReferenceServiceUtil} to access the feed reference remote service. Add custom service methods to {@link org.nterlearning.datamodel.catalog.service.impl.FeedReferenceServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedIri(
        java.lang.String feedIri)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;

    public org.nterlearning.datamodel.catalog.model.FeedReference findByFeedHref(
        java.lang.String href)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;
}
