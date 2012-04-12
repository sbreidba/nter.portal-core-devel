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

<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.JavaConstants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="org.nterlearning.course.util.NterKeys" %>
<%@ page import="javax.portlet.PortletRequest" %>
<%@ page import="java.util.Iterator" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<portlet:defineObjects/>
<liferay-theme:defineObjects/>

<%
	JSONObject json = JSONFactoryUtil.createJSONObject();

	PortletRequest portletRequest =
		(PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

	if(SessionErrors.isEmpty(portletRequest)) {
		json.put("success", true);
		json.put(NterKeys.AVERAGE_RATING, ParamUtil.getDouble(request, NterKeys.AVERAGE_RATING));
        JSONArray histogram = JSONFactoryUtil.createJSONArray();
        int[] histValues = ParamUtil.getIntegerValues(request, NterKeys.REVIEW_HISTOGRAM);
        for(int i=0; i<histValues.length; i++) {
            histogram.put(histValues[i]);
        }
        json.put(NterKeys.REVIEW_HISTOGRAM, histogram);
	}else {
		json.put("success", false);
		JSONArray errors = JSONFactoryUtil.createJSONArray();
		Iterator<String> itr = SessionErrors.iterator(portletRequest);
		while(itr.hasNext()) {
			errors.put(LanguageUtil.get(pageContext, itr.next()));
		}
		json.put("errors", errors);
	}
	json.put(NterKeys.REVIEW_CONTENT, ParamUtil.getString(request, NterKeys.REVIEW_CONTENT));
	json.put(NterKeys.REVIEW_RATING, ParamUtil.getDouble(request, NterKeys.REVIEW_RATING));
	json.put(NterKeys.REVIEW_COUNT, ParamUtil.getDouble(request, NterKeys.REVIEW_COUNT));
	out.print(json.toString());
%>