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

AUI.add("course-thumbnails", function(Y) {

	var IMG_CLASS = 'main-image';
	var THUMB_CONTAINER_CLASS = 'thumbnails';
	var THUMB_CLASS = 'thumbnail';
	var SELECTED_CLASS = 'selected';
	var DATA_ATTR = 'data-full-version';

    function CourseThumbnails(config) {
        CourseThumbnails.superclass.constructor.apply(this, arguments);
    }
    CourseThumbnails.NAME = "CourseThumbnails";
    CourseThumbnails.ATTRS = {
	
	    fullsizeImg: {}
		, thumbs: {}
		, thumbContainer: {}
		, fullsizeUrls: {}
		, selectedIndex: {}
	
	};

    /* 
     * The HTML_PARSER static constant is used if the Widget supports progressive enhancement, and is
     * used to populate the configuration for the MyWidget instance from markup already on the page.
     */
    CourseThumbnails.HTML_PARSER = {
		fullsizeImg: function (srcNode) {
			return srcNode.all('.'+IMG_CLASS);
		}
		, thumbContainer: function (srcNode) {
			return srcNode.one('.'+THUMB_CONTAINER_CLASS);	// must be one, not all, because of YUI bugs with things like .get('children') on NodeList
		}
		, thumbs: function (srcNode) { // list of thumbnail image nodes
			return srcNode.all('.'+THUMB_CONTAINER_CLASS+' .'+THUMB_CLASS);
		}
		, fullsizeUrls: function (srcNode) { // array of fullsize image urls
			var fullsize = [];
			CourseThumbnails.HTML_PARSER.thumbs(srcNode).each(function (node) {
				var img;
				if (node.get('nodeName').toLowerCase() == 'a') img = node.getAttribute('href');
				else img = node.getAttribute(DATA_ATTR);
				fullsize.push(img);
			});
			return fullsize;
        }
		, selectedIndex: function (srcNode) {
			var selectedThumb = CourseThumbnails.HTML_PARSER.thumbs(srcNode).filter('.'+SELECTED_CLASS);
			var fullsizeUrls = CourseThumbnails.HTML_PARSER.fullsizeUrls(srcNode);
			if (selectedThumb.size() != 0) {
				selectedThumb = selectedThumb.item(0);
				return this._getThumbIndex(selectedThumb);
			}
			else {
				var currentUrl = CourseThumbnails.HTML_PARSER.fullsizeImg(srcNode).getAttribute('src');
				for (var i=0; i<fullsizeUrls.length; i++) {
					if (currentUrl == fullsizeUrls[i]) return i;
				}
			}
			// can only get here if the big image is none of the thumbnails
			return 0;
		}
    };

    /* MyWidget extends the base Widget class */
    Y.extend(CourseThumbnails, Y.Widget, {
        destructor : function() {
        }
        , bindUI : function() {
			var instance = this;
            this.get('thumbs').on('click', function (ev) {
				ev.stopPropagation();
				ev.preventDefault();
				instance.change(instance._getThumbIndex(ev.currentTarget));
			});
        }
		, syncUI : function() {
            if (this.get('thumbs').size()) this.change(this.get('selectedIndex'));
        }
		
		
		, change: function (index) {
			var url = this.get('fullsizeUrls')[index];
			var thumbs = this.get('thumbs');
			var thumbContainer = this.get('thumbContainer')
			this.get('fullsizeImg').set('src',url);
			thumbs.removeClass(SELECTED_CLASS);
			thumbs.item(index).addClass(SELECTED_CLASS);
		}
		, _getThumbIndex: function (img) {	// thumbContainer must be passed because this.get returns undefined when called from HTML_PARSER
			var thumbContainer = img.ancestor('.'+THUMB_CONTAINER_CLASS);
			var children = thumbContainer.get('children');
			var childIndex = children.indexOf(img);
			// if childIndex is greater than -1, thumbContainer contains the image directly (e.g. div>img)
			// otherwise there's some other element in between (e.g. ul>li>img)
			if (childIndex == -1) children.each(function (node) {
				if (node.contains(img)) {
					childIndex = children.indexOf(node);	// TODO: find a way for this not to break in IE if there are comment nodes (or get YUI to fix it http://yuilibrary.com/projects/yui3/ticket/2529384)
					return;
				}
			});
			return childIndex;
		}
    });

    Y.namespace('NTER').CourseThumbnails = CourseThumbnails;

}, "3.2.0", {requires:['base','node-base','dom-base','widget-base','widget-htmlparser']});