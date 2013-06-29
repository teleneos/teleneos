<div class="span2">
	<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
		<li>
		<#if currentUser??>
			<#if currentUser.role != 'ADMINISTRATOR'>
				<#if currentUser.role != 'MASTER'>
					<a href="/ticket/user/status">
						<@s.text name="Status" />
					</a>
				</#if>
			<#else>
					<a href="/ticket">
						<@s.text name="Status" />
					</a>
			</#if>
		</#if>
		</li>
		<#if currentUser??>
		<#if currentUser.role == 'MASTER'>
		<li>
			<a href="/ticket/premade">
				<@s.text name="Premade Answer" />
			</a>
		</li>
		<li>
			<a href="/ticket/category">
				<@s.text name="Ticket Category" />
			</a>
		</li>
		</#if>
		</#if>
	</ul>
</div>