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

AUI.add("course-popup", function(Y) {

    function CoursePopup(config) {
        CoursePopup.superclass.constructor.apply(this, arguments);
    }

    CoursePopup.NAME = "CoursePopup";
    CoursePopup.NS = "cpop";     

    CoursePopup.ATTRS = {
		// set externally
		  pageUrl: {}
		, index: {}
		, classname: { value: 'course-popup' }
		
		// text
		, contentsText: { value: 'Course Contents' }
		, leaveContentsText: { value: 'Back to Course' }
		
		// internal use
		, frameWidth: {}
		, frameHeight: {}
		, showingIndex: { value: false }
		
		, indexPanel: {}
		, contentPanel: {}
		, controlsPanel: {}
		, nextButton: {}
		, prevButton: {}
		, contentsButton: {}
    };
	
	CoursePopup.INDEX_CLASS = 'course-index';
	CoursePopup.CONTENT_CLASS = 'course-content';
	CoursePopup.CONTROLS_CLASS = 'course-popup-controls';
	CoursePopup.CONTENTS_BUTTON_CLASS = 'contents-button';
	CoursePopup.PREVIOUS_BUTTON_CLASS = 'prev';
	CoursePopup.NEXT_BUTTON_CLASS = 'next';
	CoursePopup.DIALOG_BODY_CLASS = 'aui-dialog-bd';
	CoursePopup.CURRENT_CLASS = 'current';
	
	CoursePopup.INDEX_TEMPLATE = '<div class="'+CoursePopup.INDEX_CLASS+'"></div>';
	CoursePopup.CONTROLS_TEMPLATE = '<div class="'+CoursePopup.CONTROLS_CLASS+'"><div role="button" class="'+CoursePopup.PREVIOUS_BUTTON_CLASS+'">Previous</div><div role="button" class="'+CoursePopup.NEXT_BUTTON_CLASS+'">Next</div><div role="button" class="'+CoursePopup.CONTENTS_BUTTON_CLASS+'"></div></div>';
	CoursePopup.FRAME_TEMPLATE = CoursePopup.INDEX_TEMPLATE + '<div class="'+CoursePopup.CONTENT_CLASS+'"><iframe src=""></iframe></div>' + CoursePopup.CONTROLS_TEMPLATE;

    Y.extend(CoursePopup, Y.Plugin.Base, {

        initializer: function() {
			var host = this.get('host');
			host.get('contentBox').addClass(this.get('classname'));
			host.set('bodyContent',CoursePopup.FRAME_TEMPLATE);
			this.afterHostEvent('render', this._setup);
        }
        , destructor : function() {
			var host = this.get('host');
			host.get('contentBox').removeClass(this.get('classname'));
			host.set('bodyContent','');
        }
		
		
		, _setup: function (event) {
			var host = this.get('host');
			var hostBox = host.get('contentBox');
			var iframe = host.get('contentBox').one('iframe');
			var context = this;
			
			iframe.setStyles({	// we resize with its wrapper, less buggy
				  width: '100%'
				, height: '100%'
			});
			
			host.on('widthChange', function (event) { context._widthChangeCompensate(event, context); });		// change width to compensate for index width
			host.on('heightChange', function (event) { context._heightChangeCompensate(event, context); });		// change height to compensate for dialog height
			
			host.after('indexChange', function (event) { context._indexChange(event, context); });
			host.after('pageUrlChange', function (event) { context._urlChange(event, context); });
			host.after('widthChange', function (event) { context._widthChange(event, context); });
			host.after('heightChange', function (event) { context._heightChange(event, context); });
			
			this.set('indexPanel',hostBox.one('.'+CoursePopup.INDEX_CLASS));
			this.set('contentPanel',hostBox.one('.'+CoursePopup.CONTENT_CLASS));
			this.set('controlsPanel',hostBox.one('.'+CoursePopup.CONTROLS_CLASS));
			this.set('nextButton',hostBox.one('.'+CoursePopup.NEXT_BUTTON_CLASS));
			this.set('prevButton',hostBox.one('.'+CoursePopup.PREVIOUS_BUTTON_CLASS));
			this.set('contentsButton',hostBox.one('.'+CoursePopup.CONTENTS_BUTTON_CLASS));
			
			this.get('contentsButton').set('text',this.get('contentsText')).on('click', function (event) { context._toggleIndex(event, context); });
			
			iframe.on('load', function (event) { context._highlightIndex(event, context); });
		}
		
		
		, _widthChangeCompensate: function (event) {
			var hostBox = event.currentTarget.get('contentBox');
			var dialogBody = hostBox.one('.'+CoursePopup.DIALOG_BODY_CLASS);
			var index = this.get('indexPanel');
			var content = this.get('contentPanel');
			
			var hostWidth = parseInt(hostBox.get('parentNode').getStyle('width'))
			var innerWidth = parseInt(dialogBody.getStyle('width'));	// compensate for borders, padding, etc.
			var indexWidth = parseInt(index.get('offsetWidth'))
			                 + parseInt(index.getStyle('marginLeft'))
							 + parseInt(index.getStyle('marginRight'));			// subtract the width taken by index
			var frameExterior = parseInt(content.getComputedStyle('marginLeft'))
								+ parseInt(content.getComputedStyle('marginRight'))
								+ parseInt(content.getComputedStyle('paddingLeft'))
								+ parseInt(content.getComputedStyle('paddingRight'));
			var widthDiff = hostWidth - (innerWidth - indexWidth - frameExterior);
			
			this.set('frameWidth',event.newVal);
			event.newVal = parseInt(event.newVal) + widthDiff;
		}
		, _heightChangeCompensate: function (event) {
			var hostBox = event.currentTarget.get('contentBox');
			var dialogBody = hostBox.one('.'+CoursePopup.DIALOG_BODY_CLASS);
			var content = this.get('contentPanel');
			var controls = this.get('controlsPanel');
			
			var hostHeight = parseInt(hostBox.get('parentNode').getStyle('height'))
			var innerHeight = parseInt(dialogBody.getStyle('height'));	// compensate for borders, padding, etc.
			var controlsHeight = parseInt(controls.get('offsetHeight'))
								 + parseInt(controls.getStyle('marginTop'))
								 + parseInt(controls.getStyle('marginBottom'));	
			var frameExterior = parseInt(content.getComputedStyle('marginBottom'))
								+ parseInt(content.getComputedStyle('marginTop'))
								+ parseInt(content.getComputedStyle('paddingBottom'))
								+ parseInt(content.getComputedStyle('paddingTop'));
			var heightDiff = hostHeight - (innerHeight - controlsHeight - frameExterior);
			
			this.set('frameHeight',event.newVal);
			event.newVal = parseInt(event.newVal) + heightDiff;
		}
		
		, _indexChange: function (event, context) {
			var host = context.get('host');
			var node = event.newVal;
			var index = context.get('indexPanel');
			index.append(node);
			
			var tree = new Y.NTER.TreeViewHTML({ srcNode: node });
			tree.render();
			
			index.all('a').on('click', function (event) {
				var link = event.target;
				var url = link.get('href');
				var width = link.getAttribute('data-window-width');
				var height = link.getAttribute('data-window-height');
				if (!isNaN(width)) host.set('width', width);
				if (!isNaN(height)) host.set('height', height);
				context.set('pageUrl', url);
				context.set('showingIndex',true);
				context._toggleIndex(event, context);
				event.preventDefault();
			});
		}
		, _urlChange: function (event) {
			var newUrl = event.newVal;
			this.get('contentPanel').one('iframe').set('src',newUrl);
			this._highlightIndex(newUrl, this);
		}
		, _widthChange: function (event) {
			this.get('contentPanel').setStyle('width',this.get('frameWidth'));
		}
		, _heightChange: function (event) {
			this.get('contentPanel').setStyle('height',this.get('frameHeight'));
		}
		
		, _highlightIndex: function (loadedSrc) {
			if (typeof(loadedSrc) != 'string') loadedSrc = loadedSrc.currentTarget.get('src');
			var index = this.get('indexPanel');
			var links = index.all('a');
			links.each(function (link) {
				if (link.get('href') == loadedSrc) link.get('parentNode').addClass(CoursePopup.CURRENT_CLASS);
				else link.get('parentNode').removeClass(CoursePopup.CURRENT_CLASS);
			});
		}
		
		
		, _toggleIndex: function (event, context) {
			var hostBox = context.get('host').get('contentBox');
			var contentsButton = context.get('contentsButton');
			if (!context.get('showingIndex')) {
				context.get('contentPanel').setStyle('display','none');
				context.get('indexPanel').setStyle('display','block');
				contentsButton.set('text', context.get('leaveContentsText'));
				context.set('showingIndex',true);
			} else {
				context.get('contentPanel').setStyle('display','block');
				context.get('indexPanel').setStyle('display','none');
				contentsButton.set('text', context.get('contentsText'));
				context.set('showingIndex',false);
			}
		}

    });

    Y.namespace("Plugin.NTER").CoursePopup = CoursePopup;

}, "3.1.0", {requires:["plugin","tree-view-html"]});