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


<%@include file="/course-testing/jsp/init.jsp" %>

<%
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale());
    long courseId = ParamUtil.getLong(request, "resourcePrimKey");
    List<AssetTag> curTags = AssetTagLocalServiceUtil.getEntryTags(courseId);
    Course course = CourseLocalServiceUtil.getCourse(courseId);
    String createDateString = "";
    if (course.getCreateDate() != null) {
        createDateString = df.format(course.getCreateDate());
    }
    String updatedDateString = "";
    if (course.getUpdatedDate() != null) {
        updatedDateString = df.format(course.getUpdatedDate());
    }
    String removedDateString = "";
    if (course.getRemovedDate() != null) {
        removedDateString = df.format(course.getRemovedDate());
    }
    String releaseOnDateString = "";
    if (course.getReleaseOnDate() != null) {
        releaseOnDateString = df.format(course.getReleaseOnDate());
    }
    String acceptUntilDateString = "";
    if (course.getAcceptUntilDate() != null) {
        acceptUntilDateString = df.format(course.getAcceptUntilDate());
    }
    String versionDateString = "";
    if (course.getVersionDate() != null) {
        versionDateString = df.format(course.getVersionDate());
    }
%>

<div class="course-detail">
    <h3 class="course-title"><%= course.getTitle(locale) %></h3>
    <dl>
        <div class="course-attribute"><dt>Course IRI: </dt><dd><%= course.getCourseIri() %></dd></div>
        <div class="course-attribute"><dt>Updated Date: </dt><dd><%= updatedDateString %></dd></div>
        <div class="course-attribute"><dt>Date Created: </dt><dd><%= createDateString %></dd></div>
        <div class="course-attribute"><dt>Removed: </dt><dd><%= course.getRemoved() %></dd></div>
        <div class="course-attribute"><dt>Date Removed: </dt><dd><%= removedDateString %></dd></div>
        <div class="course-attribute"><dt>HREF: </dt><dd><%= course.getHref() %></dd></div>
        <div class="course-attribute"><dt>fullTextHREF: </dt><dd><%= course.getFullTextHref() %></dd></div>
        <div class="course-attribute"><dt>Description: </dt><dd><%= course.getDescription(locale) %></dd></div>
        <div class="course-attribute"><dt>Transcript Abstract: </dt><dd><%= course.getTranscriptAbstract(locale) %></dd></div>
        <div class="course-attribute"><dt>Copyright: </dt><dd><%= course.getCopyright(locale) %></dd></div>
        <div class="course-attribute"><dt>Rating Level: </dt><dd><%= course.getRatingLevel(locale) %></dd></div>
        <div class="course-attribute"><dt>Rating Reason: </dt><dd><%= course.getRatingReason(locale) %></dd></div>
        <div class="course-attribute"><dt>Duration: </dt><dd><%= course.getDuration() %></dd></div>
        <div class="course-attribute"><dt>DurationStandard: </dt><dd><%= course.getDurationStandard() %></dd></div>
        <div class="course-attribute"><dt>Featured Status: </dt><dd><%= course.getFeaturedStatus() %></dd></div>
        <div class="course-attribute"><dt>Popular Weight: </dt><dd><%= course.getPopularWeight() %></dd></div>
        <div class="course-attribute"><dt>Access Count: </dt><dd><%= course.getAccessCount() %></dd></div>
        <div class="course-attribute"><dt>Completed Count: </dt><dd><%= course.getCompletedCount() %></dd></div>
        <div class="course-attribute"><dt>Supersedes Course IRI: </dt><dd><%= course.getSupersedesCourseIri() %></dd></div>
        <div class="course-attribute"><dt>Superseded by Course IRI: </dt><dd><%= course.getSupersededByCourseIri() %></dd></div>
        <div class="course-attribute"><dt>Release On Date: </dt><dd><%= releaseOnDateString %></dd></div>
        <div class="course-attribute"><dt>Accept Until Date: </dt><dd><%= acceptUntilDateString %></dd></div>
        <div class="course-attribute"><dt>Version</dt><dd><%= course.getVersion() %></dd></div>
        <div class="course-attribute"><dt>Version Date: </dt><dd><%= versionDateString %></dd></div>
        <div class="course-attribute"><dt>Price</dt><dd><%= course.getPrice() %></dd></div>
        <div class="course-attribute"><dt>Price Unit</dt><dd><%= course.getPriceUnit() %></dd></div>
        <div class="course-attribute"><dt>Price Terms</dt><dd><%= course.getPriceTerms() %></dd></div>
        <div class="course-attribute"><dt>Price Expiration</dt><dd><%= course.getPriceExpiration() %></dd></div>
        <div class="course-attribute"><dt>One Star Rate Count</dt><dd><%= course.getOneStarRateCount() %></dd></div>
        <div class="course-attribute"><dt>Two Star Rate Count</dt><dd><%= course.getTwoStarRateCount() %></dd></div>
        <div class="course-attribute"><dt>Three Star Rate Count</dt><dd><%= course.getThreeStarRateCount() %></dd></div>
        <div class="course-attribute"><dt>Four Star Rate Count</dt><dd><%= course.getFourStarRateCount() %></dd></div>
        <div class="course-attribute"><dt>Five Star Rate Count</dt><dd><%= course.getFiveStarRateCount() %></dd></div>
        <div class="separator"></div>
        <div class="course-attribute">
			<dt>Categories: </dt><dd>
            <nter:category className="<%=Course.class.getName() %>" classPK="<%= course.getPrimaryKey() %>" />
           </dd>
            </div>
        <div class="separator"></div>
        <div class="course-attribute">
            <dt>Tags (aka Keywords): </dt>
			<liferay-ui:asset-tags-summary
			classPK="<%= course.getCourseId() %>"
			className="<%= Course.class.getName() %>"
			portletURL="<%= renderResponse.createRenderURL() %>" />
        </div>
    </dl>
</div>

<aui:button-row>
    <%
        String backURL = ParamUtil.getString(request, "redirect", "location.href='';");
    %>
    <aui:button name="returnButton" type="button" value="return-to-course-listing" last="true" onClick="<%=backURL %>" />
</aui:button-row>
