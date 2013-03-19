<html>
	<head>
		<title><@s.text name="page.item.title" /></tile>
		<meta name="header" content="<@s.text name="page.item.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="item.id" />
					<@s.textfield key="label.admin.item.code" required="true" name="item.code" cssClass="span4" />
					<@s.textfield key="label.admin.item.name" required="true" name="item.name" cssClass="span4" />
					<@s.textfield key="label.admin.item.description"  name="item.description" cssClass="span6" />
					<div class="control-group ">
						<label class="control-label" for="add_item_category_id">Category Name <span class="required">*</span></label>
							<div class="controls">
							<@s.hidden id="category-id" name="item.category.id" />
							<input type="text" readonly="true" value="<#if item.category??> ${item.category.name!} </#if>" id="category-name" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.itemcategory.title" />" object-name="itemCategories|name" field-target="category-id|category-name" href="<@s.url value="/pos/itemcategory" />">Choose</button>
							</div>
					</div>
					<@s.textfield key="label.admin.item.price" required="true" name="item.price" cssClass="span4" />
					
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