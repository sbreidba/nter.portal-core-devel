package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.NoSuchGlobalCourseReviewException;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the global course review remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.GlobalCourseReviewService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.GlobalCourseReviewServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.GlobalCourseReviewServiceUtil
 */
public class GlobalCourseReviewServiceImpl extends GlobalCourseReviewServiceBaseImpl {

    public GlobalCourseReview findByCourseReviewIri(String courseReviewIri)
            throws SystemException, NoSuchGlobalCourseReviewException {
        return GlobalCourseReviewLocalServiceUtil.findByCourseReviewIri(courseReviewIri);
    }


    public List<GlobalCourseReview> findByCourseId(long courseId) throws SystemException {
        return GlobalCourseReviewLocalServiceUtil.findByCourseId(courseId);
    }


    public List<GlobalCourseReview> findByCourseIri(String courseIri) throws SystemException {
        return GlobalCourseReviewLocalServiceUtil.findByCourseIri(courseIri);
    }


    public List<GlobalCourseReview> findValidByCourseId(long courseId, int start, int end) throws SystemException {
        return GlobalCourseReviewLocalServiceUtil.findValidByCourseId(courseId, start, end);
    }


    public long countValidByCourseId(long courseId) throws SystemException {
        return GlobalCourseReviewLocalServiceUtil.countValidByCourseId(courseId);
    }
}
