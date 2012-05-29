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

<%--
    course-listing/jsp/category-tree.jsp

    This file assumes that it is included in the context of course-listing/jsp/view.jsp

--%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.SystemException" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetVocabulary" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %>

<%!

static class ListNode {
    enum EntryType { CATEGORY, INDENT, OUTDENT }
    final EntryType type;
    final AssetCategory category;
    final boolean isLeafNode;
    private boolean expanded;

    ListNode(EntryType type) {
        this(type, null, false);
    }

    ListNode(EntryType type, AssetCategory category, boolean isLeafNode) {
        this.type = type;
        this.category = category;
        this.isLeafNode = isLeafNode;
        this.expanded = false;
    }

    boolean isExpanded() {
        return expanded;
    }

    void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

// returns true if the given vocabulary should be expanded
private boolean _buildCategoryTree(AssetVocabulary vocabulary, long selectedCategoryId, List<ListNode> list) throws SystemException {
    list.clear();

    boolean expanded = false;
    List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(
            vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

    if (categories.size() > 0) {
        list.add(new ListNode(ListNode.EntryType.INDENT));
        for (AssetCategory category : categories) {
            expanded |= _buildCategoryTree(list, category, selectedCategoryId);
        }
        list.add(new ListNode(ListNode.EntryType.OUTDENT));
    }

    return expanded;
}

// returns true if the given category should be expanded
private boolean _buildCategoryTree(List<ListNode> list, AssetCategory category, long selectedCategoryId) throws SystemException {
    List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getChildCategories(
            category.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

    boolean expanded = category.getCategoryId() == selectedCategoryId;
    boolean hasChildren = categories.size() > 0;
    ListNode node = new ListNode(ListNode.EntryType.CATEGORY, category, !hasChildren);
    list.add(node);

    if (hasChildren) {
        list.add(new ListNode(ListNode.EntryType.INDENT));
        for (AssetCategory childCategory : categories) {
            expanded |= _buildCategoryTree(list, childCategory, selectedCategoryId);
        }
        list.add(new ListNode(ListNode.EntryType.OUTDENT));
    }

    node.setExpanded(expanded);
    return expanded;
}
%>

<%
String category = httpRequest.getParameter("category");
String vocabulary = httpRequest.getParameter("vocabulary");

long categoryId = 0;
try {
    categoryId = Long.parseLong(category == null ? "" : category);
}
catch (NumberFormatException e) {
    // 0 is an acceptable value in this case
}

long vocabularyId = 0;
try {
    vocabularyId = Long.parseLong(vocabulary == null ? "" : vocabulary);
}
catch (NumberFormatException e) {
    // 0 is an acceptable value in this case
}
List<AssetVocabulary> vocabularies;
try {
   vocabularies = AssetVocabularyLocalServiceUtil.getGroupVocabularies(group.getGroupId());
}
catch (PortalException e) {
    vocabularies = new ArrayList<AssetVocabulary>();
    // no vocabularies found, do not display
}
catch (SystemException e) {
    vocabularies = new ArrayList<AssetVocabulary>();
    // problem obtaining vocabulary, do not display
}

// determine if vocabulary has ExpandoValue vocabularyType = COURSE
    for (AssetVocabulary v : vocabularies) {
        try {
            String vocabularyType = (String)ExpandoValueLocalServiceUtil.getData(group.getCompanyId(),
                    AssetVocabulary.class.getName(), "AssetVocabulary", "vocabularyType", v.getPrimaryKey());
            if (vocabularyType.equals("COURSE")) {
                vocabularyExists = true;      // initialized in view.jsp
                break;
            }
        } catch (PortalException e) {
            // no expando vocabulary definition found, do not display
        } catch (SystemException e) {
            // problem obtaining expando vocabulary definition, do not display
        }
    }

if (vocabularyExists) {
%>

<nav class="category-filter">

	<h4><%= LanguageUtil.get(pageContext,"filter-by-category") %></h4>

        <ul class="toc" role="tree">
       <%
	   StringBuilder combinedCategoryTitle = new StringBuilder();
	   for (AssetVocabulary v : vocabularies) {
             try {
                    String vocabularyType = (String) ExpandoValueLocalServiceUtil.getData(group.getCompanyId(),
                            AssetVocabulary.class.getName(), "AssetVocabulary", "vocabularyType", v.getPrimaryKey());

                if (vocabularyType.equals("COURSE")) {

                    List<ListNode> categoryTree = new ArrayList<ListNode>();
                    boolean expanded = _buildCategoryTree(v, categoryId, categoryTree);

					if (expanded || v.getVocabularyId() == vocabularyId) combinedCategoryTitle.append(v.getTitle(locale));

                    if (v.getVocabularyId() == vocabularyId) { %>
                            <li role="treeitem"><%= v.getTitle(locale) %> <%
                    } else { %>
                            <li role="treeitem" <%= expanded ? "class=\"expanded\"" : "" %>>
                            <a href="<%= PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() %>/courses?vocabulary=<%= v.getVocabularyId() %>"><%= v.getTitle(locale) %>
                            </a>
            <%
                    }

                    for (ListNode node : categoryTree) {
                        if (node.type == ListNode.EntryType.INDENT) { %>
                               <ul role="group"><%
                        } else if (node.type == ListNode.EntryType.OUTDENT) { %>
                               </ul>
                               </li>
            <%
                        } else if (node.type == ListNode.EntryType.CATEGORY) {
                              AssetCategory c = node.category;

							  if (node.isExpanded() || c.getCategoryId() == categoryId) combinedCategoryTitle.insert(0, c.getTitle(locale) + " - ");

                              if (c.getCategoryId() == categoryId) { %>
                                  <li role="treeitem"><%= c.getTitle(locale) %>
                <% // intentionally unclosed so we can add sublists %> <%
                              } else {  %>
                                  <li role="treeitem" <%= node.isExpanded() ? "class=\"expanded\"" : "" %>><a
                                  href="<%= PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() %>/courses?category=<%= c.getCategoryId() %>"><%= c.getTitle(locale) %>
                                 </a><% // intentionally unclosed so we can add sublists %>  <%
                              }
                              if (node.isLeafNode) { %>
                                 </li>
            <%
                              }
                        }
                    }
                }
             } catch (PortalException e) {
               // no expando vocabulary definition found, do not display
             } catch (SystemException e) {
               // This vocabulary won't get printed, but don't cause the whole page to blow up
             }
          }
			PortalUtil.addPageSubtitle(combinedCategoryTitle.toString(), PortalUtil.getHttpServletRequest(renderRequest)); %>
        </ul> <%
}


    %>

</nav>