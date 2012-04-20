package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing GlobalCourseReview in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see GlobalCourseReview
 * @generated
 */
public class GlobalCourseReviewCacheModel implements CacheModel<GlobalCourseReview>,
    Serializable {
    public long globalCourseReviewId;
    public long companyId;
    public long groupId;
    public String courseReviewIri;
    public long updatedDate;
    public String courseIri;
    public String href;
    public String nterInstance;
    public long courseId;
    public String userDisplayName;
    public String singleSignOnValue;
    public String summary;
    public String content;
    public long createDate;
    public long modifiedDate;
    public double starScore;
    public boolean fromTrustedReviewer;
    public boolean removed;
    public long removedDate;
    public boolean isHidden;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(41);

        sb.append("{globalCourseReviewId=");
        sb.append(globalCourseReviewId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", courseReviewIri=");
        sb.append(courseReviewIri);
        sb.append(", updatedDate=");
        sb.append(updatedDate);
        sb.append(", courseIri=");
        sb.append(courseIri);
        sb.append(", href=");
        sb.append(href);
        sb.append(", nterInstance=");
        sb.append(nterInstance);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", userDisplayName=");
        sb.append(userDisplayName);
        sb.append(", singleSignOnValue=");
        sb.append(singleSignOnValue);
        sb.append(", summary=");
        sb.append(summary);
        sb.append(", content=");
        sb.append(content);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", starScore=");
        sb.append(starScore);
        sb.append(", fromTrustedReviewer=");
        sb.append(fromTrustedReviewer);
        sb.append(", removed=");
        sb.append(removed);
        sb.append(", removedDate=");
        sb.append(removedDate);
        sb.append(", isHidden=");
        sb.append(isHidden);
        sb.append("}");

        return sb.toString();
    }

    public GlobalCourseReview toEntityModel() {
        GlobalCourseReviewImpl globalCourseReviewImpl = new GlobalCourseReviewImpl();

        globalCourseReviewImpl.setGlobalCourseReviewId(globalCourseReviewId);
        globalCourseReviewImpl.setCompanyId(companyId);
        globalCourseReviewImpl.setGroupId(groupId);

        if (courseReviewIri == null) {
            globalCourseReviewImpl.setCourseReviewIri(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setCourseReviewIri(courseReviewIri);
        }

        if (updatedDate == Long.MIN_VALUE) {
            globalCourseReviewImpl.setUpdatedDate(null);
        } else {
            globalCourseReviewImpl.setUpdatedDate(new Date(updatedDate));
        }

        if (courseIri == null) {
            globalCourseReviewImpl.setCourseIri(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setCourseIri(courseIri);
        }

        if (href == null) {
            globalCourseReviewImpl.setHref(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setHref(href);
        }

        if (nterInstance == null) {
            globalCourseReviewImpl.setNterInstance(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setNterInstance(nterInstance);
        }

        globalCourseReviewImpl.setCourseId(courseId);

        if (userDisplayName == null) {
            globalCourseReviewImpl.setUserDisplayName(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setUserDisplayName(userDisplayName);
        }

        if (singleSignOnValue == null) {
            globalCourseReviewImpl.setSingleSignOnValue(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setSingleSignOnValue(singleSignOnValue);
        }

        if (summary == null) {
            globalCourseReviewImpl.setSummary(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setSummary(summary);
        }

        if (content == null) {
            globalCourseReviewImpl.setContent(StringPool.BLANK);
        } else {
            globalCourseReviewImpl.setContent(content);
        }

        if (createDate == Long.MIN_VALUE) {
            globalCourseReviewImpl.setCreateDate(null);
        } else {
            globalCourseReviewImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            globalCourseReviewImpl.setModifiedDate(null);
        } else {
            globalCourseReviewImpl.setModifiedDate(new Date(modifiedDate));
        }

        globalCourseReviewImpl.setStarScore(starScore);
        globalCourseReviewImpl.setFromTrustedReviewer(fromTrustedReviewer);
        globalCourseReviewImpl.setRemoved(removed);

        if (removedDate == Long.MIN_VALUE) {
            globalCourseReviewImpl.setRemovedDate(null);
        } else {
            globalCourseReviewImpl.setRemovedDate(new Date(removedDate));
        }

        globalCourseReviewImpl.setIsHidden(isHidden);

        globalCourseReviewImpl.resetOriginalValues();

        return globalCourseReviewImpl;
    }
}
