<html>
	<head>
		<title><@s.text name="page.item.title" /></title>
		<meta name="header" content="<@s.text name="page.item.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="item.id" />
					<@s.textfield key="label.admin.item.code" required="true" name="item.code" cssClass="span4" />
					<@s.textfield key="label.admin.item.name" required="true" name="item.name" cssClass="span4" />
					<@s.textarea key="label.admin.item.description"  name="item.description" cssClass="span4" />
					<@s.textfield key="label.admin.item.price" required="true" name="item.price" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label" for="add_item_category_id"><@s.text name="label.admin.item.uom" /><span class="required">*</span></label>
							<div class="controls">
							<@s.hidden id="uom-id" name="item.uom.id" />
							<input type="text" readonly="true" value="<#if item.uom??> ${item.uom.name!} </#if>" id="uom-name" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.uom.title" />" object-name="uoms|name" field-target="uom-id|uom-name" href="<@s.url value="/pos/uom" />">Choose</button>
							</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="add_item_category_id"><@s.text name="label.admin.item.category" /><span class="required">*</span></label>
							<div class="controls">
							<@s.hidden id="category-id" name="item.category.id" />
							<input type="text" readonly="true" value="<#if item.category??> ${item.category.name!} </#if>" id="category-name" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.itemcategory.title" />" object-name="itemCategories|name" field-target="category-id|category-name" href="<@s.url value="/pos/itemcategory" />">Choose</button>
							</div>
					</div>
					<div class="form-actions">
						<#if item.id??>
						<@s.submit key="button.update" cssClass="btn btn-primary" />
						<#else>
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						</#if>
						<@s.reset key="button.reset" cssClass="btn" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>
			</div>
		</div>		
	</body>
</html>