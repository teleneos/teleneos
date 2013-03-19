<html>
	<head>
		<title><@s.text name="page.tdetail.title" /></tile>
		<meta name="header" content="<@s.text name="page.tdetail.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="transactionHeader.id" />
					<#if transactionHeader.id??>
					<@s.textfield key="label.admin.theader.user" readonly="true" value="${transactionHeader.user.name.first!} ${transactionHeader.user.name.last!}" cssClass="span4" />
					<#else>
					<div class="control-group ">
						<label class="control-label" for="add_id">User <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="id" id="user-id" />
							<input type="text" id="user-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.user.title" />" object-name="users|user.user.namefirst" field-target="user-id|user-name" href="<@s.url value="/admin/user/list" />">Choose</button>
						</div>
					</div>
					</#if>
					<#if !transactionHeader.cash??>
					<div class="control-group ">
						<label class="control-label" for="add_transactionDetail_item_id">Item Name <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="transactionDetail.item.id" id="item-id" />
							<input type="text" id="item-name" readonly="true" class="span4">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.tdetail.quantity" required="true"  name="transactionDetail.quantity" cssClass="span4" />
					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						<@s.reset key="button.reset" cssClass="btn" />
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
						<#assign price = s.quantity * s.item.price /> 
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}</td>
							<td style="text-align: center;">${s.quantity!}</td>
							<td style="text-align: right;">${s.item.price!}</td>
							<td style="text-align: right;">${price}</td>
						</tr>
						<#assign no = no + 1 />
						<#assign totalPrice = totalPrice + price />
						<#assign totalQnty = totalQnty + s.quantity />
						</#list>
						<#assign no = no - 1 />
						
						<tr>
							<td></td>
							<td><strong><@s.text name="label.admin.tdetail.totalquantity" /></strong></td>
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
					<@s.submit key="button.save" cssClass="btn btn-primary" />
				</div>
				</@s.form>
				<div id="pagination"></div>
				</#if>
				</#if>
			</div>
		</div>		
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
 		$(function() {
			$('#pagination').pagination({
				items: ${transactionDetails.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(transactionDetails.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		}); 
		</script>
	</body>
</html>