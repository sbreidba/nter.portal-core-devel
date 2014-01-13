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
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.NoSuchComponentException;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Contributor;
import org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil;

import javax.portlet.PortletURL;
import java.util.List;
import java.util.Locale;

public class ComponentIndexer extends BaseIndexer {

    private static final String[] CLASS_NAMES = {Component.class.getName()};
    private static final String PORTLET_ID = NterKeys.COMPONENT_SEARCH_PORTLET;

    private static final String[] COMPONENT_KEYWORD_FIELDS = {
            Field.ASSET_TAG_NAMES,
            Field.COMMENTS,
            Field.CONTENT,
            Field.DESCRIPTION,
            Field.PROPERTIES,
            Field.TITLE,
            NterKeys.CONTRIBUTOR_NAME
    };

    private static final Log mLog = LogFactoryUtil.getLog(ComponentIndexer.class);

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
    public Summary doGetSummary(Document document, Locale locale, String snippet, PortletURL portletURL) {
        try {
            Component component =
                    ComponentLocalServiceUtil.getComponent(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            String title = component.getTitle();
            String content = snippet;

            if (Validator.isNull(content)) {
                content = component.getDescription();
            }

            return new Summary(title, content, portletURL);
        }
        catch (NoSuchComponentException ce) {
            // somehow the index became corrupted, manually delete doc
            doDelete(document);
            mLog.warn(ce.getMessage());
        }
        catch (Exception e) {
            mLog.error(e);
        }

        return new Summary("", "", portletURL);
    }


    @Override
    protected void doDelete(Object obj) throws Exception {
        Component component = (Component) obj;
        Document doc = new DocumentImpl();
        doc.addUID(PORTLET_ID, component.getComponentId());
        SearchEngineUtil.deleteDocument(component.getCompanyId(), doc.get(Field.UID));
    }


    protected void doDelete(Document doc) {
        try {
			SearchEngineUtil.deleteDocument(
				Long.valueOf(doc.get(Field.COMPANY_ID)), doc.get(Field.UID));
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    @Override
    protected Document doGetDocument(Object obj) throws Exception {

        Component component = (Component) obj;
        long componentId = component.getComponentId();

        Company company = CompanyLocalServiceUtil.getCompany(component.getCompanyId());
        Group defaultGroup = company.getGroup();

        long[] assetCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(
                                    Component.class.getName(), componentId);
        String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
                                    Component.class.getName(), componentId);
        List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getCategories(
                                    Component.class.getName(), componentId);

        StringBuffer localeCategoryTitles = new StringBuffer(StringPool.BLANK);
        for (AssetCategory assetCategory : assetCategories) {
            localeCategoryTitles.append(assetCategory.getTitle());
        }

        Document doc = new DocumentImpl();
        doc.addUID(PORTLET_ID, componentId);
        doc.addDate("createDate", component.getCreateDate());
        doc.addDate("modified", component.getUpdatedDate());
        doc.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        doc.addKeyword(Field.ASSET_TAG_NAMES, assetTagNames);
        doc.addKeyword(Field.COMPANY_ID, component.getCompanyId());
        doc.addKeyword(NterKeys.COMPONENT_IRI, component.getComponentIri());
        doc.addKeyword(Field.ENTRY_CLASS_NAME, Component.class.getName());
        doc.addKeyword(Field.ENTRY_CLASS_PK, componentId);
        doc.addKeyword(Field.GROUP_ID, defaultGroup.getGroupId());
        doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
        doc.addKeyword(Field.SCOPE_GROUP_ID, defaultGroup.getGroupId());
        doc.addKeyword(Field.USER_ID, company.getDefaultUser().getUserId());
        doc.addKeyword(Field.URL, component.getUrl());

        doc.addText(Field.COMMENTS, localeCategoryTitles.toString());
        doc.addText(Field.DESCRIPTION, component.getDescription());
        doc.addText(Field.TITLE, component.getTitle());

        List<Contributor> contributors = component.getContributors();
        String contributorNames[] = new String[contributors.size()];
        for (int i = 0; i < contributors.size(); i++) {
            contributorNames[i] = contributors.get(i).getContributorName();
        }

        doc.addText(NterKeys.CONTRIBUTOR_NAME, contributorNames);

        ExpandoBridge expandoBridge = component.getExpandoBridge();
        ExpandoBridgeIndexerUtil.addAttributes(doc, expandoBridge);

        return doc;
    }


    @Override
    protected void doReindex(Object obj) throws Exception {
        Component component = (Component)obj;

        if (!component.isRemoved()) {
            Document doc = getDocument(component);
            SearchEngineUtil.updateDocument(component.getCompanyId(), doc);
        }
    }


    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        Component component = ComponentLocalServiceUtil.getComponent(classPK);
        doReindex(component);
    }


    @Override
    protected void doReindex(String[] companyIds) throws Exception {
        for (String id : companyIds) {
            long companyId = GetterUtil.getLong(id);

            for (Component component :
                    ComponentLocalServiceUtil.findByCompanyId(companyId)) {
                doReindex(component);
            }
        }
    }


    @Override
    protected void addSearchKeywords(BooleanQuery searchQuery,
                                     SearchContext searchContext)
            throws Exception {

        String keywords = searchContext.getKeywords();
        if (Validator.isNull(keywords)) {
            return;
        }

        searchQuery.addTerms(COMPONENT_KEYWORD_FIELDS, keywords);
    }


    @Override
    public void postProcessSearchQuery(BooleanQuery searchQuery,
                                          SearchContext searchContext)
            throws Exception {

        String ownerName = searchContext.getKeywords();
        // ensure an ownername is present, and that it does not represent a
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

        addSearchTerm(searchQuery, searchContext, Field.TITLE, true);
        addSearchTerm(searchQuery, searchContext, Field.DESCRIPTION, true);
    }
}
