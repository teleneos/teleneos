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
					<@s.textfield key="label.admin.goodreceiving.invoice" required="true" name="goodReceiving.invoiceNo" cssClass="span4" disabled="true" />
					<@s.textfield key="label.admin.goodreceiving.date" name="goodReceiving.date" cssClass="span4" disabled="true" required="true"/>
					<@s.textfield key="label.admin.goodreceiving.businesspartner" name="goodReceiving.businessPartner.name" cssClass="span4" disabled="true"/>
					<div class="control-group ">
						<label class="control-label" for="add_id"><span class="required">*</span> <@s.text name="label.admin.goodreceiving.item" /></label>
						<div class="controls">
							<@s.hidden name="goodReceivingDetail.item.id" id="item-id" />
							<input type="text" id="item-code" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="label.admin.goodreceiving.item" />" object-name="items|code|name" field-target="item-id|item-code" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label"> <span class="required">*</span>
							Quantity
						</label>
						<div class="controls">
							<input name="goodReceivingDetail.quantity" value="0" class="span2" type="text">
							<select class="span2" name="goodReceivingDetail.uom.id">
							<#list uoms.entityList as s>
								<option value="${s.id!}">${s.name!}</option>
							</#list>
							</select>
						</div>
					</div>
					<@s.textarea key="label.admin.goodreceiving.description"  name="goodReceivingDetail.description" cssClass="span4" />
					<div class="form-actions">
						<@s.submit key="button.add" cssClass="btn btn-primary" />
					</div>
				</@s.form>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.admin.tdetail.item" /></th>
							<th style="text-align:center;"><@s.text name="label.admin.tdetail.quantity" /></th>
							<th><@s.text name="label.admin.goodreceiving.description" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#list goodReceivingDetails.entityList as s>
							<tr>
								<td>${no}</td>
								<td>${s.item.name!}</td>
								<td style="text-align: center;">${s.quantity!} ${s.item.uom.name!}</td>
								<td>${s.description!}</td>
							</tr>
							<#assign no = no + 1 />
						</#list>
					</tbody>
				</table>
			</div>
		</div>		
	</body>
</html>