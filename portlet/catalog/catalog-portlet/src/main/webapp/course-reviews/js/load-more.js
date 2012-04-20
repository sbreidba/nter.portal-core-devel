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

AUI.add('load-more', function(A) {

	function LoadMore(config) {
		LoadMore.superclass.constructor.apply(this, arguments);
	}
	LoadMore.NAME = "LoadMore";
	LoadMore.ATTRS = {
		list: {
			value: null
			, setter: function (val) {
				if (val && A.Lang.isString(val)) val = A.Node.all(val);
				return val;
			}
		},
		url: {
			value: '/'
		},
		startParam: {
			value: 'start'
		},
		perPage: {
			value: 10,
			setter: function (attrVal) { return parseInt(attrVal); }
		},
		start: {
			value: 0,
			setter: function (attrVal) { return parseInt(attrVal); }
		},
		total: {
			value: 0,
			setter: function (attrVal) { return parseInt(attrVal); }
		},
		morelink: {
			value: ''
		}
	}
	
	LoadMore.LINK_TEMPLATE = '<a href="{url}" class="load-more-items">' + Liferay.Language.get('show-more') + '</a>';
	LoadMore.END_TEMPLATE = '<div class="no-more-items">'+Liferay.Language.get('no-more-items')+'</div>';
	LoadMore.SPINNER_TEMPLATE = '<li class="load-content-spinner">'+Liferay.Language.get('loading')+'</li>';
	LoadMore.ERROR_TEMPLATE = '<li class="portlet-msg-error">{error}</li>';
	
	A.extend(LoadMore, A.Widget, {
		initializer: function () {
		}
		, destructor: function () {
		}
		, renderUI: function () {
			if (this.get('total') <= this.get('perPage')) return;
			var morelink = A.Node.create(A.substitute(LoadMore.LINK_TEMPLATE,{url:this.get('url')}));
			this.get('contentBox').append(morelink);
			this.get('list').insert(this.get('contentBox'), 'after');
			this.set('morelink', morelink);
		}
		, bindUI: function () {
			if (this.get('total') <= this.get('perPage')) return;
			var link = this.get('morelink');
			var list = this.get('list');
			var instance = this;
			link.on('click', function (event) {
				event.preventDefault();
				A.io(instance.buildUrl(), {
					  data: ''
					, on: {
						// show a loading graphic
						start: function () { list.all('.portlet-msg-error').remove(); list.append(LoadMore.SPINNER_TEMPLATE); }
						// success expects content inside <li> tags, will append directly to the list
						, success: function (id, o) { list.one('.load-content-spinner').remove(true); list.append(o.responseText); instance.checkLink(); }
						// failure expects a message, will create a <li> for it and append it to the list
						// TODO: add real error messages with internationalized text
						, failure: function (id, o) { list.one('.load-content-spinner').remove(true); list.append(A.substitute(LoadMore.ERROR_TEMPLATE,{error:o.statusText})); }
					}
				});
			});
		},
		
		buildUrl: function () {
			return this.get('url') + '&' + this.get('startParam') + '=' + this.get('start');
		},
		checkLink: function () {
			this.set('start', this.get('start') + this.get('perPage'));
			if (this.get('start') + 1 >= this.get('total')) {
				this.get('contentBox').append(A.Node.create(LoadMore.END_TEMPLATE));
				this.get('morelink').remove();
			}
		}
	});
	
	A.namespace('NTER').LoadMore = LoadMore;
	
}, "3.2.0", {requires:['io-base']});