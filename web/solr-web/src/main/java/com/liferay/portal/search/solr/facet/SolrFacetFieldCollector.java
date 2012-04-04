/*
 National Training and Education Resource (NTER)
 Copyright (C) 2011  SRI International

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

package com.liferay.portal.search.solr.facet;

import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.FacetField;

/**
 * @author Raymond Augé
 */
public class SolrFacetFieldCollector implements FacetCollector {

	public SolrFacetFieldCollector(String fieldName, FacetField facetField) {
		_fieldName = fieldName;

		List<Count> counts = facetField.getValues();

		if (counts != null) {
			for (Count count : counts) {
				_counts.put(count.getName(), count);
			}
		}
	}

	public String getFieldName() {
		return _fieldName;
	}

	public TermCollector getTermCollector(String term) {
		Count count = _counts.get(term);
        int c = (count != null) ? (int)count.getCount() : 0;

		return new SolrTermCollector(term, c);
	}

	public List<TermCollector> getTermCollectors() {
		if (_termCollectors != null) {
			return _termCollectors;
		}

		List<TermCollector> termCollectors = new ArrayList<TermCollector>();

		for (Map.Entry<String, Count> entry : _counts.entrySet()) {
			Count count = entry.getValue();

			TermCollector termCollector = new SolrTermCollector(
				entry.getKey(), (int)count.getCount());

			termCollectors.add(termCollector);
		}

		_termCollectors = termCollectors;

		return _termCollectors;
	}

	private Map<String, Count> _counts = new HashMap<String, Count>();
	private String _fieldName;
	private List<TermCollector> _termCollectors;

}