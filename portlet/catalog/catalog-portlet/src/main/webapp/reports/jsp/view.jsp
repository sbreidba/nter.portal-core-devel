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

<%-- reports/jsp/view.jsp --%>
<%@include file="/reports/jsp/init.jsp" %>

<portlet:actionURL name="exportPdfTranscript"
                   var="exportPdfTranscript" />
<aui:form action="<%= exportPdfTranscript %>" method="post">
    <aui:fieldset>
        <aui:input name="reports-user-email" size="100" />
        <aui:button-row>
            <aui:button type="submit" value="reports-export-pdf-transcript" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>

<div class="separator"></div>

<portlet:actionURL name="exportPdfLast500Users"
                   var="exportPdfLast500Users" />
<aui:form action="<%= exportPdfLast500Users %>" method="post">
    <aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="reports-export-pdf-last-500-users" />
        </aui:button-row>
    </aui:fieldset>
</aui:form>