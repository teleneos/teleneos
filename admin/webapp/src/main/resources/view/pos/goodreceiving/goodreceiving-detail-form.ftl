<html>
	<head>
		<title><@s.text name="page.goodreceivingdetail.title" /></title>
		<meta name="header" content="<@s.text name="page.goodreceivingdetail.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.textfield key="label.admin.goodreceiving.title" readonly="true" value="${goodReceiving.title!}" cssClass="span4" />
					<@s.textfield key="label.admin.goodreceiving.description" readonly="true" value="${goodReceiving.description!}" cssClass="span4" />
					<!-- <@s.textfield key="label.admin.goodreceiving.date" readonly="true" value="${goodReceiving.duedate!}" cssClass="span4" /> -->
					<@s.textfield key="label.admin.goodreceiving.purchaseorder" readonly="true" value="${goodReceiving.invoice.title!}" cssClass="span4" />
				</@s.form>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.goodreceivingdetail.item" /></th>
							<th style="text-align:center;"><@s.text name="label.admin.goodreceivingdetail.quantity" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#assign totalQnty = 0 />
						<#list invoiceDetails.entityList as s>
						<#assign subTotal = s.quantity * s.price /> 
						<tr>
							<td>${no}</td>
							<td>${s.item.name!}<input type="hidden" name="item" value="${s.item.id!}"></td>
							<td style="text-align:center;">${s.quantity!}</td>
						</tr>
						<#assign totalQnty = totalQnty + s.quantity />
						<#assign no = no + 1 />
						</#list> 
						<tr>
							<td></td>
							<td><strong><@s.text name="label.admin.tdetail.total" /></strong></td>
							<td style="text-align: center;"><strong>${totalQnty}</strong> </td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>		
	</body>
</html>