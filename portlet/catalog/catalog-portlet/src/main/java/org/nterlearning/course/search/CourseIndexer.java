/*
 National Training and Education Resource (NTER)
 Copyright (C) 2012  SRI International

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or (at
 your option) any later version.

 This program is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.
 */


package org.nterlearning.course.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.NoSuchCourseException;
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author bblonski
 */
public class CourseIndexer extends BaseIndexer {

	private static final String[] CLASS_NAMES = {Course.class.getName()};

	private static final String PORTLET_ID = NterKeys.COURSE_SEARCH_PORTLET;

	private static final String[] COURSE_KEYWORDS_FIELDS = {
		    Field.ASSET_TAG_NAMES,
            Field.COMMENTS,
            Field.CONTENT,
            Field.DESCRIPTION,
            Field.PROPERTIES,
            Field.TITLE,
            NterKeys.CONTRIBUTOR_NAME
	};

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

    public String getPortletId() {
        return PORTLET_ID;
    }


    @Override
    public Summary doGetSummary(Document doc, Locale locale, String snippet, PortletURL url) {

		try {
			Course course = CourseLocalServiceUtil.getCourse(
				GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			String title = course.getTitle(locale);
			String content = snippet;
			if (Validator.isNull(content)) {
				content = course.getDescription();
			}
			return new Summary(title, content, url);
		}
		catch (NoSuchCourseException ce) {
			// somehow the index became corrupted, manually remove the course
			doDelete(doc);
			_log.warn(ce.getMessage());
		}
		catch (Exception e) {
			_log.error(e);
		}

		return new Summary("", "", url);
	}


	@Override
	protected void doDelete(Object o)
		throws Exception {

		Course course = (Course) o;
		Document doc = new DocumentImpl();
		doc.addUID(PORTLET_ID, course.getCourseId());
		SearchEngineUtil.deleteDocument(
			course.getCompanyId(), doc.get(Field.UID));
	}

	protected void doDelete(Document doc) {
		try {
			SearchEngineUtil.deleteDocument(
				Long.valueOf(doc.get(Field.COMPANY_ID)), doc.get(Field.UID));
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}
	}

	@Override
	protected Document doGetDocument(Object o)
		throws Exception {

		Course course = (Course) o;

		long companyId = course.getCompanyId();
		long scopeGroupId = course.getGroupId();
		long groupId = getParentGroupId(scopeGroupId);
		long userId = course.getUserId();
		long courseId = course.getCourseId();

		long[] assetCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(
			Course.class.getName(), courseId);
		List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getCategories(
			Course.class.getName(), courseId);
		String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			Course.class.getName(), courseId);

		Document doc = new DocumentImpl();
		doc.addUID(PORTLET_ID, courseId);
        doc.addDate("createDate", course.getCreateDate());
		doc.addDate("modified", course.getUpdatedDate());
		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, groupId);
		doc.addKeyword(Field.SCOPE_GROUP_ID, scopeGroupId);
		doc.addKeyword(Field.USER_ID, userId);
		doc.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		doc.addKeyword(Field.ASSET_TAG_NAMES, assetTagNames);
		doc.addKeyword(Field.ENTRY_CLASS_NAME, Course.class.getName());
		doc.addKeyword(Field.ENTRY_CLASS_PK, courseId);
		doc.addKeyword(Field.URL, course.getUrl());
		doc.addKeyword(NterKeys.POPULARITY, course.getPopularWeight());
		doc.addKeyword(NterKeys.COURSE_IRI, course.getCourseIri());

        doc.addText(Field.TITLE, convertLocalizedText(course.getTitleMap()));
        doc.addText(Field.DESCRIPTION, convertLocalizedText(course.getDescriptionMap()));

        Map<Locale, String> categoryTitleMap = new HashMap<Locale, String>();
		for (AssetCategory assetCategory : assetCategories) {
			categoryTitleMap.putAll(assetCategory.getTitleMap());
		}

		doc.addText(NterKeys.OWNER_NAME, course.getOwnerName(companyId));

        List<Contributor> contributors = course.getContributors();
        String contributorNames[] = new String[contributors.size()];
        for (int i = 0; i < contributors.size(); i++) {
            contributorNames[i] = contributors.get(i).getContributorName();
        }

        doc.addText(NterKeys.CONTRIBUTOR_NAME, contributorNames);

		ExpandoBridge expandoBridge = course.getExpandoBridge();
		ExpandoBridgeIndexerUtil.addAttributes(doc, expandoBridge);

		return doc;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

        String ownerName = searchContext.getKeywords();
        // ensure an owner name is present, and that it does not represent a
        // field query (field:keyword)
		if (Validator.isNotNull(ownerName) && (!ownerName.contains(":"))) {
			if (searchContext.isAndSearch()) {
				searchQuery.addRequiredTerm(
					NterKeys.OWNER_NAME, ownerName, true);
			}
			else {
				searchQuery.addTerm(NterKeys.OWNER_NAME, ownerName, true);
			}
		}
	}

	@Override
	protected void doReindex(Object o)
		throws Exception {

		Course course = (Course) o;

		if (course.isIndexable()) {
			Document doc = getDocument(course);
			SearchEngineUtil.updateDocument(getSearchEngineId(), course.getCompanyId(), doc);
		}
		else {
			delete(course);
		}
	}

	@Override
	protected void doReindex(String s, long l)
		throws Exception {

		Course course = CourseLocalServiceUtil.getCourse(l);
		doReindex(course);
	}

	@Override
	protected void doReindex(String[] ids)
		throws Exception {
		for (String id : ids) {
			long companyId = GetterUtil.getLong(id);
			List<Course> courses = CourseLocalServiceUtil.findByCompanyId(companyId);

			for (Course course : courses) {
				doReindex(course);
			}
		}
	}

    @Override
    protected void addSearchKeywords(BooleanQuery searchQuery, SearchContext searchContext)
            throws Exception {

        String keywords = searchContext.getKeywords();
        if (Validator.isNull(keywords)) {
            return;
        }

        searchQuery.addTerms(COURSE_KEYWORDS_FIELDS, keywords);
    }


    private String convertLocalizedText(Map<Locale, String> text) {
        StringBuffer fullText = new StringBuffer();
        for (Map.Entry<Locale, String> entry : text.entrySet()) {
            fullText.append(entry.getValue());
            fullText.append(StringPool.SPACE);
        }

        return fullText.toString();
    }

	private static final Log _log = LogFactoryUtil.getLog(CourseIndexer.class);
}