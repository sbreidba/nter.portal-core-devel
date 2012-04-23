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

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PropsKeys" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portlet.ratings.model.RatingsStats" %>
<%@ page import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseReviewLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.service.CourseRecordLocalServiceUtil" %>
<%@ page import="org.nterlearning.datamodel.catalog.model.*" %>
<%@ page import="org.nterlearning.course.util.*" %>

<%@ page import="org.nterlearning.course.enumerations.CourseRecordFilterType" %>
<%@ page import="org.nterlearning.course.enumerations.CourseRecordSortType" %>
<%@ page import="org.nterlearning.course.enumerations.ComponentRecordFilterType" %>
<%@ page import="org.nterlearning.course.enumerations.ComponentRecordSortType" %>
<%@ page import="org.nterlearning.course.enumerations.CompletionStatusType" %>
<%@ page import="org.nterlearning.course.enumerations.RelationshipType" %>

<%@ page import="javax.portlet.MimeResponse" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="nter" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>