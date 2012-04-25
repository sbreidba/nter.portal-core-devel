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

<%@include file="/course-management/jsp/init.jsp" %>

<%
    String returnUrl = ParamUtil.getString(request, "redirect");
    returnUrl += "&tabs1=" + ParamUtil.getString(request, "tabs1", "Active");
    returnUrl += "&cur=" + ParamUtil.getString(request, "listCur", "1");
    returnUrl += "&delta=" + ParamUtil.getString(request, "listDelta", "10");

    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, themeDisplay.getLocale());
    
    long courseId = ParamUtil.getLong(request, "resourcePrimKey");
    Course course = CourseLocalServiceUtil.getCourse(courseId);

    String releaseOnDateString = LanguageUtil.get(pageContext, "course-management-details-not-released");
    if (course.getReleaseOnDate() != null) {
        releaseOnDateString = (course.getReleaseOnDate().before(course.getCreateDate()))
                                      ? df.format(course.getCreateDate()) 
                                      : df.format(course.getReleaseOnDate());
    }

    String acceptUntilDate = "";
    if (course.getAcceptUntilDate() != null) {
        acceptUntilDate = df.format(course.getAcceptUntilDate());
        
        if (course.getAcceptUntilDate().before(new Date())) {
            acceptUntilDate += " " + LanguageUtil.get(pageContext, "course-management-details-expired");
        }
    }

    String ratingLevel = "";
    if (!course.getRatingLevel().isEmpty()) {
        ratingLevel = course.getRatingLevel(locale) + " ";
        ratingLevel += LanguageUtil.get(pageContext, "course-management-details-rating-reason") + " ";
        ratingLevel += course.getRatingReason(locale);
    }

    String versionString = LanguageUtil.get(pageContext, "course-management-details-unknown");
    if ((course.getVersion() != null) && (course.getVersionDate() != null)) {
        versionString = course.getVersion() + ", ";
        versionString += LanguageUtil.get(pageContext, "course-management-details-version-created-on") + " ";
        versionString += df.format(course.getVersionDate());
    }

    String priceUnit = course.getPriceUnit().isEmpty() ? "USD" : course.getPriceUnit();
%>

<a href='<%= returnUrl %>' cssClass="moreLink">
    <%= LanguageUtil.get(pageContext, "course-management-details-return") %>    
</a>

<div class="course-detail">
    <h3 class="course-title"><a href='<%= course.getUrl() %>'><%= course.getTitle(locale) %></a></h3>
    <dl>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-iri") %>: </dt><dd><%= course.getCourseIri() %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-create-date") %>: </dt><dd><%= df.format(course.getCreateDate()) %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-released-date") %>: </dt><dd><%= releaseOnDateString %></dd></div>

        <% if (course.getAcceptUntilDate() != null) { %>
            <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-accept-until-date") %>: </dt><dd><%= acceptUntilDate %></dd></div>
        <% } %>

        <% if (course.getRemoved()) { %>
            <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-removed-date") %>: </dt><dd><%= df.format(course.getRemovedDate()) %></dd></div>
        <% } %>         

        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-version") %>: </dt><dd><%= versionString %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-description") %>: </dt><dd><%= course.getDescription(locale) %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-copyright") %>: </dt><dd><%= course.getCopyright(locale) %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-rating-level") %>: </dt><dd><%= ratingLevel %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-duration") %>: </dt><dd><%= course.getFriendlyDuration(pageContext) %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-accessed") %>: </dt><dd><%= LanguageUtil.format(pageContext, "course-management-details-times", course.getAccessCount()) %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-completed") %>: </dt><dd><%= LanguageUtil.format(pageContext, "course-management-details-times", course.getCompletedCount()) %></dd></div>
        <div class="course-attribute"><dt><%= LanguageUtil.get(pageContext, "course-management-details-price") %>: </dt><dd><fmt:formatNumber type="currency" value="<%= course.getPrice() %>" currencyCode="<%= priceUnit %>" /></dd></div>
    </dl>
</div>


<%--<div class="separator"></div>
<div class="course-detail">
    <h3 class="course-title"><%=LanguageUtil.get(pageContext, "course-management-details-categories")%></h3>
    <nter:category className="<%=Course.class.getName() %>" classPK="<%= course.getPrimaryKey() %>"  />
</div>

<div class="separator"></div>
<div class="course-detail">
    <h3 class="course-title"><%=LanguageUtil.get(pageContext, "course-management-details-tags")%></h3>
    <liferay-ui:asset-tags-summary
			classPK="<%= course.getCourseId() %>"
			className="<%= Course.class.getName() %>"
			portletURL="<%= renderResponse.createRenderURL() %>" />
</div>--%>
