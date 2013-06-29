<html>
	<head>
		<title><@s.text name="page.inventoryonhand.title" /></title>
		<meta name="header" content="<@s.text name="page.inventoryonhand.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="itemCategory.id" />
				<div class="control-group ">
					<label class="control-label" for="add_transactionDetail_item_id"><@s.text name="label.admin.inventoryonhand.item" /><span class="required">*</span></label>
					<div class="controls">
						<@s.hidden name="inventoryOnhand.item.id" id="item-id" />
						<input type="text" id="item-name" value="<#if inventoryOnhand.item??>${inventoryOnhand.item.name!} </#if>" readonly="true" class="span4">
						<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|category.name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
					</div>
				</div>
				<@s.textfield key="label.admin.inventoryonhand.stock"  name="inventoryOnhand.stock" cssClass="span4" />
				<div class="form-actions">
					<#if inventoryOnhand.id??>
					<@s.submit key="button.update" cssClass="btn btn-primary" />
					<#else>
					<@s.submit key="button.save" cssClass="btn btn-primary" />
					</#if>
					<@s.reset key="button.reset" cssClass="btn" />
					<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
				</div>
			</@s.form>
		</div>		
	</body>
</html>