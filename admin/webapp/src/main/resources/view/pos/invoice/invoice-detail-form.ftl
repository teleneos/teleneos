<html>
	<head>
		<title><@s.text name="page.invoicedetail.title" /></tile>
		<meta name="header" content="<@s.text name="page.invoicedetail.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<input type="hidden" value="${invoice.id!}" name="invoice.id"> 
					<input type="hidden" value="${invoice.purchaseOrder.id!}" name="purchaseOrderDetail.purchaseOrder.id"> 
					<@s.textfield key="label.admin.invoice.title" readonly="true" value="${invoice.title!}" cssClass="span4" />
					<@s.textfield key="label.admin.invoice.description" readonly="true" value="${invoice.description!}" cssClass="span4" />
					<@s.textfield key="label.admin.invoice.date" readonly="true" value="${invoice.duedate!}" cssClass="span4" />
					<@s.textfield key="label.admin.invoice.purchaseorder" readonly="true" value="${invoice.purchaseOrder.title!}" cssClass="span4" />
					<#if invoice.status==0>
					<div class="control-group ">
						<label class="control-label" for="add_transactionDetail_item_id"><@s.text name="page.item.title" /><span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="purchaseOrderDetail.item.id" id="item-id" />
							<input type="text" id="item-name" readonly="true" class="span4">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.purchaseorderdetail.quantity" required="true"  name="purchaseOrderDetail.quantity" cssClass="span4" />
					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
					</div>
					</#if>
				</@s.form>
				<#if invoice.status==0>
				<@s.form theme="bootstrap" action="/pos/invoice/invoice" cssClass="form-horizontal">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.purchaseroderdetail.item" /></th>
							<th><@s.text name="label.admin.purchaseroderdetail.quantity" /></th>
							<th><@s.text name="label.admin.purchaseroderdetail.price" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						 <#list purchaseOrderDetails.entityList as s>
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}<input type="hidden" name="item" value="${s.item.id!}"></td>
							<td><input type="text" name="quantity" value="${s.quantity!}"></td>
							<td><input type="text" name="price" value="${s.price!}"></td>
						</tr>
						<#assign no = no + 1 />
						</#list> 
						
					</tbody>
				</table>
				<input type="hidden" name="invoice.id" value="${invoice.id!}">	
				<div class="form-actions">
					<input type="submit" id="cash_button_save" name="button.save" value="Save" class="btn btn-primary">
				</div>
    			</@s.form>
				<#else>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.invoicedetail.item" /></th>
							<th style="text-align:center;"><@s.text name="label.admin.invoicedetail.quantity" /></th>
							<th style="text-align:right;"><@s.text name="label.admin.invoicedetail.price" /></th>
							<th style="text-align: right;"><@s.text name="label.admin.tdetail.subtotal" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#assign totalPrice = 0 />
						<#assign totalQnty = 0 />
						<#list invoiceDetails.entityList as s>
						<#assign subTotal = s.quantity * s.price /> 
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}<input type="hidden" name="item" value="${s.item.id!}"></td>
							<td style="text-align:center;">${s.quantity!}</td>
							<td style="text-align:right;">${s.price!}</td>
							<td style="text-align:right;">${subTotal!}</td>
						</tr>
						<#assign totalPrice = totalPrice + subTotal />
						<#assign totalQnty = totalQnty + s.quantity />
						<#assign no = no + 1 />
						</#list> 
						<tr>
							<td></td>
							<td><strong><@s.text name="label.admin.tdetail.total" /></strong></td>
							<td style="text-align: center;"><strong>${totalQnty}</strong> </td>
							<td style="text-align: right;" colspan="2"><strong>${totalPrice}</strong> </td>
						</tr>
					</tbody>
				</table>
				</#if>
			</div>
		</div>		
	</body>
</html>