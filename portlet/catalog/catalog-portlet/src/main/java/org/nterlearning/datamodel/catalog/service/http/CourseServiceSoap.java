package org.nterlearning.datamodel.catalog.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.service.CourseServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link org.nterlearning.datamodel.catalog.service.CourseServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link org.nterlearning.datamodel.catalog.model.CourseSoap}.
 * If the method in the service utility returns a
 * {@link org.nterlearning.datamodel.catalog.model.Course}, that is translated to a
 * {@link org.nterlearning.datamodel.catalog.model.CourseSoap}. Methods that SOAP cannot
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
 * @see       CourseServiceHttp
 * @see       org.nterlearning.datamodel.catalog.model.CourseSoap
 * @see       org.nterlearning.datamodel.catalog.service.CourseServiceUtil
 * @generated
 */
public class CourseServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(CourseServiceSoap.class);

    public static org.nterlearning.datamodel.catalog.model.Course[] findAllValidCourses()
        throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findAllValidCourses();

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findAllValidCourses(
        int start, int end) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findAllValidCourses(start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseSoap findByCourseId(
        long courseId) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.Course returnValue = CourseServiceUtil.findByCourseId(courseId);

            return org.nterlearning.datamodel.catalog.model.CourseSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseSoap findByCourseIri(
        java.lang.String courseIri) throws RemoteException {
        try {
            org.nterlearning.datamodel.catalog.model.Course returnValue = CourseServiceUtil.findByCourseIri(courseIri);

            return org.nterlearning.datamodel.catalog.model.CourseSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findByGroupId(
        long groupId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findByGroupId(groupId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findByGroupId(
        long groupId, int start, int end) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findByGroupId(groupId, start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findByCompanyId(
        long companyId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findByCompanyId(companyId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findByCompanyId(
        long companyId, int start, int end) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findByCompanyId(companyId, start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findByFeedReferenceId(
        long feedReferenceId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findByFeedReferenceId(feedReferenceId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.Course[] findAllCourses(
        int start, int end) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.Course> returnValue =
                CourseServiceUtil.findAllCourses(start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.Course[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
