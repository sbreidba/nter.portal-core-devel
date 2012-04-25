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

/**
 * And immutable object pair containing Liferay Search ResultRow and it's
 * associated search score as a double.
 *
 * @author bblonski
 *
 */
public class ResultAndScorePair {

	private final OpenSearchResult resultRow;
	private final double score;

	public ResultAndScorePair(OpenSearchResult resultRow, double score) {
		this.resultRow = resultRow;
		this.score = score;
	}

	public OpenSearchResult getResultRow() {
		return resultRow;
	}

	public double getSearchScore() {
		return score;
	}

}