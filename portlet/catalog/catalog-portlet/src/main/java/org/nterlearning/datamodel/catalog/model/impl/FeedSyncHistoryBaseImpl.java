package org.nterlearning.datamodel.catalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.nterlearning.datamodel.catalog.model.FeedSyncHistory;
import org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalServiceUtil;

/**
 * The extended model base implementation for the FeedSyncHistory service. Represents a row in the &quot;CATALOG_FeedSyncHistory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FeedSyncHistoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedSyncHistoryImpl
 * @see org.nterlearning.datamodel.catalog.model.FeedSyncHistory
 * @generated
 */
public abstract class FeedSyncHistoryBaseImpl extends FeedSyncHistoryModelImpl
    implements FeedSyncHistory {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a feed sync history model instance should use the {@link FeedSyncHistory} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            FeedSyncHistoryLocalServiceUtil.addFeedSyncHistory(this);
        } else {
            FeedSyncHistoryLocalServiceUtil.updateFeedSyncHistory(this);
        }
    }
}
