package org.nterlearning.datamodel.catalog.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.nterlearning.datamodel.catalog.service.ClpSerializer;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.ComponentRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.ComponentRecordServiceUtil;
import org.nterlearning.datamodel.catalog.service.ContributorLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseImageLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRecordServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRelatedLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseRequirementLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.CourseReviewServiceUtil;
import org.nterlearning.datamodel.catalog.service.Courses_ComponentsLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.ExternalLinkLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FlagReportLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FlagReportStatsLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.GlobalCourseReviewServiceUtil;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            ComponentLocalServiceUtil.clearService();

            ComponentRecordLocalServiceUtil.clearService();

            ComponentRecordServiceUtil.clearService();
            ContributorLocalServiceUtil.clearService();

            CourseLocalServiceUtil.clearService();

            CourseImageLocalServiceUtil.clearService();

            CourseRecordLocalServiceUtil.clearService();

            CourseRecordServiceUtil.clearService();
            CourseRelatedLocalServiceUtil.clearService();

            CourseRequirementLocalServiceUtil.clearService();

            CourseReviewLocalServiceUtil.clearService();

            CourseReviewServiceUtil.clearService();
            Courses_ComponentsLocalServiceUtil.clearService();

            ExternalLinkLocalServiceUtil.clearService();

            FeedReferenceLocalServiceUtil.clearService();

            FeedSyncHistoryLocalServiceUtil.clearService();

            FlagReportLocalServiceUtil.clearService();

            FlagReportStatsLocalServiceUtil.clearService();

            GlobalCourseReviewLocalServiceUtil.clearService();

            GlobalCourseReviewServiceUtil.clearService();
        }
    }
}
