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

<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@page
	import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.util.MathUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="org.nterlearning.utils.ReviewUtil" %>
<%@include file="init.jsp" %>

<%
	long reviewId = ParamUtil.getLong(request, "reviewId");
	RatingsStats stats =
		RatingsStatsLocalServiceUtil.getStats(
			CourseReview.class.getName(), reviewId);
/*	ClassLoader cl = PortalClassLoaderUtil.getClassLoader();
 	int positive =
		(int) RatingsEntryLocalServiceUtil.dynamicQueryCount(DynamicQueryFactoryUtil.forClass(
			RatingsEntry.class, cl).add(
			PropertyFactoryUtil.forName("score").gt(0.0)).add(
			PropertyFactoryUtil.forName("classNameId").eq(
				ClassNameLocalServiceUtil.getClassNameId(CourseReview.class.getName()))));
*/
	// This should equal the number of positive ratings as long as all ratings are +/-1.
	int positive = ((int)stats.getTotalScore() + stats.getTotalEntries())/2;
	double weightedScore =
		ReviewUtil.wilsonScore(
			positive, stats.getTotalEntries(), 0.05);
	CourseReviewLocalServiceUtil.updateCourseReviewRating(
		reviewId, weightedScore);
	double averageScore =
		MathUtil.format(stats.getAverageScore(), 1, 1);
	JSONObject json = JSONFactoryUtil.createJSONObject();
	json.put("totalEntries", stats.getTotalEntries());
	json.put("totalScore", stats.getTotalScore());
	json.put("averageScore", averageScore);
	out.print(json.toString());
%>