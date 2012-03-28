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

AUI.add('accessible-dialog', function(A) {

	BUTTONS = 'buttons',
	CLOSE = 'close',
	CLOSETHICK = 'closethick',
	FOOTER_CONTENT = 'footerContent',
	ICONS = 'icons',
	
	NODE_BLANK_TEXT = document.createTextNode(''),
	
    AccessibleDialog = A.Base.create('accessibleDialog', A.Dialog, [], {

		initializer: function(config) {
			var instance = this;
			var icons = instance.get(ICONS);
			var close = instance.get(CLOSE);
			var buttons = instance.get(BUTTONS);

			if (buttons && buttons.length && !instance.get(FOOTER_CONTENT)) {
				instance.set(FOOTER_CONTENT, NODE_BLANK_TEXT);
			}

			if (close) {
				var closeConfig = {
					icon: CLOSETHICK,
					id: CLOSETHICK,
					handler: {
						fn: instance.close,
						context: instance
					}
				};

				if (icons) {
					icons.push(closeConfig);
				}

				instance.set(ICONS, icons);
			}
			
			instance.after('render', instance._setupAria);
			instance.after('render', instance._setupKeys);
			instance.after('render', function () { instance.focus(); });
			instance.after('focusedChange', instance._focus);
			instance.after('destroy', instance._removeKeys);
		},

        _setupAria : function() {
			var contentBox = this.get('contentBox');
			var dialogId = contentBox.get('id');
			var titleId = contentBox.one('.aui-widget-hd').get('id');
			var contentId = contentBox.one('.aui-widget-bd').get('id');
			/*if (titleId == '') {
				titleId = dialogId + '-title';
				contentBox.one('.aui-widget-hd').set('id',titleId);
			}*/
			if (contentId == '') {
				contentId = dialogId + '-content';
				contentBox.one('.aui-widget-bd').set('id',contentId);
			}
			contentBox.one('.aui-widget-bd').addClass('aui-dialog-bd');
			contentBox.setAttribute('role','dialog');
			contentBox.setAttribute('tabindex',-1);
			contentBox.setAttribute('aria-labelledby',titleId);
			contentBox.setAttribute('aria-describedby',contentId);
		},
		_setupKeys : function() {
			var instance = this;
			var contentBox = instance.get('contentBox');
			
			A.on('key', function () { instance.close(); }, contentBox, 'down:27', instance);
			A.delegate('key', function () { instance.close(); }, contentBox, 'down:27');
			if (this.get('modal')) {
				var buttonBox = contentBox.one('.aui-dialog-button-container');
				if (buttonBox) {
					buttonBox.plug(A.Plugin.NodeFocusManager, {
						descendants: 'button',
						keys: { next: 'down:39', previous: 'down:37' },
						circular: true
					});
					// focus manager isn't quite what we want here, since buttons tend to be tabable
					A.on('key', function (event) {
						var target = event.currentTarget;
						if (event.shiftKey) target.simulate('keydown', {keyCode:37});
						else target.simulate('keydown', {keyCode:39});
						event.preventDefault();
					}, contentBox.all('.aui-dialog-button'), 'down:9', instance);
				}
			}
		},
		_removeKeys : function() {
			var instance = this;
			var contentBox = this.get('contentBox');
			
			contentBox.detach();
			contentBox.all('.aui-dialog-button').detach();
		},
		_focus : function(event) {
			var instance = this;
			var contentBox = this.get('contentBox');
			if (event.newVal && !event.prevVal) {
				var button = contentBox.one('.aui-dialog-button');
				if (button) setTimeout(function () {
					button.focus();
					button.addClass('focused');
				}, 10);
				else contentBox.focus();
			}
		}
	});
	
	A.namespace("NTER").AccessibleDialog = AccessibleDialog;

}, "3.2.0", {requires:['base','node-base','dom-base','widget-base', 'node-pluginhost', 'node-focusmanager', 'aui-dialog', 'node-event-simulate']});
