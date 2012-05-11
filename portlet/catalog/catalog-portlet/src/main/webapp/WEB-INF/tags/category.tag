<%--
  National Training and Education Resource (NTER)
  Copyright (C) 2012 SRI International

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
  --%>

<%
/**
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
%>

<%-- category.tag --%>

<%@tag import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ tag import="com.liferay.portal.kernel.exception.SystemException" %>
<%@ tag import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ tag import="com.liferay.portal.kernel.util.Validator" %>
<%@ tag import="com.liferay.portal.model.Group" %>
<%@ tag import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>
<%@ tag import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ tag import="com.liferay.portlet.asset.model.AssetVocabulary" %>
<%@ tag import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %>
<%@ tag import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil" %>
<%@ tag	import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil" %>
<%@ tag	import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>
<%@ tag import="java.util.ArrayList" %>
<%@ tag import="java.util.Collections" %>
<%@ tag import="java.util.List" %>

<%@ attribute name="className" required="true" %>
<%@ attribute name="classPK" type="java.lang.Long" required="true" %>
<%@ attribute name="languageId" required="false" %>

<portlet:defineObjects/>
<liferay-theme:defineObjects/>

<%

	if (Validator.isNotNull(languageId)) {
		locale = LocaleUtil.fromLanguageId(languageId);
	}
	List<AssetVocabulary> vocabularies = CourseLocalServiceUtil.findAllAssetVocabularies();
	List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories(className, classPK);
%>

<%
	for (AssetVocabulary vocabulary : vocabularies) {

		// verify the vocabulary is COURSE type
		try {
			String vocabularyType = (String) ExpandoValueLocalServiceUtil.getData(vocabulary.getCompanyId(),
					AssetVocabulary.class.getName(), "AssetVocabulary", "vocabularyType", vocabulary.getPrimaryKey());

			if (vocabularyType.equals("COURSE")) {

				List<AssetCategory> curCategories = _filterCategories(categories, vocabulary);
				if (!curCategories.isEmpty()) {
%>
<ul class="asset-categories">
	<%
		Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
		for (AssetCategory category : curCategories) {
			AssetVocabulary categoryVocabulary = AssetVocabularyLocalServiceUtil.getVocabulary(
					category.getVocabularyId());
			String vocabularyTitle = categoryVocabulary.getTitle(locale);
			String vocabularyHref = PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/courses?vocabulary=" + categoryVocabulary.getVocabularyId();
			String categoryHref = PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/courses?category=" + category.getCategoryId();

	%>
	<li class="asset-category"><span class="vocabulary-title"><a
			href="<%=vocabularyHref%>"><%=vocabularyTitle%>
	</a>
	</span> <%
		List<AssetCategory> ancestorCategories = category.getAncestors();

		if (!ancestorCategories.isEmpty()) {
			Collections.reverse(ancestorCategories);
			for (AssetCategory ancestorCategory : ancestorCategories) {
				String ancestorCategoryHref = PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/courses?category=" + ancestorCategory.getCategoryId();
	%> <span class="vocabulary-ancestor"><a
			href="<%=ancestorCategoryHref%>"><%=ancestorCategory.getTitle(locale)%>
	</a>
	</span> <%
			}
		}
	%> <span class="vocabulary-category"><a
			href="<%=categoryHref%>"><%=category.getTitle(locale)%>
	</a>
	</span></li>
	<%
		}
	%>
</ul>
<%
				}
			}
		} catch (PortalException e) {
			// no expando vocabulary definition found, do not display
		} catch (SystemException e) {
			// This vocabulary won't get printed, but don't cause the whole page to blow up
		}
	}
%>

<%!
	private List<AssetCategory> _filterCategories(List<AssetCategory> categories, AssetVocabulary vocabulary) {

		List<AssetCategory> filteredCategories = new ArrayList<AssetCategory>();

		for (AssetCategory category : categories) {
			if (category.getVocabularyId() == vocabulary.getVocabularyId()) {
				filteredCategories.add(category);
			}
		}

		return filteredCategories;
	}
%>