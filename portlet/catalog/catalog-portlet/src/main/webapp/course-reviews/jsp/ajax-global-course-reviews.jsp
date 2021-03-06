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

<%@include file="init.jsp" %>

<%
	long courseId = ParamUtil.getLong(request, NterKeys.COURSE_ID);
	int start = ParamUtil.getInteger(request, "start");
	int end = ParamUtil.getInteger(request, "end");
	if(end < start) {
		end = (int) GlobalCourseReviewLocalServiceUtil.countValidByCourseId(courseId);
	}
	List<GlobalCourseReview> globalReviews = GlobalCourseReviewLocalServiceUtil.findValidByCourseId(courseId, start, end);
	if (!globalReviews.isEmpty()) {
%>
	<% for (GlobalCourseReview globalReview : globalReviews) { %>
	<% request.setAttribute("globalReview", globalReview); %>
	<nter:globalReview globalReview="<%=globalReview%>" pageContext="<%=pageContext%>" />
	<% } %>
<% } %>