/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.nterlearning.datamodel.catalog.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;

import org.nterlearning.datamodel.catalog.NoSuchCourseRecordException;
import org.nterlearning.datamodel.catalog.model.ComponentRecord;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.CourseRecordLocalServiceBaseImpl;
import org.nterlearning.datamodel.catalog.service.persistence.CourseRecordFinderUtil;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the course record local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.CourseRecordLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.CourseRecordLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil
 */
public class CourseRecordLocalServiceImpl
    extends CourseRecordLocalServiceBaseImpl {

    static Log mLog = LogFactoryUtil.getLog(CourseRecordLocalServiceImpl.class);

    @Override
    public CourseRecord addCourseRecord(CourseRecord courseRecord)
            throws SystemException {

        long id = counterLocalService.increment(CourseRecord.class.getName());
        courseRecord.setPrimaryKey(id);

        addCourseRecordResource(courseRecord);

        return super.addCourseRecord(courseRecord);
    }

    public CourseRecord addCourseRecord(
            Long feedRefId, String courseRecordIRI, long userId, String singleSignOnValue,
            String courseIRI, Date updatedDate, String completionStatus,
            boolean userHidden, boolean assigned)
                throws SystemException {

        long id = counterLocalService.increment(CourseRecord.class.getName());

        CourseRecord record = courseRecordPersistence.create(id);
        record.setFeedReferenceId(feedRefId);
        record.setCourseRecordIri(courseRecordIRI);
        record.setUserId(userId);
        record.setSingleSignOnValue(singleSignOnValue);
        record.setCourseIri(courseIRI);
        record.setUpdatedDate(updatedDate);
        record.setCompletionStatus(completionStatus);
        record.setRemoved(false);
        // do not set removedDate
        record.setUserHidden(userHidden);
        record.setAssigned(assigned);

        addCourseRecordResource(record);

        return super.addCourseRecord(record);
    }

    public void deleteCourseRecord(CourseRecord courseRecord)
            throws PortalException, SystemException {
        deleteAllChildren(courseRecord);
        deleteCourseRecordResource(courseRecord);
        super.deleteCourseRecord(courseRecord);
    }

    public void deleteAllChildren(CourseRecord courseRecord)
            throws PortalException, SystemException {
        for (ComponentRecord componentRecord : CourseRecordLocalServiceUtil.getComponentRecords(courseRecord)) {
            ComponentRecordLocalServiceUtil.deleteComponentRecord(componentRecord);
        }
    }

    public void addCourseRecordResource(CourseRecord record)
            throws SystemException {

        long companyId = CompanyThreadLocal.getCompanyId();

        if (companyId == 0) {
            // hack to get the company id if one can't be found in the thread.
            // assumes only 1 company per NTER instance
            companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
        }

        long groupId = 0;

        Course course = CourseLocalServiceUtil.fetchByCourseIri(record.getCourseIri());
        if (course != null) {
            groupId = course.getGroupId();
            companyId = course.getCompanyId();
        }

        try {
            ResourceLocalServiceUtil.addResources(companyId, groupId, record.getUserId(), CourseRecord.class.getName(), record.getPrimaryKey(), false, true, true);
        }
        catch(PortalException pe) {
            mLog.error(pe.getMessage());
        }
    }

    public void deleteCourseRecordResource(CourseRecord record) {
        try {
            ResourceLocalServiceUtil
                    .deleteResource(CompanyThreadLocal.getCompanyId(),
                                    CourseRecord.class.getName(),
                                    ResourceConstants.SCOPE_INDIVIDUAL,
                                    record.getPrimaryKey());
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }

    public CourseRecord findByPrimaryKey(long courseRecordId) throws NoSuchCourseRecordException, SystemException {
        return courseRecordPersistence.findByPrimaryKey(courseRecordId);
    }

    public CourseRecord fetchByPrimaryKey(long courseRecordId) throws SystemException {
        return courseRecordPersistence.fetchByPrimaryKey(courseRecordId);
    }

    public CourseRecord findByCourseRecordIri(String courseRecordIri) throws NoSuchCourseRecordException, SystemException {
        return courseRecordPersistence.findByCourseRecordIri(courseRecordIri);
    }

    public CourseRecord fetchByCourseRecordIri(String courseRecordIri) throws SystemException {
        return courseRecordPersistence.fetchByCourseRecordIri(courseRecordIri);
    }

    public List<CourseRecord> findByCourseIri(String courseIri) throws NoSuchCourseRecordException, SystemException {
        return courseRecordPersistence.findByCourseIri(courseIri);
    }

    public List<Object[]> findByUserIdFilterSorted(long userId, long classNameId, String filterType, String sortType, boolean asc, int start, int end) throws SystemException {
        return CourseRecordFinderUtil.findByUserIdFilterSorted(userId, classNameId, filterType, sortType, asc, start, end);
    }

    public long countByUserIdFilter(long userId, long classNameId, String filterType) throws SystemException {
        return CourseRecordFinderUtil.countByUserIdFilter(userId, classNameId, filterType);
    }

    public List<CourseRecord> findBySingleSignOnValue(String singleSignOnValue) throws SystemException {
        return courseRecordPersistence.findBySingleSignOnValue(singleSignOnValue);
    }

    public List<CourseRecord> findBySingleSignOnValue(String singleSignOnValue, int start, int end) throws SystemException {
        return courseRecordPersistence.findBySingleSignOnValue(singleSignOnValue, start, end);
    }

    public List<CourseRecord> findByUserId(Long userId) throws SystemException {
        return courseRecordPersistence.findByUserId(userId);
    }

    public List<CourseRecord> findByUserId(Long userId, int start, int end) throws SystemException {
        return courseRecordPersistence.findByUserId(userId, start, end);
    }

    public long countAccessedByCourseIri(String courseIri) throws SystemException {
        return CourseRecordFinderUtil.countAccessedByCourseIri(courseIri);
    }

    public long countCompletedByCourseIri(String courseIri) throws SystemException {
        return CourseRecordFinderUtil.countCompletedByCourseIri(courseIri);
    }

    public List<CourseRecord> findByFeedReferenceId(long feedRefId) throws SystemException {
        return courseRecordPersistence.findByFeedReferenceId(feedRefId);
    }

    public List<ComponentRecord> getComponentRecords(CourseRecord courseRecord)
        throws SystemException {
        return courseRecordPersistence.getComponentRecords(courseRecord.getCourseRecordId());
    }

    public List<ComponentRecord> getComponentRecords(long courseRecordPrimaryKey)
        throws SystemException {
        return courseRecordPersistence.getComponentRecords(courseRecordPrimaryKey);
    }
}