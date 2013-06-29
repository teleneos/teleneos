<div class="span2">
	<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
		<#if currentUser.role != 'ADMINISTRATOR'>
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
		<#else>
		<li>
			<a href="/pos/inventoryonhand">
				<@s.text name="menu.pos.inventoryonhand" />
			</a>	
		</li>
		<li>
			<a href="/pos/transaction">
				<@s.text name="menu.pos.sales" />
			</a>	
		</li>
		</#if>
	</ul>
</div>