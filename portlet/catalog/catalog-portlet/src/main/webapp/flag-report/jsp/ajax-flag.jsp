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

<%@include file="/flag-report/jsp/init.jsp" %>

<%
	JSONObject json = JSONFactoryUtil.createJSONObject();

	PortletRequest portletRequest =
		(PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

	if(SessionErrors.isEmpty(portletRequest)) {
		json.put("success", true);
	}else {
		json.put("success", false);
		JSONArray errors = JSONFactoryUtil.createJSONArray();
		Iterator<String> itr = SessionErrors.iterator(portletRequest);
		while(itr.hasNext()) {
			errors.put(LanguageUtil.get(pageContext, itr.next()));
		}
		json.put("errors", errors);
	}
	json.put(NterKeys.MODERATOR_COMMENT, ParamUtil.getString(request, NterKeys.MODERATOR_COMMENT));
	out.print(json.toString());
%>