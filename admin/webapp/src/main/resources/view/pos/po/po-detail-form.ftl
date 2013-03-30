<html>
	<head>
		<title><@s.text name="page.purchaseorderdetail.title" /></title>
		<meta name="header" content="<@s.text name="page.purchaseorderdetail.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<input type="hidden" value="${purchaseOrder.id!}" name="purchaseOrder.id"> 
					<input type="hidden" value="${purchaseOrder.requisition.id!}" name="requisitionDetail.requisition.id"> 
					<@s.textfield key="label.admin.purchaseorder.title" readonly="true" value="${purchaseOrder.title!}" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorder.description" readonly="true" value="${purchaseOrder.description!}" cssClass="span4" />
					<!-- <@s.textfield key="label.admin.purchaseorder.duedate" readonly="true" value="${purchaseOrder.duedate!}" cssClass="span4" /> -->
					<@s.textfield key="label.admin.purchaseorder.businesspartner" readonly="true" value="${purchaseOrder.businessPartner.name!}" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorder.requisition" readonly="true" value="${purchaseOrder.requisition.title!}" cssClass="span4" />
					<#if purchaseOrder.status==0>
					<div class="control-group ">
						<label class="control-label" for="add_transactionDetail_item_id"><@s.text name="page.item.title" /><span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="requisitionDetail.item.id" id="item-id" />
							<input type="text" id="item-name" readonly="true" class="span4">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.requisitiondetail.quantity" required="true"  name="requisitionDetail.quantity" cssClass="span4" />
					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
					</div>
					</#if>
				</@s.form>
				<#if purchaseOrder.status==0>
				<@s.form theme="bootstrap" action="/pos/po/purchaseorder" cssClass="form-horizontal">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.requisitiondetail.item" /></th>
							<th><@s.text name="label.admin.requisitiondetail.quantity" /></th>
							<th><@s.text name="label.admin.purchaseorderdetail.price" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						 <#list requisitionDetails.entityList as s>
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}<input type="hidden" name="item" value="${s.item.id!}"></td>
							<td><input type="text" name="quantity" value="${s.quantity!}"></td>
							<td><input type="text" name="price" value="<#if s.price??>${s.price?string('#')!}<#else>${s.item.price?string('#')!}</#if>"></td>
						</tr>
						<#assign no = no + 1 />
						</#list> 
						
					</tbody>
				</table>
				<input type="hidden" name="purchaseOrder.id" value="${purchaseOrder.id!}">	
				<div class="form-actions">
					<input type="submit" id="cash_button_save" name="button.save" value="Save" class="btn btn-primary">
				</div>
    			</@s.form>
				<#else>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.requisitiondetail.item" /></th>
							<th style="text-align:center;"><@s.text name="label.admin.requisitiondetail.quantity" /></th>
							<th style="text-align:right;"><@s.text name="label.admin.purchaseorderdetail.price" /></th>
							<th style="text-align: right;"><@s.text name="label.admin.tdetail.subtotal" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#assign totalPrice = 0 />
						<#assign totalQnty = 0 />
						<#list purchaseOrderDetails.entityList as s>
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