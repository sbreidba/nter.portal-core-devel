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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.GroupLocalServiceUtil;

import org.nterlearning.course.util.NterKeys;
import org.nterlearning.datamodel.catalog.model.Course;
import org.nterlearning.datamodel.catalog.service.CourseLocalServiceUtil;

import java.util.*;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

public class CourseSearchUtil {

	/**
	 * Sorts a list of courses on the given property name. The property name
     * must correspond to a getter method. ie. the property
     * <code>"courseId"</code> corresponds to <code>getCourseId()</code>.
	 *
	 * @param results List of courses to be modified.
	 * @param property Name of property to sort on.
	 * @param asc True to sort ascending, false to sort descending.
	 * @return a sorted list of courses.
	 */
	@SuppressWarnings("unchecked")
	public static List<Course> orderCoursesBy(List<Course> results,
                                final String property, final boolean asc) {

		Comparator<?> comparator;
		if (asc) {
			comparator = new ComparableComparator();
		} else {
			comparator = new ReverseComparator(new ComparableComparator());
		}
		BeanComparator comp = new BeanComparator(property, comparator);
		Collections.sort(results, comp);

		return results;
	}

	public static List<Course> getCoursesBySearchResults(
			List<Document> search) throws PortalException, SystemException {

		List<Course> results = new ArrayList<Course>();
		for (Document doc : search) {
			results.add(CourseLocalServiceUtil.getCourse(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
		}
		return results;
	}

	public static SearchContainer<Course> search(SearchContainer<Course> searchContainer, boolean removed,
			long companyId, long groupId) throws PortalException, SystemException {

		List<Course> results = new ArrayList<Course>();
		int total = 0;
		String searchTerms = searchContainer.getPortletRequest().getParameter(NterKeys.SEARCH_TERMS);
		if (Validator.isNotNull(searchTerms)) { // Lucene Search
			// Get all hits
			Hits hits = CourseLocalServiceUtil.search(companyId, groupId, searchTerms, false,
					searchContainer.getStart(), searchContainer.getEnd());
			// Set total
			total = hits.getLength(); // Count total hits
			// Set results sublist of current page
			results = CourseSearchUtil.getCoursesBySearchResults(hits.toList());
			// Save searchTerms for pagination
			searchContainer.getIteratorURL().setParameter(NterKeys.SEARCH_TERMS, searchTerms);
		} else {

			DynamicQuery query;
			DynamicQuery query2;
			// if the contextual group is 'global', we want to show all courses
			if (GroupLocalServiceUtil.getGroup(groupId).isCompany()) {
				// TODO why does getCoursesCount() return an int and
				// dynamicQueryCount() return a long?
				// Can't reuse the same query twice for some reason...
				query = DynamicQueryFactoryUtil.forClass(Course.class).add(
						PropertyFactoryUtil.forName("companyId").eq(companyId)).add(
						PropertyFactoryUtil.forName("removed").eq(removed));
				query2 = DynamicQueryFactoryUtil.forClass(Course.class).add(
						PropertyFactoryUtil.forName("companyId").eq(companyId)).add(
						PropertyFactoryUtil.forName("removed").eq(removed));
			} else {
				// TODO why does getCoursesCount() return an int and
				// dynamicQueryCount() return a long?
				// Can't reuse the same query twice for some reason...
				query = DynamicQueryFactoryUtil.forClass(Course.class).add(
						PropertyFactoryUtil.forName("groupId").eq(groupId)).add(
						PropertyFactoryUtil.forName("removed").eq(removed));
				query2 = DynamicQueryFactoryUtil.forClass(Course.class).add(
						PropertyFactoryUtil.forName("groupId").eq(groupId)).add(
						PropertyFactoryUtil.forName("removed").eq(removed));
			}
			total = (int) CourseLocalServiceUtil.dynamicQueryCount(query);
			results = (List<Course>) CourseLocalServiceUtil.dynamicQuery(query2, searchContainer.getStart(),
					searchContainer.getEnd());
		}
        
		// Assign results to search container
		searchContainer.setResults(results);
		searchContainer.setTotal(total);
		return searchContainer;
	}

    private static final Log _log = LogFactoryUtil.getLog(CourseSearchUtil.class);
}