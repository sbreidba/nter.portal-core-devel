<%--
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
  --%>

<%@ include file="/unified-search/jsp/init.jsp" %>

<%
	long groupId = ParamUtil.getLong(request, "groupId");

	// GET PARAMETERS
	String primarySearch = GetterUtil.getString(request.getParameter("primarySearch"));
	HttpServletRequest httpRequest =
		PortalUtil.getOriginalServletRequest(request);
	if(Validator.isNull(primarySearch)) {
		primarySearch = GetterUtil.getString(httpRequest.getParameter("primarySearch"));
	}
	String keywords;
	if (Validator.isNotNull(httpRequest.getParameter("keywords"))) {
		keywords = HtmlUtil.unescape(httpRequest.getParameter("keywords"));
	}
	else if (Validator.isNotNull(request.getParameter("keywords"))) {
		keywords = HtmlUtil.unescape(request.getParameter("keywords"));
	}
	else {
		keywords = StringPool.BLANK;
	}
	String format = ParamUtil.getString(request, "format");

	// Get list of searchable portlets
	List<Portlet> filtercats = SearchUtil.getSearchablePortlets(company,
            application, locale, permissionChecker, groupId, primarySearch, 
            includeSystemPortlets);

	List<Portlet> portlets = new ArrayList<Portlet>();
	if(Validator.isNotNull(primarySearch)) {
		for(Portlet portlet : filtercats) {
			if(StringUtils.equals(primarySearch, portlet.getOpenSearchClass())) {
				portlets.add(portlet);
			}
            else if((StringUtils.equals(primarySearch, CourseOpenSearchImpl.class.getName()) ||
                     StringUtils.equals(primarySearch, ComponentOpenSearchImpl.class.getName())) &&
					StringUtils.equals(portlet.getPortletId(), NterKeys.EXTERNAL_SEARCH_PORTLET)) {
				portlets.add(portlet);
			}
		}
	}else {
		portlets = new ArrayList<Portlet>(filtercats);
	}

	LinkedHashMap groupParams = new LinkedHashMap();

	groupParams.put("active", Boolean.FALSE);

	int inactiveGroupsCount =
		GroupLocalServiceUtil.searchCount(
			themeDisplay.getCompanyId(), null, null, groupParams);

	// we should be using renderResponse.createElement (http://blogs.oracle.com/deepakg/entry/setting_markup_head_elements_in)
	// but it doesn't seem to work in all circumstances, e.g. maximized portlets
	Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	List meta = new ArrayList();
	if (oldMeta != null) meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	meta.add("<meta name=\"robots\" content=\"noindex\" />");
	httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);
%>

<portlet:renderURL var="searchURL">
</portlet:renderURL>

<form action="<%=searchURL%>" method="get" name="fm" role="search"
	class="main-search-form">
	<h3 class="main-page-heading label-heading">
		<label for="<portlet:namespace />search">
            <liferay-ui:message key="search" />
		</label>
	</h3>

	<input id="<portlet:namespace />search" name="keywords" type="text"
	       value="<%=HtmlUtil.escapeAttribute(keywords) %>" />
	<input name="primarySearch" type="hidden" value="<%=primarySearch%>" />
	<input value="<liferay-ui:message key="search" />" class="submit" type="submit" />
</form>

<div class="search-filter">
	<section>
	<ul class="types">
		<portlet:renderURL var="everythingUrl">
			<portlet:param name="keywords" value="<%= HtmlUtil.escape(keywords) %>" />
			<portlet:param name="format" value="<%= format %>" />
		</portlet:renderURL>

		<li <%if (Validator.isNull(primarySearch)) {%>
			class="current" <%}%>><a href="<%=everythingUrl%>">Everything</a>
		</li>

		<%
			for (int i = 0; i < filtercats.size(); i++) {
				Boolean current = false;
				Portlet portlet = filtercats.get(i);

				if(portlet.getPortletId().equals(NterKeys.EXTERNAL_SEARCH_PORTLET))
					continue;

				if (StringUtils.equals(primarySearch, portlet.getOpenSearchClass()))
					current = true;
		%>
		<portlet:renderURL var="filteredUrl">
			<portlet:param name="primarySearch" value="<%= portlet.getOpenSearchClass() %>" />
			<portlet:param name="keywords" value="<%= HtmlUtil.escape(keywords) %>" />
			<portlet:param name="format" value="<%= format %>" />
		</portlet:renderURL>

		<li <%if (current) {%> class="current" <%}%>>
            <a href="<%=filteredUrl%>"><%=PortalUtil.getPortletTitle(portlet, application, locale) %></a></li>
		<%
			}
		%>
	</ul>
	</section>
</div>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	SearchContainer globalSearchContainer = SearchUtil.getGlobalSearchContainer(renderRequest, portletURL,
			pageContext, keywords);
	portletURL = globalSearchContainer.getIteratorURL();
	portletURL.setParameter("keywords", keywords);
	portletURL.setParameter("primarySearch", primarySearch);
	portletURL.setParameter("format", format);
	globalSearchContainer.setDeltaConfigurable(true);
	FederatedSearchManager searchManager = new FederatedSearchManager(portlets, groupId, portletURL, format,
			pageContext);
	searchManager.setKeywords(keywords);
	searchManager.setSearchDelta(globalSearchContainer.getDelta());
	List<OpenSearchResult> displayResults = searchManager.getPageResults(request, globalSearchContainer.getCur());
	globalSearchContainer.setTotal(searchManager.getTotalResultsCount());
	globalSearchContainer.getResultRows().addAll(displayResults);

	if (Validator.isNotNull(keywords)) {
%>

<section class="search-listing">
<ul>
	<%
		for (OpenSearchResult result : displayResults) {
            %>
            <nter:search_result searchResult="<%= result%>" />
            <%
		}
	%>
</ul>

<c:choose>
	<c:when test="<%= globalSearchContainer.getTotal() > 0 %>">
		<div class="search-paginator-container">
			<liferay-ui:search-paginator searchContainer="<%=globalSearchContainer%>" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="no-results">
			<%=LanguageUtil.format(
							pageContext,
							"no-results-were-found-that-matched-the-keywords-x",
							"<strong>" + keywords +
								"</strong>") %>
		</div>
	</c:otherwise>
</c:choose>
</section>
<%
	}
%>

<aui:script>
	function <portlet:namespace />search() {
		var keywords = document.<portlet:namespace />fm.<portlet:namespace />keywords.value;

		keywords = keywords.replace(/^\s+|\s+$/, '');

		if (keywords != '') {
			document.<portlet:namespace />fm.submit();
		}
	}
</aui:script>

<%
	if (Validator.isNotNull(keywords)) {
		PortalUtil.addPageSubtitle(keywords, request);
	}
%>

<%!	private static Log _log =
		LogFactoryUtil.getLog("portal-web.docroot.html.portlet.search.search.jsp");%>