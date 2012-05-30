
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

<%@ page import="org.nterlearning.utils.ReviewUtil" %>


<%@ include file="/course-listing/jsp/init.jsp" %>

<%
HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);

Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
long groupId = -1;
if (group.isOrganization()) {
    groupId = group.getGroupId();
}

//set course filter type (where clause) and course sort type (order by clause)
String urlFilter = httpRequest.getParameter("filter");
if (urlFilter == null) urlFilter = "";
CourseFilterType courseFilterType = CourseFilterType.ALL;
CourseSortType courseSortType = CourseSortType.NEW_POPULAR;
if (urlFilter.equals("new")) {
    courseSortType = CourseSortType.NEW_POPULAR;
} else if (urlFilter.equals("popular")) {
    courseSortType = CourseSortType.POPULAR_NEW;
} else if (urlFilter.equals("featured")) {
    courseFilterType = CourseFilterType.FEATURED;
    courseSortType = CourseSortType.NEW_POPULAR;
}

long categoryIdFilter = ParamUtil.getLong(httpRequest, "category");
long vocabularyIdFilter = ParamUtil.getLong(httpRequest, "vocabulary");
String keywordFilter = ParamUtil.getString(httpRequest, "keyword");


int delta = Integer.parseInt(prefs.getValue(ListingConstants.PREF_NUM_DISPLAYED, defaultDisplayCount));
int total;
if (vocabularyIdFilter > 0) {
    total = (int) CourseLocalServiceUtil.countAllValidCourses(vocabularyIdFilter, groupId);
} else if (categoryIdFilter > 0) {
    AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getCategory(categoryIdFilter);
    total = (int) CourseLocalServiceUtil.countAllValidCourses(assetCategory, groupId);
} else if (!keywordFilter.equals("")) {
    total = AssetTagLocalServiceUtil.getTag(themeDisplay.getScopeGroupId(), keywordFilter).getAssetCount();
}
else {
    total = (int) CourseLocalServiceUtil.countAllValidCourses(courseFilterType.getWhereSql(), groupId);
}

String curParam = "page";
int totalPages = (int) Math.ceil((double)total/(double)delta);
int cur = ParamUtil.getInteger(httpRequest, curParam, 1);
if (cur > totalPages) cur = totalPages;
int start = (cur - 1) * delta;
int end = cur * delta;

// If there are no results, must set results to empty list because return of findAllValidCourses will cause exception
List<Course> results = new ArrayList<Course>();
if (total > 0) {
    if (vocabularyIdFilter > 0) {
        try {
            results = CourseLocalServiceUtil.findAllValidCourses(vocabularyIdFilter, groupId, start, end);
        }
        catch (Exception e) {
            // problem searching for courses, most likely due to corrupted local index.
            total = 0;
        }
    } else if (categoryIdFilter > 0) {
        AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getCategory(categoryIdFilter);
        results = CourseLocalServiceUtil.findAllValidCourses(assetCategory, groupId, start, end);
    } else if (!keywordFilter.equals("")) {
        AssetTag assetTag = AssetTagLocalServiceUtil.getTag(themeDisplay.getScopeGroupId(), keywordFilter);

        AssetEntryQuery assetQuery = new AssetEntryQuery();
        assetQuery.setAnyTagIds(new long[]{assetTag.getTagId()});
        assetQuery.setClassName(Course.class.getName());
        assetQuery.setGroupIds(new long[]{themeDisplay.getScopeGroupId()});
        assetQuery.setStart(start);
        assetQuery.setEnd(end);

        List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil.getEntries(assetQuery);
        for (AssetEntry entry : assetEntries) {
            try {
                results.add(CourseLocalServiceUtil.getCourse(entry.getClassPK()));
            }
            catch (Exception e) {
                // problem searching for course, most likely due ot corrupted local index
                total -= 1;
            }
        }          
    }
    else {
        results = CourseLocalServiceUtil.findAllValidCourses(courseFilterType.getWhereSql(),
                courseSortType.getSortSql(), groupId, start, end);
    }
} else {
    results = new java.util.ArrayList<Course>();
}

pageContext.setAttribute("results", results);
pageContext.setAttribute("total", total);

StringBuffer subpage = new StringBuffer("");
if (urlFilter.equals("featured") || urlFilter.equals("new") || urlFilter.equals("popular")) {
	subpage.append("?filter=" + urlFilter);
}
if (vocabularyIdFilter > 0) {
	if (subpage.length() > 0) subpage.append("&");
	else subpage.append("?");
	subpage.append("vocabulary=" + vocabularyIdFilter);
}
if (categoryIdFilter > 0) {
	if (subpage.length() > 0) subpage.append("&");
	else subpage.append("?");
	subpage.append("category=" + categoryIdFilter);
}
if (!keywordFilter.equals("")) {
    if (subpage.length() > 0) subpage.append("&");
    else subpage.append("?");
    subpage.append("keyword=" + keywordFilter);
}
    
ArrayList meta = new ArrayList();
meta.add("<link rel=\"canonical\" href=\"" + PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId()).getFriendlyURL() + layout.getFriendlyURL() + subpage.toString() + "\" />");
httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);
%>

<!-- heading -->
<h3 class="main-page-heading">
	<%
	String pageTitle;
	boolean changeWindowTitle = true;
	if (urlFilter.equals("") && keywordFilter.equals("") && vocabularyIdFilter == 0 && categoryIdFilter == 0) {
		pageTitle = LanguageUtil.get(pageContext, "all-courses");
		changeWindowTitle = false;
	} else if (urlFilter.equals("featured")) {
		pageTitle = LanguageUtil.get(pageContext, "featured-courses");
	} else if (urlFilter.equals("new")) {
		pageTitle = LanguageUtil.get(pageContext, "new-courses");
	} else if (urlFilter.equals("popular")) {
		pageTitle = LanguageUtil.get(pageContext, "popular-courses");
	} else if (vocabularyIdFilter > 0) {
		pageTitle = LanguageUtil.format(pageContext, "vocabulary-courses", AssetVocabularyLocalServiceUtil.getAssetVocabulary(vocabularyIdFilter).getTitle(locale,true));
    } else if (categoryIdFilter > 0) {
		pageTitle = LanguageUtil.format(pageContext, "category-courses", AssetCategoryLocalServiceUtil.getAssetCategory(categoryIdFilter).getTitle(locale,true));
		changeWindowTitle = false;
    } else if (!keywordFilter.equals("")) {
        pageTitle = LanguageUtil.format(pageContext, "keyword-courses", LanguageUtil.get(pageContext, "keyword-label") + ": " + keywordFilter);
        changeWindowTitle = false;
    } else {
		pageTitle = LanguageUtil.get(pageContext, "courses");
		changeWindowTitle = false;
	}
	%>
	<%= pageTitle %>
	<% if (changeWindowTitle) PortalUtil.addPageTitle(pageTitle, PortalUtil.getHttpServletRequest(renderRequest)); %>
</h3>

<!-- type filter -->
<ul class="course-filter">
	<% if (urlFilter.equals("") && keywordFilter.equals("") && vocabularyIdFilter == 0 && categoryIdFilter == 0) {%><li class="current"><%= LanguageUtil.get(pageContext, "all-course-carousel") %></li><% } else { %><li><a href="?"><%= LanguageUtil.get(pageContext, "all-course-carousel") %></a></li><% } %>
	<% if (urlFilter.equals("featured")) { %><li class="current"><%= LanguageUtil.get(pageContext, "featured-course-carousel") %></li><% } else { %><li><a href="?filter=featured"><%= LanguageUtil.get(pageContext, "featured-course-carousel") %></a></li><% } %>
	<% if (urlFilter.equals("new")) { %><li class="current"><%= LanguageUtil.get(pageContext, "new-course-carousel") %></li><% } else { %><li><a href="?filter=new"><%= LanguageUtil.get(pageContext, "new-course-carousel") %></a></li><% } %>
	<% if (urlFilter.equals("popular")) { %><li class="current"><%= LanguageUtil.get(pageContext, "popular-course-carousel") %></li><% } else { %><li><a href="?filter=popular"><%= LanguageUtil.get(pageContext, "popular-course-carousel") %></a></li><% } %>
</ul>

<!-- category filter -->
<!-- only include category tree if it is partner page and a vocabulary is defined-->

<% Boolean vocabularyExists = false;
    if (group.isOrganization()) {    %>
    <%@include file="/course-listing/jsp/category-tree.jsp" %>
<% } %>


<% if (results.size() > 0) {
    if (group.isOrganization() && vocabularyExists) {%>
        <ul class="course-listing thumbnail-listing paged-thumbnail-listing listing-with-filter">
<%  } else { %>
        <ul class="course-listing thumbnail-listing paged-thumbnail-listing">
<%  }
   } %>
<% for (int i = 0; i < results.size(); i++) {
    Course course = results.get(i);
    course.startSafeImageEnumeration(locale, LocaleUtil.getDefault());
    CourseImage image0 = course.getSafeImage(0);
	Group courseGroup = GroupLocalServiceUtil.getGroup(course.getGroupId());
    %>
<li>
<article class="course">
<a class="thumbnail-link" href="<%= course.getUrl() %>"><img class="course-thumbnail no-png-fix"
     src="<%= image0.getMediumImageUrl(themeDisplay) %>"
     title="<%= image0.getAlternateText() %>"
     alt="<%= image0.getAlternateText() %>" /></a>
<h4><a href="<%= course.getUrl() %>"><%= course.getTitle(locale) %></a></h4>
<% RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(Course.class.getName(), course.getPrimaryKey()); %>
<div class="rating"><liferay-ui:ratings-score score="<%= ReviewUtil.getCourseAverageRating(course) %>" /></div>
<div class="organization"> <%= course.getCourseDomain() %></div>
</article>
</li>
<% } %>
<% if (results.size() > 0) { %></ul><% } %>

<%
String paginationUrl = renderResponse.createRenderURL().toString();
if (!urlFilter.equals("")) paginationUrl += "&filter=" + urlFilter.toLowerCase();
%>

<liferay-ui:page-iterator
    cur="<%= cur %>"
    curParam="<%= curParam %>"
    delta="<%= delta %>"
    deltaConfigurable="false"
    total="<%= total %>"
    url="<%= paginationUrl %>"
    type="article"
/>