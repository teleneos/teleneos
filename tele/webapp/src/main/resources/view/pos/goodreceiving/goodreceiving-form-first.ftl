<html>
	<head>
		<title><@s.text name="page.goodreceiving.title" /></title>
		<meta name="header" content="<@s.text name="page.goodreceiving.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
		<script type="text/javascript">
		$(function(){
			$('#duedate').datepicker();
		});
		</script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="goodReceiving.id" />
					<@s.textfield key="label.admin.goodreceiving.invoice" required="true" name="goodReceiving.invoiceNo" cssClass="span4" />
					<@s.textfield key="label.admin.goodreceiving.date" name="date" cssClass="span4" id="duedate" />
					<div class="control-group ">
						<label class="control-label" for="add_id"><span class="required">*</span> <@s.text name="label.admin.goodreceiving.businesspartner" /></label>
						<div class="controls">
							<@s.hidden name="goodReceiving.businessPartner.id" id="businessPartner-id" />
							<input type="text" id="businessPartner-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="label.admin.goodreceiving.businesspartner" />" object-name="businessPartners|name" field-target="businessPartner-id|businessPartner-name" href="<@s.url value="/pos/businesspartner" />">Choose</button>
						</div>
					</div>
					<div class="form-actions">
						<@s.submit key="button.next" cssClass="btn btn-primary" />
					</div>
				</@s.form>
			</div>
		</div>		
	</body>
</html>