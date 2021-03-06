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

package org.nterlearning.atom.parser.feedParser;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.ExtensibleElement;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserIdMapper;
import com.liferay.portal.service.UserIdMapperLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.NoSuchCategoryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.ServiceContextUtil;
import org.nterlearning.atom.parser.dao.CatalogDataModelUtils;
import org.nterlearning.atom.parser.dao.NterCatalogRecordDependencyException;
import org.nterlearning.atom.parser.model.AsVerb;
import org.nterlearning.atom.parser.model.NterCategoryRef;
import org.nterlearning.atom.parser.model.NterCopyright;
import org.nterlearning.atom.parser.model.NterCourse;
import org.nterlearning.atom.parser.model.NterCourseDescription;
import org.nterlearning.atom.parser.model.NterCourseRecord;
import org.nterlearning.atom.parser.model.NterDuration;
import org.nterlearning.atom.parser.model.NterKeyword;
import org.nterlearning.atom.parser.model.NterRating;
import org.nterlearning.atom.parser.model.NterRelated;
import org.nterlearning.atom.parser.model.NterTitle;
import org.nterlearning.atom.parser.model.NterTranscriptAbstract;
import org.nterlearning.atom.parser.model.NterVocabulary;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.course.enumerations.FeedType;
import org.nterlearning.course.enumerations.RelationshipType;
import org.nterlearning.course.util.FeedReferenceUtil;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.model.CourseRecord;
import org.nterlearning.datamodel.catalog.model.CourseReview;
import org.nterlearning.datamodel.catalog.model.FeedReference;
import org.nterlearning.datamodel.catalog.model.GlobalCourseReview;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.utils.PortalPropertiesUtil;

public class FeedParser_061 implements FeedParser {

    private final Log mLog = LogFactoryUtil.getLog(FeedParser_061.class);

    private NterNameSpace mNterNamespace;
    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;


    public FeedParser_061() {
        setExtension();
    }


    public void setExtension() {
        mNterNamespace = NterNameSpace.FEED_VERSION_061;
        mNterExtension = new NterExtension(mNterNamespace);
        mStaticParser = new StaticNterAtomParser(mNterExtension);   
    }


    public void setExtension(NterNameSpace nameSpace) {
        mNterNamespace = nameSpace;
        mNterExtension = new NterExtension(mNterNamespace);
        mStaticParser = new StaticNterAtomParser(mNterExtension);
    }
    

    public FeedReference parserToCatalog(Feed feed, FeedContext fc) {

        FeedReference feedReference = null;
        try {
            feedReference =
                    FeedReferenceLocalServiceUtil.fetchByFeedIri(feed.getId().toString());
        }
        catch (SystemException se) {
            mLog.error("Error locating feedReference object for " +
                    feed.getId().toString() + ": " + se.getMessage());
        }

        if (feedReference == null) {
            feedReference = new FeedReferenceImpl();
            feedReference.setFeedType(FeedType.unknown.getCodeValue());
            feedReference.setHref(feed.getSelfLinkResolvedHref().toString());
            feedReference.setHrefHash(
                    FeedReferenceUtil.generateHash(feed.getSelfLinkResolvedHref().toString()));
            feedReference.setContentProviderId(fc.getContentProviderId());
            feedReference.setFeedIri(feed.getId().toString());
            feedReference.setFeedVersion(mNterNamespace.getVersion());
            feedReference.setGroupId(fc.getScopeGroupId());
            feedReference.setCompanyId(fc.getCompanyId());
            feedReference.setPshbSubscribed(false);
        }
        else {
            // there's a chance that a few fields have updated
            feedReference.setFeedVersion(mNterNamespace.getVersion());
            feedReference.setContentProviderId(fc.getContentProviderId());

            feedReference.setHref(feed.getSelfLinkResolvedHref().toString());
            feedReference.setHrefHash(
                    FeedReferenceUtil.generateHash(feed.getSelfLinkResolvedHref().toString()));
            feedReference.setGroupId(fc.getScopeGroupId());
            feedReference.setCompanyId(fc.getCompanyId());
        }

        return feedReference;
    }


    public Component parserCourseComponentToCatalog(Entry componentEntry, FeedContext fc) {

        Component catalogComponent = new ComponentImpl();
        catalogComponent.setCompanyId(fc.getCompanyId());
        catalogComponent.setFeedReferenceId(fc.getFeedReferenceId());
        catalogComponent.setComponentIri(componentEntry.getId().toString());
        catalogComponent.setDescription(componentEntry.getSummary());

        Link link = componentEntry.getLinks().get(0);
        catalogComponent.setDisplayHeight(Integer.valueOf(link.getAttributeValue(
                        mNterExtension.getQName(NterExtension.DISPLAY_HEIGHT_ATTRIBUTE_NAME))));
        catalogComponent.setDisplayWidth(Integer.valueOf(link.getAttributeValue(
                        mNterExtension.getQName(NterExtension.DISPLAY_WIDTH_ATTRIBUTE_NAME))));
        catalogComponent.setHref(link.getHref().toString());

        catalogComponent.setTitle(componentEntry.getTitle());
        catalogComponent.setCreateDate(new Date());
        catalogComponent.setUpdatedDate(componentEntry.getUpdated());
        catalogComponent.setVersionDate(componentEntry.getUpdated());
        catalogComponent.setPrice(0.0);

        if (componentEntry.getLanguage() == null) {
            catalogComponent.setLanguage(DEFAULT_JAVA_LANGUAGE);
        }
        else {
            catalogComponent.setLanguage(
                    FeedParserUtil.parserLangToCatalogLang(componentEntry.getLanguage()));
        }

        setSearchTerms(componentEntry, catalogComponent);

        return catalogComponent;
    }


    public Component setSearchTerms(Entry componentEntry, Component catalogComponent) {

        // for course components that are part of a search result
        ExtensibleElement searchContext = componentEntry.getExtension(
                mNterExtension.getQName(NterExtension.SEARCH_CONTEXT_ELEMENT_NAME));
        ExtensibleElement searchRelevance = componentEntry.getExtension(
                mNterExtension.getQName(NterExtension.SEARCH_RELEVANCE_ELEMENT_NAME));

        if (searchContext != null) {
            catalogComponent.setSearchContext(searchContext.getText());
        }

        if (searchRelevance != null) {
            catalogComponent.setSearchRelevance(
                    Float.parseFloat(searchRelevance.getText()));
        }

        return catalogComponent;
    }


    public Course parserCourseToCatalog(Entry courseEntry, FeedContext fc) {

        NterCourse parserCourse = mStaticParser.getCourse(courseEntry);

        Course catalogCourse = new CourseImpl();
        catalogCourse.setFeedReferenceId(fc.getFeedReferenceId());

        List<Link> parserEntryLinks = courseEntry.getLinks();
        if (parserEntryLinks.size() > 0) {
            String entryHref =
                    URLEncoder.encode(parserEntryLinks.get(0).getHref().toString());
            catalogCourse.setHref(entryHref);
        }
        else {
            throw new RuntimeException("Course entry with id [" +
                    courseEntry.getId() + "] has no links");
        }

        catalogCourse.setCourseIri(courseEntry.getId().toString());
        catalogCourse.setUpdatedDate(courseEntry.getUpdated());
        catalogCourse.setGroupId(fc.getFeedReferenceGroupId());
        catalogCourse.setCompanyId(fc.getCompanyId());
        catalogCourse.setVersionDate(courseEntry.getUpdated());
        catalogCourse.setPrice(0.0);

        for (NterDuration duration : parserCourse.getDurations()) {
            catalogCourse.setDuration(duration.getText());
            catalogCourse.setDurationStandard(duration.getDurationStandard());
        }
        for (NterTitle title : parserCourse.getTitles()) {
            catalogCourse.setTitle(title.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(title.getLanguage()));
        }
        for (NterTranscriptAbstract abstract_ : parserCourse.getTranscriptAbstracts()) {
            catalogCourse.setTranscriptAbstract(abstract_.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(abstract_.getLanguage()));
        }
        for (NterCourseDescription description : parserCourse.getDescriptions()) {
            catalogCourse.setDescription(description.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(description.getLanguage()));
        }
        for (NterKeyword keyword : parserCourse.getKeywords()) {
            catalogCourse.setKeywords(keyword.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(keyword.getLanguage()));
        }
        for (NterCopyright copyright : parserCourse.getCopyrights()) {
            catalogCourse.setCopyright(copyright.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(copyright.getLanguage()));
        }
        for (NterRating rating : parserCourse.getRatings()) {
            catalogCourse.setRatingLevel( rating.getLevel(),
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()) );
            catalogCourse.setRatingReason(rating.getReason(),
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()));
        }
        for (NterRelated related : parserCourse.getRelateds()) {
            if (related.getRelationship().equals(RelationshipType.SUPERSEDES)) {
                catalogCourse.setSupersedesCourseIri(related.getRelatedEntryId());
            }
        }

        if (parserCourse.getCategoryRefs().size() > 0) {
            ArrayList<Long> categoryIdList = new ArrayList<Long>();

            for (NterCategoryRef categoryRef : parserCourse.getCategoryRefs()) {
                String categoryIri = categoryRef.getCategoryId();
                try {
                    AssetCategory assetCategory =
                            CourseLocalServiceUtil.findAssetCategoryByG_N(
                                    fc.getFeedReferenceGroupId(),
                                    categoryIri);
                    categoryIdList.add(assetCategory.getCategoryId());
                }
                catch (NoSuchCategoryException e) {
                    mLog.warn("Category " + categoryIri + " not found: " + e.getMessage());
                }
                catch (SystemException e) {
                    mLog.error(e.getMessage());
                }
            }

            long[] categoryIdArray = new long[categoryIdList.size()];
            for (int i = 0; i < categoryIdList.size(); i++) {
                categoryIdArray[i] = categoryIdList.get(i);
            }

            fc.getSc().setAssetCategoryIds(categoryIdArray);
        }        

        setSearchTerms(courseEntry, catalogCourse);

        return catalogCourse;
    }


    public Course setSearchTerms(Entry courseEntry, Course catalogCourse) {

        // for courses that are part of a search result
        ExtensibleElement searchContext = courseEntry.getExtension(
                mNterExtension.getQName(NterExtension.SEARCH_CONTEXT_ELEMENT_NAME));
        ExtensibleElement searchRelevance = courseEntry.getExtension(
                mNterExtension.getQName(NterExtension.SEARCH_RELEVANCE_ELEMENT_NAME));

        if (searchContext != null) {
            catalogCourse.setSearchContext(searchContext.getText());
        }

        if (searchRelevance != null) {
            catalogCourse.setSearchRelevance(Float.parseFloat(searchRelevance.getText()));
        }

        return catalogCourse;
    }


    public CourseRecord parserCourseRecordToCatalog(Entry courseRecordEntry,
            long feedReferenceId, long companyId, boolean dependenciesArePersisted)
            throws SystemException, PortalException, NterCatalogRecordDependencyException {

        NterCourseRecord parserRecord = mStaticParser.getCourseRecord(courseRecordEntry);

        if (parserRecord == null) {
            return null;
        }

        String studentSingleSignOnValue = parserRecord.getStudentUserId();
        String recordCourseIri = parserRecord.getCourseId();

        UserIdMapper userMapper;
        try {
            userMapper = UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(
                                PortalPropertiesUtil.getSsoImplementation(),
                                studentSingleSignOnValue);
        }
        catch (NoSuchUserIdMapperException e) {
            return CatalogDataModelUtils.createCourseRecord(feedReferenceId,
                    studentSingleSignOnValue, courseRecordEntry.getId().toString(),
                    recordCourseIri, courseRecordEntry.getUpdated(),
                    parserRecord.getProgress(), false, false, parserRecord.getAssigned());
        }

        User user;
        try {
            user = UserLocalServiceUtil.getUserById(companyId, userMapper.getUserId());

            if (dependenciesArePersisted) {
                Course course = CourseLocalServiceUtil.fetchByCourseIri(recordCourseIri);

                if (course == null) {
                    mLog.warn("Course with IRI [" + recordCourseIri +
                            "] is not persisted, and may be a dependency for a course record [" +
                            courseRecordEntry.getId().toString() + "]");
                }
            }
            // user found in Liferay User_ table
            return CatalogDataModelUtils.createCourseRecord(feedReferenceId,
                    user.getUserId(), studentSingleSignOnValue,
                    courseRecordEntry.getId().toString(), recordCourseIri,
                    courseRecordEntry.getUpdated(), parserRecord.getProgress(),
                    false, false, parserRecord.getAssigned());
        }
        catch (NoSuchUserException nsu) {
            if (dependenciesArePersisted) {
                throw new NterCatalogRecordDependencyException(
                        "Dependencies Required: Could not find student user ID from the SSO ID [" +
                                studentSingleSignOnValue + "]");
            }
            else {
                // student progress reports processed so an SSO id was created but
                // the user is not yet in the Liferay User_ table
                return CatalogDataModelUtils.createCourseRecord(feedReferenceId,
                        studentSingleSignOnValue, courseRecordEntry.getId().toString(),
                        recordCourseIri, courseRecordEntry.getUpdated(),
                        parserRecord.getProgress(), false, false,
                        parserRecord.getAssigned());
            }
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
            throw new NterCatalogRecordDependencyException(e.getMessage());
        }
    }


    public AssetVocabulary parserVocabularyToCatalog(Entry parserEntry, FeedContext fc) {

        long vocabularyId = 0;
        NterVocabulary parserVocabulary = mStaticParser.getVocabulary(parserEntry);

        AssetVocabulary catalogVocabulary =
                AssetVocabularyLocalServiceUtil.createAssetVocabulary(vocabularyId);

        Date now = new Date();
        catalogVocabulary.setGroupId(fc.getFeedReferenceGroupId());
        catalogVocabulary.setCompanyId(fc.getCompanyId());
        catalogVocabulary.setCreateDate(now);
        catalogVocabulary.setModifiedDate(now);
        catalogVocabulary.setUserId(fc.getUserId());
        catalogVocabulary.setName(parserEntry.getId().toString());
        for (NterTitle title : parserVocabulary.getTitles()) {
            catalogVocabulary.setTitle( title.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(title.getLanguage()));
        }
        catalogVocabulary.setDescription(parserEntry.getSummary());

        return catalogVocabulary;
    }


    public GlobalCourseReview parserReviewToCatalogGlobal(Entry reviewEntry, FeedContext fc)
            throws SystemException {
        return null;
    }

    public CourseReview parserReviewToCatalogLocal(Entry reviewEntry, FeedContext fc)
            throws SystemException, PortalException {
        return null;
    }

    public Entry catalogReviewToParserLocal(CourseReview courseReview, Entry entry,
            AsVerb.VerbType verbType)
            throws SystemException, PortalException {

    	return null;

    }

    public Entry catalogReviewToParserGlobal(CourseReview courseReview, Entry entry,
            AsVerb.VerbType verbType)
            throws SystemException, PortalException {

    	return null;

    }
}
