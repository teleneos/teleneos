<html>
	<head>
		<title><@s.text name="page.purchaseorder.title" /></tile>
		<meta name="header" content="<@s.text name="page.purchaseorder.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="purchaseOrder.id" />
					<@s.textfield key="label.admin.purchaseorder.title" required="true" name="purchaseOrder.title" cssClass="span4" />
					<@s.textfield key="label.admin.purchaseorder.description"  name="purchaseOrder.description" cssClass="span6" />
					<@s.textfield key="label.admin.purchaseorder.duedate"  name="purchaseOrder.duedate" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label" for="add_id"><@s.text name="label.admin.purchaseorder.businesspartner" /> <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="purchaseOrder.businessPartner.id" id="businessPartner-id" />
							<input type="text" id="businessPartner-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.businesspartner.title" />" object-name="businessPartners|name" field-target="businessPartner-id|businessPartner-name" href="<@s.url value="/pos/businesspartner" />">Choose</button>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="add_id"><@s.text name="label.admin.purchaseorder.requisition" /> <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="purchaseOrder.requisition.id" id="requisition-id" />
							<input type="text" id="requisition-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.requisition.title" />" object-name="requisitions|title" field-target="requisition-id|requisition-name" href="<@s.url value="/pos/requisition" />">Choose</button>
						</div>
					</div>
					<div class="form-actions">
						<#if purchaseOrder.id??>
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