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
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.pos" />">
			<@s.text name="menu.pos" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/pos/businesspartner">
				<@s.text name="menu.pos.businesspartner" />
				</a>
			</li>
			<li>
				<a href="/pos/uom">
					<@s.text name="menu.pos.uom" />
				</a>
			</li>
			<li>
				<a href="/pos/conversion">
					<@s.text name="menu.pos.itemtype" />
				</a>
			</li>
			<li>
				<a href="/pos/itemcategory">
					<@s.text name="menu.pos.itemcategory" />
				</a>
			</li>
			<li>
				<a href="/pos/item">
					<@s.text name="menu.pos.item" />
				</a>
			</li>
			<li>
				<a href="/pos/goodreceiving">
					<@s.text name="menu.pos.goodreceiving" />
				</a>	
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.package" />">
			<@s.text name="menu.package" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/master/packages">
					<@s.text name="menu.master.packagemanager" />
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			Ticketing
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
		<li>
			<a href="/ticket/premade">
				Premade Answer
			</a>
		</li>
		<li>
			<a href="/ticket/category">
				Ticket Category
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
			${user.username}
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/admin/configuration">
					<i class="icon-wrench"></i>
					<@s.text name="menu.admin.configuration" />
				</a>
			</li>
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