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

<%@include file="/component-details/jsp/init.jsp" %>

<%

HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
boolean isPurchased = false;
Component component = null;

long userId = 0;

String componentParam = httpRequest.getParameter("ccid");

// only someone signed in, and who has view rights to the component-search portlet
// can view this page
if (!themeDisplay.isSignedIn() ||
    !permissionChecker.hasPermission(0, NterKeys.COMPONENT_SEARCH_PORTLET,
            themeDisplay.getCompanyId(), ActionKeys.VIEW)) {
    %>
    <div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext, "component-details-log-in") %></div>
    <%
}
else if (Validator.isNull(componentParam)) {
    %>
    <div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext, "component-details-not-found") %></div>
    <%
}
else {
    long componentId = Long.parseLong(componentParam);
    try {
    component = ComponentLocalServiceUtil.findByComponentId(componentId);

    // determine language
    String languageId = httpRequest.getParameter("lang");
    if (languageId != null) {
        locale = LocaleUtil.fromLanguageId(languageId);
    }

    if (themeDisplay.isSignedIn()) {
            isPurchased = component.isPurchased(themeDisplay.getUserId());
        }
    }
        catch (Exception e) {
        %>
        <div class="portlet-msg-alert"><%= LanguageUtil.get(pageContext, "component-details-not-found") %></div>
        <%
        return;
        }
%>

<article class="course-details" itemscope itemtype="http://schema.org/CreativeWork">
    <div class="course-image">
        <img src="/nter-theme/images/default_images/course_default_large.jpg"
             class="main-image" itemprop="image" />
        <ul class="thumbnails"></ul>
    </div>

    <div class="course-description">
        <h3 class="course-title" itemprop="name"><%= component.getTitle() %></h3>
        <p itemprop="description"><%= component.getDescription() %></p>

        <dl>
            <div class="course-attribute">
                <%
                    String author = "";
                    List<Contributor> authors =
                            ComponentLocalServiceUtil.getAuthors(component);
                    for (int i = 0; i < authors.size(); i++) {
                        if (i == 0) {
                            author = authors.get(i).getContributorName();
                        }
                        else if (i < (authors.size() - 1)) {
                            author = author + ", " + authors.get(i).getContributorName();
                        }
                        else {
                            author = author + "and " + authors.get(i).getContributorName();
                        }
                    }
                %>
                <dt><%= LanguageUtil.get(pageContext,"component-details-authors") %>:</dt>
                <dd><%= author %> </dd>
            </div>

            <div class="course-attribute">
                <dt><%= LanguageUtil.get(pageContext, "component-details-last-modified") %>:</dt>
                <dd><%= component.getFriendlyUpdateDate(pageContext) %></dd>
            </div>

            <div class="course-attribute">
                <dt><%= LanguageUtil.get(pageContext, "component-details-copyright")%>:</dt>
                <dd><%= component.getCopyright(locale)%></dd>
            </div>

            <div class="course-attribute">
                <dt><%= LanguageUtil.get(pageContext, "component-details-price")%>:</dt>
                <dd itemprop="offers" itemscope itemtype="http://schema.org/Offer">
                    <span itemprop="price">
                        <% if (component.getPrice() > 0) { %>
                            <fmt:formatNumber type="currency"
                                              value='<%= component.getPrice() %>'
                                              currencyCode='<%= component.getPriceUnit() %>'/>
                        <% } else { %>
                            <%= LanguageUtil.get(pageContext, "component-details-price-free")%>                            
                        <% } %>
                    </span>
                </dd>
            </div>
        </dl>

        <%  if (ComponentLocalServiceUtil.getExternalLinks(component).size() > 0) { %>
                <div class="actions">
                    <nter:component-button
                            buttonCssClass="join-course button"
                            isPurchased='<%= isPurchased %>'
                            component='<%= component %>'
                            pageContext='<%= pageContext %>'/>
                </div> <%
            }
            else { %>
                <div class="highlightbox">
                    <%= LanguageUtil.get(pageContext, "component-details-currently-unavailable") %>    
                </div>
            <% }
        %>
    </div>
</article>
<% } %>
