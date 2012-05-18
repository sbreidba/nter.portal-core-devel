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

AUI().ready('base', 'node', 'dom', 'selector-css3', function(A) {
	// progressive enhancement for nth-child
	var items = A.all('#partner-top-courses .thumbnail-listing li:not(.main-course)');
	if (items.size() > 0) {
		var first = items.item(0);
		var marginRight = parseInt(first.getStyle('marginRight'));
		var marginLeft = parseInt(first.getStyle('marginLeft'));
		var paddingRight = parseInt(first.getStyle('paddingRight'));
		var paddingLeft = parseInt(first.getStyle('paddingLeft'));
		var extra = marginLeft + marginRight + paddingLeft + paddingRight;
		var width = parseInt(first.getStyle('width'));
		var total = parseInt(first.get('parentNode').getStyle('width'));
		var numAcross = Math.floor((total + extra) / (width + extra));
		
		A.all('#partner-top-courses .thumbnail-listing li:nth-child('+numAcross+'n+1)').setStyles({paddingRight:0,marginRight:0});
		A.all('#partner-top-courses .thumbnail-listing li:nth-child('+numAcross+'n+2)').setStyles({clear:'left'});
		A.all('#partner-top-courses .thumbnail-listing li:nth-child(2)').setStyles({clear:'none'});
		if (Liferay.Browser.isIe() && Liferay.Browser.getMajorVersion() <= 7) A.all('#partner-top-courses .thumbnail-listing li:nth-child('+numAcross+'n+1)').insert(A.Node.create('<br />'), 'after');
	}
});