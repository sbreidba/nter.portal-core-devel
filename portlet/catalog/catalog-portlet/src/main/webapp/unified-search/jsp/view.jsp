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
	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
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

    // ensure that if a starting quote is present, that a closing quote is found too
    // if not, remove the quote
    if (keywords.contains("\"")) {
        int quoteIndex = keywords.indexOf("\"");
        if (keywords.indexOf("\"", quoteIndex + 1) == -1) {
            keywords = keywords.substring(0, quoteIndex) + keywords.substring(quoteIndex +1);
        }
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
	}
    else {
		portlets = new ArrayList<Portlet>(filtercats);
	}

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

<form action="<%= searchURL %>" method="get" name="fm" role="search" class="main-search-form">
	<h3 class="main-page-heading label-heading">
		<label for="<portlet:namespace />search">
            <liferay-ui:message key="search" />
		</label>
	</h3>

	<input id="<portlet:namespace />search" name="keywords" type="text"
	       value="<%= HtmlUtil.escapeAttribute(keywords) %>" />
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

		<li <% if (Validator.isNull(primarySearch)) {%>class="current" <%}%>>
            <a href="<%= everythingUrl %>">Everything</a>
		</li>

		<%
			for (int i = 0; i < filtercats.size(); i++) {
				Boolean current = false;
				Portlet portlet = filtercats.get(i);

				if (portlet.getPortletId().equals(NterKeys.EXTERNAL_SEARCH_PORTLET))
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

    int currentPage = GetterUtil.getInteger(request.getParameter("cur"), 1);
    int displayDelta = 10;
    // query the indexes in batches of 100 results
    int searchDelta = 100 * ((int)(currentPage / displayDelta) + 1);

	PortletURL portletURL = renderResponse.createRenderURL();

	SearchContainer globalSearchContainer =
            SearchUtil.getGlobalSearchContainer(renderRequest, portletURL, pageContext, keywords);
    globalSearchContainer.setDeltaConfigurable(true);
    globalSearchContainer.setDelta(displayDelta);

    portletURL = globalSearchContainer.getIteratorURL();
	portletURL.setParameter("keywords", keywords);
	portletURL.setParameter("primarySearch", primarySearch);
	portletURL.setParameter("format", format);

	FederatedSearchManager searchManager = new FederatedSearchManager(portlets, groupId,
            portletURL, format, pageContext);
    searchManager.setSearchDelta(searchDelta);
	searchManager.setKeywords(keywords);

    List<OpenSearchResult> displayResults = searchManager.getPageResults(request, 1);

    globalSearchContainer.setTotal(searchManager.getTotalResultsCount());
	globalSearchContainer.getResultRows().addAll(displayResults);
    globalSearchContainer.setEmptyResultsMessage(LanguageUtil.format(pageContext,
            "no-results-were-found-that-matched-the-keywords-x",
            "<strong>" + keywords + "</strong>"));

	if (Validator.isNotNull(keywords)) {
%>

<section class="search-listing">
<ul>
<liferay-ui:search-container
        searchContainer="<%= globalSearchContainer %>">

    <liferay-ui:search-container-results>
        <%
            results = displayResults;
            total = displayResults.size();

            int displayEnd = Math.min(displayResults.size(), globalSearchContainer.getEnd());
            int displayStart = Math.min(displayEnd, globalSearchContainer.getStart());

            pageContext.setAttribute("results", results.subList(displayStart, displayEnd));
            pageContext.setAttribute("total", total);

            request.setAttribute("cur", globalSearchContainer.getCur());
        %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row
            className="org.nterlearning.course.search.OpenSearchResult"
            modelVar="result">

        <nter:search_result searchResult="<%= result %>"/>
    </liferay-ui:search-container-row>

    <liferay-ui:search-paginator searchContainer="<%= globalSearchContainer %>" type="more"/>
</liferay-ui:search-container>
</ul>
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