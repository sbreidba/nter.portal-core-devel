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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.asset.NoSuchCategoryException;
import com.liferay.portlet.asset.model.AssetCategory;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.model.*;
import org.nterlearning.atom.parser.staticParser.StaticNterAtomParser;
import org.nterlearning.course.enumerations.RelationshipType;
import org.nterlearning.datamodel.catalog.model.*;
import org.nterlearning.datamodel.catalog.model.impl.ComponentImpl;
import org.nterlearning.datamodel.catalog.model.impl.CourseImpl;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;
import org.apache.abdera.model.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

public class FeedParser_063 extends FeedParser_062 implements FeedParser {

    private final Log mLog = LogFactoryUtil.getLog(FeedParser_063.class);

    private NterNameSpace mNterNamespace;
    private NterExtension mNterExtension;
    private StaticNterAtomParser mStaticParser;


    public FeedParser_063() {
        setExtension();
    }


    @Override
    public void setExtension() {
        mNterNamespace = NterNameSpace.FEED_VERSION_063;
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

        NterComponent parserComponent =
                mStaticParser.getComponent(componentEntry);

        Component catalogComponent = new ComponentImpl();
        catalogComponent.setCompanyId(fc.getCompanyId());
        catalogComponent.setGroupId(fc.getScopeGroupId());
        catalogComponent.setFeedReferenceId(fc.getFeedReferenceId());
        catalogComponent.setComponentIri(componentEntry.getId().toString());
        catalogComponent.setTitle(componentEntry.getTitle());
        catalogComponent.setDescription(componentEntry.getSummary());

        Link selfLink = componentEntry.getLink("self");
        catalogComponent.setHref(selfLink.getHref().toString());
        catalogComponent.setDisplayHeight(Integer.valueOf(selfLink.getAttributeValue(
                        mNterExtension.getQName(NterExtension.DISPLAY_HEIGHT_ATTRIBUTE_NAME))));
        catalogComponent.setDisplayWidth(Integer.valueOf(selfLink.getAttributeValue(
                        mNterExtension.getQName(NterExtension.DISPLAY_WIDTH_ATTRIBUTE_NAME))));

        Link searchLink = componentEntry.getLink("search");
        if (searchLink != null) {
            catalogComponent.setFullTextHref(searchLink.getHref().toString());
        }

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

        for (NterCopyright copyright : parserComponent.getCopyrights()) {
            catalogComponent.setCopyright( copyright.getText(),
                    FeedParserUtil.parserLangToCatalogLocale(copyright.getLanguage()));
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

        try {
            Link selfLink = courseEntry.getLink("self");
            if (selfLink != null) {
                catalogCourse.setHref(URLEncoder.encode(
                        selfLink.getHref().toString(), StringPool.UTF8));
            }

            Link searchLink = courseEntry.getLink("search");
            if (searchLink != null) {
                catalogCourse.setFullTextHref(URLEncoder.encode(
                        searchLink.getHref().toString(), StringPool.UTF8));
            }
        }
        catch (Exception e) {
            mLog.warn("Unsupported encoding type");
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
            catalogCourse.setRatingLevel(rating.getLevel(),
                    FeedParserUtil.parserLangToCatalogLocale(rating.getLanguage()));
            catalogCourse.setRatingReason(rating.getReason(),
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
}
