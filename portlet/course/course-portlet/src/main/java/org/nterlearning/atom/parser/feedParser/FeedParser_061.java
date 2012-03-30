/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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
import org.nterlearning.atom.parser.dao.CatalogDataModelUtils;
import org.nterlearning.atom.parser.dao.NterCatalogRecordDependencyException;
import org.nterlearning.atom.parser.model.*;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.course.enumerations.FeedType;
import org.nterlearning.course.enumerations.RelationshipType;
import org.nterlearning.course.util.FeedReferenceUtil;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.model.impl.FeedReferenceImpl;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.datamodel.catalog.service.FeedReferenceLocalServiceUtil;
import org.nterlearning.utils.PortalPropertiesUtil;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.ExtensibleElement;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedParser_061 implements FeedParser {

    private final Log mLog = LogFactoryUtil.getLog(FeedParser_061.class);

    private NterNameSpace mNterNamespace;
    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;


    public FeedParser_061() {
        setExtension();
    }


    @Override
    public void setExtension() {
        mNterNamespace = NterNameSpace.FEED_VERSION_061;
        mNterExtension = new NterExtension(mNterNamespace);
        mStaticParser = new StaticNterAtomParser(mNterExtension);   
    }


    @Override
    public void setExtension(NterNameSpace nameSpace) {
        mNterNamespace = nameSpace;
        mNterExtension = new NterExtension(mNterNamespace);
        mStaticParser = new StaticNterAtomParser(mNterExtension);
    }
    

    @Override
    public FeedReference parserToCatalog(Feed feed, FeedContext fc) {

        FeedReference feedReference;
        try {
            feedReference =
                    FeedReferenceLocalServiceUtil.fetchByFeedIri(feed.getId().toString());

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
            }
        }
        catch (SystemException se) {
            feedReference = new FeedReferenceImpl();
            mLog.error("Error creating feedReference object for " +
                    feed.getId().toString());
        }

        return feedReference;
    }


    @Override
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


    @Override
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


    @Override
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
            catalogCourse.setTitle(
                    FeedParserUtil.parserLangToCatalogLocale(title.getLanguage()),
                    title.getText());
        }
        for (NterTranscriptAbstract abstract_ : parserCourse.getTranscriptAbstracts()) {
            catalogCourse.setTranscriptAbstract(
                    FeedParserUtil.parserLangToCatalogLocale(abstract_.getLanguage()),
                    abstract_.getText());
        }
        for (NterCourseDescription description : parserCourse.getDescriptions()) {
            catalogCourse.setDescription(
                    FeedParserUtil.parserLangToCatalogLocale(description.getLanguage()),
                    description.getText());
        }
        for (NterKeyword keyword : parserCourse.getKeywords()) {
            catalogCourse.setKeywords(
                    FeedParserUtil.parserLangToCatalogLocale(keyword.getLanguage()),
                    keyword.getText());
        }
        for (NterCopyright copyright : parserCourse.getCopyrights()) {
            catalogCourse.setCopyright(
                    FeedParserUtil.parserLangToCatalogLocale(copyright.getLanguage()),
                    copyright.getText());
        }
        for (NterRating rating : parserCourse.getRatings()) {
            catalogCourse.setRatingLevel(
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()),
                    rating.getLevel());
            catalogCourse.setRatingReason(
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()),
                    rating.getReason());
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


    @Override
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


    @Override
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


    @Override
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
            catalogVocabulary.setTitle(
                    FeedParserUtil.parserLangToCatalogLocale(title.getLanguage()),
                    title.getText());
        }
        catalogVocabulary.setDescription(parserEntry.getSummary());

        return catalogVocabulary;
    }


    @Override
    public GlobalCourseReview parserReviewToCatalog(Entry reviewEntry, FeedContext fc)
            throws SystemException {
        return null;
    }


    @Override
    public Entry catalogReviewToParser(CourseReview courseReview, Entry entry,
            AsVerb.VerbType verbType)
            throws SystemException, PortalException {
        return null;
    }
}
