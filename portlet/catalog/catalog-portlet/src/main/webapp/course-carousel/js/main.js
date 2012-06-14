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

AUI().ready('carousel', function(A) {
	A.all('.carousel-wrapper').each(function (item) {
		var portlet = item.ancestor(function (a) { return a.hasClass('portlet'); });
		var carouselLabel = portlet.one('.portlet-title-text');
		var namespaceId = portlet.get('id').split('_');
		namespaceId = namespaceId[namespaceId.length - 1]  + '_label';
		carouselLabel.set('id', namespaceId);
		item.setAttribute('aria-labelledby',namespaceId);
		
		var carousel = new A.NTER.Carousel({ srcNode:item });
		carousel.render();
	});
});