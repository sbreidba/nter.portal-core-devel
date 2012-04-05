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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetCategoryUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.expando.service.persistence.ExpandoValueUtil;

import org.nterlearning.atom.parser.push.PubSubHubbubSubscriber;
import org.nterlearning.course.util.FeedReferenceUtil;
import org.nterlearning.datamodel.catalog.NoSuchFeedReferenceException;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.service.*;
import org.nterlearning.datamodel.catalog.service.base.FeedReferenceLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the feed reference local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.nterlearning.datamodel.catalog.service.FeedReferenceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author SRI International
 * @see org.nterlearning.datamodel.catalog.service.base.FeedReferenceLocalServiceBaseImpl
 * @see org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil
 */
public class FeedReferenceLocalServiceImpl
    extends FeedReferenceLocalServiceBaseImpl {

    private static final String EXPANDO_TABLENAME = "AssetVocabulary";
    private static final String EXPANDO_FEED_REFERENCE_ID_COLUMN = "feedReferenceId";


    @Override
    public FeedReference addFeedReference(FeedReference feedReference)
            throws SystemException {
        long id = counterLocalService.increment(FeedReference.class.getName());
        feedReference.setPrimaryKey(id);

        if ((feedReference.getHrefHash() == null) ||
                feedReference.getHrefHash().equals("")) {
            feedReference.setHrefHash(
                    FeedReferenceUtil.generateHash(feedReference.getHref()));
        }

        return super.addFeedReference(feedReference);
    }


    public void deleteFeedReference(long feedReferenceId)
            throws PortalException, SystemException {

        removeAssociatedEntries(feedReferenceId);

        super.deleteFeedReference(feedReferenceId);
    }


    public void deleteFeedReference(FeedReference feedReference)
            throws SystemException {

        removeAssociatedEntries(feedReference.getFeedReferenceId());

        super.deleteFeedReference(feedReference);
    }


    /**
     * Updates the feed reference in the database. Also notifies the appropriate
     * model listeners.
     *
     * @param feedReference the feed reference to update
     * @return the feed reference that was updated
     * @throws SystemException if a system exception occurred
     */
    public FeedReference updateFeedReference(FeedReference feedReference)
            throws SystemException {

        feedReference.setNew(false);
        return updateFeedReference(feedReference, true);
    }


    /**
     * Updates the feed reference in the database. Also notifies the appropriate
     * model listeners.
     *
     * @param feedReference the feed reference to update
     * @param merge whether to merge the feed reference with the current session.
     * See {@link com.liferay.portal.service.persistence.BatchSession#update
     * (com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)}
     *  for an explanation.
     * @return the feed reference that was updated
     * @throws SystemException if a system exception occurred
     */
    public FeedReference updateFeedReference(FeedReference feedReference,
            boolean merge) throws SystemException {

        feedReference.setNew(false);

        // determine what changed to update accordingly
        boolean updateRemovalState = false;
        boolean updatedGroupId = false;
        try {
            FeedReference originalFeed = getFeedReference(feedReference.getFeedReferenceId());
            updateRemovalState = originalFeed.getRemoved() != feedReference.getRemoved();
            updatedGroupId = originalFeed.getGroupId() != feedReference.getGroupId();
        }
        catch (PortalException pe) {
            _log.error("Could not find FeedReference with id: " +
                    feedReference.getFeedReferenceId());
        }

        // handle any push hubs
        if (updateRemovalState) {
            subscribeToHubs(feedReference, !feedReference.isRemoved());
        }

        // update all associated courses
        List<Course> courses =
                coursePersistence.findByFeedReferenceId(feedReference.getFeedReferenceId());
        long updatedGroup = feedReference.getGroupId();

        for (Course course : courses) {

            if (updateRemovalState) {
                course.setRemoved(feedReference.getRemoved());
                course.setRemovedDate(feedReference.getRemovedDate());
            }

            if (updatedGroupId) {
                course.setGroupId(updatedGroup);
            }

            if (updateRemovalState || updatedGroupId) {
                CourseLocalServiceUtil.updateCourse(course, true);
            }
        }

        // update all associated course-components
        List<Component> components =
                componentPersistence.findByFeedReferenceId(feedReference.getFeedReferenceId());
        for (Component component : components) {

            if (updateRemovalState) {
                component.setRemoved(feedReference.getRemoved());
                component.setRemovedDate(feedReference.getRemovedDate());
            }

            if (updatedGroupId) {
                component.setGroupId(updatedGroup);
            }

            if (updateRemovalState || updatedGroupId) {
                ComponentLocalServiceUtil.updateComponent(component, true);
            }
        }

        // update all associated vocabularies
        long companyId = feedReference.getCompanyId();
        String className = AssetVocabulary.class.getName();
        try {

            // workaround to force the permission checker to initialize
            // when running as a scheduled task, it may not always be init'd
            User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);
            PermissionThreadLocal.setPermissionChecker(
                    PermissionCheckerFactoryUtil.create(defaultUser, true));

            ExpandoTable expandoTable =
                    ExpandoTableLocalServiceUtil.getTable(companyId, className,
                                                          EXPANDO_TABLENAME);
            ExpandoColumn expandoColumn =
                    ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(),
                                                            EXPANDO_FEED_REFERENCE_ID_COLUMN);

            List<ExpandoValue> expandoValues =
                    ExpandoValueUtil.findByT_C(expandoTable.getTableId(), expandoColumn.getColumnId());
            for (ExpandoValue expandoValue : expandoValues) {
                if (expandoValue.getData().equals(Long.toString(feedReference.getFeedReferenceId()))) {

                    _log.debug("update vocabulary with new groupId here!");
                    long vocabularyId = expandoValue.getClassPK();

                    List<AssetCategory> assetCategories =
                            AssetCategoryLocalServiceUtil.getVocabularyCategories(
                                    vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
                    for (AssetCategory assetCategory : assetCategories) {
                        assetCategory.setGroupId(updatedGroup);
                        AssetCategoryLocalServiceUtil.updateAssetCategory(assetCategory);
                    }

                    AssetVocabulary assetVocabulary =
                            AssetVocabularyLocalServiceUtil.getVocabulary(vocabularyId);
                    assetVocabulary.setGroupId(updatedGroup);
                    AssetVocabularyLocalServiceUtil.updateAssetVocabulary(assetVocabulary);
                }
            }
        }
        catch (PortalException e) {
            _log.error("Could not find Expando table entries for AssetVocabulary: " + e.getMessage());
            throw new SystemException(e);
        }
        catch (Exception ge) {
            _log.error("Could not create Expando table entries for AssetVocabulary: " + ge.getMessage());
        }
		return super.feedReferencePersistence.update(feedReference, merge);
	}


    public FeedReference findByFeedIri(String feedIri)
            throws NoSuchFeedReferenceException, SystemException {
        return feedReferencePersistence.findByFeedIri(feedIri);
    }


    public FeedReference fetchByFeedIri(String feedIri) throws SystemException {
        return feedReferencePersistence.fetchByFeedIri(feedIri);
    }


    public FeedReference findByFeedHref(String href)
            throws NoSuchFeedReferenceException, SystemException {

        List<FeedReference> feedRefs =
                feedReferencePersistence.findByHrefHash(
                        FeedReferenceUtil.generateHash(href));

        // this may return a collection of feedReference objects that have
        // the same hash, but are not the actual feedReference we're looking
        // for.  need to verify each hashed feedreference against original href
        for (FeedReference feedRef : feedRefs) {
            if (feedRef.getHref().equals(href)) {
                return feedRef;
            }
        }

        throw new NoSuchFeedReferenceException();
    }


    public DynamicQuery generateDynamicQuery(long groupId, boolean removed) {
        return DynamicQueryFactoryUtil.forClass(FeedReference.class)
                .add(PropertyFactoryUtil.forName("groupId").eq(groupId))
                .add(PropertyFactoryUtil.forName("removed").eq(removed));
    }


    public DynamicQuery generateDynamicQuery(boolean removed) {
        return DynamicQueryFactoryUtil.forClass(FeedReference.class)
                .add(PropertyFactoryUtil.forName("removed").eq(removed));
    }


    public DynamicQuery generateDynamicQuery(boolean removed, String reasonCode) {

        // If searching for a removed feed, then we care about the reason too,
        // otherwise, we don't
        if (removed) {
            return DynamicQueryFactoryUtil.forClass(FeedReference.class)
                    .add(PropertyFactoryUtil.forName("removed").eq(removed))
                    .add(PropertyFactoryUtil.forName("removedReason").eq(reasonCode));
        }
        else {
            return generateDynamicQuery(removed);
        }
    }


    public DynamicQuery generateDynamicQuery(String feedType, long groupId, boolean removed) {
        return DynamicQueryFactoryUtil.forClass(FeedReference.class)
                .add(PropertyFactoryUtil.forName("groupId").eq(groupId))
                .add(PropertyFactoryUtil.forName("feedType").eq(feedType))
                .add(PropertyFactoryUtil.forName("removed").eq(removed));
    }


    /**
     * Removes all component entries associated with a given FeedReference.
     * These entries include courses, course components, student records, and
     * feed sync histories.
     *
     * @param feedRefId The PrimaryKey of the feedReference object.
     */
    private void removeAssociatedEntries(long feedRefId) {
        removeAssociatedVocabularies(feedRefId);
        removeAssociatedCourses(feedRefId);
        removeAssociatedCourseComponents(feedRefId);
        removeAssociatedRecords(feedRefId);
        removeAssociatedSyncHistory(feedRefId);
    }


    public void removeAssociatedVocabularies(long feedReferenceId) {

        try {
            List<ExpandoColumn> expandoColumns =
                    ExpandoColumnLocalServiceUtil.getExpandoColumns(
                            QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            long columnId = 0;
            for (ExpandoColumn expandoColumn : expandoColumns) {
                if (expandoColumn.getName().equals(EXPANDO_FEED_REFERENCE_ID_COLUMN)) {
                    columnId = expandoColumn.getColumnId();
                }
            }

            if (columnId != 0) {
                List<ExpandoValue> expandoValues =
                        ExpandoValueLocalServiceUtil.getColumnValues(columnId,
                            QueryUtil.ALL_POS, QueryUtil.ALL_POS);
                for (ExpandoValue expandoValue : expandoValues) {
                    if (expandoValue.getData().equals(Long.toString(feedReferenceId))) {
                        long removeTableId = expandoValue.getTableId();
                        long removeVocabularyId = expandoValue.getClassPK();
                        long removeClassNameId = expandoValue.getClassNameId();

                        // Delete Row:
                        ExpandoRowLocalServiceUtil.deleteRow(removeTableId, removeVocabularyId);
                        // Delete Values:
                        ExpandoValueLocalServiceUtil.deleteValues(removeClassNameId, removeVocabularyId);

                        // Remove assetEntries_AssetCategories records.
                        List<AssetEntry> assetEntries =
                                CourseLocalServiceUtil.findAllAssetEntries();
                        List<AssetCategory> assetCategories =
                                AssetCategoryLocalServiceUtil.getVocabularyCategories(
                                        removeVocabularyId, QueryUtil.ALL_POS,
                                        QueryUtil.ALL_POS, null);
                        for (AssetCategory assetCategory : assetCategories) {
                            AssetCategoryUtil.removeAssetEntries(
                                    assetCategory.getPrimaryKey(), assetEntries);
                        }

                        AssetCategoryLocalServiceUtil.deleteVocabularyCategories(removeVocabularyId);
                        AssetVocabularyLocalServiceUtil.deleteAssetVocabulary(removeVocabularyId);
                    }
                }
            }
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }


    /**
     * Removes all courses associated with the given FeedReferenceId
     *
     * @param feedReferenceId PrimaryKey of the feedReference
     */
    private void removeAssociatedCourses(long feedReferenceId) {
        try {
            List<Course> courses =
                    coursePersistence.findByFeedReferenceId(feedReferenceId);
            for (Course course : courses) {
                for (CourseReview review : coursePersistence.getCourseReviews(course.getCourseId())) {
                    CourseReviewLocalServiceUtil.deleteCourseReview(review);
                }
                coursePersistence.remove(course);
            }
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }


    /**
     * Removes all course-components associated with the given feedReferenceId
     *
     * @param feedReferenceId PrimaryKey of the feedReference
     */
    private void removeAssociatedCourseComponents(long feedReferenceId) {
        try {
            List<Component> components =
                     componentPersistence.findByFeedReferenceId(feedReferenceId);
            for (Component component : components) {
                componentPersistence.remove(component);
            }
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }


    private void removeAssociatedRecords(long feedReferenceId) {
        try {
            List<CourseRecord> courseRecords =
                    courseRecordPersistence.findByFeedReferenceId(feedReferenceId);
            for (CourseRecord record : courseRecords) {
                CourseRecordLocalServiceUtil.deleteCourseRecord(record);
            }
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }


    /**
     * Removes all feedSync information associated with the given FeedReferenceId.
     *
     * @param feedReferenceId PrimaryKey of the feedReference being removed
     */
    private void removeAssociatedSyncHistory(long feedReferenceId) {
        try {
            List<FeedSyncHistory> syncHistories =
                    feedSyncHistoryPersistence.findByfeedReferenceId(feedReferenceId);
            for (FeedSyncHistory history : syncHistories) {
                FeedSyncHistoryLocalServiceUtil.deleteFeedSyncHistory(history);
            }
        }
        catch (Exception e) {
            _log.error(e.getMessage());
        }
    }


    /**
     * Handles the subscription / unsubscription of a Feed from its
     * associated hubs.
     *
     * @param feedRef The FeedReference to process
     * @param subscribe True to subscribe to the hubs, false to unsubscribe
     */
    private void subscribeToHubs(FeedReference feedRef, Boolean subscribe) {
        // if already subscribed to a hub, and supposed to unsubscribe
        // or if not subscribed, and supposed to subscribe
        // otherwise, do nothing
        String hubs[] = feedRef.getPshb().split(",");
        if (feedRef.getPshbSubscribed() && !subscribe) {
            for (String hub : hubs) {
                PubSubHubbubSubscriber.getInstance().unsubscribe(hub, feedRef.getHref());
            }
        }
        else if (!feedRef.getPshbSubscribed() && subscribe) {
            for (String hub : hubs) {
                PubSubHubbubSubscriber.getInstance().subscribe(hub, feedRef.getHref());
            }
        }
    }


    /**
     * Clears the cache for all feedReferences stored in this session.  This
     * should only be needed in a multi-threaded environment, where a thread is
     * not notified of persistence updates done in a different thread.
     */
    public void clearCache() {
        feedReferencePersistence.clearCache();
    }


    /**
     * Clears the cache for all feedReferences stored in this session.  This
     * should only be needed in a multi-threaded environment, where a thread is
     * not notified of persistence updates done in a different thread.
     *
     * @param feedRef the FeedReference object to remove from the cache
     */
    public void clearCache(FeedReference feedRef) {
        feedReferencePersistence.clearCache(feedRef);
    }


    /**
     * Clears the cache for all feedReferences stored in this session.  This
     * should only be needed in a multi-threaded environment, where a thread is
     * not notified of persistence updates done in a different thread.
     *
     * @param feedRefId The Id of the FeedReference to remove from the cache
     */
    public void clearCache(Long feedRefId) {
        try {
            FeedReference feedRef =
                    feedReferencePersistence.fetchByPrimaryKey(feedRefId);
            clearCache(feedRef);
        }
        catch (Exception e) {
            _log.error("Could not find FeedReference with id: " + feedRefId);
        }
    }


    private static Log _log =
            LogFactoryUtil.getLog(FeedReferenceLocalServiceImpl.class);
}