AUI.add('anim-custom', function(Y) {
/**
 * Adds custom animations like fadeIn and fadeOut to Node and NodeList
 * which can be called like nodeobject.fadeOut({speed:.5,remove:true});
 */

	var _validate = {
		speed: function (val) {
			if (!isNaN(val)) return val;
			if (val == 'fast') return .2;
			if (val == 'slow') return .6;
			return .4;
		}
		, scale: function (val) { // expects 'y', 'x', 'xy' (or 'yx'), or 'none'; defaults to 'y'
			var scale = {x: false, y: true }
			if (typeof(val) == 'string') {
				val = val.toLowerCase();
				if (val.indexOf('x') != -1 && val.indexOf('y') != -1) { scale.x = true; scale.y=true; }
				else if (val.indexOf('x') != -1) { scale.x = true; scale.y=false; }
				else if (val.indexOf('y') != -1) { scale.x = false; scale.y=true; }
				else if (val == 'none') { scale.x = false; scale.y=false; }
			}
			return scale;
		}
		, dir: function (val) {
			var directions = {left:'',right:'',top:'',bottom:''};
			if (val in directions) return val;
			else return top;
		}
		, remove: function (val) {
			if (val === true || val === false) return val;
			else return false;
		}
		, callback: function (val) {
			if (typeof(val) != 'function') return new Function;
			else return val;
		}
	};

	/**
	 * Fades and shrinks an element and optionally removes it
	 * @method fadeOut
	 * @param {HTMLElement} An HTMLElement to apply the style to.
	 * @param {String} The animation speed. (optional)
	 * @param {Boolean} Whether to remove the element when it has finished disappearing. (optional)
	 * @param {Function} Callback. (optional)
	 */
	var fadeOut = function(domnode, args) {
		var node = Y.all(domnode);  // 'this' could also be used, but there's a bug: http://yuilibrary.com/projects/yui3/ticket/2529327
		if (typeof(args) != 'object') args = {};
		var speed = _validate.speed(args.speed);
		var scale = _validate.scale(args.scale);
		var remove = _validate.remove(args.remove);
		var callback = _validate.callback(args.callback);
		
		// anim does not normally allow us to animate a NodeList
		// TODO: callback will run after each separate animation is done, we'll probably want to change that at some point
		node.each(function (n) {
			var oldstyles = {
				  height: n.getStyle('height')
				, width: n.getStyle('width')
				, overflow: n.getStyle('overflow')
				, visibility: n.getStyle('visibility')
				, display: n.getStyle('display')
			};
			var to = {opacity:0};
			if (scale.y) to.height = 0;
			if (scale.x) to.width = 0;
			var a = new Y.Anim({
				node: n,
				to: to,
				duration: speed
			});
			a.on('start', function(ev) {
				n.setStyle('overflow','hidden');
			});
			a.on('end', function(ev) {
				if (remove) n.remove();
				else {
					n.setStyles({
						  display: 'none'
						, height: oldstyles.height
						, width: oldstyles.width
						, overflow: oldstyles.overflow
					});
				}
				callback(n);
			});
			a.run();
		});
	}
	Y.Node.addMethod('fadeOut',fadeOut);
	Y.NodeList.importMethod(Y.Node.prototype, 'fadeOut');
	
	var fadeIn = function(domnode, args) {
		var node = Y.all(domnode);  // 'this' could also be used, but there's a bug: http://yuilibrary.com/projects/yui3/ticket/2529327
		if (typeof(args) != 'object') args = {};
		var speed = _validate.speed(args.speed);
		var scale = _validate.scale(args.scale);
		var callback = _validate.callback(args.callback);
		
		// anim does not normally allow us to animate a NodeList
		// TODO: callback will run after each separate animation is done, we'll probably want to change that at some point
		node.each(function (n) {
			var oldstyles = {
				  height: 'auto'
				, width: n.getStyle('width')
				, overflow: n.getStyle('overflow')
				, visibility: n.getStyle('visibility')
				, display: n.getStyle('display')
			};
			var to = {opacity:1};
			var from = {opacity:0};
			
			// set up height scaling
			var fullheight = n.getStyle('height');
			if (fullheight == 'auto' || parseInt(fullheight) <= 0) {	// IE reports 0 or negative border width if the height is auto and display is none
				oldstyles.height = 'auto';	// reset from 0 or negative for IE
				n.setStyles({ height:'1px', overflow:'hidden', visibility:'hidden', display:'block' });
				var fullheight = n.get('scrollHeight');
				n.setStyles(oldstyles);
			}
			if (scale.y) {
				to.height = fullheight;
				from.height = 0;
			}
			
			// set up width scaling
			var fullwidth = n.getStyle('width');
			if (fullwidth == 'auto' || parseInt(fullwidth) <= 0) {	// IE reports 0 or negative border width if the width is auto and display is none
				oldstyles.width = 'auto';	// reset from 0 or negative for IE
				n.setStyles({ width:'1px', overflow:'hidden', visibility:'hidden', display:'block' });
				var fullwidth = n.get('scrollWidth');
				n.setStyles(oldstyles);
			}
			if (scale.x) {
				to.width = fullwidth;
				from.width = 0;
			}
			
			// animate
			var a = new Y.Anim({
				node: n,
				from: from,
				to: to,
				duration: speed
			});
			a.on('start', function(ev) {
				var startstyles = {display:'block', overflow:'hidden', opacity:0}
				if (scale.y) startstyles.height = 0;
				if (scale.x) startstyles.width = 0;
				n.setStyles(startstyles);
			});
			a.on('end', function(ev) {
				n.setStyles({overflow:oldstyles.overflow, height:oldstyles.height, width:oldstyles.width});
				// IE 7 doesn't like display table-row and table-cell
				// but other browsers need them to display the table elements correctly
				// IE 7 will work without, so we just catch the error
				if (n.get('nodeName').toLowerCase() == 'tr') try { n.setStyle('display','table-row'); } catch (e) {}
				if (n.get('nodeName').toLowerCase() == 'td') try { n.setStyle('display','table-cell'); } catch (e) {}
				callback(n);
			});
			a.run();
		});
	}
	Y.Node.addMethod('fadeIn',fadeIn);
	Y.NodeList.importMethod(Y.Node.prototype, 'fadeIn');
	
	/**
	 * Slides an element away and optionally removes it
	 * @method fadeOut
	 * @param {HTMLElement} An HTMLElement to apply the style to.
	 * @param {String} The animation speed. (optional)
	 * @param {Boolean} Whether to remove the element when it has finished disappearing. (optional)
	 * @param {Function} Callback. (optional)
	 */
	var slideOut = function(domnode, args) {
		var node = Y.all(domnode);  // 'this' could also be used, but there's a bug: http://yuilibrary.com/projects/yui3/ticket/2529327
		if (typeof(args) != 'object') args = {};
		var speed = _validate.speed(args.speed);
		var dir = _validate.dir(args.dir);
		var remove = _validate.remove(args.remove);
		var callback = _validate.callback(args.callback);
		
		// anim does not normally allow us to animate a NodeList
		// TODO: callback will run after each separate animation is done, we'll probably want to change that at some point
		node.each(function (n) {
			var to = {}
			if (dir == 'left' || dir == 'top') {
				var w = n.get('offsetWidth') + parseInt(n.getStyle('marginLeft')) + parseInt(n.getStyle('marginRight'));
				if (dir == 'left') to = {left: '-'+w};
				else to = {left: w};
			} else {
				var h = n.get('offsetHeight') + parseInt(n.getStyle('marginTop')) + parseInt(n.getStyle('marginBottom'));
				if (dir == 'top') to = {top: '-'+h};
				else to = {top: h};
			}
			
			var a = new Y.Anim({
				node: n,
				to: to,
				duration: speed
			});
			a.on('start', function(ev) {
			});
			a.on('end', function(ev) {
				if (remove) n.remove();
				callback(n);
			});
			a.run();
		});
	}
	Y.Node.addMethod('slideOut',slideOut);
	Y.NodeList.importMethod(Y.Node.prototype, 'slideOut');
	
	var slideIn = function(domnode, args) {
		var node = Y.all(domnode);  // 'this' could also be used, but there's a bug: http://yuilibrary.com/projects/yui3/ticket/2529327
		if (typeof(args) != 'object') args = {};
		var speed = _validate.speed(args.speed);
		var dir = _validate.dir(args.dir);
		var callback = _validate.callback(args.callback);
		
		// anim does not normally allow us to animate a NodeList
		// TODO: callback will run after each separate animation is done, we'll probably want to change that at some point
		node.each(function (n) {
			var to = {};
			var from = {};
			if (dir == 'left' || dir == 'right') {
				var w = n.get('offsetWidth') + parseInt(n.getStyle('marginLeft')) + parseInt(n.getStyle('marginRight'));
				to = {left: 0};
				if (dir == 'left') from = {left: '-'+w};
				else from = {left: w};
				n.setStyles({
					  width: n.getStyle('width')
					, left: from.left
					, display: 'block'
				});
			} else {
				var h = n.get('offsetHeight') + parseInt(n.getStyle('marginTop')) + parseInt(n.getStyle('marginBottom'));
				to = {top: 0};
				if (dir == 'top') from = {top: '-'+h};
				else from = {top: h};
				n.setStyles({
					top: from.top
				});
			}
			
			var a = new Y.Anim({
				node: n,
				from: from,
				to: to,
				duration: speed
			});
			a.on('start', function(ev) {
			});
			a.on('end', function(ev) {
				callback(n);
			});
			a.run();
		});
	}
	Y.Node.addMethod('slideIn',slideIn);
	Y.NodeList.importMethod(Y.Node.prototype, 'slideIn');
	

}, '3.2.0' ,{requires:['node-base','anim-base']});