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

YUI.add('review-form', function(Y) {

    function ReviewForm(config) {
        ReviewForm.superclass.constructor.apply(this, arguments);
    }

    ReviewForm.NAME = 'reviewForm';

    ReviewForm.ATTRS = {
        portletId: {},
        submitUrl: {},
		ajaxUrl: {},
		subjectId: {},
		reviewId: {},
		
		dataNodes: {
			value: {
				content: '',
				rating: 0
			}
		},
		returnData: {
			value: {}
		},
		
		isOpen: { value: false }
    };

    ReviewForm.FORM_TEMPLATE = 
			'<form action="{action}" method="post" class="uniForm">'
		+		'<fieldset>'
		+			'<div class="ctrlHolder">'
		+				'<label for="{widgetId}-review-content">'+Liferay.Language.get('review-form-review')+'</label>'
		+ 				'<textarea id="{widgetId}-review-content" name="review-content">{contentValue}</textarea>'
		+			'</div>'
		+			'<div class="ctrlHolder">'
		+				'<p class="label">'+Liferay.Language.get('review-form-rating')+'</p>'
		+				'<div id="{widgetId}-review-rating"></div>'
		+			'</div>'
		+			'<div class="buttonHolder">'
		+				'<button type="submit" class="primaryAction" value="save">'+Liferay.Language.get('save')+'</button>'
		+				'<button type="button" class="secondaryAction" value="cancel">'+Liferay.Language.get('cancel')+'</button>'
		+			'</div>'
		+		'</fieldset>'
		+		'<input type="hidden" name="review-classPK" value="{subjectId}"/>'
		+		'<input type="hidden" name="cid" value="{subjectId}"/>'
		+		'<input type="hidden" name="review-reviewId" value="{reviewId}"/>'
		+	'</form>';

    Y.extend(ReviewForm, Y.Widget, {

        initializer: function() {
			this.publish('save', {
				defaultFn: this._defSaveFn,
				broadcast: 2,
				emitFacade: true,
				bubbles:true
			});
			this.publish('cancel', {
				defaultFn: this._defCancelFn,
				broadcast: 2,
				emitFacade: true,
				bubbles:true
			});
			this.publish('open', {
				defaultFn: this._defOpenFn,
				broadcast: 2,
				emitFacade: true,
				bubbles:true
			});
			this.publish('close', {
				defaultFn: this._defCloseFn,
				broadcast: 2,
				emitFacade: true,
				bubbles:true
			});
			this.publish('saveSuccess', {
				defaultFn: function () {},
				broadcast: 2,
				emitFacade: true,
				bubbles:true
			});
			this.publish('saveFailure', {
				defaultFn: function () {},
				broadcast: 2,
				emitFacade: true,
				bubbles:true
			});
        },

        destructor : function() {
			if (this.get('isOpen')) {
				this.fire('close');
				return;
			}
			this.form.remove();
        },

        renderUI : function() {
			this.form = Y.Node.create(Y.substitute(ReviewForm.FORM_TEMPLATE, {
				widgetId: this.get('id') + '-reviewform',
				action: this.get('submitUrl'),
				contentValue: Y.Lang.trim(this.get('dataNodes').content.html()),
				subjectId: this.get('subjectId'),
				reviewId: this.get('reviewId')
			})); 
			if (!this.get('isOpen')) this.form.setStyle('display', 'none');
			this.get('contentBox').append(this.form);
			
			var ratingNode = this.form.one('#'+this.get('id')+'-reviewform-review-rating');
			var rating = new Y.Rating({
				boundingBox: ratingNode,
				defaultSelected: parseInt(this.get('dataNodes').rating.getAttribute('data-rating')),
				inputName: 'review-rating'
			});
			rating.render();
			var inputs = rating.get('boundingBox').all('input');
			inputs.on('focus', function (event) {
				var newIndex = inputs.indexOf(event.target);
				if (rating.get('selectedIndex') != newIndex) rating.select(newIndex);
			});
        },

        bindUI : function() {
			var instance = this;
			
			var submit = this.form.one('button.primaryAction');
			var cancel = this.form.one('button.secondaryAction');
			
			this.form.on('submit', function (e) { instance._handleSubmit(e); });
			cancel.on('click', function (e) { instance._handleCancel(e); });
        },

        syncUI : function() {
			if (!this.get('isOpen')) this.open();
        },

        // Beyond this point is the ReviewForm specific application and rendering logic
		
		open: function (e) {
			var instance = this;
			setTimeout(function () { instance.fire('open'); }, 0);	// YUI bug?
			instance.form.fadeIn({callback:function(){
				instance.form.one('textarea').focus();
				instance.set('isOpen', true);
			}});
		},
		
		_handleSubmit: function (e) {
			e.preventDefault();
			this.fire('save');
		},
		_handleCancel: function (e) {
			e.preventDefault();
			this.fire('cancel');
		},
		
		_defSaveFn: function (e) {
			var instance = this;
			var postUrl = instance.get('ajaxUrl');
			Y.io(postUrl, {
				method: 'POST',
				form: {id:instance.form}, 
				on: {
					start: function () { instance.form.plug(Y.NTER.ChangeSpinner); },
					complete: function () { instance.form.unplug(Y.NTER.ChangeSpinner);  },
					success: function (id, response) {
						try {
							var data = Y.JSON.parse(response.responseText);
						} catch (e) {
							instance.form.prepend(Y.Node.create('<div class="portlet-msg-error">'+Liferay.Language.get('ajax-fail')+'</div>'));
							return;
						}
						if (data.success) {
							instance._update(data);
							instance.set('returnData', data);
							instance.fire('saveSuccess');
							instance.fire('close');
						} else {
							instance.fire('saveFailure');
							instance.form.all('.portlet-msg-error').remove();
							var errors = data.errors;
							for (var i = 0; i < errors.length; i++) {
								var msg = Y.Node.create('<div class="portlet-msg-error">'+errors[i]+'</div>');
								msg.setStyle('display', 'none');
								instance.form.prepend(msg);
								msg.fadeIn();
							}
						}
					},
					failure: function (id, response) {
						instance.fire('saveFailure');
						var msg = Y.Node.create('<div class="portlet-msg-error">'+Liferay.Language.get('ajax-fail')+'</div>');
						msg.setStyle('display', 'none');
						instance.form.prepend(msg);
						msg.fadeIn();
					}
				}
			});
		},
		_defCancelFn: function (e) {
			this.fire('close');
		},
		_defOpenFn: function (e) {},
		_defCloseFn: function (e) {
			var instance = this;
			instance.form.fadeOut({callback:function(){
				instance.set('isOpen', false);
				instance.destructor();
			}});
		},
		
		
		_update: function (data) {
			var nodes = this.get('dataNodes');
			nodes.content.html(data['review-content']);
			nodes.rating.setAttribute('data-rating', data['review-rating']);
			nodes.rating.empty();
			new Y.Rating({
				boundingBox: nodes.rating,
				defaultSelected: data['review-rating'],
				disabled: true
			}).render();
			
			// highlight
			var review = this.get('boundingBox')
			review.addClass('inline-update');
			setTimeout(function(){
				var fade = new Y.Anim({
					node: review,
					to: { 'backgroundColor':'white' },
					on: { end: function () { review.removeClass('inline-update'); review.removeAttribute('style'); } }
				});
				fade.run();
			}, 3000);
		}
		
    });

    Y.namespace('NTER').ReviewForm = ReviewForm;

}, '3.2.0', {skinnable:true, requires:['widget-base', 'substitute', 'aui-rating', 'anim-custom', 'change-spinner', 'json-parse']});
