<html>
	<head>
		<title><@s.text name="page.premade.title.page" /></title>
		<meta name="header" content="<@s.text name="page.premade.header" />">
		<content tag="sidenav">/view/decorator/nav/ticket-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="answer.id" />
				<@s.textfield key="label.ticket.premade.name" name="answer.title" value="${answer.title!}" cssClass="span4" required="true"/>
				<@s.textarea key="label.ticket.premade.content" name="answer.content" value="${answer.content!}" cssClass="span4" required="true"/>
				<@s.checkboxlist key="label.ticket.premade.faq" name="answer.faq" list="{'true'}" listValue="" />
				<div class="form-actions">
					<#if answer.id??>
					<@s.submit key="button.update" cssClass="btn btn-primary" />
					<#else>
					<@s.submit key="button.save" cssClass="btn btn-primary" />
					</#if>
					<@s.reset key="button.reset" cssClass="btn" />
				</div>
			</@s.form>
		</div>
	</body>
</html>