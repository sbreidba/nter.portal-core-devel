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

<%                                                                            com.sri.nter
String redirectUrl = ParamUtil.getString(request, "redirect");

PortalUtil.addPageSubtitle(LanguageUtil.get(pageContext, "rate-review"), PortalUtil.getHttpServletRequest(renderRequest));
// we should be using renderResponse.createElement (http://blogs.oracle.com/deepakg/entry/setting_markup_head_elements_in)
// but it doesn't seem to work in all circumstances, e.g. maximized portlets
HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(request);
Object oldMeta = httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
List meta = new ArrayList();
if (oldMeta != null) meta = (List) httpRequest.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
meta.add("<meta name=\"robots\" content=\"noindex\" />");
httpRequest.setAttribute(MimeResponse.MARKUP_HEAD_ELEMENT, meta);
%>

<p><%= LanguageUtil.get(pageContext,"meta-thanks") %></p>
<a href="<%= redirectUrl %>" class="button"><%= LanguageUtil.get(pageContext,"back-to-course") %></a>