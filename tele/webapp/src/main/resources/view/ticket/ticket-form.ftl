<html>
	<head>
		<title><@s.text name="page.ticket.title" /></title>
		<meta name="header" content="<@s.text name="page.ticket.header" />">
		<content tag="sidenav">/view/decorator/nav/ticket-sidenav.ftl</content>
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="ticket.id" />
				<@s.textfield key="label.ticket.subject" name="ticket.subject" value="${ticket.subject!}" cssClass="span4" required='true' />
				<@s.textarea key="label.ticket.message" name="ticket.message" value="${ticket.message!}" cssClass="span4" required='true' />
				<div class="control-group <#if fieldErrors.containsKey('ticket.category.id')>error</#if>">
					<label class="control-label" for="add_id"><span class="required">* </span>Ticket Category</label>
					<div class="controls">
						<@s.hidden name="ticket.category.id" id="category-id" />
						<input type="text" id="category-name" readonly="true" class="span4">
						<button class="btn openpopup" type="button" title="<@s.text name="page.ticket.category.title.page" />" object-name="categories|name" field-target="category-id|category-name" href="<@s.url value="/ticket/user/category" />">Choose</button>
						<#if fieldErrors.containsKey('ticket.category.id')>
							<span class="help-inline">${fieldErrors.get('ticket.category.id').get(0)?string}</span>
						</#if>
					</div>
				</div>
				<@s.select key="label.ticket.priority" name="ticket.priority" list={'LOW' : 'Low', 'NORMAL' : 'Normal', 'HIGH' : 'High'} listValue="value" listKey="key" />
				<div class="form-actions">
					<#if ticket.id??>
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