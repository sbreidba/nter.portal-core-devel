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

AUI().ready('tree-view-html', 'course-thumbnails', 'course-popup', function(A) {
	var treenode = A.one('.toc');
	if (treenode) {
		var tree = new A.NTER.TreeViewHTML({ srcNode: treenode });
		tree.render();
	}
	
	var thumbSwitcher = new A.NTER.CourseThumbnails({srcNode:'.course-image'});
	thumbSwitcher.render();
	
	var useLightbox = false;	// switch for tabs vs lightbox
	
	if (useLightbox) {
		A.all('.join-course, .toc a').on('click',function (event) {
			var link = event.currentTarget;
			
			var popup = new A.Dialog({
				centered: true,
				constrain2view: true,
				draggable: false,
				resizable: false,
				modal: true,
				title: 'Dialog title'
			});
			popup.plug(A.Plugin.NTER.CoursePopup);
			popup.render();
			popup.set('index',treenode.cloneNode(true));
			popup.set('pageUrl',link.get('href'));
			popup.set('width',link.getAttribute('data-window-width'));
			popup.set('height',link.getAttribute('data-window-height'));
			
			event.preventDefault();
		});
	}

});