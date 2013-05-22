<html>
	<head>
		<title><@s.text name="page.ticket.title" /></title>
		<meta name="header" content="<@s.text name="page.ticket.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
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
		</div>
		<div class="modal hide fade in" id="popup-dialog">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3 id="popup-header"></h3>
			</div>
			<div class="modal-body" style="height: 400px;">
				<form method="get" class="form-inline" id="popup-search" style="width: 100%">
					<div class="input-append" style="width: 100%">
						<input type="text" name="q" value="" placeholder="Search..." style="width: 80%;">
						<button value="Search" class="btn" style="min-width: 0; width: 15%;">
							&nbsp;<i class="icon-search"></i>&nbsp;
						</button>
					</div>
				</form>
				<div id="popup-data"></div>
			</div>
		</div>
	</body>
</html>