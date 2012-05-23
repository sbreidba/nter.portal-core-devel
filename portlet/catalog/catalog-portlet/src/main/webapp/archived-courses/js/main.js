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

AUI().ready('tree-view-html', 'removable', function(A) {
	var portletClass = 'archived-courses-portlet';
	
	var treeSetup = function () {
		var treenodes = A.all('.my-courses .toc');
		treenodes.each(function (treenode) {
			var tree = new A.NTER.TreeViewHTML({ srcNode: treenode });
			tree.render();
		});
	};
	var buttonSetup = function () {
		var courses = A.all('.my-courses .results-row');
		courses.plug(A.NTER.Removable);
		courses.each(function (course) {
			var courseRecordId;
			var courseRecordEl = course.one('[data-record-id]');
			if (!courseRecordEl) return;	// liferay tables insert extra rows, ignore them
			courseRecordId = courseRecordEl.getAttribute('data-record-id');
			itemName = escape(course.one('.course-title').text());
			
			if (!course.one('.course-title')) return false;	// default search template adds a lot of junk rows with nothing in them, ignore them
			course.Removable.registerBtn({
				  btnClass: '.remove-course'
				, text: {
					  successMsg: Liferay.Language.get('course-removed-success', [itemName])
					, errorMsg: Liferay.Language.get('course-removed-error', [itemName])
					, cancelSuccessMsg: Liferay.Language.get('course-removed-cancel-success', [itemName])
					, cancelErrorMsg: Liferay.Language.get('course-removed-cancel-error', [itemName])
					, confirmMsg: Liferay.Language.get('course-removed-confirmation', [itemName])
					, confirmBtn: Liferay.Language.get('remove')
				}
				, url: ''
				, cancelUrl: ''
				, confirm: true
			});
			course.Removable.after('removeSuccess', function (event) { Liferay.fire('courseRemovedChange', {courseRecordId:courseRecordId, isRemoved:true, portletOrigin:portletClass}) });
			course.Removable.after('unremoveSuccess', function (event) { Liferay.fire('courseRemovedChange', {courseRecordId:courseRecordId, isRemoved:false, portletOrigin:portletClass}) });
		});
	};
	var tableSetup = function () {
		var table = A.one('.my-courses');
		if (table) {
			// TODO: replace all this with Liferay.Portlet.refresh()
			var portletContainer = table.ancestor(function (el) { if (el.hasClass('portlet-body')) return true; });
			if (typeof (Liferay.Portlet.refreshLayout) != 'function') Liferay.Portlet.refreshLayout = function () {};	// Liferay.Portlet.refreshLayout is only defined for admins but refresh calls it anyway, remove this line when fixed http://issues.liferay.com/browse/LPS-12872
			portletContainer.delegate('click', function (event) {
				event.preventDefault();
				var target = event.currentTarget;
				var url = '/c/portal/render_portlet?' + target.getAttribute('href').split('?')[1] + '&p_p_isolated=1&p_l_id=' + themeDisplay.getPlid();
				A.one('.'+portletClass).refreshURL = url;
				Liferay.Portlet.refresh('.'+portletClass);
			}, '.my-courses th a');
			portletContainer.delegate('click', function (event) {
				event.preventDefault();
				var target = event.currentTarget;
				var url = '/c/portal/render_portlet?' + target.getAttribute('href').split('?')[1] + '&p_p_isolated=1&p_l_id=' + themeDisplay.getPlid();
				A.one('.'+portletClass).refreshURL = url;
				Liferay.Portlet.refresh('.'+portletClass);
			}, '.taglib-page-iterator a');
		}
	};
	buttonSetup();treeSetup();tableSetup();
	
	Liferay.on('courseRemovedChange', function (data) {
		if (data.portletOrigin != portletClass) {
			/* reload the portlet, relying on tableSetup to have set the right refresh url for the page and sorting */
			Liferay.Portlet.refresh('.'+portletClass);
		}
	});
	
	Liferay.on('portletReady', function (cfg) { if (cfg.portlet.hasClass(portletClass)) { buttonSetup(); treeSetup(); tableSetup(); } });	// fires when portlet loads via ajax


	function trackCourseEvent(action, event) {//console.log(action);event.preventDefault();
		var course = event.currentTarget.ancestor('.results-row');
		var course_id = course.getAttribute('data-course-id');
		//var completion_status = 0;
		if (course.hasClass('notstarted')) completion_status = 1;
		else if (course.hasClass('progress')) completion_status = 2;
		else if (course.hasClass('failed-retry')) completion_status = 3;
		else if (course.hasClass('failed')) completion_status = 4;
		else if (course.hasClass('complete')) completion_status = 5;
		console.log(completion_status);
		if (typeof _trackEvent == 'undefined') return false;
		_trackEvent('recent courses', action, course_id, completion_status);
	}
	var section = A.one('.my-courses');
	// course image and title
	section.all('.course-title a, .thumbnail-link').on('click', function (event) {
		trackCourseEvent('go to course details', event);
	});
	// next/failed/updated component
	/*section.all('.course-attribute a, .update a').on('click', function (event) {
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
	});*/
});