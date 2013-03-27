<html>
	<head>
		<title><@s.text name="page.tdetail.title" /></title>
		<meta name="header" content="<@s.text name="page.tdetail.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
		<script type="text/javascript">
			printDivCSS = new String ('<link href="myprintstyle.css" rel="stylesheet" type="text/css">')
			function printDiv(divId) {
				var mywindow = window.open('', 'my div', '');
				mywindow.document.body.innerHTML=printDivCSS + document.getElementById(divId).innerHTML
				mywindow.window.focus()
			    mywindow.window.print()
			    
			}
			
			$(function() {
			$('#package').hide();
			$('input[name="change"]').change(function() {
				if ($(this).val() == 'true') {
					$('#package').hide();
					$('#item').fadeIn();
					$('input[name="transactionDetail.quantity"]').parents('.control-group').fadeIn();
				} else {
					$('#item').hide();
					$('input[name="transactionDetail.quantity"]').parents('.control-group').hide();
					$('#package').fadeIn();
				}
			});
			
			$('input[name="change"]:checked').change();
			});
		</script>
	</head>
	<body>
	<#function noInvoice counter>
			<#if counter &gt;= 100000>
				<#return counter?string('#')/>
			<#elseif counter &gt;= 10000>
				<#return '0' + counter?string('#')/>
			<#elseif counter &gt;= 1000>
				<#return '00' + counter?string('#')/>
			<#elseif counter &gt;= 100>
				<#return '000' + counter?string('#') />
			<#elseif counter &gt;= 10>
				<#return '0000' + counter?string('#') />
			<#else>
				<#return '00000' + counter?string('#') />
			</#if>
		</#function>
	
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="transactionHeader.id" />
					<#if transactionHeader.id??>
					<@s.textfield key="label.admin.theader.noinvoice" readonly="true" value="${noInvoice(transactionHeader.counter)}" cssClass="span4" />
					<@s.textfield key="label.admin.theader.date" readonly="true" value="${transactionHeader.logInformation.createDate?string('dd-MM-yyyy')!} ${transactionHeader.logInformation.createDate?string('hh:mm')!}" cssClass="span4" />
					<@s.textfield key="label.admin.theader.user" readonly="true" value="${transactionHeader.user.user.username!}" cssClass="span4" />
					<@s.textfield key="label.admin.theader.admin" readonly="true" value="${currentUser.username!}" cssClass="span4" />
					<#else>
					<div class="control-group ">
						<label class="control-label" for="add_id"><@s.text name="menu.admin.user" /> <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="id" id="user-id" />
							<input type="text" id="user-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.user.title" />" object-name="users|user" field-target="user-id|user-name" href="<@s.url value="/admin/user/list" />">Choose</button>
						</div>
					</div>
					</#if>
					<#if !transactionHeader.cash??>
					<@s.radio key="" name="change" list={'true' : 'Item', 'false' : 'Internet Package'} listKey="key" listValue="value" />
					<div class="control-group " id="item">
						<label class="control-label" for="add_transactionDetail_item_id"><@s.text name="page.item.title" /> <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="transactionDetail.item.id" id="item-id" />
							<input type="text" id="item-name" readonly="true" class="span4">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<div class="control-group " id="package">
						<label class="control-label" for="add_id">Package<span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="transactionDetail.internetPackage.id" id="package-id" />
							<input type="text" id="package-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="Package" object-name="internetPackages|code" field-target="package-id|package-name" href="/master/packages">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.tdetail.quantity" required="true"  name="transactionDetail.quantity" cssClass="span4" />
					<div class="form-actions">
						<@s.submit key="button.add" cssClass="btn btn-primary" />
					</div>
					</#if>
				</@s.form>
				<#if transactionHeader.id??>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.tdetail.item" /></th>
							<th style="text-align:center;"><@s.text name="label.admin.tdetail.quantity" /></th>
							<th style="text-align: right;"><@s.text name="label.admin.tdetail.price" /></th>
							<th style="text-align: right;"><@s.text name="label.admin.tdetail.subtotal" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#assign totalPrice = 0 />
						<#assign totalQnty = 0 />
						<@s.url value="/pos/transaction/edit/" var="editUrl" />
						<#list transactionDetails.entityList as s>
						<#assign price = s.quantity * s.price /> 
						<tr>
							<td>${no}</td>
							<td><#if s.item??>${s.item.name!}</#if><#if s.internetPackage??>${s.internetPackage.code!}</#if></td>
							<td style="text-align: center;"><#if s.item??>${s.quantity!}<#else>-</#if></td>
							<td style="text-align: right;">${s.price!}</td>
							<td style="text-align: right;"><#if s.item??>${price}<#else>${s.internetPackage.price!0}</#if></td>
						</tr>
						<#assign no = no + 1 />
						<#assign totalPrice = totalPrice + price!0 />
						<#if s.internetPackage??>
							<#assign totalPrice = totalPrice + s.internetPackage.price!0 />
						</#if>
						<#assign totalQnty = totalQnty + s.quantity />
						</#list>
						<#assign no = no - 1 />
						
						<tr>
							<td></td>
							<td><strong><@s.text name="label.admin.tdetail.total" /></strong></td>
							<td style="text-align: center;"><strong>${totalQnty}</strong> </td>
							<td style="text-align: right;" colspan="2"><strong>${totalPrice}</strong> </td>
						</tr>
						<#if transactionHeader.cash??>
						<#assign cashBack = transactionHeader.cash -  totalPrice />
						<tr>
							<td></td>
							<td colspan="2"><strong><@s.text name="label.admin.tdetail.cash" /></strong></td>
							<td style="text-align: right;" colspan="2"><strong>${transactionHeader.cash!}</strong></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2"><strong><@s.text name="label.admin.tdetail.cashback" /></strong></td>
							<td style="text-align: right;" colspan="2"><strong>${cashBack!}</strong></td>
						</tr>
						</#if>
					</tbody>
				</table>
				<#if !transactionHeader.cash??>
				<@s.form theme="bootstrap" action="/pos/transaction/cash" cssClass="form-horizontal">
				<@s.hidden name="transactionHeader.id" />
				<@s.textfield key="label.admin.tdetail.cash" required="true"  name="transactionHeader.cash" cssClass="span4" />
				<div class="form-actions">
					<@s.submit key="button.save" cssClass="btn btn-primary" onclick="open_win()" />
				</div>
				</@s.form>
				<#else>
				<form theme="bootstrap" class="form-horizontal">
				<div class="form-actions">
					<input type="button" value="Print" onclick="javascript:printDiv('print')" class="btn btn-primary"/>
				
				</div>
				</form>
				</#if>
				</#if>
			</div>
		</div>
		<div id="print" style="visibility: hidden;">
		<#include "/view/pos/invoice/invoice-print.ftl" />
		</div>
	</body>
</html>