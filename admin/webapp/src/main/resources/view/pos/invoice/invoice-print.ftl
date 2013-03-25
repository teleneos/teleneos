<table>
	<thead>
		<tr>
			<th style="text-align: center;" colspan="3">TELECENTRE</th>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3">JL SWADAYA 2 NO 39</th>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3">+62 8573 149 5681</th>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3"><hr></th>
		</tr>
		<tr>
			<td>No Invoice</td>
			<td>: 020C-1934-409987</td>
			<td style="text-align: right;">${transactionHeader.logInformation.createDate?string('dd-MM-yyyy')}</td>
		</tr>
		<tr>
			<td>User</td>
			<td colspan="2">: ${transactionHeader.user.user.username!}</td>
		</tr>
		<tr>
			<td>Admin</td>
			<td colspan="2">: ${currentUser.username!}</td>
		</tr>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3"><hr></th>
		</tr>
	</tbody>
	<tbody>
		<tr>
			<td colspan="3"><#if transactionHeader.id??>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th>&nbsp;&nbsp;&nbsp;&nbsp;<@s.text
								name="label.admin.tdetail.item" /></th>
							<th style="text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;<@s.text
								name="label.admin.tdetail.quantity" /></th>
							<th style="text-align: right;">&nbsp;&nbsp;&nbsp;&nbsp;<@s.text
								name="label.admin.tdetail.price" /></th>
							<th style="text-align: right;">&nbsp;&nbsp;&nbsp;&nbsp;<@s.text
								name="label.admin.tdetail.subtotal" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 /> <#assign totalPrice = 0 /> <#assign totalQnty =
						0 /> <@s.url value="/pos/transaction/edit/" var="editUrl" />
						<#list transactionDetails.entityList as s> <#assign price =
						s.quantity * s.price />
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}</td>
							<td style="text-align: center;">${s.quantity!}</td>
							<td style="text-align: right;">${s.price!}</td>
							<td style="text-align: right;">${price}</td>
						</tr>
						<#assign no = no + 1 /> <#assign totalPrice = totalPrice + price
						/> <#assign totalQnty = totalQnty + s.quantity /> </#list>
						<#assign no = no - 1 />

						<tr>
							<td></td>
							<td><strong><@s.text
									name="label.admin.tdetail.total" /></strong></td>
							<td style="text-align: center;"><strong>${totalQnty}</strong>
							</td>
							<td style="text-align: right;" colspan="2"><strong>${totalPrice}</strong>
							</td>
						</tr>
						<#if transactionHeader.cash??> <#assign cashBack =
						transactionHeader.cash - totalPrice />
						<tr>
							<td></td>
							<td colspan="2"><strong><@s.text
									name="label.admin.tdetail.cash" /></strong></td>
							<td style="text-align: right;" colspan="2"><strong>${transactionHeader.cash!}</strong></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2"><strong><@s.text
									name="label.admin.tdetail.cashback" /></strong></td>
							<td style="text-align: right;" colspan="2"><strong>${cashBack!}</strong></td>
						</tr>
						</#if>
					</tbody>
				</table> </#if>
			</td>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3"><hr></th>
		</tr>
	</tbody>
</table>