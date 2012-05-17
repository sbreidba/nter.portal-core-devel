package org.nterlearning.datamodel.catalog.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.service.CourseRecordServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link org.nterlearning.datamodel.catalog.service.CourseRecordServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link org.nterlearning.datamodel.catalog.model.CourseRecordSoap}.
 * If the method in the service utility returns a
 * {@link org.nterlearning.datamodel.catalog.model.CourseRecord}, that is translated to a
 * {@link org.nterlearning.datamodel.catalog.model.CourseRecordSoap}. Methods that SOAP cannot
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
 * @see       CourseRecordServiceHttp
 * @see       org.nterlearning.datamodel.catalog.model.CourseRecordSoap
 * @see       org.nterlearning.datamodel.catalog.service.CourseRecordServiceUtil
 * @generated
 */
public class CourseRecordServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(CourseRecordServiceSoap.class);

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap findByPrimaryKey(
        long courseRecordId) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.CourseRecord returnValue = CourseRecordServiceUtil.findByPrimaryKey(courseRecordId);

            return org.nterlearning.datamodel.catalog.model.CourseRecordSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap findByCourseRecordIri(
        java.lang.String courseRecordIri) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.CourseRecord returnValue = CourseRecordServiceUtil.findByCourseRecordIri(courseRecordIri);

            return org.nterlearning.datamodel.catalog.model.CourseRecordSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap[] findByCourseIri(
        java.lang.String courseIri) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> returnValue =
                CourseRecordServiceUtil.findByCourseIri(courseIri);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap[] findBySingleSignOnValue(
        java.lang.String singleSignOnValue) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> returnValue =
                CourseRecordServiceUtil.findBySingleSignOnValue(singleSignOnValue);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap[] findByUserId(
        java.lang.Long userId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> returnValue =
                CourseRecordServiceUtil.findByUserId(userId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap[] findByFeedReferenceId(
        long feedRefId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseRecord> returnValue =
                CourseRecordServiceUtil.findByFeedReferenceId(feedRefId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap[] getComponentRecords(
        org.nterlearning.datamodel.catalog.model.CourseRecord courseRecord)
        throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> returnValue =
                CourseRecordServiceUtil.getComponentRecords(courseRecord);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseRecordSoap[] getComponentRecords(
        long courseRecordPrimaryKey) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.ComponentRecord> returnValue =
                CourseRecordServiceUtil.getComponentRecords(courseRecordPrimaryKey);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseRecordSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
