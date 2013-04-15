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
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<@s.text name="menu.admin" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/admin/user/list">
					<@s.text name="menu.admin.user" />
				</a>
			</li>
			<li>
				<a href="/admin/user/online">
					<@s.text name="menu.admin.onlineuser" />
				</a>
			</li>
			<#--
			<li>
				<a href="/admin/service">
					<@s.text name="menu.admin.service" />
				</a>
			</li>
			<li>
				<a href="/admin/user/group">
					<@s.text name="menu.admin.group" />
				</a>
			</li>
			-->
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
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
				<a href="/pos/itemtype">
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
				<a href="/pos/inventoryonhand">
					<@s.text name="menu.pos.inventoryonhand" />
				</a>
			</li>
			<li>
				<a href="/pos/requisition">
					<@s.text name="menu.pos.requisition" />
				</a>
			</li>
			<li>
				<a href="/pos/po">
					<@s.text name="menu.pos.purchaseorder" />
				</a>	
			</li>
			<li>
				<a href="/pos/invoice">
					<@s.text name="menu.pos.invoice" />
				</a>	
			</li>
			<li>
				<a href="/pos/goodreceiving">
					<@s.text name="menu.pos.goodreceiving" />
				</a>	
			</li>
			<li>
				<a href="/pos/transaction">
					<@s.text name="menu.pos.sales" />
				</a>	
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<@s.text name="menu.package" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<#--
			<li>
				<a href="/package/prepaid">
					<@s.text name="menu.package.prepaid" />
				</a>
			</li>
			<li>
				<a href="/package/postpaid">
					<@s.text name="menu.package.postpaid" />
				</a>
			</li>
			<li>
				<a href="/package/reward">
					<@s.text name="menu.package.reward" />
				</a>
			</li>
			<li>
				<a href="/package/subscribe">
					<@s.text name="menu.package.subscribe" />
				</a>
			</li>			
			<li>
				<a href="/master/group">
					<@s.text name="menu.master.group" />
				</a>
			</li>
			-->
			<li>
				<a href="/master/packages">
					<@s.text name="menu.master.packagemanager" />
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="/report" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			<@s.text name="menu.report" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/report/store">
					<@s.text name="menu.report.accounting" />
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" data-toggle="dropdown" title="<@s.text name="menu.user.title"><@s.param>${user.username}</@s.param></@s.text>">
			Network
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li>
				<a href="/network/availability">
					Availability
				</a>
			</li>
			<li>
				<a href="/network/monitoring">
					Monitoring
				</a>
			</li>
			<li>
				<a href="/network/remote">
					Remote Desktop
				</a>
			</li>
			<li>
				<a href="/network/performance">
					Performance
				</a>
			</li>
			<li>
				<a href="/network/utilization">
					Utilization
				</a>
			</li>
			<li>
				<a href="/network/report">
					Report
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
			<a href="/ticket/add">
				<@s.text name="Submit" />
			</a>
		</li>
		<li>
			<a href="/ticket/faq">
				<@s.text name="FAQ" />
			</a>
		</li>
		<li>
			<a href="/ticket/status">
				<@s.text name="Status" />
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
			${user.username}
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