package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.FlagReport;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing FlagReport in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FlagReport
 * @generated
 */
public class FlagReportCacheModel implements CacheModel<FlagReport>,
    Serializable {
    public String uuid;
    public long flagReportId;
    public long groupId;
    public long companyId;
    public long userId;
    public long classNameId;
    public long classPK;
    public long createDate;
    public String title;
    public String content;
    public String flagReason;
    public String flagComment;
    public String moderateAction;
    public String moderatorComment;
    public int status;
    public long statusByUserId;
    public String statusByUserName;
    public long statusDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", flagReportId=");
        sb.append(flagReportId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", classNameId=");
        sb.append(classNameId);
        sb.append(", classPK=");
        sb.append(classPK);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", title=");
        sb.append(title);
        sb.append(", content=");
        sb.append(content);
        sb.append(", flagReason=");
        sb.append(flagReason);
        sb.append(", flagComment=");
        sb.append(flagComment);
        sb.append(", moderateAction=");
        sb.append(moderateAction);
        sb.append(", moderatorComment=");
        sb.append(moderatorComment);
        sb.append(", status=");
        sb.append(status);
        sb.append(", statusByUserId=");
        sb.append(statusByUserId);
        sb.append(", statusByUserName=");
        sb.append(statusByUserName);
        sb.append(", statusDate=");
        sb.append(statusDate);
        sb.append("}");

        return sb.toString();
    }

    public FlagReport toEntityModel() {
        FlagReportImpl flagReportImpl = new FlagReportImpl();

        if (uuid == null) {
            flagReportImpl.setUuid(StringPool.BLANK);
        } else {
            flagReportImpl.setUuid(uuid);
        }

        flagReportImpl.setFlagReportId(flagReportId);
        flagReportImpl.setGroupId(groupId);
        flagReportImpl.setCompanyId(companyId);
        flagReportImpl.setUserId(userId);
        flagReportImpl.setClassNameId(classNameId);
        flagReportImpl.setClassPK(classPK);

        if (createDate == Long.MIN_VALUE) {
            flagReportImpl.setCreateDate(null);
        } else {
            flagReportImpl.setCreateDate(new Date(createDate));
        }

        if (title == null) {
            flagReportImpl.setTitle(StringPool.BLANK);
        } else {
            flagReportImpl.setTitle(title);
        }

        if (content == null) {
            flagReportImpl.setContent(StringPool.BLANK);
        } else {
            flagReportImpl.setContent(content);
        }

        if (flagReason == null) {
            flagReportImpl.setFlagReason(StringPool.BLANK);
        } else {
            flagReportImpl.setFlagReason(flagReason);
        }

        if (flagComment == null) {
            flagReportImpl.setFlagComment(StringPool.BLANK);
        } else {
            flagReportImpl.setFlagComment(flagComment);
        }

        if (moderateAction == null) {
            flagReportImpl.setModerateAction(StringPool.BLANK);
        } else {
            flagReportImpl.setModerateAction(moderateAction);
        }

        if (moderatorComment == null) {
            flagReportImpl.setModeratorComment(StringPool.BLANK);
        } else {
            flagReportImpl.setModeratorComment(moderatorComment);
        }

        flagReportImpl.setStatus(status);
        flagReportImpl.setStatusByUserId(statusByUserId);

        if (statusByUserName == null) {
            flagReportImpl.setStatusByUserName(StringPool.BLANK);
        } else {
            flagReportImpl.setStatusByUserName(statusByUserName);
        }

        if (statusDate == Long.MIN_VALUE) {
            flagReportImpl.setStatusDate(null);
        } else {
            flagReportImpl.setStatusDate(new Date(statusDate));
        }

        flagReportImpl.resetOriginalValues();

        return flagReportImpl;
    }
}
