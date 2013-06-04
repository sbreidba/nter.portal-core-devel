/*
 National Training and Education Resource (NTER)
 Copyright (C) 2012  SRI International

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
 */


package org.nterlearning.course.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseOpenSearchImpl;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import org.apache.lucene.queryParser.QueryParser;
import org.nterlearning.atom.parser.portlet.ServiceRegistryClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

public class ExternalOpenSearchImpl extends BaseOpenSearchImpl {

	private static String searchAddress;

	public static String setSearchAddressFromRegistry() {
		searchAddress = StringPool.BLANK;
		try {
			searchAddress = new ServiceRegistryClient().getSearchService();
		}catch (Exception e){
			_log.error(e);
		}
		return searchAddress;
	}

	public static String getSearchAddress() {
		return searchAddress;
	}

	@Override
	public String search(
		HttpServletRequest request, long groupId, long userId, String keywords,
		int startPage, int itemsPerPage, String format)
		throws SearchException {

		HttpClient client = new HttpClient();
		String query;
		try {
			query = qetQuery(keywords, startPage, itemsPerPage);
		}
		catch (Exception e) {
			_log.error("Cannot find search service");
			throw new SearchException();
		}
		_log.info(query);
		HttpMethod method = new GetMethod(query);
		String results = null;
		try {
			client.executeMethod(method);
			results = method.getResponseBodyAsString();
		}
		catch (Exception e) {
			if (e.getCause() != null &&
				e.getCause().getClass().equals(
					javax.net.ssl.SSLHandshakeException.class)) {
				_log.error("Untrusted certificate.  Please import certificate into your trusted Java keystore.");
			}
			else {
				_log.error("Error querying server: " + e.getMessage());
			}
			throw new SearchException(e);
		}

		return results;
	}

	private String qetQuery(String keywords, int startPage, int itemsPerPage)
		throws Exception {

		if (Validator.isNull(searchAddress)) {
            setSearchAddressFromRegistry();
        }

        String queryTerms = URLEncoder.encode(QueryParser.escape(keywords), StringPool.UTF8);

		String query =
			String.format(
				"%s?q=%s&page=%s&resultsPerPage=%s", searchAddress,
				queryTerms, startPage, itemsPerPage);
		return query;
	}

	private static final Log _log =
		LogFactoryUtil.getLog(ExternalOpenSearchImpl.class);
}