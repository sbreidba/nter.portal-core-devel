package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.ComponentRecord;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ComponentRecord in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ComponentRecord
 * @generated
 */
public class ComponentRecordCacheModel implements CacheModel<ComponentRecord>,
    Serializable {
    public long componentRecordId;
    public long courseRecordId;
    public String componentIri;
    public long updatedDate;
    public String completionStatus;
    public Integer completionPercent;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{componentRecordId=");
        sb.append(componentRecordId);
        sb.append(", courseRecordId=");
        sb.append(courseRecordId);
        sb.append(", componentIri=");
        sb.append(componentIri);
        sb.append(", updatedDate=");
        sb.append(updatedDate);
        sb.append(", completionStatus=");
        sb.append(completionStatus);
        sb.append(", completionPercent=");
        sb.append(completionPercent);
        sb.append("}");

        return sb.toString();
    }

    public ComponentRecord toEntityModel() {
        ComponentRecordImpl componentRecordImpl = new ComponentRecordImpl();

        componentRecordImpl.setComponentRecordId(componentRecordId);
        componentRecordImpl.setCourseRecordId(courseRecordId);

        if (componentIri == null) {
            componentRecordImpl.setComponentIri(StringPool.BLANK);
        } else {
            componentRecordImpl.setComponentIri(componentIri);
        }

        if (updatedDate == Long.MIN_VALUE) {
            componentRecordImpl.setUpdatedDate(null);
        } else {
            componentRecordImpl.setUpdatedDate(new Date(updatedDate));
        }

        if (completionStatus == null) {
            componentRecordImpl.setCompletionStatus(StringPool.BLANK);
        } else {
            componentRecordImpl.setCompletionStatus(completionStatus);
        }

        componentRecordImpl.setCompletionPercent(completionPercent);

        componentRecordImpl.resetOriginalValues();

        return componentRecordImpl;
    }
}
