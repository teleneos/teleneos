<ul class="nav">
	<li>
		<a href='<@s.url value="/" />'>
			<i class="icon-home"></i>
			<@s.text name="page.home" />
		</a>
	</li>
</ul>

<#assign user = request.session.getAttribute("YAMA_SECURITY_USER")!'null' />
<#if user != 'null'>
<ul class="nav">
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<@s.text name="menu.admin" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/admin/user">
					<@s.text name="menu.admin.user" />
				</a>
			</li>
			<li>
				<a href="/admin/user/online">
					<@s.text name="menu.admin.onlineuser" />
				</a>
			</li>
			<li>
				<a href="/admin/service">
					<@s.text name="menu.admin.service" />
				</a>
			</li>
		</ul>
	</li>
</ul>

<ul class="nav pull-right">
	<li class="divider-vertical"></li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<i class="icon-user"></i>
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