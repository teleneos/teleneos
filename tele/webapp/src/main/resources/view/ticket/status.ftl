<html>
	<head>
		<title><@s.text name="Ticket" /></title>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<meta name="header" content="<@s.text name="Tickets" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<form class="form-inline span10" method="get">
					<div class="input-append pull-right">
						<input type="text" name="q" value="${q!}" />
						<button class="btn">
							<i class="icon-search"></i>
							<@s.text name="button.search" />
						</button>
					</div>
				</form>
				</div>
				
				<table class="table table-condensed table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Ticket</th>
							<th>Date</th>
							<th>Subject</th>
							<th>Telecentre</th>
							<th>Priority</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>22389</td>
							<td>10-02-2013</td>
							<td>Cannot connect to NOC</td>
							<td>TC-0012</td>
							<td>
								<span class="label label-important">Critical</span>
							</td>
							<td>
								<span class="label label-important">Open</span>
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>22390</td>
							<td>10-02-2013</td>
							<td>Network connection down</td>
							<td>TC-0012</td>
							<td>
								<span class="label label-important">Critical</span>
							</td>
							<td>
								<span class="label label-important">Open</span>
							</td>
						</tr>
						<tr>
							<td>3</td>
							<td>22391</td>
							<td>10-02-2013</td>
							<td>Duplicate client data</td>
							<td>TC-0012</td>
							<td>
								<span class="label label-important">Major</span>
							</td>
							<td>
								<span class="label label-success">Closed</span>
							</td>
						</tr>
						<tr>
							<td>4</td>
							<td>22392</td>
							<td>10-02-2013</td>
							<td>Network disconnected every 5 hours</td>
							<td>TC-0012</td>
							<td>
								<span class="label label-warning">Trivial</span>
							</td>
							<td>
								<span class="label label-success">Closed</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>