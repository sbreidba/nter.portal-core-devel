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

%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_score_page") + StringPool.UNDERLINE;

double score = GetterUtil.getDouble((String)request.getAttribute("liferay-ui:ratings-score:score"));

NumberFormat numberFormat = NumberFormat.getInstance();

numberFormat.setMaximumFractionDigits(1);
numberFormat.setMinimumFractionDigits(0);

String scoreString = numberFormat.format(score);

String[] scoreVars = {scoreString, "5"};
String scoreStringPartial = LanguageUtil.format(pageContext, "star-rating", scoreString);
String scoreStringTotal = LanguageUtil.format(pageContext, "star-rating-total", scoreVars);
%>

<c:choose>
	<c:when test="<%= themeDisplay.isFacebook() %>">
		<%= scoreStringPartial %>
	</c:when>
	<c:otherwise>
		<div class="taglib-ratings score aui-rating aui-starrating" id="<%= randomNamespace %>averageRating">
			<div class="aui-helper-clearfix aui-rating-content" id="<%= randomNamespace %>averageRatingContent">

				<%
				for (int i = 1; i <= 5; i++) {
				%>

					<div class="aui-rating-element <%= (i <= score) ? "aui-rating-element-on" : StringPool.BLANK %>"></div>

				<%
				}
				%>

				<span class="aui-helper-hidden-accessible aui-rating-total"><%= scoreStringTotal %></span>
				<meta itemprop="ratingValue" content="<%=scoreString%>" />
				<meta itemprop="bestRating" content="5" />

			</div>
		</div>
		
		<aui:script use="aui-rating">
			A.one('#<%= randomNamespace %>averageRating').on(
				'mouseenter',
				function(event) {
					var el = A.Node.getDOMNode(event.currentTarget);

					Liferay.Portal.ToolTip.show(el, '<%= scoreStringPartial %>');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>