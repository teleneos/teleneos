<html>
<head>
<title><@s.text name="page.ticket.title" /></title>
<meta name="header" content="<@s.text name="page.ticket.header" />">
<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
<script type="text/javascript">
$(function() {
	$('#premade').change(function() {
		$('#message').val($("#premade option:selected").val());
	});
});
</script>
<style type="text/css">
th {
	text-align: left;
}

#ticketthread table.message {
	margin: 10px 0px 5px;
}

table.message,table.response,table.note {
	border: 1px solid rgb(205, 205, 205);
	border-radius: 3px 3px 3px 3px;
}


table.message th,table.response th,table.note th {
	line-height: 24px;
	font-size: 12px;
	font-weight: bold;
	text-align: left;
	padding: 1px 1px 1px 2px;
}

table.message td,table.message th,table.response td,table.response th,table.note td,table.note th
	{
	border-bottom: 1px solid;
	padding: 5px;
}

td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: rgb(62, 62, 62);
	text-decoration: none;
	border: medium none;
}

.ok {
	background: -moz-linear-gradient(center top, rgb(253, 253, 253),
		rgb(234, 234, 234) ) repeat scroll 0% 0%/100% auto rgb(234, 234, 234);
	text-shadow: 0px 1px white;
	border-bottom: 1px solid rgb(205, 205, 205);
	color: rgb(99, 99, 99);
	font-weight: 600;
}
</style>
</head>
<body>
	<div class="row-fluid">
		<#include "/view/decorator/nav/ticket-sidenav.ftl" />
		<div class="span10">
			<table border="0" cellpadding="2" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td width="50%"><h2>Ticket #${ticket.code!} ${ticket.subject!}</h2></td>
					</tr>
					<tr>
						<td width="50%">
							<table class="ticketinfo" align="center" border="0"
								cellpadding="3" cellspacing="1" width="100%">
								<tbody>
									<tr>
										<th width="30%">Status:</th>
										<td><#if ticket.logInformation.statusFlag == 'ACTIVE' >OPEN<#else>CLOSE</#if></td>
									</tr>
									<tr>
										<th>Priority:</th>
										<td>${ticket.priority!}</td>
									</tr>
									<tr>
										<th>Create Date:</th>
										<td>${ticket.logInformation.createDate!}</td>
									</tr>
									<tr>
										<th>Message:</th>
										<td><b>${ticket.message!}</b></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="top" width="50%">
							<table class="ticketinfo" align="center" border="0"
								cellpadding="3" cellspacing="1" width="40%">
								<tbody>
									<tr>
										<th>User:</th>
										<td>${ticket.logInformation.createBy!}</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table border="0" cellpadding="2" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td width="50%"><h4>Ticket Thread</h4></td>
					</tr>
					<tr>
						<div style="display: block;" id="ticketthread">
							<table class="message" align="center" border="0" cellpadding="1"
								cellspacing="0" width="100%">
								<tbody>
								<#list ticketThreads.entityList as x>
								<tr>
								<th class="ok">${x.logInformation.createDate!} by ${x.logInformation.createBy!}</th>
								</tr>
								<tr>
									<td>${x.message!}</td>
								</tr>
								</#list>
								</tbody>
								<#if ticket.logInformation.statusFlag == 'ACTIVE' >
								<tfoot>
									<tr>
										<td><h4>Post Reply</h4>
										<form action="" method="post">
											<#if currentUser.role == 'ADMINISTRATOR'>										
												<button style="margin-bottom: 10px;" class="btn openpopup" type="button" title="<@s.text name="page.user.title" />" object-name="answers|title" field-target="noid|message" href="<@s.url value="/ticket/answer" />">Premade Answer</button>
											</#if>
											<textarea style="width: 95%;" rows="" cols="" name="ticketThread.message" id="message">${ticketThread.message!}</textarea>
											<input type="submit" class="btn" value="Submit"> <#if currentUser.role == 'ADMINISTRATOR'><input type="checkbox" name="close" value="true"> Close on Reply </#if> 
										</form>
										</td>
									</tr>
								</tfoot>
								</#if>
							</table>
						</div>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>