<#assign path = request.servletPath />
<#assign path = path?substring(0, path?last_index_of('/')) />
<div class="span2">
	<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
		<li>
			<a href="${path}/daily">
				<@s.text name="menu.report.daily" />
			</a>
		</li>
		<li>
			<a href="${path}/weekly">
				<@s.text name="menu.report.weekly" />
			</a>
		</li>
		<li>
			<a href="${path}/monthly">
				<@s.text name="menu.report.monthly" />
			</a>
		</li>
		<li>
			<a href="/report/postpaid">
				<@s.text name="menu.report.postpaid" />
			</a>
		</li>
	</ul>
</div>