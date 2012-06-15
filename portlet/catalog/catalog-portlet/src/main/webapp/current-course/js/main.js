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
		var completion_status = 0;
		if (course.hasClass('notstarted')) completion_status = 1;
		else if (course.hasClass('progress')) completion_status = 2;
		else if (course.hasClass('failed-retry')) completion_status = 3;
		else if (course.hasClass('failed')) completion_status = 4;
		else if (course.hasClass('complete')) completion_status = 5;
		if (typeof _gaq == 'undefined') return false;
		_gaq.push(['_trackEvent', 'recent courses', action, course_id, completion_status]);
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
		trackCourseEvent('go to other course version', event);
	});
	// start/continue/retry buttons, write course review button
	section.all('.actions .button').on('click', function (event) {
		var course = event.currentTarget.ancestor('.course');
		if (course.hasClass('notstarted')) trackCourseEvent('start course', event);
		else if (course.hasClass('progress')) trackCourseEvent('continue course', event);
		else if (course.hasClass('failed-retry')) trackCourseEvent('retry course', event);
		else if ((course.hasClass('complete') || course.hasClass('failed'))
			&& event.currentTarget.getAttribute('href').indexOf('course-details' > -1))
				trackCourseEvent('go review course', event);
	});
});