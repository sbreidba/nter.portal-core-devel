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
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import org.nterlearning.atom.enumerations.NterEntryType;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.AsExtension;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.generator.AbderaAtomGenerator;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.model.*;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.course.enumerations.RelationshipType;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.model.impl.GlobalCourseReviewImpl;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.nterlearning.utils.PortalPropertiesUtil;
import org.apache.abdera.model.*;
import org.apache.commons.lang.Validate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedParser_062 extends FeedParser_061 implements FeedParser {

    private final Log mLog = LogFactoryUtil.getLog(FeedParser_062.class);

    private NterNameSpace mNterNamespace;
    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;


    public FeedParser_062() {
        setExtension();
    }


    @Override
    public void setExtension() {
        mNterNamespace = NterNameSpace.FEED_VERSION_062;
        mNterExtension = new NterExtension(mNterNamespace);
        mStaticParser = new StaticNterAtomParser(mNterExtension);
        super.setExtension(mNterNamespace);
    }

    @Override
    public void setExtension(NterNameSpace nameSpace) {
        mNterNamespace = nameSpace;
        mNterExtension = new NterExtension(mNterNamespace);
        mStaticParser = new StaticNterAtomParser(mNterExtension);

        super.setExtension(mNterNamespace);
    }

    

    @Override
    public Component parserCourseComponentToCatalog(Entry componentEntry, FeedContext fc) {

        NterComponent parserComponent = mStaticParser.getComponent(componentEntry);

        Component catalogComponent = new ComponentImpl();
        catalogComponent.setCompanyId(fc.getCompanyId());
        catalogComponent.setGroupId(fc.getScopeGroupId());
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
        //on initial entry of course the versionDate should be updatedDate
        catalogComponent.setVersionDate(componentEntry.getUpdated());

        for (NterVersion version : parserComponent.getVersions()) {
            catalogComponent.setVersion(version.getText());
        }

        for (NterPrice price : parserComponent.getPrices()) {
            catalogComponent.setPrice(price.getPrice());
            catalogComponent.setPriceUnit(price.getPriceUnit());
            catalogComponent.setPriceTerms(price.getPriceTerms());
            catalogComponent.setPriceExpiration(price.getPriceExpiration());
        }

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
            throw new RuntimeException("Course entry with id [" + courseEntry.getId()
                    + "] has no links");
        }

        catalogCourse.setCourseIri(courseEntry.getId().toString());
        catalogCourse.setUpdatedDate(courseEntry.getUpdated());
        catalogCourse.setGroupId(fc.getFeedReferenceGroupId());
        catalogCourse.setCompanyId(fc.getCompanyId());
        //on initial entry of course the versionDate should be updatedDate
        catalogCourse.setVersionDate(courseEntry.getUpdated());

        for (NterVersion version : parserCourse.getVersions()) {
            catalogCourse.setVersion(version.getText());
        }

        for (NterDuration duration : parserCourse.getDurations()) {
            catalogCourse.setDuration(duration.getText());
            catalogCourse.setDurationStandard(duration.getDurationStandard());
        }

        for (NterTitle title : parserCourse.getTitles()) {
            catalogCourse.setTitle( title.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(title.getLanguage()));
        }
        for (NterTranscriptAbstract abstract_ : parserCourse.getTranscriptAbstracts()) {
            catalogCourse.setTranscriptAbstract( abstract_.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(abstract_.getLanguage()));
        }
        for (NterCourseDescription description : parserCourse.getDescriptions()) {
            catalogCourse.setDescription( description.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(description.getLanguage()));
        }
        for (NterKeyword keyword : parserCourse.getKeywords()) {
            catalogCourse.setKeywords(keyword.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(keyword.getLanguage()));
        }
        for (NterCopyright copyright : parserCourse.getCopyrights()) {
            catalogCourse.setCopyright( copyright.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(copyright.getLanguage()));
        }
        for (NterRating rating : parserCourse.getRatings()) {
            catalogCourse.setRatingLevel( rating.getLevel(),
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()));
            catalogCourse.setRatingReason( rating.getReason(),
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()));
        }
        for (NterRelated related : parserCourse.getRelateds()) {
            if (related.getRelationship().equals(RelationshipType.SUPERSEDES)) {
                catalogCourse.setSupersedesCourseIri(related.getRelatedEntryId());
            }
        }

        for (NterPrice price : parserCourse.getPrices()) {
            catalogCourse.setPrice(price.getPrice());
            catalogCourse.setPriceUnit(price.getPriceUnit());
            catalogCourse.setPriceTerms(price.getPriceTerms());
            catalogCourse.setPriceExpiration(price.getPriceExpiration());
        }

        if (parserCourse.getCategoryRefs().size() > 0) {
            ArrayList<Long> categoryIdList = new ArrayList<Long>();

            for (NterCategoryRef categoryRef : parserCourse.getCategoryRefs()) {
                String categoryIri = categoryRef.getCategoryId();
                try {
                    AssetCategory assetCategory =
                            CourseLocalServiceUtil.findAssetCategoryByG_N(
                                    fc.getFeedReferenceGroupId(), categoryIri);
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
    public GlobalCourseReview parserReviewToCatalog(Entry reviewEntry, FeedContext fc)
            throws SystemException {

        AsObject review = mStaticParser.getObject(reviewEntry);
        AsTarget target = mStaticParser.getTarget(reviewEntry);
        Person actor = reviewEntry.getAuthor();
        boolean trustedReviewer =
                Boolean.parseBoolean(mStaticParser.getTrustedReviewer(actor));
        String singleSignOnValue = mStaticParser.getActorId(actor);
        String nterInstance = mStaticParser.getNterInstance(reviewEntry);

        Course course = CourseLocalServiceUtil.fetchByCourseIri(target.getId());
        if (course == null) {
            mLog.warn("Review [" + review.getId() + "] in entry [" +
                reviewEntry.getId() + "] has a target course with ID [" +
                target.getId() + "], but this course cannot be found in the database.");
            return null;
        }

        List<AsPermalinkUrl> permalinks = review.getPermalinkUrls();

        String href = null;
        if (permalinks.size() > 0) {
            href = permalinks.get(0).getHref();
        }

        GlobalCourseReview gcr = new GlobalCourseReviewImpl();
        gcr.setCompanyId(fc.getCompanyId());
        gcr.setContent(review.getContent().getText());
        gcr.setCourseId(course.getCourseId());
        gcr.setCourseIri(target.getId());
        gcr.setCourseReviewIri(review.getId());
        gcr.setFromTrustedReviewer(trustedReviewer);
        gcr.setGroupId(fc.getScopeGroupId());

        if (href != null) {
            gcr.setHref(href);
        }

        gcr.setIsHidden(false);
        gcr.setCreateDate(review.getPublished());
        gcr.setModifiedDate(review.getUpdated());
        gcr.setNterInstance(nterInstance);
        gcr.setSingleSignOnValue(singleSignOnValue);
        gcr.setStarScore(review.getRating());
        gcr.setSummary(review.getSummary());
        gcr.setUpdatedDate(reviewEntry.getUpdated());
        gcr.setUserDisplayName(actor.getName());

        return gcr;
    }


    @Override
    public Entry catalogReviewToParser(CourseReview courseReview, Entry entry,
            AsVerb.VerbType verbType)
            throws SystemException, PortalException {

        String catalogLanguage = "en_US";
        String xmlLanguage = "en-US";

        AsObject review = entry.addExtension(AsExtension.OBJECT_ELEMENT);
        AsTarget target = entry.addExtension(AsExtension.TARGET_ELEMENT);
        AsVerb verb = entry.addExtension(AsExtension.VERB_ELEMENT);
        verb.setVerb(verbType);

        // get all the parameters we need from the local review
        String content = courseReview.getContent();
        long courseId = courseReview.getCourseId();
        long reviewId = courseReview.getCourseReviewId();
        Date modifiedDate = courseReview.getModifiedDate();
        Date publishedDate = courseReview.getCreateDate();
        String summary = courseReview.getSummary();
        long userId = courseReview.getUserId();
        double score = RatingsEntryLocalServiceUtil.getEntry(userId,
                Course.class.getCanonicalName(), courseId).getScore();
        // TODO: add trusted reviewer
        String trustedReviewer = String.valueOf(false);

        // error checking
        Validate.notNull(entry.getId(),
                "The entry in which to convert course review ID " + reviewId + " has no ID");

        // create parameters we need to fetch from elsewhere
        String courseIri, userName, ssoValue, courseName, reviewName;

        // the course IRI
        Course course = CourseLocalServiceUtil.fetchByCourseId(courseId);
        Validate.notNull(course, "When trying to create a global course review from local review ID " +
                reviewId + ", cannot find the target course with ID " + courseId);
        courseIri = course.getCourseIri();
        courseName = course.getTitle(catalogLanguage);
        reviewName = "Review of course ID " + courseId + " (" + courseName + ")";

        // the user name
        User user = UserLocalServiceUtil.getUser(userId);
        Validate.notNull(user, "When trying to create a global course review from local review ID " +
                reviewId + ", cannot find the review author, user with ID " + userId);
        userName = user.getFullName();

        // the sso value
        try {
            UserIdMapper userMapper =
                    UserIdMapperLocalServiceUtil.getUserIdMapper(
                            userId, PortalPropertiesUtil.getSsoImplementation());
            ssoValue = userMapper.getExternalUserId();
        }
        catch (NoSuchUserIdMapperException e) {
            throw new SystemException(
                    "When trying to create a global course review from local review ID " +
                    reviewId + ", no SSO users were found with ID " + userId +
                    ", when exactly one user with that ID was expected.");
        }

        review.setContent(content, AsContent.AsContentTypeType.TEXT);
        review.setId(AbderaAtomGenerator.generateAtomId(
                        reviewId, AsExtension.ATOM_ID_DATA_TYPE_REVIEW));
        review.setName(reviewName);
        review.setObjectType(AsObjectType.AsObjectTypeType.REVIEW);
        review.setPublished(publishedDate);
        review.setRating((float) score);
        review.setSummary(summary);
        review.setUpdated(modifiedDate);

        target.setObjectType(AsObjectType.AsObjectTypeType.COURSE);
        target.setId(courseIri);
        target.setName(courseName);

        // create the author
        Person author = entry.getAuthor();
        if (author == null) {
            author = entry.addAuthor(userName);
        }
        author.setName(userName);
        author.setAttributeValue(
                mNterExtension.getQName(NterExtension.STUDENT_USER_ID_ATTRIBUTE_NAME),
                ssoValue);
        author.setAttributeValue(
                mNterExtension.getQName(NterExtension.TRUSTED_REVIEWER_ATTRIBUTE_NAME),
                trustedReviewer);
        author.addSimpleExtension(
                AsExtension.OBJECT_TYPE_ELEMENT,
                AsObjectType.AsObjectTypeType.PERSON.value());

        // add the entry stuff required by atom and NTER
        entry.setTitle(author.getName() + " " + verbType.getActionTense() +
                " a review of " + courseName);
        entry.setUpdated(new Date(System.currentTimeMillis()));
        entry.setAttributeValue(
                mNterExtension.getQName(NterExtension.ENTRY_TYPE_ATTRIBUTE_NAME),
                NterEntryType.REVIEW.value());
        entry.setLanguage(xmlLanguage);
        entry.setPublished(publishedDate);

        return entry;
    }
}
