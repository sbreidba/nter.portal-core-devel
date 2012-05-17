package org.nterlearning.datamodel.catalog.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.service.ComponentRecordServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link org.nterlearning.datamodel.catalog.service.ComponentRecordServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link org.nterlearning.datamodel.catalog.model.ComponentRecordSoap}.
 * If the method in the service utility returns a
 * {@link org.nterlearning.datamodel.catalog.model.ComponentRecord}, that is translated to a
 * {@link org.nterlearning.datamodel.catalog.model.ComponentRecordSoap}. Methods that SOAP cannot
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
 * @see       ComponentRecordServiceHttp
 * @see       org.nterlearning.datamodel.catalog.model.ComponentRecordSoap
 * @see       org.nterlearning.datamodel.catalog.service.ComponentRecordServiceUtil
 * @generated
 */
public class ComponentRecordServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(ComponentRecordServiceSoap.class);

    public static org.nterlearning.datamodel.catalog.model.ComponentRecordSoap[] findByComponentIri(
        java.lang.String componentIri) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> returnValue =
                ComponentRecordServiceUtil.findByComponentIri(componentIri);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.ComponentRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.ComponentRecordSoap[] findByCourseRecordId(
        long courseRecordId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> returnValue =
                ComponentRecordServiceUtil.findByCourseRecordId(courseRecordId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.ComponentRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
