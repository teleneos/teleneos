<html>
	<head>
		<title><@s.text name="page.purchaseorderdetail.title" /></tile>
		<meta name="header" content="<@s.text name="page.purchaseorderdetail.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<input type="hidden" value="${purchaseOrder.id!}" name="purchaseOrderDetail.purchaseOrder.id"> 
					<@s.textfield key="label.admin.purchaseorder.title" readonly="true" value="${purchaseOrder.title!}" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorder.description" readonly="true" value="${purchaseOrder.description!}" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorder.duedate" readonly="true" value="${purchaseOrder.duedate!}" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorder.businesspartner" readonly="true" value="${purchaseOrder.businessPartner.name!}" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label" for="add_transactionDetail_item_id"><@s.text name="page.item.title" /><span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="purchaseOrderDetail.item.id" id="item-id" />
							<input type="text" id="item-name" readonly="true" class="span4">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.purchaseorderdetail.quantity" required="true"  name="purchaseOrderDetail.quantity" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorderdetail.price" required="true"  name="purchaseOrderDetail.price" cssClass="span4" />
					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						<@s.reset key="button.reset" cssClass="btn" />
					</div>
				</@s.form>
				<#if purchaseOrder.id??>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.purchaseorderdetail.item" /></th>
							<th><@s.text name="label.admin.purchaseorderdetail.quantity" /></th>
							<th><@s.text name="label.admin.purchaseorderdetail.duedate" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#list purchaseOrderDetails.entityList as s>
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}</td>
							<td>${s.quantity!}</td>
							<td>${s.price!}</td>
						</tr>
						</#list>
					</tbody>
				</table>
				</#if>
			</div>
		</div>		
	</body>
</html>