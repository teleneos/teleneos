<html>
	<head>
		<title><@s.text name="page.itemtype.title" /></title>
		<meta name="header" content="<@s.text name="page.itemtype.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="conversion.id" />
					<div class="control-group ">
						<label class="control-label"> 
							<span class="required">*</span>
							Conversion from
						</label>
						<div class="controls">
							<input name="conversion.qty" value="${conversion.qty!}" class="span2" type="text">
							<select class="span2" name="conversion.uomFrom.id">
							<#list uoms.entityList as s>
								<option value="${s.id!}">${s.name!}</option>
							</#list>
							</select>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label"> 
							<span class="required">*</span>
							to
						</label>
						<div class="controls">
							<input name="conversion.multiplyRate" value="${conversion.multiplyRate!}" class="span2" type="text">
							<select class="span2" name="conversion.uomTo.id">
							<#list uoms.entityList as s>
								<option value="${s.id!}">${s.name!}</option>
							</#list>
							</select>
						</div>
					</div>
					<div class="form-actions">
						<#if conversion.id??>
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