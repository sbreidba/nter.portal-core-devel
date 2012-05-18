package org.nterlearning.datamodel.catalog.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.service.FeedReferenceServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link org.nterlearning.datamodel.catalog.service.FeedReferenceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link org.nterlearning.datamodel.catalog.model.FeedReferenceSoap}.
 * If the method in the service utility returns a
 * {@link org.nterlearning.datamodel.catalog.model.FeedReference}, that is translated to a
 * {@link org.nterlearning.datamodel.catalog.model.FeedReferenceSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedReferenceServiceHttp
 * @see       org.nterlearning.datamodel.catalog.model.FeedReferenceSoap
 * @see       org.nterlearning.datamodel.catalog.service.FeedReferenceServiceUtil
 * @generated
 */
public class FeedReferenceServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(FeedReferenceServiceSoap.class);

    public static org.nterlearning.datamodel.catalog.model.FeedReferenceSoap findByFeedIri(
        java.lang.String feedIri) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.FeedReference returnValue = FeedReferenceServiceUtil.findByFeedIri(feedIri);

            return org.nterlearning.datamodel.catalog.model.FeedReferenceSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.FeedReferenceSoap findByFeedHref(
        java.lang.String href) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.FeedReference returnValue = FeedReferenceServiceUtil.findByFeedHref(href);

            return org.nterlearning.datamodel.catalog.model.FeedReferenceSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
