<html>
	<head>
		<title><@s.text name="page.itemtype.title" /></title>
		<meta name="header" content="<@s.text name="page.itemtype.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal" action="/pos/conversion/add">
				<@s.hidden name="conversion.id" />
				<div class="control-group  <#if fieldErrors.containsKey('conversion.multiplyRate')>error</#if>">
					<label class="control-label"> 
						<span class="required">*</span>
						Conversion from
					</label>
					<div class="controls">
						1
						<select class="span2" name="conversion.uomFrom.id">
							<#if conversion.uomFrom??>
								<option value="${conversion.uomFrom.id!}">${conversion.uomFrom.name!}</option>
							</#if>
						<#list uoms.entityList as s>
							<option value="${s.id!}">${s.name!}</option>
						</#list>
						</select>
						=
						<input name="conversion.multiplyRate" value="${conversion.multiplyRate?string('#')!}" class="span2" type="text">
						<select class="span2" name="conversion.uomTo.id">
							<#if conversion.uomTo??>
								<option value="${conversion.uomTo.id!}">${conversion.uomTo.name!}</option>
							</#if>
						<#list uoms.entityList as s>
							<option value="${s.id!}">${s.name!}</option>
						</#list>
						</select>
						<#if fieldErrors.containsKey('conversion.multiplyRate')>
							<span class="help-inline">${fieldErrors.get('conversion.multiplyRate').get(0)?string}</span>
						</#if>
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
	</body>
</html>