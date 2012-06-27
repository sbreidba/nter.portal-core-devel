/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.nterlearning.course.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.comparator.PortletTitleComparator;
import groovy.json.StringEscapeUtils;
import org.apache.lucene.queryParser.QueryParser;
import org.nterlearning.course.util.NterKeys;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * This class provides generic search tools to be used by both CourseSearchUtil
 * and ComponentSearchUtil.
 */
public class SearchUtil {

    public static final HashSet<String> SEARCH_PORTLETS = new HashSet<String>();
    static {
        SEARCH_PORTLETS.add(PortletKeys.BLOGS);
        SEARCH_PORTLETS.add(PortletKeys.MESSAGE_BOARDS);
        SEARCH_PORTLETS.add(PortletKeys.WIKI);
        SEARCH_PORTLETS.add(NterKeys.COMPONENT_SEARCH_PORTLET);
        SEARCH_PORTLETS.add(NterKeys.COURSE_SEARCH_PORTLET);
        SEARCH_PORTLETS.add(NterKeys.EXTERNAL_SEARCH_PORTLET);
    }


    public static List<Portlet> getSearchablePortlets(Company company,
        ServletContext application, Locale locale,
        PermissionChecker permissionChecker, long groupId, String primarySearch,
        boolean includeSystemPortlets)
            throws SystemException, PortalException {

        List<Portlet> portlets =
                PortletLocalServiceUtil.getPortlets(company.getCompanyId(),
                        includeSystemPortlets, false);
        portlets = ListUtil.sort(portlets,
                        new PortletTitleComparator(application, locale));
        Iterator itr = portlets.iterator();

        while (itr.hasNext()) {

            Portlet portlet = (Portlet) itr.next();
            if (Validator.isNull(portlet.getOpenSearchClass())) {
                itr.remove();
                continue;
            }

            OpenSearch openSearch = portlet.getOpenSearchInstance();
            if (!openSearch.isEnabled()) {
                itr.remove();
                continue;
            }

            String portletId = portlet.getPortletId();
            if (!SEARCH_PORTLETS.contains(portletId)) {
                itr.remove();
                continue;
            }
            
            if (groupId != 0) {
                long curPlid = PortalUtil.getPlidFromPortletId(groupId, portlet.getPortletId());

                if (!PortletPermissionUtil.contains(permissionChecker, curPlid,
                        portlet, ActionKeys.VIEW)) {
                    itr.remove();
                    continue;
                }
            }
            else {
                if (portletId.equals(NterKeys.COMPONENT_SEARCH_PORTLET) &&
                    !permissionChecker.hasPermission(groupId, portletId,
                            permissionChecker.getCompanyId(),
                            ActionKeys.VIEW)) {
                    itr.remove();
                    continue;
                }
            }
        }

        return portlets;
    }


    public static SearchContainer getGlobalSearchContainer(
            RenderRequest renderRequest, PortletURL portletURL,
            PageContext pageContext,  String keywords) {

        SearchContainer globalSearchContainer = new SearchContainer(
                renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM,
                100, portletURL, null, LanguageUtil.format(pageContext,
                        "no-results-were-found-that-matched-the-keywords-x",
                        "<strong>" + HtmlUtil.escape(keywords) + "</strong>"));
        globalSearchContainer.setIteratorURL(portletURL);
        globalSearchContainer.setDeltaConfigurable(true);

        return globalSearchContainer;
    }


    public static String escapeKeywords(String keywords) {
        // the double escape is deal with http://issues.liferay.com/browse/LPS-24609
        return QueryParser.escape(QueryParser.escape(keywords));
    }

    public static String unEscapeKeywords(String keywords) {
        return StringEscapeUtils.unescapeJava(StringEscapeUtils.unescapeJava(keywords));
    }
}
