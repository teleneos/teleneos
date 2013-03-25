<html>
	<head>
		<title><@s.text name="page.goodreceiving.title" /></tile>
		<meta name="header" content="<@s.text name="page.goodreceiving.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="goodReceiving.id" />
					<@s.textfield key="label.admin.goodreceiving.title" required="true" name="goodReceiving.title" cssClass="span4" />
					<@s.textfield key="label.admin.goodreceiving.description"  name="goodReceiving.description" cssClass="span6" />
					<@s.textfield key="label.admin.goodreceiving.date"  name="goodReceiving.duedate" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label" for="add_id"><@s.text name="label.admin.goodreceiving.invoice" /> <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="goodReceiving.invoice.id" id="invoice-id" />
							<input type="text" id="invoice-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.invoice.title" />" object-name="invoices|title" field-target="invoice-id|invoice-name" href="<@s.url value="/pos/invoice" />">Choose</button>
						</div>
					</div>
					<div class="form-actions">
						<#if goodReceiving.id??>
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