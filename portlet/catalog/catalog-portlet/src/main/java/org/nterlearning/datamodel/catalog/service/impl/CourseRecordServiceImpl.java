package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.CourseRecordServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the course record remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.CourseRecordService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRecordServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseRecordServiceUtil
 */
public class CourseRecordServiceImpl extends CourseRecordServiceBaseImpl {

    public CourseRecord findByPrimaryKey(long courseRecordId)
            throws NoSuchCourseRecordException, SystemException {
        return CourseRecordLocalServiceUtil.findByPrimaryKey(courseRecordId);
    }

    public CourseRecord findByCourseRecordIri(String courseRecordIri)
            throws NoSuchCourseRecordException, SystemException {
            return CourseRecordLocalServiceUtil.findByCourseRecordIri(courseRecordIri);
    }

    public List<CourseRecord> findByCourseIri(String courseIri)
            throws NoSuchCourseRecordException, SystemException {
        return CourseRecordLocalServiceUtil.findByCourseIri(courseIri);
    }

    public List<CourseRecord> findBySingleSignOnValue(String singleSignOnValue)
            throws SystemException {
        return CourseRecordLocalServiceUtil.findBySingleSignOnValue(singleSignOnValue);
    }

    public List<CourseRecord> findByUserId(Long userId)
            throws SystemException {
        return CourseRecordLocalServiceUtil.findByUserId(userId);
    }

    public List<CourseRecord> findByFeedReferenceId(long feedRefId)
            throws SystemException {
        return CourseRecordLocalServiceUtil.findByFeedReferenceId(feedRefId);
    }

    public List<ComponentRecord> getComponentRecords(CourseRecord courseRecord)
            throws SystemException {
        return CourseRecordLocalServiceUtil.getComponentRecords(courseRecord.getCourseRecordId());
    }

    public List<ComponentRecord> getComponentRecords(long courseRecordPrimaryKey)
            throws SystemException {
        return CourseRecordLocalServiceUtil.getComponentRecords(courseRecordPrimaryKey);
    }
}
