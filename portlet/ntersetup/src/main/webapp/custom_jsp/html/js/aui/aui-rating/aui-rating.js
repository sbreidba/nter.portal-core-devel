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

AUI.add('aui-rating', function(A) {
/**
 * The Rating Utility - The Star Rating creates a non-obstrusive star rating
 * control, could be based on a set of radio input boxes.
 *
 * @module aui-rating
 */

var L = A.Lang,
	isBoolean = L.isBoolean,
	isNumber = L.isNumber,
	isString = L.isString,

	isNodeList = function(v) {
		return (v instanceof A.NodeList);
	},

	isNode = function(v) {
		return (v instanceof A.Node);
	},

	ANCHOR = 'a',
	ACCESSIBLE = 'accessible',
	BLANK = '',
	BOUNDING_BOX = 'boundingBox',
	CAN_RESET = 'canReset',
	CLEARFIX = 'clearfix',
	CONTENT_BOX = 'contentBox',
	DEFAULT_SELECTED = 'defaultSelected',
	DISABLED = 'disabled',
	DOT = '.',
	ELEMENT = 'element',
	ELEMENTS = 'elements',
	EMPTY_STR = '',
	HELPER = 'helper',
	HIDDEN = 'hidden',
	HOVER = 'hover',
	HREF = 'href',
	HREF_JAVASCRIPT = 'javascript:;',
	ID = 'id',
	INPUT = 'input',
	INPUTS = 'inputs',
	INPUT_NAME = 'inputName',
	LABEL = 'label',
	LABEL_NODE = 'labelNode',
	NAME = 'name',
	NODE_NAME = 'nodeName',
	OFF = 'off',
	ON = 'on',
	RATING = 'rating',
	RATING_ELEMENT = 'ratingElement',
	SELECTED_INDEX = 'selectedIndex',
	SHOW_TITLE = 'showTitle',
	SIZE = 'size',
	TITLE = 'title',
	TOTAL = 'total',
	VALUE = 'value',

	EV_RATING_ITEM_CLICK = 'itemClick',
	EV_RATING_ITEM_SELECT = 'itemSelect',
	EV_RATING_ITEM_OUT = 'itemOut',
	EV_RATING_ITEM_OVER = 'itemOver',

	getCN = A.getClassName,

	CSS_CLEAR_FIX = getCN(HELPER, CLEARFIX),
	CSS_ACCESSIBLE = getCN(HELPER, HIDDEN, ACCESSIBLE),
	CSS_RATING_LABEL_EL = getCN(RATING, LABEL, ELEMENT),
	CSS_RATING_EL = getCN(RATING, ELEMENT),
	CSS_RATING_EL_HOVER  = getCN(RATING, ELEMENT, HOVER),
	CSS_RATING_EL_OFF = getCN(RATING, ELEMENT, OFF),
	CSS_RATING_EL_ON = getCN(RATING, ELEMENT, ON),
	CSS_RATING_TOTAL = getCN(RATING, TOTAL),

	TPL_LABEL = '<div class="'+CSS_RATING_LABEL_EL+'"></div>',
	TPL_RATING_EL = '<label><input type="radio" name="" /></label>',
	TPL_RATING_EL_DISABLED = '<span></span>';
	TPL_TOTAL_LABEL = '<span class="'+CSS_ACCESSIBLE+' '+CSS_RATING_TOTAL+'"></span>'

/**
 * <p><img src="assets/images/aui-rating/main.png"/></p>
 *
 * A base class for Rating, providing:
 * <ul>
 *    <li>A non-obstrusive star rating control</li>
 *    <li>Could be based on a set of radio input boxes</li>
 * </ul>
 *
 * Quick Example:<br/>
 *
 * <pre><code>var instance = new A.Rating({
 *   boundingBox: '#rating',
 *   defaultSelected: 3,
 *   disabled: false,
 *   label: 'Label'
 * }).render();
 * </code></pre>
 *
 * Check the list of <a href="Rating.html#configattributes">Configuration Attributes</a> available for
 * Rating.
 *
 * @param config {Object} Object literal specifying widget configuration properties.
 *
 * @class Rating
 * @constructor
 * @extends Component
 */
var Rating = A.Component.create(
	{
		/**
		 * Static property provides a string to identify the class.
		 *
		 * @property Rating.NAME
		 * @type String
		 * @static
		 */
		NAME: 'rating',

		/**
		 * Static property used to define the default attribute
		 * configuration for the Rating.
		 *
		 * @property Rating.ATTRS
		 * @type Object
		 * @static
		 */
		ATTRS: {
			/**
			 * Whether the Rating is disabled or not. Disabled Ratings don't allow
	         * hover or click, just display selected stars.
			 *
			 * @attribute disabled
			 * @default false
			 * @type boolean
			 */
			disabled: {
				value: false,
				validator: isBoolean
			},

			/**
			 * If <code>true</code> could be reseted (i.e., have no values
	         * selected).
			 *
			 * @attribute canReset
			 * @default true
			 * @type boolean
			 */
			canReset: {
				value: true,
				validator: isBoolean
			},

			/**
			 * The number of selected starts when the Rating render.
			 *
			 * @attribute defaultSelected
			 * @default 0
			 * @writeOnce
			 * @type Number
			 */
			defaultSelected: {
				value: 0,
				validator: isNumber
			},

			/**
			 * <a href="NodeList.html">NodeList</a> of elements used on the
	         * Rating. Each element is one Star.
			 *
			 * @attribute elements
			 * @writeOnce
			 * @readOnly
			 * @type NodeList
			 */
			elements: {
				validator: isNodeList
			},

			/**
			 * Inputs to handle the selected value.
			 *
			 * @attribute inputs
			 * @type Node
			 */
			inputs: {
				validator: isNodeList
			},

			/**
			 * Name of the <a
	         * href="Rating.html#config_hiddenInput">hiddenInput</a> element. If
	         * not specified will use the name of the replaced radio.
			 *
			 * @attribute inputName
			 * @default ''
			 * @type String
			 */
			inputName: {
				value: BLANK,
				validator: isString
			},

			/**
			 * Label to be displayed with the Rating elements.
			 *
			 * @attribute label
			 * @default ''
			 * @type String
			 */
			label: {
				value: BLANK,
				validator: isString
			},

			/**
			 * DOM Node to display the text of the StarRating. If not
             * specified try to query using HTML_PARSER an element inside
             * boundingBox which matches <code>aui-rating-label-element</code>.
			 *
			 * @attribute labelNode
			 * @default Generated div element.
			 * @type String
			 */
			labelNode: {
				valueFn: function() {
					return A.Node.create(TPL_LABEL);
				},
				validator: isNode
			},

			ratingElement: {
				valueFn: function() {
					var instance = this;

					var ratingElement = A.Node.create(
						instance.get(DISABLED) ? TPL_RATING_EL_DISABLED : TPL_RATING_EL
					);

					return ratingElement.addClass(CSS_RATING_EL);
				}
			},

			/**
			 * Stores the index of the selected element.
			 *
			 * @attribute selectedIndex
			 * @default -1
			 * @type Number
			 */
			selectedIndex: {
				value: -1,
				validator: isNumber
			},

			/**
			 * If <code>true</code> will extract the value of the
	         * <code>title</code> attribute on the radio, and use it on the
	         * generated Rating elements.
			 *
			 * @attribute showTitle
			 * @default true
			 * @type boolean
			 */
			showTitle: {
				value: true,
				validator: isBoolean
			},

			/**
			 * Number of Rating elements to be displayed.
			 *
			 * @attribute size
			 * @default 5
			 * @type Number
			 */
			size: {
				value: 5,
				validator: function(v) {
					return isNumber(v) && (v > 0);
				}
			},

			/**
			 * If set, will be used when there is no DOM <code>title</code> on the
	         * radio elements.
			 *
			 * @attribute title
			 * @default null
			 * @type String
			 */
			title: null,

			/**
			 * Stores the value of the current selected Rating element.
			 *
			 * @attribute value
			 * @default null
			 * @type String
			 */
			value: null
		},

		/**
		 * Object hash, defining how attribute values are to be parsed from
		 * markup contained in the widget's content box.
		 *
		 * @property StarRating.HTML_PARSER
		 * @type Object
		 * @static
		 */
		HTML_PARSER: {
			elements: function(srcNode) {
				return srcNode.all(DOT+CSS_RATING_EL);
			},

			inputs: function(srcNode) {
				var instance = this;
				return instance.get(BOUNDING_BOX).all(INPUT);
			},

			label: function(srcNode) {
				var labelNode = srcNode.one(DOT+CSS_RATING_LABEL_EL);

				if (labelNode) {
					return labelNode.html();
				}
			},

			labelNode: DOT+CSS_RATING_LABEL_EL
		},

		prototype: {
			/**
			 * Construction logic executed during Rating instantiation. Lifecycle.
			 *
			 * @method initializer
			 * @protected
			 */
			initializer: function(){
				var instance = this;

				instance.inputElementsData = {};

				instance.after('labelChange', this._afterSetLabel);
			},

			/**
			 * Create the DOM structure for the Rating. Lifecycle.
			 *
			 * @method renderUI
			 * @protected
			 */
			renderUI: function () {
				var instance = this;
				var contentBox = instance.get(CONTENT_BOX);

				contentBox.addClass(CSS_CLEAR_FIX);

				instance._renderLabel();
				instance._renderElements();
				instance._parseInputElements();
			},

			/**
			 * Bind the events on the Rating UI. Lifecycle.
			 *
			 * @method bindUI
			 * @protected
			 */
			bindUI: function () {
				var instance = this;
				var inputs = instance.get(INPUTS);

				instance._createEvents();
				instance.on('click', instance._handleClickEvent);
				instance.on('mouseover', instance._handleMouseOverEvent);
				instance.on('mouseout', instance._handleMouseOutEvent);

				inputs.on('focus', function (event) { instance._handleKeyboardFocusEvent(event, instance); });
				inputs.on('key', function (event) { instance._handleKeyboardSelectEvent(event, instance); }, 'down:13');
				inputs.on('blur', function (event) { instance._handleKeyboardBlurEvent(event, instance); });
			},

			/**
			 * Sync the Rating UI. Lifecycle.
			 *
			 * @method syncUI
			 * @protected
			 */
			syncUI: function(){
				var instance = this;

				instance._syncElements();
				instance._syncLabelUI();
			},

			/**
			 * Clear all selected starts to the default state.
			 *
			 * @method clearSelection
			 */
			clearSelection: function() {
				var instance = this;

				instance.get(ELEMENTS).each(function(node) {
					node.removeClass(CSS_RATING_EL_ON);
					node.removeClass(CSS_RATING_EL_HOVER);
				});
			},

			/**
			 * Selects the <code>index</code> Rating element.
			 *
			 * @method select
			 * @param {Number} index Index to be selected
			 */
			select: function(index) {
				var instance = this;
				var oldIndex = instance.get(SELECTED_INDEX);
				var canReset = instance.get(CAN_RESET);

				// clear selection when the selected element is clicked
				if (canReset && (oldIndex == index)) {
					index = -1;
				}

				instance.set(SELECTED_INDEX, index);

				var selectedIndex = instance.get(SELECTED_INDEX);
				var	data = instance._getInputData(selectedIndex);

				var title = (TITLE in data) ? data.title : BLANK;
				var value = (VALUE in data) ? data.value : selectedIndex;

				instance.fillTo(selectedIndex);
				
				var inputs = instance.get(INPUTS);
				if (selectedIndex >= 0 && inputs.size() > 0) inputs.item(selectedIndex).setAttribute('checked','checked');

				instance.set(TITLE, title);
				instance.set(VALUE, value);
			},

			/**
			 * Add the <code>className</code> on the the <code>index</code> element
		     * and all the previous Rating elements.
			 *
			 * @method fillTo
			 * @param {Number} index Index to be selected
			 * @param {String} className Class name to be applied when fill the Rating elements
			 */
			fillTo: function(index, className) {
				var instance = this;

				instance.clearSelection();

				if (index >= 0) {
					instance.get(ELEMENTS).some(function(node, i) {
						node.addClass(className || CSS_RATING_EL_ON);

						// stop loop when return true
						return (i == Math.floor(index));
					});
				}
			},

			/**
			 * Finds the index of the <code>elem</code>.
			 *
			 * @method indexOf
			 * @param {Node} elem Rating element
			 * @return {Number}
			 */
			indexOf: function(elem) {
				var instance = this;

				if (elem.get('nodeName').toLowerCase() == INPUT) return instance.get(INPUTS).indexOf(elem);
				else return instance.get(ELEMENTS).indexOf(elem);
			},

			/**
			 * Check if the Rating element can fire the custom events. Disabled
		     * elements won't fire nothing.
			 *
			 * @method _canFireCustomEvent
			 * @param {EventFacade} event
			 * @protected
			 * @return {Boolean}
			 */
			_canFireCustomEvent: function(event) {
				var instance = this;
				var domTarget = event.domEvent.target;

				// checks if the widget is not disabled and if the dom event is firing with a item as target
				// do not fire custom events for other elements into the boundingBox
				return !instance.get(DISABLED) && domTarget.hasClass(CSS_RATING_EL);
			},

			/**
			 * Create rating elements based on the <code>size</code>
             * attribute. It's only invoked when the HTML_PARSER does not find
             * nothing.
			 *
			 * @method _createElements
			 * @protected
			 * @return {NodeList}
			 */
			_createElements: function() {
				var instance = this;
				var elements = [];
				var ratingElement = instance.get(RATING_ELEMENT);
				var inputs = instance.get(INPUTS);
				var labels = instance.get(BOUNDING_BOX).all(LABEL);

				if (inputs.size()) {
					instance.set(SIZE, inputs.size());
					inputs.each(function (node, index) {
						var label;
						var id = node.get(ID);
						
						if (id) label = labels.filter('[for="'+node.get('id')+'"]').item(0);
						if (!label) label = node.ancestor(function (parent) { return parent.get('nodeName').toLowerCase() == 'label'; });
						
						if (label) {
							var value = node.getAttribute(VALUE) != '' ? node.getAttribute(VALUE) : index;
							var name = node.getAttribute(NAME) != '' ? node.getAttribute(NAME) : this.get(INPUT_NAME);
							var size = inputs.size();
							var labelText = label.text();
						} else {
							var value = node.getAttribute(VALUE) != '' ? node.getAttribute(VALUE) : index;
							var name = node.getAttribute(NAME) != '' ? node.getAttribute(NAME) : this.get(INPUT_NAME);
							var size = inputs.size();
							var labelname = (value==0) ? 'rate-this-x-stars-out-of-x-singular' : 'rate-this-x-stars-out-of-x';
							var labelText = Liferay.Language.get(
								labelname,
								[value, size]
							);
						}
						if (node.get('checked')) instance.set(DEFAULT_SELECTED, parseInt(value));
						node.remove();
						if (label) label.remove();
						elements.push(
							ratingElement.clone()
						);
						var newEl = elements[elements.length - 1];
						newEl.one(INPUT).setAttribute(VALUE, value).setAttribute(NAME, name);
						newEl.prepend(labelText);
					});
				} else {
					for (var i = 0, size = this.get(SIZE); i < size; i++) {
						elements.push(
							ratingElement.clone()
						);
						if (!instance.get(DISABLED)) {
							var newEl = elements[elements.length - 1];
							newEl.one(INPUT).setAttribute(VALUE, i+1).setAttribute(NAME, this.get(INPUT_NAME));
							var labelname = (i==0) ? 'rate-this-x-stars-out-of-x-singular' : 'rate-this-x-stars-out-of-x';
							newEl.prepend(Liferay.Language.get(
								labelname,
								[i+1, size]
							));
						}
					}
					if (instance.get(DISABLED)) {
						var totalLabel = Liferay.Language.get('star-rating-total', [instance.get(DEFAULT_SELECTED), instance.get(SIZE)]);
						var totalNode = A.Node.create(TPL_TOTAL_LABEL);
						totalNode.text(totalLabel);
						instance.get(BOUNDING_BOX).append(totalNode);
					}
				}

				return new A.NodeList(elements);
			},

			/**
			 * Create the custom events.
			 *
			 * @method _createEvents
			 * @protected
			 */
			_createEvents: function() {
				var instance = this;

				// create publish function for kweight optimization
				var publish = function(name, fn) {
					instance.publish(name, {
			            defaultFn: fn,
			            queuable: false,
			            emitFacade: true,
			            bubbles: true
			        });
				};

				/**
				 * Handles the itemClick event.
				 *
				 * @event itemClick
				 * @preventable _defRatingItemClickFn
				 * @param {Event.Facade} event The itemClick event.
				 * @type {Event.Custom}
				 */
				publish(
					EV_RATING_ITEM_CLICK,
					this._defRatingItemClickFn
				);

				/**
				 * Handles the itemSelect event.
				 *
				 * @event itemSelect
				 * @preventable _defRatingItemSelectFn
				 * @param {Event.Facade} event The itemSelect event.
				 * @type {Event.Custom}
				 */
				publish(
					EV_RATING_ITEM_SELECT,
					this._defRatingItemSelectFn
				);

				/**
				 * Handles the itemOver event.
				 *
				 * @event itemSelect
				 * @preventable _defRatingItemOverFn
				 * @param {Event.Facade} event The itemOver event.
				 * @type {Event.Custom}
				 */
				publish(
					EV_RATING_ITEM_OVER,
					this._defRatingItemOverFn
				);

				/**
				 * Handles the itemOut event.
				 *
				 * @event itemOut
				 * @preventable _defRatingItemOutFn
				 * @param {Event.Facade} event The itemOut event.
				 * @type {Event.Custom}
				 */
				publish(
					EV_RATING_ITEM_OUT,
					this._defRatingItemOutFn
				);
			},

			/**
			 * Fires the itemClick event.
			 *
			 * @method _defRatingItemClickFn
			 * @param {EventFacade} event itemClick event facade
			 * @protected
			 */
			_defRatingItemClickFn: function(event) {
				var instance = this;
				var domEvent = event.domEvent;

				instance.fire(EV_RATING_ITEM_SELECT, {
					delegateEvent: event,
					domEvent: domEvent,
					ratingItem: domEvent.target
				});
			},

			/**
			 * Fires the itemSelect event.
			 *
			 * @method _defRatingItemSelectFn
			 * @param {EventFacade} event itemSelect event facade
			 * @protected
			 */
			_defRatingItemSelectFn: function(event) {
				var instance = this;
				var domTarget = event.domEvent.target;

				instance.select(
					instance.indexOf(domTarget)
				);
			},

			/**
			 * Fires the itemOut event.
			 *
			 * @method _defRatingItemOutFn
			 * @param {EventFacade} event itemOut event facade
			 * @protected
			 */
			_defRatingItemOutFn: function(event) {
				var instance = this;

				instance.fillTo(
					instance.get(SELECTED_INDEX)
				);
			},

			/**
			 * Fires the itemOver event.
			 *
			 * @method _defRatingItemOverFn
			 * @param {EventFacade} event itemOver event facade
			 * @protected
			 */
			_defRatingItemOverFn: function(event) {
				var instance = this;
				var index = instance.indexOf(event.domEvent.target);

				instance.fillTo(index, CSS_RATING_EL_HOVER);
			},

			/**
			 * Parse the HTML radio elements from the markup to be Rating elements.
			 *
			 * @method _parseInputElements
			 * @protected
			 */
			_parseInputElements: function() {
				var instance = this;
				var boundingBox = instance.get(BOUNDING_BOX);
				var inputs = boundingBox.all(INPUT);
				var size = inputs.size();

				inputs.each(function(node, index) {
					instance.inputElementsData[index] = {
						value: node.getAttribute(VALUE) || index,
						title: node.getAttribute(TITLE)
					};
				});
			},

			/**
			 * Render the Rating label.
			 *
			 * @method _renderLabel
			 * @protected
			 */
			_renderLabel: function() {
				var instance = this;

				instance.get(CONTENT_BOX).append(
					instance.get(LABEL_NODE)
				);
			},

			/**
			 * Render the Rating elements.
			 *
			 * @method _renderElements
			 * @protected
			 */
			_renderElements: function(elements) {
				var instance = this;
				var contentBox = instance.get(CONTENT_BOX);

				// if not found any elements from the HTML_PARSER create them based on the size attribute
				if (!instance.get(ELEMENTS).size()) {
					instance.set(
						ELEMENTS,
						instance._createElements()
					);
					instance.set(INPUTS, instance.get(ELEMENTS).all(INPUT));
				}

				instance.get(ELEMENTS).each(
					function(element, i) {
						var	data = instance._getInputData(i);

						var content = data.content;

						// try to use the pulled title data from the dom, otherwise use the TITLE attr, in the last case use the content
						var title = data.title || instance.get(TITLE) || content;

						// setting the content
						if (content || title) {
							// if there is no content use the title as content
							element.html(content || title);
						}

						// setting the title
						if (title && instance.get(SHOW_TITLE)) {
							element.setAttribute(TITLE, title);
						}

						if (!element.attr(HREF) && (element.get(NODE_NAME).toLowerCase() == ANCHOR)) {
							element.setAttribute(HREF, HREF_JAVASCRIPT);
						}

						contentBox.appendChild(element);
					}
				);
				
				instance.get(INPUTS).addClass(CSS_ACCESSIBLE);
			},

			/**
			 * Sync the Rating elements.
			 *
			 * @method _syncElements
			 * @protected
			 */
			_syncElements: function() {
				var instance = this;
				var selectedIndex = instance.get(DEFAULT_SELECTED) - 1;

				instance.set(SELECTED_INDEX, selectedIndex);

				instance.select();
			},

			/**
			 * Sync the Rating label UI.
			 *
			 * @method _syncLabelUI
			 * @protected
			 */
			_syncLabelUI: function() {
				var instance = this;
				var labelText = instance.get(LABEL);

				instance.get(LABEL_NODE).html(labelText);
			},

			/**
			 * Get the <code>index</code> element input data stored on <a
		     * href="Rating.html#property_inputElementsData">inputElementsData</a>.
			 *
			 * @method _getInputData
			 * @protected
			 */
			_getInputData: function(index) {
				var instance = this;

				return instance.inputElementsData[index] || {};
			},

			/**
			 * Fires the click event.
			 *
			 * @method _handleClickEvent
			 * @param {EventFacade} event click event facade
			 * @protected
			 */
			_handleClickEvent: function(event, instance) {
				instance = this;

				if (instance._canFireCustomEvent(event)) {
					instance.fire(EV_RATING_ITEM_CLICK, {
						delegateEvent: event,
						domEvent: event.domEvent
					});
				}
			},

			/**
			 * Fires the mouseOut event.
			 *
			 * @method _handleMouseOutEvent
			 * @param {EventFacade} event mouseOut event facade
			 * @protected
			 */
			_handleMouseOutEvent: function(event) {
				var instance = this;

				if (instance._canFireCustomEvent(event)) {
					instance.fire(EV_RATING_ITEM_OUT, {
						delegateEvent: event,
						domEvent: event.domEvent
					});
				}
			},

			/**
			 * Fires the mouseOver event.
			 *
			 * @method _handleMouseOverEvent
			 * @param {EventFacade} event mouseOver event facade
			 * @protected
			 */
			_handleMouseOverEvent: function(event) {
				var instance = this;

				if (instance._canFireCustomEvent(event)) {
					instance.fire(EV_RATING_ITEM_OVER, {
						delegateEvent: event,
						domEvent: event.domEvent
					});
				}
			},

			/**
			 * Fires the focus event.
			 *
			 * @method _handleKeyboardFocusEvent
			 * @param {EventFacade} event focus event facade
			 * @protected
			 */
			_handleKeyboardFocusEvent: function(event, instance) {
				if (typeof(instance) == 'undefined') instance = this;

				instance.fire(EV_RATING_ITEM_OVER, {
					delegateEvent: event,
					domEvent: event,
					ratingItem: event.target
				});
			},

			/**
			 * Fires the blur event.
			 *
			 * @method _handleKeyboardBlurEvent
			 * @param {EventFacade} event blur event facade
			 * @protected
			 */
			_handleKeyboardBlurEvent: function(event, instance) {
				if (typeof(instance) == 'undefined') instance = this;

				instance.fire(EV_RATING_ITEM_OUT, {
					delegateEvent: event,
					domEvent: event,
					ratingItem: event.target
				});
			},

			/**
			 * Fires the key select event.
			 *
			 * @method _handleKeyboardSelectEvent
			 * @param {EventFacade} event key event facade
			 * @protected
			 */
			_handleKeyboardSelectEvent: function(event, instance) {
				if (typeof(instance) == 'undefined') instance = this;

				instance.fire(EV_RATING_ITEM_SELECT, {
					delegateEvent: event,
					domEvent: event,
					ratingItem: event.target
				});
			},

			/**
			 * Fires after the value of the
			 * <a href="Rating.html#config_label">label</a> attribute change.
			 *
			 * @method _afterSetLabel
			 * @param {EventFacade} event
			 * @protected
			 */
			_afterSetLabel: function(event) {
				this._syncLabelUI();
			}
		}
	}
);

/*
* ThumbRating
*/
var DOWN = 'down',
	THUMB = 'thumb',
	THUMB_RATING = 'ThumbRating',
	UP = 'up',

	CSS_RATING_THUMB_DOWN = getCN(RATING, THUMB, DOWN),
	CSS_RATING_THUMB_UP = getCN(RATING, THUMB, UP);

/**
 * <p><img src="assets/images/aui-rating/thumb-rating.png"/></p>
 *
 * A base class for ThumbRating, providing:
 * <ul>
 *    <li>A non-obstrusive star rating control using Thumb up and Thumb down icons</li>
 *    <li>Could be based on a set of radio input boxes</li>
 * </ul>
 *
 * Quick Example:<br/>
 *
 * <pre><code>var instance = new A.ThumbRating({
 *   boundingBox: '#rating',
 *   defaultSelected: 3,
 *   disabled: false,
 *   label: 'Label'
 * }).render();
 * </code></pre>
 *
 * Check the list of <a href="ThumbRating.html#configattributes">Configuration Attributes</a> available for
 * ThumbRating.
 *
 * @param config {Object} Object literal specifying widget configuration properties.
 *
 * @class ThumbRating
 * @constructor
 * @extends Rating
 */
var ThumbRating = A.Component.create(
	{
		/**
		 * Static property provides a string to identify the class.
		 *
		 * @property ThumbRating.NAME
		 * @type String
		 * @static
		 */
		NAME: THUMB_RATING,

		/**
		 * Static property used to define the default attribute
		 * configuration for the ThumbRating.
		 *
		 * @property ThumbRating.ATTRS
		 * @type Object
		 * @static
		 */
		ATTRS: {
			/**
			 * The size on ThumbRating is always 2 (i.e., thumb up and thumb down).
			 *
			 * @attribute size
			 * @default 2
			 * @readOnly
			 * @type Number
			 */
			size: {
				value: 2,
				readOnly: true
			}
		},

		EXTENDS: Rating,

		prototype: {
			/**
			 * Create the DOM structure for the ThumbRating. Lifecycle.
			 *
			 * @method renderUI
			 * @protected
			 */
			renderUI: function() {
				var instance = this;

				ThumbRating.superclass.renderUI.apply(this, arguments);

				var elements = instance.get(ELEMENTS);

				elements.addClass(CSS_RATING_EL_OFF);
				elements.item(0).addClass(CSS_RATING_THUMB_UP);
				elements.item(1).addClass(CSS_RATING_THUMB_DOWN);
			},

			/**
			 * Add the <code>className</code> on the the <code>index</code> element
		     * and all the previous Rating elements.
			 *
			 * @method fillTo
			 * @param {Number} index Index to be selected
			 * @param {String} className Class name to be applied when fill the Rating elements
			 */
			fillTo: function(index, className) {
				this.clearSelection();

				if (index >= 0) {
					this.get(ELEMENTS).item(index).addClass(className || CSS_RATING_EL_ON);
				}
			},

			/**
			 * Empty method, no logic needed on this method on ThumbRating.
			 *
			 * @method _syncElements
			 * @protected
			 */
			_syncElements: function() {}
		}
	}
);

A.Rating = Rating;
A.StarRating = Rating;
A.ThumbRating = ThumbRating;

}, '1.0.1' ,{skinnable:true, requires:['aui-base']});
