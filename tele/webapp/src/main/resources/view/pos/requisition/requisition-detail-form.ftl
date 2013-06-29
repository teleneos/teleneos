<html>
	<head>
		<title><@s.text name="page.requisitiondetail.title" /></title>
		<meta name="header" content="<@s.text name="page.requisitiondetail.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<input type="hidden" value="${requisition.id!}" name="requisitionDetail.requisition.id"> 
				<@s.textfield key="label.admin.requisition.title" readonly="true" value="${requisition.title!}" cssClass="span4" />
				<@s.textfield key="label.admin.requisition.description" readonly="true" value="${requisition.description!}" cssClass="span4" />
				<!-- <@s.textfield key="label.admin.requisition.duedate" readonly="true" value="${requisition.duedate!}" cssClass="span4" /> -->
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
					<@s.reset key="button.reset" cssClass="btn" />
				</div>
			</@s.form>
			<#if requisition.id??>
			<table class="table table-striped table-condensed">
				<thead>
					<tr>
						<th>#</th>
						<th><@s.text name="label.admin.requisitiondetail.item" /></th>
						<th><@s.text name="label.admin.requisitiondetail.quantity" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<#list requisitionDetails.entityList as s>
					<tr>
						<td>${no}</td>
						<td>${s.item.name!}</td>
						<td>${s.quantity!}</td>
					</tr>
					<#assign no = no + 1 />
					</#list>
				</tbody>
			</table>
			</#if>
		</div>		
	</body>
</html>