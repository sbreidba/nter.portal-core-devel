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

<%@ include file="init.jsp" %>

<portlet:actionURL name="forceCrawl" var="forceCrawlURL" />
<portlet:actionURL name="purgeIndex" var="purgeIndexURL" />

<liferay-ui:error key="crawl-cp-master-node-error"
                  message="crawl-cp-master-node-error" />
<liferay-ui:error key="crawl-cp-master-node-purge-error"
                  message="crawl-cp-master-node-purge-error" />

<%
    CrawlTool mCrawlTool = CrawlTool.getInstance();
    String indexerName = mCrawlTool.getIndexerName().equals("lucene") ? "Lucene Indexer" : "Solr Indexer";
%>

<article class="nutch-crawler">
<div>
<%
    if (!mCrawlTool.isMaster()) { %>
        <p><%= LanguageUtil.get(pageContext, "crawl-cp-not-master-node") %></p> <%
    }
    else { %>
        <h3 class="nutch-crawler-heading"><%= LanguageUtil.get(pageContext, "crawl-cp-status") %></h3>
        <div id="<portlet:namespace/>status-block"></div>
        <br>

        <h3 class="nutch-crawler-heading"><%= LanguageUtil.get(pageContext, "crawl-cp-configuration") %></h3>
        <div id="<portlet:namespace/>config-block">
            <div class="nutch-attribute"><dt><%= LanguageUtil.get(pageContext, "crawl-cp-crawl-depth") %>&nbsp;&nbsp;</dt><dd><%= mCrawlTool.getMaxDepth() %></dd></div>
            <div class="nutch-attribute"><dt><%= LanguageUtil.get(pageContext, "crawl-cp-threads") %>&nbsp;&nbsp;</dt><dd><%= mCrawlTool.getMaxThreads() %></dd></div>
            <div class="nutch-attribute"><dt><%= LanguageUtil.get(pageContext, "crawl-cp-indexer-type") %>&nbsp;&nbsp;</dt><dd><%= indexerName %></dd></div>
            <%
                if (mCrawlTool.getIndexerName().equals("solr")) { %>
                    <div class="nutch-attribute"><dt><%= LanguageUtil.get(pageContext, "crawl-cp-indexer-url") %>&nbsp;&nbsp;</dt><dd><%= mCrawlTool.getSolrUrl() %></dd></div> <%
                }
            %>
        </div>
    <%
    }
%>
</div>

<%
    if (CrawlTool.getInstance().isMaster()) { %>
        <div class="separator"></div>

        <h3 class="nutch-crawler-heading"><%= LanguageUtil.get(pageContext, "crawl-cp-force-crawl-heading") %></h3>
        <p><%= LanguageUtil.get(pageContext, "crawl-cp-force-crawl-label") %></p>

        <aui:form action="<%= forceCrawlURL.toString() %>" method="post">
            <aui:fieldset>
                <aui:button type="submit" value="crawl-cp-force-crawl" />
            </aui:fieldset>
        </aui:form>

        <div class="separator"></div>
        <h3 class="nutch-crawler-heading"><%= LanguageUtil.get(pageContext, "crawl-cp-force-purge")%></h3>
        <p><%= LanguageUtil.get(pageContext, "crawl-cp-force-purge-label") %></p>

        <aui:form action="<%= purgeIndexURL.toString() %>" method="post">
            <aui:fieldset>
                <aui:button type="submit" value="crawl-cp-force-purge" />
            </aui:fieldset>
        </aui:form>  <%
    }
%>
</article>

<aui:script>
function loadStatus(A) {
    AUI().use('aui-io', function(A) {
        var block = A.one('#<portlet:namespace />status-block');
        var url = "<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="jspPage" value="/nutch-crawler/jsp/cp/statusblock.jsp" /></portlet:renderURL>";

        A.io.request(url,
            {on: {
                success: function(A) {
                    block.html(this.get('responseData'));
                }
            }
        });
    });

    return false;
}

AUI().ready('aui-io', function(A) {
    loadStatus(A);

    var auto_refresh = setInterval(function() {loadStatus(A)}, 10000);
});
</aui:script>