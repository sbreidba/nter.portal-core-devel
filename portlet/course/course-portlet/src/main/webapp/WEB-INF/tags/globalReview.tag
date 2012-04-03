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

<%-- globalReview.tag --%>

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
<%@ tag import="com.liferay.portal.model.UserIdMapper" %>
<%@ tag import="com.liferay.portal.NoSuchUserIdMapperException" %>
<%@ tag import="com.liferay.portal.service.UserIdMapperLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>
<%@ tag import="org.nterlearning.course.util.NterKeys" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.Course" %>
<%@ tag import="org.nterlearning.datamodel.catalog.model.GlobalCourseReview" %>
<%@ tag import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>
<%@ tag import="org.nterlearning.utils.PortalPropertiesUtil" %>
<%@ tag import="java.text.DateFormat" %>
<%@ tag import="java.text.SimpleDateFormat" %>

<%@ attribute name="globalReview" type="org.nterlearning.datamodel.catalog.model.GlobalCourseReview" required="true" %>
<%@ attribute name="pageContext" type="javax.servlet.jsp.PageContext" required="true" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />


<%
String className = GlobalCourseReview.class.getName();
Long classPK = globalReview.getPrimaryKey();

String currentURL = PortalUtil.getCurrentURL(request);
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_flags_page") + StringPool.UNDERLINE;
// fill in title from first 5 words of content
String reviewTitle = StringUtil.shorten(globalReview.getContent(), 30);

Course course = CourseLocalServiceUtil.getCourse(globalReview.getCourseId());

%>

<li class="review" data-review-id="<%= globalReview.getPrimaryKey() %>" data-user-id="<%= globalReview.getSingleSignOnValue() %>"
	data-course-id="<%= globalReview.getCourseId() %>" data-course-id-param="<%=NterKeys.REVIEW_CLASSPK%>" data-redirect-url="<%= PortalUtil.getCurrentURL(request) %>"
	itemscope itemtype="http://schema.org/Review">
    <header>
        <!-- will users really upload their pictures? <a href="#"><img class="reviewer-portrait" src="/course-reviews-portlet/images/user_male_portrait.jpg" title="Student Name" alt="Student Name" /></a> -->
        <div class="reviewer-name" itemprop="author"><%= globalReview.getUserDisplayName() %> </div>
        <% double score = globalReview.getStarScore(); %>
        <div class="user-rating" data-rating="<%= score %>" itemprop="reviewRating" itemscope itemtype="http://schema.org/Rating">
            <liferay-ui:ratings-score score="<%= score %>"/>
        </div>
        <div class="review-date" itemprop="datePublished" content="<%= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(globalReview.getModifiedDate()) %>">
			<%= DateFormat.getDateInstance(DateFormat.LONG, locale).format(globalReview.getModifiedDate()) %>
		</div>
        <% if (globalReview.getFromTrustedReviewer()) { %>
        <div class="badge trusted-reviewer-badge">Trusted Reviewer</div>
        <% } %>
    </header>
	<div class="review-body">

		<%//remove summary; may reactivate based on user feedback
          // <div class="review-title" itemprop="name"><globalReview.getSummary()></div>%>

		<div class="review-text" itemprop="description">
			<%= globalReview.getContent() %>
		</div>

		<% if (!course.isRemoved() || permissionChecker.isOmniadmin()) { %>
		<div class="actions <%= randomNamespace %>">
			<% if (permissionChecker.isCommunityAdmin(scopeGroupId)) { %>
			<div class="admin">
				<portlet:renderURL var="remoteHideUrl" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(globalReview.getPrimaryKey()) %>"/>
					<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(globalReview.getCourseId()) %>"/>
					<portlet:param name="jspPage" value="/course-reviews/html/hide-global-review-confirm.jsp"/>
				</portlet:renderURL>
				<a class="button hide" href="<%= remoteHideUrl %>" data-id-name="review-reviewId" data-id="<%= globalReview.getPrimaryKey() %>"
						data-url="<portlet:actionURL name='hideGlobalCourseReviewRating' />"><%= LanguageUtil.get(pageContext, "hide") %>
				</a>
			</div>
			<% } %>
			<% if (themeDisplay.isSignedIn()) {
                // check to see if the sso value is known by this system, if not, use default userId
                long reviewUser = themeDisplay.getUserId();
                try {
                    UserIdMapper userMapper =
                            UserIdMapperLocalServiceUtil.getUserIdMapperByExternalUserId(
                                    PortalPropertiesUtil.getSsoImplementation(),
                                    globalReview.getSingleSignOnValue());
                    reviewUser = userMapper.getUserId();
                }
                catch (NoSuchUserIdMapperException e) {
                    long defaultUser = UserLocalServiceUtil.getDefaultUserId(themeDisplay.getCompanyId());
                    if (defaultUser != 0) {
                       reviewUser = defaultUser;
                    }
                }
            %>
			<div class="metamoderation">
				<portlet:renderURL var="remoteMetaLanding" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="<%=NterKeys.REVIEW_ID%>" value="<%= Long.toString(globalReview.getPrimaryKey()) %>"/>
					<portlet:param name="<%=NterKeys.REVIEW_CLASSPK%>" value="<%= Long.toString(globalReview.getCourseId()) %>"/>
					<portlet:param name="jspPage" value="/course-reviews/html/metareview-landing.jsp"/>
					<portlet:param name="redirect" value="<%=currentURL%>"/>
				</portlet:renderURL>
				<portlet:renderURL var="remoteFlagUrl" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
					<portlet:param name="jspPage" value="/course-reviews/html/editGlobalFlagEntry.jsp" />
				</portlet:renderURL>
				<label><%= LanguageUtil.get(pageContext, "meta-prompt") %>
				</label>

				<!-- workaround for the input named className overriding the form class in ie7 -->
				<!--[if lte IE 7]>
				<!--span class="flag">
				<!--[endif]
				//replaced this form code with new button code.
				<form class="flag" action="< remoteFlagUrl %>" method="post">
					<input type="hidden" name="className" value="< className %>"/>
					<input type="hidden" name="classPK" value="< classPK %>"/>
                    <input type="hidden" name="contentTitle" value="< reviewTitle %>"/>
					<input type="hidden" name="contentURL" value="< PortalUtil.getPortalURL(request) + currentURL %>"/>
                    <input type="hidden" name="reportedUserId" value="< reviewUser %>"/>
     				<button>< LanguageUtil.get(pageContext, "remote-inappropriate") %>
					</button>
				</form>
				-->
				<!-- end of workaround tag -->
				<!--[if lte IE 7]-->

	            <a class="button remoteFlagEntry" href="<%= remoteFlagUrl %>&cid=<%=course.getCourseId()%>&crid=<%=classPK%>#review-form"><%= LanguageUtil.get(pageContext,
                           "inappropriate") %>
                </a>
                <!--
				</span>
				[endif]-->
			</div>
			<% }  %>
		</div>
		<% } %>
	</div>
</li>
<%! private static final Log _log = LogFactoryUtil.getLog("courseportlet.docroot.tags.globalReview.tag"); %>

