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

package org.nterlearning.course.testing.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrettyDateFormat;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.UserIdMapper;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserIdMapperLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetCategoryUtil;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import org.nterlearning.atom.parser.FeedParser;
import org.nterlearning.atom.parser.ServiceContextUtil;
import org.nterlearning.course.management.portlet.CourseManagementPortlet;
import org.nterlearning.course.search.ExternalOpenSearchImpl;
import org.nterlearning.crawl.nutch.CrawlTool;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.service.*;
import org.nterlearning.utils.PortalPropertiesUtil;
import org.nterlearning.utils.ReviewUtil;

import org.apache.commons.lang.Validate;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.*;

public class CourseTestingPortlet extends MVCPortlet {

	public static final String PARAM_URL_TO_PARSE = "course-testing-feeds-url-to-parse";
	private static final String VOCABULARY_TYPE = "COURSE";

	/**
	 * parses an Atom feed located at the URL given in the PARAM_URL_TO_PARSE
	 * parameter
	 *
	 * @param request HTTP Request handler
	 * @param response HTTP response handler
	 * @throws Exception Standard Liferay Exception
	 */
	public void parseUrl(ActionRequest request, ActionResponse response)
		throws Exception {

		String urlToParse = request.getParameter(PARAM_URL_TO_PARSE);

        if ((urlToParse == null) || urlToParse.equals("")) {
            return;
        }

		try {
            FeedParser.getInstance().runFeedParser(urlToParse);
		}
		catch (Exception e) {
			SessionErrors.add(request, e.getMessage());
		}
	}

	/**
	 * Clears all feeds from the database.
	 *
	 * @param request HTTP Request handler
	 * @param response HTTP Response handler
	 * @throws Exception Standard Liferay Exception
	 */
	public void purgeFeeds(ActionRequest request, ActionResponse response)
		throws Exception {

		List<FeedReference> feedRefs = new ArrayList<FeedReference>();
        try {
            feedRefs = FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        }
        catch (Exception e) {
            _log.error("Could not generate list of FeedReference objects: " + e.getMessage());
        }

		for (FeedReference feedRef : feedRefs) {
            try {
			    FeedReferenceLocalServiceUtil.deleteFeedReference(feedRef.getFeedReferenceId());
            }
            catch (Exception e) {
                _log.error("Could not purge FeedRef [" + feedRef.getFeedIri() + "] : " + e.getMessage());
            }
		}
	}


    /**
     * Processes all courses and components, and if they are not associated with
     * a valid feedReference object, removes them.
     *
	 * @param request HTTP Request handler
	 * @param response HTTP Response handler
	 * @throws Exception Standard Liferay Exception
     */
    public void purgeOrphans(ActionRequest request, ActionResponse response)
            throws Exception {

        HashSet<Long> feedRefIds = new HashSet<Long>();
		List<FeedReference> feedRefs =
                FeedReferenceLocalServiceUtil.getFeedReferences(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        List<Course> courses =
                CourseLocalServiceUtil.getCourses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        List<Component> components =
                ComponentLocalServiceUtil.getComponents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (FeedReference feedRef : feedRefs) {
            feedRefIds.add(feedRef.getFeedReferenceId());
        }

        for (Course course : courses) {
            if (!feedRefIds.contains(course.getFeedReferenceId())) {
                CourseLocalServiceUtil.deleteCourse(course);
            }
        }

        for (Component component : components) {
            if (!feedRefIds.contains(component.getFeedReferenceId())) {
                ComponentLocalServiceUtil.deleteComponent(component);
            }
        }
    }
    

	public void updateCourse(ActionRequest request, ActionResponse response)
		throws Exception {

		// TODO validate that the calling user has permission to edit the course
		long courseId = ParamUtil.getLong(request, "resourcePrimaryKey");
		if (Validator.isNotNull(courseId)) {
			Course course = CourseLocalServiceUtil.getCourse(courseId);
			ThemeDisplay themeDisplay =
				(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			ServiceContext serviceContext =
				ServiceContextFactory.getInstance(
					Course.class.getName(), request);
			Locale locale = request.getLocale();

			String title = ParamUtil.getString(request, "title");
			String description = ParamUtil.getString(request, "description");
			String assetTagsString = ParamUtil.getString(request, "tags");
			String catString = ParamUtil.getString(request, "categories");
			Date releaseOnDate =
				ParamUtil.getDate(
					request, "releaseOnDate", new PrettyDateFormat(
						locale, themeDisplay.getTimeZone()));
			Date removedDate =
				ParamUtil.getDate(request, "removedDate", new PrettyDateFormat(
					locale, themeDisplay.getTimeZone()));

			if (Validator.isNotNull(title)) {
				course.setTitle( title, locale);
			}
			course.setUpdatedDate(new Date());
			course.setDescription( description, request.getLocale());
			course.setKeywords(assetTagsString, locale);
			if (Validator.isNotNull(releaseOnDate)) {
				course.setReleaseOnDate(releaseOnDate);
			}
			if (Validator.isNotNull(removedDate)) {
				course.setRemovedDate(removedDate);
			}

			long[] categoryIds = new long[0];
			if (Validator.isNotNull(catString)) {
				String[] cats = catString.split(",");
				categoryIds = new long[cats.length];
				for (int i = 0; i < cats.length; i++) {
					categoryIds[i] = Integer.parseInt(cats[i]);
				}
			}

			String[] assetTags = new String[0];
			if (Validator.isNotNull(assetTagsString)) {
				assetTags = assetTagsString.split(",");
			}

			// TODO: this causes a
			// "You have entered invalid data. Please try again."
			// message but still works?
			CourseLocalServiceUtil.updateCourse(
				course.getUserId(), course.getCourseId(),
				course.getFeedReferenceId(), course.getHref(),
				course.getCourseIri(), course.getUpdatedDate(),
				course.getTitle(), course.getTranscriptAbstract(),
				course.getDescription(), course.getKeywords(),
				course.getCopyright(), course.getRatingLevel(),
				course.getRatingReason(), course.getDuration(),
                course.getDurationStandard(),
				course.getFeaturedStatus(), course.getPopularWeight(),
				course.getAccessCount(), course.getCompletedCount(),
				course.getRemoved(), course.getRemovedDate(),
				course.getSupersedesCourseIri(),
				course.getSupersededByCourseIri(), course.getReleaseOnDate(),
				course.getAcceptUntilDate(),
                course.getVersion(),course.getVersionDate(),
                course.getPrice(),course.getPriceUnit(),course.getPriceTerms(),course.getPriceExpiration(),
                course.getOneStarRateCount(),course.getTwoStarRateCount(),
                course.getThreeStarRateCount(),course.getFourStarRateCount(),
                course.getFiveStarRateCount(), serviceContext);
			CourseLocalServiceUtil.updateAsset(
				serviceContext.getUserId(), course, categoryIds, assetTags);
		}
	}

	public void deleteCourse(ActionRequest request, ActionResponse response)
		throws Exception {

		// TODO validate that the calling user has permission to delete the
		// course
		long courseId = ParamUtil.getLong(request, "resourcePrimKey");
		if (Validator.isNotNull(courseId)) {
			_log.debug("Removing course id " + courseId);
			CourseLocalServiceUtil.markCourseRemoved(courseId, true);
		}
		else {
			SessionErrors.add(request, "error-removing");
		}
	}

	public void purgeAllCourses(ActionRequest request, ActionResponse response)
		throws Exception {

		List<Course> courses;
		do {
			courses =
				CourseLocalServiceUtil.getCourses(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (Course course : courses) {
                // don't remove GlobalCourseReviews - but remove course PK assignment
                for (GlobalCourseReview globalCourseReview : CourseLocalServiceUtil.getGlobalCourseReviews(course)) {
                    globalCourseReview.setCourseId(0);
			        GlobalCourseReviewLocalServiceUtil.updateGlobalCourseReview(globalCourseReview);
		        }
                // remove local CourseReviews
                for (CourseReview review : CourseLocalServiceUtil.getCourseReviews(course)) {
			        CourseReviewLocalServiceUtil.deleteCourseReview(review);
		        }
				CourseLocalServiceUtil.deleteCourse(course);
			}
		}
		while (courses.size() > 0);
	}

	public void markCourseAsFeatured(
		ActionRequest request, ActionResponse response)
		throws Exception {

		// TODO validate that the calling user has permission to edit the course
		setCourseFeaturedStatus(request, response, 1.0);
	}

	public void markCourseAsNotFeatured(
		ActionRequest request, ActionResponse response)
		throws Exception {

		// TODO validate that the calling user has permission to edit the course
		setCourseFeaturedStatus(request, response, 0.0);
	}

	protected void setCourseFeaturedStatus(
		ActionRequest request, ActionResponse response, double featured)
		throws Exception {

		// TODO validate that the calling user has permission to edit the course
		long courseId = ParamUtil.getLong(request, "resourcePrimKey");
		if (Validator.isNotNull(courseId)) {
			_log.debug("Setting course id " + courseId +
				" featured status value " + featured);
			CourseLocalServiceUtil.setCourseFeaturedStatus(courseId, featured);
		}
		else {
			SessionErrors.add(request, "error-marking-featured");
		}
	}

	public void purgeAllComponents(
		ActionRequest request, ActionResponse response)
		throws Exception {

		List<Component> components;
		do {
			components =
				ComponentLocalServiceUtil.getComponents(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (Component component : components) {
				ComponentLocalServiceUtil.deleteComponent(component);
			}
		}
		while (components.size() > 0);
	}

	public void assignAllAccessCounts(
		ActionRequest request, ActionResponse response)
		throws Exception {

		try {
			CourseLocalServiceUtil.assignAllAccessCounts();
		}
		catch (Exception e) {
			SessionErrors.add(
				request, "error-updating-access-counts" + e.getMessage());
		}
	}

	public void assignAllCompletedCounts(
		ActionRequest request, ActionResponse response)
		throws Exception {

		try {
			CourseLocalServiceUtil.assignAllCompletedCounts();
		}
		catch (Exception e) {
			SessionErrors.add(
				request, "error-updating-completed-counts" + e.getMessage());
		}
	}

	public void assignAllPopularWeights(
		ActionRequest request, ActionResponse response)
		throws Exception {

		try {
			double accessCountWeight =
				CourseManagementPortlet.getAccessCountWeight();
			double completedCountWeight =
				CourseManagementPortlet.getCompletedCountWeight();
			double averageScoreWeight =
				CourseManagementPortlet.getAverageScoreWeight();

			CourseLocalServiceUtil.assignAllPopularWeights(
				accessCountWeight, completedCountWeight, averageScoreWeight);
		}
		catch (Exception e) {
			SessionErrors.add(
				request, "error-updating-popular-weights" + e.getMessage());
		}

	}

	public void purgeAllCourseTypeVocabularies(
		ActionRequest request, ActionResponse response)
		throws Exception {

		// TODO replace this with List all vocabularies,create edit button to
		// remove one at a time.
		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getExpandoValues(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			if (expandoValue.getData().equals(VOCABULARY_TYPE)) {
				long removeTableId = expandoValue.getTableId();
				long removeVocabularyId = expandoValue.getClassPK();
				long removeClassNameId = expandoValue.getClassNameId();

				// Delete Row:
				ExpandoRowLocalServiceUtil.deleteRow(
					removeTableId, removeVocabularyId);
				// Delete Values:
				ExpandoValueLocalServiceUtil.deleteValues(
					removeClassNameId, removeVocabularyId);

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

	/**
	 * Permanently removes all CourseRecords from the database Test Feature:
	 * Purge CourseRecords
	 */
	public void purgeAllCourseRecords(
		ActionRequest request, ActionResponse response)
		throws Exception {

		List<CourseRecord> courseRecords;
		do {
			courseRecords =
				CourseRecordLocalServiceUtil.getCourseRecords(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (CourseRecord courseRecord : courseRecords) {
				CourseRecordLocalServiceUtil.deleteCourseRecord(courseRecord);
			}
		}
		while (courseRecords.size() > 0);
	}

	/**
	 * Permanently removes all ComponentRecords from the database Test Feature:
	 * Purge ComponentRecords
	 */
	public void purgeAllComponentRecords(
		ActionRequest request, ActionResponse response)
		throws Exception {

		List<ComponentRecord> componentRecords;
		do {
			componentRecords =
				ComponentRecordLocalServiceUtil.getComponentRecords(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (ComponentRecord componentRecord : componentRecords) {
				ComponentRecordLocalServiceUtil.deleteComponentRecord(componentRecord);
			}
		}
		while (componentRecords.size() > 0);
	}

    /**
	 * Permanently removes all globalCourseReviews from the database Test Feature:
	 * Purge GlobalCourseReviews
	 */
	public void purgeAllGlobalCourseReviews(
		ActionRequest request, ActionResponse response)
		throws Exception {

		List<GlobalCourseReview> globalCourseReviews;
		do {
			globalCourseReviews =
				GlobalCourseReviewLocalServiceUtil.getGlobalCourseReviews(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (GlobalCourseReview globalCourseReview : globalCourseReviews) {
				GlobalCourseReviewLocalServiceUtil.deleteGlobalCourseReview(globalCourseReview);
			}
		}
		while (globalCourseReviews.size() > 0);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void insertCannedLocalCourseReview(
			ActionRequest request, ActionResponse response)
			throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        long userId = themeDisplay.getUserId();

        List<UserIdMapper> userMappers = UserIdMapperLocalServiceUtil.getUserIdMappers(userId);
        if (userMappers.size() == 0) {
            UserIdMapperLocalServiceUtil.updateUserIdMapper(userId,
                                PortalPropertiesUtil.getSsoImplementation(), null,
                                themeDisplay.getUser().getEmailAddress());
        }
        
        List<Course> courses = CourseLocalServiceUtil.findByCompanyId(companyId);
        Validate.notEmpty(courses,"There are no courses in the database for company ID " + companyId);
        
        Course course = courses.get(0);
        String summary = "This is the review summary";
        String content = "This is the review content";
        double rating = 4;
        
        CourseReview review =
                CourseReviewLocalServiceUtil.addCourseReview(userId,
                        course.getCourseId(), summary, content, rating,
                        ServiceContextUtil.createDefaultServiceContext(request));
        CourseReviewLocalServiceUtil.updateCourseReviewRating(review.getCourseReviewId(), 4);
	}
	
	public void updateUserHelpfulness(ActionRequest request, ActionResponse response)
			throws SystemException, PortalException {
		CourseManagementPortlet.updateUserHelpfulness();
	}
	
	/**
     * Purges all items from the index, regardless of whether the index is
     * a Nutch or Solr index.
     * 
     * @param request
     * @param response
     */
    public void purgeNutchIndex(ActionRequest request, ActionResponse response) {
        CrawlTool crawlTool = CrawlTool.getInstance();

        if (crawlTool.isMaster()) {
            crawlTool.purgeIndex();
        }
    }

	public void setSearchAddressFromRegistry(ActionRequest request, ActionResponse response) {
		ExternalOpenSearchImpl.setSearchAddressFromRegistry();
	}

	public void reloadTopReviewerThreshold(ActionRequest request, ActionResponse response) {
		ReviewUtil.reloadTopReviewerThreshold();
	}

	private static Log _log = LogFactoryUtil.getLog(CourseTestingPortlet.class);
}