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

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

/**
 * @author Bruno Farache
 */
public interface SolrServerFactory {

	public List<SolrServerWrapper> getDeadServers();

	public SolrServerWrapper getLiveServer()
		throws SolrServerException;

	public List<SolrServerWrapper> getLiveServers();

	public void killServer(SolrServerWrapper serverWrapper);

	public void startServer(SolrServerWrapper serverWrapper);

}