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

package com.liferay.portal.search.solr.facet;

import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class SolrFacetQueryCollector implements FacetCollector {

	public SolrFacetQueryCollector(
		String fieldName, Map<String, Integer> facetQueries) {

		_fieldName = fieldName;

		for (Map.Entry<String, Integer> entry : facetQueries.entrySet()) {
			String term = _getTerm(entry.getKey());
			Integer count = entry.getValue();

			_counts.put(term, count);
		}
	}

	public String getFieldName() {
		return _fieldName;
	}

	public TermCollector getTermCollector(String term) {
		Integer count = _counts.get(term);

		return new SolrTermCollector(term, count.intValue());
	}

	public List<TermCollector> getTermCollectors() {
		if (_termCollectors != null) {
			return _termCollectors;
		}

		List<TermCollector> termCollectors = new ArrayList<TermCollector>();

		for (Map.Entry<String, Integer> entry : _counts.entrySet()) {
			Integer count = entry.getValue();

			TermCollector termCollector = new SolrTermCollector(
				entry.getKey(), count.intValue());

			termCollectors.add(termCollector);
		}

		_termCollectors = termCollectors;

		return _termCollectors;
	}

	private String _getTerm(String term) {
		return term.substring(_fieldName.length() + 1);
	}

	private Map<String, Integer> _counts = new HashMap<String, Integer>();
	private String _fieldName;
	private List<TermCollector> _termCollectors;

}