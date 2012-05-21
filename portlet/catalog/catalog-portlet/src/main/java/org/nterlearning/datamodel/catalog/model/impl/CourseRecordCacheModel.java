package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.CourseRecord;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CourseRecord in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CourseRecord
 * @generated
 */
public class CourseRecordCacheModel implements CacheModel<CourseRecord>,
    Serializable {
    public long courseRecordId;
    public long feedReferenceId;
    public String courseRecordIri;
    public long userId;
    public String singleSignOnValue;
    public String courseIri;
    public long updatedDate;
    public String completionStatus;
    public boolean removed;
    public long removedDate;
    public boolean userHidden;
    public boolean assigned;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{courseRecordId=");
        sb.append(courseRecordId);
        sb.append(", feedReferenceId=");
        sb.append(feedReferenceId);
        sb.append(", courseRecordIri=");
        sb.append(courseRecordIri);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", singleSignOnValue=");
        sb.append(singleSignOnValue);
        sb.append(", courseIri=");
        sb.append(courseIri);
        sb.append(", updatedDate=");
        sb.append(updatedDate);
        sb.append(", completionStatus=");
        sb.append(completionStatus);
        sb.append(", removed=");
        sb.append(removed);
        sb.append(", removedDate=");
        sb.append(removedDate);
        sb.append(", userHidden=");
        sb.append(userHidden);
        sb.append(", assigned=");
        sb.append(assigned);
        sb.append("}");

        return sb.toString();
    }

    public CourseRecord toEntityModel() {
        CourseRecordImpl courseRecordImpl = new CourseRecordImpl();

        courseRecordImpl.setCourseRecordId(courseRecordId);
        courseRecordImpl.setFeedReferenceId(feedReferenceId);

        if (courseRecordIri == null) {
            courseRecordImpl.setCourseRecordIri(StringPool.BLANK);
        } else {
            courseRecordImpl.setCourseRecordIri(courseRecordIri);
        }

        courseRecordImpl.setUserId(userId);

        if (singleSignOnValue == null) {
            courseRecordImpl.setSingleSignOnValue(StringPool.BLANK);
        } else {
            courseRecordImpl.setSingleSignOnValue(singleSignOnValue);
        }

        if (courseIri == null) {
            courseRecordImpl.setCourseIri(StringPool.BLANK);
        } else {
            courseRecordImpl.setCourseIri(courseIri);
        }

        if (updatedDate == Long.MIN_VALUE) {
            courseRecordImpl.setUpdatedDate(null);
        } else {
            courseRecordImpl.setUpdatedDate(new Date(updatedDate));
        }

        if (completionStatus == null) {
            courseRecordImpl.setCompletionStatus(StringPool.BLANK);
        } else {
            courseRecordImpl.setCompletionStatus(completionStatus);
        }

        courseRecordImpl.setRemoved(removed);

        if (removedDate == Long.MIN_VALUE) {
            courseRecordImpl.setRemovedDate(null);
        } else {
            courseRecordImpl.setRemovedDate(new Date(removedDate));
        }

        courseRecordImpl.setUserHidden(userHidden);
        courseRecordImpl.setAssigned(assigned);

        courseRecordImpl.resetOriginalValues();

        return courseRecordImpl;
    }
}
