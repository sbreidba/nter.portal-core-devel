package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.CourseReview;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CourseReview in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CourseReview
 * @generated
 */
public class CourseReviewCacheModel implements CacheModel<CourseReview>,
    Serializable {
    public long courseReviewId;
    public long companyId;
    public long groupId;
    public long courseId;
    public long userId;
    public String summary;
    public String content;
    public long createDate;
    public long modifiedDate;
    public double weightedScore;
    public boolean removed;
    public long removedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{courseReviewId=");
        sb.append(courseReviewId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", courseId=");
        sb.append(courseId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", summary=");
        sb.append(summary);
        sb.append(", content=");
        sb.append(content);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", weightedScore=");
        sb.append(weightedScore);
        sb.append(", removed=");
        sb.append(removed);
        sb.append(", removedDate=");
        sb.append(removedDate);
        sb.append("}");

        return sb.toString();
    }

    public CourseReview toEntityModel() {
        CourseReviewImpl courseReviewImpl = new CourseReviewImpl();

        courseReviewImpl.setCourseReviewId(courseReviewId);
        courseReviewImpl.setCompanyId(companyId);
        courseReviewImpl.setGroupId(groupId);
        courseReviewImpl.setCourseId(courseId);
        courseReviewImpl.setUserId(userId);

        if (summary == null) {
            courseReviewImpl.setSummary(StringPool.BLANK);
        } else {
            courseReviewImpl.setSummary(summary);
        }

        if (content == null) {
            courseReviewImpl.setContent(StringPool.BLANK);
        } else {
            courseReviewImpl.setContent(content);
        }

        if (createDate == Long.MIN_VALUE) {
            courseReviewImpl.setCreateDate(null);
        } else {
            courseReviewImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            courseReviewImpl.setModifiedDate(null);
        } else {
            courseReviewImpl.setModifiedDate(new Date(modifiedDate));
        }

        courseReviewImpl.setWeightedScore(weightedScore);
        courseReviewImpl.setRemoved(removed);

        if (removedDate == Long.MIN_VALUE) {
            courseReviewImpl.setRemovedDate(null);
        } else {
            courseReviewImpl.setRemovedDate(new Date(removedDate));
        }

        courseReviewImpl.resetOriginalValues();

        return courseReviewImpl;
    }
}
