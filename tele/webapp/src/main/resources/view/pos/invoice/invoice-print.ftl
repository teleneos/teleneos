<#function timeFormat time>
	<#if (time = (60 * 24 * 7))>
		<#return (time / (60 * 24 * 7))?string('#') + ' Weeks' />
	<#elseif (time >= (60 * 24))>
		<#return (time / (60 * 24))?string('#') + ' Days' />
	<#elseif (time >= 60)>
		<#return (time / (60))?string('#') + ' Hours' />
	<#elseif (time >= 1)>
		<#return (time)?string('#') + ' Min' />
	<#elseif (time == 0)>
		<#return '-' />
	</#if>
</#function>
<table>
	<thead>
		<tr>
			<th style="text-align: center;" colspan="3">TELECENTRE</th>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3"><hr></th>
		</tr>
		<tr>
			<td>No Invoice</td>
			<td>: ${noInvoice(transactionHeader.counter)}</td>
			<td style="text-align: right;">${transactionHeader.logInformation.createDate?string('dd-MM-yyyy')}</td>
		</tr>
		<tr>
			<td>User</td>
			<td>: ${transactionHeader.username!}</td>
			<td style="text-align: right;">${transactionHeader.logInformation.createDate?string('HH:mm')}</td>
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
							<th><@s.text
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
						<#assign no = 1 + ((page - 1) * max) /> 
						<#assign totalPrice = 0 /> 
						<#assign totalQnty = 0 /> 
						<@s.url value="/pos/transaction/edit/" var="editUrl" />
						<#list transactionDetails.entityList as s> 
							<#assign price = s.quantity * s.price />
							<tr>
								<td>${no}</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<#if s.item??>${s.item.name!}<#elseif s.internetPackage??>${s.internetPackage.code!}<#else>${s.userPackage.internetPackage.name!}</#if></td>
								<td style="text-align: center;"><#if s.item??>${s.quantity!}<#elseif s.userPackage??>${timeFormat(minuteUsage)}<#else>-</#if></td>
								<td style="text-align: right;">
									<#if s.internetPackage?? > 
										<#if s.internetPackage.paymentMethod == 'POSTPAID' > 
											@ ${timeFormat(s.internetPackage.time!0)} 
										</#if>
									<#elseif s.item??>
									${s.price!} 
									<#else>
									@${s.userPackage.internetPackage.price}
									</#if>
								</td>
								<td style="text-align: right;">
								<#if s.item??>
									${price}
								<#elseif s.internetPackage??>
									<#if s.internetPackage.paymentMethod != 'POSTPAID' >
										${s.internetPackage.price!0}
									<#else>
										-
									</#if>
								<#else>
									${priceTotal}
								</#if>
								</td>
							</tr>
							<#assign no = no + 1 /> 
							<#if s.internetPackage??>
								<#if s.internetPackage.paymentMethod != 'POSTPAID' >
									<#assign totalPrice = totalPrice + s.internetPackage.price!0 />
								</#if>
							<#elseif s.item??>
								<#if s.conversion?? >
									<#assign price = (s.quantity * s.conversion.multiplyRate!0) * s.price />
									<#else>
									<#assign price = s.quantity * s.price />
								</#if> 
								<#assign totalPrice = totalPrice + price /> 
								<#assign totalQnty = totalQnty + s.quantity />
							<#else>
								<#assign totalPrice = totalPrice + priceTotal />
							</#if> 
						</#list>
						<#assign no = no - 1 />
						<tr>
							<td></td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<strong><@s.text
									name="label.admin.tdetail.total" /></strong></td>
						<#--<td style="text-align: center;"><strong>${totalQnty}</strong></td>-->
							<td style="text-align: right;" colspan="2"><strong>${totalPrice}</strong>
							</td>
						</tr>
						<#if transactionHeader.cash??> 
						<#assign cashBack = transactionHeader.cash - totalPrice />
						<tr>
							<td></td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<strong><@s.text
									name="label.admin.tdetail.cash" /></strong></td>
							<td style="text-align: right;" colspan="2"><strong>${transactionHeader.cash!}</strong></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<strong><@s.text
									name="label.admin.tdetail.cashback" /></strong></td>
							<td style="text-align: right;" colspan="2"><strong>${cashBack!}</strong></td>
						</tr>
						</#if>
					</tbody>
				</table> 
				</#if>
			</td>
		</tr>
		<tr>
			<th style="text-align: center;" colspan="3"><hr></th>
		</tr>
	</tbody>
</table>