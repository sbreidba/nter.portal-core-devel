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

<%-- search_result.tag --%>

<%@tag import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@tag import="com.liferay.portal.kernel.util.*"%>
<%@tag import="com.liferay.portal.service.ClassNameLocalServiceUtil" %>
<%@tag import="com.liferay.portal.util.PortalUtil"%>
<%@tag import="com.liferay.portal.util.PortletKeys"%>
<%@tag import="com.liferay.portlet.asset.model.AssetTag" %>
<%@tag import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %>
<%@tag import="com.liferay.portlet.documentlibrary.model.DLFileEntry"%>
<%@tag import="com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil"%>
<%@tag import="com.liferay.portlet.journal.model.JournalArticle"%>
<%@tag import="com.liferay.portal.kernel.search.Field" %>
<%@tag import="com.liferay.portlet.PortletURLFactoryUtil"%>
<%@tag import="com.liferay.portlet.PortletURLUtil" %>

<%@tag import="org.nterlearning.course.util.NterKeys"%>
<%@tag import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@tag import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>

<%@tag import="javax.portlet.PortletRequest"%>
<%@tag import="javax.portlet.PortletURL"%>
<%@tag import="java.util.List" %>
<%@tag import="org.nterlearning.datamodel.catalog.model.Component" %>
<%@tag import="org.nterlearning.datamodel.catalog.service.ComponentLocalServiceUtil" %>

<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<%@ attribute name="searchResult"
              type="org.nterlearning.course.search.OpenSearchResult"
              required="true"%>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
	boolean dlLinkToViewURL = false;

	PortletURL portletURL = renderResponse.createRenderURL();
	String format = ParamUtil.getString(request, "format");

    // format query terms by unquoting it and then splitting on ", "
    searchResult.keywords = searchResult.keywords.replaceAll("\"", "");
	String[] queryTerms = searchResult.keywords.split(",?\\ +");

	// Localize courses/components display
	if (NterKeys.COURSE_SEARCH_PORTLET.equals(searchResult.type) ||
        NterKeys.COMPONENT_SEARCH_PORTLET.equals(searchResult.type) ||
        NterKeys.EXTERNAL_SEARCH_PORTLET.equals(searchResult.type)) {

        if (Course.class.getName().equals(searchResult.className)) {
            Course course;
            if (searchResult.classPK != 0) {
                course = CourseLocalServiceUtil.getCourse(searchResult.classPK);
            }
            else {
                course = CourseLocalServiceUtil.findByCourseIri(searchResult.classIri);
            }

            String resultTitle = searchResult.title;
            if (resultTitle.startsWith("[")) {
                resultTitle = resultTitle.substring(1, resultTitle.length() - 1);
                resultTitle = resultTitle.split(StringPool.COMMA_AND_SPACE)[0];
            }

            resultTitle = LocalizationUtil.
                    getLocalization(resultTitle, LanguageUtil.getLanguageId(locale));

            searchResult.title = course.getTitle(locale) + " : " +
                    LocalizationUtil.getLocalization(resultTitle, LanguageUtil.getLanguageId(locale));
        }
        else if (Component.class.getName().equals(searchResult.className)) {
            Component component;
            if (searchResult.classPK != 0) {
                component = ComponentLocalServiceUtil.getComponent(searchResult.classPK);
            }
            else {
                component = ComponentLocalServiceUtil.fetchByComponentIri(searchResult.classIri);
            }

            String resultTitle = searchResult.title;
            if (resultTitle.startsWith("[")) {
                resultTitle = resultTitle.substring(1, resultTitle.length() - 1);
                resultTitle = resultTitle.split(StringPool.COMMA_AND_SPACE)[0];
            }

            resultTitle = LocalizationUtil.
                    getLocalization(resultTitle, LanguageUtil.getLanguageId(locale));

            searchResult.title = component.getTitle() + " : " +
                    LocalizationUtil.getLocalization(resultTitle, LanguageUtil.getLanguageId(locale));
        }
        
		searchResult.summary =LocalizationUtil.getLocalization(
				searchResult.summary, LanguageUtil.getLanguageId(locale));
	}

	if (PortletKeys.DOCUMENT_LIBRARY.equals(searchResult.type) ||
		PortletKeys.SEARCH.equals(searchResult.type) &&
		DLFileEntry.class.getName().equals(searchResult.className)) {

		long folderId =
			GetterUtil.getLong(HttpUtil.getParameter(searchResult.href, "_20_folderId", false));
		String name =
			GetterUtil.getString(HttpUtil.getParameter(searchResult.href, "_20_name", false));

		DLFileEntry fileEntry =
			DLFileEntryLocalServiceUtil.getFileEntry(searchResult.groupId, folderId, name);

		searchResult.title = fileEntry.getTitle();

		if (PortletKeys.SEARCH.equals(searchResult.type)) {
			searchResult.title =
				PortalUtil.getPortletTitle(
					PortletKeys.DOCUMENT_LIBRARY, locale) +
					" " +
					CharPool.RAQUO + " " + searchResult.title;
		}

		if (dlLinkToViewURL) {
			long dlPlid = PortalUtil.getPlidFromPortletId(
					        fileEntry.getGroupId(), PortletKeys.DOCUMENT_LIBRARY);

			PortletURL viewURL =
				PortletURLFactoryUtil.create(
					request, PortletKeys.DOCUMENT_LIBRARY, dlPlid,
					PortletRequest.RENDER_PHASE);

			viewURL.setParameter(
				"struts_action", "/document_library/view_file_entry");
			viewURL.setParameter("redirect", portletURL.toString());
			viewURL.setParameter(
				"folderId", String.valueOf(fileEntry.getFolderId()));
			viewURL.setParameter("name", HtmlUtil.unescape(name));

			searchResult.href = viewURL.toString();
		}
	}

	// Tags
	StringBundler tagSB = new StringBundler();

    long classId = ClassNameLocalServiceUtil.getClassNameId(searchResult.className);
    List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(classId, searchResult.classPK);

    tagSB.append("<div class=\"entry-tags\">");
    tagSB.append("<div class=\"taglib-asset-tags-summary\">");

    for (AssetTag assetTag : assetTags) {
        PortletURL tagURL = PortletURLUtil.clone(portletURL, renderResponse);
        tagURL.setParameter("keywords", Field.ASSET_TAG_NAMES + ":" + assetTag.getName());
        tagURL.setParameter("format", format);

        tagSB.append("<a class=\"tag\" href=\"" + tagURL.toString() + "\">" + StringUtil.highlight(assetTag.getName(), queryTerms) + "</a>");
    }

    tagSB.append("</div>");
    tagSB.append("</div>");
%>

<c:choose>
	<c:when
		test="<%= NterKeys.COMPONENT_SEARCH_PORTLET.equals(searchResult.type) ||
		          NterKeys.COURSE_SEARCH_PORTLET.equals(searchResult.type) ||
		          NterKeys.EXTERNAL_SEARCH_PORTLET.equals(searchResult.type) %>">
		<li class="item has-photo course">
			<!-- course -->
			<a href="<%=searchResult.href%>">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(searchResult.imgURL) %>">
					    <img src="<%= searchResult.imgURL %>" class="course-mini-thumbnail" alt="<%= searchResult.imgAltTxt %>" />
					</c:when>
					<c:otherwise>
					    <img src="/nter-nter-theme/images/default_images/course_default_small.jpg" class="course-mini-thumbnail" alt="<%= LanguageUtil.get(locale,"no-course-image") %>" />
					</c:otherwise>
				</c:choose>
			</a>
			<div class="details">
				<h3>
					<a href="<%=searchResult.href%>">
                        <%=StringUtil.highlight(HtmlUtil.escape(searchResult.title), queryTerms)%>
                    </a>
				</h3>
                <c:if test="<%= Validator.isNotNull(searchResult.ownerName)%>">
				    <div class="origin">
                        <a href="<%= searchResult.ownerURL %>"><%=searchResult.ownerName%></a>
                    </div>
                </c:if>
				<c:if test="<%= Validator.isNotNull(searchResult.summary) %>">
					<div class="description">
                        <%=StringUtil.highlight(HtmlUtil.escape(searchResult.summary), queryTerms)%>
                    </div>
				</c:if>
				<c:if test="<%= Validator.isNotNull(searchResult.rating) &&
				                 !Component.class.getName().equals(searchResult.className)%>">
					<liferay-ui:ratings-score score="<%= searchResult.rating %>" />
				</c:if>
				<div class="origin"><%= tagSB %></div>
			</div>
		</li>
	</c:when>
	<c:when
		test="<%= PortletKeys.JOURNAL.equals(searchResult.type) ||
		           PortletKeys.SEARCH.equals(searchResult.type) &&
		            JournalArticle.class.getName().equals(searchResult.type) %>">
		<li class="item">
			<h3>
				<a href="<%=searchResult.href%>" target="_blank">
                    <%=StringUtil.highlight(HtmlUtil.escape(searchResult.title), queryTerms)%>
                </a>
			</h3>

            <c:if test="<%= Validator.isNotNull(searchResult.summary) %>">
				<div class="description">
                    <%=StringUtil.highlight(HtmlUtil.escape(searchResult.summary), queryTerms)%>
                </div>
			</c:if>

			<div class="origin"><%=tagSB%></div>
		</li>
	</c:when>
	<c:otherwise>
		<li class="item">
			<h3>
				<a href="<%=searchResult.href%>">
                    <%=StringUtil.highlight(HtmlUtil.escape(searchResult.title), queryTerms)%>
                </a>
			</h3>

            <c:if test="<%= Validator.isNotNull(searchResult.summary) %>">
				<div class="description">
                    <%=StringUtil.highlight(HtmlUtil.escape(searchResult.summary), queryTerms)%>
                </div>
			</c:if>

			<div class="origin"><%=tagSB%></div>
		</li>
	</c:otherwise>
</c:choose>

