<html>
<head>
<title><@s.text name="page.ticket.title" /></title>
<meta name="header" content="<@s.text name="page.ticket.header" />">
<style type="text/css">
th {
	text-align: left;
}

#ticketthread table.message {
	margin: 10px 0px 5px;
}

table.message,table.response,table.note {
	border-width: 1px 1px 1px;
	border-style: solid solid none;
	-moz-border-top-colors: none;
	-moz-border-right-colors: none;
	-moz-border-bottom-colors: none;
	-moz-border-left-colors: none;
	border-image: none;
	border-color: rgb(173, 173, 173);
}

table.message th {
	background-color: rgb(195, 217, 255);
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
										<th>Status:</th>
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
								<th>${x.logInformation.createDate!} by ${x.logInformation.createBy!}</th>
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
											<textarea style="width: 95%;" rows="" cols="" name="ticketThread.message">${ticketThread.message!}</textarea>
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