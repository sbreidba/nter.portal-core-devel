package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetCategory;
import org.nterlearning.datamodel.catalog.NoSuchCourseException;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.CourseServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the course remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.CourseService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.CourseServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseServiceUtil
 */
public class CourseServiceImpl extends CourseServiceBaseImpl {

    public List<Course> findAllValidCourses() throws SystemException {
        return CourseLocalServiceUtil.findAllValidCourses();
    }


    public List<Course> findAllValidCourses(int start, int end)  throws SystemException {
        return CourseLocalServiceUtil.findAllValidCourses(start, end);
    }

    public Course findByCourseId(long courseId)
            throws NoSuchCourseException, SystemException {
        return CourseLocalServiceUtil.findByCourseId(courseId);
    }


    public Course findByCourseIri(String courseIri)
            throws SystemException, NoSuchCourseException {
        return CourseLocalServiceUtil.findByCourseIri(courseIri);
    }


    public List<Course> findByGroupId(long groupId)
            throws SystemException {
        return CourseLocalServiceUtil.findByGroupId(groupId);
    }


    public List<Course> findByGroupId(long groupId, int start, int end)
            throws SystemException {
        return CourseLocalServiceUtil.findByGroupId(groupId,  start, end);
    }


    public List<Course> findByCompanyId(long companyId)
            throws SystemException {
        return CourseLocalServiceUtil.findByCompanyId(companyId);
    }


    public List<Course> findByCompanyId(long companyId, int start, int end)
            throws SystemException {
        return CourseLocalServiceUtil.findByCompanyId(companyId, start, end);
    }


    public List<Course> findByFeedReferenceId(long feedReferenceId)
            throws SystemException {
        return CourseLocalServiceUtil.findByFeedReferenceId(feedReferenceId);
    }


    public List<Course> findAllCourses(int start, int end)
            throws SystemException {
        return CourseLocalServiceUtil.findAllCourses(start, end);
    }
}
