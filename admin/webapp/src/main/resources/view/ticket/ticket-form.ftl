<html>
	<head>
		<title><@s.text name="page.ticket.title" /></title>
		<meta name="header" content="<@s.text name="page.ticket.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="ticket.id" />
					<@s.textfield key="label.ticket.subject" name="ticket.subject" value="${ticket.subject!}" cssClass="span4" required='true' />
					<@s.textarea key="label.ticket.message" name="ticket.message" value="${ticket.message!}" cssClass="span4" required='true' />
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
	</body>
</html>