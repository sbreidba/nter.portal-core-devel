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
});