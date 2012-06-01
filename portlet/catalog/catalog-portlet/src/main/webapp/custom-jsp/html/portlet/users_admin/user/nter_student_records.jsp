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

<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="org.apache.axis.client.Service" %>
<%@ page import="org.apache.axis.client.Call" %>
<%@ page import="javax.xml.rpc.ParameterMode" %>
<%@ page import="javax.xml.namespace.QName" %>

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
    User selUser = (User)request.getAttribute("user.selUser");
    String endpoint = "http://localhost:8080/nter-catalog-portlet/api/axis";

    Service service = new Service();

//    Call courseRecordCall = new Call(service);
//    courseRecordCall.setTargetEndpointAddress(endpoint + "/Plugin_CATALOG_CourseRecordService?wsdl");
//    courseRecordCall.setOperationName("findByUserId");
//    courseRecordCall.addParameter("userId", org.apache.axis.Constants.XSD_LONG, ParameterMode.IN);
//    courseRecordCall.setReturnType(new QName());

//    Object resp = courseRecordCall.invoke(new Object[]{selUser.getUserId()});

%>

Student records will be displayed here.