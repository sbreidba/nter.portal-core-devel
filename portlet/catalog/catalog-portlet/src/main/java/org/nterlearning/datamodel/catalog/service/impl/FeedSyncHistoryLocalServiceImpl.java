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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.PortletLocalServiceUtil;

import org.nterlearning.datamodel.catalog.NoSuchFeedSyncHistoryException;
import org.nterlearning.datamodel.catalog.model.FeedSyncHistory;
import org.nterlearning.datamodel.catalog.service.base.FeedSyncHistoryLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the feed sync history local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.FeedSyncHistoryLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.FeedSyncHistoryLocalServiceUtil
 */
public class FeedSyncHistoryLocalServiceImpl
    extends FeedSyncHistoryLocalServiceBaseImpl {

    @Override
    public FeedSyncHistory addFeedSyncHistory(FeedSyncHistory feedHistory)
            throws SystemException {

        long id = counterLocalService.increment(FeedSyncHistory.class.getName());
        feedHistory.setPrimaryKey(id);
        return super.addFeedSyncHistory(feedHistory);
    }

    public void deleteFeedSyncHistory(long feedHistoryId)
            throws PortalException, SystemException {
        FeedSyncHistory feedHistory = feedSyncHistoryPersistence.findByPrimaryKey(feedHistoryId);
        deleteFeedSyncHistory(feedHistory);
    }

    /**
     * Purges older feedSyncHistory log entries from the database.
     *
     * @param feedRefId The feedReferenceId to purge entries for.
     * @param retainCount The number of entries to retain
     *
     * @throws PortalException - Liferay's SystemException
     * @throws SystemException - Liferay's SystemException
     */
    public void purgeFeedSyncHistory(long feedRefId, long retainCount)
            throws PortalException, SystemException {

        List<FeedSyncHistory> historyList = new ArrayList<FeedSyncHistory>();

        if (retainCount == 0) {
            historyList = findByFeedReference(feedRefId);
        }
        else {
            int historyCount = findByFeedReference(feedRefId).size();

            if (historyCount > retainCount) {
                int limit = (int)(historyCount - retainCount);
                historyList = feedSyncHistoryPersistence.findByfeedReferenceId(feedRefId, 0, limit);
            }
        }

        for (FeedSyncHistory history : historyList) {
            feedSyncHistoryPersistence.remove(history);
        }
    }

    /**
     * Prunes the FeedSyncHistory table for a given FeedReference object.  It
     * retains the latest number of entries based on the configuration parameter
     * in the portlet.xml file.
     *
     * @param feedRefId The id of the FeedReference object to prune entries for.
     *
     * @throws PortalException - Standard Liferay exception
     * @throws SystemException - Standard Liferay exception
     */
    public void pruneFeedSyncHistory(long feedRefId)
            throws PortalException, SystemException {

        int defaultRetentionCount =
                Integer.valueOf(PortletLocalServiceUtil
                        .getPortletById("feeds_WAR_ntercatalogportlet")
                        .getInitParams().get("syncHistoryLimit"));

        purgeFeedSyncHistory(feedRefId, (long)defaultRetentionCount);
    }

    /**
     * Returns a list of FeedSyncHistory objects corresponding to a particular
     * feedReference object.
     *
     * @param feedRefId The feedReferenceId to search for.
     *
     * @return A Collection of FeedSyncHistory objects
     *
     * @throws NoSuchFeedSyncHistoryException - Returned if no objects are found
     * @throws SystemException - Liferay's SystemException
     */
    public List<FeedSyncHistory> findByFeedReference(long feedRefId)
            throws NoSuchFeedSyncHistoryException, SystemException {
        return feedSyncHistoryPersistence.findByfeedReferenceId(feedRefId);
    }

    /**
     * Generates a dynamicQuery object that searches for FeedSyncHistory objects
     * based on the associated feedReferenceIds.
     *
     * @param feedRefId The feedReferenceId to search for
     *
     * @return The associated dynamicQuery
     */
    public DynamicQuery generateDynamicQuery(long feedRefId) {
        return DynamicQueryFactoryUtil.forClass(FeedSyncHistory.class)
                                      .add(PropertyFactoryUtil.forName("feedReferenceId").eq(feedRefId));
    }
}