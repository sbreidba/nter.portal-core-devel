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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetVocabulary;
import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.parser.FeedContext;
import org.nterlearning.atom.parser.dao.NterCatalogRecordDependencyException;
import org.nterlearning.atom.parser.model.AsVerb;
import org.nterlearning.datamodel.catalog.model.*;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

public interface FeedParser {

	public static final String DEFAULT_JAVA_LANGUAGE = "en_US";
    
    public void setExtension();

    public void setExtension(NterNameSpace nameSpace);

    public FeedReference parserToCatalog(Feed feed, FeedContext fc);

    public Component parserCourseComponentToCatalog(Entry componentEntry, FeedContext fc);

    public Component setSearchTerms(Entry componentEntry, Component catalogComponent);

    public Course parserCourseToCatalog(Entry courseEntry, FeedContext fc);

    public Course setSearchTerms(Entry courseEntry, Course catalogCourse);

    public CourseRecord parserCourseRecordToCatalog(Entry courseRecordEntry,
                                                    long feedReferenceId, long companyId, boolean dependenciesArePersisted)
            throws SystemException, PortalException, NterCatalogRecordDependencyException;

    public AssetVocabulary parserVocabularyToCatalog(Entry parserEntry, FeedContext fc);

    public GlobalCourseReview parserReviewToCatalog(Entry reviewEntry, FeedContext fc)
            throws SystemException;

    public Entry catalogReviewToParser(CourseReview courseReview, Entry entry,
                                       AsVerb.VerbType verbType)
            throws SystemException, PortalException;

}
