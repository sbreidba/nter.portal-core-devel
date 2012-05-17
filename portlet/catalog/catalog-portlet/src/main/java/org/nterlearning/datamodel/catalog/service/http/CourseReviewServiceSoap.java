package org.nterlearning.datamodel.catalog.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link org.nterlearning.datamodel.catalog.model.CourseReviewSoap}.
 * If the method in the service utility returns a
 * {@link org.nterlearning.datamodel.catalog.model.CourseReview}, that is translated to a
 * {@link org.nterlearning.datamodel.catalog.model.CourseReviewSoap}. Methods that SOAP cannot
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
 * @see       CourseReviewServiceHttp
 * @see       org.nterlearning.datamodel.catalog.model.CourseReviewSoap
 * @see       org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil
 * @generated
 */
public class CourseReviewServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(CourseReviewServiceSoap.class);

    public static double findScoreByReviewId(long reviewId)
        throws RemoteException {
        try {
            double returnValue = CourseReviewServiceUtil.findScoreByReviewId(reviewId);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static java.lang.Double[] findScoreByCourseId(long courseId)
        throws RemoteException {
        try {
            java.util.List<java.lang.Double> returnValue = CourseReviewServiceUtil.findScoreByCourseId(courseId);

            return returnValue.toArray(new java.lang.Double[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByCourseId(
        long courseId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByCourseId(courseId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByCourseId(
        long courseId, int start, int end) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByCourseId(courseId, start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByCourseIdWithUserId(
        long userId, long courseId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByCourseIdWithUserId(userId,
                    courseId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByCourseIdWithUserId(
        long userId, long courseId, int start, int end)
        throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByCourseIdWithUserId(userId,
                    courseId, start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByUserId(
        long userId) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByUserId(userId);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByUserId(
        long userId, int start, int end) throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByUserId(userId, start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static org.nterlearning.datamodel.catalog.model.CourseReviewSoap[] findByCourseIdWithRemoved(
        long courseId, boolean removed, int start, int end)
        throws RemoteException {
        try {
            java.util.List<org.nterlearning.datamodel.catalog.model.CourseReview> returnValue =
                CourseReviewServiceUtil.findByCourseIdWithRemoved(courseId,
                    removed, start, end);

            return returnValue.toArray(new org.nterlearning.datamodel.catalog.model.CourseReviewSoap[returnValue.size()]);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static long countByCourseIdWithRemoved(long courseId, boolean removed)
        throws RemoteException {
        try {
            long returnValue = CourseReviewServiceUtil.countByCourseIdWithRemoved(courseId,
                    removed);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
