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

<%-- review.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ tag import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ tag import="com.liferay.portal.kernel.log.Log" %>
<%@ tag import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ tag import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ tag import="com.liferay.portal.kernel.util.StringPool" %>
<%@ tag import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ tag import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>
<%@ tag import="com.liferay.portlet.ratings.NoSuchEntryException" %>
<%@ tag import="com.liferay.portlet.ratings.model.RatingsEntry" %>
<%@ tag import="com.liferay.portlet.ratings.model.RatingsStats" %>
<%@ tag import="com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil" %>
<%@ tag import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %>
<%@ tag import="org.nterlearning.course.util.NterKeys" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.CourseReview" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil" %>
<%@ tag import="org.nterlearning.utils.ReviewUtil" %>
<%@ tag import="java.text.DateFormat" %>
<%@ tag import="java.text.SimpleDateFormat" %>

<%@ attribute name="review" type="org.nterlearning.datamodel.catalog.model.CourseReview" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />


<%
String className = CourseReview.class.getName();
Long classPK = review.getPrimaryKey();

String currentURL = PortalUtil.getCurrentURL(request);
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_flags_page") + StringPool.UNDERLINE;

// fill in title from first 5 words of content
String reviewTitle = StringUtil.shorten(review.getContent(), 30);

double yourScore = 0.0;
try {
	RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.getEntry(themeDisplay.getUserId(), className, classPK);
	yourScore = ratingsEntry.getScore();
} catch (NoSuchEntryException nsee) {
	_log.debug("no existing review", nsee);
}

Course course = CourseLocalServiceUtil.getCourse(review.getCourseId());

// figure out how many upvotes and downvotes, only works if all votes are +1 and -1
RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(className, classPK);
double totalScore = ratingsStats.getTotalScore();
double totalEntries = ratingsStats.getTotalEntries();
int downVotes = (int) (totalEntries - totalScore) / 2;
int upVotes = (int) totalEntries - downVotes;
%>

<li class="review" data-review-id="<%= review.getPrimaryKey() %>" data-review-id-param="<%=NterKeys.REVIEW_ID%>" data-user-id="<%= review.getUserId() %>"
	data-course-id="<%= review.getCourseId() %>" data-course-id-param="<%=NterKeys.REVIEW_CLASSPK%>" data-redirect-url="<%= PortalUtil.getCurrentURL(request) %>"
	itemscope itemtype="http://schema.org/Review">
    <header>
        <!-- will users really upload their pictures? <a href="#"><img class="reviewer-portrait" src="/course-reviews-portlet/images/user_male_portrait.jpg" title="Student Name" alt="Student Name" /></a> -->
        <div class="reviewer-name" itemprop="author"><%= UserLocalServiceUtil.getUserById(review.getUserId()).getFullName() %> </div>
        <% double score = CourseReviewLocalServiceUtil.findScoreByReviewId(review.getCourseReviewId()); %>
        <div class="user-rating" data-rating="<%= score %>" itemprop="reviewRating" itemscope itemtype="http://schema.org/Rating">
            <liferay-ui:ratings-score score="<%= score %>"/>
        </div>
        <div class="review-date" itemprop="datePublished" content="<%= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(review.getModifiedDate()) %>">
			<%= DateFormat.getDateInstance(DateFormat.LONG, locale).format(review.getModifiedDate()) %>
		</div>
        <% if (ReviewUtil.isTopReviewer(review.getUserId())) { %>
        <div class="badge top-reviewer-badge" title="<liferay-ui:message key='top-reviewer-description' />"><liferay-ui:message key="top-reviewer" /></div>
        <% } %>
    </header>
	<div class="review-body">
        <%//remove summary; may reactivate based on user feedback
          //<div class="review-title" itemprop="name">< review.getSummary() ></div>%>
		<div class="review-text" itemprop="description">
			<%= review.getContent() %>
		</div>

		<% if ((!course.isRemoved() || permissionChecker.isOmniadmin())) { %>
		<div class="actions <%= randomNamespace %>">
			<% if (review.getUserId() == user.getUserId() || permissionChecker.isCommunityAdmin(scopeGroupId)) { %>
			<div class="admin">
				<portlet:renderURL var="editUrl">
					<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(review.getPrimaryKey()) %>"/>
					<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(review.getCourseId()) %>"/>
					<portlet:param name="jspPage" value="/course-reviews/html/edit-review.jsp"/>
					<portlet:param name="redirect" value="<%=currentURL%>"/>
				</portlet:renderURL>
				<portlet:renderURL var="deleteUrl" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(review.getPrimaryKey()) %>"/>
					<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(review.getCourseId()) %>"/>
                    <portlet:param name="jspPage" value="/course-reviews/html/delete-review-confirm.jsp"/>
				</portlet:renderURL>
				<a class="button edit" href="<%= editUrl %>&cid=<%=review.getCourseId()%>#review-form"><%= LanguageUtil.get(pageContext,
						"edit") %>
				</a>

				<a class="button delete" href="<%= deleteUrl %>" data-id-name="review-reviewId" data-id="<%= review.getPrimaryKey() %>"
						data-url="<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" name='deleteCourseRating' />&ajax=true"><%= LanguageUtil.get(pageContext, "remove") %>
				</a>
			</div>
			<% } %>
			<% if (review.getUserId() != user.getUserId() && themeDisplay.isSignedIn()) { %>
			<div class="metamoderation">
				<portlet:renderURL var="metaLanding" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(review.getPrimaryKey()) %>"/>
					<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(review.getCourseId()) %>"/>
					<portlet:param name="jspPage" value="/course-reviews/html/metareview-landing.jsp"/>
					<portlet:param name="redirect" value="<%=currentURL%>"/>
				</portlet:renderURL>
				<portlet:actionURL var="metaUrl" name="updateReviewRating"
								   windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="redirect" value="<%=metaLanding%>"/>
				</portlet:actionURL>
                <portlet:renderURL var="flagEntryUrl" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
                    <portlet:param name="jspPage" value="/course-reviews/html/editFlagEntry.jsp"/>
                </portlet:renderURL>
				<label><%= LanguageUtil.get(pageContext, "meta-prompt") %>
				</label>

				<form class="good" action="<%= metaUrl %>" method="post">
					<input type="hidden" name="<%= NterKeys.REVIEW_ID %>" value="<%= classPK %>"/>
					<input type="hidden" name="score" value="<%= (yourScore > 0) ? "0" : "1" %>"/>
					<button class="<%= (yourScore > 0) ? "selected" : "" %>"><%= LanguageUtil.get(pageContext, "helpful") %>
					</button>
					<%
						String upLabel = "meta-votes";
						if (upVotes == 0) upLabel = "meta-votes-zero";
						if (upVotes == 1) upLabel = "meta-votes-singular";
					%>
					<small class="votes"><%= LanguageUtil.format(pageContext, upLabel, upVotes) %></small>
				</form>
				<form class="bad" action="<%= metaUrl %>" method="post">
					<input type="hidden" name="<%= NterKeys.REVIEW_ID %>" value="<%= classPK %>"/>
					<input type="hidden" name="score" value="<%= (yourScore < 0) ? "0" : "-1" %>"/>
					<button class="<%= (yourScore < 0) ? "selected" : "" %>"><%= LanguageUtil.get(pageContext, "unhelpful") %>
					</button>
					<%
						String downLabel = "meta-votes";
						if (downVotes == 0) downLabel = "meta-votes-zero";
						if (downVotes == 1) downLabel = "meta-votes-singular";
					%>
					<small class="votes"><%= LanguageUtil.format(pageContext, downLabel, downVotes) %></small>
				</form>
     			<a class="button flagEntry" href="<%= flagEntryUrl %>&cid=<%=review.getCourseId()%>&crid=<%=review.getPrimaryKey()%>#review-form"><%= LanguageUtil.get(pageContext,
						"inappropriate") %>
				</a>
			</div>
			<% } else { %>
			<div class="metamoderation">
				<% String[] args = {Integer.toString((int) upVotes), Integer.toString((int) totalEntries)}; %>
				<div class="summary"><%= LanguageUtil.format(pageContext, "meta-helpful-summary", args) %>
				</div>
			</div>
			<% } %>
		</div>
		<% } %>
	</div>
</li>
<%! private static final Log _log = LogFactoryUtil.getLog("courseportlet.docroot.tags.review.tag"); %>

