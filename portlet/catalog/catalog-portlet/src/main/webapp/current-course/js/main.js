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

AUI().ready('liferay-portlet-url', function(A) {
	var maxH = 0;
	var prevLeft = 0;
	var items = A.all('.course-status-section .course');
	var row = [];
	function processRow(row) {
		for (var i = 0; i < row.length; i++) {
			row[i].setStyle('minHeight',maxH);
		}
	}
	items.each(function(item, index) {
		var left = item.getX();
		if (left <= prevLeft) {
			processRow(row);
			row = []
			maxH = 0;
			item.setStyle('minWidth', item.getStyle('width'));	// fix for IE7 display glitch
			item.setStyle('clear', 'left');
			left = item.getX();
		}
		var curH = parseFloat(item.getStyle('height'));
		if (curH > maxH) maxH = curH;
		prevLeft = left;
		row.push(item);
	});
	processRow(row);


	function trackCourseEvent(action, event) {
		var course = event.currentTarget.ancestor('.course');
		var course_id = course.getAttribute('data-course-id');
		var completion_status = course.getAttribute('data-completion-status');
		var status_map = {
			"Not Started" : 1,
			"In Progress" : 2,
			"Failed Retry" : 3,
			"Failed" : 4,
			"Completed" : 5
		};
		completion_status = status_map[completion_status];
		if (typeof _trackEvent == 'undefined') return false;
		_trackEvent('recent courses', action, course_id, completion_status);
	}
	var section = A.one('.course-status-section');
	// course image and title
	section.all('.course-title a, .thumbnail-link').on('click', function (event) {
		trackCourseEvent('go to course details', event);
	});
	// next/failed/updated component
	section.all('.course-attribute a, .update a').on('click', function (event) {
		trackCourseEvent('go to specific course component', event);
	});
	// link to new version course page
	section.all('.new-version a').on('click', function (event) {
		trackCourseEvent('go to new course version', event);
	});
	// start/continue/retry buttons, write course review button
	section.all('.actions .button').on('click', function (event) {
		var course = event.currentTarget.ancestor('.course');
		var completion_status = course.getAttribute('data-completion-status');
		if (completion_status == 'Not Started') trackCourseEvent('start course', event);
		else if (completion_status == 'In Progress') trackCourseEvent('continue course', event);
		else if (completion_status == 'Failed Retry') trackCourseEvent('retry course', event);
		else if ((completion_status == 'Completed' || completion_status == 'Failed')
			&& course.getAttribute('href').indexOf('course-details' > -1))
				trackCourseEvent('go review course', event);
	});
});