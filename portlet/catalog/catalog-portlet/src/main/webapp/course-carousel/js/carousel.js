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

AUI.add("carousel", function(Y) {

	// key constants
	var KEYS = {
		  LEFTARROW:     37
		, UPARROW:       38
		, RIGHTARROW:    39
		, DOWNARROW:     40
		, ENTER:         13
		, SPACEBAR:      32
	};

    function Carousel(config) {
        Carousel.superclass.constructor.apply(this, arguments);
    }
    Carousel.NAME = "Carousel";
    Carousel.ATTRS = {
		pos: {
			value: 0
			, validator: function (val) {
				if (isNaN(val)) return false
				else return true;
			}
			, broadcast: 1
		}
		, total: {
			value: 0
			, validator: function (val) {
				if (isNaN(val)) return false
				else return true;
			}
		}
		, containerWidth: {}
		, numVisible: {
			value: 0
		}
		
		, itemList: {}
		, itemsCopy: {
			value: []
		}
		, clippingBox: {}
		
		, speed: {
			value: .4
		}
		, easing: {
			value: 'easeBoth'
		}
		
		, animating: {
			value: false
		}
		, empty: {
			value: false
		}
	};

    /* 
     * The HTML_PARSER static constant is used if the Widget supports progressive enhancement, and is
     * used to populate the configuration for the MyWidget instance from markup already on the page.
     */
    Carousel.HTML_PARSER = {
		containerWidth: function (srcNode) {
			return parseInt(srcNode.getStyle('width'));
		}
		, itemList: function (srcNode) {
			return srcNode.one('ul, ol');
		}, total: function (srcNode) {
			return srcNode.one('ul, ol').all('li').size();
		}
    };
	
	Carousel.PREV_CLASS = 'carousel-prev';
	Carousel.PREV_DISABLED_CLASS = 'carousel-prev-disabled';
	Carousel.NEXT_CLASS = 'carousel-next';
	Carousel.NEXT_DISABLED_CLASS = 'carousel-next-disabled';
	Carousel.CLIPPER_CLASS = 'carousel-clipper';
	
	Carousel.CONTROLS_TEMPLATE = '<div class="carousel-controls" role="scrollbar"><div class="'+Carousel.PREV_CLASS+'" tabindex="-1" role="button"><span class="icon-accessibility-text">'+Liferay.Language.get('previous')+'</span></div><div class="'+Carousel.NEXT_CLASS+'" tabindex="-1" role="button"><span class="icon-accessibility-text">'+Liferay.Language.get('next')+'</span></div></div>';
	Carousel.CLIPPER_TEMPLATE = '<div class="'+Carousel.CLIPPER_CLASS+'" tabindex="0" role="listbox"></div>';

    Y.extend(Carousel, Y.Widget, {
        destructor : function() {
        }
		, renderUI : function() {
			var contentBox = this.get('contentBox');
			var itemList = this.get('itemList');
			var items = itemList.all('li');
			
			this._checkNumVisible();
			
			if (itemList.all('li').size() == 0 || this.get('total') <= this.get('numVisible')) {
				this.set('empty',true);
				return;
			}
			
			var clipper = Y.Node.create(Carousel.CLIPPER_TEMPLATE);
			this.set('clippingBox', clipper);
			contentBox.append(clipper);
			clipper.append(itemList);
			clipper.setStyles({overflow:'hidden', position:'relative', width: this.get('containerWidth')});
			itemList.setStyles({width:'9999px', position:'relative', left:'0px'});
			
			contentBox.setAttribute('role','application');
			itemList.all('li').setAttribute('role','option');
			itemList.all('a').setAttribute('tabindex',-1);
			
			var copy = this.get('itemsCopy');
			items.each(function (item) {
				copy.push(item.cloneNode(true));
			});
			
			itemList.setStyle('height', itemList.getStyle('height'));
			while (itemList.all('li').size() > this.get('numVisible')) itemList.one('li:last-child').remove();
			
			this._addArrows(clipper);
		}
        , bindUI : function() {
			if (this.get('empty')) return;
			
			var contentBox = this.get('contentBox');
			var context = this;
			contentBox.one('.'+Carousel.PREV_CLASS).on('click', function (event) {
				event.preventDefault();
				if (event.currentTarget.hasClass(Carousel.PREV_DISABLED_CLASS)) return;
				context.scroll('previous');
			});
			contentBox.one('.'+Carousel.NEXT_CLASS).on('click', function (event) {
				event.preventDefault();
				if (event.currentTarget.hasClass(Carousel.NEXT_DISABLED_CLASS)) return;
				context.scroll('next');
			});
			
			this.get('clippingBox').on('key', context._handleKeyArrows, 'down:'+[KEYS.LEFTARROW,KEYS.UPARROW,KEYS.RIGHTARROW,KEYS.DOWNARROW,KEYS.ENTER,KEYS.SPACEBAR].toString(), Y, context);
        }
		, syncUI: function () {
			if (this.get('empty')) return;
			this._select(0);
		}
		
		
		, scroll: function (dir) {
			if (this.get('animating')) return;
			
			var pos = this.get('pos');					// starting position
			var numVisible = this.get('numVisible');	// counted number of items visible (not 0-index)
			var total = this.get('total');				// counted number of items in the list (not 0-index)
			var itemList = this.get('itemList');		// reference to original list in DOM
			var itemsCopy = this.get('itemsCopy');		// copy of items, not in DOM, contains all items
			var newItemPos = pos;						// position of item to add
			var context = this;
			var transition = new Y.Anim({
				duration: this.get('speed'),
				easing: this.get('easing')
			});
			
			if (dir == 'next') {
				pos++;
				if (pos >= total) pos -= total;
				newItemPos = pos + numVisible - 1;
				if (newItemPos >= total) newItemPos -= total;
				var newItem = itemsCopy[newItemPos].clone(true);
				itemList.append(newItem);
				var firstItem = itemList.one('li');
				transition.set('node', firstItem);
				transition.set('to', { marginLeft: (-1 * (context._outerWidth(firstItem) - parseInt(firstItem.getStyle('marginLeft')))) + 'px' });
				transition.on('end', function () { firstItem.remove(); context._select(numVisible - 1); });
			} else if (dir == 'previous') {
				pos--;
				if (pos < 0) pos += total;
				newItemPos--;
				if (newItemPos < 0) newItemPos += total;
				var newItem = itemsCopy[newItemPos].clone(true);
				var originalMargin = parseInt(newItem.getStyle('marginLeft'));
				itemList.prepend(newItem);
				newItem.setStyle('marginLeft', (-1 * (context._outerWidth(newItem) - originalMargin)) + 'px');
				var lastItem = itemList.one('li:last-child');
				transition.set('node', newItem);
				transition.set('to', { marginLeft: originalMargin + 'px' });
				transition.on('end', function () { lastItem.remove(); context._select(0); });
			}
			transition.on('start', function () { context.set('animating',true); });
			transition.on('end', function () { context.set('animating',false); });
			transition.run();
			this.set('pos', pos);
		}
		
		, _outerWidth: function (e) {
			var dim = {
				  width: parseInt(e.get('offsetWidth'))
				, margin: parseInt(e.getStyle('marginLeft')) + parseInt(e.getStyle('marginRight'))
			};
			var total = 0;
			for (i in dim) {
				if (!isNaN(dim[i])) total += dim[i];
			}
			return total;
		}
		, _addArrows: function (container) {
			var contentBox = this.get('contentBox');
			var listId = this.get('itemList').get('id');
			var controls = Y.Node.create(Carousel.CONTROLS_TEMPLATE);
			contentBox.prepend(controls);
			controls.setAttribute('aria-controls',listId);
		}
		
		
		, _checkNumVisible: function () {
			var itemList = this.get('itemList').all('li');
			if (itemList.size() == 0) {
				this.set('numVisible',0);
				return;
			}
			this.set('numVisible', Math.ceil(this.get('containerWidth') / this._outerWidth(itemList.item(0))));
		}
		
		
		, _handleKeyArrows: function (event, context) {
			event.preventDefault();
			event.stopPropagation();
			if (context.get('animating')) return;
			var target = event.currentTarget;
			var pos = context.get('pos');
			var total = context.get('total');
			var numVisible = context.get('numVisible');
			var selected = target.one('li[aria-selected="true"]');
			var itemList = context.get('itemList');
			var selectedIndex = -1;
			if (selected) selectedIndex = itemList.all('li').indexOf(selected);
			switch (event.keyCode) {
				case KEYS.UPARROW:
				case KEYS.LEFTARROW:
					selectedIndex--;
					if (selectedIndex < 0) {
						context.scroll('previous');
						return; // depend on scroll function to select a new item
					}
					break;
				case KEYS.DOWNARROW:
				case KEYS.RIGHTARROW:
					selectedIndex++;
					if (selectedIndex >= (numVisible)) {
						context.scroll('next');
						return; // depend on scroll function to select a new item
					}
					break;
				case KEYS.ENTER:
				case KEYS.SPACE:
					context._handleKeyLink(selected);
					break;
			}
			context._select(selectedIndex);
		}
		, _handleKeyLink: function (selected) {
			window.location = selected.one('a').get('href');
		}
		, _select: function (index) {
			var items = this.get('itemList').all('li');
			var currentItem = items.item(index);
			items.setAttribute('aria-selected','false').removeClass('selected');
			currentItem.setAttribute('aria-selected','true').addClass('selected');
			this.get('contentBox').one('.'+Carousel.CLIPPER_CLASS).setAttribute('aria-activedescendant',currentItem.get('id'));
		}
    });

    Y.namespace('NTER').Carousel = Carousel;

}, "3.2.0", {requires:['base','node-base','dom-base','widget-base','anim-base','selector-css3']});