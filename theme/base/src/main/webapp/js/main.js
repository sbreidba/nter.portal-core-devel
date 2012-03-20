AUI().ready('base', 'node-base', 'dom-base', 'selector-css3',

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function(A) {
		// progressive enhancement for nth-child
		var items = A.all('.paged-thumbnail-listing li');
		if (items.size() > 0) {
			var first = items.item(0);
			var marginRight = parseInt(first.getStyle('marginRight'));
			var marginLeft = parseInt(first.getStyle('marginLeft'));
			var paddingRight = parseInt(first.getStyle('paddingRight'));
			var paddingLeft = parseInt(first.getStyle('paddingLeft'));
			var extra = marginLeft + marginRight + paddingLeft + paddingRight;
			var width = parseInt(first.getStyle('width'));
			var total = parseInt(first.get('parentNode').getStyle('width'));
			var numAcross = Math.floor((total + extra) / (width + extra));
			
			A.all('.paged-thumbnail-listing li:nth-child('+numAcross+'n)').setStyles({paddingRight:0,marginRight:0});
			A.all('.paged-thumbnail-listing li:nth-child('+numAcross+'n+1)').setStyles({clear:'left',paddingLeft:0,marginLeft:0});
			if (Liferay.Browser.isIe() && Liferay.Browser.getMajorVersion() <= 7) A.all('.paged-thumbnail-listing li:nth-child('+numAcross+'n)').insert(A.Node.create('<br />'), 'after');
		}
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',
	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);