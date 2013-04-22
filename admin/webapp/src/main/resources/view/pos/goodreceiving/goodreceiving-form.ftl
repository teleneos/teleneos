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
					<@s.textfield key="label.admin.goodreceiving.date" name="goodReceiving.duedate" cssClass="span4" id="duedate" />
					<div class="control-group ">
						<label class="control-label" for="add_id"><span class="required">*</span> <@s.text name="label.admin.goodreceiving.businesspartner" /></label>
						<div class="controls">
							<@s.hidden name="goodReceiving.businessPartner.id" id="businessPartner-id" />
							<input type="text" id="businessPartner-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="label.admin.goodreceiving.businesspartner" />" object-name="businessPartners|name" field-target="businessPartner-id|businessPartner-name" href="<@s.url value="/pos/businesspartner" />">Choose</button>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="add_id"><span class="required">*</span> <@s.text name="label.admin.goodreceiving.item" /></label>
						<div class="controls">
							<@s.hidden name="goodReceiving.item.id" id="item-id" />
							<input type="text" id="item-code" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="label.admin.goodreceiving.item" />" object-name="items|code|name" field-target="item-id|item-code" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.goodreceiving.quantity" required="true" name="goodReceiving.quantity" cssClass="span4" />
					<@s.select name="goodReceiving.itemType.id" list="itemTypes.entityList" listValue="name" listKey="id"/>
					<@s.textarea key="label.admin.goodreceiving.description"  name="goodReceiving.description" cssClass="span4" />
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