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

<%-- course-details/jsp/view.jsp --%>
<%@ page import="org.nterlearning.utils.ReviewUtil" %>
<%@include file="/course-details/jsp/init.jsp" %>
<%
HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
Course course;
CourseRecord courseRecord = null;
String buttonCssClass = "";
int activeComponentCount = 0;
int finishedComponentCount = 0;
Component resumeComponent = null;
Component failedComponent = null;
long userId = 0;
List<ComponentQueryResult> componentResults = new ArrayList <ComponentQueryResult>();
List<ComponentQueryResult> resourceResults = new ArrayList <ComponentQueryResult>();
List<ComponentRecordQueryResult> componentRecordResults = new ArrayList <ComponentRecordQueryResult>();
List<ComponentRecordQueryResult> resourceRecordResults = new ArrayList <ComponentRecordQueryResult>();

// hard-coded:
boolean isPurchased = false;

String courseParam = httpRequest.getParameter("cid");
if (Validator.isNull(courseParam)) {
%>
<div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext,"course-detail-not-found") %> </div>
<%
} else {
	long courseId = Long.parseLong(courseParam);
	if (!themeDisplay.isSignedIn()) {
        course = CourseLocalServiceUtil.getCourse(courseId);
    } else {
        // retrieve course and student's record
        userId = themeDisplay.getUserId();
        String orderByType = "desc";
        String sortType = CourseRecordSortType.UPDATED_DATE.toString();
        String filterType = CourseRecordFilterType.SPECIFIC_COURSE.toString();
        int start = 0;
        int end = 1;
        Boolean dynamicSortEnabled = false;

        List<CourseRecordQueryResult> courseRecordQueryResults = CourseRecordQueryUtils.getCompoundQueryResults(userId,
				locale, courseId, filterType, sortType, orderByType.equals("asc"), start, end, dynamicSortEnabled);
        if (courseRecordQueryResults.size() > 0) {
            CourseRecordQueryResult mostRecent = CourseRecordQueryUtils.getMostRecent(courseRecordQueryResults);
            course = mostRecent.getCourse();
            courseRecord = mostRecent.getCourseRecord();
        } else {
            course = CourseLocalServiceUtil.getCourse(courseId);
        }
		try{
			isPurchased = course.isPurchased(user.getUserId());
		}catch(IndexOutOfBoundsException e) {
			out.print("This user is for testing purposes only.  Commerce will not work as expected.");
		}
    }

    //determine language
    String languageId = httpRequest.getParameter("lang");
    if (languageId != null) {
	    locale = LocaleUtil.fromLanguageId(languageId);
    }

    if (courseRecord == null) {
        //Query for combined components and courses_components.  First with locale and if none exist query with default locale.
        List<ComponentQueryResult> allComponentQueryResults = ComponentQueryUtils.getCompoundQueryResults(
				course.getCourseId(), locale, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        if (allComponentQueryResults.size() < 1) {
            allComponentQueryResults = ComponentQueryUtils.getCompoundQueryResults(courseId, LocaleUtil.getDefault(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        }
        // Separate results of combined query into contents and resources.
        componentResults = ComponentQueryUtils.getContentComponents(allComponentQueryResults);
        resourceResults = ComponentQueryUtils.getResourceComponents(allComponentQueryResults);

        resumeComponent = ComponentQueryUtils.getFirst(componentResults, locale);
        if (resumeComponent == null) {
            resumeComponent = ComponentQueryUtils.getFirst(componentResults, LocaleUtil.getDefault() );
        }

    } else {      //student progress exists
        String completionStatus = courseRecord.getCompletionStatus();

        // obtain students status on course components
        String componentRecordFilterType = ComponentRecordFilterType.ALL.toString();
        String componentRecordSortType = ComponentRecordSortType.COMPONENT_ORDER_WEIGHT.toString();
        List<ComponentRecordQueryResult> allComponentRecordResults = ComponentRecordQueryUtils.getCompoundQueryResults(
				courseRecord.getCourseRecordId(), userId, locale, componentRecordFilterType, componentRecordSortType,
				true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        // Separate results of combined query into contents and resources.
        componentRecordResults = ComponentRecordQueryUtils.getContentComponents(allComponentRecordResults);
        resourceRecordResults = ComponentRecordQueryUtils.getResourceComponents(allComponentRecordResults);

        // retrieve count of active, finished components
        activeComponentCount = ComponentRecordQueryUtils.getComponentActiveCount(componentRecordResults);
        finishedComponentCount = ComponentRecordQueryUtils.getComponentFinishedCount(componentRecordResults);

        //Determine resumeComponent for button to Continue, retry, Start component of a course
        ComponentRecordQueryResult resumeComponentRecord = ComponentRecordQueryUtils.getFirstUncompletedComponent(
				componentRecordResults);
        if (resumeComponentRecord != null) resumeComponent = resumeComponentRecord.getComponent();

        //Determine failedComponent for button failed retry of a course
        if (completionStatus.equals(CompletionStatusType.FAILED_RETRY.getDbValue())) {
            ComponentRecordQueryResult failedComponentRecord = ComponentRecordQueryUtils.getFirstFailedComponent(
					componentRecordResults);
            failedComponent = failedComponentRecord.getComponent();
        }
    }

    request.setAttribute("buttonCssClass"," ");
    request.setAttribute("isPurchased",isPurchased);
    request.setAttribute("course",course);
    request.setAttribute("courseRecord", courseRecord);
    request.setAttribute("finishedComponentCount",finishedComponentCount);
    request.setAttribute("activeComponentCount", activeComponentCount);
    request.setAttribute("resumeComponent", resumeComponent);
    request.setAttribute("failedComponent", failedComponent);

    String searchPage = PrefsPropsUtil.getString(PropsKeys.COMPANY_DEFAULT_HOME_URL) + "/search?";

    PortalUtil.addPageSubtitle(course.getTitle(locale), PortalUtil.getHttpServletRequest(renderRequest));
    PortalUtil.addPageKeywords(course.getKeywords(locale), PortalUtil.getHttpServletRequest(renderRequest));	// add categories too?
    PortalUtil.addPageDescription(course.getDescription(locale), PortalUtil.getHttpServletRequest(renderRequest));
%>

<article class="course-details" itemscope itemtype="http://schema.org/CreativeWork">
	<div class="course-image">
	<%
		course.startSafeImageEnumeration(locale, LocaleUtil.getDefault());
		CourseImage image0 = course.getSafeImage(0);
	%>

 		<img src="<%= image0.getLargeImageUrl(themeDisplay) %>" title="<%= image0.getAlternateText() %>" alt="<%= image0
 		.getAlternateText() %>" class="main-image" itemprop="image" />

		<ul class="thumbnails">
		<%
			if (course.getSafeImageCount() > 1) {
				for (int i = 0; i < course.getSafeImageCount(); ++i) {
					CourseImage image = course.getSafeImage(i);
		%>
					<li>
						<a href="<%= image.getLargeImageUrl(themeDisplay) %>" title="<%= LanguageUtil.get(pageContext, "view-course-image") %>" class="thumbnail course-mini-thumbnail <%= (i == 0) ? "selected" : "" %>">
							<img src="<%= image.getSmallImageUrl(themeDisplay) %>" alt="<%= image.getAlternateText() %>" itemprop="image" />
						</a>
					</li>
		<%
				}
			}
		%>
		</ul>
	</div>
	<div class="course-description">
		<%
		// we should be using renderResponse.createElement (http://blogs.oracle.com/deepakg/entry/setting_markup_head_elements_in)
		// but it doesn't seem to work in all circumstances, e.g. maximized portlets
		Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
		List meta = new ArrayList();
		if (oldMeta != null) meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
		meta.add("<meta property=\"og:type\" content=\"product\" />");
		meta.add("<meta property=\"og:title\" content=\"" + course.getTitle(locale) + "\" />");
		meta.add("<meta property=\"og:url\" content=\"" + PortalUtil.getPortalURL(httpRequest)
				+ PortalUtil.getPathFriendlyURLPublic() + GroupLocalServiceUtil.getGroup(
				themeDisplay.getScopeGroupId()).getFriendlyURL() + layout.getFriendlyURL() + "?cid=" + courseId
				+ "\" />");
		// TODO: should be absolute--default image is not an absolute url but real images are
		meta.add("<meta property=\"og:image\" content=\"" + image0.getLargeImageUrl(themeDisplay) + "\" />");
		meta.add("<meta property=\"og:site_name\" content=\"" + PortalUtil.getCompany(httpRequest).getName() + "\" />");
		meta.add("<meta property=\"og:description\" content=\"" + course.getDescription(locale) + "\" />");
		httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);
		%>

		<h3 class="course-title" itemprop="name"><%= course.getTitle(locale) %></h3>

		<% if (course.hasNewerVersion()) {
			Course newestVersion = course.getMostRecentVersion();
			if (course.isRemoved()) { %>
			<div class="portlet-msg-error"><%= LanguageUtil.format(pageContext, "course-superseded-removed",
					newestVersion.getUrl()) %></div>
			<% } else { %>
			<div class="portlet-msg-error"><%= LanguageUtil.format(pageContext, "course-superseded",
					newestVersion.getUrl()) %></div>
			<% }
		} else if (course.isRemoved()) { %>
			<div class="portlet-msg-error"><%= LanguageUtil.get(pageContext, "course-removed") %></div>
		<% } %>

		<% if (courseRecord != null) { %>
        <nter:course-last-visit
            course="<%=course%>" courseRecord="<%=courseRecord%>" finishedComponentCount="<%=finishedComponentCount%>"
            activeComponentCount="<%=activeComponentCount%>" pageContext="<%=pageContext%>"/>
		<% } %>

		<p itemprop="description"><%= course.getDescription(locale) %></p>
<%
    //Get components and resources without/with status depending on student progress
    if (courseRecord == null) {
        if (componentResults.size() > 0) {
%>
        <div class="highlightbox">
            <h4 id="contents-label"><%= LanguageUtil.get(pageContext, "course-contents") %></h4>
            <ul class="toc" role="tree" aria-labelledby="contents-label">
                <%
                int linkId=0;
                for (ComponentQueryResult componentResult : componentResults) {
                    Component component = componentResult.getComponent();
                    Courses_Components courses_components = componentResult.getCourses_Components();
                    ComponentRecord componentRecord = null;
                %>
                    <nter:component-list courseIsPurchased="<%=isPurchased%>" courseIsRemoved="<%=course.isRemoved()%>"
                                         component="<%=component%>" courses_components="<%=courses_components%>"
                                         componentRecord="<%=componentRecord%>" linkId="<%=linkId%>"
                                         pageContext="<%=pageContext%>" componentIsResource="<%=false%>"></nter:component-list>
                <%
                }
                %>
            </ul>
        </div>

<%      }
        if (resourceResults.size() > 0) { %>
        <div class="highlightbox">
            <h4 id="contents-label"><%= LanguageUtil.get(pageContext, "course-resources") %></h4>
            <ul class="toc" role="tree" aria-labelledby="contents-label">
                <%
                int linkId=0;
                for (ComponentQueryResult resourceResult : resourceResults) {
                    Component resource = resourceResult.getComponent();
                    Courses_Components courses_components = resourceResult.getCourses_Components();
                    ComponentRecord componentRecord = null;
                %>
                    <nter:component-list courseIsPurchased="<%=isPurchased%>" courseIsRemoved="<%=course.isRemoved()%>"
                                         component="<%=resource%>" courses_components="<%=courses_components%>"
                                         componentRecord="<%=componentRecord%>" linkId="<%=linkId%>"
                                         pageContext="<%=pageContext%>" componentIsResource="<%=true%>"></nter:component-list>
                <%
                }
                %>
            </ul>
        </div>
<%
        }

    } else {      //student progress exists

        if (componentRecordResults.size() > 0) {
%>

        <div class="highlightbox">
            <h4 id="contents-label"><%= LanguageUtil.get(pageContext, "course-contents") %></h4>
            <ul class="toc" role="tree" aria-labelledby="contents-label">
<%
                int linkId = 1;
                for (ComponentRecordQueryResult componentResult : componentRecordResults) {
                    Component component = componentResult.getComponent();
                    Courses_Components courses_components = componentResult.getCourses_Components();
                    ComponentRecord componentRecord = componentResult.getComponentRecord();
%>
                    <nter:component-list courseIsPurchased="<%=isPurchased%>" courseIsRemoved="<%=course.isRemoved()%>"
                        component="<%=component%>" courses_components="<%=courses_components%>"
                        componentRecord="<%=componentRecord%>" linkId="<%=linkId%>"
                        pageContext="<%=pageContext%>" componentIsResource="<%=false%>"></nter:component-list>
<%
					linkId++;
				}
%>
           </ul>
        </div>
<%
        }
        if (resourceRecordResults.size() > 0) {
%>

        <div class="highlightbox">
            <h4 id="contents-label"><%= LanguageUtil.get(pageContext, "course-resources") %></h4>
            <ul class="toc" role="tree" aria-labelledby="contents-label">
<%
                int linkId = 1;
                for (ComponentRecordQueryResult resourceResult : resourceRecordResults) {
                    Component resource = resourceResult.getComponent();
                    Courses_Components courses_components = resourceResult.getCourses_Components();
                    ComponentRecord componentRecord = null;       //resources will not have progress records
%>
                    <nter:component-list courseIsPurchased="<%=isPurchased%>" courseIsRemoved="<%=course.isRemoved()%>"
                        component="<%=resource%>" courses_components="<%=courses_components%>"
                        componentRecord="<%=componentRecord%>" linkId="<%=linkId%>"
                        pageContext="<%=pageContext%>" componentIsResource="<%=true%>"></nter:component-list>
<%
					linkId++;
				}
%>
           </ul>
        </div>
<%
        }
    }   //end component/resource display
%>

		<dl>
            <div class="course-attribute">
				<%
				String duration = course.getFriendlyDuration(pageContext);
				%>
				 <dt><%= LanguageUtil.get(pageContext,"course-description-duration") %>:</dt>
                 <dd><%= duration %> </dd>
			</div>

			<% // date and author

                String lastStatusDate = course.getFriendlyVersionDate(pageContext);
                String courseAuthor = "";
                if (course.getCourseAuthor() != null) courseAuthor = course.getCourseAuthor().getContributorName();
                if (courseAuthor.equals(" ") || courseAuthor.equals("")) {
            %>
                <div class="course-attribute">
					<dt><%= LanguageUtil.get(pageContext,"course-description-last-modified") %>:</dt>
					<dd><%= lastStatusDate %> </dd>
				</div>
            <%
                } else {
            %>
                <div class="course-attribute">
					<dt><%= LanguageUtil.get(pageContext,"course-description-last-modified") %>:</dt>
					<dd><%= lastStatusDate %> <%= LanguageUtil.get(pageContext,"by") %> <span itemprop="author"><%= courseAuthor %></span></dd>
				</div>
            <%
                }
                long reviewCount = course.getOneStarRateCount() + course.getTwoStarRateCount()
                        + course.getThreeStarRateCount() + course.getFourStarRateCount()
                        + course.getFiveStarRateCount();
				String reviewCountFormat = "num-votes-plural";
				if (reviewCount == 0) reviewCountFormat = "num-votes-zero";
				else if (reviewCount == 1) reviewCountFormat = "num-votes-singular";
            %>
			<div class="course-attribute">
                <dt><%= LanguageUtil.get(pageContext,"course-description-avg-rating") %>:</dt>
                <dd itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating">
					<span itemprop="ratingValue"><liferay-ui:ratings-score score="<%= ReviewUtil.getCourseAverageRating(course) %>" /></span>
                    <span itemprop="ratingCount"><%= LanguageUtil.format(pageContext, reviewCountFormat, reviewCount) %></span>
                </dd>
            </div>

			<%
			List<CourseRelated> relatedCourses = course.getCourseRelateds();
			if (relatedCourses.size() > 0) { %>
			<div class="course-attribute">
				<dt><%= LanguageUtil.get(pageContext,"course-description-prereq") %>:</dt>
				<dd>
					<%
						for (CourseRelated rCourse : relatedCourses) {
                            //only display prerequisite courses
							if (rCourse.getRelationshipType().equals(RelationshipType.PREREQUISITE.getDbValue())) {
                                Course relatedCourse = CourseLocalServiceUtil.fetchByCourseId(rCourse.getRelatedCourseId());
                                if (relatedCourse != null) {
					%>
							        <a href='<%= relatedCourse.getUrl() %>'><%= relatedCourse.getTitle(locale) %></a>
					<%
                                }
                            }
						}
					%>
				</dd>
			</div>
			<% } %>

            <div class="course-attribute">
				<dt><%= LanguageUtil.get(pageContext,"course-description-category") %>:</dt>
				<dd>
                    <nter:category className="<%=Course.class.getName() %>" classPK="<%= course.getPrimaryKey() %>"
								   languageId="<%= languageId %>" />
 				</dd>
			</div>

            <div class="course-attribute">
                <dt><%= LanguageUtil.get(pageContext,"course-description-keywords") %>:</dt>
                <dd itemprop="keywords">
                    <nter:keywords className="<%= Course.class.getName() %>" classPK="<%= course.getPrimaryKey() %>"/>
                </dd>
            </div>


			<% //languages
			if (course.getAvailableLanguageIds().size() > 1) {
			%>
				<div class="course-attribute">
					<dt><%= LanguageUtil.get(pageContext,"course-description-languages") %>:</dt>
					<dd>
						<%
						for (String langId : course.getAvailableLanguageIds()) {
							if (!langId.equalsIgnoreCase(locale.toString())) {
								Locale other = LocaleUtil.fromLanguageId(langId);
						%>
							 <a href="<%= course.getUrl(langId) %>"><%= other.getDisplayName(other) %></a>&nbsp;
						<%
							}
						}
						%>
					</dd>
				</div>
			<%
			}
			%>

			<%
			String copyright = course.getCopyright(locale);
			if (copyright != null && !copyright.equals("")) { %>
				<div class="course-attribute">
					<dt><%= LanguageUtil.get(pageContext,"course-description-license") %>:</dt>
					<dd><%= copyright %></dd>
				</div>
			<% } %>

			<% if (course.getPrice() > 0) { %>
				<div class="course-attribute price">
					<dt><%= LanguageUtil.get(pageContext,"course-description-price") %>:</dt>
					<dd itemprop="offers" itemscope itemtype="http://schema.org/Offer">
						<span itemprop="price">
							<fmt:formatNumber type="currency" value="<%= course.getPrice() %>"
										  currencyCode="<%= course.getPriceUnit() %>" />
						</span>
						<% if (isPurchased) { %>
						<%= LanguageUtil.get(pageContext,"course-description-purchased") %>
						<% } %>
					</dd>
				</div>
			<% } %>
		</dl>

		<%
		List<Course> versions = course.getAllVersions();
		if (versions.size() > 1) { %>
		<div class="highlightbox versions">
			<h4>Other versions of this course</h4>
			<ul>
				<%
				Boolean newest, current;
				for (Course version : versions) {
					newest = version.getCourseIri().equals(course.getSupersededByCourseIri()) ||
							(!course.hasNewerVersion() && version.getCourseIri().equals(course.getCourseIri()));
					current = version.getCourseIri().equals(course.getCourseIri());
                    double score = ReviewUtil.getCourseAverageRating(version);
					%>
				<li class="<%= current ? "current-version" : "" %> <%= newest ? "best-version" : "" %>">
					<% if (current) { %>
					<span class="title"><%= version.getTitle(locale) %></span>
					<% } else { %>
					<a class="title" href="<%= version.getUrl() %>"><%= version.getTitle(locale) %></a>
					<% } %>
					<span class="updated-date">
						<%= DateFormat.getDateInstance(DateFormat.LONG, locale).format(version.getUpdatedDate()) %>
					</span>
					<% if (newest) { %>
					<span class="best-version">Most Recent</span>
					<% } %>
					<% if (current) { %>
					<span class="current-version">(this version)</span>
					<% } %>
                    <% if (score > 0) { %>
	                    <span class="rating-label"><liferay-ui:message key="average-rating" />
		                <liferay-ui:ratings-score score="<%= score %>" /></span>
	                <% } %>
				</li>
				<% } %>
			</ul>
		</div>
		<% }

        buttonCssClass="join-course button";
        %>
		<div class="actions">
            <nter:course-button buttonCssClass="<%=buttonCssClass%>" isPurchased="<%=isPurchased%>"
                course="<%=course%>" courseRecord="<%=courseRecord%>" finishedComponentCount="<%=finishedComponentCount%>"
                activeComponentCount="<%=activeComponentCount%>" resumeComponent="<%=resumeComponent%>"
                failedComponent="<%=failedComponent%>" pageContext="<%=pageContext%>"/>

        <%  if (!course.isRemoved() && themeDisplay.isSignedIn() && courseRecord != null) {
                 if (CourseReviewLocalServiceUtil.findByCourseIdWithUserId(themeDisplay.getUserId(),
						 course.getCourseId()).size() == 0) {
        %>
				    <a href="#review" class="review-course button"><%= LanguageUtil.get(pageContext,"review-course") %></a>
		<%
                 }
		    }
        %>
        </div>

	</div>
</article>

<% } %>