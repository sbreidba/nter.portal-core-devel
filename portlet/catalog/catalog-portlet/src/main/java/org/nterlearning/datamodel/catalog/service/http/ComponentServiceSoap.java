package org.nterlearning.datamodel.catalog.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.service.ComponentServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link org.nterlearning.datamodel.catalog.service.ComponentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link org.nterlearning.datamodel.catalog.model.ComponentSoap}.
 * If the method in the service utility returns a
 * {@link org.nterlearning.datamodel.catalog.model.Component}, that is translated to a
 * {@link org.nterlearning.datamodel.catalog.model.ComponentSoap}. Methods that SOAP cannot
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
 * @see       ComponentServiceHttp
 * @see       org.nterlearning.datamodel.catalog.model.ComponentSoap
 * @see       org.nterlearning.datamodel.catalog.service.ComponentServiceUtil
 * @generated
 */
public class ComponentServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(ComponentServiceSoap.class);

    public static org.nterlearning.datamodel.catalog.model.ComponentSoap[] findByCompanyId(
        long companyId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Component> returnValue =
                ComponentServiceUtil.findByCompanyId(companyId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.ComponentSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.ComponentSoap findByComponentId(
        long componentId) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.Component returnValue = ComponentServiceUtil.findByComponentId(componentId);

            return org.nterlearning.datamodel.catalog.model.ComponentSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.ComponentSoap findByComponentIri(
        java.lang.String componentIri) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.Component returnValue = ComponentServiceUtil.findByComponentIri(componentIri);

            return org.nterlearning.datamodel.catalog.model.ComponentSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.ComponentSoap[] findByFeedReferenceId(
        java.lang.Long feedRefId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Component> returnValue =
                ComponentServiceUtil.findByFeedReferenceId(feedRefId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.ComponentSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
