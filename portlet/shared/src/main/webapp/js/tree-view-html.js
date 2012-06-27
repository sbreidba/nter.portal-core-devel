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

AUI.add('tree-view-html', function(A) {

	// key constants
	var KEYS = {
		  LEFTARROW:     37
		, UPARROW:       38
		, RIGHTARROW:    39
		, DOWNARROW:     40
		, ENTER:         13
		, HOME:          36
		, END:           35
		, ASTERISK:      106
		, SHIFTASTERISK: 56
	};
	
	/* tree view widget
	   Alloy has one, but this degrades more gracefully
	   because everything is in the markup */
	function TreeViewHTML(config) {
		TreeViewHTML.superclass.constructor.apply(this, arguments);
	}
	TreeViewHTML.NAME = "TreeViewHTML";
	
	TreeViewHTML.EXPANDED_CLASS = 'expanded';
	TreeViewHTML.COLLAPSED_CLASS = 'collapsed';
	
	TreeViewHTML.ATTRS = {
		defaultState: {
			value: TreeViewHTML.COLLAPSED_CLASS
			, setter: function (val) {
				if (val != TreeViewHTML.COLLAPSED_CLASS && val != TreeViewHTML.EXPANDED_CLASS) val = TreeViewHTML.COLLAPSED_CLASS;
				return val;
			}
		}
	}
	A.extend(TreeViewHTML, A.Widget, {
		initializer: function () {
		}
		, destructor: function () {
			var root = this.get('contentBox');
			root.removeClass('treeview');
			root.all('li').removeClass('collapsible').removeClass('nochildren').removeClass('leaf').removeClass(this.get('defaultState'));
		}
		, renderUI: function () {
			var root = this.get('contentBox');
			var havechildren = root.all('ul').get('parentNode');
			if (havechildren instanceof Array) havechildren = A.all(havechildren);
			var nochildren = []
			var leafnodes = [];
			var first = root.one('li');
			
			root.all('li').each(function (node) {
				if (node.all('ul').size() == 0) nochildren.push(node);
			});
			nochildren = A.all(nochildren);
			nochildren.each(function (node) {
				if (node.get('parentNode').all('ul').size() == 0) leafnodes.push(node);
			});
			leafnodes = A.all(leafnodes);
			
			root.addClass('treeview');
			havechildren.addClass('collapsible');
			nochildren.addClass('collapsible').addClass('nochildren');
			leafnodes.addClass('leaf');
			
			var state = this.get('defaultState');
			havechildren.each(function (node) {
				// add class for styling
				if (!node.hasClass(TreeViewHTML.COLLAPSED_CLASS) && !node.hasClass(TreeViewHTML.EXPANDED_CLASS)) node.addClass(state);
				// add aria state info
				if (node.hasClass(TreeViewHTML.EXPANDED_CLASS)) {
					node.setAttribute('aria-expanded', 'true');
					node.get('children').filter('ul').setStyle('display','block');
				} else if (node.hasClass(TreeViewHTML.COLLAPSED_CLASS)) {
					node.setAttribute('aria-expanded', 'false');
					node.get('children').filter('ul').setStyle('display','none');
				}
			});
			
			root.all('li').setAttribute('tabindex',-1).prepend('<span class="toggle"></span>');
			root.all('a').setAttribute('tabindex',-1);
			if (first) first.setAttribute('tabindex',0);
		}
		, bindUI: function () {
			var root = this.get('contentBox');
			var context = this;
			
			// click events
			root.delegate('click', function (event) {
				var node = event.currentTarget.get('parentNode');
				context.toggleNode(node);
			}, '.collapsible .toggle');
			
			// key events
			root.on('key', context._handleRootKeys, 'down:'+[KEYS.DOWNARROW,KEYS.ASTERISK,KEYS.SHIFTASTERISK].toString(), A, context);
			root.all('li').on('key', context._handleNodeKeys, 'down:'+[KEYS.LEFTARROW,KEYS.UPARROW,KEYS.RIGHTARROW,KEYS.DOWNARROW,KEYS.ENTER,KEYS.HOME,KEYS.END,KEYS.ASTERISK,KEYS.SHIFTASTERISK].toString(), A, context);
			root.delegate('focus', function (event) {
				var target = event.currentTarget;
				if (target != event.target) return;
				target.addClass('focused');
				root.setAttribute('aria-activedescendant',target.get('id'));
			}, 'li');
			root.delegate('blur', function (event) {
				if (event.currentTarget != event.target) return;
				event.currentTarget.removeClass('focused');
			}, 'li');
		}
		
		, toggleNode: function (node) {
			if (node.all('ul').size() == 0) return;
			if (this._isOpen(node)) node.get('children').filter('ul').fadeOut({callback:function () { node.removeClass(TreeViewHTML.EXPANDED_CLASS).addClass(TreeViewHTML.COLLAPSED_CLASS); node.setAttribute('aria-expanded','false'); }});
			else node.get('children').filter('ul').fadeIn({callback:function () { node.removeClass(TreeViewHTML.COLLAPSED_CLASS).addClass(TreeViewHTML.EXPANDED_CLASS); node.setAttribute('aria-expanded','true'); }});
		}
		, expandAll: function () {
			var context = this;
			this.get('contentBox').all('li[aria-expanded="false"]').each(function (node) {
				context.toggleNode(node);
			});
		}
		
		, _isOpen: function (target) {
			return (!target.hasClass(TreeViewHTML.COLLAPSED_CLASS) || target.hasClass(TreeViewHTML.EXPANDED_CLASS)) && !target.hasClass('nochildren');
		}
		
		, _handleRootKeys: function (event, context) {
			switch(event.keyCode) {
				case KEYS.DOWNARROW:		// go to first item
					event.currentTarget.one('li').focus();
					break;
				case KEYS.SHIFTASTERISK:
					if (!event.shiftKey) return;
					// fall through...
				case KEYS.ASTERISK:			// open all items
					if (event.currentTarget != event.target) break;
					context.expandAll();
					break;
			}
			event.preventDefault();
		}
		, _handleNodeKeys: function (event, context) {
			var target = event.currentTarget;
			if (target != event.target) return;	// bubbled event, do nothing--we're using delegation, so we have to fix this here instead of using stopPropagation
			if (context._eventHasModifiers(event)) {
				if (event.keyCode != KEYS.SHIFTASTERISK) return;
			}
			switch (event.keyCode) {
				case KEYS.RIGHTARROW:		// expand closed node or move to first child of open node
					if (!context._isOpen(target)) context.toggleNode(target);
					else if (child = target.one('li')) child.focus();
					break;
				case KEYS.LEFTARROW:		// closes open node or move to parent node
					if (context._isOpen(target)) context.toggleNode(target);
					else if (parent = context._getParentNode(target)) parent.focus();
					break;
				case KEYS.DOWNARROW:		// move to next visible element (may be a child element)
					var nextItem = context._getNextItem(target, false);
					if (nextItem) nextItem.focus();
					break;
				case KEYS.UPARROW:			// move to previous visible element (may be a child element of previous sibling)
					var prevItem = context._getPreviousItem(target);
					if (prevItem) prevItem.focus();
					break;
				case KEYS.ENTER:			// perform the "default action" for the node.  this code assumes the node contains a link.
					location.href = target.one('a').get('href');
					break;
				case KEYS.HOME:				// go to first item
					context.get('contentBox').one('li').focus();
					break;
				case KEYS.END:				// go to last visible item
					var children = context.get('contentBox').get('children');
					context._getLastInNode(children.item(children.size() - 1)).focus();
					break;
				case KEYS.SHIFTASTERISK:
					if (!event.shiftKey) return;
					// fall through...
				case KEYS.ASTERISK:			// open all items
					context.expandAll();
					break;
			}
			event.preventDefault();
			event.stopPropagation();
		}
		, _eventHasModifiers: function (event) {
			if (event.altKey || event.ctrlKey || event.metaKey || event.shiftKey) return true;
			else return false;
		}
		, _getNextItem: function (target, treatAsClosed) {
			var isOpen = (!treatAsClosed && this._isOpen(target));
			var parent = target.get('parentNode');
			var siblings = target.get('parentNode').get('children');
			var index = siblings.indexOf(target);
			
			var nextItem = null;
			
			if (isOpen) nextItem = target.one('li');										// go to first child node
			else if (siblings.size() > (index + 1)) nextItem = siblings.item(index + 1);	// go to next sibling
			else if (siblings.size() == (index + 1)) {										// get next item for parent
				if (parent.getAttribute('role') == 'group') {
					nextItem = this._getNextItem(parent.get('parentNode'), true);
				}
			}
			
			return nextItem;
		}
		, _getPreviousItem: function (target) {
			var parent = target.get('parentNode');
			var siblings = target.get('parentNode').get('children');
			var index = siblings.indexOf(target);
			
			var nextItem = null;
			
			if (index == 0) {																// go to parent li, if there is one
				nextItem = this._getParentNode(target);
			} else {
				var previousSibling = siblings.item(index - 1);
				nextItem = this._getLastInNode(previousSibling);
			}
			
			return nextItem;
		}
		
		, _getLastInNode: function (node) {
			if (!this._isOpen(node)) return node;
			else {
				var list = node.get('children').filter('ul');
				if (list.size() > 0) {
					var children = list.item(0).get('children');
					if (children.size() > 0) return this._getLastInNode(children.item(children.size() - 1));
					else return node;
				} else return node;
			}
		}
		, _getParentNode: function (node) {
			var parentList = node.get('parentNode');
			var parent = null
			if (parentList.getAttribute('role') == 'group') {
				var grandparent = parentList.get('parentNode');
				if (grandparent.getAttribute('role') == 'treeitem') parent = grandparent;
			}
			return parent;
		}
	});
	
	A.namespace('NTER').TreeViewHTML = TreeViewHTML;
	
}, "3.2.0", {requires:['base','node-base','dom-base','widget-base','anim-custom']});