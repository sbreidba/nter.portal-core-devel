package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.nterlearning.datamodel.catalog.model.FeedSyncHistory;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing FeedSyncHistory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistory
 * @generated
 */
public class FeedSyncHistoryCacheModel implements CacheModel<FeedSyncHistory>,
    Serializable {
    public long syncId;
    public long feedReferenceId;
    public long syncDate;
    public Boolean success;
    public String syncMessage;
    public Integer numberOfEntries;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{syncId=");
        sb.append(syncId);
        sb.append(", feedReferenceId=");
        sb.append(feedReferenceId);
        sb.append(", syncDate=");
        sb.append(syncDate);
        sb.append(", success=");
        sb.append(success);
        sb.append(", syncMessage=");
        sb.append(syncMessage);
        sb.append(", numberOfEntries=");
        sb.append(numberOfEntries);
        sb.append("}");

        return sb.toString();
    }

    public FeedSyncHistory toEntityModel() {
        FeedSyncHistoryImpl feedSyncHistoryImpl = new FeedSyncHistoryImpl();

        feedSyncHistoryImpl.setSyncId(syncId);
        feedSyncHistoryImpl.setFeedReferenceId(feedReferenceId);

        if (syncDate == Long.MIN_VALUE) {
            feedSyncHistoryImpl.setSyncDate(null);
        } else {
            feedSyncHistoryImpl.setSyncDate(new Date(syncDate));
        }

        feedSyncHistoryImpl.setSuccess(success);

        if (syncMessage == null) {
            feedSyncHistoryImpl.setSyncMessage(StringPool.BLANK);
        } else {
            feedSyncHistoryImpl.setSyncMessage(syncMessage);
        }

        feedSyncHistoryImpl.setNumberOfEntries(numberOfEntries);

        feedSyncHistoryImpl.resetOriginalValues();

        return feedSyncHistoryImpl;
    }
}
