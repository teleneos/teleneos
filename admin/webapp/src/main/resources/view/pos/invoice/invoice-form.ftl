<html>
	<head>
		<title><@s.text name="page.invoice.title" /></title>
		<meta name="header" content="<@s.text name="page.invoice.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="invoice.id" />
					<@s.textfield key="label.admin.invoice.title" required="true" name="invoice.title" cssClass="span4" />
					<@s.textfield key="label.admin.invoice.description"  name="invoice.description" cssClass="span6" />
					<!-- <@s.textfield key="label.admin.invoice.date"  name="invoice.duedate" cssClass="span4" /> -->
					<div class="control-group ">
						<label class="control-label" for="add_id"><@s.text name="label.admin.invoice.purchaseorder" /> <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="invoice.purchaseOrder.id" id="purchaseOrder-id" />
							<input type="text" id="purchaseOrder-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.purchaseorder.title" />" object-name="purchaseOrders|title" field-target="purchaseOrder-id|purchaseOrder-name" href="<@s.url value="/pos/po" />">Choose</button>
						</div>
					</div>
					<div class="form-actions">
						<#if invoice.id??>
						<@s.submit key="button.update" cssClass="btn btn-primary" />
						<#else>
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						</#if>
						<@s.reset key="button.reset" cssClass="btn" />
					</div>
				</@s.form>
			</div>
		</div>		
	</body>
</html>