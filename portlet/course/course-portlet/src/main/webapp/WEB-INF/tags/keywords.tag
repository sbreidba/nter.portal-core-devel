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

<%-- keywords.tag --%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ tag import="java.util.List" %>
<%@ tag import="com.liferay.portlet.asset.model.AssetTag" %>
<%@ tag import="com.liferay.portal.kernel.log.Log" %>
<%@ tag import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ tag import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.util.PortalUtil" %>
<%@ tag import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ tag import="com.liferay.portal.model.Group" %>
<%@ tag import="com.liferay.portal.service.ClassNameLocalServiceUtil" %>

<%@ attribute name="className" required="true" %>
<%@ attribute name="classPK" type="java.lang.Long" required="true" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
    try {
        Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
        long classId = ClassNameLocalServiceUtil.getClassNameId(className);
        String hrefPrefix = PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL();

        List<AssetTag> tags = AssetTagLocalServiceUtil.getTags(classId, classPK);
        %>

        <div class="entry-tags">

        <%
        for (AssetTag tag : tags) {
            String tagHref =  hrefPrefix + "/courses?keyword=" + tag.getName();
            %>

            <a class="tag" href="<%= tagHref%>"><%=tag.getName()%></a>

            <%
        }
        %>
        </div>
    <%
    }
    catch (Exception e) {
        _log.error(e.getMessage());    
    }
%>

<%! private static final Log _log = LogFactoryUtil.getLog("courseportlet.docroot.tags.keywords.tag"); %>
