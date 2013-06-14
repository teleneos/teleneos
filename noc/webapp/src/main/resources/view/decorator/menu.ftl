<ul class="nav">
	<li>
		<a href='<@s.url value="/" />'>
			<i class="icon-home icon-white"></i>
			<@s.text name="page.home" />
		</a>
	</li>
</ul>

<#assign user = request.session.getAttribute("YAMA_SECURITY_USER")!'null' />
<#if user != 'null'>
<ul class="nav">
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.telecentre" />">
			<@s.text name="menu.telecentre" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/tele">
					<@s.text name="menu.telecentre.manage" />
				</a>
			</li>
			<li>
				<a href="/tele/user">
					<@s.text name="menu.telecentre.user" />
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.monitoring" />">
			<@s.text name="menu.monitoring" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/monitoring/availability">
					<@s.text name="menu.monitoring.availability" />
				</a>
			</li>
			<li>
				<a href="/monitoring/network">
					<@s.text name="menu.monitoring.network" />
				</a>
			</li>
			<li>
				<a href="/monitoring/performance">
					<@s.text name="menu.monitoring.performance" />
				</a>
			</li>
			<li>
				<a href="/monitoring/utilization">
					<@s.text name="menu.monitoring.utilization" />
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.log" />">
			<@s.text name="menu.log" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/log/online">
					<@s.text name="menu.log.online" />
				</a>
			</li>
			<li>
				<a href="/log/access">
					<@s.text name="menu.log.access" />
				</a>
			</li>
		</ul>
	</li>
</ul>
<ul class="nav pull-right">
	<li class="divider-vertical"></li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<i class="icon-user icon-white"></i>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/security/password">
					<i class="icon-cog"></i>
					<@s.text name="menu.profile.changepassword" />
				</a>
			</li>
			<li>
				<a href="/security/profile/edit">
					<i class="icon-edit"></i>
					<@s.text name="menu.profile.edit" />
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="/security/logout">
					<i class="icon-off"></i>
					<@s.text name="menu.logout" />
				</a>
			</li>
		</ul>
	</li>
</ul>
</#if>