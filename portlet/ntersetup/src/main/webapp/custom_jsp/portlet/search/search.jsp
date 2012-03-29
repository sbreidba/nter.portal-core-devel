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
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
String primarySearch = ParamUtil.getString(request, "primarySearch");

/*if (Validator.isNotNull(primarySearch)) {
	portalPrefs.setValue(PortletKeys.SEARCH, "primary-search", primarySearch);
}
else {
	primarySearch = portalPrefs.getValue(PortletKeys.SEARCH, "primary-search", StringPool.BLANK);
}*/

long groupId = ParamUtil.getLong(request, "groupId");

Group group = themeDisplay.getScopeGroup();

String keywords = ParamUtil.getString(request, "keywords");

String format = ParamUtil.getString(request, "format");

List<Portlet> portlets = PortletLocalServiceUtil.getPortlets(company.getCompanyId(), includeSystemPortlets, false);

portlets = ListUtil.sort(portlets, new PortletTitleComparator(application, locale));

Iterator itr = portlets.iterator();
List<Portlet> filtercats = new ArrayList<Portlet>();

List<String> portletTitles = new ArrayList<String>();

while (itr.hasNext()) {
	Portlet portlet = (Portlet)itr.next();

	if (Validator.isNull(portlet.getOpenSearchClass())) {
		itr.remove();

		continue;
	}

	OpenSearch openSearch = portlet.getOpenSearchInstance();

	if (!openSearch.isEnabled()) {
		itr.remove();

		continue;
	}

	if (groupId != 0) {
		long curPlid = PortalUtil.getPlidFromPortletId(groupId, portlet.getPortletId());

		if (!PortletPermissionUtil.contains(permissionChecker, curPlid, portlet, ActionKeys.VIEW)) {
			itr.remove();

			continue;
		}
	}
	
	filtercats.add(portlet);
	
	if (primarySearch != null && !primarySearch.equals("") && !primarySearch.equals(portlet.getOpenSearchClass())) {
		itr.remove();
		continue;
	}

	portletTitles.add(PortalUtil.getPortletTitle(portlet, application, locale));
}

if (Validator.isNotNull(primarySearch)) {
	for (int i = 0; i < portlets.size(); i++) {
		Portlet portlet = (Portlet)portlets.get(i);

		if (portlet.getOpenSearchClass().equals(primarySearch)) {
			if (i != 0) {
				portlets.remove(i);
				portlets.add(0, portlet);
			}

			break;
		}
	}
}

LinkedHashMap groupParams = new LinkedHashMap();

groupParams.put("active", Boolean.FALSE);

int inactiveGroupsCount = GroupLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, null, groupParams);

String searchPage = PrefsPropsUtil.getString(PropsKeys.COMPANY_DEFAULT_HOME_URL) + "/search";
%>

<portlet:renderURL var="searchURL">
	<portlet:param name="struts_action" value="/search/search" />
</portlet:renderURL>

<form action='<%=searchPage%>' method="get" name="fm" role="search">
	<input name="format" type="hidden" value="<%= format %>" />

	<label for="<portlet:namespace />keywords"><liferay-ui:message key="search" /></label>
	<input name="<portlet:namespace />keywords" size="30" value="<%= keywords %>" type="text" />

	<select name="<portlet:namespace />groupId">
		<option value="0" <% if (groupId == 0) { %>selected<% } %>><liferay-ui:message key="everything" /></option>
		<option value="<%= group.getGroupId() %>" <% if (groupId != 0) { %>selected<% } %>><%= "this-" + (group.isOrganization() ? "organization" : "community") %></option>
	</select>
	
	<input type="hidden" name="p_p_id" value="<%= PortletKeys.SEARCH %>" />
	<input type="hidden" name="<portlet:namespace />struts_action" value="/search/search" />

	<input value="<%= LanguageUtil.get(pageContext, "search") %>" type="submit" />
</form>

<div class="search-filter">
	<section>
		<ul class="types">
			<portlet:renderURL var="everythingUrl">
				<portlet:param name="struts_action" value="/search/search" />
				<portlet:param name="primarySearch" value="" />
				<portlet:param name="keywords" value="<%= HtmlUtil.escape(keywords) %>" />
				<portlet:param name="format" value="<%= format %>" />
			</portlet:renderURL>
			<li <% if (primarySearch == null || primarySearch.equals("")) { %>class="current"<% } %>><a href="<%= everythingUrl %>">Everything</a></li>
			<% for (int i = 0; i < filtercats.size(); i++) {
				Boolean current = false;
				Portlet portlet = filtercats.get(i);
				if (primarySearch.equals(portlet.getOpenSearchClass())) current = true;
				%>
				<portlet:renderURL var="filteredUrl">
					<portlet:param name="struts_action" value="/search/search" />
					<portlet:param name="primarySearch" value="<%= portlet.getOpenSearchClass() %>" />
					<portlet:param name="keywords" value="<%= HtmlUtil.escape(keywords) %>" />
					<portlet:param name="format" value="<%= format %>" />
				</portlet:renderURL>
			<li <% if (current) { %>class="current"<% } %>><a href="<%= filteredUrl %>"><%= PortalUtil.getPortletTitle(portlet, application, locale) %></a></li>
			<% } %>
		</ul>
	</section>
</div>

<%
int totalResults = 0;

for (int i = 0; i < portlets.size(); i++) {
	Portlet portlet = (Portlet)portlets.get(i);

	OpenSearch openSearch = portlet.getOpenSearchInstance();

	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/search/search");
	portletURL.setParameter("keywords", keywords);
	portletURL.setParameter("format", format);

	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM + i, 5, portletURL, null, LanguageUtil.format(pageContext, "no-results-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(keywords) + "</strong>"));

	if (Validator.isNotNull(primarySearch) && portlet.getOpenSearchClass().equals(primarySearch)) {
		searchContainer.setDelta(SearchContainer.DEFAULT_DELTA);
	}
	
	String portletId = portlet.getPortletId();
		
	String portletTitle = PortalUtil.getPortletTitle(portlet, application, locale);

	try {
		String xml = openSearch.search(request, groupId, 0, keywords, searchContainer.getCur(), searchContainer.getDelta(), format);

		xml = XMLFormatter.stripInvalidChars(xml);

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		String[] queryTerms = StringUtil.split(root.elementText("queryTerms"), StringPool.COMMA_AND_SPACE);

		List<Element> entries = root.elements("entry");

		int total = GetterUtil.getInteger(root.elementText(OpenSearchUtil.getQName("totalResults", OpenSearchUtil.OS_NAMESPACE)));

		if (entries.size() > 0) {
		%>
		<section class="search-listing">
			<h1 class="main-page-heading"><%= portletTitle %></h1>
			
			<ul>
		<%
		}

		for (int j = 0; j < entries.size(); j++) { 
			Element el = (Element)entries.get(j);

			ResultRow row = new ResultRow(doc, String.valueOf(j), j);

			// Summary

			String entryClassName = el.elementText("entryClassName");
			String entryTitle = el.elementText("title");
			String entryHref = el.element("link").attributeValue("href");
			String summary = el.elementText("summary");
			String rating = el.elementText("ratings");

			// Group id

			long entryGroupId = GetterUtil.getLong(el.elementText(OpenSearchUtil.getQName("groupId", OpenSearchUtil.LIFERAY_NAMESPACE)));

			if (Validator.isNotNull(entryGroupId) && (inactiveGroupsCount > 0)) {
				Group entryGroup = GroupServiceUtil.getGroup(entryGroupId);

				if (!entryGroup.isActive()) {
					total--;

					continue;
				}
			}

			//// MODIFIED bblonski
			// Localize courses display
			if (portletId.equals("coursesearch_WAR_courseportlet")) {
				entryTitle = LocalizationUtil.getLocalization(entryTitle, LanguageUtil.getLanguageId(locale));
				summary = LocalizationUtil.getLocalization(summary, LanguageUtil.getLanguageId(locale));
			}
			//// END MODIFIED

			if (portletId.equals(PortletKeys.DOCUMENT_LIBRARY) || (portletId.equals(PortletKeys.SEARCH) && entryClassName.equals(DLFileEntry.class.getName()))) {
				long folderId = GetterUtil.getLong(HttpUtil.getParameter(entryHref, "_20_folderId", false));
				String name = GetterUtil.getString(HttpUtil.getParameter(entryHref, "_20_name", false));

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(entryGroupId, folderId, name);

				entryTitle = fileEntry.getTitle();

				if (portletId.equals(PortletKeys.SEARCH)) {
					entryTitle = PortalUtil.getPortletTitle(PortletKeys.DOCUMENT_LIBRARY, locale) + " " + CharPool.RAQUO + " " + entryTitle;
				}

				if (dlLinkToViewURL) {
					long dlPlid = PortalUtil.getPlidFromPortletId(fileEntry.getGroupId(), PortletKeys.DOCUMENT_LIBRARY);

					PortletURL viewURL = new PortletURLImpl(request, PortletKeys.DOCUMENT_LIBRARY, dlPlid, PortletRequest.RENDER_PHASE);

					viewURL.setParameter("struts_action", "/document_library/view_file_entry");
					viewURL.setParameter("redirect", currentURL);
					viewURL.setParameter("folderId", String.valueOf(fileEntry.getFolderId()));
					viewURL.setParameter("name", HtmlUtil.unescape(name));

					entryHref = viewURL.toString();
				}
			}

			// Tags

			StringBundler tagSB = new StringBundler();

			String tagsString = el.elementText("tags");

			tagsString = tagsString.replaceAll("[\\[\\]]","");

			String[] tags = StringUtil.split(tagsString);

			String[] tagsQueryTerms = queryTerms;

			if (StringUtil.startsWith(keywords, Field.ASSET_TAG_NAMES + StringPool.COLON)) {
				tagsQueryTerms = new String[] {StringUtil.replace(keywords, Field.ASSET_TAG_NAMES + StringPool.COLON, StringPool.BLANK)};
			}

			for (int k = 0; k < tags.length; k++) {
				String tag = tags[k];

				String newKeywords = tag.trim();

				if (newKeywords.matches(".+\\s.+")) {
					newKeywords = StringPool.QUOTE + tag + StringPool.QUOTE;
				}

				PortletURL tagURL = PortletURLUtil.clone(portletURL, renderResponse);

				tagURL.setParameter("keywords", Field.ASSET_TAG_NAMES + StringPool.COLON + newKeywords);
				tagURL.setParameter("format", format);

				if (k == 0) {
					tagSB.append("<div class=\"entry-tags\">");
					tagSB.append("<div class=\"taglib-asset-tags-summary\">");
				}

				tagSB.append("<a class=\"tag\" href=\"");
				tagSB.append(tagURL.toString());
				tagSB.append("\">");
				tagSB.append(StringUtil.highlight(tag, tagsQueryTerms));
				tagSB.append("</a>");

				if ((k + 1) == tags.length) {
					tagSB.append("</div>");
					tagSB.append("</div>");
				}
			}
		%>
		
		<c:choose>
			<c:when test='<%= portletId.equals("coursemanagementcontrolpanel_WAR_courseportlet") %>'>
				<li class="item has-photo course">
					<!-- course -->
					<a href="<%= entryHref %>"><img src="/nter-theme/images/default_images/course_default_small.jpg" class="course-mini-thumbnail" alt="image alt text" /></a>
					<div class="details">
						<h3><a href="<%= entryHref %>"><%= StringUtil.highlight(HtmlUtil.escape(entryTitle), queryTerms) %></a></h3>
						<!--<div class="origin">Org Name in Category, Category</div>-->
						<c:if test="<%= Validator.isNotNull(summary) %>">
						<div class="description"><%= StringUtil.highlight(HtmlUtil.escape(summary), queryTerms) %></div>
						</c:if>
						<c:if test="<%= Validator.isNotNull(rating) %>">
						<liferay-ui:ratings-score score="<%= Double.parseDouble(rating) %>" />
						</c:if>
					</div>
				</li>
			</c:when>
			<c:when test='<%= portletId.equals(PortletKeys.JOURNAL) || (portletId.equals(PortletKeys.SEARCH) && entryClassName.equals(JournalArticle.class.getName())) %>'>
				<li class="item">
				<%
					String articleId = el.elementText(OpenSearchUtil.getQName(Field.ENTRY_CLASS_PK, OpenSearchUtil.LIFERAY_NAMESPACE));

					JournalArticle article = JournalArticleLocalServiceUtil.getArticle(entryGroupId, articleId);

					if (DateUtil.compareTo(article.getDisplayDate(), new Date()) > 0) {
						total--;

						continue;
					}
				%>
					<h3><a href="<%= entryHref %>" target="_blank"><%= StringUtil.highlight(HtmlUtil.escape(entryTitle), queryTerms) %></a></h3>

					<c:if test="<%= Validator.isNotNull(summary) %>">
					<div class="description"><%= StringUtil.highlight(HtmlUtil.escape(summary), queryTerms) %></div>
					</c:if>

					<div class="origin"><%= tagSB %></div>
				</li>
			</c:when>
			<c:otherwise>
				<li class="item">
					<h3><a href="<%= entryHref %>"><%= StringUtil.highlight(HtmlUtil.escape(entryTitle), queryTerms) %></a></h3>

					<c:if test="<%= Validator.isNotNull(summary) %>">
					<div class="description"><%= StringUtil.highlight(HtmlUtil.escape(summary), queryTerms) %></div>
					</c:if>

					<div class="origin"><%= tagSB %></div>
				</li>
			</c:otherwise>
		</c:choose>
		
		<% } 
		
		searchContainer.setTotal(total);
		%>

		<% if (searchContainer.getTotal() > 0) { %>
			</ul>
			
			<%
			totalResults = totalResults + searchContainer.getTotal();
			%>

			<c:choose>
				<c:when test='<%= (searchContainer.getTotal() <= searchContainer.getDelta()) || (Validator.isNotNull(primarySearch) && portlet.getOpenSearchClass().equals(primarySearch)) %>'>
					<div class="search-paginator-container">
						<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="more-results">
						<portlet:renderURL var="moreResultsURL">
							<portlet:param name="struts_action" value="/search/search" />
							<portlet:param name="primarySearch" value="<%= portlet.getOpenSearchClass() %>" />
							<portlet:param name="keywords" value="<%= HtmlUtil.escape(keywords) %>" />
							<portlet:param name="format" value="<%= format %>" />
						</portlet:renderURL>

						<aui:a href="<%= moreResultsURL %>"><%= LanguageUtil.format(pageContext, "more-x-results", portletTitle) %> &raquo;</aui:a>
					</div>
				</c:otherwise>
			</c:choose>
		</section>
		<% } %>
	<% }
	catch (Exception e) {
		_log.error("Error displaying content of type " + portlet.getOpenSearchClass() + ": " + e);
	}
%>

<%
}
%>



<c:if test='<%= totalResults == 0 %>'>
	<div class="no-results">
		<%= LanguageUtil.format(pageContext, "no-results-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(keywords) + "</strong>") %>
	</div>
</c:if>

<aui:script>
	function <portlet:namespace />search() {
		var keywords = document.<portlet:namespace />fm.<portlet:namespace />keywords.value;

		keywords = keywords.replace(/^\s+|\s+$/, '');

		if (keywords != '') {
			document.<portlet:namespace />fm.submit();
		}
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />keywords);
	</c:if>
</aui:script>

<%
String pageSubtitle = LanguageUtil.get(pageContext, "search-results");
String pageDescription = LanguageUtil.get(pageContext, "search-results");
String pageKeywords = LanguageUtil.get(pageContext, "search");

if (!portletTitles.isEmpty()) {
	pageDescription = LanguageUtil.get(pageContext, "searched") + StringPool.SPACE + StringUtil.merge(portletTitles, StringPool.COMMA_AND_SPACE);
}

if (Validator.isNotNull(keywords)) {
	pageKeywords = keywords;

	if (StringUtil.startsWith(pageKeywords, Field.ASSET_TAG_NAMES + StringPool.COLON)) {
		pageKeywords = StringUtil.replace(pageKeywords, Field.ASSET_TAG_NAMES + StringPool.COLON, StringPool.BLANK);
	}
}

PortalUtil.setPageSubtitle(pageSubtitle, request);
PortalUtil.setPageDescription(pageDescription, request);
PortalUtil.setPageKeywords(pageKeywords, request);
%>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.search.search.jsp");
%>