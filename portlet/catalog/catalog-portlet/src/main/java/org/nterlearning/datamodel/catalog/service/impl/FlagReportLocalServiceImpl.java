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
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.WorkflowDefinitionLinkUtil;
import org.nterlearning.datamodel.catalog.NoSuchFlagReportException;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.model.FlagReport;
import org.nterlearning.datamodel.catalog.model.FlagReportStats;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.impl.FlagReportStatsImpl;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.base.FlagReportLocalServiceBaseImpl;
import org.nterlearning.datamodel.catalog.service.persistence.FlagReportFinderUtil;
import org.nterlearning.utils.FlagReportConstants;

import javax.mail.Flags;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the flag report local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.FlagReportLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.FlagReportLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil
 */
public class FlagReportLocalServiceImpl extends FlagReportLocalServiceBaseImpl {



    @Override
    public FlagReport addFlagReport(FlagReport flagReport) throws SystemException
    {
        throw new IllegalArgumentException("Must pass userId, FlagReport columns, serviceContext when adding a new flag report.");
    }

    public FlagReport addFlagReport(long userId, long classNameId, long classPK,
                            String title, String content, String flagReason, String flagComment,
                            String moderateAction, String moderatorComment, ServiceContext serviceContext) throws PortalException, SystemException {

    // Report
        User user = userPersistence.findByPrimaryKey(userId);
        long groupId = serviceContext.getScopeGroupId();

        Date now = new Date();

        long flagReportId = counterLocalService.increment(FlagReport.class.getName());

        FlagReport flagReport = flagReportPersistence.create(flagReportId);

        flagReport.setGroupId(groupId);
        flagReport.setCompanyId(user.getCompanyId());
        flagReport.setUserId(user.getUserId());
        flagReport.setClassNameId(classNameId);
        flagReport.setClassPK(classPK);
        flagReport.setCreateDate(serviceContext.getCreateDate(now));
        flagReport.setTitle(title);
        flagReport.setContent(content);
        flagReport.setFlagReason(flagReason);
        flagReport.setFlagComment(flagComment);
        flagReport.setModerateAction(moderateAction);
        flagReport.setModeratorComment(moderatorComment);
        flagReport.setStatus(WorkflowConstants.STATUS_DRAFT);

        flagReportPersistence.update(flagReport, false);

        // Resources
        if(serviceContext.isAddGroupPermissions() ||
           serviceContext.isAddGuestPermissions()) {
            addFlagReportResources(flagReport, serviceContext.isAddGroupPermissions(), serviceContext.isAddGuestPermissions());
        } else {
            addFlagReportResources(flagReport, serviceContext.getGroupPermissions(), serviceContext.getGuestPermissions());
        }

        // Asset
        updateAsset(userId, flagReport, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames());

        //Statistics
        updateFlagReportStats(flagReport.getClassNameId(),flagReport.getClassPK());

        // Workflow
        WorkflowHandlerRegistryUtil.startWorkflowInstance(
                user.getCompanyId(),groupId, userId,
                FlagReport.class.getName(),flagReport.getPrimaryKey(), flagReport,
                serviceContext);

        return flagReport;
    }

    public void addFlagReportResources(FlagReport flagReport, boolean addGroupPermissions,
                                         boolean addGuestPermissions) throws PortalException, SystemException {
        resourceLocalService.addResources(
                flagReport.getCompanyId(), flagReport.getGroupId(), flagReport.getUserId(),
                FlagReport.class.getName(), flagReport.getFlagReportId(), false,
                addGroupPermissions, addGuestPermissions);
    }

    public void addFlagReportResources(FlagReport flagReport, String[] groupPermissions,
                                         String[] guestPermissions) throws PortalException, SystemException {
        try {
            resourceLocalService.addModelResources(
                    flagReport.getCompanyId(), flagReport.getGroupId(), flagReport.getUserId(),
                    FlagReport.class.getName(), flagReport.getFlagReportId(),
                    groupPermissions, guestPermissions);
        }
        catch (PortalException e) {
            e.printStackTrace();
            throw e;
        }
        catch (SystemException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateAsset(long userId, FlagReport flagReport, long[] assetCategoryIds,
                            String[] assetTagNames) throws PortalException, SystemException {

        assetEntryLocalService.updateEntry(
                userId, flagReport.getGroupId(), FlagReport.class.getName(), flagReport.getFlagReportId(),
                null, 0, assetCategoryIds, assetTagNames, true, null, null, null, null, ContentTypes.TEXT_HTML,
                null, null, null, null, null, 0, 0, null, false);
    }

    public void deleteReports(long groupId) throws PortalException, SystemException {

        for (FlagReport flagReport : flagReportPersistence.findByGroupId(groupId)){
            deleteReport(flagReport);
        }
    }

    public void deleteReport(FlagReport flagReport) throws PortalException, SystemException {
        // Resources
        resourceLocalService.deleteResource(
                flagReport.getCompanyId(), FlagReport.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, flagReport.getFlagReportId());

        // Asset
        assetEntryLocalService.deleteEntry(FlagReport.class.getName(), flagReport.getFlagReportId());

        // Workflow
        workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
                flagReport.getCompanyId(),flagReport.getGroupId(),
                FlagReport.class.getName(), flagReport.getFlagReportId());

        // Report
        flagReportPersistence.remove(flagReport);
    }

    public void deleteReport(long flagReportId) throws PortalException, SystemException {

        FlagReport flagReport = flagReportPersistence.findByPrimaryKey(flagReportId);
        deleteReport(flagReport);
    }

    public FlagReport updateReport(FlagReport flagReport) throws SystemException {
        throw new IllegalArgumentException("Must pass userId, Report columns and serviceContext when updating a report.");
    }

    public  FlagReport updateFlagReport(long userId, long flagReportId, long classNameId, long classPK,
                            String title, String content, String flagReason, String flagComment,
                            String moderateAction, String moderatorComment, Integer Status,
                            long statusByUserId, String statusByUserName, Date StatusDate,
                            ServiceContext serviceContext) throws PortalException, SystemException {

        // Report
        User user = userPersistence.findByPrimaryKey(userId);
        FlagReport flagReport = flagReportPersistence.findByPrimaryKey(flagReportId);

        flagReport.setFlagReportId(flagReportId);
        flagReport.setClassNameId(classNameId);
        flagReport.setClassPK(classPK);
        flagReport.setTitle(title);
        flagReport.setContent(content);
        flagReport.setFlagReason(flagReason);
        flagReport.setFlagComment(flagComment);
        flagReport.setModerateAction(moderateAction);
        flagReport.setModeratorComment(moderatorComment);

        flagReportPersistence.update(flagReport, false);

        // Resources
        if ((serviceContext.getGroupPermissions() != null) ||
                (serviceContext.getGuestPermissions() != null)) {
            updateFlagReportResources(flagReport, serviceContext.getGroupPermissions(),
                    serviceContext.getGuestPermissions());
        }

        // Asset
         updateAsset(user.getUserId(), flagReport, serviceContext.getAssetCategoryIds(),
                 serviceContext.getAssetTagNames());

        // Workflow
        WorkflowHandlerRegistryUtil.startWorkflowInstance(
                user.getCompanyId(),flagReport.getGroupId(), userId,
                FlagReport.class.getName(),flagReport.getPrimaryKey(), flagReport,
                serviceContext);

        return flagReport;
    }

    // moderate method using custom built control portlet
    public  FlagReport moderateFlagReport(long userId, long flagReportId, Integer moderateStatus,
                                          String moderateAction, String moderatorComment,
                            ServiceContext serviceContext) throws PortalException, SystemException {

        // Report
        User user = userPersistence.findByPrimaryKey(userId);
        FlagReport flagReport = flagReportPersistence.findByPrimaryKey(flagReportId);

        flagReport.setFlagReportId(flagReportId);
        flagReport.setModerateAction(moderateAction);
        flagReport.setModeratorComment(moderatorComment);

        flagReportPersistence.update(flagReport, false);

        // Resources
        if ((serviceContext.getGroupPermissions() != null) ||
                (serviceContext.getGuestPermissions() != null)) {
            updateFlagReportResources(flagReport, serviceContext.getGroupPermissions(),
                    serviceContext.getGuestPermissions());
        }

        // Asset
         updateAsset(user.getUserId(), flagReport, serviceContext.getAssetCategoryIds(),
                 serviceContext.getAssetTagNames());

        // Workflow
        updateStatus(user.getUserId(), flagReport.getPrimaryKey(), moderateStatus, serviceContext);

        return flagReport;
    }

    public void updateFlagReportResources(FlagReport flagReport, String[] groupPermissions,
                                            String[] guestPermissions) throws PortalException, SystemException {
        resourceLocalService.updateResources(
                flagReport.getCompanyId(), flagReport.getGroupId(),
                FlagReport.class.getName(), flagReport.getFlagReportId(),
                groupPermissions, guestPermissions);
    }

    public void updateFlagReportStats(long classNameId, long classPK) throws PortalException, SystemException {
        FlagReportStats flagReportStats = flagReportStatsPersistence.fetchByClassNameIdWithClassPK(classNameId, classPK);
        if (flagReportStats == null) {
            FlagReportStats newFlagReportStats = new FlagReportStatsImpl();
            newFlagReportStats.setClassNameId(classNameId);
            newFlagReportStats.setClassPK(classPK);
            newFlagReportStats.setTotalEntries(1);
            newFlagReportStats.setTotalModerated(0);
            newFlagReportStats.setTotalApproved(0);
            FlagReportStatsLocalServiceUtil.addFlagReportStats(newFlagReportStats);
        } else  {
            FlagReportStatsLocalServiceUtil.incrementTotalEntries(flagReportStats.getClassNameId(),flagReportStats.getClassPK());
        }
    }

    public FlagReport updateStatus(
            long userId, long resourcePrimKey, int status, ServiceContext serviceContext)
        throws PortalException, SystemException {

        User user = userPersistence.findByPrimaryKey(userId);
        Date now = new Date();
        FlagReport flagReport = flagReportPersistence.findByPrimaryKey(resourcePrimKey);
        //Liferay 6.1 added in a classPK and Type. They are both zero by default when configured.
        //Liferay 6.1 you must configure the workflow in order for the groupId to be set properly.
        WorkflowDefinitionLink workflowDefinitionLink = WorkflowDefinitionLinkUtil.fetchByG_C_C_C_T(flagReport.getGroupId(),
                flagReport.getCompanyId(), ClassNameLocalServiceUtil.getClassNameId(FlagReport.class),
                0, 0);

        if (flagReport.getModerateAction().equals("") && workflowDefinitionLink == null) {
            //Comment indicates workflow NOT configured and auto processing occurred.
            flagReport.setModerateAction(FlagReportConstants.MODERATE_ACTION_NONE);
            flagReport.setModeratorComment("Kaleo workflow OFF");
        } else if (flagReport.getModerateAction().equals("") && workflowDefinitionLink != null) {
            //Workflow processed with default Kaleo workflow portlet.
            if (status == WorkflowConstants.STATUS_APPROVED) {
                flagReport.setModerateAction(FlagReportConstants.MODERATE_ACTION_REMOVE);
                flagReport.setModeratorComment("Processed with My Workflow Tasks portlet");
            } else if (status == WorkflowConstants.STATUS_DENIED) {
                flagReport.setModerateAction(FlagReportConstants.MODERATE_ACTION_IGNORE);
                flagReport.setModeratorComment("Processed with My Workflow Tasks portlet");
            }
        }

        flagReport.setStatus(status);
        flagReport.setStatusByUserId(user.getUserId());
        flagReport.setStatusByUserName(user.getFullName());
        flagReport.setStatusDate(serviceContext.getModifiedDate(now));

        flagReportPersistence.update(flagReport, false);

        //Verify workflow definition is applied/turned.
        //If not applied, status will default to approved - causing review to be marked for removal/hidden.
        //Therefore added extra "if (workflowDefinitionLink != null)" statement so that review and stats are not processed incorrectly.
        //If applied, the moderator will determine the workflow status.

        if (workflowDefinitionLink == null) {
            //Workflow not turned on, automatically approved.  Increment totals in stats entity
            // This method will be accessed twice as a flag report is inserted, only update 2nd entry.
            if (status == WorkflowConstants.STATUS_APPROVED)  {
                FlagReportStatsLocalServiceUtil.incrementTotalModerated(flagReport.getClassNameId(), flagReport.getClassPK());
                FlagReportStatsLocalServiceUtil.incrementTotalApproved(flagReport.getClassNameId(), flagReport.getClassPK());
            }
        } else if (status == WorkflowConstants.STATUS_APPROVED) {

            if (flagReport.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId(CourseReview.class)) {
                // mark local class review for removal
                CourseReviewLocalServiceUtil.setRemoved(flagReport.getClassPK(), true);
                //increment statistics
                FlagReportStatsLocalServiceUtil.incrementTotalModerated(flagReport.getClassNameId(), flagReport.getClassPK());
                FlagReportStatsLocalServiceUtil.incrementTotalApproved(flagReport.getClassNameId(), flagReport.getClassPK());
            } else if (flagReport.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId(GlobalCourseReview.class)) {
                // hide global class review
                GlobalCourseReviewLocalServiceUtil.setReviewIsHidden(flagReport.getClassPK(), true);
                //increment statistics
                FlagReportStatsLocalServiceUtil.incrementTotalModerated(flagReport.getClassNameId(), flagReport.getClassPK());
                FlagReportStatsLocalServiceUtil.incrementTotalApproved(flagReport.getClassNameId(), flagReport.getClassPK());
            }

        } else if (status == WorkflowConstants.STATUS_DENIED) {
            FlagReportStatsLocalServiceUtil.incrementTotalModerated(flagReport.getClassNameId(), flagReport.getClassPK());
        }

        return flagReport;
    }

    //method used when customized moderation is used instead of kaleo default "My Workflow Tasks"
    //Removes all the workflow instance links where groupId = ? and companyId = ? and className = ? and classPK = ? from the database.
    public void removeWorkflowInstance(long groupId, long companyId, String className, long classPK) throws PortalException, SystemException {

        workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(companyId, groupId, className, classPK);
    }


    public FlagReport findByFlagReportId(long flagReportId) throws NoSuchFlagReportException, SystemException {
        return flagReportPersistence.findByflagReportId(flagReportId);
    }

    public FlagReport fetchByFlagReportId(long flagReportId) throws SystemException {
        return flagReportPersistence.fetchByflagReportId(flagReportId);
    }

    public List<FlagReport> findByGroupId(long groupId) throws SystemException {
        return flagReportPersistence.findByGroupId(groupId);
    }

    public List<FlagReport> findByCompanyId(long companyId) throws SystemException {
        return flagReportPersistence.findByCompanyId(companyId);
    }

    public List<FlagReport> findByClassNameIdClassPK(long classNameId, long classPK) throws SystemException {
        return flagReportPersistence.findByClassNameIdWithClassPK(classNameId, classPK);
    }
    public List<Object[]> findByClassNameIdAndFilter(long classNameId,String filterType, int start, int end)throws SystemException {
        return FlagReportFinderUtil.findByClassNameIdAndFilter(classNameId, filterType, start, end);
    }

    private static Log _log = LogFactoryUtil.getLog(
		FlagReportLocalServiceImpl.class);
}