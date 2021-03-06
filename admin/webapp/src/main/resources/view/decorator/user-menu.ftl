<ul class="nav">
	<li>
		<a href='<@s.url value="/" />'>
			<i class="icon-home icon-white"></i>
			<@s.text name="page.home" />
		</a>
	</li>
	<#if currentUser??>
	<#if currentUser.role != 'ADMINISTRATOR'>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title=""><@s.text name="menu.internet" /> <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li>
				<a href="/user/history">
					<@s.text name="menu.user.status" />
				</a>
			</li>
			<li>
				<a href="/user/subscription">
					<@s.text name="menu.user.subscription" />
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title=""> Ticketing <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="/ticket/user/status"> Ticket </a></li>
		</ul>
	</li>
	</#if>
	</#if>
</ul>

<#assign user = request.session.getAttribute("YAMA_SECURITY_USER")!'null' />

<#if user != 'null'>
<ul class="nav pull-right">
	<li class="divider-vertical"></li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<i class="icon-user icon-white"></i>
			${currentUser.username!}
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