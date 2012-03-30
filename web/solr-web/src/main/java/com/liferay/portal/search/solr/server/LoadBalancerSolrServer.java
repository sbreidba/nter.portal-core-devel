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

package com.liferay.portal.search.solr.server;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.util.NamedList;

/**
 * @author Bruno Farache
 */
public class LoadBalancerSolrServer extends SolrServer {

	public LoadBalancerSolrServer(SolrServerFactory solrServerFactory) {
		_solrServerFactory = solrServerFactory;
	}

	@Override
	public NamedList<Object> request(SolrRequest solrRequest)
		throws SolrServerException {

		SolrServerWrapper solrServerWapper = _solrServerFactory.getLiveServer();

		try {
			return solrServerWapper.request(solrRequest);
		}
		catch (SolrException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SolrServerException(e);
		}
	}

	private SolrServerFactory _solrServerFactory;

}