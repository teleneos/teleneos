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
				<a class="btn btn-primary span2" href="<@s.url value="/admin/user/add" />">
					<i class="icon-plus icon-white"></i>
					<@s.text name="New FAQ" />
				</a>
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
							<th>FAQ</th>
							<th class="span1">Status</th>
							<th class="span1">Feature</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a>How to setup free email</a></td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td><a>New user registration</a></td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
						</tr><tr>
							<td>3</td>
							<td><a>Free internet reward</a></td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
							<td>
								<center><i class="icon-remove"></i></center>
							</td>
						</tr><tr>
							<td>4</td>
							<td><a>Cannot login to portal</a></td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
							<td>
								<center><i class="icon-remove"></i></center>
							</td>
						</tr><tr>
							<td>5</td>
							<td><a>Change password</a></td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
							<td>
								<center><i class="icon-ok"></i></center>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>