<div class="columns-1 layout-sidenav" id="main-content">
	<div class="portlet-layout">
		<div class="portlet-column portlet-column-only" id="column-1" role="main">
			<nav class="sidenav">
				#set ($VOID = $velocityPortletPreferences.setValue('display-style', '3'))
				#set ($VOID = $velocityPortletPreferences.setValue('portlet-setup-show-borders', 'false'))
				#set ($instanceId = 'zzzz')
				#set ($myPortletId = "71_INSTANCE_${instanceId}")
				$taglibLiferay.runtime($myPortletId, '', $velocityPortletPreferences.toString())
				#set ($VOID = $velocityPortletPreferences.reset())
			</nav>
			$processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
		</div>
	</div>
</div>