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

AUI.add("removable", function(A) {
    function Removable(config) {
        Removable.superclass.constructor.apply(this, arguments);
    }
    Removable.NAME = "Removable";
    Removable.NS = "Removable";
	
	Removable.ATTRS = {
		btns: {value:{}}
		, msgTimeoutLength: {value:30000}
		
		, alertArea: {}
		
		, returnData: {
			value: {}
		}
	}
	
	var UNDO_BUTTON_TEMPLATE = '<button class="inline archive-course-undo">'+Liferay.Language.get('undo')+'</button>';
	var ACTIONS_TEMPLATE = '<div class="alert-actions"></div>';
	var SUCCESS_MSG_TEMPLATE = '<div class="portlet-msg-success"></div>';
	var ERROR_MSG_TEMPLATE = '<div class="portlet-msg-error"></div>';

    A.extend(Removable, A.Plugin.Base, {
		initializer: function () {
			var host = this.get('host');
			if (host.get('nodeName').toLowerCase() == 'tr') var topnode = host.ancestor('table');
			else topnode = host.get('parentNode');
			var alertArea = topnode.get('parentNode').get('children').filter('.notification-area');
			if (alertArea.size() == 0) {
				alertArea = A.Node.create('<div class="notification-area"></div>');
				topnode.insert(alertArea, 'before');
			} else {
				alertArea = alertArea.item(0);
			}
			alertArea.setAttrs({
				'aria-live':'assertive',
				'aria-atomic':'false'
			});
			this.set('alertArea', alertArea);
			
			this.publish('remove', { defaultFn: this.remove, broadcast: false });
			this.publish('unremove', { defaultFn: this.unremove, broadcast: false });
			this.publish('confirm', { defaultFn: this._confirm, broadcast: false });
			this.publish('removeSuccess', { defaultFn: this._removeItem, broadcast: true });
			this.publish('unremoveSuccess', { defaultFn: this._unremoveItem, broadcast: true });
		}
        , destructor: function () {
        }
		
		, registerBtn: function (btnCfg) {
			var context = this;
			var defaultCfg = {
				  btnClass: ''
				, confirm: false
				, showUndoButton: true
				, itemNameClass: ''
				, text: {
					  successMsg: ''
					, errorMsg: ''
					, cancelSuccessMsg: ''
					, cancelErrorMsg: ''
					, confirmMsg: ''
					, confirmBtn: ''
				}
				, url: ''
				, cancelUrl: ''
			}
			var btn = context.get('host').one(btnCfg.btnClass);
			if (!btn) {
				context.get('host').unplug('A.NTER.Removable');
				return;
			} else {
				var id = btn.getAttribute('id');
				if (id == '') {
					id = A.guid();
					btn.setAttribute('id', id);
				}
				btnCfg.btn = btn;
				btnCfg.url = btn.getAttribute('data-url');
				if (typeof btnCfg.cancelUrl == 'undefined' || btnCfg.cancelUrl == '') btnCfg.cancelUrl = btnCfg.url;
				btnCfg.data = {};
				btnCfg.cancelData = {};
				var idName = btn.getAttribute('data-id-name');
				var idValue = btn.getAttribute('data-id');
				var flagName = btn.getAttribute('data-flag-name');
				btnCfg.data[idName] = btnCfg.cancelData[idName] = idValue;
				if (flagName != '') {
					btnCfg.data[flagName] = true;
					btnCfg.cancelData[flagName] = false;
				}

				for (var i in btnCfg) {
					defaultCfg[i] = btnCfg[i];
				}
				btnCfg = defaultCfg;
				context.get('btns')[id] = btnCfg;
				
				btn.on('click', function (e) { context._handleRemove(e); });
			}
		}

		, _getCfg: function (event) {
			var btn = event.target;
			var id = btn.getAttribute('id');
			return this.get('btns')[id];
		}

		, _handleRemove: function (event) {
			var context = this;
			event.preventDefault();
			var btnCfg = context._getCfg(event);

			if (btnCfg.confirm) {
				context.fire('confirm', btnCfg);
			} else {
				context.fire('remove', btnCfg);
			}
		}

		, _handleUnremove: function (event, btnCfg) {
			var context = this;
			event.preventDefault();
			var alertMsg = event.target.ancestor('.portlet-msg-success');

			context.fire('unremove', btnCfg, alertMsg);
		}

		, _confirm: function (btnCfg) {
			var context = this;

			var dialog = new A.NTER.ConfirmDialog({
				confirmMsg: btnCfg.text.confirmMsg,
				confirmBtn: btnCfg.text.confirmBtn,
				handler: function () { context.fire('remove', btnCfg) }
			}).render();
			dialog.on('destroy', function () { btnCfg.btn.focus(); });
		}
		
		, remove: function (btnCfg) {
			var item = this.get('host');
			var context = this;

			A.io(btnCfg.url, {
				  method: 'POST'
				, data: btnCfg.data
				, headers: {
					'Accept': 'application/json'
				}
				, on: {
					start: function () { item.plug(A.NTER.ChangeSpinner); }
					, complete: function () { item.unplug(A.NTER.ChangeSpinner); }
					, success: function (id, response) {
						try {
							var data = A.JSON.parse(response.responseText);
						} catch (e) {
							// TODO: we should catch json parse errors properly when all the json responses are consistent
							//context.form.prepend(Y.Node.create('<div class="portlet-msg-error">'+Liferay.Language.get('ajax-fail')+'</div>'));
							//return;
							var data = {success:true};
						}
						if (data.success) {
							context.set('returnData', data);
							setTimeout(function () {
								context.fire('removeSuccess', btnCfg);
							}, 0);
						} else {
							// TODO: handle this when all the json responses are consistent
						}
					}
					, failure: function () { context._iofail(btnCfg.text.errorMsg, this.get('alertArea')); }
				}
			});
		}
		
		, _removeItem: function (btnCfg) {
			var context = this;
			var alertArea = context.get('alertArea');
			var item = context.get('host');

			var undoButton = btnCfg.showUndoButton ? A.Node.create(UNDO_BUTTON_TEMPLATE) : "";
			var actions = A.Node.create(ACTIONS_TEMPLATE).append(undoButton);
			var content = A.Node.create(SUCCESS_MSG_TEMPLATE).append(btnCfg.text.successMsg).append(actions);
			var alertMsg = content;
			alertMsg.setStyles({'position':'relative'});
			alertArea.append(alertMsg);

			var fadeTimer = setTimeout(function () { alertMsg.remove(); item.remove(); }, context.get('msgTimeoutLength'));
			//var focusTimer = setTimeout(function () { undoButton.focus(); }, 10);	// this makes the screen reader stop reading the message and just read the button
			if (btnCfg.showUndoButton) {
				undoButton.on('click', function (e) { clearTimeout(fadeTimer); context._handleUnremove(e, btnCfg); });
			}
			item.fadeOut();
		}

		, unremove: function (btnCfg, alertMsg) {
			var context = this;
			var item = context.get('host');
			
			alertMsg.plug(A.NTER.ChangeSpinner);

			A.io(btnCfg.cancelUrl, {
				method: 'POST'
				, data: btnCfg.cancelData
				, headers: {
					'Accept': 'application/json'
				}
				, on: {
					  start: function () {}
					, complete: function () { alertMsg.unplug(A.NTER.ChangeSpinner); alertMsg.remove(); }
					, success: function (id, response) {
						try {
							var data = A.JSON.parse(response.responseText);
						} catch (e) {
							// TODO: we should catch json parse errors properly when all the json responses are consistent
							//context.form.prepend(Y.Node.create('<div class="portlet-msg-error">'+Liferay.Language.get('ajax-fail')+'</div>'));
							//return;
							var data = {success:true};
						}
						if (data.success) {
							context.set('returnData', data);
							setTimeout(function () {
								context.fire('unremoveSuccess', btnCfg);
							}, 0);
						} else {
							// TODO: handle this when all the json responses are consistent
						}
					}
					, failure: function () { context._iofail(btnCfg.text.cancelErrorMsg, alertArea); }
				}
			}, 1000);
		}
		
		, _unremoveItem: function (btnCfg) {
			var context = this;
			var alertArea = context.get('alertArea');
			var item = context.get('host');
			item.fadeIn({callback:function () { item.one('a,button,input,select,textarea,[tabindex>0],iframe,object,video,audio').focus(); }});
			alertMsg = A.Node.create('<div class="icon-accessibility-text">'+btnCfg.text.cancelSuccessMsg+'</div>');
			alertArea.append(alertMsg);
			fadeTimer = setTimeout(function () { alertMsg.remove(); }, context.get('msgTimeoutLength'));
		}
		
		, _iofail: function (msg, alertArea) {
			var context = this;
			var alertMsg = A.Node.create(ERROR_MSG_TEMPLATE).append(msg).setStyles({'position':'relative'});
			alertArea.append(alertMsg);
			fadeTimer = setTimeout(function () { alertMsg.remove(); }, context.get('msgTimeoutLength'));
		}
    });

    A.namespace('NTER').Removable = Removable;

}, "3.2.0", {requires:['base','node-base','dom-base','plugin','confirm-dialog', 'change-spinner', 'anim-custom', 'io-base']});

AUI.add("change-spinner", function(A) {
    function ChangeSpinner(config) {
        ChangeSpinner.superclass.constructor.apply(this, arguments);
    }
    ChangeSpinner.NAME = "ChangeSpinner";
    ChangeSpinner.NS = "Spinner";
	
	ChangeSpinner.ATTRS = {
		spinner: {}
		, wrapper: {}
		, startTimer: {}
	}
	
	ChangeSpinner.SPINNERTEMPLATE = '<div class="change-content-spinner"><span class="icon-accessibility-text">'+Liferay.Language.get('loading')+'</span></div>';
	ChangeSpinner.WRAPPERTEMPLATE = '<div class="change-content-spinner-wrapper" style="position:absolute;z-index:200;"></div>';

    A.extend(ChangeSpinner, A.Plugin.Base, {
		initializer : function() {
			var context = this;
			this.set('startTimer', setTimeout(function () {
				var node = context.get('host');
				node.setAttribute('aria-busy',true);
				context.set('spinner',A.Node.create(ChangeSpinner.SPINNERTEMPLATE));
				var spinner = context.get('spinner');
				if (node.get('nodeName').toLowerCase() == 'tr') {
					context.set('wrapper',A.Node.create(ChangeSpinner.WRAPPERTEMPLATE));
					var wrapper = context.get('wrapper');
					var parent = node.one('tr,td');
					wrapper.append(spinner);
					parent.prepend(wrapper);
					if (parent.getStyle('position') == 'static') parent.setStyle('position','relative');
					parent.setStyle('overflow','visible');
					spinner.setStyles({
						  height:node.get('offsetHeight')
						, width: node.get('offsetWidth')
					});
					wrapper.setStyles({
						  marginTop:'-'+(parent.getStyle('paddingTop'))
						, marginLeft:'-'+(parent.getStyle('paddingLeft'))
						, marginRight:'-'+(parent.getStyle('paddingRight'))
						, marginBottom:'-'+(parent.getStyle('paddingBottom'))
					});
				} else {
					if (node.getStyle('position') == 'static') node.setStyle('position','relative');
					node.append(context.get('spinner'));
				}
			}, 50));
		}
        , destructor : function() {
			var spinner = this.get('spinner');
			var wrapper = this.get('wrapper');
			clearTimeout(this.get('startTimer'));
			if (spinner) spinner.remove();
			if (wrapper) wrapper.remove();
			this.get('host').setAttribute('aria-busy',false);
        }
    });

    A.namespace('NTER').ChangeSpinner = ChangeSpinner;

}, "3.2.0", {requires:['base','node-base','dom-base','plugin']});

AUI.add("confirm-dialog", function(A) {
    function ConfirmDialog(config) {
        ConfirmDialog.superclass.constructor.apply(this, arguments);
    }
    ConfirmDialog.NAME = "ConfirmDialog";
    ConfirmDialog.ATTRS = {
		  confirmMsg:{value:Liferay.Language.get('are-you-sure')}
		, confirmBtn:{value:''}
		, cancelBtn:{value:Liferay.Language.get('cancel')}
		, confirmAccTxt:{value:Liferay.Language.get('confirm')}
		, handler:{value:function(){}}
		
		, dialog:{}
	};

    A.extend(ConfirmDialog, A.Widget, {
		renderUI : function() {
			var context = this;
			this.set('dialog', new A.NTER.AccessibleDialog({
				centered: true,
				constrain2view: true,
				destroyOnClose: true,
				height: 200,
				width: 300,
				modal: true,
				bodyContent: this.get('confirmMsg'),
				buttons: [
					{
						label: this.get('confirmBtn'),
						handler: function () {
							this.close();
							context.get('handler')();
						}
					},
					{
						label: this.get('cancelBtn'),
						handler: function() {
							this.close();
						}
					}
				]
			}));
			var dialog = this.get('dialog');
			dialog.on('close', function (event) { context.destroy(); });
			dialog.render();
			dialog.focus();
		}
        , destructor : function() {
			var dialog = this.get('dialog');
			if (dialog) dialog.close();
        }
    });

    A.namespace('NTER').ConfirmDialog = ConfirmDialog;

}, "3.2.0", {requires:['base','node-base','dom-base','widget-base', 'accessible-dialog']});