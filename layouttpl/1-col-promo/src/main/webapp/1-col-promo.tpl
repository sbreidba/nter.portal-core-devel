<div class="columns-1 one-col-promo" id="main-content" role="main">
	<div class="portlet-layout">
		#if($themeDisplay.isSignedIn())
		<div class="portlet-column recent-courses" id="column-1">
			$processor.processColumn("column-1", "portlet-column-content")
		</div>
		#end
		#if(!$themeDisplay.isSignedIn())
		<div class="portlet-column call-to-action" id="column-2">
			$processor.processColumn("column-2", "portlet-column-content")
		</div>
		#end
		<div class="portlet-column promo-boxes" id="column-3">
			$processor.processColumn("column-3", "portlet-column-content")
		</div>
		<div class="portlet-column top-courses" id="column-4">
			$processor.processColumn("column-4", "portlet-column-content")
		</div>
	</div>
</div>

#* this looks stupid, but it MUST be here or permissions for the portlets in this container won't get set up properly
   because the Liferay doesn't see the $processor... statement inside the isSignedIn condition when registering templates
   and only the columns it sees get cached (or something like that) *#
#if(!$themeDisplay.isSignedIn())
<div style="display:none;" id="hidden-column">$processor.processColumn("column-1", "")</div>
#end
#if($themeDisplay.isSignedIn())
<div style="display:none;" id="hidden-column">$processor.processColumn("column-2", "")</div>
#end